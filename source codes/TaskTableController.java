/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectdemo;

/**
 *
 * @author AshitaRaghu
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */






import javax.persistence.EntityManager;
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
public class TaskTableController implements ListSelectionListener, TableModelListener{
	private TaskTableModel tableModel;
	private WelcomeGUI gui;
	public int selectedIndex;
        Object[] userResultList;
        Object[] projectResultList;

        private EntityManager manager;
        private TaskService taskService;
	
	public TaskTableController(WelcomeGUI gui) {
		this.gui = gui;   
         // create the tableModel using the data in the cachedRowSet
    		tableModel = new TaskTableModel(); 
                manager = tableModel.getEntityManager();
                taskService = new TaskService(manager);
                userResultList = taskService.readUserAll();
                projectResultList = taskService.readProjectAll();

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
	        tableModel = new TaskTableModel(tableModel.getList(), tableModel.getEntityManager());
	        tableModel.addTableModelListener(this);
	        // update the JTable with the data
	    	gui.updateTaskTable();
	        // read the data in each column using getValueAt and display it on corresponding textfield
			//gui.setProjectCombo( (String) tableModel.getValueAt(firstIndex, 0));
                        gui.setTaskTeamMemberTextField( (String) tableModel.getValueAt(firstIndex, 1));
                        gui.setTaskNameTextField( (String) tableModel.getValueAt(firstIndex, 2));
			gui.setTasktDescTextField( (String) tableModel.getValueAt(firstIndex, 3));
			gui.setStartDateTextField1( (String) tableModel.getValueAt(firstIndex, 4));
			gui.setEndDateTextField1( (String) tableModel.getValueAt(firstIndex, 5));
			gui.setDependencyTextField( (String) tableModel.getValueAt(firstIndex,6));
            
	} catch(Exception exp) {
		exp.getMessage();
		exp.printStackTrace();
	}
}


	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel selectModel = (ListSelectionModel) e.getSource();
		selectedIndex = selectModel.getMinSelectionIndex();
		
		// read the data in each column using getValueAt and display it on corresponding textfield
		/*gui.setProjectCombo( (String) tableModel.getValueAt(selectedIndex, 0));*/
                gui.setTaskTeamMemberTextField( (String) tableModel.getValueAt(selectedIndex, 1));
                gui.setTaskNameTextField( (String) tableModel.getValueAt(selectedIndex, 2));
		gui.setTasktDescTextField( (String) tableModel.getValueAt(selectedIndex, 3));
		gui.setStartDateTextField1( (String) tableModel.getValueAt(selectedIndex, 4));
		gui.setEndDateTextField1( (String) tableModel.getValueAt(selectedIndex, 5));
		gui.setDependencyTextField( (String) tableModel.getValueAt(selectedIndex,6));
                
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
        public Object[] getUserList(){
             return userResultList;
         }
        public Object[] getProjectList(){
             return projectResultList;
         }
}




