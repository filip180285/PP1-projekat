// generated with ast extension for cup
// version 0.8
// 16/5/2023 19:35:8


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStat_Ceasar extends DesignatorStatement {

    private Designator Designator;
    private UnaryMinus UnaryMinus;
    private Integer N3;

    public DesignatorStat_Ceasar (Designator Designator, UnaryMinus UnaryMinus, Integer N3) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.UnaryMinus=UnaryMinus;
        if(UnaryMinus!=null) UnaryMinus.setParent(this);
        this.N3=N3;
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public UnaryMinus getUnaryMinus() {
        return UnaryMinus;
    }

    public void setUnaryMinus(UnaryMinus UnaryMinus) {
        this.UnaryMinus=UnaryMinus;
    }

    public Integer getN3() {
        return N3;
    }

    public void setN3(Integer N3) {
        this.N3=N3;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(UnaryMinus!=null) UnaryMinus.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(UnaryMinus!=null) UnaryMinus.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(UnaryMinus!=null) UnaryMinus.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStat_Ceasar(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(UnaryMinus!=null)
            buffer.append(UnaryMinus.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+N3);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStat_Ceasar]");
        return buffer.toString();
    }
}
