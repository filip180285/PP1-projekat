// generated with ast extension for cup
// version 0.8
// 27/5/2023 14:47:52


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatement_ERROR extends DesignatorStatement {

    public DesignatorStatement_ERROR () {
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
        buffer.append("DesignatorStatement_ERROR(\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatement_ERROR]");
        return buffer.toString();
    }
}
