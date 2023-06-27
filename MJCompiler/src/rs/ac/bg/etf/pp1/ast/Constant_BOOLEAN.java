// generated with ast extension for cup
// version 0.8
// 27/5/2023 16:22:7


package rs.ac.bg.etf.pp1.ast;

public class Constant_BOOLEAN extends Constant {

    private Integer B1;

    public Constant_BOOLEAN (Integer B1) {
        this.B1=B1;
    }

    public Integer getB1() {
        return B1;
    }

    public void setB1(Integer B1) {
        this.B1=B1;
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
        buffer.append("Constant_BOOLEAN(\n");

        buffer.append(" "+tab+B1);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Constant_BOOLEAN]");
        return buffer.toString();
    }
}
