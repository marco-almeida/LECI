import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.stringtemplate.v4.*;
import error.*;

public class AdvMain {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: antlr4-java -ea AdvMain <source-file>");
			System.exit(1);
		}
		runCompiler(args[0]);
	}

	public static void runCompiler(String sourceFile) {
		assert sourceFile != null && !sourceFile.isEmpty();

		try {
			// create a CharStream that reads from standard input:
			CharStream input = CharStreams.fromStream(new FileInputStream(sourceFile));
			// create a lexer that feeds off of input CharStream:
			AdvLexer lexer = new AdvLexer(input);
			// create a buffer of tokens pulled from the lexer:
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			// create a parser that feeds off the tokens buffer:
			AdvParser parser = new AdvParser(tokens);
			// replace error listener:
			parser.removeErrorListeners(); // remove ConsoleErrorListener
			parser.addErrorListener(new ErrorHandlingListener());
			// begin parsing at program rule:
			ParseTree tree = parser.program();
			if (parser.getNumberOfSyntaxErrors() == 0) {
				// print LISP-style tree:
				// System.out.println(tree.toStringTree(parser));

				AdvSemantic visitor0 = new AdvSemantic();
				visitor0.visit(tree);
				if (!ErrorHandling.error()) {
					AdvCompiler compiler = new AdvCompiler();
					ST code = compiler.visit(tree);

					String filename = "output.py";
					String output = code.render();

					try {
						PrintWriter pw = new PrintWriter(new File(filename));
						pw.print(output);
						pw.close();
					} catch (IOException e) {
						System.err.println("ERROR: unable to write in file " + filename);
						System.exit(2);
					}
				} else {
					ErrorHandling.reset();
				}
			}
		} catch (IOException e) {
			System.err.println("ERROR: unable to read from file " + sourceFile);
			System.exit(3);
		} catch (RecognitionException e) {
			System.err.println("ERROR: Recognition exception " + sourceFile);
			System.exit(4);
		}
	}
}
