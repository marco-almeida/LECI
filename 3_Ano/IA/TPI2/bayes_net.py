
class BayesNet:

    def __init__(self):
        self.dependencies = {}

    def add(self,var,mtrue,mfalse,p):
        self.dependencies.setdefault(var,[]).append((mtrue,mfalse,p))

    def joint_prob(self,conjunction):
        mtrue, mfalse = conjunction
        result = 1.0
        for var in mtrue+mfalse:
            for (mt,mf,p) in self.dependencies[var]:
                if all(x in mfalse for x in mf) and \
                   all(x in mtrue for x in mt):
                    result *= (p if var in mtrue else 1-p)
        return result
