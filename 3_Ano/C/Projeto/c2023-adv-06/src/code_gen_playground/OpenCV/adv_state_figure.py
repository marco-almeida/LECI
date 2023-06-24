import cv2 as cv

from adv_figure import AdvFigure
from point import Point


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
