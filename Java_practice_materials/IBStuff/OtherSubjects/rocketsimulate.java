class rocketsimulate 
{
	public static void main(String[] args) 
	{
		int vicCount=0;
		int ELO=1200;
		for (int i=0; i<100; i++)
		{
			if (Math.random()>0.5)
			{
				System.out.println("victory");
				vicCount++;
			}
			else
				System.out.println("defeat");
			
		}
		System.out.println(vicCount);
	}





	public static int choose(int n, int r)
	{
		return factorial(n)/(factorial(n-r)*factorial(r));
	}
	public static int factorial(int i)
	{
		int num=1;
		for (int j=1; j<=i; j++)
			num*=j;
		return num;
	}
}