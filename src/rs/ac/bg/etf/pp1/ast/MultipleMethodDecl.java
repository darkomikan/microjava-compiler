// generated with ast extension for cup
// version 0.8
// 17/0/2023 12:43:33


package rs.ac.bg.etf.pp1.ast;

public class MultipleMethodDecl extends MethodDeclListNoE {

    private MethodDeclListNoE MethodDeclListNoE;
    private MethodDecl MethodDecl;

    public MultipleMethodDecl (MethodDeclListNoE MethodDeclListNoE, MethodDecl MethodDecl) {
        this.MethodDeclListNoE=MethodDeclListNoE;
        if(MethodDeclListNoE!=null) MethodDeclListNoE.setParent(this);
        this.MethodDecl=MethodDecl;
        if(MethodDecl!=null) MethodDecl.setParent(this);
    }

    public MethodDeclListNoE getMethodDeclListNoE() {
        return MethodDeclListNoE;
    }

    public void setMethodDeclListNoE(MethodDeclListNoE MethodDeclListNoE) {
        this.MethodDeclListNoE=MethodDeclListNoE;
    }

    public MethodDecl getMethodDecl() {
        return MethodDecl;
    }

    public void setMethodDecl(MethodDecl MethodDecl) {
        this.MethodDecl=MethodDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodDeclListNoE!=null) MethodDeclListNoE.accept(visitor);
        if(MethodDecl!=null) MethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDeclListNoE!=null) MethodDeclListNoE.traverseTopDown(visitor);
        if(MethodDecl!=null) MethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDeclListNoE!=null) MethodDeclListNoE.traverseBottomUp(visitor);
        if(MethodDecl!=null) MethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleMethodDecl(\n");

        if(MethodDeclListNoE!=null)
            buffer.append(MethodDeclListNoE.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDecl!=null)
            buffer.append(MethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleMethodDecl]");
        return buffer.toString();
    }
}
