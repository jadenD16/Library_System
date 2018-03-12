package techServ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Section;

public class SectionDA {
	private Connection connection;
	private Section section;
	private List<Section> sectionList;
	private ResultSet rs;
	public SectionDA(Connection connection){
		sectionList = new ArrayList<Section>();
		this.connection = connection;
		
		try{
			PreparedStatement s= null;
			String query;
			
			query="SELECT * FROM SECTION";
			s=connection.prepareStatement(query);
			rs=s.executeQuery();
			
			while(rs.next()){
			
			section = new Section();
			section.setSectionCode(rs.getString(1));
			section.setSectionName(rs.getString(2));
			
			sectionList.add(section);	
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	public List<Section> getSectionList(){
		return sectionList;
	}
}
