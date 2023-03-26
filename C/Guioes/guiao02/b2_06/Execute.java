import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("CheckReturnValue")
public class Execute extends CalculatorBaseVisitor<Long> {

   protected Map<String, Long> map = new HashMap<>();

   @Override
   public Long visitProgram(CalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public Long visitStat(CalculatorParser.StatContext ctx) {
      if (ctx.expr() != null) {
         Long result = visit(ctx.expr());
         System.out.println(result == null ? "Error" : result);
      }
      return visitChildren(ctx);
   }

   @Override
   public Long visitAssignment(CalculatorParser.AssignmentContext ctx) {
      String var = ctx.ID().getText();
      Long val = visit(ctx.expr());
      map.put(var, val);
      return null;
   }

   @Override
   public Long visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
      Long n1 = visit(ctx.expr(0));
      Long n2 = visit(ctx.expr(1));
      if (ctx.op.getText().equals("+")) {
         return n1 + n2;
      }
      return n1 - n2;
   }

   @Override
   public Long visitExprParent(CalculatorParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override
   public Long visitExprInteger(CalculatorParser.ExprIntegerContext ctx) {
      return Long.parseLong(ctx.Integer().getText());
   }

   @Override
   public Long visitExprID(CalculatorParser.ExprIDContext ctx) {
      return map.getOrDefault(ctx.ID().getText(), null);
   }

   @Override
   public Long visitExprPosNeg(CalculatorParser.ExprPosNegContext ctx) {
      Long num = visit(ctx.expr());
      if (ctx.op.getText().equals("-")) {
         return num * -1;
      }
      return num;
   }

   @Override
   public Long visitExprMultDivMod(CalculatorParser.ExprMultDivModContext ctx) {
      Long n1 = visit(ctx.expr(0));
      Long n2 = visit(ctx.expr(1));
      String op = ctx.op.getText();

      if (op.equals("*")) {
         return n1 * n2;
      }
      if (n2 != 0) {
         if (op.equals("/")) {
            return n1 / n2;
         }
         return n1 % n2;
      }
      return null;
   }
}
