class Euler023 extends HongJoonLibrary
{
	public static void main(String[] args) 
	{
		long sum = 0;
		int[] abundantNumbers = new int[28124];
		int arrayCount=0;
		startTimer();
		for (int i=12; i<28124; i++)
		{
			if (isAbundant(i))
				abundantNumbers[arrayCount++]=i;
		}
		System.out.println(arrayCount);
		
		for (int i=0; i<arrayCount; i++)
		{
			System.out.println(abundantNumbers[i]);
		}
		
		for (int x=0; x<28124*2; x++)
		{
			boolean flag=true;
			verificationLoop: for (int i=0; i<arrayCount; i++)
				for (int j=0; j<arrayCount; j++)
					if (abundantNumbers[i]+abundantNumbers[j]==x)
					{
						flag=false;
						break verificationLoop;
					}
			if (flag)
			{
				System.out.println(x);
				sum+=x;
			}
		}
		System.out.println(sum);

		endTimer();
		
	}

	public static boolean isAbundant(int num)
	{
		int sum=1;
		for (int i=2; i<=num/2; i++)
			if (num%i==0)
			{
				sum+=i;
			}
		return sum>num;
	}
}
//9,4,6,11
//2
