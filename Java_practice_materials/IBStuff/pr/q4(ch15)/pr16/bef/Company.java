import java.io.*;
class Company
{
	Employee[] staff;
	int roll = 0;
	String fileName;
	public Company(String ss)
	{
		staff = new Employee[100];
		roll = 0;
		fileName = ss;
	}

	
	public void read() throws IOException
	{
		File f = new File(fileName);
		FileReader xx = new FileReader(f);
		BufferedReader in  = new BufferedReader(xx);
		for (int i = 0; i < roll; i++)
		{ 
			Employee aa = new Employee();
			aa.read(in);
			staff[i] = aa;
		}
	}

	public void save() throws IOException
	{
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		PrintWriter out = new PrintWriter(fw);
		for (int i=0;i<roll ;i++ )
			staff[i].save(out);
	}
}
