class State:
    def __init__(self, label):
        self.label = label
        self.is_accepting = False
        self.is_starting = False
        self.transitions = {}

    def add_transition(self, state, symbol):
        if state not in self.transitions:
            self.transitions[state] = [symbol]
        else:
            self.transitions[state].append(symbol)

    def __str__(self):
        res = f"State({self.label})"
        if self.is_accepting:
            res += " [accepting]"
        if self.is_starting:
            res += " [starting]"
        return res + str(self.transitions)

    def __repr__(self):
        return self.__str__()
