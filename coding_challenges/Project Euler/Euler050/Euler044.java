class Euler044
{
	public static void main(String[] args) 
	{
		int ans=0;
		int[] pentagonalNumbers = new int[30000];
		for (int i=0;i<30000 ;i++ )
			pentagonalNumbers[i]=((i+1)*(3*i+2))/2;//n(3n1)/2;
		for (int i=0;i<pentagonalNumbers.length ;i++ )
		{
			//System.out.println(i);
			for (int j=i;j<pentagonalNumbers.length ;j++ )
			{
				if (search((pentagonalNumbers[i]+pentagonalNumbers[j]), pentagonalNumbers) && search(Math.abs((pentagonalNumbers[i]-pentagonalNumbers[j])), pentagonalNumbers))
				{
					int temp=Math.abs(pentagonalNumbers[i]-pentagonalNumbers[j]);
					System.out.println(temp);
					if (temp<ans || ans==0 && temp!=0)
						ans=temp;
				}
			}
		}
		System.out.println(ans);
	}

	public static boolean search(int a, int[] s)		//Search for (int a) in array (int[]s) and returns position of (int a) else, returns -1
	{
		for (int i=0;i<s.length ;i++ )
			if (a == s[i])
				return true;
		return false;
	}
}
