import java.util.*;

public class Execute extends CalculatorBaseVisitor<Double> {

   @Override public Double visitShow(CalculatorParser.ShowContext ctx) {
      Double res = visit(ctx.expr());
      if (res != null)
         System.out.println("Result: "+res);
      return res;
   }

   @Override public Double visitAssignment(CalculatorParser.AssignmentContext ctx) {
      Double res = visit(ctx.expr());
      if (res != null) {
         String id = ctx.ID().getText();
         variables.put(id, res);
      }
      return res;
   }

   @Override public Double visitExprUnaryOp(CalculatorParser.ExprUnaryOpContext ctx) {
      Double res = visit(ctx.expr());
      if (res != null) {
         switch(ctx.op.getText()) {
            case "-":
               res = -res;
               break;
         }
      }
      return res;
   }

   @Override public Double visitExprMultDiv(CalculatorParser.ExprMultDivContext ctx) {
      Double res = null;
      Double e1 = visit(ctx.expr(0));
      Double e2 = visit(ctx.expr(1));
      if (e1 != null && e2 != null) {
         switch(ctx.op.getText()) {
            case "*":
               res = e1 * e2;
               break;
            case "/":
               if (e2 == 0)
                  System.err.println("ERROR: divide by zero!");
               else
                  res = e1 / e2;
               break;
         }
      }
      return res;
   }

   @Override public Double visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
      Double res = null;
      Double e1 = visit(ctx.expr(0));
      Double e2 = visit(ctx.expr(1));
      if (e1 != null && e2 != null) {
         switch(ctx.op.getText()) {
            case "+":
               res = e1 + e2;
               break;
            case "-":
               res = e1 - e2;
               break;
         }
      }
      return res;
   }

   @Override public Double visitExprParent(CalculatorParser.ExprParentContext ctx) {
      Double res = visit(ctx.expr());
      return res;
   }

   @Override public Double visitExprID(CalculatorParser.ExprIDContext ctx) {
      Double res = null;
      String id = ctx.ID().getText();
      if (!variables.containsKey(id))
         System.err.println("ERROR: variable \""+id+"\" not found!");
      else
         res = variables.get(id);
      return res;
   }

   @Override public Double visitExprNumber(CalculatorParser.ExprNumberContext ctx) {
      Double res = Double.parseDouble(ctx.Number().getText());
      return res;
   }

   private Map<String,Double> variables = new HashMap<>();
}
