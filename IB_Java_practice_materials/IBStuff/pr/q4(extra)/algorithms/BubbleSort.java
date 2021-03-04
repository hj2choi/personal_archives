import java.io.*;

public class BubbleSort
{
	public static int[] list = new int[30000];
	public static int	index;

	public static void load() throws IOException
	{	// load FILE into ARRAY
		index = 0;
		File			ff = new File("Nums.txt");
		FileReader		fr = new FileReader(ff);
		BufferedReader	in = new BufferedReader(fr);
		while ( in.ready() )
		{	// read lines while file has content
			list[index] = Integer.parseInt( in.readLine() );
			index++;
		}
		in.close();
		System.out.println("File successfully loaded.");
	}
	//------------------------------------------------------------------
	
	public static void save() throws IOException
	{	// write/update the FILE
		File		ff	= new File("NumsSorted.txt");
		FileWriter	fw	= new FileWriter(ff);
		PrintWriter out = new PrintWriter(fw);
		for (int i = 0; i < index; i++)
			out.println( list[i] );
		out.close();
		System.out.println("File successfully saved.");
	}
	//------------------------------------------------------------------

	public static void bubbleSort()
	{	/** Bubble sort method */
    	boolean needNextPass = true;	// * Early exit bubble sort flag
    
		for (int k = 1; k < list.length && needNextPass; k++)
		{	// Array may be sorted and next pass not needed * see -^
			needNextPass = false;
			for (int i = 0; i < list.length - k; i++)
			{	if (list[i] > list[i + 1])
				{	// Swap list[i] with list[i + 1]
					int temp = list[i];
					list[i] = list[i + 1];
					list[i + 1] = temp;        
					needNextPass = true; // Next pass still needed
				}
			}
		}
	}

  /** A test method */
  public static void main(String[] args) throws IOException
  {
    load();
    bubbleSort();
	/*for (int i = 0; i < list.length; i++)
		System.out.print(list[i] + " ");
	*/
	save();
  }
}

/*
time java BubbleSort
File successfully loaded.
File successfully saved.

real	0m1.814s
user	0m1.940s
sys	0m0.000s
*/
