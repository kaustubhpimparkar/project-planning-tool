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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Sowmya
 */
public class UserListTableController implements ListSelectionListener, TableModelListener {
    private UserListTableModel tableModel;
	private WelcomeGUI gui;
        private WelcomeAdminGUI gui_admin;
        private Login gui1;
	public int selectedIndex;
        
	public UserListTableController(WelcomeGUI gui) {
		this.gui = gui;   
         // create the tableModel using the data in the cachedRowSet
    		tableModel = new UserListTableModel(); 
    		tableModel.addTableModelListener(this);
	}
        public UserListTableController(WelcomeAdminGUI gui) {
		this.gui_admin = gui_admin;   
         // create the tableModel using the data in the cachedRowSet
    		tableModel = new UserListTableModel(); 
    		tableModel.addTableModelListener(this);
	}
        
        public UserListTableController(Login gui) {
		this.gui1 = gui;   
         // create the tableModel using the data in the cachedRowSet
    		tableModel = new UserListTableModel(); 
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
	        tableModel = new UserListTableModel(tableModel.getList(), tableModel.getEntityManager());
	        tableModel.addTableModelListener(this);
	        // update the JTable with the data
	    	gui.updateUserTable();
	    
	        // read the data in each column using getValueAt and display it on corresponding textfield
			gui.setFirstNameTextField( (String) tableModel.getValueAt(firstIndex, 1));
			gui.setLastNameTextField( (String) tableModel.getValueAt(firstIndex, 2));
			gui.setEmailTextField((String) tableModel.getValueAt(firstIndex, 3));
			gui.setPhoneNumberTextField((String) tableModel.getValueAt(firstIndex, 4));
                        gui.setUserNameTextField((String) tableModel.getValueAt(firstIndex, 5));
                        gui.setPasswordTextField((String) tableModel.getValueAt(firstIndex, 6));
                        gui.setRoleCombo((String) tableModel.getValueAt(firstIndex, 7));
	} catch(Exception exp) {
		exp.getMessage();
		exp.printStackTrace();
	}
}


	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel selectModel = (ListSelectionModel) e.getSource();
		int firstIndex = selectModel.getMinSelectionIndex();
                selectedIndex = selectModel.getMinSelectionIndex();
		
		// read the data in each column using getValueAt and display it on corresponding textfield
		        gui.setFirstNameTextField( (String) tableModel.getValueAt(firstIndex, 1));
			gui.setLastNameTextField( (String) tableModel.getValueAt(firstIndex, 2));
			gui.setEmailTextField((String) tableModel.getValueAt(firstIndex, 3));
			gui.setPhoneNumberTextField((String) tableModel.getValueAt(firstIndex, 4));
                        gui.setUserNameTextField((String) tableModel.getValueAt(firstIndex, 5));
                        gui.setPasswordTextField((String) tableModel.getValueAt(firstIndex, 6));
                        gui.setRoleCombo((String) tableModel.getValueAt(firstIndex, 7));
	}
	
	public void addRow(String[] array) {
		tableModel.addRow(array);			
	}
	
	public void deleteRow() {
		tableModel.deleteRow(selectedIndex);			
	}
        public void updateRow(String[] array) {
		tableModel.updateRow(array,selectedIndex);			
	}
        
        public int loginUser(String userName,String password)
        {
            int check_login = tableModel.loginUser(userName,password);
            return check_login;
        }
	

    
}
