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
    
    private Struct currentType; // za cuvanje tipa konstanti i varijabli
    private int currentConstValue; // za cuvanje vrednosti konstante
    
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
    	Tab.chainLocalSymbols(program.getProgramName().obj);
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
    		report_error("GRESKA-ConstDecl: Ime " + constName + " je vec definisano", constDecl);
    		return;
    	} else {
    		Constant c = constDecl.getConstant();
    		Struct cType = c.struct;
    		if(cType.assignableTo(currentType) == false) { 
    			report_error("GRESKA-ConstDecl: Neadekvatna dodela konstanti " + constName , constDecl);
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
    
}
