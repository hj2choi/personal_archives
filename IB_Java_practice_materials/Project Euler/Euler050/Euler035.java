class Euler035 extends HongJoonLibrary	 //Catalan formula
{
	final static int sF = 1000000;		//searchFor
	public static void main(String[] args) 
	{
		int count=0;
		for (int i=2;i<sF ;i++ )
		{
			//test(i);
			if (circularNumber(i))
			{
				test(i);
				count++;
			}
		}
		System.out.println(count);
	}
	public static boolean circularNumber(int n)
	{
		String num = ""+n;
		char[] numCharacter = num.toCharArray();//123-1 213-2 321-3		//1234 1243 1324 1342 
		for (int i=0;i<num.length() ;i++ )
		{
			String ss="";
			for (int j=0;j<num.length() ;j++ )
			{
				try
				{
					ss+=numCharacter[j+i];
				}
				catch (Exception e)
				{
					ss+=numCharacter[i+j-num.length()];
				}
					
			}
			//System.out.println(ss);
			if (!primeChk(Integer.parseInt(ss)))
				return false;
		}
		return true;
	}
	public static boolean primeChk(int num)
	{
		for (int a = 2;a<num/1.8 ;a++ )
		{
			if (num%a==0 && num!=2)
			{
				return false;		//for optimum performance
			}
		}
		return true;
	}
}
