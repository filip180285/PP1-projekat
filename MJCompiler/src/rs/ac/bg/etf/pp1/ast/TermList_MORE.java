// generated with ast extension for cup
// version 0.8
// 7/4/2023 1:37:10


package rs.ac.bg.etf.pp1.ast;

public class TermList_MORE extends TermList {

    private TermList TermList;
    private Mulop Mulop;
    private FactorSign FactorSign;

    public TermList_MORE (TermList TermList, Mulop Mulop, FactorSign FactorSign) {
        this.TermList=TermList;
        if(TermList!=null) TermList.setParent(this);
        this.Mulop=Mulop;
        if(Mulop!=null) Mulop.setParent(this);
        this.FactorSign=FactorSign;
        if(FactorSign!=null) FactorSign.setParent(this);
    }

    public TermList getTermList() {
        return TermList;
    }

    public void setTermList(TermList TermList) {
        this.TermList=TermList;
    }

    public Mulop getMulop() {
        return Mulop;
    }

    public void setMulop(Mulop Mulop) {
        this.Mulop=Mulop;
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
        if(TermList!=null) TermList.accept(visitor);
        if(Mulop!=null) Mulop.accept(visitor);
        if(FactorSign!=null) FactorSign.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TermList!=null) TermList.traverseTopDown(visitor);
        if(Mulop!=null) Mulop.traverseTopDown(visitor);
        if(FactorSign!=null) FactorSign.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TermList!=null) TermList.traverseBottomUp(visitor);
        if(Mulop!=null) Mulop.traverseBottomUp(visitor);
        if(FactorSign!=null) FactorSign.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TermList_MORE(\n");

        if(TermList!=null)
            buffer.append(TermList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Mulop!=null)
            buffer.append(Mulop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FactorSign!=null)
            buffer.append(FactorSign.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TermList_MORE]");
        return buffer.toString();
    }
}
