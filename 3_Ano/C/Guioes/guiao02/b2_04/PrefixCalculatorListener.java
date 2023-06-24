// Generated from PrefixCalculator.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PrefixCalculatorParser}.
 */
public interface PrefixCalculatorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PrefixCalculatorParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(PrefixCalculatorParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrefixCalculatorParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(PrefixCalculatorParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link PrefixCalculatorParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(PrefixCalculatorParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link PrefixCalculatorParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(PrefixCalculatorParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprPrefix}
	 * labeled alternative in {@link PrefixCalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprPrefix(PrefixCalculatorParser.ExprPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprPrefix}
	 * labeled alternative in {@link PrefixCalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprPrefix(PrefixCalculatorParser.ExprPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprNumber}
	 * labeled alternative in {@link PrefixCalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprNumber(PrefixCalculatorParser.ExprNumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprNumber}
	 * labeled alternative in {@link PrefixCalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprNumber(PrefixCalculatorParser.ExprNumberContext ctx);
}