package techServ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Category;
import domain.Section;

public class CategoryDA {
	private Connection connection;
	private Category category;
	private List<Category> categoryList;
	private ResultSet rs;
	public CategoryDA(Connection connection){
		categoryList = new ArrayList<Category>();
		this.connection = connection;
		
		try{
			PreparedStatement s= null;
			String query;
			
			query="SELECT * FROM CATEGORY";
			s=connection.prepareStatement(query);
			rs=s.executeQuery();
			
			while(rs.next()){
			
			category = new Category();
			category.setCategoryCode(rs.getString(1));
			category.setCategoryName(rs.getString(2));
			categoryList.add(category);	
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	public List<Category> getCategoryList(){
		return categoryList;
	}
}
