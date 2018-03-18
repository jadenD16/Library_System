package techServ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.BookAuthor;
import domain.Category;

public class BookAuthorDA {
	private Connection connection;
	private BookAuthor bookAuthor;
	private List<BookAuthor> bookAuthorList;
	private ResultSet rs;
	public BookAuthorDA(Connection connection){
		bookAuthorList = new ArrayList<BookAuthor>();
		this.connection = connection;
		
		try{
			PreparedStatement s= null;
			String query;
			
			query="SELECT * FROM BookAuthor";
			s=connection.prepareStatement(query);
			rs=s.executeQuery();
			
			while(rs.next()){
			
			bookAuthor = new BookAuthor();
			bookAuthor.setBookCode(rs.getString(1));
			bookAuthor.setAuthorId(rs.getString(2));
			bookAuthorList.add(bookAuthor);
			
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	public List<BookAuthor> getBookauthorList(){
		return bookAuthorList;
	}
}
	

