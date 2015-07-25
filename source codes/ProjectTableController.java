/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectdemo;





import projectdemo.WelcomeAdminGUI;
import javax.swing.ListSelectionModel;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.event.*;

/**
* Glue between the view (CourseListGUI) and the model (CourseListTableModel). 
* No database specific code here. Contains a reference to both the TableModel and the graphical user interface.
* @author rgrover
*/
public class ProjectTableController implements ListSelectionListener, TableModelListener{
	private ProjectTableModel tableModel;
	private WelcomeGUI gui;
        private WelcomeAdminGUI gui_admin;
	public int selectedIndex;
	
	public ProjectTableController(WelcomeGUI gui) {
		this.gui = gui;   
         // create the tableModel using the data in the cachedRowSet
    		tableModel = new ProjectTableModel(); 
    		tableModel.addTableModelListener(this);
	}
        public ProjectTableController(WelcomeAdminGUI gui) {
		this.gui_admin = gui_admin;   
         // create the tableModel using the data in the cachedRowSet
    		tableModel = new ProjectTableModel(); 
    		tableModel.addTableModelListener(this);
	}
	
	
	// new code
	public TableModel getTableModel() {
		return tableModel;
	}
	
	public void tableChanged(TableModelEvent e)
	{
	   try {
	    	// get the index of the inserted row
	        //tableModel.getRowSet().moveToCurrentRow();
	    	int firstIndex =  e.getFirstRow();
	    	
	    	// create a new table model with the new data
	        tableModel = new ProjectTableModel(tableModel.getList(), tableModel.getEntityManager());
	        tableModel.addTableModelListener(this);
	        // update the JTable with the data
	    	gui.updateTable();
	        // read the data in each column using getValueAt and display it on corresponding textfield
			//gui.setProjectIDTextField( (String) tableModel.getValueAt(firstIndex, 0));
                        gui.setProjectNameTextField( (String) tableModel.getValueAt(firstIndex, 1));
			gui.setProjectDescTextField( (String) tableModel.getValueAt(firstIndex, 2));
			gui.setStartDateTextField( (String) tableModel.getValueAt(firstIndex, 3));
			gui.setEndDateTextField( (String) tableModel.getValueAt(firstIndex, 4));
			gui.setStatusTextField( (String) tableModel.getValueAt(firstIndex, 5));
                        gui.setOutcomeTextField( (String) tableModel.getValueAt(firstIndex, 6));
	} catch(Exception exp) {
		exp.getMessage();
		exp.printStackTrace();
	}
}


	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel selectModel = (ListSelectionModel) e.getSource();
		selectedIndex = selectModel.getMinSelectionIndex();
		
		// read the data in each column using getValueAt and display it on corresponding textfield
		//gui.setProjectIDTextField( (String) tableModel.getValueAt(selectedIndex, 0));
                gui.setProjectNameTextField( (String) tableModel.getValueAt(selectedIndex, 1));
		gui.setProjectDescTextField( (String) tableModel.getValueAt(selectedIndex, 2));
		gui.setStartDateTextField( (String) tableModel.getValueAt(selectedIndex, 3));
		gui.setEndDateTextField( (String) tableModel.getValueAt(selectedIndex, 4));
		gui.setStatusTextField( (String) tableModel.getValueAt(selectedIndex, 5));
                gui.setOutcomeTextField( (String) tableModel.getValueAt(selectedIndex, 6));
	}
	
	public void addRow(String[] array) {
		tableModel.addRow(array,selectedIndex);			
	}
	
	public void deleteRow() {
		tableModel.deleteRow(selectedIndex);			
	}
        public void updateRow(String[] array) {
		tableModel.updateRow(array,selectedIndex);			
	}
}



