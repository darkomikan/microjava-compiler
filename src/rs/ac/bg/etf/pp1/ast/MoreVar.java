// generated with ast extension for cup
// version 0.8
// 17/0/2023 12:43:33


package rs.ac.bg.etf.pp1.ast;

public class MoreVar extends MoreVarDecl {

    private MoreVarDecl MoreVarDecl;
    private AnotherVarDecl AnotherVarDecl;

    public MoreVar (MoreVarDecl MoreVarDecl, AnotherVarDecl AnotherVarDecl) {
        this.MoreVarDecl=MoreVarDecl;
        if(MoreVarDecl!=null) MoreVarDecl.setParent(this);
        this.AnotherVarDecl=AnotherVarDecl;
        if(AnotherVarDecl!=null) AnotherVarDecl.setParent(this);
    }

    public MoreVarDecl getMoreVarDecl() {
        return MoreVarDecl;
    }

    public void setMoreVarDecl(MoreVarDecl MoreVarDecl) {
        this.MoreVarDecl=MoreVarDecl;
    }

    public AnotherVarDecl getAnotherVarDecl() {
        return AnotherVarDecl;
    }

    public void setAnotherVarDecl(AnotherVarDecl AnotherVarDecl) {
        this.AnotherVarDecl=AnotherVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MoreVarDecl!=null) MoreVarDecl.accept(visitor);
        if(AnotherVarDecl!=null) AnotherVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MoreVarDecl!=null) MoreVarDecl.traverseTopDown(visitor);
        if(AnotherVarDecl!=null) AnotherVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MoreVarDecl!=null) MoreVarDecl.traverseBottomUp(visitor);
        if(AnotherVarDecl!=null) AnotherVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreVar(\n");

        if(MoreVarDecl!=null)
            buffer.append(MoreVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AnotherVarDecl!=null)
            buffer.append(AnotherVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreVar]");
        return buffer.toString();
    }
}
