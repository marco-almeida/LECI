// Generated from Dict.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DictParser}.
 */
public interface DictListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DictParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(DictParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link DictParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(DictParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link DictParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(DictParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link DictParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(DictParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link DictParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(DictParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link DictParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(DictParser.ExprContext ctx);
}