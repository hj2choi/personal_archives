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
		mOffensive = 0;			//skills
		mbolt = 0;
		mheal = 0;
		mRest = 0;
		mCurse = 0;			// special skills
		mflee = 0;				// flee
		poison = 0;
		msp = 1;
		sp = speed;
		mexp = 0;					//reward
		mmon = 0;
		damage = 0;		//AI
		hit = 0;
		AI = 0;			// only accepts 0~10
		mDefensive=0;
		mMelee=0;
		kerrigan = 0;				// kerrigan variables
		kd = 0;
		macc=100;
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
				{mname="Training Ward";mhp=200;mmp=0;mRest=0;		matk=18;matkm=20;mdef=2;			mflee=0;msp=10;		mOffensive=0;mDefensive=0;mCurse=0;mSkillPref=0;mMelee=10;	mexp=30;mmon=0;	break BattleLoop;}
				else if(kerriganbeat==false && UserOrder2 ==4)
				{mname="Training Ward";	mhp=750;	mmp=1000;		matk=37;	matkm =42;	mdef=8;		mOffensive=0;	mbolt=52;	mheal=200;		mflee=20;msp = 14;	mexp=100;	mmon=0;	break BattleLoop;}
				else if(kerriganbeat==false && UserOrder2 ==5)
				{mname="Training Ward";	mhp=3000;	mmp=2000;		matk=200;	matkm =201;	mdef=20;		mOffensive=2;	mbolt=150;	mheal=1000;		mflee=30;msp = 18;	mexp=600;	mmon=0;	break BattleLoop;}
				/*******************************************************Human Gateway****************************************************************/
				else if (kerriganbeat==false &&UserOrder2 == 1)
				{
					switch (stage)
					{
					case 1:	 //name					hp, mp, MPrestoration%				Attack&Defence						Agility(dodge,speed,acc)				AI Degree 0~10 (Offence, Deffence, Curse, Preference, Melee)				Reward(exp, mon)
						mname="Thief";	mhp=21;mmp=50;mRest=15;		matk=14;matkm=15;mdef=0;		mflee=0;msp=19;macc=100;		mOffensive=0;mDefensive=0;mCurse=0;mSkillPref=0;mMelee=0;		mexp=10;mmon=200;	break BattleLoop;
					case 2:
						mname="Bandit";		mhp=28;mmp=40;mRest=40;		matk=16;matkm=17;mdef=0;		mflee=3;msp=22;		mOffensive=0;mDefensive=0;mCurse=0;mSkillPref=0;mMelee=0;		mexp=12;mmon=240;	break BattleLoop;
					case 3:
						mname="Guardian";		mhp=24;mmp=25;mRest=10;		matk=13;matkm=14;mdef=3;			mflee=0;msp=21;		mOffensive=0;mDefensive=0;mCurse=0;mSkillPref=0;mMelee=3;		mexp=14;mmon=250;	break BattleLoop;
					case 4:
						mname="Filch";		mhp=45;mmp=30;mRest=25;		matk=22;matkm=25;mdef=0;			mflee=0;msp=25;		mOffensive=0;mDefensive=0;mCurse=0;mSkillPref=0;mMelee=0;		mexp=20;mmon=320;	break BattleLoop;
					case 5:
						mname="Marcus Flint";mhp=54;mmp=60;mRest=15;		matk=16;matkm=18;mdef=1;			mflee=2;msp=25;		mOffensive=0;mDefensive=0;mCurse=0;mSkillPref=0;mMelee=0;	mexp=22;mmon=400;	break BattleLoop;	//sliderine's quiditch leader
					case 6:
						mname="Seer";mhp=65;mmp=100;mRest=10;		matk=18;matkm=20;mdef=0;			mflee=4;msp=27;		mOffensive=3;mDefensive=1;mCurse=0;mSkillPref=0;mMelee=0;	mexp=22;mmon=500;	break BattleLoop;		
					case 7:
						mname="Warrier";mhp=80;mmp=80;mRest=10;		matk=20;matkm=22;mdef=1;			mflee=0;msp=18;		mOffensive=0;mDefensive=3;mCurse=0;mSkillPref=0;mMelee=3;	mexp=36;mmon=530;	break BattleLoop;
					case 8:
						mname="Burke";	mhp=65;mmp=130;mRest=12;		matk=19;matkm=22;mdef=1;			mflee=5;msp=34;		mOffensive=3;mDefensive=1;mCurse=0;mSkillPref=2;mMelee=0;	mexp=30;mmon=570;	break BattleLoop;
					case 9:
						mname="Gudgeon David";mhp=100;mmp=100;mRest=35;		matk=23;matkm=25;mdef=2;			mflee=0;msp=22;		mOffensive=4;mDefensive=2;mCurse=0;mSkillPref=0;mMelee=0;	mexp=35;mmon=600;	break BattleLoop;
					case 10:
						mname="Mercenary";mhp=90;mmp=40;mRest=20;		matk=34;matkm=38;mdef=0;			mflee=0;msp=31;		mOffensive=0;mDefensive=0;mCurse=0;mSkillPref=0;mMelee=0;	mexp=37;mmon=700;	break BattleLoop;
					case 11:
						mname="Selwin";mhp=70;mmp=150;mRest=10;		matk=28;matkm=31;mdef=7;			mflee=30;msp=35;		mOffensive=3;mDefensive=0;mCurse=0;mSkillPref=0;mMelee=0;	mexp=45;mmon=700;	break BattleLoop;
					case 12:
						mname="GateKeeper";	mhp=200;	mmp=90;		matk=25;	matkm =27;	mdef=7;		mOffensive=2;	mbolt=21;	mheal=40;		mflee=0;msp = 15;	mexp=64;	mmon=800;	break BattleLoop;
					case 13:// increase difficulty
						mname="Cavalry";	mhp=145;	mmp=0;		matk=27;	matkm =30;	mdef=3;		mOffensive=0;	mbolt=32;	mheal=40;		mflee=10;msp = 33;	mexp=70;	mmon=850;	break BattleLoop;
					case 14:
						mname="Spell Caster";	mhp=180;	mmp=200;		matk=24;	matkm =28;	mdef=0;		mOffensive=6;	mbolt=32;	mheal=40;		mflee=3;msp = 28;	mexp=80;	mmon=900;	break BattleLoop;
					case 15:
						mname="Dragoon";	mhp=210;	mmp=110;		matk=31;	matkm =33;	mdef=3;		mOffensive=3;	mbolt=28;	mheal=50;		mflee=0;msp = 25;	mexp=92;	mmon=800;	break BattleLoop;
					case 16:
						mname="Felon";	mhp=200;	mmp=60;		matk=33;	matkm =36;	mdef=2;		mOffensive=3;	mbolt=36;	mheal=50;		mflee=3;msp = 30;	mexp=102;	mmon=1000;	break BattleLoop;
					case 17:
						mname="Warrier";	mhp=280;	mmp=110;		matk=40;	matkm =43;	mdef=3;		mOffensive=0;	mbolt=33;	mheal=80;		mflee=0;msp = 23;	mexp=110;	mmon=850;	break BattleLoop;
					case 18:
						mname="Peltast";	mhp=240;	mmp=20;		matk=44;	matkm =46;	mdef=10;		mOffensive=0;	mbolt=33;	mheal=80;		mflee=0;msp = 28;	mexp=112;	mmon=950;	break BattleLoop;
					case 19:
						mname="Ronin";	mhp=300;	mmp=120;		matk=39;	matkm =41;	mdef=3;		mOffensive=4;	mbolt=40;	mheal=80;		mflee=6;msp =34;	mexp=130;	mmon=1050;	break BattleLoop;
					case 20:
						mname="Anti Ninja";	mhp=200;	mmp=180;		matk=38;	matkm =47;	mdef=0;		mOffensive=2;	mbolt=36;	mheal=50;		mflee=40;msp = 34;	mexp=142;	mmon=1200;	break BattleLoop;
					case 21:
						mname="Samurai";	mhp=360;	mmp=110;		matk=50;	matkm =59;	mdef=5;		mOffensive=3;	mbolt=52;	mheal=100;		mflee=0;msp = 32;	mexp=151;	mmon=1250;	break BattleLoop;
					case 22:
						mname="Agent";	mhp=290;	mmp=110;		matk=45;	matkm =48;	mdef=1;		mOffensive=8;	mbolt=46;	mheal=100;		mflee=7;msp = 56;	mexp=160;	mmon=1200;	break BattleLoop;
					case 23:
						mname="Shaman";	mhp=410;	mmp=300;		matk=61;	matkm =65;	mdef=2;		mOffensive=4;	mbolt=58;	mheal=120;		mflee=0;msp = 33;	mexp=170;	mmon=1400;	break BattleLoop;
					case 24:
						mname="Priestess";	mhp=440;	mmp=500;		matk=40;	matkm =45;	mdef=2;		mOffensive=5;	mbolt=61;	mheal=146;mflee=5;msp = 34;	mexp=200;	mmon=1450;	break BattleLoop;
					case 25:
						mname="Lancer";	mhp=600;	mmp=200;		matk=70;	matkm =78;	mdef=7;		mOffensive=5;	mbolt=61;	mheal=146; 		mflee=8;msp = 34;	mexp=228;	mmon=1500;	break BattleLoop;
					case 26:
						mname="Issac Clarke";	mhp=1100;	mmp=180;		matk=112;	matkm =120;	mdef=10;		mOffensive=2;	mbolt=102;	mheal=300;		mflee=0;msp = 39;	mexp=360;	mmon=2000;	break BattleLoop;
					case 27:
						mname="Tassadar";	mhp=1200;mmp=600;mRest=10;		matk=70;matkm=76;mdef=0;		mflee=13;msp=50;		mOffensive=7;mDefensive=6;mCurse=0;mSkillPref=167;mMelee=0;		mexp=400;mmon=2100;	break BattleLoop;
					case 28:
						mname="Pheonix";	mhp=800;	mmp=140;		matk=58;matkm =60;mdef=0;		mOffensive=8;mbolt=180;	mheal=400;		mflee=28;msp = 49;	mexp=430;	mmon=2300;	break BattleLoop;
					case 29:
						mname="Immortal";	mhp=1000;	mmp=100;		matk=61;matkm =78;mdef=50;		mOffensive=0;mbolt=180;	mheal=400;		mflee=0;msp = 30;	mexp=455;	mmon=2450;	break BattleLoop;
					}
				}
				/*******************************************************Monster Portal****************************************************************/
				else if (stage>15 && UserOrder2 == 2)
				{
					switch (mstage)
					{
						case 1:
							mname="Thrall";	mhp=300;	mmp=60;		matk=42;	matkm = 50;	mdef=6;		mOffensive=2;	mbolt=45;	mheal=10;		mflee=0;msp = 21;	mexp=120;	mmon=1100;	monPortal=1; break BattleLoop;
						case 2:
							mname="Viper";	mhp=230;	mmp=160;		matk=46;	matkm = 50;	mdef=2;		mOffensive=5;	mbolt=47;	mheal=70;		mflee=7;msp = 36;	mexp=138;	mmon=1000;	monPortal=1; break BattleLoop;
						case 3:
							mname="Skeleton Mage";	mhp=360;	mmp=240;		matk=53;	matkm = 58;	mdef=6;		mOffensive=4;	mbolt=50;	mheal=100; mflee=10;msp = 32;	mexp=154;	mmon=1300;	monPortal=1; break BattleLoop;
						case 4:
							mname="Drone";	mhp=450;	mmp=80;		matk=60;	matkm = 65;	mdef=13;		mOffensive=1;	mbolt=100;	mheal=150;		mflee=0;msp = 20;	mexp=170;	mmon=1400;	monPortal=1; break BattleLoop;
						case 5:
							mname="Undead";	mhp=540;	mmp=500;		matk=41;	matkm = 44;	mdef=4;		mOffensive=4;	mbolt=58;	mheal=100; 	mflee=0;msp = 29;	mexp=180;	mmon=1450;	monPortal=1; break BattleLoop;
						case 6:
							mname="Mountain Naga";	mhp=600;	mmp=340;		matk=51;	matkm = 57;	mdef=6;		mOffensive=6;	mbolt=67;	mheal=150;		mflee=0;msp = 33;	mexp=200;	mmon=1550;	monPortal=1; break BattleLoop;
						case 7:
							mname="Hunter Killer";		mhp=500;	mmp=100;		matk=82;matkm = 90;mdef=0;		mOffensive=3.5;mbolt=100;mheal=160;	msp = 40;	mflee=10;		mexp=245;mmon=1600;	monPortal=1;break BattleLoop;
						case 8:
							mname="Dark Boomer";		mhp=600;	mmp=120;		matk=72;matkm = 80;mdef=42;		mOffensive=2;mbolt=100;mheal=200;	msp = 25;	mflee=0;		mexp=262;mmon=1700;	monPortal=1;break BattleLoop;
						case 9:
							mname="Irate Cago";		mhp=620;	mmp=340;		matk=68;matkm = 74;mdef=4;		mOffensive=4;mbolt=83;mheal=200;	msp = 50;mflee=15;		mexp=280;mmon=1750;	monPortal=1;break BattleLoop;
						case 10:
							mname="Flesh Fiend";		mhp=800;	mmp=1200;		matk=1;matkm = 50;mdef=11;		mOffensive=10;mbolt=76;mheal=250;	msp=36;mflee=0;		mexp=300;mmon=1900;	monPortal=1;break BattleLoop;
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
						mname="Thief";	mhp=21;	mmp=1;		matk=15;	matkm = 16;	mdef=0;		mOffensive=0;	mbolt=20;	mheal=10;		mflee=0;msp = 19;	mexp=0;	mmon=0; monPortal=2;BattleSimulation Battle = new BattleSimulation();
						escape=true; bescape=true;
						if (!ql(monPortal)){mname="Bandit";	mhp=30;	mmp=5;		matk=16;	matkm =17;	mdef=0;		mOffensive=0;	mbolt=20;	mheal=10;		mflee=3;msp = 21;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal)){mname="Stealer";	mhp=45;	mmp=1;		matk=19;	matkm = 21;	mdef=0;		mOffensive=0;	mbolt=20;	mheal=10;		mflee=0;msp = 30;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal)){mname="Thief";	mhp=30;	mmp=1;		matk=15;	matkm = 16;	mdef=0;		mOffensive=0;	mbolt=20;	mheal=10;		mflee=0;msp = 19;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal)){mname="Selwin";	mhp=80;	mmp=75;		matk=20;	matkm = 26;	mdef=0;		mOffensive=6;	mbolt=23;	mheal=40;		mflee=5;msp = 32;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
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
						mname="Zergling";	mhp=120;	mmp=70;		matk=24;	matkm = 26;	mdef=3;		mOffensive=2;	mbolt=26;	mheal=60;		mflee=10;msp = 40;	mexp=0;	mmon=0; monPortal=2; Battle = new BattleSimulation();
						escape=true; bescape=true;
						if (!ql(monPortal)){mname="Roach";	mhp=145;	mmp=100;		matk=26;	matkm =28;	mdef=5;		mOffensive=3;	mbolt=32;	mheal=100;		mflee=3;msp = 28;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal)){mname="Hydralisk";	mhp=160;	mmp=300;		matk=24;	matkm = 26;	mdef=0;		mOffensive=6;	mbolt=30;	mheal=40;		mflee=5;msp = 28;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
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
						mname="Guard";	mhp=150;	mmp=150;		matk=37;	matkm =41;	mdef=3;		mOffensive=2;	mbolt=30;	mheal=60;		mflee=10;msp = 40;	mexp=0;	mmon=0; monPortal=2; Battle = new BattleSimulation();
						escape=true; bescape=true;
						if (!ql(monPortal)){mname="Assasin";	mhp=420;	mmp=230;		matk=64;	matkm = 75;	mdef=2;		mOffensive=9;	mbolt=32;	mheal=80;  mflee=5;msp = 45;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
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
						mname="Devourer";	mhp=150;	mmp=150;		matk=37;	matkm =41;	mdef=3;		mOffensive=2;	mbolt=30;	mheal=60;		mflee=10;msp = 40;	mexp=0;	mmon=0; monPortal=2; Battle = new BattleSimulation();
						escape=true; bescape=true;
						if (!ql(monPortal)){mname="Roach";	mhp=145;	mmp=100;		matk=26;	matkm =28;	mdef=5;		mOffensive=3;	mbolt=32;	mheal=100;		mflee=3;msp = 28;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal)){mname="Hydralisk";	mhp=160;	mmp=300;		matk=24;	matkm = 26;	mdef=0;		mOffensive=6;	mbolt=30;	mheal=40; mflee=5;msp = 28;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal)){mname="Roach";	mhp=145;	mmp=100;		matk=26;	matkm =28;	mdef=5;		mOffensive=3;	mbolt=32;	mheal=100;		mflee=3;msp = 28;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal)){mname="Hydralisk";	mhp=160;	mmp=300;		matk=24;	matkm = 26;	mdef=0;		mOffensive=6;	mbolt=30;	mheal=40;		mflee=5;msp = 28;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal)){mname="Assasin";	mhp=420;	mmp=230;		matk=64;	matkm = 75;	mdef=2;		mOffensive=9;	mbolt=32;	mheal=80;		mflee=5;msp = 45;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal)){mname="Hydralisk";	mhp=160;	mmp=300;		matk=24;	matkm = 26;	mdef=0;		mOffensive=6;	mbolt=30;	mheal=40;	mflee=5;msp = 28;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
						if (!ql(monPortal)){mname="Assasin";	mhp=420;	mmp=230;		matk=64;	matkm = 75;	mdef=2;		mOffensive=9;	mbolt=32;	mheal=80;mflee=5;msp = 45;	mexp=0;	mmon=0; monPortal=2;Battle = new BattleSimulation();}
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
