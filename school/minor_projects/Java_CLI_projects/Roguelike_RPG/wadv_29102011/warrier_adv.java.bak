/***************************************************************************
									Hong Joon Choi ver 1.0.0
									Subject : Ing Warrier's Adventure
							Description :  3D MMORPG (Demolished Disturbed Destroyed Million Men`s Rarely Played Game)
								Platform : Command Prompt on Windows xp & Windows Vista & Windows 7
	Minimum Requirements : 8bit processor or better, 128kb free hard drive space, 20mb free memory space, java enabled
			Project Started Date : 26.10.2011
Date version 1.0 published : 29.10.2011
							All rights reserved
Usage of any of the contents inside without permission is not allowed
****************************************************************************/
/****************************************************************************
										Patch notes
***************************************************************************/

public class warrier_adv
{
	static boolean chk (double g)			// chk if either user or enemy's health is equal or below 0
	{
		return (g <= 0);
	}
	static String hpbar (double t)				// generate health, mana bar **** : max health, ____ : 0 health
	{
		String hbar = "";
		t = (int)(t*20);
		for (int e = 0;e<t ;e++ )
		{
			hbar = hbar+"*";
		}
		for (int kl = 0;kl<(20-t) ;kl++ )
		{
			hbar = hbar+"_";
		}
		return hbar;
	}
	public static void main (String args [] )
	{
		java.util.Scanner s = new java.util.Scanner(System.in);
		System.out.println("Adventure of Warrier ver 1.0.0");
		System.out.println("Developed by Hong Joon Choi");
		System.out.println("Enter your name to start the game(without space) no more than 8 letters!");
		String User = s.next();
		int extravariable = User.length();
		if (extravariable>8)
		{
			System.out.println("No more than 10 letters!! This program was force terminated.");
			System.out.println("Please restart the game");
			return;
		}
		/***************************************************************value initialization(starting stat)**********************************************************************/
		long starttime = System.currentTimeMillis();
		int increment = 20;
		int increment2 = 10;
		int UserOrder2 = 0;
		String anykey = "";
		int money = 500;		//fundamental
		int lv = 1;
		int exp = 0;
		int expmax = 10;
		int sa = 3;					//userstat
		int sb = 3;
		int sc = 3;
		int sd = 3;
		int[] wep_stat = new int[] {24, 18, 2,0,0,0,0,0,0,0};		//weapons(array) 0atk 1spatk 2def 3~6stat 7hp 8mp 9acc
		String wep = "Club";
		String arm = "Poor's clothes";
		String head = "none";
		int difficulty = 13;
		double skstab = 0;								// skills
	 	double skdoublestrike = 0;			//doublestrike
		double skheal = 0;					// heal
		double skdebuff = 0;
		double skpsionicstorm = 0;
		double skallin = 0;
		/***********************************************************************end of value initialization*********************************************************************/
		System.out.println("load?(5 for yes, other number for no.)");
		UserOrder2 = s.nextInt();
		if (UserOrder2 == 5)
		{
			System.out.println("input stat fundamentals");
			sa = s.nextInt();
			sb = s.nextInt();
			sc = s.nextInt();
			sd = s.nextInt();
			System.out.println("input level fundamentals");
			lv = s.nextInt();
			expmax = s.nextInt();
			increment = s.nextInt();
			increment2 = s.nextInt();
			System.out.println("input money fundamentals");
			money = s.nextInt();
			System.out.println("input skill");
			skstab = s.nextInt();
			skdoublestrike = s.nextInt();
			skheal = s.nextInt();
			skdebuff = s.nextInt();
		}
		MainGameLoop: while (true)
		{
		/*******************************************************************load fundamental statistics******************************************************/
			double str = sa+wep_stat[3];				//final stat
			double dex = sb+wep_stat[4];
			double intel = sc+wep_stat[5];
			double sta = sd+wep_stat[6];

			double hp = (long)((lv*lv/2) - lv + (sta*(sta/2)) + (str*str/6) + 50 + wep_stat[7]);				//hp.mp 
			double mp = (long)((lv*lv)/4 - lv + sta + (intel*((intel-2)/5))-(intel*(intel-3)/6)-(dex/5) + 20 + intel*2 + wep_stat[8]);
			double Gamehp = hp;			// hp.mp in battle
			double Gamemp = mp;

			double atkmax = (long)((str*(str/5))*(wep_stat[0]/20) + (dex*(dex/10)-1) + (sta/4) + ((wep_stat[0])/4)-str);		//battle statistics
			double atkmin = (long)((atkmax/4)+(dex*(dex+1)/8)*(wep_stat[0]/20)+(intel*intel/16));
			if (atkmin>atkmax)
				atkmin=atkmax;
			double spatk = (long)(intel*(intel/4))*(wep_stat[1]/20)+str/5+(dex*dex/12);
			double def = (long)(str/10+sta/3+wep_stat[2]+dex/10);
			double acc = 100-difficulty+dex/2+sta/5+intel/3+wep_stat[9];				// use with random(range = 0~100) (hit is determined when (num is 1~acc))

		/*******************************************************************end of loading fundamental satistics****************************************************/
			for (int k = 0;k<25 ;k++ )
				System.out.println("");
		/********************************************************************level up!!**********************************************************************/
			if (exp >= expmax)
			{
				lv++;
				exp = exp- expmax;
				expmax = increment;
				increment = increment+increment2;
				increment2 = increment2+3;
				System.out.println("Congrats!! You are now level "+lv+"!!");
				System.out.println("You can now spend 5 stat points");
				System.out.println("enter 1 for Strength, 2 for Agility, 3 for Intelligence,");
				System.out.println("4 for stamina.");
				System.out.println("");
				System.out.println("Strength: "+(int)(sa)+"   Agility: "+(int)(dex)+"   Intelligence: "+(int)(intel)+"    Stamina: "+(int)(sta));
				lvloop : for (int pt = 0;pt<5 ;pt++ )
				{
					UserOrder2 = s.nextInt();
					switch (UserOrder2)
					{
					default :
						pt--;
						continue lvloop;
					case 1 :
						sa++;
						System.out.println("Strength: "+(int)sa+"   Agility: "+(int)sb+"   Intelligence: "+(int)sc+"    Stamina: "+(int)sd);
						continue  lvloop;
					case 2 :
						sb++;
						System.out.println("Strength: "+(int)sa+"   Agility: "+(int)sb+"   Intelligence: "+(int)sc+"    Stamina: "+(int)sd);
						continue  lvloop;
					case 3 :
						sc++;
						System.out.println("Strength: "+(int)sa+"   Agility: "+(int)sb+"   Intelligence: "+(int)sc+"    Stamina: "+(int)sd);
						continue  lvloop;
					case 4 :
						sd++;
						System.out.println("Strength: "+(int)sa+"   Agility: "+(int)sb+"   Intelligence: "+(int)sc+"    Stamina: "+(int)sd);
						continue  lvloop;
					
					}

				}
				if (lv >=6)
				{
					sssloop : for (int pt = 0;pt<1 ;pt++ )
					{
						System.out.println("");
						System.out.println("");
						System.out.println("You can now spend one skill point.");
						System.out.println("Skills tab all skills heavily rely on special attack points.");
						System.out.println("2. Stab:	"+(int)skstab+"/"+"5"+"  deals additional damage to enemy.	("+(int)(117+skstab*3)+"%), mana:"+(46+((int)skstab*4)));
						System.out.println("");
						System.out.println("3. DoubleStrike:"+(int)skdoublestrike+"/"+"8"+"  hits enemy twice. Ignores defence	("+(int)(67+skdoublestrike*3)+"%), mana:"+(95+((int)skdoublestrike*5)));
						System.out.println("");
						System.out.println("4. Heal:	"+(int)skheal+"/"+"8"+"  heals yourself.			("+(int)(19+skheal*1.2)+"%), mana:"+(215+((int)skheal*15)));
						System.out.println("");
						System.out.println("5. Debuff:	"+(int)skdebuff+"/"+"8"+"  decreases enemy's stats.		("+(int)(9+skdebuff*1)+"%), mana:"+(260+((int)skdebuff*20)));
						System.out.println("");
						System.out.println("6. PsionicStorm:"+(int)skpsionicstorm+"/"+"10"+" critical damage and poisons enemy.	("+(int)(90+skpsionicstorm*12)+"%) mana:"+(430+((int)skpsionicstorm*20)));
						System.out.println("");
						System.out.println("7. Allin:	"+(int)skallin+"/"+"5"+"  costs half hp and all mp. Deals massive damage ("+(int)(280+skallin*20)+"%)");
						System.out.println("");
						System.out.println("8. Attribute:	increase all stat points by 1.");
						UserOrder2 = s.nextInt();
						switch (UserOrder2)
						{
						default :
							pt--;
							continue sssloop;
						case 2 :
							if (skstab <= 5)
								skstab++;
							else 
							{
								pt--;continue sssloop;
							}
							break;
						case 3 :
							if (skdoublestrike <= 8)
								skdoublestrike++;
							else 
							{
								pt--;continue sssloop;
							}
							break;
						case 4 :
							if (skheal <= 8)
								skheal++;
							else 
							{
								pt--;continue sssloop;
							}
							break;
						case 5 :
							if (skdebuff <= 8)
								skdebuff++;
							else 
							{
								pt--;continue sssloop;
							}
							break;
						case 6 :
							if (skpsionicstorm <= 10)
								skpsionicstorm++;
							else 
							{
								pt--;continue sssloop;
							}
							break;
						case 7 :
							if (skallin <= 5)
								skallin++;
							else 
							{
								pt--;continue sssloop;
							}
							break;
						case 8 :
							sa++;
							sb++;
							sc++;
							sd++;
							break;

						}

					}
				}
				System.out.println("You spent all stat and skill points. press any key to continue");
				anykey = s.next();
				continue MainGameLoop;
			}
			/*****************************************************end of level up system*************************************************************/
			/*********************************************************Main Menu***************************************************************/
			System.out.println("				Main Menu");
			for (int asd = 0;asd<3 ;asd++ )
				System.out.println("");
			System.out.println("			"+User+"'s Statistics :	[1]");
			System.out.println("			"+User+"'s Skill Tab  :	[2]");
			System.out.println("");
			System.out.println("			Enter Gear Shop :	[3]");
			System.out.println("");
			System.out.println("			Go to the battlefield :	[5]");
			System.out.println("");
			System.out.println("			Manual Save :		[8] ");
			System.out.println("			Game Information :	[9]");
			System.out.println("			Instruction manual :	[0]");
			for (int asds = 0;asds<3 ;asds++ )
				System.out.println("");



			/***************************************************************\\ main menu****************************************************************/
			int UserOrder = s.nextInt();
			/***************************************************************Developer's Tab****************************************************************/
			for (int k = 0;k<25 ;k++ )
				System.out.println("");
			switch (UserOrder)					
			{
				case 0:
					System.out.println("An year ago, there was a incredibly bored warrier called "+User);
					System.out.println("To amuse himself, he challenged Kerrigan,");
					System.out.println("who was universe-widely well known for her psionic powers, strength and ability.");
					System.out.println("However, of course, he was literally 'raped' by her.");
					System.out.println(User+" became so angry, and he decided to train himself and revenge her!!");
					System.out.println("");
					System.out.println("In this game, you have to raise your warrier and beat Kerrigan at last.");
					System.out.println("you can decide monster to fight. If you lose, you return to Main screen.");
					System.out.println("Don't worry about losing a game. You just lose quarter of your money.");
					System.out.println("you gain experience point and if it reach to a certain point, you will level up.");
					System.out.println("Then, you will be allowed to spend skill points and stat points to your warrier.");
					System.out.println("");
					System.out.println("strength highly contribute to attack and affect a bit on health and defence. ");
					System.out.println("Agility increases Overall stats except for mp hp. Accuracy heavity rely on it.");
					System.out.println("Intelligence heavily affect special attack.and mana. Also it help Accuracy to increase.");
					System.out.println("Stamina increases health, defence. Affect damage and mp by small amount.");
					System.out.println("");
					System.out.println("Good luck "+User+"! Good luck beating Kerrigan!");
					System.out.println("TIP: Be careful when you have to type number for doing certain action!!!!");
					System.out.println("Type any key to escape");
					anykey = s.next();
					continue MainGameLoop;
				case 9:
					long currenttime = System.currentTimeMillis();
					System.out.println("		It has been "+((currenttime-starttime)/60000)+" minitues since the game started!");
					System.out.println("		Playing this game is always an honor for me!!");
					System.out.println("				Thank You "+User+"!!");
					System.out.println("");
					System.out.println("");
					System.out.println("			Game Information");
					System.out.println("Subject :		Warrier's Adventure");
					System.out.println("Version :		1.0.0");
					System.out.println("Description :		3D MMORPG (Demolished Disturbed Destroyed Million Men`s");
					System.out.println("			Rarely Played Game)");
					System.out.println("");
					System.out.println("Platform :		Command Prompt on Windows xp & vista & 7");
					System.out.println("System Requirements :	8bit processor or better, 128kb hard drive space,");
					System.out.println("			20mb ram or better, java enabled");
					System.out.println("");
					System.out.println("ver 1.0 Published :	29.10.2011");
					System.out.println("");
					System.out.println("			All rights reserved");
					System.out.println("	 Usage of any of the contents without permission is not allowed");
					System.out.println("");
					System.out.println("enter any key to continue");
					anykey = s.next();
					continue MainGameLoop;

				case 8:
					System.out.println(sa+"  "+sb+"  "+sc+"  "+sd);
					System.out.println(lv+"   "+expmax+"  "+increment+"  "+increment2);
					System.out.println(money);
					System.out.println(skstab+"   "+skdoublestrike+"  "+skheal+"  "+skdebuff);
					anykey = s.next();
					continue MainGameLoop;
				
				case 486:
					System.out.println("God Mode initialized");
					sa = 128;
					sb = 128;
					sc =128;
					sd = 128;
					lv = 100;
					expmax = 200000;
					increment = 3000;
					increment2 = 200;
					money = 500000;
					skstab = 5;
					skdoublestrike = 8;
					skheal = 8;
					skdebuff = 8;
					skpsionicstorm = 10;
					continue MainGameLoop;

				/**************************************************************stats tab*************************************************************************/
				case 1 :	
					System.out.println("			"+User+"'s Statistics");
					System.out.println("");
					System.out.println("			L.V: "+lv);
					System.out.println("			EXP: "+exp+"/"+expmax);
					System.out.println("			Cash: $"+money);
					System.out.println("");
					System.out.println("Health Point:	"+(int)hp);
					System.out.println("Mana Point:	"+(int)mp);
					System.out.println("Attack:		"+(int)atkmin+" ~ "+(int)atkmax);
					System.out.println("Special Attack:	"+(int)spatk);
					System.out.println("Defence:	"+(int)def);
					System.out.println("Accuracy:	"+(int)acc);
					System.out.println("");
					System.out.println("Strength:	"+(int)str);
					System.out.println("Agility:	"+(int)dex);
					System.out.println("Intelligence:	"+(int)intel);
					System.out.println("Stamina:	"+(int)sta);
					System.out.println("");
					System.out.println("Weapon:	 	 "+wep);
					System.out.println("Armor:	 	 "+arm);
					System.out.println("HeadGear:	 "+head);
					System.out.println("");
					System.out.println("Type any key to escape");
					anykey = s.next();
					continue MainGameLoop;
				
				/***********************************************************skill tab*****************************************************************************/
				case 2 :	
					System.out.println("Skills tab all skills heavily rely on special attack points.");
					System.out.println("percentage data is not a reliable information.");
					System.out.println("");
					System.out.println("");
					System.out.println("2. Stab:	"+(int)skstab+"/"+"5"+"  deals additional damage to enemy.	("+(int)(117+skstab*3)+"%), mana:"+(46+((int)skstab*4)));
					System.out.println("");
					System.out.println("3. DoubleStrike:"+(int)skdoublestrike+"/"+"8"+"  hits enemy twice. Ignores defence	("+(int)(67+skdoublestrike*3)+"%), mana:"+(95+((int)skdoublestrike*5)));
					System.out.println("");
					System.out.println("4. Heal:	"+(int)skheal+"/"+"8"+"  heals yourself.			("+(int)(19+skheal*1.2)+"%), mana:"+(215+((int)skheal*15)));
					System.out.println("");
					System.out.println("5. Debuff:	"+(int)skdebuff+"/"+"8"+"  decreases enemy's stats.		("+(int)(9+skdebuff*1)+"%), mana:"+(260+((int)skdebuff*20)));
					System.out.println("");
					System.out.println("6. PsionicStorm:"+(int)skpsionicstorm+"/"+"10"+" critical damage and poisons enemy.	("+(int)(90+skpsionicstorm*12)+"%) mana:"+(430+((int)skpsionicstorm*20)));
					System.out.println("");
					System.out.println("7. Allin:	"+(int)skallin+"/"+"5"+"  costs half hp and all mp. Deals massive damage ("+(int)(280+skallin*20)+"%)");
					System.out.println("");
					System.out.println("8. Attribute:	increase all stat points by 1.");
					System.out.println(""); System.out.println(""); System.out.println("");
					System.out.println("Enter any key to escape"); anykey = s.next();;
					continue MainGameLoop;
					
				/********************************************************************shop tab**********************************************************************/
				case 3 :				
				{
					ShopLoop: while (true)
					{
						for (int y = 0;y<25 ;y++ )
							System.out.println("");
						System.out.println("welcome to Gear shop.");
						System.out.println("");
						System.out.println("");
						System.out.println("Weapon Shop:	[1]");
						System.out.println("");
						System.out.println("Armor Shop :	[2]");
						System.out.println("");
						System.out.println("HeadGear Shop : [3]");
						System.out.println("");
						System.out.println("Escape : [0]");
						System.out.println("");
						System.out.println("Your Cash: $"+money);
						UserOrder2 = s.nextInt();

						/***************************************************************Weapon Shop****************************************************************/
						if (UserOrder2 == 1)			//weapon
						{
							for (int vic = 0;vic<25 ;vic++ )
								System.out.println("");
							System.out.println("				Weapon Shop");
							System.out.println("");
							System.out.println("Difficulty: How difficult is weapon for warrier to meanuver it(Affects Accuracy)");
							System.out.println("Type number corresponding to the weapon to purchase it. Else, type 0 to escape.");
							System.out.println("");
							System.out.println("100.Club $500:		attack 24,    sp.attack 18,    difficulty 13");
							System.out.println("");
							System.out.println("101.Dagger $2000:	attack 32,    sp.attack 23,    difficulty 30");
							System.out.println("");
							System.out.println("102.QuarterStaff$2.5k:	attack 26,    sp.attack 26,    difficulty 17");
							System.out.println("");
							System.out.println("103.Shinai $5500:	attack 33,    sp.attack 25,    difficulty 21");
							System.out.println("");
							System.out.println("104.war bow $10k:	attack 38,    sp.attack 30,    difficulty 25");
							System.out.println("");
							System.out.println("105.BroadSword $16k:	attack 45,    sp.attack 36,    difficulty 36");
							System.out.println("");
							System.out.println("106.M1Garend $22k:	attack 46,    sp.attack 35,    difficulty 20");System.out.println("");
							System.out.println("107.OblivionStaff$33k:	attack 42,    sp.attack 50,    difficulty 45");System.out.println("");
							System.out.println("108.MithrillHammer$37k:	attack 53,    sp.attack 43,    difficulty 34");System.out.println("");
							System.out.println("109.M16A4 $60k:		attack 63,    sp.attack 48,    difficulty 40");
							UserOrder2 = s.nextInt();
							System.out.println("");
							switch (UserOrder2)
							{
							case 0:
								break;
							case 100:
								if (money>=500)
								{	money = money - 500;	 wep = "club"; wep_stat[0] = 24;	wep_stat[1] = 18; difficulty = 13;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;}
								break;
							case 101:
								if (money>=2000)
								{ money = money - 2000;	 wep = "Dagger"; wep_stat[0] = 32;	wep_stat[1] = 23; difficulty = 30;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;}
								break;
							case 102:
								if (money>=2500)
								{	money = money - 2500;	 wep = "QuarterStaff"; wep_stat[0] = 26;	wep_stat[1] = 26; difficulty = 17;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
							case 103:
								if (money>=5500)
								{	money = money - 5500;	 wep = "Shinai"; wep_stat[0] = 33;	wep_stat[1] = 25; difficulty = 21;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
							case 104:
								if (money>=10000)
								{	money = money - 10000;	 wep = "War Bow"; wep_stat[0] = 38;	wep_stat[1] = 30; difficulty = 25;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
							case 105:
								if (money>=16000)
								{	money = money - 16000;	 wep = "BroadSword"; wep_stat[0] = 45;	wep_stat[1] = 36; difficulty = 36;
									System.out.println("Purchase Successful! Enter any number to escape");anykey = s.next(); continue MainGameLoop;	}
								break;
							case 106:
								if (money>=22000)
								{	money = money - 22000;	 wep = "M1Garend"; wep_stat[0] = 46;	wep_stat[1] = 35; difficulty = 20;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
							case 107:
								if (money>=33000)
								{	money = money - 33000;	 wep = "Obvilion Staff"; wep_stat[0] = 42;	wep_stat[1] = 50; difficulty = 45;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
							case 108:
								if (money>=37000)
								{	money = money - 37000;	 wep = "Mithrill Hammer"; wep_stat[0] = 53;	wep_stat[1] = 43; difficulty = 34;
									System.out.println("Purchase Successful! Enter any number to escape");anykey = s.next(); continue MainGameLoop;	}
								break;
							case 109:
								if (money>=60000)
								{	money = money - 60000;	 wep = "M16A4"; wep_stat[0] = 63;	wep_stat[1] = 48; difficulty = 40;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;

							}
						}
						/***************************************************************Armor Shop****************************************************************/
						else if (UserOrder2 ==2)
						{
							for (int vic = 0;vic<25 ;vic++ )
								System.out.println("");
							System.out.println("				Armor Shop");
							System.out.println("");
							System.out.println("Type number corresponding to the armor to purchase it. Else, type 0 to escape.");
							System.out.println("");
							System.out.println("201.Poor'sClothes $300:	def 2");System.out.println("");
							System.out.println("202.T-shirt $1100:		def 5");System.out.println("");
							System.out.println("203.Chian Mail $3500:		def 15,	hp 50");System.out.println("");
							System.out.println("204.Plate Mail $7500:		def 18,	Stamina 4,	  hp 100");System.out.println("");
							System.out.println("205.Vanguard $17k:		def 45,	Stamina 5");System.out.println("");
							System.out.println("206.Shiva'sGuard $23k:		def 40,	hp 750");System.out.println("");
							System.out.println("207.Heart Of Tarrasque$42k:	def 80,	Stamina 8,	  hp 500");System.out.println("");
							UserOrder2 = s.nextInt();
							System.out.println("");
							switch (UserOrder2)
							{
								case 0:
								break;
								case 201:
								if (money>=300)
								{	money = money - 300;	 arm = "Poor's Clothes"; wep_stat[2] = 2;	wep_stat[6] = 0; wep_stat[7] = 0;
									System.out.println("Purchase Successful! Enter any number to escape");anykey = s.next(); continue MainGameLoop;	}
								break;
								case 202:
								if (money>=1100)
								{	money = money - 1100;	 arm = "T-shirt"; wep_stat[2] = 5;	wep_stat[6] = 0; wep_stat[7] = 0;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next();continue MainGameLoop;	}
								break;
								case 203:
								if (money>=3500)
								{	money = money - 3500;	 arm = "Chain Mail"; wep_stat[2] = 15;	wep_stat[6] = 0; wep_stat[7] = 50;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 204:
								if (money>=7500)
								{	money = money - 7500;	 arm = "Plate Mail"; wep_stat[2] = 18;	wep_stat[6] = 4; wep_stat[7] = 100;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 205:
								if (money>=17000)
								{	money = money - 17000;	 arm = "Vanguard"; wep_stat[2] = 45;	wep_stat[6] = 5; wep_stat[7] = 0;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 206:
								if (money>=23000)
								{	money = money - 23000;	 arm = "Shiva's Guard"; wep_stat[2] = 40;	wep_stat[6] = 0; wep_stat[7] = 750;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 207:
								if (money>=42000)
								{	money = money - 42000;	 arm = "Heart Of Tarrasque"; wep_stat[2] =  80;	wep_stat[6] = 8; wep_stat[7] = 500;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								
							}
							
						}
						/***************************************************************HeadGear Shop****************************************************************/
						else if (UserOrder2 ==3)
						{
							for (int vic = 0;vic<25 ;vic++ )
								System.out.println("");
							System.out.println("				HeadGear Shop");
							System.out.println("");
							System.out.println("Type number corresponding to the Headgear to purchase it. Else, type 0 to escape.");
							System.out.println("");
							System.out.println("301.Bandana $1500:		agi 3,	acc 8");System.out.println("");
							System.out.println("302.Sobi Mask $4500:		int 4,	mp 40,	acc 5");System.out.println("");
							System.out.println("303.Cap of Madness $6k:		str 5,	agi 2,	acc 15");System.out.println("");
							System.out.println("304.Helm of Legolas $11k:	agi 6,	int 3,	mp 100,	acc 30");System.out.println("");
							System.out.println("305.Helm of Dominator $15k:	str 7,	int 5,	acc 18");System.out.println("");
							System.out.println("306.Helmet of JACOOM $ 35k:	str 12,	 agi 12,	int 12");System.out.println("");
							UserOrder2 = s.nextInt();
							System.out.println("");
							switch (UserOrder2)
							{
								case 0:
								break;
								case 301:
								if (money>=1500)
								{	money = money - 1500;	 arm = "Bandana"; wep_stat[3] = 0;	wep_stat[4] = 4; wep_stat[5] = 0; wep_stat[8] = 0; wep_stat[9] = 8;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 302:
								if (money>= 4500)
								{	money = money - 4500;	 arm = "Sobi Mask"; wep_stat[3] = 0;	wep_stat[4] = 0; wep_stat[5] = 4; wep_stat[8] = 40; wep_stat[9] = 5;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 303:
								if (money>=6000)
								{	money = money - 6000;	 arm = "Cap of Madness"; wep_stat[3] = 5;	wep_stat[4] = 2; wep_stat[5] = 0; wep_stat[8] = 0; wep_stat[9] = 15;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 304:
								if (money>=11000)
								{	money = money - 11000;	 arm = "Helm of Legolas"; wep_stat[3] = 0;	wep_stat[4] = 6; wep_stat[5] = 3; wep_stat[8] = 100; wep_stat[9] = 30;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 305:
								if (money>=15000)
								{	money = money - 15000;	 arm = "Helm of Dominator"; wep_stat[3] = 7;	wep_stat[4] = 0; wep_stat[5] = 5; wep_stat[8] = 0; wep_stat[9] = 18;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 306:
								if (money>=35000)
								{	money = money - 35000;	 arm = "Helmet of JACOOM"; wep_stat[3] = 12;	wep_stat[4] = 12; wep_stat[5] = 12; wep_stat[8] = 0; wep_stat[9] = 0;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
							}
						}
						else if (UserOrder2 == 0)
						{
							continue MainGameLoop;
						}
					}
				}

				/***********************************************************************   INTO THE BATTLEFIELD!!!!!!   ******************************************************************************/
				case 5:
				/***************************************************Load Battle Fundamentals*************************************************/
					String mname = "default";
					double mhp = 1;	//mob statistics
					double mmp = 1;
					double matk = 0;
					double matkm = 0;
					double mdef = 0;
					double mskill = 0;			//skills
					double mbolt = 0;
					double mheal = 0;
					double mflee = 0;
					
					int mexp = 0;					//reward
					int mmon = 0;

					double damage = 0;		//AI
					double hit = 0;
					double AI = 0;// 1~10
					String hpb = "";				// hp bar
					int kerrigan = 0;				// kerrigan variables
					int kb = 0;
					int kc = 0;
					int kd = 0;
					int ke = 0;
					boolean kerriganb = false;
				/****************************************end of loading batttle fundamentals***************************************/
				/*******************************************************World Selection****************************************************************/
					System.out.println("				Battle Field");System.out.println("");
					System.out.println("");
					System.out.println("");
					System.out.println("				Select Map (1~4)");
					System.out.println("");
					System.out.println("		1. Leaf Island		(lv 1~12)");System.out.println("");
					System.out.println("		2. Lawless City		(lv 15~30)");System.out.println("");
					System.out.println("		3. Helm's deep Dungeon	(lv 30~50)");System.out.println("");
					System.out.println("		4. Hive Cluster		(lv 40~)");System.out.println("");System.out.println("");
					System.out.println("");
					System.out.println("escape : [0]");
					UserOrder2 = s.nextInt();
					for (int k = 0;k<25 ;k++ )
						System.out.println("");
				/******************************************************Gathering Battle Information(enemy stats)************************************************************/
					BattleLoop: while(true)
					{
						switch (UserOrder2)
						{
						default :
							continue MainGameLoop;
						case 1:
							System.out.println("				Leaf Island");
							System.out.println("");
							System.out.println("			Select Monster to fight	escape:[0]");System.out.println("");System.out.println("");
							System.out.println("11.Snail	lv1		hp 21		exp 10		earning 200");System.out.println("");
							System.out.println("12.Mushroom	lv2		hp 50		exp 22		earning 300");System.out.println("");
							System.out.println("13.GuardMan	lv4		hp 85		exp 29		earning 450");System.out.println("");
							System.out.println("14.Zergling	lv6		hp 120		exp 42		earning 600");System.out.println("");
							System.out.println("15.Slime	lv8		hp 175		exp 57		earning 800");System.out.println("");
							System.out.println("16.Viper	lv10		hp 250		exp 72		earning 1000");System.out.println("");
							System.out.println("17.Zealot	lv13		hp 320		exp 92		earning 1300");System.out.println("");
							UserOrder2 = s.nextInt();
							switch (UserOrder2)
							{
							default :
								continue MainGameLoop;
							case 11:
								mname="Snail";	mhp=21;	mmp=1;		matk=10;	matkm = 12;	mdef=0;		mskill=0;	mbolt=20;	mheal=10;		mflee=0;	mexp=10;	mmon=200;	break BattleLoop;
							case 12:
								mname="Mushroom";	mhp=55;	mmp=10;		matk=13;	matkm = 15;	mdef=1;		mskill=0;	mbolt=20;	mheal=10;		mflee=2;	mexp=22;	mmon=300;	break BattleLoop;
							case 13:
								mname="GuardMan";	mhp=85;	mmp=15;		matk=17;	matkm = 20;	mdef=1;		mskill=0;	mbolt=28;	mheal=15;		mflee=5;	mexp=29;	mmon=450;	break BattleLoop;
							case 14:
								mname="Zergling";	mhp=120;	mmp=50;		matk=22;	matkm = 29;	mdef=2;		mskill=3;	mbolt=36;	mheal=34;		mflee=5;	mexp=42;	mmon=600;	break BattleLoop;
							case 15:
								mname="Slime";mhp=175;	mmp=30;		matk=33;	matkm = 36;	mdef=5;		mskill=0;	mbolt=20;	mheal=10;		mflee=35;	mexp=57;	mmon=800;	break BattleLoop;
							case 16:
								mname="Viper";	mhp=250;	mmp=200;		matk=35;	matkm = 42;	mdef=4;		mskill=5;	mbolt=54;	mheal=45;		mflee=6;	mexp=72;	mmon=1000;	break BattleLoop;
							case 17:
								mname="Zealot";	mhp=320;	mmp=55;		matk=56;	matkm = 64;	mdef=18;		mskill=3;	mbolt=50;	mheal=24;		mflee=5;	mexp=92;	mmon=1300;	break BattleLoop;
							
							}
						case 2 :
							System.out.println("			Lawless City");
							System.out.println("");
							System.out.println("			Select convicts to fight	escape:[0]");System.out.println("");System.out.println("");
							System.out.println("21.Bandit	lv15		hp 500		exp 125		earning 1800");System.out.println("");
							System.out.println("22.SpearMan	lv18		hp 550		exp 160		earning 2100");System.out.println("");
							System.out.println("23.Tassadar	lv21		hp 720		exp 192		earning 2500");System.out.println("");
							System.out.println("24.Viper	lv24		hp 900		exp 220		earning 2900");System.out.println("");
							System.out.println("25.Thief	lv25		hp 680		exp 260		earning 3100");System.out.println("");
							System.out.println("26.Spell Caster	lv27		hp 1100		exp 300		earning 3300");System.out.println("");
							System.out.println("27. IssacClarke	lv31		hp 1500		exp 480		earning 4200");System.out.println("");
							UserOrder2 = s.nextInt();
							switch (UserOrder2)
							{
							default :
								continue MainGameLoop;
							case 21:
								mname="Bandit";	mhp=500;	mmp=80;		matk=65;	matkm = 72;	mdef=5;		mskill=2.5;	mbolt=71;	mheal=75;		mflee=5;	mexp=125;	mmon=1800;	break BattleLoop;
							case 22:
								mname="Spearman";	mhp=550;	mmp=100;		matk=96;	matkm = 123;	mdef=10;		mskill=3.5;	mbolt=110;	mheal=120;		mflee=10;	mexp=160;	mmon=2100;	break BattleLoop;
							case 23:
								mname="Warrier of DeadCity";	mhp=720;	mmp=50;		matk=90;	matkm = 100;	mdef=35;		mskill=1;	mbolt=160;	mheal=150;		mflee=5;	mexp=192;	mmon=2500;	break BattleLoop;
							case 24:
								mname="Tassadar";	mhp=900;	mmp=340;		matk=80;	matkm = 140;	mdef=8;		mskill=7;	mbolt=136;	mheal=100;		mflee=20;	mexp=220;	mmon=2900;	break BattleLoop;
							case 25:
								mname="Thief";mhp=680;	mmp=100;		matk=148;	matkm = 160;	mdef=0;		mskill=3;	mbolt=100;	mheal=50;		mflee=60;	mexp=260;	mmon=3100;	break BattleLoop;
							case 26:
								mname="Spell Caster";	mhp=1100;	mmp=500;		matk=130;	matkm = 150;	mdef=25;		mskill=6;	mbolt=198;	mheal=250;		mflee=13;	mexp=300;	mmon=3300;	break BattleLoop;
							case 27:
								mname="Issac Clarke";	mhp=1500;	mmp=250;		matk=170;	matkm = 200;	mdef=30;		mskill=3.5;	mbolt=184;	mheal=200;		mflee=5;	mexp=420;	mmon=4200;	break BattleLoop;
							
							}
							case 3 :
								System.out.println("			Helm's Deep Deongeon");
								System.out.println("");
								System.out.println("			Select Named Mobs to fight	escape:[0]");System.out.println("");System.out.println("");
								System.out.println("31.Hydralisk	lv30		hp 1350		exp 450		earning 3600");System.out.println("");
								System.out.println("32.Skeleton	lv32		hp 2600		exp 545		earning 4300");System.out.println("");
								System.out.println("33.Priestess	lv35		hp 1860		exp 700		earning 5000");System.out.println("");
								System.out.println("34.Naga Siren	lv39		hp 2300		exp 900		earning 5500");System.out.println("");
								System.out.println("35.Wild Cago	lv42		hp 2500		exp 1150	earning 6300");System.out.println("");
								System.out.println("36.Junior Valok	lv45		hp 3200		exp 1350	earning 7000");System.out.println("");
								System.out.println("37.JACOOM	lv50		hp 4400		exp 1800	earning 10000");System.out.println("");
								UserOrder2 = s.nextInt();
								switch (UserOrder2)
								{
								default :
									continue MainGameLoop;
								case 31:
									mname="Hydralisk";	mhp=1350;	mmp=150;		matk=180;	matkm = 210;	mdef=15;		mskill=3;	mbolt=166;	mheal=426;		mflee=15;	mexp=450;	mmon=3600;	break BattleLoop;
								case 32:
									mname="Skeleton";	mhp=2600;	mmp=400;		matk=130;	matkm = 270;	mdef=10;		mskill=5;	mbolt=126;	mheal=120;		mflee=30;	mexp=545;	mmon=4300;	break BattleLoop;
								case 33:
									mname="Priestess";	mhp=1860;	mmp=500;		matk= 176;	matkm = 200;	mdef=35;		mskill=7;	mbolt=310;	mheal=300;		mflee=10;	mexp=700;	mmon=5000;	break BattleLoop;
								case 34:
									mname="Naga Siren";	mhp=2200;	mmp=300;		matk=250;	matkm = 280;	mdef=30;		mskill=2;	mbolt=606;	mheal=450;		mflee=20;	mexp=900;	mmon=5500;	break BattleLoop;
								case 35:
									mname="Wild Cago";mhp=2500;	mmp=200;		matk=328;	matkm = 342;	mdef=25;		mskill=1;	mbolt=513;	mheal=460;		mflee=38;	mexp=1150;	mmon= 6300;	break BattleLoop;
								case 36:
									mname="Junior Valok";	mhp=3200;	mmp=500;		matk=300;	matkm = 400;	mdef=40;		mskill=6;	mbolt=488;	mheal=400;		mflee=10;	mexp=1400;	mmon=7000;	break BattleLoop;
								case 37:
									mname="JACOOM";	mhp=4400;	mmp=150;		matk=245;	matkm = 342;	mdef=22;		mskill=9;	mbolt=841;	mheal=600;		mflee=20;	mexp=2200;	mmon=10000;	break BattleLoop;	
							}

							case 4 :
								System.out.println("			Hive Cluster");
								System.out.println("");
								System.out.println("");
								System.out.println("			Select Kerrigan's forces to fight.	escape:[0]");
								System.out.println("		You have to defeat one to proceed to next one.");System.out.println("");System.out.println("");
								System.out.println("41.SpineCrawler	lv43		hp 3000		exp 1250	earning 8000");System.out.println("");
								System.out.println("42.Mutalisk	lv48		hp 3750		exp 1500	earning 10000");System.out.println("");
								System.out.println("43.Torrasque	lv53		hp 4800		exp 1750	earning 12000");System.out.println("");
								System.out.println("44.HunterKiller	lv58		hp 5500		exp 2250	earning 16000");System.out.println("");System.out.println("");System.out.println("");System.out.println("");
								System.out.println("		50.KERRIGAN	lv70	 hp ???	exp ???	earning ???");System.out.println("");
								UserOrder2 = s.nextInt();
								switch (UserOrder2)
								{
								default :
									continue MainGameLoop;
								case 41:
									if (kerrigan >= 0)
									{
									kerriganb = true;
									mname="Spine Crawler";	mhp= 3000;	mmp=110;		matk=250;	matkm = 300;	mdef=55;		mskill=3;	mbolt=254;	mheal=402;		mflee=0;	mexp=1250;	mmon=8000;	break BattleLoop;} continue BattleLoop;
									case 42:
									if (kerrigan >= 1)
									{
									kerriganb = true;	kerrigan = 5;
									mname="Mutalisk";	mhp=	3750;	mmp=160;		matk=340;	matkm = 400;	mdef=15;		mskill=4;	mbolt=450;	mheal=335;		mflee=30;	mexp=1500;	mmon=10000;	break BattleLoop;} continue BattleLoop;
									case 43:
									if (kb >= 1)
									{
									kerriganb = true;	kb = 5;
									mname="Torrasque";	mhp=4800;	mmp=100;		matk=420;	matkm = 440;	mdef=70;		mskill=2;	mbolt=827;	mheal=1000;		mflee=10;	mexp=1750;	mmon=12000;	break BattleLoop;} continue BattleLoop;
									case 44:
									if (kc >= 1)
									{
									kerriganb = true;	kc = 5;
									mname="Hunter Killer";	mhp=5500;	mmp=500;		matk=400;	matkm = 1000;	mdef=60;		mskill=8;	mbolt=546;	mheal=300;		mflee=33;	mexp=2250;	mmon=16000;	break BattleLoop;} continue BattleLoop;
									case 50:
									if (kd >= 1)
									{
									kerriganb = true;	kd = 5;
									mname="Kerrigan";	mhp=9999;	mmp=1024;		matk=	 512;	matkm = 1024;	mdef=100;		mskill=3.5;	mbolt=850;	mheal=1000;		mflee=50;	mexp=4500;	mmon=30000;	break BattleLoop;} continue BattleLoop;
								}
								


						}
					
					}
					/********************************************************End of Gathering Battle informations****************************************************************/
					/****************************************************************************Battle Simulation**********************************************************************************/
					
					double mchp = mhp;
					System.out.println("");
					System.out.println("");
					System.out.println("");
					System.out.println("");
					System.out.println("		"+User+"	VERSUS	"+mname);
					System.out.println("");
					System.out.println("");
					System.out.println("		  	  loading...");
					System.out.println("");
					for (int b = 0;b<200000 ;b++ )
						System.out.print("\b");
					
					/******************************************************Status Bar***********************************************************/
					BLoop : while (true)
					{
						for (int k = 0;k<25 ;k++ )
							System.out.println("");
						System.out.println(User+"'s HP: "+(int)Gamehp+" / "+(int)hp+"				");	 // user status bar
						hpb = hpbar(Gamehp/hp);
						System.out.println(hpb+"				"+User+"'s MP: "+(int)Gamemp+" / "+(int)mp);
						hpb = hpbar(Gamemp/mp);
						System.out.print("						"+hpb);
						System.out.println("");
						System.out.println("");
						System.out.println(mname+"'s hp: "+(int)mchp+" / "+(int)mhp);			// comp status bar
						hpb = hpbar(mchp/mhp);
						System.out.println(hpb);
						System.out.println("");
						System.out.println("");
						System.out.println("");
						System.out.println("");

						/***********************************************************Recieve User order****************************************************************/
						OrderLoop : while (true)						
						{
							System.out.println("Your turn. 1 for normal attack, 2~7 for skill	0 for opening skill tab");
							UserOrder2 = s.nextInt();
							switch (UserOrder2)
							{
								default:
									System.out.println("Invalid order");
									continue OrderLoop;

								case 0 :									// skill tree
									System.out.println("");System.out.println("");System.out.println("");System.out.println("");
									System.out.println("2. Stab:	"+(int)skstab+"/"+"5"+"  deals additional damage to enemy.	("+(int)(117+skstab*3)+"%), mana:"+(46+((int)skstab*4)));
									System.out.println("");
									System.out.println("3. DoubleStrike:"+(int)skdoublestrike+"/"+"8"+"  hits enemy twice. Ignores defence	("+(int)(67+skdoublestrike*3)+"%), mana:"+(95+((int)skdoublestrike*5)));
									System.out.println("");
									System.out.println("4. Heal:	"+(int)skheal+"/"+"8"+"  heals yourself.			("+(int)(19+skheal*1.2)+"%), mana:"+(215+((int)skheal*15)));
									System.out.println("");
									System.out.println("5. Debuff:	"+(int)skdebuff+"/"+"8"+"  decreases enemy's stats.		("+(int)(9+skdebuff*1)+"%), mana:"+(260+((int)skdebuff*20)));
									System.out.println("");
									System.out.println("6. PsionicStorm:"+(int)skpsionicstorm+"/"+"10"+" critical damage and poisons enemy.	("+(int)(90+skpsionicstorm*12)+"%) mana:"+(430+((int)skpsionicstorm*20)));
									System.out.println("");
									System.out.println("7. Allin:	"+(int)skallin+"/"+"5"+"  costs half hp and all mp. Deals massive damage ("+(int)(280+skallin*20)+"%)");
									System.out.println("");
									System.out.println("8. Attribute:	increase all stat points by 1.");
									System.out.println("");
									System.out.println("type any key to go back");
									anykey = s.next();
									System.out.println("");System.out.println("");System.out.println("");
									continue OrderLoop;

								case 1 :
									System.out.println("");
									System.out.println(User+" Commited an attack on "+mname+"!!");			// normal atttack
									hit = (int)(Math.random()*100);					//accuracy
									if (hit<(acc-mflee))
									{
										damage = (int)(Math.random()*atkmax%(atkmax-atkmin))+atkmin;
										if (mdef > damage)
											damage = mdef;
										damage = damage-mdef;
										System.out.println("Damage : "+(int)damage+"!!");
										mchp = mchp-damage;
									}
									else						//miss
										System.out.println(User+"'s attack was missed!!");
									break;
									
						/***********************************************User Order - Skills**************************************************/
								case 2:						// stab
									if (Gamemp <  (46+((int)skstab*4)) || skstab == 0)
									{
										System.out.println("You cannot use this skill!!");
										System.out.println("");
										continue OrderLoop;
									}
									System.out.println("	 "+User+" stabbed "+mname+"!!");
									System.out.println("");
									System.out.println("");
									Gamemp = Gamemp-(46+((int)skstab*4));								// skill mana
									damage = (spatk*0.9)*((117+skstab*3)/80)+atkmin/4-mdef;				// skill damage
									System.out.println("Damage: "+(int)damage+"!!");
									mchp = mchp - damage;
									break;

								case 3:						// double strike
									if (Gamemp < (100+((int)skdoublestrike*6)) || skdoublestrike == 0)
									{
										System.out.println("You cannot use this skill!!");
										continue OrderLoop;
									}
									System.out.println("");
									System.out.println("	 "+User+" used Double Strike!!");
									System.out.println("");
									System.out.println("");
									Gamemp = Gamemp-(95+((int)skdoublestrike*5));
									for (int d = 0;d<2;d++)
									{
										damage = ((int)(Math.random()*atkmax%(atkmax-atkmin))+atkmin)*((68+skdoublestrike*2)/150)+spatk/1.5;
										System.out.println("Damage: "+(int)damage+"!!");
										mchp = mchp - damage;
									}
									break;

								case 4:							// heal
									if (Gamemp < (215+((int)skheal*15)) || skheal == 0)
									{
										System.out.println("You cannot use this skill!!");
										continue OrderLoop;
									}
									System.out.println("");
									System.out.println(User+" Healed himself!!");
									System.out.println("");
									System.out.println("");
									Gamemp = Gamemp- (215+((int)skheal*15));
									damage = hp*((19+skheal*1.2)/200)+(Math.sqrt(spatk)/2)*(hp/10)*((19+skheal*1.2)/100);
									System.out.println("	 Healed: "+(int)damage+" hp!!");
									Gamehp = Gamehp+damage;
									break;

								case 5:							// debuff
									if (Gamemp < (260+((int)skdebuff*20)) || skdebuff == 0)
									{
										System.out.println("You cannot use this skill!!");
										continue OrderLoop;
									}
									System.out.println("");
									System.out.println("	 "+User+" Used debuff on "+mname+"!!");
									System.out.println("");
									System.out.println("");
									Gamemp = Gamemp- (260+((int)skdebuff*20));
									damage = Math.sqrt(Math.sqrt(Math.sqrt(spatk*0.08)))*((9+skdebuff*1)/100)-0.01;
									if (damage >= 0.2)
										damage = 0.2;
									matk = matk-Math.sqrt(spatk*3)*(9+skdebuff*1)/10-matk*damage;
									matkm = matkm-Math.sqrt(spatk*3)*(9+skdebuff*1)/10-matkm*damage;
									mdef = mdef-Math.sqrt(spatk*3)*(9+skdebuff*1)/100-mdef*damage;
									mflee = mflee-Math.sqrt(spatk*3)*(9+skdebuff*1)/100-mflee*damage;
									System.out.println(mname+"'s attack decreased by : "+(int)(Math.sqrt(spatk*3)*(9+skdebuff*1)/10+matkm*damage)+"!!");
									System.out.println(mname+"'s defence decreased by : "+(int)(Math.sqrt(spatk*3)*(9+skdebuff*1)/100+mdef*damage)+"!!");
									System.out.println(mname+"'s flee decreased by : "+(int)(Math.sqrt(spatk*3)*(9+skdebuff*1)/100+mflee*damage)+"!!");
									break;

								case 6:							// psionic storm
									if (Gamemp < (430+((int)skpsionicstorm*20)) || skpsionicstorm == 0)
									{
										System.out.println("You cannot use this skill!!");
										continue OrderLoop;
									}
									System.out.println("");
									System.out.println("	 "+User+" spelled PsionicStorm on "+mname+"!!!!");
									System.out.println("");
									System.out.println("");
									Gamemp = Gamemp- (430+((int)skpsionicstorm*20));
									damage = 0;
									System.out.println("Healed: "+(int)damage+" hp!!");
									Gamehp = Gamehp+damage;
									break;
							
							}
							if (chk(mchp))					// continue
								break BLoop;
							System.out.println("type any key to continue");
							anykey = s.next();
							break OrderLoop;
								
						}
						/**************************************************************Artificial Intelligence  - Enemy attack****************************************/
						CompLoop : while (true)
						{
						/**************************************************Status Bar**************************************/
							for (int k = 0;k<25 ;k++ )
								System.out.println("");
							System.out.println(User+"'s HP: "+(int)Gamehp+" / "+(int)hp);	 // user status bar
							hpb = hpbar(Gamehp/hp);
							System.out.println(hpb+"				"+User+"'s MP: "+(int)Gamemp+" / "+(int)mp);
							hpb = hpbar(Gamemp/mp);
							System.out.print("						"+hpb);
							System.out.println("");
							System.out.println("");
							System.out.println(mname+"'s hp: "+(int)mchp+" / "+(int)mhp);			// comp status bar
							hpb = hpbar(mchp/mhp);
							System.out.println(hpb);
							System.out.println("");
							System.out.println("");
							System.out.println("");
							System.out.println("");
							System.out.println(mname+"'s turn.Please wait...");		// ai decision
							/**************************************************AI******************************************/
							for (int b = 0;b<100000 ;b++ )
								System.out.print("\b");
							msloop : while (true)
							{
							AI = (Math.random()*10%9)+1;
							/**************************************************attack******************************************/
							if (AI>mskill)
							{
								System.out.println("");
								System.out.println(mname+" commited commercial attack on "+User+"!!");
								damage = (int)(Math.random()*matkm%(matkm-matk))+matk;
								if (def > damage)
									damage = def;
								damage = damage-def;
								System.out.println("Damage : "+(int)damage+"!!");
								
								Gamehp = Gamehp-damage;
								break msloop;
								
							}
							/**************************************************Heal******************************************/
							else if ((AI<mskill/3 && mchp<mhp/1.7 ) || mchp<mhp/4 )		// use heal when health is low, in certain possiblity
							{
								if (mmp<50)
									continue msloop; 
								System.out.println("");
								System.out.println(mname+" used heal!!");
								System.out.println("Healed "+(int)mheal+"hp!!");
								mchp = mchp+mheal;
								mmp = mmp-50;
								break msloop;
							}
							/***********************************************Attack Skill*************************************************/
							else
							{
								if (mmp<40)
								{
									continue msloop;
								}
								System.out.println("");
								System.out.println(mname+" used skill!!!");
								AI = Math.random();
								if (AI>0.7)
								{
									System.out.println("");
									System.out.println("	 Energy Bolt!");
								}
								else if (AI<=0.7 && AI>0.4)
								{
									System.out.println("");
									System.out.println("	 Critical Strike!");
								}
								else
								{
									System.out.println("");
									System.out.println("	 Assasinate!");
								}
								System.out.println("Damage : "+(int)mbolt+"!!");
								Gamehp=Gamehp-mbolt;
								mmp = mmp-40;
								break msloop;
							}
							}
								if (chk(Gamehp))
									break BLoop;
								System.out.println("Enter any key to continue");
								anykey=s.next();
								break CompLoop;
						}
					}


					/***************************************************************battle result****************************************************************************/
					/************************************Victory*********************************************/
					if (chk(mchp))						// won!!
					{
						for (int k = 0;k<25 ;k++ )
							System.out.println("");
						System.out.println(User+"'s HP: "+(int)Gamehp+" / "+(int)hp+"		"+User+"'s MP: "+(int)Gamemp+" / "+(int)mp);
						hpb = hpbar(Gamehp/hp);
						System.out.println(hpb);
						System.out.println("");
						System.out.println("");
						System.out.println("");
						System.out.println("");
						System.out.println("You Won! You defeated "+mname+"!!");
						System.out.println("");
						System.out.println("You got "+mexp+" exp and earned  $"+mmon);
						exp = exp+mexp;
						money = money+mmon;
						System.out.println("type any key to continue");
						anykey = s.next();
						//kerriganb = true; kd = 4; kerrigan = 4; kb = 4; kc = 4;	// enable it to see ending credit
						if (kerriganb == true)
						{
							kerrigan++;
							if (kerrigan == 5)
							{
								kb++;
								if (kb == 5)
								{
									kc++;
									if (kc == 5)
									{
										kd++;
										if (kd == 5)
										{
											for (int victory = 0;victory<25 ;victory++ )
											{
												System.out.println("");
											}
											System.out.println("Finally!! You defeated Kerrigan!!");					// Game Victory
											System.out.println("You made a outstanding revenge on her!");
											System.out.println("You are the strongest warrier in this java world!");
											System.out.println("");
											System.out.println("");
											System.out.println("");
											System.out.println("");
											System.out.println("enter any key to continue...");
											anykey = s.next();
											for (int victory = 0;victory<25 ;victory++ )
											{
												System.out.println("");
											}
											System.out.println("			Development / Game Design");						// Credits
											System.out.println("				HongJoon Choi");
											System.out.println("");
											System.out.println("			Visualization / Graphics");
											System.out.println("				HongJoon Choi");
											System.out.println("				JoonRock Choi");
											System.out.println("");
											System.out.println("			Game System Programmer");
											System.out.println("				HongJoon Choi");
											System.out.println("");
											System.out.println("			Balance Lead. Assistance");
											System.out.println("				HongJoon Choi");
											System.out.println("				JoonRock Choi");
											System.out.println("");
											System.out.println("				Thanks to...");
											System.out.println("				Seowoo Hong");
											System.out.println("				'String anykey'");
											System.out.println("				Hyungnam Oh");
											System.out.println("				JoonRock Choi");
											System.out.println("			  Overwhelming IB homeworks");
											System.out.println("		 My Labtop with incredible battery life span.");
											System.out.println("");
											System.out.println("I am very proud to have a program with 1100 line of code with 75 variables!!");
											System.out.println("			enter any key to continue...");
											anykey = s.next();
											for (int victory = 0;victory<25 ;victory++ )
											{
												System.out.println("");
											}
											System.out.println("				God mode Unlocked!!");
											System.out.println("	 Enter 486 in main screen, to summon hidden warrier of lv100");
											System.out.println("			enter any key to continue...");
											anykey = s.next();


										}
									}
								}
							}
						}
						continue MainGameLoop;
					}
					/************************************Defeat*********************************************/
					if (chk(Gamehp))						// lost!!
					{
						for (int k = 0;k<25 ;k++ )
							System.out.println("");
						System.out.println(User+"'s HP: 0 / "+(int)hp+"		"+User+"'s MP: "+(int)Gamemp+" / "+(int)mp);
						hpb = hpbar(Gamehp/hp);
						System.out.println(hpb);
						System.out.println("");
						System.out.println("");
						System.out.println("");
						System.out.println("");
						System.out.println("You Lost! "+mname+" Beated You!!");
						System.out.println("");
						System.out.println("You Lost $"+(int)(money/4)+" please try beating this "+mname+" bastard again later");
						money = money-money/4;
						System.out.println("type any key to continue");
						anykey = s.next();
						continue MainGameLoop;
					}
				
			}
		}
	}
}