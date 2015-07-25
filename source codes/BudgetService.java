/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectdemo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author team5
 */
public class BudgetService {
    private EntityManager manager;

    public BudgetService(){}

    public BudgetService(EntityManager manager) {
        this.manager = manager;
    }
    
    public Budget createBudget(String serviceName, String
        serviceDesc, String estimatedCost, String actualCost) {
         Budget budget = new Budget();

// task.setTaskID(taskID);
 
        budget.setServiceName(serviceName);
        budget.setServiceDesc(serviceDesc);
        budget.setEstimatedCost(Integer.parseInt(estimatedCost));
        budget.setActualCost(Integer.parseInt(actualCost));
        
 
 manager.persist(budget);
 return budget;
 }
    public Budget readBudget(int budgetID) {
	 Budget budget = manager.find(Budget.class, 
			 budgetID);
 return budget; 
 }
 // method to read all records
 public List<Budget> readBudgetAll() {
 TypedQuery<Budget> query = manager.createQuery("SELECT e FROM budget e", Budget.class);
  

 List<Budget> result = query.getResultList();
  

 return result;
 
 }
 
 public Object[] readProjectAll() {
 
  TypedQuery<Project> query2 = manager.createQuery("SELECT e FROM project e", Project.class);


  List<Project> result2 = query2.getResultList();
  
  Object[] userArray = result2.toArray();

 return userArray;
 
 }
 
 // method to update a record
 public Budget updateBudget(int index,String serviceName, String
serviceDesc, String estimatedCost, String actualCost) {
     List<Budget> resultList = readBudgetAll();
        
     int budgetID=0;
    try {
        budgetID = Integer.parseInt(resultList.get(index).getColumnData(0));
    } catch (Exception ex) {
        Logger.getLogger(BudgetService.class.getName()).log(Level.SEVERE, null, ex);
    }
     Budget task = manager.find(Budget.class, budgetID);
	 
 if (task != null) {
        //task.setTeamMember(teamMember); 
     task.setBudgetID(budgetID);
        task.setServiceName(serviceName);
	 task.setServiceDesc(serviceDesc);
	 task.setEstimatedCost(Integer.parseInt(estimatedCost));
	 task.setActualCost(Integer.parseInt(actualCost));
	 
         
 }
 return task;
 }
 // method to delete a record
public void deleteBudget(int index) {
    List<Budget> resultList = readBudgetAll();
        
     int budgetID=0;
    try {
        budgetID = Integer.parseInt(resultList.get(index).getColumnData(0));
    } catch (Exception ex) {
        Logger.getLogger(BudgetService.class.getName()).log(Level.SEVERE, null, ex);
    }
     Budget budget = manager.find(Budget.class, budgetID);
	 
 if (budget != null) {
 manager.remove(budget);
 }
 }
 public static void main(String[] args){
	 String PERSISTENCE_UNIT_NAME = "PersistenceUnit";
	 EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	 EntityManager manager = factory.createEntityManager();
	 BudgetService budgetService = new BudgetService(manager);
	 EntityTransaction transaction = manager.getTransaction();
	// start transaction
	   	transaction.begin();   
	   	// create a new course
	   	//Task newCourse = taskService.createTask("1","XXX", "YYY", "10-5-2014", "12-4-2014","complete","a");	
	   	// read the course with the given course number
	   	//Budget course = budgetService.readBudget("YYY");
		//System.out.println(course);		
		// update the course with the given course number
		//Task course1 = taskService.updateTask("2","ZZZ", "YYY",  "1-5-2014", "1-4-2014","Incomplete","b");
		//System.out.println(course1);		
		// delete a course
		//courseListService.deleteCourse("YYY");	
		// commit a transaction
		  transaction.commit();
                  

 }
    
}
