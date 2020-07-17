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
 *PrimeImplicantsChartTable handels the prime imlicants table
 */

package solve;

import java.util.Vector;

public class PrimeImplicantsChartTable{
    Vector<PrimeImplicantsChartRow> table;
    
    Vector<PrimeImplicantsChartRow> non_essential_table = new Vector<PrimeImplicantsChartRow>();
    
    Vector<Vector<Integer>> minimume_compination_of_non_essential_table = new Vector<Vector<Integer>>();
    
    Vector<Integer> essential_primes_indexs = new Vector<Integer>();
    
    int number_of_rows;
    
    int number_of_columns;
    
    int [] satisfied_columns_by_essintial_primes;
	
    
    PrimeImplicantsChartTable(int [] values,
			      Vector<Integer> mini_vals, 
			      Vector<Integer> mini_subs,
			      int one_sol_or_all_possible
			      ){
		Vector<Integer> ones_positions_in_table;
    	
		number_of_rows = mini_vals.size();
	
		table = new Vector<PrimeImplicantsChartRow>();
	
		ones_positions_in_table = onesPositionsInTable(values);
	
		number_of_columns = ones_positions_in_table.size();
	
		for(int i = 0; i < number_of_rows; i++)
			addRow(
			   createPrimeChartRow(
						   calPossibleValues(
								 mini_vals.get(i).intValue(),
								 mini_subs.get(i).intValue()
								 ),
						   ones_positions_in_table
						   ),
			   i
			   );
	
		calculateALL(one_sol_or_all_possible);
    }
    
    /*
     *onesPositionsInTable return ones position in table 
     */
    private Vector<Integer> onesPositionsInTable(int [] values){
    	Vector<Integer> pos = new Vector<Integer>();
	
    	for(int i = 0; i < values.length; i++)
    	    if(values[i] == 1)
				pos.add(i);
	
    	return pos;
    }
    
    /*
     *create the a row in the prime implicants table
     */
    
    private int[] createPrimeChartRow(Vector<Integer> possible_values, Vector<Integer> pos){
		int p = pos.size();
	
		int check[] = new int[p];
	
		for(int i = 0; i < p; i++)
			for(int j = 0; j < possible_values.size(); j++)
				if(pos.get(i).intValue() == possible_values.get(j).intValue()){
					check[i] = 1;
					break;
				}
		return check;
    }
    
    /*
     *calPossibleValues calculate all possible values from number and sub field 
     */
    
    private Vector<Integer> calPossibleValues(int number, int sub){
		Vector<Integer> values = new Vector<Integer>();
	
		Vector <Integer>pos = calNumberAndPositionsOfOnes(sub);
	
		int s = pos.size();
	
		int pow = (int)Math.pow(2, s);
	
		int sum = 0;
	
		for(int i = 0; i < pow; i++){
			for(int j = 0; j < s; j++)
				sum = sum + (decimalToBinary(i,j) * (int)Math.pow(2, pos.get(j).intValue()));
			
			values.add(number + sum);
	    
			sum=0;
		}
		return values;
    }
    
    /*
     *calNumberAndPositionsOfOnes return the position of ones in a number ex: 11(1011) the positions is 1,2,4
     */
    
    private Vector<Integer> calNumberAndPositionsOfOnes(int number){
		int p=(int)Math.ceil(Math.log(number) / Math.log(2));
	
		int max=(int)Math.pow(2, p);
	
		Vector<Integer> pos=new Vector<Integer>();
	
		int count=0;
	
		while(max != 0){
			if(number >= max){
				number -= max;
		
				pos.add(p-count);
			}		
			count++;
	    
			max /= 2;
		}
		return pos;
    }
    /*
     *calculateALL calculate the answer
     */
    
    private void calculateALL(int one_sol_or_all_possible){
		createEssentialPrimesIndexs();
	
		if(checkNonEssentialExist()){
			if(one_sol_or_all_possible == 0)
				createMinimumeCompinationOfNonEssentialTable();
		
			else
				createOneMinimumeCompinationOfNonEssentialTable();
		}
    }
    
    /*
     *check if there is any unessential terms
     */
    
    private boolean checkNonEssentialExist(){
		boolean check = false;
	
		for(int i = 0; i<satisfied_columns_by_essintial_primes.length; i++)
			if(satisfied_columns_by_essintial_primes[i] == 0)
				check=true;
	
		return (check && (non_essential_table.size() > 0));
    }
    
    /*
     *get the essential terms
     */
    
    private void createEssentialPrimesIndexs() throws java.lang.OutOfMemoryError{
		int count = 0;
	
		int essential_prime_index = -1;
	
		int [] mark_essential_primes = new int[number_of_rows];
	
		satisfied_columns_by_essintial_primes = new int[number_of_columns];
	
		for(int i = 0; i < number_of_columns; i++){
			for(int j = 0; j < number_of_rows; j++){
				if(table.get(j).getCell(i) == 1){
					count++;
		    
					essential_prime_index = j;
				}
			}
			if(count == 1){
				mark_essential_primes[essential_prime_index] = 1;
		
				satisfied_columns_by_essintial_primes[i] = 1;
			}
			count = 0;
		}
		for(int i = 0; i < number_of_rows; i++){
			if(mark_essential_primes[i] == 0)
				non_essential_table.add(table.get(i));
	    
			else
			essential_primes_indexs.add(i);
		}
		for(int i = 0; i < essential_primes_indexs.size(); i++)
			for(int  j = 0; j < number_of_columns; j++)
				if(table.get(essential_primes_indexs.get(i).intValue()).getCell(j)== 1)
					satisfied_columns_by_essintial_primes[j] = 1;
			
		
    }
    
    /*
     *createMinimumeCompinationOfNonEssentialTable is a function that calculates all possible minimume compinations of the non essential terms
     */
    
    private void createMinimumeCompinationOfNonEssentialTable(){
		int [] check = new int[number_of_columns];
	
		int smallest = non_essential_table.size();
	
		GenerateNumbersOrderedByNumberOfOnes g = new GenerateNumbersOrderedByNumberOfOnes(non_essential_table.size());
	
		Vector<Long> temp = new Vector<Long>();
	
		boolean mark = false;
	
		Vector<Integer> temp_combine = new Vector<Integer>();
	
       	check = satisfied_columns_by_essintial_primes;
	
		for(int i = 1; i < non_essential_table.size(); i++){
			temp = g.getNumbers();
			
			for(int j = 0; j < temp.size(); j++){
				for(int k = 0; k < non_essential_table.size(); k++){
					if(decimalToBinary(temp.get(j).longValue(),k) == 1){
					check=combine(non_essential_table.get(k).getRow(), check);
				
					temp_combine.add(non_essential_table.get(k).getRowIndex());
					}
					if (Thread.currentThread().isInterrupted())
						return;
				}
				if(checkAnswer(check)){
					minimume_compination_of_non_essential_table.add(temp_combine);
				
					mark = true;
				}
				temp_combine = new Vector<Integer>();
			
				check = satisfied_columns_by_essintial_primes;
			}
			if(mark)
				break;
		}	
    }

    /*
     *createOneMinimumeCompinationOfNonEssentialTable is a function that calculates one possible minimume compination of the non essential terms
     *and is faster than the function createMinimumeCompinationOfNonEssentialTable.
     */
    
    private void createOneMinimumeCompinationOfNonEssentialTable(){
		Vector<PrimeImplicantsChartRow> temp_table = non_essential_table;
		
		Vector<Integer> temp_combine = new Vector<Integer>();
		
		int max_n = 0;
		
		PrimeImplicantsChartRow max_r = new PrimeImplicantsChartRow();
			
		for(int j = 0; j < temp_table.size(); j++)
			temp_table.get(j).setRow(
				combineNotFirst(
					satisfied_columns_by_essintial_primes,
					temp_table.get(j).getRow()
					),
				temp_table.get(j).getRowIndex()
				);
			
		while(temp_table.size() > 0){
			max_n = calculateRowWithMaximumNumberOfOnes(temp_table);
			
			max_r = temp_table.get(max_n);
			
			temp_combine.add(max_r.getRowIndex());
			
			temp_table.remove(max_n);
			
			for(int j = 0;j < temp_table.size(); j++){
				temp_table.get(j).setRow(combineNotFirst(max_r.getRow(), temp_table.get(j).getRow()), temp_table.get(j).getRowIndex());
			
				if(temp_table.get(j).getNumberOfOnesInRow() == 0){
					temp_table.remove(j);
				
					j--;
				}
			}   
		}
		minimume_compination_of_non_essential_table.add(temp_combine);
    }

    /*
     *calculateRowWithMaximumNumberOfOnes returns the number of the row that has the maximum number of ones.
     */
    
    private int calculateRowWithMaximumNumberOfOnes(Vector<PrimeImplicantsChartRow> temp_table){
		int max = temp_table.get(0).getNumberOfOnesInRow();
		
		int max_n = 0;
		
		for(int i = 1; i < temp_table.size(); i++){
			if(temp_table.get(i).getNumberOfOnesInRow() > max){
				max = temp_table.get(i).getNumberOfOnesInRow();
			
				max_n = i;
			}
		}
		return max_n;
    }
    
    /*
     *return a digit of the input number according to the position enterd
     *if the  number is 6(0110) and the position is 2 it will return 1
     */
    
    private int decimalToBinary(long number, int pos){
		int temp = 0;
		
		for(int i = 0; i <= pos; i++){
			temp = (int)(number % 2);
			
			number /= 2;
		}
		return temp;
    }
    
    /*
     *combine will combine two integer arrays of ones and zeros(works like an or gate)
     *x[] = 1110001, y[]=0111011
     *1110001 or  0111011 =>1111011
     */
    
    private int[] combine(int [] x, int [] y){
		int [] temp = new int[x.length];
		
		for(int i = 0; i < y.length; i++)	    
			if(x[i] == 1 || y[i] == 1)
			temp[i] = 1;
		
		return temp;
    }

    /*
     *combineNotFirst will combine two integer arrays of ones and zeros(works like an or gate but it reverse the first input(x) first)
     *x[] = 1110001, y[]=0111011
     *'(1110001) and  0111011 =>0111111
     */
    
    private int[] combineNotFirst(int [] x, int [] y){
		int [] temp = new int[x.length];
		
		for(int i = 0; i < y.length; i++)	    
			if(x[i] == 1)
				temp[i] = 0;
			else
				temp[i] = y[i];
		
		return temp;
    }
    
    /*
     *check if all the inputed array digits is one 
     */
    
    private boolean checkAnswer(int [] check){
		boolean ch = true;
		
		for(int i = 0;i < check.length; i++)
			if(check[i] == 0)
				ch = false;
		
		return ch;
    }
    
    void addRow(int [] row, int index){
		PrimeImplicantsChartRow temp = new PrimeImplicantsChartRow();
	
		temp.setRow(row, index);
	
		table.add(temp);
    }
    
    int [] getRow(int row_number){
		return table.get(row_number).getRow();
    }
    
    Vector<Integer> getEssentialPrimesIndexs(){
		return essential_primes_indexs;
    }
    
    Vector<Vector<Integer>> getMinimumeCompinationOfNonEssentialTable(){
		return minimume_compination_of_non_essential_table;
    }
}
