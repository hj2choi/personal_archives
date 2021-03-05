import java.io.*;
class Company extends DataBase
{
static int sortBy;
    Employee[] staff;
    int roll;
    String file;
    Company()
    {
	staff = new Employee[rollSize];
	roll = 0;
    }

    Company(String file)
    {
	staff = new Employee[rollSize];
	roll = 0;
	this.file = file;
    }

    //=========================================List===================================================
	public void list()
	{
		clearscreen();
		System.out.println("		   ========== List =========");
		System.out.println(allignToMiddle("name","|",30)+allignToMiddle(""+"wage","|",20)+allignToMiddle(""+"birth","|",20));
		System.out.println("--------------------------------------------------------------------------");
		for (int i = 0;i<roll ;i++ )
			System.out.println(staff[i]);
		System.out.println("\nThere are "+integerToWord(roll)+"staffs in the list");
		System.out.println("Enter any key to return...");
		s.next();
	}

//=========================================Add===================================================
	void add()
	{
		String input;
		clearscreen();
		System.out.println("		   ========== Add =========");
		do
		{
		    System.out.println("Enter staff name, wage and year of birth respectively. //quit to finish adding data.");
		    input = s.next();
			if (input.equals("//quit"))
				break;
			staff[roll] = new Employee(input, safeInput(), safeInput());
			roll++;
		} while (input.equals("//quit")==false);
		staff = sort(staff, roll, sortBy);
		System.out.println("Data was successfully added and sorted. Enter any key to return.");
		s.next();
	}

//=========================================Search===================================================
	void search()
	{
		clearscreen();
		int[] deleteList = new int[roll];
		int aa=0;
		System.out.println("		   ========== Search =========");
		printLine(3);
		System.out.println("Enter a name to search. Enter * to select all names.");
		int[] listFound = searchEngine(s.next(), staff, roll);
		System.out.println();
		for (int i=0;i<roll ;i++ )
		{
			if (listFound[i]==-1)
			{
				System.out.println("\n"+integerToWord(i)+" relevant names found.");
				break;
			}
			System.out.println(staff[listFound[i]]);
		}
		printLine(3);
//=========================================Edit/Delete===================================================
		opLoop : for (int i=0;i<roll ;i++ )		//Delete, Edit operation
		{
			
			if (listFound[i]==-1)
				break;
			System.out.println(staff[listFound[i]]);
			System.out.println("[1] Delete\t[2] Edit\t[3]Skip");
			switch (safeInput())
			{
			case 1 :
				deleteList[aa] = listFound[i];	//save delete information
				aa++;
				System.out.println("successfully deleted");
				break;
			case 2 :
				System.out.println("Change to? (Input name, wage and birth respectively)");
				staff[listFound[i]] = new Employee(s.next(),safeInput(),safeInput());		//edit
				break;
			case 3 :
				break;
			default:
				i--;
				continue opLoop;
			}
		}

		for (int i=0;i<aa ;i++ )		//delete
		{
			staff = delete(roll, deleteList[i]-i, staff);
			roll--;
		}

		staff = sort(staff, roll, sortBy);	//sort
		if (listFound[0]!=-1)
			System.out.print("Successfully edited and sorted. ");
		System.out.println("Enter any key to continue");
		s.next();
	}

//=========================================Statistics===================================================
	void statistics()
	{
		clearscreen();
		long sum=0;
		long stDevSum=0;
		long sqSum=0;
		for (int i=0;i<roll ;i++ )
			sum+=staff[i].getWage();
		for (int i=0;i<roll ;i++ )
			sqSum+=staff[i].getWage()*staff[i].getWage();
		for (int i=0;i<roll ;i++ )
			stDevSum+=(staff[i].getWage()-((double)sum/(double)roll)) * (staff[i].getWage()-((double)sum/(double)roll));
		stDevSum = (long)Math.sqrt((stDevSum)/(double)roll);

		System.out.println("		   ========== Statistics =========");
		printLine(3);
		System.out.println(allignedString("wage sum", "= "+sum, 20));
		System.out.println(allignedString("mean wage ", "= "+(long)((double)sum/(double)roll), 20));
		System.out.println(allignedString("root^2mean wage ", "= "+Math.round(Math.sqrt(sqSum/roll)), 20));
		System.out.println(allignedString("St.Dev wage", "= "+Math.round(stDevSum), 20));
		System.out.println("Enter anykey to return..");
		s.next();

	}

	void options()
	{
		clearscreen();
		int input;
		while (true)
		{
			clearscreen();
			System.out.print("[1] Sort by :");
			 if (sortBy==0){System.out.println("Name");}
			 if (sortBy==1){System.out.println("Wage");}
			 if (sortBy==2){System.out.println("Birth");}
			 System.out.print("[2]");
			 if (sortBy==0){System.out.println("Name");}
			 if (sortBy==1){System.out.println("Wage");}
			 if (sortBy==2){System.out.println("Birth");}
			 System.out.println("[0] Exit");
			input = safeInput();
			if (input == 1 && sortBy == 0)
				sortBy = 1;
			else if (input == 1 && sortBy == 1)
				sortBy = 2;
			else if (input == 1 && sortBy == 2)
				sortBy = 0;
			if (input==0)
				break;
		}
		
		staff = sort(staff, roll, sortBy);
	}

	void read() throws IOException		// returns array with loaded data of file (String filename)
	{
		try
		{
			File f = new File(this.file+".txt");
			FileReader fr = new FileReader(f);
			BufferedReader in  = new BufferedReader(fr);
			while (in.ready())
			{
				Employee aa = new Employee();
				aa.read(in);
				staff[roll] = aa;
				roll++;
			}
		}
		catch (Exception e)
		{
			System.out.println("Error while reading file! Caused by : "+e);
			System.exit(0);
		}
	}

	void save()
	{
		try
		{
			File ff = new File(this.file+".txt"); //create the file
			FileWriter fw = new FileWriter(ff);
			PrintWriter out = new PrintWriter(fw);
			for (int i = 0; i <= (roll-1); i++)
				staff[i].save(out); //out to the file
			out.close();
		}
		catch (IOException e)
		{
			System.out.println("Error Occured while saving the file.");
		}
	}

	public static void loadVariables(String fileName) throws IOException		// returns array with loaded data of file (String filename)
	{
		String[] file = new String[rollSize];
		int count=0;
		File f = new File(fileName);
		FileReader fr = new FileReader(f);
		BufferedReader in  = new BufferedReader(fr);
		while (in.ready())
		{
			file[count] = in.readLine();
			count++;
		}
		sortBy = Integer.parseInt(file[0]);
	}
	public static void saveVariables(String fileName) throws IOException
	{
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		PrintWriter out = new PrintWriter(fw);
		out.println(sortBy);
		out.close();
	}
}