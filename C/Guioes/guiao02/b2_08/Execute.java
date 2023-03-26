import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("CheckReturnValue")
public class Execute extends CalculatorMainBaseVisitor<String> {

   protected Map<String, String> map = new HashMap<>();

   @Override
   public String visitProgram(CalculatorMainParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public String visitStat(CalculatorMainParser.StatContext ctx) {
      String res = null;
      return visitChildren(ctx);
      // return res;
   }

   @Override
   public String visitAssignment(CalculatorMainParser.AssignmentContext ctx) {
      String var = ctx.ID().getText();
      String val = visit(ctx.expr());
      map.put(var, val);
      return null;
   }

   @Override
   public String visitExprAddSub(CalculatorMainParser.ExprAddSubContext ctx) {
      String res = null;
      return visitChildren(ctx);
      // return res;
   }

   @Override
   public String visitExprParent(CalculatorMainParser.ExprParentContext ctx) {
      String res = null;
      return visitChildren(ctx);
      // return res;
   }

   @Override
   public String visitExprInteger(CalculatorMainParser.ExprIntegerContext ctx) {
      String res = null;
      return visitChildren(ctx);
      // return res;
   }

   @Override
   public String visitExprID(CalculatorMainParser.ExprIDContext ctx) {
      String res = null;
      return visitChildren(ctx);
      // return res;
   }

   @Override
   public String visitExprPosNeg(CalculatorMainParser.ExprPosNegContext ctx) {
      String res = null;
      return visitChildren(ctx);
      // return res;
   }

   @Override
   public String visitExprMultDivMod(CalculatorMainParser.ExprMultDivModContext ctx) {
      String res = null;
      return visitChildren(ctx);
      // return res;
   }
}
