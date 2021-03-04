public class pr004looppr12
{	public static void main (String[]args) 
	{

		java.util.Scanner s = new java.util.Scanner(System.in);
		int start = 0;
		int increment = 0;

		System.out.println("type starting number");			// type variables
		start = s.nextInt();
		System.out.println("type increment");
		increment = s.nextInt();
		System.out.println("type no. of steps");
		int a = s.nextInt();
		
		for (int i = 0;i<a ;i++ )													// increment
		{
			System.out.print(start+"  ");
			start=start+increment;
		}
			System.out.println("  ");
	}
}
/*
type starting number
30
type increment
12
type no. of steps
10
30  42  54  66  78  90  102  114  126  138
*/