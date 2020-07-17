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
 *this is a tail list class
 *this list represents the position of ones and don't cares in the truth table
 *it was implemented that way to use Quine–McCluskey algorithm
 */

package solve;

import java.util.Vector;

public class Tlist{
    private int size;    //list size
    
    private Node head;   //list head node
    
    private Node tail;   //list tail node
    
    Tlist(){
        size = 0;
	
        head = null;
	
        tail = null;
    }
    
    /*
     *add node to the list
     */
    
    void addNode(int value, int sub, boolean mark){
		Node temp = new Node();
		
		if(size == 0)
			head = tail = temp;
		
		else{
			tail.setNext(temp);
			
			tail=temp;
		}
		size++;
		
		temp.setValue(value);
		
		temp.setSub(sub);
		
		temp.setMark(mark);
    }
    
    /*
     *add node to the list
     */
    
    void addNode(int value){
		addNode(value, 0, false);
    }
    
    /*
     * get list size
     */
    
    int getSize(){
		return size;
    }
    
    /*
     * get list head
     */
    
    Node getHead(){
		return head;
    }
    
    /*
     * get list tail
     */
    
    Node getTail(){
		return tail;
    }
    
    /*
     * get all the value fields in each node of the list in avector
     */
    
    public Vector<Integer> getValues(){
		Vector<Integer> values = new Vector<Integer>();
		
		Node temp = head;
		
		while(temp != null){
			values.add(temp.getValue());
			
			temp = temp.getNext();
		}
		return values;
    }
    
    /*
     * get all the sub fields in each node of the list in avector
     */
    
    public Vector<Integer> getSubs(){
		Vector<Integer> subs = new Vector<Integer>();
		
		Node temp = head;
		
		while(temp != null){
			subs.add(temp.getSub());
			
			temp = temp.getNext();
		}
		return subs;
    }
}
