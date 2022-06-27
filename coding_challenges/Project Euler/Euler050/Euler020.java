import java.math.BigInteger;
class Euler020	 //Catalan formula
{
	public static void main(String[] args) 
	{
		BigInteger n = new BigInteger("50");	// n
		BigInteger y = new BigInteger("1");	// y
		BigInteger one = new BigInteger("1");	// 1
		BigInteger two = new BigInteger("2");	// 2
		BigInteger i = new BigInteger("1");
		BigInteger nTimesTwo = n.multiply(two);	 // 2n
		BigInteger nFactorial = new BigInteger("10");	//n!
		BigInteger ans = new BigInteger("0");
		while (i.compareTo(nTimesTwo)<=0)	// for (int i=0;i<=2*n;i++)
		{
			y=y.multiply(i);	// y*=i;
			i = i.add(one);
		}
		System.out.println(y);
		i = i.valueOf(1);
		while (i.compareTo(nTimesTwo.multiply(n))<=0) // for (int i=0;i<=n;i++)
		{
			i = i.add(one);
			BigInteger[] remainder= y.divideAndRemainder(nFactorial);
			ans = ans.add(remainder[1]);
			y=y.divide(nFactorial);	
		}
		System.out.println(ans);
	}
}
