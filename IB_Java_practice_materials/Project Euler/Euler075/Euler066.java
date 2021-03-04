class Euler066 extends HongJoonLibrary	 //Catalan formula
{
	final static int searchFor = 7;
	public static void main(String[] args) 
	{
		//============Analysis==============
		//x^2-Dy^2=1
		//x^2=Dy^2+1
		//y=(ax+1)^1/2
		//y^2=ax^2+1
		//===============================
		long[] yy = new long[searchFor];
		long count = 0;
		aLoop : for (int a=900;a<searchFor ;a++ )
		{
			if (Math.sqrt((double)a)-Math.round(Math.sqrt((double)a))==0)
				continue aLoop;
			pp(a);
			xyLoop:for (long x=1;x<200000 ;x++ )
			{
				for (long y=1;y<200000 ;y++ )
				{
					if (a*x*x+1==y*y && y!=0)
					{
						System.out.println((a*x*x+1)+"   : "+y);
						yy[a]=y;
						break xyLoop;
					}
				}
			}
		}
		for (int i=0;i<searchFor ;i++ )
			System.out.println(yy[i]);
		System.out.println(maxOf(yy));
	}
	public static long maxOf(long[] s)			//returns position of array in which maximum number in (int[]) occurs
	{
		for (int k=0;k<=searchFor ;k++)
		{
			boolean maxchk = true;
			for (int l=0;l<=searchFor ;l++ )
			{
				try
				{
					if (s[k]<s[l]||s[k]==0)
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
}
