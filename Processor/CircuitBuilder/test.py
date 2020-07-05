from enum import Enum
import json

class UL(Enum): # Universal Language

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
    EDGE = 'A'

class Sticker:
    with open("solutions.json") as file:
        solutions = json.load(file)

    def __init__(self, stickerType, position):
        self.stickerType = stickerType
        self.position = position
        self.name = stickerType.value + str(position)
        self.testPosition = self.transition[self.name]

    def rotate(self, move):
        key = str(self.position) + move.value.name
        solutions = self.solutions[self.stickerType.value]
        if key in solutions:
            self.position = solutions[key]

    def toString(self):
        return self.name + '=' + str(self.position)

class Cube:
    def __init__(self):
        self.corners = [Sticker(StickerType.CORNER, i) for i in range(1, 25)]
        self.edges = [Sticker(StickerType.EDGE, i) for i in range(1, 25)]

    def rotate(self, move):
        for sticker in self.corners + self.edges:
            sticker.rotate(move)

    def toString(self):
        return ' '.join([sticker.toString() for sticker in self.corners]) \
                + "\n" + \
                ' '.join([sticker.toString() for sticker in self.edges])


if __name__ == "__main__" :
    cube = Cube()
    print('Cube initiale\n' + cube.toString() + '\n')

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
    #print('Cube après rotation R\n' + cube.toString())

    print('Cube après beaucoup de rotations\n' + cube.toString())
