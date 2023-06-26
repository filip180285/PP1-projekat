// generated with ast extension for cup
// version 0.8
// 26/5/2023 23:29:36


package rs.ac.bg.etf.pp1.ast;

public abstract class VisitorAdaptor implements Visitor { 

    public void visit(Designator Designator) { }
    public void visit(Factor Factor) { }
    public void visit(Mulop Mulop) { }
    public void visit(NEW_Matrix NEW_Matrix) { }
    public void visit(DesignatorStatement DesignatorStatement) { }
    public void visit(Constant Constant) { }
    public void visit(DesignatorStatementList DesignatorStatementList) { }
    public void visit(MayPrintNumConst MayPrintNumConst) { }
    public void visit(Expr Expr) { }
    public void visit(MethodVarDeclList MethodVarDeclList) { }
    public void visit(ConstDeclOneOrMore ConstDeclOneOrMore) { }
    public void visit(UnaryMinus UnaryMinus) { }
    public void visit(MayDesignator MayDesignator) { }
    public void visit(VarDecl VarDecl) { }
    public void visit(VarDeclOneOrMore VarDeclOneOrMore) { }
    public void visit(Addop Addop) { }
    public void visit(Statement Statement) { }
    public void visit(ConstVarDeclList ConstVarDeclList) { }
    public void visit(Term Term) { }
    public void visit(MayMatrix MayMatrix) { }
    public void visit(StatementList StatementList) { }
    public void visit(Mulop_MOD Mulop_MOD) { visit(); }
    public void visit(Mulop_DIV Mulop_DIV) { visit(); }
    public void visit(Mulop_MUL Mulop_MUL) { visit(); }
    public void visit(Addop_MINUS Addop_MINUS) { visit(); }
    public void visit(Addop_PLUS Addop_PLUS) { visit(); }
    public void visit(Assignop Assignop) { visit(); }
    public void visit(MayMatrix_EPSILON MayMatrix_EPSILON) { visit(); }
    public void visit(MayMatrix_MATRIX MayMatrix_MATRIX) { visit(); }
    public void visit(DesignatorArrayOrMatrixName DesignatorArrayOrMatrixName) { visit(); }
    public void visit(Designator_Elem Designator_Elem) { visit(); }
    public void visit(Designator_ONE Designator_ONE) { visit(); }
    public void visit(NEW_Matrix_EPSILON NEW_Matrix_EPSILON) { visit(); }
    public void visit(NEW_Matrix_MATRIX NEW_Matrix_MATRIX) { visit(); }
    public void visit(NEW_Array NEW_Array) { visit(); }
    public void visit(Factor_Expr Factor_Expr) { visit(); }
    public void visit(Factor_NEW Factor_NEW) { visit(); }
    public void visit(Factor_BOOLEAN Factor_BOOLEAN) { visit(); }
    public void visit(Factor_CHARACTER Factor_CHARACTER) { visit(); }
    public void visit(Factor_NUMBER Factor_NUMBER) { visit(); }
    public void visit(Factor_Designator Factor_Designator) { visit(); }
    public void visit(UnaryMinus_EPSILON UnaryMinus_EPSILON) { visit(); }
    public void visit(UnaryMinus_MINUS UnaryMinus_MINUS) { visit(); }
    public void visit(FactorSign FactorSign) { visit(); }
    public void visit(Term_MORE Term_MORE) { visit(); }
    public void visit(Term_ONE Term_ONE) { visit(); }
    public void visit(Expr_MORE Expr_MORE) { visit(); }
    public void visit(Expr_ONE Expr_ONE) { visit(); }
    public void visit(MayDesignator_EPSILON MayDesignator_EPSILON) { visit(); }
    public void visit(MayDesignator_Designator MayDesignator_Designator) { visit(); }
    public void visit(DesignatorStatementList_ONE DesignatorStatementList_ONE) { visit(); }
    public void visit(DesignatorStatementList_MORE DesignatorStatementList_MORE) { visit(); }
    public void visit(DesignatorStatement_List DesignatorStatement_List) { visit(); }
    public void visit(DesignatorStat_HASH_NEG DesignatorStat_HASH_NEG) { visit(); }
    public void visit(DesignatorStat_HASH DesignatorStat_HASH) { visit(); }
    public void visit(DesignatorStat_DEC DesignatorStat_DEC) { visit(); }
    public void visit(DesignatorStat_INC DesignatorStat_INC) { visit(); }
    public void visit(DesignatorStatement_ERROR DesignatorStatement_ERROR) { visit(); }
    public void visit(DesignatorSt_Assign DesignatorSt_Assign) { visit(); }
    public void visit(MayPrintNumConst_EPSILON MayPrintNumConst_EPSILON) { visit(); }
    public void visit(MayPrintNumConst_NUMBER MayPrintNumConst_NUMBER) { visit(); }
    public void visit(Statement_StatementList Statement_StatementList) { visit(); }
    public void visit(Statement_PRINT Statement_PRINT) { visit(); }
    public void visit(Statement_READ Statement_READ) { visit(); }
    public void visit(Statement_RETURN Statement_RETURN) { visit(); }
    public void visit(Statement_DesignatorStatement Statement_DesignatorStatement) { visit(); }
    public void visit(StatementList_EPSILON StatementList_EPSILON) { visit(); }
    public void visit(StatementList_STATEMENTS StatementList_STATEMENTS) { visit(); }
    public void visit(MethodVarDeclList_EPSILON MethodVarDeclList_EPSILON) { visit(); }
    public void visit(MethodVarDeclList_VAR MethodVarDeclList_VAR) { visit(); }
    public void visit(MethodName MethodName) { visit(); }
    public void visit(MethodDecl MethodDecl) { visit(); }
    public void visit(VarDecl_MATRIX VarDecl_MATRIX) { visit(); }
    public void visit(VarDecl_ARRAY VarDecl_ARRAY) { visit(); }
    public void visit(VarDecl_ERROR VarDecl_ERROR) { visit(); }
    public void visit(VarDecl_VAR VarDecl_VAR) { visit(); }
    public void visit(VarDeclOneOrMore_ONE VarDeclOneOrMore_ONE) { visit(); }
    public void visit(VarDeclOneOrMore_MORE VarDeclOneOrMore_MORE) { visit(); }
    public void visit(VarDeclList VarDeclList) { visit(); }
    public void visit(Type Type) { visit(); }
    public void visit(Constant_BOOLEAN Constant_BOOLEAN) { visit(); }
    public void visit(Constant_CHARACTER Constant_CHARACTER) { visit(); }
    public void visit(Constant_NUMBER Constant_NUMBER) { visit(); }
    public void visit(ConstDecl ConstDecl) { visit(); }
    public void visit(ConstDeclOneOrMore_ONE ConstDeclOneOrMore_ONE) { visit(); }
    public void visit(ConstDeclOneOrMore_MORE ConstDeclOneOrMore_MORE) { visit(); }
    public void visit(ConstDeclList ConstDeclList) { visit(); }
    public void visit(ConstVarDeclList_EPSILON ConstVarDeclList_EPSILON) { visit(); }
    public void visit(ConstVarDeclList_VAR ConstVarDeclList_VAR) { visit(); }
    public void visit(ConstVarDeclList_CONST ConstVarDeclList_CONST) { visit(); }
    public void visit(ProgramName ProgramName) { visit(); }
    public void visit(Program Program) { visit(); }


    public void visit() { }
}
