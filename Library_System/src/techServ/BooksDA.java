package techServ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Author;
import domain.Books;


public class BooksDA 
{
	
	private Connection connection;
	private ResultSet	rs;
	private Books books;
	private Author author;	
	private List<Books> bookList = new ArrayList();
	
	
	public BooksDA(Connection connection)
	{
		
		this.connection = connection;
		try 
		{
			PreparedStatement s = null;
			//prepared chuhcu s will contain the sql statement / query
			String query;
			query = "Select book.BookCode,book.BookName,book.SectionCode ,book.ShelfNumber ,book.CategoryCode ,book.PublishYear ,"
					+ "book.Description,bookAuthor.authorID, author.FirstName,author.MiddleInitial,author.LastName from book join BookAuthor on book.BookCode = BookAuthor.bookCode join author on author.authorID "
					+ "= BookAuthor.authorID";
			
			s= connection.prepareStatement(query);
			rs = s.executeQuery();
			
			while(rs.next())
			{
				
				books = new Books();
	
				books.setBookCode(rs.getString(1));
				books.setBookName(rs.getString(2));
				books.setSection(rs.getString(3));
				books.setShelfNumber(rs.getString(4));
				books.setCategory(rs.getString(5));
				books.setYearPub(rs.getString(6));
				books.setDesc(rs.getString(7));
				
				String mi=rs.getString(10)+".";
				
				if(mi.equals("null."))
					mi=" ";
				books.setBookAuthor(rs.getString(9)+" "+mi+" "+rs.getString(11));
				
				
				bookList.add(books);
				
				
				author = new Author();
				
				author.setAuthorID(rs.getString(8));
				author.setAuthorName(rs.getString(9));
				
				
				
			}
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void AddBook(Connection connection, Books books) {
		
	}
	public List<Books> getBookList()	
	{
		return bookList;
	}
}