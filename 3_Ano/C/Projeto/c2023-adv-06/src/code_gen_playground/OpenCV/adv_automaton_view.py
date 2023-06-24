class AdvAutomatonView:
    def __init__(self, name):
        self.name = name
        self.figures = {}

    def add_figure(self, key, figure):
        self.figures[key] = figure

    def draw(self, mat, scale_from, scale_to):
        for f in self.figures.values():
            f.draw(mat, scale_from, scale_to)
