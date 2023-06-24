import logging
import sys

import cv2 as cv
import numpy as np
from adv_automaton_view import AdvAutomatonView
from adv_line_transition_figure import AdvLineTransitionFigure
from adv_loop_transition_figure import AdvLoopTransitionFigure
from adv_state_figure import AdvStateFigure
from align import Align
from automaton import Automaton
from point import Point
from viewport import Viewport

# START OF FILE
logging.basicConfig(
    level=logging.DEBUG,
    format="%(asctime)s,%(msecs)03d: %(module)17s->%(funcName)-15s - [%(levelname)7s] - %(message)s",
    handlers=[logging.StreamHandler(stream=sys.stdout)],
)


def draw_grid(img, grid_shape, color=(0, 255, 0), margin=12, step=25, line_type="solid", thickness=1):
    h, w, _ = img.shape
    start_x, start_y = grid_shape

    # draw vertical lines
    for x in range(margin + start_x, w - margin, step):
        x = int(round(x))
        if line_type == "dotted":
            for xy in range(h - margin - start_y - margin):
                if xy % 3 == 0:
                    cv.line(
                        img,
                        (x, start_y + margin + xy),
                        (x, start_y + margin + xy + 1),
                        color=color,
                        thickness=thickness,
                        lineType=cv.LINE_AA,
                    )
        elif line_type == "dashed":
            for xy in range(h - margin - start_y - margin):
                if xy % 10 == 0:
                    cv.line(
                        img,
                        (x, start_y + margin + xy),
                        (x, start_y + margin + xy + 5),
                        color=color,
                        thickness=thickness,
                        lineType=cv.LINE_AA,
                    )
        else:
            cv.line(img, (x, start_y + margin), (x, h - margin), color=color, thickness=thickness, lineType=cv.LINE_8)

    # draw horizontal lines
    for y in range(margin + start_y, h - margin, step):
        y = int(round(y))
        if line_type == "dotted":
            for xy in range(w - margin - start_x - margin):
                if xy % 3 == 0:
                    cv.line(
                        img,
                        (start_x + margin + xy, y),
                        (start_x + margin + xy + 1, y),
                        color=color,
                        thickness=thickness,
                        lineType=cv.LINE_AA,
                    )
        elif line_type == "dashed":
            for xy in range(w - margin - start_x - margin):
                if xy % 10 == 0:
                    cv.line(
                        img,
                        (start_x + margin + xy, y),
                        (start_x + margin + xy + 5, y),
                        color=color,
                        thickness=thickness,
                        lineType=cv.LINE_AA,
                    )
        else:
            cv.line(img, (start_x + margin, y), (w - margin, y), color=color, thickness=thickness, lineType=cv.LINE_8)


logger = logging.getLogger().getChild("System")
automata = {}
views = {}
grids = {}
defined_alignments = {}

COLOR_STRING_TO_TUPLE = {
    "black": (0, 0, 0),
    "white": (255, 255, 255),
    "red": (0, 0, 255),
    "green": (0, 255, 0),
    "blue": (255, 0, 0),
    "yellow": (0, 255, 255),
    "cyan": (255, 255, 0),
    "gray": (128, 128, 128),
    "orange": (0, 165, 255),
    "brown": (42, 42, 165),
    "purple": (128, 0, 128),
    "pink": (203, 192, 255),
}

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

# grid g3 ...
grids["g3"] = {"width": 20, "height": 20, "color": "orange"}

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
######## modify_transition ########
points = [Point(5.0, 1.0), Point(3.5, 2.0), Point(2.0, 1.0)]
if "<B,A>" not in views[view_name].figures:
    f = AdvLineTransitionFigure("<B,A>", "", *points)
    views[view_name].add_figure("<B,A>", f)
else:
    views[view_name].figures["<B,A>"].arrow_points = points[:]

if "<B,A>" in defined_alignments:
    views[view_name].figures["<B,A>"].label_alignment = defined_alignments["<B,A>"]


# <A,A> as p1 -- pm -- p2 -- p3;
######## modify_transition ########
points = [Point(2.0, 1.0), Point(2.0, 2.0), Point(3.0, 2.0), Point(1.9, 1.0)]
if "<A,A>" not in views[view_name].figures:
    f = AdvLoopTransitionFigure("<A,A>", "", *points)
    views[view_name].add_figure("<A,A>", f)
else:
    views[view_name].figures["<A,A>"].arrow_points = points[:]

if "<A,A>" in defined_alignments:
    views[view_name].figures["<A,A>"].label_alignment = defined_alignments["<A,A>"]

######## end modify_transition ########

# place <B,A>#label [align = below] at pa;
pa = Point(3.5, 2.1)  # + Point(0.0, -0.2)
if "<B,A>" in views[view_name].figures:
    views[view_name].figures["<B,A>"].label_alignment = Align.BELOW
    views[view_name].figures["<B,A>"].labelreference_point = pa
else:
    f = AdvLineTransitionFigure("<B,A>", "", Point(0, 0))
    f.label_alignment = Align.BELOW
    f.labelreference_point = pa
    views[view_name].add_figure("<B,A>", f)

if "<B,A>" in defined_alignments:
    views[view_name].figures["<B,A>"].label_alignment = defined_alignments["<B,A>"]

#######################

# <A,E>#label [align=below left];
defined_alignments["<A,A>"] = Align.ABOVE

# animation m2 <<<


def animation_m2():
    logger.info("Animation m2")
    animation_name = "m2"
    viewports = {}
    # viewport vp2 for v2 at (10,10) -- ++(500,500);
    viewports["vp2"] = Viewport("vp2", views["v2"], 10, 10, 500, 300)

    #######################

    # on vp2 <<<
    viewport_name = "vp2"
    vp2_bound_x = viewports["vp2"].bound_x
    vp2_bound_y = viewports["vp2"].bound_y
    vp2_width = viewports["vp2"].width
    vp2_height = viewports["vp2"].height
    view = viewports["vp2"].view
    window = np.zeros((vp2_bound_y + vp2_height, vp2_bound_x + vp2_width, 3), dtype="uint8")
    window.fill(100)
    vp2 = np.zeros((vp2_height, vp2_width, 3), dtype="uint8")
    vp2.fill(255)

    # show g3;
    draw_grid(
        vp2,
        (grids["g3"]["width"], grids["g3"]["height"]),
        COLOR_STRING_TO_TUPLE[grids["g3"]["color"]],
        margin=12,
        step=40,
        line_type="dashed",
        thickness=1,
    )

    # show A, B [accepting = false];
    view.figures["A"].visible = True
    view.figures["B"].visible = True
    view.figures["B"].accepting = False
    view.draw(vp2, 1.0, 50)
    np.copyto(window[vp2_bound_y:, vp2_bound_x:, :], vp2)
    cv.imshow(f"Animation {animation_name} - Viewport {viewport_name}", window)

    # pause;
    cv.waitKey(0)

    # show <A,B>;
    label = ",".join(view.figures["A"].transitions["B"])
    if "<A,B>" not in view.figures:
        pa = view.figures["A"].reference_point
        pb = view.figures["B"].reference_point
        f = AdvLineTransitionFigure("<A,B>", label, pa, pb)
        views[view_name].add_figure("<A,B>", f)
    else:
        view.figures["<A,B>"].label = label

    if "<A,B>" in defined_alignments:
        views[view_name].figures["<A,B>"].label_alignment = defined_alignments["<A,B>"]

    view.figures["<A,B>"].visible = True
    view.draw(vp2, 1.0, 50)
    np.copyto(window[vp2_bound_y:, vp2_bound_x:, :], vp2)
    cv.imshow(f"Animation {animation_name} - Viewport {viewport_name}", window)

    # pause;
    cv.waitKey(0)

    # show <B,A>;
    label = ",".join(view.figures["B"].transitions["A"])
    if "<B,A>" not in view.figures:
        pb = view.figures["B"].reference_point
        pa = view.figures["A"].reference_point
        f = AdvLineTransitionFigure("<B,A>", label, pa, pb)
        views[view_name].add_figure("<B,A>", f)
    else:
        view.figures["<B,A>"].label = label
    if "<B,A>" in defined_alignments:
        views[view_name].figures["<B,A>"].label_alignment = defined_alignments["<B,A>"]
    view.figures["<B,A>"].visible = True
    view.draw(vp2, 1.0, 50)
    np.copyto(window[vp2_bound_y:, vp2_bound_x:, :], vp2)
    cv.imshow(f"Animation {animation_name} - Viewport {viewport_name}", window)

    # pause;
    cv.waitKey(0)

    # show <A,A>;
    label = ",".join(view.figures["A"].transitions["A"])
    if "<A,A>" not in view.figures:
        pa = view.figures["A"].reference_point
        f = AdvLoopTransitionFigure("<A,A>", label, pa)
        views[view_name].add_figure("<A,A>", f)
    else:
        view.figures["<A,A>"].label = label

    if "<A,A>" in defined_alignments:
        views[view_name].figures["<A,A>"].label_alignment = defined_alignments["<A,A>"]

    view.figures["<A,A>"].visible = True
    view.draw(vp2, 1.0, 50)
    np.copyto(window[vp2_bound_y:, vp2_bound_x:, :], vp2)
    cv.imshow(f"Animation {animation_name} - Viewport {viewport_name}", window)

    # pause;
    cv.waitKey(0)

    # show B [accepting = true];
    view.figures["B"].accepting = True
    view.draw(vp2, 1.0, 50)
    np.copyto(window[vp2_bound_y:, vp2_bound_x:, :], vp2)
    cv.imshow(f"Animation {animation_name} - Viewport {viewport_name}", window)

    # pause;
    cv.waitKey(0)


# play m2;
animation_m2()
