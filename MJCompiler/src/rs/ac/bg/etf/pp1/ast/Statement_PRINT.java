// generated with ast extension for cup
// version 0.8
// 31/4/2023 21:0:47


package rs.ac.bg.etf.pp1.ast;

public class Statement_PRINT extends Statement {

    private Expr Expr;
    private MayPrintNumConst MayPrintNumConst;

    public Statement_PRINT (Expr Expr, MayPrintNumConst MayPrintNumConst) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.MayPrintNumConst=MayPrintNumConst;
        if(MayPrintNumConst!=null) MayPrintNumConst.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public MayPrintNumConst getMayPrintNumConst() {
        return MayPrintNumConst;
    }

    public void setMayPrintNumConst(MayPrintNumConst MayPrintNumConst) {
        this.MayPrintNumConst=MayPrintNumConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(MayPrintNumConst!=null) MayPrintNumConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(MayPrintNumConst!=null) MayPrintNumConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(MayPrintNumConst!=null) MayPrintNumConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Statement_PRINT(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MayPrintNumConst!=null)
            buffer.append(MayPrintNumConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Statement_PRINT]");
        return buffer.toString();
    }
}
