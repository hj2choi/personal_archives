class Euler041
{
	public static void main(String[] args) 
	{
		for (int i=1000; i<10000; i++)
		{
			if (primeCheck(i) && numberPermutation(i).equals("0000011110"))
				System.out.println(i);
		}
		for (int i=3000000; i<10000000; i++)
		{
			if (primeCheck(i) && numberPermutation(i).equals("0011111110"))
				System.out.println(i);
		}
		System.out.println("done");
	}

	public static boolean primeCheck(int num)
	{
		if (num==2)
			return true;
		if (num<=0)
			return false;
		for (int a = 2;a<Math.sqrt(num) ;a++ )
			if (num%a==0)
				return false;
		return true;
	}

	public static String numberPermutation(int num)
	{
		int[] convert = new int[10];
		String arrangedString = "";
		while (num>0)
		{
			convert[num%10]++;
			num/=10;
		}
		for (int i=9;i>=0 ;i-- )
			arrangedString+=convert[i];
		return arrangedString;
	}
}
