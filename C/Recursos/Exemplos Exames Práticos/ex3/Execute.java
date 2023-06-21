import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("CheckReturnValue")
public class Execute extends VectorBaseVisitor<Value> {
   private Map<String, Value> symbolTable = new HashMap<>();

   @Override
   public Value visitStatShow(VectorParser.StatShowContext ctx) {
      Value res = visit(ctx.expr());
      if (res != null) {
         System.out.println(res);
      }
      return null;
   }

   @Override
   public Value visitStatAssigment(VectorParser.StatAssigmentContext ctx) {
      String variable = ctx.ID().getText();
      Value value = visit(ctx.expr());
      return symbolTable.put(variable, value);
   }

   @Override
   public Value visitExprSumSub(VectorParser.ExprSumSubContext ctx) {
      Value v1 = visit(ctx.e1);
      Value v2 = visit(ctx.e2);
      if (v1 != null && v2 != null) {
         if (v1.getClass().equals(v2.getClass())) {
            if (ctx.op.getText().equals("+")) {
               return v1.sum(v2);
            } else {
               return v1.subtract(v2);
            }
         } else {
            System.err.printf("ERROR: Operation unsupported for different types\n");
         }
      }
      return null;
   }

   @Override
   public Value visitExprMultiply(VectorParser.ExprMultiplyContext ctx) {
      Value v1 = visit(ctx.e1);
      Value v2 = visit(ctx.e2);
      if (v1 != null && v2 != null) {
         if (!(v1.isVector() && v2.isVector())) {
            return v1.multiply(v2);
         } else {
            System.err.printf("ERROR: Cant multiply vectors\n");
         }
      }
      return null;
   }

   @Override
   public Value visitExprInternalProduct(VectorParser.ExprInternalProductContext ctx) {
      Value v1 = visit(ctx.e1);
      Value v2 = visit(ctx.e2);
      if (v1 != null && v2 != null) {
         if (v1.isVector() && v2.isVector()) {
            return ((Vector) v1).internalProduct(v2);
         } else {
            System.err.printf("ERROR: Cant do internal product with numbers\n");
         }
      }
      return null;
   }

   @Override
   public Value visitExprId(VectorParser.ExprIdContext ctx) {
      String variable = ctx.ID().getText();
      Value value = symbolTable.get(variable);
      if (value == null) {
         System.err.printf("ERROR: bad variable %s\n", variable);
      }
      return value;
   }

   @Override
   public Value visitExprUnary(VectorParser.ExprUnaryContext ctx) {
      Value res = visit(ctx.expr());
      if (res != null && ctx.sign.getText().equals("-")) {
         return res.symmetric();
      }
      return res;
   }

   @Override
   public Value visitExprParenthesis(VectorParser.ExprParenthesisContext ctx) {
      return visit(ctx.expr());
   }

   @Override
   public Value visitExprNumber(VectorParser.ExprNumberContext ctx) {
      return new Scalar(Double.parseDouble(ctx.NUMBER().getText()));
   }

   @Override
   public Value visitExprVector(VectorParser.ExprVectorContext ctx) {
      String vector = ctx.VECTOR().getText();
      try {
         return new Vector(vector);
      } catch (Exception e) {
         System.out.printf("ERROR: bad vector %s\n", vector);
      }
      return null;
   }
}
