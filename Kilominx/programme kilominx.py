

import pygame, time
pygame.init()

true=1



cube=[
['N1', 'N2', 'N3', 'N4', 'N5'],
['*1', '*2', 'R3', 'R4', 'R5'],
['*1', '*2', 'B3', 'B4', 'B5'],
['*1', '*2', 'J3', 'J4', 'J5'],
['*1', '*2', 'M3', 'M4', 'M5'],
['*1', '*2', 'V3', 'V4', 'V5']
]


'''
cube=[
['N1', 'N2', 'N3', 'N4', 'N5'],
['R1', 'R2', 'R3', 'R4', 'R5'],
['B1', 'B2', 'B3', 'B4', 'B5'],
['J1', 'J2', 'J3', 'J4', 'J5'],
['M1', 'M2', 'M3', 'M4', 'M5'],
['V1', 'V2', 'V3', 'V4', 'V5']
]

'''

noir  =(  0,   0,   0)
blanc =(255, 255, 255)
rouge =(255,   0,   0)
bleu  =(  0,   0, 255)
jaune =(255, 255,   0)
violet=(192,   0, 255)
vert  =(  0, 170,  64)
gris  =(128, 128, 128)

couleur={
'N':blanc,
'R':rouge,
'B':bleu,
'J':jaune,
'M':violet,
'V':vert,
' ':noir,
'*':gris
}

def cube_va_dans_cube2():
    global cube, cube2
    cube2=[]
    for i in range(6):
        cube2.append([])
        for j in range(5):
            cube2[i].append(cube[i][j])


def cube2_va_dans_cube():
    global cube, cube2
    cube=[]
    for i in range(6):
        cube.append([])
        for j in range(5):
            cube[i].append(cube2[i][j])


var=[]
for i in range(16):
    var.append(' ')


def U():
    var[0]=cube[0][0]
    cube[0][0]=cube[0][1]
    cube[0][1]=cube[0][3]
    cube[0][3]=cube[0][4]
    cube[0][4]=cube[0][2]
    cube[0][2]=var[0]
    
    var[0]=cube[1][0]
    var[1]=cube[1][1]
    cube[1][0]=cube[2][1]
    cube[1][1]=cube[2][0]
    cube[2][0]=cube[3][1]
    cube[2][1]=cube[3][0]
    cube[3][0]=cube[4][0]
    cube[3][1]=cube[4][1]
    cube[4][0]=cube[5][0]
    cube[4][1]=cube[5][1]
    cube[5][0]=var[0]
    cube[5][1]=var[1]


def U2():
    var[0]=cube[0][0]
    cube[0][0]=cube[0][3]
    cube[0][3]=cube[0][2]
    cube[0][2]=cube[0][1]
    cube[0][1]=cube[0][4]
    cube[0][4]=var[0]
    
    var[0]=cube[1][0]
    var[1]=cube[1][1]
    cube[1][0]=cube[3][0]
    cube[1][1]=cube[3][1]
    cube[3][0]=cube[5][0]
    cube[3][1]=cube[5][1]
    cube[5][0]=cube[2][1]
    cube[5][1]=cube[2][0]
    cube[2][1]=cube[4][0]
    cube[2][0]=cube[4][1]
    cube[4][0]=var[0]
    cube[4][1]=var[1]


def U3():
    var[0]=cube[0][0]
    cube[0][0]=cube[0][4]
    cube[0][4]=cube[0][1]
    cube[0][1]=cube[0][2]
    cube[0][2]=cube[0][3]
    cube[0][3]=var[0]
    
    var[0]=cube[1][0]
    var[1]=cube[1][1]
    cube[1][0]=cube[4][0]
    cube[1][1]=cube[4][1]
    cube[4][0]=cube[2][1]
    cube[4][1]=cube[2][0]
    cube[2][1]=cube[5][0]
    cube[2][0]=cube[5][1]
    cube[5][0]=cube[3][0]
    cube[5][1]=cube[3][1]
    cube[3][0]=var[0]
    cube[3][1]=var[1]


def U4():
    var[0]=cube[0][0]
    cube[0][0]=cube[0][2]
    cube[0][2]=cube[0][4]
    cube[0][4]=cube[0][3]
    cube[0][3]=cube[0][1]
    cube[0][1]=var[0]
    
    var[0]=cube[1][0]
    var[1]=cube[1][1]
    cube[1][0]=cube[5][0]
    cube[1][1]=cube[5][1]
    cube[5][0]=cube[4][0]
    cube[5][1]=cube[4][1]
    cube[4][0]=cube[3][0]
    cube[4][1]=cube[3][1]
    cube[3][0]=cube[2][1]
    cube[3][1]=cube[2][0]
    cube[2][1]=var[0]
    cube[2][0]=var[1]


def R():
    var[0]=cube[2][0]
    cube[2][0]=cube[2][1]
    cube[2][1]=cube[2][3]
    cube[2][3]=cube[2][4]
    cube[2][4]=cube[2][2]
    cube[2][2]=var[0]
    
    var[0]=cube[0][2]
    var[1]=cube[0][4]
    cube[0][2]=cube[1][1]
    cube[0][4]=cube[1][3]
    cube[1][1]=var[2]
    cube[1][3]=var[3]
    var[2]=var[4]
    var[3]=var[5]
    var[4]=cube[3][2]
    var[5]=cube[3][0]
    cube[3][2]=var[0]
    cube[3][0]=var[1]


def R2():
    var[0]=cube[2][0]
    cube[2][0]=cube[2][3]
    cube[2][3]=cube[2][2]
    cube[2][2]=cube[2][1]
    cube[2][1]=cube[2][4]
    cube[2][4]=var[0]
    
    var[0]=cube[0][2]
    var[1]=cube[0][4]
    cube[0][2]=var[2]
    cube[0][4]=var[3]
    var[2]=cube[3][2]
    var[3]=cube[3][0]
    cube[3][2]=cube[1][1]
    cube[3][0]=cube[1][3]
    cube[1][1]=var[4]
    cube[1][3]=var[5]
    var[4]=var[0]
    var[5]=var[1]


def R3():
    var[0]=cube[2][0]
    cube[2][0]=cube[2][4]
    cube[2][4]=cube[2][1]
    cube[2][1]=cube[2][2]
    cube[2][2]=cube[2][3]
    cube[2][3]=var[0]
    
    var[0]=cube[0][2]
    var[1]=cube[0][4]
    cube[0][2]=var[4]
    cube[0][4]=var[5]
    var[4]=cube[1][1]
    var[5]=cube[1][3]
    cube[1][1]=cube[3][2]
    cube[1][3]=cube[3][0]
    cube[3][2]=var[2]
    cube[3][0]=var[3]
    var[2]=var[0]
    var[3]=var[1]


def R4():
    var[0]=cube[2][2]
    cube[2][2]=cube[2][4]
    cube[2][4]=cube[2][3]
    cube[2][3]=cube[2][1]
    cube[2][1]=cube[2][0]
    cube[2][0]=var[0]
    
    var[0]=cube[0][2]
    var[1]=cube[0][4]
    cube[0][2]=cube[3][2]
    cube[0][4]=cube[3][0]
    cube[3][2]=var[4]
    cube[3][0]=var[5]
    var[4]=var[2]
    var[5]=var[3]
    var[2]=cube[1][1]
    var[3]=cube[1][3]
    cube[1][1]=var[0]
    cube[1][3]=var[1]


def L():
    var[0]=cube[5][0]
    cube[5][0]=cube[5][2]
    cube[5][2]=cube[5][4]
    cube[5][4]=cube[5][3]
    cube[5][3]=cube[5][1]
    cube[5][1]=var[0]
    
    var[0]=cube[0][1]
    var[1]=cube[0][3]
    cube[0][1]=cube[4][3]
    cube[0][3]=cube[4][1]
    cube[4][3]=var[7]
    cube[4][1]=var[6]
    var[6]=var[8]
    var[7]=var[9]
    var[8]=cube[1][2]
    var[9]=cube[1][0]
    cube[1][0]=var[0]
    cube[1][2]=var[1]


def L2():
    var[0]=cube[5][0]
    cube[5][0]=cube[5][4]
    cube[5][4]=cube[5][1]
    cube[5][1]=cube[5][2]
    cube[5][2]=cube[5][3]
    cube[5][3]=var[0]
    
    var[0]=cube[0][1]
    var[1]=cube[0][3]
    cube[0][1]=var[7]
    cube[0][3]=var[6]
    var[7]=cube[1][0]
    var[6]=cube[1][2]
    cube[1][0]=cube[4][3]
    cube[1][2]=cube[4][1]
    cube[4][3]=var[9]
    cube[4][1]=var[8]
    var[9]=var[0]
    var[8]=var[1]


def L3():
    var[0]=cube[5][3]
    cube[5][3]=cube[5][2]
    cube[5][2]=cube[5][1]
    cube[5][1]=cube[5][4]
    cube[5][4]=cube[5][0]
    cube[5][0]=var[0]
    
    var[0]=cube[0][1]
    var[1]=cube[0][3]
    cube[0][1]=var[9]
    cube[0][3]=var[8]
    var[9]=cube[4][3]
    var[8]=cube[4][1]
    cube[4][3]=cube[1][0]
    cube[4][1]=cube[1][2]
    cube[1][0]=var[7]
    cube[1][2]=var[6]
    var[7]=var[0]
    var[6]=var[1]


def L4():
    var[0]=cube[5][1]
    cube[5][1]=cube[5][3]
    cube[5][3]=cube[5][4]
    cube[5][4]=cube[5][2]
    cube[5][2]=cube[5][0]
    cube[5][0]=var[0]
    
    var[0]=cube[0][1]
    var[1]=cube[0][3]
    cube[0][1]=cube[1][0]
    cube[0][3]=cube[1][2]
    cube[1][0]=var[9]
    cube[1][2]=var[8]
    var[9]=var[7]
    var[8]=var[6]
    var[7]=cube[4][3]
    var[6]=cube[4][1]
    cube[4][3]=var[0]
    cube[4][1]=var[1]


def F():
    var[0]=cube[1][0]
    cube[1][0]=cube[1][2]
    cube[1][2]=cube[1][4]
    cube[1][4]=cube[1][3]
    cube[1][3]=cube[1][1]
    cube[1][1]=var[0]
    
    var[0]=cube[0][3]
    var[1]=cube[0][4]
    cube[0][3]=cube[5][3]
    cube[0][4]=cube[5][1]
    cube[5][3]=var[10]
    cube[5][1]=var[9]
    var[9]=var[11]
    var[10]=var[2]
    var[11]=cube[2][3]
    var[2]=cube[2][1]
    cube[2][3]=var[1]
    cube[2][1]=var[0]


def F2():
    var[0]=cube[1][0]
    cube[1][0]=cube[1][4]
    cube[1][4]=cube[1][1]
    cube[1][1]=cube[1][2]
    cube[1][2]=cube[1][3]
    cube[1][3]=var[0]
    
    var[0]=cube[0][3]
    var[1]=cube[0][4]
    cube[0][3]=var[10]
    cube[0][4]=var[9]
    var[10]=cube[2][1]
    var[9]=cube[2][3]
    cube[2][1]=cube[5][3]
    cube[2][3]=cube[5][1]
    cube[5][3]=var[2]
    cube[5][1]=var[11]
    var[2]=var[0]
    var[11]=var[1]


def F3():
    var[0]=cube[1][3]
    cube[1][3]=cube[1][2]
    cube[1][2]=cube[1][1]
    cube[1][1]=cube[1][4]
    cube[1][4]=cube[1][0]
    cube[1][0]=var[0]
    
    var[0]=cube[0][3]
    var[1]=cube[0][4]
    cube[0][3]=var[2]
    cube[0][4]=var[11]
    var[2]=cube[5][3]
    var[11]=cube[5][1]
    cube[5][3]=cube[2][1]
    cube[5][1]=cube[2][3]
    cube[2][1]=var[10]
    cube[2][3]=var[9]
    var[10]=var[0]
    var[9]=var[1]


def F4():
    var[0]=cube[1][0]
    cube[1][0]=cube[1][1]
    cube[1][1]=cube[1][3]
    cube[1][3]=cube[1][4]
    cube[1][4]=cube[1][2]
    cube[1][2]=var[0]
    
    var[0]=cube[0][3]
    var[1]=cube[0][4]
    cube[0][3]=cube[2][1]
    cube[0][4]=cube[2][3]
    cube[2][1]=var[2]
    cube[2][3]=var[11]
    var[2]=var[10]
    var[11]=var[9]
    var[10]=cube[5][3]
    var[9]=cube[5][1]
    cube[5][3]=var[0]
    cube[5][1]=var[1]


def etat_cube():
    ec=0
    for i in range(6):
        for j in range(5):
            if cube[i][j][0] == cube[i][2][0] or cube[i][j][0] == '*':
                ec+=0
            else:
                ec+=1
    if ec == 0:
        return 1
    else:
        return 0








def affichage():
    pygame.draw.polygon(fenetre, couleur[cube[0][0][0]], [[236, 10], [282, 23], [236, 51], [191, 24]])
    pygame.draw.polygon(fenetre, couleur[cube[0][1][0]], [[187, 25], [232, 52], [157, 63], [140, 39]])
    pygame.draw.polygon(fenetre, couleur[cube[0][2][0]], [[285, 24], [331, 40], [315, 63], [241, 52]])
    pygame.draw.polygon(fenetre, couleur[cube[0][3][0]], [[234, 55], [234, 89], [176, 88], [160, 66]])
    pygame.draw.polygon(fenetre, couleur[cube[0][4][0]], [[238, 54], [312, 66], [296, 89], [238, 89]])
    
    pygame.draw.polygon(fenetre, couleur[cube[1][0][0]], [[177, 93], [234, 92], [234, 171], [160, 146]])
    pygame.draw.polygon(fenetre, couleur[cube[1][1][0]], [[239, 92], [295, 93], [313, 147], [238, 171]])
    pygame.draw.polygon(fenetre, couleur[cube[1][2][0]], [[158, 150], [233, 175], [187, 238], [141, 204]])
    pygame.draw.polygon(fenetre, couleur[cube[1][3][0]], [[314, 150], [332, 205], [286, 239], [239, 175]])
    pygame.draw.polygon(fenetre, couleur[cube[1][4][0]], [[236, 177], [283, 241], [236, 274], [190, 240]])
    
    pygame.draw.polygon(fenetre, couleur[cube[2][0][0]], [[334, 42], [362, 82], [350, 133], [317, 66]])
    pygame.draw.polygon(fenetre, couleur[cube[2][1][0]], [[315, 69], [349, 136], [316, 146], [300, 92]])
    pygame.draw.polygon(fenetre, couleur[cube[2][2][0]], [[365, 85], [393, 123], [393, 171], [353, 136]])
    pygame.draw.polygon(fenetre, couleur[cube[2][3][0]], [[317, 149], [349, 139], [362, 213], [336, 204]])
    pygame.draw.polygon(fenetre, couleur[cube[2][4][0]], [[353, 140], [393, 174], [393, 223], [365, 213]])
    
    pygame.draw.polygon(fenetre, couleur[cube[5][0][0]], [[138, 43], [155, 65], [123, 131], [110, 82]])
    pygame.draw.polygon(fenetre, couleur[cube[5][1][0]], [[158, 69], [174, 92], [156, 145], [125, 135]])
    pygame.draw.polygon(fenetre, couleur[cube[5][2][0]], [[108, 86], [120, 135], [80, 170], [80, 123]])
    pygame.draw.polygon(fenetre, couleur[cube[5][3][0]], [[124, 139], [154, 149], [137, 203], [111, 212]])
    pygame.draw.polygon(fenetre, couleur[cube[5][4][0]], [[119, 142], [107, 213], [80, 223], [80, 174]])
    
    if etat_cube() == 1:
        pygame.draw.polygon(fenetre, (0, 255, 0), [[10, 10], [50, 10], [50, 50], [10, 50]])
    else:
        pygame.draw.polygon(fenetre, rouge, [[10, 10], [50, 10], [50, 50], [10, 50]])
    
    pygame.display.update()





def algo_search1():
    solution1 = 0
    print ''
    if etat_cube() == 1:
        print 'Cube fait.'
        return
    print 'En 1 mouvement:'
    for mouv1 in range(8):
        algolist[mouv1]()
        if etat_cube() == 1:
            print algolisttxt[mouv1]
            algolist[mouv1+8]()
            solution1 = 1
        else:
            algolist[mouv1+8]()
    if solution1 == 0:
        print 'rien.\n'
        algo_search2()
    else:
        print 'Termine en', time.time()-t1, 'secondes.'



def algo_search2():
    solution2 = 0
    print ''
    print 'En 2 mouvements:'
    for mouv1 in range(8):
        for mouv2 in range(8):
            algolist[mouv1]()
            algolist[mouv2]()
            if etat_cube() == 1:
                print algolisttxt[mouv1],
                print algolisttxt[mouv2]
                algolist[mouv2+8]()
                algolist[mouv1+8]()
                solution2 = 1
            else:
                algolist[mouv2+8]()
                algolist[mouv1+8]()
    if solution2 == 0:
        print 'rien.\n'
        algo_search3()
    else:
        print 'Termine en', time.time()-t1, 'secondes.'


def algo_search3():
    solution3 = 0
    print ''
    print 'En 3 mouvements:'
    for mouv1 in range(8):
        for mouv2 in range(8):
            for mouv3 in range(8):
                algolist[mouv1]()
                algolist[mouv2]()
                algolist[mouv3]()
                if etat_cube() == 1:
                    print algolisttxt[mouv1],
                    print algolisttxt[mouv2],
                    print algolisttxt[mouv3]
                    algolist[mouv3+8]()
                    algolist[mouv2+8]()
                    algolist[mouv1+8]()
                    solution3 = 1
                else:
                    algolist[mouv3+8]()
                    algolist[mouv2+8]()
                    algolist[mouv1+8]()
    if solution3 == 0:
        print 'rien.\n'
        algo_search4()
    else:
        print 'Termine en', time.time()-t1, 'secondes.'


def algo_search4():
    solution4 = 0
    print ''
    print 'En 4 mouvements:'
    for mouv1 in range(8):
        for mouv2 in range(8):
            for mouv3 in range(8):
                for mouv4 in range(8):
                    algolist[mouv1]()
                    algolist[mouv2]()
                    algolist[mouv3]()
                    algolist[mouv4]()
                    if etat_cube() == 1:
                        print algolisttxt[mouv1],
                        print algolisttxt[mouv2],
                        print algolisttxt[mouv3],
                        print algolisttxt[mouv4]
                        algolist[mouv4+8]()
                        algolist[mouv3+8]()
                        algolist[mouv2+8]()
                        algolist[mouv1+8]()
                        solution4 = 1
                    else:
                        algolist[mouv4+8]()
                        algolist[mouv3+8]()
                        algolist[mouv2+8]()
                        algolist[mouv1+8]()
    if solution4 == 0:
        print 'rien.\n'
        algo_search5()
    else:
        print 'Termine en', time.time()-t1, 'secondes.'
                        

def algo_search5():
    global cube, var
    solution5 = 0
    compteur5 = -1
    print ''
    print 'En 5 mouvements:'
    for mouv1 in range(8):
        compteur5 +=1
        print 100*compteur5/8, '%'
        for mouv2 in range(8):
            for mouv3 in range(8):
                for mouv4 in range(8):
                    for mouv5 in range(8):
                        algolist[mouv1]()
                        algolist[mouv2]()
                        algolist[mouv3]()
                        algolist[mouv4]()
                        algolist[mouv5]()
                        if etat_cube() == 1:
                            print algolisttxt[mouv1],
                            print algolisttxt[mouv2],
                            print algolisttxt[mouv3],
                            print algolisttxt[mouv4],
                            print algolisttxt[mouv5]
                            algolist[mouv5+8]()
                            algolist[mouv4+8]()
                            algolist[mouv3+8]()
                            algolist[mouv2+8]()
                            algolist[mouv1+8]()
                            
                            '''
                            cube = 
                            var = 
                            '''
                            
                            solution5 = 1
                        else:
                            algolist[mouv5+8]()
                            algolist[mouv4+8]()
                            algolist[mouv3+8]()
                            algolist[mouv2+8]()
                            algolist[mouv1+8]()
                            
                            '''
                            cube = 
                            var = 
                            '''
    if solution5 == 0:
        print 'rien.\n'
        algo_search6()
    else:
        print 'Termine en', time.time()-t1, 'secondes.'
                        

def algo_search6():
    global cube, var
    solution6 = 0
    compteur6 = -1
    print ''
    print 'En 6 mouvements:'
    for mouv1 in range(8):
        for mouv2 in range(8):
            compteur6 +=1
            print 100*compteur6/(8*8), '%'
            for mouv3 in range(8):
                for mouv4 in range(8):
                    for mouv5 in range(8):
                        for mouv6 in range(8):
                            algolist[mouv1]()
                            algolist[mouv2]()
                            algolist[mouv3]()
                            algolist[mouv4]()
                            algolist[mouv5]()
                            algolist[mouv6]()
                            if etat_cube() == 1:
                                print algolisttxt[mouv1],
                                print algolisttxt[mouv2],
                                print algolisttxt[mouv3],
                                print algolisttxt[mouv4],
                                print algolisttxt[mouv5],
                                print algolisttxt[mouv6]
                                algolist[mouv6+8]()
                                algolist[mouv5+8]()
                                algolist[mouv4+8]()
                                algolist[mouv3+8]()
                                algolist[mouv2+8]()
                                algolist[mouv1+8]()
                                
                                '''
                                cube = 
                                var = 
                                '''
                                solution6 = 1
                            else:
                                algolist[mouv6+8]()
                                algolist[mouv5+8]()
                                algolist[mouv4+8]()
                                algolist[mouv3+8]()
                                algolist[mouv2+8]()
                                algolist[mouv1+8]()
                                
                                '''
                                cube = 
                                var = 
                                '''
    if solution6 == 0:
        print 'rien.\n'
        algo_search7()
    else:
        print 'Termine en', time.time()-t1, 'secondes.'
                        

def algo_search7():
    global cube, var
    solution7 = 0
    compteur7 = -1
    print ''
    print 'En 7 mouvements:'
    for mouv1 in range(8):
        for mouv2 in range(8):
            compteur7 +=1
            print 100*compteur7/(8*8), '%'
            for mouv3 in range(8):
                for mouv4 in range(8):
                    for mouv5 in range(8):
                        for mouv6 in range(8):
                            for mouv7 in range(8):
                                algolist[mouv1]()
                                algolist[mouv2]()
                                algolist[mouv3]()
                                algolist[mouv4]()
                                algolist[mouv5]()
                                algolist[mouv6]()
                                algolist[mouv7]()
                                if etat_cube() == 1:
                                    print algolisttxt[mouv1],
                                    print algolisttxt[mouv2],
                                    print algolisttxt[mouv3],
                                    print algolisttxt[mouv4],
                                    print algolisttxt[mouv5],
                                    print algolisttxt[mouv6],
                                    print algolisttxt[mouv7]
                                    '''
                                    algolist[mouv7+8]()
                                    algolist[mouv6+8]()
                                    algolist[mouv5+8]()
                                    algolist[mouv4+8]()
                                    algolist[mouv3+8]()
                                    algolist[mouv2+8]()
                                    algolist[mouv1+8]()
                                    '''
                                    
                                    '''
                                    cube = 
                                    var = 
                                    '''
                                    solution7 = 1
                                else:
                                    '''
                                    algolist[mouv7+8]()
                                    algolist[mouv6+8]()
                                    algolist[mouv5+8]()
                                    algolist[mouv4+8]()
                                    algolist[mouv3+8]()
                                    algolist[mouv2+8]()
                                    algolist[mouv1+8]()
                                    '''
                                    
                                    '''
                                    cube = 
                                    var = 
                                    '''
    if solution7 == 0:
        print 'rien.\n'
        algo_search8()
    else:
        print 'Termine en', time.time()-t1, 'secondes.'
                        

def algo_search8():
    global cube, var
    solution8 = 0
    compteur8 = -1
    print ''
    print 'En 8 mouvements:'
    for mouv1 in range(8):
        for mouv2 in range(8):
            compteur8 +=1
            print 100*compteur8/(8*8), '%'
            for mouv3 in range(8):
                for mouv4 in range(8):
                    for mouv5 in range(8):
                        for mouv6 in range(8):
                            for mouv7 in range(8):
                                for mouv8 in range(8):
                                    algolist[mouv1]()
                                    algolist[mouv2]()
                                    algolist[mouv3]()
                                    algolist[mouv4]()
                                    algolist[mouv5]()
                                    algolist[mouv6]()
                                    algolist[mouv7]()
                                    algolist[mouv8]()
                                    if etat_cube() == 1:
                                        print algolisttxt[mouv1],
                                        print algolisttxt[mouv2],
                                        print algolisttxt[mouv3],
                                        print algolisttxt[mouv4],
                                        print algolisttxt[mouv5],
                                        print algolisttxt[mouv6],
                                        print algolisttxt[mouv7],
                                        print algolisttxt[mouv8]
                                        '''
                                        algolist[mouv8+8]()
                                        algolist[mouv7+8]()
                                        algolist[mouv6+8]()
                                        algolist[mouv5+8]()
                                        algolist[mouv4+8]()
                                        algolist[mouv3+8]()
                                        algolist[mouv2+8]()
                                        algolist[mouv1+8]()
                                        '''
                                        
                                        '''
                                        cube = 
                                        var = 
                                        '''
                                        solution8 = 1
                                    else:
                                        '''
                                        algolist[mouv8+8]()
                                        algolist[mouv7+8]()
                                        algolist[mouv6+8]()
                                        algolist[mouv5+8]()
                                        algolist[mouv4+8]()
                                        algolist[mouv3+8]()
                                        algolist[mouv2+8]()
                                        algolist[mouv1+8]()
                                        '''
                                        
                                        '''
                                        cube = 
                                        var = 
                                        '''
    if solution8 == 0:
        print 'rien.\n'
        algo_search9()
    else:
        print 'Termine en', time.time()-t1, 'secondes.'
                        

def algo_search9():
    global cube, var
    solution9 = 0
    compteur9 = -1
    print ''
    print 'En 9 mouvements:'
    for mouv1 in range(8):
        for mouv2 in range(8):
            compteur9 +=1.0
            print 100*compteur9/(8*8), '%'
            for mouv3 in range(8):
                for mouv4 in range(8):
                    for mouv5 in range(8):
                        for mouv6 in range(8):
                            for mouv7 in range(8):
                                for mouv8 in range(8):
                                    for mouv9 in range(8):
                                        algolist[mouv1]()
                                        algolist[mouv2]()
                                        algolist[mouv3]()
                                        algolist[mouv4]()
                                        algolist[mouv5]()
                                        algolist[mouv6]()
                                        algolist[mouv7]()
                                        algolist[mouv8]()
                                        algolist[mouv9]()
                                        if etat_cube() == 1:
                                            print algolisttxt[mouv1],
                                            print algolisttxt[mouv2],
                                            print algolisttxt[mouv3],
                                            print algolisttxt[mouv4],
                                            print algolisttxt[mouv5],
                                            print algolisttxt[mouv6],
                                            print algolisttxt[mouv7],
                                            print algolisttxt[mouv8],
                                            print algolisttxt[mouv9]
                                            '''
                                            algolist[mouv9+8]()
                                            algolist[mouv8+8]()
                                            algolist[mouv7+8]()
                                            algolist[mouv6+8]()
                                            algolist[mouv5+8]()
                                            algolist[mouv4+8]()
                                            algolist[mouv3+8]()
                                            algolist[mouv2+8]()
                                            algolist[mouv1+8]()
                                            '''
                                            
                                            '''
                                            cube = 
                                            var = 
                                            '''
                                            solution9 = 1
                                        else:
                                            '''
                                            algolist[mouv9+8]()
                                            algolist[mouv8+8]()
                                            algolist[mouv7+8]()
                                            algolist[mouv6+8]()
                                            algolist[mouv5+8]()
                                            algolist[mouv4+8]()
                                            algolist[mouv3+8]()
                                            algolist[mouv2+8]()
                                            algolist[mouv1+8]()
                                            '''
                                            
                                            '''
                                            cube = 
                                            var = 
                                            '''
    if solution9 == 0:
        print 'rien.\n'
        algo_search10()
    else:
        print 'Termine en', time.time()-t1, 'secondes.'
                        
def algo_search10():
    global cube, var
    global cube, var
    solution10 = 0
    compteur10 = -1
    print ''
    print 'En 10 mouvements:'
    for mouv1 in range(8):
        for mouv2 in range(8):
            compteur10 +=1.0
            print 100*compteur10/(8*8), '%'
            for mouv3 in range(8):
                for mouv4 in range(8):
                    for mouv5 in range(8):
                        for mouv6 in range(8):
                            for mouv7 in range(8):
                                for mouv8 in range(8):
                                    for mouv9 in range(8):
                                        for mouv10 in range(8):
                                            algolist[mouv1]()
                                            algolist[mouv2]()
                                            algolist[mouv3]()
                                            algolist[mouv4]()
                                            algolist[mouv5]()
                                            algolist[mouv6]()
                                            algolist[mouv7]()
                                            algolist[mouv8]()
                                            algolist[mouv9]()
                                            algolist[mouv10]()
                                            if etat_cube() == 1:
                                                print algolisttxt[mouv1],
                                                print algolisttxt[mouv2],
                                                print algolisttxt[mouv3],
                                                print algolisttxt[mouv4],
                                                print algolisttxt[mouv5],
                                                print algolisttxt[mouv6],
                                                print algolisttxt[mouv7],
                                                print algolisttxt[mouv8],
                                                print algolisttxt[mouv9],
                                                print algolisttxt[mouv10]
                                                '''
                                                algolist[mouv10+8]()
                                                algolist[mouv9+8]()
                                                algolist[mouv8+8]()
                                                algolist[mouv7+8]()
                                                algolist[mouv6+8]()
                                                algolist[mouv5+8]()
                                                algolist[mouv4+8]()
                                                algolist[mouv3+8]()
                                                algolist[mouv2+8]()
                                                algolist[mouv1+8]()
                                                '''
                                                
                                                '''
                                                cube = 
                                                var = 
                                                '''
                                                solution10 = 1
                                            else:
                                                '''
                                                lgolist[mouv10+8]()
                                                algolist[mouv9+8]()
                                                algolist[mouv8+8]()
                                                algolist[mouv7+8]()
                                                algolist[mouv6+8]()
                                                algolist[mouv5+8]()
                                                algolist[mouv4+8]()
                                                algolist[mouv3+8]()
                                                algolist[mouv2+8]()
                                                algolist[mouv1+8]()
                                                '''
                                                
                                                '''
                                                cube = 
                                                var = 
                                                '''
    if solution10 == 0:
        print 'rien.\n'
#        algo_search11()
    else:
        print 'Termine en', time.time()-t1, 'secondes.'


def algo_search11():
    global cube, var
    solution11 = 0
    compteur11 = -1
    print ''
    print 'En 11 mouvements:'
    for mouv1 in range(8):
        for mouv2 in range(8):
            compteur11 +=1.0
            print 100*compteur11/(8*8), '%'
            for mouv3 in range(8):
                for mouv4 in range(8):
                    for mouv5 in range(8):
                        for mouv6 in range(8):
                            for mouv7 in range(8):
                                for mouv8 in range(8):
                                    for mouv9 in range(8):
                                        for mouv10 in range(8):
                                            for mouv11 in range(8):
                                                algolist[mouv1]()
                                                algolist[mouv2]()
                                                algolist[mouv3]()
                                                algolist[mouv4]()
                                                algolist[mouv5]()
                                                algolist[mouv6]()
                                                algolist[mouv7]()
                                                algolist[mouv8]()
                                                algolist[mouv9]()
                                                algolist[mouv10]()
                                                algolist[mouv11]()
                                                if etat_cube() == 1:
                                                    print algolisttxt[mouv1],
                                                    print algolisttxt[mouv2],
                                                    print algolisttxt[mouv3],
                                                    print algolisttxt[mouv4],
                                                    print algolisttxt[mouv5],
                                                    print algolisttxt[mouv6],
                                                    print algolisttxt[mouv7],
                                                    print algolisttxt[mouv8],
                                                    print algolisttxt[mouv9],
                                                    print algolisttxt[mouv10],
                                                    print algolisttxt[mouv11]
                                                    
                                                    algolist[mouv11+8]()
                                                    algolist[mouv10+8]()
                                                    algolist[mouv9+8]()
                                                    algolist[mouv8+8]()
                                                    algolist[mouv7+8]()
                                                    algolist[mouv6+8]()
                                                    algolist[mouv5+8]()
                                                    algolist[mouv4+8]()
                                                    algolist[mouv3+8]()
                                                    algolist[mouv2+8]()
                                                    algolist[mouv1+8]()
                                                    
                                                    
                                                    '''
                                                    cube = 
                                                    var = 
                                                    '''
                                                    solution11 = 1
                                                else:
                                                    
                                                    algolist[mouv11+8]()
                                                    algolist[mouv10+8]()
                                                    algolist[mouv9+8]()
                                                    algolist[mouv8+8]()
                                                    algolist[mouv7+8]()
                                                    algolist[mouv6+8]()
                                                    algolist[mouv5+8]()
                                                    algolist[mouv4+8]()
                                                    algolist[mouv3+8]()
                                                    algolist[mouv2+8]()
                                                    algolist[mouv1+8]()
                                                    
                                                    
                                                    '''
                                                    cube = 
                                                    var = 
                                                    '''
    if solution11 == 0:
        print 'rien.\n'
#        algo_search12()
    else:
        print 'Termine en', time.time()-t1, 'secondes.'



def lire_algo(a):
    a+=' '
    mouvement=''
    for i in a:
        if i!=' ':
            mouvement+=i
        else:
            try:
                mouv[mouvement]()
                mouvement=''
            except:
#                print 'Erreur.'
                mouvement=''
        

mouv={"U":U, "U2":U2, "U2'":U3, "U'":U4,
      "F":F, "F2":F2, "F2'":F3, "F'":F4,
      "R":R, "R2":R2, "R2'":R3, "R'":R4,
      "L":L, "L2":L2, "L2'":L3, "L'":L4}
algolist=[U, R, L, F, U4, R4, L4, F4, U4, R4, L4, F4, U, R, L, F]
algolisttxt=["U", "R", "L", "F", "U'", "R'", "L'", "F'"]




lire_algo(a=raw_input("Entrer l'algorithme:\n"))

#lire_algo("40 - L' U' L U' L' U2 L U R U R' U R U2' R' U' F R U2 R' U' R U' R' U' L' U2' L U L' U L U F' U2' R U R' U'")

#U R U' R' F R' F' R F' U' F


#algolist[0]()
#algolist[1]()
#algolist[2]()
#algolist[3]()
#algolist[4]()
#algolist[5]()
#algolist[6]()
#algolist[7]()


'''

print cube
print var

'''

t1=time.time()
algo_search11()




'''


x=480
y=300

pygame.init()
fenetre = pygame.display.set_mode((x, y))
pygame.display.set_caption('Kilominx Emulator')


affichage()
#pygame.draw.polygon(fenetre, vert, [[10, 10], [50, 10], [50, 50], [10, 50]])
#pygame.display.update()


while true:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            true=0
        if event.type == pygame.KEYDOWN:
            lire_algo(a=raw_input('Entrer un mouvement\n'))
            affichage()

'''

