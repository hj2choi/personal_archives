public class TestR
{	public static void main (String []args)
	{
		java.util.Scanner s = new java.util.Scanner(System.in);
		
		long num = 0;
		while(true)
		{
			System.out.println("Enter a positive integer less than 16:");
			num = s.nextInt();
			if (num < 1)
			System.out.println("Idiot u must enter bigger than 1");
		//	else if (num > 15)
		//	System.out.println("What are u? Less than 15");
			else
			break;
		}
		
		long ans = 1;
		String ss = "" + num + "! = ";
		for (long i = num; i >= 2 ; i-- )
		{
			long a = i;
			ans = ans*i;
			ss = ss + i + " x ";
			System.out.printn(a);
			System.out.println(ans);
		}
	//	ss = ss + "1 = " + ans;
	//	System.out.println(ss);
		ss = "" + ans;
		System.out.println(ans);
	//	System.out.println("there are "+ss.length()+" digits");
	}
}