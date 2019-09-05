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
 * the Main class which check for the command line
 * parameters if it was "-nw" the command line mode
 * works if there is no parameters the gui works
 */

package main;

import solve.*;

import cmdIn.*;

import gui.*;

public class Main{
    public static void main(String args []) throws Exception {
		if(args.length != 0){
			if(args[0].equalsIgnoreCase("-nw")){
				Prompt in = new Prompt();
						
				String answer;
				
				in.promptUser();

				Solver sol = new Solver(
							in.getValues(),
							in.getTermsNames(),
							in.getSumOfProductsOrProductOfSums(),
							in.getOneAllPossibleSolutionsOrOneSolution()
							);
				
				sol.Solve();

				System.out.println();
				
				answer = sol.getSolution();
				
				answer = answer.replaceAll("\\<.*?>", "");	//remove html tags
				
				System.out.println("\nTHE RESULT :\n\n" + answer);
			}
			else{
				System.out.println("\n" +args[0]+ " : INVALIED PARAMETER, PROGRAM WILL EXIT.");
			
				System.exit(0);
			}
		}
		
		else{
			Gui g=new Gui();
		
			g.createGui();
		
			g.validate();
		}
	}
}
