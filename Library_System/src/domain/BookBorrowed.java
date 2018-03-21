package domain;

import java.sql.Date;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;

public class BookBorrowed 
{

	private String transNumber,penalty,userID;
	private Timestamp returnDate, borrowedDate,dueDate;
	private SelectedBook selectedBooks;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getPenalty() {
		return penalty;
	}
	public void setPenalty(String penalty) {
		this.penalty = penalty;
	}
	public Timestamp getDueDate() {
		return dueDate;
	}
	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}
	public String getTransNumber() {
		return transNumber;
	}
	public void setTransNumber(String transNumber) {
		this.transNumber = transNumber; 
	}
	public Timestamp getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Timestamp dueDateS) {
		this.returnDate = dueDateS;
	}
	public Timestamp getBorrowedDate() {
		return borrowedDate;
	}
	public void setBorrowedDate(Timestamp dDate) {
		this.borrowedDate = dDate;
	}
	public SelectedBook getSelectedBooks() {
		return selectedBooks;
	}
	public void setSelectedBooks(SelectedBook selectedBooks) {
		this.selectedBooks = selectedBooks;
	}
		
}
