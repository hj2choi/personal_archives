public class pr022switch41
{	public static void main (String[] args)
	{
		java.util.Scanner s = new java.util.Scanner(System.in);
		System.out.println("enter the number");
		long num1 = s.nextInt();
		System.out.println("enter the second number");
		long num2 = s.nextInt();
		int operation;
		long output=0;
		do
		{
			System.out.println("[1] for add");
			System.out.println("[2] for multiplication");
			System.out.println("[3] for quit");
			operation = s.nextInt();
			switch (operation)
			{
				case 1:
					output=num1+num2;
					break;
				case 2:
					output=num1*num2;
					break;
				case 3:
					return;
			}

		}
		while (operation>3 || operation < 1);
		System.out.println(output);

	}
}
/*
enter the number
556
enter the second number
312
[1] for add
[2] for multiplication
[3] for quit
2
173472
*/