package techServ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.ProgStudy;

public class ProgStudyDA {
	private ProgStudy progStudy;
	private List<ProgStudy> progList;
	private ResultSet rs;
	private Connection connection;
	
	public ProgStudyDA(Connection connection) {
		progList = new ArrayList<ProgStudy>();
		
		try {
			PreparedStatement ps = null;
			String query;
			query="Select * from progstudy";
			
			ps=connection.prepareStatement(query);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				progStudy = new ProgStudy();
				
				progStudy.setProgCode(rs.getString(1));
				progStudy.setProgName(rs.getString(2));
				
				progList.add(progStudy);
			}
			
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public List<ProgStudy> getProgList(){
		return progList;
	}
}
