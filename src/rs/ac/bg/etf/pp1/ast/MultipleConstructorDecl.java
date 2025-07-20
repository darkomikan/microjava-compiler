// generated with ast extension for cup
// version 0.8
// 17/0/2023 12:43:33


package rs.ac.bg.etf.pp1.ast;

public class MultipleConstructorDecl extends ConstructorDeclListNoE {

    private ConstructorDeclListNoE ConstructorDeclListNoE;
    private ConstructorDecl ConstructorDecl;

    public MultipleConstructorDecl (ConstructorDeclListNoE ConstructorDeclListNoE, ConstructorDecl ConstructorDecl) {
        this.ConstructorDeclListNoE=ConstructorDeclListNoE;
        if(ConstructorDeclListNoE!=null) ConstructorDeclListNoE.setParent(this);
        this.ConstructorDecl=ConstructorDecl;
        if(ConstructorDecl!=null) ConstructorDecl.setParent(this);
    }

    public ConstructorDeclListNoE getConstructorDeclListNoE() {
        return ConstructorDeclListNoE;
    }

    public void setConstructorDeclListNoE(ConstructorDeclListNoE ConstructorDeclListNoE) {
        this.ConstructorDeclListNoE=ConstructorDeclListNoE;
    }

    public ConstructorDecl getConstructorDecl() {
        return ConstructorDecl;
    }

    public void setConstructorDecl(ConstructorDecl ConstructorDecl) {
        this.ConstructorDecl=ConstructorDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstructorDeclListNoE!=null) ConstructorDeclListNoE.accept(visitor);
        if(ConstructorDecl!=null) ConstructorDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstructorDeclListNoE!=null) ConstructorDeclListNoE.traverseTopDown(visitor);
        if(ConstructorDecl!=null) ConstructorDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstructorDeclListNoE!=null) ConstructorDeclListNoE.traverseBottomUp(visitor);
        if(ConstructorDecl!=null) ConstructorDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleConstructorDecl(\n");

        if(ConstructorDeclListNoE!=null)
            buffer.append(ConstructorDeclListNoE.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstructorDecl!=null)
            buffer.append(ConstructorDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleConstructorDecl]");
        return buffer.toString();
    }
}
