import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class Database {
	public static Connection conn=null;
	/**
	 * this method is used to establish connection with database with user, password, and url to connect the database
	 * @return this return the connection of the database
	 * @throws Exception on getting error for connecting to the database
	 */
	public static Connection getConn() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		//Connection conn=null;
		//String url="jdbc:mysql://localhost:3306/db_stock?characterEncoding=utf8&useSSL=true";
		String url="jdbc:mysql://cs370.c4r4mysxl6j2.us-east-1.rds.amazonaws.com:3306/dbStock";
		String user="cs370";
		String password="cs370root";
		conn=DriverManager.getConnection(url,user,password);//try to connect to local host first
		return conn;
	}//Connection
	
	/**
	 * This method is used to insert a user account into the database
	 * @param username of the account
	 * @param pw password of the account
	 * @param accType account type of the account
	 * @param key get the Admin account type
	 * @throws Exception exclude runtime
	 */
	public static void post(String username, String pw, String accType, String key) throws Exception{
		//Connection con=null;
		try{
			conn=getConn();
			PreparedStatement posted=(PreparedStatement) conn.prepareStatement("INSERT INTO tb_user values('"+username+"', '"+pw+"','"+ accType+"', '"+key+"')");
			posted.executeUpdate();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}finally{
			try{
				conn.close();
			}
			catch(Exception e1){
				
			}				
		}//finally
	}//post
	
	/**
	 * create a table to store user account information
	 * @throws Exception
	 */
	public static void createUserTable() throws Exception{
		//Connection con=null;
		try{
			conn=getConn();
			String userTable="CREATE TABLE IF NOT EXISTS dbStock. tb_user "+
					"(username VARCHAR(255) NOT NULL, "+
					"password VARCHAR(255) NOT NULL, "+
					"AccountType VARCHAR(45) NOT NULL, "+
					"adminKey VARCHAR(45) NULL, "+
					"PRIMARY KEY (username))"; 
			PreparedStatement create=(PreparedStatement) conn.prepareStatement(userTable);
			create.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();			
		}finally{
			try{
				conn.close();
			}
			catch(Exception e1){
				
			}				
		}
	}//createUserTable
	
	/**
	 * This method is used to create a table in the database
	 */
	public static void createStockTable() {
		//Connection con=null;
		try{
			conn=getConn();
			String userTable="CREATE TABLE IF NOT EXISTS dbStock. tb_stock "+
					"(symbol VARCHAR(255) NOT NULL , "+
					"companyName VARCHAR(255) NOT NULL , "+
					"price VARCHAR(255) NOT NULL, "+
					"changePrice VARCHAR(255) NOT NULL, "+
					"percentChange VARCHAR(255) NOT NULL, "+
					"yearToDate VARCHAR(255) NULL, "+
					"url VARCHAR(255) NULL, "+
					"nameFile VARCHAR(255) NULL, "+
					"location VARCHAR(255) NULL, "+
					"PRIMARY KEY (symbol))"; 
			PreparedStatement create=(PreparedStatement) conn.prepareStatement(userTable);
			create.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();			
		}finally{
			try{
				conn.close();
			}
			catch(Exception e1){
				
			}//catch				
		}//finally
	}//createStockTable
	
	/**
	 * Insert stock data into the database
	 * @param symbol of the stock company
	 * @param cName company name of the stock
	 * @param price of the stock
	 * @param change of the stock
	 * @param percentChange of the stock
	 * @param link of the stock
	 * @throws Exception
	 */
	public static void insert(String symbol, String cName, String price, String change, String percentChange, String link) throws Exception{
		//Connection con=null;
		try{
			conn=getConn();
			PreparedStatement ps=(PreparedStatement) conn.prepareStatement("INSERT INTO tb_stock values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, symbol);
			ps.setString(2,  cName);
			ps.setString(3, price);
			ps.setString(4,  change);
			ps.setString(5, percentChange);
			ps.setString(6, "");
			ps.setString(7, link);
			ps.setString(8, "");
			ps.setString(9, "");
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();		
		}finally{
			try{
				conn.close();
			}
			catch(Exception e1){
				
			}				
		}//finally
	}//insert
	
	/**
	 * Update the stock table in database
	 * @param symbol
	 * @param price
	 * @param change
	 * @param percentChange
	 * @param yearToDate
	 * @throws Exception
	 */
	public static void update(String symbol, String price, String change, String percentChange, String yearToDate) throws Exception{
		//Connection con=null;
		try{
			conn=getConn();
			String sql="UPDATE tb_stock set price=?, changePrice=?, percentChange=?, yearToDate=? where symbol=?";
			PreparedStatement ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1,  price);
			ps.setString(2,  change);
			ps.setString(3,  percentChange);
			ps.setString(4,  yearToDate);
			ps.setString(5, symbol);
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				conn.close();
			}
			catch(Exception e1){
				
			}				
		}//finally
	}//update
	
	/**
	 * find the symbol in the database and delete the whole row
	 * @param symbol
	 * @throws Exception
	 */
	public static void delete(String symbol) throws Exception{
		//Connection con=null;
		try{
			conn=getConn();
			String sql="delete from tb_stock where symbol=?";// find the symbol in the database
			PreparedStatement ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, symbol);
			ps.executeUpdate();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}finally{
			try{
				conn.close();
			}
			catch(Exception e1){
				
			}				
		}//finally
	}//delete
	
	/**
	 * This method is to check if the symbol is in the database
	 * @param symbol of the stock company
	 * @return true if it's in the database otherwise false
	 * @throws SQLException on access sql error
	 */
	public static boolean ifExist(String symbol) throws SQLException {
		//Connection con=null;
		ResultSet rt = null;
		try {
			conn=getConn();
			String sql="select * from tb_stock where symbol= '" + symbol +"'";// find the symbol in the database
			Statement st= (Statement) conn.createStatement();		
			rt=st.executeQuery(sql);	
			return rt.next();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rt.next();

	}//ifExist

	/**
	 * Search the symbol in the database and get the symbol's URL
	 * @param symbol
	 * @return the symbol's url
	 * @throws SQLException connect to database error
	 */
	public static String getURL(String symbol) throws SQLException {
		//Connection con=null;
		ResultSet rt = null;
		try{
			conn=getConn();
			String sql="select * from tb_stock where symbol= '" + symbol +"'"; // find the symbol in the database
			Statement st= (Statement) conn.createStatement();
			rt=st.executeQuery(sql);
			if(rt.next()&&rt.getString("url")!=null){
				return rt.getString("url");
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}finally{
			try{
				conn.close();
			}
			catch(Exception e1){
				
			}				
		}//finally
		return null;
	}//getUrl
	
	/**
	 * Get all data from database and print it on the jtable
	 * @param model
	 * @throws SQLException
	 */
	public static void retrieve(DefaultTableModel model) throws SQLException {
		//Connection con=null;
		ResultSet rt = null;
		if(DataStorageController.getStorage().equals("bo"))
			model.setRowCount(0);
		model.setRowCount(0);
		try{
			conn=Database.getConn();
			String sql="select * from tb_stock";// find the symbol in the database
			Statement st= (Statement) conn.createStatement();
			rt=st.executeQuery(sql);
			ResultSetMetaData metaData=(ResultSetMetaData) rt.getMetaData();
			int columnSize=metaData.getColumnCount();
			while(rt.next()){
				Vector<String> vector=new Vector<String>();
				for(int i=1; i<=columnSize; i++)
					vector.add(rt.getString(i));
				model.addRow(vector);
			}//while
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}//catch
	}//retrieve

	/**
	 * Search on a symbol on the database and get the whole row the the data and then print it on jtable
	 * @param symbol
	 * @param companyNameText
	 * @param priceText
	 * @param changeText
	 * @param changePctText
	 * @param yearToDateText
	 */
	public static void getInfor(String symbol, JTextField companyNameText, JTextField priceText, JTextField changeText,
			JTextField changePctText, JTextField yearToDateText) {
		//Connection con=null;
		ResultSet rt = null;
		try{
			conn=getConn();
			String sql="select * from tb_stock where symbol= '" + symbol +"'";// find the symbol in the database
			Statement st= (Statement) conn.createStatement();
			rt=st.executeQuery(sql);
			if(rt.next()){
				companyNameText.setText(rt.getString("companyName"));
				priceText.setText(rt.getString("price"));
				changeText.setText(rt.getString("changePrice"));
				changePctText.setText(rt.getString("percentChange"));
				yearToDateText.setText(rt.getString("yearToDate"));
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}finally{
			try{
				conn.close();// close the connection to the database
			}
			catch(Exception e1){
				
			}				
		}//finally	
	}//getInfor
	
}//Database
