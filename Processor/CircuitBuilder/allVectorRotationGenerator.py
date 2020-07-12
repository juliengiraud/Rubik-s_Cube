import json
from numpy import array

def getFaceName(u):
    if (u[0] == -2):
        return 'N'
    if (u[0] == 2):
        return 'J'
    if (u[1] == -2):
        return 'R'
    if (u[1] == 2):
        return 'O'
    if (u[2] == -2):
        return 'B'
    if (u[2] == 2):
        return 'V'

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

    moves = ["R", "R'", "U", "U'", "L", "L'", "F", "F'", "D", "D'", "B", "B'"]
    stickerType = ['A', 'C']

    for st in stickerType:
        print('StickerType :', st)
        for i in range(1, 25):
            print('    Number :', i)
            for move in moves:
                print('        Move :', move)
                key = str(i) + move
                sol = solutions[st]
                val = sol[key] if key in sol else i
                print('        Key :', key)
                print('        Val :', val)
                u = transition[st][str(i)]
                v = faces[getFaceName(u)]
                w = transition[st][str(val)]
                recap = '(' + str(u[0]) + ', ' + str(u[1]) + ', ' + str(u[2]) + ')\n      + '
                recap += '(' + str(v[0]) + ', ' + str(v[1]) + ', ' + str(v[2]) + ')\n      = '
                recap += '(' + str(w[0]) + ', ' + str(w[1]) + ', ' + str(w[2]) + ')'
                print('        Recap :\n       ', recap)
                print('')
            print('')
        print('')
