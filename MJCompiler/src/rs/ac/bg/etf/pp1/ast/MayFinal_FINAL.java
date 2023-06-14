// generated with ast extension for cup
// version 0.8
// 13/5/2023 23:51:41


package rs.ac.bg.etf.pp1.ast;

public class MayFinal_FINAL extends MayFinal {

    public MayFinal_FINAL () {
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
        buffer.append("MayFinal_FINAL(\n");

        buffer.append(tab);
        buffer.append(") [MayFinal_FINAL]");
        return buffer.toString();
    }
}
