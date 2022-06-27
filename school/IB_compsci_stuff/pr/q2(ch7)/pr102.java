public class pr102 extends IBIO
{
	public static void main(String args[])
	{ 
		String bin = input("Input Binary number:");
		char[] xx = bin.toCharArray();
		int i=0;
		int dec = 0;
		int position = bin.length()-1;
		
		for (i=0;i<position;i++);
		{
			if (xx[i] != '1' && xx[i] != '0')					// chk if numbers are binary
				{
					output("error");
					return;
				}
				
		}
		
		for (int k = 0; k<bin.length();k++)			// binary conversion algorithm
		{
			int t=1;
			for (int a = bin.length()-1; a>k; a--)
			{
				t=t*2;
			}
			dec = dec+((int)xx[k]-48)*t;
		}
		output(dec);
	}
}


