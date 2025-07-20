package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor
{
	Logger log = Logger.getLogger(getClass());
	
	Obj currentMethod = null;
	Struct currDeclType = null;
	int currLevel = -1;
	boolean returnFound = false;
	boolean errorDetected = false;
	boolean voidMainPresent = false;
	int nVars;
	
	ArrayList<Designator> multDesignators = new ArrayList<>();
	Collection<Obj> currMethodPars = null;
	ArrayList<Struct> currMethodArgs = new ArrayList<>();
	
	public void report_error(String message, SyntaxNode info)
	{
		errorDetected = true;
		StringBuilder msg = new StringBuilder("Greska");
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		msg.append(": ").append(message);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info)
	{
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public void report_obj(Obj o, int line)
	{
		StringBuilder msg = new StringBuilder("Pretraga na liniji " + line);
		msg.append("(").append(o.getName()).append("), detektovan ");
		switch (o.getKind())
		{
			case Obj.Con:
				msg.append("Con ");
				break;
			case Obj.Meth:
				msg.append("Meth ");
				break;
			case Obj.Var:
				msg.append("Var ");
				break;
			default:
				msg.append(o.getKind() + " ");	
		}
		
		msg.append(o.getName()).append(": ");
		
		switch (o.getType().getKind())
		{
			case Struct.Array:
			{
				msg.append("Arr of ");
				switch (o.getType().getElemType().getKind())
				{
				case Struct.Bool:
					msg.append("bool, ");
					break;
				case Struct.Char:
					msg.append("char, ");
					break;
				case Struct.Int:
					msg.append("int, ");
					break;
				default:
					msg.append(o.getType().getElemType().getKind() + ", ");
				}
				break;
			}
			case Struct.Bool:
				msg.append("bool, ");
				break;
			case Struct.Char:
				msg.append("char, ");
				break;
			case Struct.Int:
				msg.append("int, ");
				break;
			default:
				msg.append(o.getType().getKind() + ", ");
		}
		msg.append(o.getAdr()).append(", ").append(o.getLevel());
		
		if (o.getKind() == Obj.Meth)
		{
			msg.append(" (");
			for (Obj local : o.getLocalSymbols())
			{
				switch (local.getKind())
				{
					case Obj.Con:
						msg.append("Con ");
						break;
					case Obj.Meth:
						msg.append("Meth ");
						break;
					case Obj.Var:
						msg.append("Var ");
						break;
					default:
						msg.append(local.getKind() + " ");	
				}
				
				msg.append(local.getName()).append(": ");
				
				switch (local.getType().getKind())
				{
					case Struct.Array:
					{
						msg.append("Arr of ");
						switch (local.getType().getElemType().getKind())
						{
						case Struct.Bool:
							msg.append("bool, ");
							break;
						case Struct.Char:
							msg.append("char, ");
							break;
						case Struct.Int:
							msg.append("int, ");
							break;
						default:
							msg.append(local.getType().getElemType().getKind() + ", ");
						}
						break;
					}
					case Struct.Bool:
						msg.append("bool, ");
						break;
					case Struct.Char:
						msg.append("char, ");
						break;
					case Struct.Int:
						msg.append("int, ");
						break;
					default:
						msg.append(local.getType().getKind() + ", ");
				}
				msg.append(local.getAdr()).append(", ").append(local.getLevel()).append(";");
			}
			msg.append(")");
		}
		log.info(msg.toString());
	}
	
	/*-----------------------------------------------------Program-----------------------------------------------------------*/
	@Override
	public void visit(ProgramMethods ProgramMethods)
	{
		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(ProgramMethods.getProgName().obj);
		Tab.closeScope();
		currLevel--;
		if (!voidMainPresent)
			report_error("program " + ProgramMethods.getProgName().getName() + " ne sadrzi void main() metodu.", ProgramMethods);
	}
	
	@Override
	public void visit(ProgramNoMethods ProgramNoMethods)
	{
		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(ProgramNoMethods.getProgName().obj);
		Tab.closeScope();
		currLevel--;
		report_error("program " + ProgramNoMethods.getProgName().getName() + " ne sadrzi void main() metodu.", ProgramNoMethods);
	}
	
	@Override
	public void visit(ProgName ProgName)
	{
		ProgName.obj = Tab.insert(Obj.Prog, ProgName.getName(), Tab.noType);
		Tab.openScope();
		currLevel++;
	}
	/*-----------------------------------------------------ConstDecl---------------------------------------------------------*/
	@Override
	public void visit(ConstDecl ConstDecl)
	{
		Obj q = Tab.find(ConstDecl.getName());
		if (q == Tab.noObj || q.getLevel() != currLevel)
		{
			q = Tab.insert(Obj.Con, ConstDecl.getName(), currDeclType);
			q.setAdr(ConstDecl.getLiteral().obj.getAdr());
		}
		else
			report_error("ime " + ConstDecl.getName() + " je vec deklarisano.", ConstDecl);
		if (!ConstDecl.getLiteral().obj.getType().equals(currDeclType))
			report_error("nekompatibilan tip konstante.", ConstDecl);
	}
	
	@Override
	public void visit(DeclType DeclType)
	{
		currDeclType = DeclType.getType().struct;
	}
	
	@Override
	public void visit(IntLiteral IntLiteral)
	{
		IntLiteral.obj = new Obj(Obj.Con, "#", Tab.intType, IntLiteral.getNum(), currLevel);
	}
	
	@Override
	public void visit(CharLiteral CharLiteral)
	{
		CharLiteral.obj = new Obj(Obj.Con, "#", Tab.charType, CharLiteral.getCh(), currLevel);
	}
	
	@Override
	public void visit(BoolLiteral BoolLiteral)
	{
		BoolLiteral.obj = new Obj(Obj.Con, "#", Tab.boolType, BoolLiteral.getOk()?1:0, currLevel);
	}
	
	@Override
	public void visit(MoreConst MoreConst)
	{
		Obj q = Tab.find(MoreConst.getName());
		if (q == Tab.noObj || q.getLevel() != currLevel)
		{
			q = Tab.insert(Obj.Con, MoreConst.getName(), currDeclType);
			q.setAdr(MoreConst.getLiteral().obj.getAdr());
		}
		else
			report_error("ime " + MoreConst.getName() + " je vec deklarisano.", MoreConst);
		if (!MoreConst.getLiteral().obj.getType().equals(currDeclType))
			report_error("nekompatibilan tip konstante.", MoreConst);
	}
	/*-----------------------------------------------------VarDecl-----------------------------------------------------------*/
	@Override
	public void visit(Arr Arr)
	{
		Obj q = Tab.find(Arr.getName());
		if (q == Tab.noObj || q.getLevel() != currLevel)
			Tab.insert(Obj.Var, Arr.getName(), new Struct(Struct.Array, currDeclType));
		else
			report_error("ime " + Arr.getName() + " je vec deklarisano.", Arr);
	}
	
	@Override
	public void visit(Var Var)
	{
		Obj q = Tab.find(Var.getName());
		if (q == Tab.noObj || q.getLevel() != currLevel)
			Tab.insert(Obj.Var, Var.getName(), currDeclType);
		else
			report_error("ime " + Var.getName() + " je vec deklarisano.", Var);
	}
	/*-----------------------------------------------------MethodDecl--------------------------------------------------------*/
	@Override
	public void visit(VoidMethodPars VoidMethodPars)
	{
		if (currentMethod != null)
		{
			Tab.chainLocalSymbols(currentMethod);
			Tab.closeScope();
			currLevel--;
		}
		returnFound = false;
		currentMethod = null;
	}
	
	@Override
	public void visit(VoidMethodNoPars VoidMethodNoPars)
	{
		if (currentMethod != null)
		{
			Tab.chainLocalSymbols(currentMethod);
			Tab.closeScope();
			currLevel--;
			if ("main".equals(VoidMethodNoPars.getMethodVoidName().getName()))
				voidMainPresent = true;
		}
		returnFound = false;
		currentMethod = null;
	}
	
	@Override
	public void visit(TypeMethodPars TypeMethodPars)
	{
		if (currentMethod != null)
		{
			if (!returnFound)
				report_error("funkcija " + currentMethod.getName() + " nema return iskaz.", TypeMethodPars);
			Tab.chainLocalSymbols(currentMethod);
			Tab.closeScope();
			currLevel--;
		}
		returnFound = false;
		currentMethod = null;
	}
	
	@Override
	public void visit(TypeMethodNoPars TypeMethodNoPars)
	{
		if (currentMethod != null)
		{
			if (!returnFound)
				report_error("funkcija " + currentMethod.getName() + " nema return iskaz.", TypeMethodNoPars);
			Tab.chainLocalSymbols(currentMethod);
			Tab.closeScope();
			currLevel--;
		}
		returnFound = false;
		currentMethod = null;
	}
	
	@Override
	public void visit(MethodTypeName MethodTypeName)
	{
		Obj q = Tab.find(MethodTypeName.getName());
		if (q == Tab.noObj)
		{
			currentMethod = Tab.insert(Obj.Meth, MethodTypeName.getName(), MethodTypeName.getType().struct);
			Tab.openScope();
			currLevel++;
		}
		else
			report_error("ime " + MethodTypeName.getName() + " je vec deklarisano.", MethodTypeName);
		MethodTypeName.obj = currentMethod;
	}
	
	@Override
	public void visit(MethodVoidName MethodVoidName)
	{
		Obj q = Tab.find(MethodVoidName.getName());
		if (q == Tab.noObj)
		{
			currentMethod = Tab.insert(Obj.Meth, MethodVoidName.getName(), Tab.noType);
			Tab.openScope();
			currLevel++;
		}
		else
			report_error("ime " + MethodVoidName.getName() + " je vec deklarisano.", MethodVoidName);
		MethodVoidName.obj = currentMethod;
	}
	/*-----------------------------------------------------FormPars----------------------------------------------------------*/
	@Override
	public void visit(MultipleFormPars MultipleFormPars)
	{
		if (currentMethod != null)
			currentMethod.setFpPos(currentMethod.getFpPos() + 1);
	}
	
	@Override
	public void visit(SingleFormPars SingleFormPars)
	{
		if (currentMethod != null)
			currentMethod.setFpPos(currentMethod.getFpPos() + 1);
	}
	/*-----------------------------------------------------Type--------------------------------------------------------------*/
	@Override
	public void visit(Type Type)
	{
		Obj typeNode = Tab.find(Type.getName());
		if (typeNode == Tab.noObj)
		{
			report_error("nije pronadjen tip " + Type.getName() + " u tabeli simbola.", Type);
			Type.struct = Tab.noType;
		}
		else
		{
			if (Obj.Type == typeNode.getKind())
				Type.struct = typeNode.getType();
			else
			{
				report_error("ime " + Type.getName() + " ne predstavlja tip.", Type);
				Type.struct = Tab.noType;
			}
		}
	}
	/*-----------------------------------------------------Statement---------------------------------------------------------*/
	@Override
	public void visit(ReturnExpr ReturnExpr)
	{
		returnFound = true;
		if (currentMethod != null)
		{
			Struct currMethType = currentMethod.getType();
			if (!currMethType.compatibleWith(ReturnExpr.getExpr().struct))
				report_error("nekompatibilan tip iskaza u return naredbi funkcije " + currentMethod.getName() + ".", ReturnExpr);
		}
	}
	
	@Override
	public void visit(ReadStatement ReadStatement)
	{
		if ((ReadStatement.getDesignator().obj.getKind() != Obj.Var &&
			ReadStatement.getDesignator().obj.getKind() != Obj.Elem) ||
			(ReadStatement.getDesignator().obj.getType() != Tab.intType &&
			ReadStatement.getDesignator().obj.getType() != Tab.charType &&
			ReadStatement.getDesignator().obj.getType() != Tab.boolType))
			report_error("argument mora biti promjenljiva ili element niza tipa int, char ili bool.", ReadStatement);
	}
	
	@Override
	public void visit(PrintStatement PrintStatement)
	{
		if (PrintStatement.getExpr().struct != Tab.intType &&
			PrintStatement.getExpr().struct != Tab.charType &&
			PrintStatement.getExpr().struct != Tab.boolType)
			report_error("argument mora biti tipa int, char ili bool.", PrintStatement);
	}
	
	@Override
	public void visit(PrintStatement2 PrintStatement2)
	{
		if (PrintStatement2.getExpr().struct != Tab.intType &&
			PrintStatement2.getExpr().struct != Tab.charType &&
			PrintStatement2.getExpr().struct != Tab.boolType)
			report_error("prvi argument mora biti tipa int, char ili bool.", PrintStatement2);
	}
	
	@Override
	public void visit(ForeachStatement ForeachStatement)
	{
		Obj ident = Tab.find(ForeachStatement.getName());
		if (ident == Tab.noObj)
			report_error("ime " + ident + " nije deklarisano.", ForeachStatement);
		else
			report_obj(ident, ForeachStatement.getLine());
	}
	/*-----------------------------------------------------DesignatorStatement-----------------------------------------------*/
	@Override
	public void visit(DesignatorAssign DesignatorAssign)
	{
		if (DesignatorAssign.getDesignator().obj.getKind() == Obj.Var ||
			DesignatorAssign.getDesignator().obj.getKind() == Obj.Elem)
		{
			if (!DesignatorAssign.getExpr().struct.assignableTo(DesignatorAssign.getDesignator().obj.getType()))
				report_error("nekompatibilni tipovi u dodjeli vrijednosti.", DesignatorAssign);
		}
		else
			report_error("lijeva strana izraza za dodjelu nije promjenljiva niti element niza.", DesignatorAssign);
	}
	
	@Override
	public void visit(DesignatorCall DesignatorCall)
	{
		if (DesignatorCall.getDesignator().obj.getKind() != Obj.Meth)
			report_error("ime " + DesignatorCall.getDesignator().obj.getName() + " nije funkcija.", DesignatorCall);
		else
		{
			if (currMethodArgs.size() != DesignatorCall.getDesignator().obj.getFpPos())
				report_error("broj argumenata ne odgovara broju parametara funkcije " + DesignatorCall.getDesignator().obj.getName() + ".", DesignatorCall);
			else
			{
				if (!"len".equals(DesignatorCall.getDesignator().obj.getName()))
				{
					int i = 0;
					for (Obj par : currMethodPars)
					{
						if (i == DesignatorCall.getDesignator().obj.getFpPos())
							break;
						if (!par.getType().equals(currMethodArgs.get(i)))
							report_error("argument broj " + (i + 1) + " nije odgovarajuceg tipa.", DesignatorCall);
						++i;
					}
				}
				else if (currMethodArgs.get(0).getKind() != Struct.Array)
					report_error("argument broj " + 1 + " nije odgovarajuceg tipa.", DesignatorCall);
			}
			currMethodArgs.clear();
			currMethodPars = null;
		}
	}
	
	@Override
	public void visit(DesignatorInc DesignatorInc)
	{
		if ((DesignatorInc.getDesignator().obj.getKind() != Obj.Var &&
			DesignatorInc.getDesignator().obj.getKind() != Obj.Elem) ||
			DesignatorInc.getDesignator().obj.getType() != Tab.intType)
			report_error("operand mora biti promjenljiva ili element niza tipa int.", DesignatorInc);
	}
	
	@Override
	public void visit(DesignatorDec DesignatorDec)
	{
		if ((DesignatorDec.getDesignator().obj.getKind() != Obj.Var &&
			DesignatorDec.getDesignator().obj.getKind() != Obj.Elem) ||
			DesignatorDec.getDesignator().obj.getType() != Tab.intType)
			report_error("operand mora biti promjenljiva ili element niza tipa int.", DesignatorDec);
	}
	
	@Override
	public void visit(MultDesignator MultDesignator)
	{
		if (MultDesignator.getDesignator().obj.getType().getKind() != Struct.Array)
			report_error("desna strana izraza ne predstavlja niz.", MultDesignator);
		else
		{
			boolean ok = true;
			for (Designator d : multDesignators)
			{
				if (!MultDesignator.getDesignator().obj.getType().getElemType().assignableTo(d.obj.getType()))
				{
					ok = false;
					break;
				}
			}
			if (!ok)
				report_error("tip elementa niza nije kompatibilan pri dodjeli sa tipom svih operanada.", MultDesignator);
		}
		multDesignators.clear();
	}
	
	@Override
	public void visit(MoreDesignator MoreDesignator)
	{
		if (MoreDesignator.getDesignator().obj.getKind() != Obj.Var &&
			MoreDesignator.getDesignator().obj.getKind() != Obj.Elem)
			report_error("operandi sa lijeve strane moraju biti promjenljive ili elementi niza.", MoreDesignator);
		else
			multDesignators.add(MoreDesignator.getDesignator());
	}
	
	@Override
	public void visit(SingleDesignator SingleDesignator)
	{
		if (SingleDesignator.getDesignator().obj.getKind() != Obj.Var &&
			SingleDesignator.getDesignator().obj.getKind() != Obj.Elem)
			report_error("operandi sa lijeve strane moraju biti promjenljive ili elementi niza.", SingleDesignator);
		else
			multDesignators.add(SingleDesignator.getDesignator());
	}
	/*-----------------------------------------------------ActPars-----------------------------------------------------------*/
	@Override
	public void visit(MultipleActPars MultipleActPars)
	{
		currMethodArgs.add(MultipleActPars.getExpr().struct);
	}
	
	@Override
	public void visit(SingleActPars SingleActPars)
	{
		currMethodArgs.add(SingleActPars.getExpr().struct);
	}
	/*-----------------------------------------------------Expr--------------------------------------------------------------*/
	@Override
	public void visit(NegativeTerm NegativeTerm)
	{
		if (NegativeTerm.getTerm().struct == Tab.intType)
			NegativeTerm.struct = NegativeTerm.getTerm().struct;
		else
		{
			NegativeTerm.struct = Tab.noType;
			report_error("operand nije tipa int.", NegativeTerm);
		}
	}
	
	@Override
	public void visit(MultipleTerm MultipleTerm)
	{
		Struct te = MultipleTerm.getExpr().struct;
		Struct t = MultipleTerm.getTerm().struct;
		if (te.equals(t) && te == Tab.intType)
			MultipleTerm.struct = te;
		else
		{
			report_error("nekompatibilni tipovi u izrazu za sabiranje.", MultipleTerm);
			MultipleTerm.struct = Tab.noType;
		}
	}
	
	@Override
	public void visit(SingleTerm SingleTerm)
	{
		SingleTerm.struct = SingleTerm.getTerm().struct;
	}
	/*-----------------------------------------------------Term--------------------------------------------------------------*/
	@Override
	public void visit(MultipleFactor MultipleFactor)
	{
		Struct te = MultipleFactor.getTerm().struct;
		Struct t = MultipleFactor.getFactor().struct;
		if (te.equals(t) && te == Tab.intType)
			MultipleFactor.struct = te;
		else
		{
			report_error("nekompatibilni tipovi u izrazu za mnozenje.", MultipleFactor);
			MultipleFactor.struct = Tab.noType;
		}
	}
	
	@Override
	public void visit(SingleFactor SingleFactor)
	{
		SingleFactor.struct = SingleFactor.getFactor().struct;
	}
	/*-----------------------------------------------------Factor------------------------------------------------------------*/
	@Override
	public void visit(DesignatorFactor DesignatorFactor)
	{
		DesignatorFactor.struct = DesignatorFactor.getDesignator().obj.getType();
	}
	
	@Override
	public void visit(MethodFactor MethodFactor)
	{
		Obj func = MethodFactor.getDesignator().obj;
		if(Obj.Meth == func.getKind())
		{
			MethodFactor.struct = func.getType();
			if (Tab.noType == func.getType())
				report_error("funkcija " + MethodFactor.getDesignator().obj.getName() + " se ne moze koristiti u izrazima.", MethodFactor);
			else
			{
				if (currMethodArgs.size() != MethodFactor.getDesignator().obj.getFpPos())
					report_error("broj argumenata ne odgovara broju parametara funkcije " + MethodFactor.getDesignator().obj.getName() + ".", MethodFactor);
				else
				{
					if (!"len".equals(MethodFactor.getDesignator().obj.getName()))
					{
						int i = 0;
						for (Obj par : currMethodPars)
						{
							if (i == MethodFactor.getDesignator().obj.getFpPos())
								break;
							if (!par.getType().equals(currMethodArgs.get(i)))
								report_error("argument broj " + (i + 1) + " nije odgovarajuceg tipa.", MethodFactor);
							++i;
						}
					}
					else if (currMethodArgs.get(0).getKind() != Struct.Array)
						report_error("argument broj " + 1 + " nije odgovarajuceg tipa.", MethodFactor);
				}
				currMethodArgs.clear();
				currMethodPars = null;
			}
		}
		else
		{
			report_error("ime " + func.getName() + " nije funkcija.", MethodFactor);
			MethodFactor.struct = Tab.noType;
		}
	}
	
	@Override
	public void visit(LiteralFactor LiteralFactor)
	{
		LiteralFactor.struct = LiteralFactor.getLiteral().obj.getType();
	}

	@Override
	public void visit(NewArrFactor NewArrFactor)
	{
		NewArrFactor.struct = new Struct(Struct.Array, NewArrFactor.getType().struct);
		if (NewArrFactor.getExpr().struct != Tab.intType)
			report_error("izraz u zagradi nije tipa int.", NewArrFactor);
	}
	
	@Override
	public void visit(ExprFactor ExprFactor)
	{
		ExprFactor.struct = ExprFactor.getExpr().struct;
	}
	/*-----------------------------------------------------Designator--------------------------------------------------------*/
	@Override
	public void visit(ArrDesignator ArrDesignator)
	{
		if (ArrDesignator.getDesignator().obj.getType().getKind() == Struct.Array)
		{
			if (ArrDesignator.getExpr().struct == Tab.intType)
				ArrDesignator.obj = new Obj(Obj.Elem, null, ArrDesignator.getDesignator().obj.getType().getElemType());
			else
			{
				ArrDesignator.obj = Tab.noObj;
				report_error("izraz u zagradi nije tipa int.", ArrDesignator);
			}
		}
		else
		{
			ArrDesignator.obj = Tab.noObj;
			report_error("ime " + ArrDesignator.getDesignator().obj.getName() + " ne predstavlja niz.", ArrDesignator);
		}
	}
	
	@Override
	public void visit(SingleIdent SingleIdent)
	{
		SingleIdent.obj = Tab.find(SingleIdent.getName());
		if (SingleIdent.obj == Tab.noObj)
			report_error("ime " + SingleIdent.getName() + " nije deklarisano.", SingleIdent);
		else
		{
			report_obj(SingleIdent.obj, SingleIdent.getLine());
			if (SingleIdent.obj.getKind() == Obj.Meth)
				currMethodPars = SingleIdent.obj.getLocalSymbols();
		}
	}
}
