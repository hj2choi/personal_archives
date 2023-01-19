/*
(x-n)/(nx)=y


*/
class Euler108 
{
	public static void main(String[] args)
	{
		for (int n=1260; n<1261; n++)
		{
			int sum=0;
			for (int x=n*2; x<300000; x++)
			{
				//System.out.print(n+", "+x);
				if ((x*n)%(x-n)==0)//(double)(x-n)/(double)(x*n))
				{
					//System.out.print("  "+true);
					sum++;
				}
				//System.out.println();
			}
			System.out.println(sum);
			if (sum>=1000)
			{
				return;
			}
		}
	}	
}