// generated with ast extension for cup
// version 0.8
// 19/5/2023 12:46:6


package rs.ac.bg.etf.pp1.ast;

public class FactorSign implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private UnaryMinus UnaryMinus;
    private Factor Factor;

    public FactorSign (UnaryMinus UnaryMinus, Factor Factor) {
        this.UnaryMinus=UnaryMinus;
        if(UnaryMinus!=null) UnaryMinus.setParent(this);
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
    }

    public UnaryMinus getUnaryMinus() {
        return UnaryMinus;
    }

    public void setUnaryMinus(UnaryMinus UnaryMinus) {
        this.UnaryMinus=UnaryMinus;
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(UnaryMinus!=null) UnaryMinus.accept(visitor);
        if(Factor!=null) Factor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(UnaryMinus!=null) UnaryMinus.traverseTopDown(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(UnaryMinus!=null) UnaryMinus.traverseBottomUp(visitor);
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorSign(\n");

        if(UnaryMinus!=null)
            buffer.append(UnaryMinus.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorSign]");
        return buffer.toString();
    }
}
