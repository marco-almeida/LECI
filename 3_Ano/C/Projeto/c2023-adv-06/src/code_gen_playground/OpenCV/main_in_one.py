import logging
import math
import sys
from enum import Enum

import cv2 as cv
import numpy as np


class Align(Enum):
    CENTERED = 0
    LEFT = 1
    RIGHT = 2
    ABOVE = 3
    BELOW = 4


class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __add__(self, other):
        return Point(self.x + other.x, self.y + other.y)

    def __sub__(self, other):
        return Point(self.x - other.x, self.y - other.y)

    def __mul__(self, scalar):
        return Point(self.x * scalar, self.y * scalar)

    def __truediv__(self, scalar):
        return Point(self.x / scalar, self.y / scalar)

    def __floordiv__(self, scalar):
        return Point(self.x // scalar, self.y // scalar)

    def __str__(self):
        return "(" + str(self.x) + "," + str(self.y) + ")"

    def round_to_int(self):
        return (int(round(self.x)), int(round(self.y)))

    def norm(self):
        return math.sqrt(self.x**2 + self.y**2)


class State:
    def __init__(self, label):
        self.label = label
        self.initial = False
        self.accepting = False
        self.transitions = {}

    def add_transition(self, state, symbol):
        if state not in self.transitions:
            self.transitions[state] = [symbol]
        else:
            self.transitions[state].append(symbol)

    def __str__(self):
        res = f"State({self.label})"
        if self.initial:
            res += " [initial]"
        return res + str(self.transitions)

    def __repr__(self):
        return self.__str__()


class AdvAutomatonView:
    def __init__(self, name):
        self.name = name
        self.figures = {}

    def add_figure(self, key, figure):
        self.figures[key] = figure

    def draw(self, mat, scale_from, scale_to):
        for f in self.figures.values():
            f.draw(mat, scale_from, scale_to)


class AdvFigure:
    def __init__(self, key):
        self.key = key
        self.reference_point = Point(0, 0)
        self.visible = False
        self.stroke_color = (0, 0, 0)
        self.stroke_thickness = 2

    def draw(self, mat, scale_from, scale_to):
        # to be overwritten by subclasses
        pass


class AdvTransitionFigure(AdvFigure):
    def __init__(self, key, label):
        super().__init__(key)
        self.label = label
        self.labelreference_point = Point(0, 0)
        self.label_alignment = Align.CENTERED
        self.arrow_points = []

    def draw(self, mat, scale_from, scale_to):
        # if not visible do nothing
        if not self.visible:
            return

        print("  Drawing transition " + self.key)

        # convert arrow's points to image coordinates
        points = []
        for p in self.arrow_points:
            p1 = p / scale_from * scale_to
            points.append(p1.round_to_int())

        print(points)
        # draw the arrow, assuming there are at least 2 points
        for i, p in enumerate(points[:-2]):
            cv.line(mat, p, points[i + 1], self.stroke_color, self.stroke_thickness)
        cv.arrowedLine(mat, points[-2], points[-1], self.stroke_color, self.stroke_thickness)


class AdvLineTransitionFigure(AdvTransitionFigure):
    def __init__(self, key, label, p1, p2):
        super().__init__(key, label)

        # set arrow points
        p21 = p2 - p1
        d = p21 / p21.norm() * 0.7
        pa = p1 + d
        self.arrow_points.append(pa)
        pb = p2 - d
        self.arrow_points.append(pb)

        # set label reference point and alignment
        p = (pa + pb) / 2 + Point(0, -0.2)
        self.labelreference_point = p
        self.label_alignment = Align.CENTERED

    def draw(self, mat, scale_from, scale_to):
        # if not visible do nothing
        if not self.visible:
            return

        print("  Drawing transition " + self.key)

        # convert arrow's points to image coordinates
        points = []
        for p in self.arrow_points:
            p1 = p / scale_from * scale_to
            points.append(p1.round_to_int())

        print(points)
        # draw the arrow, assuming there are at least 2 points
        for i, p in enumerate(points[:-2]):
            cv.line(mat, p, points[i + 1], self.stroke_color, self.stroke_thickness)
        cv.arrowedLine(mat, points[-2], points[-1], self.stroke_color, self.stroke_thickness)

        # draw the label in the arrow's middle point
        sz, _ = cv.getTextSize(self.label, cv.FONT_HERSHEY_SIMPLEX, 0.6, self.stroke_thickness)
        c = self.labelreference_point / scale_from * scale_to + Point(-sz[0] / 2, sz[1] / 2)
        center = c.round_to_int()
        cv.putText(mat, self.label, center, cv.FONT_HERSHEY_SIMPLEX, 0.6, self.stroke_thickness)


class AdvLoopTransitionFigure(AdvTransitionFigure):
    def __init__(self, key, label, p):
        super().__init__(key, label)

        # set arrow points
        p1 = p + Point(-0.2, -0.6)
        self.arrow_points.append(p1)
        p1 = p1 + Point(-0.2, -0.3)
        self.arrow_points.append(p1)
        p1 = p1 + Point(0.8, 0.0)
        self.arrow_points.append(p1)
        p1 = p1 + Point(-0.2, 0.3)
        self.arrow_points.append(p1)

        # set label reference point and alignment
        p1 = p1 + Point(0.2, -0.2)
        self.labelreference_point = p1
        self.label_alignment = Align.LEFT

    def draw(self, mat, scale_from, scale_to):
        # if not visible do nothing
        if not self.visible:
            return

        print("  Drawing transition " + self.key)

        # convert arrow's points to image coordinates
        points = []
        for p in self.arrow_points:
            p1 = p / scale_from * scale_to
            points.append(p1.round_to_int())

        print(points)
        # draw the arrow, assuming there are at least 2 points
        for i, p in enumerate(points[:-2]):
            cv.line(mat, p, points[i + 1], self.stroke_color, self.stroke_thickness)
        cv.arrowedLine(mat, points[-2], points[-1], self.stroke_color, self.stroke_thickness)

        # draw the label in the arrow's middle point
        sz, _ = cv.getTextSize(self.label, cv.FONT_HERSHEY_SIMPLEX, 0.6, self.stroke_thickness)
        c = self.labelreference_point / scale_from * scale_to + Point(-sz[0] / 2, sz[1] / 2)
        center = c.round_to_int()

        cv.putText(mat, self.label, center, cv.FONT_HERSHEY_SIMPLEX, 0.6, self.stroke_thickness)


class AdvStateFigure(AdvFigure):
    def __init__(self, key, origin):
        super().__init__(key)
        self.accepting = False
        self.initial = False
        self.reference_point = origin
        self.transitions = {}
        self.radius = 0.5

    def draw(self, mat, scale_from, scale_to):
        # if not visible do nothing
        if not self.visible:
            return

        print("  Drawing state " + self.key)

        # determine center and radius in image coordinates
        c = self.reference_point / scale_from * scale_to
        center = c.round_to_int()
        r = int(round(self.radius / scale_from * scale_to))

        # draw state shape
        cv.circle(mat, center, r, self.stroke_color, self.stroke_thickness)
        if self.accepting:
            r2 = int(round(0.8 * self.radius / scale_from * scale_to))
            cv.circle(mat, center, r2, self.stroke_color, self.stroke_thickness)
        if self.initial:
            p2 = self.reference_point - Point(self.radius, 0)
            p1 = self.reference_point - Point(self.radius * 3, 0)
            p21 = p2 - p1
            d = p21 / p21.norm() * 0.9
            pa = p1 + d
            points = []
            points.append((pa / scale_from * scale_to).round_to_int())
            pb = p2 - d
            points.append((pb / scale_from * scale_to).round_to_int())
            for i, p in enumerate(points[:-2]):
                cv.line(mat, p, points[i + 1], self.stroke_color, self.stroke_thickness)
            cv.arrowedLine(mat, points[-1], points[-2], self.stroke_color, self.stroke_thickness)

        # draw label
        sz, _ = cv.getTextSize(self.key, cv.FONT_HERSHEY_SIMPLEX, 0.6, self.stroke_thickness)
        c = c + Point(-sz[0] / 2, sz[1] / 2)
        center = c.round_to_int()
        cv.putText(mat, self.key, center, cv.FONT_HERSHEY_SIMPLEX, 0.6, self.stroke_thickness)


class Automaton:
    def __init__(self, name, automaton_type):
        self.name = name
        self.automaton_type = automaton_type
        self.states = {}

    def add_state(self, label):
        self.states[label] = State(label)

    def __str__(self):
        return f"Automaton({self.name}, {self.automaton_type}, {self.states})"

    def __repr__(self):
        return self.__str__()


class Viewport:
    def __init__(self, name, view, bound_x, bound_y, width, height):
        self.name = name
        self.view = view
        self.bound_x = bound_x
        self.bound_y = bound_y
        self.width = width
        self.height = height


# START OF FILE
logging.basicConfig(
    level=logging.DEBUG,
    format="%(asctime)s,%(msecs)03d: %(module)17s->%(funcName)-15s - [%(levelname)7s] - %(message)s",
    handlers=[logging.StreamHandler(stream=sys.stdout)],
)

logger = logging.getLogger().getChild("System")
automata = {}
views = {}

# alphabet { 'a', 'b', 'c' }
# alphabet = set(["a", "b", "c"])  # doesnt seem important here

#######################

# NFA a2 <<<
automaton_name = "a2"
automata[automaton_name] = Automaton(automaton_name, "NFA")

# state A, B;
automata[automaton_name].add_state("A")
automata[automaton_name].add_state("B")

# A [initial = true];  // state A as the initial one
automata[automaton_name].states["A"].initial = True
# B [accepting = true];  // state B as an accepting one
automata[automaton_name].states["B"].accepting = True

# transition
#         A -> 'a','b' -> B,
automata[automaton_name].states["A"].add_transition("B", "a")
automata[automaton_name].states["A"].add_transition("B", "b")
#         A -> 'c' -> A,
automata[automaton_name].states["A"].add_transition("A", "c")
#         B -> 'c' -> A,
automata[automaton_name].states["B"].add_transition("A", "c")
#         B -> 'a','b' -> B;
automata[automaton_name].states["B"].add_transition("B", "a")
automata[automaton_name].states["B"].add_transition("B", "b")

#######################

# view v2 of a2 <<<
view_name = "v2"
automaton_name = "a2"
views[view_name] = AdvAutomatonView(view_name)

# place A at (2,1), B at (5,1);
# A
state_name = "A"
f = AdvStateFigure(state_name, Point(2.0, 1.0))
f.initial = automata[automaton_name].states[state_name].initial
f.accepting = automata[automaton_name].states[state_name].accepting
f.transitions = automata[automaton_name].states[state_name].transitions
views[view_name].add_figure(state_name, f)
# B
state_name = "B"
f = AdvStateFigure(state_name, Point(5.0, 1.0))
f.initial = automata[automaton_name].states[state_name].initial
f.accepting = automata[automaton_name].states[state_name].accepting
f.transitions = automata[automaton_name].states[state_name].transitions
views[view_name].add_figure(state_name, f)

# <B,A> as p1 -- pm -- p2;
p1 = Point(5.0, 1.0)
pm = Point(3.5, 1.0)
p2 = Point(2.0, 1.0)

#######################

# animation m1 <<<


def animation_m1():
    logger.info("Animation m1")
    animation_name = "m1"
    viewports = {}
    # viewport vp1 for v2 at (10,10) -- ++(500,500);
    viewports["vp1"] = Viewport("vp1", views["v2"], 10, 10, 500, 500)

    #######################

    # on vp1 <<<
    viewport_name = "vp1"
    vp1_bound_x = viewports["vp1"].bound_x
    vp1_bound_y = viewports["vp1"].bound_y
    vp1_width = viewports["vp1"].width
    vp1_height = viewports["vp1"].height
    view = viewports["vp1"].view
    window = np.zeros((vp1_bound_x + vp1_width, vp1_bound_y + vp1_height, 3), dtype="uint8")
    window.fill(100)
    vp1 = np.zeros((vp1_width, vp1_height, 3), dtype="uint8")
    vp1.fill(255)

    # show A, B [accepting = false];
    view.figures["A"].visible = True
    view.figures["B"].visible = True
    view.figures["B"].accepting = False
    view.draw(vp1, 1.0, (vp1_width + vp1_height) / (vp1_bound_x + vp1_bound_y))
    np.copyto(window[vp1_bound_x:, vp1_bound_y:, :], vp1)
    cv.imshow(f"Animation {animation_name} - Viewport {viewport_name}", window)

    # pause;
    cv.waitKey(0)

    # show <A,B>;
    label = ",".join(view.figures["A"].transitions["B"])
    pa = view.figures["A"].reference_point
    pb = view.figures["B"].reference_point
    f = AdvLineTransitionFigure("<A,B>", label, pa, pb)
    views[view_name].add_figure("<A,B>", f)
    view.figures["<A,B>"].visible = True
    view.draw(vp1, 1.0, (vp1_width + vp1_height) / (vp1_bound_x + vp1_bound_y))
    np.copyto(window[vp1_bound_x:, vp1_bound_y:, :], vp1)
    cv.imshow(f"Animation {animation_name} - Viewport {viewport_name}", window)

    # pause;
    cv.waitKey(0)

    # show <A,A>;
    label = ",".join(view.figures["A"].transitions["A"])
    pa = view.figures["A"].reference_point
    f = AdvLoopTransitionFigure("<A,A>", label, pa)
    views[view_name].add_figure("<A,A>", f)
    view.figures["<A,A>"].visible = True
    view.draw(vp1, 1.0, (vp1_width + vp1_height) / (vp1_bound_x + vp1_bound_y))
    np.copyto(window[vp1_bound_x:, vp1_bound_y:, :], vp1)
    cv.imshow(f"Animation {animation_name} - Viewport {viewport_name}", window)

    # pause;
    cv.waitKey(0)

    # show B [accepting = true];
    view.figures["B"].accepting = True
    view.draw(vp1, 1.0, (vp1_width + vp1_height) / (vp1_bound_x + vp1_bound_y))
    np.copyto(window[vp1_bound_x:, vp1_bound_y:, :], vp1)
    cv.imshow(f"Animation {animation_name} - Viewport {viewport_name}", window)

    # pause;
    cv.waitKey(0)


# play m1;
animation_m1()
