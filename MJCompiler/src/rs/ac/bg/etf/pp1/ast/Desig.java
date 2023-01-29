// generated with ast extension for cup
// version 0.8
// 14/8/2022 8:29:17


package rs.ac.bg.etf.pp1.ast;

public class Desig extends Designator {

    private String dname;

    public Desig (String dname) {
        this.dname=dname;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname=dname;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Desig(\n");

        buffer.append(" "+tab+dname);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Desig]");
        return buffer.toString();
    }
}
