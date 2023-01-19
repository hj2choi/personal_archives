class Euler015
{	
	final static int grid =21;		//	 add one to actual gridsize
	final static int arraySize = 30000000;
	public static void main(String args[])
	{
		System.out.println("This Program is used to solve advanced questions on project Euler");
		long[] route = new long[arraySize];
		
		for (int k=0;k<grid ;k++ )
		{
			route[k*grid+1]=1;
			for (int i=2;i<=grid ;i++ )
			{
				try
				{
					route[k*grid+i]=route[k*grid+i-1]+route[k*grid+i-grid];
				}
				catch (Exception e)
				{
					route[k*grid+i]=route[k*grid+i-1];
				}
			}
		}
		for (int a=0;a<grid ;a++ )
		{
			for (int i=1;i<=grid ;i++ )
				System.out.print(route[a*grid+i]+" ");
			System.out.println("");
		}

	}
}
