
from collections import Counter
# Guiao de representacao do conhecimento
# -- Redes semanticas
#
# Inteligencia Artificial & Introducao a Inteligencia Artificial
# DETI / UA
#
# (c) Luis Seabra Lopes, 2012-2020
# v1.9 - 2019/10/20
#


# Classe Relation, com as seguintes classes derivadas:
#     - Association - uma associacao generica entre duas entidades
#     - Subtype     - uma relacao de subtipo entre dois tipos
#     - Member      - uma relacao de pertenca de uma instancia a um tipo
#

class Relation:
    def __init__(self, e1, rel, e2):
        self.entity1 = e1
#       self.relation = rel  # obsoleto
        self.name = rel
        self.entity2 = e2

    def __str__(self):
        return self.name + "(" + str(self.entity1) + "," + \
            str(self.entity2) + ")"

    def __repr__(self):
        return str(self)


# Subclasse Association
class Association(Relation):
    def __init__(self, e1, assoc, e2):
        Relation.__init__(self, e1, assoc, e2)

#   Exemplo:
#   a = Association('socrates','professor','filosofia')

# Subclasse Subtype


class Subtype(Relation):
    def __init__(self, sub, super):
        Relation.__init__(self, sub, "subtype", super)


#   Exemplo:
#   s = Subtype('homem','mamifero')

# Subclasse Member
class Member(Relation):
    def __init__(self, obj, type):
        Relation.__init__(self, obj, "member", type)

#   Exemplo:
#   m = Member('socrates','homem')

class AssocOne(Relation):
    # associaçoes com apenas um valor
    # e.g pai
    def __init__(self, obj, assoc, value):
        Relation.__init__(self, obj, assoc, value)

class AssocNum(Relation):
    def __init__(self, e1, e2, num):
        Relation.__init__(self, e1, e2, num)
# classe Declaration
# -- associa um utilizador a uma relacao por si inserida
#    na rede semantica
#


class Declaration:
    def __init__(self, user, rel):
        self.user = user
        self.relation = rel

    def __str__(self):
        return "decl("+str(self.user)+","+str(self.relation)+")"

    def __repr__(self):
        return str(self)

#   Exemplos:
#   da = Declaration('descartes',a)
#   ds = Declaration('darwin',s)
#   dm = Declaration('descartes',m)

# classe SemanticNetwork
# -- composta por um conjunto de declaracoes
#    armazenado na forma de uma lista
#


class SemanticNetwork:
    def __init__(self, ldecl=None):
        self.declarations = [] if ldecl == None else ldecl

    def __str__(self):
        return str(self.declarations)

    def insert(self, decl):
        self.declarations.append(decl)

    def query_local(self, user=None, e1=None, rel=None, e2=None):
        self.query_result = \
            [d for d in self.declarations
                if (user == None or d.user == user)
                and (e1 == None or d.relation.entity1 == e1)
                and (rel == None or d.relation.name == rel)
                and (e2 == None or d.relation.entity2 == e2)]
        return self.query_result

    def show_query_result(self):
        for d in self.query_result:
            print(str(d))

    def list_associations(self):  # ex 1
        return set([d.relation.name for d in self.declarations if isinstance(d.relation, Association)])

    def list_objects(self):  # ex 2
        return set([d.relation.entity1 for d in self.declarations if isinstance(d.relation, Member)])

    def list_users(self):  # ex 3
        return set([d.user for d in self.declarations])

    def list_types(self):  # ex 4
        return set([d.relation.entity2 for d in self.declarations if isinstance(d.relation, Subtype) or isinstance(d.relation, Member)])

    def list_local_associations(self, entity: str):  # ex 5
        return set([d.relation.name for d in self.query_local(user=None, e1=entity, rel=None, e2=None) if isinstance(d.relation, Association)])

    def list_relations_by_user(self, entity: str):   # ex 6
        return set([d.relation.name for d in self.query_local(user=entity, e1=None, rel=None, e2=None)])

    def associations_by_user(self, entity: str):  # ex 7
        return len(set([d.relation.name for d in self.query_local(user=entity, e1=None, rel=None, e2=None) if isinstance(d.relation, Association)]))

    def list_local_associations_by_user(self, entity: str):   # ex 8
        return set([(d.relation.name, d.user) for d in set([d for d in self.declarations if isinstance(d.relation, Association) and d.relation.entity1 == entity])])

    def predecessor(self, pre1: str, pre2: str):  # ex 9
        '''if pre1 (e.g vertebrado) and pre2 (e.g socrates) -> returns true
        '''
        if pre1 == pre2:
            return True
        for d in self.declarations:
            if (isinstance(d.relation, Member) or isinstance(d.relation, Subtype)) and d.relation.entity2 == pre1:
                if self.predecessor(d.relation.entity1, pre2):
                    return True
        return False

    def predecessor_path(self, pre1: str, pre2: str):  # ex 10
        if pre1 == pre2:
            return [pre1]
        for d in self.declarations:
            if (isinstance(d.relation, Member) or isinstance(d.relation, Subtype)) and d.relation.entity2 == pre1:
                if self.predecessor(d.relation.entity1, pre2):
                    return [pre1] + self.predecessor_path(d.relation.entity1, pre2)
        return []

    def query(self, e1: str, assoc_name: str = None):  # ex 11 a)
        stunf = []
        for d in self.declarations:
            if isinstance(d.relation, Association) or isinstance(d.relation, Member):
                if assoc_name != None:
                    if d.relation.name == assoc_name and self.predecessor(d.relation.entity1, e1):
                        stunf.append(d)
                else:
                    if self.predecessor(d.relation.entity1, e1):
                        stunf.append(d)
        return stunf

    def query2(self, e1: str, assoc_name: str = None):  # ex 11 b)
        stunf = []
        for d in self.declarations:
            if isinstance(d.relation, Member) or isinstance(d.relation, Subtype):
                if e1 == d.relation.entity1:
                    stunf.append(d)
        stunf.extend(self.query(e1, assoc_name))
        return stunf

    def query_cancel(self, e1: str, assoc_name: str):  # ex 12
        filho = ""
        for d in self.declarations:
            if isinstance(d.relation, Member) and d.relation.entity1 == e1:
                filho = d.relation.entity2
                break
        return [d for d in self.declarations if isinstance(d.relation, Association) and d.relation.entity1 == filho and d.relation.name == assoc_name]

    def query_down(self, type: str, assoc_name: str): # ex 13
        stunf = []
        for d in self.declarations:
            if (isinstance(d.relation, Member) or isinstance(d.relation, Subtype)) and type == d.relation.entity2: # if is predecessor
                for dec in self.declarations: # append own associations (with assoc_name), then go to next child
                    if isinstance(dec.relation, Association) and dec.relation.name == assoc_name and dec.relation.entity1 == d.relation.entity1:
                        stunf.append(dec)
                return stunf + self.query_down(d.relation.entity1, assoc_name)
        return stunf
    
    def query_induce(self, e1: str, assoc_name: str): # ex 14
        lst = [dec.relation.entity2 for dec in self.query_down(e1, assoc_name)]
        # get most common
        return max(set(lst), key=lst.count)

    def query_local_assoc(self, entity: str, assoc_name: str): # ex 15
        local_decl = self.query_local(e1 = entity, rel = assoc_name)

        for l in local_decl:
            if isinstance(l.relation, Association):
                c = Counter([la.relation.entity2 for la in local_decl])

                local_val = []
                for common in c.most_common():
                    local_val.append(common)
                    if sum([c/len(local_decl) for dummy,c in local_val]) > 0.75:
                        return [(val, c/len(local_decl)) for val, c in local_val]

            if isinstance(l.relation, AssocOne):
                c = Counter([la.relation.entity2 for la in local_decl])

                val, count = c.most_common(1)[0]
                return val, count/len(local_decl)

            if isinstance(l.relation, AssocNum):
                return sum([la.relation.entity2 for la in local_decl])/len(local_decl)
    
    def query_assoc_value(self, e1: str, assoc: str):
        return "bruh"
            


            

                    
                
