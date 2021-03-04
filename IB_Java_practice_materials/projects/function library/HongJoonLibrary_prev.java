import java.math.BigInteger;
import java.io.*;
class HongJoonLibrary			//	 Just tried to test
{
/*
sorting algorithm
==binary search==
split data into half















*/
/*====================================
Class for methods
This class contains some useful methods that aims to be helpful in most programming.
This library is mainly in compliance with (int) data type. Thus, changing some data type is needed sometimes.
***This class also contains some final variables.***
Version 23.03.2012
Created by HongJoon Choi, with some references to class IBIO.
*///====================================
/*
maxArraySize  : Int
screenSize : Int
delayMilliSeconds : Int
*/
/*====================================
--Method Summary-- 33 total
return type		method(input data type) : description

	[[Print, input methods]]
void			pp(info)											: System.out.println(info). Made especially for lazy people who aren't bothered to type System.out.println.
void			p(info)											: System.out.print(info). Made for same reason as above.
int			safeInput()										: it acts exacty same as nextInt(); but it catches exception. for ex, if you enter abc, it returns 0 instead of terminating program.
void			printLine(int n)									: It prints ("\n") n times.
void			clearscreen()										: It clears screen. With specified degree in int screenSize below.
void			textAnimation(String info)							: It is advanced version of System.out.println(String info). It displays textAnimation.
String		pad(String n, int index)							: Allign n to given index(Allign on RHS). (From IB CS "introduction to Java Programming" PDF file)
String		AllignedString(String a, String b, int index)			: Allign b on (index)th index no matter the length or content of a, which comes before b. (Allign on LHS)
String		allignToMiddle(String a, String c, int distance)		: Alligns String a, which comes before c, to middle. int distance is position of String c." aaa ccc" if distance = 5

	[[DataBase methods]]
double		random(double min, double max)					: returns random value that ranges from min to max.
int			searchFor(int x, int[] data)							: Search for x through array 'int[] data' and returns position of it. If not found, returns -1.
*String[]		delete(int roll, int require, String[] data)				: Delete data in required ArrayLocation.
int[]			searchEngine(String ss, String[] data)				: returns all array locations of array of String that is in relevance to (String ss). (Ignore capital letters)
int			minOf(int[] data) & maxOf(int[] data)				: returns position of array in which smallest or highest number in (int[] data) occurs.
int[]			sort(int[] data, int a)								: sorts out int[] data and returns sorted array. If a=0, sorts in ascending order. Else if a=1, sorts in descending order.
int			arraySize(int[] data)								: returns size of the array.

	[[Thread, IO methods]]
void			delay(long milliSecond)							: Pause thread for given milliseconds.
*void		fileWriter(String filename)							: Simply declares fileWriter.
*String[]		readFile(String filename)							: Reads the whole file and returns them into array.
*void		save(String filename, String[] data)					: Saves data into a file.

	[[program testing methods]]
void			test()											: System.out.println("read"); for easier examining program.
long		time()											: Retuns current time in milliseconds.
void			speedTest(), speed()								: checks time taken for calculations
void			beep()											: generates beep sound

	[[Math methods]]				(Usually from algorithms used for Project Euler or math IA)
double[]		solveQuadraticEquation(double a, double b, double c) : from ax^2+bx+c, returns two x values in array
boolean		primeCheck(int num)								: Returns true if argument is prime number. Else, return false.
String		inverseString(String ss)							: Retruns inversed string of ss.
int			binaryToDecimal(String bin)						: Converts String bin into binary. If binary is not entered in, 0 is returned.
String		numArrangement(int num)						: Return value as following example : if argument is '13579', '1010101010' is returned. If argument is '1123390', '1000002121' is returned.
int			gcd(int n1, int n2)									: Returns Greatest common divisor of two arguments.
*int			lcm(int n1, int n2)									: Returns Least common multiple of two arguments. If it doesn't exist under integer range, returns -1
long[]		primeNumbersUntil(long num)						: Returns all prime numbers until num in array.
int			XOR(int x, int y)									: Returns x XOR y.
String		encryptOrDecrypt(String data, int key)				: Encrypts String argument with master key argued(Using XOR method). If it's already encrypted, it encrypts it.
String		integerToWord(long num)							: Returns String that writes num in words.(compiles with British language )

*///====================================
//Program Code
//===================================Variables====================================
	final static int maxArraySize =30000000;
	final static int screenSize = 30;
	final static int delayMilliSeconds = 15;
	static long initialTimeInSpeedCheck=0;
	static java.util.Scanner s = new java.util.Scanner(System.in);
//===================================Print/InputMethods====================================
	static void p(String info)
	 {	System.out.print(info);}
	static void p(char info)
	 {	System.out.print(info);}
	static void p(byte info)
	 {	System.out.print(info);}
	static void p(short info)
	 {	System.out.println(info);}
	static void p(boolean info)
	 {	System.out.print(info);}
	static void p(int info)
	 {	System.out.print(info);}
	static void p(long info)
	 {	System.out.print(info);}
	static void p(double info)
	{	System.out.print(info);}

	static void pp()
	 {	System.out.println();}
	static void pp(String info)
	 {	System.out.println(info);}
	static void pp(char info)
	 {	System.out.println(info);}
	static void pp(byte info)
	 {	System.out.println(info);}
	static void pp(short info)
	 {	System.out.println(info);}
	static void pp(boolean info)
	 {	System.out.println(info);}
	static void pp(int info)
	 {	System.out.println(info);}
	static void pp(double info)
	 {	System.out.println(info);}

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

	public static void clearscreen()		//Clears screen
	{
		for (int i=0;i<screenSize ;i++ )
			System.out.println("");
	}

	public static void textAnimation(String a)		//Prints (String a) character by character
	{
		char[] txt = new char[a.length()];
		txt = a.toCharArray();
		for (int i = 0;i<a.length();i++ )
		{
			delay(delayMilliSeconds);
			System.out.print(txt[i]);
		}
		System.out.println("");
	}

	public static String pad(String n, int tab)
	{
		do 
			n+=" ";
		while (n.length()<tab);
		return n;
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

	public static String allignToMiddle(String a, String c, int distance)	// " aaa ccc" if distance = 5
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

	
//===================================CalculationMethods====================================
	public static double random(double a, double b)		//returns random number rnging from (double a) to (double b)
	{
		return a+Math.random()*(b-a);
	}
	public static double round(double num, int digit)
	{
		return (double)((int)(num*digit))/digit;
	}
	public static int searchFor(int a, int[] s)		//Search for (int a) in array (int[]s) and returns position of (int a) else, returns -1
	{
		for (int i=0;i<maxArraySize ;i++ )
		{
			try
			{
				if (a == s[i])
					return i;
			}
			catch (Exception e)
			{
				return -1;
			}
		}
		return -1;
	}
	
	public static String[] delete(int roll, int found, String[] staff)
	{
		//System.out.println(roll);
		//System.out.println(found);
		String[] staffOverwrite = new String[roll];
		for (int i=0;i<found ;i++ )
		{
			staffOverwrite[i] = staff[i];
		}
		for (int j=found;j<roll-1 ;j++ )
		{
			staffOverwrite[j] = staff[j+1];
		}
		return staffOverwrite;
	}

	public static int[] searchEngine(String ss, String[] staff, int roll)
	{
		int[] list = new int[roll];
		for (int i=0;i<roll ;i++ )	 //initialize array
			list[i] = -1;
		int numberFound = 0;
		for (int i=0;i<roll ;i++ )
		{
			char[] disAssemble = staff[i].toCharArray();
			for (int k=0;k<staff[i].length() ;k++ )
			{
				String reAssembledString ="";
				for (int j=k;j<staff[i].length() ;j++ )
					reAssembledString+=disAssemble[j];
				pp(reAssembledString);	// enable it to test algorithm
				if ((reAssembledString.toLowerCase()).startsWith(ss.toLowerCase()))
				{
					pp("					Found!!");
					list[numberFound]=i;
					numberFound++;
					break;
				}
			}
		}
		return list;
		//Method to print searched Strings : 
		/*
		for (int i=0;i<maxArraySize ;i++ )
		{
			if (listFound[i]==0)
				break;
			pp(data[listFound[i]]);
		}
		*/
	}
	
	public static int minOf(double[] list, int length)
	{
		double temp=list[0];
		int position=0;
		for (int i=0;i<length;i++)
			if (list[i]<temp)
			{
				temp=list[i];
				position=i;
			}
		return position;
	}

	public static int maxOf(double[] list, int length)
	{
		double temp=list[0];
		int position=0;
		for (int i=0;i<length;i++)
			if (list[i]>temp)
			{
				temp=list[i];
				position=i;
			}
		return position;
	}

	public static int[] sort(int[] data, int length, int a)		//returns array with sorted integers. From lower value to higher value if a=0, from higher to lower if a=1
	{
		int swap=0;
		for (int k=0;k<length ;k++ )
			for (int i=0;i<length ;i++ )
				if ((data[k]<data[i] && a==0) || (data[k]>data[i] && a==1))
				{
					swap = data[k];
					data[k] = data[i];
					data[i] = swap;
				}
		return data;
	}

	public static int[] selectionSort(int[] list)//select smallest number
	{
		int[] sortedList = new int[list.length];
		for (int i=0;i<list.length; i++)
		{
			int locationOfMin = i;
			for (int j=i;j<list.length ;j++ )
				if (list[j]<list[i])
					locationOfMin = j;
			sortedList[i] = list[locationOfMin];
		}
		return sortedList;

	}

	public static int[] bubbleSort(int[] list)//flags, centinals
	{
		boolean needNextPass=true;
		for (int i=1; i<list.length && needNextPass ; i++)
		{
			//array may be sorted and next pass not needed
			needNextPass=false;
			for (int j=0; j<list.length-i; j++)
			{
				if (list[j]>list[j+1])
				{
					//System.out.println("a");
					//swap list [j] with [j+1]
					int temp=list[j];
					list[j]=list[j+1];
					list[j+1] = temp;
					
					needNextPass=true;
				}
			}
		}
		return list;

	}

	public static int arraySize(int[] data)
	{
		for (int i=0;i<maxArraySize ;i++ )
		{
			try
			{
				data[i]++;
			}
			catch (Exception e)
			{
				return i-1;
			}
		}
		return maxArraySize;
	}

//===================================Thread/IO/etc...====================================
	public static void delay(int a)		//Stop Thread for (int a) MilliSeconds
	{
		try 
		{ 
			Thread.sleep((int)a);
		}
		catch (Exception e)
		{ 
			System.out.println("Delay Function Error \n Caused by:"+e);
		}
	}

	public static void fileWriter(String fileName) throws IOException		//	preperation for writing file (String filename)
	{
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		PrintWriter out = new PrintWriter(fw);
	}
	
	public static String[] readFile(String fileName) throws IOException		// returns array with loaded data of file (String filename)
	{
		String[] file = new String[maxArraySize];
		int count=0;
		File f = new File(fileName);
		FileReader fr = new FileReader(f);
		BufferedReader in  = new BufferedReader(fr);
		while (in.ready())
		{
			file[count] = in.readLine();
			count++;
		}
		return file;
	}

	public static void save(String fileName, String[]data) throws IOException
	{
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		PrintWriter out = new PrintWriter(fw);
		for (int i=0;i<maxArraySize ;i++ )
		{
			try
			{
				out.println(data[i]);
			}
			catch (Exception e)
			{
				break;
			}
		}
		out.close();
	}

//===================================ProgramTest====================================
	public static void test()		
	{
		System.out.println("read");
	}

	public static long time()
	{
		return System.currentTimeMillis();
	}

	public static void speedTest()
	{
		initialTimeInSpeedCheck=System.currentTimeMillis();
	}
	public static void speed()
	{
		System.out.println("time = "+(System.currentTimeMillis()-initialTimeInSpeedCheck)+"ms");
	}
	public static void beep()
	{
		System.out.print((char)7);
	}
//===================================Math Methods====================================
	public static double[] solveQuadraticEquation(double a, double b, double c)
	{	
		double[] x = new double[2];
		int i=0;
		if (b*b-4*a*c>0)
		{
			x[0] = (-b-Math.sqrt(b*b-4*a*c))/(2*a);
			x[1] = (-b-Math.sqrt(b*b-4*a*c))/(2*a);
		}
		return x;
	}
	public static boolean primeCheck(int num)
	{
		for (int a = 2;a<num/1.8 ;a++ )
			if (num%a==0 && num!=2)
				return false;
		return true;
	}

	public static String inverseString(String ss)
	{
		String result="";
		char[] ssC = ss.toCharArray();
		for (int i=ss.length()-1;i>=0 ;i-- )
			result = result+ssC[i];
		return result;
	}

	public static int binaryToDecimal(String bin)
	{
		char[] xx = bin.toCharArray();
		int dec = 0;
		for (int i=0;i<bin.length() ;i++ )
			if (xx[i]!='1' && xx[i] !='0')
				return 0;
		for (int k = 0; k<bin.length();k++)			// binary conversion algorithm
		{
			int t=1;
			for (int a = bin.length()-1; a>k; a--)
				t=t*2;
			dec = dec+((int)xx[k]-48)*t;
		}
		return dec;
	}

	public static String numArrangement(int num)
	{
		int[] convert = new int[10];
		String arrangedString = "";
		while (num>0)
		{
			convert[num%10]++;
			num/=10;
		}
		for (int i=9;i>=0 ;i-- )
			arrangedString+=convert[i];
		return arrangedString;
	}

	static int gcd(int f, int g)//Greatest common divisor
	{
		while (true)
		{
			if (f>g)
				f = f-g;
			if (g>f)
				g = g-f;
			if (g == f)
				break;
		}
		return g;
	}

	static int lcm (int n1, int n2)//Least common multiple
	{
		return -1;
	}

	public static int[] primeNumbersUntil(int num)
	{
		boolean chk = false;
		int[] prime = new int[(int)(num/5)+100];
		prime[0] = 2;
		int roll=1;
		for (int you=1;you<num ;you+=2 )		//for optimum performance
		{
			chk=false;
			for (int a = 2;a<you ;a++ )									//factor finding
			{
				if (you%a==0)
				{
					chk = true;
					break;		//for optimum performance
				}
			}
			if (!chk && you!=1)
			{
				prime[roll]=you;
				roll++;
			}
		}
		return prime;
	}
	
	public static int XOR(int x, int y)
	{
		byte maxBinSize=25;
		String XOR = "";
		String xBin = "";//Integer.toBinaryString(x);
		String yBin = "";//Integer.toBinaryString(y);
		for (int i=0;i<maxBinSize-(Integer.toBinaryString(x)).length() ;i++)
			xBin+="0";
		for (int i=0;i<maxBinSize-(Integer.toBinaryString(y)).length() ;i++)
			yBin+="0";
		xBin+=""+Integer.toBinaryString(x);
		yBin+=""+Integer.toBinaryString(y);
		for (int i=0;i<maxBinSize ;i++ )
		{
			if (xBin.charAt(i)!=yBin.charAt(i))
				XOR+="1";
			else
				XOR+="0"; 
		}
		return binaryToDecimal(XOR);
	}

	public static String encryptOrDecrypt(String data, int masterKey)
	{
		char[] xx = data.toCharArray();
		data="";
		for (int j=0;j<10000 ;j++ )
		{
			try
			{
				data+=(char)(XOR((int)xx[j],masterKey));
			}
			catch (Exception e)
			{
				break;
			}
		}
		return data;
	}

//===================================IntegerToWord(String)====================================
	public static String integerToWord(long num)
	{
		if (num==0)
			return "zero ";
		String word="";
		if (num>=(1000000000*100))		//Billions
		{
			word+=digitsBelowHundreds(num/(1000000000*100));
			word+="trillion ";
			if (num%(1000000000*100)!=0)
				word+="and ";
		}
		num=num%(1000000000*100);
		if (num>=1000000000)		//Billions
		{
			word+=digitsBelowHundreds(num/1000000000);
			word+="billion ";
			if (num%1000000000!=0)
				word+="and ";
		}
		num=num%1000000000;
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
		else if (num==2)
			word+="two ";
		else if (num==3)
			word+="three ";
		else if (num==4)
			word+="four ";
		else if (num==5)
			word+="five ";
		else if (num==6)
			word+="six ";
		else if (num==7)
			word+="seven ";
		else if (num==8)
			word+="eight ";
		else if (num==9)
			word+="nine ";
		return word;
	}
	public static String tensToString(long num, boolean hyphen)
	{
		String word="";
		String a="-";
		if (hyphen)
			a=" ";
		else if (num==2)
			word+="twenty"+a;
		else if (num==3)
			word+="thirty"+a;
		else if (num==4)
			word+="forty"+a;
		else if (num==5)
			word+="fifty"+a;
		else if (num==6)
			word+="sixty"+a;
		else if (num==7)
			word+="seventy"+a;
		else if (num==8)
			word+="eighty"+a;
		else if (num==9)
			word+="ninety"+a;
		return word;
	}
	public static String tensToStringException(long num)
	{
		String word="";
		if (num==10)
			word+="ten ";
		else if (num==11)
			word+="eleven ";
		else if (num==12)
			word+="twelve ";
		else if (num==13)
			word+="thirteen ";
		else if (num==14)
			word+="fourteen ";
		else if (num==15)
			word+="fifteen ";
		else if (num==16)
			word+="sixteen ";
		else if (num==17)
			word+="seventeen ";
		else if (num==18)
			word+="eighteen ";
		else if (num==19)
			word+="nineteen ";
		return word;
	}
}
