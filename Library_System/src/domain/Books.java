package domain;

import java.sql.Date;

public class Books
{

	private String bookName,
	bookPict,
	bookAuthor,
	section,
	category,
	bookCode,
	desc,
	shelfNumber, yearPub;
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getShelfNumber() {
		return shelfNumber;
	}

	public void setShelfNumber(String string) {
		this.shelfNumber = string;
	}

	public String getYearPub() {
		return yearPub;
	}

	public void setYearPub(String yearPub) {
		this.yearPub = yearPub;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
	public String getBookName() {
		return bookName;
	}
	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public String getBookPict() {

		return bookPict;
	}
	
	public void setBookPict(String bookPict) {
		this.bookPict = bookPict;
	}
	
	public String getBookAuthor() {
		return bookAuthor;
	}
	
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	
	public String getBookCode() {
		return bookCode;
	}
	
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	
	
	
}