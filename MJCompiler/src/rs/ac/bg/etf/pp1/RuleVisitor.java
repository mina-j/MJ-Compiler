package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;

public class RuleVisitor extends VisitorAdaptor{
	
	int readCallCount = 0;
	int varDeclCount = 0;
	int printCallCount = 0;
	int constDeclCount = 0;
	Logger log = Logger.getLogger(getClass());


	
    public void visit(PrintStmt print) {
		printCallCount++;
	}
    
    public void visit(PrintNumStmt print) {
		printCallCount++;
	}
    
	public void visit(Variable vardecl){
		varDeclCount++;
	}
	
	public void visit(ConstListElemDecl constlistelem){
		constDeclCount++;
	}
    
    public void visit(ReadStmt read) {
    	readCallCount++;
	}

}
