package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.mj.runtime.Run;
import rs.etf.pp1.mj.runtime.disasm;
import rs.etf.pp1.symboltable.concepts.Obj;

public class Compiler
{
	static
	{
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void tsdump()
	{
        Tab.dump(new Tab.DumpSymbolTableVisitor());
	}
	
	public static void main(String[] args)
	{
		Logger log = Logger.getLogger(Compiler.class);
		if (args.length < 2)
		{
			log.error("Nedovoljan broj argumenata. Upotreba: MJParser <source-file> <obj-file>");
			return;
		}
		
		File sourceCode = new File(args[0]);
		if (!sourceCode.exists())
		{
			log.error("Izvorni fajl [" + sourceCode.getAbsolutePath() + "] nije pronadjen.");
			return;
		}	
		log.info("Prevodjenje izvornog fajla: " + sourceCode.getAbsolutePath());
		
		try (BufferedReader br = new BufferedReader(new FileReader(sourceCode)))
		{
			Yylex lexer = new Yylex(br);
			MJParser p = new MJParser(lexer);
			
			// pocetak parsiranja
	        Symbol s = p.parse();  
	        SyntaxNode prog = (SyntaxNode)(s.value);
	        if (!p.errorDetected)
	        {
	        	// ispis sintaksnog stabla
	        	log.info("===================================");
		        log.info(prog.toString());
		        log.info("Parsiranje uspijesno zavrseno.");
				
				// pocetak semanticke analize
				Tab.init();
				Tab.insert(Obj.Type, "bool", Tab.boolType);
				Tab.find("chr").setFpPos(1);
				Tab.find("ord").setFpPos(1);
				Tab.find("len").setFpPos(1);
		        SemanticAnalyzer v = new SemanticAnalyzer();
		        prog.traverseBottomUp(v);
		        
		        if (!v.errorDetected)
		        {
		        	// ispis tabele simbola
		        	tsdump();
		        	log.info("Semanticka analiza uspijesno zavrsena.");
		        	
		        	// pocetak generisanja koda
		        	File objFile = new File(args[1]);
		        	if (objFile.exists())
		        		objFile.delete();
		        	log.info("Generisanje objektnog fajla: " + objFile.getAbsolutePath());
		        	
		        	CodeGenerator codeGenerator = new CodeGenerator();
		        	prog.traverseBottomUp(codeGenerator);
		        	Code.dataSize = v.nVars;
		        	Code.mainPc = codeGenerator.getMainPc();
		        	Code.write(new FileOutputStream(objFile));
		        	log.info("Uspijesno generisan objektni fajl.");
		        	
		        	// disasembliranje i pokretanje objektnog fajla
		        	disasm.main(new String[] {args[1]});
		        	Run.main(new String[] {args[1]});
		        }
		        else
		        	log.error("Semanticka analiza NIJE uspijesno zavrsena.");
	        }
	        else
	        	log.error("Parsiranje NIJE uspijesno zavrseno.");
		}
		catch (Exception e)
		{
			log.error("Neocekivana greska prilikom parsiranja.");
		}
	}
}
