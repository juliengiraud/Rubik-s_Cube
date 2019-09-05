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
 *this class draws the gui
 */
package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;

import java.util.Vector;

import java.io.IOException;

import solve.*;


public class Gui extends JFrame{
    public JPanel mainpanel;
    
    public static int number_of_terms;
    
    public static Vector<Integer> values;
    
    static String [] terms_names;

    static final  JEditorPane editor_pane = new  JEditorPane();

    static final JLabel status_bar = new JLabel();

    static JProgressBar progress_bar;

    static final String [] default_terms_names =
		{"A", "B", "C", "D", "E", "F", "G", "H" , "I" , "J" , "K" , "L" , "M" , "N" , "O" , "P"};
          
    int sum_of_products_or_product_of_sums;

    int one_sol_or_all_possible;

    Thread t = new Thread();

    
    public Gui(){
		super("Truth Table Solver 1.2 Beta");
	
        terms_names = default_terms_names;
	
		number_of_terms = 2;

		sum_of_products_or_product_of_sums = 0;

		one_sol_or_all_possible = 0;

        progress_bar = new JProgressBar();

        values = new Vector<Integer>(number_of_terms);

        setAndClearValuesArray();
	
		mainpanel = new JPanel(new GridBagLayout());
		mainpanel.setOpaque(true);
	
		JScrollPane scroll = new JScrollPane(mainpanel);
	
		add(scroll, BorderLayout.CENTER);
		setSize(750, 700);
		setMinimumSize(new Dimension(950, 700));
	
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
    }
    
    public void createGui() throws IOException{
		/*
		 *create the menubar
		 *create file and help menues
		 */
		
		JMenuBar menubar = new JMenuBar();
		
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		
		JMenuItem fileClose = new JMenuItem("Close");
		fileClose.setMnemonic(KeyEvent.VK_C);
		fileClose.setToolTipText("Exit application");
		fileClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		
		file.add(fileClose);
		
		JMenu Help = new JMenu("Help");
		Help.setMnemonic(KeyEvent.VK_F);
		
		JMenuItem help = new JMenuItem("help");
		help.setMnemonic(KeyEvent.VK_H);
		help.setToolTipText("help");
		
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JPanel panel = new JPanel();
				panel = new JPanel();
				panel.setLayout(new GridLayout(2, 2));
				
				JOptionPane.showMessageDialog(panel, "see \"HOW TO.txt\" in the program folder.");
			}
		});
		
		JMenuItem About = new JMenuItem("About");
		About.setMnemonic(KeyEvent.VK_A);
		About.setToolTipText("About");
		
		About.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JPanel panel = new JPanel();
				panel = new JPanel();
				panel.setLayout(new GridLayout(2, 2));
				
				JOptionPane.showMessageDialog(
							  panel,
							  "                                 <Truth Table Solver 1.2 Beta>\n"+
							  "                             Copyright (C) <2011>  <Sherif Ahmed>\n\n"+
							  "Truth table Solver is a program that solves the truth table and output\n"+
							  "all possible minimized equations.\n\n"+
							  "This program is free software: you can redistribute it and/or modify\n"+
							  "it under the terms of the GNU General Public License as published by\n"+
							  "the Free Software Foundation, either version 3 of the License, or\n"+
							  "(at your option) any later version.\n\n"+
							  "This program is distributed in the hope that it will be useful,\n"+
							  "but WITHOUT ANY WARRANTY; without even the implied warranty of\n"+
							  "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n"+
							  "GNU General Public License for more details.\n\n"+
							  "You should have received a copy of the GNU General Public License\n"+
							  "along with this program.  If not, see <http://www.gnu.org/licenses/>.\n\n"+
							  "report any bug to <eng_sherif_ahmed@live.com>"
							  );
			}
		});
		
		Help.add(help);
		Help.add(About);
		
		menubar.add(file);
		menubar.add(Help);
		
		setJMenuBar(menubar);
		
		/*
		 *create terms names table
		 */
		
		ListModel temrs_names_table_listModel = new AbstractListModel(){
			public int getSize() {
				return number_of_terms;
			}
			public Object getElementAt(int index){
				return Character.toString ((char)(65 + index));
			}
		};
		
		final AbstractTableModel y;
		
		TableModel terms_names_table_dataModel =
			y = new AbstractTableModel()
			{ 
				public int getColumnCount(){
					return 1;
				}
				public int getRowCount(){
					return number_of_terms;
				}
				@Override
				public boolean isCellEditable(int row, int col){
					return true;
				}
				@Override
				public void setValueAt(Object value, int row, int col){
					terms_names[row] = (String)value;
					fireTableCellUpdated(row, col);
				}
				
				public Object getValueAt(int row, int col){
					return terms_names[row];
				}
				@Override
				public String getColumnName(int col){
					return "Terms Names";
				}
			};
		
		final JTable terms_names_table = new JTable(terms_names_table_dataModel);
		terms_names_table.setPreferredScrollableViewportSize(new Dimension(180, 160));
		
		JList terms_names_rowHeader = new JList(temrs_names_table_listModel);
		terms_names_rowHeader.setFixedCellWidth(50);
		terms_names_rowHeader.setFixedCellHeight(terms_names_table.getRowHeight()
							 + terms_names_table.getRowMargin() - 1);
		terms_names_rowHeader.setCellRenderer(new RowHeaderRenderer(terms_names_table));
		
		final JScrollPane terms_names_scroll=new JScrollPane(terms_names_table);
		terms_names_scroll.setCorner(JScrollPane.UPPER_LEFT_CORNER,terms_names_table.getTableHeader());
		terms_names_scroll.setRowHeaderView(terms_names_rowHeader);
		terms_names_scroll.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		/*
		 *create the main table(the truth table)
		 */
		
		ListModel main_table_listModel = new AbstractListModel(){
			public int getSize(){
				return (int)Math.pow(2, number_of_terms);
			}
			public Object getElementAt(int index){
				return index;
			}
		};
		
		final AbstractTableModel x;
		
		TableModel main_table_dataModel =
			x = new AbstractTableModel()
			{
				private Object[][] data = getData(number_of_terms+1);
				
				public int getColumnCount() {
					data = getData(number_of_terms + 1);
					return number_of_terms + 1;
				}
				public int getRowCount() {
					return (int)Math.pow(2, number_of_terms);
				}
				@Override
				public boolean isCellEditable(int row, int col)
				{
					if(col == 0)
						return true;
				
					return false;
				}
				@Override
				public void setValueAt(Object value, int row, int col) {
					data[row][col] = value;
					fireTableCellUpdated(row, col);
				}
				public Object getValueAt(int row, int col) {
					return data[row][col];
				}
				@Override
				public String getColumnName(int col) {
					if(col == 0)
						return "FUNCTION";
				
					return terms_names[col - 1];
				}
			};
		
		final JTable main_table = new JTable( main_table_dataModel );
		main_table.getColumn("FUNCTION").setCellRenderer(new ButtonRenderer());
		main_table.getColumn("FUNCTION").setCellEditor(new ButtonEditor(new JCheckBox()));
		

		final JList main_table_rowHeader = new JList(main_table_listModel);
		main_table_rowHeader.setFixedCellWidth(50);
		main_table_rowHeader.setFixedCellHeight(main_table.getRowHeight()
							+ main_table.getRowMargin() - 1);
		main_table_rowHeader.setCellRenderer(new RowHeaderRenderer(main_table));
		
		final JScrollPane main_table_scroll = new JScrollPane( main_table );
		main_table_scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, main_table.getTableHeader());
		main_table_scroll.setRowHeaderView(main_table_rowHeader);
		main_table_scroll.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		main_table_scroll.setPreferredSize(new Dimension(690, 0));
		
		/*
		 *create buttons
		 *create status bar
		 *create text_area
		 *create spinner
		 *create radio buttons
		 */
		
		JButton solve_button = new JButton("S    O    L    V    E");
		solve_button.setForeground(Color.red);
		
		JButton clear_button = new JButton("CLEAR VALUES");
		clear_button.setForeground(Color.blue);
		
		
		status_bar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		editor_pane.setEditable(false);
		editor_pane.setContentType("text/html");
		editor_pane.setText("<html><font color=blue><big>ZERO</big></font></html>");
		
		JScrollPane editor_pane_scroll = new JScrollPane(editor_pane);
		editor_pane_scroll.setPreferredSize(new Dimension(0, 160));
		
		
		SpinnerModel smodel =
			new SpinnerNumberModel(
					   number_of_terms, //initial value
					   2, //min
					   16, //max
					   1
					   );
		final JSpinner spinner = new JSpinner(smodel);
		spinner.setName("number of terms");
		spinner.setFont(new Font( "Dialog", Font.BOLD, 15 ));
		
		JRadioButton sum_of_products = new JRadioButton("<html><font color=olive>Sum Of Products</font></html>");
		sum_of_products.setSelected(true);
		
		JRadioButton product_of_sums = new JRadioButton("<html><font color=olive>Product Of Sums</font></html>");
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
		buttonGroup.add(sum_of_products);
		buttonGroup.add(product_of_sums);
		

		JRadioButton all_possible_sol = new JRadioButton("<html><font color=green>All possible solutions<br></br>(Occasionally slow)</font></html>");
		all_possible_sol.setSelected(true);
		
		JRadioButton one_sol = new JRadioButton("<html><font color=green>One solution<br></br>(fast)</font></html>");
		
		ButtonGroup buttonGroup2 = new ButtonGroup();
		
		buttonGroup2.add(all_possible_sol);
		buttonGroup2.add(one_sol);
		
		
		/*
		 *add listeners
		 */
		
		spinner.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				number_of_terms = Integer.parseInt(spinner.getValue().toString());
				
				setAndClearValuesArray();
				
				main_table.updateUI();
				x.fireTableStructureChanged();
				
				main_table.getColumn("FUNCTION").setCellRenderer(new ButtonRenderer());
				main_table.getColumn("FUNCTION").setCellEditor(new ButtonEditor(new JCheckBox()));
				
				main_table_rowHeader.updateUI();
				main_table_scroll.updateUI();
				terms_names_table.updateUI();    
			}
		});
		
		
		
		terms_names_table.addKeyListener(new KeyListener(){
			
			@Override
				public void keyTyped(KeyEvent e) {
					Vector<Integer> temp=values;//save values
					
					main_table.updateUI();
					main_table_scroll.updateUI();
					x.fireTableStructureChanged();
					
					main_table.getColumn("FUNCTION").setCellRenderer(new ButtonRenderer());
					main_table.getColumn("FUNCTION").setCellEditor(new ButtonEditor(new JCheckBox()));
					
					terms_names_table.updateUI();
					terms_names_scroll.updateUI();
					
					values=temp;//store values
				}
			
			@Override
				public void keyPressed(KeyEvent e) {
				}
			
			@Override
				public void keyReleased(KeyEvent e) {
				}
		});
		
		
		
		terms_names_table.addMouseListener(new MouseListener(){
			
			@Override
				public void mouseClicked(MouseEvent e) {
					Vector<Integer> temp = values;//save values
					
					main_table.updateUI();
					x.fireTableStructureChanged();
					
					main_table.getColumn("FUNCTION").setCellRenderer(new ButtonRenderer());
					main_table.getColumn("FUNCTION").setCellEditor(new ButtonEditor(new JCheckBox()));
					
					main_table_scroll.updateUI();
					terms_names_table.updateUI();
					terms_names_scroll.updateUI();
					
					values=temp;//store values
				}
			
			@Override
				public void mousePressed(MouseEvent e) {
				}
			
			@Override
				public void mouseReleased(MouseEvent e) {
				}
			
			@Override
				public void mouseEntered(MouseEvent e) {
				}
			
			@Override
				public void mouseExited(MouseEvent e) {
				}
		});
		
		clear_button.addActionListener(new ActionListener(){
			
			@Override
				public void actionPerformed(ActionEvent e) {
					terms_names[0]="A";
					terms_names[1]="B";
					terms_names[2]="C";
					terms_names[3]="D";
					terms_names[4]="E";
					terms_names[5]="F";
					terms_names[6]="G";
					terms_names[7]="H";
					terms_names[8]="I";
					terms_names[9]="J";
					terms_names[10]="K";
					terms_names[11]="L";
					terms_names[12]="M";
					terms_names[13]="N";
					terms_names[14]="O";
					terms_names[15]="P";
					
					setAndClearValuesArray();
					
					x.fireTableStructureChanged();
					y.fireTableStructureChanged();
					
					main_table.getColumn("FUNCTION").setCellRenderer(new ButtonRenderer());
					main_table.getColumn("FUNCTION").setCellEditor(new ButtonEditor(new JCheckBox()));
				}
		});
		
		
		
		solve_button.addMouseListener(new MouseListener(){
			
			@Override
				public void mouseClicked(MouseEvent e) {
				
					int [] fvalues = new int[(int)Math.pow(2, number_of_terms)];
					String [] fterms_names = new String[number_of_terms];
				
					Solver sol = new Solver(
								fvalues,fterms_names,
								sum_of_products_or_product_of_sums,
								one_sol_or_all_possible
								);
					
					if(t.isAlive()){
						t.interrupt();
					
						try {
							t.join();
						} 
						catch (InterruptedException ex) {
						}
						t=null;
					}
					t=new Thread(sol);
					
					System.arraycopy(terms_names, 0, fterms_names, 0, number_of_terms);
					
					for(int i = 0; i < (int)Math.pow(2, number_of_terms); i++)
						fvalues[i] = values.get(i).intValue();
					
				
					
					try{
						t.start();
						t.setPriority(9);
						Thread.currentThread().setPriority(1);
					}
					catch(java.lang.OutOfMemoryError ex1){
						JPanel panel = new JPanel();
						panel = new JPanel();
						panel.setLayout(new GridLayout(2, 2));
				
						JOptionPane.showMessageDialog(
								  panel,
								  "    " + ex1.getMessage() +" ERROR"+
								  ((one_sol_or_all_possible==0)?"\n   you should use the \"one solution\" option":""),
								  "ERROR",
								  JOptionPane.ERROR_MESSAGE
								  );
					}
					catch(java.lang.Error ex2){
						JPanel panel = new JPanel();
						panel = new JPanel();
					panel.setLayout(new GridLayout(2, 2));

					JOptionPane.showMessageDialog(panel,
									  "    " + ex2.getMessage()+" ERROR ",
									  "ERROR",
									  JOptionPane.ERROR_MESSAGE);
					}
					finally{
						Gui.setStatusBar("Done");
				
						Gui.setProgressBar(false);
						
						sol=null;
					}
				}
			
			@Override
				public void mousePressed(MouseEvent e) {
				}
			
			@Override
				public void mouseReleased(MouseEvent e) {   
				}
			
			@Override
				public void mouseEntered(MouseEvent e) {   
				}
			
			@Override
				public void mouseExited(MouseEvent e) {
				}
			
		});
		
		editor_pane.addInputMethodListener(null);
		
		sum_of_products.addActionListener(new ActionListener(){
			
			@Override
				public void actionPerformed(ActionEvent e) {
					sum_of_products_or_product_of_sums = 0;
			}
		});
		
		product_of_sums.addActionListener(new ActionListener(){
			
			@Override
				public void actionPerformed(ActionEvent e) {
					sum_of_products_or_product_of_sums = 1;
			}
		});
		
		all_possible_sol.addActionListener(new ActionListener(){
			
			@Override
				public void actionPerformed(ActionEvent e) {
					one_sol_or_all_possible=0;
				}
		});
		
		one_sol.addActionListener(new ActionListener(){

			@Override
				public void actionPerformed(ActionEvent e) {
					one_sol_or_all_possible=1;
				}
			
		});
		
		
		
		/*
		 *arrange gui components
		 */
		
		GridBagConstraints c=new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 0.5;
		c.gridheight=11;
		c.gridwidth=1;
		c.anchor=GridBagConstraints.CENTER;
		mainpanel.add(main_table_scroll, c);
		
		GridBagConstraints c1=new GridBagConstraints();
		c1.fill = GridBagConstraints.BOTH;
		c1.gridx = 1;
		c1.gridy = 0;
		c1.weightx = 0.1;
		c1.weighty = 0.5;
		c1.anchor=GridBagConstraints.WEST;
		mainpanel.add(terms_names_scroll, c1);
		
		GridBagConstraints c2=new GridBagConstraints();
		c2.gridx = 1;
		c2.gridy = 5;
		c2.weightx = 0.1;
		c2.weighty = 0.5;
		c2.anchor=GridBagConstraints.FIRST_LINE_END;
		mainpanel.add(spinner, c2);
		
		GridBagConstraints c3=new GridBagConstraints();
		c3.gridx = 1;
		c3.gridy = 4;
		c3.weightx = 0.1;
		c3.weighty = 0.5;
		c3.anchor=GridBagConstraints.LAST_LINE_END;
		mainpanel.add(
				  new JLabel("<html><font color=green>Number Of Terms</font></html>"), 
				  c3);
		
		GridBagConstraints c4=new GridBagConstraints();
		c4.fill = GridBagConstraints.BOTH;
		c4.gridx = 1;
		c4.gridy = 10;
		c4.weightx = 0.1;
		c4.weighty = 0.5;
		c4.anchor=GridBagConstraints.EAST;
		mainpanel.add(solve_button, c4);
		
		GridBagConstraints c5=new GridBagConstraints();
		c5.gridx = 1;
		c5.gridy = 9;
		c5.weightx = 0.1;
		c5.weighty = 0.5;
		c5.anchor=GridBagConstraints.EAST;
		mainpanel.add(clear_button, c5);
		
		GridBagConstraints c6=new GridBagConstraints();
		c6.fill = GridBagConstraints.BOTH;
		c6.gridx = 0;
		c6.gridy = 11;
		c6.weightx = 0.5;
		c6.weighty = 0.5;
		c6.gridwidth=3;
		mainpanel.add(editor_pane_scroll, c6);
		
		GridBagConstraints c7=new GridBagConstraints();
		c7.fill = GridBagConstraints.BOTH;
		c7.gridx=0;
		c7.gridy=12;
		c7.gridwidth=2;
		c7.gridheight=3;
		mainpanel.add(status_bar, c7);
		
		GridBagConstraints c8=new GridBagConstraints();
		c8.gridx = 1;
		c8.gridy = 5;
		c8.weightx = 0.1;
		c8.weighty = 0.5;
		c8.anchor=GridBagConstraints.LAST_LINE_START;
		mainpanel.add(sum_of_products, c8);

		GridBagConstraints c9=new GridBagConstraints();
		c9.gridx = 1;
		c9.gridy = 6;
		c9.weightx = 0.1;
		c9.weighty = 0.5;
		c9.anchor=GridBagConstraints.FIRST_LINE_START;
		mainpanel.add(product_of_sums, c9);
		
		GridBagConstraints c10=new GridBagConstraints();
		c10.gridx = 1;
		c10.gridy = 7;
		c10.weightx = 0.1;
		c10.weighty = 0.5;
		c10.anchor=GridBagConstraints.LAST_LINE_START;
		mainpanel.add(all_possible_sol, c10);
		
		GridBagConstraints c11=new GridBagConstraints();
		c11.gridx = 1;
		c11.gridy = 8;
		c11.weightx = 0.1;
		c11.weighty = 0.5;
		c11.anchor=GridBagConstraints.FIRST_LINE_START;
		mainpanel.add(one_sol, c11);
		
		GridBagConstraints c12=new GridBagConstraints();
		c12.gridx = 1;
		c12.gridy = 12;
		c12.weightx = 0.1;
		c12.weighty = 0.5;
		c12.anchor=GridBagConstraints.EAST;
		mainpanel.add(progress_bar, c12);
		}
		
		/*
		 *return column names
		 */
		
		String [] getColumnNames(int number_of_terms, AbstractTableModel y){
			String [] temp = new String[number_of_terms];
			
			temp[0] = "FUNCTION";
			
			for(int i = 1;i < number_of_terms; i++)
				temp[i] = y.getValueAt(i - 1, 0).toString();
			
			return temp;
    }
    
    /*
     *fill the main table(truth table) with intial values
     */
    
    Object[][] getData(int number_of_terms){
		int col=number_of_terms;
		
		int row =(int) Math.pow(2,col-1);
		
		Object[][] temp=new Object[row][col];
		
		for(int i = 0;i < row; i++){
				if(values.get(i).intValue() == 0)
			temp[i][0]="<html><font color=green>0</font></html>";
			
				else if(values.get(i).intValue()==1)
			temp[i][0]="<html><font color=red>1</font></html>";
			
				else if(values.get(i).intValue()==2)
			temp[i][0]="<html><font color=blue>d</font></html>";
		}
		int x;
	
		for(int i = 0; i < row; i++){
			x = i;
			
			for(int j = col - 1; j > 0; j--){
				temp[i][j] = x % 2;
				x /= 2;
			}
		}
		return temp;
    }
    
    public static void setAndClearValuesArray(){
        values=new Vector<Integer>();
	
        for(int i = 0;i < (int)Math.pow(2, Gui.number_of_terms); i++)
            values.add(0);
    }
    
    public static void setAnswer(String an){
        editor_pane.setText(an);
    }
    
    public static void setStatusBar(String st){
        status_bar.setText(st);
    }
    
    public static void setProgressBar(boolean b){
        progress_bar.setIndeterminate(b);
    }
    
}
