// generated with ast extension for cup
// version 0.8
// 6/4/2023 17:10:36


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclList implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private ConstDeclOneOrMore ConstDeclOneOrMore;

    public ConstDeclList (Type Type, ConstDeclOneOrMore ConstDeclOneOrMore) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ConstDeclOneOrMore=ConstDeclOneOrMore;
        if(ConstDeclOneOrMore!=null) ConstDeclOneOrMore.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public ConstDeclOneOrMore getConstDeclOneOrMore() {
        return ConstDeclOneOrMore;
    }

    public void setConstDeclOneOrMore(ConstDeclOneOrMore ConstDeclOneOrMore) {
        this.ConstDeclOneOrMore=ConstDeclOneOrMore;
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
        if(Type!=null) Type.accept(visitor);
        if(ConstDeclOneOrMore!=null) ConstDeclOneOrMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ConstDeclOneOrMore!=null) ConstDeclOneOrMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ConstDeclOneOrMore!=null) ConstDeclOneOrMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclList(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclOneOrMore!=null)
            buffer.append(ConstDeclOneOrMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclList]");
        return buffer.toString();
    }
}
