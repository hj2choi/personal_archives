class Area extends DataBase
{
//Library :                   �� �� �� �� �� 
	private Unit[] armyInArea; //ArmyInPaticularArea
	private int armySizeInArea;
	private int occupiedBy; // occupied by which player (-1: neutral, 0: AI, 1: player1)
	private int areaStatus; // get status of Area
	Area()
	{
		occupiedBy=-1;
		armyInArea = new Unit[20];
		for (int i=0;i<20 ;i++ )
			armyInArea[i]=new Unit();
		armySizeInArea = 0;
		areaStatus = -1;// -1:unUsed, 0:field, 1:main, 2:outPost, 3:minefield
		
	}
	public String toString()
	{
		String ss="";
		if (occupiedBy!=0 && occupiedBy!=-1) // occupied status
			ss+=" "+occupiedBy;
		else
			ss+="  ";

		if (armySizeInArea>15) // armySize
			ss+="";
		else if (armySizeInArea>10)
			ss+="@";
		else if (armySizeInArea>5)
			ss+="";
		else if (armySizeInArea>0)
			ss+="*";
		else
			ss+=" ";

		if (areaStatus==-1) //areaStatus
			ss+= "   ";
		else if (areaStatus==0)
			ss+= ".  ";
		else if (areaStatus==1)
			ss+= "�� ";
		else if (areaStatus==2)
			ss+= "�� ";
		else if (areaStatus==3)
			ss+= "  ";
		return ss;
	}
	public void printAreaStatus()
	{
		clearscreen();
		System.out.println(pad("",30)+"Occupied by player "+occupiedBy);
		System.out.print(pad("",30)+"Area status : ");
		if (areaStatus==0)
			System.out.println("Field");
		else if(areaStatus==1)
			System.out.println("Main Base");
		else if(areaStatus==2)
			System.out.println("Construction zone");
		else if(areaStatus==3)
			System.out.println("Power Generator");
		System.out.println("\n\n\n"+pad("",20)+"(Army status)\n\n\n\n");
		System.out.println("---------------------------------------------------------------------------------------------------");
		printBattlefield(armyInArea, armySizeInArea);
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("Enter any key to return...");
		s.next();

	}
	public void defeatedBy(Unit[] newArmy, int newArmySize, int playerNumber)
	{
		occupiedBy=playerNumber;
		armyInArea = newArmy;
		armySizeInArea = newArmySize;
	}
	public void clearArmyInArea()
	{
		//armyInArea = null;
		armySizeInArea = 0;
	}
	public void buildUnit(Player player, int choice) // build unit in game (haven't work out watt yet)
	{
		try
		{
			if (player.getConstructedUnit(choice).getHp()>0)
			{
				//dd yy=UnoptimizedDeepCopy.copy(xx);
				armyInArea[armySizeInArea]=new Unit();
				armyInArea[armySizeInArea].copy(player.getConstructedUnit(choice));
				//armyInArea[armySizeInArea]=player.getConstructedUnit(choice);
				armySizeInArea++;
			}
			else
				System.out.println("Error! couldn't achieve request!");	
		}
		catch (Exception e)
		{
		}
		
	}
	public void reinforceWith(Unit[] reinforcement, int reinforcementSize)
	{
		try
		{
			for (int i=0;i<reinforcementSize ;i++ )
			{
				armyInArea[armySizeInArea+i]=new Unit();
				armyInArea[armySizeInArea+i].copy(reinforcement[i]);
			}
			armySizeInArea+=reinforcementSize;
		}
		catch (Exception e)
		{
		}
		

	}
	public void chkUpdate() // give modifications when there is changes
	{
		if (armySizeInArea==0 && areaStatus!=-2 && areaStatus!=1 && areaStatus!=2 && areaStatus!=3)
			occupiedBy=-1;
	}
	public void modifyOccupyStatusTo(int i)
	{
		occupiedBy=i;
	}
	public void setAreaProperty(int i)
	{
		areaStatus=i;
	}
	public void makeAreaUsable()
	{
		areaStatus=0;
	}
	public int getOccupiedStatus()
	{
		return occupiedBy;
	}
	public int getAreaProperty()
	{
		return areaStatus;
	}
	public Unit[] getArmyInArea()
	{
		return armyInArea;
	}
	public int getArmySizeInArea()
	{
		return armySizeInArea;
	}
	public void printBattlefield(Unit army[], int armySize)
	{
		String row = "";
		for (int i=0;i<armySize ;i++ )		// unit name, status SUPPORT
			if (army[i].getMelee() == false )
				row+=pad(""+army[i], 10);
		System.out.println(allignToMiddle(row,"",100));
		row = "";
		for (int i=0;i<armySize ;i++ )		//unit hp bar
			if (army[i].getMelee() == false )
				row+=pad(""+army[i].hpBar(), 10);
		System.out.println(allignToMiddle(row,"",100)+"\n\n");


		row = "";
		for (int i=0;i<armySize ;i++ )		// unit name, status MELEE
			if (army[i].getMelee() == true )
				row+=pad(""+army[i], 10);
		System.out.println(allignToMiddle(row,"",100)+"");
		row = "";
		for (int i=0;i<armySize ;i++ )		//unit hp bar
			if (army[i].getMelee() == true )
				row+=pad(""+army[i].hpBar(), 10);
		System.out.println(allignToMiddle(row,"",100)+"\n\n");
	}
}
