import java.awt.EventQueue;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class OfflineQueriesFrame extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private static OfflineQueriesFrame frame;
	/**
	 * Launch the GUI to allow user to type user's email and password.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new OfflineQueriesFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}//run
		});
	}//main
	
	/**
	 * Create the frame.
	 */
	public OfflineQueriesFrame() {	
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(175, 94, 145, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(108, 147, 86, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblFrom = new JLabel("From:");
		lblFrom.setBounds(63, 150, 46, 14);
		getContentPane().add(lblFrom);
		
		JLabel lblTo = new JLabel("To:");
		lblTo.setBounds(209, 150, 46, 14);
		getContentPane().add(lblTo);
		
		textField_2 = new JTextField();
		textField_2.setBounds(234, 147, 86, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblAllUsersUser = new JLabel("All users/ user name:");
		lblAllUsersUser.setBounds(63, 97, 102, 14);
		getContentPane().add(lblAllUsersUser);
		
		JLabel lblHintMmddyyyyEx = new JLabel("Hint: mm/dd/yyyy ex: 04/03/2018");
		lblHintMmddyyyyEx.setBounds(63, 122, 257, 14);
		getContentPane().add(lblHintMmddyyyyEx);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					System.out.println(textField.getText());
					OfflineQueries.obtainData(textField.getText(),textField_1.getText()  , textField_2.getText());
				} catch (IOException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.dispose();
			}
		});
		btnSearch.setBounds(166, 199, 89, 23);
		getContentPane().add(btnSearch);
		
		JLabel lblForAdminUses = new JLabel("For Admin uses 1. User uses 2.");
		lblForAdminUses.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblForAdminUses.setBounds(63, 46, 257, 37);
		getContentPane().add(lblForAdminUses);
		
		JLabel label = new JLabel("1.");
		label.setBounds(45, 97, 17, 14);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("2.");
		label_1.setBounds(45, 150, 17, 17);
		getContentPane().add(label_1);
		
	}//OfflineQueriesFrame
}//OfflineQueriesFrame
