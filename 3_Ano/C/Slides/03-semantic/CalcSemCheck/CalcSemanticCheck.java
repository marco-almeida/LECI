import static java.lang.System.*;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.*;
import error.*;

public class CalcSemanticCheck extends CalcBaseVisitor<Boolean> { // visitor callbacks return true if no semantic error was detected!
   public Map<String,Symbol<Double>> symbolTable() {
      return symbolTable;
   }

	@Override public Boolean visitDeclaration(CalcParser.DeclarationContext ctx) {
      // first visit type:
      boolean res = visit(ctx.type()); // in this example is always true
      if (res) {
         ctx.idList().t = ctx.type().res;
         res = visit(ctx.idList());
      }
      return res;
   }

	@Override public Boolean visitIdList(CalcParser.IdListContext ctx) {
      boolean res = true;
      Iterator<TerminalNode> list = ctx.ID().iterator();
      while(list.hasNext()) {
         String id = list.next().getText(); 
         if (symbolTable.containsKey(id)) {
            ErrorHandling.printError(ctx, "variable "+id+" already exists!");
            res = false;
         }
         else {
            Symbol<Double> s = new VariableSymbol<Double>(id, ctx.t);
            symbolTable.put(id, s);
         }
      }
      return res;
   }

   @Override public Boolean visitIntegerType(CalcParser.IntegerTypeContext ctx) {
      ctx.res = integerType;
      return true;
   }

   @Override public Boolean visitRealType(CalcParser.RealTypeContext ctx) {
      ctx.res = realType;
      return true;
   }

   @Override public Boolean visitAssignment(CalcParser.AssignmentContext ctx) {
      boolean res = visit(ctx.expr());
      String v = ctx.ID().getText();
      if (!symbolTable.containsKey(v)) {
         ErrorHandling.printError(ctx, "variable "+v+" not declared!");
         res = false;
      }
      else if (res && !ctx.expr().t.subtype(symbolTable.get(v).type())) {
         ErrorHandling.printError(ctx, ctx.expr().t.name()+" expression is not assignable to variable "+v+" "+symbolTable.get(v).type().name()+" type!");
         res = false;
      }
      return res;
   }

   protected Type getBinaryOperationType(CalcParser.ExprContext e1, CalcParser.ExprContext e2) {
      Type res;
      if (e1.t.equals(e2.t))
         res = e1.t;
      else
         res = realType;
      return res;
   }

   @Override public Boolean visitExprMultDiv(CalcParser.ExprMultDivContext ctx) {
      boolean res = visit(ctx.e1) && visit(ctx.e2);
      if (res) {
         ctx.t = getBinaryOperationType(ctx.e1, ctx.e2);
         switch(ctx.op.getText())
         {
            case "//":
            case "\\":
               if (!ctx.e1.t.subtype(integerType)) {
                  ErrorHandling.printError(ctx, "type expression "+ctx.e1.getText()+" is not integer!");
                  res = false;
               }
               else if (!ctx.e2.t.subtype(integerType)) {
                  ErrorHandling.printError(ctx, "type expression "+ctx.e2.getText()+" is not integer!");
                  res = false;
               }
               break;
         }
      }
      return res;
   }

   @Override public Boolean visitExprAddSub(CalcParser.ExprAddSubContext ctx) {
      boolean res = visit(ctx.e1) && visit(ctx.e2);
      if (res)
         ctx.t = getBinaryOperationType(ctx.e1, ctx.e2);
      return res;
   }

   @Override public Boolean visitExprInt(CalcParser.ExprIntContext ctx) {
      ctx.t = integerType;
      return true;
   }

   @Override public Boolean visitExprReal(CalcParser.ExprRealContext ctx) {
      ctx.t = realType;
      return true;
   }

   @Override public Boolean visitExprID(CalcParser.ExprIDContext ctx) {
      boolean res = true;
      String v = ctx.ID().getText();
      if (!symbolTable.containsKey(v)) {
         ErrorHandling.printError(ctx, "variable "+v+" not declared!");
         res = false;
      }
      else
         ctx.t = symbolTable.get(v).type();
      return res;
   }

	@Override public Boolean visitExprParen(CalcParser.ExprParenContext ctx) {
      boolean res = visit(ctx.e);
      if (res)
         ctx.t = ctx.e.t;
      return res;
   }

   protected Map<String,Symbol<Double>> symbolTable = new HashMap<>();
   protected static final Type realType = new RealType();
   protected static final Type integerType = new IntegerType();
}

