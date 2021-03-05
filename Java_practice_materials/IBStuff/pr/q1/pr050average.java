public class pr050average
{	public static void main (String []args)
	{
		java.util.Scanner s = new java.util.Scanner(System.in);
		int n = 0;
		float sum = 0;
		int x;

		do
		{
			System.out.println("Enter a number");
			x = s.nextInt();
		
			if (x != 0)
			{
				sum = sum+x;
				n++;
			}
		}
		while (x != 0);
		sum = (int)(((float)sum/(float)n)*100);
		sum = sum/100;

		System.out.println(sum);
	} 
}