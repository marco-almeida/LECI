// Generated from Quiz.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QuizParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QuizVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QuizParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(QuizParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuizParser#quiz}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuiz(QuizParser.QuizContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuizParser#quizHeader}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuizHeader(QuizParser.QuizHeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link QuizParser#answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswer(QuizParser.AnswerContext ctx);
}