package techServ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import domain.Author;
import domain.Books;
import domain.User;
import ui.LibrarySystemMain;

public class UserDA 
{

	private Connection connection;
	private ResultSet rs;
	private User user;
	private LibrarySystemMain ui;
	
	public UserDA(Connection connection,User user)
	{
		this.connection = connection;
		this.user = user;
		ui = new LibrarySystemMain();
		
		try 
		{
		
			PreparedStatement ps;
			//prepared chuhcu s will contain the sql statement / query
			String query;
			query = "Select * from Users where username = ? and password = ? ";
			ps= connection.prepareStatement(query);
						
			ps.setString(1,user.getUserName());
			ps.setString(2, user.getPassWord());
			rs = ps.executeQuery();
			
			if(!rs.next())
			{
			
			 String message = "Invalid username or password";
				    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
				        JOptionPane.ERROR_MESSAGE);
			}
			
			else
			{
				user.setUserId(rs.getString(1));
				user.setUserName(rs.getString(2));
				user.setUserType(rs.getString(4));
//				ui.loggedIn(user);
			}
			
		}
		
				
		catch(SQLException e)
		{
			e.printStackTrace();
		}		
	}
	
				
	
}
