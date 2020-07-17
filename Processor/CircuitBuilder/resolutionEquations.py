import json
import numpy as np

def eqSolve(u, v):
    if v[0] != 0:
        dim = 0
    elif v[1] != 0:
        dim = 1
    else:
        dim = 2

    sign = 1
    if v[dim] == 1 or v[dim] == -2:
        sign = -1

    w = [0,0,0]

    w[dim] = u[dim]
    if dim == 0:
        w[dim+1] = sign * u[dim+2]
        w[dim+2] = -sign * u[dim+1]

    if dim == 1:
        w[dim+1] = sign * u[dim-1]
        w[dim-1] = -sign * u[dim+1]

    if dim == 2:
        w[dim-2] = sign * u[dim-1]
        w[dim-1] = -sign * u[dim-2]



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
