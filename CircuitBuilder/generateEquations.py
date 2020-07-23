import json
import numpy as np

'''
    u sticker
    v face of the move
'''
def getEcart(u, v):
    # Get the index of the dimension we want to check (the dimension of the rotation)
    for index, value in enumerate(v):
        if value != 0:
            i = index
    # Check if u and v are on the same face
    if u[i] == v[i] :
        return 0
    # Check if u is on the layer of v (it means they have the same sign)
    if u[i] * v[i] > 0:
        return 1
    return 2

def eqSolve(u, v):
    val = v[0] + v[1] + v[2]
    s = 1 if val in {2, -1} else -1

    if v[0] != 0:
        return [ u[0], s*u[2], -s*u[1] ]
    elif v[1] != 0:
        return [ -s*u[2], u[1], s*u[0] ]
    else:
        return [ s*u[1], -s*u[0], u[2] ]

def getResult(u, v):
    ecart = getEcart(u, v)
    # Nothing to change
    if ecart == 2:
        return u
    return eqSolve(u, v)

def getPiece(u):
    v = np.array([0, 0, 0])
    for i in range(len(u)):
        val = u[i]
        if val < -1 or val > 1:
            val /= 2
        v[i] = val
    return v

if __name__ == "__main__" :

    file=True
    with open("main_data.json") as file:
        data = json.load(file)
    with open("stickers_vectors_generation.json") as file:
        generation = json.load(file)

    corners = generation['C']
    aretes = generation['E']
    faces = data['faces']
    moveToVector = data['moveToVector']

    moves = ["R", "R'", "U", "U'", "L", "L'", "F", "F'", "D", "D'", "B", "B'"]
    stickerType = ['E', 'C']

    fails = 0

    dictTransition = {}
    dictTransition['E'] = {}
    dictTransition['C'] = {}
    dictTransition['E']['clockwise'] = []
    dictTransition['E']['counterclockwise'] = []
    dictTransition['C']['clockwise'] = []
    dictTransition['C']['counterclockwise'] = []

    for st in stickerType:
        for move in moves:
            for i in range(1, 25):
                key = str(i) + move
                sol = data[st]
                val = int(sol[key]) if key in sol else i
                u = data[st][str(i)]
                v = moveToVector[move]
                w = data[st][str(val)]
                testResult = getResult(u, v)
                ecart = getEcart(u, v)
                direction = 'clockwise' if move in ["R", "U", "L", "F", "D", "B"] else 'counterclockwise'

                change = i != val

                if testResult != w:
                    fails += 1
                    dictTransition[st][direction].append([u, v, w])
                    # print('Key+value :', st, key, '->', val, ', Rotation type :', ecart)
                    # print('Sticker :    ', u)
                    # print('Move :       ', v)
                    # print('Result :     ', w)
                    # print('TestResult : ', testResult)
                    print('')
    print('fails :', fails)
    print(dictTransition)
