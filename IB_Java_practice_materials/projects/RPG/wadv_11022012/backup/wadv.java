/*
wep stat array[](int[] wep_stat = new int[])
0,1,2 spatk
3~6: str...
6 hp
7 mp
8 acc
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
improve screen(clear screen more often)

line 1111 => Stage 1 ~ Stage 30
***************************************************************************/
import java.io.*;
public class wadv
{
	/////////////////////// User Changable, Option Variable
	static int clearscreen = 22;
	static int loading = 1000;
	static char bardot = '*';
	final static char bar2dot='_';
	static int textSpeed=8;
	static int savefile = 0;
	static String User = "Warrier";
	/////////////////////// Level Variable
	static int lv = 1;
	static int exp = 0;
	static int expmax = 10;
	static int increment = 20;
	static int increment2 = 10;
	/////////////////////// Character Stat
	static double str = 0;				//final stat
	static double dex = 0;
	static double intel = 0;
	static double sta = 0;
	static double hp = 0;				//hp.mp 
	static double mp = 0;
	static double Gamehp = 0;			// hp.mp in battle
	static double Gamemp = 0;
	static double atkmax = 0;// + (dex*(dex/10)-1) + (sta/4) + ((wep_stat[0])/4)-str);		//battle statistics
	static double atkmin = 0;
	static double spatk = 0;
	static double def = 0;
	static double acc = 0;				// use with random(range = 0~100) (hit is determined when (num is 1~acc))
	static double speed = 0;
	static double sa = 3;					//userstat
	static double sb = 3;
	static double sc = 3;
	static double sd = 3;
	static double[] wep_stat = new double[] {24, 18, 2,0,0,0,0,0,0,0};		//weapons(array) 0atk 1spatk 2def 3~6stat 7hp 8mp 9acc
	static String wep = "Club";
	static String arm = "Poor's clothes";
	static String head = "none";
	static int difficulty = 13;
	static int money = 500;		//fundamental
	static int chrystal = 0;
	/////////////////////// skills
	static double skstab = 0;								// skills
	static double skdoublestrike = 0;			//doublestrike
	static double skheal = 0;					// heal
	static double skdebuff = 0;			//debuff
	static double skpsionicstorm = 0;		//psionicstorm
	static double skallin = 0;			//allin
	static double pskCritical = 0;
	static double pskManaRest = 0;
	static double pskDodge = 0;
	//////////////////////// information counting //Descreteeeee~~
	static int mobCount = 0;		// count for statistics
	static long Timeplus = 0;
	static long TimeMillisSave = 0;
	static long Exptot=0;
	static long montot=money;
	static long atktot=0;
	static long dietot=0;
	static long skilltot=0;
	static long misstot=0;
	static long starttime = System.currentTimeMillis();
	static long currenttime = 0;
	/////////////////////storymode
	static boolean kerriganbeat = false;
	static int stage = 1;
	static int quest = 0;
	static int mstage = 1;
	static int monPortal = 1;
	static boolean kerriganb = false;
	//////////////////////external
	static long Speed = 0;
	static long TimerBegin = 0;
	static long time=0;
	//////////////////////Mob Variables
	static String mname = "default";
	static double mhp = 1;	//mob statistics
	static double mmp = 1;
	static double matk = 0;
	static double matkm = 0;
	static double mdef = 0;
	static double mskill = 0;			//skills
	static double mbolt = 0;
	static double mheal = 0;
	static double mSpskill = 0;			// special skills
	static double mIceStrike = 0;
	static double mflee = 0;				// flee
	static boolean poison = false;
	static double msp = 0;
	static double sp = speed;
	static int mexp = 0;					//reward
	static int mmon = 0;
	static double damage = 0;		//AI
	static double hit = 0;
	static double AI = 0;			// only accepts 0~10
	static double AIdmg = 1;
	static int kerrigan = 0;				// kerrigan variables
	static int kb = 0;
	static int kc = 0;
	static int kd = 0;
	static int ke = 0;
	/////////////////////// System Variable
	static int callClassType = 0;
	static int UserOrder2 = 0;
	static String anykey = "";
	static String printsave = "";
	static boolean bescape = false;
	static String hpb = "";				// hp bar
	public static void main (String args [] )	throws IOException
	{
		/**************Character Casting Test***************/
		int ha = 8;
		int hb = 15;
		int hc = 3;
		int hi = 9;
		String invite = "Developed by "+(char)(ha+64)+(char)(hb+64+32)+"ng"+" J"+(char)(hb+96)+(char)(hb+96)+"n"+" "+(char)(hc+64)+(char)(ha+96)+(char)(hb+96)+(char)(hi+96);
		/*******************************end*********************************/
		java.util.Scanner s = new java.util.Scanner(System.in);
		TextAnimation tAni = new TextAnimation("Extremely Bored Warrier's Adventure ver 1.1.1");
		tAni = new TextAnimation(invite);
		SkillTab SKT = new SkillTab(skstab, skdoublestrike, skheal, skdebuff, skpsionicstorm, skallin);
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
			do
			{
				for (int aaa=0;aaa<clearscreen ;aaa++ )
					System.out.println("");
				System.out.println("			Which savefile do you want to load?");System.out.println("");System.out.println("");
				System.out.println("				Savedata [1]");
				System.out.println("				Savedata [2]");
				System.out.println("				Savedata [3]");
				System.out.println("");System.out.println("");System.out.println("");System.out.println("");System.out.println("");System.out.println("");System.out.println("");
				savefile=s.nextInt();
			}
			while (savefile>3 || savefile<1);
			System.out.println("");System.out.println("");
			savefile=savefile-1;
			System.out.println("loading...");
			int count = 0;
			String[] fileread = new String[100];
			File ff = new File("save"+savefile+".txt");
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
			savefile = Integer.parseInt(fileread[47]);
			stage = Integer.parseInt(fileread[48]);
			quest = Integer.parseInt(fileread[49]);
			mstage = Integer.parseInt(fileread[50]);
			pskCritical = Double.parseDouble(fileread[51]);
			pskManaRest = Double.parseDouble(fileread[52]);
			pskDodge = Double.parseDouble(fileread[53]);
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
			CharacterStat UserStat = new CharacterStat();
			currenttime = System.currentTimeMillis()+Timeplus;
			TimeMillisSave = currenttime-starttime;
			for (int k = 0;k<clearscreen ;k++ )
				System.out.println("");
			LevelUp Lvup = new LevelUp();
			/*********************************************************Main Menu***************************************************************/
			//System.out.println("Warrier's adventure ver 1.1.1");
			for (int asd = 0;asd<5 ;asd++ )
				System.out.println("");
			System.out.println("				MAIN MENU");
			for (int asd = 0;asd<3 ;asd++ )
				System.out.println("");
			System.out.println("			[1] Character Information	");
			System.out.println("			[2] GearShop");
			System.out.println("			[3] Enter Battlefield");
			System.out.println("");
			System.out.println("			[5] AutoSave");
			System.out.println("");
			System.out.println("			[7] Manual");
			System.out.println("			[8] Options");
			System.out.println("			[9] Extra Fetures");
			System.out.println("");
			System.out.println("			[0] Exit");
			for (int asds = 0;asds<3 ;asds++ )
				System.out.println("");
			int UserOrder = s.nextInt();
			for (int k = 0;k<clearscreen ;k++ )
				System.out.println("");
			switch (UserOrder)					
			{
				case 1 :	
					CharacterInformation Stat = new CharacterInformation();
					break;
				case 2 :				
					Shop Shop = new Shop();
					break;
				case 3:
					BattlePanel BattlePanel = new BattlePanel();
					break;
				case 7:										// Game information
					Manual Manual = new Manual(User);
					break;
				case 8:									// Options
					Options Options = new Options();
					break;
				case 9:
					ExternalFeatures Ext = new ExternalFeatures();
					break;
				/****************************************************************************AutoSave**********************************************************************************/
				case 5:
					File ff = new File("save"+savefile+".txt"); //create the file
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
					out.println(savefile);
					out.println(stage);
					out.println(quest);
					out.println(mstage);	// 50
					out.println(pskCritical);
					out.println(pskManaRest);
					out.println(pskDodge);
					out.close();
					for (int k = 0;k<clearscreen ;k++ )
						System.out.println("");
					System.out.println("		Successfully saved to your installation directory.");
					System.out.println("			Enter any key to continue.");
					for (int k = 0;k<10 ;k++ )
						System.out.println("");
					anykey = s.next();
					break;
				/****************************************************************************EndGame**********************************************************************************/
				case 0:
					System.out.println("			Do you really want to exit the game?");
					for (int k = 0;k<3 ;k++ )
						System.out.println("");
					System.out.println("				[1] : Yes");
					System.out.println("				[2] : No\n\n\n\n\n\n\n\n");
					UserOrder2 = s.nextInt();
					if (UserOrder2 == 1)
						break MainGameLoop;
					else
						break;
			}
		}
	}
	public static void delay(double temp)
	 {
	  try 
	{ Thread.sleep((int)temp); }
	  catch (Exception e)
	{ System.out.println("Delay Function Error \n Caused by:"+e); }
	 }
}
