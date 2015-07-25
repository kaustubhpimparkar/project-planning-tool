/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectdemo;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sowmya
 */
public class WelcomeAdminGUI extends javax.swing.JFrame {

    /**
     * Creates new form WelcomeGUI
     */
    
    private JTable jtable1; // the table displayed on the GUI
    private ProjectTableController projectTableController;
    
    private JTable userTable; // the table displayed on the GUI
    private UserListTableController userListTableController;
    
    private JTable userProjectTable;
        private ListSelectionModel selectedRows; 
	
	private ParticipantTableController participantTableController; 

	Object[] columnNames = {"Project Name", "User Name","Role"};
	Object[][] data = {/*{"elizabeth"},{"saurabh"}*/};
	List<UserList> user;
	List<Project> projectList;
	List<UserList> usersList;
        
        private JTable searchTable;
    
    // private JTable jtable2; // the table displayed on the GUI
   // private UserListTableController userListTableController;
    
    public WelcomeAdminGUI() {
        initComponents();
     projectTableController = new ProjectTableController(this);
         addJTable();
         
         userListTableController = new UserListTableController(this);
         addUserTable();
         
        // userListTableController = new UserListTableController(this);
        // addJTable1();
         participantTableController = new ParticipantTableController();
		
		usersList= participantTableController.getUserList();
		projectList = participantTableController.getProjectList();
		data = participantTableController.getJTableEntries();
		
		List<String> participantList = new ArrayList<String>();
                JScrollPane scrollpane = new JScrollPane(userProjectTable);
                
                jPanel7.setLayout(new BorderLayout());
		jPanel7.add(scrollpane, BorderLayout.CENTER);
                
                //table = new JTable(participantTableController.getTableModel());
		userProjectTable = new JTable(new DefaultTableModel(data, columnNames));
                //System.out.println("After jtable");
		userProjectTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				ListSelectionModel selectedRows = (ListSelectionModel)e.getSource();		
				setSelectedRow(selectedRows);
			}

		});
                scrollpane.setViewportView(userProjectTable);
                for(int i=0; i < usersList.size(); i++) {
			
			try {
				comboBoxUsers.addItem(usersList.get(i).getColumnData(1));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
                for(int i=0; i < projectList.size(); i++) {
			
			try {
				comboBoxProjects.addItem(projectList.get(i).getColumnData(1));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
                
                for(int i=0; i < usersList.size(); i++) {
			
			try {
				comboBoxUser.addItem(usersList.get(i).getColumnData(1));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
                
                for(int i=0; i < projectList.size(); i++) {
			
			try {
				comboBoxProject.addItem(projectList.get(i).getColumnData(1));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
                
                searchController controller;
                controller = new searchController();
		
		usersList = controller.getUserList();
		projectList = controller.getProjectList();
                
                JScrollPane scrollSearch = new JScrollPane(searchTable);
                jPanel9.setLayout(new BorderLayout());
		jPanel9.add(scrollSearch, BorderLayout.CENTER);
                searchTable = new JTable();
		scrollSearch.setViewportView(searchTable);
                
                
     
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
     
     public void setSelectedRow(ListSelectionModel selectedRows) {
		this.selectedRows = selectedRows;
	}	
     

     	

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        userPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        tablePanel = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        firstNameTextField = new javax.swing.JTextField();
        lastNameTextField = new javax.swing.JTextField();
        emailAddressTextField = new javax.swing.JTextField();
        phoneNumberTextField = new javax.swing.JTextField();
        userNameTextField = new javax.swing.JTextField();
        passwordTextField = new javax.swing.JTextField();
        roleCombo = new javax.swing.JComboBox();
        userDeleteButton = new javax.swing.JButton();
        userEditButton = new javax.swing.JButton();
        userAddButton = new javax.swing.JButton();
        projectPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        projectNameTextField = new javax.swing.JTextField();
        projectDescTextField = new javax.swing.JTextField();
        startDateTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        endDateTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        statusTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        outcomeTextField = new javax.swing.JTextField();
        addProjectButton = new javax.swing.JButton();
        updateProjectButton = new javax.swing.JButton();
        deleteProjectButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        participantPanel = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        comboBoxProjects = new javax.swing.JComboBox();
        comboBoxUsers = new javax.swing.JComboBox();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        comboBoxProject = new javax.swing.JComboBox();
        comboBoxSearch = new javax.swing.JComboBox();
        comboBoxUser = new javax.swing.JComboBox();
        jPanel9 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));

        jTabbedPane1.setBackground(new java.awt.Color(51, 51, 255));
        jTabbedPane1.setBorder(new javax.swing.border.MatteBorder(null));

        userPanel.setBackground(new java.awt.Color(255, 255, 255));
        userPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(51, 51, 255)));
        userPanel.setForeground(new java.awt.Color(240, 240, 240));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 902, Short.MAX_VALUE)
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 318, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

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

        userDeleteButton.setText("Delete");
        userDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userDeleteButtonActionPerformed(evt);
            }
        });

        userEditButton.setText("Edit");
        userEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userEditButtonActionPerformed(evt);
            }
        });

        userAddButton.setText("Add");
        userAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userAddButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addGap(0, 97, Short.MAX_VALUE)
                                .addComponent(userAddButton)
                                .addGap(66, 66, 66)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(userNameTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                .addComponent(phoneNumberTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(emailAddressTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lastNameTextField, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(roleCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(userEditButton)
                                .addGap(58, 58, 58)
                                .addComponent(userDeleteButton)))))
                .addGap(69, 69, 69))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(emailAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(phoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(userNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(roleCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userEditButton)
                    .addComponent(userDeleteButton)
                    .addComponent(userAddButton))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(373, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout userPanelLayout = new javax.swing.GroupLayout(userPanel);
        userPanel.setLayout(userPanelLayout);
        userPanelLayout.setHorizontalGroup(
            userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPanelLayout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(268, Short.MAX_VALUE))
        );
        userPanelLayout.setVerticalGroup(
            userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userPanelLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("User", userPanel);

        projectPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(51, 51, 255)));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setText("Project Name :");

        jLabel13.setText("Project Decription :");

        jLabel12.setText("Start Date :");

        projectDescTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectDescTextFieldActionPerformed(evt);
            }
        });

        jLabel11.setText("End Date :");

        jLabel10.setText("Status :");

        jLabel7.setText("Outcome :");

        outcomeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outcomeTextFieldActionPerformed(evt);
            }
        });

        addProjectButton.setText("Add");
        addProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProjectButtonActionPerformed(evt);
            }
        });

        updateProjectButton.setText("Update");
        updateProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProjectButtonActionPerformed(evt);
            }
        });

        deleteProjectButton.setText("Delete");
        deleteProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProjectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(117, 117, 117)
                        .addComponent(outcomeTextField))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(135, 135, 135)
                        .addComponent(statusTextField))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(118, 118, 118)
                        .addComponent(endDateTextField))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(112, 112, 112)
                        .addComponent(startDateTextField))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(91, 91, 91)
                        .addComponent(projectNameTextField))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(61, 61, 61)
                        .addComponent(projectDescTextField)))
                .addGap(54, 54, 54))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(addProjectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(updateProjectButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteProjectButton)
                .addContainerGap(171, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(projectNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(projectDescTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(startDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(endDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(statusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(outcomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addProjectButton)
                    .addComponent(updateProjectButton)
                    .addComponent(deleteProjectButton))
                .addGap(56, 56, 56))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 593, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 259, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(872, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );

        javax.swing.GroupLayout projectPanelLayout = new javax.swing.GroupLayout(projectPanel);
        projectPanel.setLayout(projectPanelLayout);
        projectPanelLayout.setHorizontalGroup(
            projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, projectPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        projectPanelLayout.setVerticalGroup(
            projectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(projectPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 785, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Project", projectPanel);

        participantPanel.setBackground(new java.awt.Color(255, 255, 255));
        participantPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(51, 51, 255)));

        jLabel21.setText("Project :");

        jLabel22.setText("User :");

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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 777, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 319, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout participantPanelLayout = new javax.swing.GroupLayout(participantPanel);
        participantPanel.setLayout(participantPanelLayout);
        participantPanelLayout.setHorizontalGroup(
            participantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(participantPanelLayout.createSequentialGroup()
                .addGroup(participantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(participantPanelLayout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addGroup(participantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(btnAdd))
                        .addGap(43, 43, 43)
                        .addGroup(participantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDelete)
                            .addGroup(participantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(comboBoxProjects, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comboBoxUsers, 0, 110, Short.MAX_VALUE))))
                    .addGroup(participantPanelLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(751, Short.MAX_VALUE))
        );
        participantPanelLayout.setVerticalGroup(
            participantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(participantPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(participantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(comboBoxProjects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(participantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(comboBoxUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(participantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnDelete))
                .addGap(37, 37, 37)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(204, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Participant", participantPanel);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(51, 51, 255)));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setText("Search By :");

        jLabel16.setText("Choose Project :");

        jLabel17.setText("Choose User :");

        comboBoxProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxProjectActionPerformed(evt);
            }
        });

        comboBoxSearch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Project", "Team Member" }));
        comboBoxSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSearchActionPerformed(evt);
            }
        });

        comboBoxUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addGap(56, 56, 56)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboBoxSearch, 0, 139, Short.MAX_VALUE)
                    .addComponent(comboBoxProject, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboBoxUser, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(comboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(comboBoxProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(comboBoxUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 216, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(1036, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(283, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Search", jPanel2);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(255, 51, 51)));

        jLabel1.setBackground(new java.awt.Color(255, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon("/Users/Sowmya/Desktop/Screen Shot 2014-12-05 at 12.32.42 AM.png")); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1)))
                .addGap(41, 41, 41))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 843, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(339, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteProjectButtonActionPerformed
        // TODO add your handling code here:
        projectTableController.deleteRow();
    }//GEN-LAST:event_deleteProjectButtonActionPerformed

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

    private void addProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProjectButtonActionPerformed
        // TODO add your handling code here:
        String[] array = new String[jtable1.getColumnCount()];
        //array[0] = projectIDTextField.getText();
        array[0] = projectNameTextField.getText();
        array[1] = projectDescTextField.getText();
        array[2] = startDateTextField.getText();
        array[3] = endDateTextField.getText();
        array[4] = statusTextField.getText();
        array[5] = outcomeTextField.getText();

        projectTableController.addRow( array);
    }//GEN-LAST:event_addProjectButtonActionPerformed

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

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        
				
				Object roleName = null;
				Object projectId = null;
				Object userId = null;
				
				for(int i=0;i<usersList.size();i++) {
					try {
						if(comboBoxUsers.getSelectedItem().toString().equals(usersList.get(i).getColumnData(1).toString())) {
							
							userId = usersList.get(i).getColumnData(0);
							roleName = usersList.get(i).getColumnData(4);
		//					System.out.println(((Integer)userId).intValue());
							break;
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				for(int i=0;i<projectList.size();i++) {
					try {
						if(comboBoxProjects.getSelectedItem().toString().equals(projectList.get(i).getColumnData(1).toString())) {
							
							projectId = projectList.get(i).getColumnData(0);
			//				System.out.println(((Integer)projectId).intValue());
							break;
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				DefaultTableModel dtm = (DefaultTableModel) userProjectTable.getModel();
				dtm.addRow(new Object[] {comboBoxProjects.getSelectedItem(),comboBoxUsers.getSelectedItem(),roleName});
				
				//participantTableController.setController();
				participantTableController.addParticipantList(userId,projectId);
			
    }//GEN-LAST:event_btnAddActionPerformed

    private void outcomeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outcomeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_outcomeTextFieldActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        
				// TODO Auto-generated method stub
				
				if(userProjectTable.getRowCount()==0) {
					
					JOptionPane.showMessageDialog(null, "List is empty", "Warning",
					        JOptionPane.WARNING_MESSAGE);
				}
				else if(userProjectTable.getSelectedRow() == -1) {
					
					JOptionPane.showMessageDialog(null, "Select onr or more rows first", "Warning",
					        JOptionPane.WARNING_MESSAGE);
				}
				else {
					
					DefaultTableModel model = (DefaultTableModel) userProjectTable.getModel();
					
				int row = userProjectTable.getSelectedRow();
		
				Object userName = null;
				Object projectName = null;
				Object userId = null;
				Object projectId = null;
				//Object roleName = null;
					
				projectName = model.getValueAt(row, 0);
				userName = model.getValueAt(row, 1);
				//roleName = model.getValueAt(row, 2);
				//System.out.println(userName.toString());
				//System.out.println(projectName.toString());
				model.removeRow(row);
					
				for(int i=0;i<usersList.size();i++) {
					try {
						if(userName.toString().equals(usersList.get(i).getColumnData(1).toString())) {
							
							userId = usersList.get(i).getColumnData(0);
							//System.out.println(((Integer)userId).intValue());
							break;
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				for(int i=0;i<projectList.size();i++) {
					try {
						if(projectName.toString().equals(projectList.get(i).getColumnData(1).toString())) {
							
							projectId = projectList.get(i).getColumnData(0);
						//		System.out.println(((Integer)projectId).intValue());
							break;
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				participantTableController.removeParticipants(userId,projectId);
				}	
			
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void comboBoxSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSearchActionPerformed
        // TODO add your handling code here:
        if(comboBoxSearch.getSelectedItem().toString().equals("Project")) {
					comboBoxUser.setEnabled(false);
					comboBoxProject.setEnabled(true);
					
				}	
				else {
					comboBoxProject.setEnabled(false);
					comboBoxUser.setEnabled(true);
				}
        
    }//GEN-LAST:event_comboBoxSearchActionPerformed

    private void comboBoxProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxProjectActionPerformed
        // TODO add your handling code here:
        

    }//GEN-LAST:event_comboBoxProjectActionPerformed

    private void comboBoxUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxUserActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_comboBoxUserActionPerformed

    private void roleComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleComboActionPerformed
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(WelcomeAdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WelcomeAdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WelcomeAdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomeAdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WelcomeAdminGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProjectButton;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JComboBox comboBoxProject;
    private javax.swing.JComboBox comboBoxProjects;
    private javax.swing.JComboBox comboBoxSearch;
    private javax.swing.JComboBox comboBoxUser;
    private javax.swing.JComboBox comboBoxUsers;
    private javax.swing.JButton deleteProjectButton;
    private javax.swing.JTextField emailAddressTextField;
    private javax.swing.JTextField endDateTextField;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JTextField outcomeTextField;
    private javax.swing.JPanel participantPanel;
    private javax.swing.JTextField passwordTextField;
    private javax.swing.JTextField phoneNumberTextField;
    private javax.swing.JTextField projectDescTextField;
    private javax.swing.JTextField projectNameTextField;
    private javax.swing.JPanel projectPanel;
    private javax.swing.JComboBox roleCombo;
    private javax.swing.JTextField startDateTextField;
    private javax.swing.JTextField statusTextField;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JButton updateProjectButton;
    private javax.swing.JButton userAddButton;
    private javax.swing.JButton userDeleteButton;
    private javax.swing.JButton userEditButton;
    private javax.swing.JTextField userNameTextField;
    private javax.swing.JPanel userPanel;
    // End of variables declaration//GEN-END:variables
}
