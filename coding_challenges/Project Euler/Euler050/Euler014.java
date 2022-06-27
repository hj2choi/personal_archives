class Euler014
{	public static void main(String args[])
	{
		System.out.println("This Program is used to solve advanced questions on project Euler");
		// qn 14
		// Which starting number, under one million, produces the longest chain?
		/*
		long num = 0;
		long count = 0;
		for (long i = 13;i<1000000 ;i++ )
		{
			num = i;
			count = 0;
			while (num !=1)								// loop
			{
				if (num==0)
					break;
				if (num%2==1)							
				{
					num = (num*3)+1;
					count++;
					continue; 
				}
				else if (num%2==0)					
				{
					num = num/2;
					count++;
					continue;
				}
			}
			if (count>300)
			{
				System.out.println(count);
				System.out.println(i);
			}
		}
		
		// external
		
		int ans = 0;
		String word = "";
		for (int a = 1;a<27;a++)
		{
			for (int b = 1;b<22;b++)
			{
				for (int c = 1;c<22;c++)
				{
					for (int d = 1;d<27;d++)
					{
						for (int e = 1;e<27;e++)
						{
							for (int f = 1;f<27;f++)
							{
								for (int g = 1;g<10;g++)
								{
									for (int h = 1;h<10;h++)
									{
										ans = a+b+c+d+e+f+g+h;
										if (ans == 100 && a==1 && b==20 && c>18 && d==9 && e==20 && f>19 && g>3 && h>3 )
										{
											System.out.println("");
											System.out.print((char)(a+64));
											System.out.print((char)(b+64));
											System.out.print((char)(c+64));
											System.out.print((char)(d+64));
											System.out.print((char)(e+64));
											System.out.print((char)(f+64));
											System.out.print((char)(g+64));
											System.out.print((char)(h+64));
										}
									}
								}
							}
						}
					}
				}
				
			}
		}
		*/
		

	}
}
