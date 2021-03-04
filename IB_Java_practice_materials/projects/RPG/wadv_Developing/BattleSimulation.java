/*																				
(Class Structure Summary)

BLoop(BattleLoop)	// loop for battle
{
	Loading screen (delay)
	PlayerLoop
	{
		does fndamenatal operation including cheking speed, freeze, etc.
		Switch	
		{
			Accepts user order and excecutes operation
		}
		Fundamental operation for checking hp,mhp and mana Restoration
	compLoop
	{
		Fundamental operation for AI
		msloop			
		{
			Accept AI's order.
		}
		Fundamental operation for AI
	}
}	
If(Result : mobhp<0)
	Reward, escape class. (Present credit if it satisfy condition)
If(Result : playerhp<0)
	Penalty, escape class.

*/
/*																					*************Spell Source****************
Maximum Impectra	
Serpensortia	�����̳����� ������ �Ͼ�鼭 ��ٶ� � ���� �ҷ�����.
Sectumsempra	ȥ�������� å�� �����ִ� �ֹ�, ������ �ʴ� Į�� ��븦 ��������� ������ ���ݸ����̴�.
Reducto		�ձ��� ���θ��� �ܴ��� ��ü�� ���Ľ�Ų��.[�ѱ�����] =���� ����(4��), =������(5��)
Diffendo		�ظ��� ������ ������ ���� �ֹ��� ������� �ѼҸ� ���� ����� ������ ���鼭 ������ ¦ ��������. 
Obscuro		���������� ������ ���� ���� ���ϰ� �ϴ� �ֹ�, �츣�̿´��� �ǴϾ ������������ ���ڷ� �ҷ��� �� �� �ֹ��� ��ġ�� ���� ���������� �׸� ���� ĥ������ �ǴϾ�� ��ī�Ӱ� � ���� �� ���� ���ȴ�.
Incarcerous	���� �� ���� ������ ���� ����� ���� ĪĪ ����Ű� �ְ��´�.����ȭ/5�����긴���� ��Ÿ��ν��� ���ٷ� ���� �� ����ߴ�
Tergeo		�츣�̿´��� �ظ� ���� ������� ���ڱ��� ���ٶ� ����� �ֹ�
Furnumculus 	ũ�� ���� ���� ������� ����� �ϴ� �ֹ�
Flagrate
//////////Spell name/////////
Lumos - lighting spell
Petrificus Totalus - stunning spell
Brachium Emendo - 2���� �� ��ġ�� ����
Stupefy - ���ܾ� stupefy���� ���� ���� ����. 4�ǿ��� ó������ �����ϴ��� �Ĺݺο��� �����ҳ� ���� �ٴϴ� �������� ���� ����. �׷��� �������� ���������̴� ��ü�� �ı��� ���� �ִ� ���� ���� �������� ������ ���� ������ �޶����� ������ �����ȴ�. �����ϴ��� ������� ������ �Ǹ� �ƿ� ƨ�ܳ��� �� ���� �����ؼ�, ������ ���������̸� ���� �� �°� ���� �ѵ��� ��Ÿ�̾��ߴ� �ư��� �����ʹ� �޸� ����ȥ���� �ر׸���� ������ ��������� ������ ���������̸� �����ذ��鼭 ��ƿ �� �־���.
Enervate - ������ ���� ����� ����� ����.
Wingardium Leviousa - 
Expeliarmus - �� ������ ������ ���ϰ� �ִ� ������ �����ǰ� �����ϱ� �������� ������[11]. 4�ǿ��� ��¥���𱳼��� �뼭���� ���� ���ָ� �����Ҷ� �������� �̷��Ǵ� ���� �ֹ��̶� ���� �����Ŀ� ���� ȿ���� ������ �ٸ� �� ����. ����� �����縮�Ƹ����� ���� ������ ���������̺��� ���� �⼼ 2�ǿ��� ó�� ������ ���� �ظ� ���͸� ��¡�ϴ� �������� �ڸ� ��Ҵ�. 7�ǿ��� ���ɿ��� �ַ� ����ġ�� �ʹ� ���� ������ �ƴϳĴ� �Ҹ����� ������� �ᱹ�� �� �������� �����Ʈ�� �ƹٴ� �ɴٺ�� ƨ�ܳ� �����Ʈ�� ����´�.
Incendio - ȭ���� �߻��Ͽ� �����ϴ� ����. ���� ��� incen-�� ���� ���Ѵ�.
Confringo - Explosive reaction cast
Protego - Protect�� ����� �δ� ���� ���� ����. ����� �ֹ��� ƨ�ܳ� �� �ִ� �������� ����� ������ �����̴�. ��ǻ� �ƹ� �����̳� �̰� �ϳ��� ���� ƨ�ܳ� �� �ִ� �������ݻ� ����. ���� �������� ���ؿ� ���� ��� ������ �Ѱ�� �����Ѵ�. �ƹٴ� �ɴٺ��� �� �Ǵ� ��.[13] �׷� �������� �� ������ �ִٴ� �ǰ�. �� ���� ���
Fianto Duri - �ٸ� �ֹ��� ���� ���ϰ� ����� �ֹ�. ��ƾ�� Fiant(�Ǵ�)�� ���������� Duri(����)�� �ռ���� "��������"�� ���� �ȴ�. ������ ���� part 2���� �����װ� ���ø�, ����� �̴Ϲ̰����� �Բ� ���Ǿ���.
Expulso - Rapid-fire cast

//////////Curse name/////////
Mosmodre - �ϴÿ� �����Ʈ�� ��¡�� ����� ǥ��(Dark Mark)�� ������ ����. 
Sectumsempra - ���������� �������� ����, ������ ������ Į�� ���� ���� ��ó�� ����� ������ ����Ų��.
Imperio - ����� ���� ����� ���� �� �� �ְ� ���ִ� ����. ������ ������ ���ؾ� ����� ����� �θ� �� �ִ�. ������ �Ĺݰ��� �����̵� ���� �ظ��� ���� �����ҳ� �� ����
Crucio - ���濡�� ������ ������ �����ϱ⶧���� �������� ũ��ÿ����� ���ָ� �ް� �Ǹ� �ᱹ �ߵ��� ���ϰ� ���Ĺ����ų� �״´�. ������ �׺� �չ����� �θ� �� ���ָ� �°�� �Ƶ� �󱼵� �� �˾ƺ� ������ ���Ĺ�����. �����̸� ��� ��� �־�� ��ȿ�� ä�θ� �����̸�, 
Avada Kedavra - �ƹٴ� �ɴٺ�� - ��� ����. ����� �� �ʷϺ� �Һ��� ���ư���. �����Ʈ�� ����� ������ �Դ� �ڵ��� ���� ��������� ó�� ������ ���� �� ������ �λ��� �־���. ������ ���ϸ� �׸� �̱� ������ ���� �������� �״��� ���� �ʾҰ�, 4�ǿ��� ����(�� ������ ũ���ġ �ִϾ�)�� ������δ� ������ ������ ���� ������ ���� ������ ���� ������ �� ����. ���ߴ����� ���� ���и������ε� ���� �� ���� �׳� ����� �� ������ ���� ����� ���� ���������̿� �ε����� ���ȴٴ���... ��° ���ϸ� �׸��� �͵� �׷��� ����ġ��� �Ƚ��Ϸ�. �翬�ϰ����� �� ������ ��鵵 ������ ������ ����� �翬�� ���ǳ���. �׷��� 7�ǿ����� ��û�ϱ�� ������ ũ���̺�� ���ϵ� ����� �� �ְ� �ȴ�(��).
																				*************AI Spell Design****************
								---------Offensive------									  															  Level dmg%	sp	mana		AI Calculation
Diffendo							basic attacking spell	Last option of AI decision													0~	100		1			20		Last AI option
/====Basic Spell
1. Flagrate						piercing defence cast(100% acc)																		1~	100+	1			50		Player has high defence
2. Scourge						Rapid Cast(Usually used upon defenceless,protected player)						1~	40		0.3		20		Player has less defence, with protego
//====High Level Spell
3. Serpensortia				Slow, powerful cast.Decreases accuracy of player(Increase flee)					3~	170		1.5		80		Used upon player with lower health, without protego
4. Sectumsempra			fast, powerful, protego piercing cast.																	4~	130		0.8		90		Used when caster has more mana, when player has more health
5. Tergeo						cast that cost less mana. Increase mdef. delayed damage	 							3~	120		1			60		When caster has less mana, player has higher hp
6. Incarcerous				Stunning spell.																										5 ~	60		1			100		Caster has lower health, when player has no protego
//====Forbidden Spell
7.Imperio						Drains Player's health and mana																		sp~	80		1			80							
8.Crucio							Pain Curse.	Gives player AND CASTER damage for a period of time				sp~	130		1			110		AI cast these curse whenever it feel like it.
9.Avada Kedavra			Death curse. Deals Massive Damage.	Need casting time									sp~	300		2			140		

								---------Defensive--------
1. Protego						- protective spell																									2~	-			0.5		40			Prefer caster and player with high mp
2. BrachiumsEmendo	- healing spell																										0~	30		1			90			Prefer caster with low hp
3. Mosmodre					- Increase strength																								4~	7			1.3		120			Prefer caster with high hp,mp

AI, computer turn ������
*/
public class BattleSimulation extends wadv		// Simulate Artificial Intelligence which has been declared in BattlePanel.java
{
	wadv main = new wadv();
	private double mchp = main.mhp;
	private double speed = main.speed;
	private double mcsp = main.msp;
	private double mfmp = mmp;
	private int UserOrder2=0;
	private String anykey="";
	private boolean ice = false;
	private byte tergeo = 0;
	private int protect = 0;
	private int mProtect = 0;
	private int RestNo = 0;
	private int mRestNo = 0;
	private String printSave = "";
	java.util.Scanner s = new java.util.Scanner(System.in);
	{
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
			PrintLine(1);
		System.out.println("			"+main.User+"   VERSUS   "+main.mname);
		if (main.bescape==false)
		{
			PrintLine(6);
			System.out.println(" loading... please wait");
			PrintLine(3);
			System.out.print("Initializing Battle Variables");for (int b = 0;b<50 ;b++ )System.out.print("\b");delay(main.loading/2.5);
			System.out.print("Importing user input package              ");for (int b = 0;b<50 ;b++ )System.out.print("\b");delay(main.loading/4);
			System.out.print("Loading AI status				");for (int b = 0;b<50 ;b++ )System.out.print("\b");delay(main.loading/3);
			System.out.print("Implementing graphics            ");for (int b = 0;b<50 ;b++ )System.out.print("\b");delay(main.loading/6);
			System.out.print("Loading battle data             ");for (int b = 0;b<50 ;b++ )System.out.print("\b");delay(main.loading/5);
			System.out.print("Simulating Battle...            ");for (int b = 0;b<50 ;b++ )System.out.print("\b");	delay(main.loading/2);
		}
		/******************************************************Status Bar***********************************************************/
		BLoop : while (true)
		{
			StatusBar BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
/************************************************************************Recieve User order*******************************************************************************/
			PlayerLoop : while (speed>=mcsp)
			{
				printSave = "";
				if (ice==true)
				{
					BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
					BStatus.print();
					System.out.println("	You are Binded by chain!! \n	You cannot take any action this turn!!");
					System.out.println("Enter any key to continue\n");
					anykey = s.next();
					ice = false;
					break PlayerLoop;
				}
				BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
				BStatus.print();
				System.out.println("User turn."+(int)speed+"  "+(int)mcsp);
				System.out.println("[1] : Attack");
				System.out.println("[2-8] : Skills");
				System.out.println("[0] : Open Skill Tab");
				PrintLine(1);
				UserOrder2 = s.nextInt();
				main.skilltot++;
				switch (UserOrder2)
				{
					default:
						skilltot--;
						System.out.println("Input Denied");
						continue PlayerLoop;
					case 0 :									// skill tree
						PrintLine(3);
						SkillTab SKT = new SkillTab(main.skstab, main.skdoublestrike, main.skheal, main.skdebuff, main.skpsionicstorm, main.skallin, skConfringo, skProtego);
						SKT.print();
						PrintLine(1);
						System.out.println("Enter any key to go back");
						main.skilltot--;
						main.anykey = s.next();
						PrintLine(3);
						continue PlayerLoop;
//===============================================Stupefy==========================================================
					case 1 :
						if (Gamemp<20+(int)(lv/3))
						{
							main.skilltot--;
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("	You have to regenerate mana!");
							PrintLine(3);
							speed = DeductSpeed(speed, msp, 1);
							break;
						}
						Gamemp=MpCalc(Gamemp, 20+(int)(lv/3));
						if (Accuracy(acc, mflee))
						{
							damage = FinalDamage(Damage(atkmax, atkmin), pskCritical, mdef);
							if (mProtect>0)
							{
								damage = DamageAbsorb(damage, mDefensive);
								mchp = HpCalc(mchp, damage/2);	 mmp = MpCalc(mmp, lv*2.5);mProtect--; printSave = "Spell Absorbed!!";
							}
							else
								mchp = HpCalc(mchp, damage);
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("	 "+main.User+" casted Stupefy!!");// normal atttack
							PrintLine(2);
							System.out.println("Damage : "+(int)main.damage+"!! "+printSave);
						}
						else						//miss
						{
							misstot++;
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();PrintLine(3);
							System.out.println(main.mname+"' dodged "+main.User+"'s spell!!");
						}
						speed = DeductSpeed(speed, msp, 1);
						break;
//===============================================Expeliarmus==========================================================
					case 2:						// stab
						if (main.Gamemp <  (47+((int)main.skstab*3)) || main.skstab == 0)
						{
							main.skilltot--;
							continue PlayerLoop;
						}
						if (Accuracy(acc, mflee))
						{
							Gamemp=MpCalc(Gamemp, (47+main.skstab*3));								// skill mana
							damage=Damage(atkmax,atkmax)+2+main.skstab*2;
							damage=FinalDamage(damage,pskCritical,0);
							if (mProtect>0)
							{
								mchp = HpCalc(mchp, damage/1.8);	 mmp = MpCalc(mchp, 10+lv*3);mProtect--;printSave = "Pierced Protego!";
							}
							else
								mchp=HpCalc(mchp, damage);
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("	 "+main.User+" casted Expeliarmus!!");
							PrintLine(2);
							System.out.println("Damage: "+(int)damage+"!! "+printSave);
						}
						else
						{
							Gamemp=MpCalc(Gamemp, (47+main.skstab*3));								// skill mana
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();PrintLine(3);
							System.out.println(main.mname+" dodged your spell!");
							main.misstot++;
						}
						speed = DeductSpeed(speed, msp, 1.15-main.skstab*0.03);
						break;
//===============================================Expulso==========================================================
					case 3:						// double strike
						if (main.Gamemp < (30+((int)main.skdoublestrike*1)) || main.skdoublestrike == 0)
						{
							System.out.println("You cannot use this skill!!");
							main.skilltot--;
							continue PlayerLoop;
						}
						if (Accuracy(acc, mflee))
						{
							Gamemp=MpCalc(Gamemp, (30+main.skstab*1));								// skill mana
							damage=Damage(atkmax,atkmax)*((60+skdoublestrike/2)/100);
							damage=FinalDamage(damage,pskCritical,mdef);
							if (mProtect>0)
							{
								damage = DamageAbsorb(damage, mDefensive);
								mchp = HpCalc(mchp, damage/2);	 mmp = MpCalc(mmp, lv*1.8);mProtect--;printSave = "Spell Absorbed!!";
							}
							else
								mchp=HpCalc(mchp, damage);
							BStatus = new StatusBar(User, Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("	 Expulso!!");
							PrintLine(2);
							System.out.println("Damage: "+(int)damage+"!! "+printSave);
						}
						else
						{
							Gamemp=MpCalc(Gamemp, (30+main.skstab*1));								// skill mana
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();PrintLine(3);
							System.out.println("miss!");
							main.misstot++;
						}
						speed = DeductSpeed(speed, msp, 0.5-main.skstab*0.02);
						break;
//===============================================Protego==========================================================
					case 7:							// Protego
						if (main.Gamemp < (50+((int)main.skProtego*3)) || main.skProtego == 0)
						{
							main.skilltot--;continue PlayerLoop;
						}
						Gamemp = MpCalc(Gamemp, (50+((int)main.skheal*3)));
						protect = 3;
						BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
						BStatus.print();
						System.out.println("	 "+User+" casted Protego!!");
						PrintLine(2);
						System.out.println("	 This protective spell lasts 3 turns");
						speed = DeductSpeed(speed, msp, 0.5-(0.015*skProtego));
						break;
//===============================================Ennervate==========================================================
					case 8:							// heal
						if (main.Gamemp < (150+((int)main.skheal*5)) || main.skheal == 0)
						{
							main.skilltot--;continue PlayerLoop;
						}
						Gamemp = MpCalc(Gamemp, (150+((int)main.skheal*5)));
						damage = main.hp*((20+main.skheal*2)/100)+(Math.sqrt(Math.sqrt(main.spatk))*Math.sqrt(main.spatk))*((80+main.skheal*2)/100);
						if (damage>main.hp/1.8)	// prevent OP
							damage = main.hp/1.8;
						Gamehp = Gamehp+damage;
						if (main.Gamehp>main.hp)	//prevent bug
							main.Gamehp=main.hp;
						BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
						BStatus.print();
						System.out.println("	 "+User+" casted Ennervate!!");
						PrintLine(2);
						System.out.println("	 Restored: "+(int)main.damage+" hp!!");
						speed = DeductSpeed(speed, msp, 1);
						break;
//===============================================Fianto Duri==========================================================
					case 9:							// debuff
						if (main.Gamemp < (220+((int)main.skdebuff*5)) || main.skdebuff == 0)
						{
							System.out.println("You cannot use this skill!!");
							main.skilltot--;continue PlayerLoop;
						}
						main.Gamemp = main.Gamemp- (220+((int)main.skdebuff*5));
						main.damage = (Math.sqrt(main.spatk*0.12)*(8.5+main.skdebuff*0.6))/150;
						if (main.damage >= 0.15)
							main.damage = 0.15;
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
//===============================================Impedimenta==========================================================
					case 4:							// Impedimenta
						if (main.Gamemp < (170+((int)main.skpsionicstorm*6)) || main.skpsionicstorm == 0)
						{
							main.skilltot--;continue PlayerLoop;
						}
						main.Gamemp = main.Gamemp- (170+((int)main.skpsionicstorm*6));
						double bojung = 0.8;
						if (main.spatk>400)
							bojung = 0.73;
						if (main.spatk>600)
							bojung = 0.66;
						if (main.spatk>850)
							bojung = 0.58;
						if (main.spatk>1200)
							bojung = 0.53;
						if (main.spatk>1550)
							bojung = 0.49;
						if (Accuracy(acc, mflee))
						{
							main.damage = ((int)(Math.random()*main.atkmax%(main.atkmax-main.atkmin))+main.atkmin)+(bojung*main.spatk)*((146+main.skpsionicstorm*4)/320)*Math.sqrt(main.spatk/55)-main.mdef;
							main.damage = FinalDamage(damage, pskCritical, mdef);
							main.poison = 4+(int)(main.skpsionicstorm/2.5);
							if (mProtect>0)
							{
								damage = DamageAbsorb(damage, mDefensive);
								mchp = HpCalc(mchp, damage/2);	 mmp = MpCalc(mmp, lv*4);mProtect--;printSave = "Spell Absorbed!!";
							}
							else
								mchp = mchp-main.damage;
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("	 "+main.User+" spelled Impedimenta on "+main.mname+"!!!!");
							PrintLine(1);
							System.out.println("Damage: "+(int)main.damage+"!!"+printSave);
							System.out.println("The Spell Energy poisoned "+main.mname+"!!");
						}
						else
						{
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();System.out.println("");System.out.println("");System.out.println("");
							System.out.println(main.mname+" dodged your spell!");
							main.misstot++;
						}
						speed = DeductSpeed(speed, msp, 1.5-(0.02*skpsionicstorm));
						break;
//===============================================Petrificus Totalus==========================================================
					case 5:							// Petrificus Totalus
						
						if (main.Gamemp < (180+((int)main.skallin*5)) || main.skallin == 0)
						{
							System.out.println("You cannot use this skill!!");
							main.skilltot--;continue PlayerLoop;
						}
						if (Accuracy(acc, mflee))
						{
							main.Gamemp = main.Gamemp- (180+((int)main.skallin*5));
							main.damage = Damage(atkmin, atkmax);
							main.damage = FinalDamage(damage, pskCritical, mdef);
							if (mProtect>0)
							{
								damage = DamageAbsorb(damage, mDefensive);
								mchp = HpCalc(mchp, damage/2);	 mmp = MpCalc(mmp, lv/2.5);mProtect--;printSave = "Spell Absorbed!!";
							}
							else
								mchp = mchp-main.damage;
							mcsp = DeductSpeed(mcsp, main.speed, 1);
							msp-=2;
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("	 "+main.User+" casted Petrificus Totalus on "+main.mname+"!!!!");
							PrintLine(1);
							System.out.println("Damage: "+(int)main.damage+"!! "+"Spell Absorbed!!");
							System.out.println("The spell freezed "+main.mname+"!!");
						}
						else
						{
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();System.out.println("");System.out.println("");System.out.println("");
							System.out.println(main.mname+" dodged spell");
							main.misstot++;
						}
						speed = DeductSpeed(speed, msp, 1);
						break;
				}
				System.out.println("Enter any key to continue");
				anykey = s.next();
				tergeo--;
				if (tergeo==1)
					HpCalc(Gamehp, Gamehp*(0.05+mOffensive*0.003));
				if (MpRest(main.speed, speed, RestNo, msp, lv))
				{
					main.Gamemp=main.Gamemp+5+lv+(main.mp*((5+main.pskManaRest*0.8)/100));
					RestNo++;
				}
				if (Gamemp>mp)
					Gamemp=mp;
				if (chk(mchp))					// continue
					break BLoop;
			}
			
			
/***************************************************************************************Artificial Intelligence******************************************************************************************/
			CompLoop : while (mcsp>speed)
			{
				printSave = "";
				BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
				BStatus.print();
				System.out.println(main.mname+"'s turn. Please wait...");		// ai decision
				PrintLine(4);
				delay(main.loading);
				msloop : while (true)
				{
					AI = (Math.random()*10);
/***************************************************************************************Curse******************************************************************************************/
//======================================Curse============================================================
					System.out.println("Stage A");
					switch (CurseAI(1,1,1,1,1))
					{
					case 1:
						break;
					}
/***************************************************************************************Deffensive******************************************************************************************/

					System.out.println("Stage B");
					switch (DefensiveAI(mmp, mDefensive, mchp/mhp, mmp/mfmp, mProtect, Gamemp/mp, mSkillPref))
					{
//===============================================Protego==========================================================
					case 1:
						mcsp = DeductSpeed(mcsp, main.speed, 0.5);
						mmp = MpCalc(mmp, 40);
						BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
						BStatus.print();
						mProtect = 3;
						System.out.println("	 "+main.mname+" casted Protego!!");
						PrintLine(2);
						System.out.println(mname+" Absorbs damage for 3 attacks!");
						break msloop;
//===============================================Brachium Emendo==========================================================
					case 2:
						mcsp = DeductSpeed(mcsp, main.speed, 1);
						mmp = MpCalc(mmp, 80);
						mchp = mchp+mhp*(0.20+mDefensive*0.012);
						BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
						BStatus.print();
						System.out.println("	 "+main.mname+" casted Brachium Emendo!!");
						PrintLine(2);
						System.out.println(mname+" Healed "+(int)(mhp*(0.20+mDefensive*0.012))+"hp!!");
						break msloop;
//===============================================Mosmodre==========================================================
					case 3:
						mcsp = DeductSpeed(mcsp, main.speed, 1.3);
						mmp = MpCalc(mmp, 120);
						matkm = matkm+matkm*(0.04+mDefensive*0.001);
						matk = matk+matk*(0.04+mDefensive*0.001);
						mdef = mdef+mdef*(0.04+mDefensive*0.001);
						msp = msp+msp*(0.04+mDefensive*0.001);
						BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
						BStatus.print();
						System.out.println("	 "+main.mname+" casted Mosmodre!!");
						PrintLine(2);
						System.out.println(mname+"'s power, defence and speed has increased by "+4+(mDefensive*0.1)+"%");
						break msloop;
					default :
						break;
					}
					if (Accuracy(macc, pskDodge*2))
					{
					}
/***************************************************************************************Offensive******************************************************************************************/
					System.out.println("Stage C");
					switch (OffensiveAI(mOffensive, mmp, mchp/mhp, mmp/mfmp, Gamehp/hp, matkm, def, protect, pskDodge, mSkillPref))
					{
//===============================================1. Flagrate==========================================================
					case 1:
						mcsp = DeductSpeed(mcsp, main.speed, 1);
						mmp = MpCalc(mmp, 50);
						damage = Damage(matkm, matk*(1+mOffensive*0.1));
						damage = FinalDamage(damage, 0, 0);
						if (protect>0)
						{
							damage = DamageAbsorb(damage, skProtego);
							Gamehp = HpCalc(Gamehp, damage/2);	 Gamemp = MpCalc(Gamemp, damage/2);protect--;
							printSave = "Spell Absorbed!!";
						}
						else
							Gamehp = Gamehp-main.damage;
						BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
						BStatus.print();
						System.out.println("	 "+main.mname+" casted Flagrate!!");
						PrintLine(2);
						System.out.println("Damage : "+(int)damage+"!! "+printSave);
						break msloop;
//===============================================2. Scourge==========================================================
					case 2:
						String[] printsave = new String[2];
						mcsp = DeductSpeed(mcsp, main.speed, 0.7-mOffensive*0.01);
						mmp = MpCalc(mmp, 40);
						for (int i=0;i<2 ;i++ )
						{
							printSave = "";
							if (!Accuracy(macc, pskDodge*2))
								printsave[i] = "miss!";
							else
							{
								damage = Damage(matkm*0.5, matk*0.5);
								damage = FinalDamage(damage, 0, def);
								if (protect>0)
								{
									damage = DamageAbsorb(damage, skProtego);
									Gamehp = HpCalc(Gamehp, damage/2);	 Gamemp = MpCalc(Gamemp, damage/2);protect--;
									printSave = "Spell Absorbed!!";
								}
								else
									Gamehp = HpCalc(Gamehp, damage);
								printsave[i] = "damage: "+(int)damage+" "+printSave;
							}
						}
						BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
						BStatus.print();
						System.out.println("	 "+main.mname+" casted Scourge!!");
						PrintLine(1);
						System.out.println(printsave[0]+"\n"+printsave[1]);
						break msloop;
//===============================================3. Serpensortia==========================================================
					case 3:
						mcsp = DeductSpeed(mcsp, main.speed, 1.5-mOffensive*0.01);
						mmp = MpCalc(mmp, 80);
						damage = Damage(matkm*(1.5+mOffensive*0.01), matk*(1.5+mOffensive*0.01));
						damage = FinalDamage(damage, 0, def);
						if (!Accuracy(macc, pskDodge*2))
						{
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("	 "+main.mname+" casted Serpensortia!!");
							PrintLine(1);
							System.out.println(User+" dodged spell!!");
							System.out.println("");
						}
						else
						{
							mflee=mflee+2;
							if (protect>0)
							{
								damage = DamageAbsorb(damage, skProtego);
								Gamehp = HpCalc(Gamehp, damage/2);	 Gamemp = MpCalc(Gamemp, damage/2);protect--;
								printSave = "Damage Absorbed!!";
							}
							else
								Gamehp = Gamehp-main.damage;
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("	 "+main.mname+" casted Serpensortia!!");
							PrintLine(1);
							System.out.println(""+"Damage : "+(int)damage+"!! "+printSave);
							System.out.println("Player's Accuracy decreased by 2%!!");
						}
						break msloop;
					case 4:
						mcsp = DeductSpeed(mcsp, main.speed, 0.8);
						mmp = MpCalc(mmp, 90);
						damage = Damage(matkm*(1.2+mOffensive*0.08), matk*(1.2+mOffensive*0.08));
						damage = FinalDamage(damage, 0, def);
						if (!Accuracy(macc, pskDodge*2))
						{
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("	 "+main.mname+" casted Sectumsempra!!");
							PrintLine(1);
							System.out.println(User+" dodged spell!!");
							System.out.println("");
						}
						else
						{
							if (protect>0)
							{
								damage = DamageAbsorb(damage, skProtego);
								Gamehp = HpCalc(Gamehp, damage/2);	 Gamemp = MpCalc(Gamemp, damage/2);protect--;
								printSave = "Spell Absorbed!";
							}
							else
								Gamehp = Gamehp-main.damage;
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("	 "+main.mname+" casted Sectumsempra!!");
							PrintLine(2);
							System.out.println("Damage : "+(int)damage+"!! "+printSave);
						}
						break msloop;
					case 5:
						mcsp = DeductSpeed(mcsp, main.speed, 1);
						mmp = MpCalc(mmp, 30);
						damage = Damage(matkm*(0.7+mOffensive*0.1), matk*(0.7+mOffensive*0.1));
						damage = FinalDamage(damage, 0, def);
						if (!Accuracy(macc, pskDodge*2))
						{
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("	 "+main.mname+" casted Tergeo!!");
							PrintLine(1);
							System.out.println(User+" dodged spell!!");
							System.out.println("");
						}
						else
						{
							tergeo=4;
							if (protect>0)
							{
								damage = DamageAbsorb(damage, skProtego);
								Gamehp = HpCalc(Gamehp, damage/2);	 Gamemp = MpCalc(Gamemp, damage/2);protect--;
								printSave = "Spell Absorbed!!";
							}
							else
								Gamehp = Gamehp-main.damage;
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("	 "+main.mname+" casted Tergeo!!");
							PrintLine(1);
							System.out.println("Damage : "+(int)damage+"!! "+printSave);
							System.out.println("Delayed attack has been casted upon "+User+"!!");
						}
						break msloop;
					case 6:
						mcsp = DeductSpeed(mcsp, main.speed, 1);
						mmp = MpCalc(mmp, 100);
						damage = Damage(matkm*(0.8+mOffensive*0.2), matk*(0.8+mOffensive*0.2));
						damage = FinalDamage(damage, 0, def);
						if (!Accuracy(macc, pskDodge*2))
						{
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("	 "+main.mname+" casted Incarcerous!!");
							PrintLine(1);
							System.out.println(User+" dodged spell!!");
							System.out.println("");
						}
						else
						{
							ice = true;
							if (protect>0)
							{
								damage = DamageAbsorb(damage, skProtego);
								Gamehp = HpCalc(Gamehp, damage/2);	 Gamemp = MpCalc(Gamemp, damage/2);protect--;
								printSave = "Spell Absorbed!!";
							}
							else
								Gamehp = Gamehp-main.damage;
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("	 "+main.mname+" casted Incarcerous!!");
							PrintLine(1);
							System.out.println("Damage : "+(int)damage+"!! "+printSave);
							System.out.println("The cast binded "+User+"!!");
						}
						break msloop;
					default :
						break;
					}
					System.out.println("Stage D");
/***************************************************************************************Last AI Option******************************************************************************************/
//================================================Physical Attack============================================================
					mcsp = DeductSpeed(mcsp, main.speed, 1);
					if (Math.random()<mMelee/10.0 || (mMelee>0.1 && mmp<20))
					{
						damage = Damage(matkm, matk);
						damage = FinalDamage((damage*0.6)*(1+(mMelee/10)), 0, def);
						if (!Accuracy(macc, pskDodge*2))
						{	
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("	 "+main.mname+" attacked "+User+"!!");PrintLine(1);
							System.out.println(""+main.User+" dodged "+main.mname+"'s attack!!\n");
						}
						else
						{
							if (protect>0)
							{
								damage = DamageAbsorb(damage, skProtego);
								Gamehp = HpCalc(Gamehp, damage);	 protect--;
								printSave = "Protego failed to absorb attack!!";
							}
							else
								Gamehp = Gamehp-main.damage;
							BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
							BStatus.print();
							System.out.println("	 "+main.mname+" attacked "+User+"!!");
							PrintLine(2);
							System.out.println("Damage : "+(int)damage+"!! "+printSave);
						}
						break msloop;
					}
//================================================Diffendo============================================================
					if (mmp<20)
					{
						BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
						BStatus.print();
						PrintLine(2);
						System.out.println("	"+main.mname+" doesn't have enough mana to cast spell!");PrintLine(2);
						break msloop;
					}
					mmp = MpCalc(mmp, 20);
					damage = Damage(matkm, matk);
					damage = FinalDamage(damage, 0, def);
					if (!Accuracy(macc, pskDodge*2))
					{	
						BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
						BStatus.print();
						System.out.print("	 "+main.mname+" casted");
						if (Math.random()<0.6)
							System.out.println(" Diffendo!!");
						else
							System.out.println(" Stupefy!!");
						PrintLine(2);
						System.out.println("\n"+main.User+" dodged "+main.mname+"'s attack!!\n\n");
					}
					else
					{
						if (protect>0)
							{
								damage = DamageAbsorb(damage, skProtego);
								Gamehp = HpCalc(Gamehp, damage/2);	 Gamemp = MpCalc(Gamemp, damage/2);protect--;
								printSave = "Spell Absorbed!!";
							}
							else
								Gamehp = Gamehp-main.damage;
						BStatus = new StatusBar(main.User, main.Gamehp, main.hp, main.mname, mchp, main.mhp, main.Gamemp, main.mp, main.bardot);
						BStatus.print();
						System.out.print("	 "+main.mname+" casted");
						if (Math.random()<0.6)
							System.out.println(" Diffendo!!");
						else
							System.out.println(" Stupefy!!");
						PrintLine(2);
						System.out.println("Damage : "+(int)damage+"!! "+printSave);
					}
					break msloop;
				}
//================================================Fundamental Operation============================================================
				if (main.poison >= 1)
				{
					main.damage = (mchp/55)*(145+main.skpsionicstorm*5)/200+Math.sqrt(main.spatk)*(145+main.skpsionicstorm*5)/200;
					System.out.println("poison damage to "+main.mname+": "+(int)main.damage);
					mchp = mchp - main.damage;
					poison--;
				}
				else
					System.out.println("");
				tergeo--;
				if (tergeo==1)
					HpCalc(Gamehp, Gamehp*(0.05+mOffensive*0.003));
				if (chk(main.Gamehp))
					break BLoop;
				if (chk(mchp))
					break BLoop;
				if (MpRest(msp, mcsp, mRestNo, main.speed, lv))
				{
					mmp=mmp+(mfmp*(mRest/100));
					mRestNo++;
				}
				if (mmp>mfmp)
					mmp=mfmp;
				//System.out.print((int)mmp+"  "+DefensiveAI(mmp, mDefensive, mchp/mhp, mmp/mfmp, mProtect, Gamemp/mp, mSkillPref)+"  "+OffensiveAI(mOffensive, mmp, mchp/mhp, mmp/mfmp, Gamehp/hp, matkm, def, protect, pskDodge, mSkillPref)+" Enter any key to continue : ");
				System.out.println("Enter any key to continue..");
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
			PrintLine(1);
			if (Math.random()<(0.1+((double)main.lv/300)) && main.mname.equals("Training Ward")==false && monPortal!=2)
			{
				main.chrystal = main.chrystal+1;
				System.out.println("		***Congrats! You found a Crystal!***");
			}
			else
				PrintLine(1);
			PrintLine(1);
			if (main.monPortal!=2)
			{
				main.exp = main.exp+main.mexp;
				main.Exptot = main.Exptot+main.mexp;
				main.money = main.money+main.mmon;
				main.montot = main.montot+main.mmon;
				PrintLine(1);
				System.out.println("		Current EXP: "+main.exp+"/"+main.expmax);
				main.hpb = hpbar((double)main.exp/(double)main.expmax, main.bardot);
				System.out.println("		"+main.hpb);
				PrintLine(1);
				System.out.println("		Cash: "+main.money);
			}
			else
				PrintLine(5);
			PrintLine(4);
			System.out.println("Enter any key to continue");
			anykey = s.next();
			//kerriganb = true; kd = 5;// enable it to see ending credit
			if (main.kerriganb == true && main.kerriganbeat == false)
			{
							if (main.kd == 5)
							{
								PrintLine(main.clearscreen);
								main.kerriganbeat = true;
								TextAnimation tAni = new TextAnimation("			Finally!! You defeated Kerrigan!!");					// Game Victory
								tAni = new TextAnimation("			You made a outstanding revenge on her!");
								tAni = new TextAnimation("			You are the strongest warrier in this java world!");
								PrintLine(4);
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
								s.next();
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
			PrintLine(4);
			System.out.println("			You Lost! "+main.mname+" Beated You!!");
			PrintLine(1);
			if (mname.equals("Training Ward"))
				System.out.println(" You failed to get Training Experience Points! Try again later.");
			else
			{
				System.out.println("		You Lost $"+(int)(main.money/5)+" please try beating "+main.mname+" again later");
				//main.money = main.money-main.money/5;
				main.dietot++;
			}
			PrintLine(2);
			System.out.println("Enter any key to continue");
			anykey = s.next();
			main.monPortal = 5;
		}
	}
	//============================BattleCalculations===================
	public boolean Accuracy(double acc, double dodge)	//determine hit or miss
	{
		double hit = Math.random()*100;
		return hit<(acc-dodge);
	}
	public double DeductSpeed(double speed, double opSpeed, double percent)	//speed deduction calc
	{
		return speed-opSpeed*percent;
	}
	public double Damage(double atkmax, double atkmin)
	{
		return ((int)(Math.random()*atkmax%(atkmax-atkmin+0.001))+atkmin);
	}
	 public double FinalDamage(double damage, double pskCritical, double opDefence)//calculate critical damage(passive skill)
	{
		double hit = Math.random()*100;
		if (hit<(pskCritical*3.5))
			damage=damage*(1.25+pskCritical*0.015)-opDefence;
		else
			damage=damage-opDefence;
		if (opDefence >damage)
			damage = opDefence;
		return damage;
	}
	 public double DamageAbsorb(double damage, double Protego) //calculate damage absorbed by Protego
	 {
		damage = damage-damage*(0.1+Protego*0.02);
		return damage;
	 }
	 public double HpCalc(double hp, double damage)
	{
		return hp-damage;
	}
	public double MpCalc(double mp, double mana)
	{
		return mp-mana;
	}
	public boolean MpRest(double mainSpeed, double speed, double restNo, double msp, int lv)
	{
		if ((speed/mainSpeed)<(restNo*-1)+1 || (speed/mainSpeed)>restNo || lv<6)
			return true;
		return false;
	}
	//============================AICalculations===================
	public byte DefensiveAI(double mmp, double preference, double mhpRatio, double mmpRatio,double mProtect, double gamempRatio, int mSkillPref)		//defensive AI
	{
		double chooseProtego =0;
		double chooseHeal = 0;
		double chooseFianto = 0;
		double random = Math.random()*10;
		if (mProtect<1 && mmp>=40 && preference>=3)	//Fundamental Requirement for protego
		{
			chooseProtego = preference*(0.5+(0.2/mhpRatio+mmpRatio/2+(gamempRatio/8)))-random;				//protego
			chooseProtego *= PrefCalc(mSkillPref, 7);
		}
		if (mmp>=80 && mhpRatio<0.6)//Fundamental Requirement
		{
			chooseHeal = preference*(0.4+((0.5/mhpRatio)-mProtect/4))-random;			//heal
			chooseHeal *= PrefCalc(mSkillPref, 8);
		}
		if (mmp>=120 && mhpRatio>0.7 && preference>4)//Fundamental Requirement
		{
			chooseFianto = preference*(mhpRatio/1.5+mmpRatio/1.5-0.2)-random;// Mosmodre
			chooseFianto *= PrefCalc(mSkillPref, 9);
		}
		if (chooseProtego>0 && chooseProtego>chooseHeal && chooseProtego>chooseFianto)
			return 1;		//return Protego1
		else if ((mhpRatio<0.2 && preference>=5 && mmp>=90) || (Math.random()<preference/10 && chooseHeal>0 && chooseProtego<chooseHeal && chooseHeal>chooseFianto))
			return 2;				//return heal
		else if (chooseFianto>0 && chooseProtego<chooseFianto && chooseHeal<chooseFianto)
			return 3;				// return mosmodre
		return 0;				// return nothing
	}
	public byte OffensiveAI(double preference, double mmp, double mhpRatio, double mmpRatio, double gamehpRatio, double matkm, double def, double Protect, double pskDodge, int mSkillPref)		//offensive AI
	{
		double chooseFlagrate = 0;
		double chooseScourge = 0;
		double chooseTergeo = 0;
		double chooseSerpensortia = 0;
		double chooseSectumsempra = 0;
		double chooseIncarcerous = 0;
		double c3 = -10;
		double c4 = -10;
		double c5 = -10;
		double c6 = -10;
		double random = Math.random()*10;//Use skill at (AILevel*SkillReq)%
		if (mmp>=80 && preference>=4) 
		{
			chooseSerpensortia = preference*(0.3+(def/matkm)*0.6+Math.sqrt(0.7/gamehpRatio)-(int)(((Protect+1)/3)+0.5)/2)-random;
			c3 = preference*(0.3+(def/matkm)*0.6+Math.sqrt(0.7/gamehpRatio)-(int)(((Protect+1)/3)+0.5)/2)-Math.random()*6;	//serpensortia
			c3 *= PrefCalc(mSkillPref, 3);
		}
		if (mmp>=90 && preference>=5 && mmpRatio>0.4)
		{
			chooseSectumsempra = preference*(0.4+mmpRatio+gamehpRatio/1.6)-random;		//sectumsempra
			c4 =preference*(0.4+mmpRatio+gamehpRatio/1.6)-Math.random()*6;
			c4 *= PrefCalc(mSkillPref, 4);
		}
		if (mmp>=60 && preference>=3 && mmpRatio<0.4)
		{
			chooseTergeo = preference*(0.4+Math.sqrt(0.2/mmpRatio)+gamehpRatio*0.7)-random;		//tergeo
			c5 = preference*(0.4+Math.sqrt(0.2/mmpRatio)+gamehpRatio*0.7)-Math.random()*6;
			c5 *= PrefCalc(mSkillPref, 5);
		}
		if (mmp>=100 && preference>=6 && Protect<1.5)
		{
			chooseIncarcerous = preference*(0.3+Math.sqrt(0.3/mhpRatio)-(int)(((Protect+1)/3)+0.5)/1.2)-random;	//incarcerous
			c6 = preference*(0.3+Math.sqrt(0.3/mhpRatio)-(int)(((Protect+1)/3)+0.5)/1.2)-Math.random()*6;
			c6 *= PrefCalc(mSkillPref, 6);
		}
		if (Accuracy(30+preference*6, 0))
		{
			if (chooseSerpensortia>0 && c3>c4 && c3>c5 && c3>c6)
				return 3;
			else if (chooseSectumsempra>0 && c4>c3 && c4>c5 && c4>c6)
				return 4;
			else if (chooseTergeo>0 && c5>c4 && c5>c3 && c5>c6)
				return 5;
			else if (chooseIncarcerous>0 && c6>c4 && c5<c6 && c3<c6)
				return 6;
		}
		random = Math.random()*4;
		if (preference>Math.random()*7)
		{
			double dmgbenefit = 0.1;
			if ((matkm*0.5-def)*2.5>matkm)
				dmgbenefit=0.5;
			if (mmp>=50)
			{
				chooseFlagrate = preference*(0.7+(def/(matkm/5))+pskDodge*0.02)-random -Math.random()*6;
				chooseFlagrate *= PrefCalc(mSkillPref, 1);
			}
			if (mmp>=40)
			{
				chooseScourge = preference*(dmgbenefit+((matkm/5)/def))*(1+(int)(((Protect+1)/3)+0.7)/1.5)-random -Math.random()*6;
				chooseScourge *= PrefCalc(mSkillPref, 2);
			}
		}
		if (chooseScourge>0 && chooseScourge>chooseFlagrate)
			return 2;
		else if (chooseFlagrate>0 && chooseFlagrate>chooseScourge)
			return 1;
		return 0;			
	}
	public byte CurseAI(double preference, double mmp, double mhpRatio, double gamehpRatio, double Protect)
	{
		return 1;
	}
	public double PrefCalc(int mSkillPref, int skillNo)	//find if SkillPreference is declared
	{
		int i=1;
		while (mSkillPref>=1)
		{
			if (mSkillPref%10==skillNo)
				return 1.5;
			mSkillPref /= 10;
		}
		return 1;
	}
	 //===============================Printing Functions=======================
	 public void output(String a)
	{
		System.out.println(a);
	}
	 public void PrintLine(int ln)
	{
		 for (int i=0;i<ln ;i++ )
			 System.out.println("");
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
}

