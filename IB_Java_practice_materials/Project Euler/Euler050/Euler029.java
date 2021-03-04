class Euler029	extends HongJoonLibrary
{
	public static void main(String[] args) 
	{
		double[] numbers = new double[10000];
		int ans=0;

		for (double i=2; i<=100; i++)
		{
			for (double j=2; j<=100; j++)
			{
				double n=Math.pow(i,j);
				if (!search(n, numbers))
				{
					numbers[ans++]=n;
				}
			}
		}
		System.out.println(ans);
	}
	public static boolean search(double a, double[] s)		//Search for (int a) in array (int[]s) and returns position of (int a) else, returns -1
	{
		for (int i=0;i<s.length ;i++ )
			if (a == s[i])
				return true;
		return false;
	}
}
