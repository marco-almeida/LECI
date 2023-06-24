import static java.lang.System.*;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import java.util.*;

public class CalcExec extends CalcBaseListener {
   public CalcExec(Map<String,Symbol<Double>> symbolTable) {
      this.symbolTable = symbolTable;
   }

   @Override public void exitShow(CalcParser.ShowContext ctx) {
      double r = value.get(ctx.expr());
      if (ctx.expr().t.name().equals("integer"))
         out.println((int)r);
      else
         out.println(r);
   }

	@Override public void exitAssignment(CalcParser.AssignmentContext ctx) {
      String v = ctx.ID().getText();
      symbolTable.get(v).setValue(value.get(ctx.expr()));
   }

   @Override public void exitExprAddSub(CalcParser.ExprAddSubContext ctx) {
      double e1 = value.get(ctx.e1);
      double e2 = value.get(ctx.e2);
      double res = 0;
      switch(ctx.op.getText())
      {
         case "+":
            res = e1+e2;
            break;
         case "-":
            res = e1-e2;
            break;
      }
      value.put(ctx, res);
   }

   @Override public void exitExprMultDiv(CalcParser.ExprMultDivContext ctx) {
      double e1 = value.get(ctx.e1);
      double e2 = value.get(ctx.e2);
      double res = 0;
      switch(ctx.op.getText())
      {
         case "*":
            res = e1*e2;
            break;
         case "/":
            res = e1/e2;
            break;
         case "//":
            res = (double)((int)e1/(int)e2);
            break;
         case "\\":
            res = (double)((int)e1%(int)e2);
            break;
      }
      value.put(ctx, res);
   }

   @Override public void exitExprInt(CalcParser.ExprIntContext ctx) {
      double sign = ctx.sign != null && ctx.sign.getText().equals("-") ? -1.0 : 1.0;
      value.put(ctx, sign*(double)Integer.parseInt(ctx.INT().getText()));
   } 

	@Override public void exitExprReal(CalcParser.ExprRealContext ctx) {
      double sign = ctx.sign != null && ctx.sign.getText().equals("-") ? -1.0 : 1.0;
      value.put(ctx, sign*Double.parseDouble(ctx.REAL().getText()));
   }

	@Override public void exitExprID(CalcParser.ExprIDContext ctx) {
      String v = ctx.ID().getText();
      value.put(ctx, (Double)symbolTable.get(v).value());
   }

	@Override public void exitExprParen(CalcParser.ExprParenContext ctx) {
      value.put(ctx, value.get(ctx.e));
   }

   protected ParseTreeProperty<Double> value = new ParseTreeProperty<>();
   protected final Map<String,Symbol<Double>> symbolTable;
}

