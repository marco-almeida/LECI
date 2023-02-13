#encoding: utf8

class Relation:
    def __init__(self,e1,name,e2):
        self.entity1 = e1
        self.name = name
        self.entity2 = e2
    def __str__(self):
        return self.name + "(" + str(self.entity1) + "," + \
               str(self.entity2) + ")"
    def __repr__(self):
        return str(self)


# Subclasse Association
class Association(Relation):
    def __init__(self,e1,name,e2,card=None,default=None):
        Relation.__init__(self,e1,name,e2)
        self.card = card
        self.default = default
    def __str__(self):
        if self.card==None:
            default=""
        elif self.default==None:
            default = "[=?]"
        else:
            default = "[=" + str(self.default) + "]"
        return self.name + "(" + str(self.entity1) + "," + \
               str(self.entity2) + default + ")"

class Subtype(Relation):
    def __init__(self,sub,super):
        Relation.__init__(self,sub,"subtype",super)


class Member(Relation):
    def __init__(self,obj,type):
        Relation.__init__(self,obj,"member",type)

class Declaration:
    def __init__(self,user,rel):
        self.user = user
        self.relation = rel
    def __str__(self):
        return "decl("+str(self.user)+", "+str(self.relation)+")"
    def __repr__(self):
        return str(self)

class SemanticNetwork:
    def __init__(self,ldecl=[]):
        self.declarations = ldecl
    def __str__(self):
        return my_list2string(self.declarations)
    def insert(self,decl):
        self.declarations.append(decl)
    def query_local(self,user=None,e1=None,rel=None,e2=None):
        self.query_result = \
            [ d for d in self.declarations
                if  (user == None or d.user==user)
                and (e1 == None or d.relation.entity1 == e1)
                and (rel == None or d.relation.name == rel)
                and (e2 == None or d.relation.entity2 == e2) ]
        return self.query_result
    def show_query_result(self):
        for d in self.query_result:
            print(str(d))

    # Seguem-se funcoes para acrescentar novas relações,
    # fazendo as verificações prévias que forem necessárias.
    # Precisariam, para serem completadas, das funcoes que 
    # vai fazer.

    def add_member(self,user,obj,type):
        """
        if self.is_type(user,obj) or self.is_object(user,type):
            return False
        """
        self.insert(Declaration(user,Member(obj,type)))
        return True

    def add_subtype(self,user,subt,supert):
        """
        if self.is_object(user,subt) or self.is_object(user,supert):
            return False
        """
        self.insert(Declaration(user,Subtype(subt,supert)))
        return True

    def add_association(self,user,e1,assoc,e2,card=None,default=None):

        if card=='many' and default != None:
            return False

        """
        if (self.is_object(user,e1) and self.is_type(user,e2)) \
              or  (self.is_type(user,e1) and self.is_object(user,e2)):
            return False

        at = self.infer_signature(user,assoc)
        if at!=None:
            (t1,t2) = at
            pass
        """
        self.insert(Declaration(user,Association(e1,assoc,e2,card,default)))
        return True


# Funcao auxiliar para converter para cadeias de caracteres
# listas cujos elementos sejam convertiveis para
# cadeias de caracteres
def my_list2string(list):
   if list == []:
       return "[]"
   s = "[ " + str(list[0])
   for i in range(1,len(list)):
       s += ", " + str(list[i])
   return s + " ]"
    

