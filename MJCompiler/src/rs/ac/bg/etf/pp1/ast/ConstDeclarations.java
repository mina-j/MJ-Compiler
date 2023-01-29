// generated with ast extension for cup
// version 0.8
// 14/8/2022 8:29:17


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclarations extends ConstDecl {

    private Type Type;
    private ConstListDecl ConstListDecl;

    public ConstDeclarations (Type Type, ConstListDecl ConstListDecl) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ConstListDecl=ConstListDecl;
        if(ConstListDecl!=null) ConstListDecl.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public ConstListDecl getConstListDecl() {
        return ConstListDecl;
    }

    public void setConstListDecl(ConstListDecl ConstListDecl) {
        this.ConstListDecl=ConstListDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(ConstListDecl!=null) ConstListDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ConstListDecl!=null) ConstListDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ConstListDecl!=null) ConstListDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclarations(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstListDecl!=null)
            buffer.append(ConstListDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclarations]");
        return buffer.toString();
    }
}
