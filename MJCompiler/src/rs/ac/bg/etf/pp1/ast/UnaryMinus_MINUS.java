// generated with ast extension for cup
// version 0.8
// 10/5/2023 23:56:58


package rs.ac.bg.etf.pp1.ast;

public class UnaryMinus_MINUS extends UnaryMinus {

    public UnaryMinus_MINUS () {
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
        buffer.append("UnaryMinus_MINUS(\n");

        buffer.append(tab);
        buffer.append(") [UnaryMinus_MINUS]");
        return buffer.toString();
    }
}
