public class ForLoop extends IBIO
{
	public static void main(String[] args)
	{			// WARNING: NO ";" @ the end of for line  -v-
		for(int counter=0; counter<=30; counter=counter+5)
		{
			output( "Pass #" + counter + ": is counter<=30? "+ (counter<=30) );
		}
		output( "Exit loop. counter<=30 is no longer true (condition is now false)" );
	}
}

/*
185-2:java m_drienvargas$ java Loop
Pass #0: is counter<=30? true
Pass #5: is counter<=30? true
Pass #10: is counter<=30? true
Pass #15: is counter<=30? true
Pass #20: is counter<=30? true
Pass #25: is counter<=30? true
Pass #30: is counter<=30? true
Exit loop. counter<=30 is no longer true (condition is now false)
*/