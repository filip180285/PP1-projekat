// generated with ast extension for cup
// version 0.8
// 9/4/2023 17:24:45


package rs.ac.bg.etf.pp1.ast;

public class Constant_CHARACTER extends Constant {

    private Character C1;

    public Constant_CHARACTER (Character C1) {
        this.C1=C1;
    }

    public Character getC1() {
        return C1;
    }

    public void setC1(Character C1) {
        this.C1=C1;
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
        buffer.append("Constant_CHARACTER(\n");

        buffer.append(" "+tab+C1);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Constant_CHARACTER]");
        return buffer.toString();
    }
}
