// generated with ast extension for cup
// version 0.8
// 14/8/2022 8:29:17


package rs.ac.bg.etf.pp1.ast;

public class PrintNumStmt extends SingleStatement {

    private Expr Expr;
    private NumConst numConst;

    public PrintNumStmt (Expr Expr, NumConst numConst) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.numConst=numConst;
        if(numConst!=null) numConst.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public NumConst getNumConst() {
        return numConst;
    }

    public void setNumConst(NumConst numConst) {
        this.numConst=numConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(numConst!=null) numConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(numConst!=null) numConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(numConst!=null) numConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintNumStmt(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(numConst!=null)
            buffer.append(numConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintNumStmt]");
        return buffer.toString();
    }
}
