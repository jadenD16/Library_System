package techServ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.ibm.db2.jcc.am.SqlIntegrityConstraintViolationException;

import domain.Author;
import domain.Books;
import domain.User;
import domain.UserInfo;
import ui.LibrarySystemMain;

public class UserDA 
{

	private Connection connection;
	private ResultSet rs;
	private User user;
	private List<User> accList;
	private Integer recordPointer= 0;
	public UserDA(Connection connection)
	{
		accList = new ArrayList<User>();
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
	public void AddUser(Connection connection,User user)
	{
		
		this.connection = connection;
		
		try 
		{
			PreparedStatement ps;
			//prepared chuhcu s will contain the sql statement / query
			String query;
			query = "insert into users(userId,userName,password,userType) VALUES(?,?,?,?)";
			
		
			ps= connection.prepareStatement(query);
	
			
			ps.setString(1,  user.getUserId());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getPassWord());
			System.out.println(user.getPassWord());
			ps.setString(4, user.getUserType());
			
			ps.executeUpdate();
			accList.add(user);
			refreshList(connection);
			
		}
			catch(SqlIntegrityConstraintViolationException e) {
				System.out.println("Missing or Replicated Value Error");			
			}
			catch(SQLException e) {
				System.out.print("Something is wrong in db or queue....");
			}	
	}
	
	public void EditUser(Connection connection,User user) {
	    
		accList = new ArrayList<>();
		this.connection=connection;
		
		try 
		{
			
			PreparedStatement ps = null;
			String query;
			
			query="Update users set username=?,password=? WHERE userid=?";
			
			ps = connection.prepareStatement(query);
			
			ps.setString(1, user.getUserName().trim());
			ps.setString(2, user.getPassWord().trim());
			ps.setString(3, user.getUserId().trim());
			
			ps.executeUpdate();
			
			refreshList(connection);
		}
		catch(SqlIntegrityConstraintViolationException e) {
			System.out.println("Missing or Replicated Value Error");			
		}
		catch(SQLException e) {
			System.out.print("Something is wrong in db or queue....");
		}
		
	}
	
	public void DeleteData(Connection connection, User users) {
			accList = new ArrayList<>();
		    
			
			this.connection=connection;
			try 
			{
				 PreparedStatement s = null;
				 String query;
				 query = "DELETE FROM Users WHERE userID=? " ;
				 
				 s=connection.prepareStatement(query);		
				 
				 s.setString(1, users.getUserId());
				 
				 
				 
				 s.executeUpdate();
				 // update customerList
				 accList.remove(users);	
				 refreshList(connection);
				 
				
			}
			catch(SqlIntegrityConstraintViolationException e) {
				System.out.println("Missing or Replicated Value Error");			
			}
			catch(SQLException e) {
				System.out.print("Something is wrong in db or queue....");
			}
				
		}

	public User GetFirstUser() { 
		recordPointer = 0;
		return accList.get(recordPointer);
	}
		
	
	public User GetLastUser() {
		recordPointer = accList.size()-1;
		return accList.get(recordPointer);
	}
	
	public User getNextUser() {
		recordPointer++;
		if(recordPointer > accList.size()-1)
			recordPointer = accList.size()-1;
		
		return accList.get(recordPointer);
	}
	public User getPrevious()
	{
		recordPointer --;
		if(recordPointer < 0)
			recordPointer =0;
		return accList.get(recordPointer);
	}
	
	public List<User> getAccList(){
		return accList;
	}
	
	public  void refreshList(Connection connection)
	{

		accList = new ArrayList<User>();
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
}
