class Euler037 extends HongJoonLibrary
{
	public static void main(String[] args) 
	{
		boolean flagUp  = true;
		int ans = 0;
		int count=0;
		
		//System.out.println(primeCheck(1));
		//System.out.println(primeCheck(7));
		for (int i=10;i<1000000  ;i++ )
		{
			flagUp=false;
			if (primeCheck(i))
			{
				flagUp=true;
				//System.out.println("prime : "+i);
				int num = i;
				while (num>=1)
				{
					//System.out.println(num);
					if (!primeCheck(num))
					{
						//System.out.println("non prime found");
						flagUp=false;
						break;
					}
					num = eliminateRightDigit(num);
				}
				//System.out.println(flagUp);
				num = i;
				while (num>=1)
				{
					//System.out.println(num);
					if (!primeCheck(num))
					{
						//System.out.println("non prime found");
						flagUp=false;
						break;
					}
					num = eliminateLeftDigit(num);
				}
			}
			//System.out.println("");
			if (flagUp)
			{
				System.out.println(i);
				ans+=i;
				count++;
			}
			if (count==11)
			{
				break;
			}
		}
		System.out.println(ans);
		s.next();
	}
	public static int eliminateRightDigit(int num)
	{
		num%=Math.pow(10,(""+num).length()-1);
		return num;
	}
	public static int eliminateLeftDigit(int num)
	{
		return num/10;

	}
}
