package techServ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Department;
import domain.UserInfo;

public class DepartmentDA {

	private Department department;
	private List<Department> deptList;
	private Connection connection;
	private ResultSet rs;
	
	public DepartmentDA(Connection connection) {
		this.connection=connection;
		deptList = new ArrayList<Department>();
		
		try {
			PreparedStatement ps = null;
			String query;
			query = "Select * from Department";
			
			ps =connection.prepareStatement(query);
			rs=ps.executeQuery();
			
			
			while(rs.next()) {
				department = new Department();
				department.setDeptCode(rs.getString(1));
				department.setDeptName(rs.getString(2));
				
				deptList.add(department);
				
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public List<Department> getdeptList(){
		return deptList;
	}
	
	
	
}
