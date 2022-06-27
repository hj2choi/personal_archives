class Field extends SimulationSystem
{
	/*
	Increase refreshRate and decrease playspeed for more accurate result.
	
	29.03.2012
	*/
//=============Field Properties==============
	final static double gravity = 9.8;
	final static double densityOfFluid =1.2;//1.2041;//1.2041 at T=293K, unit : kg/(m^3) -->http://en.wikipedia.org/wiki/Density_of_air
	
	final static double radiusInMeters = 0.12;
	final static double massInKg = 0.5;
	final static double initialXInMeters = 10;
	final static double initialYInMeters = 10;
	

	
	private static String[][] field = new String[yFieldSize][xFieldSize];

	public static void main(String args[])
	{
		clearField();
		long calcStartTime=0; //time taken to calculate(for more precise time control)
		byte print=0;
		Projectile ball = new Projectile(radiusInMeters, massInKg, initialXInMeters, initialYInMeters);
		// radius/m, mass/kg, x, y	//basketball:12cm, 500g //rain = 0.002, 0.000014 //terminal velocity of basketball : http://blog.naver.com/PostView.nhn?blogId=khj314270&logNo=110012426915

		ball.projectileMotion(30, 30, 0, 0,1);
	
		
		startTime = time(); //time1
		do
		{
			calcStartTime =time(); //time2 initialize
			
			ball.moveProjectileWithTime((int)(refreshPeriod*playSpeed)); //move ball

			if (!showTrajectory)
				clearField();
			printLine(5);
			System.out.println("Time estimated to reach ground = "+(double)((int)(ball.timeToReachGround()*100))/100);
			System.out.println(ball);	//print ball status
			drawBall(ball, ballShape);
			printField(); //print field
			
			delay((int)refreshPeriod-(int)(time()-calcStartTime)); //time tweaking //(exception may be caused if time taken to calculate is bigger than refresh delay)
		}
		while (ball.y>=0 && (time()-startTime)/1000<terminateProgramIn);

		getFieldStatus(ball, startTime);
	}

	public static void clearField()
	{
		for (int i=0;i<yFieldSize; i++ )
			for (int j=0;j<xFieldSize; j++ )
				field[i][j]=gridShape;
	}

	public static void printField()
	{
		
		for (int i=0;i<yFieldSize; i++ ) //print field with ball
		{
			for (int j=0;j<xFieldSize; j++ )
				System.out.print(field[i][j]+" ");
			System.out.println();
		}
	}
	public static void drawBall(Projectile object, String objShape)
	{
		try
		{field[yFieldSize-1-(int)(object.y/yScale)][(int)(object.x/xScale)]=objShape; //location of ball (exception may be caused)
		}
		catch (Exception e){}
	}
	public static void getFieldStatus(Projectile ball, long startTime)
	{
		System.out.println(((double)((time()-startTime)/10)/100)*playSpeed+"seconds elapsed");//print result if ball reaches ground
		System.out.println((int)ball.x+"m travelled. "+(int)ball.y+"m altittude. "+(int)Math.sqrt(ball.velY*ball.velY+ball.velX*ball.velX)+"m/s at "+(int)(Math.atan(ball.velY/ball.velX)*180/Math.PI)+" degrees");
	}
}