// generated with ast extension for cup
// version 0.8
// 5/5/2023 19:45:9


package rs.ac.bg.etf.pp1.ast;

public class MayPrintNumConst_NUMBER extends MayPrintNumConst {

    private Integer N1;

    public MayPrintNumConst_NUMBER (Integer N1) {
        this.N1=N1;
    }

    public Integer getN1() {
        return N1;
    }

    public void setN1(Integer N1) {
        this.N1=N1;
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
        buffer.append("MayPrintNumConst_NUMBER(\n");

        buffer.append(" "+tab+N1);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MayPrintNumConst_NUMBER]");
        return buffer.toString();
    }
}
