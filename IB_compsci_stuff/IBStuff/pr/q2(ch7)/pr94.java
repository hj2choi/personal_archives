/*
Write a program that will generate 100 pairs of random numbers from 1 to 6. It will save the
sum of these numbers in an array. So at this stage every element of the array will have a
number from 2 to 12. Then to print out a bar graph showing how many 2¡¯s, 3¡¯s there are.
Use the method above to print the numbers from 2 to 12 in the margin lined up. This is like
throwing two dice and adding the numbers together.
*/
public class pr94 extends IBIO
{
	static int random(int max)
	{
			return (int)(Math.random()*max+1);			// random
	}
	public static void main (String [] args)
	{
		int[] num = new int[100];
		
		for (int i = 0; i<100; i++)
			num[i] = random(6)+random(6);		// random with max
			
		for (int j = 2; j<=12; j++)
			{
				output("");
				out(j+"	");
				for (int k = 0; k<100; k++)
					if (j==num[k])					// output	
						out("X");
				
			}
	}
}
/*
2       XXXXX
3       XXXXX
4       XXXXXXXXXXX
5       XXXXXXX
6       XXXXXXXXXXX
7       XXXXXXXXXXXXXXXXXXX
8       XXXXXXXXXXX
9       XXXXXXXXXXXXXXX
10      XXXXXX
11      XXXXXXX
12      XXX
*/

