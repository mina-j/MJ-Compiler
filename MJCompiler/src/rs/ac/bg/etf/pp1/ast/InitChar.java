// generated with ast extension for cup
// version 0.8
// 14/8/2022 8:29:17


package rs.ac.bg.etf.pp1.ast;

public class InitChar extends Init {

    private CharConst charConst;

    public InitChar (CharConst charConst) {
        this.charConst=charConst;
        if(charConst!=null) charConst.setParent(this);
    }

    public CharConst getCharConst() {
        return charConst;
    }

    public void setCharConst(CharConst charConst) {
        this.charConst=charConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(charConst!=null) charConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(charConst!=null) charConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(charConst!=null) charConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("InitChar(\n");

        if(charConst!=null)
            buffer.append(charConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [InitChar]");
        return buffer.toString();
    }
}
