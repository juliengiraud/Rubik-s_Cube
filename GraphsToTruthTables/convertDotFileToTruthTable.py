
def getTruthTableToString(values):
    size = len(values)
    sortie = [" " for i in range(size)]
    for i in range(size):
        nb = values[i]
        nb1 = str(bin(nb[0])[2:]).rjust(5, '0')
        nb2 = str(bin(nb[1])[2:]).rjust(4, '0') + " "
        nb3 = str(bin(nb[2])[2:]).rjust(5, '0')
        s = nb1 + nb2 + nb3
        s = s.replace(" 00000", " 22222")
        sortie[i] = s
    return sortie

def tableContain(i, j, list):
    for line in list:
        if line[0] == i and line[1] == j:
            return line[2]
    return -1

def getTruthTableSize(table, col):
    max = -1
    pow = 1

    # Get max of list
    for line in table:
        nb = line[col - 1]
        if nb > max:
            max = nb

    if max < 0:
        return 0

    # Get first power of 2 greater than max (or equal)
    while pow < max:
        pow = pow * 2

    return pow

def getTruthTable(table):
    input1 = getTruthTableSize(table, 1)
    input2 = getTruthTableSize(table, 2)

    # Create empty truth table
    list = []
    for i in range(input1):
        for j in range(input2):
            list.append([i, j, i])

    # Fill truth table using using parameter of function
    for i in range(input1 * input2): # For each line of list
        e1 = list[i][0]
        e2 = list[i][1]
        s = tableContain(e1, e2, table)
        if s > 0: # if table contains result for current line
            list[i][2] = s

    # We replace impossible exit values by 0
    for i in range(input1 * input2): # For each line of list
        e1 = list[i][0]
        e2 = list[i][1]

        if (e1 == 0 or # There is nothing in 0
                e2 == 0 or # There is no move 0
                e1 > 24 or # There is no piece after 24
                e2 > 12): # There is no move after 12
            list[i][2] = 0

    return list

def getFileNameFromFileSrc(fileSrc):
    fileSrcTab = fileSrc.split("/")
    return fileSrcTab[len(fileSrcTab) - 1]

def getvaluesFromFilename(filename):
    nbsList = []
    with open(filename) as file:
        line = file.readline()
        while line:
            line = file.readline()
            length = len(line)
            if length >= 27 and length <= 29:
                # Format text
                line = line.replace("    ", "")
                line = line.replace("-> ", "")
                line = line.replace("[label=\"", "")
                line = line.replace("\"];", "")
                line = line.replace("A", "")
                line = line.replace("C", "")

                # Convert moves into numbers
                # R -> 1, R' -> 2,  U -> 3,  U' -> 4
                # L -> 5, L' -> 6,  F -> 7,  F' -> 8
                # D -> 9, D' -> 10, B -> 11, B' -> 12
                line = line.replace("R'", "2")
                line = line.replace("R", "1")
                line = line.replace("U'", "4")
                line = line.replace("U", "3")
                line = line.replace("L'", "6")
                line = line.replace("L", "5")
                line = line.replace("F'", "8")
                line = line.replace("F", "7")
                line = line.replace("D'", "10")
                line = line.replace("D", "9")
                line = line.replace("B'", "12")
                line = line.replace("B", "11")

                strnb = line.split(" ")
                nbsList.append([ # Input-Exit-Label -> [0]-[2]-[1]
                    int(strnb[0]),
                    int(strnb[2]),
                    int(strnb[1])
                ])

    return nbsList

if __name__ == "__main__" :
    inputs = {
        1: {
            "file": "../Graphs/Sources/oriented_graph_corners.dot",
            "header": "p1 p2 p3 p4 p5 b1 b2 b3 b4, s1 s2 s3 s4 s5",
            "output": open("output_corners.txt", "w")
        },
        2: {
            "file": "../Graphs/Sources/oriented_graph_edges.dot",
            "header": "p1 p2 p3 p4 p5 b1 b2 b3 b4, s1 s2 s3 s4 s5",
            "output": open("output_edges.txt", "w")
        }
    }

    for key, inp in inputs.items(): # For each DOT file
        # Convert file into str array
        valuesList = getvaluesFromFilename(inp["file"])
        fullList = getTruthTable(valuesList)
        fullStrList = getTruthTableToString(fullList)

        inp["output"].write(inp["header"] + '\n')
        for line in fullStrList:
            inp["output"].write(line + '\n')
