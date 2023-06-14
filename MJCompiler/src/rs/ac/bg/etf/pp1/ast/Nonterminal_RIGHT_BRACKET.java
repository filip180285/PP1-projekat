// generated with ast extension for cup
// version 0.8
// 14/5/2023 16:2:21


package rs.ac.bg.etf.pp1.ast;

public class Nonterminal_RIGHT_BRACKET implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public Nonterminal_RIGHT_BRACKET () {
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
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Nonterminal_RIGHT_BRACKET(\n");

        buffer.append(tab);
        buffer.append(") [Nonterminal_RIGHT_BRACKET]");
        return buffer.toString();
    }
}
