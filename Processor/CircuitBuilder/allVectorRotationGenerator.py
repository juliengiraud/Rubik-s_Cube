import json
import numpy as np

'''
    u sticker
    v face of the move
    return the equivalent of `np.linalg.norm(np.array(u)-np.array(v))`
'''
def getEcart(u, v):
    # Get the index of the dimension we want to check (the dimension of the rotation)
    for index, value in enumerate(v):
        if value != 0:
            i = index
    # Check if u and v are on the same face (check if they both have the same sign)
    if u[i] * v[i] > 0:
        return 'oui'
    return 'non'

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
    with open("solutions.json") as file:
        solutions = json.load(file)
    with open("generation.json") as file:
        generation = json.load(file)

    coins = generation['coins']
    aretes = generation['aretes']
    transition = solutions['transition']
    faces = transition['faces']
    moveToFace = transition['move-to-face']
    moveToFaceBis = transition['move-to-face-bis']

    moves = ["R", "R'", "U", "U'", "L", "L'", "F", "F'", "D", "D'", "B", "B'"]
    stickerType = ['A', 'C']

    fails = 0

    for st in stickerType:
        for move in moves:
            for i in range(1, 25):
                key = str(i) + move
                sol = solutions[st]
                val = int(sol[key]) if key in sol else i
                u = transition[st][str(i)]
                v = moveToFace[move]
                w = transition[st][str(val)]
                somme = getEcart(u, v)

                change = 'non' if i == val else 'oui'

                if change != somme:
                    fails += 1

                print('        Key+value :', st, key, '->', val, 'Rotation :', change, ',', somme)
                print('        Sticker :', u)
                print('        Move :   ', v)
                print('        Result : ', w)
                print('')
            print('')
        print('')
    print('fails :', fails)
