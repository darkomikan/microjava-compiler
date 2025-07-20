// generated with ast extension for cup
// version 0.8
// 17/0/2023 12:43:33


package rs.ac.bg.etf.pp1.ast;

public class MoreConst extends MoreConstDecl {

    private MoreConstDecl MoreConstDecl;
    private String name;
    private Literal Literal;

    public MoreConst (MoreConstDecl MoreConstDecl, String name, Literal Literal) {
        this.MoreConstDecl=MoreConstDecl;
        if(MoreConstDecl!=null) MoreConstDecl.setParent(this);
        this.name=name;
        this.Literal=Literal;
        if(Literal!=null) Literal.setParent(this);
    }

    public MoreConstDecl getMoreConstDecl() {
        return MoreConstDecl;
    }

    public void setMoreConstDecl(MoreConstDecl MoreConstDecl) {
        this.MoreConstDecl=MoreConstDecl;
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MoreConstDecl!=null) MoreConstDecl.accept(visitor);
        if(Literal!=null) Literal.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MoreConstDecl!=null) MoreConstDecl.traverseTopDown(visitor);
        if(Literal!=null) Literal.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MoreConstDecl!=null) MoreConstDecl.traverseBottomUp(visitor);
        if(Literal!=null) Literal.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreConst(\n");

        if(MoreConstDecl!=null)
            buffer.append(MoreConstDecl.toString("  "+tab));
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

        buffer.append(tab);
        buffer.append(") [MoreConst]");
        return buffer.toString();
    }
}
