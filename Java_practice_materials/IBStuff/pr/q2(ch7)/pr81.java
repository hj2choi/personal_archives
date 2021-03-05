/*
Change the program so that no duplicates will be printed out.
*/
public class pr81 extends IBIO
{
	static boolean good(int a, int b)
	{
		int x = a*b+1;
		int y = (int)(Math.sqrt(x)+.5);
		return (y*y == x);
	}
    public static void main (String args [] )
    {
		for (int i=1;i<100;i++)
			for (int j = 1;j<100;j++)
				for (int k = 1;k<100;k++)
					if (good(i,j) && good(j,k) && good(i,k))
						if (i<j && j<k)				//rejecting duplicates
							output(i+"    "+j+"    "+k);
    }
}
/*
1    3    8
1    8    15
1    15    24
1    24    35
1    35    48
1    48    63
1    63    80
1    80    99
2    4    12
2    12    24
2    24    40
2    40    60
2    60    84
3    5    16
3    8    21
3    16    33
3    21    40
3    33    56
3    40    65
3    56    85
3    65    96
4    6    20
4    12    30
4    20    42
4    30    56
4    42    72
4    56    90
5    7    24
5    16    39
5    24    51
5    39    72
5    51    88
6    8    28
6    20    48
6    28    60
6    48    88
7    9    32
7    24    57
7    32    69
8    10    36
8    15    45
8    21    55
8    28    66
8    36    78
8    45    91
9    11    40
9    32    75
9    40    87
10    12    44
10    36    84
10    44    96
11    13    48
11    40    93
12    14    52
12    24    70
12    30    80
13    15    56
14    16    60
15    17    64
15    24    77
16    18    68
16    33    95
17    19    72
18    20    76
19    21    80
20    22    84
21    23    88
22    24    92
23    25    96
*/