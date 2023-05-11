package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {
	
	boolean errorDetected = false;
	Logger log = Logger.getLogger(getClass());

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
    public boolean passed() {
    	return !errorDetected;
    }
    
    private String objKindToString(int kind) {
    	String kindName;
    	
    	switch(kind) { // Con = 0, Var = 1, Type = 2, Meth = 3, Fld = 4, Elem=5, Prog = 6;
		case 0:
			kindName = "Con"; break;
		case 1:
			kindName = "Var"; break;
		case 2:
			kindName = "Type"; break;
		case 3:
			kindName = "Meth"; break;
		case 4:
			kindName = "Fld"; break;
		case 5:
			kindName = "Elem"; break;
		case 6:
			kindName = "Prog"; break;
		default:
			kindName = "null"; break;
    	}
    	return kindName;
    }
    
    private String structKindToString(int kind) {
    	String structKindName;
    	
    	switch(kind) { // None, Int, Char, Array, Class, Bool, Enum, Interface
		case 0:
			structKindName = "None"; break;
		case 1:
			structKindName = "Int"; break;
		case 2:
			structKindName = "Char"; break;
		case 3:
			structKindName = "Array"; break;
		case 4:
			structKindName = "Class"; break;
		case 5:
			structKindName = "Bool"; break;
		case 6:
			structKindName = "Enum"; break;
		case 7:
			structKindName = "Interface"; break;
		default:
			structKindName = "null"; break;
    	}
    	return structKindName;
    }
    
    // za ispis pri prisupu simbolickoj kontanti i lokalnoj/globalnoj promjenjivoj
    private String objNodeToString(Obj o) { // kind, name, type(struct.kind, struct.elemtype), adr, level), 
    	StringBuilder sb = new StringBuilder();
    	String kindName, structKindName, arrayTypeName = "", matrixTypeName = "";
    	
    	kindName = objKindToString(o.getKind());
    	
    	structKindName = structKindToString(o.getType().getKind());
    	if("Array".equals(structKindName) == true) { // niz ili matrica
    		arrayTypeName = structKindToString(o.getType().getElemType().getKind()); // niz
    		if("Array".equals(arrayTypeName) == true) { // matrica
    			matrixTypeName = structKindToString(o.getType().getElemType().getElemType().getKind());
    		} 
    	}
    	
    	sb.append("Objektni cvor: { ");
    	sb.append("KIND: "); sb.append(kindName); sb.append(", "); 
    	sb.append("NAME: "); sb.append(o.getName()); sb.append(", ");
    	sb.append("TYPE(Struct): { KIND: "); sb.append(structKindName); sb.append(", ");
    	
    	sb.append("ElemType: ");
    	if("Array".equals(structKindName) == true) { // niz ili matrica
    		sb.append(arrayTypeName); sb.append("Type"); // niz
    		if("Array".equals(arrayTypeName) == true) { // matrica
    			sb.append(" { TYPE(Struct): { KIND: "); sb.append(matrixTypeName);
    			sb.append("ElemType: "); sb.append(matrixTypeName); sb.append("Type"); sb.append(" }, ");
    		} 
    		sb.append(" } , ");
    	}
    	else { // prost tip
    		sb.append(structKindName); sb.append("Type"); sb.append(" } , ");
    	}
    	
    	sb.append("ADR: "); sb.append(o.getAdr()); sb.append(", ");
    	sb.append("LEVEL: "); sb.append(o.getLevel()); sb.append(" }");
    	return sb.toString();
    }
    
    private Struct currentType; // za cuvanje tipa konstanti i varijabli i postavljanje struct kod factor_new
    private int currentConstValue; // za cuvanje vrednosti konstante
    
    private boolean mainDetected = false; // za razlikovanje opsega pri ubacivanju varijabli u tabelu simbola
    
    // ProgramName ::= (ProgramName) IDENTIFIER;
    @Override
    public void visit(ProgramName programName) {
    	String progName = programName.getI1();
    	
    	programName.obj = Tab.insert(Obj.Prog, progName, Tab.noType);
    	Tab.openScope();
    }
    
    // Program ::= (Program) PROGRAM ProgramName ConstVarDeclList LEFT_BRACE MethodDecl RIGHT_BRACE;
    @Override
    public void visit(Program program){
    	Obj progObj = program.getProgramName().obj;
    	
    	Tab.chainLocalSymbols(progObj);
    	Tab.closeScope();
    }
    
    // Type ::= (Type) IDENTIFIER;
    @Override
    public void visit(Type type) {
    	Obj typeObj = Tab.find(type.getI1());
    	
    	if(typeObj != Tab.noObj) { // tip postoji
    		if(typeObj.getKind() == Obj.Type) {	// int, char, bool
    			currentType = typeObj.getType();
    		}
    		else {
    			currentType = Tab.noType;
    			report_error("GRESKA-Type: Tip nije odgovarajuci(int, char, bool)", type);
    			return;
    		}
    	}
    	else {
    		currentType = Tab.noType;
    		report_error("GRESKA-Type: Tip ne postoji u tabeli simbola", type);
    		return;
    	}
    }
    
    // ConstDecl ::= (ConstDecl) IDENTIFIER EQUALS Constant;
    @Override
    public void visit(ConstDecl constDecl) { 
    	String constName = constDecl.getI1();
    	Obj constObj = Tab.find(constName);
    	
    	if(constObj != Tab.noObj) {
    		report_error("GRESKA-ConstDecl: Ime konstante" + constName + " je vec deklarisano", constDecl);
    		return;
    	} else {
    		Constant c = constDecl.getConstant();
    		Struct cType = c.struct;
    		if(cType.assignableTo(currentType) == false) { 
    			report_error("GRESKA-ConstDecl: Neadekvatna dodela vrednosti konstanti " + constName , constDecl);
        		return;
    		}
    		constObj = Tab.insert(Obj.Con, constName, currentType);
    		constObj.setAdr(currentConstValue);
    	}
    }
    
    // Constant ::= (Constant_NUMBER) NUMBER
    @Override
    public void visit(Constant_NUMBER constant_NUMBER) { 
    	constant_NUMBER.struct = Tab.intType;
    	currentConstValue = constant_NUMBER.getN1();
    }
    
    // Constant ::= (Constant_CHARACTER) CHARACTER
    @Override
    public void visit(Constant_CHARACTER constant_CHARACTER) { 
    	constant_CHARACTER.struct = Tab.charType;
    	currentConstValue = constant_CHARACTER.getC1();
    }
    
    // Constant ::= (Constant_BOOLEAN) BOOLEAN;
    @Override
    public void visit(Constant_BOOLEAN constant_BOOLEAN) { 
    	Obj boolType = Tab.find("bool");
    	constant_BOOLEAN.struct = boolType.getType();
    	currentConstValue = constant_BOOLEAN.getB1();
    }
    
    // VarDecl ::= (VarDecl_VAR) IDENTIFIER
    @Override
    public void visit(VarDecl_VAR varDecl_VAR) {
    	String varName = varDecl_VAR.getI1();
    	Obj varObj = null;
    	
    	if(mainDetected == false) {
    		varObj = Tab.find(varName); // trazi se u globalnom opsegu
    	}
    	else {
    		varObj = Tab.currentScope.findSymbol(varName); // trazi se u lokalnom i universe opsegu
    		if(varObj == null) {
    			varObj = Tab.currentScope.getOuter().getOuter().findSymbol(varName);
    		}
    	}
    	
    	if((mainDetected == false && varObj != Tab.noObj) || (mainDetected == true && varObj != null)) {
    		report_error("GRESKA-VarDecl_VAR: Ime varijable " + varName + " je vec deklarisano", varDecl_VAR);
    		return;
    	} else {
    		Tab.insert(Obj.Var, varName, currentType);
    	}
    }
    
    // VarDecl ::= (VarDecl_ARRAY) IDENTIFIER LEFT_BRACKET RIGHT_BRACKET;
    @Override
    public void visit(VarDecl_ARRAY varDecl_ARRAY) {
    	String arrayName = varDecl_ARRAY.getI1();
    	Obj varObj = null;
    	
    	if(mainDetected == false) {
    		varObj = Tab.find(arrayName); // trazi se u globalnom opsegu
    	}
    	else {
    		varObj = Tab.currentScope.findSymbol(arrayName); // trazi se u lokalnom i universe opsegu
    		if(varObj == null) {
    			varObj = Tab.currentScope.getOuter().getOuter().findSymbol(arrayName);
    		}
    	}

    	if((mainDetected == false && varObj != Tab.noObj) || (mainDetected == true && varObj != null)) {
    		report_error("GRESKA-VarDecl_ARRAY: Ime niza " + arrayName + " je vec deklarisano", varDecl_ARRAY);
    		return;
    	} else {
    		Tab.insert(Obj.Var, arrayName, new Struct(Struct.Array, currentType));
    	}
    }
    
    // MethodName ::= (MethodName) IDENTIFIER;
    @Override
    public void visit(MethodName methodName) {
    	String methName = methodName.getI1();
    	
    	if("main".equals(methName) == false) {
    		report_error("GRESKA-MethodName: Ime metode mora biti main", methodName);
    		return;
    	}
    	mainDetected = true;
    	methodName.obj = Tab.insert(Obj.Meth, methName, Tab.noType);
    	Tab.openScope();
    }
    
    // MethodDecl ::= (MethodDecl) VOID MethodName LEFT_PARENTHESIS RIGHT_PARENTHESIS MethodVarDeclList LEFT_BRACE StatementList RIGHT_BRACE;
    @Override
    public void visit(MethodDecl methodDecl) {
    	if(mainDetected == true) {
	    	Obj methObj = methodDecl.getMethodName().obj;
		    Tab.chainLocalSymbols(methObj);
		    Tab.closeScope();
    	}
    }
    
    // Factor ::= (Factor_Designator) Designator
    @Override
    public void visit(Factor_Designator factor_Designator) {
    	Designator d = factor_Designator.getDesignator();
    	factor_Designator.struct = d.obj.getType(); 
    	
    	if(d instanceof Designator_ONE) {
    		report_info("INFO-Factor_Designator: Pristup oznaci " + d.obj.getName() + ". " + objNodeToString(d.obj) , factor_Designator);
    	}
    }
    
    // Factor ::= (Factor_NUMBER) NUMBER
    @Override
    public void visit(Factor_NUMBER factor_NUMBER) {
    	factor_NUMBER.struct = Tab.intType;
    }
    
    // Factor ::= (factor_CHARACTER) CHARACTER
    @Override
    public void visit(Factor_CHARACTER factor_CHARACTER) {
    	factor_CHARACTER.struct = Tab.charType;
    }
    
    // Factor ::= (factor_BOOLEAN) BOOLEAN
    @Override
    public void visit(Factor_BOOLEAN factor_BOOLEAN) {
    	factor_BOOLEAN.struct = Tab.find("bool").getType();
    }
    
    // Factor ::= (Factor_NEW) NEW Type LEFT_BRACKET Expr RIGHT_BRACKET
    @Override
    public void visit(Factor_NEW factor_NEW) {
    	Struct exprType = factor_NEW.getExpr().struct;
    	
    	if(exprType.equals(Tab.intType) == false) {
    		report_error("GRESKA-Factor_NEW: Tip izraza u definiciji niza nije int", factor_NEW);
    		factor_NEW.struct = Tab.noType;
    		return;
    	}
    	factor_NEW.struct = new Struct(Struct.Array, currentType);
    }
    
    // Factor ::= (Factor_Expr) LEFT_PARENTHESIS Expr RIGHT_PARENTHESIS
    @Override
    public void visit(Factor_Expr factor_Expr) {
    	factor_Expr.struct = factor_Expr.getExpr().struct;
    }
    
    // Designator ::= (Designator_ONE) IDENTIFIER
    @Override
    public void visit(Designator_ONE designator_ONE) {
    	String desName = designator_ONE.getI1();
    	Obj desObj = Tab.find(desName);
    	int desKind = desObj.getKind();
    	
    	if(desObj == Tab.noObj) {
    		report_error("GRESKA-Designator_ONE: Pristup nedeklarisanoj oznaci " + desName, designator_ONE);
    		designator_ONE.obj = Tab.noObj;
    		return;
    	}
    	else if ( (desKind == Obj.Con || desKind == Obj.Var) == false ) {
    		report_error("GRESKA-Designator_ONE: Oznaka " + desName + " nije konstanta ni varijabla", designator_ONE);
    		designator_ONE.obj = Tab.noObj;
    		return;
    	}
    	//report_info("ONE" , designator_ONE);
    	//report_info("Pristup oznaci " + desName + ". " + objNodeToString(desObj) , designator_ONE);
    	designator_ONE.obj = desObj;
    }
    
    // DesignatorArrayName ::= (DesignatorArrayName) IDENTIFIER;
    public void visit(DesignatorArrayName designatorArrayName) { 
    	String desArrayName = designatorArrayName.getI1();
    	Obj desArrayObj = Tab.find(desArrayName);
    	int desKind = desArrayObj.getKind();
    	
    	if(desArrayObj == Tab.noObj) {
    		report_error("GRESKA-Designator_ARRAYName: Pristup nedeklarisanoj oznaci niza " + desArrayName, designatorArrayName);
    		designatorArrayName.obj = Tab.noObj;
    		return;
    	}
    	else if (desKind != Obj.Var)  {
    		report_error("GRESKA-Designator_ARRAYName: Oznaka " + desArrayName + " nije varijabla", designatorArrayName);
    		designatorArrayName.obj = Tab.noObj;
    		return;
    	}
    	else if(desArrayObj.getType().getKind() != Struct.Array) {
    		report_error("GRESKA-Designator_ARRAYName: Oznaka " + desArrayName + " nije varijabla nizovnog tipa", designatorArrayName);
    		designatorArrayName.obj = Tab.noObj;
    		return;
    	}
    	// report_info("ARRAY" , designatorArrayName);
    	designatorArrayName.obj = desArrayObj;
    }
    
    // Designator ::= (designator_ARRAY_Elem) DesignatorArrayName LEFT_BRACKET Expr RIGHT_BRACKET;
    @Override
    public void visit(Designator_ARRAY_Elem designator_ARRAY_Elem) {
    		Obj danObj = designator_ARRAY_Elem.getDesignatorArrayName().obj;
    		Struct exprType = designator_ARRAY_Elem.getExpr().struct;
    		
    		if(exprType.equals(Tab.intType) == false) {
        		report_error("GRESKA-Designator_ARRAY_Elem: Tip izraza u indeksiranju elementa niza nije int", designator_ARRAY_Elem);
        		designator_ARRAY_Elem.obj = Tab.noObj;
        		return;
    		}
    		
    		if(danObj != Tab.noObj) {
	    		Struct elemType = danObj.getType().getElemType();
	    		Obj elemObj = new Obj(Obj.Elem, "elementNiza", elemType);
	    		designator_ARRAY_Elem.obj = elemObj;
    		}
    		else {
    			designator_ARRAY_Elem.obj = Tab.noObj;
    		}
    }
    
    // FactorSign ::= (FactorSign) UnaryMinus Factor;
    @Override
    public void visit(FactorSign factorSign) {
    	UnaryMinus um = factorSign.getUnaryMinus();
    	Factor f = factorSign.getFactor();
    	
    	if(um instanceof UnaryMinus_MINUS) {
    		if(f.struct.equals(Tab.intType) == true) {
    			factorSign.struct = f.struct;
    		}
    		else {
        		report_error("GRESKA-FactorSign: Negiran je tip koji nije int", factorSign);
        		factorSign.struct = Tab.noType;
        		return;
    		}
    	}
    	else {
    		factorSign.struct = f.struct;
    	}
    }
    
    // Term ::= (Term_ONE) FactorSign
    public void visit(Term_ONE term_ONE) {
    	term_ONE.struct = term_ONE.getFactorSign().struct;
    }
    
    // Term ::= (Term_MORE) Term Mulop FactorSign;
    public void visit(Term_MORE term_MORE) {
    	Struct factorType = term_MORE.getFactorSign().struct; // right operand type
    	Struct termType = term_MORE.getTerm().struct; // left operand type
    	
    	if(factorType.equals(termType) && termType.equals(Tab.intType)) {
    		term_MORE.struct = Tab.intType;
    	}
    	else {
    		report_error("GRESKA-Term_MORE: Mnoze se/Dele se/Moduluju se tipovi koji nisu oba int", term_MORE);
    		term_MORE.struct = Tab.noType;
    		return;
    	}
    }
    
    // Expr ::= (Expr_ONE) Term
    public void visit(Expr_ONE expr_ONE) {
    	expr_ONE.struct = expr_ONE.getTerm().struct;
    }
    
    // Expr ::= (Expr_MORE) Expr Addop Term;
    public void visit(Expr_MORE expr_MORE) {
    	Struct termType = expr_MORE.getTerm().struct; // right operand type
    	Struct exprType = expr_MORE.getExpr().struct; // left operand type
    	
    	if(termType.equals(exprType) && exprType.equals(Tab.intType)) {
    		expr_MORE.struct = Tab.intType;
    	}
    	else {
    		report_error("GRESKA-Expr_MORE: Sabiraju se/Oduzimaju se tipovi koji nisu oba int", expr_MORE);
    		expr_MORE.struct = Tab.noType;
    		return;
    	}
    }
    
    // DesignatorStatement ::= (DesignatorSt_Assign) Designator Assignop Expr
    public void visit(DesignatorSt_Assign designatorSt_Assign) {
    	Obj desObj = designatorSt_Assign.getDesignator().obj;
    	Struct exprType = designatorSt_Assign.getExpr().struct;
    	int kind = desObj.getKind();
    	
    	if(kind != Obj.Var && kind != Obj.Elem) {
    		report_error("GRESKA-DesignatorSt_Assign: Dodela vrednosti u "+ desObj.getName() +" nije u promenljivu ni u element niza", designatorSt_Assign);
    		return;
    	}
    	else if(exprType.assignableTo(desObj.getType()) == false) {
    		report_error("GRESKA-DesignatorSt_Assign: Dodela vrednosti u " + desObj.getName() + ",tipovi sa leve i desne strane jednakosti nisu isti", designatorSt_Assign);
    		return;
    	}
    	
    	if(designatorSt_Assign.getDesignator() instanceof Designator_ONE) {
    		report_info("INFO-DesignatorSt_Assign: Pristup oznaci " + desObj.getName() + ". " + objNodeToString(desObj) , designatorSt_Assign);
    	}	
    }
    
    // DesignatorStatement ::= (DesignatorStat_INC) Designator INCREMENT
    public void visit(DesignatorStat_INC designatorStat_INC) { 
    	Obj desObj = designatorStat_INC.getDesignator().obj;
    	int kind = desObj.getKind();
    	
    	if(kind != Obj.Var && kind != Obj.Elem) {
    		report_error("GRESKA-DesignatorStat_INC: Inkrementiranje "+ desObj.getName() +" nije inkrementiranje varijable ni elementa niza", designatorStat_INC);
    		return;
    	}
    	else if(desObj.getType().equals(Tab.intType) == false) {
    		report_error("GRESKA-DesignatorStat_INC: Inkrementiranje " + desObj.getName() + " nije inkrementiranje nad int tipom", designatorStat_INC);
    		return;
    	}
    	
    	if(designatorStat_INC.getDesignator() instanceof Designator_ONE) {
    		report_info("INFO-DesignatorStat_INC: Pristup oznaci " + desObj.getName() + ". " + objNodeToString(desObj) , designatorStat_INC);
    	}
    }
    
    // DesignatorStatement ::= (DesignatorStat_DEC) Designator DECREMENT
    public void visit(DesignatorStat_DEC designatorStat_DEC) { 
    	Obj desObj = designatorStat_DEC.getDesignator().obj;
    	int kind = desObj.getKind();
    	
    	if(kind != Obj.Var && kind != Obj.Elem) {
    		report_error("GRESKA-DesignatorStat_DEC: Dekrementiranje "+ desObj.getName() +" nije dekrementiranje varijable ni elementa niza", designatorStat_DEC);
    		return;
    	}
    	else if(desObj.getType().equals(Tab.intType) == false) {
    		report_error("GRESKA-DesignatorStat_DEC: Dekrementiranje " + desObj.getName() + " nije dekrementiranje nad int tipom", designatorStat_DEC);
    		return;
    	}
    	
    	if(designatorStat_DEC.getDesignator() instanceof Designator_ONE) {
    		report_info("INFO-DesignatorStat_DEC: Pristup oznaci " + desObj.getName() + ". " + objNodeToString(desObj) , designatorStat_DEC);
    	}
    }
    
    // MayDesignator ::= (MayDesignator_Designator) Designator
    public void visit(MayDesignator_Designator mayDesignator_Designator) {
    	Obj desObj = mayDesignator_Designator.getDesignator().obj;
    	
    	if(mayDesignator_Designator.getDesignator() instanceof Designator_ONE) {
    		report_info("INFO-MayDesignator_Designator: Pristup oznaci " + desObj.getName() + ". " + objNodeToString(desObj) , mayDesignator_Designator);
    	}	
    }
    
    // ************************************************************OBRADA
    // DesignatorStatement ::= (DesignatorStatement_List) LEFT_BRACKET DesignatorStatementList RIGHT_BRACKET EQUALS Designator;
    public void visit(DesignatorStatement_List designatorStatement_List) { 
    	Obj desObj = designatorStatement_List.getDesignator().obj;
    	
    	if(designatorStatement_List.getDesignator() instanceof Designator_ONE) {
    		report_info("INFO-DesignatorStatement_List: Pristup oznaci " + desObj.getName() + ". " + objNodeToString(desObj) , designatorStatement_List);
    	}	
    }
    
    // Statement ::= (Statement_READ) READ LEFT_PARENTHESIS Designator RIGHT_PARENTHESIS SEMICOLON
    public void visit(Statement_READ statement_READ) {
    	Obj desObj = statement_READ.getDesignator().obj;
    	int kind = desObj.getKind();
    	
    	if(kind != Obj.Var && kind != Obj.Elem) {
    		report_error("GRESKA-statement_READ: Read "+ desObj.getName() +" nije read varijable ni elementa niza", statement_READ);
    		return;
    	}
    	else if(desObj.getType().equals(Tab.intType) == false && desObj.getType().equals(Tab.charType) == false && 
    		desObj.getType().equals(Tab.find("bool").getType()) == false) {
    		report_error("GRESKA-Statement_READ: Read " + desObj.getName() + " nije read nad int/char/bool tipom", statement_READ);
    		return;
    	}
    	
    	if(statement_READ.getDesignator() instanceof Designator_ONE) {
    		report_info("INFO-Statement_READ: Pristup oznaci " + desObj.getName() + ". " + objNodeToString(desObj) , statement_READ);
    	}	
    }
    
    // Statement ::= (Statement_PRINT) PRINT LEFT_PARENTHESIS Expr MayPrintNumConst RIGHT_PARENTHESIS SEMICOLON
    public void visit(Statement_PRINT statement_PRINT) {
    	Struct exprType = statement_PRINT.getExpr().struct;
    	if(exprType.equals(Tab.intType) == false && exprType.equals(Tab.charType) == false && 
    			exprType.equals(Tab.find("bool").getType()) == false) {
        	report_error("GRESKA-Statement_PRINT: Print nema int/char/bool argument", statement_PRINT);
        	return;
        }
    }
    
}











