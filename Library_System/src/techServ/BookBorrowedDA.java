package techServ;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ibm.db2.jcc.am.BatchUpdateException;

import domain.BookBorrowed;
import domain.SelectedBook;
import domain.User;
import domain.UserInfo;

public class BookBorrowedDA 
{

	private SelectedBook selectedB;
	private SimpleDateFormat sdf;
	private ResultSet rs;
	private Date resultdate;
	private Connection connection;
	BookBorrowed bookb;
	private List<SelectedBook> selectedBList = new ArrayList<SelectedBook>();
	private List<BookBorrowed> bookBList = new ArrayList<BookBorrowed>();
	
	public BookBorrowedDA(Connection connection)
	{
		
		selectedB = new SelectedBook();

		bookb = new BookBorrowed();
		this.connection=connection;
	}
	
	public void confirmBooks(BookBorrowed bookBR, User user)
	{
		
		bookb= bookBR;

		
		  try {
					PreparedStatement ps = null;
					String query;
					
					query="Insert into bookborrow(Transactionnumber,userid,bookCode,booktitle,bookAuthor,dateissued,duedate,penalty)values(?, ?, ?, ?, ?, ?, ?, 	?)";

					ps = connection.prepareStatement(query);
			
					int i=0;
					
			        for (SelectedBook selectedB : getSelectedBList()) {

						ps.setString(1, bookb.getTransNumber());
						ps.setString(2, user.getUserId());
						System.out.println(user.getUserId()+"UNique");
						ps.setTimestamp(6, bookb.getBorrowedDate());
						System.out.println(bookb.getBorrowedDate());
						ps.setTimestamp(7, bookb.getDueDate());
						System.out.println(bookb.getReturnDate());
						ps.setString(3, selectedB.getBookCode());
						System.out.println(selectedB.getBookCode());
						ps.setString(4, selectedB.getBookName());
						System.out.println(selectedB.getBookName());				
						ps.setString(5, selectedB.getBookAuthor());
						System.out.println(selectedB.getBookAuthor());
						ps.setString(8, "None");
			            ps.addBatch();			            

				           i++; 
			            
			            if(getSelectedBList().size()==i)
			            {
					        System.out.println("PASOK!!!");
					        ps.executeBatch();
					        
			            }
			            
			      }
			    
			}
		  catch (BatchUpdateException ex) {
//            ex.printStackTrace();
            System.out.println(ex.getNextException());
		  }
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void getHistory(Connection connection)
	{
		this.connection=connection;
		try 
		{
			
			PreparedStatement ps = null;
			String query;
						
			query="select * from bookborrow";

			ps = connection.prepareStatement(query);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
			
				bookb = new BookBorrowed();
				selectedB = new SelectedBook();
				
				bookb.setTransNumber(rs.getString(1));
				bookb.setUserID(rs.getString(2));
				selectedB.setBookCode(rs.getString(3));
				selectedB.setBookName(rs.getString(4));
				selectedB.setBookAuthor(rs.getString(5));
				bookb.setBorrowedDate(rs.getTimestamp(6));
				bookb.setDueDate(rs.getTimestamp(7));
				bookb.setReturnDate(rs.getTimestamp(8));
				bookb.setPenalty(rs.getString(9));
				bookb.setSelectedBooks(selectedB);
				
				bookBList.add(bookb);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();	
		}

	}
	
	 
	//TODO history of history
	public void getUserHistory(Connection connection,User user)
	{
		
		this.connection=connection;
		try 
		{
			
			PreparedStatement ps = null;
			String query;
						
			query="select * from bookborrow where userID=?";

			ps = connection.prepareStatement(query);
			
			ps.setString(1, user.getUserId());
			
			rs=ps.executeQuery();
			
			while(rs.next())
			{
			
				bookb = new BookBorrowed();
				selectedB = new SelectedBook();
				
				bookb.setTransNumber(rs.getString(1));
				bookb.setUserID(rs.getString(2));
				selectedB.setBookCode(rs.getString(3));
				selectedB.setBookName(rs.getString(4));
				selectedB.setBookAuthor(rs.getString(5));
				bookb.setBorrowedDate(rs.getTimestamp(6));
				bookb.setDueDate(rs.getTimestamp(7));
				bookb.setReturnDate(rs.getTimestamp(8));
				bookb.setPenalty(rs.getString(9));
				bookb.setSelectedBooks(selectedB);
				
				bookBList.add(bookb);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();	
		}
		
		
	}
	
	public void setSelectedBook(SelectedBook selected)
	{
		selectedBList.add(selected);
	}
			
	
	public List<BookBorrowed> getHistoryList()
	{
	
		return bookBList;
	}
	
	public List<SelectedBook> getSelectedBList()
	{
		return selectedBList;
	}
	
}
