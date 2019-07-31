# Introduction
Ce projet a pour but de représenter les relations entre les facettes d'un Rubik's Cube à l'aide des graphes  
La numérotation des facettes se fait selon le schéma (voir PDF)  
S'il y a les 48 pièces alors la numérotation est exactement la même que sur le schéma (de 1 à 48)  
S'il n'y a que 24 pièces (coins ou arêtes) alors la numérotation se fait de 1 à 24 suivant l'ordre du schéma  
Le langage utilisé pour formaliser les graphes et générer les images est le langage DOT  


# Partie logiciel
Installation sur un Linux :
```
sudo apt-get install graphviz
```

[Documentation graphviz / DOT](https://www.graphviz.org/)

Génération d'une image :
```
dot -Gratio=auto -Tpng -o nomDuGraphe.png nomDuGraphe.dot
```


# Rappel sur les graphes
- Un graphe stricte est un graphe avec uniquement des relations bidirectionnelles
- Un graphe orienté n'a aucune relation bidirectionnelle


# Liste des graphes
*strict_graph_full*  
C'est le graphe stricte de toutes les facettes du Rubik's Cube, coins et arêtes confondus (48 facettes de P01 à P48)

*strict_graph_corners*  
C'est le graphe stricte des coins (24 facettes de C01 à C24)

*strict_graph_edge*  
C'est le graphe stricte des arêtes (24 facettes de A01 à A24)
