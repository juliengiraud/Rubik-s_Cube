import json
import numpy as np

def eqSolve(u, v):
    if v[0] != 0:
        dim = 0
    elif v[1] != 0:
        dim = 1
    else:
        dim = 2

    if v[dim] < 0:
        v[dim] += 3

    [a,b,c] = u
    [x,y,z] = v

    w = [0,0,0]


    if [x,y,z] == [1, 0, 0]:
        w = [a,-c, b]
    if [x,y,z] == [2, 0, 0]:
        w = [a, c,-b]

    if [x,y,z] == [0, 1, 0]:
        w = [c, b,-a]
    if [x,y,z] == [0, 2, 0]:
        w = [-c, b, a]

    if [x,y,z] == [0, 0, 1]:
        w = [-b, a, c]
    if [x,y,z] == [0, 0, 2]:
        w = [b,-a, c]

    w[dim] = u[dim]

    return w

if __name__ == "__main__" :

    file=True
    with open("equations.json") as file:
        equations = json.load(file)

    eqA = equations['A']['clockwise'] + equations['A']['counterclockwise']

    fails = 0
    count = 0

    for eq in eqA:
        count += 1
        if eqSolve(eq[0], eq[1]) != eq[2]:
            fails += 1
    print(fails, count)
