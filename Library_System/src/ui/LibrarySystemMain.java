package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import domain.Author;
import domain.BookBorrowed;
import domain.Books;
import domain.SelectedBook;
import domain.User;
import techServ.BookBorrowedDA;
import techServ.BooksDA;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

public class LibrarySystemMain extends JFrame implements ActionListener{

	private JPanel contentPane,panel;
	private JTable table;
	private JTextField textField;
	private SelectedBook selectB;
	private JScrollPane scrollPane;	
	
	private JLabel lblNewLabel_1,lblprofile,lblNewLabel  ;
	
	private JButton btnUpdate, btnAdd, btnLogin,btnBorrowedBooks,btnSearch,btnManageBooks,btnViewHistory,btnManageUsers,btnBorrow ;
	
	private Connection connection;
	
	private String[] tableHeader;
	private	UpdateUI updateUI; 
	private BooksDA booksDA;
	private Books book;
	private Author author;
	private DefaultTableModel tableModel;
	private TableColumnModel columnModel;
	private UserInformationUI userinformationUI;
	private List<Books> bookList;
	private ArrayList<Books> bookSearchList;
	
	private AddUI addUI;
	private LoginUI loginUI;
	private User user;	
	private JLabel lblname;
	private SelectedBook selected;
	private BookBorrowedUI bookBorrowedUI;
	private BookBorrowedDA bookBDA;
	private static LibrarySystemMain home;
	
	public static void main(String[]args)
	{
		home = new LibrarySystemMain();
	}
	
	public LibrarySystemMain() 
	{
		getContentPane().setBackground(new Color(0, 0, 255));
		booksDA = new BooksDA(getConnection());
		bookBDA = new BookBorrowedDA(getConnection());
		
		setResizable(false);
		setSize(1370, 718);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Library System");
		setLocationRelativeTo(null);
		getContentPane().setBackground(UIManager.getColor("Button.shadow"));
		getContentPane().setLayout(null);
		panel = new JPanel();
		panel.setBackground(new Color(51, 153, 153));
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(10, 134, 263, 529);
		
		getContentPane().add(panel);
		panel.setLayout(null);
		
//		
//		lblNewLabel = new JLabel("New label");
//		lblNewLabel.setBounds(100, 75, 170, 142);
//		panel.add(lblNewLabel);
		
		lblprofile = new JLabel("");
		lblprofile.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		lblprofile.setBounds(34, 11, 174, 140);
		
		btnBorrowedBooks = new JButton("Borrowing List");
		btnBorrowedBooks.addActionListener(this);
		btnBorrowedBooks.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnBorrowedBooks.setBounds(34, 296, 203, 29);
		
		btnViewHistory = new JButton("View History");
		btnViewHistory.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnViewHistory.setBounds(34, 343, 203, 29);
		btnViewHistory.addActionListener(this);
		
		btnManageBooks = new JButton("Manage Books");
		btnManageBooks.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnManageBooks.setBounds(34, 386, 203, 29);
		btnManageBooks.addActionListener(this);
		//panel.add(btnManageBooks);
		
		btnManageUsers = new JButton("Manage Users");
		btnManageUsers.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnManageUsers.setBounds(34, 426, 203, 29);
		btnManageUsers.addActionListener(this);
		
		lblname = new JLabel("New label");
		lblname.setBounds(44, 162, 134, 14);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(this);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.setBounds(1204, 134, 89, 23);
		getContentPane().add(btnLogin);
		
				
		table = new JTable()
        {
            public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
        };
        
		table.setOpaque(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
		scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(153, 153, 153), new Color(153, 153, 153), new Color(153, 153, 153), new Color(153, 153, 153)));
		scrollPane.setEnabled(false);
		scrollPane.setOpaque(false);
		scrollPane.setBounds(341, 237, 985, 352);
		getContentPane().add(scrollPane);
		
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnUpdate.addActionListener(this);
		btnUpdate.setBounds(875, 615, 111, 35);
		 
		btnAdd = new JButton("ADD");
		btnAdd.addActionListener(this);
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnAdd.setBounds(624, 615, 111, 35);
		btnAdd.addActionListener(this);
		
		textField = new JTextField();
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		textField.setBounds(425, 185, 290, 27);
		textField.setColumns(10);
		getContentPane().add(textField);
		
		btnSearch = new JButton("Search");	
		btnSearch.setBounds(342, 185, 80, 26);
		btnSearch.addActionListener(this);
		getContentPane().add(btnSearch);
	
		
		JLabel lblLibraryManagementSystem = new JLabel("LIBRARY MANAGEMENT SYSTEM");
		lblLibraryManagementSystem.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblLibraryManagementSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibraryManagementSystem.setFont(new Font("Segoe UI Light", Font.BOLD | Font.ITALIC, 60));
		lblLibraryManagementSystem.setForeground(new Color(255,255,255));
		
		lblLibraryManagementSystem.setBackground(new Color(51, 153, 153));
		lblLibraryManagementSystem.setOpaque(true);
		lblLibraryManagementSystem.setBounds(0, 0, 1380, 123);
		getContentPane().add(lblLibraryManagementSystem);
		
		btnBorrow = new JButton("Borrow");
		btnBorrow.setBounds(1237, 209, 89, 23);
		btnBorrow.addActionListener(this);
		
		fillBookTable();
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		
		String action = event.getActionCommand();
		
		if(action.equalsIgnoreCase("Manage Books"))
		{
			getContentPane().add(btnSearch);
			getContentPane().add(textField);
			getContentPane().add(scrollPane);
			getContentPane().add(btnAdd);
			getContentPane().add(btnUpdate);
			remove(userinformationUI);
			repaint();
			
			fillBookTable();
		}
		
		else if(action.equalsIgnoreCase("Search")) {
			tableHeader = new String[]{"Title","Author","Section",
					"Shelf","Category","P.Y"};
			
			tableModel = new DefaultTableModel(tableHeader,0);
			table.setModel(tableModel);
			bookList= booksDA.getBookList();
			
			if(textField.getText().length() != 0 )
			{
				
				if(isValidWord(textField.getText())) 
				{
					bookSearchList = new ArrayList<Books>();
					bookSearchList.clear();
					 
					bookList= (ArrayList<Books>)booksDA.getBookList();
					
					for(Books book : booksDA.getBookList())
					{
						if(book.getBookName().toLowerCase().contains(textField.getText())) 
						{
															
							String[] row = {book.getBookName(),book.getBookAuthor(),book.getSection(),book.getShelfNumber(),
										  book.getCategory(),book.getYearPub()};
							bookSearchList.add(book);
							tableModel.addRow(row);
						}
						
						
					}	
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Incorrect Input");
					fillBookTable();
				}
			}
			else 
				
				fillBookTable();
			
			renderTable();
		}	
		else if(action.equalsIgnoreCase("Add"))
		{
			addUI = new AddUI(connection);
			book = booksDA.GetLastBookInfo();
			author = booksDA.GetLastAuthorInfo();
				
		}
		
		else if(action.equalsIgnoreCase("Update")) 
			getTableValue("Update");			
		
		
		
		else if(action.equalsIgnoreCase("Manage Users")) 
		{
			manageUser(user);
			repaint();
			revalidate();
		}	
		else if(action.equalsIgnoreCase("login"))
			loginUI = new LoginUI(getConnection(),home);

<<<<<<< HEAD
		else if(action.equalsIgnoreCase("Log out")) {
=======
		}
		else if(action.equalsIgnoreCase("Logout")) {
>>>>>>> 4645d871a8e3f8357321340002a7135851cc2b8d
			btnLogin.setText("Login");
			System.out.println("napipindot");
			panel.removeAll();
			fillBookTable();
			remove(btnAdd);
			remove(btnUpdate);
			remove(btnBorrow);
			add(btnSearch);
			add(textField);
			getContentPane().remove(userinformationUI);
			getContentPane().add(scrollPane);
			panel.repaint();
			repaint();
		}
<<<<<<< HEAD
					
=======
		else if(action.equalsIgnoreCase("View Books")) {
			fillBookTable();
		}
			
		else if(action == "Manage Books")
			fillBookTable();

		else if(action == "Manage Users") 
			manageUser();
		
>>>>>>> 4645d871a8e3f8357321340002a7135851cc2b8d
		else if(action.equalsIgnoreCase("View History"))
		{
			fillHistoryTable();
		}
		else if(action.equals("Borrow"))
			getTableValue("Borrow");
			
		
		else if(action.equalsIgnoreCase("Borrowing List"))
			
				bookBorrowedUI = new BookBorrowedUI(bookBDA,user);
		
		else if(action.equalsIgnoreCase("View Profile"))
			manageUser(user);
			repaint();
			revalidate();
	}
		
	
	
	public void loggedIn(String userType, User user)
	{
		this.user = user;
		
		if(userType.equalsIgnoreCase("admin"))
		{
			btnLogin.setText("Logout");
			btnManageUsers.setText("Manage Users");
			panel.add(btnManageUsers);
			panel.add(btnManageBooks);
			panel.add(btnViewHistory);
			
		}
		
		else
		{
			btnLogin.setText("Logout");
			btnManageUsers.setText("View Profile");
			btnManageBooks.setText("View Books");
			btnViewHistory.setText("Borrowing List");
			panel.add(btnManageUsers);
//			panel.add(btnBorrowedBooks);
			panel.add(btnViewHistory);
			panel.add(btnManageBooks);
			getContentPane().add(btnBorrow);
		}
		
		
			panel.repaint();	
			repaint();
		}	
	
	
	public void manageUser(User user) {
		userinformationUI = new UserInformationUI(getConnection(),user);

		remove(btnSearch);
		remove(textField);
		remove(scrollPane);
		remove(btnAdd);
		remove(btnUpdate);

<<<<<<< HEAD
=======
		
>>>>>>> 4645d871a8e3f8357321340002a7135851cc2b8d
		getContentPane().add(userinformationUI);
		repaint();
		
	}
	public static boolean isValidWord(String book) {
		if((book.charAt(0)+"").matches("[a-zA-Z0-9]"))
			return true;

		return false;
	}

	public Connection getConnection()
	{
				
			try
			{
				Class.forName("com.ibm.db2.jcc.DB2Driver");
				connection = DriverManager.getConnection
						("jdbc:db2://localhost:50000/library","Edwin Javinar", "secret");
			}
			catch(ClassNotFoundException e1)
			{
				e1.printStackTrace();
			}
			catch(SQLException e2)
			{
				e2.printStackTrace();
			}
		return connection;
	}

	public void fillBookTable()
	{
		
		tableHeader = new String[]{"Book No.","Title","Author","Section",
					"Shelf","Category","P.Y"};
	
		tableModel = new DefaultTableModel(tableHeader,0)
		{
			boolean[] columnEditables = new boolean[] {
					false, false, false, false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		};
		
		table.setModel(tableModel);		
						
		for(Books book : booksDA.getBookList())
		{
												
				tableModel.addRow(new Object[]{book.getBookCode(),book.getBookName(),book.getBookAuthor(),book.getSection(),book.getShelfNumber(),
							  book.getCategory(),book.getYearPub()});
		}
				
			renderTable();
	}
	public void renderTable()
	{
		columnModel = table.getColumnModel();
	//	tableHead = table.getTableHeader();
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
		for (int column = 0; column < table.getColumnCount(); column++) 
		{
	        int width = 70; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) 
	        {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width+10 , width);
	        }
	        	        	        
	        columnModel.getColumn(column).setPreferredWidth(width);
	        table.getTableHeader().getColumnModel().getColumn(column).setResizable(false);
	    }	
	}

	public void fillHistoryTable()

	{
		tableHeader = new String[]{"Transaction No.","UserID","Book Code","Book Title",
				"Book Author","Date Issued","Due Date" ,"Date Returned","Penalty"};

		
		tableModel = new DefaultTableModel(tableHeader,0)
		{
		boolean[] columnEditables = new boolean[] {
				false, false, false, false,false,false,false,true,false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		
		bookBDA.getHistory(connection);
		
		table.setModel(tableModel);
		for(BookBorrowed borrowed : bookBDA.getHistoryList())
		{
											
			selected = borrowed.getSelectedBooks();
			tableModel.addRow(new Object[]{borrowed.getTransNumber(),borrowed.getUserID(),selected.getBookCode(),selected.getBookName(),selected.getBookAuthor(),
					borrowed.getBorrowedDate(),borrowed.getDueDate(),"-------","-------"});
		}
			
		renderTable();

	}

	public void getTableValue(String button)
	{

		int row=table.getSelectedRow(),
			column=0;
			selected = new SelectedBook();
			book = new Books();
			while(column<7)
			{
				if(column==0)
				{
					selected.setBookCode(String.valueOf(table.getValueAt(row, column)));
					book.setBookCode(String.valueOf(table.getValueAt(row, column)));
				}
				else if(column==1)
				{
					selected.setBookName(String.valueOf(table.getValueAt(row, column)));
					book.setBookName(String.valueOf(table.getValueAt(row, column)));
				}
				else if(column==2)
				{	
					selected.setBookAuthor(String.valueOf(table.getValueAt(row, column)));
					book.setBookAuthor(String.valueOf(table.getValueAt(row, column)));
				}					
				
				else if(column==3)
					book.setShelfNumber(String.valueOf(table.getValueAt(row, column)));
					
				else if(column==4)
					book.setSection(String.valueOf(table.getValueAt(row, column)));
				
				else if(column==5)
				{
					selected.setCategory(String.valueOf(table.getValueAt(row, column)));
					book.setCategory(String.valueOf(table.getValueAt(row, column)));
				}
				else if(column==6)
				{
					selected.setPublishYear(String.valueOf(table.getValueAt(row, column)));
					book.setYearPub(String.valueOf(table.getValueAt(row, column)));
				}
				column++;					
			}
			
			if(button.equalsIgnoreCase("Update"))
				updateUI = new UpdateUI(connection,book);
			
			else
				bookBDA.setSelectedBook(selected);
	}




}
