@SuppressWarnings("CheckReturnValue")
public class Execute extends PrefixCalculatorBaseVisitor<Double> {

   @Override public Double visitProgram(PrefixCalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Double visitStat(PrefixCalculatorParser.StatContext ctx) {
      if (ctx.expr() != null) {
         Double res = visit(ctx.expr());
         System.out.println(res == null ? "Error" : res);
      }
      return visitChildren(ctx);
   }

   @Override public Double visitExprPrefix(PrefixCalculatorParser.ExprPrefixContext ctx) {
      Double n1 = visit(ctx.expr(0));
      Double n2 = visit(ctx.expr(1));
      
      switch (ctx.op.getText()) {
         case "*":
            return n1 * n2;
         case "/":
            return n2 == 0 ? null : n1 / n2;
         case "+":
            return n1 + n2;
         case "-":
            return n1 - n2;
         default:
            return null;
      }
   }

   @Override public Double visitExprNumber(PrefixCalculatorParser.ExprNumberContext ctx) {
      return Double.parseDouble(ctx.Number().getText());
   }
}
