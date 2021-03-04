import java.io.*;
class Simple extends DataBase
{
	
	public static void main(String args []) throws IOException
	{
		
		String input = "";

		java.util.Scanner s = new java.util.Scanner(System.in);
		//////////////////////********************Load Data********************//////////////////////
		System.out.println("Chapter16 : Project. HongJoon");
		System.out.println("Enter name of the file to load : ");
		Company company = new Company(s.next());
		company.read();
		try
		{
			company.loadVariables("dataBaseVariables.txt");
		}
		catch (Exception e)
		{
			company.sortBy = 0;
		}
		System.out.println("Lodaing Successful.");
		//////////////////////********************Main Menu********************//////////////////////
		MainMenuLoop : while (true)
		{ 
			clearscreen();
			System.out.println("		   ==========Main Menu=========\n");
			System.out.println("			[1]List");
			System.out.println("			[2]Add");
			System.out.println("			[3]Search");
			System.out.println("			[4]Statistics");
			System.out.println("			[5]Options");
			System.out.println("			[6]Quit");
			switch (firstLetterOf(s.next()))
			{
			case '1':
				company.list();
				break;
			case '2':
				company.add();
				break;
			case '3':
				company.search();
				break;
			case '4':
				company.statistics();
				break;
			case '5':
				company.options();
				break;
			case '6':
				break MainMenuLoop;
			default :
				break;
			}
		}
		company.save();
		company.saveVariables("dataBaseVariables.txt");	
	}
}

/*
    name       |        wage        |        birth       |
-------------------------------------------------------------------------
  Hongjoon  |         380        |         50         |
   Joonrock   |         380        |         50         |

*/