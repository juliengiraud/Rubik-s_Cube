import json

def getValueToBinary(val):
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

def getVectorToBinary(u):
    u1 = getValueToBinary(u[0])
    u2 = getValueToBinary(u[1])
    u3 = getValueToBinary(u[2])
    return u1+u2+u3

def addOneBit(tab):
    for i in range(len(tab)):
        if tab[len(tab)-1-i] == '0':
            tab[len(tab)-1-i] = '1'
            break
        tab[len(tab)-1-i] = '0'

def getEmptyTruthTable():
    table = []
    n = 18
    line = ['0' for i in range(n)]
    table.append([''.join(line), "222222222"])
    for i in range(2**n - 1):
        addOneBit(line)
        table.append([''.join(line), "222222222"])
    return table

def getTruthTableResults():
    table = []

    file=True
    with open("main_data.json") as file:
        data = json.load(file)

    stickerType = data['stickerType']
    moves = data['moves']
    moveToVector = data['moveToVector']

    for st in stickerType:
        for move in moves:
            for i in range(1, 25):
                key = str(i) + move
                sol = data[st]
                val = int(sol[key]) if key in sol else i
                u = data[st][str(i)]
                v = moveToVector[move]
                w = data[st][str(val)]
                table.append([''.join(getVectorToBinary(u) + getVectorToBinary(v)), ''.join(getVectorToBinary(w))])
    return table

def getTruthTable():
    table = getEmptyTruthTable()
    results = getTruthTableResults()

    for result in results:
        for row in table:
            if row[0] == result[0]:
                row[1] = result[1]
    return table

if __name__ == "__main__" :
    table = getTruthTable()
    output = open("output.txt","w")
    for row in table:
        output.write(row[0] + ' ' + row[1] + '\n')
