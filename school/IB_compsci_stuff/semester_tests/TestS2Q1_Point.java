/* ////////// /* /////////// /* //////////// /* ////////////// /*
		Programming Test Quarter 3 - Point Calc
		16.March.2012
*/ ////////// */ //////////// */ //////////// */ //////////// *
public class TestS2Q1_Point
{
	static java.util.Scanner s = new java.util.Scanner(System.in);

	static void p(String x)
	{	System.out.print(x);}
	static void pp(String x)
	{	System.out.println(x);}

	static int safeInput()
	{
		String input = s.next();
		try
		{
			return Integer.parseInt(input);
		}
		catch (Exception e)
		{
			return 0;
		}
	} 

	public static void main(String args[])
	{
		Point[] point = new Point[5];
		for (int i=0;i<5 ;i++ )
			point[i]=new Point();
		pp("Point Calculator\n^^^^^^^^^^^^^^^^^^^");
		for (int i=0;i<4 ;i++ )
		{
			pp("\nPoint "+(i+1));
			p("Enter x and y (ex: 0 10) :");
			point[i] = new Point(safeInput(),safeInput());
			pp("\nPoint object created");
			pp(""+point[i]);
			if (i==1)    
			{
				pp("\nSum = "+point[4].add(point[0],point[1]));
				pp("Distance between P1 and P2 = "+point[4].dist(point[0], point[1])+"\n\n\n\n");
				pp("Enter two additional points below");
			}
		}
		pp("\n\n\n\nRectangle area = "+point[4].rectangleArea(point[0],point[1],point[2],point[3]));
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

	public Point add(Point a, Point b)
	{
		Point sum = new Point((a.x+b.x), (a.y+b.y));
		return sum;
	}

	public double dist(Point a, Point b)
	{
		return Math.sqrt(Math.pow((b.x-a.x),2)+Math.pow((b.y-b.x),2));
	}

	public String rectangleArea(Point a, Point b, Point c, Point d)
	{
		if (b.y-a.y==c.y-d.y && c.x-b.x==d.x-a.x)
			return ""+Math.round((b.y-a.y)*(c.x-b.x));
		return "It is not a rectangle";
	}
}
	
	