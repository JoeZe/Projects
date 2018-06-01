
/**
 * This class is used for storing multiple values of the stock
 * @author Joe
 */
public class Stock {
	private String companyName;
	String price;
	String change;
	String percentChange;
	String url;
	String nameFile;
	String location;
	String yearToDate;
	
	/**
	 * Class constructor Set values for a new stock
	 * @param cn String of company name
	 * @param price String of the price
	 * @param change String of the change
	 * @param percentChange String of the percent change
	 * @param ytd String of the year to date
	 * @param url String of the url
	 */
	public Stock(String cn, String price, String change, String percentChange, String ytd, String url){
		this.companyName=cn;
		this.price=price;
		this.change=change;
		this.percentChange=percentChange;
		this.yearToDate=ytd;
		this.url=url;
	}//stock
	
	/**
	 *  Class constructor specifying to add the file name and the location that store the file
	 * @param nf the file name 
	 * @param loc the location of the file
	 */
	public Stock(String nf, String loc){
		this.nameFile=nf;
		this.location=loc;
	}//stock
	
	/**
	 * Class constructor Set values for a new stock
	 * @param companyName2 String of company name
	 * @param price2 String of the price
	 * @param change2 String of the change
	 * @param percentChange2 String of the percent change
	 * @param ytd String of the year to date
	 * @param url2 String of the url
	 * @param name string of file name
	 * @param location2 string of location that store the file
	 */
	public Stock(String companyName2, String price2, String change2, String percentChange2, String ytd,
			String url2, String name, String location2) {
		this.companyName=companyName2;
		this.price=price2;
		this.change=change2;
		this.percentChange=percentChange2;
		this.yearToDate=ytd;
		this.url=url2;
		this.nameFile=name;
		this.location=location2;
	}//stock

	/**
	 * This is a method to get the company name
	 * @return the company name of the stock
	 */
	public String getCompanyName(){
		return companyName;
	}
	
	/**
	 * This is a method to get the stock price
	 * @return the stock price
	 */
	public String getPrice(){
		return price;
	}
	
	/**
	 * This is a method to get how much it changes in the stock
	 * @return the stock change
	 */
	public String getChange(){
		return change;
	}
	
	/**
	 * This is a method to get how many percent it changes in the stock
	 * @return the stock  percent change
	 */
	public String getPercentChange(){
		return percentChange;
	}
	
	/**
	 * This is a method to get url of the stock company
	 * @return the url of the stock
	 */
	public String getURL(){
		return url;
	}
	
	/**
	 * This is a method to get file name of the stock company
	 * @return the file name of the chart of the stock company
	 */
	public String getFileName(){
		return nameFile;
	}
	
	/**
	 * This is a method to get location that store the jpg file of the stock company
	 * @return the file location
	 */
	public String getlocation(){
		return location;
	}
	
	/**
	 * This is a method to get year to date of the stock company
	 * @return the year to date of change of the stock
	 */
	public String getYearToDate(){
		return yearToDate;
	}
	
	/**
	 * This is a method to add how many percent the stock change from year to date 
	 */
	public void setYearToDate(String ytd){
		this.yearToDate=ytd;
	}
	
	/**
	 * This is a method to add the file name
	 */
	public void setFileName(String fn){
		this.nameFile=fn;
	}
	
	/**
	 * This is a method to add the location of the file
	 */
	public void setLocation(String location){
		this.location=location;
	}
	
	/**
	 * This is a method to add the url of the stock company
	 */
	public void setURL(String url){
		this.url=url;
	}
}
