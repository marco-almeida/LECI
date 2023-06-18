// Generated from Quiz.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QuizParser}.
 */
public interface QuizListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QuizParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(QuizParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuizParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(QuizParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuizParser#quiz}.
	 * @param ctx the parse tree
	 */
	void enterQuiz(QuizParser.QuizContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuizParser#quiz}.
	 * @param ctx the parse tree
	 */
	void exitQuiz(QuizParser.QuizContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuizParser#quizHeader}.
	 * @param ctx the parse tree
	 */
	void enterQuizHeader(QuizParser.QuizHeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuizParser#quizHeader}.
	 * @param ctx the parse tree
	 */
	void exitQuizHeader(QuizParser.QuizHeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuizParser#answer}.
	 * @param ctx the parse tree
	 */
	void enterAnswer(QuizParser.AnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuizParser#answer}.
	 * @param ctx the parse tree
	 */
	void exitAnswer(QuizParser.AnswerContext ctx);
}