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
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class LibrarySystemMain extends JFrame implements ActionListener{

	private JPanel contentPane,panel;
	private JTable table;
	private JTextField textField;
	private SelectedBook selectB;
	private JScrollPane scrollPane;	
	private JComboBox comboBox;
	
	private JLabel lblNewLabel_1,lblFilterby,lblBackground,lblSearch,lblprofile  ;
	
	private JButton btnUpdate, btnAdd, btnLogin,btnBorrowedBooks,btnManageBooks,btnViewHistory,btnManageUsers,btnBorrow ;
	
	private Connection connection;
	
	private String[] tableHeader;
	
	private BooksDA booksDA;
	
	private DefaultTableModel tableModel;
	private TableColumnModel columnModel;
	private UserInformationUI userinformationUI;
	private List<Books> bookList;
	private AddUI addUI;
	private LoginUI loginUI;
	private User user;
	private JLabel lblname;
	private SelectedBook selected;
	private BookBorrowedUI bookBorrowedUI;
	private BookBorrowedDA bookBDA;
	
	public LibrarySystemMain() 
	{
		userinformationUI = new UserInformationUI(getConnection());
		booksDA = new BooksDA(getConnection());
		user = new User();
		bookBDA = new BookBorrowedDA();
		
		setResizable(false);
		setSize(1370, 718);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Library System");
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 7, true));
		panel.setBounds(10, 134, 263, 529);
		getContentPane().add(panel);
		panel.setLayout(null);
		
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
		scrollPane.setEnabled(false);
		scrollPane.setOpaque(false);
		scrollPane.setBounds(341, 237, 985, 402);
		getContentPane().add(scrollPane);
		
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Segoe UI", Font.BOLD, 20));
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
		
		lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblSearch.setBounds(342, 185, 80, 26);
		getContentPane().add(lblSearch);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial",Font.PLAIN,14));
		comboBox.setModel(new DefaultComboBoxModel(new String[]{"----","A-C","D-F","G-I","J-L","M-P","Q-S","T-V","W-Z"}));
		comboBox.setBounds(425, 217, 89, 20);
		getContentPane().add(comboBox);
		
		lblFilterby = new JLabel("Sort By:");
		lblFilterby.setFont(new Font("Arial", Font.BOLD, 16));
		lblFilterby.setBounds(341, 218, 80, 16);
		getContentPane().add(lblFilterby);
		
		JLabel lblLibraryManagementSystem = new JLabel("LIBRARY MANAGEMENT SYSTEM");
		lblLibraryManagementSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibraryManagementSystem.setFont(new Font("Segoe UI", Font.BOLD, 52));
		lblLibraryManagementSystem.setBackground(new Color(30, 144, 255));
		lblLibraryManagementSystem.setOpaque(true);
		lblLibraryManagementSystem.setBounds(0, 0, 1380, 123);
		getContentPane().add(lblLibraryManagementSystem);
		
		btnBorrow = new JButton("Borrow");
		btnBorrow.setBounds(1237, 209, 89, 23);
		btnBorrow.addActionListener(this);
		add(btnBorrow);
		
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(LibrarySystemMain.class.getResource("/pictures/background.jpg")));
		lblBackground.setBounds(0, 0, 1364, 674);
		getContentPane().add(lblBackground);
		
		fillBookTable();
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		
		String action = event.getActionCommand();
		
		if(action.equalsIgnoreCase("Manage Books"))
		{
			getContentPane().add(lblSearch);
			getContentPane().add(textField);
			getContentPane().add(scrollPane);
			getContentPane().add(btnAdd);
			getContentPane().add(btnUpdate);
			getContentPane().add(comboBox);
			getContentPane().add(lblFilterby);
			getContentPane().add(lblBackground); 
			
			remove(userinformationUI);
			repaint();
			
			fillBookTable();
		}
		else if(action.equalsIgnoreCase("Add"))
			 addUI = new AddUI(getConnection());
		
		else if(action.equalsIgnoreCase("Manage Users")) 
		{
			manageUser();
			repaint();
			revalidate();
		}	
		else if(action.equalsIgnoreCase("login"))
			loginUI = new LoginUI(getConnection());
						
		else if(action.equals("Borrow"))
		{
			int row=table.getSelectedRow(),
				column=0;
				selected = new SelectedBook();
				
				while(column<7)
				{
					if(column==0)
						selected.setBookCode(String.valueOf(table.getValueAt(row, column)));
					
					else if(column==1)
						selected.setBookName(String.valueOf(table.getValueAt(row, column)));
					
					else if(column==2)
						selected.setBookAuthor(String.valueOf(table.getValueAt(row, column)));
										
					else if(column==5)
						selected.setCategory(String.valueOf(table.getValueAt(row, column)));
					
					else if(column==6)
						selected.setPublishYear(String.valueOf(table.getValueAt(row, column)));	
					
					column++;					
				}

				bookBDA.setSelectedBook(selected);
			 }
		
		else if(action.equalsIgnoreCase("Borrowing List"))
				bookBorrowedUI = new BookBorrowedUI(bookBDA);
		}
		
	
	
	public void loggedIn(String userType)
	{
		
		if(userType.equalsIgnoreCase("admin"))
		{
			btnManageUsers.setText("Manage Users");
			panel.add(btnManageUsers);
			panel.add(btnManageBooks);
			panel.add(btnViewHistory);
		}
		
		else
		{
			btnManageUsers.setText("View Profile");
			panel.add(btnManageUsers);
			panel.add(btnBorrowedBooks);
			panel.add(btnViewHistory);
			panel.add(btnManageBooks);
			
			
		}
		
		
			panel.repaint();	
		}	
	
	
	public void manageUser() {
		remove(lblSearch);
		remove(textField);
		remove(scrollPane);
		remove(btnAdd);
		remove(btnUpdate);
		remove(comboBox);
		remove(lblFilterby);
		remove(lblBackground);
		
		getContentPane().add(userinformationUI);
		getContentPane().add(lblBackground);
		repaint();
		

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
		bookList= booksDA.getBookList();
		
						
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
	
	
	
 	public static void main(String[]args)
	{
		new LibrarySystemMain();		
	}

	
}
