class Euler045 extends HongJoonLibrary
{
	public static void main(String[] args) 
	{
		for (int i=1;i<1000	 ;i++ )
		{
			for (int j=1; j<1000; j++)
			{
				for (int k=1; k<1000; k++)
				{
					long t=(i*(i+1))/2;
					long p=(j*(3*j-1))/2;
					long h=k*(2*k-1);
					System.out.println(i+", "+j+", "+k+" = "+t);
					if (t==p && t==h && p==h)
					{
						System.out.println(i+", "+j+", "+k+" = "+t);
						s.next();
					}
				}
			}
		}
	}
}