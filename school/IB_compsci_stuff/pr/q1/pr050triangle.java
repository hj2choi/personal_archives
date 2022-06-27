class  pr50triangle
{
	public static void main(String[] args) /*throws Exception*/ 
	{
		String star = "";
		for(int a = 1; a < 20; a ++) 
		{
			for (int b = 1; b < a*2; b++) 
			{
				star+="*";
				System.out.println(star);
			}
		}
	}
}