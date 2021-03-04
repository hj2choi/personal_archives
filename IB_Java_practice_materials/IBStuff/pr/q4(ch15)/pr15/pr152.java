import java.io.*;
class pr152
{
	public static void main(String args[]) throws IOException
	{
		java.util.Scanner s = new java.util.Scanner(System.in);
		String[] ss = new String[10000];
		int count = 0;
		int numCurrent = -1;
		int input = s.nextInt();
		int inPut;
		File f = new File("sort.txt");
		File ff = new File("sort.txt");
		FileReader fr = new FileReader(f);
		BufferedReader in  = new BufferedReader(fr);
		FileWriter fw = new FileWriter(ff);
		PrintWriter out = new PrintWriter(fw);
		while (in.ready())
		{
			int numPrev = numCurrent;
			numCurrent = Integer.parseInt(in.readLine());
			if (input>numPrev && input<=numCurrent)
				inPut = input;
			out.println(numCurrent);
		}
		if (input>numCurrent)
			inPut = input;
		in.close();
		out.close();
	}
}