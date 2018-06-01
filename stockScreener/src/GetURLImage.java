
import java.awt.image.*; 
import javax.imageio.*;

import com.mysql.jdbc.PreparedStatement;

import java.io.*; 
import java.net.URL;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.net.MalformedURLException; 
/**
 * This class is used to download the image from the web
 * @author Joe
 */
public class GetURLImage { 
	static URL url = null; 
	static File outputFile = null; 
	public static BufferedImage image = null; 
	
	/**
	 * read from a url and save it into image
	 * @param url of the image
	 */
	public static void fetchImageFromURL (URL url) { 
		try { 
			// Read from a URL 
			image = ImageIO.read(url); 
		} catch (IOException e) { 
			e.printStackTrace();
		} // catch 
	} // fetchImageFromURL 
    // Create a URL from the specified address, open a connection to it, 
    // and then display information about the URL. 
	/**
	 * 
	 * @param arg a string of the url
	 * @param symbol the stock company sybmol 
	 * @throws MalformedURLException to indicate that a malformed URL has occurred
	 * @throws IOException on input or output error
	 */
    public static void main(String arg, String symbol)throws MalformedURLException, IOException { 
    	url = new URL(arg);
    	BufferedWriter writeLog=new BufferedWriter(new FileWriter("Log.txt", true));// open the file to be ready to write
    	outputFile=new File(symbol+".jpg");//set the image file name
    	fetchImageFromURL(url); //call this method to download the image
    	ImageIO.write(image, "jpg", outputFile); //write the image byte to the file and store it as jpg format
		Connection con=null;
		String dir = System.getProperty("user.dir"); //get the path of the current directory 
		
		//save the file name of the image and location the store and update in the data storage depend on whcih one the user chose
		if(DataStorageController.getStorage().equals("db")){ // check if the user choose to user database as the data storage
			try{
				con=Database.getConn();// open the connection to the database			
				String sql="UPDATE tb_stock set nameFile=?, location=? where symbol=?";
				PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql);
				ps.setString(1, symbol+".jpg"  );
				ps.setString(2,  dir+"/"+symbol+".jpg");
				ps.setString(3, symbol);
				ps.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					con.close();// close the connection to the database
				}catch(Exception e1){
					e1.printStackTrace();
				}				
			}//finally
		}else if(DataStorageController.getStorage().equals("ht")){ // check if the user choose to user hash table as the data storage
			HashTable.update(symbol, symbol+".jpg",dir+"/"+symbol+".jpg");// update the file name of the image and the location into the symbol 
		}else{ // user uses database and database as data storage
			HashTable.update(symbol, symbol+".jpg",dir+"/"+symbol+".jpg");
			try{
				con=Database.getConn();// open the connection to the database		
				String sql="UPDATE tb_stock set nameFile=?, location=? where symbol=?";
				PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql);
				ps.setString(1, symbol+".jpg"  );
				ps.setString(2,  dir+"/"+symbol+".jpg");
				ps.setString(3, symbol);
				ps.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					con.close(); // close the connection to the database
				}catch(Exception e1){
					e1.printStackTrace();
				}				
			}//finally
		}//else
		SimpleDateFormat df=new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);//get the date
		String dateString=df.format(new Date());
		writeLog.write("Update | " + symbol.toUpperCase() +" | "+symbol+".jpg" +" | " + dir+"/"+symbol+".jpg" +" | " + dateString+"\r\n");//write the update to the file with time stamp
		writeLog.close();//close the file
    } // main 

} // GetURLImage 
