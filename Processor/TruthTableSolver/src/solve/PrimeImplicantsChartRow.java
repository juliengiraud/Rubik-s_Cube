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
 *PrimeImplicantsChartRow class represent a row in the PrimeImplicantsTable class
 */

package solve;

public class PrimeImplicantsChartRow{
    private int [] row; //prime implicants row values
    
    private int row_index;//prime implicants row index
    
    private int row_length; //prime implicant row length
    
    PrimeImplicantsChartRow(){
		row_index = -1;
	
		row_length = 0;
    }
    
    void setRow(int [] x_row, int index){
		row = x_row;
	
		row_index = index;
	
		row_length = row.length;
    }
    
    void setRow(int [] x_row){
		row = x_row;
	
		row_length = row.length;
    }
    
    int [] getRow(){
		return row;
    }
    
    int getCell(int cell_number){
		return row[cell_number];
    }
    
    int getRowIndex(){
		return row_index;
    }
    
    int getRowLength(){
		return row_length;
    }
    
    int getNumberOfOnesInRow(){
		int count = 0;
	
		for(int i = 0; i < row_length; i++)
			if(row[i] == 1)
				count++;
	
		return count;
    }
}
