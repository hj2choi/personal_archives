import java.io.*;
import java.util.*;
class Restaurant extends Utility
{
	Reservation[] myReservations;
	int reservationCount;
	String file;

	Restaurant()	//instanciate restaurant class
	{
		myReservations = new Reservation[1000];
		reservationCount = 0;
		file = "database";
	}

    //=========================================List===================================================
	public void list()
	{
		clearScreen();
		System.out.println("		   ========== List =========");
		System.out.println(allignMiddle("name",25)+"|"+allignMiddle("time",25)+"|"+allignMiddle("people",10)
		+"|"+allignMiddle("table",10)+"|"+allignMiddle("request",25)+"|");
		System.out.println("----------------------------------------------------------------------------------------------------");
		for (int i = 0;i<reservationCount; i++ )
			System.out.println(myReservations[i]);
		System.out.println("\nThere are "+reservationCount+" reservations in the list");
		System.out.println("table is allocated for 2 hours for sngle reservation");
		System.out.println("Enter any key to return...");
		s.next();
	}

    //=========================================Add===================================================
	public void add()
	{
		String name;
		int customer;
		Calendar cal = Calendar.getInstance();
		int table=0;
		
		clearScreen();
		System.out.println("		   ========== Add =========");

		// name
		System.out.println("Enter name of customer");
		name=s.next();

		// number of customer
		while (true)
		{
			System.out.println("Enter number of customers");
			try{customer=Integer.parseInt(s.next());}
			catch (Exception e){System.out.print("Error!! ");continue;}
			break;
		}

		//time
		while (true)
		{
			System.out.println("Enter Hour, Day, Month and Year of reservation. (Ex : HH/DD/MM/YYYY)");
			System.out.println("reservation can only be made at hours 11:00~14:00 and 17:00~22:00");
			String[] data = new String[4];
			data = (s.next()).split("/");
			try
			{
				cal.set(Integer.parseInt(data[3]), Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]), 0);
				if (!validateTime(Integer.parseInt(data[0])))
				{
					System.out.println("not available hour!");
					continue;
				}
			}
			catch (Exception e)
			{
				System.out.print("Error!");
				continue;
			}
			break;
		}

		// tableNumber
		do
		{
			while (true)
			{
				System.out.println("Enter tableNumber");
				try
				{
					table = Integer.parseInt(s.next());
				}
				catch (Exception e)
				{
					continue;
				}
				break;
			}
			if (!validateTable(table, cal.getTimeInMillis()))
				System.out.print("Error! table not available at that time! ");
		}
		while (!validateTable(table, cal.getTimeInMillis()));
		
		//additional request and instanciate reservation class
		System.out.println("Enter additionRequest");
		myReservations[reservationCount++] = new Reservation(name, cal.getTimeInMillis(), customer, table, s.next());
		
		sort();
		System.out.println("Data was successfully added and sorted. Enter any key to return.");
		s.next();
	}

//=========================================Search===================================================
	void search()
	{
		int[] deleteList = new int[reservationCount];
		int listCount=0;
		
		clearScreen();
		System.out.println("		   ========== Search =========");
		spaceLines(3);
		System.out.println("Enter a name to search. Enter * to select all names.");
		
		int[] searchList = smartSearch(s.next(), myReservations, reservationCount);

		System.out.println();
		for (int i=0;i<reservationCount ;i++ ) // count number of searched reservations
		{ 
			if (searchList[i]==-1) // if it is end of the searchlist, breaks loop
			{
				System.out.println("\n"+i+" relevant names found.");
				break;
			}
			//print out all searched names
			System.out.println(myReservations[searchList[i]]);
		}
		spaceLines(3);

//=========================================Edit/Delete===================================================
		//Delete, Edit operation for all searched reservations
		EditLoop : for (int i=0;i<reservationCount ;i++ )
		{
			if (searchList[i]==-1)
				break;
			
			System.out.println(myReservations[searchList[i]]);
			System.out.println("[1] Delete\t[2] Edit\t[3]Skip");
			
			switch (inputInt())
			{

			case 1 :
				deleteList[listCount] = searchList[i];	//add to delete list
				listCount++;
				System.out.println("successfully deleted");
				break;
			
			case 2 :
				case2Loop: while (true)
				{
					System.out.println("What do you want to change? (Enter any other key to escape)");
					System.out.println("[1] name      [2] Number of customers      [3] Date of reservation      [4] Table number      [5] Additional request");
					switch (inputInt())
					{
					//edit name
					case 1:
						System.out.println("Enter name of customer");
						myReservations[searchList[i]].setCustomerName(s.next());
						break;
					//edit number of ppl
					case 2:
						while (true)
						{
							System.out.println("Enter number of customers");
							try{myReservations[searchList[i]].setNumberOfCustomers(s.nextInt());}
							catch (Exception e){System.out.print("Error!! ");continue;}
							break;
						}
						break;
					//edit date
					case 3:
						Calendar cal = Calendar.getInstance();
						while (true)
						{
							System.out.println("Enter Hour, Day, Month and Year of reservation. (Ex : HH/DD/MM/YYYY)");
							String[] data = new String[4];
							data = (s.next()).split("/");
							try
							{
								cal.set(Integer.parseInt(data[3]), Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]), 0);
								if (!validateTime(Integer.parseInt(data[0])))
								{
									System.out.println("not available hour!");
									continue;
								}
							}
							catch (Exception e)
							{
								System.out.print("Error!");
								continue;
							}
							break;
						}
						myReservations[searchList[i]].setReservationTime(cal.getTimeInMillis());
						break;
					//edit tableNumber
					case 4:
						int table=0;
						do
						{
							while (true)
							{
								System.out.println("Enter tableNumber");
								try
								{
									table = Integer.parseInt(s.next());
								}
								catch (Exception e)
								{
									continue;
								}
								break;
							}
							if (!validateTable(table, myReservations[searchList[i]].getReservationTime()))
								System.out.print("Error! table not available at that time! ");
						}
						while (!validateTable(table, myReservations[searchList[i]].getReservationTime()));
						myReservations[searchList[i]].setTableNumber(table);
						break;
					//edit request
					case 5:
						System.out.println("Enter additional request");
						myReservations[searchList[i]].setAdditionalRequest(s.next());
						break;
					
					default:
						break case2Loop;
					}
				}
				break;
			
			case 3 :
				break;
			
			default:
				i--;
				continue EditLoop;
			}
		}

		for (int i=0;i<listCount ;i++ )		//delete all data in deleting list
		{
			myReservations = delete(deleteList[i]-i, myReservations);
			reservationCount--;
		}

		sort();	//sort
		if (searchList[0]!=-1)
			System.out.print("Successfully edited and sorted. ");
		System.out.println("Enter any key to continue");
		s.next();
	}

//========================================ReservationStatus==================================================
	void reservationStatus()
	{
		Calendar cal = Calendar.getInstance();
		String[] data = new String[4];
		long requiredTime=System.currentTimeMillis();
		int count=0;

		System.out.println();
		clearScreen();
		System.out.println("		   ========== Reservation Status =========");
		System.out.println("Enter date HH/DD/MM/YYYY to see reservations  at particular time.");
		System.out.println("Enter any other key to see currently reserved table.");
		
		data = (s.next()).split("/");
		try
		{
			cal.set(Integer.parseInt(data[3]), Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]), 0);
			requiredTime=cal.getTimeInMillis();
		}
		catch (Exception e)
		{
		}
		
		System.out.println(allignMiddle("name",25)+"|"+allignMiddle("time",25)+"|"+allignMiddle("people",10)+"|"+allignMiddle("table",10)+"|"+allignMiddle("request",25)+"|");
		System.out.println("----------------------------------------------------------------------------------------------------");
		for (int i=0; i<reservationCount; i++)
			if (requiredTime-myReservations[i].getReservationTime()<=36000000 && requiredTime-myReservations[i].getReservationTime()>=-36000000)	// check for time
			{
				System.out.println(myReservations[i]);
				count++;
			}
		System.out.println("\nThere are "+count+" reservations at that time");
		System.out.println("Enter any key to return...");
		s.next();
	}

//========================================deleteAllOutdatedReservation==================================================

	void deleteOutdatedReservations()
	{
		clearScreen();
		int[] deleteList = new int[1000];
		System.out.println("Are you sure to delete all outdated reservation? [1] yes [2] no");
		
		if (inputInt()==1)
		{
			long currentTime=System.currentTimeMillis();
			for (int i=reservationCount-1; i>=0; i--)
				if (myReservations[i].getReservationTime()<currentTime-3600000)	// delete outDated reservations
				{
					myReservations = delete(i, myReservations);
					reservationCount--;
				}
		}
		else
		{}
		
		sort();
		System.out.println("Successfully deleted. Enter any key to continue.");
		s.next();

	}

//=========================================Methods==================================================
  	boolean validateTable(int tableNumber, long reservationTime) // validate if table is empty or full at given time period
	{
		for (int i=0; i<reservationCount; i++)
			if (myReservations[i].getReservationTime()-reservationTime>-3600000 && myReservations[i].getReservationTime()-reservationTime<3600000)
				if (tableNumber==myReservations[i].getTableNumber())
					return false;
		return true;
	}

	boolean validateTime(int hour)
	{
		if ((hour>=11 && hour<=14)|| (hour>=17 && hour<=22))
			return true;
		return false;
	}

	public static int[] smartSearch(String ss, Reservation[] reservation, int roll) // searches all list which contains String ss. ("cba", "aaa", etc are found when ss="a")variable 'roll' is size of reservation array
	{
		if (ss.equals("*"))
			ss="";
		int[] list = new int[roll];
		for (int i=0;i<roll ;i++ )	 //initialize array
			list[i] = -1;
		int numberFound = 0;
		for (int i=0;i<roll ;i++ )
		{
			char[] disAssemble = reservation[i].getCustomerName().toCharArray(); //break String down into characters
			for (int k=0;k<reservation[i].getCustomerName().length() ;k++ )
			{
				String reAssembledString =""; // assemble String with few characters
				for (int j=k;j<(reservation[i].getCustomerName()).length() ;j++ )
					reAssembledString+=disAssemble[j];
				//System.out.println(reAssembledString);	// disable two comments to test algorithm
				if ((reAssembledString.toLowerCase()).startsWith(ss.toLowerCase())) // check if reAssembledString contains String ss
				{
					//System.out.println("					Found!!");
					list[numberFound]=i;
					numberFound++;
					break;
				}
			}
		}
		return list; // returns list of all names found
	}

	Reservation[] delete(int index, Reservation[] original) // overwrite all reservations into new array except for one that is being deleted
	{
		Reservation[] overwrite = new Reservation[10000];
		for (int i=0;i<index ;i++ )
			overwrite[i] = original[i];
		for (int j=index;j<reservationCount-1 ;j++ )
			overwrite[j] = original[j+1];
		return overwrite;
	}

	void sort()	//sort reservation according to time (lower to higher)
	{
		Reservation swap;
		for (int k=0;k<reservationCount ;k++ )
			for (int i=0;i<reservationCount ;i++ )
				if (myReservations[k].getReservationTime()<myReservations[i].getReservationTime())
				{
					swap = myReservations[k];
					myReservations[k] = myReservations[i];
					myReservations[i] = swap;		// swap two arrays
				}
	}

	void load() throws IOException		// returns array with loaded data of file (String filename)
	{
		try
		{
			File f = new File(this.file+".txt");
			FileReader fr = new FileReader(f);
			BufferedReader in  = new BufferedReader(fr);
			while (in.ready())
			{
				Reservation aa = new Reservation();
				aa.read(in);
				myReservations[reservationCount++] = aa;
			}
		}
		catch (Exception e)
		{
			System.out.println("Error while reading file! Caused by : "+e);
			System.exit(0);
		}
	}

	void save()		// save reservation data into textfile
	{
		try
		{
			File ff = new File(this.file+".txt"); //create the file
			FileWriter fw = new FileWriter(ff);
			PrintWriter out = new PrintWriter(fw);
			for (int i = 0; i < reservationCount; i++)
				myReservations[i].save(out); //out to the file
			out.close();
		}
		catch (IOException e)
		{
			System.out.println("Error Occured while saving the file.");
		}
	}
}