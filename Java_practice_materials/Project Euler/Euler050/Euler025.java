import java.math.BigInteger;
class Euler025
{
	public static void main(String[] args) 
	{
		BigInteger first = new BigInteger("0");
		BigInteger second = new BigInteger("1");
		BigInteger temp = new BigInteger("0");
		long count=2;
		System.out.println(first);
		while(true)							// Fibbonaci sequence
		{
			System.out.println(first.add(second));
			if ((""+(first.add(second))).length()>=1000)
			{
				System.out.println(first.add(second));
				System.out.println(count);
				return;
			}
			temp = first;
			first = second;
			second = temp.add(second);
			count++;
		}
	}
}
