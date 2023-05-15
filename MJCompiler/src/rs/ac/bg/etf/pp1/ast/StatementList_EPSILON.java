// generated with ast extension for cup
// version 0.8
// 14/4/2023 18:29:39


package rs.ac.bg.etf.pp1.ast;

public class StatementList_EPSILON extends StatementList {

    public StatementList_EPSILON () {
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
        buffer.append("StatementList_EPSILON(\n");

        buffer.append(tab);
        buffer.append(") [StatementList_EPSILON]");
        return buffer.toString();
    }
}
