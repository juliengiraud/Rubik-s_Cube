import json

def processor_corners(sticker, move):
    full_input = str(sticker) + move
    graph = {
        "1D'": 2, "1D": 3, "1F": 7, "1R'": 10, "1R": 13, "1F'": 20,
        "2D": 1, "2D'": 4, "2L": 9, "2F'": 12, "2L'": 14, "2F": 15,
        "3D'": 1, "3D": 4, "3R": 5, "3B'": 8, "3R'": 18, "3B": 19,
        "4D": 2, "4D'": 3, "4L'": 6, "4B": 11, "4B'": 16, "4L": 17,
        "5R'": 3, "5F": 6, "5D'": 7, "5D": 11, "5F'": 13, "5R": 21,
        "6L": 4, "6F'": 5, "6D'": 8, "6D": 12, "6F": 14, "6L'": 22,
        "7F'": 1, "7D": 5, "7L": 8, "7D'": 9, "7L'": 15, "7F": 22,
        "8B": 3, "8D": 6, "8L'": 7, "8D'": 10, "8L": 16, "8B'": 24,
        "9L'": 2, "9D": 7, "9B": 10, "9D'": 11, "9B'": 17, "9L": 24,
        "10R": 1, "10D": 8, "10B'": 9, "10D'": 12, "10B": 18, "10R'": 23,
        "11B'": 4, "11D'": 5, "11D": 9, "11R": 12, "11R'": 19, "11B": 23,
        "12F": 2, "12D'": 6, "12D": 10, "12R'": 11, "12R": 20, "12F'": 21,
        "13R'": 1, "13F": 5, "13F'": 14, "13U": 15, "13U'": 19, "13R": 23,
        "14L": 2, "14F'": 6, "14F": 13, "14U": 16, "14U'": 20, "14L'": 24,
        "15F'": 2, "15L": 7, "15U'": 13, "15L'": 16, "15U": 17, "15F": 21,
        "16B": 4, "16L'": 8, "16U'": 14, "16L": 15, "16U": 18, "16B'": 23,
        "17L'": 4, "17B": 9, "17U'": 15, "17B'": 18, "17U": 19, "17L": 22,
        "18R": 3, "18B'": 10, "18U'": 16, "18B": 17, "18U": 20, "18R'": 21,
        "19B'": 3, "19R": 11, "19U": 13, "19U'": 17, "19R'": 20, "19B": 24,
        "20F": 1, "20R'": 12, "20U": 14, "20U'": 18, "20R": 19, "20F'": 22,
        "21R'": 5, "21F": 12, "21F'": 15, "21R": 18, "21U": 22, "21U'": 23,
        "22L": 6, "22F'": 7, "22L'": 17, "22F": 20, "22U'": 21, "22U": 24,
        "23R": 10, "23B'": 11, "23R'": 13, "23B": 16, "23U": 21, "23U'": 24,
        "24B": 8, "24L'": 9, "24L": 14, "24B'": 19, "24U'": 22, "24U": 23,
    }
    return graph.get(full_input, sticker)

def processor_edges(sticker, move):
    full_input = str(sticker) + move
    graph = {
        "1D": 2, "1D'": 3, "1F": 11, "1F'": 16,
        "2D'": 1, "2D": 4, "2R": 9, "2R'": 14,
        "3D": 1, "3D'": 4, "3L'": 10, "3L": 13,
        "4D'": 2, "4D": 3, "4B'": 12, "4B": 15,
        "5D'": 6, "5D": 8, "5F'": 9, "5F": 10,
        "6D": 5, "6D'": 7, "6L'": 11, "6L": 12,
        "7D": 6, "7D'": 8, "7B'": 13, "7B": 14,
        "8D": 7, "8D'": 5, "8R'": 15, "8R": 16,
        "9R'": 2, "9F": 5, "9F'": 17, "9R": 22,
        "10L": 3, "10F'": 5, "10F": 17, "10L'": 23,
        "11F'": 1, "11L": 6, "11L'": 18, "11F": 21,
        "12B": 4, "12L'": 6, "12L": 18, "12B'": 24,
        "13L'": 3, "13B": 7, "13B'": 19, "13L": 23,
        "14R": 2, "14B'": 7, "14B": 19, "14R'": 22,
        "15B'": 4, "15R": 8, "15R'": 20, "15B": 24,
        "16F": 1, "16R'": 8, "16R": 20, "16F'": 21,
        "17F": 9, "17F'": 10, "17U": 18, "17U'": 20,
        "18L": 11, "18L'": 12, "18U'": 17, "18U": 19,
        "19B": 13, "19B'": 14, "19U'": 18, "19U": 20,
        "20R": 15, "20R'": 16, "20U": 17, "20U'": 19,
        "21F'": 11, "21F": 16, "21U'": 22, "21U": 23,
        "22R'": 9, "22R": 14, "22U": 21, "22U'": 24,
        "23L": 10, "23L'": 13, "23U'": 21, "23U": 24,
        "24B": 12, "24B'": 15, "24U": 22, "24U'": 23,
    }
    return graph.get(full_input, sticker)

def int2bin_str(n, s):
    return "{0:b}".format(n).zfill(s)

def get_move_number(move):
    return moves.index(move)+1

def move2bin_str(move):
    return int2bin_str(get_move_number(move), 4)

def validate_gate(myInput, gate):
    bits = ["p1", "p2", "p3", "p4", "p5", "b1", "b2", "b3", "b4"]
    for i in range(9):
        if bits[i] in gate and myInput[i] == "0" or bits[i] + "'" in gate and myInput[i] == "1":
            return False
    return True

def circuit_reader(myInput, expressions):
    # Conversion de l'expression
    circuits = []
    for expression_str in expressions:
        expression = expression_str.replace(" ", "").replace("(", "").replace(")", "").split("+")
        circuit = []
        for gate in expression:
            circuit.append(gate.split("."))
        circuits.append(circuit)
    myExit = ["0", "0", "0", "0", "0"]

    # Utilisation de l'expression
    for i in range(5):
        for gate in circuits[i]: # Pour toutes les portes AND de la colone du circuit
            if validate_gate(myInput, gate): # Si l'entrée valide la porte
                myExit[i] = "1"
                break
    exit_str = myExit[0]+myExit[1]+myExit[2]+myExit[3]+myExit[4]

    return exit_str

moves = ["R", "R'", "U", "U'", "L", "L'", "F", "F'", "D", "D'", "B", "B'"]

if __name__ == "__main__":

    with open("booleanExpression.json") as f_circuits:
        circuits = json.load(f_circuits)

    debug = False
    corners = circuits[0]["corners"]
    edges = circuits[0]["edges"]
    isGood = True
    for i in range(1, 25):
        for j in moves:
            sticker = int2bin_str(i, 5)
            move = move2bin_str(j)
            myInput = sticker + move
            exit_corners = circuit_reader(myInput, corners)
            exit_edges = circuit_reader(myInput, edges)
            exit_i_want_corners = int2bin_str(processor_corners(i, j), 5)
            exit_i_want_edges = int2bin_str(processor_edges(i, j), 5)
            if exit_corners == exit_i_want_corners and exit_edges == exit_i_want_edges:
                myExit = "GOOD"
            else:
                myExit = "BAD"
                isGood = False
            if debug:
                print("Entrée humaine :", i, j.ljust(2, " "),
                    "   Entrée machine :", sticker, move, myInput,
                    "   Sortie obtenue :", exit_corners, exit_edges,
                    "   Sortie souhaitée :", exit_i_want_corners, exit_i_want_edges,
                    "   ", myExit)
    if isGood:
        print("Le circuit fonctionne")
    else:
        print("Le circuit ne fonctionne pas")
