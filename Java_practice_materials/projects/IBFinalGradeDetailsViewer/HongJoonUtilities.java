import java.io.*;
class HongJoonUtilities 
{
	static String input(String prompt)
    {	String inputLine = "";
        System.out.print(prompt);
        try
        {inputLine = (new java.io.BufferedReader(
                    new java.io.InputStreamReader(System.in))).readLine();}
        catch (Exception e)
        { String err = e.toString();
            System.out.println(err);
            inputLine = "";
        }
        return inputLine;
    }

	static int inputInt(String prompt)
    {	int result=0;
        try{result=Integer.valueOf(
                input(prompt).trim()).intValue();}
        catch (Exception e){result = 0;}
        return result;
    }

	public static String barGraph(int count)
	{
		String bar = "";
		for (int i=0; i<count; i++)
			bar+="#";
		return bar;
	}

	public static String allignLeft(String n, int tab)						// writes string and leaves space
	{
		do 
			n+=" ";
		while (n.length()<tab);
		return n;
	}

	public static String allignMiddle(String a, int distance)	// " aaa " if distance = 5
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

	public static double round(double num, int digit)             //rounds number to given digit(floating point)
	{
		int power=1;
		for (int i=0; i<digit; i++)
			power*=10;
		return (double)((int)(num*power))/power;
	}

	public static int search(int[] s, int a)		//Search for (int a) in array (int[]s) and returns position of (int a) else, returns -1
	{
		for (int i=0;i<s.length ;i++ )
			if (a == s[i])
				return i;
		return -1;
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

	public static int[] smartSearch(String ss, Candidate[] staff, int roll)		// search for array of data that includes any part of the string
	{
		int[] list = new int[roll];
		for (int i=0;i<roll ;i++ )	 //initialize array
			list[i] = -1;
		int numberFound = 0;
		for (int i=0;i<roll ;i++ )
		{
			char[] disAssemble = staff[i].name.toCharArray();
			for (int k=0;k<staff[i].name.length() ;k++ )
			{
				String reAssembledString ="";
				for (int j=k;j<staff[i].name.length() ;j++ )
					reAssembledString+=disAssemble[j];
				//System.out.println(reAssembledString);	// enable it to test algorithm
				if ((reAssembledString.toLowerCase()).startsWith(ss.toLowerCase()))
				{
					//System.out.println("					Found!!");
					list[numberFound]=i;
					numberFound++;
					break;
				}
			}
		}
		int[] returnList = new int[numberFound];
		for (int i=0; i<numberFound; i++)
			returnList[i]=list[i];
		return returnList;
		//Method to print searched Strings : 
		/*
		for (int i=0;i<maxArraySize ;i++ )
			if (listFound[i]!=0)
				data[listFound[i]];
		*/
	}

	public static int[] smartSearch(String ss, String[] staff, int roll)		// search for array of data that includes any part of the string
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
				//System.out.println(reAssembledString);	// enable it to test algorithm
				if ((reAssembledString.toLowerCase()).startsWith(ss.toLowerCase()))
				{
					//System.out.println("					Found!!");
					list[numberFound]=i;
					numberFound++;
					break;
				}
			}
		}
		int[] returnList = new int[numberFound];
		for (int i=0; i<numberFound; i++)
			returnList[i]=list[i];
		return returnList;
		//Method to print searched Strings : 
		/*
		for (int i=0;i<maxArraySize ;i++ )
			if (listFound[i]!=-1)
				data[listFound[i]];
		*/
	}


	
}
