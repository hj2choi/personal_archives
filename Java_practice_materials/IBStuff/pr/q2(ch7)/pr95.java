/*
Write a program that will read in a sequence of letters and print out the word and then the
word reversed and then the combination of both (note that the last letter is not repeated).
*/
public class pr95 extends IBIO
{
		public static void main(String args[])
		{ 
			String name = input("Input your name:");
			char[] xx = name.toCharArray();			// make it into array
			for (int i=0; i < name.length(); i++)
				out(xx[i]);
			output("");
			for (int j=(name.length()-1);j>=0;j--)
				out(xx[j]);
			output("");
			for (int k=0; k < name.length(); k++)	// reverse
				out(xx[k]);
			for (int l=((name.length())-2);l>=0;l--)		//d
				out(xx[l]);
					out("");
			
		}
}
/*
Input your name:HongJoon
HongJoon
nooJgnoH
HongJoonooJgnoH
*/

