/*
Write a program that will generate 100 random numbers from 1 to 6 as in the above
program. Then it will print them out. First all the 1¡¯s then all the 2¡¯s etc.
*/
public class pr93 extends IBIO
{
	static int random()
	{
			return (int)(Math.random()*6+1);
	}
	public static void main (String [] args)
	{
		int[] num = new int[100];
		
		for (int i = 0; i<100; i++)
			num[i] = random();
	
		for (int k=1;k<7;k++)		// chk num
		{
			output("");				// line
			for (int j = 0;j<100;j++)
				if(num[j]==k)
					out(num[j]);
		}
	}
}
/*
111111111
2222222222222
3333333333333333333333333
4444444444444
55555555555555555555
66666666666666666666
*/

