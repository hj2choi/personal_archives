class SimulationSystem
{
//===================================Variables====================================
	final static double refreshRate = 2;// per second
	final static double refreshPeriod = (1/refreshRate)*1000.0;//milliseconds
	final static double playSpeed = 1;//1 : real life speed, bigger the value, faster
	final static double xScale =1.5; //scale represents how many meters does one dot exhibit
	final static double yScale = 1.5; 
	final static int xFieldSize=80;
	final static int yFieldSize=30;
	final static String gridShape = " ";
	final static String ballShape = "@";
	final static boolean showTrajectory = true;
	final static int terminateProgramIn=300;// terminate program after given seconds (in case of infinite loop)
	static long startTime;

//===================================Print/InputMethods====================================
	public static void printLine(int a)		//Spaces (int a) lines
	{
		for (int i=0;i<a ;i++ )
			System.out.println("");
	}

//===================================Thread/IO/etc...====================================
	public static void delay(int a)		//Stop Thread for (int a) MilliSeconds, 
	{
		try 
		{ 
			Thread.sleep((int)a);
		}
		catch (Exception e)
		{
			startTime-=a;
		}
	}

	public static long time()
	{
		return System.currentTimeMillis();
	}
//===================================Math methods====================================
	public static double solveQuadraticEquation(double a, double b, double c) // not for extended purpose(suvat eq only)
	{	
		return (-b-Math.sqrt(b*b-4*a*c))/(2*a);
	}
}