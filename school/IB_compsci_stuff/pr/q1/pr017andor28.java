public class pr017andor28
{	public static void main (String[]args)
	{
		long count =0;
		for (long i = 1;i<=1000000 ;i++ )
		{
			if (i%2!=0 && i%3!=0 &&  i%5!=0 &&  i%7!=0)					// count if ca't divide by 2,3,5,7
			{
				count++;
			}
		}
		System.out.println(count);							// print
	}
}
/*
228571
*/