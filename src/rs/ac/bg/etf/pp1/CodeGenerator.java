package rs.ac.bg.etf.pp1;

import java.util.ArrayList;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.Add;
import rs.ac.bg.etf.pp1.ast.ArrDesignator;
import rs.ac.bg.etf.pp1.ast.DesignatorAssign;
import rs.ac.bg.etf.pp1.ast.DesignatorCall;
import rs.ac.bg.etf.pp1.ast.DesignatorDec;
import rs.ac.bg.etf.pp1.ast.DesignatorFactor;
import rs.ac.bg.etf.pp1.ast.DesignatorInc;
import rs.ac.bg.etf.pp1.ast.Div;
import rs.ac.bg.etf.pp1.ast.LiteralFactor;
import rs.ac.bg.etf.pp1.ast.MethodFactor;
import rs.ac.bg.etf.pp1.ast.MethodTypeName;
import rs.ac.bg.etf.pp1.ast.MethodVoidName;
import rs.ac.bg.etf.pp1.ast.MoreComma;
import rs.ac.bg.etf.pp1.ast.MoreDesignator;
import rs.ac.bg.etf.pp1.ast.Mul;
import rs.ac.bg.etf.pp1.ast.MultDesignator;
import rs.ac.bg.etf.pp1.ast.MultipleFactor;
import rs.ac.bg.etf.pp1.ast.MultipleTerm;
import rs.ac.bg.etf.pp1.ast.NegativeTerm;
import rs.ac.bg.etf.pp1.ast.NewArrFactor;
import rs.ac.bg.etf.pp1.ast.NoDesignator;
import rs.ac.bg.etf.pp1.ast.PrintStatement;
import rs.ac.bg.etf.pp1.ast.PrintStatement2;
import rs.ac.bg.etf.pp1.ast.ReadStatement;
import rs.ac.bg.etf.pp1.ast.ReturnExpr;
import rs.ac.bg.etf.pp1.ast.ReturnNoExpr;
import rs.ac.bg.etf.pp1.ast.SingleDesignator;
import rs.ac.bg.etf.pp1.ast.SingleIdent;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.TypeMethodNoPars;
import rs.ac.bg.etf.pp1.ast.TypeMethodPars;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.ac.bg.etf.pp1.ast.VoidMethodNoPars;
import rs.ac.bg.etf.pp1.ast.VoidMethodPars;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor
{
	private int mainPc;
	private ArrayList<Obj> multiDesignatorsList = new ArrayList<>();
	
	public int getMainPc()
	{
		return mainPc;
	}
	
	/*-----------------------------------------------------Program-----------------------------------------------------------*/
	/*-----------------------------------------------------ConstDecl---------------------------------------------------------*/
	/*-----------------------------------------------------VarDecl-----------------------------------------------------------*/
	/*-----------------------------------------------------MethodDecl--------------------------------------------------------*/
	@Override
	public void visit(VoidMethodPars VoidMethodPars)
	{
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(VoidMethodNoPars VoidMethodNoPars)
	{
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(TypeMethodPars TypeMethodPars)
	{
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(TypeMethodNoPars TypeMethodNoPars)
	{
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(MethodTypeName MethodTypeName)
	{
		MethodTypeName.obj.setAdr(Code.pc);
		
		// Collect arguments and local variables.
		SyntaxNode methodNode = MethodTypeName.getParent();
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);
		
		// Generate the entry.
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(varCnt.getCount() + fpCnt.getCount());
	}
	
	@Override
	public void visit(MethodVoidName MethodVoidName)
	{
		if ("main".equals(MethodVoidName.getName()))
			mainPc = Code.pc;
		MethodVoidName.obj.setAdr(Code.pc);
		
		// Collect arguments and local variables.
		SyntaxNode methodNode = MethodVoidName.getParent();
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);
		
		// Generate the entry.
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(varCnt.getCount() + fpCnt.getCount());
	}
	/*-----------------------------------------------------FormPars----------------------------------------------------------*/
	/*-----------------------------------------------------Type--------------------------------------------------------------*/
	/*-----------------------------------------------------Statement---------------------------------------------------------*/
	@Override
	public void visit(ReturnExpr ReturnExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(ReturnNoExpr ReturnNoExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(ReadStatement ReadStatement)
	{
		if (ReadStatement.getDesignator().obj.getType() == Tab.charType)
			Code.put(Code.bread);
		else
			Code.put(Code.read);
		Code.store(ReadStatement.getDesignator().obj);
	}
	
	@Override
	public void visit(PrintStatement PrintStatement)
	{
		if (PrintStatement.getExpr().struct == Tab.intType)
		{			
			Code.loadConst(5);
			Code.put(Code.print);
		}
		else if (PrintStatement.getExpr().struct == Tab.charType)
		{
			Code.loadConst(2);
			Code.put(Code.bprint);
		}
		else
		{
			Code.loadConst(2);
			Code.put(Code.print);
		}
	}
	
	@Override
	public void visit(PrintStatement2 PrintStatement2)
	{
		Code.loadConst(PrintStatement2.getNum());
		if (PrintStatement2.getExpr().struct == Tab.intType)
			Code.put(Code.print);
		else if (PrintStatement2.getExpr().struct == Tab.charType)
			Code.put(Code.bprint);
		else
			Code.put(Code.print);
	}
	/*-----------------------------------------------------DesignatorStatement-----------------------------------------------*/
	@Override
	public void visit(DesignatorAssign DesignatorAssign)
	{
		Code.store(DesignatorAssign.getDesignator().obj);
	}
	
	@Override
	public void visit(DesignatorCall DesignatorCall)
	{
		Obj functionObj = DesignatorCall.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc; 
		Code.put(Code.call);
		Code.put2(offset);
		if (DesignatorCall.getDesignator().obj.getType() != Tab.noType)
			Code.put(Code.pop);
	}
	
	@Override
	public void visit(DesignatorInc DesignatorInc)
	{
		if (DesignatorInc.getDesignator().obj.getKind() == Obj.Elem)
			Code.put(Code.dup2);	
		Code.load(DesignatorInc.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(DesignatorInc.getDesignator().obj);
	}
	
	@Override
	public void visit(DesignatorDec DesignatorDec)
	{
		if (DesignatorDec.getDesignator().obj.getKind() == Obj.Elem)
			Code.put(Code.dup2);	
		Code.load(DesignatorDec.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(DesignatorDec.getDesignator().obj);
	}
	
	@Override
	public void visit(MultDesignator MultDesignator)
	{
		Code.load(MultDesignator.getDesignator().obj);
		Code.put(Code.arraylength);
		Code.load(new Obj(Obj.Con, null, Tab.intType, multiDesignatorsList.size(), 0));
		Code.putFalseJump(Code.lt, 0);
		int jmpVal = Code.pc - 2;
		Code.put(Code.trap);
		Code.loadConst(1);
		Code.fixup(jmpVal);
		for (int j = multiDesignatorsList.size() - 1; j >= 0; j--)
		{
			if (multiDesignatorsList.get(j).getType() != Tab.noType)
			{
				Code.load(MultDesignator.getDesignator().obj);
				Code.loadConst(j);
				if (multiDesignatorsList.get(j).getType() != Tab.charType)
					Code.put(Code.aload);
				else
					Code.put(Code.baload);
				Code.store(multiDesignatorsList.get(j));
			}
		}
		multiDesignatorsList.clear();
	}
	
	@Override
	public void visit(MoreDesignator MoreDesignator)
	{
		multiDesignatorsList.add(MoreDesignator.getDesignator().obj);
	}
	
	@Override
	public void visit(MoreComma MoreComma)
	{
		multiDesignatorsList.add(Tab.noObj);
	}
	
	@Override
	public void visit(SingleDesignator SingleDesignator)
	{
		multiDesignatorsList.add(SingleDesignator.getDesignator().obj);
	}
	
	@Override
	public void visit(NoDesignator NoDesignator)
	{
		multiDesignatorsList.add(Tab.noObj);
	}
	/*-----------------------------------------------------ActPars-----------------------------------------------------------*/
	/*-----------------------------------------------------Expr--------------------------------------------------------------*/
	@Override
	public void visit(NegativeTerm NegativeTerm)
	{
		Code.put(Code.neg);
	}
	
	@Override
	public void visit(MultipleTerm MultipleTerm)
	{
		if (MultipleTerm.getAddop() instanceof Add)
			Code.put(Code.add);
		else
			Code.put(Code.sub);
	}
	/*-----------------------------------------------------Term--------------------------------------------------------------*/
	@Override
	public void visit(MultipleFactor MultipleFactor)
	{
		if (MultipleFactor.getMulop() instanceof Mul)
			Code.put(Code.mul);
		else if (MultipleFactor.getMulop() instanceof Div)
			Code.put(Code.div);
		else
			Code.put(Code.rem);
	}
	/*-----------------------------------------------------Factor------------------------------------------------------------*/
	@Override
	public void visit(DesignatorFactor DesignatorFactor)
	{
		Code.load(DesignatorFactor.getDesignator().obj);
	}
	
	@Override
	public void visit(MethodFactor MethodFactor)
	{
		Obj functionObj = MethodFactor.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc; 
		Code.put(Code.call);
		Code.put2(offset);
	}
	
	@Override
	public void visit(LiteralFactor LiteralFactor)
	{
		Code.load(LiteralFactor.getLiteral().obj);
	}
	
	@Override
	public void visit(NewArrFactor NewArrFactor)
	{
		Code.put(Code.newarray);
		if (NewArrFactor.struct.getElemType() == Tab.charType)
			Code.put(0);
		else
			Code.put(1);
	}
	/*-----------------------------------------------------Designator--------------------------------------------------------*/
	@Override
	public void visit(SingleIdent SingleIdent)
	{
		SyntaxNode parent = SingleIdent.getParent();
		if (ArrDesignator.class == parent.getClass())
			Code.load(SingleIdent.obj);
	}
}
