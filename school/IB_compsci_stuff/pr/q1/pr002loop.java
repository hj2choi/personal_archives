public class pr002loop	{
	public static void main (String [] args)	{


		/*
		googoodan
		*/
		int hello = 0;
		int times = 1;
		for (int number = 2;number<10 ;number=number+1 )
		{
			System.out.println("Multiple of "+number);
			for (int loop = 0;loop<9 ;loop++ )
			{
				System.out.println (+number+" times "+times+" is "+(number*times));
				times++;
			}								
			//number++;
			times=1;
			System.out.println ("");
		}
	}
}
/*
Multiple of 2
2 times 1 is 2
2 times 2 is 4
2 times 3 is 6
2 times 4 is 8
2 times 5 is 10
2 times 6 is 124
2 times 7 is 14
2 times 8 is 16
2 times 9 is 18

Multiple of 3
3 times 1 is 3
3 times 2 is 6
3 times 3 is 9
3 times 4 is 12
3 times 5 is 15
3 times 6 is 18
3 times 7 is 21
3 times 8 is 24
3 times 9 is 27

Multiple of 4
4 times 1 is 4
4 times 2 is 8
4 times 3 is 12
4 times 4 is 16
4 times 5 is 20
4 times 6 is 24
4 times 7 is 28
4 times 8 is 32
4 times 9 is 36

Multiple of 5
5 times 1 is 5
5 times 2 is 10
5 times 3 is 15
5 times 4 is 20
5 times 5 is 25
5 times 6 is 30
5 times 7 is 35
5 times 8 is 40
5 times 9 is 45

Multiple of 6
6 times 1 is 6
6 times 2 is 12
6 times 3 is 18
6 times 4 is 24
6 times 5 is 30
6 times 6 is 36
6 times 7 is 42
6 times 8 is 48
6 times 9 is 54

Multiple of 7
7 times 1 is 7
7 times 2 is 14
7 times 3 is 21
7 times 4 is 28
7 times 5 is 35
7 times 6 is 42
7 times 7 is 49
7 times 8 is 56
7 times 9 is 63

Multiple of 8
8 times 1 is 8
8 times 2 is 16
8 times 3 is 24
8 times 4 is 32
8 times 5 is 40
8 times 6 is 48
8 times 7 is 56
8 times 8 is 64
8 times 9 is 72

Multiple of 9
9 times 1 is 9
9 times 2 is 18
9 times 3 is 27
9 times 4 is 36
9 times 5 is 45
9 times 6 is 54
9 times 7 is 63
9 times 8 is 72
9 times 9 is 81

*/