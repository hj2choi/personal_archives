/*
find the number of triangles for which the interior contains the origin.

1. find gradient, y-intercept and x-intercept (3 lines)
2.


conditions :
3 lines should touch +y and -y once at least.
point of intersection(which is given) should 





*/

import java.io.*;
class Euler102 
{
	static String[] file = new String[1100];
	public static void main(String[] args) throws IOException
	{
		String[] n = new String[6];
		int[] num = new int[6];
		load();
		int ans=0;
		for (int i=0; i<1000; i++)
		{
			n = file[i].split(",");
			for (int j=0; j<6; j++)
				num[j]=Integer.parseInt(n[j]);
			if (triangleCheck(new Point(num[0],num[1]), new Point(num[2],num[3]), new Point(num[4],num[5])))
			{
				ans++;
			}
		}
		System.out.println(ans);
		System.out.println(triangleCheck(new Point(-340,495), new Point(-153,-910), new Point(835,-947)));
		System.out.println(triangleCheck(new Point(-175,41), new Point(-421,-714), new Point(574,-645)));
	}

	public static boolean triangleCheck(Point aa, Point bb, Point cc) // y=mx+c //+x=0, +y=1, -x=2, -y=3
	{
		Point a=aa;
		Point b=bb;
		Point c=cc;

		boolean[] intersect = new boolean[4];
		double AB_gradient=Point.gradient(a, b);
		double BC_gradient=Point.gradient(b, c);
		double AC_gradient=Point.gradient(a, c);
		
		double y=0;
		double x=0;

		try// line AB
		{
			intersect[infinityCheck(AB_gradient, a)]=true;
		}
		catch (Exception e)
		{
			y=-AB_gradient*a.x+a.y;
			x=-y/AB_gradient;
			if ((a.y<=y && y<=b.y) || (a.y>=y && y>=b.y))//within range, and + or -
			{
				
				if (y>0)
					intersect[1]=true;
				if (y<0)
					intersect[3]=true;
				
			}
			if ((a.x<=x && x<=b.x) || (a.x>=x && x>=b.x))//within range, and + or -
			{
				if (x>0)
					intersect[0]=true;
				if (x<0)
					intersect[2]=true;
				
			}
		
		}
		
		try// line BC
		{
			intersect[infinityCheck(BC_gradient, b)]=true;
		}
		catch (Exception e)
		{
			y=-BC_gradient*b.x+b.y;
			x=-y/BC_gradient;
			//System.out.println(y+" "+x);
			if ((b.y<=y && y<=c.y) || (b.y>=y && y>=c.y))//within range, and + or -
			{
				if (y>0)
					intersect[1]=true;
				if (y<0)
					intersect[3]=true;
				
			}
			if ((b.x<=x && x<=c.x) || (b.x>=x && x>=c.x))//within range, and + or -
			{
				if (x>0)
					intersect[0]=true;
				if (x<0)
					intersect[2]=true;
				
			}
		
		}

		try// line AC
		{
			intersect[infinityCheck(AC_gradient, a)]=true;
		}
		catch (Exception e)
		{
			y=-AC_gradient*a.x+a.y;
			x=-y/AC_gradient;
			if ((a.y<=y && y<=c.y) || (a.y>=y && y>=c.y))//within range, and + or -
			{
				if (y>0)
					intersect[1]=true;
				else if (y<0)
					intersect[3]=true;
				
			}
			if ((a.x<=x && x<=c.x) || (a.x>=x && x>=c.x))//within range, and + or -
			{
				if (x>0)
					intersect[0]=true;
				else if (x<0)
					intersect[2]=true;
				
			}
		
		}
		
		for (int i=0; i<4; i++)
		{
			//System.out.println(i+" "+intersect[i]);
			if (!intersect[i])
				return false;
		}
		return true;
	}

	public static int infinityCheck(double gradient, Point a)
	{
		if (gradient==999 && a.x>0)
			return 0;
		if (gradient==999 && a.x<0)
			return 2;
		return 9;
	}


	public static void load() throws IOException
	{
		int count=0;
		File f = new File("triangles_Euler102.txt");
		FileReader fr = new FileReader(f);
		BufferedReader in  = new BufferedReader(fr);
		while (in.ready())
			file[count++] = in.readLine();
	}
}



class Point
{
	int x;
	int y;
	
	public Point(){}

	public Point(int x, int y)
	{
		this.x=x;
		this.y=y;
	}

	public String toString()
	{
		if (x==0 && y==0)
			return "Origin";
		return "("+x+", "+y+")";
	}

	public double dist(Point a, Point b)
	{
		return Math.sqrt(Math.pow((b.x-a.x),2)+Math.pow((b.y-b.x),2));
	}
	
	public static double gradient(Point a, Point b)
	{
		try
		{
			return (double)(b.y-a.y)/(double)(b.x-a.x);
		}
		catch (Exception e)
		{
			return 999;
		}
		
	}

	public String rectangleArea(Point a, Point b, Point c, Point d)
	{
		if (b.y-a.y==c.y-d.y && c.x-b.x==d.x-a.x)
			return ""+Math.round((b.y-a.y)*(c.x-b.x));
		return "It is not a rectangle";
	}
}