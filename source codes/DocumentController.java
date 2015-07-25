package projectdemo;

import java.util.List;

public class DocumentController {
	
	private DocumentModel model;
	private ProjectService1 projectService;
	private DocumentService documentService;
	
	List<Project> projectList;
	List<Document> documentList;
	Object[][] jTableEntries;
	int documentId;
	
	public DocumentController() {
		
		model = new DocumentModel();
		projectService = new ProjectService1();
		projectList = projectService.readAll();
                
                documentService = new DocumentService();
		
		
		documentList = documentService.readAll();
	}
	
	public void addDocument(Object projectName, String documentName, String documentPath) {
		
		documentService.addDocument(projectName, documentName, documentPath);
	}
	
	public void removeDocument(int documentId) {
		documentService.deleteDocument(documentId);
	}
	
	public List<Project> getProjectList() {
		return projectList;
	}
	
	public List<Document> getDocumentList() {
		return documentList;
	}
	/*
	public void setJTableDetails() {
		
		for(int i=0;i<documentList.size();i++) {
			
			// user entries for jtable
			for(int j=0;j<usersList.size();j++) {
				try {
					if(((Integer)assignmentList.get(i).getColumnData(0)).intValue() == ((Integer)usersList.get(j).getColumnData(0)).intValue()) {
						
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
	}
	*/ /*
	public Object[][] getJtableEntries() {
		
		
	}*/	
}
