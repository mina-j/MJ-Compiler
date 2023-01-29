// generated with ast extension for cup
// version 0.8
// 14/8/2022 8:29:17


package rs.ac.bg.etf.pp1.ast;

public class EqualStmt extends DesignatorStatement {

    private Designator Designator;
    private EqualStatement EqualStatement;

    public EqualStmt (Designator Designator, EqualStatement EqualStatement) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.EqualStatement=EqualStatement;
        if(EqualStatement!=null) EqualStatement.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public EqualStatement getEqualStatement() {
        return EqualStatement;
    }

    public void setEqualStatement(EqualStatement EqualStatement) {
        this.EqualStatement=EqualStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(EqualStatement!=null) EqualStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(EqualStatement!=null) EqualStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(EqualStatement!=null) EqualStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EqualStmt(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EqualStatement!=null)
            buffer.append(EqualStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EqualStmt]");
        return buffer.toString();
    }
}
