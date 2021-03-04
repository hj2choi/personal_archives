public class BattleSimulation extends wadv		// Simulate Artificial Intelligence which has been declared in BattlePanel.java
{
	java.util.Scanner s = new java.util.Scanner(System.in);
	{
		wadv main = new wadv();
		double mchp = main.mhp;
		double speed = main.speed;
		double mcsp = main.msp;
		int UserOrder2=0;
		String anykey="";
		boolean ice = false;
		for (int dd = 0;dd<main.clearscreen ;dd++ )
			System.out.println("");
		if (mname.equals("Training Ward"))
			System.out.println("			Entering Training Zone");
		else if (monPortal==0)
			System.out.println("Human Gateway Stage "+stage+"\n\n\n\n\n");
		else if (monPortal==1)
			System.out.println("Monster Portal Stage "+mstage+"\n\n\n\n\n");
		else if (monPortal==2)
			System.out.println("			Entering Quest Zone...");
		else
			System.out.println("");
		System.out.println("			"+main.User+"   VERSUS   "+main.mname);
		if (main.bescape==false)
		{
			for (int dd = 0;dd<6 ;dd++ )
				System.out.println("");
			System.out.println(" loading... please wait");
			for (int dd = 0;dd<3 ;dd++ )
				System.out.println("");
			System.out.print("Initializing Battle Variables");for (int b = 0;b<50 ;b++ )System.out.print("\b");main.delay(main.loading/2.5);
			System.out.print("Importing user input              ");for (int b = 0;b<50 ;b++ )System.out.print("\b");main.delay(main.loading/4);
			System.out.print("Declaring AI status and level				");for (int b = 0;b<50 ;b++ )System.out.print("\b");main.delay(main.loading/3);
			System.out.print("Implementing graphics            ");for (int b = 0;b<50 ;b++ )System.out.print("\b");main.delay(main.loading/6);
			System.out.print("Loading java classes             ");for (int b = 0;b<50 ;b++ )System.out.print("\b");main.delay(main.loading/5);
			System.out.print("Simulating Battle...            ");for (int b = 0;b<50 ;b++ )System.out.print("\b");	main.delay(main.loading/2);
		}
		/******************************************************Status Bar***********************************************************/
		BLoop : while (true)
		{
			StatusBar BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
			/***********************************************************Recieve User order****************************************************************/
			OrderLoop : while (true)						
			{
				if (speed<mcsp)
					break OrderLoop;
				speed = speed-main.msp;
				PlayerLoop : while (true)
				{
					if (ice==true)
					{
						BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
						BStatus.print();
						System.out.println("	You are Frozen becasue of Ice! \n	You cannot take any action this turn!!");
						System.out.println("Enter any key to continue\n");
						anykey = s.next();
						ice = false;
						break OrderLoop;
					}
					BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
					BStatus.print();
					System.out.println("User turn.");
					System.out.println("[1] : Attack");
					System.out.println("[2-8] : Skills");
					System.out.println("[0] : Open Skill Tab");
					System.out.println("");
					UserOrder2 = s.nextInt();
					main.skilltot++;
					switch (UserOrder2)
					{
						default:
							System.out.println("Input Denied");
							main.skilltot--;
							continue PlayerLoop;
						case 0 :									// skill tree
							System.out.println("");System.out.println("");System.out.println("");
							SkillTab SKT = new SkillTab(main.skstab, main.skdoublestrike, main.skheal, main.skdebuff, main.skpsionicstorm, main.skallin);
							SKT.print();
							System.out.println("");
							System.out.println("Enter any key to go back");
							main.skilltot--;
							main.anykey = s.next();
							System.out.println("");System.out.println("");System.out.println("");
							continue PlayerLoop;
						case 1 :
							boolean crit = false;
							main.atktot++;
							main.skilltot--;
							main.hit = (int)(Math.random()*100);					//accuracy
							if (main.hit<(main.acc-main.mflee))
							{
								main.damage = (int)(Math.random()*main.atkmax%(main.atkmax-main.atkmin))+main.atkmin;
								if (Math.random()<main.pskCritical/100)
									{main.damage=main.damage*((main.pskCritical*2+130)/100); crit = true;}
								if (main.mdef > main.damage)
									main.damage = main.mdef;
								main.damage = main.damage-main.mdef;
								mchp = mchp-main.damage;
								BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
								BStatus.print();
								System.out.println(main.User+" attacked "+main.mname+"!!");// normal atttack
								System.out.println("");
								System.out.println("");
								if (crit==true)
									System.out.println("Critical Damage! : "+(int)main.damage+"!!");
								else 
									System.out.println("Damage : "+(int)main.damage+"!!");
							}
							else						//miss
							{
								BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
								BStatus.print();
								for (int line =0;line<3 ;line++ )
									System.out.println("");
								System.out.println(main.mname+"' evaded "+main.User+"'s attack!!");
								main.misstot++;
							}
							break;
				/***********************************************User Order - Skills**************************************************/
				/**************************************************Stab******************************************/
						case 2:						// stab
							if (main.Gamemp <  (47+((int)main.skstab*3)) || main.skstab == 0)
							{
								System.out.println("You cannot use this skill!!");
								System.out.println("");
								main.skilltot--;
								continue PlayerLoop;
								
							}
							main.hit = (int)(Math.random()*100);					//accuracy
							if (main.hit<(main.acc-main.mflee))
							{
								main.Gamemp = main.Gamemp-(47+((int)main.skstab*3));								// skill mana
								main.damage = main.atkmin+(2+main.skstab*2)+((120+main.skstab*10)/110)*Math.sqrt(Math.sqrt(main.spatk));				// skill damage
								mchp = mchp - main.damage;
								BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
								BStatus.print();
								System.out.println("	 "+main.User+" stabbed "+main.mname+"!!");
								System.out.println("");
								System.out.println("");
								System.out.println("Damage: "+(int)main.damage+"!!");
							}
							else
							{
								main.Gamemp = main.Gamemp-(47+((int)main.skstab*3));								// skill mana
								BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
								BStatus.print();System.out.println("");System.out.println("");System.out.println("");
								System.out.println(main.mname+" dodged your attack!");
								main.misstot++;
							}
							break;
					/**************************************************DoubleStrike******************************************/
						case 3:						// double strike
							double[] dm = new double[3];
							if (main.Gamemp < (100+((int)main.skdoublestrike*4)) || main.skdoublestrike == 0)
							{
								System.out.println("You cannot use this skill!!");
								main.skilltot--;
								continue PlayerLoop;
							}
							
							main.Gamemp = main.Gamemp-(100+((int)main.skdoublestrike*4));
							for (int d = 0;d<2;d++)
							{
								main.hit = (int)(Math.random()*100);					//accuracy
								if (main.hit<(main.acc-main.mflee))
								{
									main.damage = ((int)(Math.random()*main.atkmax%(main.atkmax-main.atkmin))+main.atkmin)*((70+main.skdoublestrike*2)/105)+main.spatk*((20+main.skdoublestrike*1)/500);
									if (main.mdef>main.damage)
										main.damage = main.mdef;
									dm[d]=main.damage;
									mchp =mchp - main.damage+main.mdef;
								}
								else
								{
									dm[d]=-1;
									System.out.println("miss!!");
									main.misstot++;
								}
							}
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("	 "+main.User+" used Double Strike!!");
							System.out.println("");
							if (dm[0]!=-1)
								System.out.println("Damage: "+(int)(dm[0]-main.mdef)+"!!");
							else
								System.out.println("miss!!");
							if (dm[1]!=-1)
								System.out.println("Damage: "+(int)(dm[1]-main.mdef)+"!!");
							else
								System.out.println("miss!!");
							break;
				/**************************************************Heal******************************************/
						case 4:							// heal
							if (main.Gamemp < (140+((int)main.skheal*6)) || main.skheal == 0)
							{
								System.out.println("You cannot use this skill!!");
								main.skilltot--;continue PlayerLoop;
							}
							main.Gamemp = main.Gamemp- (140+((int)main.skheal*6));
							main.damage = main.hp*((20+main.skheal*2)/100)+(Math.sqrt(Math.sqrt(main.spatk))*Math.sqrt(main.spatk))*((80+main.skheal*2)/100);
							if (main.damage>main.hp/1.8)
								main.damage = main.hp/1.8;
							System.out.println("	 "+User+"Healed "+(int)main.damage+" hp!!");
							main.Gamehp = main.Gamehp+main.damage;
							if (main.Gamehp>main.hp)
								main.Gamehp=main.hp;
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println(main.User+" Used Heal!!");
							System.out.println("");
							System.out.println("");
							System.out.println("	 Healed: "+(int)main.damage+" hp!!");
							break;
					/**************************************************Debuff******************************************/
						case 5:							// debuff
							if (main.Gamemp < (220+((int)main.skdebuff*5)) || main.skdebuff == 0)
							{
								System.out.println("You cannot use this skill!!");
								main.skilltot--;continue PlayerLoop;
							}
							main.Gamemp = main.Gamemp- (220+((int)main.skdebuff*5));
							main.damage = (Math.sqrt(main.spatk*0.12)*(8.5+main.skdebuff*0.6))/150;
							if (main.damage >= 0.2)
								main.damage = 0.2;
							main.atkmin = main.atkmin+main.atkmin*main.damage/1.3;
							main.atkmax = main.atkmax+main.atkmax*main.damage/1.3;
							main.def = main.def+main.def*main.damage;
							main.acc = main.acc+main.acc*main.damage;
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("	 "+main.User+" Used Blue Aura!");
							System.out.println(main.User+"'s attack increased by : "+(int)(main.atkmax*main.damage/1.3)+"!!");
							System.out.println(main.User+"'s defence increased by : "+(int)(main.def*main.damage)+"!!");
							System.out.println(main.User+"'s Accuracy increased by : "+(int)(main.acc*main.damage)+"!!");
							break;
					/**************************************************PsionicStorm******************************************/
						case 6:							// psionic storm
							if (main.Gamemp < (260+((int)main.skpsionicstorm*4)) || main.skpsionicstorm == 0)
							{
								System.out.println("You cannot use this skill!!");
								main.skilltot--;continue PlayerLoop;
							}
							main.Gamemp = main.Gamemp- (260+((int)main.skpsionicstorm*4));
							double bojung = 0.8;
							if (main.spatk>400)
								bojung = 0.73;
							if (main.spatk>600)
								bojung = 0.66;
							if (main.spatk>800)
								bojung = 0.58;
							if (main.spatk>1100)
								bojung = 0.53;
							if (main.spatk>1400)
								bojung = 0.49;
							main.hit = (int)(Math.random()*100);					//accuracy
							if (main.hit<(main.acc-main.mflee))
							{
								main.damage = ((int)(Math.random()*main.atkmax%(main.atkmax-main.atkmin))+main.atkmin)+(bojung*main.spatk)*((146+main.skpsionicstorm*4)/320)*Math.sqrt(main.spatk/55)-main.mdef;
								main.poison = true;
								mchp = mchp-main.damage;
								BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
								BStatus.print();
								System.out.println("	 "+main.User+" spelled PsionicStorm on "+main.mname+"!!!!");
								System.out.println("");
								System.out.println("Damage: "+(int)main.damage+"!!");
								System.out.println("The Psionic Energy poisoned "+main.mname+"!!");
							}
							else
							{
								BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
								BStatus.print();System.out.println("");System.out.println("");System.out.println("");
								System.out.println(main.mname+" dodged Psionic Storm!!");
								main.misstot++;
							}
							break;
						/**************************************************Booster******************************************/
						case 7:							// Booster
							if (main.Gamemp < (272+((int)main.skpsionicstorm*8)) || main.skpsionicstorm == 0)
							{
								System.out.println("You cannot use this skill!!");
								main.skilltot--;continue PlayerLoop;
							}
							main.Gamemp = main.Gamemp- (272+((int)main.skpsionicstorm*8));
							main.damage = ((int)(Math.random()*main.atkmax%(main.atkmax-main.atkmin))+main.atkmin)+(main.spatk)*((146+main.skpsionicstorm*4)/320)*Math.sqrt(main.spatk/55)-main.mdef;
							main.poison = true;
							mchp = mchp-main.damage;
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("	 "+main.User+" spelled PsionicStorm on "+main.mname+"!!!!");
							System.out.println("");
							System.out.println("Damage: "+(int)main.damage+"!!");
							System.out.println("The Psionic Energy poisoned "+main.mname+"!!");
							break;
					}
					System.out.println("type any key to continue");
					main.Gamemp=main.Gamemp+(main.mp*((main.pskManaRest*0.8)/100));
					if (Gamemp>mp)
						Gamemp=mp;
					anykey = s.next();
					if (chk(mchp))					// continue
						break BLoop;
					break OrderLoop;
				}
			}



			/**************************************************************Artificial Intelligence  - Enemy Turn****************************************/
			CompLoop : while (true)
			{
			/**************************************************Status Bar**************************************/
				if (mcsp<speed)
					break CompLoop;
				mcsp = mcsp-main.speed;
				BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
				BStatus.print();
				System.out.println(main.mname+"'s turn. Please wait...");		// ai decision
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				/**************************************************AI******************************************/
				delay(main.loading);
				msloop : while (true)
				{
					main.AI = (Math.random()*10);
					/**************************************************Special Skill******************************************/
					if (Math.random()*10<mSpskill && mmp>=90)
					{
						mSpskill = Math.random();
						mIceStrike=mIceStrike-(mIceStrike*0.1)+(Math.random()*(mIceStrike*0.2));	// Random Damage +- 10%
						if (mSpskill>0.66 || mchp<mIceStrike)
						{
							main.Gamehp = main.Gamehp-mIceStrike/1.3;
							main.mmp = main.mmp-90;
							ice = true;
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("		 "+main.mname+" used Skill!!");
							System.out.println("Ice Strike!!");
							System.out.println("");
							System.out.println("Damage "+((int)(main.mIceStrike/1.3))+"!! Ice Strike freezed "+User+"!!");
						}
						else if (mSpskill>0.33)
						{
							main.Gamehp = main.Gamehp-mIceStrike/1.6;
							main.Gamemp = main.Gamemp-(mp/20)-main.mIceStrike/3;
							mchp = mchp+mIceStrike/1.6;
							main.mmp = main.mmp-55;
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("		 "+main.mname+" used Skill!!");
							System.out.println("Drain!!");
							System.out.println("");
							System.out.println("Drained "+(int)(main.mIceStrike/1.6)+"hp and "+(int)((mp/20)+main.mIceStrike/3)+"mp!!");
						}
						else
						{
							main.Gamehp = main.Gamehp-mIceStrike*1.5+def;
							mchp = mchp-mIceStrike/1.5;
							main.mmp = main.mmp-90;
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("		 "+main.mname+" used Skill!!");
							System.out.println("Charge!!");
							System.out.println("");
							System.out.println("Damage "+(int)(main.mIceStrike*1.5)+"!! Charge damaged himself by"+(int)(main.mIceStrike/1.5)+"!");
						}
						break msloop;
					}
					/**************************************************attack******************************************/
					if (main.AI>main.mskill)
					{
						main.damage = (int)(Math.random()*main.matkm%(main.matkm-main.matk))+main.matk;
						if (main.def > main.damage)
							main.damage = main.def;
						if (Math.random()<(pskDodge*3.5/100))
						{	main.damage = main.def;
							System.out.println("\n"+main.User+" dodged "+main.mname+"'s attack!!\n\n");
						}
						main.damage = main.damage-main.def;
						main.Gamehp = main.Gamehp-main.damage;
						BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
						BStatus.print();
						System.out.println(main.mname+" attacked "+main.User+"!!");
						System.out.println("");
						System.out.println("");
						System.out.println("Damage : "+(int)main.damage+"!!");
						break msloop;
						
					}
					/**************************************************Heal******************************************/
					else if ((main.AI<main.mskill/3 && mchp<main.mhp/1.7 ) || (main.AI<main.mskill/1.5 && mchp<main.mhp/3) )		// use heal when health is low, in certain possiblity
					{
						if (main.mmp<60)
							continue msloop; 
						mchp = mchp+main.mheal;
						main.mmp = main.mmp-60;
						BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
						BStatus.print();
						System.out.println("		 "+main.mname+" used Skill!!");
						System.out.println("Heal!!");
						System.out.println("");
						System.out.println("Healed "+(int)main.mheal+"hp!!");
						break msloop;
					}
					/***********************************************Attack Skill*************************************************/
					else
					{
						if (main.mmp<50)
							continue msloop;
						
						main.AI = Math.random();
						if (main.AI>0.66)
						{
							main.printsave = "Energy Shot!";
							main.AIdmg = 0.88;
						}
						else if (main.AI<=0.66 && main.AI>0.33)
						{
							main.printsave = "Critical Strike!";
							main.AIdmg = 1;
						}
						else
						{
							main.printsave = "Blast Fire!";
							main.AIdmg = 1.12;
						}
						main.Gamehp=main.Gamehp-main.mbolt;
						main.mmp = main.mmp-50;
						BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
						BStatus.print();
						System.out.println("	 	 "+main.mname+" used skill!!!");
						System.out.println(main.printsave);System.out.println("");
						System.out.println("Damage : "+(int)main.mbolt+"!!");
						break msloop;
					}
				}
					if (main.poison == true)
					{
						main.damage = (mchp/55)*(145+main.skpsionicstorm*5)/200+Math.sqrt(main.spatk)*(145+main.skpsionicstorm*5)/200;
						System.out.println("poison damage to "+main.mname+": "+(int)main.damage);
						mchp = mchp - main.damage;
					}
					else
						System.out.println("");
					if (chk(main.Gamehp))
						break BLoop;
					if (chk(mchp))
						break BLoop;
					System.out.print("Enter any key to continue : ");
					anykey=s.next();
					break CompLoop;
			}
		}
		/***************************************************************battle result****************************************************************************/
		/************************************Victory*********************************************/
		if (chk(mchp) && monPortal!=2)						// won!!
		{
			main.mobCount++;
			for (int k = 0;k<main.clearscreen ;k++ )
				System.out.println("");
			if (main.mname.equals("Training Ward")==false && main.monPortal==0)
				main.stage++;
			if (main.mname.equals("Training Ward")==false && main.monPortal==1)
				main.mstage++;
			System.out.println("				You defeated "+main.mname+"!!\n\n");
			System.out.println("		You gained "+main.mexp+" EXP and earned  $"+main.mmon);
			System.out.println("");
			if (Math.random()<(0.1+((double)main.lv/300)) && main.mname.equals("Training Ward")==false && monPortal!=2)
			{
				main.chrystal = main.chrystal+1;
				System.out.println("		***Congrats! You found a Crystal!***");
			}
			else
				System.out.println("");
			System.out.println("");
			if (main.monPortal!=2)
			{
				main.exp = main.exp+main.mexp;
				main.Exptot = main.Exptot+main.mexp;
				main.money = main.money+main.mmon;
				main.montot = main.montot+main.mmon;
				System.out.println("");
				System.out.println("		Current EXP: "+main.exp+"/"+main.expmax);
				main.hpb = hpbar((double)main.exp/(double)main.expmax, main.bardot);
				System.out.println("		"+main.hpb);
				System.out.println("");
				System.out.println("		Cash: "+main.money);
			}
			else
				for (int k = 0;k<5 ;k++ )
					System.out.println("");
			for (int k = 0;k<4 ;k++ )
				System.out.println("");
			System.out.println("Enter any key to continue");
			anykey = s.next();
			//kerriganb = true; kd = 5;// enable it to see ending credit
			if (main.kerriganb == true && main.kerriganbeat == false)
			{
							if (main.kd == 5)
							{
								for (int victory = 0;victory<main.clearscreen ;victory++ )
								{
									System.out.println("");
								}
								main.kerriganbeat = true;
								TextAnimation tAni = new TextAnimation("			Finally!! You defeated Kerrigan!!");					// Game Victory
								tAni = new TextAnimation("			You made a outstanding revenge on her!");
								tAni = new TextAnimation("			You are the strongest warrier in this java world!");
								System.out.println("");
								System.out.println("");
								System.out.println("");
								System.out.println("");
								delay(main.loading);
								tAni = new TextAnimation("enter any key to continue...");
								anykey = s.next();
								for (int victory = 0;victory<main.clearscreen ;victory++ )
								{
									System.out.println("");
								}
								System.out.println("			Extremely Bored Warrier's Adventure"); delay(500);
								System.out.println("");delay(1500/3);
								System.out.println("				Project Lead");delay(1500/3);
								System.out.println("				HongJoon Choi");delay(1500/3);
								System.out.println("");delay(1500/3);
								System.out.println("			Director / Producer");delay(1500/3);						// Credits
								System.out.println("				HongJoon Choi");delay(1500/3);
								System.out.println("");delay(1500/3);
								System.out.println("			Graphic Designer");delay(1500/3);
								System.out.println("				HongJoon Choi");delay(1500/3);
								System.out.println("				JoonRock Choi");delay(1500/3);
								System.out.println("				BonWoong Koo");delay(1500/3);
								System.out.println("");delay(1500/3);
								System.out.println("			Programmer");delay(1500/3);
								System.out.println("				HongJoon Choi");delay(1500/3);
								System.out.println("");delay(1500/3);
								System.out.println("			Quality Assuarance");delay(1500/3);
								System.out.println("				HongJoon Choi");delay(1500/3);
								System.out.println("				JoonRock Choi");delay(1500/3);
								System.out.println("");delay(1500/3);
								System.out.println("			Level Designer");delay(1500/3);
								System.out.println("				HongJoon Choi");delay(1500/3);
								System.out.println("");delay(1500/3);
								System.out.println("				Thanks to...");delay(1500/3);
								System.out.println("				My Dad");delay(1500/3);
								System.out.println("				Hyungnam Oh");delay(1500/3);
								System.out.println("				JoonRock Choi");delay(1500/3);
								System.out.println("				Seowoo Hong");delay(1500/3);
								System.out.println("				Mr.Marcos");delay(1500/3);
								System.out.println("				Ice Lemon Tea");delay(1500/3);
								System.out.println("			  Overwhelming IB homeworks");delay(1500/3);
								System.out.println("		 My Labtop with incredible battery life span.");delay(1500/3);
								System.out.println("");delay(1500/3);
								System.out.println("");delay(1500/3);
								System.out.println("				2011.13.December");delay(1500/3);
								System.out.println("				Some rights Reserved");delay(1500/3);
								System.out.println("I am proud to have a program with 2000 line of code with 200 Variables!!");delay(1500/3);
								for (int i =0;i<23 ;i++ )
								{System.out.println("");delay(1500/3);}
								System.out.println("");delay(1500/3);
								System.out.println("			enter any key to continue...");delay(1500/3);
								anykey = s.next();
								for (int victory = 0;victory<main.clearscreen ;victory++ )
								{
									System.out.println("");
								}
								main.currenttime = System.currentTimeMillis();
								tAni = new TextAnimation("		It has been "+(int)((main.currenttime-main.starttime)/3600000)+" hours and "+(((main.currenttime-main.starttime)/60000)%60000)+" minitues since the game started!");
								tAni = new TextAnimation("		Total Score :	"+(int)(((main.currenttime-main.starttime)/1000)+((double)main.montot/100)+((double)main.Exptot/10)+((double)main.mobCount*100)+(main.atktot*5)+(main.skilltot*5)+(main.lv*400)));
								tAni = new TextAnimation("		Thank you very much for playing Warrier's Adventure!!");
								System.out.println("				Thank You "+main.User+"!!");
								System.out.println("");delay(main.loading/3);
								System.out.println("");delay(main.loading/3);
								tAni = new TextAnimation("			Developed by Hong Joon Choi");
								tAni = new TextAnimation("Type any key to continue...");
								anykey = s.next();
								for (int vitory = 0;vitory<main.clearscreen ;vitory++ )
								{
									System.out.println("");
								}
								tAni = new TextAnimation("				Secret Shop Unlocked!!");
								tAni = new TextAnimation("				Gym Mode Unlocked!!");
								tAni = new TextAnimation("			enter any key to continue...");
								anykey = s.next();
							}
			}
			if (main.stage==11 && main.mname.equals("Training Ward")==false && main.monPortal == 0)
			{main.quest++; for(int k = 0;k<main.clearscreen ;k++ ){System.out.println("");}System.out.println("			New Quest Available!!"); for(int k = 0;k<11 ;k++ ){System.out.println("");} System.out.println("Enter anykey to continue..."); anykey=s.next();}
			if (main.stage==18 && main.mname.equals("Training Ward")==false && main.monPortal == 0)
			{main.quest++; for(int k = 0;k<main.clearscreen ;k++ ){System.out.println("");}System.out.println("			New Quest Available!!"); for(int k = 0;k<11 ;k++ ){System.out.println("");} System.out.println("Enter anykey to continue..."); anykey=s.next();}
			if (main.stage==22 && main.mname.equals("Training Ward")==false && main.monPortal == 0)
			{main.quest++; for(int k = 0;k<main.clearscreen ;k++ ){System.out.println("");}System.out.println("			New Quest Available!!"); for(int k = 0;k<11 ;k++ ){System.out.println("");} System.out.println("Enter anykey to continue..."); anykey=s.next();}
			if (main.stage==30 && main.mname.equals("Training Ward")==false && main.monPortal == 0)
			{main.quest++; for(int k = 0;k<main.clearscreen ;k++ ){System.out.println("");}System.out.println("			New Quest Available!!"); for(int k = 0;k<11 ;k++ ){System.out.println("");} System.out.println("Enter anykey to continue..."); anykey=s.next();}
			if (main.mstage==11 && main.mname.equals("Training Ward")==false && main.monPortal == 1)
			{main.quest++; for(int k = 0;k<main.clearscreen ;k++ ){System.out.println("");}System.out.println("			New Quest Available!!"); for(int k = 0;k<11 ;k++ ){System.out.println("");} System.out.println("Enter anykey to continue..."); anykey=s.next();}
			if (main.mstage==20 && main.mname.equals("Training Ward")==false && main.monPortal == 1)
			{main.quest++; for(int k = 0;k<main.clearscreen ;k++ ){System.out.println("");}System.out.println("			New Quest Available!!"); for(int k = 0;k<11 ;k++ ){System.out.println("");} System.out.println("Enter anykey to continue..."); anykey=s.next();}
			if (main.stage==16 && main.mname.equals("Training Ward")==false && main.monPortal == 0)
			{for(int k = 0;k<main.clearscreen ;k++ ){System.out.println("");}System.out.println("			Monster Portal Unlocked!!!"); for(int k = 0;k<11 ;k++ ){System.out.println("");} System.out.println("Enter anykey to continue..."); anykey=s.next();}
		}
		/************************************Defeat*********************************************/
		if (chk(main.Gamehp))						// lost!!
		{
			for (int k = 0;k<main.clearscreen ;k++ )
				System.out.println("");
			System.out.println(main.User+"'s HP: 0 / "+(int)main.hp+"		"+main.User+"'s MP: "+(int)main.Gamemp+" / "+(int)main.mp);
			main.kd = 0;
			main.hpb = hpbar(main.Gamehp/main.hp, main.bardot);
			System.out.println(main.hpb);
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("			You Lost! "+main.mname+" Beated You!!");
			System.out.println("");
			if (mname.equals("Training Ward"))
				System.out.println(" You failed to get Training Experience Points! Try again later.");
			else
			{
				System.out.println("		You Lost $"+(int)(main.money/5)+" please try beating "+main.mname+" again later");
				//main.money = main.money-main.money/5;
				main.dietot++;
			}
			System.out.println("");
			System.out.println("");
			System.out.println("Enter any key to continue");
			anykey = s.next();
			main.monPortal = 5;
		}
	}
	public static void delay(double temp)
	 {
	  try 
	{ Thread.sleep((int)temp); }
	  catch (Exception e)
	{ System.out.println("Delay Function Error \n Caused by:"+e); }
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
		if (t>20)
			t=20;
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

}

