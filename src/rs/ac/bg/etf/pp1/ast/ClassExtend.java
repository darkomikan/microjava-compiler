// generated with ast extension for cup
// version 0.8
// 17/0/2023 12:43:33


package rs.ac.bg.etf.pp1.ast;

public class ClassExtend extends ClassDecl {

    private String name;
    private Type Type;
    private VarDeclList VarDeclList;
    private OptConstructorsMethods OptConstructorsMethods;

    public ClassExtend (String name, Type Type, VarDeclList VarDeclList, OptConstructorsMethods OptConstructorsMethods) {
        this.name=name;
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.OptConstructorsMethods=OptConstructorsMethods;
        if(OptConstructorsMethods!=null) OptConstructorsMethods.setParent(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public OptConstructorsMethods getOptConstructorsMethods() {
        return OptConstructorsMethods;
    }

    public void setOptConstructorsMethods(OptConstructorsMethods OptConstructorsMethods) {
        this.OptConstructorsMethods=OptConstructorsMethods;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(OptConstructorsMethods!=null) OptConstructorsMethods.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(OptConstructorsMethods!=null) OptConstructorsMethods.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(OptConstructorsMethods!=null) OptConstructorsMethods.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassExtend(\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptConstructorsMethods!=null)
            buffer.append(OptConstructorsMethods.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassExtend]");
        return buffer.toString();
    }
}
