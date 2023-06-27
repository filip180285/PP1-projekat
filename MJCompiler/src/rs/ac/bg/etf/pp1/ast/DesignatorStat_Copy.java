// generated with ast extension for cup
// version 0.8
// 27/5/2023 21:52:58


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStat_Copy extends DesignatorStatement {

    private DesignatorArrayOrMatrixName DesignatorArrayOrMatrixName;
    private DesignatorArrayOrMatrixName DesignatorArrayOrMatrixName1;

    public DesignatorStat_Copy (DesignatorArrayOrMatrixName DesignatorArrayOrMatrixName, DesignatorArrayOrMatrixName DesignatorArrayOrMatrixName1) {
        this.DesignatorArrayOrMatrixName=DesignatorArrayOrMatrixName;
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.setParent(this);
        this.DesignatorArrayOrMatrixName1=DesignatorArrayOrMatrixName1;
        if(DesignatorArrayOrMatrixName1!=null) DesignatorArrayOrMatrixName1.setParent(this);
    }

    public DesignatorArrayOrMatrixName getDesignatorArrayOrMatrixName() {
        return DesignatorArrayOrMatrixName;
    }

    public void setDesignatorArrayOrMatrixName(DesignatorArrayOrMatrixName DesignatorArrayOrMatrixName) {
        this.DesignatorArrayOrMatrixName=DesignatorArrayOrMatrixName;
    }

    public DesignatorArrayOrMatrixName getDesignatorArrayOrMatrixName1() {
        return DesignatorArrayOrMatrixName1;
    }

    public void setDesignatorArrayOrMatrixName1(DesignatorArrayOrMatrixName DesignatorArrayOrMatrixName1) {
        this.DesignatorArrayOrMatrixName1=DesignatorArrayOrMatrixName1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.accept(visitor);
        if(DesignatorArrayOrMatrixName1!=null) DesignatorArrayOrMatrixName1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.traverseTopDown(visitor);
        if(DesignatorArrayOrMatrixName1!=null) DesignatorArrayOrMatrixName1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.traverseBottomUp(visitor);
        if(DesignatorArrayOrMatrixName1!=null) DesignatorArrayOrMatrixName1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStat_Copy(\n");

        if(DesignatorArrayOrMatrixName!=null)
            buffer.append(DesignatorArrayOrMatrixName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorArrayOrMatrixName1!=null)
            buffer.append(DesignatorArrayOrMatrixName1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStat_Copy]");
        return buffer.toString();
    }
}
