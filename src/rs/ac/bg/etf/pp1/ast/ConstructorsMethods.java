// generated with ast extension for cup
// version 0.8
// 17/0/2023 12:43:33


package rs.ac.bg.etf.pp1.ast;

public class ConstructorsMethods extends OptConstructorsMethods {

    private ConstructorDeclListNoE ConstructorDeclListNoE;
    private MethodDeclListNoE MethodDeclListNoE;

    public ConstructorsMethods (ConstructorDeclListNoE ConstructorDeclListNoE, MethodDeclListNoE MethodDeclListNoE) {
        this.ConstructorDeclListNoE=ConstructorDeclListNoE;
        if(ConstructorDeclListNoE!=null) ConstructorDeclListNoE.setParent(this);
        this.MethodDeclListNoE=MethodDeclListNoE;
        if(MethodDeclListNoE!=null) MethodDeclListNoE.setParent(this);
    }

    public ConstructorDeclListNoE getConstructorDeclListNoE() {
        return ConstructorDeclListNoE;
    }

    public void setConstructorDeclListNoE(ConstructorDeclListNoE ConstructorDeclListNoE) {
        this.ConstructorDeclListNoE=ConstructorDeclListNoE;
    }

    public MethodDeclListNoE getMethodDeclListNoE() {
        return MethodDeclListNoE;
    }

    public void setMethodDeclListNoE(MethodDeclListNoE MethodDeclListNoE) {
        this.MethodDeclListNoE=MethodDeclListNoE;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstructorDeclListNoE!=null) ConstructorDeclListNoE.accept(visitor);
        if(MethodDeclListNoE!=null) MethodDeclListNoE.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstructorDeclListNoE!=null) ConstructorDeclListNoE.traverseTopDown(visitor);
        if(MethodDeclListNoE!=null) MethodDeclListNoE.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstructorDeclListNoE!=null) ConstructorDeclListNoE.traverseBottomUp(visitor);
        if(MethodDeclListNoE!=null) MethodDeclListNoE.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstructorsMethods(\n");

        if(ConstructorDeclListNoE!=null)
            buffer.append(ConstructorDeclListNoE.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclListNoE!=null)
            buffer.append(MethodDeclListNoE.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstructorsMethods]");
        return buffer.toString();
    }
}
