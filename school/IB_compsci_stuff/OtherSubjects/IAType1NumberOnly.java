/***===============*****__________________________
HongJoon Choi
Grade 11 Semester2 Internal Assessment for Mathematics HL
22.Jan.2012
__________________________*******************===============***/
class IAType1NumberOnly
{
	public static void main(String[] args) 
	{
		long bin = 2;		// integer that will be converted to binary
		long y, term = 0;		// y(n)
		for (int k=0; k<60; k++)
		{
			y=0;
			for (long i=0;i<bin ;i++ )	//generate n digit binaries
				if (MountainCondition(Long.toBinaryString(bin+i)))
					y++;
			System.out.println("y("+(++term)+") : "+(y));
			bin*=4;				//add two digits to binary
		}
	}
	static boolean MountainCondition (String m)//Method for checking MountainCondition
	{
		byte a = 0;
		for (byte k=0;k<m.length() ;k++ )
		{
			if (m.charAt(k)=='1')
				a++;
			else
				a--;
			if (a<0)
				return false;	// number of 0s exceed number of 1s
		}
		if (a!=0)
			return false;	// Not equal numbers of 1s and 0s 
		return true;
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
13 Stroke : 742900
14 Stroke : 2674440
15 Stroke : 9694845
16 Stroke : 35357670
17 Stroke : 129644790
18 Stroke : 477638700
*/