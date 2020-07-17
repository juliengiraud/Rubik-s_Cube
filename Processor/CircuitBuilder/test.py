from enum import Enum
import json
from numpy import array

class UL(Enum): # Langage universel

    class Move:
        moves = ["R", "R'", "U", "U'", "L", "L'", "F", "F'", "D", "D'", "B", "B'"]

        def __init__(self, move):
            self.value = self.moves.index(move)+1 # R=1, R'=2, ..., B'=12
            self.name = move

    R = Move("R")
    R3 = Move("R'")
    U = Move("U")
    U3 = Move("U'")
    L = Move("L")
    L3 = Move("L'")
    F = Move("F")
    F3 = Move("F'")
    D = Move("D")
    D3 = Move("D'")
    B = Move("B")
    B3 = Move("B'")

class StickerType(Enum):
    CORNER = 'C'
    EDGE = 'E'

class Sticker:
    file=True
    with open("main_data.json") as file:
        data = json.load(file)

    def __init__(self, stickerType, position):
        self.stickerType = stickerType
        self.position = position
        self.name = stickerType.value + str(position)
        self.solveData = self.data[stickerType.value][str(position)]

    def rotate(self, move):
        key = str(self.position) + move.value.name
        data = self.data[self.stickerType.value]
        if key in data:
            self.position = data[key]

    def toString(self, debug=False):
        string = self.name + '=' + str(self.position)
        if debug:
            u = self.solveData
            string += ', (' + str(u[0]) + ', ' + str(u[1]) + ', ' + str(u[2]) + ')'
        return string

    def check(self):
        return self.name == self.stickerType.value + str(self.position)

class Cube:
    def __init__(self):
        self.corners = [Sticker(StickerType.CORNER, i) for i in range(1, 25)]
        self.edges = [Sticker(StickerType.EDGE, i) for i in range(1, 25)]

    def rotate(self, move):
        for sticker in self.corners + self.edges:
            sticker.rotate(move)

    def toString(self, debug=False):
        joinParam = '\n' if debug else ' '
        return joinParam.join([sticker.toString(debug=debug) for sticker in self.corners]) \
                + "\n" + \
                joinParam.join([sticker.toString(debug=debug) for sticker in self.edges])

    def check(self):
        for sticker in self.corners + self.edges:
            if not sticker.check():
                return False
        return True

def testRotate(cube):
    cube.rotate(UL.R)
    cube.rotate(UL.U)
    cube.rotate(UL.L)
    cube.rotate(UL.F)
    cube.rotate(UL.D)
    cube.rotate(UL.B)
    cube.rotate(UL.B3)
    cube.rotate(UL.D3)
    cube.rotate(UL.F3)
    cube.rotate(UL.L3)
    cube.rotate(UL.U3)
    cube.rotate(UL.R3)

if __name__ == "__main__" :
    cube = Cube()
    print('Cube initiale\n' + cube.toString() + '\n')

    print ('État avant test : ' + ('refait' if cube.check() else 'mélangé'))
    testRotate(cube)
    print ('État après test : ' + ('refait' if cube.check() else 'mélangé'))

    print('Cube de débug\n' + cube.toString(debug=True) + '\n')
