import org.stringtemplate.v4.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings("CheckReturnValue")
public class AdvCompiler extends AdvBaseVisitor<ST> {
	protected STGroup stg = null;
	protected String target = "adv_output";
	protected ST result = null;
	protected Map<String, Point> stateCoords = new HashMap<>();
	protected Map<String, Point> pointCoords = new HashMap<>();
	protected Set<String> grids = new HashSet<>();

	public boolean validTarget(String target) {
		File f = new File(target + ".stg");
		return "adv_output".equalsIgnoreCase(target) && f.exists() && f.isFile() && f.canRead();
	}

	@Override
	public ST visitProgram(AdvParser.ProgramContext ctx) {
		assert validTarget(target);
		stg = new STGroupFile(target + ".stg");
		result = stg.getInstanceOf("module");
		result.add("stat", visit(ctx.alphabet()).render());
		Iterator<AdvParser.StructsContext> it = ctx.structs().iterator();
		while (it.hasNext()) {
			ST tmp = visit(it.next());
			if (tmp != null) {
				String output = tmp.render();
				System.out.println("not null " + output);
				result.add("stat", output);
			}
		}
		return result;
	}

	@Override
	public ST visitStructs(AdvParser.StructsContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public ST visitPlay(AdvParser.PlayContext ctx) {
		ST template = stg.getInstanceOf("play");
		template.add("animation_name", ctx.NAME());
		result.add("stat", template.render());
		return visitChildren(ctx);
	}

	@Override
	public ST visitAnimation(AdvParser.AnimationContext ctx) {
		String animationName = visit(ctx.decl_animation()).render();
		ST template = stg.getInstanceOf("declare_animation");
		template.add("animation_name", animationName);
		result.add("stat", template.render());
		return visitChildren(ctx);
	}

	@Override
	public ST visitDecl_animation(AdvParser.Decl_animationContext ctx) {
		ST res = new ST("<decl_animation>");
		res.add("decl_animation", ctx.NAME());
		return res;
	}

	@Override
	public ST visitAnimation_body(AdvParser.Animation_bodyContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public ST visitViewport_description(AdvParser.Viewport_descriptionContext ctx) {
		String viewportName = ctx.NAME().getText();
		ST template = stg.getInstanceOf("declare_animation_viewport");
		template.add("viewport_name", viewportName);
		result.add("stat", template.render());

		return visitChildren(ctx);
	}

	@Override
	public ST visitViewport_commands(AdvParser.Viewport_commandsContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public ST visitPause(AdvParser.PauseContext ctx) {
		ST template = stg.getInstanceOf("pause");
		result.add("stat", template.render());
		return visitChildren(ctx);
	}

	@Override
	public ST visitShow_command(AdvParser.Show_commandContext ctx) {
		visitChildren(ctx);
		ST template = stg.getInstanceOf("post_show_command");
		result.add("stat", template.render());
		return null;
	}

	public void showStuff(String showableName, String key, String value) {
		String name = showableName;
		ST template = null;
		if (name.contains("<")) {
			// remove '<' and '>'
			String state1 = name.split(",")[0].substring(1);
			String state2 = name.split(",")[1];
			state2 = state2.substring(0, state2.length() - 1);
			if (!state1.equals(state2)) {
				// line transition
				template = stg.getInstanceOf("animation_show_line_transition");
				template.add("full_name", name);
				template.add("state1", state1);
				template.add("state2", state2);
			} else {
				// loop transition
				template = stg.getInstanceOf("animation_show_loop_transition");
				template.add("state", state1);
			}
			result.add("stat", template.render());
		}

		if (key != null) {
			template = stg.getInstanceOf("animation_show_state_properties");
			template.add("state_name", name);
			template.add("key", key);
			template.add("value", value.substring(0, 1).toUpperCase() + value.substring(1));
			result.add("stat", template.render());
		}
		template = stg.getInstanceOf("animation_show_state");
		template.add("state_name", name);
		result.add("stat", template.render());
		template = stg.getInstanceOf("post_show_command");
		result.add("stat", template.render());
	}

	@Override
	public ST visitShowables(AdvParser.ShowablesContext ctx) {
		String name = "";
		ST template = null;
		if (ctx.NAME() != null) {
			name = ctx.NAME().getText();
			if (grids.contains(name)) {
				template = stg.getInstanceOf("animation_show_grid");
				template.add("grid_name", name);
				result.add("stat", template.render());
				return visitChildren(ctx);
			}
		} else {
			name = visit(ctx.transition()).render();
			// remove '<' and '>'
			String state1 = name.split(",")[0].substring(1);
			String state2 = name.split(",")[1];
			state2 = state2.substring(0, state2.length() - 1);
			if (!state1.equals(state2)) {
				// line transition
				template = stg.getInstanceOf("animation_show_line_transition");
				template.add("full_name", name);
				template.add("state1", state1);
				template.add("state2", state2);
			} else {
				// loop transition
				template = stg.getInstanceOf("animation_show_loop_transition");
				template.add("state", state1);
			}
			result.add("stat", template.render());
		}
		String keyName = "";
		String keyValue = "";
		if (ctx.key != null) {
			template = stg.getInstanceOf("animation_show_state_properties");
			keyName = ctx.key.getText();
			keyValue = ctx.value.getText();
			template.add("state_name", name);
			template.add("key", keyName);
			template.add("value", keyValue.substring(0, 1).toUpperCase() + keyValue.substring(1));
			result.add("stat", template.render());
		}
		template = stg.getInstanceOf("animation_show_state");
		template.add("state_name", name);
		result.add("stat", template.render());
		return visitChildren(ctx);
	}

	@Override
	public ST visitViewport(AdvParser.ViewportContext ctx) {
		String viewportName = ctx.NAME(0).getText();
		String viewName = ctx.NAME(1).getText();
		String coords = visit(ctx.cart_vector(0)).render();
		String coordsX = coords.split(",")[0];
		String coordsY = coords.split(",")[1];
		String size = visit(ctx.cart_vector(1)).render();
		String width = size.split(",")[0];
		String height = size.split(",")[1];
		ST template = stg.getInstanceOf("declare_viewport");
		template.add("viewport_name", viewportName);
		template.add("view_name", viewName);
		template.add("x", coordsX);
		template.add("y", coordsY);
		template.add("width", width);
		template.add("height", height);
		result.add("stat", template.render());
		return template;
	}

	@Override
	public ST visitView(AdvParser.ViewContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public ST visitView_body(AdvParser.View_bodyContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public ST visitGrid_statement(AdvParser.Grid_statementContext ctx) {
		String width = ctx.width.getText();
		String height = ctx.height.getText();
		String gridName = ctx.NAME().getText();
		String keypairValues = visit(ctx.keypair_properties()).render();
		Map<String, String> map = new HashMap<>();
		String content = keypairValues.substring(1, keypairValues.length() - 1);
		String[] pairs = content.split(",");
		for (String pair : pairs) {
			String[] keyValue = pair.split("=");
			if (keyValue.length == 2) {
				String key = keyValue[0];
				String value = keyValue[1];
				map.put(key, value);
			}
		}
		grids.add(gridName);
		ST template = stg.getInstanceOf("declare_grid");
		template.add("grid_name", gridName);
		template.add("width", width);
		template.add("height", height);
		template.add("color", map.getOrDefault("color", "black"));
		template.add("margin", map.getOrDefault("margin", "0.25"));
		template.add("step", map.getOrDefault("step", "0.5"));
		template.add("line_type", map.getOrDefault("line", "solid"));
		result.add("stat", template.render());
		return template;
	}

	@Override
	public ST visitTransition_arrow_statement(AdvParser.Transition_arrow_statementContext ctx) {
		ST template = stg.getInstanceOf("redefine_transition");
		for (AdvParser.PointContext point : ctx.point()) {
			String coords = visit(point).render();
			String x = coords.split(",")[0];
			String y = coords.split(",")[1];
			Point p = new Point(Double.parseDouble(x), Double.parseDouble(y));
			template.add("points", p);
		}
		String transitionName = visit(ctx.transition()).render();
		String state1 = transitionName.split(",")[0].substring(1);
		String state2 = transitionName.split(",")[1];
		template.add("transition_name", transitionName);
		template.add("is_loop", state1.equals(state2));
		result.add("stat", template.render());
		return result;
	}

	@Override
	public ST visitMulti_point_declaration(AdvParser.Multi_point_declarationContext ctx) {
		for (TerminalNode name : ctx.NAME()) {
			pointCoords.put(name.getText(), null);
		}
		return null;
	}

	@Override
	public ST visitPoint_assignment(AdvParser.Point_assignmentContext ctx) {
		String pointName = ctx.NAME().getText();
		String coords = visit(ctx.point()).render();
		String x = coords.split(",")[0];
		String y = coords.split(",")[1];
		pointCoords.put(pointName, new Point(Double.parseDouble(x), Double.parseDouble(y)));
		return visitChildren(ctx);
	}

	@Override
	public ST visitPoint_decl_assign(AdvParser.Point_decl_assignContext ctx) {
		String pointName = ctx.NAME().getText();
		String coords = visit(ctx.point()).render();
		String x = coords.split(",")[0];
		String y = coords.split(",")[1];
		pointCoords.put(pointName, new Point(Double.parseDouble(x), Double.parseDouble(y)));
		return visitChildren(ctx);
	}

	@Override
	public ST visitPoint(AdvParser.PointContext ctx) {
		ST res = new ST("<n1>,<n2>");
		Point p = null;
		if (ctx.state != null) {
			p = stateCoords.get(ctx.state.getText());
		} else if (ctx.vector() != null) {
			String x = visit(ctx.vector()).render().split(",")[0];
			String y = visit(ctx.vector()).render().split(",")[1];
			p = new Point(Double.parseDouble(x), Double.parseDouble(y));
		} else {
			// ctx.expr() != null
			String expr = visit(ctx.expr()).render();
			String x = expr.split(",")[0];
			String y = expr.split(",")[1];
			p = new Point(Double.parseDouble(x), Double.parseDouble(y));
		}
		res.add("n1", Double.toString(p.x));
		res.add("n2", Double.toString(p.y));
		return res;
	}

	@Override
	public ST visitPlace_statement(AdvParser.Place_statementContext ctx) {
		List<AdvParser.PlaceableContext> placeables = ctx.placeable();
		List<AdvParser.Place_inContext> placeIns = ctx.place_in();
		for (int i = 0; i < placeables.size(); i++) {
			String placeable = visit(placeables.get(i)).render();
			boolean isTransitionLabel = placeable.contains("<");
			String placeIn = visit(placeIns.get(i)).render();
			Point p = null;
			if (placeIn.matches("-?\\d+(\\.\\d+)?,-?\\d+(\\.\\d+)?")) {
				p = new Point(Double.parseDouble(placeIn.split(",")[0]),
						Double.parseDouble(placeIn.split(",")[1]));
				if (!isTransitionLabel) {
					stateCoords.put(placeable, p);
				}
			} else {
				p = pointCoords.get(placeIn);
			}
			ST template;
			if (isTransitionLabel) {
				template = stg.getInstanceOf("place_label");
				String transitionName = placeable.substring(0, placeable.indexOf("["));
				template.add("transition_name", transitionName);
				template.add("point", p);
				String alignValue = placeable.substring(placeable.indexOf("align=") + 6, placeable.indexOf("]"));
				template.add("alignment", alignValue);
			} else {
				template = stg.getInstanceOf("place_state");
				template.add("state_name", placeable);
				template.add("x", Double.toString(p.x));
				template.add("y", Double.toString(p.y));
			}
			result.add("stat", template.render());
		}
		return null;
	}

	@Override
	public ST visitPlace_in(AdvParser.Place_inContext ctx) {
		if (ctx.NAME() != null) {
			Point p = pointCoords.get(ctx.NAME().getText());
			return new ST("<n1>,<n2>").add("n1", Double.toString(p.x)).add("n2", Double.toString(p.y));
		}
		return visit(ctx.vector());
	}

	@Override
	public ST visitPlaceable(AdvParser.PlaceableContext ctx) {
		if (ctx.NAME() != null) {
			return new ST("<state_name>").add("state_name", ctx.NAME().getText());
		}
		String fullTransition = visit(ctx.view_transition()).render();
		return new ST("<transition>").add("transition", fullTransition);
	}

	@Override
	public ST visitKeypair_properties(AdvParser.Keypair_propertiesContext ctx) {
		return visit(ctx.key_values());
	}

	@Override
	public ST visitKey_values(AdvParser.Key_valuesContext ctx) {
		ST template = new ST("[<key_values; separator=\",\">]");
		for (AdvParser.Key_valueContext key_value : ctx.key_value()) {
			template.add("key_values", visit(key_value).render());
		}
		return template;
	}

	@Override
	public ST visitKey_value(AdvParser.Key_valueContext ctx) {
		String key = ctx.key.getText();
		StringBuilder values = new StringBuilder();
		for (AdvParser.Keypair_valueContext value : ctx.keypair_value()) {
			values.append(visit(value).render()).append(" ");
		}
		values.deleteCharAt(values.length() - 1);
		ST template = new ST("<key>=<values>");
		template.add("key", key);
		template.add("values", values.toString());
		return template;
	}

	@Override
	public ST visitKeypair_value(AdvParser.Keypair_valueContext ctx) {
		return new ST("<value>").add("value", ctx.getText());
	}

	@Override
	public ST visitView_transition(AdvParser.View_transitionContext ctx) {
		String transition = visit(ctx.transition()).render();
		String keypairProperties = visit(ctx.keypair_properties()).render();
		ST extra = stg.getInstanceOf("declare_label_alignment");
		extra.add("transition_name", transition);
		extra.add("alignment_value", keypairProperties.substring(keypairProperties.indexOf("align=") + 6,
				keypairProperties.indexOf("]")));
		result.add("stat", extra.render());
		ST template = new ST("<transition><keypair_properties>");
		template.add("transition", transition);
		template.add("keypair_properties", keypairProperties);
		return template;
	}

	@Override
	public ST visitDecl_view(AdvParser.Decl_viewContext ctx) {
		String viewName = ctx.NAME(0).getText();
		String automatonName = ctx.NAME(1).getText();
		ST template = stg.getInstanceOf("declare_view");
		template.add("view_name", viewName);
		template.add("automaton_name", automatonName);
		result.add("stat", template.render());
		return template;
	}

	@Override
	public ST visitAutomaton(AdvParser.AutomatonContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public ST visitAutomaton_body(AdvParser.Automaton_bodyContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public ST visitFor_loop_viewport(AdvParser.For_loop_viewportContext ctx) {
		String[] list = visit(ctx.list()).render().split(",");
		String elem = ctx.elem.getText();
		for (String s : list) {
			for (AdvParser.Viewport_commandsContext a : ctx.viewport_commands()) {
				if (a.show_command() != null) {
					for (AdvParser.ShowablesContext showable : a.show_command().showables()) {
						String subject = showable.NAME().getText();
						String key = showable.key != null ? showable.key.getText() : null;
						String value = showable.value != null ? showable.value.getText() : null;
						if (subject.equals(elem)) {
							showStuff(s, key, value);
						}
					}
				} else if (a.pause() != null) {
					result.add("stat", stg.getInstanceOf("pause").render());
				}
			}
		}
		return null;
	}

	@Override
	public ST visitAutomaton_commands(AdvParser.Automaton_commandsContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public ST visitFor_loop_state(AdvParser.For_loop_stateContext ctx) {
		String[] list = visit(ctx.list()).render().split(",");
		String elem = ctx.elem.getText();
		for (String s : list) {
			for (AdvParser.Automaton_commandsContext a : ctx.automaton_commands()) {
				if (a.automaton_transition() != null) {
					String firstFound = a.automaton_transition().NAME(0).getText();
					String s1 = firstFound.equals(elem) ? s : firstFound;
					String secondFound = a.automaton_transition().NAME(1).getText();
					String s2 = secondFound.equals(elem) ? s : secondFound;
					List<TerminalNode> symbols = a.automaton_transition().SYMBOL();
					List<String> finalList = new ArrayList<>();
					for (TerminalNode t : symbols) {
						String symbol = t.getText();
						finalList.add(symbol.equals(elem) ? s : symbol);
					}
					automatonTransition(s1, s2, finalList);
				} else if (a.change_state_property() != null) {
					changeStateProperty(s, a.change_state_property().key.getText(),
							a.change_state_property().value.getText());
				} else if (a.declare_states() != null) {
					declareState(s);
				}
			}
		}
		return null;
	}

	@Override
	public ST visitList(AdvParser.ListContext ctx) {
		ST template = new ST("<list; separator=\",\">");
		for (TerminalNode t : ctx.NAME()) {
			template.add("list", t.getText());
		}
		return template;
	}

	public void automatonTransition(String from, String to, List<String> symbol) {
		ST template = stg.getInstanceOf("declare_transition");
		for (String s : symbol) {
			template.add("from", from);
			template.add("to", to);
			template.add("symbol", s);
			result.add("stat", template.render());
			template = stg.getInstanceOf("declare_transition");
		}
	}

	@Override
	public ST visitAutomaton_transition(AdvParser.Automaton_transitionContext ctx) {
		ST template = stg.getInstanceOf("declare_transition");
		for (TerminalNode symbol : ctx.SYMBOL()) {
			template.add("from", ctx.NAME(0).getText());
			template.add("to", ctx.NAME(1).getText());
			template.add("symbol", symbol.getText());
			result.add("stat", template.render());
			template = stg.getInstanceOf("declare_transition");
		}
		return template;
	}

	public void changeStateProperty(String stateName, String key, String value) {
		ST template = stg.getInstanceOf("change_state_property");
		template.add("state", stateName);
		template.add("key", key);
		template.add("value", value.substring(0, 1).toUpperCase() + value.substring(1));
		result.add("stat", template.render());
	}

	@Override
	public ST visitChange_state_property(AdvParser.Change_state_propertyContext ctx) {
		ST template = stg.getInstanceOf("change_state_property");
		template.add("state", ctx.NAME().getText());
		template.add("key", ctx.key.getText());
		String bool = ctx.value.getText();
		template.add("value", bool.substring(0, 1).toUpperCase() + bool.substring(1));
		result.add("stat", template.render());
		return template;
	}

	public void declareState(String stateName) {
		ST template = stg.getInstanceOf("declare_state");
		template.add("state", stateName);
		result.add("stat", template.render());
	}

	@Override
	public ST visitDeclare_states(AdvParser.Declare_statesContext ctx) {
		ST template = stg.getInstanceOf("declare_state");
		for (TerminalNode state : ctx.NAME()) {
			template.add("state", state.getText());
			result.add("stat", template.render());
			template = stg.getInstanceOf("declare_state");
		}
		return template;
	}

	@Override
	public ST visitDecl_automaton(AdvParser.Decl_automatonContext ctx) {
		String automatonName = ctx.NAME().getText();
		String type = visit(ctx.automaton_types()).render();
		ST template = stg.getInstanceOf("declare_automaton");
		template.add("name", automatonName);
		template.add("type", type);
		result.add("stat", template.render());
		return template;
	}

	@Override
	public ST visitAlphabet(AdvParser.AlphabetContext ctx) {
		ST template = stg.getInstanceOf("alphabet");
		for (TerminalNode symbol : ctx.SYMBOL()) {
			template.add("values", symbol);
		}
		return template;
	}

	@Override
	public ST visitTransition(AdvParser.TransitionContext ctx) {
		ST res = new ST("<transition>");
		res.add("transition", ctx.getText());
		return res;
	}

	@Override
	public ST visitVector(AdvParser.VectorContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public ST visitCart_vector(AdvParser.Cart_vectorContext ctx) {
		ST res = new ST("<n1>,<n2>");
		res.add("n1", visit(ctx.expr(0)).render());
		res.add("n2", visit(ctx.expr(1)).render());
		return res;
	}

	@Override
	public ST visitPolar_vector(AdvParser.Polar_vectorContext ctx) {
		ST res = new ST("<n1>,<n2>");
		Point p = Point.pointByPolar(Double.parseDouble(visit(ctx.expr(0)).render()),
				Double.parseDouble(visit(ctx.expr(1)).render()));
		res.add("n1", Double.toString(p.x));
		res.add("n2", Double.toString(p.y));
		return res;
	}

	@Override
	public ST visitExprAddSub(AdvParser.ExprAddSubContext ctx) {
		ST res = new ST("<n1>,<n2>");
		String p1Name = visit(ctx.expr(0)).render();
		String p2Name = visit(ctx.expr(1)).render();
		Point p1 = new Point(Double.parseDouble(p1Name.split(",")[0]), Double.parseDouble(p1Name.split(",")[1]));
		Point p2 = new Point(Double.parseDouble(p2Name.split(",")[0]), Double.parseDouble(p2Name.split(",")[1]));
		switch (ctx.op.getText()) {
			case "+":
				res.add("n1", Double.toString(p1.x + p2.x));
				res.add("n2", Double.toString(p1.y + p2.y));
				break;
			case "-":
				res.add("n1", Double.toString(p1.x - p2.x));
				res.add("n2", Double.toString(p1.y - p2.y));
				break;
			default:
				break;
		}
		return res;
	}

	@Override
	public ST visitExprParenthesis(AdvParser.ExprParenthesisContext ctx) {
		ST res = new ST("<n1>,<n2>");
		String coords = visit(ctx.expr()).render();
		Point p = pointCoords.get(coords) != null ? pointCoords.get(coords) : stateCoords.get(coords);
		String x = "";
		String y = "";
		if (p == null) {
			x = coords.split(",")[0];
			y = coords.split(",")[1];
		} else {
			x = Double.toString(p.x);
			y = Double.toString(p.y);
		}
		res.add("n1", x);
		res.add("n2", y);
		return res;
	}

	@Override
	public ST visitExprNumber(AdvParser.ExprNumberContext ctx) {
		ST res = new ST("<number>");
		res.add("number", visit(ctx.number()).render());
		return res;
	}

	@Override
	public ST visitExprName(AdvParser.ExprNameContext ctx) {
		Point p = pointCoords.get(ctx.getText()) != null ? pointCoords.get(ctx.getText())
				: stateCoords.get(ctx.getText());
		ST res = new ST("<n1>,<n2>");
		res.add("n1", Double.toString(p.x));
		res.add("n2", Double.toString(p.y));
		return res;
	}

	@Override
	public ST visitExprPositiveNegative(AdvParser.ExprPositiveNegativeContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public ST visitExprMulDiv(AdvParser.ExprMulDivContext ctx) {
		String p1Name = visit(ctx.expr(0)).render();
		String p2Name = visit(ctx.expr(1)).render();
		double factor = 1;
		Point p = null;
		if (p1Name.contains(",")) {
			factor = Double.parseDouble(p2Name);
			p = new Point(Double.parseDouble(p1Name.split(",")[0]), Double.parseDouble(p1Name.split(",")[1]));
		} else {
			factor = Double.parseDouble(p1Name);
			p = new Point(Double.parseDouble(p2Name.split(",")[0]), Double.parseDouble(p2Name.split(",")[1]));
		}
		ST res = new ST("<n1>,<n2>");
		switch (ctx.op.getText()) {
			case "*":
				res.add("n1", Double.toString(p.x * factor));
				res.add("n2", Double.toString(p.y * factor));
				break;
			case "/":
				res.add("n1", Double.toString(p.x / factor));
				res.add("n2", Double.toString(p.y / factor));
				break;
			default:
				break;
		}
		return res;
	}

	@Override
	public ST visitExprVector(AdvParser.ExprVectorContext ctx) {
		ST res = new ST("<vector>");
		res.add("vector", visit(ctx.vector()).render());
		return res;
	}

	@Override
	public ST visitNumber(AdvParser.NumberContext ctx) {
		ST res = new ST("<number>");
		res.add("number", ctx.getText());
		return res;
	}

	@Override
	public ST visitAutomaton_types(AdvParser.Automaton_typesContext ctx) {
		ST res = new ST("<type>");
		res.add("type", ctx.getText());
		return res;
	}
}
