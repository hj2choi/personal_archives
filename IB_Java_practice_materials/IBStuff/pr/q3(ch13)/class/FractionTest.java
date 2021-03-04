public class FractionTest
{
	static java.util.Scanner s = new java.util.Scanner(System.in);
	public static void main (String args[])
	{
		Fraction f1 = enter();
		Fraction f2 = enter();
		Fraction f3 = Fraction.add(f1,f2);
		System.out.println(f3);
		System.out.println(f3.toDec());
	}
	static Fraction enter()
	{
		System.out.println("Enter a fraction : ");
		String ss = s.next();
		String[] c = ss.split("/");
		Fraction f = new Fraction(Integer.parseInt(c[0]), Integer.parseInt(c[1]));
		return f;
	}
}


class Fraction
{
	private int num;
	private int den;
	public Fraction()
	{}
	public Fraction(int a, int b)
	{
		num=a;
		den=b;
	}
	public String toString()
	{
		String print=num+"/"+den;
		return print;
	}
	public static Fraction add(Fraction f1, Fraction f2)
	{
		Fraction ff = new Fraction();
		ff.den=f1.den+f2.den;
		ff.num=f1.num+f2.num;
		return ff;
	}
	public double toDec()
	{
		return (double)num/(double)den;
	}
}