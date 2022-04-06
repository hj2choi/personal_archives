import java.io.*;
class pr151
{
	public static void main(String args[]) throws IOException
	{
		String[] ss = new String[10000];
		int count = 0;
		java.util.Scanner s = new java.util.Scanner(System.in);
		File f = new File("temp");
		FileReader fr = new FileReader(f);
		BufferedReader in  = new BufferedReader(fr);
		while (in.ready())
		{
			ss[count] = in.readLine();
			count++;
		}
		in.close();
		File ff = new File("num10");
		FileWriter fw = new FileWriter(ff);
		PrintWriter out = new PrintWriter(fw);
		for (int i=0;i<count ;i++ )
		{
			out.println(Integer.parseInt(ss[i])+10);
		}
		out.close();
	}
}