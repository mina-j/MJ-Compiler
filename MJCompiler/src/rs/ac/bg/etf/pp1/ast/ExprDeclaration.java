// generated with ast extension for cup
// version 0.8
// 14/8/2022 8:29:17


package rs.ac.bg.etf.pp1.ast;

public class ExprDeclaration extends Expr {

    private ExprElem ExprElem;

    public ExprDeclaration (ExprElem ExprElem) {
        this.ExprElem=ExprElem;
        if(ExprElem!=null) ExprElem.setParent(this);
    }

    public ExprElem getExprElem() {
        return ExprElem;
    }

    public void setExprElem(ExprElem ExprElem) {
        this.ExprElem=ExprElem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprElem!=null) ExprElem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprElem!=null) ExprElem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprElem!=null) ExprElem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprDeclaration(\n");

        if(ExprElem!=null)
            buffer.append(ExprElem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprDeclaration]");
        return buffer.toString();
    }
}
