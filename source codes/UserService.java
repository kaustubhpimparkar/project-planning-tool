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
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class UserService {
	
	UserList user;
	ParticipantAndUserModel model;
	EntityManager manager;
	List<UserList> userList;
        private static EntityManagerFactory factory;
        private static final String PERSISTENCE_UNIT_NAME ="PersistenceUnit";
	
	public UserService(/*ParticipantAndUserModel model*/) {
		
		//this.model = model;
                factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
                manager = factory.createEntityManager();
		user = new UserList();
	}
	
    // method to read all records
    public List<UserList> readAll() {
    
   // manager = model.getEntityManager();	
   	TypedQuery<UserList> query = manager.createQuery("SELECT e FROM useraccount e", UserList.class);
   	userList =  query.getResultList();
   	
   	return userList;
   	}
    
    public List<UserList> getUserList() {
    	
    	return userList;
    }
    
}
