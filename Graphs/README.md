# Le Rubik's Cube en graphes

Ce projet a pour but de représenter les relations entre les facettes d'un Rubik's Cube à l'aide des graphes

## Objectifs

1. Vérifier s'il existe une représentation des rotations sous la forme d'un graphe planaire orienté

2. Rassembler les données des rotations dans un fichier de façon structurée et réutilisable

## Contexte

- La numérotation des facettes se fait selon le schéma ([voir PNG](./schema.png))

- Pour utiliser le langage universel du Rubik's Cube il faut positionner la face rouge devant soit avec la jaune au dessus

- S'il y a les 48 pièces dans un graphe, la numérotation est exactement la même que sur le schéma (de 1 à 48)

- S'il n'y a que 24 pièces (coins ou arêtes) dans un graphe, la numérotation se fait de 1 à 24 suivant l'**ordre** du schéma

- J'utilise le langage DOT pour représenter les graphes

## Rappels sur les graphes

- Un **graphe stricte** est un graphe avec uniquement des relations bidirectionnelles

- Un **graphe orienté** n'a aucune relation bidirectionnelle

- Un **graphe planaire** peut être replésenté en 2D sans qu'aucune de ses relations ne se croisent

## Génération des images

J'utilise [graphviz](https://www.graphviz.org/) pour générer les images. Si les graphes sont planaires au sens mathématique, ils seront visuellement planaires sur les images générées

### Installation sur un Linux

```shell
sudo apt-get install graphviz
```

### Génération d'une image

```shell
dot -Gratio=auto -Tpng -o nomDuGraphe.png nomDuGraphe.dot
```

## Liste des graphes

1. *strict_graph_full*
    C'est le graphe stricte de toutes les facettes du Rubik's Cube, coins et arêtes confondus (48 facettes de P01 à P48)

2. *strict_graph_corners*
    C'est le graphe stricte des coins (24 facettes de C01 à C24)

3. *strict_graph_edges*
    C'est le graphe stricte des arêtes (24 facettes de A01 à A24)

4. *oriented_graph_full*
    C'est le graphe orienté de toutes les facettes du Rubik's Cube, coins et arêtes confondus (48 facettes de P01 à P48)

5. *oriented_graph_corners*
    C'est le graphe orienté des coins (24 facettes de C01 à C24)

6. *oriented_graph_edges*
    C'est le graphe orienté des arêtes (24 facettes de A01 à A24)
