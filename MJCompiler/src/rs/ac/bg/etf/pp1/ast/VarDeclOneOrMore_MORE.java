// generated with ast extension for cup
// version 0.8
// 11/5/2023 2:53:27


package rs.ac.bg.etf.pp1.ast;

public class VarDeclOneOrMore_MORE extends VarDeclOneOrMore {

    private VarDecl VarDecl;
    private VarDeclOneOrMore VarDeclOneOrMore;

    public VarDeclOneOrMore_MORE (VarDecl VarDecl, VarDeclOneOrMore VarDeclOneOrMore) {
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
        this.VarDeclOneOrMore=VarDeclOneOrMore;
        if(VarDeclOneOrMore!=null) VarDeclOneOrMore.setParent(this);
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
    }

    public VarDeclOneOrMore getVarDeclOneOrMore() {
        return VarDeclOneOrMore;
    }

    public void setVarDeclOneOrMore(VarDeclOneOrMore VarDeclOneOrMore) {
        this.VarDeclOneOrMore=VarDeclOneOrMore;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDecl!=null) VarDecl.accept(visitor);
        if(VarDeclOneOrMore!=null) VarDeclOneOrMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
        if(VarDeclOneOrMore!=null) VarDeclOneOrMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        if(VarDeclOneOrMore!=null) VarDeclOneOrMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclOneOrMore_MORE(\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclOneOrMore!=null)
            buffer.append(VarDeclOneOrMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclOneOrMore_MORE]");
        return buffer.toString();
    }
}
