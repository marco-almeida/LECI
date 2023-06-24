// Generated from Hello.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HelloParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HelloVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HelloParser#top}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTop(HelloParser.TopContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#bye}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBye(HelloParser.ByeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#greetings}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreetings(HelloParser.GreetingsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(HelloParser.NameContext ctx);
}