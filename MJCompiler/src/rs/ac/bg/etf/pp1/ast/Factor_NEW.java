// generated with ast extension for cup
// version 0.8
// 13/5/2023 23:51:41


package rs.ac.bg.etf.pp1.ast;

public class Factor_NEW extends Factor {

    private NEW_Array NEW_Array;
    private NEW_Matrix NEW_Matrix;

    public Factor_NEW (NEW_Array NEW_Array, NEW_Matrix NEW_Matrix) {
        this.NEW_Array=NEW_Array;
        if(NEW_Array!=null) NEW_Array.setParent(this);
        this.NEW_Matrix=NEW_Matrix;
        if(NEW_Matrix!=null) NEW_Matrix.setParent(this);
    }

    public NEW_Array getNEW_Array() {
        return NEW_Array;
    }

    public void setNEW_Array(NEW_Array NEW_Array) {
        this.NEW_Array=NEW_Array;
    }

    public NEW_Matrix getNEW_Matrix() {
        return NEW_Matrix;
    }

    public void setNEW_Matrix(NEW_Matrix NEW_Matrix) {
        this.NEW_Matrix=NEW_Matrix;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(NEW_Array!=null) NEW_Array.accept(visitor);
        if(NEW_Matrix!=null) NEW_Matrix.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NEW_Array!=null) NEW_Array.traverseTopDown(visitor);
        if(NEW_Matrix!=null) NEW_Matrix.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NEW_Array!=null) NEW_Array.traverseBottomUp(visitor);
        if(NEW_Matrix!=null) NEW_Matrix.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Factor_NEW(\n");

        if(NEW_Array!=null)
            buffer.append(NEW_Array.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NEW_Matrix!=null)
            buffer.append(NEW_Matrix.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Factor_NEW]");
        return buffer.toString();
    }
}
