class Mq1assessment
{	public static void main(String args[])
	{
		System.out.println("Hong Joon Choi. IBHL Mathematics Assignment Type 1");

		long n1 = 1;
		long n2 = 1;
		int s_increment = 1;
		int s_term = 0;
		for (long s = 1;s<3000000 ;s=s+s_increment )			//square sequence
		{
			s_increment = s_increment+2;
			s_term++;
			int t_increment = 1;
			int t_term = 0;
			for (long t = 1;t<3000000 ;t=t+t_increment )			// triangle sequence
			{
				t_term++;
				t_increment++;
				n1 = n2;
				if (s == t)							// in case square number = triangle number
				{
					n2 = s;
					System.out.print(s);
					System.out.println("	 sq.num:"+s_term+"th,	 tr.num:"+t_term+"th,	subt:"+(n2-n1)+",	divide:"+(double)((double)n2/(double)n1));
				}
			}
		}
	}
}