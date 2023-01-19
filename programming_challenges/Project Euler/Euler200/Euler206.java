import java.math.BigInteger;
class Euler206
{
	final static int searchRange = 1000000000*10;
	public static void main(String args[])
	{
		BigInteger x = new BigInteger("1");
		for (long i=1106000000;i<searchRange ;i++ )
		{
			x=x.valueOf(i);
			x=x.multiply(x);
			System.out.println(x);
		}
		System.out.println("a");
	}
}
/*
4620
9240
30030
39270

43890
46410
212520
300300
363090
*/