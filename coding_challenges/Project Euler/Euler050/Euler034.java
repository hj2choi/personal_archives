class Euler034	extends HongJoonLibrary
{
	public static void main(String[] args) 
	{
		long n=0;
		long num=0;
		long ans=0;
		for (long i=10;i<10000000 ;i++ )
		{
			n=i;
			num=0;
			for (int j=0;j<(""+i).length() ;j++ )
			{
				num+=factorial((short)(n%10));
				n/=10;
			}
			if (num==i)
			{
				System.out.println(i);
				ans+=i;
			}
		}
		System.out.println(ans);
	}
	public static int factorial(short n)
	{
		int xx = 1;
		for (short i=1;i<=n ;i++ )
			xx*=i;
		return xx;
	}
}
