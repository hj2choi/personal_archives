class Euler032
{
	public static void main(String[] args) 
	{
		int[] pandigitalNumber = new int[1000];
		int roll=0;
		int ans=0;

		int ss=0;

		for (int i=1; i<5000; i++)//multiplicand
		{
			for (int j=1; j<5000; j++)//multiplier
			{
				try
				{
					ss = Integer.parseInt(""+i*j+i+j);}
				catch (Exception e){}
				if (numberPermutation(ss).equals("1111111110") && !search(i*j,pandigitalNumber))
				{
					System.out.println(i+" x "+j+" = "+(i*j));
					pandigitalNumber[roll++] = i*j;
				}
			}
		}
		System.out.println(add(pandigitalNumber));
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

	public static boolean search(int a, int[] s)		//Search for (int a) in array (int[]s) and returns position of (int a) else, returns -1
	{
		for (int i=0;i<s.length ;i++ )
			if (a == s[i])
				return true;
		return false;
	}

	public static int add(int[] s)
	{
		int n=0;
		for (int i=0; i<s.length; i++)
			n+=s[i];
		return n;
	}
}
