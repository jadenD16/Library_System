package ui;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Window.Type;
import java.awt.Dimension;
import java.awt.Dialog.ModalityType;

public class LoginUI extends JDialog  
{
	private JTextField userNameTF;
	private JPasswordField passwordField;
	
	private JLabel lblUserName,userIcon,lblPassword,passIcon;
	private JButton btnLogin,btnCancel;
	private Connection connection;
	
	public LoginUI(Connection connection)
	{
		setModalityType(ModalityType.APPLICATION_MODAL);
		setSize(new Dimension(442, 305));
		setResizable(false);
		setModal(true);
		this.connection = connection;
		setType(Type.POPUP);
		setUndecorated(true);
		getContentPane().setLayout(null);
		login();

		setLocationRelativeTo(null);
		
		setVisible(true);
	}
	
	public void login()
	{			
		lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblUserName.setBounds(97, 83, 93, 18);
		getContentPane().add(lblUserName);
		
		userIcon = new JLabel(new ImageIcon(LoginUI.class.getResource("/pictures/userI.png")));
		userIcon.setBounds(62, 75, 25, 26);
		getContentPane().add(userIcon);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblPassword.setBounds(97, 119, 93, 18);
		getContentPane().add(lblPassword);
		
		userNameTF = new JTextField();
		userNameTF.setBounds(200, 84, 156, 23);
		getContentPane().add(userNameTF);
		userNameTF.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnLogin.setBounds(97, 185, 89, 23);
		getContentPane().add(btnLogin);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnCancel.setBounds(242, 185, 89, 23);
		getContentPane().add(btnCancel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 119, 156, 22);
		getContentPane().add(passwordField);
		
		passIcon = new JLabel(new ImageIcon(LoginUI.class.getResource("/pictures/passI.png")));
		passIcon.setBounds(62, 111, 25, 26);
		getContentPane().add(passIcon);
	}
			
	
	
}