/*
Pr 12.2 Write a new program that will read in the previous file into an array. Then print the names to
the screen. Using the code above just add extra lines to print the names to the screen.
*/
import java.io.*;
public class zpr123 extends IBIO
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


