import java.util.*;
import javax.jdo.*;

@javax.jdo.annotations.PersistenceCapable

public class Flight
{
	private static final int Collection = 0;
	String airlineCompanyName;
	String flightNum; // { airlineCompanyName, flightNum } is a key
	Airport origin; 
	Airport destination;
	Time departTime;
	Time arriveTime;


	public String toString()
	{
		return airlineCompanyName+" "+flightNum+" "+
		       origin.name+" "+departTime.hour+":"+departTime.minute+" "+
		       destination.name+" "+arriveTime.hour+":"+arriveTime.minute ;
	}

	public static Flight find(String airlineCompanyName, String flightNum, PersistenceManager pm)

	/* Returns the flight that has the two parameter values; returns null if no such flight exists.
           { airlineCompanyName, flightNum } is assumed to be a key for Flight class.
	   The function is applied to the database held by the persistence manager "pm". */

	{
		Query q=pm.newQuery();
		q.setClass(Flight.class);
		q.declareParameters("String airlineCompanyName, String flightNum");
	    q.setFilter("this.airlineCompanyName == airlineCompanyName && this.flightNum == flightNum");
		q.setUnique(true);
		Flight result=(Flight)q.execute(airlineCompanyName, flightNum);
		q.closeAll();
		return result;

	}

	public static Collection<Flight> getFlights(String a1, String a2, Query q)
	
	/* Given airport names a1 and a2, the function returns the collection of
	   all flights departing from a1 and arriving to a2.
	   Sort the result by (airlineCompanyName, flightNum). */

	{
		q.setClass(Flight.class);
		q.declareParameters("String a1, String a2");
		q.setFilter("origin.name==a1 &&"+"destination.name==a2" );
		q.setOrdering("airlineCompanyName, flightNum");
		
		return (Collection<Flight>) q.execute(a1,a2);

	}

	public static Collection<Flight> getFlightsForCities(String c1, String c2, Query q)

	/* Given city names c1 and c2, the function returns the collection of all flights 
	   departing from an airport close to c1 and arriving to an airport close to c2. 
	   Sort the result by (airlineCompanyName, flightNum). */

	{
		q.setClass(Flight.class);
		q.declareParameters("String c1, String c2");
		q.setFilter("origin.closeTo.name.contains(c1)&&destination.closeTo.name.contains(c2)");
		q.setOrdering("airlineCompanyName, flightNum");
		return  (Collection<Flight>) q.execute(c1, c2);

	}

	@SuppressWarnings("unchecked")
	public static Collection<Flight> getFlightsDepartTime(
		  String a1, String a2, int h1, int m1, int h2, int m2, Query q)

	/* Given airport names a1 and a2 and times h1:m1 and h2:m2,
	   the function returns the collection of all flights departing from a1 and arriving to a2
	   satisfying the condition that the departure time is h1:m1 at earliest and h2:m2 at latest.
	   Note that the time interval from h1:m1 to h2:m2 may include midnight.
	   Sort the result by (airlineCompanyName, flightNum). */

	{
		q.setClass(Flight.class);
		//q.declareVariables("Airport a1, Airport a2");
		q.declareParameters("String a1, String a2, int h1, int m1, int h2, int m2");
		q.setFilter("(origin.name==a1 &&"+"destination.name==a2) &&"+
		"((h2>10&&h1<departTime.hour&&"+ "h2>departTime.hour)||"+
		"(h1=departTime.hour&&"+ "m1<departTime.minute&&"+
		"h2<departTime.hour)||" +
		"(h2=departTime.hour&&" +"m2<departTime.minute&&+h1>departTime.hour))||"+
		"(origin.name==a1 &&"+"destination.name==a2 &&"+"h1>h2 &&h2<10&&"+ "h1<departTime.hour&&"+"(h2+24)>departTime.hour)");
		q.setOrdering("airlineCompanyName, flightNum");
		Object[] args= new Object[]{a1, a2, h1, m1, h2, m2};
		return (Collection<Flight>) q.executeWithArray(args);

	}

	public static Collection<Object[]> getFlightsConnection(
		  String a1, String a2, int h1, int m1, int h2, int m2,
		  int connectionAtLeast, int connectionAtMost, Query q)

	/* Given airport names a1 and a2, times h1:m1 and h2:m2, and connection time lower and upper bounds in minutes,
	   connectionAtLeast and connectionAtMost, the function returns the pairs of all flights f and f1 satisfying
	   the following conditions:

	   1. f departs from a1 and arrives to a connecting airport "ca" different from a2; and
	   2. The departure time of f is h1:m1 at earliest and h2:m2 at latest; and
	   3. There is a second flight f1 from "ca" to a2; and
	   4. The connecting time, i.e. the time interval in minutes between 
	      the arrival time of f and the departure time of f1, is at least connectionAtLeast
	      and at most connectionAtMost. 

	   Note again that the relevant time intervals may include midnight.
	   Sort the result by (f.airlineCompanyName, f.flightNum, f1.airlineCompanyName, f1.flightNum). */

	{	q.setClass(Flight.class);
		q.declareVariables("Airport a;Flight f; Flight f1");
		q.declareParameters("String a1, String a2, int h1, int m1, int h2, int m2, int connectionAtLeast, int connectionAtMost");
		q.setFilter("this.origin.name==a1&&this.origin.name==f.origin.name&&f!=f1&&f1.destination.name==a2&&"+"  f.destination.name==f1.origin.name&& f.destination.name==a2&&"+"this.destination.name==a.toString() &&"+"h2>10&&h1<departTime.hour&&"+ "h2>departTime.hour &&"+
		"a.toString()==a2&&this!=f&& "+"(f.departTime.hour<this.arriveTime.hour&&f.departTime.hour<10&&"
				+ "connectionAtLeast <=(((f.departTime.hour+24)-this.arriveTime.hour)*60-this.arriveTime.minute+f.departTime.minute)&&"
				+ "connectionAtMost >=(((f.departTime.hour+24)-this.arriveTime.hour)*60-this.arriveTime.minute+f.departTime.minute))");
		//q.setFilter("this.origin.name==a1 &&"+"(h2>10&&h1<this.departTime.hour&&"+ "h2>this.departTime.hour)");
		q.setOrdering("airlineCompanyName, flightNum, airlineCompanyName, flightNum");
		Object[] args= new Object[]{a1, a2, h1, m1, h2, m2, connectionAtLeast, connectionAtMost};
		return (Collection<Object[]>) q.executeWithArray(args);

	}

	public static Collection<Object[]> groupByCompany(Query q)

	/* Group the flights by their airline company names.
	   Then return the set of 2-tuples <airlineCompanyName: String, num: int> where:

	   num = the total number of flights operated by airlineCompanyName

	   Sort the result by airlineCompanyName. */

	{
		q.setClass(Flight.class);
		q.setResult("airlineCompanyName, count(this)");
		q.setGrouping("airlineCompanyName");
		q.setOrdering("airlineCompanyName");
		return (Collection<Object[]>) q.execute();

	}
}