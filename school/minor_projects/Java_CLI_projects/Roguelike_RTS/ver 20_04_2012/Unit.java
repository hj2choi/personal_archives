class Unit extends DataBase
{
	private String name;
	private char indicativeChar;
	
	private int legNo;
	private int bodyNo;
	private int weaponNo;

	private int maxHp;
	private int hp;
	private int maxMp;
	private int mp;
	private String armType; // 1=normal, 2=armed, 3=mobile
	
	private int atk;
	private int acc;
	private int splash;
	private String wepType; // 1=plasma, 2=normal, 3=explosive
	private boolean atkAir;

	private boolean melee;//Melee or Support (give less load for support)
	private boolean air;
	private int maxLoad;
	private int stability;
	private int load;
	private int watt;

	private int[] upgradeLog;
	private int upgradeCount;
	Unit(){}
	Unit(String name, char indicativeName, int leg, int body, int weapon) // construct unit
	{
		this.name = name;
		this.indicativeChar=indicativeName;	
		this.legNo=leg;
		this.bodyNo=body;
		this.weaponNo=weapon;

		maxHp=bodyParts[body].getMaxHp();
		hp=maxHp;
		maxMp=bodyParts[body].getMaxMp();
		mp=maxMp;
		armType=bodyParts[body].getArmType();
		
		atk=weaponParts[weapon].getAtk();
		acc=weaponParts[weapon].getAcc();
		splash=weaponParts[weapon].getSplash();
		wepType=weaponParts[weapon].getWepType();
		atkAir=weaponParts[weapon].getAtkAir();

		melee=legParts[leg].getMelee();
		air=legParts[leg].getAir();
		maxLoad=legParts[leg].getMaxLoad();
		stability=legParts[leg].getStability();
		watt=legParts[leg].getWatt()+bodyParts[body].getWatt()+weaponParts[weapon].getWatt();
		load=+bodyParts[body].getLoad()+weaponParts[weapon].getLoad();
		
		upgradeLog = new int[5];
		upgradeCount = 0;

		if (load>maxLoad)
		{
			hp/=2;
			mp=0;
		}
	}
	public void copy(Unit original)
	{
		name=original.name;
		indicativeChar=original.indicativeChar;
		maxHp=original.maxHp;
		hp=original.hp;
		maxMp=original.maxMp;
		mp=original.mp;
		armType=original.armType; // 1=normal, 2=armed, 3=mobile
		
		atk=original.atk;
		acc=original.acc;
		splash=original.splash;
		wepType=original.wepType; // 1=plasma, 2=normal, 3=explosive
		atkAir=original.atkAir;

		melee=original.melee;//Melee or Support (give less load for support)
		air=original.air;
		//maxLoad;
		stability=original.stability;
		//load;
		watt=original.watt;

	}
	public void upgrade(int upgrade) //102 => upgrade no.1, 02%
	{
		upgradeLog[upgradeCount] = upgrade;
		if (upgrade!=0)
			upgradeCount++;
		if (upgrade/100==1)
			maxHp=(int)((double)maxHp*((double)(100+(upgrade%100))/100.0));
		else if (upgrade/100==2)
			atk=(int)Math.round((double)atk*((double)(100+(upgrade%100))/100.0));
		else if (upgrade/100==3)
			acc+=acc%100;
		else if (upgrade/100==4)
			stability+=1;
		else if (upgrade/100==5)
			maxLoad=(int)((double)maxLoad*((double)(100+(upgrade%100))/100.0));
		else if (upgrade/100==6)
			watt=(int)Math.round((double)watt*((double)(100-(upgrade%100))/100.0));

		if (load<=maxLoad)
		{
			hp=maxHp;
			mp=maxMp;
		}
	}
	
	public String toString()
	{
		if (hp<=0)
			return ".";
		return indicativeChar+"["+allignToMiddle(""+hp,"]",4);
	}
	public String hpBar()
	{
		if (hp<=0)
			return"       ";
		return scaleBar((double)hp/(double)maxHp,'*');
	}

	public String printInformation()
	{
		String ss;
		if (wepType.equals("-"))
		{
			return "\n";
			//ss =pad(allignToMiddle(name+" ("+indicativeChar+")", "|", 21)+allignToMiddle("","|",10)+allignToMiddle("","|",10), 50)+"\n";
			//ss+= allignToMiddle("", "|", 10)+allignToMiddle("", "|", 10)+allignToMiddle("", "|", 10)+allignToMiddle("", "|", 10)+allignToMiddle("", "|", 10)+allignToMiddle("", "|", 10)+allignToMiddle(""+(Melee?"":""), "|", 10)+allignToMiddle("", "|", 10)+allignToMiddle("", "|", 10);
		}
		else
		{
			ss =pad(allignToMiddle(name+" ("+indicativeChar+")", "|", 21)+allignToMiddle(watt+"W","|",10)+allignToMiddle(load+"/"+maxLoad+"kg","|",10), 50)+weaponParts[weaponNo].getName()+", "+bodyParts[bodyNo].getName()+", "+legParts[legNo].getName()+"\n";
			ss+= allignToMiddle(hp+"/"+maxHp, "|", 10)+allignToMiddle(mp+"/"+maxMp, "|", 10)+allignToMiddle(""+atk, "|", 10)+allignToMiddle(splash+"/s", "|", 10)+allignToMiddle(acc+"%", "|", 10)+allignToMiddle(""+stability, "|", 10)+allignToMiddle(""+wepType+(atkAir?"":"(g)"), "|", 12)+allignToMiddle(""+armType, "|", 9)+allignToMiddle(""+(melee?"Melee":"Support"), "|", 9);
		}
		return ss;
	}

	public String getName()
	{
		return name;
	}
	public int getMaxHp(){return maxHp;}
	public int getMaxMp(){return maxMp;}
	public int getHp(){return hp;}
	public int getMp(){return mp;}
	public String getArmType(){return armType;}
	public String getWepType(){return wepType;}
	public int getAtk(){return atk;}
	public int getAcc(){return acc;}
	public int getSplash(){return splash;}
	public boolean getAtkAir(){return atkAir;}
	public boolean getMelee(){return melee;}
	public boolean getAir(){return air;}
	public int getWatt(){return watt;}
	public char getIndicativeChar()
	{
		return indicativeChar;
	}
	public int getLegNo()
	{
		return legNo;
	}
	public int getBodyNo()
	{
		return bodyNo;
	}
	public int getWeaponNo()
	{
		return weaponNo;
	}
	public int[] getUpgradeLog()
	{
		return upgradeLog;
	}
	public int getUpgradeCount()
	{
		return upgradeCount;
	}

	public void deductHp(int damage)
	{
		int def=stability;
		if (damage-1<=def)
			def=damage-1;
		hp-=damage-def;
	}
	public void deductMp(int mpCost)
	{
		mp-=mpCost;
	}
	private String scaleBar(double percentage, char dot)
	{
		String bar = "";
		percentage = (int)(percentage*7);
		if (percentage<0)
			percentage=0;
		for (int i = 0;i<percentage ;i++ )
			bar = bar+dot;
		for (int i = 0;i<(7-percentage) ;i++ )
			bar = bar+"_";
		return bar;
	}
	
}






//=======================================Class Leg==========================================
class Leg
{
	private String name;
	private boolean melee;
	private boolean air;
	private int maxLoad;
	private int stability;
	private int watt;
	private int cost;

	Leg(boolean air,String name, boolean melee, int stability, int maxLoad, int watt, int cost)
	{
		this.name=name;
		this.melee=melee;
		this.air=air;
		this.stability=stability;
		this.maxLoad = maxLoad;
		this.watt=watt;
		this.cost=cost;
	}
	public String toString()
	{
		return pad("  <"+name+">", 18)+pad("type= "+(air?"air,":"ground,")+(melee?"melee":"support"),23)+pad("stability= "+stability,15)+pad("maxLoad= "+maxLoad,15)+pad("watt= "+watt,15)+"rec="+(air?1.7:1)*(melee?1:1.3)*(Math.pow(maxLoad,1.1)+stability*3);
	}
	public String getName()
	{
		return name;
	}
	public boolean getMelee()
	{
		return melee;
	}
	public boolean getAir()
	{
		return air;
	}
	public int getMaxLoad()
	{
		return maxLoad;
	}
	public int getStability()
	{
		return stability;
	}
	public int getWatt()
	{
		return watt;
	}
	public int getCost()
	{
		return cost;
	}
	private static String pad(String n, int tab)
	{
		do 
			n+=" ";
		while (n.length()<tab);
		return n;
	}
}

//=======================================Class Body==========================================
class Body
{
	private String name;
	private int maxHp;
	private int maxMp;
	private String armType;
	private int load;
	private int watt;
	private int cost;

	Body(String name, int maxHp, int maxMp, String armType, int load, int watt, int cost)
	{
		this.name=name;
		this.maxHp = maxHp;
		this.maxMp = maxMp;
		this.armType = armType;
		this.load = load;
		this.watt = watt;
		this.cost = cost;
	}
	public String toString()
	{
		return pad("  <"+name+">", 18)+pad("hp= "+maxHp,12)+pad("mp= "+maxMp,12)+pad("type= "+armType,15)+pad("weight= "+load,12)+pad("watt= "+watt,12)+"rec="+(((maxHp/3)-(load))*(double)(1.0+Math.pow((double)maxMp/30.0,0.05)));
	}
	public String getName()
	{
		return name;
	}
	public int getMaxHp()
	{
		return maxHp;
	}
	public int getMaxMp()
	{
		return maxMp;
	}
	public String getArmType()
	{
		return armType;
	}
	public int getLoad()
	{
		return load;
	}
	public int getWatt()
	{
		return watt;
	}
	public int getCost()
	{
		return cost;
	}
	private static String pad(String n, int tab)
	{
		do 
			n+=" ";
		while (n.length()<tab);
		return n;
	}
}

//=======================================Class Weapon==========================================
class Weapon
{
	private String name;
	private int atk;
	private int acc;
	private int splash;
	private String wepType;
	private boolean atkAir;
	private int load;
	private int watt;
	private int cost;

	Weapon(boolean atkAir, String name, int atk, int acc, int splash, String wepType, int load, int watt, int cost)
	{
		this.name = name;
		this.atk = atk;
		this.acc = acc;
		this.splash = splash;
		this.wepType = wepType;
		this.atkAir = atkAir;
		this.load = load;
		this.watt = watt;
		this.cost = cost;
	}
	public String toString()
	{
		return pad("  <"+name+">", 17)+pad("atk= "+atk,10)+pad("speed= "+splash,10)+pad("acc= "+acc,10)+pad("type= "+wepType,17)+pad("weight= "+load,12)+pad("watt= "+watt,10)+"rec="+((atkAir?1.2:1)*(atk)*Math.pow(splash,0.9)*(acc/100.0)*3-load);
	}
	public String getName()
	{
		return name;
	}
	public int getAtk()
	{
		return atk;
	}
	public int getAcc()
	{
		return acc;
	}
	public int getSplash()
	{
		return splash;
	}
	public String getWepType()
	{
		return wepType;
	}
	public boolean getAtkAir()
	{
		return atkAir;
	}
	public int getLoad()
	{
		return load;
	}
	public int getWatt()
	{
		return watt;
	}
	public int getCost()
	{
		return cost;
	}
	private static String pad(String n, int tab)
	{
		do 
			n+=" ";
		while (n.length()<tab);
		return n;
	}
}