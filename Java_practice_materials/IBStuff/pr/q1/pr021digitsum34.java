public class pr021digitsum34
{	public static void main (String[] args)
	{
		java.util.Scanner s = new java.util.Scanner(System.in);
		System.out.println("Enter a number");
		int num = s.nextInt();
		int count = 0;
		while (num !=1)								// loop
		{
			if (num==0)
				break;
			if (num%2==1)							// if num is odd
			{
				num = (num*3)+1;
				//System.out.println(num);
				count++;
				continue; 
			}
			else if (num%2==0)					// if num is even
			{
				num = num/2;
				//System.out.println(num);
				count++;
				continue;
			}
		}
		System.out.println(count+" Steps required");
	}
}
/*
Enter a number
42
21
64
32
16
8
4
2
1
*/