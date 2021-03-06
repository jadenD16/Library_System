package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JYearChooser;

import domain.Author;
import domain.BookAuthor;
import domain.Books;
import domain.Category;
import domain.Section;
import techServ.BookAuthorDA;
import techServ.BooksDA;
import techServ.CategoryDA;
import techServ.SectionDA;

import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class AddUI extends JDialog implements ActionListener {

	private final JPanel contentPanel;
	
	private JTextField titleTF,bookCodeTF,authorTF,yearPublishedTF,yearPubTF,fnameTF,middleTF,lnameTF,authorCode;
	
	private JComboBox sectionCB,categoryCB,shelfNoCB;
	
	private JLabel firstName,lblBookcode,lblAuthor,lblYearPublished,lblCategory,lblLocation
					,lblLocation_1,lblNumber;
	private JYearChooser yearChooser;
	private Section section;
	private SectionDA sectionDA;
	private Category category;
	private CategoryDA categoryDA;
	private Books book;
	private BooksDA bookDA;
	private Author author;
	private BookAuthor bookAuthor;
	private BookAuthorDA bookAuthorDA;
	private Connection connection;
	private UserInformationUI userinformationUI;
	
	public AddUI(Connection connection) {
		this.connection = connection;
		contentPanel = new JPanel();
				
		dispose();
		setSize(599, 266);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
<<<<<<< HEAD
		getContentPane().add(contentPanel, BorderLayout.CENTER);
//		contentPanel.setBackground(new Color(51, 153, 153));
		
		contentPanel.setLayout(null);
=======
		getContentPane().add(contentPanel);
>>>>>>> 4645d871a8e3f8357321340002a7135851cc2b8d
		
		bookAuthorDA = new BookAuthorDA(connection);
		sectionDA = new SectionDA(connection);
		categoryDA=new CategoryDA(connection);
		bookDA= new BooksDA(connection);
		book = new Books();
		contentPanel.setLayout(null);
		contentPanel.setLayout(null);
		
		firstName = new JLabel("Title");
		firstName.setBounds(10, 43, 42, 16);
		firstName.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPanel.add(firstName);
		
		titleTF = new JTextField();
		titleTF.setBounds(103, 42, 153, 20);
		contentPanel.add(titleTF);
		titleTF.setColumns(10);
		
		lblBookcode = new JLabel("Book Code");
		lblBookcode.setBounds(10, 12, 73, 16);
		lblBookcode.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPanel.add(lblBookcode);

		bookCodeTF = new JTextField();
		bookCodeTF.setBounds(103, 11, 153, 20);
		bookCodeTF.setColumns(10);
		bookCodeTF.setEditable(false);
		
		book=bookDA.GetLastBookInfo();
		Integer Book = Integer.valueOf(book.getBookCode())+1;
		bookCodeTF.setText(String.format("%05d", Book ));
		contentPanel.add(bookCodeTF);
		
		lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(10, 74, 55, 16);
		lblAuthor.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPanel.add(lblAuthor);
		
		JLabel lblAuthorCode = new JLabel("Author Code");
		lblAuthorCode.setBounds(279, 12, 88, 16);
		lblAuthorCode.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPanel.add(lblAuthorCode);
		
		authorCode = new JTextField();
		authorCode.setBounds(420, 11, 153, 20);
		authorCode.setColumns(10);
		authorCode.setEditable(false);
		
		author=bookDA.GetLastAuthorInfo();
		Integer Author = Integer.valueOf(author.getAuthorID())+1;
		authorCode.setText(String.format("%05d", Author));
		
		contentPanel.add(authorCode);
		
		fnameTF = new JTextField();
		fnameTF.setBounds(103, 73, 64, 20);
		contentPanel.add(fnameTF);
		fnameTF.setColumns(10);
		
		middleTF = new JTextField();
		middleTF.setBounds(167, 73, 29, 20);
		contentPanel.add(middleTF);
		middleTF.setColumns(10);
		
		lnameTF = new JTextField();
		lnameTF.setBounds(196, 73, 60, 20);
		contentPanel.add(lnameTF);
		lnameTF.setColumns(10);
		
		lblYearPublished = new JLabel("Year Published");
		lblYearPublished.setBounds(279, 43, 109, 16);
		lblYearPublished.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPanel.add(lblYearPublished);
	
		
		lblCategory = new JLabel("Section");
		lblCategory.setBounds(10, 129, 73, 16);
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPanel.add(lblCategory);
		
		sectionCB = new JComboBox();
		sectionCB.setBounds(79, 128, 88, 20);
		for(Section section: sectionDA.getSectionList()) 
		{
			sectionCB.addItem(section.getSectionCode());
		}
		contentPanel.add(sectionCB);
		
		lblLocation = new JLabel("Location:");
		lblLocation.setBounds(10, 101, 73, 16);
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPanel.add(lblLocation);
		
		lblLocation_1 = new JLabel("Category");
		lblLocation_1.setBounds(196, 129, 73, 16);
		lblLocation_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPanel.add(lblLocation_1);
		
		categoryCB = new JComboBox();
		categoryCB.setBounds(279, 128, 88, 20);
		for(Category category: categoryDA.getCategoryList())
		{
			categoryCB.addItem(category.getCategoryCode());
		}
		contentPanel.add(categoryCB);
		
		lblNumber = new JLabel("Shelf No.");
		lblNumber.setBounds(377, 129, 73, 16);
		lblNumber.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPanel.add(lblNumber);
		
		shelfNoCB = new JComboBox();
		shelfNoCB.setBounds(460, 128, 88, 20);
		shelfNoCB.setModel(new DefaultComboBoxModel(new String[]{"1","2","3","4","5","6","7","8","9","10"}));
		contentPanel.add(shelfNoCB);
		

		
		 yearChooser = new JYearChooser();
		yearChooser.setBounds(475, 43, 98, 20);
		contentPanel.add(yearChooser);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);;
				buttonPane.add(cancelButton);
			}
		}
		
		setVisible(true);
		setLocationRelativeTo(null);		
	}
	public boolean isFilled() {
		
		if(!fnameTF.getText().trim().equals("") && !middleTF.getText().trim().equals("") && !lnameTF.getText().trim().equals("") && !titleTF.getText().trim().equals("")) {
			return true;
		}
		
		return false;
	}
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		
		String action = event.getActionCommand();
		
		if(action.equalsIgnoreCase("Cancel"))
			dispose();
		else if(action.equalsIgnoreCase("Save"))
		{
			
			if(isFilled()) 
			{
				if((fnameTF.getText().matches("^[a-zA-Z]*$")) && (middleTF.getText().matches("^[a-zA-Z]*$")) && (lnameTF.getText().matches("^[a-zA-Z]*$")) && (titleTF.getText().matches("^[a-zA-Z0-9]*$"))) 
				{
					book = new Books();
					author = new Author();
					bookAuthor = new BookAuthor();
					
					book.setBookCode(bookCodeTF.getText());
					book.setBookName(titleTF.getText());
					book.setSection(sectionCB.getSelectedItem().toString());
					book.setShelfNumber(shelfNoCB.getSelectedItem().toString());
					book.setCategory(categoryCB.getSelectedItem().toString());
					
					book.setYearPub(Integer.toString(yearChooser.getYear()));
					author.setAuthorID(authorCode.getText());
					author.setFname(fnameTF.getText());
					author.setMiddleInitial(middleTF.getText());
					author.setLname(lnameTF.getText());
					
					bookAuthor.setBookCode(bookCodeTF.getText());
					bookAuthor.setAuthorId(authorCode.getText());
					
					bookDA.AddBooks(connection, book, author,bookAuthor);
					repaint();
					revalidate();
					dispose();
					}
					else
						JOptionPane.showMessageDialog(null, "Invalid Inputs");
			}
			else

				JOptionPane.showMessageDialog(null, "Please Fill Up the Blanks");
		}
	}
}

