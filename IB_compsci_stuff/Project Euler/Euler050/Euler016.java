import java.math.BigInteger;
class Euler016	 //Catalan formula
{
	public static void main(String[] args) 
	{
		BigInteger y = new BigInteger("1");	// y
		BigInteger one = new BigInteger("1");	// 1
		BigInteger two = new BigInteger("2");	// 2
		BigInteger ten = new BigInteger("10");		// 10
		BigInteger twoThousand = new BigInteger("1000");	//1000
		BigInteger i = new BigInteger("1");
		BigInteger ans = new BigInteger("0");
		while (i.compareTo(twoThousand)<=0)	// for (int i=0;i<=2*n;i++)
		{
			y=y.multiply(two);	// y*=i;
			i = i.add(one);
		}
		i = i.valueOf(1);
		while (i.compareTo(twoThousand)<=0)	// for (int i=0;i<=2*n;i++)
		{
			i = i.add(one);
			BigInteger[] remainder= y.divideAndRemainder(ten);
			ans = ans.add(remainder[1]);
			y=y.divide(ten);	
		}
		System.out.println(y);
		System.out.println(ans);
	}
}
