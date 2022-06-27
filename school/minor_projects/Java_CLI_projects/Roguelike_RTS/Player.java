import java.io.*;
class Player extends DataBase
{
//========================================Player Variables, Units, Abilities==========================================

	private int level;
	private int exp;
	private int expMax;
	private int expIncrement;
	private int money;
	private int skill;
	private int campaignStage;
	//private int battleAILevel;

	private int incomeLevel;
	private int[] legOwn; // legParts that player own
	private int[] bodyOwn; //bodyParts that player own
	private int[] weaponOwn; // weaponParts that player own
	private Unit[] constructedUnit; // units that player constructed

//========================================insideGame Variables==========================================
	private int watt;// just for development stage
	private int income;
	private int baseLevel;
	
	Player()//When player first start the game
	{
		legOwn = new int[50];
		bodyOwn = new int[50];
		weaponOwn = new int[50];
		constructedUnit = new Unit[10];
		level=1;
		exp=0;
		expMax=100;
		expIncrement=50;
		money=300000;
		incomeLevel=1;
		for (int i=0;i<10 ;i++ )
			editUnit("--",'-',i,0,0,0);
		editUnit("armourGun",'a', 1, 1,1,1);//default unit
		campaignStage=1;
	}
	
	
	
	public void editUnit(String name, char indicatedChar ,int category, int leg, int body, int weapon) // make new unit, (category 1~9)
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
	public void pay(int cost)
	{
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
	public void rewardWith(int ex, int mon, int stageProgress)
	{
		exp+=ex;
		money+=mon;
		campaignStage+=stageProgress;
	}


//========================================IntoBattleField==========================================
	
	public void enterBattleField(int watt, int income)
	{
		this.watt=watt;
		this.income=income;
	}
	public void earnWatt(int additionalIncome)
	{
		watt+=income*(1+(((double)incomeLevel-1)/2.0))+additionalIncome;//if incomeLevel=1, income=100, level=2, income=150
	}
	

	
//========================================Get Information==========================================
	public void printUnitInfo()
	{
		String ss =pad(allignToMiddle("name"+" (*)", "|", 21)+allignToMiddle("watt","|",10)+allignToMiddle("load"+"/"+"kg","|",10), 50)+"weapon"+", "+"body"+", "+"leg"+"\n";
		ss+= allignToMiddle("hp"+"/"+"maxHp", "|", 10)+allignToMiddle("mp"+"/"+"maxMp", "|", 10)+allignToMiddle(""+"atk", "|", 10)+allignToMiddle("atkSpeed"+"/s", "|", 10)+allignToMiddle("acc"+"%", "|", 10)+allignToMiddle(""+"stability", "|", 10)+allignToMiddle("wepType", "|", 12)+allignToMiddle(""+"armType", "|", 9)+allignToMiddle(""+"Placement", "|", 9);
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
		System.out.println(pad("",30)+"EXP: "+exp+"/"+expMax);
		System.out.println("			"+statusBar((double)exp/(double)expMax,'*'));
		System.out.println(pad("",30)+"Cash: $"+money);
		System.out.println("");
	}

	public Unit getConstructedUnit(int category)
	{
		return constructedUnit[category];
	}
	public Unit[] getConstructedUnit()
	{
		return constructedUnit;
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
	public int getWatt()
	{
		return watt;
	}
	public int getCampaignStage()
	{
		return campaignStage;
	}
	public void payWatt(int cost)
	{
		watt-=cost;
	}
//========================================Saving Functions==========================================
	public void loadPlayer(String fileName) throws IOException
	{
		String[] read = readFile(fileName);
		for (int i=0;i<50 ;i++ )
			legOwn[i] = Integer.parseInt(read[i+1]);
		for (int i=0;i<50 ;i++ )
			bodyOwn[i] = Integer.parseInt(read[i+51]);
		for (int i=0;i<50 ;i++ )
			weaponOwn[i] = Integer.parseInt(read[i+101]);
		System.out.println("as");
		for (int i=1;i<=9 ;i++ )
		{
			String name=read[(i-1)*5+151];
			char[] a = read[(i-1)*5+152].toCharArray();
			char indicate = a[0];
			int legNo = Integer.parseInt(read[(i-1)*5+153]);
			int bodyNo =  Integer.parseInt(read[(i-1)*5+154]);
			int weaponNo =  Integer.parseInt(read[(i-1)*5+155]);
			constructedUnit[i] = new Unit(name, indicate, legNo, bodyNo, weaponNo);
		}
		level=Integer.parseInt(read[196]);
		exp=Integer.parseInt(read[197]);
		expMax=Integer.parseInt(read[198]);
		expIncrement=Integer.parseInt(read[199]);
		money=Integer.parseInt(read[200]);
		incomeLevel=Integer.parseInt(read[201]);
		for (int i=1;i<=9 ;i++ )
			for (int j=0;j<5 ;j++ )
				constructedUnit[i].upgrade(Integer.parseInt(read[(i-1)*5+j+202]));
		campaignStage=Integer.parseInt(read[247]);

	}
	
	public void save(String fileName)throws IOException
	{

		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		PrintWriter out = new PrintWriter(fw);
		for (int i=0;i<50 ;i++ )
			out.println(""+legOwn[i]);
		for (int i=0;i<50 ;i++ )
			out.println(""+bodyOwn[i]);
		for (int i=0;i<50 ;i++ )
			out.println(""+weaponOwn[i]);
		for (int i=1;i<=9;i++ )
		{
			out.println(constructedUnit[i].getName());
			out.println(constructedUnit[i].getIndicativeChar());
			out.println(constructedUnit[i].getLegNo());
			out.println(constructedUnit[i].getBodyNo());
			out.println(constructedUnit[i].getWeaponNo());
		}
		out.println(level);
		out.println(exp);
		out.println(expMax);
		out.println(expIncrement);
		out.println(money);
		out.println(incomeLevel);
		for (int i=1;i<=9 ;i++ )
			for (int j=0;j<5 ;j++ )
				out.println(constructedUnit[i].getUpgradeLog()[j]);
		out.println(campaignStage);
		out.close();

	}

	private String[] readFile(String fileName) throws IOException		// returns array with loaded data of file (String filename)
	{
		String[] file = new String[500];
		int count=1;
		File f = new File(fileName);
		FileReader fr = new FileReader(f);
		BufferedReader in  = new BufferedReader(fr);
		while (in.ready())
		{
			file[count] = in.readLine();
			count++;
		}
		return file;
	}
}
