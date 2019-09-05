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
 * this class handels the values of the buttons in the "FUNCTION" column
 */

package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ButtonEditor extends DefaultCellEditor 
{
    protected JButton button;
    private String    label ;
    private boolean   isPushed;
    
    private static int clicked_button_number=0;
    
    
    
    public ButtonEditor(JCheckBox checkBox) {
		super(checkBox);

		Gui.setAndClearValuesArray();

		button = new JButton();
		button.setOpaque(true);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
    }
    
    
    @Override
    public Component getTableCellEditorComponent(
						 JTable table, 
						 Object value,
						 boolean isSelected, 
						 int row, 
						 int column
						 ){
		clicked_button_number = row;
		
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			
			button.setBackground(table.getSelectionBackground());
		} 
		else{
			button.setForeground(table.getForeground());
			
			button.setBackground(table.getBackground());
		}
		label = (value ==null) ? "" : value.toString();
		
		button.setText( label );
		
		isPushed = true;
		
		return button;
    }
    
    @Override
	public Object getCellEditorValue() {
		if (isPushed)  {
			if(label.equalsIgnoreCase("<html><font color=green>0</font></html>")){
				label="<html><font color=red>1</font></html>";
				Gui.values.set(clicked_button_number,1);
			}
			else if(label.equalsIgnoreCase("<html><font color=red>1</font></html>")){
				label="<html><font color=blue>d</font></html>";
				Gui.values.set(clicked_button_number,2);
			}
			else if(label.equalsIgnoreCase("<html><font color=blue>d</font></html>")){
				label="<html><font color=green>0</font></html>";
				Gui.values.set(clicked_button_number,0);
			}
		}	
		isPushed = false;
		
		return label ;
    }
    
    @Override
	public boolean stopCellEditing() {
		isPushed = false;
		
		return super.stopCellEditing();
    }
    
    @Override
	protected void fireEditingStopped() {
		super.fireEditingStopped();
    }
}

