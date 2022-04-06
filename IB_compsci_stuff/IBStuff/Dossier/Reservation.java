import java.util.*;
import java.io.*;
class Reservation extends Utility
{
	private String customerName;
	private long reservationTime;
	private int numberOfCustomers;
	private int tableNumber;
	private String additionalRequest;
	
	Reservation()
	{
	}
	
	Reservation(String customerName, long reservationTime, int numberOfCustomers, int tableNumber, String additionalRequest)
	{
		this.customerName = customerName;
		this.reservationTime = reservationTime;
		this.numberOfCustomers = numberOfCustomers;
		this.tableNumber = tableNumber;
		this.additionalRequest = additionalRequest;
	}
	
	public String toString()
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(reservationTime);
		return allignMiddle(customerName,25)+"|"+allignMiddle(insertZero(c.get(c.HOUR_OF_DAY))+". "+insertZero(c.get(c.DAY_OF_MONTH))
			+"/"+insertZero(c.get(c.MONTH))+"/"+c.get(c.YEAR),25)+"|"+allignMiddle(""+numberOfCustomers,10)+"|"+allignMiddle(""+tableNumber,10)+"|"+allignMiddle(additionalRequest,25)+"|";
	}

	public String getCustomerName()
	{
		return customerName;
	}

	public long getReservationTime()
	{ 
		return reservationTime;
	}

	public int getNumberOfCustomers()
	{
		return numberOfCustomers;
	}

	public int getTableNumber()
	{
		return tableNumber;
	}

	public String getAdditionalRequest()
	{
		return additionalRequest;
	}

	public void setCustomerName(String ss)
	{
		customerName=ss;
	}

	public void setReservationTime(long time)
	{
		reservationTime=time;
	}

	public void setNumberOfCustomers(int num)
	{
		numberOfCustomers=num;
	}

	public void setTableNumber(int num)
	{
		tableNumber=num;
	}

	public void setAdditionalRequest(String ss)
	{
		additionalRequest=ss;
	}

	public String insertZero(int num)
	{
		if (num<10)
			return "0"+num;
		return ""+num;
	}

	public void save(PrintWriter xx) throws IOException
	{
		xx.println(customerName);
		xx.println(reservationTime);
		xx.println(numberOfCustomers);
		xx.println(tableNumber);
		xx.println(additionalRequest);
	}

	public void read(BufferedReader xx) throws IOException
	{
		customerName = xx.readLine();
		reservationTime = Long.parseLong(xx.readLine());
		numberOfCustomers = Integer.parseInt(xx.readLine());
		tableNumber = Integer.parseInt(xx.readLine());
		additionalRequest = xx.readLine();
	}
}
