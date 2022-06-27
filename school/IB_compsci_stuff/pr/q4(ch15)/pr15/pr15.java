import java.io.*;
class pr15
{
	public static void main(String args[]) throws IOException
	{
		java.util.Scanner s = new java.util.Scanner(System.in);
		File f = new File("temp");
		FileWriter fw = new FileWriter(f);
		PrintWriter out = new PrintWriter(fw);
		do
		{ 
			System.out.println("enter a number (99 to finish) ");
			int num = s.nextInt();
			if (num == 99)
				break;
			String ss = "" + num; //change to string
			out.println(ss); //out to the file
		} while (true);
		out.close();
	}
}
/*
number : 1000
Enter number that you wish to find number of occurance.
36
Number of occurance of 36 : 6
Average : 50.215
0~9 : 102
10~19 : 80
20~29 : 102
30~39 : 103
40~49 : 106
50~59 : 103
60~69 : 100
70~79 : 102
80~89 : 100
90~99 : 102
*/