/*Pr 12.1 Change the last program so that the words are printed out.*/
public class zpr121 extends IBIO
{
	public static void main(String args[])
	{ 
		int count = 0;
		String[] names = new String[20];
		do
		{
			names[count] = input("enter some words ");
			if (names[count].length() == 0)
				break;
			count++;
		} while (true);
		for (int i=0; i<count; i++)
			System.out.println(names[i]);
	}
}
/*
enter some words 123123
enter some words 333432
enter some words 112312
enter some words 445555
enter some words
123123
333432
112312
445555
*/
