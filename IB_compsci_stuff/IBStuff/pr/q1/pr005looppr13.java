import java.util.*;
public class pr005looppr13	{
	public static void main (String [] args)	{


		/*
		squares and cubes
		*/
		int times = 10;
		int square = 0;
		int cube = 0;
		
		System.out.println("square and cube of the tirst 10 numbers.");
		int number = 1;
		
		for (int i = 0;i<times ;i++ )
		{ square=number*number;
			cube=number*number*number;
			System.out.println(square+"  "+cube);
			number++;
		}

	}
}

/*
square and cube of the tirst 10 numbers.
1  1
4  8
9  27
16  64
25  125
36  216
49  343
64  512
81  729
100  1000
*/