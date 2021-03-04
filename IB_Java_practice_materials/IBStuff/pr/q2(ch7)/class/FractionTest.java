import java.util.Scanner;

public class FractionTest extends IBIO
{
	public String toString()
	{
		int gcd=gcd(num,den);
		num = num/gcd;
		den = den/gcd;
		String ss = num+"/"+den;
		return ss;
	}
	FractionTest(int a,int b)
	{
		num = a;
		den = b;
		
	}
	static int gcd(int f, int g)			// gcd function
	{
		while (true)
		{
			if (f>g)
				f = f-g;
			if (g>f)
				g = g-f;
			if (g == f)
				break;
		}
		return g;
	}
	
	private int den;
	private int num;
	
	
	
	static FractionTest add(FractionTest a, FractionTest b)
	{
		int n = a.num*b.den+b.num*a.den;
		int d = a.den*b.den;
		FractionTest c = new FractionTest(n, d);
		return c;
	}
	static FractionTest dec(FractionTest a, FractionTest b)
	{
		int n = a.num*b.den+b.num*a.den;
		int d = a.den*b.den;
		FractionTest c = new FractionTest(n, d);
		
		return c;
	}
	
	public double toDec()
	{
		return 1.0 * num / den;
	}
	
	void print()
		{ output(num + "/" + den); }
		
	/*public int getDen()
	{
		return den;
	}
	*/
}

