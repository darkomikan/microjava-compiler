// generated with ast extension for cup
// version 0.8
// 17/0/2023 12:43:33


package rs.ac.bg.etf.pp1.ast;

public class SingleFormPars extends FormPars {

    private Type Type;
    private VarOrArr VarOrArr;

    public SingleFormPars (Type Type, VarOrArr VarOrArr) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarOrArr=VarOrArr;
        if(VarOrArr!=null) VarOrArr.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarOrArr getVarOrArr() {
        return VarOrArr;
    }

    public void setVarOrArr(VarOrArr VarOrArr) {
        this.VarOrArr=VarOrArr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(VarOrArr!=null) VarOrArr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarOrArr!=null) VarOrArr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarOrArr!=null) VarOrArr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleFormPars(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarOrArr!=null)
            buffer.append(VarOrArr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleFormPars]");
        return buffer.toString();
    }
}
