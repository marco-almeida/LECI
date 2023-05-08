// Generated from SetCalcVisitors.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SetCalcVisitorsParser}.
 */
public interface SetCalcVisitorsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SetCalcVisitorsParser#main}.
	 * @param ctx the parse tree
	 */
	void enterMain(SetCalcVisitorsParser.MainContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetCalcVisitorsParser#main}.
	 * @param ctx the parse tree
	 */
	void exitMain(SetCalcVisitorsParser.MainContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetCalcVisitorsParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(SetCalcVisitorsParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetCalcVisitorsParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(SetCalcVisitorsParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprVar}
	 * labeled alternative in {@link SetCalcVisitorsParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprVar(SetCalcVisitorsParser.ExprVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprVar}
	 * labeled alternative in {@link SetCalcVisitorsParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprVar(SetCalcVisitorsParser.ExprVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprParen}
	 * labeled alternative in {@link SetCalcVisitorsParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprParen(SetCalcVisitorsParser.ExprParenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprParen}
	 * labeled alternative in {@link SetCalcVisitorsParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprParen(SetCalcVisitorsParser.ExprParenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprSubtract}
	 * labeled alternative in {@link SetCalcVisitorsParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprSubtract(SetCalcVisitorsParser.ExprSubtractContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprSubtract}
	 * labeled alternative in {@link SetCalcVisitorsParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprSubtract(SetCalcVisitorsParser.ExprSubtractContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprUnion}
	 * labeled alternative in {@link SetCalcVisitorsParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprUnion(SetCalcVisitorsParser.ExprUnionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprUnion}
	 * labeled alternative in {@link SetCalcVisitorsParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprUnion(SetCalcVisitorsParser.ExprUnionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAssign}
	 * labeled alternative in {@link SetCalcVisitorsParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprAssign(SetCalcVisitorsParser.ExprAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAssign}
	 * labeled alternative in {@link SetCalcVisitorsParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprAssign(SetCalcVisitorsParser.ExprAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprIntersect}
	 * labeled alternative in {@link SetCalcVisitorsParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprIntersect(SetCalcVisitorsParser.ExprIntersectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprIntersect}
	 * labeled alternative in {@link SetCalcVisitorsParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprIntersect(SetCalcVisitorsParser.ExprIntersectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprSet}
	 * labeled alternative in {@link SetCalcVisitorsParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprSet(SetCalcVisitorsParser.ExprSetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprSet}
	 * labeled alternative in {@link SetCalcVisitorsParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprSet(SetCalcVisitorsParser.ExprSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetCalcVisitorsParser#set}.
	 * @param ctx the parse tree
	 */
	void enterSet(SetCalcVisitorsParser.SetContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetCalcVisitorsParser#set}.
	 * @param ctx the parse tree
	 */
	void exitSet(SetCalcVisitorsParser.SetContext ctx);
	/**
	 * Enter a parse tree produced by {@link SetCalcVisitorsParser#elem}.
	 * @param ctx the parse tree
	 */
	void enterElem(SetCalcVisitorsParser.ElemContext ctx);
	/**
	 * Exit a parse tree produced by {@link SetCalcVisitorsParser#elem}.
	 * @param ctx the parse tree
	 */
	void exitElem(SetCalcVisitorsParser.ElemContext ctx);
}