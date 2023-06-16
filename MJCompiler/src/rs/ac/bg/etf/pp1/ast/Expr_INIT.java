// generated with ast extension for cup
// version 0.8
// 15/5/2023 17:25:14


package rs.ac.bg.etf.pp1.ast;

public class Expr_INIT extends Expr {

    private ListOfNCB ListOfNCB;

    public Expr_INIT (ListOfNCB ListOfNCB) {
        this.ListOfNCB=ListOfNCB;
        if(ListOfNCB!=null) ListOfNCB.setParent(this);
    }

    public ListOfNCB getListOfNCB() {
        return ListOfNCB;
    }

    public void setListOfNCB(ListOfNCB ListOfNCB) {
        this.ListOfNCB=ListOfNCB;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ListOfNCB!=null) ListOfNCB.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ListOfNCB!=null) ListOfNCB.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ListOfNCB!=null) ListOfNCB.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Expr_INIT(\n");

        if(ListOfNCB!=null)
            buffer.append(ListOfNCB.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Expr_INIT]");
        return buffer.toString();
    }
}
