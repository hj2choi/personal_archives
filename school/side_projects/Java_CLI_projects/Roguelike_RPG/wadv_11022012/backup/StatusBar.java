public class StatusBar
{
	wadv main = new wadv();
	public String heBar(double gg, char dtt)
	{
		String hebar = "";
		gg = (int)(gg*20);
		for (int e = 0;e<gg ;e++ )
		{
			hebar = hebar+dtt;
		}
		if (gg<0)
			gg=0;
		for (int kl = 0;kl<(20-gg) ;kl++ )
		{
			hebar = hebar+"_";
		}
		return hebar;
	}
	
	private String user;
	private String mob;
	private double ughp;
	private double umhp;
	private double mghp;
	private double mmhp;
	private String GayBar;
	private double ugmp;
	private double ummp;
	private char dt;
	
	StatusBar(String a, double b,double c, String ba, double bb, double bc, double ca, double cb, char dot)
	{
		user = a;
		mob = ba;
		ughp = b;
		umhp = c;
		mghp = bb;
		mmhp = bc;
		ugmp = ca;
		ummp = cb;
		dt = dot;
		if (mghp<0)
			mghp=0;
		if (ughp<0)
			mghp=0;
		if (mghp<0)
			mghp=0;
		if (ugmp<0)
			ugmp=0;
	}
		void print()
		{ 
			for (int k = 0;k<main.clearscreen ;k++ )
				System.out.println("");
			System.out.println(user+"'s HP: "+(int)ughp+" / "+(int)umhp+"				");	
			GayBar = heBar(ughp/umhp, dt);
			System.out.println(" "+GayBar+"			       "+mob+" "+(int)mghp+" / "+(int)mmhp);
			GayBar = heBar(mghp/mmhp, dt);
			System.out.print("						 "+GayBar);
			System.out.println("");				
			System.out.println("  "+user+"'s MP: "+(int)ugmp+" / "+(int)ummp);			// comp status bar
			GayBar = heBar(ugmp/ummp, dt);
			System.out.println("   "+GayBar);	
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
		}
}

