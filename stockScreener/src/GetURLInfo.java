


//This examplebased on the code from from the book _Java in a Nutshell_ by David Flanagan.
//Written by David Flanagan.  Copyright (c) 1996 O'Reilly & Associates.

import java.net.*;
import java.io.*;
import java.util.*;
/**
 * This class is used to print the information about the website into the file
 * @author Joe
 *
 */
public class GetURLInfo {
	private static String url=null;
	private static String contentType=null;
	private static String contentLength=null;
	private static String lastModified=null;
	private static String expiration=null;
	private static String contentEncoding=null;
	private static String filePath=null;
	private static int line=0;
	private static String infor="";
	
	/**
	 * Get the information about the web site and store it into string
	 * @param u a url connection
	 */
	public static void getInfo(URLConnection u) {
	     // Display the URL address, and information about it.
		url=u.getURL().toExternalForm() + ":";
		contentType="  Content Type: " + u.getContentType();
		contentLength="  Content Length: " + u.getContentLength();
		lastModified="  Last Modified: " + new Date(u.getLastModified());
	 	expiration="  Expiration: " + u.getExpiration();
	 	contentEncoding="  Content Encoding: " + u.getContentEncoding();
 		filePath=u.getURL().getFile();
 		//infor=url + "\r\n "+ contentType +"\r\n "+ contentLength +"\r\n "+ lastModified +"\r\n "+ expiration +"\r\n "+ contentEncoding + "\r\n ";
	} // printinfo
	 
	public static void printInfo(String outputfile) throws IOException {
	     // Display the URL address, and information about it.
		BufferedWriter outPut=new BufferedWriter(new FileWriter(outputfile));		
		outPut.write(infor);//write the string infor to the file
		outPut.close();//close the file
	} // printinfo
	
	/**
	 * set the number of line that read from the website
	 * @param l number of line
	 */
	public static void setLine(int l){
		line=l;
	}//setLine
	
	/**
	 * 
	 * @return the url of the website
	 */
	public static String getURL(){
		 return url;
	}//getURL
	
	/**
	 * 
	 * @return the content type of the website
	 */
	public static String getContentType(){
		return contentType;
	}//getContentType
	 
	/**
	 * 
	 * @return the content length of the website
	 */
	public String getContentLength(){
		return contentLength;
	}//getContentLength
	 
	/**
	 * 
	 * @return the last modeified date of the website
	 */
	public String getLastModified(){
		return lastModified;
	}//getLastModified
	 
	/**
	 * 
	 * @return the expiration of the website
	 */
	public String getExpiration(){
		return expiration;
	}//getExpiration
	 
	/**
	 * 
	 * @return the content encoding of the website
	 */
	public String getContendEncoding(){
		return contentEncoding;
	}//getContendEncoding
	
	/**
	 * 
	 * @return the path of the file in string
	 */
	public static String getFilePath(){
		return filePath;
	}//getFilePath
	
	 // Create a URL from the specified address, open a connection to it,
	 // and then display information about the URL.
	public static void main(String webpage) throws MalformedURLException, IOException{
		URL url = new URL(webpage);
		URLConnection connection = url.openConnection();
		getInfo(connection);
	} // main
	
	/**
	 * put all information into a single string
	 * @return all string into a single string
	 */
	public static String getInfor() {
		return url + "\r\n "+ contentType +"\r\n "+ contentLength +"\r\n "+ lastModified +"\r\n "+ expiration +"\r\n "+ contentEncoding + "\r\n" + "   Line: "+line + "\r\n";
	}//getInfor
	
	/**
	 * set the string infor to a given string
	 * @param infor2 the string that contain all web information
	 */
	public static void setInfor(String infor2){
		infor=infor2;
	}//setInfor
} // GetURLInfo
	
