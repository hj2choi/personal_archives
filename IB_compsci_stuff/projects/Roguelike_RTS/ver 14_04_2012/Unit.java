class Unit
{
	private String name = "Unit";
	private char indicativeChar = 'a';
	
	private String leg = "";
	private String body = "";
	private String weapon= "";

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

	private boolean Melee;//Melee or Support (give less load for support)
	private boolean air;
	private int maxLoad;
	private int stability;
	private int load;
	private int watt;

	Unit(String name, char indicativeName, Leg leg, Body body, Weapon weapon) // construct unit
	{
		this.name = name;
		this.indicativeChar=indicativeName;	
		this.leg=leg.getName();
		this.body=body.getName();
		this.weapon=weapon.getName();

		maxHp=body.getMaxHp();
		hp=maxHp;
		maxMp=body.getMaxMp();
		mp=maxMp;
		armType=body.getArmType();
		
		atk=weapon.getAtk();
		acc=weapon.getAcc();
		splash=weapon.getSplash();
		wepType=weapon.getWepType();
		atkAir=weapon.getAtkAir();

		Melee=leg.getMelee();
		air=leg.getAir();
		maxLoad=leg.getMaxLoad();
		stability=leg.getStability();
		watt=leg.getWatt()+body.getWatt()+weapon.getWatt();
		load=+body.getLoad()+weapon.getLoad();

		if (load>maxLoad)
		{
			hp/=2;
			atk/=2;
			acc/=2;
		}
	}
	
	public String toString()
	{
		return indicativeChar+" ["+allignToMiddle(""+hp,"]",4);
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
			ss =pad(allignToMiddle(name+" ("+indicativeChar+")", "|", 21)+allignToMiddle(watt+"W","|",10)+allignToMiddle(load+"/"+maxLoad+"kg","|",10), 50)+weapon+", "+body+", "+leg+"\n";
			ss+= allignToMiddle(hp+"/"+maxHp, "|", 10)+allignToMiddle(mp+"/"+maxMp, "|", 10)+allignToMiddle(""+atk, "|", 10)+allignToMiddle(splash+"/s", "|", 10)+allignToMiddle(acc+"%", "|", 10)+allignToMiddle(""+stability, "|", 10)+allignToMiddle(""+(Melee?"Melee":"Support"), "|", 10)+allignToMiddle(""+wepType, "|", 10)+allignToMiddle(""+armType, "|", 10);
		}
		return ss;
	}

	private static String allignToMiddle(String a, String c, int distance)	// " aaa ccc" if distance = 5
	{
		String ss = "";
		int space=distance-a.length();
		if (space%2!=0)
		    ss+=" ";
		for (int i=0;i<space/2 ;i++ )
		    ss+=" ";
		ss+=a;
		for (int i=0;i<space/2 ;i++ )
		    ss+=" ";
		return ss+c;
	}
	private static String pad(String n, int tab)
	{
		do 
			n+=" ";
		while (n.length()<tab);
		return n;
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
		return pad("  <"+name+">", 18)+pad("type= "+(air?"air,":"ground,")+(melee?"melee":"support"),23)+pad("stability= "+stability,15)+pad("maxLoad= "+maxLoad,15)+pad("watt= "+watt,15);
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
		return pad("  <"+name+">", 18)+pad("hp= "+maxHp,12)+pad("mp= "+maxMp,12)+pad("type= "+armType,15)+pad("weight= "+load,12)+pad("watt= "+watt,12);
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
		return pad("  <"+name+">", 17)+pad("atk= "+atk,10)+pad("speed= "+splash,10)+pad("acc= "+acc,10)+pad("type= "+wepType,17)+pad("weight= "+load,12)+pad("watt= "+watt,10);
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