package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.MoreVar;
import rs.ac.bg.etf.pp1.ast.MultipleFormPars;
import rs.ac.bg.etf.pp1.ast.SingleFormPars;
import rs.ac.bg.etf.pp1.ast.VarDecl;
import rs.ac.bg.etf.pp1.ast.VarDeclStart;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class CounterVisitor extends VisitorAdaptor
{
	protected int count;
	
	public int getCount()
	{
		return count;
	}
	
	public static class FormParamCounter extends CounterVisitor
	{		
		@Override
		public void visit(MultipleFormPars MultipleFormPars)
		{
			count++;
		}
		
		@Override
		public void visit(SingleFormPars SingleFormPars)
		{
			count++;
		}
	}
	
	public static class VarCounter extends CounterVisitor
	{		
		@Override
		public void visit(VarDeclStart VarDeclStart)
		{
			count++;
		}
		
		@Override
		public void visit(MoreVar MoreVar)
		{
			count++;
		}
	}
}
