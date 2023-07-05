import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class MainFrame extends JFrame {
    private JDesktopPane desktopPane;
    private JTable table;
    private JTable tableS;
    private JTable tableH;
    private JTable tableP;
    private JTextField idField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField txthobby;
    private JTextField txthobbydes;
    private JTextField txthobbyname;
    private JTextField txtskill;
    private JTextField txtskillname;
    private JTextField txtskilldes;
    private JTextField txtproj;
    private JTextField txtprojdes;
    private JTextField txtprojname;
    private JTextField txtstat;

    public MainFrame() {
        setTitle("Main Frame");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktopPane = new JDesktopPane();
        JMenuBar menuBar = new JMenuBar();

       
        JMenu homeMenu = new JMenu("Home");
        JMenu studentMenu = new JMenu("Student");
        JMenu hobbyMenu = new JMenu("Hobby");
        JMenu skillMenu = new JMenu("Skill");
        JMenu projectMenu = new JMenu("Project");

        
        JMenuItem homeItem = new JMenuItem("Home");
        JMenuItem studentItem = new JMenuItem("Student");
        JMenuItem hobbyItem = new JMenuItem("Hobby");
        JMenuItem skillItem = new JMenuItem("Skill");
        JMenuItem projectItem = new JMenuItem("Project");
        

        
        homeItem .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	desktopPane.removeAll();
                try {
					showInternalFrame("Home");
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
            }
        });

        studentItem .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	desktopPane.removeAll();
            	try {
					showInternalFrame1("Student");
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
            }
        });

        hobbyItem .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	desktopPane.removeAll();
                try {
					showInternalFrame2("Hobby");
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
            }
        });

        skillItem .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	desktopPane.removeAll();
               try {
				showInternalFrame3("Skill");
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
            }
        });

        projectItem .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	desktopPane.removeAll();
                try {
					showInternalFrame4("Project");
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
            }
        });

       
        

       
        menuBar.add(homeMenu);
        menuBar.add(studentMenu);
        menuBar.add(hobbyMenu);
        menuBar.add(skillMenu);
        menuBar.add(projectMenu);
        homeMenu.add(homeItem);
        studentMenu.add(studentItem);
        hobbyMenu.add(hobbyItem);
        skillMenu.add(skillItem);
        projectMenu.add(projectItem);


       
        setJMenuBar(menuBar);

        
        
        add(desktopPane, BorderLayout.CENTER);

        
        setVisible(true);
        try {
        	desktopPane.removeAll();
			showInternalFrame("Home");
		} catch (SQLException e) {

			e.printStackTrace();
		}
        
    }

    private void showInternalFrame(String title) throws SQLException {
    	
    	JInternalFrame internalFrame = new JInternalFrame(title, true, true, true, true);
        internalFrame.setSize(800, 600);
        internalFrame.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 10, 5, 5);
        
        JLabel T = new JLabel("HOBBIES DATABASE");
        
        
        constraints.gridx = 0;
        constraints.gridy = 1;
        internalFrame.add(T, constraints);
        
        
        
        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);


        Dimension desktopSize = desktopPane.getSize();
        Dimension frameSize = internalFrame.getSize();
        internalFrame.setLocation((desktopSize.width - frameSize.width) / 2, (desktopSize.height - frameSize.height) / 2);
    
    	
    
    }
    Connection con1;
    PreparedStatement insert;
    private void table1() throws SQLException
    {
    	DefaultTableModel defaultModel = null;
        AbstractTableModel abstractModel = null;

        TableModel model = table.getModel();
        if (model instanceof DefaultTableModel) {
            defaultModel = (DefaultTableModel) model;
        } else if (model instanceof AbstractTableModel) {
            abstractModel = (AbstractTableModel) model;
        } else {
            throw new UnsupportedOperationException("Table model not supported");
        }

        if (defaultModel != null) {
            defaultModel.setRowCount(0);
        } 
    	
    	try {
    		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
	        insert = con1.prepareStatement("select * from student");
	        ResultSet rs = insert.executeQuery();
	        ResultSetMetaData Rss = rs.getMetaData();
	        
	        while(rs.next()) {
	    	String id = rs.getString("student_id");
	        String name = rs.getString("student_name");
	        String email = rs.getString("student_email");
	        String phone = rs.getString("student_phone");

	       
	        TableModel model1 = table.getModel();
	        

	        
	        Object[] rowData = {id, name, email, phone};
	        
	    	if (model1 instanceof DefaultTableModel) {
	            
	            ((DefaultTableModel) model1).addRow(rowData);
	        } else {
	            
	            Object[][] data = new Object[model1.getRowCount() + 1][model1.getColumnCount()];
	            for (int row = 0; row < model1.getRowCount(); row++) {
	                for (int col = 0; col < model1.getColumnCount(); col++) {
	                    data[row][col] = model1.getValueAt(row, col);
	                }
	            }
	            data[model.getRowCount()] = rowData;

	            
	            TableModel newModel = new DefaultTableModel(data, getColumnNames(model));

	            
	            table.setModel(newModel);
	        }
	        }

	        
	        idField.setText("");
	        nameField.setText("");
	        emailField.setText("");
	        phoneField.setText("");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
        
    	
    }
    int selectedRow1;
    String ef = "";
    private void showInternalFrame1(String title) throws SQLException {
    	
        JInternalFrame internalFrame = new JInternalFrame(title, true, true, true, true);
        internalFrame.setSize(800, 600);
        internalFrame.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 10, 5, 5);

        JLabel idLabel = new JLabel("Student ID:");
        idField = new JTextField(20);
        JLabel nameLabel = new JLabel("Student Name:");
        nameField = new JTextField(20);
        JLabel emailLabel = new JLabel("Student Email:");
        emailField = new JTextField(20);
        JLabel phoneLabel = new JLabel("Student Phone:");
        phoneField = new JTextField(20);

        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        
        

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
                    int ind = Integer.parseInt(ef);
                    String name = nameField.getText();
                    String id = idField.getText();
                    String email = emailField.getText();
                    String phone = phoneField.getText();
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                    insert = con1.prepareStatement("update student set student_name='"+name+"',student_email='"+email+"',student_phone='"+phone+"' where student_id='"+ind+"' ");

                     insert.executeUpdate();
                    
                     JOptionPane.showMessageDialog(null, "Record Update","Message", 
     						JOptionPane.INFORMATION_MESSAGE);
                    table1();
                    
                    idField.setText("");
                    nameField.setText("");
                    emailField.setText("");
                    phoneField.setText("");
                    idField.requestFocus();
                    
                } 
                catch (ClassNotFoundException | SQLException ex) {
                	JOptionPane.showMessageDialog(null, ex,"WARNING", 
    						JOptionPane.WARNING_MESSAGE);
                }
               
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
                    
                    
                    int ele = Integer.parseInt(idField.getText());
                    if(selectedRow1==-1)
                    {
                        int dialogResult = JOptionPane.showConfirmDialog(null,"Do you what to Delete the Record","Warning",JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.YES_NO_OPTION)
                    {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                        insert = con1.prepareStatement("delete from student where student_id='"+ele+"' ");
                        insert.executeUpdate();
                    
                        JOptionPane.showMessageDialog(null, "Record Deleted","Message", 
        						JOptionPane.INFORMATION_MESSAGE);
                        table1();
                    
                        idField.setText("");
                        nameField.setText("");
                        emailField.setText("");
                        phoneField.setText("");
                        idField.requestFocus();
                        
                    
                    }
                    }
                    else{
                        int ind = Integer.parseInt(table.getValueAt(selectedRow1,0).toString());
                    int dialogResult = JOptionPane.showConfirmDialog(null,"Do you what to Delete the Record","Warning",JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.YES_NO_OPTION)
                    {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                        insert = con1.prepareStatement("delete from student where student_id='"+ind+"' ");
                        insert.executeUpdate();
                    
                        JOptionPane.showMessageDialog(null, "Record Deleted","HELP", 
        						JOptionPane.INFORMATION_MESSAGE);
                        table1();
                    
                        idField.setText("");
                        nameField.setText("");
                        emailField.setText("");
                        phoneField.setText("");
                        idField.requestFocus();
                    
                    }
                    }
                    
                    
                    
                     
                    
                } 
                catch (ClassNotFoundException | SQLException ex) {
                	JOptionPane.showMessageDialog(null, ex,"WARNING", 
    						JOptionPane.WARNING_MESSAGE);
                }
                
            }
        });

        
        String[] columnNames = {"Student ID", "Student Name", "Student Email", "Student Phone"};
        Object[][] data = {
               
        };
        
            
            
        
        
        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        

        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedRow1 = table.getSelectedRow();
                    if (selectedRow1 >= 0) {
                        Object selectedId = table.getValueAt(selectedRow1, 0);
                        Object selectedName = table.getValueAt(selectedRow1, 1);
                        Object selectedEmail = table.getValueAt(selectedRow1, 2);
                        Object selectedPhone = table.getValueAt(selectedRow1, 3);

                        idField.setText(String.valueOf(selectedId));
                        ef=String.valueOf(selectedId);
                        nameField.setText(String.valueOf(selectedName));
                        emailField.setText(String.valueOf(selectedEmail));
                        phoneField.setText(String.valueOf(selectedPhone));
                    }
                }
            }
        });

        table1();
        constraints.gridx = 0;
        constraints.gridy = 0;
        internalFrame.add(idLabel, constraints);

        constraints.gridy = 1;
        internalFrame.add(nameLabel, constraints);

        constraints.gridy = 2;
        internalFrame.add(emailLabel, constraints);

        constraints.gridy = 3;
        internalFrame.add(phoneLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        internalFrame.add(idField, constraints);

        constraints.gridy = 1;
        internalFrame.add(nameField, constraints);

        constraints.gridy = 2;
        internalFrame.add(emailField, constraints);

        constraints.gridy = 3;
        internalFrame.add(phoneField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        internalFrame.add(addButton, constraints);

        constraints.gridx = 1;
        internalFrame.add(editButton, constraints);

        constraints.gridx = 2;
        internalFrame.add(deleteButton, constraints);

        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.gridheight = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        internalFrame.add(scrollPane, constraints);
    
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();

                
                TableModel model = table.getModel();

                
                Object[] rowData = {id, name, email, phone};
                
                
                
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                    insert = con1.prepareStatement("insert into student(student_id,student_name,student_email,student_phone) values('"+id+"','"+name+"','"+email+"','"+phone+"')");

                    insert.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Record Added","HELP", 
    						JOptionPane.INFORMATION_MESSAGE);
                    
                    table1();
                    idField.setText("");
                    nameField.setText("");
                    emailField.setText("");
                    phoneField.setText("");
                    idField.requestFocus();
                    
                } 
                catch (ClassNotFoundException | SQLException ex) {
                	JOptionPane.showMessageDialog(null, ex,"WARNING", 
    						JOptionPane.WARNING_MESSAGE);
                }

               
                

                
                idField.setText("");
                nameField.setText("");
                emailField.setText("");
                phoneField.setText("");
            }
        });

        
        



        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);


        Dimension desktopSize = desktopPane.getSize();
        Dimension frameSize = internalFrame.getSize();
        internalFrame.setLocation((desktopSize.width - frameSize.width) / 2, (desktopSize.height - frameSize.height) / 2);
    }
    private void table2() throws SQLException
    {
    	DefaultTableModel defaultModel = null;
        AbstractTableModel abstractModel = null;

        TableModel model = table.getModel();
        if (model instanceof DefaultTableModel) {
            defaultModel = (DefaultTableModel) model;
        } else if (model instanceof AbstractTableModel) {
            abstractModel = (AbstractTableModel) model;
        } else {
            throw new UnsupportedOperationException("Table model not supported");
        }

        if (defaultModel != null) {
            defaultModel.setRowCount(0);
        } 
    	
    	try {
    		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
	        insert = con1.prepareStatement("select * from hobby");
	        ResultSet rs = insert.executeQuery();
	        ResultSetMetaData Rss = rs.getMetaData();
	        
	        while(rs.next()) {
	    	String id = rs.getString("hobby_id");
	        String name = rs.getString("hobby_name");
	        String email = rs.getString("hobby_description");
	        

	       
	        TableModel model1 = table.getModel();
	        

	        
	        Object[] rowData = {id, name, email};
	        
	    	if (model1 instanceof DefaultTableModel) {
	            
	            ((DefaultTableModel) model1).addRow(rowData);
	        } else {
	            
	            Object[][] data = new Object[model1.getRowCount() + 1][model1.getColumnCount()];
	            for (int row = 0; row < model1.getRowCount(); row++) {
	                for (int col = 0; col < model1.getColumnCount(); col++) {
	                    data[row][col] = model1.getValueAt(row, col);
	                }
	            }
	            data[model.getRowCount()] = rowData;

	            
	            TableModel newModel = new DefaultTableModel(data, getColumnNames(model));

	            
	            table.setModel(newModel);
	        }
	        }

	        
	        txthobby.setText("");
	        txthobbyname.setText("");
	        txthobbydes.setText("");
	       
		} catch (ClassNotFoundException e) {
			
			JOptionPane.showMessageDialog(null, e,"WARNING", 
					JOptionPane.WARNING_MESSAGE);
		}
        
    	
    }
    int selectedRow2;
    String ef1 = "";
    private void showInternalFrame2(String title) throws SQLException {
        JInternalFrame internalFrame = new JInternalFrame(title, true, true, true, true);
        internalFrame.setSize(800, 600);
        internalFrame.setLayout(new GridBagLayout()); 

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 10, 5, 5);

        JLabel idLabel = new JLabel("Hobby ID:");
        txthobby = new JTextField(20);
        JLabel nameLabel = new JLabel("Hobby Name:");
        txthobbyname = new JTextField(20);
        JLabel emailLabel = new JLabel("Hobby Description:");
        txthobbydes = new JTextField(20);
        

        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String hob = txthobby.getText();
                String name = txthobbyname.getText();
                String des = txthobbydes.getText();

                TableModel model = table.getModel();

                
                Object[] rowData = {hob,name,des};
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                    
                    insert = con1.prepareStatement("insert into hobby(hobby_id,hobby_name,hobby_description) values('"+hob+"','"+name+"','"+des+"' )");

                    insert.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Record Added","HELP", 
    						JOptionPane.INFORMATION_MESSAGE);
                    table2();
                    
                    txthobby.setText("");
                    txthobbyname.setText("");
                    txthobbydes.setText("");
                    
                    txthobby.requestFocus();
                    
                } 
                catch (ClassNotFoundException | SQLException ex) {
                	JOptionPane.showMessageDialog(null, ex,"WARNING", 
    						JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            	int ind = Integer.parseInt(ef1);
                String name = txthobbyname.getText();
                String hob = txthobby.getText();
                String des = txthobbydes.getText();
                
                Class.forName("oracle.jdbc.driver.OracleDriver");
                con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                insert = con1.prepareStatement("update hobby set hobby_name='"+name+"',hobby_description='"+des+"' where hobby_id='"+ind+"' ");

                insert.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Record Update","Message", 
						JOptionPane.INFORMATION_MESSAGE);
                table2();
                
                txthobby.setText("");
                txthobbyname.setText("");
                txthobbydes.setText("");
                
                txthobby.requestFocus();
                
            } 
            catch (ClassNotFoundException | SQLException ex) {
            	JOptionPane.showMessageDialog(null, ex,"WARNING", 
						JOptionPane.WARNING_MESSAGE);
            }
                
        }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
                    
                    
                    int ele = Integer.parseInt(txthobby.getText());
                    if(selectedRow2==-1)
                    {
                        int dialogResult = JOptionPane.showConfirmDialog(null,"Do you what to Delete the Record","Warning",JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.YES_NO_OPTION)
                    {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                        insert = con1.prepareStatement("delete from hobby where hobby_id='"+ele+"' ");
                        insert.executeUpdate();
                    
                        JOptionPane.showMessageDialog(null, "Record Deleted","Message", 
        						JOptionPane.INFORMATION_MESSAGE);
                        table2();
                    
                        
                        txthobby.setText("");
                        txthobbydes.setText("");
                        txthobbyname.setText("");
                        txthobby.requestFocus();
                        
                    
                    }
                    }
                    else{
                        int ind = Integer.parseInt(table.getValueAt(selectedRow2,0).toString());
                    int dialogResult = JOptionPane.showConfirmDialog(null,"Do you what to Delete the Record","Warning",JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.YES_NO_OPTION)
                    {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                        insert = con1.prepareStatement("delete from hobby where hobby_id='"+ind+"' ");
                        insert.executeUpdate();
                    
                        JOptionPane.showMessageDialog(null, "Record Deleted","Message", 
        						JOptionPane.INFORMATION_MESSAGE);
                        table2();
                    
                        txthobby.setText("");
                        txthobbyname.setText("");
                        txthobbydes.setText("");
                        
                        txthobby.requestFocus();
                    
                    }
                    }
                    
                    
                    
                     
                    
                } 
                catch (ClassNotFoundException | SQLException ex) {
                	JOptionPane.showMessageDialog(null, ex,"WARNING", 
    						JOptionPane.WARNING_MESSAGE);
                }
                
            }
        });

        
        String[] columnNames = {"Hobby ID", "Hobby Name", "Hobby description"};
        Object[][] data = {
                
        };
        
            
            
        
        
        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        

        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedRow2 = table.getSelectedRow();
                    if (selectedRow2 >= 0) {
                        Object selectedId = table.getValueAt(selectedRow2, 0);
                        Object selectedName = table.getValueAt(selectedRow2, 1);
                        Object selectedEmail = table.getValueAt(selectedRow2, 2);
                        

                        txthobby.setText(String.valueOf(selectedId));
                        ef1= String.valueOf(selectedId);
                        txthobbyname.setText(String.valueOf(selectedName));
                        txthobbydes.setText(String.valueOf(selectedEmail));
                        
                    }
                }
            }
        });

        table2();
        constraints.gridx = 0;
        constraints.gridy = 0;
        internalFrame.add(idLabel, constraints);

        constraints.gridy = 1;
        internalFrame.add(nameLabel, constraints);

        constraints.gridy = 2;
        internalFrame.add(emailLabel, constraints);

        

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        internalFrame.add(txthobby, constraints);

        constraints.gridy = 1;
        internalFrame.add(txthobbyname, constraints);

        constraints.gridy = 2;
        internalFrame.add(txthobbydes, constraints);

        

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        internalFrame.add(addButton, constraints);

        constraints.gridx = 1;
        internalFrame.add(editButton, constraints);

        constraints.gridx = 2;
        internalFrame.add(deleteButton, constraints);

        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.gridheight = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        internalFrame.add(scrollPane, constraints);
     
        



        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);

        
        Dimension desktopSize = desktopPane.getSize();
        Dimension frameSize = internalFrame.getSize();
        internalFrame.setLocation((desktopSize.width - frameSize.width) / 2, (desktopSize.height - frameSize.height) / 2);
    }
    private void table3() throws SQLException
    {
    	DefaultTableModel defaultModel = null;
        AbstractTableModel abstractModel = null;

        TableModel model = table.getModel();
        if (model instanceof DefaultTableModel) {
            defaultModel = (DefaultTableModel) model;
        } else if (model instanceof AbstractTableModel) {
            abstractModel = (AbstractTableModel) model;
        } else {
            throw new UnsupportedOperationException("Table model not supported");
        }

        if (defaultModel != null) {
            defaultModel.setRowCount(0);
        } 
    	
    	try {
    		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
	        insert = con1.prepareStatement("select * from skill");
	        ResultSet rs = insert.executeQuery();
	        ResultSetMetaData Rss = rs.getMetaData();
	        
	        while(rs.next()) {
	    	String id = rs.getString("skill_id");
	        String name = rs.getString("skill_name");
	        String email = rs.getString("skill_description");
	        

	       
	        TableModel model1 = table.getModel();
	        

	        
	        Object[] rowData = {id, name, email};
	        
	    	if (model1 instanceof DefaultTableModel) {
	            
	            ((DefaultTableModel) model1).addRow(rowData);
	        } else {
	            
	            Object[][] data = new Object[model1.getRowCount() + 1][model1.getColumnCount()];
	            for (int row = 0; row < model1.getRowCount(); row++) {
	                for (int col = 0; col < model1.getColumnCount(); col++) {
	                    data[row][col] = model1.getValueAt(row, col);
	                }
	            }
	            data[model.getRowCount()] = rowData;

	            
	            TableModel newModel = new DefaultTableModel(data, getColumnNames(model));

	            
	            table.setModel(newModel);
	        }
	        }

	        
	        txtskill.setText("");
	        txtskillname.setText("");
	        txtskilldes.setText("");
	       
		} catch (ClassNotFoundException e) {
			
			JOptionPane.showMessageDialog(null, e,"WARNING", 
					JOptionPane.WARNING_MESSAGE);
		}
        
    	
    }
    int selectedRow3;
    String ef2 = "";
    private void showInternalFrame3(String title) throws SQLException {
        JInternalFrame internalFrame = new JInternalFrame(title, true, true, true, true);
        internalFrame.setSize(800, 600);
        internalFrame.setLayout(new GridBagLayout()); 

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 10, 5, 5);

        JLabel idLabel = new JLabel("Skill ID:");
        txtskill = new JTextField(20);
        JLabel nameLabel = new JLabel("Skill Name:");
        txtskillname = new JTextField(20);
        JLabel emailLabel = new JLabel("Skill Description:");
        txtskilldes = new JTextField(20);
        

        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String hob = txtskill.getText();
                String name = txtskillname.getText();
                String des = txtskilldes.getText();

                TableModel model = table.getModel();

                
                Object[] rowData = {hob,name,des};
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                    
                    insert = con1.prepareStatement("insert into skill(skill_id,skill_name,skill_description) values('"+hob+"','"+name+"','"+des+"' )");

                    insert.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Record Added","HELP", 
    						JOptionPane.INFORMATION_MESSAGE);
                    table3();
                    
                    txtskill.setText("");
                    txtskillname.setText("");
                    txtskilldes.setText("");
                    
                    txtskill.requestFocus();
                    
                } 
                catch (ClassNotFoundException | SQLException ex) {
                	JOptionPane.showMessageDialog(null, ex,"WARNING", 
    						JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            	int ind = Integer.parseInt(ef2);
                String name = txtskillname.getText();
                String hob = txtskill.getText();
                String des = txtskilldes.getText();
                
                Class.forName("oracle.jdbc.driver.OracleDriver");
                con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                insert = con1.prepareStatement("update skill set skill_name='"+name+"',skill_description='"+des+"' where skill_id='"+ind+"' ");

                insert.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Record Update","Message", 
						JOptionPane.INFORMATION_MESSAGE);
                table3();
                
                txtskill.setText("");
                txtskillname.setText("");
                txtskilldes.setText("");
                
                txtskill.requestFocus();
                
            } 
            catch (ClassNotFoundException | SQLException ex) {
            	JOptionPane.showMessageDialog(null, ex,"WARNING", 
						JOptionPane.WARNING_MESSAGE);
            }
                
        }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
                    
                    
                    int ele = Integer.parseInt(txtskill.getText());
                    if(selectedRow3==-1)
                    {
                        int dialogResult = JOptionPane.showConfirmDialog(null,"Do you what to Delete the Record","Warning",JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.YES_NO_OPTION)
                    {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                        insert = con1.prepareStatement("delete from skill where skill_id='"+ele+"' ");
                        insert.executeUpdate();
                    
                        JOptionPane.showMessageDialog(null, "Record Deleted","Message", 
        						JOptionPane.INFORMATION_MESSAGE);
                        table3();
                    
                        
                        txtskill.setText("");
                        txtskillname.setText("");
                        txtskilldes.setText("");
                        txtskill.requestFocus();
                        
                    
                    }
                    }
                    else{
                        int ind = Integer.parseInt(table.getValueAt(selectedRow3,0).toString());
                    int dialogResult = JOptionPane.showConfirmDialog(null,"Do you what to Delete the Record","Warning",JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.YES_NO_OPTION)
                    {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                        insert = con1.prepareStatement("delete from skill where skill_id='"+ind+"' ");
                        insert.executeUpdate();
                    
                        JOptionPane.showMessageDialog(null, "Record Deleted","Message", 
        						JOptionPane.INFORMATION_MESSAGE);
                        table3();
                    
                        txtskill.setText("");
                        txtskillname.setText("");
                        txtskilldes.setText("");
                        
                        txtskill.requestFocus();
                    
                    }
                    }
                    
                    
                    
                     
                    
                } 
                catch (ClassNotFoundException | SQLException ex) {
                	JOptionPane.showMessageDialog(null, ex,"WARNING", 
    						JOptionPane.WARNING_MESSAGE);
                }
                
            }
        });

        
        String[] columnNames = {"Skill ID", "Skill Name", "Skill description"};
        Object[][] data = {
                
        };
        
            
            
        
        
        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        

        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedRow3 = table.getSelectedRow();
                    if (selectedRow3 >= 0) {
                        Object selectedId = table.getValueAt(selectedRow3, 0);
                        Object selectedName = table.getValueAt(selectedRow3, 1);
                        Object selectedEmail = table.getValueAt(selectedRow3, 2);
                        

                        txtskill.setText(String.valueOf(selectedId));
                        ef2= String.valueOf(selectedId);
                        txtskillname.setText(String.valueOf(selectedName));
                        txtskilldes.setText(String.valueOf(selectedEmail));
                        
                    }
                }
            }
        });

        table3();
        constraints.gridx = 0;
        constraints.gridy = 0;
        internalFrame.add(idLabel, constraints);

        constraints.gridy = 1;
        internalFrame.add(nameLabel, constraints);

        constraints.gridy = 2;
        internalFrame.add(emailLabel, constraints);

        

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        internalFrame.add(txtskill, constraints);

        constraints.gridy = 1;
        internalFrame.add(txtskillname, constraints);

        constraints.gridy = 2;
        internalFrame.add(txtskilldes, constraints);

        

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        internalFrame.add(addButton, constraints);

        constraints.gridx = 1;
        internalFrame.add(editButton, constraints);

        constraints.gridx = 2;
        internalFrame.add(deleteButton, constraints);

        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.gridheight = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        internalFrame.add(scrollPane, constraints);
     
        



        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);

        
        Dimension desktopSize = desktopPane.getSize();
        Dimension frameSize = internalFrame.getSize();
        internalFrame.setLocation((desktopSize.width - frameSize.width) / 2, (desktopSize.height - frameSize.height) / 2);
    }
    
    private void table4() throws SQLException
    {
    	DefaultTableModel defaultModel = null;
        AbstractTableModel abstractModel = null;

        TableModel model = table.getModel();
        if (model instanceof DefaultTableModel) {
            defaultModel = (DefaultTableModel) model;
        } else if (model instanceof AbstractTableModel) {
            abstractModel = (AbstractTableModel) model;
        } else {
            throw new UnsupportedOperationException("Table model not supported");
        }

        if (defaultModel != null) {
            defaultModel.setRowCount(0);
        } 
    	
    	try {
    		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
	        insert = con1.prepareStatement("select * from project");
	        ResultSet rs = insert.executeQuery();
	        ResultSetMetaData Rss = rs.getMetaData();
	        
	        while(rs.next()) {
	    	String id = rs.getString("project_id");
	        String name = rs.getString("project_name");
	        String email = rs.getString("project_description");
	        String stat = rs.getString("status");
	        

	       
	        TableModel model1 = table.getModel();
	        

	        
	        Object[] rowData = {id, name, email,stat};
	        
	    	if (model1 instanceof DefaultTableModel) {
	            
	            ((DefaultTableModel) model1).addRow(rowData);
	        } else {
	            
	            Object[][] data = new Object[model1.getRowCount() + 1][model1.getColumnCount()];
	            for (int row = 0; row < model1.getRowCount(); row++) {
	                for (int col = 0; col < model1.getColumnCount(); col++) {
	                    data[row][col] = model1.getValueAt(row, col);
	                }
	            }
	            data[model.getRowCount()] = rowData;

	            
	            TableModel newModel = new DefaultTableModel(data, getColumnNames(model));

	            
	            table.setModel(newModel);
	        }
	        }

	        
	        txtproj.setText("");
	        txtprojname.setText("");
	        txtprojdes.setText("");
	        txtstat.setText("");
	       
		} catch (ClassNotFoundException e) {
			
			JOptionPane.showMessageDialog(null, e,"WARNING", 
					JOptionPane.WARNING_MESSAGE);
		}
        
    	
    }
    int selectedRow4;
    String ef3 = "";
    private void showInternalFrame4(String title) throws SQLException {
        JInternalFrame internalFrame = new JInternalFrame(title, true, true, true, true);
        internalFrame.setSize(800, 600);
        internalFrame.setLayout(new GridBagLayout()); 

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 10, 5, 5);

        JLabel idLabel = new JLabel("Project ID:");
        txtproj = new JTextField(20);
        JLabel nameLabel = new JLabel("Project Name:");
        txtprojname = new JTextField(20);
        JLabel emailLabel = new JLabel("Project Description:");
        txtprojdes = new JTextField(20);
        JLabel statLabel = new JLabel("Status :");
        txtstat = new JTextField(20);
        
        

        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String hob = txtproj.getText();
                String name = txtprojname.getText();
                String des = txtprojdes.getText();
                String stat = txtstat.getText();

                TableModel model = table.getModel();

                
                Object[] rowData = {hob,name,des};
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                    
                    insert = con1.prepareStatement("insert into project(project_id,project_name,project_description,status) values('"+hob+"','"+name+"','"+des+"','"+stat+"')");

                    insert.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Record Added","HELP", 
    						JOptionPane.INFORMATION_MESSAGE);
                    table4();
                    
                    txtproj.setText("");
                    txtprojname.setText("");
                    txtprojdes.setText("");
                    txtstat.setText("");
                    
                    txtproj.requestFocus();
                    
                } 
                catch (ClassNotFoundException | SQLException ex) {
                	JOptionPane.showMessageDialog(null, ex,"WARNING", 
    						JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            	int ind = Integer.parseInt(ef3);
                String name = txtprojname.getText();
                String hob = txtproj.getText();
                String des = txtprojdes.getText();
                String stat = txtstat.getText();
                
                Class.forName("oracle.jdbc.driver.OracleDriver");
                con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                insert = con1.prepareStatement("update project set project_name='"+name+"',project_description='"+des+"',status='"+stat+"' where project_id='"+ind+"' ");

                insert.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Record Update","Message", 
						JOptionPane.INFORMATION_MESSAGE);
                table4();
                
                txtproj.setText("");
                txtprojname.setText("");
                txtprojdes.setText("");
                txtstat.setText("");
                
                txtproj.requestFocus();
                
            } 
            catch (ClassNotFoundException | SQLException ex) {
            	JOptionPane.showMessageDialog(null, ex,"WARNING", 
						JOptionPane.WARNING_MESSAGE);
            }
                
        }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
                    
                    
                    int ele = Integer.parseInt(txtproj.getText());
                    if(selectedRow4==-1)
                    {
                        int dialogResult = JOptionPane.showConfirmDialog(null,"Do you what to Delete the Record","Warning",JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.YES_NO_OPTION)
                    {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                        insert = con1.prepareStatement("delete from project where project_id='"+ele+"' ");
                        insert.executeUpdate();
                    
                        JOptionPane.showMessageDialog(null, "Record Deleted","Message", 
        						JOptionPane.INFORMATION_MESSAGE);
                        table4();
                    
                        
                        txtproj.setText("");
                        txtprojname.setText("");
                        txtprojdes.setText("");
                        txtstat.setText("");
                        txtstat.requestFocus();
                        
                    
                    }
                    }
                    else{
                        int ind = Integer.parseInt(table.getValueAt(selectedRow4,0).toString());
                    int dialogResult = JOptionPane.showConfirmDialog(null,"Do you what to Delete the Record","Warning",JOptionPane.YES_NO_OPTION);
                    if(dialogResult == JOptionPane.YES_NO_OPTION)
                    {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                        insert = con1.prepareStatement("delete from project where project_id='"+ind+"' ");
                        insert.executeUpdate();
                    
                        JOptionPane.showMessageDialog(null, "Record Deleted","Message", 
        						JOptionPane.INFORMATION_MESSAGE);
                        table4();
                    
                        txtproj.setText("");
                        txtprojname.setText("");
                        txtprojdes.setText("");
                        txtstat.setText("");
                        
                        txtproj.requestFocus();
                    
                    }
                    }
                    
                    
                    
                     
                    
                } 
                catch (ClassNotFoundException | SQLException ex) {
                	JOptionPane.showMessageDialog(null, ex,"WARNING", 
    						JOptionPane.WARNING_MESSAGE);
                }
                
            }
        });

        
        String[] columnNames = {"Project ID", "Project Name", "Project description","Status"};
        Object[][] data = {
                
        };
        
            
            
        
        
        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        

        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedRow4 = table.getSelectedRow();
                    if (selectedRow4 >= 0) {
                        Object selectedId = table.getValueAt(selectedRow4, 0);
                        Object selectedName = table.getValueAt(selectedRow4, 1);
                        Object selectedEmail = table.getValueAt(selectedRow4, 2);
                        Object selectedStat = table.getValueAt(selectedRow4, 3);

                        txtproj.setText(String.valueOf(selectedId));
                        ef3= String.valueOf(selectedId);
                        txtprojname.setText(String.valueOf(selectedName));
                        txtprojdes.setText(String.valueOf(selectedEmail));
                        txtstat.setText(String.valueOf(selectedStat));
                        
                    }
                }
            }
        });

        table4();
        constraints.gridx = 0;
        constraints.gridy = 0;
        internalFrame.add(idLabel, constraints);

        constraints.gridy = 1;
        internalFrame.add(nameLabel, constraints);

        constraints.gridy = 2;
        internalFrame.add(emailLabel, constraints);
        
        constraints.gridy = 3;
        internalFrame.add(statLabel, constraints);
        

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        internalFrame.add(txtproj, constraints);

        constraints.gridy = 1;
        internalFrame.add(txtprojname, constraints);

        constraints.gridy = 2;
        internalFrame.add(txtprojdes, constraints);
        
        constraints.gridy = 3;
        internalFrame.add(txtstat, constraints);

        

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        internalFrame.add(addButton, constraints);

        constraints.gridx = 1;
        internalFrame.add(editButton, constraints);

        constraints.gridx = 2;
        internalFrame.add(deleteButton, constraints);

        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.gridheight = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        internalFrame.add(scrollPane, constraints);
     
        



        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);

        
        Dimension desktopSize = desktopPane.getSize();
        Dimension frameSize = internalFrame.getSize();
        internalFrame.setLocation((desktopSize.width - frameSize.width) / 2, (desktopSize.height - frameSize.height) / 2);
    }
    private String[] getColumnNames(TableModel model) {
        int columnCount = model.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int col = 0; col < columnCount; col++) {
            columnNames[col] = model.getColumnName(col);
        }
        return columnNames;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}
