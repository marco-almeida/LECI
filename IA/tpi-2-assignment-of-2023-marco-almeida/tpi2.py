#encoding: utf8

# YOUR NAME: Marco Antonio Alves Almeida
# YOUR NUMBER: 103440

# COLLEAGUES WITH WHOM YOU DISCUSSED THIS ASSIGNMENT:
from semantic_network import *
from bayes_net import *
from constraintsearch import *
import itertools

class MySN(SemanticNetwork):

    def __init__(self):
        SemanticNetwork.__init__(self)

    def is_object(self, user, obj):
        '''checks if the object obj exists in user declarations'''
        for d in self.declarations:
            if d.user == user:
                if isinstance(d.relation, Association) and d.relation.card == None:
                    if obj == d.relation.entity1 or obj == d.relation.entity2:
                        return True

                if isinstance(d.relation, Member) and d.relation.entity1 == obj:
                    return True

        return False

    def is_type(self, user, type):
        '''checks if the type type exists in user declarations'''
        for d in self.declarations:
            if d.user == user:

                if isinstance(d.relation, Member) and d.relation.entity2 == type:
                    return True

                if isinstance(d.relation, Subtype) and (type == d.relation.entity1 or type == d.relation.entity2):
                    return True

                if isinstance(d.relation, Association) and d.relation.card != None and (type == d.relation.entity1 or type == d.relation.entity2):
                    return True
        return False

    def infer_type(self, user, obj):
        '''Infers the type of an object based on the declarations of user'''
        diction = {}
        for d in self.declarations:
            if d.user == user and isinstance(d.relation, Member) and d.relation.entity1 == obj:
                return d.relation.entity2
            if d.user == user and isinstance(d.relation, Association) and d.relation.card != None:
                # guardar no dicionario, para o nome da associaçao, a assinatura
                # e.g {'altura': ('mamifero', 'number')}
                diction[d.relation.name] = self.infer_signature(user, d.relation.name)

        for d in self.declarations:
            if isinstance(d.relation, Association):
                if d.relation.name in diction.keys():
                    if d.user == user and d.relation.entity1 == obj:
                        return diction[d.relation.name][0]
                    if d.user == user and d.relation.entity2 == obj:
                        return diction[d.relation.name][1]

                if d.relation.name not in diction.keys() and (d.relation.entity1 == obj or d.relation.entity2 == obj):
                    return "__unknown__"

    def infer_signature(self, user, assoc):
        '''Infers the signature of the association based on the declarations of user'''
        # "altura" tem como assinatura ("mamifero", "number")
        for d in self.declarations:
            if d.user == user and isinstance(d.relation, Association) and d.relation.name == assoc:
                if d.relation.card == None:
                    # They're objects and thus, their types must be inferred
                    return (self.infer_type(user, d.relation.entity1), self.infer_type(user, d.relation.entity2))
                return (d.relation.entity1, d.relation.entity2)


class MyBN(BayesNet):

    def __init__(self):
        BayesNet.__init__(self)

    def markov_blanket(self, var):
        result = set()
        for key in self.dependencies:
            for dep in self.dependencies[key]:
                mtrue, mfalse, p = dep
                variables = [key]
                variables.extend(mtrue)
                variables.extend(mfalse)
                if var in variables:
                    result.update(variables)
        result.discard(var)  # remove var if it exists
        return list(result)


class MyCS(ConstraintSearch):

    def __init__(self, domains, constraints):
        ConstraintSearch.__init__(self, domains, constraints)

    def propagate(self, domains, var):
        for (x, y) in self.constraints:
            if y == var:
                new_cons = self.constraints[x, y]
                values = len(domains[x])
                domains[x] = [j for j in domains[x] if any(new_cons(x, j, y, val_i) for val_i in domains[y])]
                if len(domains[x]) < values:
                    self.propagate(domains, x)
        return domains

    def cartesian_product(self, l1, l2):
        return itertools.product(*[l1[var] for var in l2])

    def higherorder2binary(self, ho_c_vars, unary_c):  
        aux = ''.join(ho_c_vars)

        self.domains[aux] = [t for t in self.cartesian_product(self.domains, ho_c_vars) if unary_c(t)]

        for i, var in enumerate(ho_c_vars):
            self.constraints[var, aux] = lambda v,vx,av,avx : vx==avx[ho_c_vars.index(v)]
            self.constraints[aux, var] = lambda av,avx,v,vx : vx==avx[ho_c_vars.index(v)]

