package domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BookBorrowed 
{

	private Integer transNumber;
	private Date returnDate,borrowedDate;
	private SelectedBook selectedBooks;
	
	public Integer getTransNumber() {
		return transNumber;
	}
	public void setTransNumber(Integer transNumber) {
		this.transNumber = transNumber;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public Date getBorrowedDate() {
		return borrowedDate;
	}
	public void setBorrowedDate(Date borrowedDate) {
		this.borrowedDate = borrowedDate;
	}
	public SelectedBook getSelectedBooks() {
		return selectedBooks;
	}
	public void setSelectedBooks(SelectedBook selectedBooks) {
		this.selectedBooks = selectedBooks;
	}
		
}
