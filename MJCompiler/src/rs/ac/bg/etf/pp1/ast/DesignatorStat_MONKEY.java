// generated with ast extension for cup
// version 0.8
// 7/5/2023 1:55:16


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStat_MONKEY extends DesignatorStatement {

    private DesignatorArrayOrMatrixName DesignatorArrayOrMatrixName;
    private Expr Expr;
    private MonkeyNT MonkeyNT;
    private FactorSign FactorSign;

    public DesignatorStat_MONKEY (DesignatorArrayOrMatrixName DesignatorArrayOrMatrixName, Expr Expr, MonkeyNT MonkeyNT, FactorSign FactorSign) {
        this.DesignatorArrayOrMatrixName=DesignatorArrayOrMatrixName;
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.MonkeyNT=MonkeyNT;
        if(MonkeyNT!=null) MonkeyNT.setParent(this);
        this.FactorSign=FactorSign;
        if(FactorSign!=null) FactorSign.setParent(this);
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

    public MonkeyNT getMonkeyNT() {
        return MonkeyNT;
    }

    public void setMonkeyNT(MonkeyNT MonkeyNT) {
        this.MonkeyNT=MonkeyNT;
    }

    public FactorSign getFactorSign() {
        return FactorSign;
    }

    public void setFactorSign(FactorSign FactorSign) {
        this.FactorSign=FactorSign;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(MonkeyNT!=null) MonkeyNT.accept(visitor);
        if(FactorSign!=null) FactorSign.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(MonkeyNT!=null) MonkeyNT.traverseTopDown(visitor);
        if(FactorSign!=null) FactorSign.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(MonkeyNT!=null) MonkeyNT.traverseBottomUp(visitor);
        if(FactorSign!=null) FactorSign.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStat_MONKEY(\n");

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

        if(MonkeyNT!=null)
            buffer.append(MonkeyNT.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FactorSign!=null)
            buffer.append(FactorSign.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStat_MONKEY]");
        return buffer.toString();
    }
}
