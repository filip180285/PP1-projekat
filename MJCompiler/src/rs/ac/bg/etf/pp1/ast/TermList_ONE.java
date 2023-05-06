// generated with ast extension for cup
// version 0.8
// 7/4/2023 1:37:10


package rs.ac.bg.etf.pp1.ast;

public class TermList_ONE extends TermList {

    private FactorSign FactorSign;

    public TermList_ONE (FactorSign FactorSign) {
        this.FactorSign=FactorSign;
        if(FactorSign!=null) FactorSign.setParent(this);
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
        if(FactorSign!=null) FactorSign.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FactorSign!=null) FactorSign.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FactorSign!=null) FactorSign.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TermList_ONE(\n");

        if(FactorSign!=null)
            buffer.append(FactorSign.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TermList_ONE]");
        return buffer.toString();
    }
}
