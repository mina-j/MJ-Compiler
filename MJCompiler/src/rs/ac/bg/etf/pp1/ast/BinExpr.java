// generated with ast extension for cup
// version 0.8
// 14/8/2022 8:29:17


package rs.ac.bg.etf.pp1.ast;

public class BinExpr extends Expr {

    private ExprElem ExprElem;
    private BinDq BinDq;
    private ExprElem ExprElem1;
    private ExprEnd ExprEnd;

    public BinExpr (ExprElem ExprElem, BinDq BinDq, ExprElem ExprElem1, ExprEnd ExprEnd) {
        this.ExprElem=ExprElem;
        if(ExprElem!=null) ExprElem.setParent(this);
        this.BinDq=BinDq;
        if(BinDq!=null) BinDq.setParent(this);
        this.ExprElem1=ExprElem1;
        if(ExprElem1!=null) ExprElem1.setParent(this);
        this.ExprEnd=ExprEnd;
        if(ExprEnd!=null) ExprEnd.setParent(this);
    }

    public ExprElem getExprElem() {
        return ExprElem;
    }

    public void setExprElem(ExprElem ExprElem) {
        this.ExprElem=ExprElem;
    }

    public BinDq getBinDq() {
        return BinDq;
    }

    public void setBinDq(BinDq BinDq) {
        this.BinDq=BinDq;
    }

    public ExprElem getExprElem1() {
        return ExprElem1;
    }

    public void setExprElem1(ExprElem ExprElem1) {
        this.ExprElem1=ExprElem1;
    }

    public ExprEnd getExprEnd() {
        return ExprEnd;
    }

    public void setExprEnd(ExprEnd ExprEnd) {
        this.ExprEnd=ExprEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprElem!=null) ExprElem.accept(visitor);
        if(BinDq!=null) BinDq.accept(visitor);
        if(ExprElem1!=null) ExprElem1.accept(visitor);
        if(ExprEnd!=null) ExprEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprElem!=null) ExprElem.traverseTopDown(visitor);
        if(BinDq!=null) BinDq.traverseTopDown(visitor);
        if(ExprElem1!=null) ExprElem1.traverseTopDown(visitor);
        if(ExprEnd!=null) ExprEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprElem!=null) ExprElem.traverseBottomUp(visitor);
        if(BinDq!=null) BinDq.traverseBottomUp(visitor);
        if(ExprElem1!=null) ExprElem1.traverseBottomUp(visitor);
        if(ExprEnd!=null) ExprEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BinExpr(\n");

        if(ExprElem!=null)
            buffer.append(ExprElem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(BinDq!=null)
            buffer.append(BinDq.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprElem1!=null)
            buffer.append(ExprElem1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprEnd!=null)
            buffer.append(ExprEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BinExpr]");
        return buffer.toString();
    }
}
