class joonrockIA
{
	public final static int r = 100;						//change this value of r if you have to incrase range of x and y!!! But, it will take much longer time to run a program!
	static double minnum(double[] n)
	{
		return n[1];

	}
	public static void main(String[] args) 
	{
		System.out.println("Joon Rock Choi IA");
		System.out.println("heatloss		 x	y	(value of x and y for minimum value of heatloss)");
		double[] heatloss = new double[r*r];
		double[] a = new double[r*r];
		double[] b = new double[r*r];
		int i = 0;
		for (double x=1;x<r ;x++ )
			for (double y=1;y<r ;y++ )
			{
				heatloss[i] = (1.8*x*y)+(160/x)+(192/y);
				a[i] = x;
				b[i] = y;
				i++;
			}
		for (int k=0;k<=i ;k++)
		{
			boolean minchk = true;
			for (int l=0;l<=i ;l++ )
				if ((heatloss[k]>heatloss[l])||heatloss[k]<=0)
					if (heatloss[l]>0)
						minchk=false;
			if(minchk==true)
				System.out.println(heatloss[k]+"	"+a[k]+"	"+b[k]);
		}
	}
}
