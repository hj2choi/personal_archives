import java.io.*;
class pr14
{
	public static void main(String args[]) throws IOException
	{
		java.util.Scanner s = new java.util.Scanner(System.in);
		File f = new File("Num1000");
		FileReader fr = new FileReader(f);
		BufferedReader in = new BufferedReader(fr);
		int[] num = new int[10000];
		int count=0;
		while (in.ready())
		{
			String ss = in.readLine();
			num[count]=Integer.parseInt(ss);
			//System.out.println(num[count]);
			count++;
		}
		in.close();
		System.out.println("number : "+count);
		System.out.println("Enter number that you wish to find number of occurance.");
		int find = s.nextInt();
		int occur = 0;
		for (int i=0;i<count ;i++ )
			if (find==num[i])
				occur++;
		System.out.println("Number of occurance of "+find+" : "+occur);
		System.out.println("Average : "+Average(num, count));
		for (int j=0;j<100 ;j+=10 )
		{
			occur = 0;
			for (int k=0;k<count ;k++ )
				if (j<=num[k] && (j+10)>num[k] )
					occur++;
			System.out.println(j+"-"+(j+9)+" : "+occur);
		}
	}
	static double Average (int[] num, int count)
	{
		double sum = 0;
		for (int i=0;i<count ;i++ )
			sum+=num[i];
		sum=(double)((int)((sum/(double)count)*1000))/1000;
		return sum;
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