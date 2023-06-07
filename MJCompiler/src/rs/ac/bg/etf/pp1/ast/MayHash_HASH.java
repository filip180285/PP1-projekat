// generated with ast extension for cup
// version 0.8
// 7/5/2023 15:9:23


package rs.ac.bg.etf.pp1.ast;

public class MayHash_HASH extends MayHash {

    public MayHash_HASH () {
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
        buffer.append("MayHash_HASH(\n");

        buffer.append(tab);
        buffer.append(") [MayHash_HASH]");
        return buffer.toString();
    }
}
