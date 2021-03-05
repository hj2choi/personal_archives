class  Euler076
{
	public static void main(String[] args) 
	{
		int sum=0;
		int ans=0;
		//int requiredSum=100;


		
		for (int requiredSum = 2; requiredSum<20; requiredSum++)
		{
			ans=0;
			ArtificialForLoop[] loop = new ArtificialForLoop[requiredSum];
			for (int i=0; i<requiredSum; i++)//create loop
			{
				loop[i] = new ArtificialForLoop(i+2);
			}

			forLoop : while(!loop[0].reachedMax())//nested loop
			{
				sum=0;
				for (int i=loop.length-2; i>=0; i--)//check and process through all loops
					if (loop[i].reachedMax())
					{
						try{loop[i-1].add();}catch (Exception e){break forLoop;}//i++;
						
					}
				while(!loop[loop.length-1].reachedMax())//innermost loop
				{
					//dostuff
					sum=0;
					for (int k=0; k<loop.length; k++)
						sum+=(loop[k].i)*(loop.length-k);
					if (sum==requiredSum)
					{
						//print(loop);
						ans++;
						//System.out.println(ans+" "+loop[50].i);
					}
					//end of dostuff
					loop[loop.length-1].add();// i++
				}
				loop[loop.length-2].add();
			}
			System.out.println(requiredSum+" = "+--ans);

		}
	}

	public static int add(int[] s)
	{
	int n=0;
	for (int i=0; i<s.length; i++)
		n+=s[i];
	return n;
	}

	public static void print(ArtificialForLoop[] loop)
	{
		for (int i=0; i<loop.length; i++)
		{
			System.out.print(loop[i].i+" ");
		}
		System.out.println();
	}
}



class ArtificialForLoop// enable nested virtual for loops with few real loops
{
	int i;
	int max;

	ArtificialForLoop(int max)
	{
		i=0;
		this.max=max;
	}

	public boolean reachedMax()
	{
		if (i==max)
		{
			i=0;
			return true;
		}
		return false;
	}

	public void add()
	{
		i++;
	}
}


//1 2 4 6 10 14 21 29 41 55
// 1  2 2  4  4   7   8   12 14