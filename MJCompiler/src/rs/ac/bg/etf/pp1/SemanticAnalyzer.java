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
	
    public boolean passed(){
    	return !errorDetected;
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
    
    // PROVJERI TIP IZRAZA**************************************
    // Factor ::= (Factor_NEW) NEW Type LEFT_BRACKET Expr RIGHT_BRACKET
    @Override
    public void visit(Factor_NEW factor_NEW) {
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
    		report_error("GRESKA-designator_ONE: Pristup nedeklarisanoj oznaci " + desName, designator_ONE);
    		designator_ONE.obj = Tab.noObj;
    		return;
    	}
    	else if ( (desKind == Obj.Con || desKind == Obj.Var) == false ) {
    		report_error("GRESKA-designator_ONE: Oznaka " + desName + " mora biti konstanta ili varijabla", designator_ONE);
    		designator_ONE.obj = Tab.noObj;
    		return;
    	}
    	designator_ONE.obj = desObj;
    }
    
    // DesignatorArrayName ::= (DesignatorArrayName) IDENTIFIER;
    public void visit(DesignatorArrayName designatorArrayName) { 
    	String desArrayName = designatorArrayName.getI1();
    	Obj desArrayObj = Tab.find(desArrayName);
    	int desKind = desArrayObj.getKind();
    	
    	if(desArrayObj == Tab.noObj) {
    		report_error("GRESKA-Designator_ARRAY_Elem: Pristup nedeklarisanoj oznaci niza " + desArrayName, designatorArrayName);
    		designatorArrayName.obj = Tab.noObj;
    		return;
    	}
    	else if (desKind != Obj.Var)  {
    		report_error("GRESKA-Designator_ARRAY_Elem: Oznaka " + desArrayName + " mora biti varijabla", designatorArrayName);
    		designatorArrayName.obj = Tab.noObj;
    		return;
    	}
    	else if(desArrayObj.getType().getKind() != Struct.Array) {
    		report_error("GRESKA-Designator_ARRAY_Elem: Oznaka " + desArrayName + " mora biti varijabla nizovnog tipa", designatorArrayName);
    		designatorArrayName.obj = Tab.noObj;
    		return;
    	}
    	designatorArrayName.obj = desArrayObj;
    }
    
    // PROVJERI TIP IZRAZA**************************************
    // Designator ::= (designator_ARRAY_Elem) DesignatorArrayName LEFT_BRACKET Expr RIGHT_BRACKET;
    @Override
    public void visit(Designator_ARRAY_Elem designator_ARRAY_Elem) {
    		Obj danObj = designator_ARRAY_Elem.getDesignatorArrayName().obj;
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
        		report_error("GRESKA-factorSign: Ne moze se negirati drugi tip osim int", factorSign);
        		factorSign.struct = Tab.noType;
        		return;
    		}
    	}
    	else {
    		factorSign.struct = f.struct;
    	}
    }
}











