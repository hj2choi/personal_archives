class Manual
{
	wadv main = new wadv();
	java.util.Scanner s = new java.util.Scanner(System.in);	
	private int ManualOrder;
	String keytocontinue = "";
	Manual(String a)
	{
		Manualloop : while (true)
		{
			for (int zzz = 0;zzz<main.clearscreen ;zzz++ )
					System.out.println("");
			System.out.println("An year ago, there was a incredibly bored warrier called "+a);
			System.out.println("To amuse himself, he challenged Kerrigan,");
			System.out.println("who was universe-widely well known for her psionic powers, strength and ability.");
			System.out.println("However, of course, he was literally 'raped' by her.");
			System.out.println(a+" became so angry, and he decided to train himself and revenge her!!");
			System.out.println("");
			System.out.println("In this game, you have to raise your warrier and beat Kerrigan at last.");
			System.out.println("you can decide monster to fight. If you lose, you return to Main screen.");
			System.out.println("Don't worry about losing a battle. You just lose quarter of your money.");
			System.out.println("you gain experience point and if it reach to a certain point, you will level up.");
			System.out.println("Then, you will be allowed to spend skill points and stat points to your warrier.");
			for (int zzz = 0;zzz<4 ;zzz++ )
					System.out.println("");
			System.out.println("For Further instructions...");
			System.out.println("[2] Stats								");
			System.out.println("[3] Skills	");
			System.out.println("[4] Resources");
			System.out.println("[5] Game Information");
			System.out.println("[0] Escape");
			System.out.println("");
			ManualOrder = s.nextInt();
			switch (ManualOrder)
			{
			default:
				break Manualloop;
			case 2:
				for (int zzz = 0;zzz<main.clearscreen ;zzz++ )
					System.out.println("");
				System.out.println("when your exp reaches max, you will levelup and will allowed to increase stats.");
				System.out.println("There are four stats in this game, Strength, Agility, Intelligence and Stamina.");
				System.out.println("Each stats have different characteristics. It is your choice how to raise your stats.");
				for (int zzz = 0;zzz<3 ;zzz++ )
					System.out.println("");
				System.out.println(" 1. Strength (STR)");
				System.out.println("Highly effects your damage. It also increase defence and healt by a bit.");
				System.out.println("");
				System.out.println(" 2. Agility (AGI)");
				System.out.println("Agility stabilizes range of damage. Also, it improves hitting accuracy and speed.");
				System.out.println("");
				System.out.println(" 3. Intelligence (INT)");
				System.out.println("Heavily affect mana point and special attack. Increases overall stats by bit.");
				System.out.println("");
				System.out.println(" 4. Stamina (STA)");
				System.out.println("Increases health and defence. Affect damage and mp by small amount.");
				for (int zzz = 0;zzz<4 ;zzz++ )
					System.out.println("");
				System.out.println("TIP : In a late game, balancing stats out gets more important.");
				System.out.println("");
				System.out.println("Type any key to return");
				keytocontinue = s.next();
				continue Manualloop;

			case 3:
				for (int zzz = 0;zzz<main.clearscreen ;zzz++ )
					System.out.println("");
				System.out.println("When you level up, you will also be allowed to spend one point to skill.");
				System.out.println("You can use skill after level 5.");
				System.out.println("Each Skills have different abilities and characteristics.");
				for (int zzz = 0;zzz<4 ;zzz++ )
					System.out.println("");
				System.out.println("	 1. Stab");
				System.out.println("Deals extra damage with 100% hit accuracy. It penetrates enemy defence.");
				System.out.println("	 2. DoubleStrike");
				System.out.println("Hits enemy twice with lower damage. Best against mob with low defence and flee");
				System.out.println("	 3. Heal");
				System.out.println("Heals your health at 60% of your health maximum.");
				System.out.println("	 4. Debuff");
				System.out.println("Decreases enemy's attack, defence and flee by certain amount.");
				System.out.println("	 5. Psionic Storm");
				System.out.println("Deals Critical amount of damage with high mana cost. It poisons enemy as well. ");
				System.out.println("	 6. Allin ");
				System.out.println("Costs half of your mana and health. Deals damage proportional to the cost.");
				for (int zzz = 0;zzz<3 ;zzz++ )
					System.out.println("");
				System.out.println("TIP : Each skills have their strength and weaknesses. You should select them carefully.");
				System.out.println("");
				System.out.println("Type any key to return");
				keytocontinue = s.next();
				continue Manualloop;

			case 4:


			case 5:
					for (int zzz = 0;zzz<main.clearscreen ;zzz++ )
						System.out.println("");
					System.out.println("Thank You "+a+" for playing this game!!!");
					System.out.println("");
					System.out.println("");
					System.out.println("			Game Information");
					System.out.println("Subject :		Warrier's Adventure");
					System.out.println("Version :		1.1.1");
					System.out.println("Description :		3D MMORPG (Demolished Disturbed Destroyed Million Men`s");
					System.out.println("			Rarely Played Game)");
					System.out.println("");
					System.out.println("Platform :		Command Prompt on Windows xp & vista & 7");
					System.out.println("System Requirements :	8bit processor or better, 150kb hard drive space,");
					System.out.println("			30mb ram or better, java enabled");
					System.out.println("");
					System.out.println("ver 1 Published :	29.10.2011");
					System.out.println("");
					System.out.println("			few rights reserved");
					System.out.println("	 Usage of any of the contents without permission is recommended");
					System.out.println("");
					System.out.println("enter any key to return");
					keytocontinue = s.next();
					continue Manualloop;
			}
		}
	}
}
