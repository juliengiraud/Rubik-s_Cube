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
 * this is a node class for the Tlist class
 * it was implemnted that way to use Quine–McCluskey algorithm
 *
 * the value field is the position of 1 or don't care
 *
 *
 *
 * the sub field is used to show all the possible values of the value through the minimization
 * and it's called sub because it the subtraction of two values
 * and by checking if the sub is a power of two (0 or 1 or 2 or 4 etc.)you can check if they are
 * different by one bit according to the Quine–McCluskey algorithm
 *
 * ex:
 *4(0100) and 6(0110) is differnt by one bit and (6-4) = 2
 *6(0110) and 7(0111) is differnt by one bit and (7-6) = 1
 *
 * you can check if the two values is differnt by one bit by this method with out
 * transfaring from decimal to binary and checking every bit
 *
 * ex :
 *
 * if the value is : 4(0100) and the sub is 8(1000)
 *
 * look for the ones in the sub
 * if the number of ones in the sub field is one that means the there is two posiblities
 *the posiblities is(0100+0000) or (0100+1000)
 *
 * ex 2:
 * if the value is : 4(0100) and the sub is 6(0110)
 *
 * he number of ones in the sub field is two that means the there is four posiblities
 * the posiblities is(0100+0000) or (0100+0010) or (0100+0100) or(0100+0110)
 *
 *
 * the mark field is det to be true if the value can not be compined any more during the minimization
 * and should be added to the prime implicant chart
 *
 *
 *
 * the next field is pointing to the next node
 */


package solve;

class Node{
    private int value;
    
    private int sub;
    
    private Node next;
    
    private boolean mark;
    
    Node(){	
		value = -1;
	
		sub = 0;
	
		next = null;
	
		mark = false;
    }    
    
    void setValue(int v){
		value = v;
    }
    
    void setSub(int s){
		sub = s;
    }
    
    void setNext(Node n){
		next = n;
    }    
    
    void setMark(boolean m){
		mark = m;
    }
    
    int getValue(){
		return value;
    }
    
    int getSub(){
		return sub;
    }
    
    Node getNext(){
		return next;
    }
    
    boolean getMark(){
		return mark;
    }
}
