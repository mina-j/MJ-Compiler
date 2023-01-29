package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;


public class CodeGenerator extends VisitorAdaptor {
	private int mainPc;
	
	//private Obj tempDes1 = Tab.noObj;
	
	//private Obj tempDes2 = Tab.noObj;
	private int adr;
	
	
/*	public CodeGenerator(){
		tempDes1.setAdr(0);
		tempDes2.setAdr(0);
	}*/

	public int getMainPc() {
		return mainPc;
	}
	
	public void visit(ReadStmt r) {
		Obj o= r.getDesignator().obj;
		if (o.getType() == Tab.intType) {
			Code.put(Code.read);
			
		} else {
			Code.put(Code.bread); 
		}
		Code.store(o);
	}
	
	public void visit(PrintStmt print){
		
		if (print.getExpr().struct == Tab.intType) {
			Code.loadConst(5);
			Code.put(Code.print);
		} else {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}
	
	public void visit(PrintNumStmt print){
		
		Code.loadConst(print.getNumConst().getN1()); // width
		if (print.getExpr().struct == Tab.intType) {
			Code.put(Code.print);
		} else{
			Code.put(Code.bprint);
		}
	}
	
	public void visit(RetExStmt ret){
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(NoRet ret){
		Code.put(Code.exit);
		Code.put(Code.return_);
	}	
	
	

	
	public void visit(NumConst num){
		Obj o = Tab.insert(Obj.Con, "$", num.struct);
		o.setLevel(0);
		o.setAdr(num.getN1());
		//if(tempDes1.getAdr()==0){
		//tempDes1.setAdr(num.getN1());}else{tempDes2.setAdr(num.getN1());}
		Code.load(o);
	}
	
	public void visit(CharConst num){
		Obj o = Tab.insert(Obj.Con, "$", num.struct);
		o.setLevel(0);
		o.setAdr(num.getC1());
		
		Code.load(o);
	}
	
	public void visit(BoolConst num){
		Obj o = Tab.insert(Obj.Con, "$", num.struct); 
		o.setLevel(0);
		
        if (num.getB1()) {
        	o.setAdr(1);
        }else{
        	o.setAdr(0);
        }		
		Code.load(o);
        
	}
	
	public void visit(MtdTypeName mtdTypeName) {
		if (mtdTypeName.getMethName().equals("main")) {
			mainPc = Code.pc;
		}
		mtdTypeName.obj.setAdr(Code.pc);
		// parametri i lokalne promenljive
		
		
		//prosledjujemo za generisanje enter instrukcije
		Code.put(Code.enter);
		Code.put(mtdTypeName.obj.getLevel());
		Code.put(mtdTypeName.obj.getLocalSymbols().size());//OVDE POTENCIJALNO GRESI 
		
		//izgenerisali smo pocetne instrukcije za bilo koju funkciju

	}
	
	public void visit(MethodDeclarations methodDecl){
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(EqualStmt assignment) {
		
		/*if (assignment.getDesignator() instanceof DesigArray){
			if(assignment.getDesignator().obj.getType()==Tab.charType){
				Code.put(Code.bastore);
			}else {Code.put(Code.astore);}
			return;
		}*/

		Code.store(assignment.getDesignator().obj);

	}
	
	public void visit(Desig designator){
		SyntaxNode parent = designator.getParent();
		//za slucaj da je samo promenljiva tj ako se promenljiva promeni u izrazu da se njena vr stavi na expr stek
		if(EqualStmt.class != parent.getClass() && IncStmt.class!=parent.getClass() && DecStmt.class!=parent.getClass()){ //parent instanceof EqualStmt
			Code.load(designator.obj);
		}
	}
	
	public void visit(ArrIdentDecl a){
		Code.load(a.obj);
	}
	
	public void visit(DesigArray designator){
		
		
		SyntaxNode parent = designator.getParent();
		if(EqualStmt.class != parent.getClass() && IncStmt.class!=parent.getClass() && DecStmt.class!=parent.getClass()){
			//Code.load(designator.obj);
			//PROMENJENO
			if(designator.obj.getType()==Tab.charType){
				Code.put(Code.baload);
			}else {Code.put(Code.aload);}
		}
	}
	
	public void visit(IncStmt inc){
		Obj o=inc.getDesignator().obj;
		if (inc.getDesignator() instanceof DesigArray){
			Code.put(Code.dup2);//pravi duplikat			
			
		}
		Code.load(o);
		Code.loadConst(1);//dodaje 1
		Code.put(Code.add);
		Code.store(o);//vraca vr
	}
	
	public void visit(DecStmt inc){
		if (inc.getDesignator() instanceof DesigArray){
			Code.put(Code.dup2);//pravi duplikat
			
		}
		Code.load(inc.getDesignator().obj);//dohvata des
		Code.loadConst(-1);//oduzima 1
		Code.put(Code.add);
		Code.store(inc.getDesignator().obj);//vraca ga na stek
	}
	
	//za faktr
	public void visit(NewDeclFactor newf){
		//velicina niza je vec na steku kao expr, sad stavljamo ovo na expr stek
		Code.put(Code.newarray);
		if(newf.getType().struct==Tab.intType){
			Code.put(1);
		}else { Code.put(0);}
		
	}
	
	public void visit(DesigFactor df) {
		/*if (df.getDesignator() instanceof DesigArray) {
			Code.put(Code.dup2);
			if (df.getDesignator().obj.getType() == Tab.intType) {
				Code.put(Code.aload);
			} else {
				Code.put(Code.baload);
			}
		}else{
			Code.load(df.getDesignator().obj);
		}*/
		
	}
	
	//za expr
	
	public void visit(NegExprElemDecl neg){
		Code.loadConst(-1);
        Code.put(Code.mul);
	}
	
	
	public void visit(AddopListDecl oplist){
		if(oplist.getAddop() instanceof Sub){
			Code.put(Code.sub);
			return;
		}
		Code.put(Code.add);
	}
	
	public void visit(MulopListDecl oplist){
		if(oplist.getMulop() instanceof Mul){
			Code.put(Code.mul);
		}else if(oplist.getMulop() instanceof Div){
			Code.put(Code.div);
		}else {
			Code.put(Code.rem);
		}
	}
	
	
	
	
	public void visit(BinOpSign b) {
		SyntaxNode p=b.getParent().getParent();
		
		
		Code.put(Code.dup);
		Code.loadConst(0);
		
		if(DesigArray.class == p.getClass()){Code.putFalseJump(Code.eq, 0);}
		else {Code.putFalseJump(Code.eq, 0);}
		//Code.putFalseJump(Code.eq, 0);
		adr = Code.pc-2 ;		
		
		
		
		/* Code.put(Code.dup);
		  Code.loadConst(0); 
		  Code.put(Code.jcc + Code.ne);
		  if(DesigArray.class == p.getClass()){
		  Code.put2(7); Code.put(Code.pop);} else {Code.put2(4);}
		  */
		  
		 

	}
	
	public void visit(EndOfBin eob){
		SyntaxNode p=eob.getParent().getParent();
		Obj o=Tab.noObj;
		Obj o2=Tab.noObj;
		//if(DesigArray.class==p.getClass())
			//Code.put(Code.pop);
		Code.store(o);
		Code.put(Code.pop);
		Code.load(o);
		Code.fixup(adr);
	}
	
		
	

}
