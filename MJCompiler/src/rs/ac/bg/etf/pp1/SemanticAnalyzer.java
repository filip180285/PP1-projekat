package rs.ac.bg.etf.pp1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
    
    private Struct currentType; // za cuvanje tipa konstanti i varijabli i postavljanje struct kod factor_new
    private int currentConstValue; // za cuvanje vrednosti konstante
    
    private boolean mainDetected = false; // za razlikovanje opsega pri ubacivanju varijabli u tabelu simbola
    private List<Obj> listOfDesignators = new LinkedList<Obj>(); // za proveru tipova pri raspakivanju niza
    private Struct exprTypeArray; // za cuvanje tipa niza radi ispitivanja u matrici
    
    private int numGlobalVars = 0; // broj statickih varijabli
    
    public int getNumGlobalVars() {
    	return numGlobalVars;
    }
    
    // String(objKind)
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
    
    // String(structKind)
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
    
    private String firstCharToLower(String name) {
		char[] chars = name.toCharArray();
		chars[0] = Character.toLowerCase(chars[0]);;
		name = String.valueOf(chars); 
		return name;
    }
    
    private String structNodeToString(Struct s) { // za ispis strukturnog cvora radi testiranja
    	StringBuilder sb = new StringBuilder();
    	sb.append("Strukturni cvor: { ");
    	sb.append("KIND: "); sb.append(structKindToString(s.getKind())); sb.append(" ");
    	while(s.getElemType() != null) {
    		s = s.getElemType();
    		sb.append("Strukturni cvor: { ");
    		sb.append("KIND: "); sb.append(structKindToString(s.getKind())); sb.append(" ");
    		sb.append("} ");
    	}
    	sb.append("}");
    	return sb.toString();
    }
    
    // za ispis pri prisupu simbolickoj kontanti i lokalnoj/globalnoj promjenjivoj
    private String objNodeToString(Obj o) { // kind, name, type(struct.kind, struct.elemtype), adr, level), 
    	StringBuilder sb = new StringBuilder();
    	String kindName, structKindName, arrayTypeName = "", matrixTypeName = "";
    	
    	kindName = objKindToString(o.getKind());
    	
    	structKindName = structKindToString(o.getType().getKind());
    	if("Array".equalsIgnoreCase(structKindName) == true) { // niz ili matrica
    		arrayTypeName = structKindToString(o.getType().getElemType().getKind()); // niz
            arrayTypeName = firstCharToLower(arrayTypeName); 
    		if("Array".equalsIgnoreCase(arrayTypeName) == true) { // matrica
    			matrixTypeName = structKindToString(o.getType().getElemType().getElemType().getKind());
    			//matrixTypeName = firstCharToLower(matrixTypeName);  
    		} 
    	}
    	
    	sb.append("Objektni cvor: { ");
    	sb.append("KIND: "); sb.append(kindName); sb.append(", "); 
    	sb.append("NAME: "); sb.append(o.getName()); sb.append(", ");
    	sb.append("TYPE(Struct): { KIND: "); sb.append(structKindName); sb.append(", ");
    	
    	structKindName = firstCharToLower(structKindName);
    	
    	sb.append("ElemType: ");
    	if("Array".equalsIgnoreCase(structKindName) == true) { // niz ili matrica
    		sb.append(arrayTypeName); sb.append("Type"); // niz
    		if("Array".equalsIgnoreCase(arrayTypeName) == true) { // matrica
    			sb.append(" { TYPE(Struct): { KIND: "); sb.append(matrixTypeName);
    			matrixTypeName = firstCharToLower(matrixTypeName);  
    			sb.append(", ElemType: "); sb.append(matrixTypeName); sb.append("Type"); sb.append(" }");
    		} 
    		sb.append(" }, ");
    	}
    	else { // prost tip
    		sb.append(structKindName); sb.append("Type"); sb.append(" }, ");
    	}
    	
    	sb.append("ADR: "); sb.append(o.getAdr()); sb.append(", ");
    	sb.append("LEVEL: "); sb.append(o.getLevel()); sb.append(" }");
    	return sb.toString();
    }
    
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
    	
    	numGlobalVars = Tab.currentScope().getnVars();
    	Tab.chainLocalSymbols(progObj);
    	Tab.closeScope();
    }
    
    // Type ::= (Type) IDENTIFIER;
    @Override
    public void visit(Type type) {
    	Obj typeObj = Tab.find(type.getI1());
    	
    	if(typeObj != Tab.noObj) { // tip postoji
    		if(typeObj.getKind() == Obj.Type) {	// int, char, bool
    			currentType = type.struct = typeObj.getType();
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
    		report_error("GRESKA-ConstDecl: Ime konstante " + constName + " je vec deklarisano", constDecl);
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
    
    // VarDecl ::= (VarDecl_MATRIX) IDENTIFIER LEFT_BRACKET RIGHT_BRACKET LEFT_BRACKET RIGHT_BRACKET;
    @Override
    public void visit(VarDecl_MATRIX varDecl_MATRIX) {
    	String matrixName = varDecl_MATRIX.getI1();
    	Obj varObj = null;
    	
    	if(mainDetected == false) {
    		varObj = Tab.find(matrixName); // trazi se u globalnom opsegu
    	}
    	else {
    		varObj = Tab.currentScope.findSymbol(matrixName); // trazi se u lokalnom i universe opsegu
    		if(varObj == null) {
    			varObj = Tab.currentScope.getOuter().getOuter().findSymbol(matrixName);
    		}
    	}

    	if((mainDetected == false && varObj != Tab.noObj) || (mainDetected == true && varObj != null)) {
    		report_error("GRESKA-VarDecl_MATRIX: Ime matrice " + matrixName + " je vec deklarisano", varDecl_MATRIX);
    		return;
    	} else {
    		Tab.insert(Obj.Var, matrixName, new Struct(Struct.Array, new Struct(Struct.Array, currentType)));
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
    
    // Factor ::= (Factor_Subfactor) Subfactor
    @Override
    public void visit(Factor_Subfactor factor_Subfactor) {
    	factor_Subfactor.struct = factor_Subfactor.getSubfactor().struct;
    }
    
    // Factor ::= (Factor_Factorial) Subfactor FACTORIAL;
    @Override
    public void visit(Factor_Factorial factor_Factorial) {
    	Struct type = factor_Factorial.getSubfactor().struct;
    	
    	if(type.equals(Tab.intType) == false) {
    		report_error("GRESKA-Factor_Factorial: Faktorijal nije pozvan nad int tipom", factor_Factorial);
    		return;
    	}
    	
    	factor_Factorial.struct = type;
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
    
    // NEW_Array ::=  (NEW_Array) NEW Type LEFT_BRACKET Expr RIGHT_BRACKET;
    @Override
    public void visit(NEW_Array new_Array) {
    	exprTypeArray = new_Array.getExpr().struct;
    	
    	if(exprTypeArray.equals(Tab.intType) == false) {
    		report_error("GRESKA-Factor_NEW: Tip izraza u definiciji niza nije int", new_Array);
    		new_Array.struct = Tab.noType;
    		return;
    	}
    	new_Array.struct = new Struct(Struct.Array, currentType);
    }
    
    // NEW_Matrix ::=  (NEW_Matrix_MATRIX) LEFT_BRACKET Expr RIGHT_BRACKET
    @Override
    public void visit(NEW_Matrix_MATRIX new_Matrix_MATRIX) {
    	// Struct exprType1 = ((Factor_NEW)new_Matrix_MATRIX.getParent()).getNEW_Array().getExpr().struct;
    	Struct exprType2 = new_Matrix_MATRIX.getExpr().struct;
    	
    	if(exprTypeArray.equals(Tab.intType) == false || exprType2.equals(Tab.intType) == false) {
    		report_error("GRESKA-Factor_NEW_MATRIX: Tip izraza u definiciji matrice nije int", new_Matrix_MATRIX);
    		new_Matrix_MATRIX.struct = Tab.noType;
    		return;
    	}
    	new_Matrix_MATRIX.struct = new Struct(Struct.Array, new Struct(Struct.Array, currentType));
    }
    
    // Factor ::= (Factor_NEW) NEW_Array NEW_Matrix
    @Override
    public void visit(Factor_NEW factor_NEW) {
    	factor_NEW.struct = factor_NEW.getNEW_Array().struct; // za niz
    	
    	if(factor_NEW.getNEW_Matrix() instanceof NEW_Matrix_MATRIX) { // za matricu
    		factor_NEW.struct = factor_NEW.getNEW_Matrix().struct;
    	}
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
    
    // DesignatorArrayOrMatrixName ::= (DesignatorArrayOrMatrixName) IDENTIFIER;
    @Override
    public void visit(DesignatorArrayOrMatrixName designatorArrayOrMatrixName) { 
    	String desArrayMatrixName = designatorArrayOrMatrixName.getI1();
    	Obj desArrayMatrixObj = Tab.find(desArrayMatrixName);
    	int desKind = desArrayMatrixObj.getKind();
    	
    	if(desArrayMatrixObj == Tab.noObj) {
    		report_error("GRESKA-designatorArrayOrMatrixName: Pristup nedeklarisanoj oznaci niza " + desArrayMatrixName, designatorArrayOrMatrixName);
    		designatorArrayOrMatrixName.obj = Tab.noObj;
    		return;
    	}
    	else if (desKind != Obj.Var)  {
    		report_error("GRESKA-designatorArrayOrMatrixName: Oznaka " + desArrayMatrixName + " nije varijabla", designatorArrayOrMatrixName);
    		designatorArrayOrMatrixName.obj = Tab.noObj;
    		return;
    	}
    	else if(desArrayMatrixObj.getType().getKind() != Struct.Array) {
    		report_error("GRESKA-designatorArrayOrMatrixName: Oznaka " + desArrayMatrixName + " nije varijabla nizovnog/matricnog tipa", designatorArrayOrMatrixName);
    		designatorArrayOrMatrixName.obj = Tab.noObj;
    		return;
    	}
    	// report_info("ARRAY" , designatorArrayName);
    	designatorArrayOrMatrixName.obj = desArrayMatrixObj;
    }
    
    // Designator ::= (Designator_Elem) DesignatorArrayOrMatrixName LEFT_BRACKET Expr RIGHT_BRACKET MayMatrix;
    @Override
    public void visit(Designator_Elem designator_Elem) {
    	Obj danObj = designator_Elem.getDesignatorArrayOrMatrixName().obj;
    	Struct exprType = designator_Elem.getExpr().struct;
    	MayMatrix m = designator_Elem.getMayMatrix();
    		
    	if(m instanceof MayMatrix_EPSILON) { // niz
		    if(exprType.equals(Tab.intType) == false) {
		        report_error("GRESKA-Designator_Elem: Tip izraza u indeksiranju elementa niza nije int", designator_Elem);
		        designator_Elem.obj = Tab.noObj;
		        return;
		    }
		    		
		    if(danObj != Tab.noObj) {
			    Struct elemType = danObj.getType().getElemType();
			    Obj elemObj = new Obj(Obj.Elem, "elementNiza", elemType); // ili niz matrice
			    designator_Elem.obj = elemObj;
		    }
		    else {
		    	designator_Elem.obj = Tab.noObj;
		    }
    	}
    	else { // matrica
		    if(exprType.equals(Tab.intType) == false) {
		        report_error("GRESKA-Designator_Elem: Tip izraza u indeksiranju reda elementa matrice nije int", designator_Elem);
		        designator_Elem.obj = Tab.noObj;
		        return;
		    }
		    
    		MayMatrix_MATRIX mat = (MayMatrix_MATRIX)m;
    		Struct exprType2 = mat.getExpr().struct;
    		
		    if(exprType2.equals(Tab.intType) == false) {
		        report_error("GRESKA-Designator_Elem: Tip izraza u indeksiranju kolone elementa matrice nije int", designator_Elem);
		        designator_Elem.obj = Tab.noObj;
		        return;
		    }
		    		
		    if(danObj != Tab.noObj) {
			    Struct elemType = danObj.getType().getElemType().getElemType();
			    if(elemType == null) {
			        report_error("GRESKA-Designator_Elem: Nizu " + danObj.getName() + " se pristupa kao matrici", designator_Elem);
			        designator_Elem.obj = Tab.noObj;
			        return;
			    }
			    Obj elemObj = new Obj(Obj.Elem, "elementMatrice", elemType);
			    designator_Elem.obj = elemObj;
		    }
		    else {
		    	designator_Elem.obj = Tab.noObj;
		    }
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
    @Override
    public void visit(Term_ONE term_ONE) {
    	term_ONE.struct = term_ONE.getFactorSign().struct;
    }
    
    // Term ::= (Term_MORE) Term Mulop FactorSign;
    @Override
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
    @Override
    public void visit(Expr_ONE expr_ONE) {
    	expr_ONE.struct = expr_ONE.getTerm().struct;
    }
    
    // Expr ::= (Expr_MORE) Expr Addop Term;
    @Override
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
    @Override
    public void visit(DesignatorSt_Assign designatorSt_Assign) {
    	Obj desObj = designatorSt_Assign.getDesignator().obj;
    	Struct exprType = designatorSt_Assign.getExpr().struct;
    	int kind = desObj.getKind();
    	
    	if(kind != Obj.Var && kind != Obj.Elem) {
    		report_error("GRESKA-DesignatorSt_Assign: Dodela vrednosti u "+ desObj.getName() +" nije u promenljivu ni u element niza/matrice", designatorSt_Assign);
    		return;
    	}
    	else if(exprType.assignableTo(desObj.getType()) == false) {
    		report_error("GRESKA-DesignatorSt_Assign: Dodela vrednosti u " + desObj.getName() + ",tipovi sa leve i desne strane jednakosti nisu isti", designatorSt_Assign);
    		return;
    	}
    	
    	//report_info("Tip leve strane " + desObj.getName() + " " + objNodeToString(desObj) , designatorSt_Assign);
    	//report_info("Tip desne strane " + exprType.getKind()  , designatorSt_Assign);
    	
    	if(designatorSt_Assign.getDesignator() instanceof Designator_ONE) {
    		report_info("INFO-DesignatorSt_Assign: Pristup oznaci " + desObj.getName() + ". " + objNodeToString(desObj) , designatorSt_Assign);
    	}	
    }
    
    // DesignatorStatement ::= (DesignatorStat_INC) Designator INCREMENT
    @Override
    public void visit(DesignatorStat_INC designatorStat_INC) { 
    	Obj desObj = designatorStat_INC.getDesignator().obj;
    	int kind = desObj.getKind();
    	
    	if(kind != Obj.Var && kind != Obj.Elem) {
    		report_error("GRESKA-DesignatorStat_INC: Inkrementiranje "+ desObj.getName() +" nije inkrementiranje varijable ni elementa niza/matrice", designatorStat_INC);
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
    @Override
    public void visit(DesignatorStat_DEC designatorStat_DEC) { 
    	Obj desObj = designatorStat_DEC.getDesignator().obj;
    	int kind = desObj.getKind();
    	
    	if(kind != Obj.Var && kind != Obj.Elem) {
    		report_error("GRESKA-DesignatorStat_DEC: Dekrementiranje "+ desObj.getName() +" nije dekrementiranje varijable ni elementa niza/matrice", designatorStat_DEC);
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
    @Override
    public void visit(MayDesignator_Designator mayDesignator_Designator) {
    	Obj desObj = mayDesignator_Designator.getDesignator().obj;
    	int kind = desObj.getKind();
    	
    	if(kind != Obj.Var && kind != Obj.Elem) {
    		report_error("GRESKA-MayDesignator_Designator: Oznaka " + desObj.getName() + " u raspakivanju niza nije varijabla ni element niza", mayDesignator_Designator);
    		return;
    	}
    	
    	listOfDesignators.add(desObj);
    	if(mayDesignator_Designator.getDesignator() instanceof Designator_ONE) {
    		report_info("INFO-MayDesignator_Designator: Pristup oznaci " + desObj.getName() + ". " + objNodeToString(desObj) , mayDesignator_Designator);
    	}
    }
    
    // DesignatorStatement ::= (DesignatorStatement_List) LEFT_BRACKET DesignatorStatementList RIGHT_BRACKET EQUALS Designator;
    @Override
    public void visit(DesignatorStatement_List designatorStatement_List) { 
    	Obj desArrayObj = designatorStatement_List.getDesignator().obj; // mora biti niz
    	
    	if(desArrayObj.getType().getKind() != Struct.Array) {
    		report_error("GRESKA-DesignatorStatement_List: Oznaka " + desArrayObj.getName() + " nije niz", designatorStatement_List);
    		return;
    	}
    	Iterator<Obj> iterator = listOfDesignators.iterator();
    	while(iterator.hasNext()) {
    		Obj currDesignator = iterator.next();
    		if(desArrayObj.getType().getElemType().assignableTo(currDesignator.getType()) == false) {
    			report_error("GRESKA-DesignatorStatement_List: Tip niza " + desArrayObj.getName() + " nije dodeljiv oznaci " + currDesignator.getName(), designatorStatement_List);
    		}
    	}
    	
    	if(designatorStatement_List.getDesignator() instanceof Designator_ONE) {
    		report_info("INFO-DesignatorStatement_List: Pristup oznaci " + desArrayObj.getName() + ". " + objNodeToString(desArrayObj) , designatorStatement_List);
    	}	
    }
    
    // Statement ::= (Statement_READ) READ LEFT_PARENTHESIS Designator RIGHT_PARENTHESIS SEMICOLON
    @Override
    public void visit(Statement_READ statement_READ) {
    	Obj desObj = statement_READ.getDesignator().obj;
    	int kind = desObj.getKind();
    	
    	if(kind != Obj.Var && kind != Obj.Elem) {
    		report_error("GRESKA-statement_READ: Read "+ desObj.getName() +" nije read varijable ni elementa niza/matrice", statement_READ);
    		return;
    	}
    	else if(kind == Obj.Var && desObj.getType().getKind() == Struct.Array) {
    		report_error("GRESKA-statement_READ: Read "+ desObj.getName() +" ne moze da se radi nad nizom i matricom", statement_READ);
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
    @Override
    public void visit(Statement_PRINT statement_PRINT) {
    	Struct exprType = statement_PRINT.getExpr().struct;
    	if(exprType.equals(Tab.intType) == false && exprType.equals(Tab.charType) == false && 
    			exprType.equals(Tab.find("bool").getType()) == false) {
        	report_error("GRESKA-Statement_PRINT: Print nema int/char/bool argument", statement_PRINT);
        	return;
        }
    }
    
    
    public static void main(String[] args) {
    int matrica[][] = {{1,2,3},{4,5,6},{7,8,9,10}};
	    //int matrica[][] = new int[3][3];
	    int niz[] = { 8,8,8 };
	    System.out.println("Hello, World!\n");
	    matrica[1] = matrica[0];
	    for(int i = 0; i < 3; i++) {
	        for(int j = 0; j < 3; j++) {
	            System.out.print(matrica[i][j] + "    ");
	        }
	        System.out.println("\n");
	    }
	    niz = matrica[2];
	    for(int i = 0; i < 4; i++) {
	            System.out.print(niz[i] + "    ");
	    } System.out.println("\n");
	    //matrica[0][-4] = 5;
	    
	    int[] niz5 = new int[5];
	    int[] niz10 = new int[10];
	    
	    niz5 = niz10;
	    for(int i = 0; i < niz5.length; i++) {
            System.out.print(niz5[i] + "    ");
    }
	    
	    SemanticAnalyzer s = new SemanticAnalyzer();
	    Struct s1 = new Struct(Struct.Array, Tab.intType);
	    Struct s2 = new Struct(Struct.Array, new Struct(Struct.Array, Tab.intType));
	    System.out.println(s.structNodeToString(Tab.intType));
	    System.out.println(s.structNodeToString(s1));
	    System.out.println(s.structNodeToString(s2));
	    
    }
    
}

