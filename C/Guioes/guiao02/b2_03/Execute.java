@SuppressWarnings("CheckReturnValue")
public class Execute extends CalculatorBaseVisitor<Long> {

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
   public Long visitExprPosNeg(CalculatorParser.ExprPosNegContext ctx) {
      Long num = visit(ctx.expr());
      if (ctx.op.getText().equals("-")) {
         return num * -1;
      }
      return num;
   }

   @Override
   public Long visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
      Long n1 = visit(ctx.expr(0));
      Long n2 = visit(ctx.expr(1));

      if (ctx.op.getText().equals("+")) {
         return n1 + n2;
      } else if (ctx.op.getText().equals("-")) {
         return n1 - n2;
      }
      return null;
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
   public Long visitExprMultDivMod(CalculatorParser.ExprMultDivModContext ctx) {
      Long n1 = visit(ctx.expr(0));
      Long n2 = visit(ctx.expr(1));

      switch (ctx.op.getText()) {
         case "*":
            return n1 * n2;
         case "/":
            return n2 == 0 ? null : n1 / n2;
         case "%":
            return n2 == 0 ? null : n1 % n2;
         default:
            return null;
      }
   }
}
