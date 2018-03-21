package techServ;


import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.ibm.db2.jcc.am.SqlIntegrityConstraintViolationException;

import domain.Department;
import domain.ProgStudy;
import domain.User;
import domain.UserInfo;

public class UserInfoDA 
{

	private Department department;
	private ProgStudy progStudy;
	private Connection connection;
	private List<UserInfo> userInfoList;
	private ResultSet rs;
	private UserInfo userinfo;
	private Integer recordPointer = 0;
	
	public UserInfoDA(Connection connection,User user) 
	{
	
		
		userInfoList = new ArrayList<UserInfo>();
		
		this.connection=connection;
		
		try 
		{
			
			PreparedStatement ps = null;
			String query;
						
			if(user.getUserType().equalsIgnoreCase("Admin"))
			{
				query="select userinfo.userid,userinfo.firstname,userinfo.middleinitial,userinfo.lastname,userinfo.contactnumber,"
						+ "userinfo.gender,userinfo.yearlevel,userinfo.birthday,userinfo.userpic,userinfo.progcode,progStudy.progName,userinfo.deptCode,department.deptName from userinfo JOIN department ON userinfo.deptCode = department.deptCode JOIN progStudy ON userinfo.progCode=progStudy.progCode";
				ps = connection.prepareStatement(query);
				rs=ps.executeQuery();
			}	
			
			else
			{
				query="select userinfo.userid,userinfo.firstname,userinfo.middleinitial,userinfo.lastname,userinfo.contactnumber,"
						+ "userinfo.gender,userinfo.yearlevel,userinfo.birthday,userinfo.userpic,userinfo.progcode,progStudy.progName,userinfo.deptCode,department.deptName from userinfo JOIN department ON userinfo.deptCode = department.deptCode JOIN progStudy ON userinfo.progCode=progStudy.progCode"
						+ " where userinfo.userid=?" ;
				ps = connection.prepareStatement(query);
				ps.setString(1, user.getUserId());
				

				rs=ps.executeQuery();
			}
				
			ps.setString(1, user.getUserId());
			
			while(rs.next()) {
				
			 userinfo = new UserInfo();

			 
			 userinfo.setUserId(rs.getString(1));
			 userinfo.setFirstName(rs.getString(2));
			 userinfo.setMiddleInitial(rs.getString(3));
			 userinfo.setLastName(rs.getString(4));
			 userinfo.setContactNumber(rs.getString(5));
			 userinfo.setGender(rs.getString(6));
			 userinfo.setYearLevel(rs.getString(7));
			 userinfo.setBirthday(rs.getString(8));
			 userinfo.setUserpic(rs.getString(9));
			 userinfo.setDepartment(rs.getString(12) + "(" + rs.getString(13)+")");
			 userinfo.setProgStudy(rs.getString(10)+"("+rs.getString(11)+")");

			 userInfoList.add(userinfo);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();	
		}
	}
	
	public void addData(Connection connection,UserInfo userinfo) {

		this.connection=connection;
		try 
		{
			
			PreparedStatement ps = null;
			String query;
			
			query="Insert into  userinfo(userid,firstName,middleInitial,LastName,ContactNumber,Gender,ProgCode,DeptCode,YearLevel,Birthday,userpic) VALUES(?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?)";

			ps = connection.prepareStatement(query);
			
			ps.setString(1, userinfo.getUserId());
			ps.setString(2, userinfo.getFirstName());
			ps.setString(3, userinfo.getMiddleInitial());
			ps.setString(4, userinfo.getLastName());
			ps.setString(5, userinfo.getContactNumber());
			ps.setString(6, userinfo.getGender());
			ps.setString(7, userinfo.getProgStudy());
			String[] deptCode = userinfo.getDepartment().split("\\(");
			ps.setString(8, deptCode[0]);
			
			ps.setString(9, userinfo.getYearLevel());
			ps.setString(10, userinfo.getBirthday());
			ps.setString(11, userinfo.getUserpic());
			ps.executeUpdate();
			userInfoList.add(userinfo);
			RefreshList(connection);
			
		}
		catch(SqlIntegrityConstraintViolationException e) {
			System.out.println("Missing or Replicated Value Error");			
		}
		catch(SQLException e) {
			System.out.print("Something is wrong in db or queue....");
		}
				
	}
	
	public void EditData(Connection connection,UserInfo userinfo) {
		userInfoList = new ArrayList<>();
	    
		
		this.connection=connection;
		try 
		{
			
			PreparedStatement ps = null;
			String query;
			
			
			query="Update UserInfo set firstname=?,MiddleInitial=?,Lastname=?,contactNumber=?,gender=?,progCode=?,deptCode=?,yearLevel=?,birthday=? WHERE userid=?";
			
			ps = connection.prepareStatement(query);
			
			ps.setString(1, userinfo.getFirstName());
			ps.setString(2, userinfo.getMiddleInitial());
			ps.setString(3, userinfo.getLastName());
			ps.setString(4, userinfo.getContactNumber());
			ps.setString(5, userinfo.getGender());
			
			ps.setString(6, userinfo.getProgStudy());
			String[] deptCode = userinfo.getDepartment().split("\\(");
			ps.setString(7, deptCode[0]);
			
			ps.setString(8, userinfo.getYearLevel());
			ps.setString(9, userinfo.getBirthday());
			ps.setString(10, userinfo.getUserId().trim());
			
			ps.executeUpdate();
			
			RefreshList(connection);
		}
		catch(SqlIntegrityConstraintViolationException e) {
			System.out.println("Missing or Replicated Value Error");			
		}
		catch(SQLException e) {
			System.out.print("Something is wrong in db or queue....");
		}
			
	}

	public void DeleteData(Connection connection, UserInfo userinfo) {
	userInfoList = new ArrayList<>();
	    
		
		this.connection=connection;
		try 
		{
			 PreparedStatement s = null;
			 String query;
			 query = "DELETE FROM UserInfo WHERE userID=? " ;
			 
			 s=connection.prepareStatement(query);		
			 
			 s.setString(1, userinfo.getUserId());
			 
			 
			 
			 s.executeUpdate();
			 // update customerList
			 userInfoList.remove(userinfo);	
			 RefreshList(connection);
			 
			
		}
		catch(SqlIntegrityConstraintViolationException e) {
			System.out.println(userinfo.getUserId());
			System.out.println("Missing or Replicated Value Error");			
		}
		catch(SQLException e) {
			System.out.print("Something is wrong in db or queue....");
		}
			
	}

	public UserInfo GetFirstUserInfo() { 
		recordPointer = 0;
		return userInfoList.get(recordPointer);
	}
		
	public UserInfo GetLastUserInfo() {
		recordPointer = userInfoList.size()-1;
		return userInfoList.get(recordPointer);
	}
	public UserInfo getNextUserInfo() {
		recordPointer++;
		if(recordPointer > userInfoList.size()-1)
			recordPointer = userInfoList.size()-1;
		
		return userInfoList.get(recordPointer);
	}
	public UserInfo getPreviousCustomer()
	{
		recordPointer --;
		if(recordPointer < 0)
			recordPointer =0;
		return userInfoList.get(recordPointer);
	}
	
	public List<UserInfo> getUserInfoList(){
		return userInfoList;
	}
	public void RefreshList(Connection connection) 
	{
		
		userInfoList = new ArrayList<UserInfo>();
		
		this.connection=connection;
		try 
		{
			
			PreparedStatement ps = null;
			String query;
			
			query="select userinfo.userid,userinfo.firstname,userinfo.middleinitial,userinfo.lastname,userinfo.contactnumber,userinfo.gender,userinfo.yearlevel,userinfo.birthday,userinfo.userpic,userinfo.progcode,progStudy.progName,userinfo.deptCode,department.deptName from userinfo JOIN department ON userinfo.deptCode = department.deptCode JOIN progStudy ON userinfo.progCode=progStudy.progCode";

			ps = connection.prepareStatement(query);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				
			 userinfo = new UserInfo();

			 
			 userinfo.setUserId(rs.getString(1));
			 userinfo.setFirstName(rs.getString(2));
			 userinfo.setMiddleInitial(rs.getString(3));
			 userinfo.setLastName(rs.getString(4));
			 userinfo.setContactNumber(rs.getString(5));
			 userinfo.setGender(rs.getString(6));
			 userinfo.setYearLevel(rs.getString(7));
			 userinfo.setBirthday(rs.getString(8));
			 userinfo.setUserpic(rs.getString(9));
			 userinfo.setDepartment(rs.getString(12) + "(" + rs.getString(13)+")");
			 userinfo.setProgStudy(rs.getString(10)+"("+rs.getString(11)+")");

			 userInfoList.add(userinfo);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();	
		}
	}
}
