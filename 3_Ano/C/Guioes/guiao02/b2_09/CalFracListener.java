// Generated from CalFrac.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalFracParser}.
 */
public interface CalFracListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CalFracParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CalFracParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalFracParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CalFracParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatPrint}
	 * labeled alternative in {@link CalFracParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatPrint(CalFracParser.StatPrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatPrint}
	 * labeled alternative in {@link CalFracParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatPrint(CalFracParser.StatPrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatAssign}
	 * labeled alternative in {@link CalFracParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatAssign(CalFracParser.StatAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatAssign}
	 * labeled alternative in {@link CalFracParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatAssign(CalFracParser.StatAssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalFracParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(CalFracParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalFracParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(CalFracParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalFracParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(CalFracParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalFracParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(CalFracParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link CalFracParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprAddSub(CalFracParser.ExprAddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link CalFracParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprAddSub(CalFracParser.ExprAddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpExponent}
	 * labeled alternative in {@link CalFracParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpExponent(CalFracParser.ExpExponentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpExponent}
	 * labeled alternative in {@link CalFracParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpExponent(CalFracParser.ExpExponentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprRead}
	 * labeled alternative in {@link CalFracParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprRead(CalFracParser.ExprReadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprRead}
	 * labeled alternative in {@link CalFracParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprRead(CalFracParser.ExprReadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link CalFracParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprParent(CalFracParser.ExprParentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link CalFracParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprParent(CalFracParser.ExprParentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprMultDiv}
	 * labeled alternative in {@link CalFracParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprMultDiv(CalFracParser.ExprMultDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprMultDiv}
	 * labeled alternative in {@link CalFracParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprMultDiv(CalFracParser.ExprMultDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprInteger}
	 * labeled alternative in {@link CalFracParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprInteger(CalFracParser.ExprIntegerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprInteger}
	 * labeled alternative in {@link CalFracParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprInteger(CalFracParser.ExprIntegerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprReduce}
	 * labeled alternative in {@link CalFracParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprReduce(CalFracParser.ExprReduceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprReduce}
	 * labeled alternative in {@link CalFracParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprReduce(CalFracParser.ExprReduceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link CalFracParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprID(CalFracParser.ExprIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link CalFracParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprID(CalFracParser.ExprIDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprFraction}
	 * labeled alternative in {@link CalFracParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprFraction(CalFracParser.ExprFractionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprFraction}
	 * labeled alternative in {@link CalFracParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprFraction(CalFracParser.ExprFractionContext ctx);
}