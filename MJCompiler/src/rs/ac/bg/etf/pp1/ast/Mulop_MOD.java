// generated with ast extension for cup
// version 0.8
// 9/5/2023 14:28:23


package rs.ac.bg.etf.pp1.ast;

public class Mulop_MOD extends Mulop {

    public Mulop_MOD () {
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
        buffer.append("Mulop_MOD(\n");

        buffer.append(tab);
        buffer.append(") [Mulop_MOD]");
        return buffer.toString();
    }
}
