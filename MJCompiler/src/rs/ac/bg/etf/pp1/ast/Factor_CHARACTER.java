// generated with ast extension for cup
// version 0.8
// 16/4/2023 23:52:19


package rs.ac.bg.etf.pp1.ast;

public class Factor_CHARACTER extends Factor {

    private Character C1;

    public Factor_CHARACTER (Character C1) {
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
        buffer.append("Factor_CHARACTER(\n");

        buffer.append(" "+tab+C1);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Factor_CHARACTER]");
        return buffer.toString();
    }
}
