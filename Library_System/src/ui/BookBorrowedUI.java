package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import domain.*;
import techServ.BookBorrowedDA;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.SwingConstants;

public class BookBorrowedUI extends JDialog implements ActionListener {
	private DefaultTableModel tableModel;
	private JTable table;
	private String[] tableHeader;
	private JButton btnBorrowNow,btnBack;
	private JScrollPane scrollPane;
	private TableColumnModel columnModel;
	private JComboBox comboBox; 
	private BookBorrowedDA bookBDA;
	private List<SelectedBook> bookList;
	private BookBorrowed bookB;
	private JLabel lblBooksToBe,lblDeadline,lblDays,lblTransactionNumber,lblDateIssued;
	private SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy 'at' HH:mm");
	private Calendar calendar;
	private SelectedBook selectedBooks;
	private Date date,dueDate;
	private Timestamp dDate, dueDateS;
	private Integer transNumber;
	private User user;
	
	public BookBorrowedUI(BookBorrowedDA bookBorrowedDA, User user) {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().setBackground(new Color(0, 128, 128));
		bookB = new BookBorrowed();
		setUndecorated(true);
		setModal(true);
		setBounds(100, 100, 664, 496);
		getContentPane().setLayout(null);
		
		bookBDA=bookBorrowedDA;
		this.user=user;
		date = new Date();
		dueDate=new Date();
		dDate = new Timestamp(date.getTime());
		calendar = Calendar.getInstance();
		
		scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setBorder(new MatteBorder(1, 1, 5, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setBounds(10, 59, 644, 320);
		getContentPane().add(scrollPane);
				
		table = new JTable()
        {
            public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
        };
		table.setFont(new Font("Arial", Font.PLAIN, 13));
		table.setShowVerticalLines(false);
		table.setShowGrid(false);
		table.setBorder(null);
        table.addMouseListener(new MouseAdapter()
		{
			
			 public void mouseClicked(MouseEvent e) 
			 {
			
				
				 int row =  table.getSelectedRow();
				 
				 if(table.getSelectedColumn()==5)
				 {
					 System.out.println(table.getSelectedRow());
					 bookList.remove(row);
					 tableModel.removeRow(row);
					table.repaint();
				 }
			 }	 
				 
		});
		table.setOpaque(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);
		
		btnBorrowNow = new JButton("Borrow Now");
		btnBorrowNow.setForeground(new Color(255, 255, 255));
		btnBorrowNow.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnBorrowNow.setContentAreaFilled(false);
		btnBorrowNow.setOpaque(false);
		btnBorrowNow.setFont(new Font("Segoe UI", Font.BOLD, 30));
		btnBorrowNow.setBounds(335, 444, 329, 52);
		btnBorrowNow.addActionListener(this);
		getContentPane().add(btnBorrowNow);
		
		btnBack = new JButton("Back");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 30));
		btnBack.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		btnBack.setContentAreaFilled(false);
		btnBack.setOpaque(false);
		btnBack.addActionListener(this);
		btnBack.setBounds(0, 444, 337, 52);
		getContentPane().add(btnBack);
		
		JLabel lblBorrowFor = new JLabel("Borrow For:");
		lblBorrowFor.setForeground(new Color(255, 255, 255));
		lblBorrowFor.setHorizontalAlignment(SwingConstants.CENTER);
		lblBorrowFor.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblBorrowFor.setBounds(370, 390, 99, 21);
		getContentPane().add(lblBorrowFor);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7"}));
		comboBox.setBounds(479, 393, 38, 20);
		comboBox.addActionListener(this);
		getContentPane().add(comboBox);
		
		lblDays = new JLabel("Day/s");
		lblDays.setForeground(new Color(255, 255, 255));
		lblDays.setHorizontalAlignment(SwingConstants.CENTER);
		lblDays.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblDays.setBounds(527, 393, 56, 19);
		getContentPane().add(lblDays);
		
		lblTransactionNumber = new JLabel();
		lblTransactionNumber.setForeground(new Color(255, 255, 255));
		lblTransactionNumber.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblTransactionNumber.setBounds(10, 387, 252, 16);
		getContentPane().add(lblTransactionNumber);
		
		lblBooksToBe = new JLabel("Books To Be Borrowed");
		lblBooksToBe.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(0, 0, 0)));
		lblBooksToBe.setHorizontalAlignment(SwingConstants.CENTER);
		lblBooksToBe.setForeground(new Color(255, 255, 255));
		lblBooksToBe.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblBooksToBe.setBounds(179, 11, 345, 37);
		getContentPane().add(lblBooksToBe);
		
		lblDeadline = new JLabel();
		lblDeadline.setHorizontalAlignment(SwingConstants.LEFT);
		lblDeadline.setForeground(Color.WHITE);
		lblDeadline.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblDeadline.setBounds(370, 412, 284, 21);
		getContentPane().add(lblDeadline);
		
		lblDateIssued = new JLabel();
		lblDateIssued.setHorizontalAlignment(SwingConstants.LEFT);
		lblDateIssued.setForeground(Color.WHITE);
		lblDateIssued.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblDateIssued.setBounds(10, 412, 303, 21);
		getContentPane().add(lblDateIssued);
		
		fillBookTable();
		setLocationRelativeTo(null);
		setData(comboBox.getSelectedItem().toString());
		setVisible(true);
	}
	
	public void fillBookTable()
	{
		
		tableHeader = new String[]{"Book No.","Title","Author","Category","P.Y","-"};
	
		tableModel = new DefaultTableModel(tableHeader,0)
		{
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		};
		
		table.setModel(tableModel);
		bookList= bookBDA.getSelectedBList();
		
		for(SelectedBook book : bookList)
		{
			tableModel.addRow(new Object[]{book.getBookCode(),book.getBookName(),book.getBookAuthor(),book.getCategory(),
					book.getPublishYear(),"Remove From List"});
		}
				
			renderTable();
	}
	
	public void refreshTable()
	{
		for(SelectedBook book : bookList)
		{
												
				tableModel.addRow(new Object[]{book.getBookCode(),book.getBookName(),book.getBookAuthor(),book.getCategory(),
						book.getPublishYear(),"Remove From List"});
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

	public void setData(String days)
	{
		//Setting the Date abd Tune
		lblDateIssued.setText("Date issued: "+sdf.format(date));
		

		//Adding of Days
		calendar.setTime(date);
		calendar.add(Calendar.DATE,Integer.valueOf(days));
		
		dueDate = calendar.getTime();
		dueDateS = new Timestamp(calendar.getTimeInMillis());
		lblDeadline.setText("Due Date: "+sdf.format(dueDate));
		
		//Transaction Nunber
		
		lblTransactionNumber.setText("Transaction Number:"+00001);
		revalidate();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String event = e.getActionCommand();
		
		if(event.equalsIgnoreCase("Back"))
			dispose();
		
		else if(e.getSource()==comboBox)
		{
			setData(comboBox.getSelectedItem().toString());			
		}
		
		else if(event.equalsIgnoreCase("Borrow Now"))
		{
			bookB = new BookBorrowed();
			
			bookB.setTransNumber(String.format("%05d", 1));
			bookB.setBorrowedDate(dDate);
			bookB.setDueDate(dueDateS);
			bookBDA.confirmBooks(bookB,user);
		}
		
	}
}
