#encoding: utf8

from tpi2 import *

# ----------------------------------------------------------------------
# Semantic Networks
# ----------------------------------------------------------------------


z = MySN()

z.add_association('descartes','socrates','professor','filosofia')
z.add_subtype('descartes','mamifero','vertebrado')
z.add_subtype('descartes','homem','mamifero')
z.add_association('descartes','socrates','professor','matematica')
z.add_member('descartes','platao','homem')
z.add_association('descartes','platao','professor','filosofia')
z.add_association('descartes','socrates','peso',80)
z.add_member('descartes','socrates','homem')
z.add_member('descartes', 'aristoteles','homem')
z.add_association('descartes','mamifero','altura','number','one',1.2)
z.add_association('descartes','socrates','altura',1.85)

z.add_subtype('darwin','homem','mamifero')
z.add_subtype('darwin','mamifero','vertebrado')

z.add_association('simao','socrates','professor','matematica')
z.add_association('simao','platao','professor','filosofia')

z.add_association('simoes','socrates','professor','matematica')

z.add_member('damasio','socrates','filosofo')

#z.query_local()
#z.show_query_result()

print("\n-- ## Ex. 1.a) --------------------------------")

print(z.is_object('descartes','socrates')) # True
print(z.is_object('descartes','homem')) # False
print(z.is_object('descartes','merkel')) # False
print(z.is_object('descartes','filosofia')) # True

print("\n-- ## Ex. 1.b) --------------------------------")

print(z.is_type('descartes','socrates')) # False
print(z.is_type('descartes','homem')) # True
print(z.is_type('descartes','number')) # True
print(z.is_type('descartes','reptil')) # False

print("\n-- ## Ex. 1.c) --------------------------------")

print(z.infer_type('descartes','platao')) # homem
print(z.infer_type('descartes','marx')) # None
print(z.infer_type('descartes','filosofia')) # __unknown__
print(z.infer_type('descartes',1.85)) # number
print(z.infer_type('descartes','socrates')) # homem

print()

print(z.infer_signature('descartes','professor')) # ('homem', '__unknown__')
print(z.infer_signature('descartes','amigo')) # None
print(z.infer_signature('descartes','altura')) # ('mamifero', 'number')

print()
z.add_association('descartes','homem','pai_de','pessoa','many')
print(z.infer_signature('descartes','pai_de')) # ('homem', 'pessoa')
z.add_association('descartes','platonico','pai_de','socratica')
print(z.infer_type('descartes','platonico')) # homem
print(z.infer_type('descartes','socratica')) # pessoa
z.add_association('descartes','socratica','vive_em','atenas')
print(z.infer_signature('descartes','vive_em')) # ('pessoa', '__unknown__')

print("..........................................................")

# ----------------------------------------------------------------------
# Bayesian Networks
# ----------------------------------------------------------------------


print("\n-- ## Ex. 2 --------------------------------")

bn = MyBN()

bn.add('a',[],[],0.003)

bn.add('b_a',[],[],0.002)

bn.add('c_s',['a'],[],0.48)
bn.add('c_s',[],['a'],0.08)

bn.add('d',[],[],0.01)

bn.add('m_f',[],[],0.01)

bn.add('b_v',['c_s','b_a'],[],0.18)
bn.add('b_v',['c_s'],['b_a'],0.02)
bn.add('b_v',['b_a'],['c_s'],0.90)
bn.add('b_v',[],['c_s','b_a'],0.68)

bn.add('s_m',[],[],0.05)

bn.add('s_p',[],[],0.3)

bn.add('v_p',['m_f','d','b_v'],[],0.003)
bn.add('v_p',['m_f','d'],['b_v'],0.12)
bn.add('v_p',['m_f','b_v'],['d'],0.08)
bn.add('v_p',['m_f'],['d','b_v'],0.01)
bn.add('v_p',['d','b_v'],['m_f'],0.04)
bn.add('v_p',['d'],['m_f','b_v'],0.07)
bn.add('v_p',['b_v'],['m_f','d'],0.13)
bn.add('v_p',[],['m_f','d','b_v'],0.09)

bn.add('h',['b_v'],[],0.44)
bn.add('h',[],['b_v'],0.89)

bn.add('s_s',['s_m','m_f','b_v'],[],0.30)
bn.add('s_s',['s_m','m_f'],['b_v'],0.21)
bn.add('s_s',['s_m','b_v'],['m_f'],0.34)
bn.add('s_s',['m_f','b_v'],['s_m'],0.15)
bn.add('s_s',['s_m'],['m_f','b_v'],0.12)
bn.add('s_s',['m_f'],['s_m','b_v'],0.14)
bn.add('s_s',['b_v'],['s_m','m_f'],0.132)
bn.add('s_s',[],['s_m','m_f','b_v'],0.44)

bn.add('s_t',['d'],[],0.08)
bn.add('s_t',[],['d'],0.002)

bn.add('s_q',['s_p','v_p'],[],0.008)
bn.add('s_q',['s_p'],['v_p'],0.4)
bn.add('s_q',['v_p'],['s_p'],0.51)
bn.add('s_q',[],['s_p','v_p'],0.13)

bn.add('f_s',[],[],0.1)

bn.add('c_c',['s_s'],[],0.49)
bn.add('c_c',[],['s_s'],0.023)

bn.add('car_s',['c_c','s_t','s_q' ,'f_s' ],[],0.091)
bn.add('car_s',['c_c','s_t','s_q'],['f_s'],0.081)
bn.add('car_s',['c_c','s_t','f_s'],['s_q'],0.045)
bn.add('car_s',['s_t','s_q','f_s'],['c_c'],0.052)
bn.add('car_s',['c_c','f_s','s_q'],['s_t'],0.087)
bn.add('car_s',['c_c','s_t'],['s_q' ,'f_s'],0.065)
bn.add('car_s',['c_c','s_q'],['s_t','f_s'],0.043)
bn.add('car_s',['c_c','f_s'],['s_t','s_q'],0.035)
bn.add('car_s',['s_t','s_q'],['c_c','f_s'],0.054)
bn.add('car_s',['s_t','f_s'],['c_c','s_q'],0.056)
bn.add('car_s',['s_q','f_s'],['c_c','s_t'],0.045)
bn.add('car_s',['c_c'],['s_t','s_q','f_s'],0.067)
bn.add('car_s',['s_t'],['c_c','s_q','f_s'],0.078)
bn.add('car_s',['s_q'],['c_c','s_t','f_s'],0.031)
bn.add('car_s',['f_s'],['c_c','s_t','s_q'],0.034)
bn.add('car_s',[],['c_c','s_t','s_q','f_s'],0.023)


print(bn.markov_blanket('s_t')) # ['d', 'car_s', 'f_s', 's_q', 'c_c']
print(bn.markov_blanket('c_s')) # ['a', 'b_v', 'b_a']


print("..........................................................")

# ----------------------------------------------------------------------
# Semantic Networks
# ----------------------------------------------------------------------

print("\n-- ## Ex. 3.a) --------------------------------")

# 4-queens problem

variables = ['R1','R2','R3','R4']

columns = [ 1,2,3,4 ]

domains = { v:columns for v in variables }

edges = [ (v1,v2) for v1 in variables for v2 in variables if v1!=v2]

def no_attack(v1,x1,v2,x2):
    if x1==x2:
        return False
    l1 = int(v1[1:])
    l2 = int(v2[1:])
    if abs(l1-l2)==abs(x1-x2):
        return False
    return True

constraints = { e:no_attack for e in edges }



queens = MyCS(domains,constraints)
print('solution:',queens.search())
print('calls:',queens.calls)

# -----------------------------------

print("\n-- ## Ex. 3.b) --------------------------------")

# TWO+TWO=FOUR puzzle
#
#      (X2 )  (X1 )
#        T     W    O
#    +   T     W    O
#   -------------------
#    F   O     U    R
#

variables = [ 'T', 'W', 'O', 'U', 'R', 'F' ]

digits = list(range(10))

domains = { v:digits for v in variables }

domains['X1'] = [ 0, 1 ]
domains['X2'] = [ 0, 1 ]

edges = [ (v1,v2) for v1 in variables for v2 in variables if v1!=v2 ]

constraints = { e:(lambda v1,x1,v2,x2: x1!=x2) for e in edges }

puzzle = MyCS(domains,constraints)

# 2*O = R + 10*X1
puzzle.higherorder2binary([ 'O','R','X1' ],
      lambda t : 2*t[0] == t[1] + 10*t[2])

# 2*W + X1 = U + 10*X2
puzzle.higherorder2binary([ 'W','X1','U', 'X2' ],
      lambda t : 2*t[0] + t[1] == t[2] + 10*t[3])

# 2*T + X2 = O + 10*F
puzzle.higherorder2binary([ 'T','X2','O', 'F' ],
      lambda t : 2*t[0] + t[1] == t[2] + 10*t[3])

#print(puzzle.domains)
#print(puzzle.constraints)

print('number of variables:',len(puzzle.domains))
print('number of binary constrains:',len(puzzle.constraints))

print('ORX1:',puzzle.domains['ORX1'])
print('WX1UX2:',puzzle.domains['WX1UX2'])
print('TX2OF:',puzzle.domains['TX2OF'])

print('TX2OF==(0, 3, 3, 0) consistent with O==3?')
print(puzzle.constraints['TX2OF', 'O']('TX2OF', (0, 3, 3, 0), 'O', 3)) # True

print('solution:',puzzle.search())
print('calls:',puzzle.calls)
