package rs.ac.bg.etf.pp1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	
    public static final int MAIN_PC = 0; // main za A nivo krece od 0
    private List<Obj> listOfDesignators = new LinkedList<Obj>(); // za dodelu vrednosti pri raspakivanju niza
    private Obj designatorMatrix; // za cuvanje objektnog cvora matrice radi kasnijeg ucitavanja iste na stek
    
    private HashMap<String, Integer> addresses = new HashMap<String, Integer>(); // par labela - adresa
    private HashMap<String, LinkedList<Integer>> listToFill = new HashMap<String, LinkedList<Integer>>(); // par labela - sve adrese koje skacu na labelu
    
    
    public static final int LENGTH_EXCEPTION = -1;

    // MethodName ::= (MethodName) IDENTIFIER;
    @Override
    public void visit(MethodName methodName) { // enter nArg, nLoc 
    	Obj methodObj = methodName.obj;
    	methodName.obj.setAdr(Code.pc);
    	
    	Code.put(Code.enter);
    	Code.put(methodObj.getLevel());
    	Code.put(methodObj.getLocalSymbols().size());
    }
    
    // Statement ::= (Statement_RETURN) RETURN SEMICOLON
    @Override
    public void visit(Statement_RETURN statement_RETURN) {
    	Code.put(Code.exit);
    	Code.put(Code.return_);
    }
    
    // MethodDecl ::= (MethodDecl) VOID MethodName LEFT_PARENTHESIS RIGHT_PARENTHESIS MethodVarDeclList LEFT_BRACE StatementList RIGHT_BRACE;
    @Override
    public void visit(MethodDecl methodDecl) {   	
    	Code.put(Code.exit);
    	Code.put(Code.return_);
    }
    
    //Label ::= (Label) IDENTIFIER;
    @Override
    public void visit(Label label) {
    	LinkedList<Integer> list = listToFill.get(label.getI1());
    	if(list != null) {
    		while(list.isEmpty() == false) {
    			Code.fixup(list.removeFirst());
    		}
    	}
    	addresses.put(label.getI1(), Code.pc);
    }
    
    // Statement ::= (Statement_GOTO) GOTO IDENTIFIER SEMICOLON
    @Override
    public void visit(Statement_GOTO statement_GOTO) {
    	LinkedList<Integer> list = listToFill.get(statement_GOTO.getI1());
    	Integer adr = addresses.get(statement_GOTO.getI1());
    	int fixup = Code.pc + 1;
    	if(adr == null) {
        	Code.putJump(0);
	    	if(list == null) {
	    		list = new LinkedList<Integer>();
	    	}
	    	list.add(fixup);
	    	listToFill.put(statement_GOTO.getI1(), list);
	    }
    	else {
    		Code.putJump(adr);
    	}
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
    
    // NEW_Array ::=  (NEW_Array) NEW Type LEFT_BRACKET Expr RIGHT_BRACKET;
    @Override
    public void visit(NEW_Array new_Array) {
    	Code.put(Code.newarray);
    	Struct arrType = new_Array.getType().struct;
    	
    	if( ((Factor_NEW)new_Array.getParent()).getNEW_Matrix() instanceof NEW_Matrix_MATRIX ) {
    		Code.put(1);
    		Code.put(Code.dup);
    		Code.store(designatorMatrix);
    		Code.loadConst(0);
    		Code.put(Code.dup2);
    		return;
    	} 
    	
    	if(arrType.equals(Tab.charType)) {
    		Code.put(0); // za karaktere
    	}
    	else {
    		Code.put(1); // za int i bool
    	}
    }
    
    // NEW_Matrix ::=  (NEW_Matrix_MATRIX) LEFT_BRACKET Expr RIGHT_BRACKET
    @Override
    public void visit(NEW_Matrix_MATRIX new_Matrix_MATRIX) {
    	Struct matrixType = designatorMatrix.getType().getElemType().getElemType();
    	int retAddress = Code.pc;
    	Code.put(Code.newarray);
    	if(matrixType.equals(Tab.charType)) {
    		Code.put(0); // za karaktere
    	}
    	else {
    		Code.put(1); // za int i bool
    	}
    	Code.put(Code.astore);
    	Code.loadConst(1);
    	Code.put(Code.add);
    	Code.put(Code.dup2);
    	Code.load(designatorMatrix);
    	Code.put(Code.arraylength);
    	int fixup = Code.pc + 1;
    	Code.putFalseJump(Code.lt, 0);
    	Code.put(Code.dup2);
    	Code.put(Code.pop);
    	Code.load(designatorMatrix);
    	Code.loadConst(0);
    	Code.put(Code.aload);
    	Code.put(Code.arraylength);
    	Code.putJump(retAddress);
    	Code.fixup(fixup);
    	Code.put(Code.pop);
    	Code.put(Code.pop);
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
    	Code.load(desObj);
    	// designatorMatrix = desObj;
    }
    
    // Designator ::= (Designator_ONE) IDENTIFIER
    @Override
    public void visit(Designator_ONE designator_ONE) { 
    	if(designator_ONE.getParent() instanceof DesignatorSt_Assign) {
    		designatorMatrix = designator_ONE.obj;
    		// System.out.println(designatorMatrix.getName());
    	}
    }
    
    // MayMatrix ::= (MayMatrix_MATRIX) LEFT_BRACKET Expr RIGHT_BRACKET
    @Override
    public void visit(MayMatrix_MATRIX mayMatrix_MATRIX) { 
    	Code.put(Code.dup_x2);
    	Code.put(Code.pop);
    	Code.put(Code.aload);
    	Code.put(Code.dup_x1);
    	Code.put(Code.pop);
    }
    
    // DesignatorStatement ::= (DesignatorStat_INC) Designator INCREMENT
    @Override
    public void visit(DesignatorStat_INC designatorStat_INC) { 
    	Obj desObj = designatorStat_INC.getDesignator().obj;
    	
    	if(desObj.getKind() == Obj.Elem) { 
    		Code.put(Code.dup2); // za store
    	} 
    	Code.load(desObj);
    	Code.loadConst(1);
    	Code.put(Code.add);
    	Code.store(desObj);
    }
    
    // DesignatorStatement ::= (DesignatorStat_DEC) Designator DECREMENT
    @Override
    public void visit(DesignatorStat_DEC designatorStat_DEC) { 
    	Obj desObj = designatorStat_DEC.getDesignator().obj;
    	
    	if(desObj.getKind() == Obj.Elem) {
    		Code.put(Code.dup2);  // za store
    	}
    	Code.load(desObj);
    	Code.loadConst(-1);
    	Code.put(Code.add);
    	Code.store(desObj);
    }
    
    
    // DesignatorStatement ::= (DesignatorSt_Assign) Designator Assignop Expr
    @Override
    public void visit(DesignatorSt_Assign designatorSt_Assign) {
    	Obj desObj = designatorSt_Assign.getDesignator().obj;
    	
    	Code.store(desObj);
    }
    
   // MayDesignator ::= (MayDesignator_Designator) Designator
    @Override
    public void visit(MayDesignator_Designator mayDesignator_Designator) {
    	Obj desObj = mayDesignator_Designator.getDesignator().obj;
    	listOfDesignators.add(desObj);
    }
    
   // MayDesignator ::=  (MayDesignator_EPSILON) /* epsilon */
    @Override
    public void visit(MayDesignator_EPSILON mayDesignator_EPSILON) {
    	listOfDesignators.add(Tab.noObj);
    }
    
    // DesignatorStatement ::= (DesignatorStatement_List) LEFT_BRACKET DesignatorStatementList RIGHT_BRACKET EQUALS Designator;
    @Override
    public void visit(DesignatorStatement_List designatorStatement_List) {
    	Obj desArrayObj = designatorStatement_List.getDesignator().obj;
    	
		Code.loadConst(listOfDesignators.size()); 
		Code.load(desArrayObj);
		Code.put(Code.arraylength); 
		int fixupAdr = Code.pc + 1;
		Code.putFalseJump(Code.gt, 0);
		Code.put(Code.trap);
		Code.put(LENGTH_EXCEPTION);
		Code.fixup(fixupAdr);
    	
    	for(int index = listOfDesignators.size() - 1; index >= 0; index--) {
    		Obj desObj = listOfDesignators.remove(index);
    		
    		if(desObj == Tab.noObj) { 
    			continue; 
    		}
    		Code.load(desArrayObj);
    		Code.loadConst(index);
    		if(desArrayObj.getType().getElemType().equals(Tab.charType)) {
    			Code.put(Code.baload);
    		}
    		else { 
    			Code.put(Code.aload);
    		}
    		Code.store(desObj);
    	}
    	listOfDesignators = new LinkedList<Obj>();
    }
    
    // Statement ::= (Statement_READ) READ LEFT_PARENTHESIS Designator RIGHT_PARENTHESIS SEMICOLON
    @Override
    public void visit(Statement_READ statement_READ) {
    	Obj desObj = statement_READ.getDesignator().obj;
    	
    	if(desObj.getType().equals(Tab.charType)) Code.put(Code.bread);
    	else Code.put(Code.read);
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
