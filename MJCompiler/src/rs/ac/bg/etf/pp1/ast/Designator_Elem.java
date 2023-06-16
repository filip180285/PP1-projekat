// generated with ast extension for cup
// version 0.8
// 15/5/2023 17:25:14


package rs.ac.bg.etf.pp1.ast;

public class Designator_Elem extends Designator {

    private DesignatorArrayOrMatrixName DesignatorArrayOrMatrixName;
    private Expr Expr;
    private MayMatrix MayMatrix;

    public Designator_Elem (DesignatorArrayOrMatrixName DesignatorArrayOrMatrixName, Expr Expr, MayMatrix MayMatrix) {
        this.DesignatorArrayOrMatrixName=DesignatorArrayOrMatrixName;
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
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
        if(MayMatrix!=null) MayMatrix.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(MayMatrix!=null) MayMatrix.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
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
