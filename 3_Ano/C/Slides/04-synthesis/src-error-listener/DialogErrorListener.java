import org.antlr.v4.runtime.*;
import java.util.List;
import java.util.Collections;
import java.awt.*;
import javax.swing.*;

public class DialogErrorListener extends BaseErrorListener {
   @Override public void syntaxError(Recognizer<?, ?> recognizer,
         Object offendingSymbol,
         int line, int charPositionInLine,
         String msg,
         RecognitionException e)
   {
      Parser p = ((Parser)recognizer);
      List<String> stack = p.getRuleInvocationStack();
      Collections.reverse(stack);
      StringBuilder buf = new StringBuilder();
      buf.append("rule stack: "+stack+" ");
      buf.append("line "+line+":"+charPositionInLine+" at "+
            offendingSymbol+": "+msg);
      JDialog dialog = new JDialog();
      Container contentPane = dialog.getContentPane();
      contentPane.add(new JLabel(buf.toString()));
      contentPane.setBackground(Color.white);
      dialog.setTitle("Syntax error");
      dialog.pack();
      dialog.setLocationRelativeTo(null);
      dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      dialog.setVisible(true);
   }
}

