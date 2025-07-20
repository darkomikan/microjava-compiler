// generated with ast extension for cup
// version 0.8
// 17/0/2023 12:43:33


package rs.ac.bg.etf.pp1.ast;

public class ConstructorsOnly extends OptConstructorsMethods {

    private ConstructorDeclListNoE ConstructorDeclListNoE;

    public ConstructorsOnly (ConstructorDeclListNoE ConstructorDeclListNoE) {
        this.ConstructorDeclListNoE=ConstructorDeclListNoE;
        if(ConstructorDeclListNoE!=null) ConstructorDeclListNoE.setParent(this);
    }

    public ConstructorDeclListNoE getConstructorDeclListNoE() {
        return ConstructorDeclListNoE;
    }

    public void setConstructorDeclListNoE(ConstructorDeclListNoE ConstructorDeclListNoE) {
        this.ConstructorDeclListNoE=ConstructorDeclListNoE;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstructorDeclListNoE!=null) ConstructorDeclListNoE.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstructorDeclListNoE!=null) ConstructorDeclListNoE.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstructorDeclListNoE!=null) ConstructorDeclListNoE.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstructorsOnly(\n");

        if(ConstructorDeclListNoE!=null)
            buffer.append(ConstructorDeclListNoE.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstructorsOnly]");
        return buffer.toString();
    }
}
