class Euler070	extends HongJoonLibrary	
{
// n/q(n) with small values is odd, and has 2 factors
	final static int sF = (int)Math.pow(10,7);
	final static int skip=1;
	public static void main(String[] args) 
	{
		double[] n= new double[sF];
		double xx=0;
		int rPN=0;
		for (int i=3;i<sF ;i+=2 )
		{
			rPN=relativelyPrimeNumber(i);
			xx=(double)i/(double)rPN;
			if (xx<1.1 && encryptNumArrangement(rPN).equals(encryptNumArrangement(i)))
			{
				pp("n="+i+"		q(n)="+rPN+"		n/q(n)="+xx);
				n[i]=xx;
			}
		}
		pp("answer : "+minOf(n));
	}

	public static int minOf(double[] s)			//returns position of array in which maximum number in (int[]) occurs
	{
		for (int k=0;k<=maxArraySize ;k++)
		{
			boolean maxchk = true;
			for (int l=0;l<=maxArraySize ;l++ )
			{
				try
				{
					if (s[l]==0)
						continue;
				}
				catch (Exception e)
				{
					break;
				}
				if ((s[k]>s[l])||s[k]==0)
					maxchk=false;
			}
			if(maxchk==true)
				return k;
		}
		return -1;
	}
	public static int relativelyPrimeNumber(int n)
	{
		int i=0;
		for (int k=1;k<=n ;k++ )
			if (gcd(n,k)==1)
				i++;
		return i; 
	}
	public static String encryptNumArrangement(int num)
	{
		int[] encryption = new int[10];
		String encryptedArrangement = "";
		while (num>0)
		{
			encryption[num%10]++;
			num/=10;
		}
		for (int i=9;i>=0 ;i-- )
			encryptedArrangement+=encryption[i];
		return encryptedArrangement;
	}
}
/*
4620
9240
30030
39270

43890
46410
212520
300300
363090
*/