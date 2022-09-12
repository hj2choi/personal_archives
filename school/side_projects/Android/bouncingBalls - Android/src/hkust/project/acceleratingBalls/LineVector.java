package hkust.project.acceleratingBalls;

public class LineVector {
	
	double pointX;
	double pointY;
	
	double directionX;
	double directionY;
	
	
	public LineVector(double pointX, double pointY, double directionX, double directionY, double scale)
	{
		this.pointX=pointX/scale;
		this.pointY=pointY/scale;
		this.directionX=directionX/scale;
		this.directionY=directionY/scale;
	}
}
