/*
 Consider the program below. It computes powers of the number 3.732 and prints them out.
Change the program so that all the answers are printed out to 2 decimal places only. Do this
by multiplying by 100, change to integer and then dividing by 100;
*/
public class pr71 extends IBIO
{
    public static void main (String args [] )
    {
		double xx = 1;
		String yy = "";
		for (int i = 0; i < 10; i++)
		{ 
			xx = xx * 3.732;
			xx = (double)((long)(xx*100))/100;	// 2 decimal places
			output(xx);
		}
    }
}
/*
3.73
13.92
51.94
193.84
723.41
2699.76
10075.5
37601.76
140329.76
523710.66
*/