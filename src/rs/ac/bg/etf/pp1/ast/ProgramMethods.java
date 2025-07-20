// generated with ast extension for cup
// version 0.8
// 17/0/2023 12:43:33


package rs.ac.bg.etf.pp1.ast;

public class ProgramMethods extends Program {

    private ProgName ProgName;
    private GlobalDeclList GlobalDeclList;
    private MethodDeclListNoE MethodDeclListNoE;

    public ProgramMethods (ProgName ProgName, GlobalDeclList GlobalDeclList, MethodDeclListNoE MethodDeclListNoE) {
        this.ProgName=ProgName;
        if(ProgName!=null) ProgName.setParent(this);
        this.GlobalDeclList=GlobalDeclList;
        if(GlobalDeclList!=null) GlobalDeclList.setParent(this);
        this.MethodDeclListNoE=MethodDeclListNoE;
        if(MethodDeclListNoE!=null) MethodDeclListNoE.setParent(this);
    }

    public ProgName getProgName() {
        return ProgName;
    }

    public void setProgName(ProgName ProgName) {
        this.ProgName=ProgName;
    }

    public GlobalDeclList getGlobalDeclList() {
        return GlobalDeclList;
    }

    public void setGlobalDeclList(GlobalDeclList GlobalDeclList) {
        this.GlobalDeclList=GlobalDeclList;
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
        if(ProgName!=null) ProgName.accept(visitor);
        if(GlobalDeclList!=null) GlobalDeclList.accept(visitor);
        if(MethodDeclListNoE!=null) MethodDeclListNoE.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgName!=null) ProgName.traverseTopDown(visitor);
        if(GlobalDeclList!=null) GlobalDeclList.traverseTopDown(visitor);
        if(MethodDeclListNoE!=null) MethodDeclListNoE.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgName!=null) ProgName.traverseBottomUp(visitor);
        if(GlobalDeclList!=null) GlobalDeclList.traverseBottomUp(visitor);
        if(MethodDeclListNoE!=null) MethodDeclListNoE.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramMethods(\n");

        if(ProgName!=null)
            buffer.append(ProgName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobalDeclList!=null)
            buffer.append(GlobalDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclListNoE!=null)
            buffer.append(MethodDeclListNoE.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramMethods]");
        return buffer.toString();
    }
}
