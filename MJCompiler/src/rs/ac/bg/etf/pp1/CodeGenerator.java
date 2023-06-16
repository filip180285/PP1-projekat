package rs.ac.bg.etf.pp1;

import java.util.LinkedList;
import java.util.List;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	
    public static final int MAIN_PC = 0; // main za A nivo krece od 0
    private List<Obj> listOfDesignators = new LinkedList<Obj>(); // za dodelu vrednosti pri raspakivanju niza
    private Obj designatorMatrix; // za cuvanje objektnog cvora matrice radi kasnijeg ucitavanja iste na stek
    
    private Obj designatorArray; // za cuvanje objektnog cvora niza kod obrade Cezar sifre
    
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
    	designatorArray = designator_ONE.obj;
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
    
    // DesignatorStatement ::= (DesignatorStat_Ceasar) Designator TILDE UnaryMinus NUMBER
    @Override
    public void visit(DesignatorStat_Ceasar designatorStat_Ceasar) { 
    	int num = designatorStat_Ceasar.getN3();
    	if(designatorStat_Ceasar.getUnaryMinus() instanceof UnaryMinus_MINUS) { num *= -1; }
    	// for(int i = 0; i < len; i++) niz[i] = (niz[i] + num) % 26
    	// 0 0 gore
    	// 0 0 niz -> 0 0 len -> jmp eq kraj(pop)
    	// 0 niz 0 niz 0 -> 0 niz 0 niz[0] -> 0 niz 0 niz[0] -> 0 niz 0 niz[0] num -> 0 niz 0 niz[0] + num % 26 -> 0
    	// 0 1 -> 1 gore
    	Code.loadConst(0); // 0
    	int gore = Code.pc;
    	Code.put(Code.dup); // 0 0
    	Code.load(designatorArray); // 0 0 arr
    	Code.put(Code.arraylength); // 0 0 5
    	int fixup = Code.pc + 1;
    	Code.putFalseJump(Code.ne, 0); // if ind == 5
    	Code.load(designatorArray); // 0 arr
    	Code.put(Code.dup2); // 0 arr 0 arr
    	Code.put(Code.dup2); // 0 arr 0 arr 0 arr
    	Code.put(Code.pop); // 0 arr 0 arr 0
    	Code.put(Code.baload); // 0 arr 0 arr[0]
    	
    	Code.put(Code.dup); // 0 arr 0 arr[0] arr[0]
    	Code.loadConst(97); // 0 arr 0 arr[0] arr[0] 97
    	int fixup2 = Code.pc + 1;
    	Code.putFalseJump(Code.lt, 0); // if(arr[0] >= 97) dole  0 arr 0 arr[0] 
    	
    	Code.loadConst(65);  // 0 arr 0 arr[0] 65
    	Code.put(Code.sub); // 0 arr 0 arr[0]-65
    	Code.loadConst(26);  // 0 arr 0 arr[0]-65 26
    	Code.put(Code.rem); // 0 arr 0 arr[0]-65 % 26
    	Code.loadConst(num); // 0 arr 0 arr[0]-65 % 26 pom
    	Code.put(Code.add); // 0 arr 0 arr[0]-65 % 26 + pom
    	Code.loadConst(26);  // 0 arr 0 arr[0]-65 % 26 + pom 26
    	Code.put(Code.rem); //0 arr 0 arr[0]-65 % 26 + pom % 26
    	Code.loadConst(65);  // 0 arr 0 arr[0]-65 % 26 + pom % 26 65
    	Code.put(Code.add); // 0 arr 0 arr[0]-65 % 26 + pom % 26 + 65
    	Code.put(Code.bastore); // 0
    	int fixup3 = Code.pc + 1;
    	Code.putJump(0);
    	
    	Code.fixup(fixup2); // dole
    	Code.loadConst(97);  // 0 arr 0 arr[0] 97
    	Code.put(Code.sub); // 0 arr 0 arr[0]-97
    	Code.loadConst(26);  // 0 arr 0 arr[0]-97 26
    	Code.put(Code.rem); // 0 arr 0 arr[0]-97 % 26
    	Code.loadConst(num); // 0 arr 0 arr[0]-97 % 26 pom
    	Code.put(Code.add); // 0 arr 0 arr[0]-97 % 26 + pom
    	Code.loadConst(26);  // 0 arr 0 arr[0]-97 % 26 + pom 26
    	Code.put(Code.rem); //0 arr 0 arr[0]-65 % 26 + pom % 26
    	Code.loadConst(97);  // 0 arr 0 arr[0]-65 % 26 + pom % 26 97
    	Code.put(Code.add); // 0 arr 0 arr[0]-65 % 26 + pom % 26 + 97
    	Code.put(Code.bastore); // 0
    	
    	Code.fixup(fixup3);
    	Code.loadConst(1); // 0 1
    	Code.put(Code.add); // 1
    	Code.putJump(gore);
    	//kraj
    	Code.fixup(fixup); // 5
    	Code.put(Code.pop); //
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
    	
    	Code.put(Code.read);
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
