import java.math.BigInteger;
import java.io.*;
/*contents
15----------Print methods
100---------Calc methods





*/
class HongJoonLibrary			//	 Just tried to test
{
	final static int screenSize = 30;
	final static int delayMilliSeconds = 15;
	static long initialTimeInSpeedCheck=0;
	static java.util.Scanner s = new java.util.Scanner(System.in);
//===================================Print methods====================================
	
	static int inputInt()			//Simple s.nextInt() method. Safe from datatype exception
	{	
		String input = s.next();
		try{return Integer.parseInt(input);}
		catch (Exception e){System.out.println("Invalid input! Caused by : "+e);return 0;}
	}

	public static void spaceLines(int a)		//Spaces (int a) lines
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
		char[] txt = a.toCharArray();
		for (int i = 0;i<a.length();i++ )
		{
			delay(delayMilliSeconds);
			System.out.print(txt[i]);
		}
		System.out.println("");
	}

	public static String allignAtPosition(String a, String b, int allign)			//Allign (String b) on (byte allign)th line. (String a) is what comes earlier.
	{
		String ss = a;
		for (int i=0;i<a.length()-allign ;i++ )
			ss+="\b";
		for (int i=allign;i>a.length() ;i-- )
			ss+=" ";
		ss+=b;
		return ss;
	}

	public static String allignLeft(String n, int tab)						// writes string and leaves space
	{
		do 
			n+=" ";
		while (n.length()<tab);
		return n;
	}

	public static String allignMiddle(String a, int distance)	// " aaa ccc" if distance = 5
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
		return ss;
	}

	public static String allignRight(String a, int allign)
	{
		String ss="";
		for (int i=0;i<allign-a.length() ;i++ )
			ss+=" ";
		return ss+a;
	}

//===================================CalculationMethods====================================
	public static double random(double a, double b)		//returns random number rnging from (double a) to (double b)
	{
		return a+Math.random()*(b-a);
	}

	public static double round(double num, int digit)             //rounds number to given digit(decimal place)
	{
		int power=1;
		for (int i=0; i<digit; i++)
			power*=10;
		return (double)((int)(num*power))/power;
	}

	public static int summision(int[] s)
	{
		int n=0;
		for (int i=0; i<s.length; i++)
			n+=s[i];
		return n;
	}

	public static int search(int a, int[] s)		//Search for (int a) in array (int[]s) and returns position of (int a) else, returns -1
	{
		for (int i=0;i<s.length ;i++ )
			if (a == s[i])
				return i;
		return -1;
	}

	public static String[] delete(int roll, int found, String[] staff)		//delete data from list
	{
		String[] staffOverwrite = new String[roll];
		for (int i=0;i<found ;i++ )
			staffOverwrite[i] = staff[i];
		for (int j=found;j<roll-1 ;j++ )
			staffOverwrite[j] = staff[j+1];
		return staffOverwrite;
	}

	public static int mean(int[] list, int length)
	{
		int summision=0;
		for (int i=0; i<length; i++)
			summision+=list[i];
		return summision/length;
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

//===================================Thread/IO/etc...====================================
	public static void delay(int a)		//Stop Thread for (int a) MilliSeconds
	{
		try {Thread.sleep((int)a);}
		catch (Exception e){System.out.println("Delay Function Error \n Caused by:"+e);}
	}
	
	public static String[] fileReader(String fileName) throws IOException		// returns array with loaded data of file (String filename)
	{
		int count=0;
		String[] file = new String[30000000];
		File f = new File(fileName);
		FileReader fr = new FileReader(f);
		BufferedReader in  = new BufferedReader(fr);
		while (in.ready())
			file[count++] = in.readLine();
		return file;
	}

	public static void save(String fileName, String[]data, int arraySize) throws IOException
	{
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		PrintWriter out = new PrintWriter(fw);
		for (int i=0;i<arraySize ;i++ )
				out.println(data[i]);
		out.close();
	}

//===================================ProgramTest====================================
	public static void t()		
	{
		System.out.println("test");
	}

	public static long time()
	{
		return System.currentTimeMillis();
	}

	public static void startTimer()
	{
		initialTimeInSpeedCheck=System.currentTimeMillis();
	}

	public static void endTimer()
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
	public static boolean[] generatePrimes(int num)// for enhancedPrimeCheck
	{
		boolean[] prime = new boolean[num+1];
		for (int i=0; i<=num; i++)
			prime[i]=true;
		for (int i=4; i<=num; i+=2)
			prime[i]=false;
		for (int i=3; i<=num; i+=2)
			for (int j=i*2; j<=num; j+=i)
				prime[j]=false;
		return prime;
	}

	public static boolean primarility(boolean[] prime, int num) // pre-requisite: run generatePrimes()
	{
		if (prime[num])
			return true;
		return false;
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

	static int gcd(int a, int b) //greatest common divisor, uses recursion
	{
		if (b==0)
			return a;
		else
			return gcd(b, a%b);
		//return 0;//gcd(b, a%b);
	}
}