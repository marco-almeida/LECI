import math
from ctypes import POINTER, cast, create_string_buffer, pointer

from OpenGL.GL import *
from OpenGL.GLU import *
from OpenGL.GLUT import *

from state import State


def draw_line(x1, y1, x2, y2, r=1.0, g=1.0, b=1.0):
    glBegin(GL_LINES)
    glColor3f(r, g, b)  # Set color
    glVertex2f(x1, y1)  # Start point
    glVertex2f(x2, y2)  # End point
    glEnd()


def write_string(x, y, string):
    glRasterPos2f(x, y)
    for char in string:
        glutBitmapCharacter(GLUT_BITMAP_HELVETICA_18, ord(char))


def write_centered_string(x, y, string):
    string_bytes = string.encode("utf-8")  # string to bytes
    string_buffer = create_string_buffer(string_bytes)  # C-style string buffer
    string_pointer = cast(pointer(string_buffer), POINTER(GLubyte))  # buffer pointer to LP_c_ubyte

    write_string(x - (glutBitmapLength(GLUT_BITMAP_HELVETICA_18, string_pointer) / 2), y, string)


def draw_labeled_line(x1, y1, x2, y2, label, r=1.0, g=1.0, b=1.0):
    draw_line(x1, y1, x2, y2, r, g, b)
    # label written in the middle of the line
    write_centered_string((x1 + x2) / 2, (y1 + y2) / 2, label)


def draw_circle(x, y, radius=40, r=1.0, g=1.0, b=1.0):
    num_segments = 100
    angle_step = 2 * math.pi / num_segments

    glBegin(GL_LINE_LOOP)
    glColor3f(r, g, b)  # Set color
    for i in range(num_segments):
        angle = i * angle_step
        cx = x + radius * math.cos(angle)
        cy = y + radius * math.sin(angle)
        glVertex2f(cx, cy)
    glEnd()


def draw_arrow(x1, y1, x2, y2, label, radius=0, r=1.0, g=1.0, b=1.0):
    arrow_size = abs(math.sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2) - radius * 2) * 0.2
    # 0.2 so the arrow head is smaller than the arrow body

    # calculate the angle of the line
    angle = math.atan2(y2 - y1, x2 - x1)

    # adjust starting position
    x1 += radius * math.cos(angle)
    y1 += radius * math.sin(angle)
    # adjust final position
    x2 -= radius * math.cos(angle)
    y2 -= radius * math.sin(angle)

    draw_line(x1, y1, x2, y2, r, g, b)

    glBegin(GL_LINES)
    # draw arrowhead, according to angle
    glVertex2f(x2, y2)
    glVertex2f(
        x2 - arrow_size * math.cos(angle + math.radians(45)),
        y2 - arrow_size * math.sin(angle + math.radians(45)),
    )
    glVertex2f(x2, y2)
    glVertex2f(
        x2 - arrow_size * math.cos(angle - math.radians(45)),
        y2 - arrow_size * math.sin(angle - math.radians(45)),
    )
    glEnd()

    write_centered_string((x1 + x2) / 2 + 3, (y1 + y2) / 2 + 3, label)


def draw_arrow_leaving_state(x1, y1, x2, y2, label, radius=40, r=1.0, g=1.0, b=1.0):
    # coordinates are the states' center coordinates and radius is the states' radius
    # 1.1 = 10% do enunciado
    draw_arrow(x1, y1, x2, y2, label, radius * 1.1, r, g, b)


def draw_triangular_loop_arrow(x, y, label, radius=40, r=1.0, g=1.0, b=1.0):
    draw_line(x, y + radius, x + radius * 1.25, y + radius * 2.25, r, g, b)
    draw_labeled_line(
        x + radius * 1.25,
        y + radius * 2.25,
        x - radius * 1.25,
        y + radius * 2.25,
        label,
        r,
        g,
        b,
    )
    draw_arrow(x - radius * 1.25, y + radius * 2.25, x, y + radius, "", r=r, g=g, b=b)


def draw_state(
    x,
    y,
    state: State,
    is_accepting=False,
    is_starting=False,
    radius=40,
    accepting_radius=32,
    r=1.0,
    g=1.0,
    b=1.0,
):
    draw_circle(x, y, radius, r, g, b)
    if is_starting:
        draw_arrow(x - radius * 2, y, x - radius, y, "", 0, r, g, b)

    if is_accepting:
        draw_circle(x, y, accepting_radius, r, g, b)

    write_centered_string(x - 10, y - 10, state.label)
