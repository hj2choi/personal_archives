class pr006extloopTriangle
{
	public static void main(String[] args) 
	{
			java.util.Scanner s = new java.util.Scanner(System.in);
			System.out.println("enter the nuber of lines");
			int k = s.nextInt();  						 // number of lines
			k=(k*2)+1;	

			for (int i = 1;i<k ;i++ )
			{
				System.out.println("");
				for (int j = 1;j<(k-10) ;j++ )
				{
					System.out.print(" ");
				} 
				for (int z = 2;z<(int)((i*2)-1) ;z++ )
				{
					
					System.out.print("*");
				}
				k--;
			}
	}
}