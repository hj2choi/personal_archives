public class pr020digitsum33
{	public static void main (String[] args)
	{
		java.util.Scanner s = new java.util.Scanner(System.in);
		int sum = 0;
		System.out.println("enter a number");
		int n = s.nextInt();

		do
		{
			int digit = n%10;					// get right most digit
			int save = digit;					// save number for digit cubed
			for (int i = 1;i<3 ;i++ )
			{
				digit = digit*save;				// cube digit
			}
			sum = sum +digit;				// add to units digits
			n = n/10;								// make new number
		}
		while (n != 0 );

		System.out.println("the cube sum of the digits of the number is " + sum);
	}
}
/*
enter a number
20110913
the sum of the digits of the number is 767
*/