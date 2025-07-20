// generated with ast extension for cup
// version 0.8
// 17/0/2023 12:43:33


package rs.ac.bg.etf.pp1.ast;

public class MethodsOnly extends OptConstructorsMethods {

    private MethodDeclListNoE MethodDeclListNoE;

    public MethodsOnly (MethodDeclListNoE MethodDeclListNoE) {
        this.MethodDeclListNoE=MethodDeclListNoE;
        if(MethodDeclListNoE!=null) MethodDeclListNoE.setParent(this);
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
        if(MethodDeclListNoE!=null) MethodDeclListNoE.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDeclListNoE!=null) MethodDeclListNoE.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDeclListNoE!=null) MethodDeclListNoE.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodsOnly(\n");

        if(MethodDeclListNoE!=null)
            buffer.append(MethodDeclListNoE.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodsOnly]");
        return buffer.toString();
    }
}
