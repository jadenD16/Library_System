package techServ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	private List<User> accList = new ArrayList<User>();
	
	public UserDA(Connection connection)
	{
		this.connection = connection;
		
		try 
		{
		

			PreparedStatement ps;
			//prepared chuhcu s will contain the sql statement / query
			String query;
			query = "Select * from Users";
			ps= connection.prepareStatement(query);
						
			rs = ps.executeQuery();
			
			while(rs.next())
			{

			user = new User();
			user.setUserId(rs.getString(1));
			user.setUserName(rs.getString(2));
			user.setPassWord(rs.getString(3));
			user.setUserType(rs.getString(4));
			
			accList.add(user);
			}
		}
		
				
		catch(SQLException e)
		{
			e.printStackTrace();
		}		
	}
	
	public List<User> getAccList(){
		return accList;
	}
	
}
