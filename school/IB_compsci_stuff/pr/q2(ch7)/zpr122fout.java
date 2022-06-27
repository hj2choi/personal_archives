/*
Pr 12.1 Change the last program so that the words are printed out.
*/
import java.io.*;
public class zpr122fout extends IBIO
{
	public static void main(String args[]) throws IOException
	{ 
		int count = 0;
		int[] names = new int[1000];
		for (int i=0; i<1000; i++)
			names[i]=(int)((Math.random()*99)+1);
		File ff = new File("temp.txt"); //create the file
		FileWriter fw = new FileWriter(ff);
		PrintWriter out = new PrintWriter(fw);
		for (int i = 0; i < 1000; i++)
			out.println(names[i]); //out to the file
		out.close();
	}
}
/*
enter some words 11
enter some words 22
enter some words abc
enter some words qwerty
enter some words 33
enter some words
11
22
abc
qwerty
33
*/
