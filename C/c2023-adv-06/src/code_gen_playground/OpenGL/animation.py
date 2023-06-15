class Animation:
    def __init__(self, name):
        self.name = name
        self.viewports = {}

    def add_viewport(self, viewport):
        self.viewports[viewport.name] = viewport
