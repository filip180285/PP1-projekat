// generated with ast extension for cup
// version 0.8
// 7/5/2023 15:9:23


package rs.ac.bg.etf.pp1.ast;

public class Factor_Designator extends Factor {

    private Designator Designator;
    private MayHash MayHash;

    public Factor_Designator (Designator Designator, MayHash MayHash) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.MayHash=MayHash;
        if(MayHash!=null) MayHash.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
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
        if(Designator!=null) Designator.accept(visitor);
        if(MayHash!=null) MayHash.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(MayHash!=null) MayHash.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(MayHash!=null) MayHash.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Factor_Designator(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MayHash!=null)
            buffer.append(MayHash.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Factor_Designator]");
        return buffer.toString();
    }
}
