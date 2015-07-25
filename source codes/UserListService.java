/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectdemo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author Sowmya
 */
public class UserListService {
    
    private EntityManager manager;
    public UserListService(EntityManager manager) 
    { 
	this.manager = manager;
    }
    
public UserList createCourse(String firstName, String lastName, String email, String phoneNumber , String userName , String password,String role)
{
    UserList user = new UserList();
    user.setfirstName(firstName);
    user.setlastName(lastName);
    user.setEmail(email);
    user.setPhoneNumber(phoneNumber);
    user.setUserName(userName);
    user.setPassword(password);
    user.setRole(role);
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");

EntityManager manager = emf.createEntityManager();

EntityTransaction transaction = manager.getTransaction();
   
    
    transaction.begin();
    manager.merge(user);
    transaction.commit();
   
    return user; 
 }

public UserList readCourse(int userID) 
{  
    UserList user = manager.find(UserList.class,userID);
    return user;
}
// method to read all records
public List<UserList> readAll() {
    TypedQuery<UserList> query = manager.createQuery("SELECT e FROM useraccount e ", UserList.class);
    List<UserList> result = query.getResultList();
    return result;
}
// method to update a record
public UserList updateUser(int index,String firstName, String lastName, String email, String phoneNumber , String userName ,String password,String role) 
{
    
    
    List<UserList> resultList = readAll();
        
     int projectID=0;
    try {
        projectID = Integer.parseInt(resultList.get(index).getColumnData(0));
    } catch (Exception ex) {
        Logger.getLogger(UserListService.class.getName()).log(Level.SEVERE, null, ex);
    }
     UserList user = manager.find(UserList.class, projectID);
     
      if (user != null)
      { 
    	  user.setfirstName(firstName);
          user.setlastName(lastName);
          user.setEmail(email);
          user.setPhoneNumber(phoneNumber);
          user.setUserName(userName);
          user.setPassword(password);
          user.setRole(role);
      }
      return user; 
 }

public void deleteUser(int index)
{
	List<UserList> resultList = readAll();
        
     int projectID=0;
    try {
        projectID = Integer.parseInt(resultList.get(index).getColumnData(0));
    } catch (Exception ex) {
        Logger.getLogger(UserListService.class.getName()).log(Level.SEVERE, null, ex);
    }
     UserList user = manager.find(UserList.class, projectID);
 if (user != null) {
 manager.remove(user);
 }
}

public int loginUser(String userName,String password)
{
    String usrname = null;
    String pwd = null;
    List<UserList> resultList = readAll();
    for(int i = 0;i<resultList.size();i++)
    {
        try {
            usrname = resultList.get(i).getColumnData(5);
            pwd = resultList.get(i).getColumnData(6);
        } catch (Exception ex) {
            Logger.getLogger(UserListService.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("username" + usrname);
        //System.out.println("password" + pwd);
        if(usrname.equals(userName) && pwd.equals(password))
        {
            
            return 1;
        }  
    }
    return 0;
    //System.out.println("Login Not Successful");
}
    
}
