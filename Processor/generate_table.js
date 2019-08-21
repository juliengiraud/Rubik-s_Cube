// Récupération des données JSON
var fs=require('fs');
var corners=fs.readFileSync('JSON_data/corner.json', 'utf8');
var edge=fs.readFileSync('JSON_data/edge.json', 'utf8');
var ctab = JSON.parse(corners).rotation;
var etab = JSON.parse(edge).rotation;

var conversion = ["R", "R'", "U", "U'", "L", "L'", "F", "F'", "D", "D'", "B", "B'"];

/**
 * Retourne le numéro du mouvement :
 * R -> 1    R' -> 2    U -> 3    U' -> 4
 * L -> 5    L' -> 6    F -> 7    F' -> 8
 * D -> 9    D' -> 10   B -> 11   B' -> 12
 * 
 * @param {string} move valeur du mouvement
 */
function getMoveNumber(move) {
    return (conversion.indexOf(move) + 1);
}

function dec2bin(dec){
    return (dec >>> 0).toString(2);
}

function setSize(str, _size, char) {
    var size = parseInt(_size);
    if (str.length >= size) return str;
    while (str.length < size) str = char + str;
    return str;
}

/*var input, move, output; // valeurs de départ
var b_input, b_move, b_output; // valeurs binaires affichées

for(var line of etab) {
    // valeurs de départ
    input = line.input;
    move = getMoveNumber(line.move);
    output = line.output;
    
    // valeurs binaires affichées
    b_input = setSize(dec2bin(input), 5, 0);
    b_move = setSize(dec2bin(input), 5, 0);
    b_output = setSize(dec2bin(input), 5, 0);

    //console.log(b_input, b_move, b_output);
}

var t_input, t_move;
var b_t_input, b_t_move;

for (t_move = 0; t_move < 16; t_move++) {
    for (t_input = 0; t_input < 32; t_input++) {
        b_t_input = setSize(dec2bin(t_input), 5, 0);
        b_t_move = setSize(dec2bin(t_move), 5, 0);

        //console.log(b_t_input, b_t_move, "d");
    }
}*/

function getValTab(tab) {
    var line;
    var valtab = [], tmptab = [];

    for (line of tab) {
        tmptab = [];
        tmptab.push(line.input);
        tmptab.push(getMoveNumber(line.move));
        tmptab.push(line.output);
        valtab.push(tmptab);
    }
    return valtab;
}

function getEmptyTable(e1, e2) {
    var i, j;
    var tab = [], subtab = [];
    for (i = 0; i < e1; i++) {
        for (j = 0; j < e2; j++) {
            subtab = [];
            subtab.push(i);
            subtab.push(j);
            tab.push(subtab);
        }
    }
    return tab;
}

function containLine(line, array) {
    var nbs;
    for (nbs of array) {
        if (nbs[0] == line[0] && nbs[1] == line[1]) {
            return nbs;
        }
    }
    return false;
}

function rempliTab(emptyTab, valTab) {
    var line, work;
    for (line of emptyTab) {
        work = containLine(line, valTab);
        if (work) {
            line.push(work[2]);
        }
        else {
            line.push(0);
        }
    }
    return emptyTab;
}

var tab = getEmptyTable(32, 16);

console.log(tab);

var valTab = getValTab(etab);
var finalTab = rempliTab(tab, valTab);

//console.log(finalTab);

for (var line of finalTab) {
    var a, b, c;
    a = setSize(dec2bin(line[0]), 5, 0);
    b = setSize(dec2bin(line[1]), 4, 0);
    r = setSize(dec2bin(line[2]), 5, 0)
    console.log(a + ";" + b + ";" + r);
}
