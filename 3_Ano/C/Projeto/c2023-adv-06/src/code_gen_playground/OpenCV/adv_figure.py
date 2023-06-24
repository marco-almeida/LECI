from point import Point


class AdvFigure:
    def __init__(self, key):
        self.key = key
        self.reference_point = Point(0, 0)
        self.visible = False
        self.stroke_color = (0, 0, 0)
        self.stroke_thickness = 2

    def draw(self, mat, scale_from, scale_to):
        pass
