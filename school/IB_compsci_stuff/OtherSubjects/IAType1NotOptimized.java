/***===============***
Hong Joon Choi
Grade 11 Semester2 Internal Assessment (Summited to IBIO) for Mathematics HL

Mountains (Up stroke is represented by 1, Down stroke is represented by 0)

22.Jan.2012
All Rights Reserved. I AM NOT KIDDING!!
Copying any of contents below is STRICTLY PROHIBITED BY IBIO AND HongJoon
***===============***/
class IAType1NotOptimized
{
	final static int strokeFind = 6;
	public static void main(String[] args) 
	{
		long stroke = 2;

		long temp = 2;
		long possibleMountain =0;
		System.out.println("Grade 11 Semester2 Internal Assessment (Summited to IBIO) for Mathematics HL");
		System.out.println("---------------- MOUNTAINS -------------------\n");
		long tm = System.currentTimeMillis();
		for (int k=1;k<=strokeFind ;k++ )		//number of strokes
		{
			String mountain = "";
			temp=stroke;
			possibleMountain=0;
			for (int i=0;i<stroke ;i++ )	//Draw All Possible Mountains(Represented in binary)
			{
				mountain = toBinary(temp);
				if (UpDownStrokesEqual(mountain) && DoNotGoBelowGround(mountain))	// If mountain satisfy condition, add 1 to number of possible mountains.
				{
					System.out.println(stringInverse(mountain));
					possibleMountain++;
				}
				temp++;
			}
			System.out.println(k+" Stroke : "+possibleMountain+"			=>"+(System.currentTimeMillis()-tm)/1000.0+"s elapsed");
			stroke=stroke*4;			//2digitBinary=1stroke; 4digit=2stroke; 6digit=3stroke
		}
	}


////////////***********Decimal to Binary Method**********////////////
	public static String toBinary(long a)
	{
		String bin = "";
		while (a != 0)
		{
			if (a%2==1)
				bin = bin+"1";
			else
				bin=bin+"0";
			a = a/2;
		}
		//bin=stringInverse(bin);
		return bin;
	}
////////////***********Turning String upside down**********////////////
	public static String stringInverse(String ss)
	{
		String result="";
		char[] ssC = ss.toCharArray();
		for (int i=ss.length()-1;i>=0 ;i-- )
			result = result+ssC[i];
		return result;
	}
	////////////***********Check if there are equal number of 1 and 0**********////////////
	public static boolean UpDownStrokesEqual (String mountain)
	{
		boolean chk=false;
			int one=0;
			char[] chkEq = mountain.toCharArray();
			for (int k=0;k<mountain.length() ;k++ )
				if (chkEq[k]=='1')
					one++;
			if (one*2==mountain.length())
				chk=true;
		return chk;
	}
	////////////***********Check if Mountain go below the ground**********////////////
	public static boolean DoNotGoBelowGround (String mountain)
	{
		boolean chk=true;
		char[] chkEq = mountain.toCharArray();
		long ans = 0;
		for (int k=0;k<mountain.length() ;k++ )
		{
			if (chkEq[k]=='1')
				ans--;
			else
				ans++;
			if (ans<0)
				chk=false;
		}
		return chk;
	}
}
/*
1 Stroke : 1
2 Stroke : 2
3 Stroke : 5
4 Stroke : 14
5 Stroke : 42
6 Stroke : 132
7 Stroke : 429
8 Stroke : 1430
9 Stroke : 4862
10 Stroke : 16796
11 Stroke : 58786
12 Stroke : 208012
*/