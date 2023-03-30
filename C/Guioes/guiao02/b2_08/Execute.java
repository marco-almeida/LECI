@SuppressWarnings("CheckReturnValue")
public class Execute extends CalculatorBaseVisitor<String> {

	@Override
	public String visitProgram(CalculatorParser.ProgramContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitStatExpr(CalculatorParser.StatExprContext ctx) {
		System.out.println(visit(ctx.expr()));
		return visitChildren(ctx);
	}

	@Override
	public String visitStatAssign(CalculatorParser.StatAssignContext ctx) {
		System.out.println(visit(ctx.assignment()));
		return visitChildren(ctx);
	}

	@Override
	public String visitStatNL(CalculatorParser.StatNLContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitAssignment(CalculatorParser.AssignmentContext ctx) {
		return String.format("%s = %s", ctx.ID().getText(), visit(ctx.expr()));
	}

	@Override
	public String visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
		String n1 = visit(ctx.expr(0));
		String n2 = visit(ctx.expr(1));
		String op = ctx.op.getText();
		return String.format("%s %s %s", n1, n2, op);
	}

	@Override
	public String visitExprParent(CalculatorParser.ExprParentContext ctx) {
		return visit(ctx.expr());
	}

	@Override
	public String visitExprInteger(CalculatorParser.ExprIntegerContext ctx) {
		return ctx.Integer().getText();
	}

	@Override
	public String visitExprID(CalculatorParser.ExprIDContext ctx) {
		return ctx.ID().getText();
	}

	@Override
	public String visitExprPosNeg(CalculatorParser.ExprPosNegContext ctx) {
		String posNeg = ctx.op.getText();
		return String.format("!%s", posNeg);
	}

	@Override
	public String visitExprMultDivMod(CalculatorParser.ExprMultDivModContext ctx) {
		String n1 = visit(ctx.expr(0));
		String n2 = visit(ctx.expr(1));
		String op = ctx.op.getText();
		return String.format("%s %s %s", n1, n2, op);
	}
}
