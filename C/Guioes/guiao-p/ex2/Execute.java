import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SuppressWarnings("CheckReturnValue")
public class Execute extends FracLangBaseVisitor<Fraction> {
	protected Map<String, Fraction> variables;
	protected Scanner sc;

	public Execute() {
		variables = new HashMap<>();
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
		return variables.put(varName, value);
	}

	@Override
	public Fraction visitFractionNum(FracLangParser.FractionNumContext ctx) {
		return new Fraction(Integer.parseInt(ctx.NUM().getText()));
	}

	@Override
	public Fraction visitFractionDivision(FracLangParser.FractionDivisionContext ctx) {
		Fraction f1 = visit(ctx.f1);
		Fraction f2 = visit(ctx.f2);
		if (f1 != null && f2 != null) {
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
	public Fraction visitFractionMultiplication(FracLangParser.FractionMultiplicationContext ctx) {
		Fraction f1 = visit(ctx.f1);
		Fraction f2 = visit(ctx.f2);
		if (f1 != null && f2 != null) {
			return Fraction.multiplyFractions(f1, f2);
		}
		return null;
	}

	@Override
	public Fraction visitFractionRead(FracLangParser.FractionReadContext ctx) {
		String toPrint = ctx.LITERAL_STRING().getText();
		System.out.printf("%s: ", toPrint.substring(1, toPrint.length() - 1));
		String input = sc.nextLine();
		try {
			if (input.contains("/")) {
				String[] data = input.split("/");
				return new Fraction(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
			}
			return new Fraction(Integer.parseInt(input));
		} catch (NumberFormatException e) {
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
		if (!variables.containsKey(variable)) {
			System.err.printf("ERROR: variable \"%s\" not found!\n", variable);
		}
		return variables.get(variable);
	}

	@Override
	public Fraction visitFractionFraction(FracLangParser.FractionFractionContext ctx) {
		return new Fraction(Integer.parseInt(ctx.NUM(0).getText()), Integer.parseInt(ctx.NUM(1).getText()));
	}

	@Override
	public Fraction visitFractionSum(FracLangParser.FractionSumContext ctx) {
		Fraction f1 = visit(ctx.f1);
		Fraction f2 = visit(ctx.f2);
		if (f1 != null && f2 != null) {
			return Fraction.sumFractions(f1, f2);
		}
		return null;
	}

	@Override
	public Fraction visitFractionSubtraction(FracLangParser.FractionSubtractionContext ctx) {
		Fraction f1 = visit(ctx.f1);
		Fraction f2 = visit(ctx.f2);
		if (f1 != null && f2 != null) {
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
		if (ctx.sign != null) {
			if (ctx.sign.getText().equals("-")) {
				return Fraction.multiplyFractions(f1, new Fraction(-1));
			} else {
				return f1;
			}
		}
		return f1;
	}
}
