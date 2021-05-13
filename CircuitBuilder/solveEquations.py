import json

def eqSolve(u, v):
    """Returns the new position of a sticker.

    Keyword arguments:
    u -- position of the sticker before moving
    v -- move to use
    """
    val = v[0] + v[1] + v[2]
    s = 1 if val in {2, -1} else -1

    if v[0] != 0:
        return [ u[0], s*u[2], -s*u[1] ]
    elif v[1] != 0:
        return [ -s*u[2], u[1], s*u[0] ]
    else:
        return [ s*u[1], -s*u[0], u[2] ]

class Value:
    def __init__(self, b2, b1, b0):
        self.b2 = b2
        self.b1 = b1
        self.b0 = b0

    def fromNumber(value):
        if value == -2:
            return Value(True, True, False)
        if value -1:
            return Value(True, False, True)
        if value == 0:
            return Value(False, False, False)
        if value == 1:
            return Value(False, False, True)
        if value == 2:
            return Value(False, True, False)

    def toNumber(self):
        if self.b2 and self.b1 and not self.b0:
            return -2
        elif self.b2 and not self.b1 and self.b0:
            return -1;
        elif not self.b2 and not self.b1 and not self.b0:
            return 0;
        elif not self.b2 and not self.b1 and self.b0:
            return 1;
        elif not self.b2 and self.b1 and not self.b0:
            return 2

    def toString(self):
        return str(Value.toNumber(self))

class Vector:
    def __init__(self, x: Value, y: Value, z: Value):
        self.x = x #  -2,  -1,   0,   1,   2
        self.y = y # 110, 101, 000, 001, 010
        self.z = z

    def fromNumbers(tab):
        return Vector(
            Value.fromNumber(tab[0]),
            Value.fromNumber(tab[1]),
            Value.fromNumber(tab[2])
        )

    def toNumbers(self):
        return [self.x.toNumber(), self.y.toNumber(), self.z.toNumber()]

    def toString(self):
        return '[ ' + self.x.toString() + ', ' + self.y.toString() + ', ' + self.z.toString() + ' ]'

def eqSolveBool(u, v):
    moveValue = Value( # Somme des valeurs de v
        v.x.b2 or  v.y.b2 or  v.z.b2, # Somme des bits de poids 2 de v
        v.x.b1 or  v.y.b1 or  v.z.b1, # Somme des bits de poids 1 de v
        v.x.b0 or  v.y.b0 or  v.z.b0 # Somme des bits de poids 0 de v
    )

    # S est True si move vaut -1 ou 2
    s = (
            moveValue.b2 and not moveValue.b1 and moveValue.b0 # move == -1
        or
            not moveValue.b2 and moveValue.b1 and not moveValue.b0 # move == 2
    )

    condSortie1 = v.x.b0 or  v.x.b1 or  v.x.b2
    condSortie2 = v.y.b0 or  v.y.b1 or  v.y.b2
    condSortie3 = v.z.b0 or  v.z.b1 or  v.z.b2

    uxIsNotZero = u.x.b1 or  u.x.b0
    uyIsNotZero = u.y.b1 or  u.y.b0
    uzIsNotZero = u.z.b1 or  u.z.b0

    xValue = Value(
        condSortie1 and u.x.b2 or  # u[0] sur b2
        condSortie2 and xor(s, u.z.b2) and (uzIsNotZero) or  # -s*u[2] sur b2
        condSortie3 and xor(not s, u.y.b2) and (uyIsNotZero) # s*u[1] sur b2
    ,
        condSortie1 and u.x.b1 or  # u[0] sur b1
        condSortie2 and u.z.b1 or  # -s*u[2] sur b1
        condSortie3 and u.y.b1 # s*u[1] sur b1
    ,
        condSortie1 and u.x.b0 or  # u[0] sur b0
        condSortie2 and u.z.b0 or  # -s*u[2] sur b0
        condSortie3 and u.y.b0 # s*u[1] sur b0
    )

    yValue = Value(
        condSortie1 and xor(not s, u.z.b2) and (uzIsNotZero) or  # s*u[2] sur b2
        condSortie2 and u.y.b2 or  # u[1] sur b2
        condSortie3 and xor(s, u.x.b2) and (uxIsNotZero) # -s*u[0] sur b2
    ,
        condSortie1 and u.z.b1 or  # s*u[2] sur b1
        condSortie2 and u.y.b1 or  # u[1] sur b1
        condSortie3 and u.x.b1 # -s*u[0] sur b1
    ,
        condSortie1 and u.z.b0 or  # s*u[2] sur b0
        condSortie2 and u.y.b0 or  # u[1] sur b0
        condSortie3 and u.x.b0 # -s*u[0] sur b0
    )

    zValue = Value(
        condSortie1 and xor(s, u.y.b2) and (uyIsNotZero) or  # -s*u[1] sur b2
        condSortie2 and xor(not s, u.x.b2) and (uxIsNotZero) or  # s*u[0] sur b2
        condSortie3 and u.z.b2 # u[2] sur b2
    ,
        condSortie1 and u.y.b1 or  # -s*u[1] sur b1
        condSortie2 and u.x.b1 or  # s*u[0] sur b1
        condSortie3 and u.z.b1 # u[2] sur b1
    ,
        condSortie1 and u.y.b0 or  # -s*u[1] sur b0
        condSortie2 and u.x.b0 or  # s*u[0] sur b0
        condSortie3 and u.z.b0 # u[2] sur b0
    )

    return Vector(xValue, yValue, zValue)

def xor(a, b):
    return a and not b or  not a and b

def eqSolveBoolVect(u, v):
    u2 = Vector.fromNumbers(u)
    v2 = Vector.fromNumbers(v)
    sortie = eqSolveBool(u2, v2)
    print(u2.toString())
    return sortie

if __name__ == "__main__" :

    file=True
    with open("equations.json") as file:
        equations = json.load(file)

    eqE = equations['E']['clockwise'] + equations['E']['counterclockwise']
    eqC = equations['C']['clockwise'] + equations['C']['counterclockwise']

    fails = 0
    counts = 0

    for eq in eqE + eqC:
        counts += 1
        if eqSolveBoolVect(eq[0], eq[1]) != eq[2]:
            fails += 1

    print(int(100*(1 - fails / counts)), '% de réussite')
