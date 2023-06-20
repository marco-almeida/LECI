import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SuppressWarnings("CheckReturnValue")
public class Execute extends FracLangBaseVisitor<Fraction> {
	protected Map<String, Fraction> symbolTable;
	protected Scanner sc;

	public Execute() {
		symbolTable = new HashMap<>();
		sc = new Scanner(System.in);
	}

	@Override
	public Fraction visitStatDisplay(FracLangParser.StatDisplayContext ctx) {
		Fraction f = visit(ctx.fraction());
		if (f != null) {
			System.out.println(f);
		}
		return null;
	}

	@Override
	public Fraction visitStatAssigment(FracLangParser.StatAssigmentContext ctx) {
		String varName = ctx.ID().getText();
		Fraction value = visit(ctx.fraction());
		return symbolTable.put(varName, value);
	}

	@Override
	public Fraction visitFractionNum(FracLangParser.FractionNumContext ctx) {
		return new Fraction(Integer.parseInt(ctx.NUM().getText()));
	}

	@Override
	public Fraction visitFractionMultDiv(FracLangParser.FractionMultDivContext ctx) {
		Fraction f1 = visit(ctx.f1);
		Fraction f2 = visit(ctx.f2);
		if (f1 != null && f2 != null) {
			if (ctx.op.getText().equals("*")) {
				return Fraction.multiplyFractions(f1, f2);
			}
			return Fraction.divideFractions(f1, f2);
		}
		return null;
	}

	@Override
	public Fraction visitFractionReduce(FracLangParser.FractionReduceContext ctx) {
		Fraction f1 = visit(ctx.fraction());
		if (f1 != null) {
			return Fraction.reduce(f1);
		}
		return null;
	}

	@Override
	public Fraction visitFractionRead(FracLangParser.FractionReadContext ctx) {
		String toPrint = ctx.LITERAL_STRING().getText();
		System.out.printf("%s: ", toPrint.substring(1, toPrint.length() - 1));
		String input = sc.nextLine();
		if (input.length() == 0) {
			System.err.println("ERROR: empty input"); // o stor n verifica isto e dá lhe erro
			return null;
		}
		String[] data = input.split("/");
		try {
			switch (data.length) {
				case 1:
					return new Fraction(Integer.parseInt(input));
				case 2:
					return new Fraction(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
				default:
					System.err.printf("ERROR: invalid fraction %s\n", input);
			}
		} catch (Exception e) {
			System.err.printf("ERROR: invalid fraction %s\n", input);
		}
		return null;

	}

	@Override
	public Fraction visitFractionParenthesis(FracLangParser.FractionParenthesisContext ctx) {
		return visit(ctx.fraction());
	}

	@Override
	public Fraction visitFractionId(FracLangParser.FractionIdContext ctx) {
		String variable = ctx.ID().getText();
		if (!symbolTable.containsKey(variable)) {
			System.err.printf("ERROR: variable \"%s\" not found!\n", variable);
		}
		return symbolTable.get(variable);
	}

	@Override
	public Fraction visitFractionFraction(FracLangParser.FractionFractionContext ctx) {
		return new Fraction(Integer.parseInt(ctx.NUM(0).getText()), Integer.parseInt(ctx.NUM(1).getText()));
	}

	@Override
	public Fraction visitFractionSumSub(FracLangParser.FractionSumSubContext ctx) {
		Fraction f1 = visit(ctx.f1);
		Fraction f2 = visit(ctx.f2);
		if (f1 != null && f2 != null) {
			if (ctx.op.getText().equals("+")) {
				return Fraction.sumFractions(f1, f2);
			}
			return Fraction.subtractFractions(f1, f2);
		}
		return null;
	}

	@Override
	public Fraction visitFractionSign(FracLangParser.FractionSignContext ctx) {
		Fraction f1 = visit(ctx.fraction());
		if (f1 == null) {
			return null;
		}
		if (ctx.sign.getText().equals("-")) {
			return Fraction.multiplyFractions(f1, new Fraction(-1));
		}
		return f1;
	}
}
