// generated with ast extension for cup
// version 0.8
// 17/0/2023 12:43:33


package rs.ac.bg.etf.pp1.ast;

public class ConstDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private DeclType DeclType;
    private String name;
    private Literal Literal;
    private MoreConstDecl MoreConstDecl;

    public ConstDecl (DeclType DeclType, String name, Literal Literal, MoreConstDecl MoreConstDecl) {
        this.DeclType=DeclType;
        if(DeclType!=null) DeclType.setParent(this);
        this.name=name;
        this.Literal=Literal;
        if(Literal!=null) Literal.setParent(this);
        this.MoreConstDecl=MoreConstDecl;
        if(MoreConstDecl!=null) MoreConstDecl.setParent(this);
    }

    public DeclType getDeclType() {
        return DeclType;
    }

    public void setDeclType(DeclType DeclType) {
        this.DeclType=DeclType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public Literal getLiteral() {
        return Literal;
    }

    public void setLiteral(Literal Literal) {
        this.Literal=Literal;
    }

    public MoreConstDecl getMoreConstDecl() {
        return MoreConstDecl;
    }

    public void setMoreConstDecl(MoreConstDecl MoreConstDecl) {
        this.MoreConstDecl=MoreConstDecl;
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
        if(DeclType!=null) DeclType.accept(visitor);
        if(Literal!=null) Literal.accept(visitor);
        if(MoreConstDecl!=null) MoreConstDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DeclType!=null) DeclType.traverseTopDown(visitor);
        if(Literal!=null) Literal.traverseTopDown(visitor);
        if(MoreConstDecl!=null) MoreConstDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DeclType!=null) DeclType.traverseBottomUp(visitor);
        if(Literal!=null) Literal.traverseBottomUp(visitor);
        if(MoreConstDecl!=null) MoreConstDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDecl(\n");

        if(DeclType!=null)
            buffer.append(DeclType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        if(Literal!=null)
            buffer.append(Literal.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MoreConstDecl!=null)
            buffer.append(MoreConstDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDecl]");
        return buffer.toString();
    }
}
