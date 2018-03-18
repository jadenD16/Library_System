package ui;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Window.Type;

public class DetailsPopUpUI extends JDialog  
{
	private JTextField userNameTF;
	private JPasswordField passwordField;
	
	private JLabel lblUserName,userIcon,lblPassword,passIcon;
	private JButton btnAdd,btnCancel;
	
	public DetailsPopUpUI()
	{
		setType(Type.POPUP);
		setUndecorated(true);
		setLayout(null);
		
	}
	
	/*public void login()
	{			
		lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblUserName.setBounds(97, 83, 93, 18);
		add(lblUserName);
		
		userIcon = new JLabel(new ImageIcon(DetailsPopUpUI.class.getResource("/pictures/userI.png")));
		userIcon.setBounds(62, 75, 25, 26);
		add(userIcon);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblPassword.setBounds(97, 119, 93, 18);
		add(lblPassword);
		
		userNameTF = new JTextField();
		userNameTF.setBounds(200, 84, 156, 23);
		add(userNameTF);
		userNameTF.setColumns(10);
		
		btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNewButton.setBounds(97, 185, 89, 23);
		add(btnNewButton);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnCancel.setBounds(242, 185, 89, 23);
		add(btnCancel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 119, 156, 22);
		add(passwordField);
		
		passIcon = new JLabel(new ImageIcon(DetailsPopUpUI.class.getResource("/pictures/passI.png")));
		passIcon.setBounds(62, 111, 25, 26);
		add(passIcon);
		
	}*/
	
	public void Login()
	{
		
	}
	
	
}