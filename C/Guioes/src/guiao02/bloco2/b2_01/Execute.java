@SuppressWarnings("CheckReturnValue")
public class Execute extends HelloBaseVisitor<String> {

   @Override public String visitGreetings(HelloParser.GreetingsContext ctx) {
      System.out.println("Custom Hello " + ctx.ID().getText() + "!");
      return visitChildren(ctx);
   }

   @Override public String visitBye(HelloParser.ByeContext ctx) {
      System.out.println("Custom Bye " + ctx.ID().getText() + "!");
      return visitChildren(ctx);
   }
}
