/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectdemo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author AshitaRaghu
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author AshitaRaghu
 */
@Entity(name = "task")
public class Task implements Serializable {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int task_id;
  
   @Column
  private String  project_name;
   
    @Column
  private String  user_name;
   
   
  
  
  @Column
  private String task_name;
  
  @Column
  private String task_desc;
  
  @Column
  private String start_date;
  
 @Column
  private String end_date;
 
@Column
 private String dependency;

  
public int getTaskID() {
    return task_id;
  }  
public void setTaskID(int id) {
    this.task_id = id;
  }


  

public String getTaskName() {
    return task_name;
  }

  public void setTaskName(String name) {
    this.task_name = name;
  }

  public String getTaskDesc() {
    return task_desc;
  }

  public void setTaskDesc(String desc) {
    this.task_desc = desc;
  }
 
   
   public String getStartDate() {
	    return start_date;
	}
   public String getProjectName() {
	    return project_name;
	}
   
   public String getUserName() {
	    return user_name;
	}
   
   public void setStartDate(String date) {
	    this.start_date = date;
	}
   
    public void setProjectName(String project_name) {
	    this.project_name = project_name;
	}
    
     public void setUserName(String user_name) {
	    this.user_name = user_name;
	}

   public String getEndDate() {
	    return end_date;
	}
   
   public void setEndDate(String date) {
	    this.end_date = date;
	}
   
   
   public String getDependency() {
 	    return dependency;
    }

    public void setDependency(String s) {
 	    this.dependency = s;
 	}
    
    
   
   // return number of columns in the table
   public int getNumberOfColumns() {
	   return 7;
   
   }
   // return the data in column i
   public String getColumnData(int i) throws Exception {
           
           if(i == 0)
                return Integer.toString(getTaskID());
           else if (i == 1)
		   return getTaskName();
           else if (i == 2)
		   return getTaskDesc();
	   else if (i == 3)
		   return getStartDate();
	   else if (i == 4)
		   return getEndDate();
	   else if (i == 5)
		   return getDependency();
	   else if (i == 6) 
		   return getProjectName();
           else if (i == 7) 
		   return getUserName();
           
            throw new Exception("Error: invalid column index in courselist table");    
   }
   
   // return the name of column i
   public String getColumnName(int i) throws Exception {
	   String colName = null;
           if (i == 0)
                colName = "task_id";
           else if (i == 1) 
		   colName = "task_name";
           else if (i == 2) 
		   colName = "task_desc";
	   else if (i == 3)
		   colName = "start_date";
	   else if (i == 4)
		   colName = "end_date";
	   else if (i == 5)
		   colName = "dependency";
	   else if (i == 6)
		   colName = "project_name";
           else if (i == 7)
		   colName = "user_name";
          
           
	   else 
		   throw new Exception("Access to invalid column number in courselist table");
	   
	   return colName;
   }
   
   // set data column i to value
   public void setColumnData(int i, Object value) throws Exception {
           if (i ==0)
               task_id = Integer.parseInt((String)value);
        
           else if (i == 1) 
		   task_name = (String) value;
	   else if (i == 2) 
		   task_desc = (String) value;
	   else if (i == 3) 
		   start_date = (String) value;
	   else if (i == 4)
		   end_date = (String) value;
	   else if (i == 5)
		  dependency = (String) value;
           else if (i == 6)
		  project_name = (String) value;
           else if (i == 7)
		  user_name = (String) value;
           
	   else
		   throw new Exception("Error: invalid column index in courselist table");    
   }
   
  @Override
  public String toString() {
      return "Task [Task ID =" + task_id + ", "
              
            + "Task Name =" + task_name + ", "
    	    + " Task Description =" + task_desc + ","
    	    + " Start Date =" + start_date + ","
    	    + " End Date =" + end_date + ","
    	    + " dependency =" + dependency + ","
        + "]";
    
  }
}
