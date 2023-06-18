import cv2 as cv

from adv_transition_figure import AdvTransitionFigure
from align import Align
from point import Point


class AdvLineTransitionFigure(AdvTransitionFigure):
    def __init__(self, key, label, *points):
        super().__init__(key, label)

        # for p in points
        if len(points) == 2:
            p1 = points[0]
            p2 = points[1]
            # set arrow points
            p21 = p2 - p1
            d = p21 / p21.norm() * 0.7
            pa = p1 + d
            self.arrow_points.append(pa)
            pb = p2 - d
            self.arrow_points.append(pb)
            p = (pa + pb) / 2 + Point(0, -0.2)
        else:
            self.redefine_arrow_points(*points)
            # find the middle point
            p = Point(0, 0)
            for p1 in self.arrow_points:
                p += p1
            p /= len(self.arrow_points)
        self.labelreference_point = p

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
        for i in range(len(points) - 1):
            cv.line(mat, points[i], points[i + 1], self.stroke_color, self.stroke_thickness)
        cv.arrowedLine(mat, points[-2], points[-1], self.stroke_color, self.stroke_thickness)

        # draw the label according to the alignment
        sz, _ = cv.getTextSize(self.label, cv.FONT_HERSHEY_SIMPLEX, 0.6, self.stroke_thickness)
        c = self.labelreference_point / scale_from * scale_to + Point(-sz[0] / 2, sz[1] / 2)
        c += self.adjust_label_position(self.label_alignment, sz)
        center = c.round_to_int()
        cv.putText(mat, self.label, center, cv.FONT_HERSHEY_SIMPLEX, 0.6, self.stroke_thickness)
