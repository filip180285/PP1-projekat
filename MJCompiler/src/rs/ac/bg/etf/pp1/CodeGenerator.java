package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor {

    // MethodName ::= (MethodName) IDENTIFIER;
    @Override
    public void visit(MethodName methodName) {
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
	
	
}
