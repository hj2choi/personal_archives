import java.math.BigInteger;
class Euler048 extends HongJoonIDE	 //Catalan formula
{
	public static void main(String[] args) 
	{
		BigInteger aaa = new BigInteger("0");
		BigInteger ans = new BigInteger("1");
		BigInteger i= new BigInteger("0");
		BigInteger dfg = new BigInteger("1000");
		BigInteger one= new BigInteger("1");
		BigInteger ten= new BigInteger("10");
		System.out.println("Euler048");
		while (i.compareTo(dfg)<=0)	// for (int i=0;i<=2*n;i++)
		{
			System.out.println("Progress: "+i+"/1000");
			ans=ans.valueOf(1);
			BigInteger j = new BigInteger("1");
			while (j.compareTo(i)<=0)
			{
				ans=ans.multiply(i);
				j=j.add(one);
			}
			aaa=ans.add(aaa);
			i=i.add(one);
		}
		System.out.println(aaa);
	}
}
