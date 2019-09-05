/*
 *<Truth Table Solver 1.2 Beta>
 *Copyright (C) <2011>  <Sherif Ahmed>
 *
 *This program is free software: you can redistribute it and/or modify
 *it under the terms of the GNU General Public License as published by
 *the Free Software Foundation, either version 3 of the License, or
 *(at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
///////////////////////////////////////////////////////////////////////

/*
 * prompt class is responsible of inputing
 * the data from the command line for the command line mode
 */

package cmdIn;

import java.io.*;

public class Prompt{
    private int values[]; //the truth table values

    private int number_of_terms;

    private String terms_names[];

    private int sum_of_products_or_product_of_sums; // 0 for sum of products for product of sums

    private int all_possible_or_one_sol;
    
    public void promptUser() throws Exception {
		opening();
		
		number_of_terms = promptNumberOfTerms();
		
		terms_names = new String[number_of_terms];
		
		values = new int[(int)Math.pow(2, number_of_terms)];

		sum_of_products_or_product_of_sums = 0;

		promptTermsNames();
		
		promptValues();
		
		promptSumOfProductsOrProductOfSums();
		
		promptOneAllPossibleSolutionsOrOneSolution();
    }
    
    void opening(){
		System.out.println(
				   "*********************************************************\n"+
				   "*      ###   #   # #  ###  # #    ###   #   ##   #   ## *\n"+
				   "*       #   # #  # #   #   # #     #   # #  # #  #   #  *\n"+
				   "*       #   ##   # #   #   ###     #   ###  ##   #   ## *\n"+
				   "*       #   # #  # #   #   # #     #   # #  # #  #   #  *\n"+
				   "*       #   # #  ###   #   # #     #   # #  ##   ##  ## *\n"+
				   "*                                                       *\n"+
				   "*                                                       *\n"+
				   "*         ##  ###  #   # #  ##   #                      *\n"+
				   "*         #   # #  #   # #  #   # #                     *\n"+
				   "*         ##  # #  #   # #  ##  ##    1.2 Beta          *\n"+
				   "*          #  # #  #   # #  #   # #                     *\n"+
				   "*         ##  ###  ##   #   ##  # #   By : Sherif Ahmed *\n"+
				   "*********************************************************\n\n"+
				   "This program solves the truth table and output all the possible minimized  \nequations.\n"
		);
    }
    
    
    
    /*
     *promptNumberOfTerms asks user to input number of terms
     */
    
    private int promptNumberOfTerms() throws Exception{
		int number;
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("enter the number of terms : ");
		
		try{
			number=Integer.parseInt(in.readLine());
		}
		catch(NumberFormatException e){
			System.out.println("\nerror : enter a positive integer.\n");
			
			number = promptNumberOfTerms();
		}
		if(number <= 0){
			System.out.println("\nerror : enter a positive integer.\n");
			
			number = promptNumberOfTerms();
		}
		return number;
    }
    
    private void promptTermsNames() throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println();
		
		for(int i = 0; i < terms_names.length; i++){
			System.out.print("enter name of term[" + ( i + 1) + "] : ");
			
			terms_names[i] = in.readLine();
		}
    }

    /*
     *promptValues asks user to input the truth table values
     */
    
    private void promptValues() throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String temp;
		
		int value = 0;

		System.out.println();

		for(int i = 0; i < values.length; i++){
			System.out.print("enter the value[" + i + "] : ");

			temp= in.readLine();
			
			if(temp.equals("0"))
				values[i] = 0;
			
			else if(temp.equals("1"))
				values[i] = 1;
			
			else if(temp.equals("d"))
				values[i] = 2;
			
			else{
				System.out.println("\nerror : enter 0 or 1 or d.\n");
			
				i--;
			}
		}
    }
    
    /*
     *promptSumOfProductsOrProductOfSums asks user if he want the answer as sum of products or product of sums
     */
    
    private void promptSumOfProductsOrProductOfSums() throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String temp;
		
		System.out.println();
		
		do{
			System.out.print("enter 1 for sum of products or 2 for product of sums : ");
			
			temp = in.readLine();
			
			if(temp.equals("1"))
				sum_of_products_or_product_of_sums = 0;
			
			else if(temp.equals("2"))
				sum_of_products_or_product_of_sums = 1;
			
			else
				System.out.println("\nerror : enter 1 for sum of products or 2 for product of sums.\n");
			
		}while(! temp.equals("1") && !temp.equals("2"));
    }

    private void promptOneAllPossibleSolutionsOrOneSolution() throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String temp;

		System.out.println();

		do{
			System.out.print("enter 1 for (all possible solutions[occasionaly slow]) or \n2 (one solution only[fast]): ");
			
			temp = in.readLine();

			if(temp.equals("1"))
				all_possible_or_one_sol = 0;
			
			else if(temp.equals("2"))
				all_possible_or_one_sol = 1;
			
			else
				System.out.println("\nerror : enter 1 for (all possible solutions[occasionaly slow]) or 2 (one solution only[fast]) .\n");
			
		}while(!temp.equals("1") && !temp.equals("2"));
    }

    public int[] getValues(){
		return values;
    }

    public int getNumberOfTerms(){
		return number_of_terms;
    }

    public String[] getTermsNames(){
		return terms_names;
    }

    public int getSumOfProductsOrProductOfSums(){
		return sum_of_products_or_product_of_sums;
    }

    public int getOneAllPossibleSolutionsOrOneSolution(){
        return all_possible_or_one_sol;
    }
}
