// generated with ast extension for cup
// version 0.8
// 27/5/2023 22:18:56


package rs.ac.bg.etf.pp1.ast;

public class Term_MORE extends Term {

    private Term Term;
    private Mulop Mulop;
    private FactorSign FactorSign;

    public Term_MORE (Term Term, Mulop Mulop, FactorSign FactorSign) {
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
        this.Mulop=Mulop;
        if(Mulop!=null) Mulop.setParent(this);
        this.FactorSign=FactorSign;
        if(FactorSign!=null) FactorSign.setParent(this);
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public Mulop getMulop() {
        return Mulop;
    }

    public void setMulop(Mulop Mulop) {
        this.Mulop=Mulop;
    }

    public FactorSign getFactorSign() {
        return FactorSign;
    }

    public void setFactorSign(FactorSign FactorSign) {
        this.FactorSign=FactorSign;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Term!=null) Term.accept(visitor);
        if(Mulop!=null) Mulop.accept(visitor);
        if(FactorSign!=null) FactorSign.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
        if(Mulop!=null) Mulop.traverseTopDown(visitor);
        if(FactorSign!=null) FactorSign.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Term!=null) Term.traverseBottomUp(visitor);
        if(Mulop!=null) Mulop.traverseBottomUp(visitor);
        if(FactorSign!=null) FactorSign.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Term_MORE(\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Mulop!=null)
            buffer.append(Mulop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FactorSign!=null)
            buffer.append(FactorSign.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Term_MORE]");
        return buffer.toString();
    }
}
