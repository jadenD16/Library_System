package techServ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibm.db2.jcc.am.SqlIntegrityConstraintViolationException;

import domain.Author;
import domain.BookAuthor;
import domain.Books;
import domain.UserInfo;


public class BooksDA 
{
	
	private Connection connection;
	private ResultSet	rs;
	private Books books;
	private Author author;	
	private BookAuthor bookAuthor;
	private List<Books> bookList;
	private List<Author> authorList;
	private List<BookAuthor> bookAuthorList;
	private Integer recordPointer = 0;
	
	
	public BooksDA(Connection connection)
	{
		bookAuthorList = new ArrayList<>();
		bookList = new ArrayList<Books>();
		authorList = new ArrayList<Author>();
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
				author.setFname(rs.getString(9));
				author.setMiddleInitial(rs.getString(10));
				author.setLname(rs.getString(11));
				authorList.add(author);
				
				
			}
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void AddBooks(Connection connection,Books book,Author author,BookAuthor bookAuthor) {
	
		bookList = new ArrayList<>();
	    authorList = new ArrayList<>();
	    bookAuthorList = new ArrayList<>();
		this.connection=connection;
		try 
		{
			
			PreparedStatement ps = null;
			String query,query1,query2;
			
			query="INSERT INTO Author(authorId,FirstName,MiddleInitial,Lastname)VALUES(?,?,?,?)";

			ps = connection.prepareStatement(query);
			
			ps.setString(1, author.getAuthorID().trim());
			ps.setString(2,author.getFname());
			ps.setString(3, author.getMiddleInitial());
			ps.setString(4, author.getMiddleInitial());
			
			ps.executeUpdate();
			authorList.add(author);
			RefreshList(connection);

			query1 ="INSERT INTO Book(bookCode,bookName,sectionCode,shelfNumber,categoryCode,PublishYear,description)VALUES(?,?,?,?,?,?,?)";
			ps = connection.prepareStatement(query1);
			
			ps.setString(1, book.getBookCode().trim());
			ps.setString(2, book.getBookName());
			ps.setString(3, book.getSection());
			ps.setString(4, book.getShelfNumber());
			ps.setString(5, book.getCategory());
			ps.setString(6, book.getYearPub());
			ps.setString(7, book.getDesc());
			
			System.out.println(book.getBookCode());
			ps.executeUpdate();
			bookList.add(book);
			RefreshList(connection);

			query2="INSERT INTO BookAuthor(bookCode,AuthorId)VALUES(?,?)";
			ps = connection.prepareStatement(query2);
			
			ps.setString(1, bookAuthor.getBookCode());
			ps.setString(2, bookAuthor.getAuthorId());
			
			ps.executeUpdate();
			bookAuthorList.add(bookAuthor);
			RefreshList(connection);

			
		}
		catch(SqlIntegrityConstraintViolationException e) {
			System.out.println("Missing or Replicated Value Error");			
		}
		catch(SQLException e) {
			System.out.print("Something is wrong in db or queue....");
		}
				
	}
	//TODO EDIT BOOK
	
	public void EditBooks(Connection connection,Books book,Author author) {
		
		bookList = new ArrayList<>();
	    authorList = new ArrayList<>();
		this.connection=connection;
		try 
		{
			
			PreparedStatement ps = null;
			String query,query1;
			
			query="Update  Author set firstName=?,middleInitial=?,lastName=? where authorId=?";

			ps = connection.prepareStatement(query);
			ps.setString(1, author.getFname());
			ps.setString(2, author.getMiddleInitial());
			ps.setString(3, author.getLname());
			
			
			ps.executeUpdate();
			RefreshList(connection);

			query1 ="Update Book set bookname=?,sectionCode=?,shelfNumber=?,categoryCode=?,publishYear=? where BookCode=?";
			ps = connection.prepareStatement(query1);
			
			ps.setString(1, book.getBookName());
			ps.setString(2, book.getSection());
			ps.setString(3, book.getShelfNumber());
			ps.setString(4, book.getCategory());
			ps.setString(5, book.getYearPub());
			ps.setString(6, book.getBookCode());
			
			System.out.println(book.getBookCode());
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
	public void RefreshList(Connection connection)
	{
		bookAuthorList = new ArrayList<>();
		bookList = new ArrayList<Books>();
		authorList = new ArrayList<Author>();
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
				author.setFname(rs.getString(9));
				author.setMiddleInitial(rs.getString(10));
				author.setLname(rs.getString(11));
				authorList.add(author);
				
				
			}
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public Books GetLastBookInfo() {
		recordPointer = bookList.size()-1;
		return bookList.get(recordPointer);
	}
	public Author GetLastAuthorInfo() {
		recordPointer = authorList.size()-1;
		return authorList.get(recordPointer);
	}
	public List<Author> getAuthorList()
	{
		return authorList;
	}
	public List<Books> getBookList()	
	{
		return bookList;
	}
}