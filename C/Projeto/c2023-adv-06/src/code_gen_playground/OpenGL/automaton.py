class Automaton:
    def __init__(self, name, automaton_type):
        self.name = name
        self.automaton_type = automaton_type
        self.states = {}

    def add_state(self, state):
        self.states[state.label] = state

    def __str__(self):
        return f"Automaton({self.name}, {self.automaton_type}, {self.states})"
    
    def __repr__(self):
        return self.__str__()
