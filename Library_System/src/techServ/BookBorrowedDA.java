package techServ;

import java.util.ArrayList;
import java.util.List;

import domain.Books;

public class BookBorrowedDA 
{

	List<Books> bookBorrowedList;
	
	public BookBorrowedDA(Books book)
	{
		 book = new Books();
		
		bookBorrowedList = new ArrayList<Books>();
		
		bookBorrowedList.add(book);
		
	}
	
	
	public void addBook()
	{
		
	}
	
	public void removedBook()
	{
	
	}
	
	
}
