from constraintsearch import ConstraintSearch


amigos = ['Andre', 'Bernardo', 'Claudio']


def map_constraint(a1, t1, a2, t2):
    b1, c1 = t1
    b2, c2 = t2

    ''' No 2 friends can take the same bike nor the same hat'''
    if b1 == b2 or c1 == c2:
        return False

    ''' No friend can take his own bike nor his own hat'''
    if a1 == b1 or a1 == c1 or a2 == c2 or a2 == b2:
        return False

    ''' No one friend can take the bike and hat from the same friend'''
    if b1 == c1 or b2 == c2:
        return False

    '''  o que leva o chapeu de Claudio anda na bicicleta de Bernardo'''
    if c1 == 'Claudio' and b1 != 'Bernardo':
        return False
    if c2 == 'Claudio' and b2 != 'Bernardo':
        return False

    return True


def make_constraint_graph(amigos):
    return {(X, Y): map_constraint for X in amigos for Y in amigos if X != Y}


def make_domains(amigos):
    return {amigo: [(chapeu, bicicleta) for chapeu in amigos for bicicleta in amigos] for amigo in amigos}


cs = ConstraintSearch(make_domains(amigos), make_constraint_graph(amigos))

print(cs.search())
