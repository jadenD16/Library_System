package techServ;

import java.util.ArrayList;
import java.util.List;

import domain.SelectedBook;

public class BookBorrowedDA 
{

	private SelectedBook selectedB;
	private List<SelectedBook> selectedBList = new ArrayList<SelectedBook>();
	
	public BookBorrowedDA()
	{
		selectedB = new SelectedBook();
	}
	
	
	
	public void setSelectedBook(SelectedBook selected)
	{
		System.out.println(selected.getBookAuthor());
		selectedBList.add(selected);
	}
	
	public List<SelectedBook> getSelectedBList()
	{
		return selectedBList;
	}
	
}
