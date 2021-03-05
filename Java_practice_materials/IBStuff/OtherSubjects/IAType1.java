/***===============*****__________________________
HongJoon Choi
Grade 11 Semester2 Internal Assessment for Mathematics HL
22.Jan.2012
__________________________*******************===============***/
class IAType1
{
	public static void main(String[] args) 
	{
		long Equal, Exceed, bin =2;
		byte binStatus, t = 0;		// status of binary, term
		int[] y = new int[100];		// y(x) (in array)
		for (byte k=1; k<=10;k++ )
		{
			Equal=0; Exceed=0; binStatus=0;
			for (long i=0;i<bin ;i+=2 )	//omits odd numbers
			{
				binStatus = MountainCondition(Long.toBinaryString(bin+i));
				if (binStatus==0)
					y[k]++;		//y
				if (binStatus==1)
					Exceed++;	//MountainCondition2
				if (binStatus==2)
					Equal++;		//MountainCondition1
			}
			System.out.println("y("+(++t)+")= "+y[k]);
		    out("a("+t+")="+(bin/2)+"  e("+t+")="+((bin/2)-Equal)+"  g("+t);
			output(")="+Exceed+"\t\te("+t+")-g("+t+")="+((bin/2)-Equal-Exceed)+"\n");
			bin*=4;		// add two digits to binary
		} 
	}
	static byte MountainCondition (String m)//Binary Analyzing rutine
	{
		byte a = 0, binStatus = 0;
		for (byte k=0;k<m.length() ;k++ ) // search by character to character
		{
			if (m.charAt(k)=='1')
				a++;
			else
				a--;
			if (a<0)
				binStatus=1;	// when it fail to meet Mountaincondition2
		}
		if (a!=0)
			binStatus=2;	// when it fail to meet Mountaincondition1
		return binStatus;	// when it satisfy both condition
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