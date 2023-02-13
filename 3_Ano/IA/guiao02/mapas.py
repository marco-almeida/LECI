from constraintsearch import ConstraintSearch

region = ['A', 'B', 'C', 'D', 'E']
colors = ['red', 'blue', 'green', 'yellow', 'white']

mapa = {
    'A': 'BED',
    'B': 'AEC',
    'C': 'BED',
    'D': 'AEC',
    'E': 'ABCD'
}


def map_constraint(r1, c1, r2, c2):
    return c1 != c2


def make_constraint_graph(mapa):
    return {(X, Y): map_constraint for X in mapa for Y in mapa[X] if X != Y}


def make_domains(region, colors):
    return {r: colors for r in region}


cs = ConstraintSearch(make_domains(region, colors), make_constraint_graph(mapa))

print(cs.search())
