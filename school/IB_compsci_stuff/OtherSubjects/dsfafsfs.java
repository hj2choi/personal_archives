class rocketsimulate 
{
	public static void main(String[] args) 
	{
		int sum = 0;
		for (int y=7;y<8 ;y++ )
			for (int i=-9;i<9;i++ )
				for (int j=-9;j<9 ;j++ )
					for (int k=-9;k<9 ;k++ )
						if (Math.sqrt(i*i+j*j+k*k)==y)
							System.out.println("i="+i+" j="+j+" k"+k+"");
		System.out.println(sum);
							
		return;



		/*
		double m = 40000;
		double V = 0;
		double M = 0;
		double DV = 0;

		System.out.println("			SIMULATION OF ROCKET PROPULSION");
			System.out.println("");
		for (double t = 0;t<=600 ;t=t+1.5 )
		{
			M = m - (t*60);
			DV = 4000*(90/M);
			V = V+DV;
			System.out.println(" t = "+t+"	M = "+(int)M+"	DV = "+(double)((double)Math.round(DV*10000)/10000)+"	V = "+((double)Math.round(V*10000)/10000));
		}*/
	}
}
