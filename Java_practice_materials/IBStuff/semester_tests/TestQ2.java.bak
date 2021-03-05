/* ////////// /* /////////// /* //////////// /* ////////////// /*
		Programming Test Quarter 2
		2011.15.Dec
		gl hf
*/ ////////// */ //////////// */ //////////// */ //////////// */
public class TestQ2 extends IBIO
{	
	final static int r = 100;
	public static void main (String []args)
	{
		String anykey = "";
		/************************************************************ no 1.0 ***********************************************************/
		System.out.println("Fibonacci Sequence Frequency");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		long first = 0;
		long second = 1;
		long temp = 0;														// //3 inputs
		long [] sequenceTerm = new long[1000];
		long  Fibonacci = 0;
		System.out.println(" 1:    "+first);

		/************************************************************ no 2.0~5.0 ***********************************************************/

		sequenceTerm[0] = 150;
		sequenceTerm[1] = second;
		System.out.println(" 2:    "+second);
		for (int a = 3;a<=99 ;a++ )							// Fibbonaci sequence
		{
			Fibonacci = first+second;
			if (Fibonacci>100)
				Fibonacci = Fibonacci%100;
			if (a<=20)
			{
				if (a<10)
					System.out.print(" ");
				System.out.print(a+":    ");
				if (Fibonacci>=10)
					System.out.print("\b");
				System.out.println(Fibonacci);
			}
			sequenceTerm[a] = Fibonacci;
			temp = first;
			first = second;
			second = temp+second;
		}

		/************************************************************ no 5.5 ***********************************************************/

		System.out.println("90th term: "+sequenceTerm[90]);
		/************************************************************ no 6.0 ***********************************************************/

		long count=0;
		long arraySearch = inputInt("Enter the number to search (0-99) : ");
		for (int k = 1;k<100 ;k++ )
		{
			if (arraySearch==sequenceTerm[k])
			{
				count++;
			}
		}
		if (count==0)
			System.out.println(arraySearch+" not found");
		else
			System.out.println("Frequency of occurences of "+arraySearch+" : "+count);

		anykey = input("Enter any key to continue... : ");
		/************************************************************ no 7.0 ***********************************************************/
		System.out.println("Unique terms : ");
		for (long a=0;a<100 ;a++ )
		{
			count=0;
			for (int l = 1;l<100 ;l++ )
			{
				if (a==sequenceTerm[l])
				{
					count++;
				}
			}
			if (count==1)
			{
				if (a<10)
					System.out.print(" ");
				System.out.println(a);
			}
			count=0;
		}
		anykey = input("Enter any key to continue... : ");
		/************************************************************ no 7.5 ***********************************************************/
		count=0;
		for (long b=0;b<100 ;b++ )
		{
			for (int i=0;i<100 ;i++ )
			{
				if (b==sequenceTerm[i])
				{
					count++;
					if (count<10)
						System.out.print(" ");
					System.out.print(count+": ");
					if (b<10)
						System.out.print(" ");
					System.out.println(b);
				}
			}
		}
	}
}