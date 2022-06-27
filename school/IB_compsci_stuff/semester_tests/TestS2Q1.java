/* ////////// /* /////////// /* //////////// /* ////////////// /*
		Programming Test Quarter 3
		13.March.2012
*/ ////////// */ //////////// */ //////////// */ //////////// *
public class TestS2Q1
{
	static java.util.Scanner s = new java.util.Scanner(System.in);
	static boolean error = false;

	static void p(String x)
	{	System.out.print(x);}
	static void pp(String x)
	{	System.out.println(x);}
	
	public static void printLine(int a)		//Spaces (int a) lines
	{
		for (int i=0;i<a ;i++ )
			System.out.println("");
	}

	static int safeInput()			//Simple s.nextInt() method. Safe from datatype exception
	{
		String input = s.next();
		try
		{
			return Integer.parseInt(input);
		}
		catch (Exception e)
		{
			System.out.println("Invalid input! Caused by : "+e);
			error = true;
			return 0;
		}
	} 

	public static String allignedString(String a, String b, int allign)			//Allign (String b) on (byte allign)th line. (String a) is what comes earlier.
	{
		String print="";
		print+=a;
		for (int i=0;i<a.length()-allign ;i++ )
			print+="\b";
		for (int i=allign;i>a.length() ;i-- )
			print+=" ";
		print+=b;
		return print;
	}

	public static void main(String args[])
	{
		Time[] time = new Time[10];
		for (int i=0;i<10 ;i++ )
			time[i]=new Time();
		pp("Time Calculator\n^^^^^^^^^^^^^^^^^^^\nEnter a time in minutes");
		for (int i=0;i<2 ;i++ )
		{	
			time[i] = new Time(safeInput());
			if (error==true)
				System.exit(0);
			if (i==1)
				break;
			pp("Enter another time in minutes");
		}
		for (int i=0;i<2 ;i++ )
			p("\nTime Object Created\n"+time[i]);
		printLine(2);
		pp(allignedString("sum","= "+time[2].add(time[0],time[1]),12));
		pp(allignedString("Difference","= "+time[3].subs(time[0],time[1]),12));
	}
}



class Time
{
	final static int HoursInDays = 24;
	int hours;
	int minutes;
	int days;
	
	public Time()
	{}

	public Time(int mins)
	{
		minutes = Math.abs(mins);
		minutes%=60;
		hours=minutes/60;
		days=hours%60;
		hours=hours/HoursInDays;
	}

	public String toString()
	{
		String d="";
		if (minutes<10)
			d="0";
		if (days>0)
			return days+"d "+hours+":"+d+minutes;
		return hours+":"+d+minutes;
	}


	public int timeToMins()
	{
		int mins = 0;
		try		// just unsure if it works without variable initialized.
		{
			mins+=(days*HoursInDays*60)+hours*60+minutes;
		}
		catch (Exception e)
		{
			mins+=hours*60+minutes;
		}
		return mins;
	}

	public Time add(Time a, Time b)
	{
		Time timeSum = new Time(a.timeToMins()+b.timeToMins());
		return timeSum;
	}

	public Time subs(Time a, Time b)
	{
		Time timeSub = new Time(a.timeToMins()-b.timeToMins());
		return timeSub;
	}

}
	
	