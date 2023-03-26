import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings("CheckReturnValue")
public class Execute extends Java8ParserBaseListener {
   @Override
   public void exitMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
      System.out.println(ctx.Identifier().getText());
   }

   @Override
   public void exitNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {
      System.out.println(ctx.Identifier().getText());
   }
}