package main;

import solve.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainBis{
    
    public static void main(String args []) throws Exception {
        
        String answer;

        Solver sol = new Solver(
            getValues(),
            getTermsNames(),
            getSumOfProductsOrProductOfSums(),
            getOneAllPossibleSolutionsOrOneSolution()
        );

        sol.Solve();

        System.out.println();
        answer = sol.getSolution();
        answer = answer.replaceAll("\\<.*?>", ""); // remove html tags
        System.out.println("\nTHE RESULT :\n\n" + answer);
        
    }
    
    private static int[] getValues(){
        BufferedReader reader;
        int[] sortie = null;
        try {
            reader = new BufferedReader(new FileReader(
                "/home/julien/Bureau/main/Projets/Rubik-s_Cube/Processor/Truth_table/corner_exit_1.txt")
            );
            sortie = new int[512];
            String line = reader.readLine();
            int i = 0, val;
            while (line != null) {
                val = Integer.parseInt(line);
                sortie[i++] = val;
                //System.out.println("Ligne n°" + i + " : " + val);
                line = reader.readLine();
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return sortie;
    }
    
    private static String[] getTermsNames(){
        return new String[]{"e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9"};
    }
    
    private static int getSumOfProductsOrProductOfSums(){
        return 0;
    }
    
    private static int getOneAllPossibleSolutionsOrOneSolution(){
        return 1;
    }
}
