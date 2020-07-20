import json
from numpy import array

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

def getinfo(tab):
    u1 = 0 if tab[2] == '0' else 1
    u1 += 2 if tab[1] == '1' else 0
    if (u1 > 0):
        u1 *= 1 if tab[0] == '0' else -1

    u2 = 0 if tab[2+3] == '0' else 1
    u2 += 2 if tab[1+3] == '1' else 0
    if (u2 > 0):
        u2 *= 1 if tab[0+3] == '0' else -1



    return [u1, u2]

def getvalbin(val):
    if val == 0:
        return ['0', '0', '0']
    elif val == 1:
        return ['0', '0', '1']
    elif val == 2:
        return ['0', '1', '0']
    elif val == -1:
        return ['1', '0', '1']
    elif val == -2:
        return ['1', '1', '0']

def getvectorbin(u):
    u1 = getvalbin(u[0])
    u2 = getvalbin(u[1])
    u3 = getvalbin(u[2])
    return u1+u2+u3

def ajouteun(tab):
    for i in range(len(tab)):
        if tab[11-i] == '0':
            tab[11-i] = '1'
            break
        tab[11-i] = '0'

if __name__ == "__main__" :

    file=True
    with open("main_data.json") as file:
        data = json.load(file)


    strLine = ['0' for i in range(12)]
    print(strLine, getinfo(strLine))
    for i in range(4095):
        ajouteun(strLine)
        #print(strLine, getinfo(strLine))

    faces = data['faces']
    moveToVector = data['move-to-vector']
    moves = ["R", "R'", "U", "U'", "L", "L'", "F", "F'", "D", "D'", "B", "B'"]
    stickerType = ['E', 'C']

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

                print(''.join(getvectorbin(u) + [' '] + getvectorbin(v) + [' '] + getvectorbin(w)))

