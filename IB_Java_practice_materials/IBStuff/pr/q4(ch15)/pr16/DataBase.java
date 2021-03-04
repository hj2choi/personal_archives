import java.io.*;
class DataBase
{
	final static int rollSize = 1000;
	static java.util.Scanner s = new java.util.Scanner(System.in);
	
	public static void clearscreen()		//Clears screen
	{
		for (int i=0;i<40 ;i++ )
			System.out.println("");
	}

	public static String pad(String n, int tab)
	{
		do 
			n+=" ";
		while (n.length()<tab);
		return n+" ";
	}
	
	public static String allignedString(String a, String b, int allign)			//Allign (String b) on (byte allign)th line. (String a) is what comes earlier.
	{
		String ss = a;
		for (int i=0;i<a.length()-allign ;i++ )
			ss+="\b";
		for (int i=allign;i>a.length() ;i-- )
			ss+=" ";
		ss+=b;
		return ss;
	}

	public static String allignToMiddle(String a, String c, int distance)	// " aaa ccc" if distance = 5,  " aa  cc" if distance = 5
	{
		String ss = "";
		int space=distance-a.length();
		if (space%2!=0)
		    ss+=" ";
		for (int i=0;i<space/2 ;i++ )
		    ss+=" ";
		ss+=a;
		for (int i=0;i<space/2 ;i++ )
		    ss+=" ";
		return ss+c;
	}

	public static char firstLetterOf(String ss)		//return first letter
	{
		if (ss.equals(""))
			return '*';
		return ss.charAt(0);
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
			return 0;
		}
	}

	public static void printLine(int a)		//Spaces (int a) lines
	{
		for (int i=0;i<a ;i++ )
			System.out.println("");
	}

	public static Employee[] delete(int roll, int found, Employee[] staff)
	{
		//System.out.println(roll);
		//System.out.println(found);
		Employee[] staffOverwrite = new Employee[roll+1];
		for (int i=0;i<found ;i++ )
			staffOverwrite[i] = new Employee(staff[i].getName(), staff[i].getWage(), staff[i].getBirth());
		for (int j=found;j<roll-1 ;j++ )
			staffOverwrite[j] = new Employee(staff[j+1].getName(), staff[j+1].getWage(), staff[j+1].getBirth());
		return staffOverwrite;
	}

	public static int[] searchEngine(String ss, Employee[] staff, int roll)
	{
		if (ss.equals("*"))
			ss="";
		int[] list = new int[roll];
		for (int i=0;i<roll ;i++ )	 //initialize array
			list[i] = -1;
		int numberFound = 0;
		for (int i=0;i<roll ;i++ )
		{
			char[] disAssemble = staff[i].getName().toCharArray();
			for (int k=0;k<staff[i].getName().length() ;k++ )
			{
				String reAssembledString ="";
				for (int j=k;j<(staff[i].getName()).length() ;j++ )
					reAssembledString+=disAssemble[j];
				//pp(reAssembledString);	// disable two comments to test algorithm
				if ((reAssembledString.toLowerCase()).startsWith(ss.toLowerCase()))
				{
					//pp("					Found!!");
					list[numberFound]=i;
					numberFound++;
					break;
				}
			}
		}
		return list;
	}

	public static Employee[] sort(Employee[] data, int roll, int sortBy)		//returns array with sorted integers. From lower value to higher value if a=0, from higher to lower if a=1
	{
		Employee staff[] = new Employee[rollSize];
		staff = data;
		Employee swap;
		for (int k=0;k<roll ;k++ )
			for (int i=0;i<roll ;i++ )			//0 : sortbyname	1: sortByWage
				if ((staff[k].getName()).compareTo( (staff[i].getName()) )<0 && sortBy==0 || (staff[k].getWage())<( (staff[i].getWage()) ) && sortBy==1 || (staff[k].getBirth())<( (staff[i].getBirth()) ) && sortBy==2)
				{
					swap = new Employee(data[k].getName(), data[k].getWage(), data[k].getBirth());
					data[k] = new Employee(data[i].getName(), data[i].getWage(), data[i].getBirth());
					data[i] = swap;
				}
		return data;
	}

	public static String integerToWord(long num)
	{
		if (num==0)
			return "no ";
		String word="";
		if (num>=(1000000*100*100))		//Billions
		{
			word+=digitsBelowHundreds(num/(1000000*100*100));
			word+="trillion ";
			if (num%(1000000*100*100)!=0)
				word+="and ";
		}
		num=num%(1000000*100*100);
		if (num>=1000000*100)		//Billions
		{
			word+=digitsBelowHundreds(num/(1000000*100));
			word+="billion ";
			if (num%(1000000*100)!=0)
				word+="and ";
		}
		num=num%(1000000*100);
		if (num>=1000000)		//Millions
		{
			word+=digitsBelowHundreds(num/1000000);
			word+="million ";
			if (num%1000000!=0)
				word+="and ";
		}
		num=num%1000000;
		if (num>=1000)			//thousands
		{
			word+=digitsBelowHundreds(num/1000);
			word+="thousand ";
			if (num%1000!=0)
				word+="and ";
		}
		num=num%1000;
		word=word+digitsBelowHundreds(num);
		return word;
	}
	public static String digitsBelowHundreds(long num)
	{
		String word ="";
		if (num>=100)		//hundreds
		{
			word+=numToString(num/100);
			word+="hundred ";
			if (num%100!=0)
				word+="and ";
		}
		num=num%100;//tens
		if (num>=10 && num<20)
				return word+tensToStringException(num%100);
		else if (num>=20)
			word+=tensToString(num/10, num%10==0);
		num=num%10;//ones
		word+=numToString(num);
		return word;
	}
	public static String numToString(long num)
	{
		String word="";
		if (num==1)
		word+="one ";
		if (num==2)
			word+="two ";
		if (num==3)
			word+="three ";
		if (num==4)
			word+="four ";
		if (num==5)
			word+="five ";
		if (num==6)
			word+="six ";
		if (num==7)
			word+="seven ";
		if (num==8)
			word+="eight ";
		if (num==9)
			word+="nine ";
		return word;
	}
	public static String tensToString(long num, boolean hyphen)
	{
		String word="";
		String a="-";
		if (hyphen)
			a=" ";
		if (num==2)
			word+="twenty"+a;
		if (num==3)
			word+="thirty"+a;
		if (num==4)
			word+="forty"+a;
		if (num==5)
			word+="fifty"+a;
		if (num==6)
			word+="sixty"+a;
		if (num==7)
			word+="seventy"+a;
		if (num==8)
			word+="eighty"+a;
		if (num==9)
			word+="ninety"+a;
		return word;
	}
	public static String tensToStringException(long num)
	{
		String word="";
		if (num==10)
			word+="ten ";
		if (num==11)
			word+="eleven ";
		if (num==12)
			word+="twelve ";
		if (num==13)
			word+="thirteen ";
		if (num==14)
			word+="fourteen ";
		if (num==15)
			word+="fifteen ";
		if (num==16)
			word+="sixteen ";
		if (num==17)
			word+="seventeen ";
		if (num==18)
			word+="eighteen ";
		if (num==19)
			word+="nineteen ";
		return word;
	}


}