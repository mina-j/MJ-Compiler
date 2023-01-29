package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;


public class SemanticAnalyzer extends VisitorAdaptor {
	int globvarDeclCount = 0;
	int locvarDeclCount = 0;
	int printCallCount = 0;
	int constDeclCount = 0;
	int nVars;
	int paramCount=0;
	//treba za pristup elem niza, glob prom, lokalne prom
	
	boolean errorDetected = false;
	boolean hasRet = false;
	
	Struct currentType= null;
	Obj currentMethod=null;

	Logger log = Logger.getLogger(getClass());
	
	public boolean passed(){
    	return !errorDetected;
	}
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public void visit(MtdTypeName mtdTypeName){
		Object o= Tab.find(mtdTypeName.getMethName());
		if(o==Tab.noObj){
		currentMethod= Tab.insert(Obj.Meth, mtdTypeName.getMethName(), currentType);
		mtdTypeName.obj=currentMethod;
		Tab.openScope();
		paramCount=0;
		report_info("Obradjuje se funkcija " + mtdTypeName.getMethName(), mtdTypeName);
		}else{
			report_error("Greska: Ime metode " + mtdTypeName.getMethName() + " vec postoji!", null);
		
		}
	}
	

	public void visit(ReadStmt r){
		report_info("Procitana promenljiva " + r.getDesignator().obj.getName(), null);
		
	}
	
    public void visit(PrintStmt print) {
    	if(print.getExpr().struct != Tab.intType && print.getExpr().struct!= Tab.charType) report_error ("Semanticka greska na liniji " + print.getLine() + ": Operand instrukcije PRINT mora biti char ili int tipa", null );
		printCallCount++;
	}
    
    public void visit(PrintNumStmt print) {
    	if(print.getExpr().struct != Tab.intType && print.getExpr().struct!= Tab.charType) report_error ("Semanticka greska na liniji " + print.getLine() + ": Operand instrukcije PRINT mora biti char ili int tipa", null );
		printCallCount++;
	}
    
	public void visit(Variable vardecl){
		
		if(Tab.find(vardecl.getName())!=Tab.noObj){
			if(Tab.currentScope.findSymbol(vardecl.getName())!=null){
			report_error("Greska: Ime " + vardecl.getName() + " vec postoji!", vardecl);
			return;
			}
		}
		
		if(vardecl.getArray() instanceof ArrDecl){
			Struct a = new Struct(Struct.Array, currentType);
			
			if(currentMethod==null){
				report_info("Deklarisana globalna promenljiva niz "+ vardecl.getName(), vardecl);
			}else{
				report_info("Deklarisana lokalna promenljiva niz "+ vardecl.getName(), vardecl);
			}			
			Tab.insert(Obj.Var, vardecl.getName(), a);
			
		}else{
			if(currentMethod==null){
				report_info("Deklarisana globalna promenljiva "+ vardecl.getName(), vardecl);
				globvarDeclCount++;
			}else{
				report_info("Deklarisana lokalna promenljiva "+ vardecl.getName(), vardecl);
				locvarDeclCount++;
			}
			Tab.insert(Obj.Var, vardecl.getName(), currentType);
		}
		
				
	}
	
	

	
	public void visit(ConstListElemDecl constlistelem){	
		Obj o=Tab.find(constlistelem.getName());
		if(o!=Tab.noObj){
			report_error("Greska: Ime " + constlistelem.getName() + " vec postoji!", constlistelem);
			return;		
		}
		//provera dal se dobra vrednpst dodeljuje konstanti
		if(constlistelem.getInit().obj.getType().assignableTo(currentType)){
		constDeclCount++;
		report_info("Deklarisana konstanta "+ constlistelem.getName(), constlistelem);
		o=Tab.insert(Obj.Con, constlistelem.getName(), currentType);
		o.setAdr(constlistelem.getInit().obj.getAdr());
		}else{
			report_error("Greska: pogresan tip vrednosti za konstantu!", constlistelem);
			return;
		}
	}
	
	public void visit(FormalParamDecl form){
		
		if(Tab.currentScope.findSymbol(form.getParam())==null){
			if(form.getArray() instanceof ArrDecl){
				Struct a = new Struct(Struct.Array, currentType);			
				Tab.insert(Obj.Var,form.getParam(), a);		
			}
			report_info("Deklarisan parametar funkcije "+ form.getParam(), form);
			Tab.insert(Obj.Var, form.getParam(), currentType);
		}else {
			report_error("Greska: Ime " + form.getParam() + " vec postoji!", form);
			return;
		}
		paramCount++;
		currentMethod.setLevel(paramCount);
	}
	

	
	
	 public void visit(ProgramName programname){
		 
		 Tab.insert(Obj.Type, "bool", new Struct(Struct.Bool));
		 //Tab.insert(Obj.Type, "String", new Struct(Struct.Array, Tab.charType));
		 Tab.insert(Obj.Type, "void", Tab.noType);
		 //Tab.insert(Obj.Type, "int", Tab.intType);
		 //Tab.insert(Obj.Type, "char", Tab.charType);
		 this.hasRet=false;

		 programname.obj=Tab.insert(Obj.Prog, programname.getProgramname(), Tab.noType); //koa objktni cvr postavljamo simbol koji smo upravo ubacili
		 Tab.openScope();
		 
		 
	    	
	}
	 
	 public void visit(Program program){
		 nVars = Tab.currentScope.getnVars();
		 Tab.chainLocalSymbols(program.getProgramName().obj);
		 Tab.closeScope();
		 
	 }
	 
	public void visit(MethodDeclarations mtddecl) {  
		if (!this.hasRet && currentMethod.getType()!=Tab.noType) {
			report_error("Semanticka greska na liniji " + mtddecl.getLine() + ": funkcija " + currentMethod.getName()
					+ " nema return iskaz!", null);
		}
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		this.hasRet=false;
		currentMethod = null;
	}
	 
	 public void visit(NumConst num){
		 num.struct=Tab.intType;
	 }
	 
	 public void visit(CharConst c){
		 c.struct=Tab.charType;
	 }
	 public void visit(BoolConst b){
		 Struct a=new Struct(Struct.Bool);
		 
		 b.struct=Tab.find("bool").getType();
	 }

	 
	public void visit(Desig designator) {
		
		Obj obj = Tab.find(designator.getDname());
		if (obj == Tab.noObj) {
			report_error("Greska na liniji " + designator.getLine() + " : ime " + designator.getDname()
					+ " nije deklarisano! ", null);
			return;
		}
		designator.obj = obj;
		if (Tab.currentScope.findSymbol(designator.getDname()) != null) {
			report_info("Koriscenje lokalnog elementa " + designator.getDname(), designator);
		} else {
			if(obj.getKind()==Obj.Con){
				report_info("Koriscenje konstante " + designator.getDname(), designator);
			}else{
				report_info("Koriscenje globalnog elementa " + designator.getDname(), designator);
			}			
		}

	}
	 
	 public void visit (DesigArray designator){
		 
	    	//Obj obj = Tab.find(designator.getArr().obj.getName());
		 Obj obj=designator.getArr().obj;
	    	if(obj == Tab.noObj){
				report_error("Greska na liniji " + designator.getLine()+ " : ime "+obj.getName()+" nije deklarisano! ", designator);
				return;
	    	}
	    	//provera da li je niz
	    	designator.obj = obj;
	    	
	    	if(obj.getType().getKind()!=Struct.Array){
	    		report_error("Greska na liniji " + designator.getLine()+ " : "+obj.getName()+" nije NIZ! ", designator);
	    		designator.obj=Tab.noObj; 
	    		return;
	    	}
	    	
	    	designator.obj =new Obj(Obj.Elem, obj.getName(), obj.getType().getElemType()); 		    	
		    	report_info("Koriscenje elementa niza "+ designator.obj.getName(), designator);
	    	    	
	    
	    }
	 
	 public void visit(ArrIdentDecl a){
		 a.obj=Tab.find(a.getName());
			 
	} 

	 
	 public void visit(Type type){
		  
		 //proverimo da li se radi o tipu
		 
		 Obj typeNode= Tab.find(type.getTypeName());
		if (typeNode == Tab.noObj) {
			report_error("Greska: Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", null);
			type.struct = Tab.noType;
			 
		 }else{
			 //ako nam je tabela vratila cvor moamo daa proverimo koji je to tip tacno
			 if(Obj.Type == typeNode.getKind()){
				 type.struct= typeNode.getType();
				 currentType = typeNode.getType();
			 }else{
				 report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
	    		 type.struct = Tab.noType;
			 }
			 
		 }
		 
	 }
	 
	 //za faktor
	 
	 public void visit(NumFactor f){
		 f.struct=f.getNumConst().struct;
	 }
	 
	 public void visit(CharFactor f){
		 f.struct=f.getCharConst().struct;
	 }
	 
	 public void visit(BoolFactor f){
		 f.struct=f.getBoolConst().struct;
	 }
	 
	 public void visit(NewDeclFactor f){
		 
		 if(f.getExpr().struct==Tab.intType ){
			 f.struct= new Struct(Struct.Array, f.getType().struct); 
			 
		 }else{
			 report_error("Greska na liniji " + f.getLine() + " : " + "tip u zagradi mora biti int! ", null);
		 }
		 
	 }
	 
	 public void visit(DesigFactor d){
		 d.struct=d.getDesignator().obj.getType();
	 }
	 
	 public void visit(ExprFactor e){
		 e.struct=e.getExpr().struct;
	 }
	 
	 //za term
	 
	 public void visit(MulopListDecl m){
		 m.struct=m.getFactor().struct;
	 }
	 
	 public void visit(TermDecl t){
		 //provera dal su int
		 if(t.getMulopList().struct!=Tab.intType && t.getMulopList() instanceof MulopListDecl){
			 report_error("Greska na liniji " + t.getLine() + " : " + "neodgovarajuci tip: expected int! ", null);
			 return;
		 }
		 t.struct=t.getFactor().struct;
		 
		 
	 }
	 
	 //za expr
	 public void visit(AddopListDecl addop){
		 addop.struct=addop.getTerm().struct;
		 
	 }
	 
	 public void visit(NegExprElemDecl e){
		 
		 if(e.getTerm().struct!=Tab.intType ){
			 report_error("Greska na liniji " + e.getLine() + " : " + "neodgovarajuci tip: expected int! ", null);
		 }
		 if(e.getAddopList().struct!=Tab.intType && e.getAddopList() instanceof AddopListDecl ){
			 report_error("Greska na liniji " + e.getLine() + " : " + "neodgovarajuci tip: expected int! ", null);
			 return;
		 }
		 e.struct=e.getTerm().struct;
		
		 
	 }
	 
	 public void visit(ExprElemDecl e){
		 if(e.getAddopList().struct!=Tab.intType && e.getAddopList() instanceof AddopListDecl){
			 report_error("Greska na liniji " + e.getLine() + " : " + "neodgovarajuci tipovi: expected int! ", null);
			 return;
		 }
		 e.struct=e.getTerm().struct;
		 
	 }
	 
	 public void visit(ExprDeclaration ex){
		 ex.struct=ex.getExprElem().struct;
	 }

	 
	 public void visit(BinExpr bin){

		 if(bin.getExprElem1().struct!=Tab.intType || bin.getExprElem().struct!=Tab.intType){
			 report_error("Greska na liniji " + bin.getLine() + " : " + "neodgovarajuci tipovi: expected int! ", null);
			 return;
		 }
		 bin.struct=Tab.intType;
	 }
	 
	 
	//za statemente
	 public void visit(EqualStatementDecl eq){
		 eq.struct=eq.getExpr().struct;
	 }
	 
	public void visit(EqualStmt stm) {

		if (!stm.getDesignator().obj.getType().compatibleWith(stm.getEqualStatement().struct)) {
			report_error("GRESKA: Tipovi se ne poklapaju", stm);
			return;
		}

	}
	
	public void visit(RetExStmt ret){
		//moze i provera dal je void tipa fja greska
		if (!currentMethod.getType().compatibleWith(ret.getExpr().struct)) {
            report_error("Greska: tip iskaza i tip f-je se ne poklapaju", ret);
        }
		this.hasRet=true;
	}
	 
	 public void visit(IncStmt inc){
		 if(inc.getDesignator().obj.getType()!=Tab.intType)
			 report_error("Greska na liniji " + inc.getLine() + " : " + "neodgovarajuci tip u inkrementiranju: expected int! ", null);
	 }
	 
	 public void visit(DecStmt dec){
		 if(dec.getDesignator().obj.getType()!=Tab.intType)
			 report_error("Greska na liniji " + dec.getLine() + " : " + "neodgovarajuci tip u dekrementiranju: expected int! ", null);
	 }
	 
	 //za init
	 public void visit(InitNum init){
		init.obj = new Obj(Obj.Con, "", Tab.intType, init.getNumConst().getN1(), 0);
	 }
	 
	 public void visit(InitChar init){
		 init.obj = new Obj(Obj.Con, "", Tab.charType, init.getCharConst().getC1(), 0);
	 }
	 
	 public void visit(InitBool init){
		// init.struct=init.getBoolConst().struct;
		 init.obj = new Obj(Obj.Con, "",init.getBoolConst().struct , init.getBoolConst().getB1()? 1 : 0, 0);
	 }

}
