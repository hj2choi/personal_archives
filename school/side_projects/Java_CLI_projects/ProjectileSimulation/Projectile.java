class Projectile extends Field
{
// projectile properties (Assume it is a sphere)
	double radius;
	double mass;
	double dragCoeffiecient=0.47;//0.47 sphere, http://en.wikipedia.org/wiki/Drag_coefficient

//mechanic properties
	double x;
	double y;
	double velX;
	double velY;
	double accX;
	double accY;

	Projectile() //constructor
	{
		this.radius=1;
		this.mass=1;
		this.x=0;
		this.y=0;
		this.velX = 0;
		this.velY = 0;
		this.accX = 0;
		this.accY = -gravity;
	}
	Projectile(double radius, double mass, double x, double y)
	{
		this.radius = radius;
		this.mass = mass;
		this.x = x;
		this.y = y;
		this.velX = 0;
		this.velY = 0;
		this.accX = 0;
		this.accY = 0;
	}	

	public String toString()
	{ 
		return "("+(int)x+", "+(int)y+")  "+(int)velX+"m/s(x), "+(int)velY+"m/s(y)";
	}

	public void projectileMotion(double velX, double velY, double accX,double accY)//accepts horizontal, vertical speed
	{
		this.velX = velX;
		this.velY = velY;
		this.accX = accX;
		this.accY = accY-gravity;
	}
	public void projectileMotion(double vel, double angle, double accX, double accY, int a)	//accepts speed and throwing angle //int a has no meaning
	{
		this.velX = vel*Math.cos((angle*Math.PI)/180.0);
		this.velY = vel*Math.sin((angle*Math.PI)/180.0);
		this.accX = accX;
		this.accY = accY-gravity;
	}
	
	public void moveProjectileWithTime(int time) //in milliseconds (default = 1000ms)
	{
		
		//double[] timeToCollide = solveQuadraticEquation();
		double t = (double)time/1000.0;
		double netAccX = accX-dragForce(velX)/mass;
		double netAccY = accY-dragForce(velY)/mass;
		x+= velX*t+(netAccX*t*t)/2;//s=ut+1/2at^2
		y+=  velY*t+(netAccY*t*t)/2;
		velX+= netAccX*t;//v=u+at
		velY+= netAccY*t;
	}
	public Projectile collide(Projectile a, Projectile b)
	{
		return a;
	}
	public double timeToCollide(Projectile a, Projectile b)
	{
		
		return 1;
	}
	public double timeToReachGround()
	{
		double t = solveQuadraticEquation((accY-dragForce(velY)/mass)/2, velY,y);
		return t;
	}
	double dragForce(double velocity)//air resistance
	{
		if (velocity>0)
			return (1.0/2.0)*densityOfFluid*velocity*velocity*dragCoeffiecient*Math.PI*radius*radius;
		else if (velocity<0)
			return -(1.0/2.0)*densityOfFluid*velocity*velocity*dragCoeffiecient*Math.PI*radius*radius;
		else
			return 0;
		//air resistance formula //F(D) = 1/2pv^2*C(d)*A
	}

}

//F(D):drag force, p:density, C(d):drag coefficient, A:referenceArea(crossSectionalArea)=pi*r*r
//