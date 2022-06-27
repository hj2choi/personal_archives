public class pr019prime32
{	public static void main (String[] args)
	{
		java.util.Scanner s = new java.util.Scanner(System.in);
		int i = 1;
		int x=0;
		while (x<1)
		{
			System.out.println("Enter a number");
			x = s.nextInt();
			if (x==1)
			{
				x=0;
			}
		}
		do
		{ i++;
		} while (x % i != 0);
		System.out.println(x + " is divisible by " + i);
		if (x==i)
		{
			System.out.println(x + " is a prime number");
		}
	}
}
/*
Enter a number
2597
2597 is divisible by 7
*/