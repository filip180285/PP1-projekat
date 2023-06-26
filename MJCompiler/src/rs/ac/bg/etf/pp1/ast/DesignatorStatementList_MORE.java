// generated with ast extension for cup
// version 0.8
// 26/5/2023 23:38:37


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementList_MORE extends DesignatorStatementList {

    private DesignatorStatementList DesignatorStatementList;
    private MayDesignator MayDesignator;

    public DesignatorStatementList_MORE (DesignatorStatementList DesignatorStatementList, MayDesignator MayDesignator) {
        this.DesignatorStatementList=DesignatorStatementList;
        if(DesignatorStatementList!=null) DesignatorStatementList.setParent(this);
        this.MayDesignator=MayDesignator;
        if(MayDesignator!=null) MayDesignator.setParent(this);
    }

    public DesignatorStatementList getDesignatorStatementList() {
        return DesignatorStatementList;
    }

    public void setDesignatorStatementList(DesignatorStatementList DesignatorStatementList) {
        this.DesignatorStatementList=DesignatorStatementList;
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
        if(DesignatorStatementList!=null) DesignatorStatementList.accept(visitor);
        if(MayDesignator!=null) MayDesignator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStatementList!=null) DesignatorStatementList.traverseTopDown(visitor);
        if(MayDesignator!=null) MayDesignator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStatementList!=null) DesignatorStatementList.traverseBottomUp(visitor);
        if(MayDesignator!=null) MayDesignator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementList_MORE(\n");

        if(DesignatorStatementList!=null)
            buffer.append(DesignatorStatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MayDesignator!=null)
            buffer.append(MayDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementList_MORE]");
        return buffer.toString();
    }
}
