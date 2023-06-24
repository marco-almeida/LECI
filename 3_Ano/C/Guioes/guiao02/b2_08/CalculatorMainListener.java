// Generated from CalculatorMain.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalculatorMainParser}.
 */
public interface CalculatorMainListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CalculatorMainParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CalculatorMainParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorMainParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CalculatorMainParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorMainParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(CalculatorMainParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorMainParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(CalculatorMainParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorMainParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(CalculatorMainParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorMainParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(CalculatorMainParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link CalculatorMainParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprAddSub(CalculatorMainParser.ExprAddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link CalculatorMainParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprAddSub(CalculatorMainParser.ExprAddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link CalculatorMainParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprParent(CalculatorMainParser.ExprParentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link CalculatorMainParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprParent(CalculatorMainParser.ExprParentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprInteger}
	 * labeled alternative in {@link CalculatorMainParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprInteger(CalculatorMainParser.ExprIntegerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprInteger}
	 * labeled alternative in {@link CalculatorMainParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprInteger(CalculatorMainParser.ExprIntegerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link CalculatorMainParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprID(CalculatorMainParser.ExprIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link CalculatorMainParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprID(CalculatorMainParser.ExprIDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprPosNeg}
	 * labeled alternative in {@link CalculatorMainParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprPosNeg(CalculatorMainParser.ExprPosNegContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprPosNeg}
	 * labeled alternative in {@link CalculatorMainParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprPosNeg(CalculatorMainParser.ExprPosNegContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprMultDivMod}
	 * labeled alternative in {@link CalculatorMainParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprMultDivMod(CalculatorMainParser.ExprMultDivModContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprMultDivMod}
	 * labeled alternative in {@link CalculatorMainParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprMultDivMod(CalculatorMainParser.ExprMultDivModContext ctx);
}