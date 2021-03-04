class Euler053
{

	public static void main(String[] args)
	{
		
		int ans=0;
		for (double i=1; i<=100; i++)
			for (double j=1; j<=i; j++)
				if (choose(i, j))
					ans++;
		System.out.println(ans);
	}
	static boolean choose(double i, double j)
	{
		if (factorial(i)/(factorial(j)*factorial(i-j))>=1000000)
			return true;
		return false;
	}
	static double factorial(double a)
	{
		double n=1;
		for (double i=1; i<=a; i++)
			n*=i;
		return n;
	}
}
