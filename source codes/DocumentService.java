package projectdemo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DocumentService {
	
	Project project;
	Document document;
        private EntityManager manager;
	private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  
	private static EntityManagerFactory factory;
	
public DocumentService(/*DocumentModel model*/) {
		
		//this.model = model;
               factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	        manager = factory.createEntityManager();
		project = new Project();
                
	}

public List<Document> readAll() {
	 
	 //manager = model.getEntityManager();
	 
	 TypedQuery<Document> query = (TypedQuery<Document>) manager.createQuery("SELECT e FROM document e",Document.class);
	 List<Document> result =  query.getResultList();

	 return result;
}

public void addDocument(Object projectName, String documentName, String documentPath) {
	
	//manager = model.getEntityManager(); 
	EntityTransaction userTransaction = manager.getTransaction();  
	
	//int userId = ((Integer)userId).intValue();
	//int projectId = ((Integer)projectId).intValue();
	document = new Document();
	
	document.setProjectName(projectName.toString());
	document.setDocumentName(documentName);
	document.setDocumentPath(documentPath);
	
	userTransaction.begin();
   	manager.persist(document);
   	userTransaction.commit();  	    	 
}

public void deleteDocument(int documentId) {
	
	//manager = model.getEntityManager(); 
	EntityTransaction userTransaction = manager.getTransaction();  
	
	Document document = manager.find(Document.class, documentId);
	
	userTransaction.begin();
    manager.remove(document);
	userTransaction.commit();
}
}
