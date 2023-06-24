// Generated from SetCalcVisitors.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SetCalcVisitorsParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SetCalcVisitorsVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SetCalcVisitorsParser#main}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain(SetCalcVisitorsParser.MainContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetCalcVisitorsParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(SetCalcVisitorsParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprVar}
	 * labeled alternative in {@link SetCalcVisitorsParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprVar(SetCalcVisitorsParser.ExprVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprParen}
	 * labeled alternative in {@link SetCalcVisitorsParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParen(SetCalcVisitorsParser.ExprParenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprSubtract}
	 * labeled alternative in {@link SetCalcVisitorsParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSubtract(SetCalcVisitorsParser.ExprSubtractContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprUnion}
	 * labeled alternative in {@link SetCalcVisitorsParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprUnion(SetCalcVisitorsParser.ExprUnionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAssign}
	 * labeled alternative in {@link SetCalcVisitorsParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAssign(SetCalcVisitorsParser.ExprAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprIntersect}
	 * labeled alternative in {@link SetCalcVisitorsParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprIntersect(SetCalcVisitorsParser.ExprIntersectContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprSet}
	 * labeled alternative in {@link SetCalcVisitorsParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSet(SetCalcVisitorsParser.ExprSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetCalcVisitorsParser#set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet(SetCalcVisitorsParser.SetContext ctx);
	/**
	 * Visit a parse tree produced by {@link SetCalcVisitorsParser#elem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElem(SetCalcVisitorsParser.ElemContext ctx);
}