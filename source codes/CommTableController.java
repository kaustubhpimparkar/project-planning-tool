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
public class CommTableController implements ListSelectionListener, TableModelListener{
	private CommTableModel tableModel;
	private WelcomeGUI gui;
	public int selectedIndex;
        Object[] userResultList;
        Object[] projectResultList;

        private EntityManager manager;
        private CommService taskService;
	
	public CommTableController(WelcomeGUI gui) {
		this.gui = gui;   
         // create the tableModel using the data in the cachedRowSet
    		tableModel = new CommTableModel(); 
                manager = tableModel.getEntityManager();
                taskService = new CommService(manager);
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
	        tableModel = new CommTableModel(tableModel.getList(), tableModel.getEntityManager());
	        tableModel.addTableModelListener(this);
	        // update the JTable with the data
	    	gui.updateCommTable();
	        // read the data in each column using getValueAt and display it on corresponding textfield
			//gui.setProjectCombo( (String) tableModel.getValueAt(firstIndex, 0));
                        gui.setTeamMemberCommTextField( (String) tableModel.getValueAt(firstIndex, 1));
                        gui.setPartnerTextField( (String) tableModel.getValueAt(firstIndex, 2));
			gui.setMessageTextField( (String) tableModel.getValueAt(firstIndex, 5));
			//gui.setStartDateTextField( (String) tableModel.getValueAt(firstIndex, 3));
			//gui.setEndDateTextField( (String) tableModel.getValueAt(firstIndex, 4));
			//gui.setDependencyTextField( (String) tableModel.getValueAt(firstIndex,5));
            
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
                gui.setTeamMemberCommTextField( (String) tableModel.getValueAt(selectedIndex, 1));
                gui.setPartnerTextField( (String) tableModel.getValueAt(selectedIndex, 2));
		gui.setMessageTextField( (String) tableModel.getValueAt(selectedIndex, 5));
		//gui.setStartDateTextField( (String) tableModel.getValueAt(selectedIndex, 3));
		//gui.setEndDateTextField( (String) tableModel.getValueAt(selectedIndex, 4));
		//gui.setDependencyTextField( (String) tableModel.getValueAt(selectedIndex,5));
                
	}
	
	public void addRow(String[] array) {
		tableModel.addRow(array,selectedIndex);			
	}
	
	public void deleteRow() {
		tableModel.deleteRow(selectedIndex);			
	}
        
        public Object[] getUserList(){
             return userResultList;
         }
        public Object[] getProjectList(){
             return projectResultList;
         }
}





