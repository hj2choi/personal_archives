import java.math.BigInteger;
import java.io.*;
class HongJoonLibrary			//	 Just tried to test
{
/*====================================
Class for methods
This class contains some useful methods that aims to be helpful in most programming, with minimum argument required, and to have minimum time required to process as possible.
This library is mainly in compliance with (int) data type. Thus, changing some data type is needed sometimes.
***This class also contains some final variables.***
Version 11.03.2012
Created by HongJoon Choi, with some references to class IBIO.
*///====================================
/*====================================
--Method Summary-- 27 total
return type		method(input data type) : description

	[[Print, input methods]]
void			pp(info)																: System.out.println(info). Made especially for lazy people who aren't bothered to type System.out.println.
void			p(info)																: System.out.print(info). Made for same reason as above.
int			safeInput()															: it acts exacty same as nextInt(); but it catches exception. for ex, if you enter abc, it returns 0 instead of terminating program.
void			printLine(int n)													: It prints ("\n") n times.
void			clearscreen()														: It clears screen. With specified degree in int screenSize below.
void			textAnimation(String info)								: It is advanced version of System.out.println(String info). It presents textAnimation.
String		AllignedString(String a, String b, int index)	: Allign b on (index)th index no matter the length or content of a, which comes before b.

	[[Calculation methods]]
double	random(double min, double max)					: returns random value that ranges from min to max.
int			searchFor(int x, int[] data)								: Search for x through array 'int[] data' and returns position of it. If not found, returns -1.
int			minOf(int[] data) & maxOf(int[] data)			: returns position of array in which smallest or highest number in (int[] data) occurs.
int[]			sort(int[] data, int a)											: sorts out int[] data and returns sorted array. If a=0, sorts in ascending order. Else if a=1, sorts in descending order.
int			arraySize(int[] data)											: returns size of the array.

	[[Thread, IO methods]]
void			delay(long milliSecond)									: Pause thread for given milliseconds.
void			fileWriter(String filename)								: Simply declares fileWriter.
String[]	readFile(String filename)									: Reads the whole file and returns them into array.
void			save(String filename, String[] data)				: Saves data into a file.

	[[program testing methods]]
void			test()																	: System.out.println("read"); for easier examining program.
long		time()																	: Retuns current time in milliseconds.

	[[Math methods]]						(Usually from algorithms used for Project Euler or math IA)
boolean	primeCheck(int num)										: Returns true if argument is prime number. Else, return false.
String		inverseString(String ss)									: Retruns inversed string of ss.
int			binaryToDecimal(String bin)							: Converts String bin into binary. If binary is not entered in, 0 is returned.
int			numArrangement(int num)							: Return value as following example : if argument is '13579', '1010101010' is returned. If argument is '1123390', '1000002121' is returned.
int			gcd(int n1, int n2)												: Returns Greatest common divisor of two arguments.
long[]		primeNumbersUntil(long num)						: Returns all prime numbers until num in array.
int			XOR(int x, int y)													: Returns x XOR y.
String		encryptOrDecrypt(String data, int key)			: Encrypts String argument with master key argued(Using XOR method). If it's already encrypted, it encrypts it.
String		integerToWord(long num)								: Returns String that writes num in words.(compiles with British language )

*///====================================
//Program Code
//===================================Variables====================================
	final static int maxArraySize =30000000;
	final static int screenSize = 30;
	final static int delayMilliSeconds = 15;
	static java.util.Scanner s = new java.util.Scanner(System.in);
//===================================Print/InputMethods====================================
	static void p(String info)
	 {	System.out.print(info);}
	static void p(char info)
	 {	System.out.print(info);}
	static void p(byte info)
	 {	System.out.print(info);}
	static void p(boolean info)
	 {	System.out.print(info);}
	static void p(int info)
	 {	System.out.print(info);}
	static void p(long info)
	 {	System.out.print(info);}
    static void p(double info)
    {	System.out.print(info);}

	static void pp(String info)
	 {	System.out.println(info);}
	static void pp(char info)
	 {	System.out.println(info);}
	static void pp(byte info)
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

//===================================CalculationMethods====================================
	public static double random(double a, double b)		//returns random number rnging from (double a) to (double b)
	{
		return a+Math.random()*(b-a);
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

	public static int minOf(double[] s)			//returns position of array in which maximum number in (int[]) occurs
	{
		for (int k=0;k<=maxArraySize ;k++)
		{
			boolean maxchk = true;
			for (int l=0;l<=maxArraySize ;l++ )
			{
				try
				{
					if (s[l]==0)
						continue;
				}
				catch (Exception e)
				{
					break;
				}
				if ((s[k]>s[l])||s[k]==0)
					maxchk=false;
			}
			if(maxchk==true)
				return k;
		}
		return -1;
	}

	public static int maxOf(int[] s)			//returns position of array in which maximum number in (int[]) occurs
	{
		for (int k=0;k<=maxArraySize ;k++)
		{
			boolean maxchk = true;
			for (int l=0;l<=maxArraySize ;l++ )
			{
				try
				{
					if ((s[k]<s[l])||s[k]==0)
						maxchk=false;
				}
				catch (Exception e)
				{
					break;
				}
			}
			if(maxchk==true)
				return k;
		}
		return -1;
	}

	public static int[] sort(int[] data, int a)		//returns array with sorted integers. From lower value to higher value if a=0, from higher to lower if a=1
	{
		int roll=arraySize(data);
		int swap=0;
		for (int k=0;k<roll ;k++ )
			for (int i=0;i<roll ;i++ )
				if ((data[k]<data[i] && a==0) || (data[k]>data[i] && a==1))
				{
					swap = data[k];
					data[k] = data[i];
					data[i] = swap;
				}
		return data;
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

//===================================Math Methods====================================

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
		int[] encryption = new int[10];
		String encryptedArrangement = "";
		while (num>0)
		{
			encryption[num%10]++;
			num/=10;
		}
		for (int i=9;i>=0 ;i-- )
			encryptedArrangement+=encryption[i];
		return encryptedArrangement;
	}

	static int gcd(int f, int g)
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
		pp(xBin);
		pp(yBin);
		for (int i=0;i<maxBinSize ;i++ )
		{
			if (xBin.charAt(i)!=yBin.charAt(i))
				XOR+="1";
			else
				XOR+="0";
		}
		pp(XOR);
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
			return "Zero ";
		String word="";
		if (num>=(1000000000*100))		//Billions
		{
			word+=digitsBelowHundreds(word, num/(1000000000*100));
			word+=CapitalOnFirstLetter(word, 'T')+"rillion ";
			if (num%(1000000000*100)!=0)
				word+="and ";
		}
		num=num%(1000000000*100);
		if (num>=1000000000)		//Billions
		{
			word+=digitsBelowHundreds(word, num/1000000000);
			word+=CapitalOnFirstLetter(word, 'B')+"illion ";
			if (num%1000000000!=0)
				word+="and ";
		}
		num=num%1000000000;
		if (num>=1000000)		//Millions
		{
			word+=digitsBelowHundreds(word, num/1000000);
			word+=CapitalOnFirstLetter(word, 'M')+"illion ";
			if (num%1000000!=0)
				word+="and ";
		}
		num=num%1000000;
		if (num>=1000)			//thousands
		{
			word+=digitsBelowHundreds(word, num/1000);
			word+=CapitalOnFirstLetter(word, 'T')+"housand ";
			if (num%1000!=0)
				word+="and ";
		}
		num=num%1000;
		word=word+digitsBelowHundreds(word, num);
		return word;
	}
	public static String digitsBelowHundreds(String sentence, long num)
	{
		String word ="";
		if (num>=100)		//hundreds
		{
			word+=numToString(sentence,num/100);
			word+="hundred ";
			if (num%100!=0)
				word+="and ";
		}
		num=num%100;//tens
		if (num>=10 && num<20)
				return word+tensToStringException(sentence, num%100);
		else if (num>=20)
			word+=tensToString(sentence, num/10, num%10==0);
		num=num%10;//ones
		word+=numToString(sentence, num);
		return word;
	}
	public static String numToString(String sentence, long num)
	{
		String word="";
		if (num==1)
		word+=CapitalOnFirstLetter(sentence, 'O')+"ne ";
		if (num==2)
			word+=CapitalOnFirstLetter(sentence, 'T')+"wo ";
		if (num==3)
			word+=CapitalOnFirstLetter(sentence, 'T')+"hree ";
		if (num==4)
			word+=CapitalOnFirstLetter(sentence, 'F')+"our ";
		if (num==5)
			word+=CapitalOnFirstLetter(sentence, 'F')+"ive ";
		if (num==6)
			word+=CapitalOnFirstLetter(sentence, 'S')+"ix ";
		if (num==7)
			word+=CapitalOnFirstLetter(sentence, 'S')+"even ";
		if (num==8)
			word+=CapitalOnFirstLetter(sentence, 'E')+"ight ";
		if (num==9)
			word+=CapitalOnFirstLetter(sentence, 'N')+"ine ";
		return word;
	}
	public static String tensToString(String sentence, long num, boolean hyphen)
	{
		String word="";
		String a="-";
		if (hyphen)
			a=" ";
		if (num==2)
			word+=CapitalOnFirstLetter(sentence, 'T')+"wenty"+a;
		if (num==3)
			word+=CapitalOnFirstLetter(sentence, 'T')+"hirty"+a;
		if (num==4)
			word+=CapitalOnFirstLetter(sentence, 'F')+"orty"+a;
		if (num==5)
			word+=CapitalOnFirstLetter(sentence, 'F')+"ifty"+a;
		if (num==6)
			word+=CapitalOnFirstLetter(sentence, 'S')+"ixty"+a;
		if (num==7)
			word+=CapitalOnFirstLetter(sentence, 'S')+"eventy"+a;
		if (num==8)
			word+=CapitalOnFirstLetter(sentence, 'E')+"ighty"+a;
		if (num==9)
			word+=CapitalOnFirstLetter(sentence, 'N')+"inety"+a;
		return word;
	}
	public static String tensToStringException(String sentence, long num)
	{
		String word="";
		if (num==10)
			word+=CapitalOnFirstLetter(sentence, 'T')+"en ";
		if (num==11)
			word+=CapitalOnFirstLetter(sentence, 'E')+"leven ";
		if (num==12)
			word+=CapitalOnFirstLetter(sentence, 'T')+"welve ";
		if (num==13)
			word+=CapitalOnFirstLetter(sentence, 'T')+"hirteen ";
		if (num==14)
			word+=CapitalOnFirstLetter(sentence, 'F')+"ourteen ";
		if (num==15)
			word+=CapitalOnFirstLetter(sentence, 'F')+"ifteen ";
		if (num==16)
			word+=CapitalOnFirstLetter(sentence, 'S')+"ixteen ";
		if (num==17)
			word+=CapitalOnFirstLetter(sentence, 'S')+"eventeen ";
		if (num==18)
			word+=CapitalOnFirstLetter(sentence, 'S')+"ighteen ";
		if (num==19)
			word+=CapitalOnFirstLetter(sentence, 'N')+"ineteen ";
		return word;
	}
	public static char CapitalOnFirstLetter(String sentence, char letter)
	{
		if (!sentence.equals("") || sentence.length()>0)
			letter=(char)((int)letter+32);
		return letter;
	}
}
