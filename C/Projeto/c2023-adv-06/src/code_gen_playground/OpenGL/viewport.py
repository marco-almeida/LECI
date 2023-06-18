class Viewport:
    def __init__(self, name, view, bound_x, bound_y, width, height):
        self.name = name
        self.view = view
        self.bound_x = bound_x
        self.bound_y = bound_y
        self.width = width
        self.height = height
        self.instructions = []
    
    def add_instruction(self, instruction):
        self.instructions.append(instruction)
