import json
import numpy as np

def eqSolve(u, v):
    val = v[0] + v[1] + v[2]
    s = 1 if val in {2, -1} else -1

    if v[0] != 0:
        return [ u[0], s*u[2], -s*u[1] ]
    elif v[1] != 0:
        return [ -s*u[2], u[1], s*u[0] ]
    else:
        return [ s*u[1], -s*u[0], u[2] ]

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
