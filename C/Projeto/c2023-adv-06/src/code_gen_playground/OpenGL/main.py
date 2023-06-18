import logging
import time

from animation import Animation
from animation_handler import show_animation
from automaton import Automaton
from OpenGL.GL import *
from OpenGL.GLU import *
from OpenGL.GLUT import *
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
queued_animations = []

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

# view v1 of a1 <<<
view_name = "v1"
automaton_name = "a1"
views[view_name] = View(automata[automaton_name])

# place A at (2,1), B at (5,1);
views[view_name].state_positions["A"] = (2, 1)
views[view_name].state_positions["B"] = (5, 1)

# animation m1 <<<
animation_name = "m1"
animations[animation_name] = Animation(animation_name)

# viewport vp1 for v1 at (10,10) -- ++(500,500);
animations[animation_name].add_viewport(Viewport("vp1", views["v1"], 10, 10, 500, 500))

# on vp1 <<<
viewport_name = "vp1"
# show A,
animations[animation_name].viewports[viewport_name].add_instruction({"command": "show", "subject": "A"})
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
# pause
animations[animation_name].viewports[viewport_name].add_instruction({"command": "pause"})
# show B [accepting = true];
animations[animation_name].viewports[viewport_name].add_instruction({"command": "show", "subject": "B", "is_accepting": True})
# pause;
animations[animation_name].viewports[viewport_name].add_instruction({"command": "pause"})

### >>>
### >>>
# play m1;
queued_animations.append("m1")


def main():
    for animation in queued_animations:
        show_animation(animations[animation], state_size, accepting_state_size)
        glutDestroyWindow(glutGetWindow())  # ou glutSwapBuffers()
        time.sleep(1)
    glutMainLoop()


if __name__ == "__main__":
    main()
# def validate_transition(states, alphabet, transitions):
#     for transition in transitions:
#         if len(transition) < 3:
#             raise RuntimeError(
#                 f"Invalid transition: {transition} has less than 3 elements"
#             )
#         if transition[0] not in states:
#             raise RuntimeError(f"Invalid transition: state {transition[0]} not found")
#         if transition[-1] not in states:
#             raise RuntimeError(f"Invalid transition: state {transition[-1]} not found")
#         for symbol in transition[1:-1]:
#             if symbol not in alphabet:
#                 raise RuntimeError(f"Invalid transition: symbol {symbol} not found")
