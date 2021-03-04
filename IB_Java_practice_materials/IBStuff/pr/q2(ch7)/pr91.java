/*
Write a program that will generate 100 random numbers from 1 to 6 as in the above
program and find the average of them.
*/
public class pr91 extends IBIO
{
	public static void main (String [] args)
	{
		double yy = 0;
		int i = 0;
		for (i = 0; i<100; i++)
		{
			double xx = (int)(Math.random()*6+1);		// random
			yy=yy+xx;
		}
		yy=yy/i;
		output(yy);
	}
}
/*
3.16
*/