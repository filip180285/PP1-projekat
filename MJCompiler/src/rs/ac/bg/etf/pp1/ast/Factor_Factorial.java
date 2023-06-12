// generated with ast extension for cup
// version 0.8
// 12/5/2023 18:36:30


package rs.ac.bg.etf.pp1.ast;

public class Factor_Factorial extends Factor {

    private Subfactor Subfactor;

    public Factor_Factorial (Subfactor Subfactor) {
        this.Subfactor=Subfactor;
        if(Subfactor!=null) Subfactor.setParent(this);
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
        if(Subfactor!=null) Subfactor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Subfactor!=null) Subfactor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Subfactor!=null) Subfactor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Factor_Factorial(\n");

        if(Subfactor!=null)
            buffer.append(Subfactor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Factor_Factorial]");
        return buffer.toString();
    }
}
