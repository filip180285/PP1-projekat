// generated with ast extension for cup
// version 0.8
// 15/5/2023 17:25:14


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementList_ONE extends DesignatorStatementList {

    private MayDesignator MayDesignator;

    public DesignatorStatementList_ONE (MayDesignator MayDesignator) {
        this.MayDesignator=MayDesignator;
        if(MayDesignator!=null) MayDesignator.setParent(this);
    }

    public MayDesignator getMayDesignator() {
        return MayDesignator;
    }

    public void setMayDesignator(MayDesignator MayDesignator) {
        this.MayDesignator=MayDesignator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MayDesignator!=null) MayDesignator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MayDesignator!=null) MayDesignator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MayDesignator!=null) MayDesignator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementList_ONE(\n");

        if(MayDesignator!=null)
            buffer.append(MayDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementList_ONE]");
        return buffer.toString();
    }
}
