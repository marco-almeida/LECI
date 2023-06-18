import logging
import time

from OpenGL.GL import *
from OpenGL.GLU import *
from OpenGL.GLUT import *

from animation import Animation
from shapes import draw_arrow_leaving_state, draw_state, draw_triangular_loop_arrow

logger = logging.getLogger().getChild("System")


def init_viewport(animation_name, viewport_name, vp_width, vp_height):
    glutInit()
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB)
    glutInitWindowSize(vp_width, vp_height)
    glutInitWindowPosition(900, 100)
    glutCreateWindow(f"{animation_name} - {viewport_name}".encode("utf-8"))
    glClearColor(1.0, 1.0, 1.0, 0.0)
    glMatrixMode(GL_PROJECTION)
    glLoadIdentity()
    gluOrtho2D(0.0, vp_width, 0.0, vp_height)
    glMatrixMode(GL_MODELVIEW)
    glClear(GL_COLOR_BUFFER_BIT)
    glLoadIdentity()


def show_animation(animation: Animation, state_size=40, accepting_state_size=40 / 1.25):
    logger.info(f"Showing animation {animation.name}")
    for viewport in animation.viewports.values():
        logger.info(f"Showing viewport {viewport.name}. Clearing viewport...")
        init_viewport(animation.name, viewport.name, viewport.width, viewport.height)
        view = viewport.view
        vp_bound_x = viewport.bound_x
        vp_bound_y = viewport.bound_y
        vp_width = viewport.width
        vp_height = viewport.height
        for instruction in viewport.instructions:
            logger.info(f"Executing instruction {instruction}")
            if instruction["command"] == "show":
                state_name = instruction["subject"]
                # get real coordinates according to view
                state_x = vp_width / vp_bound_x * view.state_positions[state_name][0]
                state_y = vp_height / vp_bound_y * view.state_positions[state_name][1]
                # get additional information
                is_starting = instruction.get("is_starting", view.automaton.states[state_name].is_starting)
                is_accepting = instruction.get("is_accepting", view.automaton.states[state_name].is_accepting)
                logger.info(f"Showing state {state_name} at {state_x}, {state_y}, is_starting={is_starting}, is_accepting={is_accepting}")
                draw_state(
                    state_x,
                    state_y,
                    view.automaton.states[state_name],
                    is_accepting=is_accepting,
                    is_starting=is_starting,
                    radius=state_size,
                    accepting_radius=accepting_state_size,
                    r=0,
                    g=0,
                    b=0,
                )
            elif instruction["command"] == "pause":
                # default time is 1 second, should be modified with advx language
                time.sleep(1)
            elif instruction["command"] == "show_transitions":
                from_state_name = instruction["subject"][0]
                to_state_name = instruction["subject"][1]
                label = ",".join(view.automaton.states[from_state_name].transitions[to_state_name])
                if from_state_name != to_state_name:
                    # get real coordinates according to view and draw transition (outgoing arrow)
                    x1 = vp_width / vp_bound_x * view.state_positions[from_state_name][0]
                    y1 = vp_height / vp_bound_y * view.state_positions[from_state_name][1]
                    x2 = vp_width / vp_bound_x * view.state_positions[to_state_name][0]
                    y2 = vp_height / vp_bound_y * view.state_positions[to_state_name][1]
                    draw_arrow_leaving_state(x1, y1, x2, y2, label, state_size, 0, 0, 0)
                else:
                    # get real coordinates according to view and draw transition (loop arrow)
                    x = vp_width / vp_bound_x * view.state_positions[from_state_name][0]
                    y = vp_height / vp_bound_y * view.state_positions[from_state_name][1]
                    draw_triangular_loop_arrow(x, y, label, state_size, 0, 0, 0)

            glFlush()
