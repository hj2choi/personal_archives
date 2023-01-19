import java.io.*;
class Euler067	extends HongJoonLibrary	//Spiral : Easiest I ever seen!!!!!
{
	public static void main(String[] args) throws IOException
	{
		String[] file = fileReader("Euler67_triangle.txt");
		String[][] triangle = new String[100][100];
		int[][] numbers = new int[100][100];
		for (int i=0; i<100; i++)
			triangle[i]=file[i].split(" ");
		for (int i=0; i<100; i++)
			for (int j=0; j<i+1; j++)
				numbers[i][j]=Integer.parseInt(triangle[i][j]);//prepare numbers for triangle
		

		for (int j=99; j>0	; j--)//algorithm to find maximum sum of triangle
			for (int i=0; i<j; i++)
			{
				if (numbers[j][i]>=numbers[j][i+1])
					numbers[j-1][i]+=numbers[j][i];
				else
					numbers[j-1][i]+=numbers[j][i+1];
			}

		System.out.println(numbers[0][0]);//print ans
	}

	public static String[] fileReader(String fileName) throws IOException		// returns array with loaded data of file (String filename)
	{
		int count=0;
		String[] file = new String[100];
		File f = new File(fileName);
		FileReader fr = new FileReader(f);
		BufferedReader in  = new BufferedReader(fr);
		while (in.ready())
			file[count++] = in.readLine();
		return file;
	}
}
