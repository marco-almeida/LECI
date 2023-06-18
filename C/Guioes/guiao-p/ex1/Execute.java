import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SuppressWarnings("CheckReturnValue")
public class Execute extends StrLangBaseVisitor<String> {
	protected Map<String, String> variables = new HashMap<>();
	protected Scanner sc = new Scanner(System.in);

	@Override
	public String visitStatPrint(StrLangParser.StatPrintContext ctx) {
		String s = visit(ctx.printable());
		if (s != null) {
			System.out.println(visit(ctx.printable()));
		}
		return null;
	}

	@Override
	public String visitStatAssign(StrLangParser.StatAssignContext ctx) {
		String varName = ctx.VARIABLE().getText();
		String value = visit(ctx.printable());
		return variables.put(varName, value);
	}

	@Override
	public String visitPrintableString(StrLangParser.PrintableStringContext ctx) {
		String str = ctx.QUOTED_STR().getText();
		return str.substring(1, str.length() - 1);
	}

	@Override
	public String visitPrintableSubstitution(StrLangParser.PrintableSubstitutionContext ctx) {
		String original = visit(ctx.printable(0));
		String target = visit(ctx.printable(1));
		String replacement = visit(ctx.printable(2));
		if (original != null && target != null && replacement != null) {
			return original.replace(target, replacement);
		}
		return null;
	}

	@Override
	public String visitPrintableVariable(StrLangParser.PrintableVariableContext ctx) {
		String variable = ctx.VARIABLE().getText();
		if (!variables.containsKey(variable)) {
			System.err.printf("ERROR: variable \"%s\" does not exist!\n", variable);
		}
		return variables.get(variable);
	}

	@Override
	public String visitPrintableTrim(StrLangParser.PrintableTrimContext ctx) {
		String s1 = visit(ctx.printable());
		if (s1 != null) {
			return s1.trim();
		}
		return null;
	}

	@Override
	public String visitPrintableParenthesis(StrLangParser.PrintableParenthesisContext ctx) {
		return visit(ctx.printable());
	}

	@Override
	public String visitPrintableRemove(StrLangParser.PrintableRemoveContext ctx) {
		String s1 = visit(ctx.printable(0));
		String s2 = visit(ctx.printable(1));
		if (s1 != null && s2 != null) {
			return s1.replace(s2, "");
		}
		return null;
	}

	@Override
	public String visitPrintableInput(StrLangParser.PrintableInputContext ctx) {
		System.out.print(visit(ctx.printable()));
		return sc.nextLine();
	}

	@Override
	public String visitPrintableConcat(StrLangParser.PrintableConcatContext ctx) {
		String s1 = visit(ctx.printable(0));
		String s2 = visit(ctx.printable(1));
		if (s1 != null && s2 != null) {
			return s1.concat(s2);
		}
		return null;
	}
}
