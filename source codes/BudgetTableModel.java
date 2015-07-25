/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectdemo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author AshitaRaghu
 */
public class BudgetTableModel extends AbstractTableModel{
    List<Budget> budgetResultList;   // stores the model data in a List collection of type CourseList
          
	  private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  // Used in persistence.xml
	  private static EntityManagerFactory factory;  // JPA  
	  private EntityManager manager;				// JPA 
	  private Budget budget;			    // represents the entity project
	  private BudgetService budgetService;
          
	
	   // This field contains additional information about the results   
	    int numcols, numrows;           // number of rows and columns
            
            BudgetTableModel() {
	    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    manager = factory.createEntityManager();
	    budget = new Budget();
	    budgetService = new BudgetService(manager);
	    
	    // read all the records from courselist
	    budgetResultList =budgetService.readBudgetAll();
            
            
	    
	    // update the number of rows and columns in the model
	    numrows = budgetResultList.size();
	    
	    numcols = budget.getNumberOfColumns();
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
		  return budgetResultList.get(row).getColumnData(col);
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
				return budget.getColumnName(col);
			} catch (Exception err) {
	             return err.toString();
	       }             
	 }
	
	 // update the data in the given row and column to aValue
	 public void setValueAt(Object aValue, int row, int col) {
		//data[rowIndex][columnIndex] = (String) aValue;
		try {
		   Budget element = budgetResultList.get(row);
                   element.setColumnData(col, aValue); 
            fireTableCellUpdated(row, col);
		} catch(Exception err) {
			err.toString();
		}	
	 }
	
	 public List<Budget> getList() {
		 return budgetResultList;
	 }

	 

	 // create a new table model using the existing data in list
	 public BudgetTableModel(List<Budget> list, EntityManager em)  {
		budgetResultList = list;
	    numrows = budgetResultList.size();
	    budget = new Budget();
	   	numcols = budget.getNumberOfColumns();     
		manager = em;  
		budgetService = new BudgetService(manager);
	 }
         public void addRow(Object[] array,int index) {
		//data[rowIndex][columnIndex] = (String) aValue;
			
	    // add row to database
		EntityTransaction userTransaction = manager.getTransaction();  
		userTransaction.begin();
		Budget newRecord = budgetService.createBudget((String) array[0], (String) array[1], (String) array[2], (String) array[3]);
		userTransaction.commit();
		 
		// set the current row to rowIndex
		budgetResultList.add(newRecord);
        int row = budgetResultList.size();  
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
	    budgetService.deleteBudget(index);
		userTransaction.commit();
		// set the current row to rowIndex
		budgetResultList.remove(index);		
		fireTableRowsDeleted(numrows, numcols);

	}
         public void updateRow(Object[] array, int index) {
		//data[rowIndex][columnIndex] = (String) aValue;
			
	    // add row to database
		EntityTransaction userTransaction = manager.getTransaction();  
		userTransaction.begin();
                System.out.println("do update"+index);
		Budget newRecord = budgetService.updateBudget(index,(String) array[0], (String) array[1], (String) array[2], (String) array[3]);
		userTransaction.commit();
		 
        int row;  
        System.out.println("get value of index"+index);
              newRecord= budgetResultList.set(index, newRecord); 
             
        int col = 1;

        // update the data in the model to the entries in array
         for (Object data : array) {
             System.out.println("get value of index"+index+" "+col);
          	 setValueAt((String) data, index, col++);
         }
         
         numrows++;
	}
         
	 
    
}
