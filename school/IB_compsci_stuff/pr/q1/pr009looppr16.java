public class pr009looppr16 {
	public static void main (String[] args) {

		java.util.Scanner s = new java.util.Scanner(System.in);

		System.out.println("Fibbonaci sequence");					// 3 inputs
		System.out.println("Type the first number");
		int first = s.nextInt();
		System.out.println("type the second number");
		int second =  s.nextInt();
		System.out.println("enter number of terms");
		int term =  s.nextInt();
		int temp = 0;														// //3 inputs

		for (int a = 1;a<term ;a++ )							// Fibbonaci sequence
		{
			System.out.println(first+second);
			System.out.println("");
			temp = first;
			first = second;
			second = temp+second;
		}
		
	}
}

/*
D:\comp\java\pr>java pr009looppr16
Fibbonaci sequence
Type the first number
21
type the second number
33
enter number of terms
11
54

87

141

228

369

597

966

1563

2529

4092

*/