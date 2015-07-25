/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectdemo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@IdClass(User_project_assignmentPK.class)
@Entity(name = "user_project_assignments") 
// manadatorily use table name from relational database and assin to entity name, 
// because we will use entity name in java query
public class User_project_assignment implements Serializable { // CourseList is called Entity Class

	@Id private int user_id;

	@Id private int project_id;
  
	public int getUserId() {
	    return user_id;
	  }

	  public void setUserId(int user_id) {
	    this.user_id = user_id;
	  }
	  
	  public int getProjectId() {
		    return project_id;
		  }

		  public void setProjectId(int project_id) {
		    this.project_id = project_id;
		  }
  // return the data in column i
   public Object getColumnData(int i) throws Exception {
	   if (i == 0)
		   return getUserId();
	   else if (i == 1)
		   return getProjectId();
	   else
		   throw new Exception("Error: invalid column index in courselist table");    
   } 
  
// return the name of column i
   public String getColumnName(int i) throws Exception {
	   String colName = null;
	   if (i == 0) 
		   colName = "User Id";
	   else if (i == 1) 
		   colName = "Project Id";
	   else 
		   throw new Exception("Access to invalid column number in courselist table");
	   
	   return colName;
   }
   
  @Override
  public String toString() {
    return user_id + " " + project_id;
  }
} 
