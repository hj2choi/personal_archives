public class If extends IBIO
{
	public static void main(String[] args)
	{
		int n = inputInt("Enter an integer: ");
		// WARNING: NO ";" @ the end of the if and else/else if lines
		if(n > 0)
		{
			output( n + " is positive." );
		}
		else if (n < 0)		// this is checked if (n>0) is FALSE
		{
			output( n + " is negative." );
		}
		else				// this runs if (n>0) AND (n<0) were FALSE
		{
			output( n + " is zero." );
		}
	}
}

