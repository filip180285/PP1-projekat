// generated with ast extension for cup
// version 0.8
// 14/5/2023 16:2:21


package rs.ac.bg.etf.pp1.ast;

public class Designator_Elem extends Designator {

    private DesignatorArrayOrMatrixName DesignatorArrayOrMatrixName;
    private Expr Expr;
    private Nonterminal_RIGHT_BRACKET Nonterminal_RIGHT_BRACKET;
    private MayMatrix MayMatrix;

    public Designator_Elem (DesignatorArrayOrMatrixName DesignatorArrayOrMatrixName, Expr Expr, Nonterminal_RIGHT_BRACKET Nonterminal_RIGHT_BRACKET, MayMatrix MayMatrix) {
        this.DesignatorArrayOrMatrixName=DesignatorArrayOrMatrixName;
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.Nonterminal_RIGHT_BRACKET=Nonterminal_RIGHT_BRACKET;
        if(Nonterminal_RIGHT_BRACKET!=null) Nonterminal_RIGHT_BRACKET.setParent(this);
        this.MayMatrix=MayMatrix;
        if(MayMatrix!=null) MayMatrix.setParent(this);
    }

    public DesignatorArrayOrMatrixName getDesignatorArrayOrMatrixName() {
        return DesignatorArrayOrMatrixName;
    }

    public void setDesignatorArrayOrMatrixName(DesignatorArrayOrMatrixName DesignatorArrayOrMatrixName) {
        this.DesignatorArrayOrMatrixName=DesignatorArrayOrMatrixName;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public Nonterminal_RIGHT_BRACKET getNonterminal_RIGHT_BRACKET() {
        return Nonterminal_RIGHT_BRACKET;
    }

    public void setNonterminal_RIGHT_BRACKET(Nonterminal_RIGHT_BRACKET Nonterminal_RIGHT_BRACKET) {
        this.Nonterminal_RIGHT_BRACKET=Nonterminal_RIGHT_BRACKET;
    }

    public MayMatrix getMayMatrix() {
        return MayMatrix;
    }

    public void setMayMatrix(MayMatrix MayMatrix) {
        this.MayMatrix=MayMatrix;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(Nonterminal_RIGHT_BRACKET!=null) Nonterminal_RIGHT_BRACKET.accept(visitor);
        if(MayMatrix!=null) MayMatrix.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(Nonterminal_RIGHT_BRACKET!=null) Nonterminal_RIGHT_BRACKET.traverseTopDown(visitor);
        if(MayMatrix!=null) MayMatrix.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(Nonterminal_RIGHT_BRACKET!=null) Nonterminal_RIGHT_BRACKET.traverseBottomUp(visitor);
        if(MayMatrix!=null) MayMatrix.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Designator_Elem(\n");

        if(DesignatorArrayOrMatrixName!=null)
            buffer.append(DesignatorArrayOrMatrixName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Nonterminal_RIGHT_BRACKET!=null)
            buffer.append(Nonterminal_RIGHT_BRACKET.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MayMatrix!=null)
            buffer.append(MayMatrix.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator_Elem]");
        return buffer.toString();
    }
}
