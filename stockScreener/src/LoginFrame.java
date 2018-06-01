	

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
/**
 * Create an graphic for security of the program
 * @author Joe
 *
 */

public class LoginFrame {
	private JFrame login;
	private static JTextField txf_username;
	private JPasswordField pwd_password;
	private JTextField userNameReg;
	private JPasswordField passWordReg;
	private JPasswordField comPassWordReg;
	/**
	 * Create the frame.
	 * @param args 
	 */	
	public LoginFrame(String[] args) {
		login=new JFrame();
		login.setVisible(true);//display the frame
		initialize(args);
		login.setTitle("Login");
		login.setLocation(700, 500);
		login.setSize(395,261);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	/**
	 * 
	 * @return the JFrame
	 */
	public JFrame getJFrame(){
		return login;
	}
	/**
	 * create properties for the frame
	 * @param args 
	 */
	private void initialize(String[] args) {
		login.getContentPane().setLayout(null);
		JLabel welcomeLabel = new JLabel("Welcome");
		welcomeLabel.setBounds(145, 40, 95, 32);
		Font font1 = new Font("SansSerif", Font.BOLD, 22);
		welcomeLabel.setFont(font1);

		login.getContentPane().add(welcomeLabel);
		//create an lable call Username
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(65, 92, 63, 32);
		login.getContentPane().add(usernameLabel);
		//create an lable call password  
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(65, 148, 63, 19);
		login.getContentPane().add(passwordLabel);
	      
		txf_username= new JTextField();
		txf_username.setBounds(141, 98, 163, 23);
		login.getContentPane().add(txf_username);
	      
		pwd_password=new JPasswordField();
		pwd_password.setBounds(141, 147, 163, 23);
		login.getContentPane().add(pwd_password);
		
		JButton signin=new JButton("Login");
		signin.setBounds(65,185,77,21);
		login.getContentPane().add(signin);
		
		JButton signUp=new JButton("Sign Up");
		signUp.setBounds(225,185,77,21);
		login.getContentPane().add(signUp);
		//captures the button name that is pressed by the user and execute the event to sign in into the system
		signin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){			
				Connection conn=null;
				Statement st=null;
				ResultSet rs=null;
				try{
					conn=Database.getConn();//connect to the database
					st=conn.createStatement();
					String user=txf_username.getText().trim();
					String pwd=new String(pwd_password.getPassword()).trim();
					String sql="select * from tb_user where username='" + user + "' and password='" + pwd +"'";
					rs= st.executeQuery(sql);
					if(rs.next()&&rs.getString("username")!=null){			
						new SaveUserInfo(user, pwd,rs.getString("AccountType"));
		
						login.dispose();//close the login frame
						//WebpageReaderWithAgent.fileParser("datahotstocks.txt");
						CommandLineParser.commandLine(args);
						new MainWindow();						
					}else{
						JOptionPane.showMessageDialog(null, "Wrong username or password");
						txf_username.setText("");
						pwd_password.setText("");
					}//else
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Database error", JOptionPane.INFORMATION_MESSAGE);
				}finally{
					try{
						conn.close();
					}
					catch(Exception e1){
						
					}				
				}//finally
			}//actionPerformed
		});	//signin
		
		//captures the button name that is pressed by the user and execute the event to open an registration frame
		signUp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				login.dispose();//close the login frame
				JFrame registration=new JFrame();//create on new frame to register an account
				registration.setVisible(true);
				registration.setTitle("Registration");		
				registration.setLocation(400, 400);
				registration.setSize(430,400);
				registration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				registrationForm(registration);
			}//actionPerformed

			private void registrationForm(final JFrame registration) {
				registration.getContentPane().setLayout(null);
				JLabel titleLabel = new JLabel("Registration form");
				titleLabel.setBounds(110, 40, 200, 32);
				Font font = new Font("SansSerif", Font.BOLD, 22);
				titleLabel.setFont(font);
				registration.getContentPane().add(titleLabel);
				
				JLabel usernameLabel = new JLabel("Username:");
				usernameLabel.setBounds(65, 90, 80, 32);
				registration.getContentPane().add(usernameLabel);
			      
				JLabel passwordLabel = new JLabel("Password:");
				passwordLabel.setBounds(65, 130, 80, 19);
				registration.getContentPane().add(passwordLabel);
				
				JLabel confirmPasswordLabel = new JLabel("Confirm password:");
				confirmPasswordLabel.setBounds(65, 158, 120, 19);
				registration.getContentPane().add(confirmPasswordLabel);
				
				JLabel accountTypeLabel = new JLabel("Account type:");
				accountTypeLabel.setBounds(65, 188, 100, 19);
				registration.getContentPane().add(accountTypeLabel);
				
				String[] type={"Admin", "User", "Guest"};
				final JComboBox<String> accountType=new JComboBox<String>(type);
				accountType.setBounds(175, 185, 163, 23);
				registration.getContentPane().add(accountType);
				
				JLabel keyLabel = new JLabel("Key:");
				keyLabel.setBounds(65, 215, 100, 19);
				registration.getContentPane().add(keyLabel);
				
				userNameReg= new JTextField();
				userNameReg.setBounds(175, 95, 163, 23);
				registration.getContentPane().add(userNameReg);
			      
				passWordReg=new JPasswordField();
				passWordReg.setBounds(175, 125, 163, 23);
				registration.getContentPane().add(passWordReg);
				
				comPassWordReg=new JPasswordField();
				comPassWordReg.setBounds(175, 155, 163, 23);
				registration.getContentPane().add(comPassWordReg);
				
				final JTextField key= new JTextField();
				key.setBounds(175, 215, 163, 23);
				registration.getContentPane().add(key);
				
				JButton submit=new JButton("Submit");
				submit.setBounds(65,300,77,21);
				registration.getContentPane().add(submit);
				
				//captures the button name that is pressed by the user and execute the event to sign up an account and insert into the database
				submit.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String pw1=String.valueOf(passWordReg.getPassword());//get the string of password
						String pw2=String.valueOf(comPassWordReg.getPassword());// get the string of confirm password
						String type=(String) accountType.getSelectedItem();						
						boolean flag=true;						
						while(flag){
							if(!userNameReg.getText().equals("")&&!pw1.equals("")&&!pw2.equals("")){//check the username and password and confirm are not empty
								if(pw1.equals(pw2)){//if password equal to confirm password
									try {
										if(accountType.getSelectedItem().toString().equals("Admin")){//user need a key inorder to get a admin account
											if(key.getText().equals("root")){
												Database.post(userNameReg.getText(), pw1, type, key.getText() );//insert the account into the databse 
												flag=false;
												JOptionPane.showMessageDialog(null, "Sucess!");
												registration.dispose();// close the registration frame
												login.setVisible(true);//display the login frame again												
												break;
											}//if
											else if(key.getText().equals("")){
												JOptionPane.showMessageDialog(null, "Key can't be empty for Admin!");
												break;
											}else{
												JOptionPane.showMessageDialog(null, "Wrong key!");
												break;
											}
											
										}else{
											Database.post(userNameReg.getText(), pw1, type, key.getText() );// insert the other acount that are not admin into the database
											flag=false;
										}
										JOptionPane.showMessageDialog(null, "Sucess!");
									} catch (Exception e1) {
									// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(null, e1);
									}
								}//if
								else{
									JOptionPane.showMessageDialog(null, "Password doesnt's match!");
									break;
								}//else
								registration.dispose();// close the registration frame
								login.setVisible(true);//display the login frame again
							}else{
								JOptionPane.showMessageDialog(null, "One of the field is empty!");
								break;
							}
						
						}//while
					}
				});//submit
				
				JButton cancel=new JButton("Cancel");
				cancel.setBounds(260,300,77,21);
				registration.getContentPane().add(cancel);
				
				cancel.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						registration.dispose();// close the registration frame
						login.setVisible(true);//display the login frame again						
					}
				});//cancel
				
			}
		});//signUp
		
	}//initialize
	
}//LoginFrame
