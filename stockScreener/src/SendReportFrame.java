import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * This class is used to allow user to interact with graph
 * when user want to send a email 
 * @author Joe
 *
 */
public class SendReportFrame extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private static SendReportFrame frame;
	/**
	 * Launch the GUI to allow user to type user's email and password.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new SendReportFrame();
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
	public SendReportFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 404, 256);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(55, 97, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(55, 132, 59, 14);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(123, 94, 195, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(124, 129, 194, 20);
		contentPane.add(passwordField);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int vflag=1;
				while(vflag==1){//keep running while vflag is equal 1
					vflag=SendEmail.sendReportToEmail(textField.getText(),new String(passwordField.getPassword()));//if the method send email sucessfully return 0 else 1
					if(vflag==0){//if vflag equal to 0 close the sendReportFrame GUI
						frame.dispose();//close the frame
					}
					textField.setText("");//empty the email text
					passwordField.setText("");//empty the password
					break;
					
				}//while
			}//actionPerformed
		});//btnSend
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSend.setBounds(159, 178, 89, 23);
		contentPane.add(btnSend);
		
		JLabel lblSendReportTo = new JLabel("Send report to email");
		lblSendReportTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSendReportTo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSendReportTo.setBounds(123, 42, 143, 23);
		contentPane.add(lblSendReportTo);
	}//sendReportFrame

}//semdReportFrame
