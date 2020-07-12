import json
from numpy import array

if __name__ == "__main__" :

    file=True
    with open("generation.json") as file:
        generation = json.load(file)
    with open("solutions.json") as file:
        solutions = json.load(file)

    coins = generation['coins']
    aretes = generation['aretes']
    faces = solutions['transition']['faces']

    print('{\n    "C": {')
    i = 0
    for coin in coins:
        i += 1
        val = array([0, 0, 0])
        for lettre in coin:
            if lettre != ' ':
                val += faces[lettre]
        print('        "' + str(i) + '": [' + str(val[0]) + ', ' + str(val[1]) + ', ' + str(val[2]) + ('],' if i < 24 else ']'))

    print('    },\n    "A": {')
    i = 0
    for arete in aretes:
        i += 1
        val = array([0, 0, 0])
        for lettre in arete:
            if lettre != ' ':
                val += faces[lettre]
        print('        "' + str(i) + '": [' + str(val[0]) + ', ' + str(val[1]) + ', ' + str(val[2]) + ('],' if i < 24 else ']'))
    print('    }\n}')
