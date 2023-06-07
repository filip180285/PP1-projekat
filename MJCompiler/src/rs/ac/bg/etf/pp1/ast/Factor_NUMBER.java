// generated with ast extension for cup
// version 0.8
// 7/5/2023 15:9:23


package rs.ac.bg.etf.pp1.ast;

public class Factor_NUMBER extends Factor {

    private Integer N1;
    private MayHash MayHash;

    public Factor_NUMBER (Integer N1, MayHash MayHash) {
        this.N1=N1;
        this.MayHash=MayHash;
        if(MayHash!=null) MayHash.setParent(this);
    }

    public Integer getN1() {
        return N1;
    }

    public void setN1(Integer N1) {
        this.N1=N1;
    }

    public MayHash getMayHash() {
        return MayHash;
    }

    public void setMayHash(MayHash MayHash) {
        this.MayHash=MayHash;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MayHash!=null) MayHash.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MayHash!=null) MayHash.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MayHash!=null) MayHash.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Factor_NUMBER(\n");

        buffer.append(" "+tab+N1);
        buffer.append("\n");

        if(MayHash!=null)
            buffer.append(MayHash.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Factor_NUMBER]");
        return buffer.toString();
    }
}
