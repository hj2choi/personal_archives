class Map extends DataBase
{
	private Area[][] area; //areas
	private int[] cursorLocation;
	private boolean cursorSelect;

	private int[] conditionScript;
	private String[] actionScript;
	private int missionObjective;
	private int expReward;
	private int moneyReward;




	Map() // construct map
	{
		cursorSelect=false;
		cursorLocation=new int[]{5,5};
		area =  new Area[10][10];
		for (int i=1;i<10 ;i++ )
			for (int j=1;j<10 ;j++ )
				area[i][j] = new Area();
		//occupiedBy=10;
	}
	public void executeCampaign(Player[] player ,int playerNumber, int initialWatt, int income, boolean[] AIcontrolled)
	{
		int turnElapsed=0;
		boolean victoryStatus=false;
		for (int i=1; i<=playerNumber; i++)
			player[i].enterBattleField(initialWatt,income);
		turnLoop : while (true)
		{
			turnElapsed++;
			for (int i=1;i<=playerNumber ;i++ )
			{
				//====trigger form====
				//if (player has "" in particular area in particular time)  : condition
				//execute AI script  : action




				player[i].earnWatt(additionalIncome(i)); // income
				printMap(i, player[i],AIcontrolled[i]);// move for player
				



				//====trigger form 2====
				//if () condition
				//break; + notify which array of player won
				//reward for player 1 if he wins
				if (eliminationStatusOf(1))
				{
					defeatScreen();
					break turnLoop;
				}
				victoryStatus=true;
				for (int j=2;j<=playerNumber ;j++ )// if other team is all eliminated
					if (!eliminationStatusOf(j))
					{
						victoryStatus=false;
						break;
					}
				if (victoryStatus)
				{
					victoryScreen();
					break turnLoop;
				}
			}
			
			
			
		}
		
	}
	public void printMap(int turn, Player player, boolean AIcontroled) //which player, and turn of who  // if player is controlled by AI agent, different rutine should be used. (No cursor)
	{
		int[] actionDone=new int[81];
		int actionCount=0;
		
//=========================================================Manually controlled turn======================================================================		
		while (!AIcontroled)
		{
			//System.out.println(area[5][6].getArmyInArea()[0]);
			//System.out.println(area[5][6].getArmySizeInArea());
			clearscreen();
			refreshField();

			for (int i=1;i<10 ;i++ )
			{
				for (int j=1;j<10 ;j++ )
				{
					System.out.print(((cursorLocation[0]==i&&cursorLocation[1]==j)?">":" ")+area[i][j]+"  ");
				}
				System.out.println("\n\n");
			}
			System.out.println("use [wasd] to move your cursor around. To select area, enter [1]. Enter [0] to end your turn");
			String input=s.next();
			enterWASD(input);
			if (input.equals("0"))
				break;
			if (input.equals("1")) //select area
			{
				int originalLocation = cursorLocation[0]*10+cursorLocation[1];

				if (area[cursorLocation[0]][cursorLocation[1]].getArmySizeInArea()>=0 && area[cursorLocation[0]][cursorLocation[1]].getOccupiedStatus()==turn )// if original place is occupied by player
				{
					Area originalArea = area[cursorLocation[0]][cursorLocation[1]];
					while (true)
					{
						System.out.println(((originalArea.getAreaProperty()==1 || originalArea.getAreaProperty()==2)?"Enter [1] to build unit, ":"")+"Enter [2] to view status. "+(!chkIfActionIsDone(originalLocation, actionDone)?"Enter [wasd] where you want your army advance to":""));
						input=s.next();
						try	// view status
						{
							int numInput=Integer.parseInt(input);
							if (numInput==2)
								originalArea.printAreaStatus();
							else if (numInput==1 && (originalArea.getAreaProperty()==1 || originalArea.getAreaProperty()==2))
							{
								clearscreen();
								MainMenu.player[turn].printUnitInfo();
								System.out.println("\nCurrent watt = "+MainMenu.player[turn].getWatt());
								while (true)
								{
									System.out.println("Choose unit to build. (1 to 9). Enter 0 to escape");
									int unitChoice = safeInput();
									if (unitChoice==0)
										break;
									if (MainMenu.player[turn].getConstructedUnit(unitChoice).getHp()>0 && MainMenu.player[turn].getWatt()>=MainMenu.player[turn].getConstructedUnit(unitChoice).getWatt())
										if ( countMeleeUnits(originalArea.getArmyInArea(), originalArea.getArmySizeInArea())<10 ||countSupportUnits(originalArea.getArmyInArea(), originalArea.getArmySizeInArea())<10) // if unit supply limit doesn't exceed
										{
											area[cursorLocation[0]][cursorLocation[1]].buildUnit(MainMenu.player[turn] ,unitChoice);
											MainMenu.player[turn].payWatt(MainMenu.player[turn].getConstructedUnit(unitChoice).getWatt());
											break;
										}
								}
								
								
							}
							
						}
						catch (Exception e){}
						break;
					}
					
					enterWASD(input);
					if (originalArea == area[cursorLocation[0]][cursorLocation[1]] || chkIfActionIsDone(originalLocation, actionDone))//chk if enterWASD() denied user input
						continue;
					if (area[cursorLocation[0]][cursorLocation[1]].getOccupiedStatus()==turn || area[cursorLocation[0]][cursorLocation[1]].getOccupiedStatus()==-1)  //if destination is occupied by player or neutral, if nothing is done on this turn
						if ( countMeleeUnits(originalArea.getArmyInArea(), originalArea.getArmySizeInArea())+countMeleeUnits(area[cursorLocation[0]][cursorLocation[1]].getArmyInArea(), area[cursorLocation[0]][cursorLocation[1]].getArmySizeInArea())<11 || countSupportUnits(originalArea.getArmyInArea(), originalArea.getArmySizeInArea())+countSupportUnits(area[cursorLocation[0]][cursorLocation[1]].getArmyInArea(), area[cursorLocation[0]][cursorLocation[1]].getArmySizeInArea())<11) // if unit supply limit doesn't exceed
						{
							System.out.println("test : "+cursorLocation[0]+cursorLocation[1]);
							area[cursorLocation[0]][cursorLocation[1]].modifyOccupyStatusTo(turn); // change occupication status
							area[cursorLocation[0]][cursorLocation[1]].reinforceWith(originalArea.getArmyInArea(), originalArea.getArmySizeInArea()); // overwrite into new area
							area[to2DLocation(originalLocation)[0]][to2DLocation(originalLocation)[1]].clearArmyInArea(); // delete original area
							actionDone[actionCount]=cursorLocation[0]*10+cursorLocation[1];
							actionCount++;
						}

					if (area[cursorLocation[0]][cursorLocation[1]].getOccupiedStatus()>=0 && area[cursorLocation[0]][cursorLocation[1]].getOccupiedStatus()!=turn ) // if destination is occupied by opponent, enter battle
					{
						System.out.println("battle");
						Battle battle = new Battle(originalArea.getArmyInArea(),originalArea.getArmySizeInArea(),area[cursorLocation[0]][cursorLocation[1]].getArmyInArea(), area[cursorLocation[0]][cursorLocation[1]].getArmySizeInArea());
						battle.battle();
						if (battle.aIsVictor())
						{
							area[cursorLocation[0]][cursorLocation[1]].defeatedBy(battle.getArmyA(), battle.getArmySizeA(), turn);// 
							area[to2DLocation(originalLocation)[0]][to2DLocation(originalLocation)[1]].clearArmyInArea(); // delete original area
							actionDone[actionCount]=cursorLocation[0]*10+cursorLocation[1];
							actionCount++;
						}
						else
						{
							area[cursorLocation[0]][cursorLocation[1]].defeatedBy(battle.getArmyB(), battle.getArmySizeB(), area[cursorLocation[0]][cursorLocation[1]].getOccupiedStatus());//remain same
							area[to2DLocation(originalLocation)[0]][to2DLocation(originalLocation)[1]].clearArmyInArea(); // delete original area
						}
					}
				}
			}
		}
//=======================================================AI controled turn======================================================================
		int[] AIcursor = new int[2];
		for (int i=1;i<=9 ;i++ )
		{
			for (int j=1;j<=9 ;j++ )
			{
				AIcursor[0]=i;
				AIcursor[1]=j;
				Area originalArea = area[AIcursor[0]][AIcursor[1]];
				int originalLocation = AIcursor[0]*10+AIcursor[1];
				if (area[i][j].getOccupiedStatus()==turn)
				{
					int[] selectedLocation = new int[]{i,j};
					AIcursor = AIenterWASD(moveToTarget(destination(), selectedLocation), AIcursor);// AI decision where to move unit
					if (originalArea == area[AIcursor[0]][AIcursor[1]] || chkIfActionIsDone(originalLocation, actionDone))//chk if enterWASD() denied user input
						continue;
					if (area[cursorLocation[0]][cursorLocation[1]].getOccupiedStatus()==turn || area[cursorLocation[0]][cursorLocation[1]].getOccupiedStatus()==-1)  //if destination is occupied by player or neutral, if nothing is done on this turn
					{
						System.out.println("test : "+AIcursor[0]+AIcursor[1]);
						area[AIcursor[0]][AIcursor[1]].modifyOccupyStatusTo(turn); // change occupication status
						area[AIcursor[0]][AIcursor[1]].reinforceWith(originalArea.getArmyInArea(), originalArea.getArmySizeInArea()); // overwrite into new area
						area[to2DLocation(originalLocation)[0]][to2DLocation(originalLocation)[1]].clearArmyInArea(); // delete original area
						actionDone[actionCount]=AIcursor[0]*10+AIcursor[1];
						actionCount++;
					}

					else if (area[AIcursor[0]][AIcursor[1]].getOccupiedStatus()>=0) // if destination is occupied by opponent, enter battle
					{
						System.out.println("battle");
						Battle battle = new Battle(originalArea.getArmyInArea(),originalArea.getArmySizeInArea(),area[AIcursor[0]][AIcursor[1]].getArmyInArea(), area[AIcursor[0]][AIcursor[1]].getArmySizeInArea());
						battle.battle();
						if (battle.aIsVictor())
						{
							area[AIcursor[0]][AIcursor[1]].defeatedBy(battle.getArmyA(), battle.getArmySizeA(), turn);// 
							area[to2DLocation(originalLocation)[0]][to2DLocation(originalLocation)[1]].clearArmyInArea(); // delete original area
							actionDone[actionCount]=AIcursor[0]*10+AIcursor[1];
							actionCount++;
						}
						else
						{
							area[AIcursor[0]][AIcursor[1]].defeatedBy(battle.getArmyB(), battle.getArmySizeB(), area[AIcursor[0]][AIcursor[1]].getOccupiedStatus());//remain same
							area[to2DLocation(originalLocation)[0]][to2DLocation(originalLocation)[1]].clearArmyInArea(); // delete original area
						}
					}
				}


			}

		}

	}
	
	public void enterWASD(String input)
	{
		
		if (input.equals("w") && cursorLocation[0]!=1 && area[cursorLocation[0]-1][cursorLocation[1]].getAreaProperty()!=-1)
			cursorLocation[0]--;
		if (input.equals("s") && cursorLocation[0]!=1 && area[cursorLocation[0]+1][cursorLocation[1]].getAreaProperty()!=-1)
			cursorLocation[0]++;
		if (input.equals("a") && cursorLocation[0]!=1 && area[cursorLocation[0]][cursorLocation[1]-1].getAreaProperty()!=-1)
			cursorLocation[1]--;
		if (input.equals("d") && cursorLocation[0]!=1 && area[cursorLocation[0]][cursorLocation[1]+1].getAreaProperty()!=-1)
			cursorLocation[1]++;
	}
	public int[] AIenterWASD(String input, int[] cursorLoc)
	{
		if (input.equals("w") && cursorLocation[0]!=1 && area[cursorLocation[0]-1][cursorLocation[1]].getAreaProperty()!=-1)
			cursorLoc[0]--;
		if (input.equals("s") && cursorLocation[0]!=1 && area[cursorLocation[0]+1][cursorLocation[1]].getAreaProperty()!=-1)
			cursorLoc[0]++;
		if (input.equals("a") && cursorLocation[0]!=1 && area[cursorLocation[0]][cursorLocation[1]-1].getAreaProperty()!=-1)
			cursorLoc[1]--;
		if (input.equals("d") && cursorLocation[0]!=1 && area[cursorLocation[0]][cursorLocation[1]+1].getAreaProperty()!=-1)
			cursorLoc[1]++;
		return cursorLoc;
		
	}
	public void refreshField()
	{
		for (int i=1;i<10 ;i++ )
			for (int j=1;j<10 ;j++ )
				area[i][j].chkUpdate();
	}
	public void defeatScreen()
	{
		clearscreen();
		System.out.println("You are defeated!!");
		System.out.println("Enter any key to continue...");
		s.next();
	}
	public void victoryScreen()
	{
		clearscreen();
		System.out.println("Victory!!");
		MainMenu.player[1].rewardWith(expReward, moneyReward, 1);
		System.out.println("You earned "+expReward);
		System.out.println("You arned $"+moneyReward);
		System.out.println("Enter any key to continue...");
		s.next();
	}
	public void statScreen()
	{
	
	}
	public int additionalIncome(int turn)
	{
		int inc=0;
		for (int i=1;i<10 ;i++ )
		{
			for (int j=1; j<10;j++ )
			{
				if (area[i][j].getOccupiedStatus()==turn && area[i][j].getAreaProperty()==1)
					inc+=200;
				if (area[i][j].getOccupiedStatus()==turn && area[i][j].getAreaProperty()==3)//mineField
					inc+=150;
			}
		}
		return inc;
	}
	public boolean chkIfActionIsDone(int location, int[]actionDone)
	{
		for (int i=0;i<81 ;i++ )
			if (actionDone[i]==location)
				return true;
		return false;
	}
	public int[] to2DLocation(int i)
	{
		int[] loc = new int[]{i/10,i%10};
		return loc;
	}
	public int countMeleeUnits(Unit[] army, int armySize)
	{
		int count=0;
		for (int i=0;i<armySize ;i++ )
		{
			if (army[i].getMelee())
				count++;
		}
		return count;
	}
	public int countSupportUnits(Unit[] army, int armySize)
	{
		int count=0;
		for (int i=0;i<armySize ;i++ )
		{
			if (army[i].getMelee())
				count++;
		}
		return count;
	}
	public boolean eliminationStatusOf(int playerNum)
	{
		for (int i=1;i<=9 ;i++ )
			for (int j=1;j<=9 ;j++ )
				if (area[i][j].getOccupiedStatus()==playerNum)
					return false;
		return true;

	}

	public void editCampaignMap(int[] setting, Player[] player)
	{
		int[]locSet;
		for (int i=0;i<100 ;i++ )
		{
			try
			{
				locSet=to2DLocation(setting[i]/10000);
				area[locSet[0]][locSet[1]].makeAreaUsable();
				area[locSet[0]][locSet[1]].setAreaProperty((setting[i]/1000)%10);
				area[locSet[0]][locSet[1]].modifyOccupyStatusTo((setting[i]/100)%10);
				for (int k =0;k<(setting[i]/10)%10 ;k++ )
					area[locSet[0]][locSet[1]].buildUnit(player[(setting[i]/100)%10], (setting[i]%10));
			}
			catch (Exception e){continue;}
		}

	}
	public void campaignTriggerSetting(int[] condition, String[] action, int objective, int expReward, int moneyReward) // 0 is default for everything
	{
		conditionScript=condition;
		actionScript=action;
		this.missionObjective=missionObjective;
		this.expReward=expReward;
		this.moneyReward=moneyReward;
		//set trigger
	}
//=====================================AI Calculations==========================================
	public double influenceMap(int playerNum, int playerLocation)
	{


		return 1;
	}
	public int[] destination()
	{
		int[] target = new int[2];
		target[0]=4;
		target[1]=4;

		return target;
	}
	public String moveToTarget(int[] target, int[] unitLocation)
	{
		int targetX = target[0];
		int targetY = target[1];
		int locX = unitLocation[0];
		int locY = unitLocation[1];
		String action = "";

		// starting from 4th quadrant, to right hand side.
		// basic movement decision table, if there is no abstraction
		if (targetX<locX && targetY<locY)
			action=Math.random()>0.5?"a":"w";
		else if (targetX>locX && targetY<locY)
			action=Math.random()>0.5?"w":"d";
		else if (targetX>locX && targetY>locY)
			action=Math.random()>0.5?"s":"d";
		else if (targetX<locX && targetY>locY)
			action=Math.random()>0.5?"a":"s";
		else if (targetX==locX && targetY<locY)
			action="w";
		else if (targetX>locX && targetY==locY)
			action="d";
		else if (targetX==locX && targetY>locY)
			action="s";
		else if (targetX<locX && targetY==locY)
			action="a";
		return action;
	}
	public double armyForce(Unit[] army, int armySize)
	{






		return 1;
	}
	public double conOverOppArmy(Unit[] armyA, int armySizeA, Unit[] armyB, int armySizeB)
	{







		return 1;
	}
	public double resourceState()
	{
		return 1;
	}
	
}