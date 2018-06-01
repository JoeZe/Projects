
/**
 * This class is used to store the user information when the user login into the program
 * @author Joe
 */
public class SaveUserInfo {
	static String userName;
	static String password;
	static String accountType;
	
	/**
	 * constructor
	 */
	public SaveUserInfo(String un, String pw, String at){
		userName=un;
		password=pw;
		accountType=at;
	}
	
	/**
	 *constructor 
	 */
	public SaveUserInfo() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Get the password of the user account
	 * @return the password of the user account
	 */
	public static String getPassword(){
		return password;		
	}//getPassword
	
	/**
	 * Set the password for the user account
	 * @param pwd password of the user account
	 */
	public static void setPassword(String pwd) {
		password=pwd;
		
	}//setPassword
	
	/**
	 * Get the user name of the user account
	 * @return the user name
	 */
	public static String getUserName(){
		return userName;
	}//getUserName
	
	/**
	 * Set the user name of the user account
	 * @param user a string of user name in string
	 */
	public static void setUserName(String user) {
		userName=user;
	}//setUserName
	
	/**
	 * Get the user account type
	 * @return the account type of the user account
	 */
	public static String getAccountType(){
		return accountType;
	}//getAccoutType
	
	/**
	 * Set the account type for the user account 
	 * @param accountType type of the account in string
	 */
	public static void setAccountType(String accountType) {
		accountType=accountType;
	}//setAccountType

}//SaveUserInfor
