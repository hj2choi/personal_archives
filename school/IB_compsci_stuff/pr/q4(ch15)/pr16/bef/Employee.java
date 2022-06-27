import java.io.*;
class Employee
{
	private String name;
	private double wage;
	private int birth;
	public Employee()
	{
		name  = "Name";
		wage = 0;
		birth = 1940;
	}

	public void save(PrintWriter xx) throws IOException
	{
		xx.println(name);
	}
	public void read(BufferedReader xx) throws IOException
	{
		name = xx.readLine();
	}
}
