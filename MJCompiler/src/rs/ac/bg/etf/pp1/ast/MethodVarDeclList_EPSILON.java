// generated with ast extension for cup
// version 0.8
// 12/5/2023 17:48:25


package rs.ac.bg.etf.pp1.ast;

public class MethodVarDeclList_EPSILON extends MethodVarDeclList {

    public MethodVarDeclList_EPSILON () {
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
        buffer.append("MethodVarDeclList_EPSILON(\n");

        buffer.append(tab);
        buffer.append(") [MethodVarDeclList_EPSILON]");
        return buffer.toString();
    }
}
