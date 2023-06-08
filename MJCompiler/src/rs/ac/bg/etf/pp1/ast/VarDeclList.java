// generated with ast extension for cup
// version 0.8
// 8/5/2023 17:29:45


package rs.ac.bg.etf.pp1.ast;

public class VarDeclList implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private VarDeclOneOrMore VarDeclOneOrMore;

    public VarDeclList (Type Type, VarDeclOneOrMore VarDeclOneOrMore) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarDeclOneOrMore=VarDeclOneOrMore;
        if(VarDeclOneOrMore!=null) VarDeclOneOrMore.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarDeclOneOrMore getVarDeclOneOrMore() {
        return VarDeclOneOrMore;
    }

    public void setVarDeclOneOrMore(VarDeclOneOrMore VarDeclOneOrMore) {
        this.VarDeclOneOrMore=VarDeclOneOrMore;
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
        if(VarDeclOneOrMore!=null) VarDeclOneOrMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarDeclOneOrMore!=null) VarDeclOneOrMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarDeclOneOrMore!=null) VarDeclOneOrMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclList(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclOneOrMore!=null)
            buffer.append(VarDeclOneOrMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclList]");
        return buffer.toString();
    }
}
