/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectdemo;

import javax.persistence.EntityManager;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author team5
 */
public class BudgetTableController implements ListSelectionListener, TableModelListener{
    private BudgetTableModel tableModel;
	private WelcomeGUI gui;
	public int selectedIndex;
        Object[] projectResultList;

        private EntityManager manager;
        private BudgetService budgetService;
        
        public BudgetTableController(WelcomeGUI gui) {
		this.gui = gui;   
         // create the tableModel using the data in the cachedRowSet
    		tableModel = new BudgetTableModel(); 
                manager = tableModel.getEntityManager();
                budgetService = new BudgetService(manager);
                projectResultList = budgetService.readProjectAll();

    		tableModel.addTableModelListener(this);
	}
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
	        tableModel = new BudgetTableModel(tableModel.getList(), tableModel.getEntityManager());
	        tableModel.addTableModelListener(this);
	        // update the JTable with the data
	    	gui.updateBudgetTable();
	        // read the data in each column using getValueAt and display it on corresponding textfield
			//gui.setProjectCombo( (String) tableModel.getValueAt(firstIndex, 0));
                        //gui.setTeamMemberCombo( (String) tableModel.getValueAt(firstIndex, 1));
                        gui.setServiceNameTextField( (String) tableModel.getValueAt(firstIndex, 1));
			gui.setServiceDescTextField( (String) tableModel.getValueAt(firstIndex, 2));
			gui.setEstimatedCostTextField( (String) tableModel.getValueAt(firstIndex, 3));
			gui.setActualCostTextField( (String) tableModel.getValueAt(firstIndex, 4));
			
            
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
               // gui.setTeamMemberCombo( (String) tableModel.getValueAt(selectedIndex, 1));
                gui.setServiceNameTextField( (String) tableModel.getValueAt(selectedIndex, 1));
		gui.setServiceDescTextField( (String) tableModel.getValueAt(selectedIndex, 2));
		gui.setEstimatedCostTextField( (String) tableModel.getValueAt(selectedIndex, 3));
		gui.setActualCostTextField( (String) tableModel.getValueAt(selectedIndex, 4));
		
                
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
      
        public Object[] getProjectList(){
             return projectResultList;
         }
}

	
    

