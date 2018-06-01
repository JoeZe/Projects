import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/**
 * This class is used to parse the url and file and write to the file
 * @author Joe
 *
 */
public class WebpageReaderWithAgent {
	public static HashTable hash= new HashTable();
	public static Stock st;
	private static String url = "http://money.cnn.com";
	private static String webpage = null;
	public static String infor="";
	public static final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.2) Gecko/20100115 Firefox/3.6";
	public static BufferedWriter writeLog;
	public static InputStream getURLInputStream(String sURL) throws Exception {

		URLConnection oConnection = (new URL(sURL)).openConnection();

		oConnection.setRequestProperty("User-Agent", USER_AGENT);

		return oConnection.getInputStream();

	}//getURLInputStream

	public static BufferedReader read(String url) throws Exception {
		InputStream content = (InputStream)getURLInputStream(url);

		return new BufferedReader (new InputStreamReader(content));

	} // read
	
	public static BufferedReader read2(String url) throws Exception {

		return new BufferedReader(

				new InputStreamReader(

						new URL(url).openStream()));

	} // read2

	/**
	 * Read from a url and write it to the file
	 * @param args is the url from the file
	 * @throws Exception
	 */
	public static void urlParser (String args) throws Exception{
		if (args.length() == 0) {

			System.out.println("No URL inputted.");

			System.exit(1);

		} // any inputs?
		
		GetURLInfo.main(args);
		
		String dir = System.getProperty("user.dir");
		webpage = args;
		System.out.println("Contents of the following URL: "+webpage+"\n");
		
		BufferedReader reader = read(webpage);
		
		String[] fileName=GetURLInfo.getFilePath().split("\\W");
		String path="";
		for(String name: fileName){
			path+=name;		
		}
		
		path= dir +"\\"+ path +".txt";//set the path that save it in the output file
		
		BufferedWriter outPut=new BufferedWriter(new FileWriter(path));
		String line = reader.readLine();//read the first line
		int lineCount = 0;

		while (line != null) {//while it's not empty
			if(GetURLInfo.getContentType().contains("text")||GetURLInfo.getContentType().contains("html")){
				//System.out.println(line);
				outPut.write(line);//write the line to the file
				outPut.newLine();
				lineCount++;//increment the line count
				line = reader.readLine();//read the next line
			}//if

		} // while
				
		outPut.close();// close to write the file
		GetURLInfo.setLine(lineCount);//call the method to add the line that read from the url
		infor+=GetURLInfo.getInfor();//store the content of the url in to a string
		GetURLInfo.setInfor(infor);		
		GetURLInfo.setLine(0);//reset the number of line
	}//urlParser 
	
	/**
	 * Parse the file and find the important data and store it in the data storage
	 * @param file name of the file in string
	 * @throws Exception
	 */
	public static void fileParser (String file) throws Exception{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		int priceStart, end, changeStart, pctChangeStart, symbolEnd, symbolStart,linkStart,linkEnd;
		String companyName=null, price=null, change=null, percentChange=null, symbol=null, link=null;
		writeLog=new BufferedWriter(new FileWriter("Log.txt", true));//open the file to be ready to write
		SaveUserInfo sui= new SaveUserInfo();
		writeLog.write("Account Type: "+sui.getAccountType() + " | "+ "Username: "+ sui.getUserName());
		writeLog.newLine();
		while (line != null) {
			if(line.contains("wsod_symbol")||line.contains("wsod_aRight")){	
				int target=line.indexOf("span title=");
				int start=line.indexOf(">", target);
				end =line.indexOf("</span>");
				symbolStart=line.indexOf("wsod_symbol");
				symbolEnd=line.indexOf("</a>");
				linkStart=line.indexOf("href=");
				linkEnd=line.indexOf("class");
				//find the symbol of a stock and the url for a specific stock
				if(target>=0){
					symbolStart=line.indexOf(">", symbolStart);
					symbol=line.substring(symbolStart+1, symbolEnd);
					link=line.substring(linkStart+6,linkEnd-2);					
					companyName=line.substring(start+1, end);
					//System.out.print(companyName + " ");
				}
				//find the company name of a stock
				if(companyName!=null&&line.contains("last")){
					priceStart=line.indexOf(companyName);
					priceStart=line.indexOf(">", priceStart);
					price=line.substring(priceStart+1, end);
					//System.out.print(price + " ");
				}
				//find the price of change of a stock
				if(companyName!=null&&line.contains("change")&&!line.contains("%")){
					changeStart=line.indexOf("Data");
					changeStart=line.indexOf(">", changeStart);
					change=line.substring(changeStart+1, end);
					//System.out.print(change + " ");
				}
				//find the percent of change of a stock
				if(companyName!=null&&line.contains("changePct")){
					pctChangeStart=line.indexOf("Data");
					pctChangeStart=line.indexOf(">", pctChangeStart);
					percentChange=line.substring(pctChangeStart+1, end);
					//System.out.println(percentChange);
					SimpleDateFormat df=new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);//get the date
					String dateString=df.format(new Date());
					if(DataStorageController.getStorage().equals("db")){//if user chose to use database for data storage
						if(!Database.ifExist(symbol)){
							Database.insert(symbol, companyName, price, change, percentChange, url+link);
							//write the inserted data to the log file
							writeLog.write("Insert | " + symbol + " | " + companyName + " | " + price+ " | " + change+ " | " + percentChange+ " | " + " "+ " | " + url+link +" | " + dateString +"\r\n");
						}
						else{
							Database.update(symbol, price, change, percentChange, "");
							//write the update data to the log file
							writeLog.write("Update | " + symbol + " | " + companyName + " | " + price+ " | " + change+ " | " + percentChange+ " | " + " "+ " | "+ url+link +" | " + dateString+"\r\n");
						}
					}else if(DataStorageController.getStorage().equals("ht")){//if user chose to use hashtable for data storage
						st=new Stock(companyName, price, change, percentChange, "", url+link);
						hash.insert(symbol, st);//insert  into the hashtable
						//write the inserted data to the log file
						writeLog.write("Insert | " + symbol + " | " + companyName + " | " + price+ " | " + change+ " | " + percentChange+ " | " + " "+ " | " + url+link +" | " + dateString+"\r\n");
					}else{
						st=new Stock(companyName, price, change, percentChange, "", url+link);
						hash.insert(symbol, st);//insert into the hashtable
						if(!Database.ifExist(symbol)){
							Database.insert(symbol, companyName, price, change, percentChange, url+link);//insert into the database
							//write the insert date to fhe file
							writeLog.write("Insert | " + symbol + " | " + companyName + " | " + price+ " | " + change+ " | " + percentChange+ " | " + " "+ " | " + url+link +" | " + dateString+"\r\n");
						}
						else{
							Database.update(symbol, price, change, percentChange, "");//update the databse
							//write the update to the file
							writeLog.write("Update | " + symbol + " | " + companyName + " | " + price+ " | " + change+ " | " + percentChange+ " | " + " "+ " | "+ url+link +" | " + dateString+"\r\n");
						}						
					}//else	

				}//if
							
			}//if
				
			line = reader.readLine();//read the next line

		} // while
		writeLog.close();//close the file
	} // main
	
	/**
	 * Parse the second url based on what the user search
	 * @param symbol
	 * @param url2 url that the user want to search
	 * @throws Exception
	 */
	public static void secondUrlParser(String symbol, String url2) throws Exception {
		System.out.println("Contents of the following URL: "+url2+"\n");
		BufferedReader reader = read(url2);
		String companyName = null, price = null, change = null, changePct = null,yearToDate = null;
		String line = reader.readLine();
		int begin = 0, last = 0;
		//BufferedWriter writeLog=new BufferedWriter(new FileWriter("Log.txt"));
		writeLog=new BufferedWriter(new FileWriter("Log.txt", true));//open the file
		MainWindow.setSymbol(symbol);
		while (line != null) {
			//get the company name
			if(line.contains("wsod_companyName")){
				int target=line.indexOf("wsod_companyName");
				target=line.indexOf("wsod_fLeft", target);
				int start=line.indexOf(">", target);
				int end=line.indexOf("<span", start);
				companyName=line.substring(start+1, end);
				//System.out.println(companyName);		
			}
			//find the price
			if(line.contains("ToHundredth")){
				int target=line.indexOf("ToHundredth");
				int start=line.indexOf(">", target);
				int end=line.indexOf("<", start);
				price=line.substring(start+1, end);
				//System.out.println(price);			
			}
			//find the price of change of a stock
			if(line.contains("change_")){
				int target=line.indexOf("change_");
				target=line.indexOf("span class", target);
				int start=line.indexOf(">", target);
				int end=line.indexOf("<", start);
				change=line.substring(start+1, end);
				//System.out.println(change);
			}
			//find the percent of price of a stock
			if(line.contains("changePct_")){
				int target=line.indexOf("changePct_");
				target=line.indexOf("span class", target);
				int start=line.indexOf(">", target);
				int end=line.indexOf("<", start);
				changePct=line.substring(start+1, end);
				//System.out.println(changePct);
			}
			////find the percent of year to date of a stock
			if(line.contains("wsod_ytd")){
				int target=line.indexOf("wsod_ytd");
				target=line.indexOf("span class", target);
				int start=line.indexOf(">", target);
				int end=line.indexOf("%</span><div class", start);
				yearToDate=line.substring(start+1, end+1);
				SimpleDateFormat df=new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);//get the date
				String dateString=df.format(new Date());
				if(DataStorageController.getStorage().equals("db")){
					Database.update(symbol, price, change, changePct, yearToDate);//update the database
				}else if(DataStorageController.getStorage().equals("ht")){
					st=new Stock(companyName, price, change, changePct, yearToDate, url2);
					HashTable.update2(symbol, st);//update the hashtable
					//write the update to the file
					writeLog.write("Update | " + symbol.toUpperCase() +" | "+ companyName+ " | "+    price+ " | " + change+ " | " + changePct+ " | " +yearToDate +" | " +url2 +" | " + dateString+"\r\n");
				}else{
					Database.update(symbol, price, change, changePct, yearToDate);//update the database
					st=new Stock(companyName,price, change, changePct, yearToDate, url2);
					HashTable.update2(symbol, st);//update the hashtable
					//write the update to the file
					writeLog.write("Update | " + symbol.toUpperCase() +" | "+ companyName+ " | "+    price+ " | " + change+ " | " + changePct+ " | " +yearToDate +" | " +url2 +" | " + dateString+"\r\n");
				}				
			}
			//get the url of the chart
			if(line.contains("http://markets.money.cnn.com/services/api/chart/snapshot_chart_api.asp?symb=")){			
				begin=line.indexOf("http://markets.money.cnn.com/services/api/chart/snapshot_chart_api.asp?symb=");
				last=line.indexOf(" />", begin);
				break;
			}					
			line = reader.readLine();//read the next line

		} // while
		writeLog.close();//close the file
		GetURLImage.main(line.substring(begin, last-1), symbol);//call the method to download an image from the url
	}//secondUrlParser

} // WebpageReaderWithAgent