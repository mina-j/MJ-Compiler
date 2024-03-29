// generated with ast extension for cup
// version 0.8
// 14/8/2022 8:29:17


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(ExprElem ExprElem);
    public void visit(MethodDecl MethodDecl);
    public void visit(Mulop Mulop);
    public void visit(Arr Arr);
    public void visit(MulopList MulopList);
    public void visit(Var Var);
    public void visit(EqualStatement EqualStatement);
    public void visit(StatementList StatementList);
    public void visit(Addop Addop);
    public void visit(ExprEnd ExprEnd);
    public void visit(Factor Factor);
    public void visit(DeclList DeclList);
    public void visit(Designator Designator);
    public void visit(Term Term);
    public void visit(Statements Statements);
    public void visit(ConstListElem ConstListElem);
    public void visit(VarDeclList VarDeclList);
    public void visit(FormalParamList FormalParamList);
    public void visit(ConstListDecl ConstListDecl);
    public void visit(Expr Expr);
    public void visit(AddopList AddopList);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(Init Init);
    public void visit(Decl Decl);
    public void visit(Statement Statement);
    public void visit(Array Array);
    public void visit(ConstDecl ConstDecl);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(BinDq BinDq);
    public void visit(SingleStatement SingleStatement);
    public void visit(FormPars FormPars);
    public void visit(BoolConst BoolConst);
    public void visit(CharConst CharConst);
    public void visit(NumConst NumConst);
    public void visit(InitBool InitBool);
    public void visit(InitChar InitChar);
    public void visit(InitNum InitNum);
    public void visit(BinOpSign BinOpSign);
    public void visit(Mod Mod);
    public void visit(Div Div);
    public void visit(Mul Mul);
    public void visit(Sub Sub);
    public void visit(Add Add);
    public void visit(DesigFactor DesigFactor);
    public void visit(NewDeclFactor NewDeclFactor);
    public void visit(BoolFactor BoolFactor);
    public void visit(ExprFactor ExprFactor);
    public void visit(CharFactor CharFactor);
    public void visit(NumFactor NumFactor);
    public void visit(NoMulopListDecl NoMulopListDecl);
    public void visit(MulopListDecl MulopListDecl);
    public void visit(TermDecl TermDecl);
    public void visit(NoAddopListDecl NoAddopListDecl);
    public void visit(AddopListDecl AddopListDecl);
    public void visit(ExprElemDecl ExprElemDecl);
    public void visit(NegExprElemDecl NegExprElemDecl);
    public void visit(EndOfBin EndOfBin);
    public void visit(ExprDeclaration ExprDeclaration);
    public void visit(BinExpr BinExpr);
    public void visit(ArrIdentDecl ArrIdentDecl);
    public void visit(Desig Desig);
    public void visit(DesigArray DesigArray);
    public void visit(ErrorDesignator ErrorDesignator);
    public void visit(EqualStatementDecl EqualStatementDecl);
    public void visit(DesignatorError DesignatorError);
    public void visit(DecStmt DecStmt);
    public void visit(IncStmt IncStmt);
    public void visit(EqualStmt EqualStmt);
    public void visit(NoRet NoRet);
    public void visit(RetExStmt RetExStmt);
    public void visit(PrintStmt PrintStmt);
    public void visit(PrintNumStmt PrintNumStmt);
    public void visit(ReadStmt ReadStmt);
    public void visit(DesignatorStmt DesignatorStmt);
    public void visit(MultipleStmtDecl MultipleStmtDecl);
    public void visit(SingleStmt SingleStmt);
    public void visit(LabeledSingleStmt LabeledSingleStmt);
    public void visit(NoStmt NoStmt);
    public void visit(StmtsList StmtsList);
    public void visit(Stmts Stmts);
    public void visit(Label Label);
    public void visit(FormalParamDecl FormalParamDecl);
    public void visit(SingleFormalParamDecl SingleFormalParamDecl);
    public void visit(FormalParamDecls FormalParamDecls);
    public void visit(NoFormParam NoFormParam);
    public void visit(FormParams FormParams);
    public void visit(ConstListElemDecl ConstListElemDecl);
    public void visit(OneConstList OneConstList);
    public void visit(ConstList ConstList);
    public void visit(ConstDeclarations ConstDeclarations);
    public void visit(MtdTypeName MtdTypeName);
    public void visit(MethodDeclarations MethodDeclarations);
    public void visit(NoMethodDecl NoMethodDecl);
    public void visit(MethodListDeclarations MethodListDeclarations);
    public void visit(Type Type);
    public void visit(NoArr NoArr);
    public void visit(ArrDecl ArrDecl);
    public void visit(ErrorVar ErrorVar);
    public void visit(Variable Variable);
    public void visit(OneVarList OneVarList);
    public void visit(VarDeclarations VarDeclarations);
    public void visit(VarDecl VarDecl);
    public void visit(ConstElem ConstElem);
    public void visit(VarElem VarElem);
    public void visit(NoDecList NoDecList);
    public void visit(DeclarationList DeclarationList);
    public void visit(ProgramName ProgramName);
    public void visit(Program Program);

}
