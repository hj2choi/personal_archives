class Euler069	extends HongJoonLibrary	
{
//large number cycle = multiple of 6
//multiple of 210 is higher than 4
	final static int sF = 1000000;
	final static int skip=210;
	public static void main(String[] args) 
	{
		double[] n= new double[sF];
		double xx=0;
		for (int i=skip;i<sF ;i+=skip )
		{
			xx=(double)i/(double)relativelyPrimeNumber(i);
			if (xx>4)
				System.out.println("n="+i+"		q(n)="+relativelyPrimeNumber(i)+"		n/q(n)="+xx);
			n[i]=xx;
		}
		System.out.println("answer :"+maxOf(n));
	}
	public static int maxOf(double[] s)			//returns position of array in which maximum number in (int[]) occurs
	{
		for (int k=0;k<=maxArraySize ;k++)
		{
			//test(k);
			boolean maxchk = true;
			for (int l=0;l<=maxArraySize ;l++ )
			{
				try
				{
					if ((s[k]<s[l])||s[k]==0)
						maxchk=false;
				}
				catch (Exception e)
				{
					break;
				}
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