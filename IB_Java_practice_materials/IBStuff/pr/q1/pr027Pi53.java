/*
Pr 5.3 Write a program that will add up the sequence discussed above to 10000 terms. Output 4
times the answer to get pi. (3.1414926535900345)
*/
public class pr027Pi53
{	public static void main (String []args)
	{
		double a = 3;
		double term = 1;
		double output = 1;
		int sign = -1;
		for (int i = 1 ; i < 10000 ; i++)
		{ 
			term = 1/a;
			output = output+(term*sign);
			a=a+2;
			sign = sign*-1;
		}
		System.out.println(output*4);
	}
}
/*
3.1414926535900345
*/