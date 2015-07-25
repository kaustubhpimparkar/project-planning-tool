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



//import communication.Communication;
import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.*;
import javax.persistence.*;
import java.util.*;



public class CommTableModel extends AbstractTableModel {

	  List<Communication> commResultList;   // stores the model data in a List collection of type CourseList
          
	  private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  // Used in persistence.xml
	  private static EntityManagerFactory factory;  // JPA  
	  private EntityManager manager;				// JPA 
	  private Communication comm;			    // represents the entity project
	  private CommService commService;
          
	
	   // This field contains additional information about the results   
	    int numcols, numrows;           // number of rows and columns

	 CommTableModel() {
	    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    manager = factory.createEntityManager();
	    comm = new Communication();
	    commService = new CommService(manager);
	    
	    // read all the records from courselist
	    commResultList =commService.readCommAll();
            
            
	    
	    // update the number of rows and columns in the model
	    numrows = commResultList.size();
	    
	    numcols = comm.getNumberOfColumns();
      }
         public EntityManager getEntityManager(){
             return manager;
         }

	 // returns a count of the number of rows
	 public int getRowCount() {
		return numrows;
	 }
	
	 // returns a count of the number of columns
	 public int getColumnCount() {
		return numcols;
	 }
	
	 // returns the data at the given row and column number
	 public Object getValueAt(int row, int col) {
		try {
		  return commResultList.get(row).getColumnData(col);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	 }
	
	 // table cells are not editable
	 public boolean isCellEditable(int rowIndex, int colIndex) {
		return false;
	 }
	
	 public Class<?> getColumnClass(int col) {
		return getValueAt(0, col).getClass();
	 }
	
	 // returns the name of the column
	 public String getColumnName(int col) {
		   try {
				return comm.getColumnName(col);
			} catch (Exception err) {
	             return err.toString();
	       }             
	 }
	
	 // update the data in the given row and column to aValue
	 public void setValueAt(Object aValue, int row, int col) {
		//data[rowIndex][columnIndex] = (String) aValue;
		try {
		   Communication element = commResultList.get(row);
                   element.setColumnData(col, aValue); 
            fireTableCellUpdated(row, col);
		} catch(Exception err) {
			err.toString();
		}	
	 }
	
	 public List<Communication> getList() {
		 return commResultList;
	 }

	 

	 // create a new table model using the existing data in list
	 public CommTableModel(List<Communication> list, EntityManager em)  {
		commResultList = list;
	    numrows = commResultList.size();
	    comm = new Communication();
	   	numcols = comm.getNumberOfColumns();     
		manager = em;  
		commService = new CommService(manager);
	 }
	 
	 // In this method, a newly inserted row in the GUI is added to the table model as well as the database table
	 // The argument to this method is an array containing the data in the textfields of the new row.
	 public void addRow(Object[] array,int index) {
		//data[rowIndex][columnIndex] = (String) aValue;
			
	    // add row to database
		EntityTransaction userTransaction = manager.getTransaction();  
		userTransaction.begin();
		Communication newRecord = commService.createComm((String) array[0], (String) array[1], (String) array[2], (String) array[3], (String) array[4]);
		userTransaction.commit();
		 
		// set the current row to rowIndex
		commResultList.add(newRecord);
        int row = commResultList.size();  
        int col = 1;

        // update the data in the model to the entries in array
         for (Object data : array) {
          	 setValueAt((String) data, row-1, col++);
         }
         
         numrows++;
	}	
	 
	 // In this method, a newly inserted row in the GUI is added to the table model as well as the database table
	 // The argument to this method is an array containing the data in the textfields of the new row.
	 public void deleteRow(int index) {
		EntityTransaction userTransaction = manager.getTransaction();  
		userTransaction.begin();
	    commService.deleteComm(index);
		userTransaction.commit();
		// set the current row to rowIndex
		commResultList.remove(index);		
		fireTableRowsDeleted(numrows, numcols);

	}
         
         
}





