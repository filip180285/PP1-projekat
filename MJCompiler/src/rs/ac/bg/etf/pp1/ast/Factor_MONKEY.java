// generated with ast extension for cup
// version 0.8
// 9/5/2023 14:28:23


package rs.ac.bg.etf.pp1.ast;

public class Factor_MONKEY extends Factor {

    private Factor Factor;
    private Subfactor Subfactor;

    public Factor_MONKEY (Factor Factor, Subfactor Subfactor) {
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
        this.Subfactor=Subfactor;
        if(Subfactor!=null) Subfactor.setParent(this);
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public Subfactor getSubfactor() {
        return Subfactor;
    }

    public void setSubfactor(Subfactor Subfactor) {
        this.Subfactor=Subfactor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Factor!=null) Factor.accept(visitor);
        if(Subfactor!=null) Subfactor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
        if(Subfactor!=null) Subfactor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        if(Subfactor!=null) Subfactor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Factor_MONKEY(\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Subfactor!=null)
            buffer.append(Subfactor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Factor_MONKEY]");
        return buffer.toString();
    }
}
