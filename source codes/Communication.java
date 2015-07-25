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
@Entity(name = "communication")
public class Communication implements Serializable {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int communication_id;
  
  
  
  @Column
  private String communicator;
  
  @Column
  private String partner;
  
  @Column
  private String date;
  
 @Column
  private String time;
 
@Column
 private String message;

  
public int getCommunicationID() {
    return communication_id;
  }  
public void setCommunicationID(int id) {
    this.communication_id = id;
  }

  

public String getCommunicator() {
    return communicator;
  }

  public void setCommunicator(String name) {
    this.communicator = name;
  }

  public String getPartner() {
    return partner;
  }

  public void setPartner(String partner) {
    this.partner = partner;
  }
 
   
   public String getDate() {
	    return date;
	}
   
   public void setDate(String date) {
	    this.date = date;
	}

   public String getTime() {
	    return time;
	}
   
   public void setTime(String time) {
	    this.time = time;
	}
   
   
   public String getMessage() {
 	    return message;
    }

    public void setMessage(String s) {
 	    this.message = s;
 	}
    
    
   
   // return number of columns in the table
   public int getNumberOfColumns() {
	   return 6;
   }
   
   // return the data in column i
   public String getColumnData(int i) throws Exception {
           
           if(i == 0)
                return Integer.toString(getCommunicationID());
           else if (i == 1)
		   return getCommunicator();
	   else if (i == 2)
		   return getPartner();
	   else if (i == 3)
		   return getDate();
	   else if (i == 4)
		   return getTime();
	   else if (i == 5) 
		   return getMessage();
           
            throw new Exception("Error: invalid column index in courselist table");    
   }
   
   // return the name of column i
   public String getColumnName(int i) throws Exception {
	   String colName = null;
           if (i == 0)
                colName = "communication_id";
           
           else if (i == 1) 
		   colName = "communicator";
	   else if (i == 2)
		   colName = "partner";
	   else if (i == 3)
		   colName = "date";
	   else if (i == 4)
		   colName = "time";
	   else if (i == 5)
		   colName = "message";
           
	   else 
		   throw new Exception("Access to invalid column number in courselist table");
	   
	   return colName;
   }
   
   // set data column i to value
   public void setColumnData(int i, Object value) throws Exception {
           if (i ==0)
               communication_id = Integer.parseInt((String)value);
          
           else if (i == 1) 
		   communicator = (String) value;
	   else if (i == 2) 
		   partner = (String) value;
	   else if (i == 3) 
		   date = (String) value;
	   else if (i == 4)
		   time = (String) value;
	   else if (i == 5)
		  message = (String) value;
           
	   else
		   throw new Exception("Error: invalid column index in courselist table");    
   }
   
  @Override
  public String toString() {
      return "Communication [Communication ID =" + communication_id + ", "
              
            + "Communicator =" + communicator + ", "
    	    + " Partner =" + partner + ","
    	    + " Date =" + date + ","
    	    + " Time =" + time + ","
    	    + " Message =" + message + ","
        + "]";
    
  }
}
