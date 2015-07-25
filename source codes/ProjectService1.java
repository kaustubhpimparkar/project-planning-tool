/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectdemo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author AshitaRaghu
 */
public class ProjectService1 {
    
	
	Project project;
	EntityManager manager;
	List<Project> projectList;
	private static EntityManagerFactory factory;
        private static final String PERSISTENCE_UNIT_NAME ="PersistenceUnit";
                
public ProjectService1(/*ParticipantAndUserModel model*/) {
		
		//this.model = model;
                factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
                manager = factory.createEntityManager();
		project = new Project();
	}

	public List<Project> readAll() {
	    
	  //  manager = model.getEntityManager();	
	   	TypedQuery<Project> query = manager.createQuery("SELECT e FROM project e", Project.class);
	   	projectList =  query.getResultList();
	   	
	   	return projectList;
	   	}

    
}
