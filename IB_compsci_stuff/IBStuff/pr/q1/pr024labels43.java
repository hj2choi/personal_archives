public class pr024labels43 extends IBIO
{	public static void main (String []args)
	{
		int num = 0;
		boolean chk = false;
		int i=0;
		int j=0;

		do
			num = inputInt("enter a number less than 1000 : ");
		while (num>=1000);
		
		mainloop :
		for (i = 1;i<num ;i++ )
			for (j = 1;j<num;j++ )
				if (i*i+j*j==num)
				{
					chk=true;
					break mainloop;
				}
		
		if (chk)
			output(i+"   "+j);
		else
			output("impossible");
	}
}
/*
enter a number less than 1000
6565
enter a number less than 1000
656
16   20
*/
