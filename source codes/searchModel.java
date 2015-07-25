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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.table.AbstractTableModel;

public class searchModel {
	
	private EntityManager manager;
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  
	private static EntityManagerFactory factory;
	
	public searchModel() {
		
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    manager = factory.createEntityManager();
	}
	
	public EntityManager getEntityManager() {
		return manager;
	}
}	
