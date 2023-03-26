import java.io.IOException;
import java.util.Scanner;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class DictMain {
   public static void main(String[] args) {
      try {
         // create a CharStream that reads file "numbers.txt"
         CharStream input = CharStreams.fromFileName("numbers.txt");
         // create a lexer that feeds off of input CharStream:
         DictLexer lexer = new DictLexer(input);
         // create a buffer of tokens pulled from the lexer:
         CommonTokenStream tokens = new CommonTokenStream(lexer);
         // create a parser that feeds off the tokens buffer:
         DictParser parser = new DictParser(tokens);
         // replace error listener:
         //parser.removeErrorListeners(); // remove ConsoleErrorListener
         //parser.addErrorListener(new ErrorHandlingListener());
         // begin parsing at program rule:
         ParseTree tree = parser.program();
         if (parser.getNumberOfSyntaxErrors() == 0) {
            // print LISP-style tree:
            // System.out.println(tree.toStringTree(parser));
            ParseTreeWalker walker = new ParseTreeWalker();
            DictTranslate listener0 = new DictTranslate();
            walker.walk(listener0, tree);

            Scanner in = new Scanner(System.in);
               while (in.hasNextLine()) {
                  String line = in.nextLine();
                  // String[] words = line.replace('-', ' ').toLowerCase().split(" ");
                  String[] words = line.split(" ");
                  StringBuilder stb = new StringBuilder();
                  for (String w : words) {
                     if (listener0.map.containsKey(w)) {
                        stb.append(listener0.map.get(w) + " ");
                     } else {
                        stb.append(w + " ");
                     }
                  }
                  System.out.println(stb.toString().trim());
               }
               in.close();
         }
      }
      catch(IOException e) {
         e.printStackTrace();
         System.exit(1);
      }
      catch(RecognitionException e) {
         e.printStackTrace();
         System.exit(1);
      }
   }
}
