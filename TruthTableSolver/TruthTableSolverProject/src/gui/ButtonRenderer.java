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
 * this class renders the buttons in the "FUNCTION" column
 */

package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class ButtonRenderer extends JButton implements TableCellRenderer {
    
    public ButtonRenderer() {
		setOpaque(true);
    }
    
    public Component getTableCellRendererComponent(
						   JTable table, 
						   Object value,
						   boolean isSelected, 
						   boolean hasFocus, 
						   int row, 
						   int column
						   ){
	
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			
			setBackground(table.getSelectionBackground());
		} 
		else{
			setForeground(table.getForeground());
			
			setBackground(UIManager.getColor("Button.background"));
		}
		
		setText((value ==null) ? "" : value.toString());
		
		return this;
    }
}
