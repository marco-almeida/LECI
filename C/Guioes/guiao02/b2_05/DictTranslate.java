import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings("CheckReturnValue")

public class DictTranslate extends DictBaseListener {
   protected Map<String, Integer> map = new HashMap<>();

   @Override
   public void enterProgram(DictParser.ProgramContext ctx) {
      System.out.println("Initializing dictionary");
   }

   @Override
   public void exitProgram(DictParser.ProgramContext ctx) {
      System.out.println("Done populating dictionary");
   }

   @Override
   public void enterStat(DictParser.StatContext ctx) {
   }

   @Override
   public void exitStat(DictParser.StatContext ctx) {
   }

   @Override
   public void enterExpr(DictParser.ExprContext ctx) {
   }

   @Override
   public void exitExpr(DictParser.ExprContext ctx) {
      int num = Integer.parseInt(ctx.Integer().getText());
      String str = ctx.Word().getText();
      map.put(str, num);
   }

   @Override
   public void enterEveryRule(ParserRuleContext ctx) {
   }

   @Override
   public void exitEveryRule(ParserRuleContext ctx) {
   }

   @Override
   public void visitTerminal(TerminalNode node) {
   }

   @Override
   public void visitErrorNode(ErrorNode node) {
   }
}
