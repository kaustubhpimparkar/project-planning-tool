/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectdemo;

import javax.persistence.*;

import java.util.*;

public class User_project_assignmentService {
	 
	 //List<User_project_assignment> assignmentList;
	 private ParticipantTableController controller;
	 EntityManager manager;
         private static final String PERSISTENCE_UNIT_NAME ="PersistenceUnit";
	 private static int user_project_assign_id = 1;
	 User_project_assignment assignmentEntry;
	 ParticipantAndUserModel model;
	 Object[] assignmentArray;
         private static EntityManagerFactory factory;
	 
	 public User_project_assignmentService(/*ParticipantAndUserModel model*/) {
					
		//this.model = model;
		assignmentEntry = new User_project_assignment();			
	}
	 
	public List<User_project_assignment> readAll() {
		 
		 //manager = model.getEntityManager();
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            manager = factory.createEntityManager();
		 
		 TypedQuery<User_project_assignment> query = (TypedQuery<User_project_assignment>) manager.createQuery("SELECT e FROM user_project_assignments e",User_project_assignment.class);
    	 List<User_project_assignment> result =  query.getResultList();
    
    	 return result;
	 }
        
        public List<User_project_assignment> getUserAndProjectStatus(int userId) {
    	 
    	 TypedQuery<User_project_assignment> query = (TypedQuery<User_project_assignment>) manager.createQuery("SELECT upa FROM user_project_assignment upa WHERE upa.project_id = :cid",User_project_assignment.class);
    	 query.setParameter("cid", userId);
    	 
    	 List<User_project_assignment> result =  query.getResultList();
    	 
    	 return result;
     }
	 
    // method to create a new record
     public void createAssignment(Object userId, Object projectId) {
    	//manager = model.getEntityManager(); 
    	EntityTransaction userTransaction = manager.getTransaction();  
    	
    	//int userId = ((Integer)userId).intValue();
    	//int projectId = ((Integer)projectId).intValue();
    	assignmentEntry = new User_project_assignment();
		
    	assignmentEntry.setUserId(Integer.valueOf((String) userId));
    	assignmentEntry.setProjectId(Integer.valueOf((String) projectId));
    	
		userTransaction.begin();
	   	manager.persist(assignmentEntry);
	   	userTransaction.commit();  	    	 
     }

     public void getContoller(ParticipantTableController controller) {
    	 
    	 this.controller = controller;
     }
     
    // method to delete a record
    public void deleteAssignment(Object userId, Object projectId) {
    	
    	//manager = model.getEntityManager(); 
    	EntityTransaction userTransaction = manager.getTransaction();  
    	
    	assignmentEntry = new User_project_assignment();
		
    	//System.out.println(((Integer)userId).intValue() + " " + ((Integer)projectId).intValue());
    	//System.out.println(((Integer)projectId).intValue());
    	//assignmentEntry.setProjectId(((Integer)projectId).intValue());
    	
		userTransaction.begin();
		
		User_project_assignmentPK pk = new User_project_assignmentPK();
		
		//System.out.println(((Integer)userId).intValue() + " " + ((Integer)projectId).intValue());
		
		pk.setProjectId(((Integer)projectId).intValue());
		pk.setUserId(((Integer)userId).intValue());
		
		System.out.println( pk.getUserId()+" " +pk.getProjectId());
		
		User_project_assignment assignmentEntry = manager.find(User_project_assignment.class, pk);
	    manager.remove(assignmentEntry);
		//Query query = em.createQuery("Delete from User_project_assignment u where u.col1 = x and r.col2 = y and r.col3 = z");  
		//query.executeUpdate();
	   	userTransaction.commit();
	   	
   /* 	List<User_project_assignment> assignList = readAll();
    	
    	boolean flag = false;
    	for(int i=0;i<participantList.length;i++) {
    		
    		for(int j=0; j<assignList.size();j++) {
    			
    			try {
					if(participantList[i].equals( assignList.get(j).getColumnData(1) ) ) {
						
						int user_assign_id = Integer.parseInt(assignList.get(j).getColumnData(0).toString());
						
						manager.getTransaction().begin();
						User_project_assignment assignmentEntry = manager.find(User_project_assignment.class, user_assign_id);  			
		   	 			manager.remove(assignmentEntry);
		   	 			manager.getTransaction().commit(); 
		   	 			
		   	 			break;
					}
				} catch (Exception e) {
					
					e.printStackTrace();
				}
    		}
    	}   	*/
    } // deleteAssignment() end
}
