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
 * class solver has the functions that minimize the truth table
 * using Quine–McCluskey algorithm and call the output class to
 * format the answer in a readable string
 */

package solve;

import gui.Gui;

public class Solver implements Runnable{    
    private Tlist table; //the table that represent the position of the ones and don't cares in the truth table
    
    private int v[];
    
    private String [] terms_names;
    
    private int sum_of_products_or_product_of_sums;
    
    private int one_sol_or_all_possible;
    
    private String solution;
    
    public Solver(
		  int v[],
		  String [] terms_names, 
		  int sum_of_products_or_product_of_sums, 
		  int one_sol_or_all_possible
		  ){
		table = new Tlist();
	
        this.v = v;
	
        this.terms_names = terms_names;
	
        this.sum_of_products_or_product_of_sums = sum_of_products_or_product_of_sums;
	
        this.one_sol_or_all_possible = one_sol_or_all_possible;
    }
    
    public void Solve(){
		Tlist min;
	
        Format out = new Format();
	
		boolean no_ones = checkNoOnes();
	
		boolean no_zeros = checkNoZeros();
	
		if(no_ones && no_zeros)
			solution = "<html><font color=blue><big>"+
			"ONE OR ZERO"+
			"</big></font></html>";
		
		else if(no_zeros)
			solution = "<html><font color=blue><big>"+
			"ONE"+
			"</big></font></html>";
		
		else if(no_ones)
			solution = "<html><font color=blue><big>"+
			"ZERO"+
			"</big></font></html>";
	
        else{
            fillTable();
	    
            min = minimize(table);
	
            min = smoosh(min);
	    
            solution = out.formatOutput(
					v,
					min.getValues(),
					min.getSubs(),
					terms_names,
					sum_of_products_or_product_of_sums,
					one_sol_or_all_possible
					);
        }

    }
    
    /*
     * fill the table with it's values.
     */
    
    private void fillTable(){
       	if(sum_of_products_or_product_of_sums == 1)
	    v = inverse(v);

	for(int i = 0; i < v.length; i++)
	    if(v[i] != 0)
		table.addNode(i);
    }
    
    /*
     *turn ones to zeros and turn zeros to ones
     */
    
    private int[] inverse(int v[]){
	int k[] = v;

	for(int i = 0; i < k.length; i++){
	    if(k[i] == 0)
		k[i] = 1;
	    else if(k[i] == 1)
		k[i] = 0;
	}
	return k;
    }

    /*
     * check if the truth table has no ones.
     */
    
    private boolean checkNoOnes(){
		boolean check = true;
	
		for(int i = 0 ; i < v.length; i++)
			if(v[i] == 1)
				check = false;
		
		return check;
    }
    
    /*
     * check if the truth table has no zeros.
     */
    
    private boolean checkNoZeros(){
		boolean check = true;
	
		for(int i = 0; i < v.length; i++)
			if(v[i] == 0)
				check = false;
		
		return check;
    }
    
    /*
     * minimize is the function that minimize the truth table.
     */
    
    private Tlist minimize(Tlist mtable){
       	Node temp = new Node();   
	
		Node ntemp = new Node();  
		
		Tlist res = new Tlist();  
		
		boolean check_end = true; 
		
		int subt = 0;            
		
		temp = mtable.getHead();
		
		while(temp != null){
			ntemp = temp.getNext();
			
			while(ntemp != null){
				subt = ntemp.getValue() - temp.getValue();
				
				if(checkVaryByOneDigite(temp, ntemp)){
					if(
					   (res.getHead() == null) 
					   || 
					   (res.getTail().getValue() != temp.getValue()) 
					   || 
					   (res.getTail().getSub() != temp.getSub() + subt)
					   )
						res.addNode(temp.getValue(), temp.getSub() + subt, false);
					
					check_end = false;
					
					temp.setMark(true);
					
					ntemp.setMark(true);
				}
				ntemp = ntemp.getNext();
			}
			if(!temp.getMark())
				res.addNode(temp.getValue(), temp.getSub(), false);
			
			temp = temp.getNext();
		}
		if(check_end)
			return res;
		
		return minimize(res);
    }
    
    /*
     * checkVaryByOneDigite checks if the two values vary by one digit.
     */
    
    private boolean checkVaryByOneDigite(Node temp,Node ntemp){	
		int v, nv;
		
		int s, ns;
		
		int subt;
		
		v = temp.getValue();
		
		nv = ntemp.getValue();
		
		s = temp.getSub();
		
		ns = ntemp.getSub();
		
		subt = Math.abs(v - nv);
		
		if(subt == 0 || s != ns){
			return false;
		}
		else if(subt == 1){
			if(v%2 == 0)
				return true;
			else
				return false;
		}
		else if(checkPowerOfTwo(subt)){
			if(((v / subt) % 2) == 0)
				return true;
			else
				return false;
		}
		return false;
    }
    
    /*
     * checkPowerOfTwo return true if the number is a power of two (0,1,2,4,8,.....).
     */
    
    private boolean checkPowerOfTwo(int number){
		double temp=Math.log(number) / Math.log(2);
	
		return ((Math.ceil(temp) == Math.floor(temp)) && (number!=0));
    }
    
    /*
     * remove any identical nodes in the list by one node.
     */
    
    private Tlist smoosh(Tlist min){
		Tlist fin;
		
		Node temp , ntemp;
		
		fin = new Tlist();
		
		temp = min.getHead();
		
		boolean check = true;
		
		while(temp != null){
			ntemp = temp.getNext();
			
			while(ntemp != null){
				if(equal(temp, ntemp))
					check = false;
			
				ntemp = ntemp.getNext();
			}
			if(check)
				fin.addNode(temp.getValue(), temp.getSub(), temp.getMark());
			
			check = true;
			
			temp = temp.getNext();
		}
		return fin;
    }
    
    /*
     * check if two nodes are equal
     */
    
    private boolean equal(Node temp, Node ntemp){
		return(
			(temp.getValue() == ntemp.getValue()) 
			&& 
			(temp.getSub() == ntemp.getSub())
			);
    }
    /*
     *run is the function that starts when the thread is of the current object starts.
     */
    public void run() {
        Gui.setAnswer("Solving.......");
	
        Gui.setStatusBar("Solving......Please Wait(\"This may take long time\").");
	
        Gui.setProgressBar(true);
	
        Solve();
	
        Gui.setAnswer(solution);
	
        Gui.setStatusBar("Done");
	
        Gui.setProgressBar(false);
	
    }
    
    /*
     *getSolution return the solution.
     */
    
    public String getSolution(){
        return solution;
    }
}

