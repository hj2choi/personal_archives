/*
Open the file that you have created above. Write a program to delete a word from the file. At
the beginning of the program enter one word and the program deletes that word from the
file. If that word is not in the list, then it is added to the file.
*/
import java.io.*;
public class zpr125 extends IBIO
{
	public static void main(String args[]) throws IOException
	{ 
		int count = 0;
		String[] names = new String[20];
		String word = "";
		boolean chk = false;
		int except = -1;
		File ff = new File("temp.txt");
		FileReader fr = new FileReader(ff);
		BufferedReader in = new BufferedReader(fr);
		while (in.ready() )									//read
		{ 
			names[count] = in.readLine();
			count++;
		}
		in.close();
		int a = 0;
		for (a=0; a<count; a++)
			System.out.println(names[a]);
		String ss = input("enter word you want to erase or to add:  ");
		for (int i = 0;i<a ;i++ )
			if (ss.equals(names[i]))						// check if there is any ss in the file
			{
		 		chk = true;
				except = i;
			}
		FileWriter fw = new FileWriter(ff);
		PrintWriter out = new PrintWriter(fw);
		for (int k = 0; k < except; k++)	
			out.println(names[k]);
		for (int j = except+1; j < count; j++)			//write except ss;
			out.println(names[j]);
		if (chk==false)							//write ss if there is no ss in the file
			out.println(ss);
		out.close();
	}
}
/*
1st run
111
222
qwerty4
335
enter word you want to erase or to add:  qwerty4

2nd run
111
222
335
enter word you want to erase or to add:  hongjoon
*/