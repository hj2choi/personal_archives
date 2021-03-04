class HongJoonLibraryExtended extends HongJoonLibrary
{
	
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
				System.out.println(reAssembledString);	// enable it to test algorithm
				if ((reAssembledString.toLowerCase()).startsWith(ss.toLowerCase()))
				{
					System.out.println("					Found!!");
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
		for (int i=0;i<listFound.length ;i++ )
				data[listFound[i]];
		*/
	}
	

	public static boolean primalty(boolean[] prime, int num) // pre-requisite: run generatePrimes()
	{
		if (prime[num])
			return true;
		return false;
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


	public static String numberPermutation(int num)
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

	static int gcd(int a, int b) //greatest common divisor, uses recursion
	{
		if (b==0)
			return a;
		else
			gcd(b, a%b);
		return 0;//gcd(b, a%b);
	}
	
	public static String encryptOrDecrypt(String data, int masterKey)//pre-requisite: XOR, binaryToDecimal
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
//===================================Djikstra pathfinding algorithm===========================
/**
returns lists of shortest path taken by djikstra pathfinding algorithm by boolean[][] dataType
function Pre-requisites: generatePathList(), canOpenListAt(), djikstraMin(), min()

adapted for 2D vector space with 4 links for each edges
*/
	public static boolean[][] dijkstraPathFinding(int[][] walkCost, int startX, int startY, int destinationX, int destinationY, int worldWidth, int worldHeight)	//djikstra's pathfinding algorithm
	{
		boolean[][] openList = new boolean[worldWidth][worldHeight];			//stores wheter given coordinate is open
		boolean[][] closedList = new boolean[worldWidth][worldHeight];			//stores wheter given coordinate is closed
		int[][] djikstraCost = new int[worldWidth][worldHeight];		//stored djikstra cost from starting point
		for (int x=0; x<worldWidth; x++)	// initial value for djikstraCost = infinity(99999);
			for (int y=0; y<worldHeight; y++)
				djikstraCost[x][y]=9999999;
		int currentX=startX;
		int currentY=startY;
		djikstraCost[currentX][currentY]=walkCost[currentX][currentY];
		
		while (!(currentX==destinationX && currentY==destinationY))
		{
			openList[currentX][currentY]=false; // closes list at current location
			closedList[currentX][currentY]=true;	// closes list at current location

			// opens adjacent coordinates(two vertices are linked by an edge) to current coordinate
			if (canOpenListAt(closedList, currentX+1, currentY, worldWidth, worldHeight))
				openList[currentX+1][currentY]=true;
			if (canOpenListAt(closedList, currentX-1, currentY, worldWidth, worldHeight))
				openList[currentX-1][currentY]=true;
			if (canOpenListAt(closedList, currentX, currentY+1, worldWidth, worldHeight))
				openList[currentX][currentY+1]=true;
			if (canOpenListAt(closedList, currentX, currentY-1, worldWidth, worldHeight))
				openList[currentX][currentY-1]=true;
			//end
			
			//traverse through all openedLists and update djikstraCost
			for (int x=0; x<worldWidth; x++)
				for (int y=0; y<worldHeight; y++)
					if (openList[x][y] && (djikstraCost[currentX][currentY]+walkCost[x][y])<djikstraCost[x][y])//if current cost>updatedCost
						djikstraCost[x][y]=djikstraCost[currentX][currentY]+walkCost[x][y];
			//search through all openedLists and find coordinate with min djikstraCost
			int[] minCostIndex = djikstraMin(openList, djikstraCost, worldWidth, worldHeight);
			currentX=minCostIndex[0];
			currentY=minCostIndex[1];
			//update currentX and currentY. Then, repeat all steps
		}
		// create pathList according to generated cost matrix and terminate procedure.
		return generatePathList(djikstraCost, startX, startY, destinationX, destinationY, worldWidth, worldHeight);
	}

	private static boolean[][] generatePathList(int[][] djikstraCost, int startX, int startY, int destinationX, int destinationY, int worldWidth, int worldHeight)
	{
		djikstraCost[startX][startY]=1;
		boolean[][] pathList = new boolean[worldWidth][worldHeight];
		boolean[][] allFalse = new boolean[worldWidth][worldHeight];
		pathList[destinationX][destinationY]=true;
		while (!(startX==destinationX && startY==destinationY))
		{
			int[] adjacentScores = new int[4];
			if (canOpenListAt(allFalse, destinationX+1, destinationY, worldWidth, worldHeight))
				adjacentScores[0]=djikstraCost[destinationX+1][destinationY];
			if (canOpenListAt(allFalse, destinationX-1, destinationY, worldWidth, worldHeight))
				adjacentScores[1]=djikstraCost[destinationX-1][destinationY];
			if (canOpenListAt(allFalse, destinationX, destinationY+1, worldWidth, worldHeight))
				adjacentScores[2]=djikstraCost[destinationX][destinationY+1];
			if (canOpenListAt(allFalse, destinationX, destinationY-1, worldWidth, worldHeight))
				adjacentScores[3]=djikstraCost[destinationX][destinationY-1];
			int index = min(adjacentScores, 4);
			switch (index)
			{
			case 0:
				pathList[destinationX+1][destinationY]=true;
				destinationX=destinationX+1;
				destinationY=destinationY;
				break;
			case 1:
				pathList[destinationX-1][destinationY]=true;
				destinationX=destinationX-1;
				destinationY=destinationY;
				break;
			case 2:
				pathList[destinationX][destinationY+1]=true;
				destinationX=destinationX;
				destinationY=destinationY+1;
				break;
			case 3:
				pathList[destinationX][destinationY-1]=true;
				destinationX=destinationX;
				destinationY=destinationY-1;
				break;
			}
		}
		pathList[startX][startY]=false;
		return pathList;
	}
	private static boolean canOpenListAt(boolean[][] closedList, int x, int y, int worldWidth, int worldHeight)	//exception handling
	{
		if (x<0 || x>=worldWidth)
			return false;
		if (y<0 || y>=worldHeight)
			return false;
		if (!closedList[x][y])
			return true;
		return false;

	}
	private static int[] djikstraMin(boolean[][] searchList, int[][] values, int lengthX, int lengthY)
	{
		int currentMin=99999999;
		int[] index=new int[2];
		for (int x=0; x<lengthX; x++)
			for (int y=0; y<lengthY; y++)
				if (searchList[x][y] && values[x][y]<currentMin)
				{
					index[0]=x;
					index[1]=y;
					currentMin=values[x][y];
				}
		return index;
	}
	private static int min(int[] list, int length)
	{
		double temp=99999999;
		int position=0;
		for (int i=0;i<length;i++)
			if (list[i]<temp && list[i]>0)
			{
				temp=list[i];
				position=i;
			}
		return position;
	}


	
//===================================IntegerToWord(String)====================================
	public static String convertNumberToWords(long num) //pre-requisite: digitsBelowHundreds, numToString, tensToString, tensToStringException
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
