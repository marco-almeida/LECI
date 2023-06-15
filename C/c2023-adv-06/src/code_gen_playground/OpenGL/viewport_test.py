import logging
import time

from OpenGL.GL import *
from OpenGL.GLU import *
from OpenGL.GLUT import *

from animation import Animation
from automaton import Automaton
from shapes import draw_arrow_leaving_state, draw_state, draw_triangular_loop_arrow
from state import State
from view import View
from viewport import Viewport

# START OF FILE
logging.basicConfig(
    level=logging.DEBUG,
    format="%(asctime)s,%(msecs)03d: %(module)17s->%(funcName)-15s - [%(levelname)7s] - %(message)s",
    handlers=[logging.StreamHandler(stream=sys.stdout)],
)

logger = logging.getLogger().getChild("System")
automata = {}
views = {}
animations = {}
state_size = 40  # 40 by default, but can be changed with advx
accepting_state_size = state_size / 1.25

# alphabet { 'a', 'b', 'c' }
alphabet = set(["a", "b", "c"])

# NFA a1 <<<
automaton_name = "a1"
automaton_type = "NFA"
automata["a1"] = Automaton(automaton_name, automaton_type)

# state A, B;
# when states are defined like this, some default values are established: is_accepting=False, is_starting=False
automata[automaton_name].add_state(State("A"))
automata[automaton_name].add_state(State("B"))
automata[automaton_name].add_state(State("C"))  # extra

# A [initial = true];  // state A as the initial one
automata[automaton_name].states["A"].is_starting = True
# B [accepting = true];  // state B as an accepting one
automata[automaton_name].states["B"].is_accepting = True

# transition
#         A -> 'a','b' -> B,
automata[automaton_name].states["A"].add_transition("B", "a")
automata[automaton_name].states["A"].add_transition("B", "b")
#         A -> 'a','b','c' -> A;
automata[automaton_name].states["A"].add_transition("A", "a")
automata[automaton_name].states["A"].add_transition("A", "b")
automata[automaton_name].states["A"].add_transition("A", "c")
automata[automaton_name].states["A"].add_transition("C", "c")  # extra

# view v1 of a1 <<<
view_name = "v1"
automaton_name = "a1"
views[view_name] = View(automata[automaton_name])

# place A at (2,1), B at (5,1);

views[view_name].state_positions["A"] = (2, 1)
views[view_name].state_positions["B"] = (5, 1)
views[view_name].state_positions["C"] = (3, 4)  # extra

# animation m1 <<<
animation_name = "m1"
animations[animation_name] = Animation(animation_name)

# viewport vp1 for v1 at (10,10) -- ++(500,500);
animations[animation_name].add_viewport(Viewport("vp1", views["v1"], 10, 10, 500, 500))

# on vp1 <<<
viewport_name = "vp1"
# show A,
animations[animation_name].viewports[viewport_name].add_instruction({"command": "show", "subject": "A"})
animations[animation_name].viewports[viewport_name].add_instruction({"command": "show", "subject": "C"})  # extra
# show B [accepting = false];
animations[animation_name].viewports[viewport_name].add_instruction({"command": "show", "subject": "B", "is_accepting": False})
# pause
animations[animation_name].viewports[viewport_name].add_instruction({"command": "pause"})
# show <A,B>;
animations[animation_name].viewports[viewport_name].add_instruction({"command": "show_transitions", "subject": ["A", "B"]})
# pause
animations[animation_name].viewports[viewport_name].add_instruction({"command": "pause"})
# show <A,A>;
animations[animation_name].viewports[viewport_name].add_instruction({"command": "show_transitions", "subject": ["A", "A"]})
animations[animation_name].viewports[viewport_name].add_instruction({"command": "show_transitions", "subject": ["A", "C"]})  # extra
# pause
animations[animation_name].viewports[viewport_name].add_instruction({"command": "pause"})
# show B [accepting = true];
animations[animation_name].viewports[viewport_name].add_instruction({"command": "show", "subject": "B", "is_accepting": True})
# pause;
animations[animation_name].viewports[viewport_name].add_instruction({"command": "pause"})

### >>>
### >>>

# play m1;
active_animation = "m1"


def display_m1():
    logger.info(f"Showing animation {active_animation}")
    for viewport in animations[active_animation].viewports.values():
        logger.info(f"Showing viewport {viewport.name}")
        glClear(GL_COLOR_BUFFER_BIT)
        glColor3f(1.0, 0.0, 0.0)
        glLoadIdentity()
        vp_name = viewport.name
        vp_view = viewport.view
        vp_bound_x = viewport.bound_x
        vp_bound_y = viewport.bound_y
        vp_width = viewport.width
        vp_height = viewport.height
        vp_instructions = viewport.instructions
        for instruction in vp_instructions:
            logger.info(f"Executing instruction {instruction}")
            if instruction["command"] == "show":
                state_name = instruction["subject"]
                state_x = vp_width / vp_bound_x * vp_view.state_positions[state_name][0]
                state_y = vp_height / vp_bound_y * vp_view.state_positions[state_name][1]
                is_starting = instruction.get("is_starting", vp_view.automaton.states[state_name].is_starting)
                is_accepting = instruction.get("is_accepting", vp_view.automaton.states[state_name].is_accepting)
                logger.info(f"Showing state {state_name} at {state_x}, {state_y}, is_starting={is_starting}, is_accepting={is_accepting}")
                draw_state(
                    state_x,
                    state_y,
                    vp_view.automaton.states[state_name],
                    is_accepting=is_accepting,
                    is_starting=is_starting,
                    radius=state_size,
                    accepting_radius=accepting_state_size,
                )
            elif instruction["command"] == "pause":
                time.sleep(1)
            elif instruction["command"] == "show_transitions":
                from_state_name = instruction["subject"][0]
                to_state_name = instruction["subject"][1]
                label = ",".join(vp_view.automaton.states[from_state_name].transitions[to_state_name])
                if from_state_name != to_state_name:
                    x1 = vp_width / vp_bound_x * vp_view.state_positions[from_state_name][0]
                    y1 = vp_height / vp_bound_y * vp_view.state_positions[from_state_name][1]
                    x2 = vp_width / vp_bound_x * vp_view.state_positions[to_state_name][0]
                    y2 = vp_height / vp_bound_y * vp_view.state_positions[to_state_name][1]
                    draw_arrow_leaving_state(x1, y1, x2, y2, label, radius=state_size)
                else:
                    x = vp_width / vp_bound_x * vp_view.state_positions[from_state_name][0]
                    y = vp_height / vp_bound_y * vp_view.state_positions[from_state_name][1]
                    draw_triangular_loop_arrow(x, y, label)

            glFlush()
    glutDestroyWindow(glutGetWindow())


glutInit()
glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB)
glutInitWindowSize(500, 500)
glutInitWindowPosition(900, 100)
glutCreateWindow(b"Circle")
glClearColor(0.0, 0.0, 0.0, 0.0)
glMatrixMode(GL_PROJECTION)
glLoadIdentity()
gluOrtho2D(0.0, 500.0, 0.0, 500.0)
glMatrixMode(GL_MODELVIEW)
glutDisplayFunc(display_m1)
glutMainLoop()
