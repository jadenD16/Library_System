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
import domain.SelectedBook;
import techServ.BookAuthorDA;
import techServ.BooksDA;
import techServ.CategoryDA;
import techServ.SectionDA;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Year;
import java.awt.event.ActionEvent;

public class UpdateUI extends JDialog implements ActionListener {

	private final JPanel contentPanel;
	
	private JTextField titleTF,bookCodeTF,authorTF,descTF,yearPublishedTF,yearPubTF,fnameTF,middleTF,lnameTF,authorCode;
	
	private JComboBox sectionCB,categoryCB,shelfNoCB;
	
	private JButton btnUpload;
	
	private JLabel firstName,picturelbl,lblBookcode,lblAuthor,lblDescription,lblYearPublished,lblCategory,lblLocation
					,lblLocation_1,lblNumber;
	private JYearChooser yearChooser;
	private Section section;
	private SectionDA sectionDA;
	private Category category;
	private CategoryDA categoryDA;
	private BooksDA bookDA;
	private Author author;
	private BookAuthor bookAuthor;
	private BookAuthorDA bookAuthorDA;
	private SelectedBook selected;
	private Connection connection;
	private UserInformationUI userinformationUI;
	private LibrarySystemMain lbmain;
	public UpdateUI(Connection connection, Books book) {
		this.connection = connection;
		contentPanel = new JPanel();
		setSize(667, 472);
		setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		bookAuthorDA = new BookAuthorDA(connection);
		sectionDA = new SectionDA(connection);
		categoryDA=new CategoryDA(connection);
		bookDA= new BooksDA(connection);
		
		firstName = new JLabel("Title");
		firstName.setFont(new Font("Tahoma", Font.BOLD, 13));
		firstName.setBounds(163, 43, 42, 16);
		contentPanel.add(firstName);
		
		titleTF = new JTextField();
		titleTF.setBounds(267, 42, 153, 20);
		contentPanel.add(titleTF);
		titleTF.setColumns(10);
		titleTF.setText(book.getBookName());
		System.out.println(book.getBookName());
		
		picturelbl = new JLabel("New label");
		picturelbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		picturelbl.setBounds(10, 11, 125, 129);
		contentPanel.add(picturelbl);
		
		btnUpload = new JButton("Upload");
		btnUpload.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpload.setBounds(10, 143, 125, 23);
		btnUpload.addActionListener(this);
		contentPanel.add(btnUpload);
		
		lblBookcode = new JLabel("Book Code");
		lblBookcode.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBookcode.setBounds(163, 14, 73, 16);
		contentPanel.add(lblBookcode);

		bookCodeTF = new JTextField();
		bookCodeTF.setColumns(10);
		bookCodeTF.setBounds(267, 11,114, 20);
		bookCodeTF.setEditable(false);
		bookCodeTF.setText(book.getBookCode());
//		book=bookDA.GetLastBookInfo();
//		Integer Book = Integer.valueOf(book.getBookCode())+1;
//		bookCodeTF.setText(String.format("%05d", Book ));
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
		contentPanel.add(authorCode);
		
		fnameTF = new JTextField();
		fnameTF.setBounds(267, 73, 64, 20);
		contentPanel.add(fnameTF);
		fnameTF.setColumns(10);
//		System.out.println(author[0].trim());
		
		middleTF = new JTextField();
		middleTF.setBounds(332, 73, 29, 20);
		contentPanel.add(middleTF);
		middleTF.setColumns(10);
		
		lnameTF = new JTextField();
		lnameTF.setBounds(362, 73, 60, 20);
		contentPanel.add(lnameTF);
		lnameTF.setColumns(10);
//		lnameTF.setText(author[2].trim());
		
		String[] author = book.getBookAuthor().split(" ");
		System.out.println(author.length+" First:"+author[0]+" last:"+author[3]);
		if(author.length == 4) {
			
			fnameTF.setText(author[0]);
			lnameTF.setText(author[3]);
			middleTF.setText(" ");
		}
		else {
			fnameTF.setText(author[0]);
			middleTF.setText(author[1]);
			lnameTF.setText(author[2]);
			
		}
		
		
		lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDescription.setBounds(10, 196, 93, 16);
		contentPanel.add(lblDescription);
		
		descTF = new JTextField();
		descTF.setColumns(10);
		descTF.setBounds(10, 215, 631, 157);
		contentPanel.add(descTF);
		
		lblYearPublished = new JLabel("Year Published");
		lblYearPublished.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblYearPublished.setBounds(163, 106, 109, 16);
		contentPanel.add(lblYearPublished);
	
		yearChooser = new JYearChooser();
		yearChooser.setBounds(322, 105, 101, 20);
		contentPanel.add(yearChooser);
		yearChooser.setYear(Integer.parseInt(book.getYearPub()));
		
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
		sectionCB.setSelectedItem(book.getSection());
		
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
		categoryCB.setSelectedItem(book.getCategory());
		
		
		lblNumber = new JLabel("Shelf No.");
		lblNumber.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNumber.setBounds(475, 170, 73, 16);
		contentPanel.add(lblNumber);
		
		shelfNoCB = new JComboBox();
		shelfNoCB.setBounds(537, 167, 88, 20);
		shelfNoCB.setModel(new DefaultComboBoxModel(new String[]{"1","2","3","4","5","6","7","8","9","10"}));
		shelfNoCB.setSelectedItem(book.getShelfNumber());
		
		contentPanel.add(shelfNoCB);
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
		else if(action.equalsIgnoreCase("Save")) {
			
		}
	}
	}

	
