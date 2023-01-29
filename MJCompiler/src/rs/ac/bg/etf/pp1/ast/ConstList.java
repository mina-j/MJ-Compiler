// generated with ast extension for cup
// version 0.8
// 14/8/2022 8:29:17


package rs.ac.bg.etf.pp1.ast;

public class ConstList extends ConstListDecl {

    private ConstListDecl ConstListDecl;
    private ConstListElem ConstListElem;

    public ConstList (ConstListDecl ConstListDecl, ConstListElem ConstListElem) {
        this.ConstListDecl=ConstListDecl;
        if(ConstListDecl!=null) ConstListDecl.setParent(this);
        this.ConstListElem=ConstListElem;
        if(ConstListElem!=null) ConstListElem.setParent(this);
    }

    public ConstListDecl getConstListDecl() {
        return ConstListDecl;
    }

    public void setConstListDecl(ConstListDecl ConstListDecl) {
        this.ConstListDecl=ConstListDecl;
    }

    public ConstListElem getConstListElem() {
        return ConstListElem;
    }

    public void setConstListElem(ConstListElem ConstListElem) {
        this.ConstListElem=ConstListElem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstListDecl!=null) ConstListDecl.accept(visitor);
        if(ConstListElem!=null) ConstListElem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstListDecl!=null) ConstListDecl.traverseTopDown(visitor);
        if(ConstListElem!=null) ConstListElem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstListDecl!=null) ConstListDecl.traverseBottomUp(visitor);
        if(ConstListElem!=null) ConstListElem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstList(\n");

        if(ConstListDecl!=null)
            buffer.append(ConstListDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstListElem!=null)
            buffer.append(ConstListElem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstList]");
        return buffer.toString();
    }
}
