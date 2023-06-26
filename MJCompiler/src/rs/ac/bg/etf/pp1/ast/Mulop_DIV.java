// generated with ast extension for cup
// version 0.8
// 26/5/2023 1:22:13


package rs.ac.bg.etf.pp1.ast;

public class Mulop_DIV extends Mulop {

    public Mulop_DIV () {
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
        buffer.append("Mulop_DIV(\n");

        buffer.append(tab);
        buffer.append(") [Mulop_DIV]");
        return buffer.toString();
    }
}
