public class pr023switch42
{	public static void main (String []args)
	{
		int chk;
		int multiply=0;
		int answer=0;
		for (int num=1;num<1001 ;num++ )
		{
			chk=num%3;
			switch (chk)
			{
				case 0:
					multiply = -5;
					break;
				case 1:
					multiply = 7;
					break;
				case 2:
					multiply = 2;
					break;
			}
			answer = answer+(num*multiply);
		}
		System.out.println(answer);
	}
}
/*
669004
*/