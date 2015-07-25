/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectdemo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.*;

/**
 *
 * @author AshitaRaghu
 */
@Entity(name = "project")
public class Project implements Serializable {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private  int project_id;
  
  @Column
  private String project_name;
  
  @Column
  private String project_desc;
  
  @Column
  private String start_date;
  
 @Column
  private String end_date;
 
@Column
 private String status;

@Column
 private String outcome;

  
public int getProjectID() {
    return project_id;
  }  
public void setProjectID(int id) {
    this.project_id = id;
  }

public String getProjectName() {
    return project_name;
  }

  public void setProjectName(String name) {
    this.project_name = name;
  }

  public String getProjectDesc() {
    return project_desc;
  }

  public void setProjectDesc(String desc) {
    this.project_desc = desc;
  }
 
   
   public String getStartDate() {
	    return start_date;
	}
   
   public void setStartDate(String date) {
	    this.start_date = date;
	}

   public String getEndDate() {
	    return end_date;
	}
   
   public void setEndDate(String date) {
	    this.end_date = date;
	}
   
   
   public String getStatus() {
 	    return status;
    }

    public void setStatus(String s) {
 	    this.status = s;
 	}
    
    public String getOutcome() {
 	    return outcome;
    }

    public void setOutcome(String s) {
 	    this.outcome = s;
 	}
   
   // return number of columns in the table
   public int getNumberOfColumns() {
	   return 7;
   }
   
  
   
   
   // return the data in column i
   public String getColumnData(int i) throws Exception {
           
            if(i == 0)
                return Integer.toString(getProjectID());
           else if (i == 1)
		   return getProjectName();
	   else if (i == 2)
		   return getProjectDesc();
	   else if (i == 3)
		   return getStartDate();
	   else if (i == 4)
		   return getEndDate();
	   else if (i == 5) 
		   return getStatus();
           else if (i == 6)
                    return getOutcome();
            throw new Exception("Error: invalid column index in courselist table");    
   }
   
    public Object getObjectColumnData(int i) throws Exception {
           
            if(i == 0)
                return getProjectID();
           else if (i == 1)
		   return getProjectName();
	   else if (i == 2)
		   return getProjectDesc();
	   else if (i == 3)
		   return getStartDate();
	   else if (i == 4)
		   return getEndDate();
	   else if (i == 5) 
		   return getStatus();
           else if (i == 6)
                    return getOutcome();
            throw new Exception("Error: invalid column index in courselist table");    
   }
   
   
   // return the name of column i
   public String getColumnName(int i) throws Exception {
	   String colName = null;
           if (i == 0)
                colName = "project_id";
           else if (i == 1) 
		   colName = "project_name";
	   else if (i == 2)
		   colName = "project_desc";
	   else if (i == 3)
		   colName = "start_date";
	   else if (i == 4)
		   colName = "end_date";
	   else if (i == 5)
		   colName = "status";
           else if (i == 6)
		   colName = "outcome";
	   else 
		   throw new Exception("Access to invalid column number in courselist table");
	   
	   return colName;
   }
   
   // set data column i to value
   public void setColumnData(int i, Object value) throws Exception {
           if (i ==0)
               project_id = Integer.parseInt((String)value);
           else if (i == 1) 
		   project_name = (String) value;
	   else if (i == 2) 
		   project_desc = (String) value;
	   else if (i == 3) 
		   start_date = (String) value;
	   else if (i == 4)
		   end_date = (String) value;
	   else if (i == 5)
		  status = (String) value;
           else if (i == 6)
		  outcome = (String) value;
	   else
		   throw new Exception("Error: invalid column index in courselist table");    
   }
   
    
   
   
  @Override
  public String toString() {
      return project_name;
    
  }
}
