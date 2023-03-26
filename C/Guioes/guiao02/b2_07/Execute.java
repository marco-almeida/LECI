import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings("CheckReturnValue")
public class Execute extends Java8ParserBaseListener {
	@Override
	public void enterNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {
		System.out.println("class " + ctx.Identifier().getText());
	}

	@Override
	public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
		System.out.println("method " + ctx.Identifier().getText());
	}

	protected boolean inside = false;

	@Override
	public void enterFieldDeclaration(Java8Parser.FieldDeclarationContext ctx) {
		inside = true;
	}

	@Override
	public void exitFieldDeclaration(Java8Parser.FieldDeclarationContext ctx) {
		inside = false;
	}

	@Override
	public void enterVariableDeclaratorId(Java8Parser.VariableDeclaratorIdContext ctx) {
		if (inside)
			System.out.println("field " + ctx.Identifier().getText());
	}
}