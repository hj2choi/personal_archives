class Player extends DataBase
{
//========================================Player Variables, Units, Abilities==========================================

	private int level;
	private int exp;
	private int money;
	private int skill;

	private int incomeLevel;
	private int[] legOwn; // legParts that player own
	private int[] bodyOwn; //bodyParts that player own
	private int[] weaponOwn; // weaponParts that player own
	private Unit[] constructedUnit; // units that player constructed

//========================================insideGame Variables==========================================
	private int watt;
	private int income;
	private boolean[] occupiedArea; //true if occupied
	
	
	Player()//When player first start the game
	{
		legOwn = new int[50];
		bodyOwn = new int[50];
		weaponOwn = new int[50];
		constructedUnit = new Unit[10];
		level=1;
		money=30000;
		incomeLevel=1;
	}
	Player(int[] legOwn,int[] bodyOwn,int[] weaponOwn, Unit[] constructedUnit, int level, int money, int incomeLevel)//Load Player status
	{
		this.legOwn = legOwn;
		this.bodyOwn = bodyOwn;
		this.weaponOwn = weaponOwn;
		this.constructedUnit = constructedUnit;
		this.level=level;
		this.money=money;
		this.incomeLevel=incomeLevel;
	}

	
	public void editUnit(String name, char indicatedChar ,int category, Leg leg, Body body, Weapon weapon) // make new unit, (category 1~9)
	{
		constructedUnit[category] = new Unit(name, indicatedChar, leg, body, weapon);
	}
	public void buy(int part, int number, int cost)
	{
		if (part==1)
			legOwn[number]++;
		else if (part==2)
			bodyOwn[number]++;
		else if (part==3)
			weaponOwn[number]++;
		money-=cost;
	}
	public void useLeg(int number)
	{
		legOwn[number]--;
	}
	public void useBody(int number)
	{
		bodyOwn[number]--;
	}
	public void useWeapon(int number)
	{
		weaponOwn[number]--;
	}



//========================================IntoBattleField==========================================
	
	public void enterBattleField(int watt, int income, int startingArea)
	{
		this.watt=watt;
		this.income=income;
		this.occupiedArea = new boolean[100];
	}
	

	
//========================================Get Information==========================================
	public void printUnitInfo()
	{
		String ss =pad(allignToMiddle("name"+" (*)", "|", 21)+allignToMiddle("watt","|",10)+allignToMiddle("load"+"/"+"maxLoad","|",10), 50)+"weapon"+", "+"body"+", "+"leg"+"\n";
		ss+= allignToMiddle("hp"+"/"+"maxHp", "|", 10)+allignToMiddle("mp"+"/"+"maxMp", "|", 10)+allignToMiddle(""+"atk", "|", 10)+allignToMiddle("atkSpeed"+"/s", "|", 10)+allignToMiddle("acc"+"%", "|", 10)+allignToMiddle(""+"stability", "|", 10)+allignToMiddle("Placement", "|", 10)+allignToMiddle(""+"wepType", "|", 10)+allignToMiddle(""+"armType", "|", 10);
		System.out.println(ss+"\n\n---------------------------------------------------------------------------------------------------");
		for (int i=1;i<10 ;i++ )
		{
			try{System.out.println(getConstructedUnit(i).printInformation());
				System.out.println("---------------------------------------------------------------------------------------------------");}
			catch (Exception e){break;}
		}
	}
	public void printPlayerStats()
	{
		System.out.println(pad("",30)+"L.V: "+level);
		System.out.println(pad("",30)+"EXP: "+exp);
		//System.out.println("			"+hpb);
		System.out.println(pad("",30)+"Cash: $"+money);
		System.out.println("");
	}

	public Unit getConstructedUnit(int category)
	{
		return constructedUnit[category];
	}
	public int getMoney()
	{
		return money;
	}
	public int getLevel()
	{
		return level;
	}
	public int[] getLegOwn()
	{
		return legOwn;
	}
	public int[] getBodyOwn()
	{
		return bodyOwn;
	}
	public int[] getWeaponOwn()
	{
		return weaponOwn;
	}
//========================================Saving Functions==========================================
}
