import json
from numpy import array

if __name__ == "__main__" :

    file=True
    with open("stickers_vectors_generation.json") as file:
        generation = json.load(file)
    with open("main_data.json") as file:
        data = json.load(file)

    corners = generation['C']
    edges = generation['E']
    faces = data['faces']

    print('{\n    "C": {')
    i = 0
    for corner in corners:
        i += 1
        val = array([0, 0, 0])
        for letter in corner:
            if letter != ' ':
                val += faces[letter]
        print('        "' + str(i) + '": [' + str(val[0]) + ', ' + str(val[1]) + ', ' + str(val[2]) + ('],' if i < 24 else ']'))

    print('    },\n    "E": {')
    i = 0
    for edge in edges:
        i += 1
        val = array([0, 0, 0])
        for letter in edge:
            if letter != ' ':
                val += faces[letter]
        print('        "' + str(i) + '": [' + str(val[0]) + ', ' + str(val[1]) + ', ' + str(val[2]) + ('],' if i < 24 else ']'))
    print('    }\n}')
