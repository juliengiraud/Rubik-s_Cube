import json
import numpy as np

def eqSolve(u, v):
    [a,b,c] = u
    [x,y,z] = v

    if [x,y,z] == [1, 0, 0]:
        return [a,-c, b]
    if [x,y,z] == [-2, 0, 0]:
        return [a,-c, b]
    if [x,y,z] == [-1, 0, 0]:
        return [a, c,-b]
    if [x,y,z] == [2, 0, 0]:
        return [a, c,-b]

    if [x,y,z] == [0, 1, 0]:
        return [c, b,-a]
    if [x,y,z] == [0,-2, 0]:
        return [c, b,-a]
    if [x,y,z] == [0,-1, 0]:
        return [-c, b, a]
    if [x,y,z] == [0, 2, 0]:
        return [-c, b, a]

    if [x,y,z] == [0, 0, 1]:
        return [-b, a, c]
    if [x,y,z] == [0, 0,-2]:
        return [-b, a, c]
    if [x,y,z] == [0, 0,-1]:
        return [b,-a, c]
    if [x,y,z] == [0, 0, 2]:
        return [b,-a, c]

    return u

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
