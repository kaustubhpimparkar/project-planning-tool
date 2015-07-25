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


import javax.persistence.*;


import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectService {
private EntityManager manager;


public ProjectService(EntityManager manager) {
this.manager = manager;

}
 // method to create a new record
 public Project createProject(String projectName, String
projectDesc, String startDate, String endDate, String status, String outcome) {
 Project project = new Project();
 //project.setProjectID(projectID);
 project.setProjectName(projectName);
 project.setProjectDesc(projectDesc);
 project.setStartDate(startDate);
 project.setEndDate(endDate);
 project.setStatus(status);
 project.setOutcome(outcome);

    
 manager.merge(project);
 
 return project;
 }
 
 // method to read a record
 public Project readProject(String projectID) {
	 Project project = manager.find(Project.class, 
			 projectID);
 return project; 
 }
 // method to read all records
 public List<Project> readAll() {
 TypedQuery<Project> query = manager.createQuery("SELECT e FROM project e", Project.class);
 List<Project> result = query.getResultList();
 return result; 
 }
 
 // method to update a record
 public Project updateProject(int index,String projectName, String
projectDesc, String startDate, String endDate, String status, String outcome)  {
	List<Project> resultList = readAll();
        
     int projectID=0;
    try {
        projectID = Integer.parseInt(resultList.get(index).getColumnData(0));
    } catch (Exception ex) {
        Logger.getLogger(ProjectService.class.getName()).log(Level.SEVERE, null, ex);
    }
     Project project = manager.find(Project.class, projectID);
 if (project != null) {
              project.setProjectID(projectID);

         project.setProjectName(projectName);
	 project.setProjectDesc(projectDesc);
	 project.setStartDate(startDate);
	 project.setEndDate(endDate);
	 project.setStatus(status);
         project.setOutcome(outcome);
 }
 return project;
 }
 // method to delete a record
 public void deleteProject(int index) {
     List<Project> resultList = readAll();
        
     int projectID=0;
    try {
        projectID = Integer.parseInt(resultList.get(index).getColumnData(0));
    } catch (Exception ex) {
        Logger.getLogger(ProjectService.class.getName()).log(Level.SEVERE, null, ex);
    }
     Project project = manager.find(Project.class, projectID);
 if (project != null) {
 manager.remove(project);
 }
 }
 public static void main(String[] args){
	 String PERSISTENCE_UNIT_NAME = "PersistenceUnit";
	 EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	 EntityManager manager = factory.createEntityManager();
	 ProjectService projectService = new ProjectService(manager);
	 EntityTransaction transaction = manager.getTransaction();
	// start transaction
	   	transaction.begin();   
	   	// create a new course
                
	   	Project newCourse = projectService.createProject("XXX", "YYY", "10-5-2014", "12-4-2014","complete","a");	
	   	// read the course with the given course number
	   	Project course = projectService.readProject("YYY");
		System.out.println(course);		
		// update the course with the given course number
		//Project course1 = projectService.updateProject("ZZZ", "YYY",  "1-5-2014", "1-4-2014","Incomplete","b");
		//System.out.println(course1);		
		// delete a course
		//courseListService.deleteCourse("YYY");	
		// commit a transaction
		  transaction.commit();
                  

 }
}


