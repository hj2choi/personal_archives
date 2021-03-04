class  Euler078 extends HongJoonLibrary
{
	public static void main(String[] args) 
	{
		System.out.println("Hello World!");
		for (int x=1;x<=1000000 ;x++ )
		{
			int fx=0;
			for (int i=1;i<x ;i++ )
			{
				fx+=x-i-1;
			}
			System.out.println("f("+x+") = "+(fx+1));
			if ((fx+1)%1000000==0)
			{
				s.next();
			}
		}
	}
}
