import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
/**
 * this class is used to parse user input on the cmd.exe
 * @author Joe
 *
 */
public class CommandLineParser {
	public static HashTable hash= new HashTable();//create a object for hashtable
	
	/**
	 * This method is used to determine which class and method to class base on what the user's input want the program do 
	 * @param args argument that user input on the cmd.exe
	 * @throws Exception on any error exclude runtime
	 */
	static void commandLine(String[] args) throws Exception {
		int i=0;
		String arg;
		String outputfile="";
		String inputfile="";
		//if the argument is empty print the error message 
        if (i == args.length)
            System.err.println("Usage: stockScreener [-i]/[-o]/[-p]/[-h db/ht/bo][-help] [input filename]/[output filename]");
        else{
        	while(i<args.length && args[i].startsWith("-")){
        		arg=args[i++];//get the argument and then increments i				
        		if(arg.equals("-i")){//check if the first argument is equil to -i
        			if(i<args.length){
        				inputfile=args[i++];//get the next argument then increment i 
        				readFile(inputfile);// call this method to file the file on a given file name
        				//login= new LoginFrame();// create a new Gui for user to login					
        			}else
        				System.err.println("-i requires a filename");

				
        		}else if(arg.equals("-o")){//check if the first argument is equil to -o
        			//login= new LoginFrame();// create a new Gui for user to login
        			if(i<args.length){
        				outputfile=args[i++];//get the next argument then increment i
        				GetURLInfo.printInfo(outputfile);//calls prinInfo to print the website information to a given file name
        				
        			}else
        				System.err.println("-o requires a output filename");
				
        		}else if(arg.equals("-p")){//check if the first argument is equil to -p
        			if(i<args.length){
        				inputfile=args[i++];//get the next argument then increment i
        				readFile(inputfile);// call this method to file the file on a given file name
        				if(i<args.length){
        					outputfile=args[i++];//get the next argument then increment i
        					GetURLInfo.printInfo(outputfile);// call this method to file the file on a given file name
        				}else
        					System.err.println("-p requires a output filename");
        			}else
        				System.err.println("-p requires a input filename");
        			
        		}else if(arg.equals("-h")){//check if the first argument is equil to -h
        			
        			Database.createStockTable();//call the method in the database to create a table in the database
        			//LoginFrame login= new LoginFrame();// create a new Gui for user to login
        			
        			if(i<args.length){
        				arg=args[i++];//get the next argument then increment i
        				DataStorageController.selectedDataStorage(arg);//call selectedDataSTorage to access the selected data storage
        			}
        		}else if(arg.equals("-help")){//check if the first argument is equil to -help
        			System.out.println("Usage: stockScreener [-i]/[-o]/[-p]/[-h db/ht/bo][-help] [input filename]/[output filename]");
        			
        		}else{
        			System.err.println("Invalid command");
        		}
        	}//while
        }
		
	}//commandLine
		
	/**
	 * Determine whether the information inside the file that has url or content of the website
	 * and then call the method that can process it 
	 * @param inputfile the name of the file to read
	 * @throws Exception on reading file error 
	 */
	private static void readFile(String inputfile) throws Exception {	
		try {
			BufferedReader inFile = new BufferedReader(new FileReader(inputfile)); // open the file
			String line=inFile.readLine();//save the first read line into string

			while(line!=null){
				if(line.contains("!DOCTYPE")){
					WebpageReaderWithAgent.fileParser(inputfile);//calls fileParser if the line is not a url
					break;
				}
				else{
					WebpageReaderWithAgent.urlParser(line);//calls urlParser if the line is a url 
				}	
				line=inFile.readLine();
			}			
			inFile.close();//close the file
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}			
	}//readFile
}//CommandLineParser
