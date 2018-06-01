import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 * This is class for data storage by using hashtable
 * @author Joe
 *
 */
public class HashTable {
	public static BufferedWriter writeLog;
	static Hashtable<String, Stock> hash=new Hashtable<String, Stock>();
	static int size=0;
	
	/**
	 * class constructor
	 */
	public HashTable(){}
	
	/**
	 * This is a method to insert a key and a value into the hashtable
	 * @param symbol a 
	 * @param st a object that is a value for the hashtable which contains multiple value 
	 */
	public void insert(String symbol, Stock st){
		hash.put(symbol, st);
		size++;

	}//insert
	
	/**
	 * This is a method to update two values of a specific symbol
	 * @param symbol it's the symbol of the stock company and it treats as the key of the hashtable
	 * @param name the name of the file
	 * @param location that store the file 
	 * @throws IOException on input or output error
	 */
	public static void update(String symbol, String name, String location) throws IOException{
		Stock st=new Stock(hash.get(symbol.toUpperCase()).getCompanyName(),hash.get(symbol.toUpperCase()).getPrice(),
				hash.get(symbol.toUpperCase()).getChange(),hash.get(symbol.toUpperCase()).getPercentChange(),hash.get(symbol.toUpperCase()).getYearToDate(),
				hash.get(symbol.toUpperCase()).getURL(), name, location);
		if(hash.containsKey(symbol.toUpperCase())){
			hash.put(symbol.toUpperCase(), st);
		}
		else
			System.out.println("Hashmap doesn't have the key: "+ symbol);

	}//update
	
	/**
	 * This is a method to delete for a specific symbol
	 * @param symbol a key of the hashtable that used for deleting
	 * @throws IOException on input or output error
	 */
	public void delete(String symbol) throws IOException{
		if(hash.containsKey(symbol)){
			writeLog=new BufferedWriter(new FileWriter("Log.txt", true));
			SimpleDateFormat df=new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);//get the date
			String dateString=df.format(new Date());
			hash.remove(symbol.toUpperCase());
			size--;
			writeLog.write("Delete | " + symbol + " | " + dateString +"\r\n");
			writeLog.close();
		}
		
	}//delete
	
	/**
	 * 
	 * @return the size of the hashtable
	 */
	public int size(){
		return size;
	}//size

	/**
	 * This method is used to get the key and value of the hashtable
	 * @param symbol the key of the hashtable
	 * @return the key and value of the hashtable
	 */
	public static String get(String symbol){
		String s="";
		return s + hash.keys()+ " " +hash.values();
		
	}//get
	
	/**
	 * This method is used for getting all the information from the hash table and print in on the jtable
	 * @param model a object of the DefaultTableModel
	 */
	public static void retrieve(DefaultTableModel model) {
		Enumeration<String> name=hash.keys();
		String s="";
		if(!DataStorageController.getStorage().equals("bo"))
			model.setRowCount(0);
		while(name.hasMoreElements()){
			String key=name.nextElement();
			String array[]={key, hash.get(key).getCompanyName(), hash.get(key).getPrice() ,hash.get(key).getChange(), hash.get(key).getPercentChange(),  hash.get(key).getYearToDate(),hash.get(key).getURL(), hash.get(key).getFileName(),hash.get(key).getlocation()};
			model.addRow(array);
			s="";
		}		
	}//retrieve
	
	/**
	 * This method is used to get the values of a specific symbol and insert the values on the jtable
	 * @param symbol of the stock company
	 * @param companyNameText the TextField column in the jtable
	 * @param priceText the TextField column in the jtable
	 * @param changeText the TextField column in the jtable
	 * @param changePctText the TextField column in the jtable
	 * @param yearToDateText the TextField column in the jtable
	 */
	public static void getInfor(String symbol, JTextField companyNameText, JTextField priceText, JTextField changeText,
			JTextField changePctText, JTextField yearToDateText) {
		symbol=symbol.toUpperCase();
		companyNameText.setText(hash.get(symbol).getCompanyName());
		priceText.setText(hash.get(symbol).getPrice());
		changeText.setText(hash.get(symbol).getChange());
		changePctText.setText(hash.get(symbol).getPercentChange());
		yearToDateText.setText(hash.get(symbol).getYearToDate());				
	}//getInfor
	
	/**
	 * This method is used to get the url of the stock company
	 * @param symbol of the stock company
	 * @return the url of stock company
	 */
	public static String getURL(String symbol) {
		return hash.get(symbol.toUpperCase()).getURL();		
	}//getURL
	
	/**
	 * This method is used to get the company name for a specific symbol
	 * @param symbol of the stock company
	 * @return the company name
	 */
	public static String getCompanyName(String symbol) {
		return hash.get(symbol.toUpperCase()).getCompanyName();
	}//getCompnayName
	
	/**
	 * This method is used for updating the value of a specific symbol that the user selected
	 * @param select a symbol that the user select on the jtable
	 * @param st object that contains multiple values of the stock
	 */
	public static void update2(String select, Stock st) {
		if(hash.containsKey(select.toUpperCase())){
			hash.put(select.toUpperCase(), st);
		}
		else
			System.out.println("Hashmap doesn't have the key: "+ select);	
	}//update2
	
}//HashTable
