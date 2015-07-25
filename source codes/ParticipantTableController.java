/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectdemo;

import java.util.List;

/**
 *
 * @author AshitaRaghu
 */
public class ParticipantTableController {

	
	private User_project_assignmentService upa;
	private UserService userService;
	private ProjectService1 projectService;
	private ParticipantAndUserModel model;
	private WelcomeAdminGUI gui;
	private ParticipantTableController controller;
	
	List<Project> projectList;
	List<UserList> usersList;
	List<User_project_assignment> assignmentList; 
	
	Object[][] jTableEntries;
	
	public ParticipantTableController() {
		
		//model = new ParticipantAndUserModel();
		upa = new User_project_assignmentService(/*model*/);
		assignmentList = upa.readAll(); // all assignments
		
		userService = new UserService(/*model*/);
		usersList = userService.readAll(); // all users
		
		projectService = new ProjectService1(/*model*/);
		projectList = projectService.readAll();
				
		jTableEntries = new Object[assignmentList.size()][3];
		setJtableDetails();		
	}
	
	public void addParticipantList(Object userId, Object projectId) {
		
		upa.getContoller(this);
		upa.createAssignment(userId, projectId);
	}
	
	public void removeParticipants(Object userId, Object projectId) {
		upa.deleteAssignment(userId,projectId);
	}
	
	public Object[][] getJTableEntries() {
		return jTableEntries;
	}
	
	public List<UserList> getUserList() {
		return usersList;
	}
	
	public List<Project> getProjectList() {
		return projectList;
	}
	
	/*public void setController(ParticipantTableController contoller) {
		
		this.controller = controller;
	}*/
	
	public void setJtableDetails() {
		
		for(int i=0;i<assignmentList.size();i++) {
		
			// user entries for jtable
			for(int j=0;j<usersList.size();j++) {
				try {
					if(((Integer)assignmentList.get(i).getColumnData(0)) == ((Integer)usersList.get(j).getObjectColumnData(0)).intValue()) {
						
						jTableEntries[i][1] = (Object) usersList.get(j).getColumnData(1);
						jTableEntries[i][2] = (Object) usersList.get(j).getColumnData(4);
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
						
						jTableEntries[i][0] = (Object) projectList.get(j).getColumnData(1);
						//System.out.println(projectList.get(j).getColumnData(1));
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}// inner for end
		}	//outer for end
			
	}	// function end

    
}
