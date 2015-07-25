/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectdemo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author team5
 */
@Entity(name = "budget")
public class Budget implements Serializable {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private  int budget_id;
  
  @Column
  private String service_name;
  
  @Column
  private String service_desc;
  
  @Column
  private int estimated_cost;
  
 @Column
  private int actual_cost;
 

  
public int getBudgetID() {
    return budget_id;
  }  
public void setBudgetID(int id) {
    this.budget_id = id;
  }

  

public String getServiceName() {
    return service_name;
  }

  public void setServiceName(String name) {
    this.service_name = name;
  }

  public String getServiceDesc() {
    return service_desc;
  }

  public void setServiceDesc(String desc) {
    this.service_desc = desc;
  }
 
   
   public int getEstimatedCost() {
	    return estimated_cost;
	}
   
   public void setEstimatedCost(int cost) {
	    this.estimated_cost = cost;
	}

   public int getActualCost() {
	    return actual_cost;
	}
   
   public void setActualCost(int cost) {
	    this.actual_cost = cost;
	}
   
   
   
    
    
   
   // return number of columns in the table
   public int getNumberOfColumns() {
	   return 5;
   }
   
   // return the data in column i
   public String getColumnData(int i) throws Exception {
           
           if(i == 0)
                return Integer.toString(getBudgetID());
           else if (i == 1)
		   return getServiceName();
	   else if (i == 2)
		   return getServiceDesc();
	   else if (i == 3)
		   return Integer.toString(getEstimatedCost());
	   else if (i == 4)
		   return Integer.toString(getActualCost());
	   
           
            throw new Exception("Error: invalid column index in courselist table");    
   }
   
   // return the name of column i
   public String getColumnName(int i) throws Exception {
	   String colName = null;
           if (i == 0)
                colName = "budget_id";
           
           else if (i == 1) 
		   colName = "service_name";
	   else if (i == 2)
		   colName = "service_desc";
	   else if (i == 3)
		   colName = "estimated_cost";
	   else if (i == 4)
		   colName = "actual_cost";
	  
	   else 
		   throw new Exception("Access to invalid column number in courselist table");
	   
	   return colName;
   }
   
   // set data column i to value
   public void setColumnData(int i, Object value) throws Exception {
           if (i ==0)
               budget_id = Integer.parseInt((String)value);
          
           else if (i == 1) 
		   service_name = (String) value;
	   else if (i == 2) 
		  service_desc = (String) value;
	   else if (i == 3) 
		  estimated_cost = Integer.parseInt((String)value);
	   else if (i == 4)
		   actual_cost = Integer.parseInt((String)value);
	   
           
	   else
		   throw new Exception("Error: invalid column index in courselist table");    
   }
   
  @Override
  public String toString() {
      return "Budget [Budget ID =" + budget_id + ", "
              
            + "Service Name =" + service_name + ", "
    	    + " Service Description =" + service_desc + ","
    	    + " Estimated Cost =" + estimated_cost + ","
    	    + " Actual Cost =" + actual_cost + ","
    	    
        + "]";
    
  }
}
