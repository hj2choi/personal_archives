import java.io.*;
import java.util.Scanner;

public class BinarySearch
{
	public static int[] list = new int[30000];

	public static void load() throws IOException
	{	// load FILE into ARRAY
		int index = 0;
		File			ff = new File("NumsSorted.txt");
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

	//-----------------------------------------------------------------

	public static int binarySearch(int key)
	{
		int low = 0;
		int high = list.length - 1;
		
		while (high >= low)
		{
			int mid = (low + high) / 2;
			if (key < list[mid])
				high = mid - 1;
			else if (key == list[mid])
				return mid;
			else low = mid + 1;
		}
		return -1;
	}
	
	public static void main (String args[]) throws IOException
	{
		// ------------------------------------------------------------
		int firstArg = 0;		// code that accepts a command line
		if (args.length > 0)	// argument (args[]) and converts it
		{	try					// to an integer
			{	firstArg = Integer.parseInt(args[0]);
			}
			catch (NumberFormatException e)
			{	System.err.println("Argument" + " must be an integer");
				System.exit(1);
			}
		}
		int search = firstArg;
		// ------------------------------------------------------------
		
		load();
		
		// Below: keyboard input of the number to search----------
		// Scanner input = new Scanner( System.in );
		// System.out.print("Enter an integer to search for = ");
		// int search	= input.nextInt();
		// -------------------------------------------------------

		int found	= binarySearch( search );

		if( found >= 0 )
			System.out.println( search + " found at index " + found );
		else
			System.out.println( search + " NOT found." );

		// System.out.println(search + "\t" + firstArg + "\t" + found);
	}
}

/*	Sample runs on an AMD Phenom II 3.2GHz, 4GB RAM
	running Ubuntu 11.04 - the Natty Narwhal - released
	in April 2011 and supported until October 2012.

java BinarySearch 33339
File successfully loaded.
33339 found at index 19928

java BinarySearch 987
File successfully loaded.
987 NOT found.


java LinearSearch 33339
File successfully loaded.

33339 found at index 19928

time java LinearSearch 987
File successfully loaded.

987 NOT found.

*/
