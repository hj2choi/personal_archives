package hkust.project.acceleratingBalls;

public class Field {

	final static double scale = 3000;	// real = 12000
	
	static double gravity=0;		//metric scale
	static double fluidDensity=1;		// 1 as default
	static double friction = 0.1;
	static double unrealisticCollision = 1;	//from 1 to 0
	
	static double accX=0;
	static double accY=0;
	
	public Field()
	{
	}
	
	public static void tiltField(double x, double y)
	{
		accX=x/10;
		accY=y/10;
	}
}
