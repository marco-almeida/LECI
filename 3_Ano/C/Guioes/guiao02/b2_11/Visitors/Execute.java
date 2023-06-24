import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("CheckReturnValue")
public class Execute extends SetCalcVisitorsBaseVisitor<Set<String>> {

	protected Map<String, Set<String>> varTable = new HashMap<>();
	protected Set<String> tempSet;

	@Override
	public Set<String> visitMain(SetCalcVisitorsParser.MainContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Set<String> visitStat(SetCalcVisitorsParser.StatContext ctx) {
		Set<String> result = visit(ctx.expr());
		String str = result.toString();
		str = "{" + str.substring(1, str.length() - 1).replaceAll(" ", "") + "}";
		System.out.println("result: " + str);
		return result;
	}

	@Override
	public Set<String> visitExprVar(SetCalcVisitorsParser.ExprVarContext ctx) {
		String var = ctx.VAR().getText();
		if (!varTable.containsKey(var)) {
			System.err.println("ERROR: variable \"" + var + "\" not defined!");
			System.exit(1);
		}
		return varTable.get(var);
	}

	@Override
	public Set<String> visitExprParen(SetCalcVisitorsParser.ExprParenContext ctx) {
		return visit(ctx.e);
	}

	@Override
	public Set<String> visitExprSubtract(SetCalcVisitorsParser.ExprSubtractContext ctx) {
		Set<String> res = new HashSet<>();
		Set<String> set1 = visit(ctx.e1);
		Set<String> set2 = visit(ctx.e2);
		for (String s : set1) {
			if (!set2.contains(s)) {
				res.add(s);
			}
		}
		return res;
	}

	@Override
	public Set<String> visitExprUnion(SetCalcVisitorsParser.ExprUnionContext ctx) {
		Set<String> set1 = visit(ctx.e1);
		Set<String> set2 = visit(ctx.e2);
		Set<String> res = new HashSet<>(set1);
		res.addAll(set2);
		return res;
	}

	@Override
	public Set<String> visitExprAssign(SetCalcVisitorsParser.ExprAssignContext ctx) {
		String varName = ctx.VAR().getText();
		Set<String> set = visit(ctx.e);
		varTable.put(varName, set);
		return set;
	}

	@Override
	public Set<String> visitExprIntersect(SetCalcVisitorsParser.ExprIntersectContext ctx) {
		Set<String> set1 = visit(ctx.e1);
		Set<String> set2 = visit(ctx.e2);
		Set<String> res = new HashSet<>(set1);
		res.retainAll(set2);
		return res;
	}

	@Override
	public Set<String> visitExprSet(SetCalcVisitorsParser.ExprSetContext ctx) {
		return visit(ctx.set());
	}

	@Override
	public Set<String> visitSet(SetCalcVisitorsParser.SetContext ctx) {
		tempSet = new HashSet<>();
		visitChildren(ctx);
		return tempSet;
	}

	@Override
	public Set<String> visitElem(SetCalcVisitorsParser.ElemContext ctx) {
		tempSet.add(ctx.getText());
		return null;
	}
}
