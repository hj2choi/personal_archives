class Euler039 extends HongJoonLibrary
{
	public static void main(String[] args) 
	{
		int max_Ans=0;
		int ans=0;
		int ansToProblem = 0;
		for (int i=1;i<=1000; i++ )
		{
			ans=noOfSolutions(i);
			System.out.println(i+" =   "+ans);
			if (ans>max_Ans)
			{
				max_Ans=ans;
				ansToProblem=i;
			}
		}
		System.out.println(ansToProblem);
	}
	public static int noOfSolutions(int num)// for triangle
	{
		int ans=0;
		int limit=num/2;
		for (int i=0;i<limit ;i++ )
			for (int j=0;j<limit ;j++ )
				for (int k=0;k<limit ;k++ )
					if (i*i+j*j==k*k && i+j+k==num)
					{
						ans++;
						//System.out.println(i+", "+j+", "+k);
					}
		return ans/2;
	}
}
