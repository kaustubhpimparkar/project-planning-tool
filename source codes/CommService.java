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
public class CommService {
private EntityManager manager;

public CommService(){}

public CommService(EntityManager manager) {
this.manager = manager;
}
 // method to create a new record
 public Communication createComm(String communicator, String
partner, String date, String time, String message) {
 Communication comm = new Communication();

// task.setTaskID(taskID);
 
 comm.setCommunicator(communicator);
 comm.setPartner(partner);
 comm.setDate(date);
 comm.setTime(time);
 comm.setMessage(message);
 
 manager.persist(comm);
 return comm;
 }
 
 // method to read a record
 public Communication readComm(String communicationID) {
	 Communication project = manager.find(Communication.class, 
			 communicationID);
 return project; 
 }
 // method to read all records
 public List<Communication> readCommAll() {
 TypedQuery<Communication> query = manager.createQuery("SELECT e FROM communication e", Communication.class);
  

 List<Communication> result = query.getResultList();
  

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
 
 // method to update a record
 
 // method to delete a record
public void deleteComm(int index) {
    List<Communication> resultList = readCommAll();
        
     int commID=0;
    try {
        commID = Integer.parseInt(resultList.get(index).getColumnData(0));
    } catch (Exception ex) {
        Logger.getLogger(CommService.class.getName()).log(Level.SEVERE, null, ex);
    }
     Communication task = manager.find(Communication.class, commID);
	 
 if (task != null) {
 manager.remove(task);
 }
 }
 public static void main(String[] args){
	 String PERSISTENCE_UNIT_NAME = "PersistenceUnit";
	 EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	 EntityManager manager = factory.createEntityManager();
	 CommService commService = new CommService(manager);
	 EntityTransaction transaction = manager.getTransaction();
	// start transaction
	   	transaction.begin();   
	   	// create a new course
	   	//Task newCourse = taskService.createTask("1","XXX", "YYY", "10-5-2014", "12-4-2014","complete","a");	
	   	// read the course with the given course number
	   	//Communication course = CommService.readComm("YYY");
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




