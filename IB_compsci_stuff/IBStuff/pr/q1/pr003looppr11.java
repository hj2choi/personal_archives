public class pr003looppr11
{	public static void main (String[]args) 
	{


		java.util.Scanner s = new java.util.Scanner(System.in);
		System.out.println("enter number of times you want programs to repeat your name");
		int number = s.nextInt();
		System.out.println("enter your name(you cannot enter the space)");
		String name =  s.next();
		System.out.println("");
		for (int i = 0; i <5; i++)
		{	
			System.out.println(name);
		}
	}
}


/*
enter number of times you want programs to repeat your name
5
enter your name(you cannot enter the space)
hongjoon

hongjoon
hongjoon
hongjoon
hongjoon
hongjoon
*/