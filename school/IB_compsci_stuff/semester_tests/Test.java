public class Test
{	public static void main (String []args)
	{
		java.util.Scanner s = new java.util.Scanner(System.in);
		int num = 0;
		do
		{
			System.out.println("Enter a positive integer less than 16:");
			num = s.nextInt();
			
		if (num>=16)
		{
			System.out.println("Error - a number out of range.");
		}
		else if (num<1)
		{
			System.out.println("Error - a number out of range.");
		}
		}
		while (num>=16 || num<1);

			int result = 1;// q 3
		System.out.print(num+"! =");
		for (int i = num;i>0 ;i-- )
		{
			result = result*i;
			System.out.print(" "+i); 
			if (i>1)
			{
				System.out.print(" x");
			}
			
		}
		System.out.print("= "+result);
		/*
		int temp = result;
		temp = result/100000000;
		
		if (temp>0)
		{
			System.out.print(temp+",");		
		}
		temp = result/1000000000;
		if (temp>0)
		{
		
		temp=result%1000;
			System.out.print(temp+",");		
		}
		temp = result/1000000;
		if (temp>0)
		{
			temp = temp%1000;
			if (temp == 1)
			{
				System.out.print("00");
			}
			if (temp == 53)
			{
				System.out.print("0");
			}
			System.out.print(temp+",");		
		}
		temp = result;
		if (temp>0)
		{
			temp = temp%1000;
			if (temp ==40)
			{
				System.out.print("0");
			}
			if (temp == 16)
			{
				System.out.print("0");
			}
			System.out.print(temp+"");		
		}
		*/
		
		int ct = 0;
		while(result>=1)
		{
			result = result/10;
			ct++;
		}
		System.out.println("");
		System.out.println("Result has "+ct+" digits");
	}
}