package ui;

import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import domain.Books;
import domain.Department;
import domain.ProgStudy;
import domain.User;
import domain.UserInfo;
import techServ.DepartmentDA;
import techServ.ProgStudyDA;
import techServ.UserDA;
import techServ.UserInfoDA;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

public class UserInformationUI extends JPanel implements ActionListener{
	private UserInfoDA userinfoDA;
	private UserInfo userinfo;
	private User users;
	private Connection connection;
	private ProgStudy progStudy;
	private ProgStudyDA progStudyDA;
	private Department department;
	private DepartmentDA departmentDA;
	private UserDA userDA;
	private LibrarySystemMain librarySystemMain;
	private String[] tableHeader;
	
	private DefaultTableModel tableModel;
	private TableColumnModel columnModel;
	
	private JTextField lnameTF,middleTF,fnameTF,userIdTF,progStudyTF ,contactTF,birthdayTF,nameTF,deptTF,yearLevelTF,genderTF,searchTF,textField,userTypeTF;
	private JButton btnFirst,btnPrevious,btnLast,btnNext,btnDelete,btnAdd,btnUpdate,btnSearch,btnSave,btnCancel;
	private JLabel lblProgStudy,lblYearLevel,lblGender,UserType;
	private JRadioButton radioButtonMale,radioButtonFemale;
	private JComboBox progBox,deptBox,yearLevelBox,userTypeBox;
	private ButtonGroup bg;
	private JDateChooser dateChooser;
	private String buttonIdentifier;
	private JTable table;
	private JLabel lblPassword;
	private JLabel userName;
	private JTextField userNameTF;
	private JTextField passwordTF;

	public UserInformationUI(Connection connection) {
		this.connection = connection;
		
		setLayout(null);
		setBounds(273,177,1071,502);
		
		JLabel lblPicture = new JLabel("New label");
		lblPicture.setIcon(new ImageIcon("C:\\Users\\Edwin odewyn\\Pictures\\greeetin picture.jpg"));
		lblPicture.setHorizontalAlignment(SwingConstants.CENTER);
		lblPicture.setBackground(Color.BLACK);
		lblPicture.setBounds(22, 38, 169, 157);
		add(lblPicture);
		
		ProgStudyDA progStudyDA = new ProgStudyDA(connection);
		DepartmentDA departmentDA = new DepartmentDA(connection);

		userIdTF = new JTextField();
		userIdTF.setBounds(316, 38, 170, 26);
		userIdTF.setEditable(false);
		add(userIdTF);
		userIdTF.setColumns(10);
		
		JLabel lblFirstName = new JLabel("Name:");
		lblFirstName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFirstName.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		lblFirstName.setBounds(201, 78, 115, 26);
		add(lblFirstName);
		
		progStudyTF = new JTextField();
		progStudyTF.setColumns(10);
		progStudyTF.setBounds(777, 78, 274, 26);
		add(progStudyTF);
		
		
		JLabel lblUserId = new JLabel("User ID:");
		lblUserId.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserId.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		lblUserId.setBounds(199, 38, 115, 26);
		add(lblUserId);
		
		JLabel lblContactNumber = new JLabel("Contact Number:");
		lblContactNumber.setHorizontalAlignment(SwingConstants.LEFT);
		lblContactNumber.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		lblContactNumber.setBounds(201, 119, 130, 26);
		add(lblContactNumber);
		
		contactTF = new JTextField();
		contactTF.setColumns(10);
		contactTF.setBounds(318, 119, 285, 26);
		add(contactTF);
		
		lblProgStudy = new JLabel("Program of Study:");
		lblProgStudy.setHorizontalAlignment(SwingConstants.LEFT);
		lblProgStudy.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		lblProgStudy.setBounds(637, 78, 130, 26);
		add(lblProgStudy);
		
		JLabel lblBirthDate = new JLabel("Birthday:");
		lblBirthDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblBirthDate.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		lblBirthDate.setBounds(201, 160, 130, 26);
		add(lblBirthDate);
		
		birthdayTF = new JTextField();
		birthdayTF.setColumns(10);
		birthdayTF.setBounds(318, 160, 285, 26);
		add(birthdayTF);
		
		nameTF = new JTextField();
		nameTF.setColumns(10);
		nameTF.setBounds(317, 78, 169, 26);
		add(nameTF);
		
		JLabel lblDepartment = new JLabel("Department:");
		lblDepartment.setHorizontalAlignment(SwingConstants.LEFT);
		lblDepartment.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		lblDepartment.setBounds(637, 119, 130, 26);
		add(lblDepartment);
		
		deptTF = new JTextField();
		deptTF.setColumns(10);
		deptTF.setBounds(778, 119, 274, 26);
		add(deptTF);
		
		lblYearLevel = new JLabel("YearLevel:");
		lblYearLevel.setHorizontalAlignment(SwingConstants.LEFT);
		lblYearLevel.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		lblYearLevel.setBounds(637, 160, 130, 26);
		add(lblYearLevel);
		
		yearLevelTF = new JTextField();
		yearLevelTF.setColumns(10);
		yearLevelTF.setBounds(777, 160, 274, 26);
		add(yearLevelTF);
		
		lblGender = new JLabel("Gender:");
		lblGender.setHorizontalAlignment(SwingConstants.LEFT);
		lblGender.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		lblGender.setBounds(487, 78, 59, 26);
		add(lblGender);
		
		genderTF = new JTextField();
		genderTF.setColumns(10);
		genderTF.setBounds(544, 81, 35, 26);
		add(genderTF);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(318, 160, 285, 26);
		dateChooser.setDateFormatString("yyyy-MM-d");
		
		searchTF = new JTextField();
		searchTF.setBounds(110, 225, 267, 23);
		add(searchTF);
		searchTF.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		btnSearch.setBounds(22, 223, 89, 26);
		btnSearch.addActionListener(this);
		add(btnSearch);
		
		textField = new JTextField();
		textField.setBounds(22, 258, 643, 219);
		add(textField);
		textField.setColumns(10);
		
		btnFirst = new JButton("<<");
		btnFirst.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		btnFirst.addActionListener(this);
		btnFirst.setBounds(675, 285, 89, 49);
		add(btnFirst);
		
		btnPrevious = new JButton("Next");
		btnPrevious.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		btnPrevious.setBounds(862, 285, 100, 49);
		btnPrevious.addActionListener(this);
		add(btnPrevious);
		
		btnLast = new JButton(">>");
		btnLast.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		btnLast.setBounds(962, 285, 89, 49);
		btnLast.addActionListener(this);
		add(btnLast);
		  		
		btnNext = new JButton("Previous");
		btnNext.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		btnNext.setBounds(763, 285, 100, 49);
		btnNext.addActionListener(this);
		add(btnNext);
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		btnAdd.setBounds(675, 399, 125, 49);
		btnAdd.addActionListener(this);
		add(btnAdd);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		btnUpdate.setBounds(801, 399, 126, 49);
		btnUpdate.addActionListener(this);
		add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		btnDelete.setBounds(926, 399, 125, 49);
		btnDelete.addActionListener(this);
		add(btnDelete);
		
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		btnSave.setBounds(801, 399, 126, 49);
		btnSave.addActionListener(this);
		add(btnSave);
		
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		btnCancel.setBounds(926, 399, 125, 49);
		btnCancel.addActionListener(this);
		add(btnCancel);
		
		progBox = new JComboBox();
		progBox.setBounds(777, 78, 274, 26);
		for(ProgStudy progStudy : progStudyDA.getProgList())
		{
			progBox.addItem(progStudy.getProgCode()+"("+progStudy.getProgName()+")");
		}
		add(progBox);
		
		deptBox = new JComboBox();
		deptBox.setBounds(778,119,275,26);
		for(Department department: departmentDA.getdeptList())
		{
			deptBox.addItem(department.getDeptCode()+"("+department.getDeptName()+")");
		}
		add(deptBox);
		
		yearLevelBox = new JComboBox();
		yearLevelBox.setBounds(777,160,274,26);
		yearLevelBox.setModel(new DefaultComboBoxModel(new String[]{"1st","2nd","3rd","4th","5th"}));
		add(yearLevelBox);
		
		userTypeBox = new JComboBox();
		userTypeBox.setBounds(777,194,274,26);
		userTypeBox.setModel(new DefaultComboBoxModel(new String[]{"Admin","User"}));
		
		UserType = new JLabel("User Type:");
		UserType.setHorizontalAlignment(SwingConstants.LEFT);
		UserType.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		UserType.setBounds(637, 197, 130, 26);
	
		
		radioButtonMale = new JRadioButton("M");
		radioButtonMale.setBounds(544, 83, 35, 23);
		radioButtonMale.setActionCommand("M");
		radioButtonMale.addActionListener(this);
		
		radioButtonFemale = new JRadioButton("F");
		radioButtonFemale.setBounds(579, 83, 35, 23);
		radioButtonFemale.addActionListener(this);
		
		fnameTF = new JTextField();
		fnameTF.setBounds(317, 78, 59, 26);
		fnameTF.addActionListener(this);
		fnameTF.setColumns(10);
		
		middleTF = new JTextField();
		middleTF.setColumns(10);
		middleTF.setBounds(376, 78, 26, 26);
		middleTF.addActionListener(this);
		
		lnameTF = new JTextField();
		lnameTF.setColumns(10);
		lnameTF.setBounds(402, 78, 84, 26);
		lnameTF.addActionListener(this);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		lblPassword.setBounds(637, 197, 130, 26);
		
		userName = new JLabel("Username:");
		userName.setHorizontalAlignment(SwingConstants.LEFT);
		userName.setFont(new Font("Segoe UI Light", Font.PLAIN, 16));
		userName.setBounds(201, 197, 130, 26);
		
		userNameTF = new JTextField();
		userNameTF.setEditable(true);
		userNameTF.setColumns(10);
		userNameTF.setBounds(318, 197, 285, 26);
		
		passwordTF = new JTextField();
		passwordTF.setEditable(true);
		passwordTF.setColumns(10);
		passwordTF.setBounds(777, 197, 274, 26);
		
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(radioButtonMale);
		bg.add(radioButtonFemale);
		
		userinfo = new UserInfo();
		userinfoDA = new UserInfoDA(connection);
		userinfo = userinfoDA.GetFirstUserInfo();
		users = new User();
		userDA = new UserDA(connection);
		users = userDA.GetFirstUser();
		getDisplayUser();
		
		
		setVisible(true);
		setEditable(false);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		String action = event.getActionCommand();
		
		if(action.equals("<<")) {
			users = userDA.GetFirstUser();
			userinfo = userinfoDA.GetFirstUserInfo();
			getDisplayUser();
			
		}
		else if(action.equals(">>")) {
			users = userDA.GetLastUser();
			userinfo=userinfoDA.GetLastUserInfo();
			getDisplayUser();
			
		}
		else if(action.equals("Next")) {
			users = userDA.getNextUser();
			userinfo = userinfoDA.getNextUserInfo();
			getDisplayUser();
			
		}
		else if(action.equals("Previous")) {
			users = userDA.getPrevious();
			userinfo = userinfoDA.getPreviousCustomer();		
			getDisplayUser();
			
		}
		else if(action.equals("Add")) {
			ClearTextField();
			setEditable(true);
			EnableNavButtons(false);
			setEditableTable(false);
			add(userTypeBox);
			add(UserType);
			AddDisables(false);
			AddEnables(true);

			AddFunction();
			repaint();
			revalidate();
			
			
			buttonIdentifier = "Add";
			
		}
		else if(action.equalsIgnoreCase("Update")) {
			add(userName);
			add(userNameTF);
			add(passwordTF);
			add(lblPassword);
			
			fnameTF.setText(userinfo.getFirstName());
			middleTF.setText(userinfo.getMiddleInitial());
			lnameTF.setText(userinfo.getLastName());

			userNameTF.setText(users.getUserName());
			passwordTF.setText(users.getPassWord());
			
			if(userinfo.getGender().equalsIgnoreCase("M"))
				radioButtonMale.setSelected(true);
			else
				radioButtonFemale.setSelected(true);

			dateChooser.setDate(Date.valueOf(userinfo.getBirthday()));
			progBox.setSelectedItem(userinfo.getProgStudy());
			deptBox.setSelectedItem(userinfo.getDepartment());
			yearLevelBox.setSelectedItem(userinfo.getYearLevel());
			
			setEditable(true);
			EnableNavButtons(false);
			setEditableTable(false);
			AddDisables(false);
			AddEnables(true);
			
			
			repaint();
			revalidate();
			AddFunction();
			
			buttonIdentifier = "Update";
			
		}
		else if(action.equalsIgnoreCase("Delete")) {
			EnableAddEditDelete(false);
			btnSave.setVisible(true);
			btnCancel.setVisible(true);
			repaint();
			revalidate();
			
			buttonIdentifier = "Delete";
		}
		
		else if(action.equalsIgnoreCase("Save")) 
		{
			
			if(buttonIdentifier.equalsIgnoreCase("Add")) 
			{
				if(isFilled() && ((radioButtonMale.isSelected() == true)||(radioButtonFemale.isSelected() == true))) 	
				{
					if((fnameTF.getText().matches("^[a-zA-Z]*$")) && (middleTF.getText().matches("^[a-zA-Z]*$")) && (lnameTF.getText().matches("^[a-zA-Z]*$"))){
							if((contactTF.getText().matches("^[0-9]*$")) && contactTF.getText().length() == 11) 
							{
									userinfo = new UserInfo();
									users = new User();
									
									userinfo.setUserId(userIdTF.getText());
									String[] name=nameTF.getText().replaceAll(",", " ").split(" ");
									userinfo.setFirstName(fnameTF.getText());
									userinfo.setMiddleInitial(middleTF.getText());
									userinfo.setLastName(lnameTF.getText());
									userinfo.setContactNumber(contactTF.getText());
									String[] progStudy = progBox.getSelectedItem().toString().split("\\(");
									System.out.println(progStudy[0]);
									userinfo.setProgStudy(progStudy[0]);
									userinfo.setDepartment(deptBox.getSelectedItem().toString());
									userinfo.setYearLevel(yearLevelBox.getSelectedItem().toString());
									
									userinfo.setBirthday(((JTextField)dateChooser.getDateEditor().getUiComponent()).getText());
									
									if(radioButtonMale.isSelected())
										userinfo.setGender("M");				
									
									else
										userinfo.setGender("F");
									
									users.setUserId(userIdTF.getText());
									users.setUserName(fnameTF.getText().substring(0, 1) + middleTF.getText().substring(0, 1) + lnameTF.getText());
									users.setPassWord(fnameTF.getText().substring(0, 1) + middleTF.getText().substring(0, 1) + lnameTF.getText());
									users.setUserType(userTypeBox.getSelectedItem().toString());
								
									userinfoDA.addData(connection, userinfo);
									userDA.AddUser(connection, users);
									setEditable(false);
									EnableNavButtons(true);
									setEditableTable(true);
									AddDisables(true);
									AddEnables(false);
									remove(radioButtonFemale);
									remove(radioButtonMale);
									remove(UserType);
									remove(userTypeBox);
									userinfo=userinfoDA.GetLastUserInfo();
									getDisplayUser();
									repaint();
									revalidate();
							}
							else
								JOptionPane.showMessageDialog(null, "Please Enter 11-digit Number");							
					}
					else
						JOptionPane.showMessageDialog(null, "Invalid Name");
				}
				else 
					JOptionPane.showMessageDialog(null, "Please Fill Up the Blanks");
				
			}
			
			else if (buttonIdentifier.equalsIgnoreCase("Update")) 
			{
				if(isFilled() && ((radioButtonMale.isSelected() == true)||(radioButtonFemale.isSelected() == true))) 
				{
						if((fnameTF.getText().matches("^[a-zA-Z.]*$")) && (middleTF.getText().matches("^[a-zA-Z.]*$")) && (lnameTF.getText().matches("^[a-zA-Z.]*$"))) 
						{
							if( (contactTF.getText().matches("^[0-9]*$")) && contactTF.getText().length() == 11) { 
								userinfo = new UserInfo();
								users = new User();
								
								userinfo.setUserId(userIdTF.getText());
								String[] name=nameTF.getText().replaceAll(",", " ").split(" ");
				
								userinfo.setFirstName(fnameTF.getText());
								userinfo.setMiddleInitial(middleTF.getText());
								userinfo.setLastName(lnameTF.getText());
								userinfo.setContactNumber(contactTF.getText());
								String[] progStudy = progBox.getSelectedItem().toString().split("\\(");

								userinfo.setProgStudy(progStudy[0]);
								userinfo.setDepartment(deptBox.getSelectedItem().toString());
								userinfo.setYearLevel(yearLevelBox.getSelectedItem().toString());
								
								userinfo.setBirthday(((JTextField)dateChooser.getDateEditor().getUiComponent()).getText());
								
								if(radioButtonMale.isSelected())
									userinfo.setGender("M");				
								
								else
									userinfo.setGender("F");
								
								//TODO eto ung sa users
								
								users.setUserId(userIdTF.getText());
								users.setUserName(userNameTF.getText());
								users.setPassWord(passwordTF.getText());
								users.setUserType(userTypeBox.getSelectedItem().toString());
								

								userDA.EditUser(connection, users);
								userinfoDA.EditData(connection, userinfo);
								
								setEditable(false);
								EnableNavButtons(true);
								setEditableTable(true);
								AddDisables(true);
								AddEnables(false);
								remove(radioButtonFemale);
								remove(radioButtonMale);
								remove(userName);
								remove(userNameTF);
								remove(lblPassword);
								remove(passwordTF);

								getDisplayUser();
								repaint();
								revalidate();
							}
						
							else
								JOptionPane.showMessageDialog(null, "Please Enter 11-digit Number");
							
						}
						else 
							JOptionPane.showMessageDialog(null, "Invalid Name");
				}
				else 
					JOptionPane.showMessageDialog(null, "Please Fill Up the Blanks");
			}
			else if(buttonIdentifier.equalsIgnoreCase("Delete")){
				userDA.DeleteData(connection, users);
				users = userDA.GetLastUser();
				
				userinfoDA.DeleteData(connection, userinfo);
				userinfo=userinfoDA.GetLastUserInfo();
				getDisplayUser();
				EnableAddEditDelete(true);
				btnSave.setVisible(false);
				btnCancel.setVisible(false);
			}
		}
		
		else if(action.equalsIgnoreCase("Cancel")) {
			getDisplayUser();
			setEditable(false);
			EnableNavButtons(true);
			setEditableTable(true);
			remove(radioButtonFemale);
			remove(radioButtonMale);
			remove(UserType);
			remove(userTypeBox);
			remove(userName);
			remove(userNameTF);
			remove(lblPassword);
			remove(passwordTF);
			AddDisables(true);
			AddEnables(false);
		}
		
	}
	
	public void EnableAddEditDelete(boolean input) {
		btnAdd.setVisible(input);
		btnDelete.setVisible(input);
		btnUpdate.setVisible(input);
	}
	
	public void AddDisables(boolean input) {
		genderTF.setVisible(input);
		btnSearch.setEnabled(input);
		birthdayTF.setVisible(input);
		progStudyTF.setVisible(input);
		nameTF.setVisible(input);
		deptTF.setVisible(input);
		yearLevelTF.setVisible(input);
		btnUpdate.setVisible(input);
		btnAdd.setVisible(input);
		btnDelete.setVisible(input);
		
	}
	public void AddEnables(boolean input) {
		fnameTF.setVisible(input);
		middleTF.setVisible(input);
		lnameTF.setVisible(input);
		progBox.setVisible(input);
		deptBox.setVisible(input);
		btnSave.setVisible(input);
		btnCancel.setVisible(input);
	}
	public void AddFunction() {
		add(dateChooser);
		add(radioButtonMale);
		add(radioButtonFemale);
		add(middleTF);
		add(lnameTF);
		add(fnameTF);

	}
	
	

	
	public void getDisplayUser() {
		
		
		
		userIdTF.setText(" "+userinfo.getUserId());
		nameTF.setText(" "+userinfo.getLastName() + ", " + userinfo.getFirstName()+" "+userinfo.getMiddleInitial());
		genderTF.setText(" "+userinfo.getGender());
		contactTF.setText(userinfo.getContactNumber());
		birthdayTF.setText(" "+String.valueOf(userinfo.getBirthday()));
		progStudyTF.setText(" "+userinfo.getProgStudy());
		deptTF.setText(" "+userinfo.getDepartment());
		yearLevelTF.setText(" "+userinfo.getYearLevel());
		userNameTF.setText(" "+users.getUserName());
		passwordTF.setText(" "+ users.getPassWord());
	
		
	}
	
	

	public void setEditable(boolean input) {
		nameTF.setEditable(input);
		genderTF.setEditable(input);
		contactTF.setEditable(input);
		birthdayTF.setEditable(input);
		progStudyTF.setEditable(input);
		deptTF.setEditable(input);
		yearLevelTF.setEditable(input);
	
	}
	public void ClearTextField() {
		userinfo=userinfoDA.GetLastUserInfo();
		Integer userInfo = Integer.valueOf(userinfo.getUserId())+1;
		userIdTF.setText(String.format("%05d",userInfo));
		fnameTF.setText("");
		middleTF.setText("");
		lnameTF.setText("");
		genderTF.setText("");
		contactTF.setText("");
		((JTextField)dateChooser.getDateEditor().getUiComponent()).setText("");
		birthdayTF.setText("");
		progStudyTF.setText("");
		deptTF.setText("");
		yearLevelTF.setText("");
	}
	public void setEditableTable(boolean input) {
		searchTF.setEditable(input);
		textField.setEditable(input);
	}
	public void EnableNavButtons(boolean choice) {
		btnFirst.setEnabled(choice);
		btnNext.setEnabled(choice);
		btnPrevious.setEnabled(choice);
		btnLast.setEnabled(choice);
	}
	public boolean isFilled() {
		
		if(!fnameTF.getText().trim().equals("") && !middleTF.getText().trim().equals("") && !lnameTF.getText().trim().equals("") && !contactTF.getText().trim().equals("") && !((JTextField)dateChooser.getDateEditor().getUiComponent()).getText().isEmpty()) {
			return true;
		}
		
		return false;
	}
}
