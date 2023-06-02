import java.util.Scanner;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import error.*;

public class CalcMain {
   public static void main(String[] args) throws Exception {
      Scanner sc = new Scanner(System.in);
      String lineText = null;
      int numLine = 1;
      if (sc.hasNextLine())
         lineText = sc.nextLine();
      CalcParser parser = new CalcParser(null);
      // replace error listener:
      parser.removeErrorListeners(); // remove ConsoleErrorListener
      parser.addErrorListener(new ErrorHandlingListener());
      ParseTreeWalker walker = new ParseTreeWalker();
      CalcSemanticCheck visitor0 = new CalcSemanticCheck();
      CalcExec listener1 = new CalcExec(visitor0.symbolTable());
      while(lineText != null) {
         // create a CharStream that reads from standard input:
         CharStream input = CharStreams.fromString(lineText + "\n");
         // create a lexer that feeds off of input CharStream:
         CalcLexer lexer = new CalcLexer(input);
         lexer.setLine(numLine);
         lexer.setCharPositionInLine(0);
         // create a buffer of tokens pulled from the lexer:
         CommonTokenStream tokens = new CommonTokenStream(lexer);
         // create a parser that feeds off the tokens buffer:
         parser.setInputStream(tokens);
         // begin parsing at program rule:
         ParseTree tree = parser.program();
         if (parser.getNumberOfSyntaxErrors() == 0) {
            // print LISP-style tree:
            // System.out.println(tree.toStringTree(parser));
            visitor0.visit(tree);
            if (!ErrorHandling.error())
               walker.walk(listener1, tree);
            else
               ErrorHandling.reset();
         }
         if (sc.hasNextLine())
            lineText = sc.nextLine();
         else
            lineText = null;
         numLine++;
      }
   }
}
