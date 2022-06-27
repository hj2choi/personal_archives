import java.io.*;
class Simple extends DataBase
{
	static int roll = 0;
	static String[] staff = new String[rollSize];
	static String input = "";
	static String file = "";
	public static void main(String args []) throws IOException
	{
		
		java.util.Scanner s = new java.util.Scanner(System.in);
		//////////////////////********************Load Data********************//////////////////////
		System.out.println("Chapter13 : Project. (Last edit 24.03.2012) HongJoon");
		System.out.println("Enter name of the file to load : ");
		file = s.next();
		read(file+".txt");
		System.out.println("Lodaing Successful.");
		//////////////////////********************Main Menu********************//////////////////////
		MainMenuLoop : while (true)
		{
			printLine(3);
			System.out.println("		   ==========Main Menu=========\n");
			System.out.println("			[L]ist");
			System.out.println("			[A]dd");
			System.out.println("			[S]earch");
			System.out.println("			[Q]uit");
			switch (firstLetterOf(s.next()))
			{
			case 'l':
				list();
				break;
			case 'a':
				add();
				break;
			case 's':
				search();
				break;
			case 'q':
				break MainMenuLoop;
			default :
				break;
			}
		}
		save(staff, file);
	}

//=========================================List===================================================
	public static void list()
	{
		printLine(3);
		System.out.println("		   ========== List =========");
		for (int i = 0;i<roll ;i++ )
			System.out.println(staff[i]);
		System.out.println("\nThere are "+integerToWord(roll)+"staffs in the list");
		System.out.println("Enter any key to return...");
		s.next();
	}

//=========================================Add===================================================
	public static void add()
	{
		printLine(3);
		System.out.println("		   ========== Add =========");
		System.out.println("Enter staff to add to the list.\nEnter '//quit' to finish adding data.");
		do
		{
			input = s.next();
			if (input.equals("//quit"))
				break;
			staff[roll] = input;
			roll++;
		} while (input.equals("//quit")==false);
		staff = sort(staff, roll);
		System.out.println("Data was successfully added and sorted. Enter any key to return.");
		s.next();
	}

//=========================================Search===================================================
	public static void search()
	{
		int[] deleteList = new int[roll];
		int aa=0;
		System.out.println("		   ========== Search =========");
		printLine(3);
		System.out.println("Enter a name to search.");
		int[] listFound = searchEngine(s.next(), staff, roll);
		System.out.println();
		for (int i=0;i<roll ;i++ )
		{
			if (listFound[i]==-1)
			{
				System.out.println("\n"+integerToWord(i)+" relavent names found.");
				break;
			}
			System.out.println(staff[listFound[i]]);
		}
		printLine(3);

		opLoop : for (int i=0;i<roll ;i++ )		//Delete, Edit operation
		{
			if (listFound[i]==-1)
				break;
			System.out.println(staff[listFound[i]]);
			System.out.println("[1] Delete\t[2] Edit\t[3]Skip");
			switch (safeInput())
			{
			case 1 :
				deleteList[aa] = listFound[i];
				aa++;
				System.out.println("successfully deleted");
				break;
			case 2 :
				System.out.print("Change to? : ");
				staff[listFound[i]]=s.next();
				break;
			case 3 :
				break;
			default:
				i--;
				continue opLoop;
			}
		}

		for (int i=0;i<aa ;i++ )
			staff = delete(roll, deleteList[i], staff);
		roll-=aa;
		staff = sort(staff, roll);
		System.out.println("Successfully edited and sorted. Enter any key to continue");
		s.next();
	}

	public static void read(String fileName) throws IOException		// returns array with loaded data of file (String filename)
	{
		try
		{
			File f = new File(fileName);
			FileReader fr = new FileReader(f);
			BufferedReader in  = new BufferedReader(fr);
			while (in.ready())
			{
				staff[roll] = in.readLine();
				roll++;
			}
		}
		catch (Exception e)
		{
			System.out.println("Error while reading file! Caused by : "+e);
			System.exit(0);
		}
	}

	public static void save(String[] staff ,String file)
	{
		try
		{
			File ff = new File(file+".txt"); //create the file
			FileWriter fw = new FileWriter(ff);
			PrintWriter out = new PrintWriter(fw);
			for (int i = 0; i <= (roll-1); i++)
				out.println(staff[i]); //out to the file
			out.close();
		}
		catch (IOException e)
		{
			System.out.println("Error Occured while saving the file.");
		}
	}
}
/*
Chapter13 : Project. (Last edit 24.03.2012)
Enter name of the file to load :
file
Lodaing Successful.



                   ==========Main Menu======

                        [L]ist
                        [A]dd
                        [S]earch
                        [Q]uit
l



                   ========== List =========
Abc
BBC
aBcd
qwertY
QWeRty
ghijk
qawsed
xyz
def

There are nine staffs in the list
Enter any key to return...
1



                   ==========Main Menu======

                        [L]ist
                        [A]dd
                        [S]earch
                        [Q]uit
s
                   ========== Search =======



Enter a name to search.
e

qwertY
QWeRty
qawsed
def

four  relavent names found.



qwertY
[1] Delete      [2] Edit        [3]Skip
2
Change to? : qwerty
QWeRty
[1] Delete      [2] Edit        [3]Skip
2
Change to? : abc
qawsed
[1] Delete      [2] Edit        [3]Skip
1
successfully deleted
def
[1] Delete      [2] Edit        [3]Skip
1
successfully deleted
Successfully edited and sorted. Enter any ke
1



                   ==========Main Menu======

                        [L]ist
                        [A]dd
                        [S]earch
                        [Q]uit
l



                   ========== List =========
Abc
BBC
aBcd
abc
ghijk
qwerty
xyz

There are seven staffs in the list
Enter any key to return...


*/