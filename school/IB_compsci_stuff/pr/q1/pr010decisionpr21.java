public class pr010decisionpr21
{	public static void main (String[]args)
	{
		java.util.Scanner s = new java.util.Scanner(System.in);
		System.out.println("input a number between 50 and 60 ");
		int a = s.nextInt();
		if (a > 60)																				//bigger than 60
		{
			System.out.println("the number is bigger than 60");
		}	
		else if (a<50)																		//smaller than 50
		{
			System.out.println("the number is smaller than 50");
		}
	}
}

/*
input a number between 50 and 60
43
the number is smaller than 50
*/