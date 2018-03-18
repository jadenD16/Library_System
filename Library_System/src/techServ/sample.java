package techServ;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JYearChooser;

public class sample extends JFrame {

	private JPanel contentPane;
	private JTextField fnameTF;
	private JTextField middleTF;
	private JTextField lnameTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sample frame = new sample();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public sample() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		JTextField authorTF = new JTextField();
		authorTF.setColumns(10);
		authorTF.setBounds(163, 178, 127, 20);
		contentPane.add(authorTF);
		
		fnameTF = new JTextField();
		fnameTF.setBounds(267, 98, 64, 20);
		contentPane.add(fnameTF);
		fnameTF.setColumns(10);
		
		middleTF = new JTextField();
		middleTF.setBounds(332, 98, 29, 20);
		contentPane.add(middleTF);
		middleTF.setColumns(10);
		
		lnameTF = new JTextField();
		lnameTF.setBounds(362, 98, 60, 20);
		contentPane.add(lnameTF);
		lnameTF.setColumns(10);
	}
}
