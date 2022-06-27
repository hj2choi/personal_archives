class Euler075	extends HongJoonLibrary	
{
// To be right angled triangle : a^2+b^2=c^2
	final static int sF = 600000;
	final static int skip=1;
	public static void main(String[] args) 
	{
		int ans=0;
		int roll=0;
		int[] L = new int[3000000];
		for (int i=1;i<sF ;i++ )
		{
			System.out.println("Progress : "+i+"/"+sF);
			double triNum=0;
			int length=0;
			for (int j=i;j<sF ;j++ )
			{
				triNum=Math.sqrt((double)(i*i+j*j));
				length=(int)triNum+i+j;
				if (triNum==(int)triNum && length<1500000)
				{
					//pp(length);
					L[roll]=length;
					roll++;
				}
			}
		}
			for (int d=0;d<roll ;d++ )
			{
				System.out.println(d+"/"+roll);
				if (searchFor(L[d], L, roll)==1)
				{
					//pp(searchFor(L[d], L, roll)+"	"+L[d]);
					ans++;
				}
			}
			System.out.println("ans : "+ans);
		}
	public static byte searchFor(int a, int[] s, int roll)
	{
		byte numberSearched=0;
		for (int i=0;i<roll ;i++ )
		{
			if (a == s[i])
				numberSearched++;
		}
		return numberSearched;
	}
}