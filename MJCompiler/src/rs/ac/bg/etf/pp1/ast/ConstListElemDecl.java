// generated with ast extension for cup
// version 0.8
// 14/8/2022 8:29:17


package rs.ac.bg.etf.pp1.ast;

public class ConstListElemDecl extends ConstListElem {

    private String name;
    private Init Init;

    public ConstListElemDecl (String name, Init Init) {
        this.name=name;
        this.Init=Init;
        if(Init!=null) Init.setParent(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public Init getInit() {
        return Init;
    }

    public void setInit(Init Init) {
        this.Init=Init;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Init!=null) Init.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Init!=null) Init.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Init!=null) Init.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstListElemDecl(\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        if(Init!=null)
            buffer.append(Init.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstListElemDecl]");
        return buffer.toString();
    }
}
