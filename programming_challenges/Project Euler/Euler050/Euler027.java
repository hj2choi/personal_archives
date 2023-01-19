class Euler027
{
	public static void main(String[] args)
	{
		int ans=0;
		int coefficientA =0;
		int coefficientB =0;

		int temp=0;
		for (int a=-999; a<1000; a++)//coefficient a
		{

			for (int b=-999; b<1000; b++)//coefficient b
			{
				temp=numberOfConsecutivePrimes(a, b);
				if (temp>=ans)
				{
					ans=temp;
					coefficientA =a;
					coefficientB =b;
				}
			}
		}
		System.out.println(ans+" = n^2"+(coefficientA>0?"+":"")+coefficientA+"n+"+coefficientB);
	}
	public static int numberOfConsecutivePrimes(int a, int b)//coefficients as parameters
	{
		int ans=0;
		for (int n=0; n<3000; n++)
		{
			if (!primeCheck(n*n+a*n+b))
				return n;
		}
		return 3000;
		
		
		
	}
	public static boolean primeCheck(int num)
	{
		if (num==2)
			return true;
		if (num<=0)
			return false;
		for (int a = 2;a<num/1.8 ;a++ )
			if (num%a==0)
				return false;
		return true;
	}
}