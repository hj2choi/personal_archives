public class pr018dowhile31
{	public static void main (String[] args)
	{
		java.util.Scanner s = new java.util.Scanner(System.in);
		int Num;
		do
		{
			System.out.println("enter a number less than 100 and more than 0");				// enter a number
			Num = s.nextInt();
		}
		while (Num%2 != 0 || 0<Num || 100<Num);										// accept 0~100
		System.out.println("Thank You");
	}
}
/*
enter a number less than 100 and more than 0
112
enter a number less than 100 and more than 0
512
enter a number less than 100 and more than 0
45
Thank You
*/