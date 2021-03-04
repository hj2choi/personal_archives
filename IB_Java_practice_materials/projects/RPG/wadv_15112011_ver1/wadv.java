/*
	PATCH NOTE 05/Sep/2017:

	special abilities are less costly and more rewarding
		- boosted abilities effectiveness overall



/*
brief planning on game system.(may change over time)

game system
stat+3 each lvup
warrier lv 1		exp 0/10
money : 500
hp : 63		
mp : 12		
atk min : 4 
atk max : 6 
def : 4 
spatk : 5 
acc : 90

lvup exp
10
30
64
108

attributes
Strength : 3
Agility : 3
Intelligence : 3
Stamina : 3
 
wep : wep_atk : 12
arm : wep_def : 2
acc : wep_stat[0~6]
2

wep stat array[](int[] wep_stat = new int[])
0,1,2 spatk
3~6: str...
6 hp
7 mp
8 acc

design
1-10 : 2-3 to lvup
20-20 : 4- 5 
20-30 : 5-6 (increase exp reward)
30-40 : 6-7

fix required:
skills not balanced
possible unbalance between def and hp
heal gives player more benefit 
player have nothing to do after beating final boss

*/
/***************************************************************************
									Hong Joon Choi ver 1.1.1
									Subject : Bored Warrier's Adventure
							Description :  3D MMORPG (Demolished Disturbed Destroyed Million Men`s Rarely Played Game)
								Platform : Command Prompt on Windows xp & Windows Vista & Windows 7
	Minimum Requirements : 8bit processor or better, 128kb free hard drive space, 20mb free memory space, java enabled
			Project Started Date : 26.10.2011
Date version 1 published : 29.10.2011
							few rights reserved
****************************************************************************/
/****************************************************************************
										Develop Req
exception handling on number input
no.8 skill
add another features on battle (1v2 / attack speed / accesories / )
Use class on this program after finishing chapter11 / look so messy

add no5 shop
add sp features
add Gym
add inventory
add Text animation(!)
add option(health character * / _ / . / ...)
show stats in lvup(create class)
improve screen(clear screen more often)
***************************************************************************/
import java.io.*;
public class wadv
{
	static int clearscreen = 22;
	static int loading = 1000;
	static char bardot = '*';
	static char bar2dot='_';
	static int textSpeed=8;
	
	public static void main (String args [] )	throws IOException
	{
		int ha = 8;
		int hb = 15;
		int hc = 3;
		int hi = 9;
		/*******************************end*********************************/
		java.util.Scanner s = new java.util.Scanner(System.in);
		TextAnimation tAni = new TextAnimation("Extremely Bored Warrier's Adventure ver 1.1.1");
		tAni = new TextAnimation("Developed by "+(char)(ha+64)+(char)(hb+64+32)+"ng"+" J"+(char)(hb+96)+(char)(hb+96)+"n"+" "+(char)(hc+64)+(char)(ha+96)+(char)(hb+96)+(char)(hi+96));
		/***************************************************************value initialization(starting stat)**********************************************************************/
		String User = "Warrier";
		long starttime = System.currentTimeMillis();
		int increment = 20;
		int increment2 = 10;
		int UserOrder2 = 0;
		String anykey = "";
		int money = 500;		//fundamental
		int chrystal = 0;
		int lv = 1;
		int exp = 0;
		int expmax = 10;
		String hpb = "";				// hp bar
		double sa = 3;					//userstat
		double sb = 3;
		double sc = 3;
		double sd = 3;
		double[] wep_stat = new double[] {24, 18, 2,0,0,0,0,0,0,0};		//weapons(array) 0atk 1spatk 2def 3~6stat 7hp 8mp 9acc
		String wep = "Club";
		String arm = "Poor's clothes";
		String head = "none";
		int difficulty = 13;
		/////////////////////// skills
		double skstab = 0;								// skills
	 	double skdoublestrike = 0;			//doublestrike
		double skheal = 0;					// heal
		double skdebuff = 0;			//debuff
		double skpsionicstorm = 0;		//psionicstorm
		double skallin = 0;			//allin
		SkillTab SKT = new SkillTab(skstab, skdoublestrike, skheal, skdebuff, skpsionicstorm, skallin);
		//////////////////////// information counting 
		int mobCount = 0;		// count for statistics
		long Timeplus = 0;
		long TimeMillisSave = 0;
		long Exptot=0;
		long montot=money;
		long atktot=0;
		long dietot=0;
		long skilltot=0;
		long misstot=0;
		boolean kerriganbeat = false;
		//////////////////////external
		long Speed = 0;
		long TimerBegin = 0;
		long time=0;
		/////////////////////options
		/***********************************************************************end of value initialization*********************************************************************/
		System.out.println("");
		System.out.println("");
		delay(200);
		tAni = new TextAnimation("Create a new Profile?");
		textSpeed=13;
		System.out.println("[1] Load Previous data");
		System.out.println("[0] Create a new Profile");
		UserOrder2 = s.nextInt();
		if (UserOrder2 != 0)											// manual loading
		{
			System.out.println("loading...");
			int count = 0;
			String[] fileread = new String[100];
			File ff = new File("save.txt");
			FileReader fr = new FileReader(ff);
			BufferedReader in = new BufferedReader(fr);
			while (in.ready())
			{ 
				fileread[count] = in.readLine();
				count++;
			}
			in.close();
			sa = Double.parseDouble(fileread[0]);
			sb = Double.parseDouble(fileread[1]);
			sc = Double.parseDouble(fileread[2]);
			sd = Double.parseDouble(fileread[3]);
			lv = Integer.parseInt(fileread[4]);
			expmax = Integer.parseInt(fileread[5]);
			increment = Integer.parseInt(fileread[6]);
			increment2 = Integer.parseInt(fileread[7]);
			money = Integer.parseInt(fileread[8]);
			skstab = Double.parseDouble(fileread[9]);
			skdoublestrike = Double.parseDouble(fileread[10]);
			skheal = Double.parseDouble(fileread[11]);
			skdebuff = Double.parseDouble(fileread[12]);
			skpsionicstorm = Double.parseDouble(fileread[13]);
			skallin = Double.parseDouble(fileread[14]);
			wep = fileread[15];
			arm = fileread[16];
			head = fileread[17];
			for (int f_r=18;f_r<28 ;f_r++ )
				wep_stat[(f_r-18)] = Double.parseDouble(fileread[f_r]);
			difficulty = Integer.parseInt(fileread[28]);
			mobCount = Integer.parseInt(fileread[29]);
			Timeplus = Long.parseLong(fileread[30]);
			Exptot = Integer.parseInt(fileread[31]);
			exp = Integer.parseInt(fileread[32]);
			User = fileread[33];
			chrystal = Integer.parseInt(fileread[34]);
			montot = Integer.parseInt(fileread[35]);
			atktot = Integer.parseInt(fileread[36]);
			kerriganbeat = Boolean.parseBoolean(fileread[37]);
			dietot=Integer.parseInt(fileread[38]);
			skilltot=Integer.parseInt(fileread[39]);
			misstot=Integer.parseInt(fileread[40]);
			Speed=Long.parseLong(fileread[41]);
			TimerBegin = Long.parseLong(fileread[42]);
			clearscreen = Integer.parseInt(fileread[43]);
			loading = Integer.parseInt(fileread[44]);
			char damn[] = fileread[45].toCharArray();
			bardot = damn[0];
			textSpeed = Integer.parseInt(fileread[46]);
			System.out.println("loading successful.");
		}
		if (UserOrder2 == 0)
		{
			tAni = new TextAnimation("WARNING : Saving with this profile will overwrite your original savedata!!");delay(300);
			tAni = new TextAnimation("Do you want to continue? [1]");
			System.out.println("[1] Yes");
			System.out.println("[2] No");
			UserOrder2 = s.nextInt();
			if (UserOrder2 != 1)
			{
				System.out.println("Please Restart the program");
				return;
			}
			tAni = new TextAnimation("Enter your name to start the game(without space) no more than 8 letters!");
			User = s.next();
			int extravariable = User.length();
			if (extravariable>8)
			{
				System.out.println("No more than 8 letters!! This program was force terminated.");
				System.out.println("Please restart the game");
				return;
			}
		}
		MainGameLoop: while (true)
		{
		/*******************************************************************load fundamental statistics******************************************************/
			double str = sa+wep_stat[3];				//final stat
			double dex = sb+wep_stat[4];
			double intel = sc+wep_stat[5];
			double sta = sd+wep_stat[6];
			double hp = (long)((((double)lv*(double)lv)/4.8)+(lv*3)+ (sta*(sta/1.5)/1.6) + (str*str/20)/7 + 50 + wep_stat[7]);				//hp.mp 
			double mp = (long)((lv*lv/10)/4+lv*2+((intel*(intel-2)/3.5))-(intel*(intel-2.6)/6.5) + 20 + intel*2 + wep_stat[8]);
			double Gamehp = hp;			// hp.mp in battle
			double Gamemp = mp;
			double atkmax = (long)((str*(str/7.4))*(wep_stat[0]/20)+(str*(wep_stat[0]/20))+Math.sqrt(dex*(dex/19))+sta/5-str+2);// + (dex*(dex/10)-1) + (sta/4) + ((wep_stat[0])/4)-str);		//battle statistics
			double atkmin = (long)((atkmax/4)+(dex*(dex-1)/13)*(wep_stat[0]/20)+(intel*Math.sqrt(intel/14)));
			if (atkmin>(atkmax*0.95))
				atkmin=(atkmax*0.95);
			double spatk = (long)((intel*intel/11)*(wep_stat[1]/20)+str/5+(dex*dex/16));
			double def = (long)(str/12+sta/3+(sta*Math.sqrt(Math.sqrt(sta))/30)+wep_stat[2]);
			double acc = 100-difficulty+dex-str/7+wep_stat[9];				// use with random(range = 0~100) (hit is determined when (num is 1~acc))
			double speed = (50+dex/1.2);
			long currenttime = System.currentTimeMillis()+Timeplus;
			TimeMillisSave = currenttime-starttime;
		/*******************************************************************end of loading fundamental satistics****************************************************/
			for (int k = 0;k<clearscreen ;k++ )
				System.out.println("");
		/********************************************************************level up!!**********************************************************************/
			if (exp >= expmax)
			{
				lv++;																// exp increment function
				exp = exp- expmax;
				expmax = increment;
				increment = increment+increment2;
				increment2 = increment2+3;
				if (lv>25)
					increment2 = increment2+1;
				if (lv>36)
					increment2 = increment2+1;
				if (lv>46)
					increment2 = increment2+1;
				if (lv>55)
					increment2 = increment2+1;
				if (lv>64)
					increment2 = increment2+3;
				if (lv>72)
					increment2 = increment2+4;
				if (lv>82)
					increment2 = increment2+5;
				System.out.println("				Level Up!!");
				System.out.println("");
				System.out.println("			Congrats!! You are now level "+lv+"!!");
				System.out.println("			You can now spend 5 stat points");
				for (int k = 0;k<10 ;k++ )
					System.out.println("");
				System.out.println("Enter any key to continue...");
				anykey = s.next();
				lvloop : for (int pt = 0;pt<5 ;pt++ )
				{
					
					System.out.println("");System.out.println("");
					System.out.println("Health Point:	"+(int)hp);
					System.out.println("Mana Point:	"+(int)mp);
					System.out.println("Attack:		"+(int)atkmin+"~"+(int)atkmax);
					System.out.println("Special Attack:	"+(int)spatk);
					System.out.println("Defence:	"+(int)def);
					System.out.println("Accuracy:	"+(int)acc);
					System.out.println("Speed:		"+(int)speed);
					System.out.println("");
					System.out.println("[1] Strength:		"+(int)str+"	(+"+(int)(str-sa)+")");
					System.out.println("[2] Agility:		"+(int)dex+"	(+"+(int)(dex-sb)+")");
					System.out.println("[3] Intelligence:	"+(int)intel+"	(+"+(int)(intel-sc)+")");
					System.out.println("[4] Stamina:		"+(int)sta+"	(+"+(int)(sta-sd)+")");
					System.out.println("");System.out.println("");System.out.println("");
					System.out.println("enter : [1] Strength, [2] Agility, [3] Intel, [4] Stamina");
					UserOrder2 = s.nextInt();												// stats
					switch (UserOrder2)
					{
					default :
						pt--;
						break;
					case 1 :
						sa++;
						break;
					case 2 :
						sb++;
						break;
					case 3 :
						sc++;
						break;
					case 4 :
						sd++;
						break;
					}
					str = sa+wep_stat[3];				//final stat
					dex = sb+wep_stat[4];
					intel = sc+wep_stat[5];
					sta = sd+wep_stat[6];
					hp = (long)((((double)lv*(double)lv)/4.8)+(lv*3)+ (sta*(sta/1.5)/1.6) + (str*str/20)/7 + 50 + wep_stat[7]);				//hp.mp 
					mp = (long)((lv*lv/10)/4+lv*2+((intel*(intel-2)/3.5))-(intel*(intel-2.6)/6.5) + 20 + intel*2 + wep_stat[8]);
					Gamehp = hp;			// hp.mp in battle
					Gamemp = mp;
					atkmax = (long)((str*(str/7.4))*(wep_stat[0]/20)+(str*(wep_stat[0]/20))+Math.sqrt(dex*(dex/19))+sta/5-str+2);// + (dex*(dex/10)-1) + (sta/4) + ((wep_stat[0])/4)-str);		//battle statistics
					atkmin = (long)((atkmax/4)+(dex*(dex-1)/13)*(wep_stat[0]/20)+(intel*Math.sqrt(intel/14)));
					if (atkmin>(atkmax*0.95))
						atkmin=(atkmax*0.95);
					spatk = (long)((intel*intel/11)*(wep_stat[1]/20)+str/5+(dex*dex/16));
					def = (long)(str/12+sta/3+(sta*Math.sqrt(Math.sqrt(sta))/30)+wep_stat[2]);
					acc = 100-difficulty+dex-str/7+wep_stat[9];				// use with random(range = 0~100) (hit is determined when (num is 1~acc))
					speed = (50+dex/1.2);
					for (int k = 0;k<clearscreen ;k++ )
					System.out.println("");
				}
				System.out.println("");System.out.println("");
				System.out.println("Health Point:	"+(int)hp);
				System.out.println("Mana Point:	"+(int)mp);
				System.out.println("Attack:		"+(int)atkmin+"~"+(int)atkmax);
				System.out.println("Special Attack:	"+(int)spatk);
				System.out.println("Defence:	"+(int)def);
				System.out.println("Accuracy:	"+(int)acc);
				System.out.println("Speed:		"+(int)speed);
				System.out.println("");
				System.out.println("[1] Strength:		"+(int)str);
				System.out.println("[2] Agility:		"+(int)dex);
				System.out.println("[3] Intelligence:	"+(int)intel);
				System.out.println("[4] Stamina:		"+(int)sta);
				System.out.println("");System.out.println("");System.out.println("");
				System.out.println("You spent all stat points. press any key to proceed");
				anykey = s.next();
				for (int k = 0;k<clearscreen ;k++ )
					System.out.println("");
				if (lv >=6)
				{
					sssloop : for (int pt = 0;pt<1 ;pt++ )							// skills
					{
						System.out.println("");
						System.out.println("You can now spend one skill point.");
						System.out.println("Skills tab all skills heavily rely on special attack points.");
						SKT = new SkillTab(skstab, skdoublestrike, skheal, skdebuff, skpsionicstorm, skallin);
						SKT.print();
						UserOrder2 = s.nextInt();
						switch (UserOrder2)
						{
						default :
							pt--;
							continue sssloop;
						case 2 :
							if (skstab <= 8)
								skstab++;
							else 
							{
								pt--;continue sssloop;
							}
							break;
						case 3 :
							if (skdoublestrike <= 12)
								skdoublestrike++;
							else 
							{
								pt--;continue sssloop;
							}
							break;
						case 4 :
							if (skheal <= 15)
								skheal++;
							else 
							{
								pt--;continue sssloop;
							}
							break;
						case 5 :
							if (skdebuff <= 15)
								skdebuff++;
							else 
							{
								pt--;continue sssloop;
							}
							break;
						case 6 :
							if (skpsionicstorm <= 20)
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
						}
						for (int k = 0;k<clearscreen ;k++ )
							System.out.println("");
							SKT = new SkillTab(skstab, skdoublestrike, skheal, skdebuff, skpsionicstorm, skallin);
							SKT.print();
							break;
						
					}
				}
				System.out.println("You spent all stat and skill points. press any key to continue");
				anykey = s.next();
				continue MainGameLoop;
			}
			/*****************************************************end of level up system*************************************************************/
			/*********************************************************Main Menu***************************************************************/
			System.out.println("Warrier's adventure ver 1.1.1");
			for (int asd = 0;asd<5 ;asd++ )
				System.out.println("");
			System.out.println("				Main Menu");
			for (int asd = 0;asd<3 ;asd++ )
				System.out.println("");
			System.out.println("			Character Information :	[1]");
			System.out.println("			Enter Gear Shop  :	[2]");
			System.out.println("			Go to the Battlefield :	[3]");
			System.out.println("");
			System.out.println("			Auto Save :		[5] ");
			System.out.println("");
			System.out.println("			Game Manual :		[7]");
			System.out.println("			Options :		[8]");
			System.out.println("			External Fetures :	[9]");
			System.out.println("");
			System.out.println("			Exit Game :		[0]");
			for (int asds = 0;asds<3 ;asds++ )
				System.out.println("");
			/***************************************************************\\ main menu****************************************************************/
			int UserOrder = s.nextInt();							// the most important input in this whole program!!
			/***************************************************************Developer's Tab****************************************************************/
			for (int k = 0;k<clearscreen ;k++ )
				System.out.println("");
			switch (UserOrder)					
			{
				case 0:
					System.out.println("Do you really want to exit the game?");
					for (int k = 0;k<10 ;k++ )
						System.out.println("");
					System.out.println("[1] : Yes");
					System.out.println("[2] : No");
					UserOrder2 = s.nextInt();
					if (UserOrder2 == 1)
						return;
					else
						continue MainGameLoop;
				case 8:									// Options
					Optionloop: while (true)
					{
						for (int k = 0;k<clearscreen ;k++ )
							System.out.println("");
						System.out.println("				------Options-----");
						System.out.println("");
						System.out.println("");
						System.out.print("[1] ClearScreen intensity :	"); if (clearscreen==15){System.out.println("Low");} if (clearscreen==22){System.out.println("Med");}if (clearscreen==30){System.out.println("High");}
						System.out.println("");
						System.out.print("[2] Game  Speed :		"); if (loading == 1){System.out.println("Extreme");}if (loading == 500){System.out.println("Expert");}if (loading == 1000){System.out.println("Basic");}if (loading == 2000){System.out.println("Epic");}
						System.out.println("");
						System.out.print("[3] Hp,Mp Status Bar :		"); if (bardot == '*'){System.out.println("***__");}if (bardot == '0'){System.out.println("000__");}if (bardot == '#'){System.out.println("###__");}if (bardot == '@'){System.out.println("@@@__");}
						System.out.println("");
						System.out.print("[4] Text Speed :		"); if (textSpeed == 0){System.out.println("No Text Animation");}if (textSpeed == 8){System.out.println("Fast");}if (textSpeed == 13){System.out.println("Normal");}if (textSpeed == 18){System.out.println("Slow");}
						System.out.println("");
						System.out.println("[0] Escape");
						for (int k = 0;k<10 ;k++ )
							System.out.println("");
						UserOrder2 = s.nextInt();
						if (UserOrder2 == 0)
							break;

						if (UserOrder2 == 1 && clearscreen == 15)
							clearscreen = 22;
						else if (UserOrder2 == 1 && clearscreen == 22)
							clearscreen = 30;
						else if (UserOrder2 == 1 && clearscreen == 30)
							clearscreen = 15;
						if (UserOrder2 == 2 && loading == 1)
							loading = 500;
						else if (UserOrder2 == 2 && loading == 500)
							loading = 1000;
						else if (UserOrder2 == 2 && loading == 1000)
							loading = 2000;
						else if (UserOrder2 == 2 && loading == 2000)
							loading = 1;
						if (UserOrder2 == 3 && bardot == '*')
							bardot = '0';
						else if (UserOrder2 == 3 && bardot == '0')
							bardot = '#';
						else if (UserOrder2 == 3 && bardot == '#')
							bardot = '@';
						else if (UserOrder2 == 3 && bardot == '@')
							bardot = '*';
						if (UserOrder2 == 4 && textSpeed == 0)
							textSpeed = 8;
						else if (UserOrder2 == 4 && textSpeed == 8)
							textSpeed = 13;
						else if (UserOrder2 == 4 && textSpeed == 13)
							textSpeed = 18;
						else if (UserOrder2 == 4 && textSpeed == 18)
							textSpeed = 0;
					}
					continue MainGameLoop;
				case 7:										// Game information
					Manual intro = new Manual(User);
					break;
				case 5:										//autosave
					File ff = new File("save.txt"); //create the file
					FileWriter fw = new FileWriter(ff);
					PrintWriter out = new PrintWriter(fw);
					out.println(sa);out.println(sb);out.println(sc);out.println(sd);	// location 4
					out.println(lv);out.println(expmax);out.println(increment);out.println(increment2);
					out.println(money);	// location 9
					out.println(skstab);out.println(skdoublestrike);out.println(skheal);out.println(skdebuff);out.println(skpsionicstorm);out.println(skallin);
					out.println(wep);out.println(arm);out.println(head);	//loc 17
					for (int i = 0; i < 10; i++)
						out.println(wep_stat[i]); //out to the file
					out.println(difficulty);	//location 28
					out.println(mobCount);
					out.println(TimeMillisSave);	//loc 30
					out.println(Exptot);
					out.println(exp);
					out.println(User);
					out.println(chrystal);
					out.println(montot);	//35
					out.println(atktot);
					out.println(kerriganbeat);
					out.println(dietot);
					out.println(skilltot);
					out.println(misstot);	 // location 40
					out.println(Speed);
					out.println(TimerBegin);
					out.println(clearscreen);
					out.println(loading);
					out.println(bardot);	//45
					out.println(textSpeed);
					out.close();
					for (int k = 0;k<clearscreen ;k++ )
						System.out.println("");
					System.out.println("Successfully saved to your installation directory");
					System.out.println("Type any key to continue");
					anykey = s.next();
					continue MainGameLoop;
				case 486:										// secret code
					System.out.println("God Mode initialized");
					sa = 128;
					sb = 128;
					sc =128;
					sd = 128;
					lv = 100;
					expmax = 50000;
					increment = 3000;
					increment2 = 200;
					money = 250000;
					skstab = 8;
					skdoublestrike = 12;
					skheal = 15;
					skdebuff = 15;
					skpsionicstorm = 15;
					User = "G.O.D";
					continue MainGameLoop;
				/**************************************************************stats tab*************************************************************************/
				case 1 :	
					Statloop: while (true)
					{
						System.out.println("		-----"+User+"'s Character Information-----");
						System.out.println("			L.V: "+lv);
						System.out.println("			EXP: "+exp+"/"+expmax);
						hpb = hpbar((double)exp/(double)expmax, bardot);
						System.out.println("			"+hpb);
						System.out.println("			Cash: $"+money+"			Crystal: "+chrystal);
						System.out.println("");
						System.out.println("Health Point:	"+(int)hp);
						System.out.println("Mana Point:	"+(int)mp);
						System.out.println("Attack:		"+(int)atkmin+"~"+(int)atkmax);
						System.out.println("Special Attack:	"+(int)spatk);
						System.out.println("Defence:	"+(int)def);
						System.out.println("Accuracy:	"+(int)acc);
						System.out.println("Speed:		"+(int)speed);
						System.out.println("");
						System.out.println("Strength:	"+(int)str);
						System.out.println("Agility:	"+(int)dex);
						System.out.println("Intelligence:	"+(int)intel);
						System.out.println("Stamina:	"+(int)sta);
						System.out.println("");
						System.out.println("		Weapon:	"+wep);
						System.out.println("		Armor:	"+arm);
						System.out.println("		Head:   "+head);
						System.out.println("");
						System.out.println("[2] Skill Tab			[3] Statistics			 [0] Escape.");
						UserOrder2 = s.nextInt();
						/***********************************************************skill tab*****************************************************************************/
						if (UserOrder2 == 2)
						{
							for (int k = 0;k<clearscreen ;k++ )
								System.out.println("");
								if (lv < 6)
							{
								for (int k = 0;k<clearscreen ;k++ )
									System.out.println("");
								System.out.println("Skills are available after lv 5.");
								System.out.println("Enter any key to return");
								anykey = s.next();
								continue Statloop;
							}
							System.out.println("Skills tab all skills heavily rely on special attack points.");
							System.out.println("percentage data is not a reliable information.");
							System.out.println("");
							SKT = new SkillTab(skstab, skdoublestrike, skheal, skdebuff, skpsionicstorm, skallin);
							SKT.print();
							System.out.println(""); System.out.println(""); System.out.println("");
							System.out.println("Enter any key to return");
							anykey = s.next();
							continue Statloop;
						}
						if (UserOrder2 == 3)
						{
							currenttime = System.currentTimeMillis()+Timeplus;
							TimeMillisSave = currenttime-starttime;
							int score = (int)(((currenttime-starttime)/1000)+((double)montot/100)+((double)Exptot/10)+((double)mobCount*100)+(atktot*5)+(skilltot*5)+(lv*400));
							for (int k = 0;k<clearscreen ;k++ )
								System.out.println("");
							System.out.println("				[Statistics]					");
							System.out.println("	 -----Summary-----");
							System.out.println("Time Played : "+(int)((currenttime-starttime)/3600000)+" hrs, "+(((currenttime-starttime)/60000)%60)+" mins");
							System.out.println("Total Score :	"+(int)(((currenttime-starttime)/1000)+((double)montot/100)+((double)Exptot/10)+((double)mobCount*100)+(atktot*5)+(skilltot*5)+(lv*400)));
							System.out.println("");
							System.out.println("	 -----Resources-----");
							System.out.println("Total EXP gained :	"+Exptot);
							System.out.println("Total money earned :	"+montot);
							System.out.println("");
							System.out.println("	 -----Battle-----");
							System.out.println("Times you died :	"+dietot);
							System.out.println("Enimies defeated :	"+mobCount);
							System.out.println("Total attacks you did :	"+atktot);
							System.out.println("Total skills you used :	"+skilltot);
							System.out.println("Accuracy rate :		"+(100-(((double)Math.round(((double)misstot/(double)(atktot+skilltot))*1000))/10))+"%");
							System.out.println("Mean Turns on battle :	"+(double)((double)((int)(((double)(atktot+skilltot+misstot)/(double)(mobCount+dietot))*10))/10)+"");
							System.out.println("");
							System.out.println("	 -----Character Specialization-----");
							if (atktot>(skilltot*1.4) && lv>15){System.out.print("Melee Fighter			");}else {System.out.print("				");} if (skilltot>(atktot) && lv>15){System.out.println("Spell Caster");}else {System.out.println("				");}
							if ((100-(((double)Math.round(((double)misstot/(double)(atktot+skilltot))*1000))/10))>94 && lv>5){System.out.print("Archer				");}else {System.out.print("				");} if ((double)((int)(((double)(atktot+skilltot+misstot)/(double)(mobCount+dietot))*10)/10)<5 && lv>5){System.out.println("Assassin");}else {System.out.println("				");}
							if (((double)dietot/(double)mobCount)<0.1 && lv>15){System.out.print("Immortal				");}else {System.out.print("				");} if (dex>((double)(sta+str+intel)/2.6)){System.out.println("Athlete");}else {System.out.println("				");}
							if (((currenttime-starttime)/3600000)>3){System.out.print("Game's Fan			");}else {System.out.print("				");} if (score>200000){System.out.println("Legend");}else if (score>100000){System.out.println("Expert");}else if (score>50000){System.out.println("Veteran");}else {System.out.println("				");}
							if (str>((double)(dex+sta+intel)/2.6)){System.out.print("Heavy Striker			");}else {System.out.print("				");} if (lv>70){System.out.println("Master");}else {System.out.println("				");}
							if (kerriganbeat==true){System.out.print("Kerrigan Defeater		");}else {System.out.print("				");} if (kerriganbeat==true){System.out.println("Kerrigan Defeater		");}else {System.out.println("				");}
							System.out.println("Enter any key to return");
							anykey = s.next();
							continue Statloop;
						}
						else
							continue MainGameLoop;
					}
				/***********************************************************Statistics*****************************************************************************/
				/********************************************************************shop tab**********************************************************************/
				case 2 :				
				{
					ShopLoop: while (true)
					{
						for (int y = 0;y<clearscreen ;y++ )
							System.out.println("");
						System.out.println("			welcome to Gear shop.");
						System.out.println("");
						System.out.println("");
						System.out.println("		Your Cash: $"+money);
						System.out.println("		Crystal : "+chrystal);
						System.out.println("");
						System.out.println("");
						System.out.println("		Weapon Shop:	[1]");
						System.out.println("");
						System.out.println("		Armor Shop :	[2]");
						System.out.println("");
						System.out.println("		HeadGear Shop : [3]");
						System.out.println("");
						System.out.println("		Forge :		[4]");
						if (kerriganbeat == true)
						{
							System.out.println("");
							System.out.println("		Secret Shop :	[5]");
						}
						System.out.println("");
						System.out.println("		Escape : 	[0]");
						System.out.println("");
						System.out.println("");
						System.out.println("");
						UserOrder2 = s.nextInt();
						/***************************************************************Weapon Shop****************************************************************/
						if (UserOrder2 == 1)			//weapon
						{
							for (int vic = 0;vic<clearscreen ;vic++ )
								System.out.println("");
							System.out.println("				Weapon Shop");
							System.out.println("");
							System.out.println("Difficulty: How difficult is weapon for warrier to meanuver it(Affects Accuracy)");
							System.out.println("Type number corresponding to the weapon to purchase it. Else, type 0 to escape.");
							System.out.println("");
							System.out.println("100.Club $500:		attack 24,    sp.attack 18,    difficulty 13");
							System.out.println("");
							System.out.println("101.Shinai $2000:	attack 32,    sp.attack 23,    difficulty 30");
							System.out.println("");
							System.out.println("102.QuarterStaff$2.5k:	attack 26,    sp.attack 26,    difficulty 17");
							System.out.println("");
							System.out.println("103.Javelin $5500:	attack 32,    sp.attack 25,    difficulty 23");
							System.out.println("");
							System.out.println("104.war bow $10k:	attack 36,    sp.attack 28,    difficulty 25");
							System.out.println("");
							System.out.println("105.BroadSword $18k:	attack 40,    sp.attack 32,    difficulty 35");
							System.out.println("");
							System.out.println("106.CrossBow $25k:	attack 42,    sp.attack 33,    difficulty 23");System.out.println("");
							System.out.println("107.OblivionStaff$37k:	attack 41,    sp.attack 40,    difficulty 42");System.out.println("");
							System.out.println("108.MithrillHammer$43k:	attack 46,    sp.attack 37,    difficulty 35");System.out.println("");
							System.out.println("109.Excaliber $72k:	attack 52,    sp.attack 42,    difficulty 40");
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
								{ money = money - 2000;	 wep = "Shinai"; wep_stat[0] = 32;	wep_stat[1] = 23; difficulty = 30;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;}
								break;
							case 102:
								if (money>=2500)
								{	money = money - 2500;	 wep = "QuarterStaff"; wep_stat[0] = 26;	wep_stat[1] = 26; difficulty = 17;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
							case 103:
								if (money>=5500)
								{	money = money - 5500;	 wep = "Javelin"; wep_stat[0] = 32;	wep_stat[1] = 25; difficulty = 23;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
							case 104:
								if (money>=10000)
								{	money = money - 10000;	 wep = "War Bow"; wep_stat[0] = 36;	wep_stat[1] = 28; difficulty = 25;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
							case 105:
								if (money>=18000)
								{	money = money - 18000;	 wep = "BroadSword"; wep_stat[0] = 40;	wep_stat[1] = 32; difficulty = 35;
									System.out.println("Purchase Successful! Enter any number to escape");anykey = s.next(); continue MainGameLoop;	}
								break;
							case 106:
								if (money>=25000)
								{	money = money - 25000;	 wep = "CrossBow"; wep_stat[0] = 42;	wep_stat[1] = 33; difficulty = 23;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
							case 107:
								if (money>=37000)
								{	money = money - 37000;	 wep = "Obvilion Staff"; wep_stat[0] = 41;	wep_stat[1] = 40; difficulty = 42;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
							case 108:
								if (money>=45000)
								{	money = money - 45000;	 wep = "Mithrill Hammer"; wep_stat[0] = 46;	wep_stat[1] = 37; difficulty = 35;
									System.out.println("Purchase Successful! Enter any number to escape");anykey = s.next(); continue MainGameLoop;	}
								break;
							case 109:
								if (money>=72000)
								{	money = money - 72000;	 wep = "Excaliber"; wep_stat[0] = 52;	wep_stat[1] = 42; difficulty = 40;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
							}
						}
						/***************************************************************Armor Shop****************************************************************/
						else if (UserOrder2 ==2)
						{
							for (int vic = 0;vic<clearscreen ;vic++ )
								System.out.println("");
							System.out.println("				Armor Shop");
							System.out.println("");
							System.out.println("Type number corresponding to the armor to purchase it. Else, type 0 to escape.");
							System.out.println("");
							System.out.println("201.Poor'sClothes $300:		def 2");System.out.println("");
							System.out.println("202.T-shirt $1100:		def 5");System.out.println("");
							System.out.println("203.Chian Mail $3500:		def 15,	hp 50");System.out.println("");
							System.out.println("204.Plate Mail $7500:		def 18,	Stamina 3,	 hp 80");System.out.println("");
							System.out.println("205.Vanguard $17k:		def 32,	Stamina 5");System.out.println("");
							System.out.println("206.Shiva'sGuard $23k:		def 40,	hp 200");System.out.println("");
							System.out.println("207.Heart Of Tarrasque$42k:	def 60,	Stamina 5,	 hp 200");System.out.println("");
							System.out.println("208.Armor of Cynthia $80k:		def 120");System.out.println("");
							UserOrder2 = s.nextInt();
							System.out.println("");
							switch (UserOrder2)
							{
								case 0:
								break;
								case 201:
								if (chrystal>=20)
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
								{	money = money - 7500;	 arm = "Plate Mail"; wep_stat[2] = 18;	wep_stat[6] = 3; wep_stat[7] = 80;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 205:
								if (money>=17000)
								{	money = money - 17000;	 arm = "Vanguard"; wep_stat[2] = 32;	wep_stat[6] = 5; wep_stat[7] = 0;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 206:
								if (money>=23000)
								{	money = money - 23000;	 arm = "Shiva's Guard"; wep_stat[2] = 40;	wep_stat[6] = 0; wep_stat[7] = 200;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 207:
								if (money>=42000)
								{	money = money - 42000;	 arm = "Heart Of Tarrasque"; wep_stat[2] =  60;	wep_stat[6] = 5; wep_stat[7] = 200;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 208:
								if (money>=80000)
								{	money = money - 80000;	 arm = "Armor of Cynthia"; wep_stat[2] =  120;	wep_stat[6] = 0; wep_stat[7] = 0;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
							}
						}
						/***************************************************************HeadGear Shop****************************************************************/
						else if (UserOrder2 ==3)
						{
							for (int vic = 0;vic<clearscreen ;vic++ )
								System.out.println("");
							System.out.println("				HeadGear Shop");
							System.out.println("");
							System.out.println("Type number corresponding to the Headgear to purchase it. Else, type 0 to escape.");
							System.out.println("");
							System.out.println("301.Bandana $1500:		agi 2,	acc 5");System.out.println("");
							System.out.println("302.Sobi Mask $4500:		int 2,	mp 20,	acc 4");System.out.println("");
							System.out.println("303.Cap of Madness $8k:		str 3,	agi 1,	acc 8");System.out.println("");
							System.out.println("304.Helm of Legolas $16k:	agi 4,	int 1,	mp 30,	acc 18");System.out.println("");
							System.out.println("305.Helm of Dominator $21k:	str4,	int 3,	acc 12");System.out.println("");
							System.out.println("306. Unobtanium Helmet $ 40k:	str 3,	 agi 3, int 3");System.out.println("");
							System.out.println("307.Helmet of JACOOM $ 120k:	str 5,	 agi 5, int 5, acc 15");System.out.println("");
							UserOrder2 = s.nextInt();
							System.out.println("");
							switch (UserOrder2)
							{
								case 0:
								break;
								case 301:
								if (money>=1500)
								{	money = money - 1500;	 head = "Bandana"; wep_stat[3] = 0;	wep_stat[4] = 2; wep_stat[5] = 0; wep_stat[8] = 0; wep_stat[9] = 5;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 302:
								if (money>= 4500)
								{	money = money - 4500;	 head = "Sobi Mask"; wep_stat[3] = 0;	wep_stat[4] = 0; wep_stat[5] = 2; wep_stat[8] = 20; wep_stat[9] = 4;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 303:
								if (money>=8000)
								{	money = money - 8000;	 head = "Cap of Madness"; wep_stat[3] = 3;	wep_stat[4] = 1; wep_stat[5] = 0; wep_stat[8] = 0; wep_stat[9] = 8;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 304:
								if (money>=16000)
								{	money = money - 16000;	 head = "Helm of Legolas"; wep_stat[3] = 0;	wep_stat[4] = 4; wep_stat[5] = 1; wep_stat[8] = 30; wep_stat[9] = 18;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 305:
								if (money>=21000)
								{	money = money - 21000;	 head = "Helm of Dominator"; wep_stat[3] = 4;	wep_stat[4] = 0; wep_stat[5] = 3; wep_stat[8] = 0; wep_stat[9] = 12;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 306:
								if (money>=40000)
								{	money = money - 40000;	 head = "Helmet of JACOOM"; wep_stat[3] = 3;	wep_stat[4] = 3; wep_stat[5] = 3; wep_stat[8] = 0; wep_stat[9] = 0;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 307:
								if (money>=120000)
								{	money = money - 120000;	 head = "Althria's Protection"; wep_stat[3] = 5;	wep_stat[4] = 5; wep_stat[5] = 5; wep_stat[8] = 0; wep_stat[9] = 15;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
							}
						}
						else if (UserOrder2 ==4)
						{
							for (int vic = 0;vic<clearscreen ;vic++ )
								System.out.println("");
							System.out.println("				Armor Upgrading Forge");
							System.out.println("");
							System.out.println("Type number corresponding to the Type of Upgrade to do. Else, type 0 to escape.");
							System.out.println("");
							System.out.println("401.Purchase 1 Crystal				$22000");System.out.println("");
							System.out.println("402.Purchase 5 Crystals				 $100000");System.out.println("");
							System.out.println("403.Upgrade Weapon's attack Cx3:	Weapon_attack+4");System.out.println("");
							System.out.println("404.Weapon Weight Lightening Cx2:	Weapon_difficulty-5");System.out.println("");
							System.out.println("405.Strengthen Material of Armor Cx2:	Armor_defence+5");System.out.println("");
							System.out.println("406.Tease weapon up Cx3:		Weapon_sp.atk+3");System.out.println("");
							System.out.println("407.Inject Adrenalin Cx4:		HeadGear_str,agi,int+1");System.out.println("");
							UserOrder2 = s.nextInt();
							System.out.println("");
							/****************************************************Forge*************************************************************/
							switch (UserOrder2)
							{
								case 0:
								break;
								case 401:
								if (money>=22000)
								{	money = money - 22000;	 chrystal=chrystal+1;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 402:
								if (money>= 100000)
								{	money = money - 100000;	 chrystal=chrystal+5;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 403:
								if (chrystal>=3)
								{	chrystal = chrystal - 3;	 wep=wep+"*"; wep_stat[0] = wep_stat[0]+4;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 404:
								if (chrystal>=1)
								{	chrystal = chrystal - 2;	 wep=wep+"*"; difficulty = difficulty-5;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 405:
								if (chrystal>=2)
								{	chrystal = chrystal - 2;	 arm=arm+"*"; wep_stat[2] = wep_stat[2]+5;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 406:
								if (chrystal>=2)
								{	chrystal = chrystal - 3;	 wep=wep+"*"; wep_stat[1] = wep_stat[1]+3;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 407:
								if (chrystal>=4)
								{	chrystal = chrystal - 4;	 head = head+"*"; wep_stat[3] = wep_stat[3]+1;	wep_stat[4] = wep_stat[4]+1; wep_stat[5] = wep_stat[5]+1;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
							}
						}
						else if (UserOrder2 == 0)
						{
							continue MainGameLoop;
						}
						else if (UserOrder2 ==5 && kerriganbeat == true)
						{
							for (int vic = 0;vic<clearscreen ;vic++ )
								System.out.println("");
							System.out.println("				Ultimate Weapon Shop");
							System.out.println("");
							System.out.println("Type number corresponding to the armor to purchase it. Else, type 0 to escape.");
							System.out.println("");
							System.out.println("501.Poor'sClothes $300:		def 2");System.out.println("");
							System.out.println("502.T-shirt $1100:		def 5");System.out.println("");
							System.out.println("503.Chian Mail $3500:		def 15,	hp 50");System.out.println("");
							System.out.println("504.Plate Mail $7500:		def 18,	Stamina 3,	 hp 80");System.out.println("");
							System.out.println("505.Vanguard $17k:		def 32,	Stamina 5");System.out.println("");
							System.out.println("506.Shiva'sGuard $23k:		def 40,	hp 200");System.out.println("");
							System.out.println("507.Heart Of Tarrasque$42k:	def 60,	Stamina 5,	 hp 200");System.out.println("");
							System.out.println("508.Armor of Cynthia $80k:		def 100");System.out.println("");
							UserOrder2 = s.nextInt();
							System.out.println("");
							switch (UserOrder2)
							{
								case 0:
								break;
								case 501:
								if (money>=150000 && chrystal>=10)
								{	money = money - 150000;	chrystal = chrystal-10; wep = "Excaliber"; wep_stat[0] = 52;	wep_stat[1] = 42; difficulty = 40;
									System.out.println("Purchase Successful! Enter any number to escape");anykey = s.next(); continue MainGameLoop;	}
								break;
								case 502:
								if (money>=1100)
								{	money = money - 1100;	 arm = "T-shirt"; wep_stat[2] = 5;	wep_stat[6] = 0; wep_stat[7] = 0;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next();continue MainGameLoop;	}
								break;
								case 503:
								if (money>=3500)
								{	money = money - 3500;	 arm = ""; wep_stat[2] = 15;	wep_stat[6] = 0; wep_stat[7] = 50;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 504:
								if (money>=7500)
								{	money = money - 7500;	 arm = "Cynthia's Guard"; wep_stat[2] = 18;	wep_stat[6] = 3; wep_stat[7] = 80;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 505:
								if (money>=17000)
								{	money = money - 17000;	 arm = "Armor"; wep_stat[2] = 32;	wep_stat[6] = 5; wep_stat[7] = 0;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 506:
								if (money>=23000)
								{	money = money - 23000;	 arm = "Althria's Aura"; wep_stat[2] = 40;	wep_stat[6] = 0; wep_stat[7] = 200;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 507:
								if (money>=42000)
								{	money = money - 42000;	 arm = "Protection of G.O.D"; wep_stat[2] =  60;	wep_stat[6] = 5; wep_stat[7] = 200;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
								case 508:
								if (money>=80000)
								{	money = money - 80000;	 arm = "Stat by 5%"; wep_stat[2] =  100;	wep_stat[6] = 0; wep_stat[7] = 0;
									System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue MainGameLoop;	}
								break;
							}
						}
					}
				}
				/***********************************************************************   INTO THE BATTLEFIELD!!!!!!   ******************************************************************************/
				case 3:
				/***************************************************Load Battle statistics*************************************************/
					String mname = "default";
					double mhp = 1;	//mob statistics
					double mmp = 1;
					double matk = 0;
					double matkm = 0;
					double mdef = 0;
					double mskill = 0;			//skills
					double mbolt = 0;
					double mheal = 0;
					double mspskill = 0;			// special skills
					double mpoison = 0;
					double mdrain = 0;
					double mflee = 0;				// flee
					boolean poison = false;
					double msp = 0;
					double sp = speed;
					int mexp = 0;					//reward
					int mmon = 0;
					double damage = 0;		//AI
					double hit = 0;
					double AI = 0;			// only accepts 1~10
					double AIdmg = 1;
					int kerrigan = 0;				// kerrigan variables
					int kb = 0;
					int kc = 0;
					int kd = 0;
					int ke = 0;
					boolean kerriganb = false;
					String printsave = "";
				/****************************************end of loading batttle statistics***************************************/
				/*******************************************************Map Selection****************************************************************/
				BattleLoop: while(true)
				{
					for (int k = 0;k<clearscreen ;k++ )
						System.out.println("");
					System.out.println("				Battle Field");System.out.println("");
					System.out.println("");
					System.out.println("");
					System.out.println("				Select Map (1~4)");
					System.out.println("");
					System.out.println("");
					System.out.println("		[1] Leaf Island		(lv 1~12)");System.out.println("");
					System.out.println("		[2] Lawless City	(lv 15~30)");System.out.println("");
					System.out.println("		[3] Helm's deep Dungeon	(lv 30~50)");System.out.println("");
					System.out.println("		[4] Hive Cluster	(lv 40~)");System.out.println("");System.out.println("");
					System.out.println("");
					System.out.println("			[0] escape");
					System.out.println("");
					System.out.println("");
					System.out.println("");
					UserOrder2 = s.nextInt();
					for (int k = 0;k<clearscreen ;k++ )
						System.out.println("");
				/******************************************************Gathering Battle Information(enemy stats)************************************************************/
						switch (UserOrder2)
						{
						default :
							continue MainGameLoop;
						case 1:
							System.out.println("				Leaf Island");
							System.out.println("");
							System.out.println("			Select Monster to fight	escape:[0]");System.out.println("");System.out.println("");
							System.out.println("11.Snail	lv1		hp 21		exp 10		Reward $200");System.out.println("");
							System.out.println("12.Mushroom	lv2		hp 55		exp 22		Reward $300");System.out.println("");
							System.out.println("13.GuardMan	lv4		hp 85		exp 31		Reward $450");System.out.println("");
							System.out.println("14.Zergling	lv6		hp 120		exp 48		Reward $600");System.out.println("");
							System.out.println("15.Slime	lv8		hp 175		exp 68		Reward $800");System.out.println("");
							System.out.println("16.Viper	lv10		hp 280		exp 90		Reward $950");System.out.println("");
							System.out.println("17.Zealot	lv13		hp 320		exp 124		Reward $1100");System.out.println("");
							UserOrder2 = s.nextInt();
							switch (UserOrder2)
							{
							default :
								continue BattleLoop;
							case 11:
								mname="Snail";	mhp=21;	mmp=1;		matk=10;	matkm = 12;	mdef=0;		mskill=0;	mbolt=20;	mheal=10;		mflee=0;msp = 20;	mexp=10;	mmon=200;	break BattleLoop;
							case 12:
								mname="Mushroom";	mhp=55;	mmp=10;		matk=13;	matkm = 15;	mdef=1;		mskill=0;	mbolt=20;	mheal=10;		mflee=2;msp = 30;	mexp=22;	mmon=300;	break BattleLoop;
							case 13:
								mname="GuardMan";	mhp=85;	mmp=15;		matk=17;	matkm = 20;	mdef=1;		mskill=0;	mbolt=28;	mheal=15;		mflee=5;msp = 0;	mexp=31;	mmon=450;	break BattleLoop;
							case 14:
								mname="Zergling";	mhp=120;	mmp=50;		matk=22;	matkm = 29;	mdef=2;		mskill=3;	mbolt=36;	mheal=34;		mflee=5;msp = 58;	mexp=48;	mmon=600;	break BattleLoop;
							case 15:
								mname="Slime";mhp=175;	mmp=30;		matk=33;	matkm = 36;	mdef=5;		mskill=0;	mbolt=20;	mheal=10;		mflee=35;msp = 60;	mexp=68;	mmon=800;	break BattleLoop;
							case 16:
								mname="Viper";	mhp=280;	mmp=200;		matk=40;	matkm = 48;	mdef=4;		mskill=5;	mbolt=54;	mheal=45;		mflee=6;msp = 70;	mexp=90;	mmon=950;	break BattleLoop;
							case 17:
								mname="Zealot";	mhp=320;	mmp=55;		matk=62;	matkm =70;	mdef=28;		mskill=3;	mbolt=60;	mheal=30;		mflee=5;msp = 60;	mexp=124;	mmon=1100;	break BattleLoop;
							}
						case 2 :
							System.out.println("			Lawless City");
							System.out.println("");
							System.out.println("			Select convicts to fight	escape:[0]");System.out.println("");System.out.println("");
							System.out.println("21.Bandit	lv14	 	hp 500		exp 145		Reward $1250");System.out.println("");
							System.out.println("22.SpearMan	lv17		hp 700		exp 175		Reward $1450");System.out.println("");
							System.out.println("23.DeadWarrier	lv19		hp 880		exp 202		Reward $1650");System.out.println("");
							System.out.println("24.Tassadar	lv23		hp 1570		exp 255		Reward $1900");System.out.println("");
							System.out.println("25.Thief	lv25		hp 1100		exp 280		Reward $2300");System.out.println("");
							System.out.println("26.Spell Caster	lv27		hp 2000		exp 340		Reward $2600");System.out.println("");
							System.out.println("27.Issac Clarke	lv29		hp 2500		exp 430		Reward $3000");System.out.println("");
							UserOrder2 = s.nextInt();
							switch (UserOrder2)
							{
							default :
								continue BattleLoop;
							case 21:
								mname="Bandit";	mhp=500;	mmp=80;		matk=75;	matkm = 82;	mdef=5;		mskill=2.5;	mbolt=71;	mheal=75;		mflee=22;msp = 100;	mexp=145;	mmon=1250;	break BattleLoop;
							case 22:
								mname="Spearman";	mhp=750;	mmp=100;		matk=100;	matkm = 130;	mdef=10;		mskill=3.5;	mbolt=100;	mheal=200;msp = 60;		mflee=10;	mexp=175;	mmon=1450;	break BattleLoop;
							case 23:
								mname="DeadWarrier";	mhp=880;	mmp=50;		matk=120;	matkm = 145;	mdef=40;		mskill=1;	mbolt=167;	mheal=220;msp = 58;		mflee=8;	mexp=202;	mmon=1650;	break BattleLoop;
							case 24:
								mname="Tassadar";	mhp=1570;	mmp=220;		matk=90;	matkm = 150;	mdef=8;		mskill=7;	mbolt=148;	mheal=400;		mflee=13;msp = 72;	mexp=255;	mmon=1900;	break BattleLoop;
							case 25:
								mname="Thief";mhp=1100;	mmp=100;		matk=150;	matkm = 180;	mdef=0;		mskill=3;	mbolt=187;	mheal=250;		mflee=45;msp = 130;	mexp=280;	mmon=2300;	break BattleLoop;
							case 26:
								mname="Spell Caster";	mhp=2000;	mmp=400;		matk=140;	matkm = 200;	mdef=25;		mskill=6;	mbolt=182;	mheal=400;		mflee=30;msp = 82;	mexp=340;	mmon=2600;	break BattleLoop;
							case 27:
								mname="Issac Clarke";	mhp=2500;	mmp=160;		matk=170;	matkm = 186;	mdef=90;		mskill=3.5;	mbolt=194;	mheal=350;		mflee=10;msp = 90;	mexp=430;	mmon=3000;	break BattleLoop;
							}
							case 3 :
								System.out.println("			Helm's Deep Deongeon");
								System.out.println("");
								System.out.println("			Select Named Mobs to fight	escape:[0]");System.out.println("");System.out.println("");
								System.out.println("31.Hydralisk	lv30		hp 3300		exp 400		Reward $3000");System.out.println("");
								System.out.println("32.Skeleton	lv32		hp 3990		exp 475		Reward $2600");System.out.println("");
								System.out.println("33.Priestess	lv34		hp 5250		exp 570		Reward $2900");System.out.println("");
								System.out.println("34.Naga Siren	lv37		hp 6800		exp 710		Reward $3100");System.out.println("");
								System.out.println("35.Wild Cago	lv40		hp 8000		exp 850		Reward $3300");System.out.println("");
								System.out.println("36.Junior Valok	lv43		hp 10000	exp 1000	Reward $3500");System.out.println("");
								System.out.println("37.JACOOM	lv48		hp 15000	exp 1400	Reward $4000");System.out.println("");
								UserOrder2 = s.nextInt();
								switch (UserOrder2)
								{
								default :
									continue BattleLoop;
								case 31:
									mname="Hydralisk";	mhp=3300;	mmp=120;		matk=197;	matkm = 226;	mdef=15;		mskill=3;	mbolt=196;	mheal=626;		mflee=12;msp = 85;	mexp=400;	mmon=3000;	break BattleLoop;
								case 32:
									mname="Skeleton";	mhp=3990;	mmp=90;		matk=170;	matkm = 280;	mdef=10;		mskill=5;	mbolt=193;	mheal=120;		mflee=33;msp = 97;	mexp=475;	mmon=3300;	break BattleLoop;
								case 33:
									mname="Priestess";	mhp=5250;	mmp=250;		matk= 234;	matkm = 250;	mdef=35;		mskill=7;	mbolt=241;	mheal=500;		mflee=10;msp = 86;	mexp=570;	mmon=3700;	break BattleLoop;
								case 34:
									mname="Naga Siren";	mhp=6800;	mmp=200;		matk=258;	matkm = 280;	mdef=30;		mskill=2;	mbolt=326;	mheal=750;		mflee=17;msp = 100;	mexp=710;	mmon=4200;	break BattleLoop;
								case 35:
									mname="Wild Cago";mhp=8000;	mmp=100;		matk=298;	matkm = 325;	mdef=15;		mskill=1.5;	mbolt=513;	mheal=900;		mflee=26;msp =122;	mexp=850;	mmon= 5000;	break BattleLoop;
								case 36:
									mname="Junior Valok";	mhp=10000;	mmp=140;		matk=290;	matkm = 340;	mdef=50;		mskill=8;	mbolt=768;	mheal=2000;		mflee=10;msp = 106;	mexp=1000;	mmon=6000;	break BattleLoop;
								case 37:
									mname="JACOOM";	mhp=15000;	mmp=260;		matk=375;	matkm = 412;	mdef=220;		mskill=4;	mbolt=401;	mheal=2400;		mflee=8;msp = 100;	mexp=1400;	mmon=8000;	break BattleLoop;	
							}
							case 4 :
								System.out.println("			Hive Cluster");
								System.out.println("");
								System.out.println("");
								System.out.println("			Select Kerrigan's forces to fight.	escape:[0]");
								System.out.println("		They are unbelievably strong. Be careful.");System.out.println("");System.out.println("");
								System.out.println("41.SpineCrawler	lv40	hp 8000		exp 1250	Reward $4000");System.out.println("");
								System.out.println("42.Mutalisk	lv44	hp 12000	exp 1500	Reward $5000");System.out.println("");
								System.out.println("43.Torrasque	lv48	hp 15500	exp 1750	Reward $6000");System.out.println("");
								System.out.println("44.HunterKiller	lv52	hp 20000	exp 2250	Reward $7000");System.out.println("");
								if (kerriganbeat==false){System.out.println("");System.out.println("");System.out.println("");System.out.println("		50.KERRIGAN	lv60	 hp ???	exp ???	Reward $???");}else {System.out.println("50.Kerrigan	lv60	 hp 45480	exp 4000	Reward $10000");}
								System.out.println("");
								UserOrder2 = s.nextInt();
								switch (UserOrder2)
								{
								default :
									continue BattleLoop;
								case 41:
									kerriganb = true;
									mname="Spine Crawler";	mhp= 8000;	mmp=110;		matk=250;	matkm = 280;	mdef=155;		mskill=3;	mbolt=244;	mheal=1202;		mflee=0;msp = 100;	mexp=1250;	mmon=4000;	break BattleLoop;
								case 42:
									kerriganb = true;	kerrigan = 4;
									mname="Mutalisk";	mhp=	12000;	mmp=160;		matk=360;	matkm = 380;	mdef=15;		mskill=4;	mbolt=420;	mheal=1535;		mflee=30;msp = 130;	mexp=1500;	mmon=5000;	break BattleLoop;
								case 43:
									kerriganb = true;	kb = 5;
									mname="Torrasque";	mhp=15500;	mmp=100;		matk=480;	matkm = 530;	mdef=350;		mskill=2;	mbolt=737;	mheal=2500;		mflee=10;msp = 120;	mexp=1750;	mmon=6000;	break BattleLoop;
								case 44:
									kerriganb = true;	kc = 5;
									mname="Hunter Killer";	mhp=20000;	mmp=230;		matk=500;	matkm = 600;	mdef=60;		mskill=8;	mbolt=546;	mheal=3000;		mflee=26;msp = 160;	mexp=2250;	mmon=7000;	break BattleLoop;
								case 50:
									kerriganb = true;	kd = 5;
									mname="Kerrigan";	mhp=45480;	mmp=1024;		matk=	 600;	matkm = 704;	mdef=150;		mskill=3.5;	mbolt=764;	mheal=3240;		mflee=35;msp = 150;	mexp=4000;	mmon=10000;	break BattleLoop;
								}
						}
					}
					/********************************************************End of Gathering Battle informations****************************************************************/
					/****************************************************************************Battle Simulation**********************************************************************************/
					double mchp = mhp;
					for (int dd = 0;dd<clearscreen ;dd++ )
						System.out.println("");
					System.out.println("			"+User+"   VERSUS   "+mname);
					for (int dd = 0;dd<6 ;dd++ )
						System.out.println("");
					System.out.println(" loading... please wait");
					for (int dd = 0;dd<3 ;dd++ )
						System.out.println("");
					System.out.print("Initializing Battle Variables");for (int b = 0;b<50 ;b++ )System.out.print("\b");delay(loading/3);
					System.out.print("Loading User Input              ");for (int b = 0;b<50 ;b++ )System.out.print("\b");delay(loading/4);
					System.out.print("Loading StatusBar Class");for (int b = 0;b<50 ;b++ )System.out.print("\b");delay(loading/3);
					System.out.print("Clearing Previous Remainder            ");for (int b = 0;b<50 ;b++ )System.out.print("\b");delay(loading/6);
					System.out.print("Cheking Screen Compatability             ");for (int b = 0;b<50 ;b++ )System.out.print("\b");delay(loading/5);
					System.out.print("Simulating Battlefield            ");for (int b = 0;b<50 ;b++ )System.out.print("\b");	delay(loading/2.5);
					/******************************************************Status Bar***********************************************************/
					BLoop : while (true)
					{
						StatusBar BStatus = new StatusBar(User, Gamehp, hp, mname, mchp, mhp, Gamemp, mp, bardot);
						BStatus.print();
						/***********************************************************Recieve User order****************************************************************/
						OrderLoop : while (true)						
						{
							if (speed<msp)				// check speed
							{
								msp = msp-(speed/2);
								break OrderLoop;
							}
							System.out.println("\nYour turn.");
							System.out.println("[1] : Attack");
							System.out.println("[2-7] : Skills");
							System.out.println("[0] : Skill Tab");
							System.out.println("");
							UserOrder2 = s.nextInt();
							skilltot++;
							switch (UserOrder2)
							{
								default:
									System.out.println("Invalid order");
									skilltot--;
									continue OrderLoop;
								case 0 :									// skill tree
									System.out.println("");System.out.println("");System.out.println("");
									SKT = new SkillTab(skstab, skdoublestrike, skheal, skdebuff, skpsionicstorm, skallin);
									SKT.print();
									System.out.println("");
									System.out.println("type any key to go back");
									skilltot--;
									anykey = s.next();
									System.out.println("");System.out.println("");System.out.println("");
									continue BLoop;
								case 1 :
									atktot++;
									skilltot--;
									hit = (int)(Math.random()*100);					//accuracy
									if (hit<(acc-mflee))
									{
										damage = (int)(Math.random()*atkmax%(atkmax-atkmin))+atkmin;
										if (mdef > damage)
											damage = mdef;
										damage = damage-mdef;
										mchp = mchp-damage;
										BStatus = new StatusBar(User, Gamehp, hp, mname, mchp, mhp, Gamemp, mp, bardot);
										BStatus.print();
										System.out.println(User+" Commited an attack on "+mname+"!!");// normal atttack
										for (int line =0;line<2 ;line++ )
												System.out.println("");
										System.out.println("Damage : "+(int)damage+"!!");
									}
									else						//miss
									{
										BStatus = new StatusBar(User, Gamehp, hp, mname, mchp, mhp, Gamemp, mp, bardot);
										BStatus.print();
										for (int line =0;line<3 ;line++ )
											System.out.println("");
										System.out.println(User+"'s attack was missed!!");
										misstot++;
									}
									break;
						/***********************************************User Order - Skills**************************************************/
								case 2:						// stab
									if (Gamemp <  (47+((int)skstab*3)) || skstab == 0)
									{
										System.out.println("You cannot use this skill!!");
										System.out.println("");
										continue OrderLoop;
									}
									Gamemp = Gamemp-(47+((int)skstab*3));								// skill mana
									damage = (int)(((atkmin+atkmax)/2)*1.0f)+(int)(5+skstab*10);				// skill damage
									mchp = mchp - damage;
									BStatus = new StatusBar(User, Gamehp, hp, mname, mchp, mhp, Gamemp, mp, bardot);
									BStatus.print();
									System.out.println("	 "+User+" stabbed "+mname+"!!");
									System.out.println("");
									System.out.println("");
									System.out.println("Damage: "+(int)damage+"!!");
									break;
								case 3:						// double strike
									double[] dm = new double[3];
									if (Gamemp < (89+((int)skdoublestrike*1)) || skdoublestrike == 0)
									{
										System.out.println("You cannot use this skill!!");
										continue OrderLoop;
									}
									
									Gamemp = Gamemp-(89+((int)skdoublestrike*1));
									for (int d = 0;d<2;d++)
									{
										hit = (int)(Math.random()*100);					//accuracy
										if (hit<(acc-mflee))
										{
											damage = ((int)(Math.random()*atkmax%(atkmax-atkmin))+atkmin)*((69+skdoublestrike*1)/160)+spatk*((69+skdoublestrike*1)/130);
											if (mdef>damage)
												damage = mdef;
											dm[d]=damage;
											mchp = mchp - damage+mdef;
										}
										else
										{
											dm[d]=-1;
											System.out.println("miss!!");
											misstot++;
										}
									}
									BStatus = new StatusBar(User, Gamehp, hp, mname, mchp, mhp, Gamemp, mp, bardot);
									BStatus.print();
									System.out.println("	 "+User+" used Magic Claw!!");
									System.out.println("");
									if (dm[0]!=-1)
										System.out.println("Damage: "+(int)(dm[0]-mdef)+"!!");
									else
										System.out.println("miss!!");
									if (dm[1]!=-1)
										System.out.println("Damage: "+(int)(dm[1]-mdef)+"!!");
									else
										System.out.println("miss!!");
									break;

								case 4:							// heal
									if (Gamemp < (177+((int)skheal*3)) || skheal == 0)
									{
										System.out.println("You cannot use this skill!!");
										continue OrderLoop;
									}
									
									Gamemp = Gamemp- (177+((int)skheal*3));
									damage = hp*((19+skheal*0.8)/190)+(Math.sqrt(spatk)/2)*(hp/10)*((19+skheal*0.8)/125);
									if (damage>hp/1.8)
										damage = hp/1.8;
									System.out.println("	 Healed: "+(int)damage+" hp!!");
									Gamehp = Gamehp+damage;
									if (Gamehp>hp)
										Gamehp=hp;
									BStatus = new StatusBar(User, Gamehp, hp, mname, mchp, mhp, Gamemp, mp, bardot);
									BStatus.print();
									System.out.println(User+" Healed himself!!");
									System.out.println("");
									System.out.println("");
									System.out.println("	 Healed: "+(int)damage+" hp!!");
									break;
								case 5:							// debuff
									if (Gamemp < 150 || skdebuff == 0)
									{
										System.out.println("You cannot use this skill!!");
										continue OrderLoop;
									}
									Gamemp = Gamemp-150;
									damage = (8.5+skdebuff*0.5)/100;
									if (damage >= 0.2)
										damage = 0.2;
									matk = matk-matk*damage/1.3;
									matkm = matkm-matkm*damage/1.3;
									mdef = mdef-mdef*damage;
									mflee = mflee-mflee*damage;
									BStatus = new StatusBar(User, Gamehp, hp, mname, mchp, mhp, Gamemp, mp, bardot);
									BStatus.print();
									System.out.println("	 "+User+" Used debuff on "+mname+"!!");
									System.out.println(mname+"'s attack decreased by : "+(int)(matkm*damage/1.3)+"!!");
									System.out.println(mname+"'s defence decreased by : "+(int)(mdef*damage)+"!!");
									System.out.println(mname+"'s flee decreased by : "+(int)(mflee*damage)+"!!");
									break;
								case 6:							// psionic storm
									if (Gamemp < (246+((int)skpsionicstorm*4)) || skpsionicstorm == 0)
									{
										System.out.println("You cannot use this skill!!");
										continue OrderLoop;
									}
									Gamemp = Gamemp- (246+((int)skpsionicstorm*4));
									hit = (int)(Math.random()*100);					//accuracy
									if (hit<(acc-mflee))
									{
										damage = spatk*((300+skpsionicstorm*10)/100)-mdef;
										poison = true;
										mchp = mchp-damage;
										BStatus = new StatusBar(User, Gamehp, hp, mname, mchp, mhp, Gamemp, mp, bardot);
										BStatus.print();
										System.out.println("	 "+User+" spelled PsionicStorm on "+mname+"!!!!");
										System.out.println("");
										System.out.println("Damage: "+(int)damage+"!!");
										System.out.println("The Psionic Energy poisoned "+mname+"!!");
									}
									else
									{
										BStatus = new StatusBar(User, Gamehp, hp, mname, mchp, mhp, Gamemp, mp, bardot);
										BStatus.print();System.out.println("");System.out.println("");System.out.println("");
										System.out.println(mname+" avoided Psionic Storm!!");
										misstot++;
									}
									break;
							}
							if (chk(mchp))					// continue
								break BLoop;
							System.out.println("\ntype any key to continue");
							anykey = s.next();
							break OrderLoop;
						}
						/**************************************************************Artificial Intelligence  - Enemy Turn****************************************/
						CompLoop : while (true)
						{
						/**************************************************Status Bar**************************************/
							BStatus = new StatusBar(User, Gamehp, hp, mname, mchp, mhp, Gamemp, mp, bardot);
							BStatus.print();
							System.out.println(mname+"'s turn.Please wait...");		// ai decision
							System.out.println("\n");
							System.out.println("");
							System.out.println("");
							System.out.println("");
							/**************************************************AI******************************************/
							delay(loading);
							double poisonDamage = spatk/5;
							if (poison == true)
							{
								mchp = mchp - poisonDamage;
							}
							msloop : while (true)
							{
							AI = (Math.random()*10%9)+1;
							/**************************************************attack******************************************/
							if (AI>mskill)
							{
								damage = (int)(Math.random()*matkm%(matkm-matk))+matk;
								if (def > damage)
									damage = def;
								damage = damage-def;
								Gamehp = Gamehp-damage;
								BStatus = new StatusBar(User, Gamehp, hp, mname, mchp, mhp, Gamemp, mp, bardot);
								BStatus.print();
								System.out.println(mname+" commited commercial attack on "+User+"!!");
								System.out.println("");
								System.out.println("");
								System.out.println("Damage : "+(int)damage+"!!");
								break msloop;
								
							}
							/**************************************************Heal******************************************/
							else if ((AI<mskill/3 && mchp<mhp/1.7 ) || (AI<mskill/7 &&mchp<mhp/4) )		// use heal when health is low, in certain possiblity
							{
								if (mmp<60)
									continue msloop; 
								mchp = mchp+mheal;
								mmp = mmp-60;
								BStatus = new StatusBar(User, Gamehp, hp, mname, mchp, mhp, Gamemp, mp, bardot);
								BStatus.print();
								System.out.println(mname+" used heal!!");
								System.out.println("");
								System.out.println("");
								System.out.println("Healed "+(int)mheal+"hp!!");
								break msloop;
							}
							/***********************************************Attack Skill*************************************************/
							else
							{
								if (mmp<50)
								{
									continue msloop;
								}
								
								AI = Math.random();
								if (AI>0.7)
								{
									printsave = "	 Energy Volt!";
									AIdmg = 0.88;
								}
								else if (AI<=0.7 && AI>0.4)
								{
									printsave = "	 Critical Strike!";
									AIdmg = 1;
								}
								else
								{
									System.out.println("");
									printsave = "	 Assasinate!";
									AIdmg = 1.12;
								}
								Gamehp=Gamehp-mbolt;
								mmp = mmp-50;
								BStatus = new StatusBar(User, Gamehp, hp, mname, mchp, mhp, Gamemp, mp, bardot);
								BStatus.print();
								System.out.println(mname+" used skill!!!");
								System.out.println(printsave);System.out.println("");
								System.out.println("Damage : "+(int)mbolt+"!!");
								break msloop;
							}
							}
								if (poison == true)
								{
									System.out.println("poison damage to "+mname+": "+(int)poisonDamage);
								} else {
									System.out.println();
								}
								if (chk(Gamehp))
									break BLoop;
								if (chk(mchp))
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
						mobCount++;
						for (int k = 0;k<clearscreen ;k++ )
							System.out.println("");
						System.out.println("			You Won! You defeated "+mname+"!!");
						System.out.println("		You got "+mexp+" exp and earned  $"+mmon);
						System.out.println("");
						if (Math.random()<(0.1+((double)lv/300)))
						{
							chrystal = chrystal+1;
							System.out.println("		***Congrats! You found a Crystal!***");
						}
						else
							System.out.println("");
						System.out.println("");
						exp = exp+mexp;
						Exptot = Exptot+mexp;
						money = money+mmon;
						montot = montot+mmon;
						System.out.println("");
						System.out.println("		EXP: "+exp+"/"+expmax);
						hpb = hpbar((double)exp/(double)expmax, bardot);
						System.out.println("		"+hpb);
						System.out.println("");
						System.out.println("		Cash: "+money);
						for (int k = 0;k<4 ;k++ )
							System.out.println("");
						System.out.println("type any key to continue");
						anykey = s.next();
						//kerriganb = true; kd = 5; kerrigan = 4; kb = 5; kc = 5;	// enable it to see ending credit
						if (kerriganb == true && kerriganbeat == false)
						{
										if (kd == 5)
										{
											for (int victory = 0;victory<clearscreen ;victory++ )
											{
												System.out.println("");
											}
											kerriganbeat = true;
											tAni = new TextAnimation("			Finally!! You defeated Kerrigan!!");					// Game Victory
											tAni = new TextAnimation("			You made a outstanding revenge on her!");
											tAni = new TextAnimation("			You are the strongest warrier in this java world!");
											System.out.println("");
											System.out.println("");
											System.out.println("");
											System.out.println("");
											delay(loading);
											tAni = new TextAnimation("enter any key to continue...");
											anykey = s.next();
											for (int victory = 0;victory<clearscreen ;victory++ )
											{
												System.out.println("");
											}
											tAni = new TextAnimation("			Extremely Bored Warrier's Adventure"); delay(loading/3);
											tAni = new TextAnimation("");delay(loading/3);
											tAni = new TextAnimation("				Project Lead");delay(loading/3);
											tAni = new TextAnimation("				HongJoon Choi");delay(loading/3);
											tAni = new TextAnimation("");delay(loading/3);
											tAni = new TextAnimation("			Development / Game Design");delay(loading/3);						// Credits
											tAni = new TextAnimation("				HongJoon Choi");delay(loading/3);
											tAni = new TextAnimation("");delay(loading/3);
											tAni = new TextAnimation("			Visualization / Graphics");delay(loading/3);
											tAni = new TextAnimation("				HongJoon Choi");delay(loading/3);
											tAni = new TextAnimation("				JoonRock Choi");delay(loading/3);
											tAni = new TextAnimation("				BonWoong Koo");delay(loading/3);
											tAni = new TextAnimation("");delay(loading/3);
											tAni = new TextAnimation("			Game Programmer");delay(loading/3);
											tAni = new TextAnimation("				HongJoon Choi");delay(loading/3);
											tAni = new TextAnimation("");delay(loading/3);
											tAni = new TextAnimation("			Balance Lead. Assistance");delay(loading/3);
											tAni = new TextAnimation("				HongJoon Choi");delay(loading/3);
											tAni = new TextAnimation("				JoonRock Choi");delay(loading/3);
											tAni = new TextAnimation("");delay(loading/3);
											tAni = new TextAnimation("				Thanks to...");delay(loading/3);
											tAni = new TextAnimation("				C++ programmer, My Dad");delay(loading/3);
											tAni = new TextAnimation("				Hyungnam Oh");delay(loading/3);
											tAni = new TextAnimation("				JoonRock Choi");delay(loading/3);
											tAni = new TextAnimation("			  Overwhelming IB homeworks");delay(loading/3);
											tAni = new TextAnimation("		 My Labtop with incredible battery life span.");delay(loading/3);
											tAni = new TextAnimation("");delay(loading/3);
											tAni = new TextAnimation("				2011.13.December");delay(loading/3);
											tAni = new TextAnimation("				Some rights Reserved");delay(loading/3);
											tAni = new TextAnimation("I am proud to have a program with 2000 line of code with 200 Variables!!");delay(loading/3);
											tAni = new TextAnimation("			enter any key to continue...");delay(loading/3);
											anykey = s.next();
											for (int victory = 0;victory<clearscreen ;victory++ )
											{
												System.out.println("");
											}
											currenttime = System.currentTimeMillis();
											tAni = new TextAnimation("		It has been "+(int)((currenttime-starttime)/3600000)+" hours and "+(((currenttime-starttime)/60000)%60000)+" minitues since the game started!");
											tAni = new TextAnimation("		Total Score :	"+(int)(((currenttime-starttime)/1000)+((double)montot/100)+((double)Exptot/10)+((double)mobCount*100)+(atktot*5)+(skilltot*5)+(lv*400)));
											tAni = new TextAnimation("		Thank you very much for playing Warrier's Adventure!!");
											System.out.println("				Thank You "+User+"!!");
											System.out.println("");delay(loading/3);
											System.out.println("");delay(loading/3);
											tAni = new TextAnimation("			Developed by Hong Joon Choi");
											tAni = new TextAnimation("Type any key to continue...");
											anykey = s.next();
											for (int vitory = 0;vitory<clearscreen ;vitory++ )
											{
												System.out.println("");
											}
											tAni = new TextAnimation("				Secret Shop Unlocked!!");
											tAni = new TextAnimation("				Gym Mode Unlocked!!");
											tAni = new TextAnimation("			enter any key to continue...");
											anykey = s.next();
										}
						}
						continue MainGameLoop;
					}
					/************************************Defeat*********************************************/
					if (chk(Gamehp))						// lost!!
					{
						dietot++;
						for (int k = 0;k<clearscreen ;k++ )
							System.out.println("");
						System.out.println(User+"'s HP: 0 / "+(int)hp+"		"+User+"'s MP: "+(int)Gamemp+" / "+(int)mp);
						kd = 0;
						hpb = hpbar(Gamehp/hp, bardot);
						System.out.println(hpb);
						System.out.println("");
						System.out.println("");
						System.out.println("");
						System.out.println("");
						System.out.println("				You Lost! "+mname+" Beated You!!");
						System.out.println("");
						System.out.println("		You Lost $"+(int)(money/4)+" please try beating this "+mname+" bastard again later");
						money = money-money/4;
						System.out.println("");
						System.out.println("");
						System.out.println("type any key to continue");
						anykey = s.next();
						continue MainGameLoop;
					}
				case 9:
					SFloop: while(true) 
					{
						for (int asds = 0;asds<clearscreen ;asds++ )
						System.out.println("");
						System.out.println("			----------Special Features----------");
						for (int asd = 0;asd<2 ;asd++ )
							System.out.println("	 ");
						System.out.println("		-Study Purpose-");
						System.out.println("[1]	Grade Calculator/Estimator");
						System.out.println("[2]	Factor Finder");
						System.out.println("[3]	Min/Max Calculator(In Preperation)");
						for (int asd = 0;asd<2 ;asd++ )
							System.out.println("");
						System.out.println("		-Technology-");
						System.out.println("[4]	Processor Check");
						System.out.println("[5]	Timer");
						System.out.println("[6]	 In Preperation");
						for (int asd = 0;asd<2 ;asd++ )
							System.out.println("");
						System.out.println("		-MiniGames-");
						System.out.println("[7]	 Number Guessing Game");
						System.out.println("[8]	 Mini Lotto");
						System.out.println("[9]	 DiceGame");
						for (int asds = 0;asds<4 ;asds++ )
							System.out.println("");
						System.out.println("[0]	 Escape");
						UserOrder2 = s.nextInt();
						switch (UserOrder2)
						{

						default :
							continue MainGameLoop;

						case 1:
							for (int asds = 0;asds<clearscreen ;asds++ )
								System.out.println("");
							for (int vic = 0;vic<clearscreen ;vic++ )
								System.out.println("");
							System.out.println("How many assessments or Tests did you do?"); 
							int repeat = s.nextInt();
							double[] grade = new double[repeat];
							double[] percent = new double[repeat];
							double remain = 100;
							double fg = 0;
							String temp = "";
							int i = 0;
							for (i =0;i<repeat ;i++ )
							{
								System.out.println("State grade for "+(i+1)+"th assessment");
								temp = s.next();
								grade[i] = Double.parseDouble(temp);
								System.out.println("State percentage for "+(i+1)+"th assessment");
								temp = s.next();
								percent[i] = Double.parseDouble(temp);
							}
							for (int j=0;j<repeat ;j++ )
							{
								remain=remain-percent[j];
								fg = fg+(grade[j]*percent[j]);
							}
							for (int asds = 0;asds<clearscreen ;asds++ )
								System.out.println("");
							System.out.println("You got grade "+fg/(double)(100-remain)+" so far.");
							System.out.println("");
							for (double l=0.5;l<7 ;l++ )
							{
								boolean aa = true;
								for (double k=0.5;k<15 ;k++ )
								{
									if (((double)fg+(double)k*(double)remain)/(100)>=l && aa==true)
									{
										System.out.println("to get "+(int)(l+0.5)+"or above, you should get "+(k+0.5)+" or above In remaining assessments.");
										aa=false;
									}
								}
							}
							System.out.println("Type any key to return.");
							anykey = s.next();
							continue SFloop;

						case 2:
							for (int asds = 0;asds<clearscreen ;asds++ )
								System.out.println("");
							long rep;
							System.out.println("This Calculator will tell you all factors of the number you typed");
							do
							{
								System.out.println("type the number of times you want to repeat this process(less than 10)"); 
								rep = s.nextInt();
							}
							while (rep>10 || rep<1);
							long you = 0;
							for (long redo = 0;redo<rep ;redo++ )
							{											
								for (int asds = 0;asds<3 ;asds++ )
									System.out.println("");
								do
								{
									System.out.println("type the number you want to check");
									you = s.nextInt();
									if (you==1)
										you=0;
									if (you<1)
									{
										System.out.println("Error - number out of range");
									}
								}
								while (you<1);
								long valuechk = 0;
								boolean chk = false;
								System.out.println("1");
								for (long a = 2;a<you ;a++ )									//factor finding
								{
									valuechk=you%a;
									if (valuechk==0)
									{
										System.out.println(a);
										chk = true;
									}
								}
								System.out.println(you);
								System.out.println("all factors have been found");						// end of factor finding
								if (chk == true)
									System.out.println("This number is not a prime number");
								if (chk == false)	
									System.out.println("This number is a prime number");
							}
							System.out.println("Type any key to return.");
							anykey = s.next();
							continue SFloop;

						case 3:
							

						case 4:
							for (int asds = 0;asds<clearscreen ;asds++ )
								System.out.println("");
							long End;
							int a = 1;
							System.out.println("This SpeedCheck shows your current condition of processor.");
							System.out.println("");
							System.out.println("");
							System.out.println("");
							System.out.println("It doesn't checks Multiple Cores. Computer's Multitasking ability is not being checked.");
							System.out.println("This System carries high value of uncertainty and it is not very reliable as well.");
							System.out.println("");
							System.out.println("Developer of this program has processor speed of 750 in average! :)");
							System.out.println("");
							System.out.println("");
							System.out.println("Checking Processor Speed... Wait for a moment....");
							long Start = System.currentTimeMillis();
							int loadfornoreaso;
							loadfornoreaso = 0;
							while (loadfornoreaso<60000)
							{
								System.out.print("\b");
								loadfornoreaso++;
							}
							End = System.currentTimeMillis();
							for (int asds = 0;asds<5 ;asds++ )
								System.out.println("");
							System.out.println("Processor Speed at previous test"+Speed);
							Speed = (End-Start);
							System.out.println("");
							System.out.println("Current Processor Speed:"+Speed+"  (Less the value, Better the Processor)");
							if (Speed>=2400)
								System.out.println("Your computer's processor is Adequate");
							else if (Speed>=1700)
								System.out.println("Your computer's processor is Satisfying");
							else if (Speed>=1100)
								System.out.println("Your computer's processor is Good");
							else if (Speed>=700)
								System.out.println("Your computer's processor is Very Good");
							else if (Speed>=400)
								System.out.println("Your computer's processor is Perfect");
							else if (Speed<200)
								System.out.println("Your computer's processor is Outstanding");
							System.out.println("Changing battery plan, would drastically improve your test result. Try with maximum performance battery plan.");
							System.out.println("Type any key to return.");
							anykey = s.next();
							continue SFloop;

						case 5:
							for (int asds = 0;asds<clearscreen ;asds++ )
								System.out.println("");
							System.out.println("This timer doesn't stop");
							System.out.println("even when you are not using this program");
							System.out.println("or even when you turn off the computer, as long as you save this game once.");
							System.out.println("");
							System.out.println("");
							time = (System.currentTimeMillis()-TimerBegin);
							long time2 = time/1000;
							System.out.println("Time Elapsed : "+((time2/2628000)%365)+"M "+(int)((time/86400000)%30.4166667)+"D "+((time/3600000)%24)+"Hr "+((time/60000)%60)+"min "+((time/1000)%60)+"s "+(time%1000)+"ms");
							System.out.println("Time Elapsed : "+(time/1000)+" seconds");
							for (int asds = 0;asds<10 ;asds++ )
									System.out.println("");
							System.out.println("[1] Return");
							System.out.println("[0] Start New Timer");
							UserOrder2 = s.nextInt();
							if (UserOrder2 == 0)
							{
								System.out.println("");
								System.out.println("Time Elapsed of Last Timer: "+(System.currentTimeMillis()-TimerBegin)+"ms");
								TimerBegin = System.currentTimeMillis();
								System.out.println("Timer Initiated.");
								System.out.println("Type any key to return.");
								anykey = s.next();
								continue SFloop;
							}
							continue SFloop;
							

						case 6:
							

						case 7:
							for (int asds = 0;asds<clearscreen ;asds++ )
								System.out.println("");
							int start=1;
							int end=3000;
							int yougg;
							int num=15;
							System.out.println ("[1] Easy mode");
							System.out.println ("[2] Normal mode");
							System.out.println ("[3] Hard mode");
							UserOrder2 = s.nextInt();
							if (UserOrder2==1)
							{
								end=30;
								num=10;
							}
							else if(UserOrder2==2)
							{
								end=100;
								num=10;
							}
							int in = 1;
							int com = (int)(Math.random()*end%(end-start))+start;										// computer's number guess(Random)
							for (int asds = 0;asds<25 ;asds++ )
								System.out.println("");
							System.out.println ("Type the number between "+start+"~"+end+ ". If you Guess correctly, you win.");
							System.out.println ("Each time you guess the number, Program will let you know if Your number is higher or less than Computer's number.");
							System.out.println ("-------------------------------------------------------------------");
							System.out.println ("You have "+num+" chances");
							System.out.println ("");
							while(in<16)
							{																			//Loop
								System.out.println (in+"th try");
								System.out.println ("Type the number between" +start+"~"+end);
								yougg = s.nextInt();																 //  Save human variable for Array
								if (yougg>end)																	// error
								{
									System.out.println ("Error has occured. Please restart the game.");
									return;
								}

								if (com==yougg)																	// victory
								{	
									System.out.println ("Congratulations!! you Guessed the number in " + in + "th turn!");
									System.out.println("");
									System.out.println("");
									System.out.println("Type any key to return.");
									anykey = s.next();
									continue SFloop;
								}
								else if (com>yougg)															// com>you
								{
									System.out.println ("Computer's number is higher than yours");
									if (0<num-in && num-in<=3)									// alert
									{
										System.out.println (num-in+" chance remaining. only a few try left before you lose!!");
									}
								}
								else if (com<yougg)															// com<you
								{
									System.out.println ("Computer's number is lower than yours");
									if (0<num-in && num-in<=3)										// alert
									{System.out.println (num-in+" chance remaining. only a few try left before you lose!!");
									}
								}
								in++;
							}		
							System.out.println ("Sorry, you failed to guess the number in"+num+"turns. Please try again.");  // fail
							System.out.println ("The number that computer guessed was" + com);
							System.out.println("");
							System.out.println("Type any key to return.");
							anykey = s.next();
							continue SFloop;

						case 8:
							for (int asds = 0;asds<clearscreen ;asds++ )
								System.out.println("");
							int numb[] = new int [6];
							int tempo;
							boolean chk;
							int Bokuan[] = new int [6];
							int Us;
							boolean ifuserbabo;
								for(int za = 0;za<6;za++)
								{
									chk = false;
									tempo = ((int)(Math.random()*100%15)+1);
									for (int j = 0;j<za ;j++)		
										if (tempo==numb[j])
											chk=true;				
									if (chk==true)
									{
										za--;
										continue;
									}
									else 
										numb[za] = tempo;
								}
								System.out.println("			-----Mini Lotto-----");
								System.out.println("Stake?(0 is also possible)");
								int Moneystake = s.nextInt();
								money = money-Moneystake;
								System.out.println("Enter 6 Lotto numbers. Repeat 6 times");
								for (int babo=0;babo<6 ;babo++ )
								{
									ifuserbabo=false;
									Us = s.nextInt();
									
									if (Us <1 || Us >15)
									{
										System.out.println("type number between 1 and 15");
										System.out.println("type again");
										babo--;
										continue;
									}
									for (int da = 0;da<babo ;da++)		
										if (Us==Bokuan[da])
											ifuserbabo=true;				

									if (ifuserbabo==true)
									{
										System.out.println("do not repeat the same number");
										System.out.println("type again");
										babo--;
										continue;
									}
									else
										Bokuan[babo] = Us;
								}



							
						case 9:


					}
				}
			}
		}
	}
			// chk if either user or enemy's health is equal or below 0
	static boolean chk (double g)
	{
		return (g <= 0);
	}
// generate health, mana bar **** : max health, ____ : 0 health
	static String hpbar (double t, char dot)				
	{
		String hbar = "";
		t = (int)(t*20);
		for (int e = 0;e<t ;e++ )
		{
			hbar = hbar+dot;
		}
		if (t<0)
			t=0;
		for (int kl = 0;kl<(20-t) ;kl++ )
		{
			hbar = hbar+"_";
		}
		return hbar;
	}
	public static void delay(double temp)
	 {
	  try 
	{ Thread.sleep((int)temp); }
	  catch (Exception e)
	{ System.out.println("Delay Function Error \n Caused by:"+e); }
	 }


}
