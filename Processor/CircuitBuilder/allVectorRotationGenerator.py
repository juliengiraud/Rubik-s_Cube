import json
from numpy import array

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

    moves = ["R", "R'", "U", "U'", "L", "L'", "F", "F'", "D", "D'", "B", "B'"]
    stickerType = ['A', 'C']

    for st in stickerType:
        for move in moves:
            for i in range(1, 25):
                key = str(i) + move
                sol = solutions[st]
                val = sol[key] if key in sol else i
                u = transition[st][str(i)]
                v = moveToFace[move]
                w = transition[st][str(val)]

                print('        Key+value :', st, key, '->', val)
                print('        Sticker :', u)
                print('        Move :   ', v)
                print('        Result : ', w)
                print('')
            print('')
        print('')
