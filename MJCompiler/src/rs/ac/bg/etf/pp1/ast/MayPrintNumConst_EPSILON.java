// generated with ast extension for cup
// version 0.8
// 11/4/2023 22:56:39


package rs.ac.bg.etf.pp1.ast;

public class MayPrintNumConst_EPSILON extends MayPrintNumConst {

    public MayPrintNumConst_EPSILON () {
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
        buffer.append("MayPrintNumConst_EPSILON(\n");

        buffer.append(tab);
        buffer.append(") [MayPrintNumConst_EPSILON]");
        return buffer.toString();
    }
}
