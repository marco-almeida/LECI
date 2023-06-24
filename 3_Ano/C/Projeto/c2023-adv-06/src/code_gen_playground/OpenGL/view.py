class View:
    def __init__(self, automaton):
        self.automaton = automaton
        self.state_positions = {}

    def set_state_position(self, state_label, x, y):
        self.state_positions[state_label] = (x, y)

    def __str__(self):
        return f"View for {self.automaton.name} {self.automaton.automaton_type}: {self.state_positions}"

    def __repr__(self):
        return self.__str__()
