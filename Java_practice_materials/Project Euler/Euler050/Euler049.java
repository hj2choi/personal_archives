class Euler049 extends HongJoonLibrary
{
	public static void main(String[] args) 
	{
		int num1;
		int num2;
		int num3;
		String n1;
		String n2;
		String n3;

		for (int increment=1; increment<4500;increment++ )
			for (int starting=1000;starting<10000 ;starting++ )
			{
				num1 = starting;
				num2 = starting+increment;
				num3 = starting+increment*2;
				n1=encryptNumArrangement(num1);
				n2=encryptNumArrangement(num2);
				n3=encryptNumArrangement(num3);
				
				
				if (primeCheck(num1) && primeCheck(num2) && primeCheck(num3) && num3<10000 && n1.equals(n2) && n1.equals(n3) && n2.equals(n3))
				{
					System.out.println(starting);
					System.out.println(starting+increment);
					System.out.println(starting+increment*2);
				}
			}
	}
}
