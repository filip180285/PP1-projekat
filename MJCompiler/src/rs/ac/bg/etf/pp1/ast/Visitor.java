// generated with ast extension for cup
// version 0.8
// 10/4/2023 19:22:5


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Designator Designator);
    public void visit(Factor Factor);
    public void visit(Mulop Mulop);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(Constant Constant);
    public void visit(DesignatorStatementList DesignatorStatementList);
    public void visit(MayPrintNumConst MayPrintNumConst);
    public void visit(Expr Expr);
    public void visit(MethodVarDeclList MethodVarDeclList);
    public void visit(ConstDeclOneOrMore ConstDeclOneOrMore);
    public void visit(UnaryMinus UnaryMinus);
    public void visit(MayDesignator MayDesignator);
    public void visit(VarDecl VarDecl);
    public void visit(VarDeclOneOrMore VarDeclOneOrMore);
    public void visit(Addop Addop);
    public void visit(Statement Statement);
    public void visit(ConstVarDeclList ConstVarDeclList);
    public void visit(Term Term);
    public void visit(StatementList StatementList);
    public void visit(Mulop_MOD Mulop_MOD);
    public void visit(Mulop_DIV Mulop_DIV);
    public void visit(Mulop_MUL Mulop_MUL);
    public void visit(Addop_MINUS Addop_MINUS);
    public void visit(Addop_PLUS Addop_PLUS);
    public void visit(Assignop Assignop);
    public void visit(DesignatorArrayName DesignatorArrayName);
    public void visit(Designator_ARRAY_Elem Designator_ARRAY_Elem);
    public void visit(Designator_ONE Designator_ONE);
    public void visit(Factor_Expr Factor_Expr);
    public void visit(Factor_NEW Factor_NEW);
    public void visit(Factor_BOOLEAN Factor_BOOLEAN);
    public void visit(Factor_CHARACTER Factor_CHARACTER);
    public void visit(Factor_NUMBER Factor_NUMBER);
    public void visit(Factor_Designator Factor_Designator);
    public void visit(UnaryMinus_EPSILON UnaryMinus_EPSILON);
    public void visit(UnaryMinus_MINUS UnaryMinus_MINUS);
    public void visit(FactorSign FactorSign);
    public void visit(Term_MORE Term_MORE);
    public void visit(Term_ONE Term_ONE);
    public void visit(Expr_MORE Expr_MORE);
    public void visit(Expr_ONE Expr_ONE);
    public void visit(MayDesignator_EPSILON MayDesignator_EPSILON);
    public void visit(MayDesignator_Designator MayDesignator_Designator);
    public void visit(DesignatorStatementList_ONE DesignatorStatementList_ONE);
    public void visit(DesignatorStatementList_MORE DesignatorStatementList_MORE);
    public void visit(DesignatorStatement_List DesignatorStatement_List);
    public void visit(DesignatorStat_DEC DesignatorStat_DEC);
    public void visit(DesignatorStat_INC DesignatorStat_INC);
    public void visit(DesignatorStatement_ERROR DesignatorStatement_ERROR);
    public void visit(DesignatorSt_Assign DesignatorSt_Assign);
    public void visit(MayPrintNumConst_EPSILON MayPrintNumConst_EPSILON);
    public void visit(MayPrintNumConst_NUMBER MayPrintNumConst_NUMBER);
    public void visit(Statement_StatementList Statement_StatementList);
    public void visit(Statement_PRINT Statement_PRINT);
    public void visit(Statement_READ Statement_READ);
    public void visit(Statement_RETURN Statement_RETURN);
    public void visit(Statement_DesignatorStatement Statement_DesignatorStatement);
    public void visit(StatementList_EPSILON StatementList_EPSILON);
    public void visit(StatementList_STATEMENTS StatementList_STATEMENTS);
    public void visit(MethodVarDeclList_EPSILON MethodVarDeclList_EPSILON);
    public void visit(MethodVarDeclList_VAR MethodVarDeclList_VAR);
    public void visit(MethodName MethodName);
    public void visit(MethodDecl MethodDecl);
    public void visit(VarDecl_ARRAY VarDecl_ARRAY);
    public void visit(VarDecl_ERROR VarDecl_ERROR);
    public void visit(VarDecl_VAR VarDecl_VAR);
    public void visit(VarDeclOneOrMore_ONE VarDeclOneOrMore_ONE);
    public void visit(VarDeclOneOrMore_MORE VarDeclOneOrMore_MORE);
    public void visit(VarDeclList VarDeclList);
    public void visit(Type Type);
    public void visit(Constant_BOOLEAN Constant_BOOLEAN);
    public void visit(Constant_CHARACTER Constant_CHARACTER);
    public void visit(Constant_NUMBER Constant_NUMBER);
    public void visit(ConstDecl ConstDecl);
    public void visit(ConstDeclOneOrMore_ONE ConstDeclOneOrMore_ONE);
    public void visit(ConstDeclOneOrMore_MORE ConstDeclOneOrMore_MORE);
    public void visit(ConstDeclList ConstDeclList);
    public void visit(ConstVarDeclList_EPSILON ConstVarDeclList_EPSILON);
    public void visit(ConstVarDeclList_VAR ConstVarDeclList_VAR);
    public void visit(ConstVarDeclList_CONST ConstVarDeclList_CONST);
    public void visit(ProgramName ProgramName);
    public void visit(Program Program);

}
