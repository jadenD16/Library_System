package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JToggleButton;

public class LibrarySystemUser extends JFrame{
	public LibrarySystemUser() {
		setResizable(false);
		setSize(1370, 718);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Library System");
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 7, true));
		panel.setOpaque(false);
		panel.setBounds(0, 134, 263, 529);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Picture");
		lblNewLabel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		lblNewLabel_1.setBounds(34, 24, 174, 140);
		panel.add(lblNewLabel_1);
		
		JButton btnBorrowedBooks = new JButton("History");
		btnBorrowedBooks.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnBorrowedBooks.setBounds(34, 296, 203, 29);
		panel.add(btnBorrowedBooks);
		
		JButton btnViewHistory = new JButton("View Profile");
		btnViewHistory.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnViewHistory.setBounds(34, 343, 203, 29);
		panel.add(btnViewHistory);
		
		JButton btnManageBooks = new JButton("CART");
		btnManageBooks.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnManageBooks.setBounds(34, 386, 203, 29);
		panel.add(btnManageBooks);
		
		JButton btnNewButton = new JButton("LogOut");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(1204, 134, 89, 23);
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setBounds(412, 229, 904, 375);
		getContentPane().add(scrollPane);
		
		JTable table = new JTable();
		table.setOpaque(false);
		scrollPane.setViewportView(table);
		
		JTextField textField = new JTextField();
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		textField.setBounds(469, 203, 322, 23);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblSearch.setBounds(412, 204, 61, 14);
		getContentPane().add(lblSearch);
		
		JButton btnAddToCart = new JButton("Add to Cart");
		btnAddToCart.setBounds(1176, 202, 117, 29);
		getContentPane().add(btnAddToCart);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LibrarySystemMain.class.getResource("/pictures/background.jpg")));
		lblNewLabel.setBounds(0, 0, 1364, 674);
		getContentPane().add(lblNewLabel);		
		setVisible(true);
	}
	
}