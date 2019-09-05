package cmdIn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import solve.Solver;

public class ConvertisseurDOT {

    public static void main(String args []) throws IOException {
        
        String[] files = new String[]{
            "/home/julien/Bureau/main/Projets/Rubik-s_Cube/Graphes/Sources/oriented_graph_corners.dot",
            "/home/julien/Bureau/main/Projets/Rubik-s_Cube/Graphes/Sources/oriented_graph_edge.dot"
        };
        
        for (int i = 0; i < files.length; i++) { // Pour chaque fichier
            // Conversion du fichier en un tableau de String
            List<int[]> valuesList = getvaluesFromFilename(files[i]);
            System.out.println("## " + getFileNameFromFileSrc(files[i])); // Affichage du nom du fichier
            List<int[]> listeComplete = getTruthTable(valuesList);
            String[] listeCompleteString = getTruthTableToString(listeComplete);
            //for (int j = 0; j < listeCompleteString.length; j++) System.out.println(listeCompleteString[j]);
            
            for (int j = 12; j < 17; j++) { // Pour chacune des 5 solutions
                System.out.println("\n### Colone " + (j - 11));
                int[] tableSolutions = getArraySolutions(listeCompleteString, j);
                
                String answer;
                int par;
                Solver sol = new Solver(
                    tableSolutions, // getValues(),
                    new String[]{"p1", "p2", "p3", "p4", "p5", "b1", "b2", "b3", "b4"}, // getTermsNames(),
                    0, // getSumOfProductsOrProductOfSums(), // 0 -> sum of products, 1 -> product of sums
                    1 // getOneAllPossibleSolutionsOrOneSolution()
                );
                sol.Solve();
                answer = sol.getSolution();
                answer = answer.replaceAll("\\<.*?>", ""); // remove html tags
                par = answer.length() - answer.replace(")", "").length();
                System.out.println("\n#### Version somme de produits (" + par + ")\n\n" + answer);
                
                sol = new Solver(
                    tableSolutions, // getValues(),
                    new String[]{"p1", "p2", "p3", "p4", "p5", "b1", "b2", "b3", "b4"}, // getTermsNames(),
                    1, // getSumOfProductsOrProductOfSums(), // 0 -> sum of products, 1 -> product of sums
                    1 // getOneAllPossibleSolutionsOrOneSolution()
                );
                sol.Solve();
                answer = sol.getSolution();
                answer = answer.replaceAll("\\<.*?>", ""); // remove html tags
                par = answer.length() - answer.replace(")", "").length();
                System.out.println("\n#### Version produit de sommes (" + par + ")\n\n" + answer);
            }
            System.out.println();
        }
        
    }
    
    private static int[] getArraySolutions(String[] tab, int col) {
        int[] sortie = new int[tab.length];
        for (int i = 0; i < tab.length; i++) {
            sortie[i] = Integer.parseInt(tab[i].substring(col-1, col));
        }
        return sortie;
    }
    
    private static List<int[]> getTruthTable(List<int[]> table) {
        int input1, input2, e1, e2, s;
        input1 = getTruthTableSize(table, 1);
        input2 = getTruthTableSize(table, 2);
        
        // Création de la table de vérité avec toute les sorties à 0
        List<int[]> list = new ArrayList();
        for (int i = 0; i < input1; i++) {
            for (int j = 0; j < input2; j++) {
                list.add(new int[]{i, j, i});
            }
        }
        
        // Remplissage de la table avec la table passée en paramètre
        for (int i = 0; i < input1 * input2; i++) { // Pour chaque ligne de "list"
            e1 = list.get(i)[0];
            e2 = list.get(i)[1];
            s = tableContain(e1, e2, table);
            if (s > 0) { // Si "table" contient une sortie pour la ligne
                list.get(i)[2] = s;
            }
        }
        
        // On remplace les valeurs de sortie impossibles par 0
        for (int i = 0; i < input1 * input2; i++) { // Pour chaque ligne de "list"
            e1 = list.get(i)[0];
            e2 = list.get(i)[1];
            if (
                e1 == 0 // Il n'y a pas d'emplacement 0
                || e2 == 0 // ni de mouvement 0
                || e1 > 24 // ni d'emplacement supérieur à 24
                || e2 > 12 // ni de mouvement supérieur à 12
            )
                list.get(i)[2] = 0;
        }
            
        return list;
    }
    
    private static int tableContain(int i, int j, List<int[]> list) {
        for (int[] line : list) {
            if (line[0] == i && line[1] == j) {
                return line[2];
            }
        }
        return -1;
    }
    
    private static int getTruthTableSize(List<int[]> table, int col) {
        int max = -1, nb, pow = 1;
        
        // Récupération de la plus grande valeur de la liste
        for (int[] line : table) {
            nb = line[col - 1];
            if (nb > max) max = nb;
        }
        if (max < 0) return 0;
        
        // Calcul de la première puissance de 2 supérieure ou égale à max
        while (pow < max) {
            pow = pow * 2;
        }
        return pow;
    }
    
    public static String padRight(String s, char c, int n) {
        return String.format("%-" + n + "s", s).replace(' ', c);
    }
    
    public static String padLeft(String s, char c, int n) {
        return String.format("%" + n + "s", s).replace(' ', c);
    }
    
    private static String getFileNameFromFileSrc(String fileSrc) {
        String[] fileSrcTab = fileSrc.split("/");
        return fileSrcTab[fileSrcTab.length-1];
    }
    
    private static void valuesToString(List<int[]> values) {
        for (int[] nb : values) {
            System.out.println(nb[0] + " " + nb[1] + " " + nb[2]);
        }
        System.out.println("\n");
    }
    
    private static void valuesToString(List<int[]> values, Boolean b) {
        if (b) {
            for (int[] nb : values) {
                System.out.println(
                    padLeft(Integer.toBinaryString(nb[0]), '0', 5) + " " +
                    padLeft(Integer.toBinaryString(nb[1]), '0', 4) + " " +
                    padLeft(Integer.toBinaryString(nb[2]), '0', 5)
                );
            }
            System.out.println("\n");
        }
        else {
            valuesToString(values);
        }
    }
    
    private static String[] getTruthTableToString(List<int[]> values) {
        int size = values.size();
        int[] nb;
        String nb1, nb2,  nb3, s;
        String[] sortie = new String[size];
        for (int i = 0; i < size; i++) {
            nb = values.get(i);
            nb1 = padLeft(Integer.toBinaryString(nb[0]), '0', 5) + " ";
            nb2 = padLeft(Integer.toBinaryString(nb[1]), '0', 4) + " ";
            nb3 = padLeft(Integer.toBinaryString(nb[2]), '0', 5);
            s = nb1 + nb2 + nb3;
            s = s.replace(" 00000", " 22222");
            sortie[i] = s;
        }
        return sortie;
    }
    
    private static List<int[]> getvaluesFromFilename(String filename) {
        List<int[]> nbsList = null;
        try {
            String line;
            nbsList = new ArrayList();
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            while ((line = reader.readLine()) != null) {
                int length = line.length();
                if (length >= 27 && length <= 28) {
                    // Formatage du texte
                    line = line.replace("    ", "");
                    line = line.replace("-> ", "");
                    line = line.replace("[label=\"", "");
                    line = line.replace("\"];", "");
                    line = line.replace("A", "");
                    line = line.replace("C", "");

                    // Conversion des mouvements en nombres
                    // R -> 1    R' -> 2    U -> 3    U' -> 4
                    // L -> 5    L' -> 6    F -> 7    F' -> 8
                    // D -> 9    D' -> 10   B -> 11   B' -> 12
                    line = line.replace("R'", "2");
                    line = line.replace("R", "1");
                    line = line.replace("U'", "4");
                    line = line.replace("U", "3");
                    line = line.replace("L'", "6");
                    line = line.replace("L", "5");
                    line = line.replace("F'", "8");
                    line = line.replace("F", "7");
                    line = line.replace("D'", "10");
                    line = line.replace("D", "9");
                    line = line.replace("B'", "12");
                    line = line.replace("B", "11");

                    String[] strnb = line.split(" ");
                    nbsList.add(new int[]{ // Entrée-Sortie-Label -> [0]-[2]-[1]
                        Integer.parseInt(strnb[0]),
                        Integer.parseInt(strnb[2]),
                        Integer.parseInt(strnb[1])
                    });
                    
                } // Fin du if
                
            } // Fin du while
            
            reader.close();
        } // Fin du try
        catch (FileNotFoundException e) {
            Logger.getLogger(ConvertisseurDOT.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (IOException e) {
            Logger.getLogger(ConvertisseurDOT.class.getName()).log(Level.SEVERE, null, e);
        }
        return nbsList;
    }
    
}
