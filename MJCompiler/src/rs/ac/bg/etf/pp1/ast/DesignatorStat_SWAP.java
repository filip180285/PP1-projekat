// generated with ast extension for cup
// version 0.8
// 12/5/2023 22:7:36


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStat_SWAP extends DesignatorStatement {

    private DesignatorArrayOrMatrixName DesignatorArrayOrMatrixName;
    private Expr Expr;
    private Expr Expr1;

    public DesignatorStat_SWAP (DesignatorArrayOrMatrixName DesignatorArrayOrMatrixName, Expr Expr, Expr Expr1) {
        this.DesignatorArrayOrMatrixName=DesignatorArrayOrMatrixName;
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.Expr1=Expr1;
        if(Expr1!=null) Expr1.setParent(this);
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

    public Expr getExpr1() {
        return Expr1;
    }

    public void setExpr1(Expr Expr1) {
        this.Expr1=Expr1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(Expr1!=null) Expr1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(Expr1!=null) Expr1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(Expr1!=null) Expr1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStat_SWAP(\n");

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

        if(Expr1!=null)
            buffer.append(Expr1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStat_SWAP]");
        return buffer.toString();
    }
}
