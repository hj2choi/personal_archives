class mathPermutation
{
	public static void main(String[] args) 
	{
		int[] permutation = new int[4];
		for (int i=0; i<permutation.length; i++)
			permutation[i]=i;
		int limit = permutation.length;

		for (int i=2; i<limit; i++)
		{
			//permutation[limit-i];
			for (int j=0; j<i ; j++)
			{

				for (int k=0; k<limit; k++)
					System.out.print(permutation[k]+" ");
				System.out.println();
			}
		}
	}

}