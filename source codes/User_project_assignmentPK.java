/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectdemo;

import java.io.Serializable;
import javax.persistence.Embeddable;

public class User_project_assignmentPK implements Serializable {
    private int project_id;
    private int user_id;

    public User_project_assignmentPK() {
    	
    	//name = "Joanne";
    	//id = 1;
    }

    public int getProjectId() {
        return project_id;
    }

    public void setProjectId(int id) {
        this.project_id = id;
       // System.out.print(this.project_id + " ");
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int id) {
        this.user_id = id;
     //   System.out.println(this.user_id);
    }

    public int hashCode() {
        return  project_id + user_id; //(int) name.hashCode() + id;
    } 

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof User_project_assignmentPK)) return false;
        if (obj == null) return false;
        User_project_assignmentPK pk = (User_project_assignmentPK) obj;
        return pk.project_id == project_id && pk.user_id == user_id;
    }
}
