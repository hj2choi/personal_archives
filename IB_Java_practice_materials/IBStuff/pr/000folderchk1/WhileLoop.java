public class WhileLoop extends IBIO
{
	public static void main(String[] args)
	{
		int counter = 1;	// Try changing the 1 to 11 =>
							// the while loop will not run at all!
		// WARNING: 	 -v- NO ";" @ the end of while line
		while(counter<=10)
		{
			output( "Pass #" + counter + ": is counter<=10? "+ (counter<=10) );
			counter++;		// same as counter = counter + 1;
		}
		output( "Exit loop. Variable counter = " + counter );
	}
}

/*
Pass #1: is counter<=10? true
Pass #2: is counter<=10? true
Pass #3: is counter<=10? true
Pass #4: is counter<=10? true
Pass #5: is counter<=10? true
Pass #6: is counter<=10? true
Pass #7: is counter<=10? true
Pass #8: is counter<=10? true
Pass #9: is counter<=10? true
Pass #10: is counter<=10? true
Exit loop. Variable counter = 11
*/