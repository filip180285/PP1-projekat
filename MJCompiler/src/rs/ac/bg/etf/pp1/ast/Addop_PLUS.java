// generated with ast extension for cup
// version 0.8
// 27/5/2023 22:18:56


package rs.ac.bg.etf.pp1.ast;

public class Addop_PLUS extends Addop {

    public Addop_PLUS () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Addop_PLUS(\n");

        buffer.append(tab);
        buffer.append(") [Addop_PLUS]");
        return buffer.toString();
    }
}
