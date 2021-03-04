public class pr012remainderpr23
{
	public static void main (String[]args)
	{
		java.util. Scanner s = new java.util. Scanner(System.in);

		System.out.println("Type the number");
		int you = s.nextInt();

		if (you%2==0)
			System.out.println("EVEN");

		else																		// else statement
			System.out.println("ODD");

	}
}
/*
Type the number
312
EVEN
*/