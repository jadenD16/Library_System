package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
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
	
	private JButton btnUpload;
	
	private JLabel firstName,picturelbl,lblBookcode,lblAuthor,lblYearPublished,lblCategory,lblLocation
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
	private JTextArea descTF;
	
	public AddUI(Connection connection) {
		this.connection = connection;
		contentPanel = new JPanel();
				
		dispose();
		setSize(667, 472);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		bookAuthorDA = new BookAuthorDA(connection);
		sectionDA = new SectionDA(connection);
		categoryDA=new CategoryDA(connection);
		bookDA= new BooksDA(connection);
		book = new Books();
		
		firstName = new JLabel("Title");
		firstName.setFont(new Font("Tahoma", Font.BOLD, 13));
		firstName.setBounds(163, 43, 42, 16);
		contentPanel.add(firstName);
		
		titleTF = new JTextField();
		titleTF.setBounds(267, 42, 153, 20);
		contentPanel.add(titleTF);
		titleTF.setColumns(10);
		
		picturelbl = new JLabel("New label");
		picturelbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		picturelbl.setBounds(10, 11, 125, 129);
		contentPanel.add(picturelbl);
		
		btnUpload = new JButton("Upload");
		btnUpload.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpload.setBounds(10, 143, 125, 23);
		contentPanel.add(btnUpload);
		
		lblBookcode = new JLabel("Book Code");
		lblBookcode.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBookcode.setBounds(163, 14, 73, 16);
		contentPanel.add(lblBookcode);

		bookCodeTF = new JTextField();
		bookCodeTF.setColumns(10);
		bookCodeTF.setBounds(267, 11,114, 20);
		bookCodeTF.setEditable(false);
		
		book=bookDA.GetLastBookInfo();
		Integer Book = Integer.valueOf(book.getBookCode())+1;
		bookCodeTF.setText(String.format("%05d", Book ));
		contentPanel.add(bookCodeTF);
		
		lblAuthor = new JLabel("Author");
		lblAuthor.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAuthor.setBounds(162, 74, 55, 16);
		contentPanel.add(lblAuthor);
		
		JLabel lblAuthorCode = new JLabel("Author Code");
		lblAuthorCode.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAuthorCode.setBounds(391, 11, 88, 16);
		contentPanel.add(lblAuthorCode);
		
		authorCode = new JTextField();
		authorCode.setColumns(10);
		authorCode.setBounds(482, 11, 114, 20);
		authorCode.setEditable(false);
		
		author=bookDA.GetLastAuthorInfo();
		Integer Author = Integer.valueOf(author.getAuthorID())+1;
		authorCode.setText(String.format("%05d", Author));
		
		contentPanel.add(authorCode);
		
		fnameTF = new JTextField();
		fnameTF.setBounds(267, 73, 64, 20);
		contentPanel.add(fnameTF);
		fnameTF.setColumns(10);
		
		middleTF = new JTextField();
		middleTF.setBounds(332, 73, 29, 20);
		contentPanel.add(middleTF);
		middleTF.setColumns(10);
		
		lnameTF = new JTextField();
		lnameTF.setBounds(362, 73, 60, 20);
		contentPanel.add(lnameTF);
		lnameTF.setColumns(10);
		
		lblYearPublished = new JLabel("Year Published");
		lblYearPublished.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblYearPublished.setBounds(163, 106, 109, 16);
		contentPanel.add(lblYearPublished);
	
		yearChooser = new JYearChooser();
		yearChooser.setBounds(322, 105, 101, 20);
		contentPanel.add(yearChooser);
		
		lblCategory = new JLabel("Section");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCategory.setBounds(162, 168, 73, 16);
		contentPanel.add(lblCategory);
		
		sectionCB = new JComboBox();
		sectionCB.setBounds(212, 167, 88, 20);
		for(Section section: sectionDA.getSectionList()) 
		{
			sectionCB.addItem(section.getSectionCode());
		}
		contentPanel.add(sectionCB);
		
		lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLocation.setBounds(163, 141, 73, 16);
		contentPanel.add(lblLocation);
		
		lblLocation_1 = new JLabel("Category");
		lblLocation_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLocation_1.setBounds(310, 168, 73, 16);
		contentPanel.add(lblLocation_1);
		
		categoryCB = new JComboBox();
		categoryCB.setBounds(377, 167, 88, 20);
		for(Category category: categoryDA.getCategoryList())
		{
			categoryCB.addItem(category.getCategoryCode());
		}
		contentPanel.add(categoryCB);
		
		lblNumber = new JLabel("Shelf No.");
		lblNumber.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNumber.setBounds(475, 170, 73, 16);
		contentPanel.add(lblNumber);
		
		shelfNoCB = new JComboBox();
		shelfNoCB.setBounds(537, 167, 88, 20);
		shelfNoCB.setModel(new DefaultComboBoxModel(new String[]{"1","2","3","4","5","6","7","8","9","10"}));
		contentPanel.add(shelfNoCB);
		
		descTF = new JTextArea();
		descTF.setBounds(10, 205, 619, 167);
		contentPanel.add(descTF);
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
	
	
	public void getData()
	{
		
		titleTF.getText();
		bookCodeTF.getText();
		authorTF.getText();
		descTF.getText();
		yearPublishedTF.getText();
		
		
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
		{
			System.out.println(book.getBookCode());
			dispose();
		}
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
					book.setDesc(descTF.getText());
					
					book.setYearPub(Integer.toString(yearChooser.getYear()));
					author.setAuthorID(authorCode.getText());
					author.setFname(fnameTF.getText());
					author.setMiddleInitial(middleTF.getText());
					author.setLname(lnameTF.getText());
					
					bookAuthor.setBookCode(bookCodeTF.getText());
					bookAuthor.setAuthorId(authorCode.getText());
					
					bookDA.AddBooks(connection, book, author,bookAuthor);
					System.out.println("ediwow");
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
