// generated with ast extension for cup
// version 0.8
// 17/0/2023 12:43:33


package rs.ac.bg.etf.pp1.ast;

public class VarDeclStart extends VarDecl {

    private DeclType DeclType;
    private VarOrArr VarOrArr;
    private MoreVarDecl MoreVarDecl;

    public VarDeclStart (DeclType DeclType, VarOrArr VarOrArr, MoreVarDecl MoreVarDecl) {
        this.DeclType=DeclType;
        if(DeclType!=null) DeclType.setParent(this);
        this.VarOrArr=VarOrArr;
        if(VarOrArr!=null) VarOrArr.setParent(this);
        this.MoreVarDecl=MoreVarDecl;
        if(MoreVarDecl!=null) MoreVarDecl.setParent(this);
    }

    public DeclType getDeclType() {
        return DeclType;
    }

    public void setDeclType(DeclType DeclType) {
        this.DeclType=DeclType;
    }

    public VarOrArr getVarOrArr() {
        return VarOrArr;
    }

    public void setVarOrArr(VarOrArr VarOrArr) {
        this.VarOrArr=VarOrArr;
    }

    public MoreVarDecl getMoreVarDecl() {
        return MoreVarDecl;
    }

    public void setMoreVarDecl(MoreVarDecl MoreVarDecl) {
        this.MoreVarDecl=MoreVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DeclType!=null) DeclType.accept(visitor);
        if(VarOrArr!=null) VarOrArr.accept(visitor);
        if(MoreVarDecl!=null) MoreVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DeclType!=null) DeclType.traverseTopDown(visitor);
        if(VarOrArr!=null) VarOrArr.traverseTopDown(visitor);
        if(MoreVarDecl!=null) MoreVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DeclType!=null) DeclType.traverseBottomUp(visitor);
        if(VarOrArr!=null) VarOrArr.traverseBottomUp(visitor);
        if(MoreVarDecl!=null) MoreVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclStart(\n");

        if(DeclType!=null)
            buffer.append(DeclType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarOrArr!=null)
            buffer.append(VarOrArr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MoreVarDecl!=null)
            buffer.append(MoreVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclStart]");
        return buffer.toString();
    }
}
