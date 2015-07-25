/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectdemo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author AshitaRaghu
 */


import javax.persistence.*;


import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class TaskService {
private EntityManager manager;
private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";
private static EntityManagerFactory factory;
        
public TaskService(){
    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    manager = factory.createEntityManager();
}

public TaskService(EntityManager manager) {
this.manager = manager;
}
 // method to create a new record
 public Task createTask(String teamMember, String taskName, String
taskDesc, String startDate, String endDate, String dependency) {
 Task task = new Task();

// task.setTaskID(taskID);
 
 task.setTaskName(taskName);
 task.setTaskDesc(taskDesc);
 task.setStartDate(startDate);
 task.setEndDate(endDate);
 task.setDependency(dependency);

 
 manager.persist(task);
 return task;
 }
 
 // method to read a record
 public Task readTask(String taskID) {
	 Task project = manager.find(Task.class, 
			 taskID);
 return project; 
 }
 // method to read all records
 public List<Task> readTaskAll() {
 TypedQuery<Task> query = manager.createQuery("SELECT e FROM task e", Task.class);
  

 List<Task> result = query.getResultList();
  

 return result;
 
 }
 public Object[] readUserAll() {
 
  TypedQuery<UserList> query1 = manager.createQuery("SELECT e FROM useraccount e", UserList.class);


  List<UserList> result1 = query1.getResultList();
  
  Object[] userArray = result1.toArray();

 return userArray;
 
 }
 public Object[] readProjectAll() {
 
  TypedQuery<Project> query2 = manager.createQuery("SELECT e FROM project e", Project.class);


  List<Project> result2 = query2.getResultList();
  
  Object[] userArray = result2.toArray();

 return userArray;
 
 }
 
 public List<Task> getTaskList(String userName) {
	
	 //System.out.println("user is: " + userName);
	// String name = "soso";
	 TypedQuery<Task> query = manager.createQuery("SELECT e FROM task e WHERE e.user_name = :cname", Task.class);
	 query.setParameter("cname", userName);

	 List<Task> result = query.getResultList();
	// System.out.println(result.toString());

	 return result;
}
 
 // method to update a record
 public Task updateTask(int index,String teamMember,String taskName, String
taskDesc, String startDate, String endDate, String dependency) {
     List<Task> resultList = readTaskAll();
        
     int taskID=0;
    try {
        taskID = Integer.parseInt(resultList.get(index).getColumnData(0));
    } catch (Exception ex) {
        Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
    }
     Task task = manager.find(Task.class, taskID);
	 
 if (task != null) {
        //task.setTeamMember(teamMember); 
     task.setTaskID(taskID);
        task.setTaskName(taskName);
	 task.setTaskDesc(taskDesc);
	 task.setStartDate(startDate);
	 task.setEndDate(endDate);
	 task.setDependency(dependency);
         
 }
 return task;
 }
 // method to delete a record
public void deleteTask(int index) {
    List<Task> resultList = readTaskAll();
        
     int taskID=0;
    try {
        taskID = Integer.parseInt(resultList.get(index).getColumnData(0));
    } catch (Exception ex) {
        Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
    }
     Task task = manager.find(Task.class, taskID);
	 
 if (task != null) {
 manager.remove(task);
 }
 }
 public static void main(String[] args){
	 String PERSISTENCE_UNIT_NAME = "PersistenceUnit";
	 EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	 EntityManager manager = factory.createEntityManager();
	 TaskService taskService = new TaskService(manager);
	 EntityTransaction transaction = manager.getTransaction();
	// start transaction
	   	transaction.begin();   
	   	// create a new course
	   	//Task newCourse = taskService.createTask("1","XXX", "YYY", "10-5-2014", "12-4-2014","complete","a");	
	   	// read the course with the given course number
	   	Task course = taskService.readTask("YYY");
		System.out.println(course);		
		// update the course with the given course number
		//Task course1 = taskService.updateTask("2","ZZZ", "YYY",  "1-5-2014", "1-4-2014","Incomplete","b");
		//System.out.println(course1);		
		// delete a course
		//courseListService.deleteCourse("YYY");	
		// commit a transaction
		  transaction.commit();
                  

 }
}




