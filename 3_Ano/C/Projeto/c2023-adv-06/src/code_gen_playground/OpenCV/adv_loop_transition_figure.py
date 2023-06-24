import cv2 as cv
from adv_transition_figure import AdvTransitionFigure
from align import Align
from point import Point


class AdvLoopTransitionFigure(AdvTransitionFigure):
    def __init__(self, key, label, *p):
        super().__init__(key, label)
        if len(p) == 1:
            # set arrow points
            p1 = p[0] + Point(-0.2, -0.6)
            self.arrow_points.append(p1)
            p1 = p1 + Point(-0.2, -0.3)
            start_mid_point = Point(0, 0) + p1
            self.arrow_points.append(p1)
            p1 = p1 + Point(0.8, 0.0)
            self.arrow_points.append(p1)
            end_mid_point = Point(0, 0) + p1
            p1 = p1 + Point(-0.2, 0.3)
            self.arrow_points.append(p1)

            # set label reference point and alignment
            self.labelreference_point = (start_mid_point + end_mid_point) / 2 + Point(0, 0.1)
            self.label_alignment = Align.CENTERED
        else:
            self.redefine_arrow_points(*p)
            # find the middle point
            p1 = Point(0, 0)
            for p2 in self.arrow_points:
                p1 += p2
            p1 /= len(self.arrow_points)
            self.labelreference_point = p1
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
        for i in range(len(points) - 1):
            cv.line(mat, points[i], points[i + 1], self.stroke_color, self.stroke_thickness)
        cv.arrowedLine(mat, points[-2], points[-1], self.stroke_color, self.stroke_thickness)

        # draw the label according to the alignment
        sz, _ = cv.getTextSize(self.label, cv.FONT_HERSHEY_SIMPLEX, 0.6, self.stroke_thickness)
        c = self.labelreference_point / scale_from * scale_to + Point(-sz[0] / 2, sz[1] / 2)
        c += self.adjust_label_position(self.label_alignment, sz)
        center = c.round_to_int()

        cv.putText(mat, self.label, center, cv.FONT_HERSHEY_SIMPLEX, 0.6, self.stroke_thickness)
