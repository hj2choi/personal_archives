class Euler031 extends HongJoonLibrary	 //Catalan formula
{
	public static void main(String[] args) 
	{
		int probability=0;
		for (int a=0; a<=200; a++)//1p
		{
			System.out.println(a+"/"+200);
			for (int b=0; b<=100; b++)//2p
			{
				for (int c=0; c<=40; c++)//5p
				{
					for (int d=0; d<=20; d++)//10p
					{
						for (int e=0; e<=10; e++)//20p
						{
							for (int f=0; f<=4; f++)//50p
							{
								for (int g=0; g<=2; g++)//1L
								{
									if (a+b*2+c*5+d*10+e*20+f*50+g*100==200)
										probability++;

								}
							}
						}
					}
				}
			}
		}
		System.out.println(probability);
	}
}
