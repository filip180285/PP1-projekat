// generated with ast extension for cup
// version 0.8
// 15/5/2023 17:25:14


package rs.ac.bg.etf.pp1.ast;

public class ListOfNCB implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Ncb Ncb;
    private ListOfNCBMore ListOfNCBMore;

    public ListOfNCB (Ncb Ncb, ListOfNCBMore ListOfNCBMore) {
        this.Ncb=Ncb;
        if(Ncb!=null) Ncb.setParent(this);
        this.ListOfNCBMore=ListOfNCBMore;
        if(ListOfNCBMore!=null) ListOfNCBMore.setParent(this);
    }

    public Ncb getNcb() {
        return Ncb;
    }

    public void setNcb(Ncb Ncb) {
        this.Ncb=Ncb;
    }

    public ListOfNCBMore getListOfNCBMore() {
        return ListOfNCBMore;
    }

    public void setListOfNCBMore(ListOfNCBMore ListOfNCBMore) {
        this.ListOfNCBMore=ListOfNCBMore;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Ncb!=null) Ncb.accept(visitor);
        if(ListOfNCBMore!=null) ListOfNCBMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Ncb!=null) Ncb.traverseTopDown(visitor);
        if(ListOfNCBMore!=null) ListOfNCBMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Ncb!=null) Ncb.traverseBottomUp(visitor);
        if(ListOfNCBMore!=null) ListOfNCBMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ListOfNCB(\n");

        if(Ncb!=null)
            buffer.append(Ncb.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ListOfNCBMore!=null)
            buffer.append(ListOfNCBMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ListOfNCB]");
        return buffer.toString();
    }
}
