
class ConstraintSearch:

    def __init__(self,domains,constraints):
        self.domains = domains
        self.constraints = constraints
        self.calls = 0

    def search(self,domains=None):

        self.calls += 1 
        
        if domains==None:
            domains = self.domains

        if any([lv==[] for lv in domains.values()]):
            return None

        if all([len(lv)==1 for lv in list(domains.values())]):
            solution = { v:domains[v][0] for v in domains }
            # this "if" is not needed after implementing propagation
            if all( self.constraints[v1,v2](v1,solution[v1],v2,solution[v2])
                    for (v1,v2) in self.constraints ): 
                return solution
 
        for var in domains.keys():
            if len(domains[var])>1:
                for val in domains[var]:
                    newdomains = dict(domains)
                    newdomains[var] = [val]
                    self.propagate(newdomains,var)
                    solution = self.search(newdomains)
                    if solution != None:
                        return solution
        return None

