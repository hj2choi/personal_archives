public class Fraction extends IBIO
{
	public static void main (String args[])
	{
		FractionTest f = enter();
		FractionTest a = enter();
		FractionTest c = FractionTest.add(f,a);
		
		System.out.println(c);
		System.out.println("Decimal : "+c.toDec());
		
	}
	
	public static FractionTest enter()
	{ 
		String ss = input("enter fraction :");
		int a;
		int b;
		String[] s1 = ss.split("/");	
		a = Integer.parseInt(s1[0]);
		b = Integer.parseInt(s1[1]);
		FractionTest f1 = new FractionTest(a, b);
		return f1;
	}
}


