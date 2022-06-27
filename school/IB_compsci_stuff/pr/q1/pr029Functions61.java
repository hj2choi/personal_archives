/*
Pr 6.1 Change the program so that it prints the square 10 spaces out. Do this by changing only the
function and not changing the main routine.
*/
public class pr029Functions61
{
	static String stars(int n)
	{ 
		String xx = "          ";
		for (int i = 0; i < n; i++)
			xx = xx + "*";
		return xx;
	}

	public static void main (String []args)
	{ 
		java.util.Scanner s = new java.util.Scanner(System.in);
			{
				System.out.println("enter number of lines");
				int num = s.nextInt();
				String aa = stars(num);
				for (int i = 0; i < num; i++)
				System.out.println(aa);
			}
	}
}
/*
enter number of lines
10
          *****
          *****
          *****
          *****
          *****
*/				
