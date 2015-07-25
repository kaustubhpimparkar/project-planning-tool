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
import javax.persistence.ManyToMany;

/**
 *
 * @author Sowmya
 */
@Entity(name = "useraccount")
public class UserList implements Serializable {
    private static final long serialVersionUID = 1L;
    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }*/
    
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column
	  private int id;
     
    

    /*@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserList)) {
            return false;
        }
        UserList other = (UserList) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }*/

     
	
 @Column(name = "first_name")
  private String first_name;
  
  
  
  @Column
  private String last_name;
  
  @Column
  private String email;
  
 @Column
  private String phone_number;
 
 @Column
 private String user_name;
 
 @Column
 private String password;
 
 @Column
 private String role;
 

  
  public String getfirstName() {
    return first_name;
  }

  public void setfirstName(String name) {
    this.first_name = name;
  }

  public String getlastName() {
    return last_name;
  }

  public void setlastName(String name) {
    this.last_name = name;
  }
  
  public int getId() {
	    return id;
   }

   public void setId(int num) {
	    this.id = num;
	}
   
   public String getEmail() {
	    return email;
	}
   
   public void setEmail(String email) {
       this.email = email;
   }
   
   public void setPhoneNumber(String phoneNumber) {
	    this.phone_number = phoneNumber;
	}

   public String getPhoneNumber() {
	    return phone_number;
	}
   
   public void setUserName(String userName) {
	    this.user_name = userName;
	}
   
   public String getUserName() {
	    return user_name;
	}
   
    public void setPassword(String password) {
	    this.password = password;
	}
   
   public String getPassword() {
	    return password;
	}
   
   // return number of columns in the table
   public int getNumberOfColumns() {
	   return 8;
   }
   
   public void setRole(String role) {
	    this.role = role;
	}
   
   public String getRole() {
	    return role;
	}
   
   
   
   // return the data in column i
   public String getColumnData(int i) throws Exception {
	   if (i == 0)
		   return Integer.toString(getId());
	   else if (i == 1)
		   return getfirstName();
	   else if (i == 2) 
		   return getlastName();
	   else if (i == 3)
		   return getEmail();
	   else if (i == 4)
		   return getPhoneNumber();
           else if (i == 5)
		   return getUserName();
           else if (i == 6)
		   return getPassword();
           else if (i == 7)
		   return getRole();
           
	   else
		   throw new Exception("Error: invalid column index in courselist table");    
   }
   
   // return the data in column i
   public Object getObjectColumnData(int i) throws Exception {
	   if (i == 0)
		   return getId();
	   else if (i == 1)
		   return getfirstName();
	   else if (i == 2) 
		   return getlastName();
	   else if (i == 3)
		   return getEmail();
	   else if (i == 4)
		   return getPhoneNumber();
           else if (i == 5)
		   return getUserName();
           else if (i == 6)
		   return getPassword();
           else if (i == 7)
		   return getRole();
           
	   else
		   throw new Exception("Error: invalid column index in courselist table");    
   }
   
   // return the name of column i
   public String getColumnName(int i) throws Exception {
	   String colName = null;
	   if (i == 0) 
		   colName = "id";
	   else if (i == 1)
		   colName = "first_name";
	   else if (i == 2)
		   colName = "last_name";
	   else if (i == 3)
		   colName = "email";
	   else if (i == 4)
		   colName = "phone_number";
           else if (i == 5)
		   colName = "user_name";
           else if (i == 6)
		   colName = "password";
           else if (i == 7)
		   colName = "role";
	   else 
		   throw new Exception("Access to invalid column number in courselist table");
	   
	   return colName;
   }
   
   // set data column i to value
   public void setColumnData(int i, Object value) throws Exception {
	   if (i == 0) 
		   id = Integer.parseInt((String) value);
	   else if (i == 1) 
		   first_name = (String) value;
	   else if (i == 2) 
		   last_name =  (String) value;
	   else if (i == 3)
		   email = (String) value;
	   else if (i == 4)
		  phone_number = (String) value;
           else if (i == 5)
		   user_name = (String) value;
           else if (i == 6)
		   password = (String) value;
           else if (i == 7)
		   role = (String) value;
	   else
		   throw new Exception("Error: invalid column index in courselist table");    
   }
   

   
  @Override
  public String toString() {
    return user_name;
  }
    
}
