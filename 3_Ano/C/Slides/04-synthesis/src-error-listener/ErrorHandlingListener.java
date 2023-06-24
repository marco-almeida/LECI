import org.antlr.v4.runtime.*;
import java.util.List;
import java.util.Collections;

/**
 * Error handling ANTLR listener
 *
 * <p>After parser object creation add the following instructions:
 * <ul>
 * <li>{@code parser.removeErrorListeners();}</li>
 * <li>{@code parser.addErrorListener(new ErrorHandlingListener());}</li>
 * </ul></p>
 *
 * @author  Miguel Oliveira e Silva (mos@ua.pt)
 * @version 1.0
 * @since   2016-05-19
 */
public class ErrorHandlingListener extends BaseErrorListener {
   @Override public void syntaxError(Recognizer<?, ?> recognizer,
         Object offendingSymbol,
         int line, int charPositionInLine,
         String msg,
         RecognitionException e)
   {
      ErrorHandling.printError(line, msg);
   }
}

