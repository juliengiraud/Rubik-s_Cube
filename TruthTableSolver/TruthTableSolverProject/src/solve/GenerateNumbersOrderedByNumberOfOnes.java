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
 *GenerateNumbersOrderedByNumberOfOnes class generate decimal numbers by number of ones in the binary form
 *
 *like the following:
 *
 *binary     decimal
 *
 *000        0
 *
 *001        1
 *010        2
 *100        4
 *
 *011        3
 *101        5
 *110        6
 *
 *111        7
 */


package solve;

import java.util.Vector;

public class GenerateNumbersOrderedByNumberOfOnes{
    private Vector<Long> previous;
    
    private int number_of_terms;
    
    private int number_of_ones;
    
    GenerateNumbersOrderedByNumberOfOnes(int n_o_t){
		number_of_terms = n_o_t;
	
		number_of_ones = 0;
	
		previous = generateNumbers_1();
    }
	
    /*
     *generateNumbers_1 generate numbers that has only one (1) in the binary form like(1,2,4,8).
     */
	 
    private Vector<Long> generateNumbers_1(){
		Vector<Long> sol = new Vector<Long>();
	
		for(int i = 0; i < number_of_terms; i++)
			sol.add((long)Math.pow(2, i));
		
		return sol;
    }
    /*
     *generateNumbers_V generate numbers that has number of ones in the binary from equal to the variable "number_of_ones".
     */
    
    private Vector<Long> generateNumbers_V(){
		Vector<Long> sol = new Vector<Long>();
	
		sol.add(((long)Math.pow(2, number_of_ones)) - 1);
	
		for(int i = (number_of_ones - 1); i >= 0; i--)
			sol.add(sol.get(sol.size() - 1).longValue() + (long)Math.pow(2, i));
		
	
		for(int i = (number_of_ones + 1); i < number_of_terms; i++){
			for(int j=0;j<previous.size();j++){
				if(previous.get(j).longValue() >= (long)Math.pow(2, i))
					break;
		
				else
					sol.add(previous.get(j).longValue() + (long)Math.pow(2, i));
			}
		}	
		previous = sol;
	
		return sol;
    }

    /*
     *getNumbers cals the generating functions and return the generated numbers depending on the variable "number_of_ones".
     */
    
    public Vector<Long> getNumbers(){
		number_of_ones++;
	
		if(number_of_ones == 1)
			return previous;
	
        else if(number_of_ones == number_of_terms){
			Vector<Long> temp = new Vector<Long>();

	    temp.add(((long)Math.pow(2, number_of_terms)) - 1);
	    
	    return temp;
		}
		else
			return generateNumbers_V();
    }
}
