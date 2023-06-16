package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROGRAM, yytext());}
"const"		{ return new_symbol(sym.CONST, yytext()); }
"new" 		{ return new_symbol(sym.NEW, yytext()); }
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"read" 		{ return new_symbol(sym.READ, yytext()); }
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }

"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"-" 		{ return new_symbol(sym.MINUS, yytext()); }
"*" 		{ return new_symbol(sym.MULTIPLICATION, yytext()); }
"/" 		{ return new_symbol(sym.DIVISION, yytext()); }
"%" 		{ return new_symbol(sym.MODULUS, yytext()); }
"=" 		{ return new_symbol(sym.EQUALS, yytext()); }
"++" 		{ return new_symbol(sym.INCREMENT, yytext()); }
"--" 		{ return new_symbol(sym.DECREMENT, yytext()); }
";" 		{ return new_symbol(sym.SEMICOLON, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"(" 		{ return new_symbol(sym.LEFT_PARENTHESIS, yytext()); }
")" 		{ return new_symbol(sym.RIGHT_PARENTHESIS, yytext()); }
"[" 		{ return new_symbol(sym.LEFT_BRACKET, yytext()); }
"]" 		{ return new_symbol(sym.RIGHT_BRACKET, yytext()); }
"{" 		{ return new_symbol(sym.LEFT_BRACE, yytext()); }
"}" 		{ return new_symbol(sym.RIGHT_BRACE, yytext()); }
"~" 		{ return new_symbol(sym.TILDE, yytext()); }

"//" 			 { yybegin(COMMENT); }
<COMMENT> . 	 { yybegin(COMMENT); }
<COMMENT> "\r\n" { yybegin(YYINITIAL); }

[0-9]+  				{ return new_symbol(sym.NUMBER, new Integer (yytext())); }
"'"."'"  				{ return new_symbol(sym.CHARACTER, new Character (yytext().charAt(1))); }
"false"  				{ return new_symbol(sym.BOOLEAN, 0); }
"true"  				{ return new_symbol(sym.BOOLEAN, 1); }
[a-zA-Z][a-zA-Z0-9_]*	{return new_symbol (sym.IDENTIFIER, yytext()); }

. { System.err.println("Leksicka greska (" + yytext() + ") na liniji " + (yyline + 1) + ", na koloni " + (yycolumn + 1) + "\n"); }
