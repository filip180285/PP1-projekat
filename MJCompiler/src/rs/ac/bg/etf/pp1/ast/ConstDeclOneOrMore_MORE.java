// generated with ast extension for cup
// version 0.8
// 10/5/2023 23:56:58


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclOneOrMore_MORE extends ConstDeclOneOrMore {

    private ConstDecl ConstDecl;
    private ConstDeclOneOrMore ConstDeclOneOrMore;

    public ConstDeclOneOrMore_MORE (ConstDecl ConstDecl, ConstDeclOneOrMore ConstDeclOneOrMore) {
        this.ConstDecl=ConstDecl;
        if(ConstDecl!=null) ConstDecl.setParent(this);
        this.ConstDeclOneOrMore=ConstDeclOneOrMore;
        if(ConstDeclOneOrMore!=null) ConstDeclOneOrMore.setParent(this);
    }

    public ConstDecl getConstDecl() {
        return ConstDecl;
    }

    public void setConstDecl(ConstDecl ConstDecl) {
        this.ConstDecl=ConstDecl;
    }

    public ConstDeclOneOrMore getConstDeclOneOrMore() {
        return ConstDeclOneOrMore;
    }

    public void setConstDeclOneOrMore(ConstDeclOneOrMore ConstDeclOneOrMore) {
        this.ConstDeclOneOrMore=ConstDeclOneOrMore;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDecl!=null) ConstDecl.accept(visitor);
        if(ConstDeclOneOrMore!=null) ConstDeclOneOrMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDecl!=null) ConstDecl.traverseTopDown(visitor);
        if(ConstDeclOneOrMore!=null) ConstDeclOneOrMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDecl!=null) ConstDecl.traverseBottomUp(visitor);
        if(ConstDeclOneOrMore!=null) ConstDeclOneOrMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclOneOrMore_MORE(\n");

        if(ConstDecl!=null)
            buffer.append(ConstDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclOneOrMore!=null)
            buffer.append(ConstDeclOneOrMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclOneOrMore_MORE]");
        return buffer.toString();
    }
}
