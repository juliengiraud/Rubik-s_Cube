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
 * Format class is responsible for format the answer to a readable string
 *
 */

package solve;

import java.util.Vector;

public class Format{
    /*
     *formatOutput format the answer to a readable string
     */
    public String formatOutput(
			       int [] values,
			       Vector<Integer> mini_vals, 
			       Vector<Integer> mini_subs,
			       String [] terms_names,
			       int sum_of_products_or_product_of_sums, 
			       int one_sol_or_all_possible
			       ){
	
		PrimeImplicantsChartTable prime_implicant_chart = 
			new PrimeImplicantsChartTable(
						  values, 
						  mini_vals, 
						  mini_subs, 
						  one_sol_or_all_possible
						  );
		
		Vector<Integer> essential_primes_indexs =
			prime_implicant_chart.getEssentialPrimesIndexs();
		
		Vector<Vector<Integer>> minimume_compination_of_non_essential_table =
			prime_implicant_chart.getMinimumeCompinationOfNonEssentialTable();
		
		Vector<String> essential_primes_st_v = new Vector<String>();
		
		String essential_primes_st = new String();
		
		Vector<String> non_essential_st_v = new Vector<String>();
		
		
		for(int i = 0; i < essential_primes_indexs.size(); i++)
			essential_primes_st_v.add(
						  formatPrime(
							  mini_vals.get(essential_primes_indexs.get(i).intValue()).intValue(),
							  mini_subs.get(essential_primes_indexs.get(i).intValue()).intValue(),
							  terms_names,
							  sum_of_products_or_product_of_sums
							  )
						  );
		
		if(essential_primes_st_v.size()>0)
			essential_primes_st =
					addFormatedPrimes(essential_primes_st_v, sum_of_products_or_product_of_sums);
		
		for(int i = 0; i < minimume_compination_of_non_essential_table.size(); i++){
			Vector<Integer>temp = minimume_compination_of_non_essential_table.get(i);
			
			Vector<String>temp_st = new Vector<String>();
			
			for(int j = 0; j < temp.size(); j++)
				temp_st.add(
						formatPrime( 
							mini_vals.get(temp.get(j).intValue()).intValue(),
							mini_subs.get(temp.get(j).intValue()).intValue(),
							terms_names,
							sum_of_products_or_product_of_sums
							 )
						);
			
			non_essential_st_v.add(addFormatedPrimes(temp_st, sum_of_products_or_product_of_sums));
		}
		
		String final_answer = new String("<html>");
		
		if(non_essential_st_v.isEmpty())
			final_answer = "<html><font color=blue><big>" + 
				essential_primes_st + 
				"</big></font></html>";
		
		else{  
			for(int i = 0; i < non_essential_st_v.size(); i++)
				final_answer +=
					"<font color=blue><big>"+
					essential_primes_st+
					"</big></font>"+
					"<font color=red><big>"+
					((essential_primes_st.length() > 0) ? ((sum_of_products_or_product_of_sums == 0) ? " + " : " . ") : "")+
					"</big></font>"+
					"<font color=green><big>"+
					non_essential_st_v.get(i)+
					"</big></font>"+
					(
					 (i != non_essential_st_v.size() - 1) ?
					 ( 
					  "\n\n<br></br><br></br>"+
					  "<big>"+
					  "OR"+
					  "</big>"+
					  "\n\n<br></br><br></br>"
					   )
					 :
					 ""
					 )
					;
		}
		final_answer += "</html>";
		
		return final_answer;
    }
    
    /*
     * formatPrime format the prime to a string
     */
    
    private String formatPrime(int value, int sub , String [] terms_names, int format){
		String answer = new String();
		
		boolean check = false;
		
		for(int i = (terms_names.length - 1); i >= 0; i--){
			if((sub % 2) == 0){	
				if(((value % 2) == 0 && format == 0) || ((value % 2) == 1 && format== 1))
					answer = terms_names[i] + "'" + ((check) ? ((format==0) ? "." : "+") : "") + answer;
				else
					answer = terms_names[i] + " " + ((check) ? ((format==0) ? "." : "+") : "") + answer;
			
				answer = " " + answer;
			
				check = true;
			}	    
			sub /= 2;
			
			value /= 2;
		}
		answer = "(" + answer + ")";
		
		return answer;
    }	
    
    /*
     * add the formated prime to a full answer
     */
    
    private String addFormatedPrimes(Vector<String> essential_primes_st_v, int format){	
		String answer = new String();
	
		answer = essential_primes_st_v.get(0);
	
		for(int i = 1; i < essential_primes_st_v.size(); i++)
			answer += ((format == 0) ? " + " : " . ") + essential_primes_st_v.get(i);
	
		return answer;
	}
}
