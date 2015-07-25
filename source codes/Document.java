package projectdemo;

import javax.persistence.*;

import java.io.*;
import java.util.ArrayList;

@Entity(name = "document") 
public class Document implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int document_id;

  @Column
  private String project_name;
  
  @Column
  private String document_name;
  
  @Column
  private String document_path;
  
  public int getDocumentId() {
    return document_id;
  }

  public void setDocumentId(int document_id) {
    this.document_id = document_id;
  }
  
  public String getProjectName() {
    return project_name;
  }

  public void setProjectName(String project_name) {
    this.project_name = project_name;
  }
  
  public String getDocumentName() {
	    return document_name;
  }
  
  public void setDocumentName(String document_name) {
	    this.document_name = document_name;
}

  public String getDocumentPath() {
	    return document_path;
  }

  public void setDocumentPath(String document_path) {
	    this.document_path = document_path;
  }
	  
   // return the data in column i
   public Object getColumnData(int i) throws Exception {
	   if (i == 0)
		   return getDocumentId();
	   else if (i == 1)
		   return getProjectName();
	   else if (i == 2)
		   return getDocumentName();
	   else if (i ==3)
		   return getDocumentPath();
	   else
		   throw new Exception("Error: invalid column index in courselist table");    
   }
   
  @Override
  public String toString() { 
    return project_name + " " + document_name + " " + document_path;
  }
} 