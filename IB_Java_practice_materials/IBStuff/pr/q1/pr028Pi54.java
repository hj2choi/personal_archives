/*
Pr 5.4 The sequence above is a much quicker way of calculating pi Write a program that will add
up the sequence above to 1000 terms. Output double the answer (3.1415926535897922)
*/
public class pr028Pi54
{	public static void main (String []args)
	{
		double numerator = 1;
		double denominator = 1;
		double output= 1;
		for (int i = 1;i<=150 ;i++ )
		{
			numerator = numerator*i;
			denominator = denominator*(i*2+1);
			output=output+(numerator/ denominator);
		}
			System.out.println(output*2);
	}
}
/*
3.1415926535897922
*/