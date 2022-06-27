public class Shop
{
	java.util.Scanner s = new java.util.Scanner(System.in);
	{
		wadv main = new wadv();
		int UserOrder2=0;
		String anykey="";
		ShopLoop: while (true)
		{
			for (int y = 0;y<main.clearscreen ;y++ )
				System.out.println("");
			System.out.println("Your Cash: $"+main.money);
			System.out.println("Crystal : "+main.chrystal);
			System.out.println("");
			System.out.println("\n\n");
			System.out.println("			----------Gear Shop----------");
			System.out.println("");
			System.out.println("");
			System.out.println("			[1] Weapon Shop");
			System.out.println("");
			System.out.println("			[2] Armor Shop");
			System.out.println("");
			System.out.println("			[3] HeadGear Shop");
			if (main.kerriganbeat == true)
			{
				System.out.println("");
				System.out.println("			[4] Secret Gear Shop");
			}
			System.out.println("");
			System.out.println("			[5] Forge");
			System.out.println("");
			System.out.println("			[0] Escape");
			System.out.println("");
			System.out.println("");
			System.out.println("\n\n");
			UserOrder2 = s.nextInt();
			/***************************************************************Weapon Shop****************************************************************/
			if (UserOrder2 == 1)			//weapon
			{
				for (int vic = 0;vic<main.clearscreen ;vic++ )
					System.out.println("");
				System.out.println("				[Weapon Shop]");
				System.out.println("");
				System.out.println("Difficulty: How difficult is weapon for warrier to meanuver it(Affects Accuracy)");
				System.out.println("Type number corresponding to the weapon to purchase it. Else, type 0 to escape.");
				System.out.println("");
				System.out.println("100.Club $500:		attack 24,    sp.attack 18,    difficulty 13");
				System.out.println("");
				System.out.println("101.Silver Knife $2000:	attack 28,    sp.attack 21,    difficulty 25");
				System.out.println("");
				System.out.println("102.Wood Wand$2.5k:	attack 26,    sp.attack 26,    difficulty 16");
				System.out.println("");
				System.out.println("103.Spiked Axe $5500:	attack 31,    sp.attack 22,    difficulty 20");
				System.out.println("");
				System.out.println("104.Rust Blade $10k:	attack 34,    sp.attack 25,    difficulty 24");
				System.out.println("");
				System.out.println("105.BroadSword $18k:	attack 40,    sp.attack 30,    difficulty 30");
				System.out.println("");
				System.out.println("106.War Bow $25k:	attack 42,    sp.attack 33,    difficulty 20");System.out.println("");
				System.out.println("107.OblivionStaff$37k:	attack 41,    sp.attack 40,    difficulty 36");System.out.println("");
				System.out.println("108.MithrillHammer$43k:	attack 46,    sp.attack 37,    difficulty 30");System.out.println("");
				System.out.println("109.Excaliber $72k:	attack 52,    sp.attack 42,    difficulty 36");
				UserOrder2 = s.nextInt();
				System.out.println("");
				switch (UserOrder2)
				{
				case 0:
					break;
				case 100:
					if (main.money>=500)
					{	main.money = main.money - 500;	 main.wep = "club"; main.wep_stat[0] = 24;	main.wep_stat[1] = 18; main.difficulty = 13;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;}
					break;
				case 101:
					if (main.money>=2000)
					{ main.money = main.money - 2000;	 main.wep = "Silver Knife"; main.wep_stat[0] = 29;	main.wep_stat[1] = 21; main.difficulty = 25;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;}
					break;
				case 102:
					if (main.money>=2500)
					{	main.money = main.money - 2500;	 main.wep = "Wood Wand"; main.wep_stat[0] = 26;	main.wep_stat[1] = 26; main.difficulty = 16;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
				case 103:
					if (main.money>=5500)
					{	main.money = main.money - 5500;	 main.wep = "Spiked Axe"; main.wep_stat[0] = 31;	main.wep_stat[1] = 22; main.difficulty = 20;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
				case 104:
					if (main.money>=10000)
					{	main.money = main.money - 10000;	 main.wep = "Rust Blade"; main.wep_stat[0] = 34;	main.wep_stat[1] = 26; main.difficulty = 24;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
				case 105:
					if (main.money>=18000)
					{	main.money = main.money - 18000;	 main.wep = "BroadSword"; main.wep_stat[0] = 40;	main.wep_stat[1] = 30; main.difficulty = 30;
						System.out.println("Purchase Successful! Enter any number to escape");anykey = s.next(); continue ShopLoop;	}
					break;
				case 106:
					if (main.money>=25000)
					{	main.money = main.money - 25000;	 main.wep = "War Bow"; main.wep_stat[0] = 42;	main.wep_stat[1] = 33; main.difficulty = 20;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
				case 107:
					if (main.money>=37000)
					{	main.money = main.money - 37000;	 main.wep = "Obvilion Staff"; main.wep_stat[0] = 41;	main.wep_stat[1] = 40; main.difficulty = 36;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
				case 108:
					if (main.money>=45000)
					{	main.money = main.money - 45000;	 main.wep = "Mithrill Hammer"; main.wep_stat[0] = 46;	main.wep_stat[1] = 37; main.difficulty = 30;
						System.out.println("Purchase Successful! Enter any number to escape");anykey = s.next(); continue ShopLoop;	}
					break;
				case 109:
					if (main.money>=72000)
					{	main.money = main.money - 72000;	 main.wep = "Excaliber"; main.wep_stat[0] = 52;	main.wep_stat[1] = 42; main.difficulty = 36;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
				}
			}
			/***************************************************************Armor Shop****************************************************************/
			else if (UserOrder2 ==2)
			{
				for (int vic = 0;vic<main.clearscreen ;vic++ )
					System.out.println("");
				System.out.println("				[Armor Shop]");
				System.out.println("");
				System.out.println("Type number corresponding to the armor to purchase it. Else, type 0 to escape.");
				System.out.println("");
				System.out.println("201.Poor'sClothes $300:		def 2");System.out.println("");
				System.out.println("202.T-shirt $1100:		def 4");System.out.println("");
				System.out.println("203.Leather Armour $3500:	def 7,	hp 20");System.out.println("");
				System.out.println("204.Chain Mail $7500:		def 12,	Stamina 2,	 hp 20");System.out.println("");
				System.out.println("205.Metal Plates $17k:		def 24,	Stamina 1");System.out.println("");
				System.out.println("206.Platinum Mail $23k:		def 32");System.out.println("");
				System.out.println("207.Vanguard$42k:		def 40,	Stamina 1,	 hp 120");System.out.println("");
				System.out.println("208.Heart of Torrasque $80k:	def 64,	Stamina 3");System.out.println("");
				UserOrder2 = s.nextInt();
				System.out.println("");
				switch (UserOrder2)
				{
					case 0:
					break;
					case 201:
					if (main.chrystal>=20)
					{	main.money = main.money - 300;	 main.arm = "Poor's Clothes"; main.wep_stat[2] = 2;	main.wep_stat[6] = 0; main.wep_stat[7] = 0;
						System.out.println("Purchase Successful! Enter any number to escape");anykey = s.next(); continue ShopLoop;	}
					break;
					case 202:
					if (main.money>=1100)
					{	main.money = main.money - 1100;	 main.arm = "T-shirt"; main.wep_stat[2] = 4;	main.wep_stat[6] = 0; main.wep_stat[7] = 0;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next();continue ShopLoop;	}
					break;
					case 203:
					if (main.money>=3500)
					{	main.money = main.money - 3500;	main.arm = "Leather Armour"; main.wep_stat[2] = 7;	main.wep_stat[6] = 0; main.wep_stat[7] = 20;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 204:
					if (main.money>=7500)
					{	main.money = main.money - 7500;	 main.arm = "Chain Mail"; main.wep_stat[2] = 12;	main.wep_stat[6] = 2; main.wep_stat[7] = 20;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 205:
					if (main.money>=17000)
					{	main.money = main.money - 17000;	 main.arm = "Metal Plates"; main.wep_stat[2] = 24;	main.wep_stat[6] = 1; main.wep_stat[7] = 0;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 206:
					if (main.money>=23000)
					{	main.money = main.money - 23000;	 main.arm = "Platinum Mail"; main.wep_stat[2] = 32;	main.wep_stat[6] = 0; main.wep_stat[7] = 0;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 207:
					if (main.money>=42000)
					{	main.money = main.money - 42000;	 main.arm = "Vanguard"; main.wep_stat[2] =  40;	main.wep_stat[6] = 1; main.wep_stat[7] = 120;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 208:
					if (main.money>=80000)
					{	main.money = main.money - 80000;	 main.arm = "Heart of Torrasque"; main.wep_stat[2] =  64;	main.wep_stat[6] = 3; main.wep_stat[7] = 0;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
				}
			}
			/***************************************************************HeadGear Shop****************************************************************/
			else if (UserOrder2 ==3)
			{
				for (int vic = 0;vic<main.clearscreen ;vic++ )
					System.out.println("");
				System.out.println("				[HeadGear Shop]");
				System.out.println("");
				System.out.println("Type number corresponding to the Headgear to purchase it. Else, type 0 to escape.");
				System.out.println("");
				System.out.println("301.Band	$1500:		agi 1,	acc 4");System.out.println("");
				System.out.println("302.Sobi Mask $4500:		int 1,	mp 20,	acc 5");System.out.println("");
				System.out.println("303.Cap of Madness $8k:		str 2,	agi 1");System.out.println("");
				System.out.println("304.Helm of Legolas $16k:	agi 2,	int 1,	mp 15,	acc 15");System.out.println("");
				System.out.println("305.Helm of Dominator $21k:	str2,	int 2,	acc 8");System.out.println("");
				System.out.println("306. Obsidiun Helmet $ 40k:	str 2,	 agi 2, int 2");System.out.println("");
				System.out.println("307.Helmet of Jacoom $ 120k:	str 3,	 agi 3, int 3, acc 15");System.out.println("");
				UserOrder2 = s.nextInt();
				System.out.println("");
				switch (UserOrder2)
				{
					case 0:
					break;
					case 301:
					if (main.money>=1500)
					{	main.money = main.money - 1500;	 main.head = "Band"; main.wep_stat[3] = 0;	main.wep_stat[4] = 1; main.wep_stat[5] = 0; main.wep_stat[8] = 0; main.wep_stat[9] = 4;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 302:
					if (main.money>= 4500)
					{	main.money = main.money - 4500;	 main.head = "Sobi Mask"; main.wep_stat[3] = 0;	main.wep_stat[4] = 0; main.wep_stat[5] = 1; main.wep_stat[8] = 20; main.wep_stat[9] = 5;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 303:
					if (main.money>=8000)
					{	main.money = main.money - 8000;	 main.head = "Cap of Madness"; main.wep_stat[3] = 2;	main.wep_stat[4] = 1; main.wep_stat[5] = 0; main.wep_stat[8] = 0; main.wep_stat[9] = 0;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 304:
					if (main.money>=16000)
					{	main.money = main.money - 16000;	 main.head = "Helm of Legolas"; main.wep_stat[3] = 0;	main.wep_stat[4] = 2; main.wep_stat[5] = 1; main.wep_stat[8] = 15; main.wep_stat[9] = 15;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 305:
					if (main.money>=21000)
					{	main.money = main.money - 21000;	 main.head = "Helm of Dominator"; main.wep_stat[3] = 2;	main.wep_stat[4] = 0; main.wep_stat[5] = 2; main.wep_stat[8] = 0; main.wep_stat[9] = 8;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 306:
					if (main.money>=40000)
					{	main.money = main.money - 40000;	 main.head = "Obsidiun Helmet"; main.wep_stat[3] = 2;	main.wep_stat[4] = 2; main.wep_stat[5] = 2; main.wep_stat[8] = 0; main.wep_stat[9] = 0;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 307:
					if (main.money>=120000)
					{	main.money = main.money - 120000;	 main.head = "Helmet of Jacoom"; main.wep_stat[3] = 3;	main.wep_stat[4] = 3; main.wep_stat[5] = 3; main.wep_stat[8] = 0; main.wep_stat[9] = 15;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
				}
			}
			else if (UserOrder2 ==5)
			{
				for (int vic = 0;vic<main.clearscreen ;vic++ )
					System.out.println("");
				System.out.println("				[Gear Upgrading Forge]");
				System.out.println("");
				System.out.println("Type number corresponding to the Type of Upgrade to do. Else, type 0 to escape.");
				System.out.println("");
				System.out.println("401.Purchase 1 Crystal				$20000");System.out.println("");
				System.out.println("402.Purchase 5 Crystals				 $90000");System.out.println("");
				System.out.println("403.Upgrade Weapon's attack Cx3:	Weapon_attack+2");System.out.println("");
				System.out.println("404.Weapon Weight Lightening Cx1:	Weapon_difficulty-2");System.out.println("");
				System.out.println("405.Strengthen Armour Material Cx2:	Armor_defence+3");System.out.println("");
				System.out.println("406.Tease weapon up Cx2:		Weapon_sp.atk+1");System.out.println("");
				System.out.println("407.Inject Adrenalin Cx6:		HeadGear_str,agi,int+1");System.out.println("");
				UserOrder2 = s.nextInt();
				System.out.println("");
				/****************************************************Forge*************************************************************/
				switch (UserOrder2)
				{
					case 0:
					break;
					case 401:
					if (main.money>=22000)
					{	main.money = main.money - 22000;	 main.chrystal=main.chrystal+1;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 402:
					if (main.money>= 100000)
					{	main.money = main.money - 100000;	 main.chrystal=main.chrystal+5;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 403:
					if (main.chrystal>=3)
					{	main.chrystal = main.chrystal - 3;	 main.wep=main.wep+"*"; main.wep_stat[0] = main.wep_stat[0]+2;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 404:
					if (main.chrystal>=1)
					{	main.chrystal = main.chrystal - 1;	 main.wep=main.wep+"*"; main.difficulty = main.difficulty-2;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 405:
					if (main.chrystal>=2)
					{	main.chrystal = main.chrystal - 2;	 main.arm=main.arm+"*"; main.wep_stat[2] = main.wep_stat[2]+2;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 406:
					if (main.chrystal>=2)
					{	main.chrystal = main.chrystal - 2;	 main.wep=main.wep+"*"; main.wep_stat[1] = main.wep_stat[1]+1;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 407:
					if (main.chrystal>=6)
					{	main.chrystal = main.chrystal - 6;	 main.head = main.head+"*"; main.wep_stat[3] = main.wep_stat[3]+1;	main.wep_stat[4] = main.wep_stat[4]+1; main.wep_stat[5] = main.wep_stat[5]+1;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
				}
			}
			else if (UserOrder2 == 0)
			{
				break;
			}
			else if (UserOrder2 ==4 && main.kerriganbeat == true)
			{
				for (int vic = 0;vic<main.clearscreen ;vic++ )
					System.out.println("");
				System.out.println("				Secret Gear Shop");
				System.out.println("");
				System.out.println("Type number corresponding to the armor to purchase it. Else, type 0 to escape.");
				System.out.println("");
				System.out.println("501.Poor'sClothes $300:		def 2");System.out.println("");
				System.out.println("502.T-shirt $1100:		def 4");System.out.println("");
				System.out.println("503.Leather Armour $3500:		def 8,	hp 30");System.out.println("");
				System.out.println("504.Chain Mail $7500:		def 10,	Stamina 2,	 hp 40");System.out.println("");
				System.out.println("505.Metal Plates $17k:		def 32,	Stamina 5");System.out.println("");
				System.out.println("506.Platinum Mail $23k:		def 40,	hp 200");System.out.println("");
				System.out.println("507.Vanguard$42k:	def 60,	Stamina 5,	 hp 200");System.out.println("");
				System.out.println("508.Heart Of Torrasque $80k:		def 100");System.out.println("");
				UserOrder2 = s.nextInt();
				System.out.println("");
				switch (UserOrder2)
				{
					case 0:
					break;
					case 501:
					if (main.money>=150000 && main.chrystal>=10)
					{	main.money = main.money - 150000;	main.chrystal = main.chrystal-10; main.wep = "Excaliber"; main.wep_stat[0] = 52;	main.wep_stat[1] = 42; main.difficulty = 40;
						System.out.println("Purchase Successful! Enter any number to escape");anykey = s.next(); continue ShopLoop;	}
					break;
					case 502:
					if (main.money>=1100)
					{	main.money = main.money - 1100;	 main.arm = "T-shirt"; main.wep_stat[2] = 4;	main.wep_stat[6] = 0; main.wep_stat[7] = 0;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next();continue ShopLoop;	}
					break;
					case 503:
					if (main.money>=3500)
					{	main.money = main.money - 3500;	 main.arm = "Leather Armour"; main.wep_stat[2] = 8;	main.wep_stat[6] = 0; main.wep_stat[7] = 30;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 504:
					if (main.money>=7500)
					{	main.money = main.money - 7500;	 main.arm = "Chain Mail"; main.wep_stat[2] = 10;	main.wep_stat[6] = 2; main.wep_stat[7] = 40;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 505:
					if (main.money>=17000)
					{	main.money = main.money - 17000;	 main.arm = "Metal Plates"; main.wep_stat[2] = 32;	main.wep_stat[6] = 5; main.wep_stat[7] = 0;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 506:
					if (main.money>=23000)
					{	main.money = main.money - 23000;	 main.arm = "Platinum Mail"; main.wep_stat[2] = 40;	main.wep_stat[6] = 0; main.wep_stat[7] = 200;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 507:
					if (main.money>=42000)
					{	main.money = main.money - 42000;	main.arm = "Vanguard"; main.wep_stat[2] =  60;	main.wep_stat[6] = 5; main.wep_stat[7] = 200;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
					case 508:
					if (main.money>=80000)
					{	main.money = main.money - 80000;	 main.arm = "Heart Of Torrasque"; main.wep_stat[2] =  100;	main.wep_stat[6] = 0; main.wep_stat[7] = 0;
						System.out.println("Purchase Successful! Enter any number to escape"); anykey = s.next(); continue ShopLoop;	}
					break;
				}
			}
		}
	}
}

