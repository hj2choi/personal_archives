/*
Pr 12.3 Write a new program that will read in the previous file into an array. Then allow you to add
one extra word (it is added at the end of the array) and then write it all back to the same file.
The extra word is added on the end of the array. The schema for that is below.
*/
import java.io.*;
public class zpr124 extends IBIO
{
	public static void main(String args[]) throws IOException
	{ 
		
		int count = 0;
		String[] names = new String[20];
		File ff = new File("temp.txt");
		FileReader fr = new FileReader(ff);
		BufferedReader in = new BufferedReader(fr);
		while (in.ready() )
		{ 
			//System.out.println(in.readLine());
			names[count] = in.readLine();
			//System.out.println(names[count]);
			count++;
		}
		in.close();
		int a = 0;
		for (a=0; a<count; a++)
			System.out.println(names[a]);
		for (int i = 0;i<a ;i++ )
		{
			String ss = input("enter extra word to each line:  ");
			names[i]=names[i]+ss;
		}
		FileWriter fw = new FileWriter(ff);
		PrintWriter out = new PrintWriter(fw);
		for (int i = 0; i < count; i++)
			out.println(names[i]); //out to the file
		out.close();
	}
}
/*
1st run:
11
22
abc
qwerty
33
enter extra word to each line:  1
enter extra word to each line:  2
enter extra word to each line:  3
enter extra word to each line:  4
enter extra word to each line:  5

2nd run:
111
222
abc3
qwerty4
335
enter extra word to each line:
*/

