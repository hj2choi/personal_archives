class Euler040 extends HongJoonLibrary
{
	public static void main(String[] args) 
	{
		int realNumber=1;
		int ans=1;
		for (int i=1;i<1000000 ;)
		{
			realNumber++;
			for (int j=0;j<(""+realNumber).length() ;j++ )
			{
				i++;
				if (i==100 || i==1000 || i==10000 || i==100000 || i==1000000)
				{
					System.out.println(j+"     "+realNumber+"      "+(""+realNumber).charAt(j));
					ans*=(""+realNumber).charAt(j)-48;
				}
			}
			
		}
		System.out.println(ans);
	}
}