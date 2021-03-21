# Présentation

Ce projet a pour but d'obtenir un circuit logique. Ce circuit permet de calculer la position d'arrivée d'une pièce sur un Rubik's Cube à partir de sa position de départ et du mouvement utilisé.

## Source

J'ai utilisé un projet opensource qui permet d'entrer manuellement des tables de vérité, de les convertir en équations logiques, et de les réduire au maximum.  
<http://truthtablesolve.sourceforge.net/>

## Modifications

Ce solver de tables de vérité ne colle pas vraiment avec mes données car il est prévu pour entrer manuellement toutes les valeurs des tables de vérité dans l'ordre croissant des entrées. J'ai donc fait un fork du projet sur lequel j'ai ajouté une option pour entrer directement un fichier ([voir ici](https://github.com/juliengiraud/TruthTableSolver)).

- Récupération des données de mes graphes à partir d'une liste de fichiers
- Conversion des données de chaque fichier en tableau de la forme " (int) Entrée 1 | (int) Entrée 2 | (int) Sortie
- Conversion de chaque tableau de données en tables de vérité (autant de tables de vérité qu'il faut de bits pour écrire les valeurs de sortie)
- Résolution de chaque table et affichage du résultat de façon propre, une copie de la sortie du programme se trouve dans le fichier [Résultats](Résultats.md)

## Générateur de circuits

Il ne s'agit pas vraiment d'un générateur de circuit, c'est plutôt un affichage pratique et compréhensible des équations booléennes de Résultats.  
Pour générer les ["Circuits"](Circuits.md) il faut partir d'une copie du fichier Résultats.md, l'ouvrir sur vs-code en activant *Match Case* et *Use Regular Expression*, et y appliquer tous les Find/Replace notés dans [regex.txt](regex.txt)
