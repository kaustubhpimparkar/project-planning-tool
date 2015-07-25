/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectdemo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author AshitaRaghu
 */
public class ParticipantAndUserModel {
    
	
	private EntityManager manager;
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  
	private static EntityManagerFactory factory;
	
	//List<User_project_assignment> assignmentList;
	//private User_project_assignmentService upaService;
	
	public ParticipantAndUserModel() {
		
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    manager = factory.createEntityManager();
	}
	
	public EntityManager getEntityManager() {
		return manager;
	}

    
}
