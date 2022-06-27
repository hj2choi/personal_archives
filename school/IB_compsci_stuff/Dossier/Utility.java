class Utility 
{
	static java.util.Scanner s = new java.util.Scanner(System.in);
	
	public static void clearScreen()
	{
		for (int i=0; i<0; i++)
			System.out.println();
	}

	public static void spaceLines(int lines)
	{
		for (int i=0; i<lines; i++)
			System.out.println();
	}

	public static int inputInt()			//Simple integer input method. Safe from datatype exception
	{	
		String input = s.next();
		try{return Integer.parseInt(input);}
		catch (Exception e){return 0;}
	}

	public static String pad(String n, int space)						// writes string and leaves space
	{
		do 
			n+=" ";
		while (n.length()<space);
		return n;
	}

	public static char firstCharacterOf(String ss)
	{
		return ss.charAt(0);
	}

	public static String allignMiddle(String a, int space)	// " aaa ccc" if space = 5
	{
		String ss = "";
		int blank=space-a.length();
		if (blank%2!=0)
		    ss+=" ";
		for (int i=0;i<blank/2 ;i++ )
		    ss+=" ";
		ss+=a;
		for (int i=0;i<blank/2 ;i++ )
		    ss+=" ";
		return ss;
	}
}