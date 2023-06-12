// generated with ast extension for cup
// version 0.8
// 12/5/2023 18:36:30


package rs.ac.bg.etf.pp1.ast;

public class MayDesignator_EPSILON extends MayDesignator {

    public MayDesignator_EPSILON () {
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
        buffer.append("MayDesignator_EPSILON(\n");

        buffer.append(tab);
        buffer.append(") [MayDesignator_EPSILON]");
        return buffer.toString();
    }
}
