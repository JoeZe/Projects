import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;
/**
 * Conduct offline queries to retrieve data from a log file
 * @author Joe
 *
 */
public class OfflineQueries {
	public static BufferedReader readLog;
	public static BufferedWriter obtainData;
	public static Stock st;
	public static HashTable hash= new HashTable();//create a object of hash table
	/**
	 * This method is used to obtain the data for offline
	 * @throws IOException on input or output error
	 * @throws ParseException 
	 */
	public static void obtainData(String querie, String startDate, String endDate) throws IOException, ParseException {				
		readLog= new BufferedReader(new FileReader("Log.txt"));//open the file
		obtainData=new BufferedWriter(new FileWriter("obtainData.txt"));
		String line=readLog.readLine();
		String user=querie;
		while (line!=null){
			if(SaveUserInfo.getAccountType().equals("Admin")){//if the user is Admin
				if(querie.equals("All users")){//retrieve data from all user
					obtainData.write(line);
					obtainData.newLine();
				}
				else if(line.contains(user)&&line.contains("Account Type")){//retrieve data from a specific user
					Boolean flag=true;
					while(flag&&line!=null){
						obtainData.write(line);
						obtainData.newLine();
						line=readLog.readLine();// go to the next line
						if(line!=null&&line.contains("Account")){
							flag=false;
							break;
						}//if
					}//while
				}else
					System.out.println("Invalid user");	
			}//if
			else if(SaveUserInfo.getAccountType().equals("User")){//retrieve data on user's behalf
				if(line.contains(SaveUserInfo.getUserName())&&line.contains("Account Type")){
					Boolean flag=true;
					obtainData.write(line);
					obtainData.newLine();
					line=readLog.readLine();// go to the next line
					Date start=new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(startDate);//parse the start date to date format
					Date end=new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(endDate);//parse the end date to date format
					while(flag&&line!=null){			
						String[] split=line.split(Pattern.quote(" | "));// split the line into the array by removing "|"
						String date=split[8];//the date from the file
						Date date1=new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(date);//parse the date from the file to date format
						if(start.compareTo(date1)<=0&&end.compareTo(date1)>=0){//check the date on the file is within a time range that user asked for
							obtainData.write(line);
							obtainData.newLine();
						}//if				
						
						line=readLog.readLine();// go to the next line
						if(line!=null&&line.contains("Account Type")){
							flag=false;
						}//if
					}//while					
				}//if

			}else{
				System.out.println("You don't have authorization to conduct offline queries");
				break;
			}
			line=readLog.readLine();// go to the next line
		}
		readLog.close();// close the file
		obtainData.close();
	
		
	}//obtainData

}//OfflineQueries
