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

public class searchController {
	
	private UserService userService;
	private ProjectService1 projectService;
	private TaskService taskService;
	private User_project_assignmentService upaService;
	private searchModel model;
		
	List<Project> projectList;
	List<UserList> usersList;
	List<User_project_assignment> assignmentList; 
	
	Object[][] jTableEntries;
	
	public searchController() {
		
		model = new searchModel();
		
		userService = new UserService();
		usersList = userService.readAll(); // all users
		
		projectService = new ProjectService1();
		projectList = projectService.readAll();
		
		taskService = new TaskService();
		upaService = new User_project_assignmentService();
	}
	
	public List<UserList> getUserList() {
		return usersList;
	}
	
	public List<Project> getProjectList() {
		return projectList;
	}
	
	public List<Task> getTaskList(String userName) {
		//System.out.println("User is: " + userName);
		List<Task> taskList = taskService.getTaskList(userName);
                
		return taskList;
	}
	
	void setJTableEntries() {
		
		for(int i=0;i<assignmentList.size();i++) {
			
			// user entries for jtable
			for(int j=0;j<usersList.size();j++) {
				try {
					if(((Integer)assignmentList.get(i).getColumnData(0)).intValue() == ((Integer)usersList.get(j).getObjectColumnData(0)).intValue()) {
						
						jTableEntries[i][0] = (Object) usersList.get(j).getColumnData(1);
						jTableEntries[i][1] = (Object) usersList.get(j).getColumnData(2);
						jTableEntries[i][2] = (Object) usersList.get(j).getColumnData(3);
					//	System.out.print(usersList.get(j).getColumnData(1) + " ");
						//System.out.print(usersList.get(j).getColumnData(4) + " ");
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
			
			//project entries for jtable
			for(int j=0;j<projectList.size();j++) {
			
				try {
					if(((Integer)assignmentList.get(i).getColumnData(1)).intValue() == ((Integer)projectList.get(j).getObjectColumnData(0)).intValue()) {
						
						jTableEntries[i][3] = (Object) projectList.get(j).getColumnData(5);
						//System.out.println(projectList.get(j).getColumnData(1));
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}// inner for end
		}	//outer for end
	}
	
	public Object[][] getUserAndProjectStatus(int projectId) {
		
		assignmentList = upaService.getUserAndProjectStatus(projectId);
		jTableEntries = new Object[assignmentList.size()][4];
		setJTableEntries();
		return jTableEntries;
		//upaService.getUserAndProjectStatus();
	}
 }
