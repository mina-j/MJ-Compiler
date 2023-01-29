// generated with ast extension for cup
// version 0.8
// 14/8/2022 8:29:17


package rs.ac.bg.etf.pp1.ast;

public class TermDecl extends Term {

    private Factor Factor;
    private MulopList MulopList;

    public TermDecl (Factor Factor, MulopList MulopList) {
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
        this.MulopList=MulopList;
        if(MulopList!=null) MulopList.setParent(this);
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public MulopList getMulopList() {
        return MulopList;
    }

    public void setMulopList(MulopList MulopList) {
        this.MulopList=MulopList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Factor!=null) Factor.accept(visitor);
        if(MulopList!=null) MulopList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
        if(MulopList!=null) MulopList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        if(MulopList!=null) MulopList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TermDecl(\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MulopList!=null)
            buffer.append(MulopList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TermDecl]");
        return buffer.toString();
    }
}
