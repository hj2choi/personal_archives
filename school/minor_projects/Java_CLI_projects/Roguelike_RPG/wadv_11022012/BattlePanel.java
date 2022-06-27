public class BattlePanel extends wadv		// Declare and set preference and status of Artificial Intelligence
{
	java.util.Scanner s = new java.util.Scanner(System.in);
	{
		kerriganb = false;
		monPortal = 0;
		int qSelect=0;
		/////////////////// value initialization
		mname = "default";
		mhp = 1;	//mob statistics
		mmp = 1;
		matk = 1;
		matkm = 1;
		mdef = 0;
		mskill = 0;			//skills
		mbolt = 0;
		mheal = 0;
		mSpskill = 0;			// special skills
		mIceStrike = 0;
		mflee = 0;				// flee
		poison = false;
		msp = 1;
		sp = speed;
		mexp = 0;					//reward
		mmon = 0;
		damage = 0;		//AI
		hit = 0;
		AI = 0;			// only accepts 0~10
		AIdmg = 1;
		kerrigan = 0;				// kerrigan variables
		kd = 0;
		boolean escape = false;
		/////////////////// value initialization
		/*******************************************************Map Selection****************************************************************/
		BattleLoop: while(true)
		{
			escape = false;
			bescape = false;
			if (kerriganbeat==false)
			{
				for (int k = 0;k<clearscreen ;k++ )
					System.out.println("");
				System.out.println("			---------BattleField---------");
				System.out.println("");
				System.out.println("");
				if (kerriganbeat==true){System.out.println("			[1] GYM");}else if (stage==30){System.out.println("			[1] Final Boss");}else {System.out.println("			[1] Human Gateway  (Stage "+stage+"/30)");}
				if (stage>=16){System.out.println("			[2] Monster Portal   (Stage "+mstage+"/15)");} else {System.out.println("");}
				System.out.println("");
				System.out.println("");
				System.out.println("			[3] Basic Training");
				System.out.println("			[4] Standard Training");
				System.out.println("			[5] Advanced Training");
				System.out.println("");
				System.out.println("\n\n\n");
				for (int qs=1;qs<10 ; qs++)
				{
					if ((quest%100)>=qs && quest<qs*100)
					{
						qSelect=qs;
						System.out.println("			[8] Quest");
						break;
					}
				}
				System.out.println("");
				System.out.println("			[0] Escape");
				System.out.println("");
				System.out.println("");
				UserOrder2 = s.nextInt();
				if (UserOrder2==0)
				{
					escape=true;
					break;
				}
				/*******************************************************Training****************************************************************/
				if (kerriganbeat==false && UserOrder2 ==3)
				{mname="Training Ward";	mhp=150;	mmp=500;		matk=17;	matkm =18;	mdef=2;		mskill=0;	mbolt=20;	mheal=40;		mflee=8;msp = 8;	mexp=26;	mmon=0;	break BattleLoop;}
				else if(kerriganbeat==false && UserOrder2 ==4)
				{mname="Training Ward";	mhp=750;	mmp=1000;		matk=37;	matkm =42;	mdef=8;		mskill=0;	mbolt=52;	mheal=200;		mflee=16;msp = 14;	mexp=100;	mmon=0;	break BattleLoop;}
				else if(kerriganbeat==false && UserOrder2 ==5)
				{mname="Training Ward";	mhp=3000;	mmp=2000;		matk=200;	matkm =201;	mdef=20;		mskill=2;	mbolt=150;	mheal=1000;		mflee=20;msp = 18;	mexp=600;	mmon=0;	break BattleLoop;}
				/*******************************************************Human Gateway****************************************************************/
				else if (kerriganbeat==false &&UserOrder2 == 1)
				{
					switch (stage)
					{
					case 1:
						mname="Thief";	mhp=21;	mmp=1;		matk=10;	matkm = 11;	mdef=0;		mskill=0;	mbolt=20;	mheal=10;		mflee=0;msp = 19;	mexp=10;	mmon=200;	break BattleLoop;
					case 2:
						mname="Bandit";	mhp=28;	mmp=5;		matk=11;	matkm = 12;	mdef=0;		mskill=0;	mbolt=20;	mheal=10;		mflee=3;msp = 22;	mexp=12;	mmon=240;	break BattleLoop;
					case 3:
						mname="GuardMan";	mhp=24;	mmp=1;		matk=9;	matkm = 10;	mdef=2;		mskill=0;	mbolt=28;	mheal=15;		mflee=0;msp = 21;	mexp=15;	mmon=250;	break BattleLoop;
					case 4:
						mname="Raider";	mhp=38;	mmp=1;		matk=14;	matkm = 15;	mdef=0;		mskill=0;	mbolt=36;	mheal=34;		mflee=0;msp = 24;	mexp= 20;	mmon=320;	break BattleLoop;
					case 5:
						mname="Scientist";mhp=46;	mmp=30;		matk=13;	matkm = 14;	mdef=0;		mskill=0;	mbolt=20;	mheal=10;		mflee=0;msp = 22;	mexp=22;	mmon=400;	break BattleLoop;
					case 6:
						mname="Truman";	mhp=58;	mmp=55;		matk=15;	matkm = 16;	mdef=0;		mskill=0;	mbolt=20;	mheal=10;		mflee=6;msp = 22;	mexp=28;	mmon=500;	break BattleLoop;
					case 7:
						mname="Warrier";	mhp=80;	mmp=5;		matk=17;	matkm =19;	mdef=2;		mskill=0;	mbolt=60;	mheal=30;		mflee=0;msp = 18;	mexp=36;	mmon=530;	break BattleLoop;
					case 8:
						mname="Seer";	mhp=100;	mmp=150;		matk=16;	matkm =18;	mdef=0;		mskill=4;	mbolt=20;	mheal=25;		mflee=3;msp = 23;	mexp=40;	mmon=570;	break BattleLoop;
					case 9:
						mname="Assailant";	mhp=70;	mmp=30;		matk=19;	matkm =21;	mdef=0;		mskill=0;	mbolt=18;	mheal=25;		mflee=35;msp = 30;	mexp=43;	mmon=600;	break BattleLoop;
					case 10:
						mname="Mercenary";	mhp=110;	mmp=20;		matk=22;	matkm =24;	mdef=0;		mskill=0;	mbolt=18;	mheal=25;		mflee=5;msp = 24;	mexp=50;	mmon=900;	break BattleLoop;
					case 11:
						mname="Zealot";	mhp=160;	mmp=110;		matk=27;	matkm =28;	mdef=1;		mskill=3;	mbolt=26;	mheal=40;		mflee=2;msp = 19;	mexp=56;	mmon=700;	break BattleLoop;
					case 12:
						mname="GateKeeper";	mhp=200;	mmp=90;		matk=25;	matkm =27;	mdef=7;		mskill=2;	mbolt=21;	mheal=40;		mflee=0;msp = 15;	mexp=64;	mmon=800;	break BattleLoop;
					case 13:// increase difficulty
						mname="Cavalry";	mhp=145;	mmp=0;		matk=27;	matkm =30;	mdef=3;		mskill=0;	mbolt=32;	mheal=40;		mflee=10;msp = 33;	mexp=70;	mmon=850;	break BattleLoop;
					case 14:
						mname="Spell Caster";	mhp=180;	mmp=200;		matk=24;	matkm =28;	mdef=0;		mskill=6;	mbolt=32;	mheal=40;		mflee=3;msp = 28;	mexp=80;	mmon=900;	break BattleLoop;
					case 15:
						mname="Dragoon";	mhp=210;	mmp=110;		matk=31;	matkm =33;	mdef=3;		mskill=3;	mbolt=28;	mheal=50;		mflee=0;msp = 25;	mexp=92;	mmon=800;	break BattleLoop;
					case 16:
						mname="Felon";	mhp=200;	mmp=60;		matk=33;	matkm =36;	mdef=2;		mskill=3;	mbolt=36;	mheal=50;		mflee=3;msp = 30;	mexp=102;	mmon=1000;	break BattleLoop;
					case 17:
						mname="Warrier";	mhp=280;	mmp=110;		matk=40;	matkm =43;	mdef=3;		mskill=0;	mbolt=33;	mheal=80;		mflee=0;msp = 23;	mexp=110;	mmon=850;	break BattleLoop;
					case 18:
						mname="Peltast";	mhp=240;	mmp=20;		matk=44;	matkm =46;	mdef=10;		mskill=0;	mbolt=33;	mheal=80;		mflee=0;msp = 28;	mexp=112;	mmon=950;	break BattleLoop;
					case 19:
						mname="Ronin";	mhp=300;	mmp=120;		matk=39;	matkm =41;	mdef=3;		mskill=4;	mbolt=40;	mheal=80;		mflee=6;msp =34;	mexp=130;	mmon=1050;	break BattleLoop;
					case 20:
						mname="Anti Ninja";	mhp=200;	mmp=180;		matk=38;	matkm =47;	mdef=0;		mskill=2;	mbolt=36;	mheal=50;		mflee=40;msp = 34;	mexp=142;	mmon=1200;	break BattleLoop;
					case 21:
						mname="Samurai";	mhp=360;	mmp=110;		matk=50;	matkm =59;	mdef=5;		mskill=3;	mbolt=52;	mheal=100;		mflee=0;msp = 32;	mexp=151;	mmon=1250;	break BattleLoop;
					case 22:
						mname="Agent";	mhp=290;	mmp=110;		matk=45;	matkm =48;	mdef=1;		mskill=8;	mbolt=46;	mheal=100;		mflee=7;msp = 56;	mexp=160;	mmon=1200;	break BattleLoop;
					case 23:
						mname="Shaman";	mhp=410;	mmp=300;		matk=61;	matkm =65;	mdef=2;		mskill=4;	mbolt=58;	mheal=120;		mflee=0;msp = 33;	mexp=170;	mmon=1400;	break BattleLoop;
					case 24:
						mname="Priestess";	mhp=440;	mmp=500;		matk=40;	matkm =45;	mdef=2;		mskill=5;	mbolt=61;	mheal=146; mSpskill=5; mIceStrike=62;		mflee=5;msp = 34;	mexp=200;	mmon=1450;	break BattleLoop;
					case 25:
						mname="Lancer";	mhp=600;	mmp=200;		matk=70;	matkm =78;	mdef=7;		mskill=5;	mbolt=61;	mheal=146; 		mflee=8;msp = 34;	mexp=228;	mmon=1500;	break BattleLoop;
					case 26:
						mname="Issac Clarke";	mhp=1100;	mmp=180;		matk=112;	matkm =120;	mdef=10;		mskill=2;	mbolt=102;	mheal=300;		mflee=0;msp = 39;	mexp=360;	mmon=2000;	break BattleLoop;
					case 27:
						mname="Tassadar";	mhp=1200;	mmp=360;		matk=70;	matkm =76;	mdef=4;		mskill=5;	mbolt=72;	mheal=146; mSpskill = 5; mIceStrike=70;		mflee=13;msp = 40;	mexp=400;	mmon=2100;	break BattleLoop;
					case 28:
						mname="Pheonix";	mhp=800;	mmp=140;		matk=58;matkm =60;mdef=0;		mskill=8;mbolt=180;	mheal=400;		mflee=28;msp = 49;	mexp=430;	mmon=2300;	break BattleLoop;
					case 29:
						mname="Immortal";	mhp=1000;	mmp=100;		matk=61;matkm =78;mdef=50;		mskill=0;mbolt=180;	mheal=400;		mflee=0;msp = 30;	mexp=455;	mmon=2450;	break BattleLoop;
					}
				}
				/*******************************************************Monster Portal****************************************************************/
				else if (stage>15 && UserOrder2 == 2)
				{
					switch (mstage)
					{
						case 1:
							mname="Thrall";	mhp=300;	mmp=60;		matk=42;	matkm = 50;	mdef=6;		mskill=2;	mbolt=45;	mheal=10;		mflee=0;msp = 21;	mexp=120;	mmon=1100;	monPortal=1; break BattleLoop;
						case 2:
							mname="Viper";	mhp=230;	mmp=160;		matk=46;	matkm = 50;	mdef=2;		mskill=5;	mbolt=47;	mheal=70;		mflee=7;msp = 36;	mexp=138;	mmon=1000;	monPortal=1; break BattleLoop;
						case 3:
							mname="Skeleton Mage";	mhp=360;	mmp=240;		matk=53;	matkm = 58;	mdef=6;		mskill=4;	mbolt=50;	mheal=100; mSpskill=3; mIceStrike=45;		mflee=10;msp = 32;	mexp=154;	mmon=1300;	monPortal=1; break BattleLoop;
						case 4:
							mname="Drone";	mhp=450;	mmp=80;		matk=60;	matkm = 65;	mdef=13;		mskill=1;	mbolt=100;	mheal=150;		mflee=0;msp = 20;	mexp=170;	mmon=1400;	monPortal=1; break BattleLoop;
						case 5:
							mname="Undead";	mhp=540;	mmp=500;		matk=41;	matkm = 44;	mdef=4;		mskill=4;	mbolt=58;	mheal=100; mSpskill=2; mIceStrike=60;		mflee=0;msp = 29;	mexp=180;	mmon=1450;	monPortal=1; break BattleLoop;
						case 6:
							mname="Mountain Naga";	mhp=600;	mmp=340;		matk=51;	matkm = 57;	mdef=6;		mskill=6;	mbolt=67;	mheal=150;		mflee=0;msp = 33;	mexp=200;	mmon=1550;	monPortal=1; break BattleLoop;
						case 7:
							mname="Hunter Killer";		mhp=500;	mmp=100;		matk=82;matkm = 90;mdef=0;		mskill=3.5;mbolt=100;mheal=160;	msp = 40;	mflee=10;		mexp=245;mmon=1600;	monPortal=1;break BattleLoop;
						case 8:
							mname="Dark Boomer";		mhp=600;	mmp=120;		matk=72;matkm = 80;mdef=42;		mskill=2;mbolt=100;mheal=200;	msp = 25;	mflee=0;		mexp=262;mmon=1700;	monPortal=1;break BattleLoop;
						case 9:
							mname="Irate Cago";		mhp=620;	mmp=340;		matk=68;matkm = 74;mdef=4;		mskill=4;mbolt=83;mheal=200;mSpskill=4;mIceStrike=80;	msp = 50;mflee=15;		mexp=280;mmon=1750;	monPortal=1;break BattleLoop;
						case 10:
							mname="Flesh Fiend";		mhp=800;	mmp=1200;		matk=1;matkm = 50;mdef=11;		mskill=10;mbolt=76;mheal=250;mSpskill=5;mIceStrike=72;	msp=36;mflee=0;		mexp=300;mmon=1900;	monPortal=1;break BattleLoop;
					}
				}
				/*******************************************************Quest****************************************************************/
				else if (UserOrder2 == 8)
				{
					switch (qSelect)
					{
					default :
						escape=true;
						break;
					case 1 :
						for(int k = 0;k<clearscreen ;k++ ){System.out.println("");}
						System.out.println("	Maria : Thieves are stealing everyting from our castle!!!");System.out.println("		Please help me pulling them away from our castle..");System.out.println("		There are total of 5 thieves and bandits.");
						for(int k = 0;k<11 ;k++ ){System.out.println("");}
						System.out.println("Accept Request??	[1] Yes	[2] No");
						UserOrder2 = s.nextInt();	
						if (UserOrder2==2)
							{escape=true;	break;}
						mname="Thief";	mhp=21;	mmp=1;		matk=15;	matkm = 16;	mdef=0;		mskill=0;	mbolt=20;	mheal=10;		mflee=0;msp = 19;	mexp=0;	mmon=0; monPortal=2;BattleSimulation Battle = new BattleSimulation();
						escape=true; bescape=true;
						if (!ql(monPortal)){mname="Bandit";	mhp=30;	mmp=5;		matk=16;	matkm =17;	mdef=0;		mskill=0;	mbolt=20;	mheal=10;		mflee=3;msp = 21;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal)){mname="Stealer";	mhp=45;	mmp=1;		matk=19;	matkm = 21;	mdef=0;		mskill=0;	mbolt=20;	mheal=10;		mflee=0;msp = 30;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal)){mname="Thief";	mhp=30;	mmp=1;		matk=15;	matkm = 16;	mdef=0;		mskill=0;	mbolt=20;	mheal=10;		mflee=0;msp = 19;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal)){mname="Ninja";	mhp=80;	mmp=75;		matk=20;	matkm = 26;	mdef=0;		mskill=6;	mbolt=23;	mheal=40;		mflee=5;msp = 32;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal))
						{
							for(int k = 0;k<clearscreen ;k++ ){System.out.println("");}
							System.out.println("	Maria : Thanks for pulling thieves away from our castle!!!");System.out.println("		I will give you one crystal for reward.\n		You can use them to upgrade your gear.");
							System.out.println("\n\n		You gained 1 crystal!!\n		You gained 92 Exp and $1400 Cash!!");
							mobCount=mobCount+5; exp = exp+92; Exptot = Exptot+92; money = money+1400; montot = montot+1400; chrystal++; quest=quest+100;
							for(int k = 0;k<11 ;k++ ){System.out.println("");}
							System.out.println("Enter any key to continue...");
							anykey = s.next();
						}
						break BattleLoop;
					case 2 :
						for(int k = 0;k<clearscreen ;k++ ){System.out.println("");}
						System.out.println("	VillageHead : Kerrigan sent us her Squadron to conquer our castle...");System.out.println("			We must defend them from destroying our castle!");System.out.println("			Please defend them "+User);
						for(int k = 0;k<11 ;k++ ){System.out.println("");}
						System.out.println("Accept Request??	[1] Yes	[2] No");
						UserOrder2 = s.nextInt();	
						if (UserOrder2==2)
							{escape=true;	break;}
						mname="Zergling";	mhp=120;	mmp=70;		matk=24;	matkm = 26;	mdef=3;		mskill=2;	mbolt=26;	mheal=60;		mflee=10;msp = 40;	mexp=0;	mmon=0; monPortal=2; Battle = new BattleSimulation();
						escape=true; bescape=true;
						if (!ql(monPortal)){mname="Roach";	mhp=145;	mmp=100;		matk=26;	matkm =28;	mdef=5;		mskill=3;	mbolt=32;	mheal=100;		mflee=3;msp = 28;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal)){mname="Hydralisk";	mhp=160;	mmp=300;		matk=24;	matkm = 26;	mdef=0;		mskill=6;	mbolt=30;	mheal=40; mSpskill = 4; mIceStrike=30;		mflee=5;msp = 28;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal))
						{
							for(int k = 0;k<clearscreen ;k++ ){System.out.println("");}
							System.out.println("	VillageHead : Thank God!! They are retreating!!");System.out.println("		I will give you some anount of exp for reward.");
							System.out.println("\n\nYou gained 220 Exp and $2000 Cash!!");
							mobCount=mobCount+3; exp = exp+220; Exptot = Exptot+220; money = money+2000; montot = montot+2000; quest=quest+100;
							for(int k = 0;k<11 ;k++ ){System.out.println("");}
							System.out.println("Enter any key to continue...");
							anykey = s.next();
						}
						break BattleLoop;
					case 3 :
						for(int k = 0;k<clearscreen ;k++ ){System.out.println("");}
						System.out.println("	Scout : I hijacked an information of Kerrigan's Forces.");System.out.println("		They are planning to assasinate you "+User+", who can be a threat to them.");System.out.println("		Of course, we cannot let it happen.");System.out.println("		We will take care of him before he take care of you!");
						for(int k = 0;k<11 ;k++ ){System.out.println("");}
						System.out.println("Accept Request??	[1] Yes	[2] No");
						UserOrder2 = s.nextInt();	
						if (UserOrder2==2)
							{escape=true;	break;}
						mname="Guard";	mhp=150;	mmp=150;		matk=37;	matkm =41;	mdef=3;		mskill=2;	mbolt=30;	mheal=60;		mflee=10;msp = 40;	mexp=0;	mmon=0; monPortal=2; Battle = new BattleSimulation();
						escape=true; bescape=true;
						if (!ql(monPortal)){mname="Assasin";	mhp=420;	mmp=230;		matk=64;	matkm = 75;	mdef=2;		mskill=9;	mbolt=32;	mheal=80; mSpskill = 6; mIceStrike=35;		mflee=5;msp = 45;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal))
						{
							for(int k = 0;k<clearscreen ;k++ ){System.out.println("");}
							System.out.println("	Scout : You succeeded  Taking Assasin down!");System.out.println("		I will give you some anount of exp for reward.");
							System.out.println("\n\nYou gained 400 Exp, $3200 Cash!! and a crystal!");
							mobCount=mobCount+3; exp = exp+400; Exptot = Exptot+400; money = money+3200; montot = montot+3200; chrystal++; quest=quest+100;
							for(int k = 0;k<11 ;k++ ){System.out.println("");}
							System.out.println("Enter any key to continue...");
							anykey = s.next();
						}
						break BattleLoop;
					case 4 :
						for(int k = 0;k<clearscreen ;k++ ){System.out.println("");}
						System.out.println("	Warrier : Dammit! A huge army is advancing to our city!");System.out.println("		Their army is pretty huge. I will help you defending them.");System.out.println("		Our castle's fate lie on you. Good luck.");System.out.println("		We will take care of him before he take care of you!");
						for(int k = 0;k<11 ;k++ ){System.out.println("");}
						System.out.println("Accept Request??	[1] Yes	[2] No");
						UserOrder2 = s.nextInt();	
						if (UserOrder2==2)
							{escape=true;	break;}
						mname="Devourer";	mhp=150;	mmp=150;		matk=37;	matkm =41;	mdef=3;		mskill=2;	mbolt=30;	mheal=60;		mflee=10;msp = 40;	mexp=0;	mmon=0; monPortal=2; Battle = new BattleSimulation();
						escape=true; bescape=true;
						if (!ql(monPortal)){mname="Roach";	mhp=145;	mmp=100;		matk=26;	matkm =28;	mdef=5;		mskill=3;	mbolt=32;	mheal=100;		mflee=3;msp = 28;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal)){mname="Hydralisk";	mhp=160;	mmp=300;		matk=24;	matkm = 26;	mdef=0;		mskill=6;	mbolt=30;	mheal=40; mSpskill = 4; mIceStrike=30;		mflee=5;msp = 28;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal)){mname="Roach";	mhp=145;	mmp=100;		matk=26;	matkm =28;	mdef=5;		mskill=3;	mbolt=32;	mheal=100;		mflee=3;msp = 28;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal)){mname="Hydralisk";	mhp=160;	mmp=300;		matk=24;	matkm = 26;	mdef=0;		mskill=6;	mbolt=30;	mheal=40; mSpskill = 4; mIceStrike=30;		mflee=5;msp = 28;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal)){mname="Assasin";	mhp=420;	mmp=230;		matk=64;	matkm = 75;	mdef=2;		mskill=9;	mbolt=32;	mheal=80; mSpskill = 6; mIceStrike=35;		mflee=5;msp = 45;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal)){mname="Hydralisk";	mhp=160;	mmp=300;		matk=24;	matkm = 26;	mdef=0;		mskill=6;	mbolt=30;	mheal=40; mSpskill = 4; mIceStrike=30;		mflee=5;msp = 28;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal)){mname="Assasin";	mhp=420;	mmp=230;		matk=64;	matkm = 75;	mdef=2;		mskill=9;	mbolt=32;	mheal=80; mSpskill = 6; mIceStrike=35;		mflee=5;msp = 45;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal))
						{
							for(int k = 0;k<clearscreen ;k++ ){System.out.println("");}
							System.out.println("	Warrier : Good Lord!! We hold against that huge army!");System.out.println("		I will give you some anount of exp for reward.");
							System.out.println("\n\nYou gained 700 Exp and $5500 Cash!!");
							mobCount=mobCount+8; exp = exp+700; Exptot = Exptot+700; money = money+5500; montot = montot+5500; quest=quest+100;
							for(int k = 0;k<11 ;k++ ){System.out.println("");}
							System.out.println("Enter any key to continue...");
							anykey = s.next();
						}
						break BattleLoop;
					}
				}
				else
					continue;
			}
			
			/*
			if (kerriganbeat==true)
			{
				for (int k = 0;k<clearscreen ;k++ )
					System.out.println("");
				System.out.println("				Gym");System.out.println("");
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
								mname="Wild Cago";mhp=8000;	mmp=100;		matk=298;	matkm = 325;	mdef=15;		mskill=1.5;	mbolt=513;	mheal=900;		mflee=26;msp =112;	mexp=850;	mmon= 5000;	break BattleLoop;
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
								mname="Torrasque";	mhp=15500;	mmp=100;		matk=480;	matkm = 530;	mdef=350;		mskill=2;	mbolt=737;	mheal=2500;		mflee=10;msp = 118;	mexp=1750;	mmon=6000;	break BattleLoop;
							case 44:
								kerriganb = true;	kc = 5;
								mname="Hunter Killer";	mhp=20000;	mmp=230;		matk=500;	matkm = 600;	mdef=60;		mskill=8;	mbolt=546;	mheal=3000;		mflee=26;msp = 130;	mexp=2250;	mmon=7000;	break BattleLoop;
							case 50:
								kerriganb = true;	kd = 5;
								mname="Kerrigan";	mhp=45480;	mmp=1024;		matk=	 600;	matkm = 704;	mdef=150;		mskill=3.5;	mbolt=764;	mheal=3240;		mflee=35;msp = 140;	mexp=4000;	mmon=10000;	break BattleLoop;
							}
						}
					
					}
					*/
		}
		if (escape==false)
		{
			BattleSimulation Battle = new BattleSimulation();
		}
	}
	
	boolean ql(int a)
	{
		return monPortal==5;
	}
}
