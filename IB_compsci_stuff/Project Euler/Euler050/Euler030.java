class Euler030 extends HongJoonLibrary	 //Catalan formula
{
	final static int sF = 100000;		//searchFor
	public static void main(String[] args) 
	{
		long[] sum = new long[100000];
		long aa=0;
		long ans=0;
		int count=0;
		for (long i=2;i<sF ;i++ )
		{
			if (sumOfPowerOfDigits(i))
			{
				sum[count]=i;
				System.out.println(i);
				count++;
			}
		}
		for (int i=0;i<1000 ;i++ )
		{
			ans+=sum[i];
		}
		System.out.println(ans);
	}
	public static boolean sumOfPowerOfDigits(long num)
	{
		long check=0;
		long numSave=num;
		for (int i=0;i<(""+num).length()+1 ;i++ )
		{
			check+=Math.pow(numSave%10, 5);
			numSave=numSave/10;
		}
		if (num==check)
		{
			return true;
		}
		return false;
	}
}
