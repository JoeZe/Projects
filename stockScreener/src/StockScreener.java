

public class StockScreener {
	public static HashTable hash= new HashTable(); //creates a object for HashTable
	public static LoginFrame login; //create a object for the login GUI
	
	/**
	 * @param args command line arguments, which stores user's input that what the user what the program do 
	 * @throws Exception if there's error print it out.
	 */
	public static void main(String[] args) throws Exception {
		//CommandLineParser.commandLine(args);
		Database.createUserTable();
		Database.createStockTable();
		LoginFrame login= new LoginFrame(args);	
		//WebpageReaderWithAgent.fileParser("datahotstocks.txt");
		//MainWindow mainFrame=new MainWindow();
		//Database.retrieve(mainFrame.getModel());
		//writeLog.close();
	}//main
	
}//StockScreener
