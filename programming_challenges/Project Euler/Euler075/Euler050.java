import java.math.BigInteger;
class Euler050 extends HongJoonLibrary	 //Catalan formula
{
	final static int searchFor = 1000;
	public static void main(String[] args) 
	{
		boolean chk = false;
		int[] prime = new int[searchFor];
		prime[0] = 2;
		int roll=1;
		for (int you=1;you<searchFor ;you+=2 )
		{
			if (you%4999==0)
				System.out.println("Prime Number Calculation Progress: "+you/5000+"/200");
			chk=false;
			for (int a = 2;a<you ;a++ )									//factor finding
			{
				if (you%a==0)
				{
					chk = true;
					break;
				}
			}
			if (!chk && you!=1)
			{
				prime[roll]=you;
				roll++;
			}
		}
		test();
		prime = sort(prime, 0);
		for (int i=0;i<roll ;i++ )
		{
			System.out.println(prime[i]);
		}
		System.out.println(prime[maxOf(prime)]);
		s.next();
		System.out.println("Calculating final ans...");
		int ans=0;
		int finAns=0;
		for (int i=0;i<roll ;i++ )
		{
			int val=0;
			for (int k=0;k<roll ;k++ )
			{
				val=0;
				for (int j=k;j<i ;j++ )
				{
					val+=prime[j];
					if (val==prime[i] && ans<j-k)
					{
						System.out.println(k+"  "+ans+"  "+prime[i]+"");
						ans=j-k;
						finAns=prime[i];
					}
					if (val>prime[i])
						break;
				}
			}
		}
		System.out.println(ans+1);		//536
		System.out.println(finAns);
		s.next();
		while (true)
		{
			s.next();
		}
	}
}
