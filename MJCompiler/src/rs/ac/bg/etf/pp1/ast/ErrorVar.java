// generated with ast extension for cup
// version 0.8
// 14/8/2022 8:29:17


package rs.ac.bg.etf.pp1.ast;

public class ErrorVar extends Var {

    public ErrorVar () {
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
        buffer.append("ErrorVar(\n");

        buffer.append(tab);
        buffer.append(") [ErrorVar]");
        return buffer.toString();
    }
}