/*
Hong Joon Choi
Version 0.1
14.04.2012

Objective :
add save
add battlefield
add battle
add accessories
add options, etc..
*/
class MainMenu extends DataBase
{

	// compatible in 35x100 size terminal
	static Player player[] = new Player[4];
	static DataBase database=new DataBase();

	static int input = 0;
	public static void main(String[] args) 
	{
		
		player[1] = new Player();
		for (int i=1;i<10 ;i++ )
		{
			player[1].editUnit("--",'-',i,legParts[0],bodyParts[0],weaponParts[0]);
		}
		player[1].editUnit("Marine",'a', 1, legParts[1], bodyParts[1], weaponParts[1]);
		/*player[1].editUnit("Bazooka", 'b', 2, legParts[3], bodyParts[2], weaponParts[3]);
		player[1].editUnit("Bazooka2", 'b', 3, legParts[3], bodyParts[1], weaponParts[3]);
		player[1].editUnit("MachineGun", 'm',4, legParts[2], bodyParts[1], weaponParts[2]);
		player[1].editUnit("Laser", 'l',5, legParts[4], bodyParts[4], weaponParts[4]);*/





		
		
		//////////////////////********************Load Data********************//////////////////////
		System.out.println("RTSProject. (Last edit 14.04.2012) HongJoon");
		//company.read();
		System.out.println("Lodaing Successful.");
		//s.next();
		//////////////////////********************Main Menu********************//////////////////////
		MainMenuLoop : while (true)
		{ 
			clearscreen();
			System.out.println("			   ==========Main Menu=========\n\n");
			System.out.println("				[1] Player Statistics");
			System.out.println("				[2] Machine Shop");
			System.out.println("				[3] Enter Campaign\n");
			System.out.println("				[5] Save\n");
			System.out.println("				[7] Options");
			System.out.println("				[8] Manual\n");
			System.out.println("				[0] Exit Game");
			printLine(10);
			switch (safeInput())
			{
			case 1:
				information(1);
				break;
			case 2:
				machineShop(1);
				break;
			case 3:
				
				break;
			case 5:
				
				break;
			case 7:
				
				break;
			case 8:
			
			break;

			case 0:

				break MainMenuLoop;
			default :
				break;
			}
		}
		
		
		
		

		
		
	}

//========================================PlayerInformation==========================================
	public static void information(int playerNo)
	{
		infoLoop : while (true)
		{
			clearscreen();
			player[playerNo].printPlayerStats();
			printLine(5);
			
			System.out.println("[1] Inventory		[2] Hanger		[3] Lab		[4] Base Skill		[0] Escape.");
			input=safeInput();
			if (input==1)
			{
				clearscreen();
				System.out.println("\n		[Leg Parts]");
				for (int i=1;i<50 ;i++ )
					if (player[playerNo].getLegOwn()[i]>=1)
					{
						try{System.out.println(pad(i+".",2)+pad(legParts[i].getName(),15)+" x "+player[playerNo].getLegOwn()[i]);}
						catch (Exception e){break;}
					}
				System.out.println("\n		[Body Parts]");
				for (int i=1;i<50 ;i++ )
					if (player[playerNo].getBodyOwn()[i]>=1)
					{
						try{System.out.println(pad(i+".",2)+pad(bodyParts[i].getName(),15)+" x "+player[playerNo].getBodyOwn()[i]);}
						catch (Exception e){break;}
					}
				System.out.println("\n		[Weapon Parts]");
				for (int i=1;i<50 ;i++ )
					if (player[playerNo].getWeaponOwn()[i]>=1)
					{
						try{System.out.println(pad(i+".",2)+pad(weaponParts[i].getName(),15)+" x "+player[playerNo].getWeaponOwn()[i]);}
						catch (Exception e){break;}
					}
				s.next();
				/*
				System.out.println("\n\nEnter 5 to slelect item to discard. Enter other key to continue..");
				if (safeInput()==5)
				{
					System.out.println("Select Inventory section     [1]leg [2]body [3]weapon");
					System.out.println("(You can enter any other key to escape)");
					input=safeInput();
					if (1>input || input>3)
						break;
					System.out.println("Enter name of item to discard");
					if (input==1)
						searchFor(s.next(),legParts,50);
					
				}*/
			}
			else if (input==2)
			{
				clearscreen();
				System.out.println("					[Hanger]\n");
				player[playerNo].printUnitInfo();
				s.next();
			}
			else if (input==3)
			{
				int legSelect;
				int bodySelect;
				int weaponSelect;
				int hangerSelect;
				String name;
				char indicative;

				clearscreen();
				System.out.println("					[Lab]");
				System.out.println("You can construct your own units with parts that you have.");
				do
				{
					System.out.println("Decide which slot in your hanger you are going to put unit in.");
					System.out.println("(Keep in mind that you can escape at any stage by entering 0.)");
					hangerSelect=safeInput();
					if (hangerSelect==0)
						continue infoLoop;
				}
				while (hangerSelect>9 || hangerSelect<1);
				
				do
				{
					System.out.println("\nSelect leg parts (Enter 1 if you want Walker)");
					legSelect = safeInput();
					System.out.println("\n"+legParts[legSelect]);
					if (legSelect==0)
						continue infoLoop;
					if (player[playerNo].getLegOwn()[legSelect]<1)
						System.out.println("Not enough inventory!");
				}
				while (player[playerNo].getLegOwn()[legSelect]<1);
				do
				{
					System.out.println("\nSelect body parts (Enter 1 if you want Walker)");
					bodySelect = safeInput();
					System.out.println("\n"+bodyParts[bodySelect]);
					if (bodySelect==0)
						continue infoLoop;
					if (player[playerNo].getBodyOwn()[bodySelect]<1)
						System.out.println("Not enough inventory!");
				}
				while (player[playerNo].getBodyOwn()[bodySelect]<1);
				do
				{
					System.out.println("\nSelect weapon parts (Enter 1 if you want Walker)");
					weaponSelect = safeInput();
					System.out.println("\n"+weaponParts[weaponSelect]);
					if (weaponSelect==0)
						continue infoLoop;
					if (player[playerNo].getWeaponOwn()[weaponSelect]<1)
						System.out.println("Not enough inventory!\n");
				}
				while (player[playerNo].getWeaponOwn()[weaponSelect]<1);
				System.out.println("Enter any key to continuue");s.next();
				clearscreen();
				do
				{
					System.out.println("Enter name of your unit (less than 18 words)");
					name=s.next();
				}
				while (name.length()>18);
				while (true)
				{
					System.out.println("Enter indictive character of your unit in battle (1 word)");
					try
					{
						indicative=firstLetterOf(s.next());
						break;
					}
					catch (Exception e){}
				}
				
				System.out.println("\nHanger number : "+hangerSelect+"\n"+legParts[legSelect]+"\n"+bodyParts[bodySelect]+"\n"+weaponParts[weaponSelect]);
				System.out.println("Enter 1 to confirm your choice. Any other key will delete your decision.");
				if (safeInput()==1)
				{
					player[playerNo].editUnit(name, indicative, hangerSelect, legParts[legSelect], bodyParts[bodySelect], weaponParts[weaponSelect]);
					player[playerNo].useLeg(legSelect);
					player[playerNo].useBody(legSelect);
					player[playerNo].useWeapon(legSelect);
				}
				else
					continue infoLoop;
				System.out.println("Unit construction successful!! Enter anykey to return..");
				s.next();

			}
			else if (input==4)
			{
				clearscreen();
			}
			else
				break;
		}
		
	}
//========================================Shop==========================================
	public static void machineShop(int playerNo)
	{
		shopLoop: while (true)
		{
			clearscreen();
			System.out.println("Your Cash: $"+player[playerNo].getMoney());
			printLine(3);
			System.out.println("			{Machine Shop}\n\n");
			System.out.println("			[1] Leg Parts\n");
			System.out.println("			[2] Body Parts\n");
			System.out.println("			[3] Weapon Parts\n");
			System.out.println("			[0] Escape");
			System.out.println("");
			System.out.println("");
			System.out.println("\n\n");
			switch (safeInput())
			{
			case 1:// legParts
				clearscreen();
				System.out.println("				[Leg Parts Shop]\n");
				for (int i=1;i<=6 ;i++ )
					System.out.println(pad("[10"+i+"] $"+legParts[i].getCost(),10)+legParts[i]+"\n");
				input = safeInput()-100;

				if (0<input && input<=6)
				{
					if (player[playerNo].getMoney()<legParts[input].getCost())
						break;
					player[playerNo].buy(1, input, legParts[input].getCost());
					clearscreen();
					System.out.println("Purchase successful! Enter any key to return...");
					s.next();
				}
				else
					break;
				break;
			case 2:// bodyParts
				clearscreen();
				System.out.println("				[Body Parts Shop]\n");
				for (int i=1;i<=6 ;i++ )
					System.out.println(pad("[20"+i+"] $"+bodyParts[i].getCost(),10)+bodyParts[i]+"\n");
				input = safeInput()-200;

				if (0<input && input<=6)
				{
					if (player[playerNo].getMoney()<bodyParts[input].getCost())
						break;
					player[playerNo].buy(2, input, bodyParts[input].getCost());
					clearscreen();
					System.out.println("Purchase successful! Enter any key to return...");
					s.next();
				}
				else
					break;
				break;

			case 3:// weaponParts
				clearscreen();
				System.out.println("				[Weapon Parts Shop]\n");
				for (int i=1;i<=6 ;i++ )
					System.out.println(pad("[30"+i+"] $"+weaponParts[i].getCost(),10)+weaponParts[i]+"\n");
				input = safeInput()-300;

				if (0<input && input<=6)
				{
					if (player[playerNo].getMoney()<weaponParts[input].getCost())
						break;
					player[playerNo].buy(3, input, weaponParts[input].getCost());
					clearscreen();
					System.out.println("Purchase successful! Enter any key to return...");
					s.next();
				}
				else
					break;
				break;

			case 0 :
				break shopLoop;

			default :
				break;
			
			}
		}
	}

}
