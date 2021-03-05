/*
Change the program in 8.3 so that all the duplicates are removed (now 50 answers) and
also remove all multiples. 3,4,5 is one of the answers and we do not want 6,8,10 to also be
an answer (16 answers). Use the function gcd() created in Pr 8.4
*/
public class pr85 extends IBIO
{
	static boolean chk(int a,int b)		// method greatest common denominator
	{
		int x = (a*a)+(b*b);
		double y = Math.sqrt(x);
		return (x == ((int)y*(int)y));
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
	public static void main (String [] args)
	{
		for (int i = 1;i<100;i++)
		{
			for (int j=i;j<100;j++)
			{
				if (chk(i,j) && (gcd(i,j)==1))
					System.out.println(i+"^2 + "+j+"^2 = "+(int)(Math.sqrt((i*i)+(j*j)))+"^2");
			}
		}
	}
}
/*
3^2 + 4^2 = 5^2
5^2 + 12^2 = 13^2
7^2 + 24^2 = 25^2
8^2 + 15^2 = 17^2
9^2 + 40^2 = 41^2
11^2 + 60^2 = 61^2
12^2 + 35^2 = 37^2
13^2 + 84^2 = 85^2
16^2 + 63^2 = 65^2
20^2 + 21^2 = 29^2
20^2 + 99^2 = 101^2
28^2 + 45^2 = 53^2
33^2 + 56^2 = 65^2
36^2 + 77^2 = 85^2
39^2 + 80^2 = 89^2
48^2 + 55^2 = 73^2
60^2 + 91^2 = 109^2
65^2 + 72^2 = 97^2
*/