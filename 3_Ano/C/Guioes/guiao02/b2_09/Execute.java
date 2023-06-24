import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SuppressWarnings("CheckReturnValue")
public class Execute extends CalFracBaseVisitor<String> {

	private Map<String, String> map = new HashMap<>();

	@Override
	public String visitProgram(CalFracParser.ProgramContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitStatPrint(CalFracParser.StatPrintContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitStatAssign(CalFracParser.StatAssignContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitPrint(CalFracParser.PrintContext ctx) {
		String res = visit(ctx.expr());
		if (res != null)
			System.out.println(res);
		return null;
	}

	@Override
	public String visitAssignment(CalFracParser.AssignmentContext ctx) {
		String key = ctx.ID().getText();
		map.put(key, visit(ctx.expr()));
		return null;
	}

	@Override
	public String visitExprAddSub(CalFracParser.ExprAddSubContext ctx) {
		String s1 = visit(ctx.expr(0));
		String s2 = visit(ctx.expr(1));
		if (ctx.op.getText().equals("-")) {
			return String.valueOf(Double.parseDouble(s1) - Double.parseDouble(s2));
		}
		return String.valueOf(Double.parseDouble(s1) + Double.parseDouble(s2));
	}

	@Override
	public String visitExpExponent(CalFracParser.ExpExponentContext ctx) {
		String base = visit(ctx.expr());
		String exponent = ctx.Integer().getText();
		if (ctx.op != null && ctx.op.getText().equals("-")) {
			return String.valueOf(Math.pow(Double.parseDouble(base), -1 * Double.parseDouble(exponent)));
		}
		return String.valueOf(Math.pow(Double.parseDouble(base), Double.parseDouble(exponent)));
	}

	@Override
	public String visitExprParent(CalFracParser.ExprParentContext ctx) {
		return visit(ctx.expr());
	}

	@Override
	public String visitExprRead(CalFracParser.ExprReadContext ctx) {
		String promptText = ctx.prompt.getText();
		System.out.print(promptText + ": ");
		Scanner in = new Scanner(System.in);
		String v = in.nextLine();
		return v;
	}

	@Override
	public String visitExprReduce(CalFracParser.ExprReduceContext ctx) {
		String expr = visit(ctx.expr());
		if (expr.contains("/")) {
			String[] values = expr.split("/");
			expr = String.valueOf(Double.parseDouble(values[0]) / Double.parseDouble(values[1]));
		}
		return reduceDecimalToFraction(Double.parseDouble(expr));
	}

	@Override
	public String visitExprMultDiv(CalFracParser.ExprMultDivContext ctx) {
		String s1 = visit(ctx.expr(0));
		String s2 = visit(ctx.expr(1));
		if (ctx.op.getText().equals("*")) {
			return String.valueOf(Double.parseDouble(s1) * Double.parseDouble(s2));
		}
		return String.valueOf(Double.parseDouble(s1) / Double.parseDouble(s2));
	}

	@Override
	public String visitExprInteger(CalFracParser.ExprIntegerContext ctx) {
		return ctx.Integer().getText();
	}

	@Override
	public String visitExprID(CalFracParser.ExprIDContext ctx) {
		String key = ctx.ID().getText();
		if (map.containsKey(key)) {
			return map.get(key);
		}
		System.out.println("Error: Variable undeclared!");
		return null;
	}

	@Override
	public String visitExprFraction(CalFracParser.ExprFractionContext ctx) {
		String s1 = ctx.Integer(0).getText();
		String s2 = ctx.Integer(1).getText();
		double value = Double.parseDouble(s1) / Double.parseDouble(s2);
		if (ctx.op != null && ctx.op.getText().equals("-")) {
			return String.valueOf(value * -1);
		}
		return String.valueOf(value);
	}

	private static String reduceDecimalToFraction(double decimal) {
		// Multiply by 10 until the decimal part becomes an integer
		int powerOf10 = 1;
		while (decimal * powerOf10 % 1 != 0) {
			powerOf10 *= 10;
		}
		int numerator = (int) (decimal * powerOf10);
		int denominator = powerOf10;
		// Reduce the fraction using the GCD
		int gcd = findGcd(numerator, denominator);
		numerator /= gcd;
		denominator /= gcd;
		return numerator + "/" + denominator;
	}

	// helper for reduceDecimalToFraction
	private static int findGcd(int a, int b) {
		if (b == 0) {
			return a;
		} else {
			return findGcd(b, a % b);
		}
	}

}
