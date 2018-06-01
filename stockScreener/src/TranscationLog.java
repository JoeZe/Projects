import java.io.*;
import java.util.regex.Pattern;
/**
 * This class is used for recovering the data by using the log file
 * @author Joe
 *
 */
public class TranscationLog {
	public static BufferedReader readLog;
	public static Stock st;
	public static HashTable hash= new HashTable();//create a object of hash table
	
	/**
	 * This method is used to reconstruct the data storage for persistent data
	 * @throws IOException on input or output error
	 */
	public static void recover() throws IOException {		
		readLog= new BufferedReader(new FileReader("Log.txt"));//open the file
		String line=readLog.readLine();		
		while (line!=null){
			if(SaveUserInfo.getAccountType().equals("Admin")||SaveUserInfo.getAccountType().equals("User")){
				if(line.contains("Insert")){
					String[] split=line.split(Pattern.quote(" | "));// split the line into the array by removing "|"
					String symbol=split[1];
					String companyName=split[2];
					String price=split[3];
					String change=split[4];
					String percentChange=split[5];
					String ytd=split[6];
					String url=split[7];
					st=new Stock(companyName, price, change, percentChange, ytd, url);				
					hash.insert(symbol, st);// insert into the hash table
				
				}else if(line.contains("Update")){
					String[] split=line.split(Pattern.quote(" | "));// split the line into the array by removing "|"
					String symbol=split[1];
					if(line.contains(".jpg")){
						hash.update(symbol, split[2], split[3]); //update the hash table if it contains the file name
					}else{
						String companyName=split[2];
						String price=split[3];
						String change=split[4];
						String percentChange=split[5];
						String ytd=split[6];
						String url=split[7];
						st=new Stock(companyName, price, change, percentChange, ytd, url);					
						hash.update2(symbol, st);//update the hash table
					}//else
				}else if(line.contains("Delete")){
					String[] split=line.split(Pattern.quote(" | "));// split the line into the array by removing "|"
					hash.delete(split[1]);//delete the hash table
				}else
					System.out.println("Error");
				line=readLog.readLine();// go to the next line
			}else
				System.out.println("You don't have authorization to recover the data");
		}
		readLog.close();// close the file
		
	}//recover

}//TrancationLog
