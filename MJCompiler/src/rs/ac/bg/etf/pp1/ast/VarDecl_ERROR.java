// generated with ast extension for cup
// version 0.8
// 10/4/2023 2:24:8


package rs.ac.bg.etf.pp1.ast;

public class VarDecl_ERROR extends VarDecl {

    public VarDecl_ERROR () {
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
        buffer.append("VarDecl_ERROR(\n");

        buffer.append(tab);
        buffer.append(") [VarDecl_ERROR]");
        return buffer.toString();
    }
}