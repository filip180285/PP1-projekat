// generated with ast extension for cup
// version 0.8
// 26/5/2023 23:29:36


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStat_HASH_NEG extends DesignatorStatement {

    private Factor Factor;
    private DesignatorArrayOrMatrixName DesignatorArrayOrMatrixName;

    public DesignatorStat_HASH_NEG (Factor Factor, DesignatorArrayOrMatrixName DesignatorArrayOrMatrixName) {
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
        this.DesignatorArrayOrMatrixName=DesignatorArrayOrMatrixName;
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.setParent(this);
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public DesignatorArrayOrMatrixName getDesignatorArrayOrMatrixName() {
        return DesignatorArrayOrMatrixName;
    }

    public void setDesignatorArrayOrMatrixName(DesignatorArrayOrMatrixName DesignatorArrayOrMatrixName) {
        this.DesignatorArrayOrMatrixName=DesignatorArrayOrMatrixName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Factor!=null) Factor.accept(visitor);
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        if(DesignatorArrayOrMatrixName!=null) DesignatorArrayOrMatrixName.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStat_HASH_NEG(\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorArrayOrMatrixName!=null)
            buffer.append(DesignatorArrayOrMatrixName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStat_HASH_NEG]");
        return buffer.toString();
    }
}
