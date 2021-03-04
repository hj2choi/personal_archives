package hkust.project.acceleratingBalls;

import java.util.Vector;

import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Ball{

	
	final static int memory=10000;
	static boolean realScale=true;
	
	double mass;	// in kg
	double radius;	// in meter
	
	double x;	// in meter
	double y;
	
	double velX;
	double velY;
	
	double accX;
	double accY;
	
	double impulseX;
	double impulseY;
	
	boolean PID=false;
	double targetY;
	double targetX;
	double prevX;
	double prevY;
	double iX=0;
	double iY=0;
	long lastDisplacementTime;
	
	ImageView imgBall;
	
	public Ball(double x, double y, double mass, double radius, ImageView ballId)
	{
		
		this.mass=mass;
		this.radius=radius;
		this.velX=0;
		this.velY=0;
		this.accX=0;
		this.accY=0;
		imgBall = ballId;
		
		setSize();
		initiateLocationInPixel(x,y);
		lastDisplacementTime=System.currentTimeMillis();
		display();
	}
	
	public void display()
	{
		imgBall.setX((int)(x*Field.scale-radius*Field.scale));
		imgBall.setY((int)(y*Field.scale-200-radius*Field.scale));
	}

	public void initiateLocationInPixel(double x, double y)
	{
		this.x=x/Field.scale;
		this.y=y/Field.scale;
	}
	
	public void displacement()
	{
		double t = (System.currentTimeMillis()-lastDisplacementTime)/1000.0;
		applyForce(t);
		
		lastDisplacementTime=System.currentTimeMillis();
		
		x+=velX*t+(accX*t*t)/2;//s=ut+1/2at^2
		y+=velY*t+(accY*t*t)/2;
		velX+=accX*t;//v=u+at
		velY+=accY*t;
	}
	
	public void PIDToLocation(int x, int y)
	{
		targetX=x/Field.scale;
		targetY=y/Field.scale;
	}
	
	public void applyForce(double t)
	{
		accX=Field.accX-dragForce(velX)/mass-friction(velX);
		accY=Field.accY-dragForce(velY)/mass-friction(velY);
		if (PID && t!=0)
		{
			double pX = targetX-this.x;//propportional
			double pY = targetY-this.y;//proportional
			double dX = (pX-prevX)/t;// differential
			double dY = (pY-prevY)/t;// differential
			prevX=pX;
			prevY=pY;
			
			iX+=pX*t;	//integral
			iY+=pY*t;	//integral 
			
			
			accX+=3*(pX+2*dX+iX);//PID to acc
			accY+=3*(pY+2*dY+iY);//PID to acc
			
			
		}
		else if (!PID)
		{
			iX=0;
			iY=0;
		}
		
		velX-=impulseX/this.mass;
		velY-=impulseY/this.mass;
		wallX();
		wallY();
		
	}
	
	public double dragForce(double velocity)
	{
		double F = (1.0/2.0)*Field.fluidDensity*velocity*velocity*0.4*Math.PI*radius*radius;
		if (velocity>0)
			return F;
		else if (velocity<0)
			return -F;
		else
			return 0;
	}
	
	public double friction(double velocity)
	{
		
		if (velocity>0)
			return Field.friction;
		else if (velocity<0)
			return -Field.friction;
		else
			return 0;
	}
	
	
	public boolean collision(Ball ext)
	{
		impulseX=0;
		impulseY=0;
		double dist = Math.sqrt((this.x-ext.x)*(this.x-ext.x)+(this.y-ext.y)*(this.y-ext.y));
		
		if (dist<(this.radius+ext.radius))
		{ 
			double normalRatioX = (ext.x-this.x)/dist;
			double normalRatioY = (ext.y-this.y)/dist;
			double relativeMomentumX = (ext.velX-this.velX)*ext.mass;
			double relativeMomentumY = (ext.velY-this.velY)*ext.mass;
			//MainActivity.txtTest.setText("("+normalRatioX+", "+normalRatioY+")"
			//		+"\n"+x+", "+y);
			
			this.impulseX=-Field.unrealisticCollision*(normalRatioX*relativeMomentumX);
			this.impulseY=-Field.unrealisticCollision*(normalRatioY*relativeMomentumY);
			if (Math.sqrt(this.velX*this.velX+this.velY*this.velY) < Math.sqrt(this.velX*this.velX+this.velY*this.velY))
			{
				ext.x=this.x-normalRatioX*(this.radius+ext.radius);
				ext.y=this.y-normalRatioY*(this.radius+ext.radius);
			}
			else
			{
				this.x=ext.x-normalRatioX*(this.radius+ext.radius);
				this.y=ext.y-normalRatioY*(this.radius+ext.radius);
			}
			
			return true;
		}
		return false;
	}
	
	public void wallX()
	{
		if (x*Field.scale<0 && velX<0){
			velX=-0.8*velX;
		}
		if (x*Field.scale>1900 && velX>0){
			velX=-0.8*velX;
		}
	}
	
	public void wallY()
	{
		if (y*Field.scale<250 && velY<0){
			velY=-velY;
		}
		if (y*Field.scale>1100 && velY>0){
			velY=-velY;
		}
	}
	
	public void setSize()
	{
		if (realScale==true)
		{
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int)(radius*Field.scale), (int)(radius*Field.scale));
			params = (android.widget.RelativeLayout.LayoutParams) imgBall.getLayoutParams();
			params.height=(int)((radius*2)*Field.scale*1);
			params.width=(int)((radius*2)*Field.scale*1);
			imgBall.setLayoutParams(params);
		}
	}
	
}
