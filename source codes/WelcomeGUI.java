/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectdemo;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import projectdemo.CommTableController;

/**
 *
 * @author Sowmya
 */
public class WelcomeGUI extends javax.swing.JFrame {

    /**
     * Creates new form WelcomeGUI
     */
     private JTable jtable1; // the table displayed on the GUI
    private ProjectTableController projectTableController;
    
    private JTable budgetTable; // the table displayed on the GUI
    private BudgetTableController budgetTableController; // glue between model and gui
    //Object[] projectList;
    
    private JTable userTable; // the table displayed on the GUI
    private UserListTableController userListTableController;
    
    private JTable taskTable; // the table displayed on the GUI
    private TaskTableController taskTableController; // glue between model and gui
    Object[] userList;
    //Object[] projectList;
    
     private JTable commTable; // the table displayed on the GUI
     private JTable docTable;
    private CommTableController commTableController; // glue between model and gui
    
    private JPanel contentPane;
	private JTable table;

	DocumentController documentController;
        private ParticipantTableController participantTableController; 
	
	Object[] columnNames = {"Project Name", "Document Name","Document Path"};
	Object[][] data = {};
        Object[] columnNames1 = {"Project Name", "User Name","Role"};
	Object[][] data1 = {/*{"elizabeth"},{"saurabh"}*/};
	List<UserList> user;
	List<Project> projectList;
	List<UserList> usersList;
        
        private JTable searchTable;
	
	String documentPath = "";
	String documentName = "";
        List<Project> project_search_List;
	searchController controller;

	List<Document> documentList;
       
 
    //Object[] userList;
    //Object[] projectList;
    
    public WelcomeGUI() {
        initComponents();
        documentController = new DocumentController();
        controller = new searchController();
        
        projectTableController = new ProjectTableController(this);
        projectList = documentController.getProjectList();
        documentList = documentController.getDocumentList();
        
        participantTableController = new ParticipantTableController();
          
        usersList= participantTableController.getUserList();
	project_search_List = participantTableController.getProjectList();
        
      
		
        projectTableController = new ProjectTableController(this);
         addJTable();
         
         budgetTableController = new BudgetTableController(this);
        /*projectList = budgetTableController.getProjectList();

        for(int i=0; i<projectList.length; i++){
            projectBudgetCombo.addItem(projectList[i]);
        }*/
         addBudgetTable();
         
         userListTableController = new UserListTableController(this);
         addUserTable();
         
         taskTableController = new TaskTableController(this);
        /*userList = taskTableController.getUserList();
        projectList = taskTableController.getProjectList();

        for(int i=0; i<userList.length; i++){
            teamMemberCombo.addItem(userList[i]);
        }
        for(int i=0; i<projectList.length; i++){
            projectTaskCombo.addItem(projectList[i]);
        }*/
         addTaskTable();
         
         commTableController = new CommTableController(this);
        /*userList = commTableController.getUserList();
        projectList = commTableController.getProjectList();

        for(int i=0; i<userList.length; i++){
            teamMemberCombo1.addItem(userList[i]);
        }
        for(int i=0; i<projectList.length; i++){
            projectCommCombo.addItem(projectList[i]);
        }*/
         addCommTable();
         
         docTable = new JTable(new DefaultTableModel(columnNames,0));
               docTable.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		    //    Point p = me.getPoint();
		        if (me.getClickCount() == 2) {
					JTable target = (JTable)me.getSource();
					int row = target.getSelectedRow();
					    
					DefaultTableModel model = (DefaultTableModel) table.getModel();  
					String documentPath = model.getValueAt(row, 2).toString();
					    
					try {
			             Desktop.getDesktop().open(
			                    new File(documentPath));
			        } catch (IOException e1) {

			            e1.printStackTrace();
			        }	
		        }
		    }
		});
               scrollPaneDoc.setViewportView(docTable);

             
//		scrollDoc.setViewportView(docTable);
                
                for(int i=0; i < projectList.size(); i++) {
			try {
				comboBoxProject.addItem(projectList.get(i).getColumnData(1));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                }
                        
                //scrollpane.setViewportView(userProjectTable);
                for(int i=0; i < usersList.size(); i++) {
			
			try {
				comboBoxUser.addItem(usersList.get(i).getColumnData(5));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
                for(int i=0; i < projectList.size(); i++) {
			
			try {
				comboBoxSearchProject.addItem(projectList.get(i).getColumnData(1));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
                
                
		usersList = controller.getUserList();
		projectList = controller.getProjectList();
                
                searchTable = new JTable();
                scrollSearch = new JScrollPane();
             
                scrollSearch.setViewportView(searchTable);
		
                
              //jPanel1 = new UserListGUI();
         
        
     
   }
     public void addJTable() {
		// add the data and column names to a JTable
		//jtable1  = new JTable(courseListTableController.getData(), courseListTableController.getColumnNames());
	   
	    jtable1 = new JTable(projectTableController.getTableModel());
		// add a ListSelectionListener to the table
		jtable1.getSelectionModel().addListSelectionListener(projectTableController);
		
		// add the table to a scrollpane
		JScrollPane scrollpane = new JScrollPane(jtable1);
		// create a window
		jPanel6.setLayout(new BorderLayout());
		jPanel6.add(scrollpane, BorderLayout.CENTER);
		

}
     public void updateTable() {
	jtable1.setModel(projectTableController.getTableModel());
}
/*public void setProjectIDTextField(String value) {
	projectIDTextField.setText(value);
}*/
// display data on the courseNameTextField
public void setProjectNameTextField(String value) {
	projectNameTextField.setText(value);
}

// display data on the courseNumberTextField
public void setProjectDescTextField(String value) {
	projectDescTextField.setText(value);
}


// display data on the startDateTextField
public void setStartDateTextField(String value) {
	startDateTextField.setText(value);
}

// display data on the endDateTextField
public void setEndDateTextField(String value) {
	endDateTextField.setText(value);
}
// display data on the enrollmentTextField
public void setStatusTextField(String value) {
	statusTextField.setText(value);
}
public void setOutcomeTextField(String value) {
	outcomeTextField.setText(value);
}

public void addBudgetTable() {
		// add the data and column names to a JTable
		//jtable1  = new JTable(courseListTableController.getData(), courseListTableController.getColumnNames());
	   
	    budgetTable = new JTable(budgetTableController.getTableModel());
		// add a ListSelectionListener to the table
		budgetTable.getSelectionModel().addListSelectionListener(budgetTableController);
		
		// add the table to a scrollpane
		JScrollPane scrollpane = new JScrollPane(budgetTable);
		// create a window
		jPanel14.setLayout(new BorderLayout());
		jPanel14.add(scrollpane, BorderLayout.CENTER);
		

}
    public void updateBudgetTable() {
	budgetTable.setModel(budgetTableController.getTableModel());
}
public void setProjectBudgetTextField(String value) {
	projectBudgetTextField.setText(value);
}
// display data on the courseNameTextField
public void setServiceNameTextField(String value) {
	serviceNameTextField.setText(value);
}

// display data on the courseNumberTextField
public void setServiceDescTextField(String value) {
	serviceDescTextField.setText(value);
}


// display data on the startDateTextField
public void setEstimatedCostTextField(String value) {
	estimatedCostTextField.setText(value);
}

// display data on the endDateTextField
public void setActualCostTextField(String value) {
	actualCostTextField.setText(value);
}
  
public void addUserTable() {
    		// add the data and column names to a JTable
    		//jtable1  = new JTable(courseListTableController.getData(), courseListTableController.getColumnNames());
    	
    	   userTable = new JTable(userListTableController.getTableModel());
    		// add a ListSelectionListener to the table
    		userTable.getSelectionModel().addListSelectionListener(userListTableController);
    		
    		// add the table to a scrollpane
    		JScrollPane scrollpane = new JScrollPane(userTable);
    		// create a window
    		tablePanel.setLayout(new BorderLayout());
    		tablePanel.add(scrollpane, BorderLayout.CENTER);
    		

    }
   public void updateUserTable() {
    	userTable.setModel(userListTableController.getTableModel());
    }
     
    // display data on the courseNameTextField
    public void setFirstNameTextField(String value) {
    	firstNameTextField.setText(value);
    }
    
    // display data on the courseNumberTextField
    public void setLastNameTextField(String value) {
    	lastNameTextField.setText(value);
    }
    
    
    
    // display data on the startDateTextField
    public void setEmailTextField(String value) {
    	emailAddressTextField.setText(value);
    }
    
    // display data on the endDateTextField
    public void setPhoneNumberTextField(String value) {
    	phoneNumberTextField.setText(value);
    }
    
    // display data on the endDateTextField
    public void setUserNameTextField(String value) {
    	userNameTextField.setText(value);
    }
    
    // display data on the endDateTextField
    public void setPasswordTextField(String value) {
    	passwordTextField.setText(value);
    }
    
     public void setRoleCombo(String value) {
    	roleCombo.addItem(value);
    }
     
     public void addTaskTable() {
		// add the data and column names to a JTable
		//jtable1  = new JTable(courseListTableController.getData(), courseListTableController.getColumnNames());
	   
	    taskTable = new JTable(taskTableController.getTableModel());
		// add a ListSelectionListener to the table
		taskTable.getSelectionModel().addListSelectionListener(taskTableController);
		
		// add the table to a scrollpane
		JScrollPane scrollpane = new JScrollPane(taskTable);
		// create a window
		jPanel12.setLayout(new BorderLayout());
		jPanel12.add(scrollpane, BorderLayout.CENTER);
		

}
public void updateTaskTable() {
	taskTable.setModel(taskTableController.getTableModel());
}
public void setTaskProjectTextField(String value) {
	projectTaskTextField.setText(value);
}
public void setTaskTeamMemberTextField(String value) {
	teamMemberTaskTextField.setText(value);
}
// display data on the courseNameTextField
public void setTaskNameTextField(String value) {
	taskNameTextField.setText(value);
}

// display data on the courseNumberTextField
public void setTasktDescTextField(String value) {
	taskDescTextField.setText(value);
}


// display data on the startDateTextField
public void setStartDateTextField1(String value) {
	startDateTextField1.setText(value);
}

// display data on the endDateTextField
public void setEndDateTextField1(String value) {
	endDateTextField1.setText(value);
}
// display data on the enrollmentTextField
public void setDependencyTextField(String value) {
	dependencyTextField.setText(value);
}

public void addCommTable() {
		// add the data and column names to a JTable
		//jtable1  = new JTable(courseListTableController.getData(), courseListTableController.getColumnNames());
	   
	    commTable = new JTable(commTableController.getTableModel());
		// add a ListSelectionListener to the table
		commTable.getSelectionModel().addListSelectionListener(commTableController);
		
		// add the table to a scrollpane
		JScrollPane scrollpane = new JScrollPane(commTable);
		// create a window
		jPanel15.setLayout(new BorderLayout());
		jPanel15.add(scrollpane, BorderLayout.CENTER);
		

}
    public void updateCommTable() {
	commTable.setModel(commTableController.getTableModel());
}
public void setProjectCommTextField(String value) {
	projectCommTextField.setText(value);
}
public void setTeamMemberCommTextField(String value) {
	teamMemberCommTextField.setText(value);
}
// display data on the courseNameTextField
public void setPartnerTextField(String value) {
	partnerTextField.setText(value);
}

// display data on the courseNumberTextField


public void setMessageTextField(String value) {
	messageTextArea.setText(value);
}
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        firstNameTextField = new javax.swing.JTextField();
        emailAddressTextField = new javax.swing.JTextField();
        lastNameTextField = new javax.swing.JTextField();
        phoneNumberTextField = new javax.swing.JTextField();
        passwordTextField = new javax.swing.JTextField();
        userNameTextField = new javax.swing.JTextField();
        roleCombo = new javax.swing.JComboBox();
        userAddButton = new javax.swing.JButton();
        userEditButton = new javax.swing.JButton();
        userDeleteButton = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        outcomeTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        statusTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        endDateTextField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        startDateTextField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        projectDescTextField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        projectNameTextField = new javax.swing.JTextField();
        updateProjectButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        addTaskButton = new javax.swing.JButton();
        updateTaskButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        dependencyTextField = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        endDateTextField1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        startDateTextField1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        taskDescTextField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        taskNameTextField = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        teamMemberTaskTextField = new javax.swing.JTextField();
        projectTaskTextField = new javax.swing.JTextField();
        deleteTaskButton = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        serviceNameTextField = new javax.swing.JTextField();
        serviceDescTextField = new javax.swing.JTextField();
        estimatedCostTextField = new javax.swing.JTextField();
        actualCostTextField = new javax.swing.JTextField();
        updateBudgetButton = new javax.swing.JButton();
        deleteBudgetButton = new javax.swing.JButton();
        projectBudgetTextField = new javax.swing.JTextField();
        addBudgetButton = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        projectCommTextField = new javax.swing.JTextField();
        teamMemberCommTextField = new javax.swing.JTextField();
        partnerTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageTextArea = new javax.swing.JTextArea();
        addCommButton = new javax.swing.JButton();
        deleteCommButton = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        lblChooseProject = new javax.swing.JLabel();
        comboBoxProject = new javax.swing.JComboBox();
        textField = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        scrollPaneDoc = new javax.swing.JScrollPane();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        comboBoxSearch = new javax.swing.JComboBox();
        comboBoxSearchProject = new javax.swing.JComboBox();
        comboBoxUser = new javax.swing.JComboBox();
        scrollSearch = new javax.swing.JScrollPane();
        jPanel22 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(51, 51, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(102, 102, 255)));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("First Name");

        jLabel3.setText("Last Name");

        jLabel4.setText("Email Address");

        jLabel5.setText("Phone Number");

        jLabel6.setText("User Name");

        jLabel8.setText("Password");

        jLabel9.setText("Role");

        roleCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Student", "Faculty Advisor", "Partner", "Admin" }));
        roleCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roleComboActionPerformed(evt);
            }
        });

        userAddButton.setText("Add");
        userAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userAddButtonActionPerformed(evt);
            }
        });

        userEditButton.setText("Edit");
        userEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userEditButtonActionPerformed(evt);
            }
        });

        userDeleteButton.setText("Delete");
        userDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userDeleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(109, 109, 109)
                        .addComponent(firstNameTextField))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(83, 83, 83)
                                .addComponent(phoneNumberTextField))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(86, 86, 86)
                                .addComponent(emailAddressTextField))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(107, 107, 107)
                                .addComponent(userNameTextField))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(116, 116, 116)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(roleCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(passwordTextField)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(111, 111, 111)
                        .addComponent(lastNameTextField)))
                .addGap(93, 93, 93))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(userAddButton)
                .addGap(124, 124, 124)
                .addComponent(userEditButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addComponent(userDeleteButton)
                .addGap(91, 91, 91))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(emailAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(phoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(userNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(roleCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userAddButton)
                    .addComponent(userEditButton)
                    .addComponent(userDeleteButton)))
        );

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1647, Short.MAX_VALUE)
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 414, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(228, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("User", jPanel1);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(51, 51, 255)));

        jLabel7.setText("Outcome ");

        jLabel10.setText("Status ");

        jLabel11.setText("End Date ");

        jLabel12.setText("Start Date ");

        jLabel13.setText("Project Decription ");

        projectDescTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectDescTextFieldActionPerformed(evt);
            }
        });

        jLabel14.setText("Project Name ");

        updateProjectButton.setText("Update");
        updateProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProjectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1027, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 352, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel7))
                                .addGap(119, 119, 119)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(statusTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                                    .addComponent(outcomeTextField)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11))
                                .addGap(63, 63, 63)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(projectNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(projectDescTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(endDateTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                                        .addComponent(startDateTextField, javax.swing.GroupLayout.Alignment.LEADING))))
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(339, 339, 339)
                        .addComponent(updateProjectButton)))
                .addContainerGap(808, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(projectNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(projectDescTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel11))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(endDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(statusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(outcomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(updateProjectButton)
                .addGap(12, 12, 12)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(199, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Project", jPanel2);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(51, 51, 255)));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        addTaskButton.setText("Add");
        addTaskButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTaskButtonActionPerformed(evt);
            }
        });

        updateTaskButton.setText("Update");
        updateTaskButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateTaskButtonActionPerformed(evt);
            }
        });

        jLabel15.setText("Dependency ");

        jLabel16.setText("End Date ");

        endDateTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endDateTextField1ActionPerformed(evt);
            }
        });

        jLabel17.setText("Start Date ");

        jLabel18.setText("Task Description ");

        jLabel19.setText("Task Name ");

        jLabel20.setText("Team Member ");

        jLabel1.setText("Project ");

        teamMemberTaskTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teamMemberTaskTextFieldActionPerformed(evt);
            }
        });

        projectTaskTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectTaskTextFieldActionPerformed(evt);
            }
        });

        deleteTaskButton.setText("Delete");
        deleteTaskButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTaskButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel1))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(endDateTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(startDateTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(taskDescTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(taskNameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(teamMemberTaskTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(projectTaskTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dependencyTextField)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(addTaskButton)
                        .addGap(91, 91, 91)
                        .addComponent(updateTaskButton)
                        .addGap(94, 94, 94)
                        .addComponent(deleteTaskButton)))
                .addContainerGap(556, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(projectTaskTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addComponent(teamMemberTaskTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(taskNameTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(taskDescTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(startDateTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(endDateTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(dependencyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateTaskButton)
                    .addComponent(addTaskButton)
                    .addComponent(deleteTaskButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1450, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(468, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Task", jPanel7);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(0, 0, 255)));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setText("Project ");

        jLabel22.setText("Service Name ");

        jLabel23.setText("Service Description ");

        jLabel24.setText("Estimated Cost ");

        jLabel25.setText("Actual Cost ");

        updateBudgetButton.setText("Update");
        updateBudgetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBudgetButtonActionPerformed(evt);
            }
        });

        deleteBudgetButton.setText("Delete");
        deleteBudgetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBudgetButtonActionPerformed(evt);
            }
        });

        addBudgetButton.setText("Add");
        addBudgetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBudgetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(addBudgetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(updateBudgetButton)
                        .addGap(89, 89, 89)
                        .addComponent(deleteBudgetButton)
                        .addGap(103, 103, 103))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel23))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(projectBudgetTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(serviceNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 424, Short.MAX_VALUE))
                                    .addComponent(serviceDescTextField)))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel24))
                                .addGap(53, 53, 53)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(actualCostTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                    .addComponent(estimatedCostTextField))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(projectBudgetTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(serviceNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(serviceDescTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(estimatedCostTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(actualCostTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateBudgetButton)
                    .addComponent(deleteBudgetButton)
                    .addComponent(addBudgetButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1319, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(581, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Budget", jPanel9);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(51, 51, 255)));
        jPanel8.setForeground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1578, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 463, Short.MAX_VALUE)
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jLabel26.setText("Project ");

        jLabel27.setText("Communicator ");

        jLabel28.setText("Partner ");

        jLabel29.setText("Message ");

        messageTextArea.setColumns(20);
        messageTextArea.setRows(5);
        jScrollPane1.setViewportView(messageTextArea);

        addCommButton.setText("Add");
        addCommButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCommButtonActionPerformed(evt);
            }
        });

        deleteCommButton.setText("Delete");
        deleteCommButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCommButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(addCommButton)
                        .addGap(134, 134, 134)
                        .addComponent(deleteCommButton))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29))
                        .addGap(72, 72, 72)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(partnerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(teamMemberCommTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(projectCommTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(199, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(projectCommTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(teamMemberCommTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel28))
                    .addComponent(partnerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCommButton)
                    .addComponent(deleteCommButton)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(199, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(174, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Communication", jPanel8);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(51, 51, 255)));

        lblChooseProject.setText("Project Name");

        comboBoxProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxProjectActionPerformed(evt);
            }
        });

        textField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnOpen.setText("Open");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(32, 32, 32)
                            .addComponent(btnOpen)
                            .addGap(51, 51, 51)
                            .addComponent(btnAdd)
                            .addGap(34, 34, 34)
                            .addComponent(btnDelete))
                        .addComponent(scrollPaneDoc))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(lblChooseProject, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(115, 115, 115)
                        .addComponent(comboBoxProject, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1196, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblChooseProject))
                .addGap(92, 92, 92)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnOpen)
                        .addComponent(btnAdd)
                        .addComponent(btnDelete)))
                .addGap(54, 54, 54)
                .addComponent(scrollPaneDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(402, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Document", jPanel10);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(51, 51, 255)));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 954, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1099, Short.MAX_VALUE)
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        jLabel30.setText("Search By");

        jLabel31.setText("Choose Project");

        jLabel32.setText("Choose User");

        comboBoxSearch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Project", "User" }));
        comboBoxSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSearchActionPerformed(evt);
            }
        });

        comboBoxSearchProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSearchProjectActionPerformed(evt);
            }
        });

        comboBoxUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32))
                        .addGap(191, 191, 191)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxSearchProject, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxUser, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(226, 226, 226)
                        .addComponent(comboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(479, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(comboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(comboBoxSearchProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(comboBoxUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(570, 570, 570))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(scrollSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82))
        );

        jTabbedPane1.addTab("Search", jPanel17);

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(255, 0, 51)));
        jPanel22.setForeground(new java.awt.Color(255, 0, 51));

        jLabel33.setIcon(new javax.swing.ImageIcon("/Users/Sowmya/Desktop/Screen Shot 2014-12-05 at 12.32.42 AM.png")); // NOI18N

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jLabel33)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 2066, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        File file = new File("C:/");

        int Checker;

        chooser.setCurrentDirectory(file);

        chooser.setDialogTitle("Choose file");

        Checker = chooser.showOpenDialog(null);

        if(Checker == JFileChooser.APPROVE_OPTION) {

            documentPath = chooser.getSelectedFile().toString();
            documentName = chooser.getSelectedFile().getName();

            textField.setText(documentPath);
        }
        else {
            JOptionPane.showConfirmDialog(null, "You clicked cancel :(", "Cancel Box", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if(docTable.getRowCount()==0) {

            JOptionPane.showMessageDialog(null, "List is empty", "Warning",
                JOptionPane.WARNING_MESSAGE);
        }
        else if(docTable.getSelectedRow() == -1) {

            JOptionPane.showMessageDialog(null, "Select onr or more rows first", "Warning",
                JOptionPane.WARNING_MESSAGE);
        }
        else {
            DefaultTableModel model = (DefaultTableModel) docTable.getModel();

            int row = docTable.getSelectedRow();

            String projectName = model.getValueAt(row, 0).toString();
            String documentName = model.getValueAt(row, 1).toString();
            String documentPath = model.getValueAt(row, 2).toString();
            int documentId = 0;

            model.removeRow(row);

            for(int i=0;i<documentList.size();i++){

                try {
                    if(projectName.equals(documentList.get(i).getColumnData(1).toString())
                        && documentName.equals(documentList.get(i).getColumnData(2).toString())
                        && documentPath.equals(documentList.get(i).getColumnData(3).toString()) ) {

                        documentId = ((Integer)(documentList.get(i).getColumnData(0))).intValue();
                        break;
                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } // for
            documentController.removeDocument(documentId);
        } // else
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel) docTable.getModel();
        dtm.addRow(new Object[] {comboBoxProject.getSelectedItem(),documentName,documentPath});

        documentController.addDocument(comboBoxProject.getSelectedItem(),documentName,documentPath);
    }//GEN-LAST:event_btnAddActionPerformed

    private void textFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldActionPerformed

    private void comboBoxProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxProjectActionPerformed
        // TODO add your handling code here:
       
        String projectName = comboBoxProject.getSelectedItem().toString();

        docTable = new JTable(new DefaultTableModel(columnNames,0));

        DefaultTableModel dtm = (DefaultTableModel) docTable.getModel();

        for(int i=0; i<documentList.size();i++) {

            try {
                if( projectName.equals(documentList.get(i).getColumnData(1).toString()) ) {

                    dtm.addRow(new Object[] {documentList.get(i).getColumnData(1),
                        documentList.get(i).getColumnData(2),
                        documentList.get(i).getColumnData(3)});
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        }
    }//GEN-LAST:event_comboBoxProjectActionPerformed

    private void deleteCommButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCommButtonActionPerformed
        // TODO add your handling code here:
        commTableController.deleteRow();
    }//GEN-LAST:event_deleteCommButtonActionPerformed

    private void addCommButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCommButtonActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String date = sdf.format(new Date());

        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss");
        String time = sdf1.format(new Date());

        String[] array = new String[jtable1.getColumnCount()];
        //array[0] = projectCombo.getSelectedItem().toString();
        array[0] = teamMemberCommTextField.getText();
        array[1] = partnerTextField.getText();
        array[2] = date;
        array[3] = time;
        array[4] = messageTextArea.getText();

        commTableController.addRow( array);
    }//GEN-LAST:event_addCommButtonActionPerformed

    private void deleteBudgetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBudgetButtonActionPerformed
        // TODO add your handling code here:
        budgetTableController.deleteRow();
    }//GEN-LAST:event_deleteBudgetButtonActionPerformed

    private void updateBudgetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBudgetButtonActionPerformed
        // TODO add your handling code here:
        String[] array = new String[budgetTable.getColumnCount()];
        //array[0] = taskIDTextField.getText();
        // array[1] = teamMemberTextField.getText();
        array[0] = serviceNameTextField.getText();
        array[1] = serviceDescTextField.getText();
        array[2] = estimatedCostTextField.getText();
        array[3] = actualCostTextField.getText();
        budgetTableController.updateRow(array);
    }//GEN-LAST:event_updateBudgetButtonActionPerformed

    private void addBudgetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBudgetButtonActionPerformed
        // TODO add your handling code here:
        String[] array = new String[budgetTable.getColumnCount()];
        //array[0] = projectCombo.getSelectedItem().toString();
        //array[1] = teamMemberCombo.getSelectedItem().toString();
        array[0] = serviceNameTextField.getText();
        array[1] = serviceDescTextField.getText();
        array[2] = estimatedCostTextField.getText();
        array[3] = actualCostTextField.getText();

        budgetTableController.addRow( array);
    }//GEN-LAST:event_addBudgetButtonActionPerformed

    private void projectTaskTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectTaskTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_projectTaskTextFieldActionPerformed

    private void teamMemberTaskTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teamMemberTaskTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teamMemberTaskTextFieldActionPerformed

    private void endDateTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endDateTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_endDateTextField1ActionPerformed

    private void deleteTaskButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTaskButtonActionPerformed
        // TODO add your handling code here:
        taskTableController.deleteRow();
    }//GEN-LAST:event_deleteTaskButtonActionPerformed

    private void updateTaskButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateTaskButtonActionPerformed
        // TODO add your handling code here:

        String[] array = new String[jtable1.getColumnCount()];
        //array[0] = taskIDTextField.getText();
        array[0] = teamMemberTaskTextField.getText();
        array[1] = taskNameTextField.getText();
        array[2] = taskDescTextField.getText();
        array[3] = startDateTextField.getText();
        array[4] = endDateTextField.getText();
        array[5] = dependencyTextField.getText();

        taskTableController.updateRow(array);
    }//GEN-LAST:event_updateTaskButtonActionPerformed

    private void addTaskButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTaskButtonActionPerformed
        // TODO add your handling code here:
        String[] array = new String[jtable1.getColumnCount()];
        //array[0] = projectCombo.getSelectedItem().toString();
        array[0] = teamMemberTaskTextField.getText();
        array[1] = taskNameTextField.getText();
        array[2] = taskDescTextField.getText();
        array[3] = startDateTextField.getText();
        array[4] = endDateTextField.getText();
        array[5] = dependencyTextField.getText();

        taskTableController.addRow( array);
    }//GEN-LAST:event_addTaskButtonActionPerformed

    private void updateProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateProjectButtonActionPerformed
        // TODO add your handling code here:
        String[] array = new String[jtable1.getColumnCount()];
        //array[0] = projectIDTextField.getText();
        // int rowIndex = jtable1.getSelectedRow();
        array[0] = projectNameTextField.getText();
        array[1] = projectDescTextField.getText();
        array[2] = startDateTextField.getText();
        array[3] = endDateTextField.getText();
        array[4] = statusTextField.getText();
        array[5] = outcomeTextField.getText();

        projectTableController.updateRow( array);
    }//GEN-LAST:event_updateProjectButtonActionPerformed

    private void projectDescTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectDescTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_projectDescTextFieldActionPerformed

    private void userDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userDeleteButtonActionPerformed
        // TODO add your handling code here:
        userListTableController.deleteRow();
    }//GEN-LAST:event_userDeleteButtonActionPerformed

    private void userEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userEditButtonActionPerformed
        // TODO add your handling code here:

        String[] array = new String[jtable1.getColumnCount()];
        array[0] = firstNameTextField.getText();
        array[1] = lastNameTextField.getText();
        array[2] = emailAddressTextField.getText();
        array[3] = phoneNumberTextField.getText();
        array[4] = userNameTextField.getText();
        array[5] = passwordTextField.getText();
        array[6] = roleCombo.getSelectedItem().toString();

        userListTableController.updateRow(array);
    }//GEN-LAST:event_userEditButtonActionPerformed

    private void userAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userAddButtonActionPerformed
        // TODO add your handling code here:
        String[] array = new String[jtable1.getColumnCount()];
        array[0] = firstNameTextField.getText();
        array[1] = lastNameTextField.getText();
        array[2] = emailAddressTextField.getText();
        array[3] = phoneNumberTextField.getText();
        array[4] = userNameTextField.getText();
        array[5] = passwordTextField.getText();
        array[6] = roleCombo.getSelectedItem().toString();

        userListTableController.addRow( array);
    }//GEN-LAST:event_userAddButtonActionPerformed

    private void comboBoxSearchProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSearchProjectActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_comboBoxSearchProjectActionPerformed

    private void comboBoxSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSearchActionPerformed
        // TODO add your handling code here:

        if(comboBoxSearch.getSelectedItem().toString().equals("Project")) {
            comboBoxUser.setEnabled(false);
            comboBoxSearchProject.setEnabled(true);

        }
        else {
            comboBoxSearchProject.setEnabled(false);
            comboBoxUser.setEnabled(true);
        }
    }//GEN-LAST:event_comboBoxSearchActionPerformed

    private void comboBoxUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxUserActionPerformed
        // TODO add your handling code here:
        
                                String userName = comboBoxUser.getSelectedItem().toString();
				System.out.println(userName);
				searchTable = new JTable(new DefaultTableModel(new Object[] {"Task Name","Task Description"}, 0));
				
                                scrollSearch.setViewportView(searchTable);
                               // controller = new searchController();
                             
				List<Task> taskList = controller.getTaskList(userName);
				
				DefaultTableModel dtm = (DefaultTableModel)searchTable.getModel();
				
				for(int i=0; i<taskList.size();i++) {
					try {
						System.out.println(taskList.get(i).getColumnData(1) + " " + taskList.get(i).getColumnData(2));
						dtm.addRow(new Object[] {taskList.get(i).getColumnData(1),taskList.get(i).getColumnData(2)});
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
        
    }//GEN-LAST:event_comboBoxUserActionPerformed

    private void roleComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleComboActionPerformed
        // TODO add your handling code here:
        System.out.println("in role combo");
    }//GEN-LAST:event_roleComboActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WelcomeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WelcomeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WelcomeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WelcomeGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField actualCostTextField;
    private javax.swing.JButton addBudgetButton;
    private javax.swing.JButton addCommButton;
    private javax.swing.JButton addTaskButton;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnOpen;
    private javax.swing.JComboBox comboBoxProject;
    private javax.swing.JComboBox comboBoxSearch;
    private javax.swing.JComboBox comboBoxSearchProject;
    private javax.swing.JComboBox comboBoxUser;
    private javax.swing.JButton deleteBudgetButton;
    private javax.swing.JButton deleteCommButton;
    private javax.swing.JButton deleteTaskButton;
    private javax.swing.JTextField dependencyTextField;
    private javax.swing.JTextField emailAddressTextField;
    private javax.swing.JTextField endDateTextField;
    private javax.swing.JTextField endDateTextField1;
    private javax.swing.JTextField estimatedCostTextField;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JLabel lblChooseProject;
    private javax.swing.JTextArea messageTextArea;
    private javax.swing.JTextField outcomeTextField;
    private javax.swing.JTextField partnerTextField;
    private javax.swing.JTextField passwordTextField;
    private javax.swing.JTextField phoneNumberTextField;
    private javax.swing.JTextField projectBudgetTextField;
    private javax.swing.JTextField projectCommTextField;
    private javax.swing.JTextField projectDescTextField;
    private javax.swing.JTextField projectNameTextField;
    private javax.swing.JTextField projectTaskTextField;
    private javax.swing.JComboBox roleCombo;
    private javax.swing.JScrollPane scrollPaneDoc;
    private javax.swing.JScrollPane scrollSearch;
    private javax.swing.JTextField serviceDescTextField;
    private javax.swing.JTextField serviceNameTextField;
    private javax.swing.JTextField startDateTextField;
    private javax.swing.JTextField startDateTextField1;
    private javax.swing.JTextField statusTextField;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JTextField taskDescTextField;
    private javax.swing.JTextField taskNameTextField;
    private javax.swing.JTextField teamMemberCommTextField;
    private javax.swing.JTextField teamMemberTaskTextField;
    private javax.swing.JTextField textField;
    private javax.swing.JButton updateBudgetButton;
    private javax.swing.JButton updateProjectButton;
    private javax.swing.JButton updateTaskButton;
    private javax.swing.JButton userAddButton;
    private javax.swing.JButton userDeleteButton;
    private javax.swing.JButton userEditButton;
    private javax.swing.JTextField userNameTextField;
    // End of variables declaration//GEN-END:variables
}
