// generated with ast extension for cup
// version 0.8
// 17/0/2023 12:43:33


package rs.ac.bg.etf.pp1.ast;

public class MultipleFormPars extends FormPars {

    private FormPars FormPars;
    private Type Type;
    private VarOrArr VarOrArr;

    public MultipleFormPars (FormPars FormPars, Type Type, VarOrArr VarOrArr) {
        this.FormPars=FormPars;
        if(FormPars!=null) FormPars.setParent(this);
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarOrArr=VarOrArr;
        if(VarOrArr!=null) VarOrArr.setParent(this);
    }

    public FormPars getFormPars() {
        return FormPars;
    }

    public void setFormPars(FormPars FormPars) {
        this.FormPars=FormPars;
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
        if(FormPars!=null) FormPars.accept(visitor);
        if(Type!=null) Type.accept(visitor);
        if(VarOrArr!=null) VarOrArr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormPars!=null) FormPars.traverseTopDown(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarOrArr!=null) VarOrArr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormPars!=null) FormPars.traverseBottomUp(visitor);
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarOrArr!=null) VarOrArr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleFormPars(\n");

        if(FormPars!=null)
            buffer.append(FormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

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
        buffer.append(") [MultipleFormPars]");
        return buffer.toString();
    }
}
