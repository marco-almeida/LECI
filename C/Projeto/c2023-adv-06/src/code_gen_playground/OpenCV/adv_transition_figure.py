import cv2 as cv
from adv_figure import AdvFigure
from align import Align
from point import Point


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

    def redefine_arrow_points(self, *points):
        self.arrow_points = []
        for p in points:
            self.arrow_points.append(p)

    def adjust_label_position(self, label_alignment, sz):
        if label_alignment == Align.BELOW:
            return Point(0, sz[1] * 0.75)
        elif label_alignment == Align.ABOVE:
            return Point(0, -sz[1] * 0.75)
        elif label_alignment == Align.LEFT:
            return Point(-sz[0] * 0.75, 0)
        elif label_alignment == Align.RIGHT:
            return Point(sz[0] * 0.75, 0)
        elif label_alignment == Align.TOP_LEFT:
            return Point(-sz[0] * 0.75, -sz[1] * 0.75)
        elif label_alignment == Align.TOP_RIGHT:
            return Point(sz[0] * 0.75, -sz[1] * 0.75)
        elif label_alignment == Align.BOTTOM_LEFT:
            return Point(-sz[0] * 0.75, sz[1] * 0.75)
        elif label_alignment == Align.BOTTOM_RIGHT:
            return Point(sz[0] * 0.75, sz[1] * 0.75)
        return Point(0, 0)
