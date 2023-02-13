import sys
from digest import calculate
import sys

d1 = calculate(sys.argv[1])
d2 = calculate(sys.argv[3])

r = ''

for i in range(len(d1)):
    v = bin(d1[i] ^ d2[i])
    v = v[2:]
    v = '0' * (8-len(v)) + v
    r += v

n0 = 0
n1 = 0

for i in r:
    if i == '0': n0 += 1 
    if i == '1': n1 += 1 
print(f"NO: {n0} N1: {n1}")