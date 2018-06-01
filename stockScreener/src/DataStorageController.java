import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.table.DefaultTableModel;
/**
 * This class is used to access data storage depend on which one the user choose to use
 * @author Joe
 *
 */
public class DataStorageController {
	public static HashTable hash= new HashTable();
	private static String storage="";
	public DataStorageController(){}
	
	/**
	 * This method is used to access the data storage that the user chose
	 * @param select the selected data storage
	 * @throws Exception on error exclude runtime
	 */
	public static void selectedDataStorage(String select) throws Exception{		
		if(select.equals("db")){
			Database.createStockTable();
			storage=select;
			//WebpageReaderWithAgent.fileParser("datahotstocks.txt");
			System.out.println("You are using database for data storage");
		}else if(select.equals("ht")){
			hash= new HashTable();
			System.out.println("You are using hashtable for data storage");
			storage=select;
			//WebpageReaderWithAgent.fileParser("datahotstocks.txt");
		}else if(select.equals("bo")){
			Database.createStockTable();
			hash= new HashTable();
			System.out.println("You are using database and hashtable for data storage");
			storage=select;
			//WebpageReaderWithAgent.fileParser("datahotstocks.txt");
		}else
			System.err.println("Invalid command:");
		//MainWindow mainFrame=new MainWindow();// open the main gui
	}
	
	/**
	 * this method is used to get the data storage that the user chose to use
	 * @return what data storage the user chose to use
	 */
	public static String getStorage(){
		return storage;
	}//getStorage
	
	/**
	 * This method is used to delete the selected data on the jtable based on which data storage the user chose and delete it
	 * @param select the symbol that the user selected on the jtable
	 * @throws Exception on any error that doesn't include runtime 
	 */
	public static void deleteSelectedDS(String select) throws Exception {
		if(DataStorageController.getStorage().equals("db")){
			Database.delete(select);
		}else if(DataStorageController.getStorage().equals("ht")){
			hash.delete(select);
		}else{
			Database.delete(select);
			hash.delete(select);
		}//else
	}//deleteSelectdDs

	/**
	 * This method is used to update the selected data on the jtable based on which data storage the user chose and update it
	 * @param selectedRowIndex the row index base on the user select on the jtable
	 * @param model the object of DefaulTableModel
	 * @throws IOException on input or output error
	 */
	public static void updateSelectedDS(int selectedRowIndex, DefaultTableModel model) throws IOException {
		//get the selected row information and store it in strings
		String select=(String) model.getValueAt(selectedRowIndex, 0);
		String cn=(String)model.getValueAt(selectedRowIndex, 1);
		String price=(String)model.getValueAt(selectedRowIndex, 2);
		String change=(String)model.getValueAt(selectedRowIndex, 3);
		String pChange=(String)model.getValueAt(selectedRowIndex, 4);
		String ytd=(String)model.getValueAt(selectedRowIndex, 5);
		String url=(String)model.getValueAt(selectedRowIndex, 6);
		Stock st=new Stock(cn, price, change, pChange, ytd, url);
		BufferedWriter writeLog=new BufferedWriter(new FileWriter("Log.txt", true));//open the file to be ready to write
		DateFormat df=DateFormat.getDateInstance(DateFormat.LONG);//get the date
		String dateString=df.format(new Date());
		if(DataStorageController.getStorage().equals("db")){ //update the data storage if the user chose database
			try {
				Database.update(select, (String)model.getValueAt(selectedRowIndex, 2), (String)model.getValueAt(selectedRowIndex, 3), (String)model.getValueAt(selectedRowIndex, 4), (String)model.getValueAt(selectedRowIndex, 5));
				Database.retrieve(model); // pass model in order to print all the information retrieve from database
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
									
		}else if(DataStorageController.getStorage().equals("ht")){//update the data storage if the user chose hash table
			HashTable.update2(select, st);
			HashTable.retrieve(model);// pass model in order to print all the information retrieve from database
			writeLog.write("Update | " + select +" | "+ cn+ " | "+    price+ " | " + change+ " | " + pChange+ " | " +ytd +" | " +url +" | " + dateString+"\r\n");//write the update to the file
			writeLog.close();
		}else{//update the data storage if the user chose database and hash table
			try {
				Database.update(select, (String)model.getValueAt(selectedRowIndex, 2), (String)model.getValueAt(selectedRowIndex, 3), (String)model.getValueAt(selectedRowIndex, 4), (String)model.getValueAt(selectedRowIndex, 5));
				Database.retrieve(model);// pass model in order to print all the information retrieve from database
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Stock st1=new Stock((String)model.getValueAt(selectedRowIndex, 1),(String)model.getValueAt(selectedRowIndex, 2), (String)model.getValueAt(selectedRowIndex, 3), (String)model.getValueAt(selectedRowIndex, 4), (String)model.getValueAt(selectedRowIndex, 5),(String)model.getValueAt(selectedRowIndex, 6));
			HashTable.update2(select, st1);
			HashTable.retrieve(model);	// pass model in order to print all the information retrieve from database
			writeLog.write("Update | " + select +" | "+ cn+ " | "+    price+ " | " + change+ " | " + pChange+ " | " +ytd +" | " +url +" | " + dateString+"\r\n");//write the update to the file
			writeLog.close();// close the file
		}	
	}//updateSelectedDB

}//DataStorageController
