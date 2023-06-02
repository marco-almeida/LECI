import java.util.*;
import org.stringtemplate.v4.*;

public class CalcCompiler extends CalcBaseVisitor<ST> {

   @Override public ST visitMain(CalcParser.MainContext ctx) {
      ST res = templates.getInstanceOf("module");
		Iterator<CalcParser.StatContext> list = ctx.stat().iterator();
      while(list.hasNext()) {
         res.add("stat", visit(list.next()).render());
      }
      return res;
   }

	@Override public ST visitStat(CalcParser.StatContext ctx) {
      ST res = templates.getInstanceOf("stats");
      res.add("stat", visit(ctx.expr()).render());
      ST print = templates.getInstanceOf("print");
      print.add("value", ctx.expr().var);
      res.add("stat", print.render());
      return res;
   }

   @Override public ST visitExprAddSub(CalcParser.ExprAddSubContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ctx.var= newVar();
      res.add("stat", visit(ctx.expr(0)).render());
      res.add("stat", visit(ctx.expr(1)).render());
      ST bop = templates.getInstanceOf("binaryOperation");
      bop.add("type", "double");
      bop.add("var", ctx.var);
      bop.add("e1", ctx.expr(0).var);
      bop.add("op", ctx.op.getText());
      bop.add("e2", ctx.expr(1).var);
      res.add("stat", bop.render());
      return res;
   }

   @Override public ST visitExprMultDiv(CalcParser.ExprMultDivContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ctx.var= newVar();
      res.add("stat", visit(ctx.expr(0)).render());
      res.add("stat", visit(ctx.expr(1)).render());
      ST bop = templates.getInstanceOf("binaryOperation");
      bop.add("type", "double");
      bop.add("var", ctx.var);
      bop.add("e1", ctx.expr(0).var);
      bop.add("op", ctx.op.getText());
      bop.add("e2", ctx.expr(1).var);
      res.add("stat", bop.render());
      return res;
   }

   @Override public ST visitExprParent(CalcParser.ExprParentContext ctx) {
      ST res = visit(ctx.expr());
      ctx.var = ctx.expr().var;
      return res;
   }

   @Override public ST visitExprNumber(CalcParser.ExprNumberContext ctx) {
      ST res = templates.getInstanceOf("decl");
      ctx.var= newVar();
      res.add("type","double");
      res.add("var",ctx.var);
      res.add("value",ctx.Number().getText());
      return res;
   }

   private String newVar() {
      numVars++;
      return "v" + numVars;
   }

   private int numVars=0;
   private STGroup templates = new STGroupFile("java.stg");
}
