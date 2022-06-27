import java.math.BigInteger;
class Euler052
{

	public static void main(String[] args)
	{
		for (int i=0;i<1000000 ;i++ )
		{
			String ss = numberPermutation(i);
			if (ss.equals(numberPermutation(2*i)) && ss.equals(numberPermutation(3*i)) && ss.equals(numberPermutation(4*i)) && ss.equals(numberPermutation(5*i)) && ss.equals(numberPermutation(6*i)))
			{
				System.out.println(i);
			}
		}


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
		//return Long.parseLong(arrangedString);
		return arrangedString;
	}
	/*
	public static void main(String[] args) 
	{
		int ans = 0;
		ans = safeInput();
		BigInteger plus = new BigInteger("10");
		BigInteger i= new BigInteger("10000000000000000000000");
		BigInteger dfg = new BigInteger("99999999999999999999999999999999999999999999999");
		BigInteger one= new BigInteger("1");
		BigInteger ten= new BigInteger("10");
		while(i.compareTo(dfg)<=0)
		{
			//System.out.println(i);
			if ((""+i).startsWith("17"))
			{
				plus=plus.valueOf(82);
				for (int j=0;j<(""+i).length()-2 ;j++ )
					plus=plus.multiply(ten);
				i=i.add(plus);
			}
			i=i.add(one);
			if (containSameDigit(i))
				System.out.println(i);
		}
	}
	public static boolean containSameDigit(BigInteger a)
	{
		boolean chk=false;
		String ss ="";
		String s=""+a;
		char[] cc=s.toCharArray();
		for (int i=0;i<6 ;i++ )
		{
			a=a.add(a);
			ss=""+a;
			char[] compare=ss.toCharArray();
			if (s.length()!=ss.length())
				return false;
			for (int j=0;j<ss.length() ;j++ )
			{
				chk=false;
				for (int k=0;k<s.length() ;k++ )
					if (compare[j]==cc[k])
						chk=true;
				if (chk==false)
					return false;
			}
		}
		return true;
	}
	*/
}
