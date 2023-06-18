import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import org.antlr.v4.runtime.tree.TerminalNode;
import error.*;

@SuppressWarnings("CheckReturnValue")
public class AdvSemantic extends AdvBaseVisitor<Boolean> {
	protected static final Set<String> alphabet = new HashSet<String>();
	protected static final Map<String, Type> declared = new HashMap<String, Type>();

	@Override
	public Boolean visitAutomaton(AdvParser.AutomatonContext ctx) {
		// Check if automaton is complete, and if so, verify that all states have
		// transitions for all symbols in the alphabet.
		checkAutomatonCompleteness(ctx);
		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPlay(AdvParser.PlayContext ctx) {
		String varname = ctx.NAME().getText();
		if (declared.containsKey(varname)) {
			if (declared.get(varname).equals(animation)) {
				return true;
			} else {
				ErrorHandling.printError(ctx, "Play statement must be used with animation type");
				return false;
			}
		} else {
			ErrorHandling.printError(ctx, "Variable " + varname + " not declared");
			return false;
		}
	}

	@Override
	public Boolean visitDecl_animation(AdvParser.Decl_animationContext ctx) {
		String varname = ctx.NAME().getText();
		if(states.size()!=0){
			ErrorHandling.printError(ctx, "Declared state " + states + " must be used in placed");
		}
		if (declared.containsKey(varname)) {
			ErrorHandling.printError(ctx, "Variable " + varname + " already declared");
			return false;
		} else {
			declared.put(varname, animation);
		}
		// Terminal node
		return true;
	}

	@Override
	public Boolean visitViewport_description(AdvParser.Viewport_descriptionContext ctx) {
		String vpname = ctx.NAME().getText();
		if (declared.containsKey(vpname)) {
			if (declared.get(vpname).equals(viewport)) {
				return true;
			} else {
				ErrorHandling.printError(ctx, "Viewport description must be used with viewport type");
				return false;
			}
		} else {
			ErrorHandling.printError(ctx, "Variable " + vpname + " not declared");
			return false;
		}
	}

	@Override
	public Boolean visitShowables(AdvParser.ShowablesContext ctx) {
		if (ctx.NAME() != null) {
			String varname = ctx.NAME().getText();
			if (declared.containsKey(varname)) {
				if (declared.get(varname).equals(state) || declared.get(varname).equals(grid)
						|| declared.get(varname).equals(loop)) {
					return true;
				} else {
					ErrorHandling.printError(ctx, "Show statement must be used with state or grid type");
					return false;
				}
			} else {
				ErrorHandling.printError(ctx, "Variable " + varname + " not declared");
				return false;
			}
		} else {
			return visitChildren(ctx);
		}
	}

	@Override
	public Boolean visitViewport(AdvParser.ViewportContext ctx) {
		String vpName = ctx.vpname.getText();
		String viewName = ctx.viewname.getText();
		if (declared.containsKey(vpName)) {
			ErrorHandling.printError(ctx, "Variable " + vpName + " already declared");
			return false;
		} else {
			if (declared.containsKey(viewName)) {
				if (declared.get(viewName).equals(view)) {
					declared.put(vpName, viewport);
					return true;
				} else {
					ErrorHandling.printError(ctx, "Viewport statement must be used with view type");
					return false;
				}
			} else {
				ErrorHandling.printError(ctx, "Variable " + viewName + " not declared");
				return false;
			}
		}

	}

	@Override
	public Boolean visitGrid_statement(AdvParser.Grid_statementContext ctx) {
		// Check if grid has already been declared
		String varname = ctx.NAME().getText();
		if (declared.containsKey(varname)) {
			ErrorHandling.printError(ctx, "Variable " + varname + " already declared");
			return false;
		} else {
			declared.put(varname, grid);
		}
		// Check if grid margin is smaller than step
		Double gridMargin = 0d;
		Double gridStep = 0d;
		for (AdvParser.Key_valueContext childCtx : ctx.keypair_properties().key_values().key_value()) {
			if (childCtx.key.getText().equals("margin")) {
				gridMargin = Double.parseDouble(childCtx.value.getText());
			}
			if (childCtx.key.getText().equals("step")) {
				gridStep = Double.parseDouble(childCtx.value.getText());
			}
		}
		if (gridMargin >= gridStep) {
			ErrorHandling.printError(ctx, "Grid margin must be smaller than step");
			return false;
		}
		return visitChildren(ctx);
	}

	@Override
	public Boolean visitKey_value(AdvParser.Key_valueContext ctx) {
		// Check if color is valid
		String[] colors = { "black", "white", "red", "green", "blue", "yellow", "cyan", "gray", "orange", "brown",
				"purple", "pink" };
		if (ctx.key.getText().equals("color")) {
			if (!Arrays.asList(colors).contains(ctx.value.getText())) {
				ErrorHandling.printError(ctx, "Invalid color");
				return false;
			}
		}
		// Check if line option is valid
		String[] lineOptions = { "solid", "dashed", "dotted" };
		if (ctx.key.getText().equals("line")) {
			if (!Arrays.asList(lineOptions).contains(ctx.value.getText())) {
				ErrorHandling.printError(ctx, "Invalid line option");
				return false;
			}
		}
		// Check if align option is valid
		String[] alignOptions = { "above", "below", "left", "right", "above left", "left above", "above right",
				"right above", "below left", "left below", "below right", "right below" };
		if (ctx.key.getText().equals("align")) {
			if (!Arrays.asList(alignOptions).contains(ctx.value.getText())) {
				ErrorHandling.printError(ctx, "Invalid align option");
				return false;
			}
		}
		return visitChildren(ctx);
	}

	@Override
	public Boolean visitMulti_point_declaration(AdvParser.Multi_point_declarationContext ctx) {
		for (TerminalNode name : ctx.NAME()) {
			String varname = name.getText();
			if (declared.containsKey(varname)) {
				ErrorHandling.printError(ctx, "Variable " + varname + " already declared");
				return false;
			} else {
				declared.put(varname, point);
			}
		}
		// There's no children to visit
		return true;
	}

	@Override
	public Boolean visitPoint_decl_assign(AdvParser.Point_decl_assignContext ctx) {
		String varname = ctx.NAME().getText();
		if (declared.containsKey(varname)) {
			ErrorHandling.printError(ctx, "Variable " + varname + " already declared");
			return false;
		} else {
			declared.put(varname, point);
			return visitChildren(ctx);
		}
	}

	@Override
	public Boolean visitPoint_assignment(AdvParser.Point_assignmentContext ctx) {
		String varname = ctx.NAME().getText();
		if (declared.containsKey(varname)) {
			if (!declared.get(varname).equals(point)) {
				ErrorHandling.printError(ctx, "Variable " + varname + " must be of type point");
				return false;
			}
		} else {
			ErrorHandling.printError(ctx, "Variable " + varname + " not declared");
			return false;
		}
		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPoint(AdvParser.PointContext ctx) {
		if (ctx.expr() != null) {
			Boolean res = visit(ctx.expr());
			if (res) {
				if (!ctx.expr().t.equals(vector) && !ctx.expr().t.equals(point)) {
					ErrorHandling.printError(ctx, "Must assign vector to variable of type point");
					return false;
				}
				return res;
			}
		}
		return visitChildren(ctx);
	}

	@Override
	public Boolean visitPlace_in(AdvParser.Place_inContext ctx) {
		if (ctx.NAME() != null) {
			String varname = ctx.NAME().getText();
			if (declared.containsKey(varname)) {
				if (declared.get(varname).equals(point)) {
					return true;
				} else {
					ErrorHandling.printError(ctx, "'at' argument must hold coordinates");
					return false;
				}
			} else {
				ErrorHandling.printError(ctx, "Variable " + varname + " not declared");
				return false;
			}
		} else {
			return visitChildren(ctx);
		}
	}

	@Override
	public Boolean visitPlaceable(AdvParser.PlaceableContext ctx) {
		if (ctx.NAME() != null) {
			String varname = ctx.NAME().getText();
			if (declared.containsKey(varname)) {
				if (declared.get(varname).equals(state)) {
					states.remove(varname);
					return true;
				} else {
					ErrorHandling.printError(ctx, "Place statement must be used with state type");
					return false;
				}
			} else {
				ErrorHandling.printError(ctx, "Variable " + varname + " not declared");
				return false;
			}
		} else {
			return visitChildren(ctx);
		}
	}

	@Override
	public Boolean visitDecl_view(AdvParser.Decl_viewContext ctx) {
		String vpname = ctx.vpname.getText();
		String automname = ctx.automname.getText();
		if (declared.containsKey(vpname)) {
			ErrorHandling.printError(ctx, "Variable " + vpname + " already declared");
			return false;
		} else {
			if (declared.containsKey(automname)) {
				if (declared.get(automname).equals(automaton)) {
					declared.put(vpname, view);
					return visitChildren(ctx);
				} else {
					ErrorHandling.printError(ctx, "View declaration must be done with automaton type");
					return false;
				}
			} else {
				ErrorHandling.printError(ctx, "Variable " + automname + " not declared");
				return false;
			}
		}
	}

	@Override
	public Boolean visitFor_loop_viewport(AdvParser.For_loop_viewportContext ctx) {
		// Create temporary loop variable
		String loopName = ctx.NAME().getText();
		declared.put(loopName, loop);
		Boolean res = null;
		for (AdvParser.Viewport_commandsContext c : ctx.viewport_commands()) {
			if (res != null) {
				res = res && visit(c);
			} else {
				res = visit(c);
			}
		}
		declared.remove(loopName);
		return res;
	}

	@Override
	public Boolean visitFor_loop_state(AdvParser.For_loop_stateContext ctx) {
		String loopName = ctx.NAME().getText();
		declared.put(loopName, loop);
		Boolean res = null;
		// for (AdvParser.Change_state_propertyContext c : ctx.change_state_property())
		// {
		// if (res != null) {
		// res = res && visit(c);
		// } else {
		// res = visit(c);
		// }
		// }
		// declared.remove(loopName);
		return res;
	}

	@Override
	public Boolean visitList(AdvParser.ListContext ctx) {
		// Check if loop elements have been declared
		for (TerminalNode name : ctx.NAME()) {
			if (!declared.containsKey(name.getText())) {
				ErrorHandling.printError(ctx, "Variable " + name.getText() + " not declared");
				return false;
			}
		}
		// Children are only TerminalNodes
		return true;
	}

	@Override
	public Boolean visitAutomaton_transition(AdvParser.Automaton_transitionContext ctx) {
		// Check if variables are states and declared
		for (TerminalNode name : ctx.NAME()) {
			String varname = name.getText();
			if (declared.containsKey(varname)) {
				if (!declared.get(varname).equals(state)) {
					ErrorHandling.printError(ctx, "Variable " + varname + " must be a state");
					return false;
				}
			} else {
				ErrorHandling.printError(ctx, "Variable " + varname + " not declared");
				return false;
			}
		}
		// Check if symbols belong to alphabet
		for (TerminalNode symbol : ctx.SYMBOL()) {
			String symname = symbol.getText();
			if (!alphabet.contains(symname)) {
				ErrorHandling.printError(ctx, "Symbol " + symname + " not declared");
				return false;
			}
		}
		if(automaton_types.contains("DFA")){
			// Check if transitions are deterministic
			List<String> atransitions = automatonTransitions.get(ctx.NAME(0).getText());
			if(!atransitions.remove(ctx.SYMBOL(0).getText())){
				ErrorHandling.printError(ctx, "Transition is not deterministic");
				return false;
			}
		}
		// Only has TerminalNodes
		return true;
	}

	@Override
	public Boolean visitChange_state_property(AdvParser.Change_state_propertyContext ctx) {
		String varname = ctx.NAME().getText();
		if (declared.containsKey(varname)) {
			if (!declared.get(varname).equals(state) && !declared.get(varname).equals(loop)) {
				ErrorHandling.printError(ctx, "Variable " + varname + " must be a state");
				return false;
			}
		} else {
			ErrorHandling.printError(ctx, "Variable " + varname + " not declared");
			return false;
		}
		if (ctx.key.getText().equals("initial") && ctx.value.getText().equals("true")) {
			initialStates++;
			if (initialStates > 1) {
				ErrorHandling.printError(ctx, "Only one initial state allowed");
				return false;
			}
		}
		// Only has TerminalNodes
		return true;
	}

	@Override
	public Boolean visitState_transitions(AdvParser.State_transitionsContext ctx) {
		if (initialStates == 0) {
			ErrorHandling.printError(ctx, "Must have an intial state");
			return false;
		}
		return visitChildren(ctx);
	}

	@Override
	public Boolean visitDeclare_states(AdvParser.Declare_statesContext ctx) {
		for (TerminalNode name : ctx.NAME()) {
			String varname = name.getText();
			if (declared.containsKey(varname)) {
				ErrorHandling.printError(ctx, "Variable " + varname + " already declared");
				return false;
			} else {
				declared.put(varname, state);
				states.add(varname);
				List<String> b = new ArrayList<String>();

				for(String a: alphabet){
					for(int i = 0; i < states.size(); i++){
						if(b.contains(a)){
							break;
						}else{
							b.add(i,a);
						}
					}
					automatonTransitions.put(varname, b);

				}
			}
		}
		
		return true;
	}

	@Override
	public Boolean visitDecl_automaton(AdvParser.Decl_automatonContext ctx) {
		// Check if automaton name is already declared
		String automname = ctx.NAME().getText();
		if (declared.containsKey(automname)) {
			ErrorHandling.printError(ctx, "Variable " + automname + " already declared");
			return false;
		} else {
			declared.put(automname, automaton);
			automaton_types = ctx.automaton_types().getText();
		}
		return visitChildren(ctx);

	}

	@Override
	public Boolean visitAlphabet(AdvParser.AlphabetContext ctx) {
		for (TerminalNode symbol : ctx.SYMBOL()) {
			String sym = symbol.getText();
			if (alphabet.contains(sym)) {
				ErrorHandling.printError(ctx, "Symbol " + sym + " already in alphabet");
				return false;
			} else {
				alphabet.add(sym);
			}
		}
		return true;
	}

	@Override
	public Boolean visitTransition(AdvParser.TransitionContext ctx) {
		for (TerminalNode name : ctx.NAME()) {
			String varname = name.getText();
			if (!declared.containsKey(varname)) {
				ErrorHandling.printError(ctx, "Variable " + varname + " must be declared before use");
				return false;
			}
		}
		return true;
	}

	@Override
	public Boolean visitVector(AdvParser.VectorContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Boolean visitCart_vector(AdvParser.Cart_vectorContext ctx) {
		Boolean res = visit(ctx.expr(0)) && visit(ctx.expr(1));
		if (res) {
			Type t1 = ctx.expr(0).t;
			Type t2 = ctx.expr(1).t;
			if (!t1.equals(t2) || !t1.equals(number)) {
				ErrorHandling.printError(ctx, "Cannot create cartesian vector with non numeric expressions");
				return false;
			}
		}
		return res;
	}

	@Override
	public Boolean visitPolar_vector(AdvParser.Polar_vectorContext ctx) {
		Boolean res = visit(ctx.expr(0)) && visit(ctx.expr(1));
		if (res) {
			Type t1 = ctx.expr(0).t;
			Type t2 = ctx.expr(1).t;
			if (!t1.equals(t2) || !t1.equals(number)) {
				ErrorHandling.printError(ctx, "Cannot create polar vector with non numeric expressions");
				return false;
			}
		}
		return res;
	}

	@Override
	public Boolean visitExprParenthesis(AdvParser.ExprParenthesisContext ctx) {
		Boolean res = visit(ctx.expr());
		if (res)
			ctx.t = ctx.expr().t;
		return res;
	}

	@Override
	public Boolean visitExprName(AdvParser.ExprNameContext ctx) {
		String varname = ctx.NAME().getText();
		if (!declared.containsKey(varname)) {
			ErrorHandling.printError(ctx, "Variable " + varname + " must be declared before use");
			return false;
		}
		if (declared.containsKey(ctx.NAME().getText()))
			ctx.t = declared.get(ctx.NAME().getText());
		else {
			ErrorHandling.printError(ctx, "Use of undeclared identifier: " + ctx.NAME().getText());
			return false;
		}
		return true;
	}

	@Override
	public Boolean visitExprPositiveNegative(AdvParser.ExprPositiveNegativeContext ctx) {
		Boolean res = visit(ctx.expr());
		if (res) {
			ctx.t = ctx.expr().t;
			if (!ctx.t.equals(vector) && !ctx.t.equals(number)) {
				ErrorHandling.printError(ctx, "Non numeric or vectorial expression cannot be negated");
				return false;
			}
		}
		return res;
	}

	@Override
	public Boolean visitExprMulDiv(AdvParser.ExprMulDivContext ctx) {
		Boolean res = visit(ctx.expr(0)) && visit(ctx.expr(1));
		if (res) {
			Type t1 = ctx.expr(0).t;
			Type t2 = ctx.expr(1).t;
			// System.out.println(t1 + " " + t2);
			ctx.t = typeConversion(t1, t2);
		}
		return res;
	}

	@Override
	public Boolean visitExprAddSub(AdvParser.ExprAddSubContext ctx) {
		Boolean res = visit(ctx.expr(0)) && visit(ctx.expr(1));
		if (res) {
			Type t1 = ctx.expr(0).t;
			Type t2 = ctx.expr(1).t;
			ctx.t = typeConversion(t1, t2);
		}
		return res;
	}

	@Override
	public Boolean visitExprVector(AdvParser.ExprVectorContext ctx) {
		ctx.t = vector;
		return visitChildren(ctx);
	}

	@Override
	public Boolean visitExprNumber(AdvParser.ExprNumberContext ctx) {
		ctx.t = number;
		return true;
	}

	public Type typeConversion(Type t1, Type t2) {
		if (t1.equals(state)) {
			return t2;
		}
		if (t2.equals(state)) {
			return t1;
		}
		if (t1.equals(vector) && t2.equals(number)) {
			return vector;
		}
		if (t2.equals(vector) && t1.equals(number)) {
			return vector;
		}
		if (t1.equals(number) && t2.equals(number)) {
			return number;
		}
		return t1;
	}

	public Boolean checkAutomatonCompleteness(AdvParser.AutomatonContext ctx) {
		if (ctx.decl_automaton().automaton_types().getText().equals("completeDFA")) {
			HashMap<String, List<String>> transitions = new HashMap<String, List<String>>();
			// Populate the map with all states and all symbols in the alphabet
			for (AdvParser.Declare_statesContext state : ctx.automaton_body().declare_states()) {
				for (TerminalNode name : state.NAME()) {
					ArrayList<String> temp = new ArrayList<String>();
					for (String symbol : alphabet) {
						temp.add(symbol);
					}
					transitions.put(name.getText(), temp);
				}
			}
			// Remove transitions from the map if they are defined in the automaton
			for (AdvParser.Automaton_transitionContext transition : ctx.automaton_body().state_transitions()
					.automaton_transition()) {
				String from = transition.NAME(0).getText();
				List<TerminalNode> symbols = transition.SYMBOL();
				for (TerminalNode symbol : symbols) {
					transitions.get(from).remove(symbol.getText());
				}
			}
			// Check if there are any transitions left in the map
			for (String state : transitions.keySet()) {
				if (!transitions.get(state).isEmpty()) {
					ErrorHandling.printError(ctx, "Automaton is not complete");
					return false;
				}
			}
		}
		return true;
	}

	protected static final Type polar = new Type("polar");
	protected static final Type cart = new Type("cart");
	protected static final Type animation = new Type("animation");
	protected static final Type state = new Type("state");
	protected static final Type grid = new Type("grid");
	protected static final Type loop = new Type("loop");
	protected static final Type viewport = new Type("viewport");
	protected static final Type view = new Type("view");
	protected static final Type automaton = new Type("automaton");
	protected static final Type point = new Type("point");
	protected static final Type vector = new Type("vector");
	protected static final Type number = new Type("number");
	protected Set<String> states = new HashSet<String>(); //stores states and removes them when called in "place"
	protected String automaton_types = new String(); //stores the type of automaton
	private int initialStates = 0; // stores the number of intial states
	protected Map<String,List<String>> automatonTransitions = new HashMap<String,List<String>>();

}