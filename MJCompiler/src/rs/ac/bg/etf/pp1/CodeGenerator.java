package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

    // MethodName ::= (MethodName) IDENTIFIER;
    @Override
    public void visit(MethodName methodName) { // enter nArg, nLoc 
    	Code.put(Code.enter);
    	Obj methodObj = methodName.obj;
    	Code.put(methodObj.getLevel());
    	Code.put(methodObj.getLocalSymbols().size());
    }
    
    // MethodDecl ::= (MethodDecl) VOID MethodName LEFT_PARENTHESIS RIGHT_PARENTHESIS MethodVarDeclList LEFT_BRACE StatementList RIGHT_BRACE;
    @Override
    public void visit(MethodDecl methodDecl) {
    	Code.put(Code.exit);
    	Code.put(Code.return_);
    }
    
    // Factor ::= (Factor_Designator) Designator
    @Override
    public void visit(Factor_Designator factor_Designator) {
    	Obj desObj = factor_Designator.getDesignator().obj;
    	Code.load(desObj);
    }
    
    // Factor ::= (Factor_NUMBER) NUMBER
    @Override
    public void visit(Factor_NUMBER factor_NUMBER) {
    	int numVal = factor_NUMBER.getN1();
    	Code.loadConst(numVal);
    }
    
    // Factor ::= (factor_CHARACTER) CHARACTER
    @Override
    public void visit(Factor_CHARACTER factor_CHARACTER) {
    	int charVal = factor_CHARACTER.getC1();
    	Code.loadConst(charVal);
    }
    
    // Factor ::= (factor_BOOLEAN) BOOLEAN
    @Override
    public void visit(Factor_BOOLEAN factor_BOOLEAN) {
    	int boolVal = factor_BOOLEAN.getB1();
    	Code.loadConst(boolVal);
    }
    
    // Factor ::= (Factor_NEW) NEW Type LEFT_BRACKET Expr RIGHT_BRACKET
    @Override
    public void visit(Factor_NEW factor_NEW) {

    }
    
    // Factor ::= (Factor_NEW_MATRIX) NEW Type LEFT_BRACKET Expr RIGHT_BRACKET LEFT_BRACKET Expr RIGHT_BRACKET;
    @Override
    public void visit(Factor_NEW_MATRIX factor_NEW_MATRIX) {

    }
    
    // FactorSign ::= (FactorSign) UnaryMinus Factor;
    @Override
    public void visit(FactorSign factorSign) {
    	UnaryMinus um = factorSign.getUnaryMinus();
    	if(um instanceof UnaryMinus_MINUS) {
    		Code.put(Code.neg);
    	}
    }
    
    // Term ::= (Term_MORE) Term Mulop FactorSign;
    @Override
    public void visit(Term_MORE term_MORE) {
    	Mulop operation = term_MORE.getMulop();
    	
    	if(operation instanceof Mulop_MUL) {
    		Code.put(Code.mul);
    	}
    	else if(operation instanceof Mulop_DIV){
    		Code.put(Code.div);
    	}
    	else {
    		Code.put(Code.rem);
    	}
    }
    
    // Expr ::= (Expr_MORE) Expr Addop Term;
    @Override
    public void visit(Expr_MORE expr_MORE) {
    	Addop operation = expr_MORE.getAddop();
    	
    	if(operation instanceof Addop_PLUS) {
    		Code.put(Code.add);
    	}
    	else {
    		Code.put(Code.sub);
    	}
    }
    
    // MayPrintNumConst ::= (MayPrintNumConst_NUMBER) COMMA NUMBER
    @Override
    public void visit(MayPrintNumConst_NUMBER mayPrintNumConst_NUMBER) {
    	int width = mayPrintNumConst_NUMBER.getN1();
    	Code.loadConst(width);
    }
    
    // MayPrintNumConst ::= (MayPrintNumConst_EPSILON) /* epsilon */
    @Override
    public void visit(MayPrintNumConst_EPSILON mayPrintNumConst_EPSILON) {
    	int width = 0; // po defaultu 0
    	Code.loadConst(width);
    }
    
    // DesignatorArrayOrMatrixName ::= (DesignatorArrayOrMatrixName) IDENTIFIER;
    @Override
    public void visit(DesignatorArrayOrMatrixName designatorArrayOrMatrixName) { 
    	Obj desObj = designatorArrayOrMatrixName.obj;
    	Code.store(desObj);
    }
    
    // DesignatorStatement ::= (DesignatorSt_Assign) Designator Assignop Expr
    @Override
    public void visit(DesignatorSt_Assign designatorSt_Assign) {
    	Obj desObj = designatorSt_Assign.getDesignator().obj;
    	Code.store(desObj);
    }
    
    // Statement ::= (Statement_PRINT) PRINT LEFT_PARENTHESIS Expr MayPrintNumConst RIGHT_PARENTHESIS SEMICOLON
    @Override
    public void visit(Statement_PRINT statement_PRINT) {
    	Struct exprType = statement_PRINT.getExpr().struct;
    	
    	if(exprType.equals(Tab.charType) == true) { // print char
    		Code.put(Code.bprint);
    	}
    	else { // print int
    		Code.put(Code.print);
    	}
    }
	
	
}
