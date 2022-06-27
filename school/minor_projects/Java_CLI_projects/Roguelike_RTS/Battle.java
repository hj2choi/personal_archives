class Battle  extends DataBase// player A vs player B
{

	private Unit[] armyA;
	private int armySizeA;
	private int battleAILevelA; //0: random, 1: standard(does not change target until it is dead), 2:efficient (calculates effieicncy, attacks units which have low health), 3:intelligent (focus fire)

	private Unit[] armyB;
	private int armySizeB;
	private int battleAILevelB;

	private boolean aIsVictor=false;

	Battle(Unit[] ArmyA, int armySizeA, Unit[] ArmyB, int armySizeB)
	{
		this.armyA = new Unit[20];
		this.armyB = new Unit[20];
		for (int i=0;i<armySizeA ;i++ )
		{
			this.armyA[i] = new Unit();
			this.armyA[i].copy(ArmyA[i]);
		}
		this.armySizeA=armySizeA;
		for (int i=0;i<armySizeB ;i++ )
		{
			this.armyB[i] = new Unit();
			this.armyB[i].copy(ArmyB[i]);
		}
		this.armySizeB=armySizeB;

		this.battleAILevelA=1;
		this.battleAILevelB=1;
	}
	
	public void battle()
	{
		
		int atkDecision = 0;
		for (int b=0;b<30000;b++)
		{
			clearscreen();
			for (int i=0;i<armySizeA ;i++ )	// A turn
			{
				if (armyA[i].getHp()<=0)
					continue;
				if (( ((b%4)-armyA[i].getSplash()<=-1 && armyA[i].getSplash()!=2) || (armyA[i].getSplash()==2 && b%2==0) ) && random(0,100)<armyA[i].getAcc()) // attack speed with units' attack period (1~4)
				{
					try
					{
						if (armyA[i].getLastHit()<0 || battleAILevelA<1 || armyB[armyA[i].getLastHit()].getHp()<=0 || battleAILevelA>=3)
							atkDecision = decideWhichToAttack(armyA[i], armyB, armySizeB, battleAILevelA);
						else
							atkDecision = armyA[i].getLastHit();
					}
					catch (Exception e){atkDecision = decideWhichToAttack(armyA[i], armyB, armySizeB, battleAILevelA);}
					
					if (atkDecision==-2)
					{
						armyA[i].modifyLastHit(-1);
						continue;
					}
					AattackB(i, atkDecision);
					armyA[i].deductMp();
					armyA[i].modifyLastHit(atkDecision);
				}
			}
			for (int i=0;i<armySizeB ;i++ )	// B turn
			{
				if (armyB[i].getHp()<=0)
					continue;
				if (( ((b%4)-armyB[i].getSplash()<=-1 && armyB[i].getSplash()!=2) || (armyB[i].getSplash()==2 && b%2==0) ) && random(0,100)<armyB[i].getAcc()) // attack speed with units' attack period (1~4)
				{
					try
					{
						if (armyB[i].getLastHit()<0 || battleAILevelB<1 || armyA[armyB[i].getLastHit()].getHp()<=0  || battleAILevelB>=3)
							atkDecision = decideWhichToAttack(armyB[i], armyA, armySizeA, battleAILevelB);
						else
							atkDecision = armyB[i].getLastHit();
					}
					catch (Exception e){atkDecision = decideWhichToAttack(armyB[i], armyA, armySizeA, battleAILevelB);}
					
					if (atkDecision==-2)
					{
						armyB[i].modifyLastHit(-1);
						continue;
					}
					BattackA(i, atkDecision);
					armyB[i].deductMp();
					armyB[i].modifyLastHit(atkDecision);
				}
			}

			printBattlefield(armyB, armySizeB);
			System.out.println("---------------------------------------------------------------------------------------------------\n\n");
			printBattlefieldInReverse(armyA, armySizeA);
			if (chkDefeat(armyA, armySizeA))
			{
				System.out.println("B won!");
				aIsVictor=false;
				break;
			}
			if (chkDefeat(armyB, armySizeB))
			{
				System.out.println("A won!");
				aIsVictor=true;
				break;
			}
			delay(battleSpeed);//s.next();
		}
		armyA = reorganizeArmy(armyA, armySizeA);
		armyB = reorganizeArmy(armyB, armySizeB);
		armySizeA = survivorsCount(armyA, armySizeA);
		armySizeB = survivorsCount(armyB, armySizeB);


	}
//=======================================Export info==========================================
	public Unit[] getArmyA()
	{
		return armyA;
	}
	public Unit[] getArmyB()
	{
		return armyB;
	}
	public int getArmySizeA()
	{
		return armySizeA;
	}
	public int getArmySizeB()
	{
		return armySizeB;
	}
	public boolean aIsVictor()
	{
		return aIsVictor;
	}
//=======================================AI Functions==========================================
	public int decideWhichToAttack(Unit A, Unit[] B, int armySizeB, int AILevel) // Unit A decides which array in army B to attack (exclude attacking air and dead here)
	{
		boolean ceaseFire=true;
		for (int i=0;i<armySizeB ;i++ )// don't attack if ther is no target
			if (efficiency(A, B[i])!=0.01 && B[i].getHp()>0)
				ceaseFire=false;
		if (ceaseFire==true)
			return -2;
		
		// calculates for unit with minimum health and maximum attack, if AI level>=2
		if (AILevel>=2)
		{
			for (int i=0;i<armySizeB ;i++)
			{
				boolean min = true;
				for (int j=0;j<armySizeB ;j++ )
				{
					if (B[i].getHp()<=0 ||  (searchForMeleeUnit(B, armySizeB) && !B[i].getMelee()))
					{
						min=false;
						break;
					}
					if (B[i].getHp()-B[i].getAtk()*3>B[j].getHp()-B[j].getAtk()*3 && AILevel==2) // stronger the attack, weaker the health
						min=false;
					//if ()
					//{
					//}
				}
				if(min==true)
					return i;
			}
		}
		
		// if AI level<=2
		int selection=-2;
		boolean denySelection=true;
		while (denySelection)
		{
			selection = (int)random(0,armySizeB);
			denySelection=false;
			if (efficiency(A, B[selection])==0.01 || B[selection].getHp()<=0 || (searchForMeleeUnit(B, armySizeB) && B[selection].getMelee()==false))
				denySelection=true;
		}
		return selection;
	}
	public boolean searchForMeleeUnit(Unit[] army, int armySize) // search if any melee unit is alive in army
	{
		for (int i=0;i<armySize ;i++ )
		{
			if (army[i].getMelee()==true && army[i].getHp()>0)
				return true;
		}
		return false;
	}
//=======================================battle Functions==========================================
	public void printBattlefield(Unit army[], int armySize)
	{
		System.out.println(allignToMiddle("Army Strength score"+(int)Map.armyStrength(army, armySize),"",100));
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
	public void printBattlefieldInReverse(Unit army[], int armySize)
	{
		System.out.println(allignToMiddle("Army Strength score"+(int)Map.armyStrength(army, armySize),"",100));
		String row = "";
		for (int i=0;i<armySize ;i++ )		// unit name, status SUPPORT
			if (army[i].getMelee() == true )
				row+=pad(""+army[i], 10);
		System.out.println(allignToMiddle(row,"",100));
		row = "";
		for (int i=0;i<armySize ;i++ )		//unit hp bar
			if (army[i].getMelee() == true )
				row+=pad(""+army[i].hpBar(), 10);
		System.out.println(allignToMiddle(row,"",100)+"\n\n");


		row = "";
		for (int i=0;i<armySize ;i++ )		// unit name, status MELEE
			if (army[i].getMelee() == false )
				row+=pad(""+army[i], 10);
		System.out.println(allignToMiddle(row,"",100)+"");
		row = "";
		for (int i=0;i<armySize ;i++ )		//unit hp bar
			if (army[i].getMelee() == false )
				row+=pad(""+army[i].hpBar(), 10);
		System.out.println(allignToMiddle(row,"",100)+"\n\n");
	}
	public void AattackB(int i, int j) // unit A[i] attacks unit B[j]. 
	{
		armyB[j].deductHp((int)(efficiency(armyA[i], armyB[j])*(double)(armyA[i].getAtk())));
	}
	public void BattackA(int i, int j) // unit B[i] attacks unit A[j]. 
	{
		armyA[j].deductHp((int)(efficiency(armyB[i], armyA[j])*(double)(armyB[i].getAtk())));
	}

	public int survivorsCount(Unit[] army, int armySize) // chk how many units of existing army has survived so far
	{
		int count=0;
		for (int i=0;i<armySize ;i++ )
		{
			try
			{
				if (army[i].getHp()>0)
					count++;
			}
			catch (Exception e){break;}
		}
		return count;
	}
	public boolean chkDefeat(Unit[] army, int armySize) // chk if 0 unit survived (Optimized version of survivorsCount)
	{
		for (int i=0;i<armySize ;i++ )
			if (army[i].getHp()>0)
				return false;
		return true;
	}
	public Unit[] reorganizeArmy(Unit[] army, int armySize) // After battle
	{
		Unit[] organizedArmy= new Unit[20];
		int count=0;
		for (int i=0;i<armySize ;i++ )
		{
			try
			{
				if (army[i].getHp()>0)
				{
					organizedArmy[count]=new Unit();
					organizedArmy[count].copy(army[i]);
					count++;
				}
			}
			catch (Exception e)
			{
			}
			
		}
		return organizedArmy;
	}

//=======================================calculation Functions==========================================
	public static double efficiency(Unit A, Unit B) // efficiency of attack (A to B)
	{
		double eff=1;
		if ((A.getWepType().equals("plasma") && B.getArmType().equals("mobile")) || (A.getWepType().equals("explosive") && B.getArmType().equals("armed")))
			eff=1.4;
		if ((A.getWepType().equals("plasma") && B.getArmType().equals("armed")) || (A.getWepType().equals("explosive") && B.getArmType().equals("mobile")))
			eff=0.7;
		if (A.getAtkAir()==false && B.getAir()==true)
			eff=0.01;
		return eff;
	}
	public static double currentCon(Unit A, Unit B) //con of current unit A over current unit B
	{
		double con=(A.getAtk()*efficiency(A,B)*(A.getAcc()/100.0)-B.getStability())*A.getSplash()*B.getWatt();
		con/=B.getMaxHp()*A.getWatt();
		return con;
	}
	public static double con(Unit A, Unit B) //con of unit A over B
	{
		double con=(A.getAtk()*efficiency(A,B)*(A.getAcc()/100.0)-B.getStability())*A.getSplash()*B.getWatt();
		con/=B.getMaxHp()*A.getWatt();
		return con;
	}


}
