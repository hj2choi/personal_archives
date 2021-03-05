import java.io.*;
class TimeManager
{
	private int managerNumber;
	private int roll;
	private TimeAgent[] time;
	
	TimeManager(int managerNumber, int roll)				//initiate timeAgent
	{
		this.roll=roll;
		this.managerNumber=managerNumber;
		time = new TimeAgent[1000000];
		for (int i=0;i<roll ; i++)
			time[i] = new TimeAgent();
	}
	
	public String toString()
	{
		String ss="";
		for (int i=0;i<roll ;i++ )
			ss+=time[i].completedTimerCountString()+"\n";
		return ss;
	}

	public int getRoll(){return roll;}

	public void createAdditionalTimeManager()
	{
		time[roll++]=new TimeAgent();
		//save(managerNumber);
	}

	public void initiateTimer(int index, long timer, long rateOfIncrease)
	{
		time[index].initiateTimer(timer, rateOfIncrease);
		//save(managerNumber);
	}

	public void stopTimer(int index)
	{
		time[index].stopTimer();
		//save(managerNumber);
	}

	public void processAll(int managerNumber) throws IOException
	{
		for (int i = 0; i<=roll; i++)
		{
			try{time[i].process();}
			catch (Exception e){}
		}
		save(managerNumber);

	}

	public long getTimerCount(int index){return time[index].completedTimerCount(false);}

	public void load(int managerNumber) throws IOException		// returns array with loaded data of file (String filename)
	{
		roll=0;
		try
		{
			File f = new File("TimeDatabase"+managerNumber+".txt");
			FileReader fr = new FileReader(f);
			BufferedReader in  = new BufferedReader(fr);
			while (in.ready())
			{
				TimeAgent aa = new TimeAgent();
				aa.read(in);
				time[roll++] = aa;
			}
		}
		catch (Exception e)
		{
			System.out.println("Error while reading file! Caused by : "+e);
			System.exit(0);
		}
	}

	public void save(int managerNumber)
	{
		try
		{
			File ff = new File("TimeDatabase"+managerNumber+".txt"); //create the file
			FileWriter fw = new FileWriter(ff);
			PrintWriter out = new PrintWriter(fw);
			for (int i = 0; i<=roll; i++)
			{
				try{time[i].save(out);}
				catch (Exception e){}
			}
			out.close();
		}
		catch (IOException e)
		{
			System.out.println("Error Occured while saving the file.");
		}
	}
}



class TimeAgent
{
	private long initiateTime;
	private long lastProcessedTime;
	private long timer;
	private double timerProgress;
	private long rateOfIncrease;
	private boolean timerStatus;
	private long tempData;

	TimeAgent()
	{
		initiateTime=System.currentTimeMillis();
		lastProcessedTime=System.currentTimeMillis();
		timer=0;
		timerProgress=0;
		rateOfIncrease=0;
		timerStatus=false;
	}

	public void modifyRateOfIncrease(long value)
	{
		rateOfIncrease=value;
	}
	
	public String toString()
	{
		process();
		return "Total-Time    = "+(new Clock(totalElapsedTime()))+"\n"+"ElapsedTime   = "+(new Clock(System.currentTimeMillis()-tempData))+"\nTimer         = "+formatNumber((long)timerProgress)+"/"+formatNumber(timer)+"   count="+completedTimerCount(false);
	}
	public String completedTimerCountString()
	{
		return "Timer         = "+formatNumber((long)timerProgress)+"/"+formatNumber(timer)+"   count="+completedTimerCount(false);
	}

	public long getInitiateTime(){return initiateTime;}
	public long getLastProcessedTime(){return lastProcessedTime;}
	public long getTimer(){return timer;}
	public double getTimerProgress(){return timerProgress;}
	public long getRateOfIncrease(){return rateOfIncrease;}
	public boolean getTimerStatus(){return timerStatus;}
	public long getTempData(){return tempData;}

	public void initiateTimer(long timer, long rateOfIncrease)
	{
		process();
		timerStatus=true;
		this.timer=timer;
		this.timerProgress=0;
		this.rateOfIncrease=rateOfIncrease;
	}
	
	public void stopTimer()
	{
		process();
		timerStatus=false;
		timer=0;
		timerProgress=0;
		rateOfIncrease=0;
	}

	public void process()
	{
		long temp = elapsedTime();
		if (timerStatus)
			timerProgress+=(double)temp/(double)rateOfIncrease;
		tempData=lastProcessedTime;
		lastProcessedTime+=temp;
	}
	
	public long completedTimerCount(boolean discardCompletedTimer)
	{
		if (timerStatus && timer!=0)
		{
			long c = (long)timerProgress/timer;
			if (discardCompletedTimer)
				timerProgress-=(c*timer);
			return c;
		}
		else
			return 0;
	}
	
	public long totalElapsedTime()
	{
		return System.currentTimeMillis()-initiateTime;
	}

	public long elapsedTime()
	{
		return System.currentTimeMillis()-lastProcessedTime;
	}

	public void save(PrintWriter xx) throws IOException
	{
		xx.println(initiateTime);
		xx.println(lastProcessedTime);
		xx.println(timerStatus);
		xx.println(timer);
		xx.println(timerProgress);
		xx.println(rateOfIncrease);
	}
	public void read(BufferedReader xx) throws IOException
	{
		initiateTime = Long.parseLong(xx.readLine());
		lastProcessedTime = Long.parseLong(xx.readLine());
		timerStatus = Boolean.parseBoolean(xx.readLine());
		if (timerStatus)
		{
			timer = Long.parseLong(xx.readLine());
			timerProgress = Double.parseDouble(xx.readLine());
			rateOfIncrease = Long.parseLong(xx.readLine());
		}
		else
			for (int i=0;i<3 ;i++ )
				xx.readLine();
	}


	static String formatNumber(long num)
	{
		String formattedNumber = "";
		char[] numCharacters = (""+num).toCharArray();
		int masterNumberLength = (""+num).length();
		int numberLength = masterNumberLength;
		while (numberLength>=1)
		{
			formattedNumber+=numCharacters[masterNumberLength-numberLength];
			numberLength--;
			if (numberLength%3==0 && numberLength!=0)
				formattedNumber+=",";
		}
		return formattedNumber;
	}

}


class Clock
{
	final int hoursInDays = 24;
	long days;
	long hours;
	long minutes;
	long seconds;
	long milliseconds;

	public Clock(long ms)
	{
		milliseconds=ms;
	}

	public void convertFormat()
	{
		seconds = milliseconds/1000;
		milliseconds%=1000;
		minutes = seconds/60;
		seconds%=60;
		hours=minutes/60;
		minutes%=60;
		days=hours/hoursInDays;
		hours%=24;
	}
	
	public long getMilliseconds()
	{
		return milliseconds;
	}

	public String toString()
	{
		if (milliseconds>=1000)
			convertFormat();
		if (days>0)
			return days+"d "+allignRight(""+hours, 2)+":"+allignRight(""+minutes, 2)+":"+allignRight(""+seconds, 2)+"."+allignRight(""+milliseconds, 3);
		return allignRight(""+hours, 2)+":"+allignRight(""+minutes, 2)+":"+allignRight(""+seconds, 2)+"."+allignRight(""+milliseconds, 3);
	}

	public long convertToMilliseconds()
	{
		return (days*hoursInDays*3600000)+(hours*3600000)+(minutes*60000)+(seconds*1000)+milliseconds;
	}

	public Clock add(Clock a, Clock b)
	{
		return new Clock(a.convertToMilliseconds()+b.convertToMilliseconds());
	}

	public Clock subs(Clock a, Clock b)
	{
		return new Clock(a.convertToMilliseconds()-b.convertToMilliseconds());
	}




	public static String pad(String n, int tab)
	{
		do 
			n+=" ";
		while (n.length()<tab);
		return n;
	}
	public static String allignRight(String a, int allign)
	{
		String ss="";
		for (int i=0;i<allign-a.length() ;i++ )
			ss+="0";
		return ss+a;
	}

}