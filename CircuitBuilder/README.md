# Circuit builder

L'objectif des scripts de ce répertoire est de générer un circuit logique beaucoup plus optimisé que celui obtenu par la combinaison des tables de vérité en parallèle

Il y a aussi un gros travail de vérification des circuits pour voir s'ils fonctionnent

## Notes

Après avoir étudié la première partie des équations (equations.json->"A") je remarque les transitions suivantes

Si le vecteur de rotation est ( 1, 0, 0) alors (a,b,c) -> ( a,-c, b)
Si le vecteur de rotation est (-2, 0, 0) alors (a,b,c) -> ( a,-c, b)
Si le vecteur de rotation est (-1, 0, 0) alors (a,b,c) -> ( a, c,-b)
Si le vecteur de rotation est ( 2, 0, 0) alors (a,b,c) -> ( a, c,-b)

Si le vecteur de rotation est ( 0, 1, 0) alors (a,b,c) -> ( c, b,-a)
Si le vecteur de rotation est ( 0,-2, 0) alors (a,b,c) -> ( c, b,-a)
Si le vecteur de rotation est ( 0,-1, 0) alors (a,b,c) -> (-c, b, a)
Si le vecteur de rotation est ( 0, 2, 0) alors (a,b,c) -> (-c, b, a)

Si le vecteur de rotation est ( 0, 0, 1) alors (a,b,c) -> (-b, a, c)
Si le vecteur de rotation est ( 0, 0,-2) alors (a,b,c) -> (-b, a, c)
Si le vecteur de rotation est ( 0, 0,-1) alors (a,b,c) -> ( b,-a, c)
Si le vecteur de rotation est ( 0, 0, 2) alors (a,b,c) -> ( b,-a, c)
