public class Options extends wadv
{
	java.util.Scanner s = new java.util.Scanner(System.in);
	{
		wadv main = new wadv();
		int UserOrder2=0;
		while (true)
		{
			for (int k = 0;k<main.clearscreen ;k++ )
				System.out.println("");
			System.out.println("				------Options-----");
			System.out.println("");
			System.out.println("");
			System.out.print("[1] ClearScreen Intensity :	"); if (main.clearscreen==15){System.out.println("Low");} if (main.clearscreen==22){System.out.println("Med");}if (main.clearscreen==30){System.out.println("High");}
			System.out.println("");
			System.out.print("[2] Game  Speed :		"); if (main.loading == 1){System.out.println("Extreme");}if (main.loading == 500){System.out.println("Fast");}if (main.loading == 1000){System.out.println("Normal");}if (main.loading == 2000){System.out.println("Slow");}
			System.out.println("");
			System.out.print("[3] Hp,Mp Status Bar :		"); if (main.bardot == '*'){System.out.println("***__");}if (main.bardot == '0'){System.out.println("000__");}if (main.bardot == '#'){System.out.println("###__");}if (main.bardot == '@'){System.out.println("@@@__");}
			System.out.println("");
			System.out.print("[4] Text Speed :		"); if (main.textSpeed == 0){System.out.println("No Text Animation");}if (main.textSpeed == 8){System.out.println("Fast");}if (main.textSpeed == 13){System.out.println("Normal");}if (main.textSpeed == 18){System.out.println("Slow");}
			System.out.println("");
			System.out.print("[5] SaveSlot :			"); if (main.savefile==0){System.out.println("File 1");}if (main.savefile == 1){System.out.println("File 2");}if (main.savefile == 2){System.out.println("File 3");}
			System.out.println("");
			System.out.println("[0] Escape");
			for (int k = 0;k<10 ;k++ )
				System.out.println("");
			UserOrder2 = s.nextInt();
			if (UserOrder2 == 0)
				break;
			if (UserOrder2 == 1 && main.clearscreen == 15)
				main.clearscreen = 22;
			else if (UserOrder2 == 1 && main.clearscreen == 22)
				main.clearscreen = 30;
			else if (UserOrder2 == 1 && main.clearscreen == 30)
				main.clearscreen = 15;
			if (UserOrder2 == 2 && main.loading == 1)
				main.loading = 500;
			else if (UserOrder2 == 2 && main.loading == 500)
				main.loading = 1000;
			else if (UserOrder2 == 2 && main.loading == 1000)
				main.loading = 2000;
			else if (UserOrder2 == 2 && main.loading == 2000)
				main.loading = 1;
			if (UserOrder2 == 3 && main.bardot == '*')
				main.bardot = '0';
			else if (UserOrder2 == 3 && main.bardot == '0')
				main.bardot = '#';
			else if (UserOrder2 == 3 && main.bardot == '#')
				main.bardot = '@';
			else if (UserOrder2 == 3 && main.bardot == '@')
				main.bardot = '*';
			if (UserOrder2 == 4 && main.textSpeed == 0)
				main.textSpeed = 8;
			else if (UserOrder2 == 4 && main.textSpeed == 8)
				main.textSpeed = 13;
			else if (UserOrder2 == 4 && main.textSpeed == 13)
				main.textSpeed = 18;
			else if (UserOrder2 == 4 && main.textSpeed == 18)
				main.textSpeed = 0;
			if (UserOrder2 == 5 && main.savefile == 0)
				main.savefile = 1;
			else if (UserOrder2 == 5 && main.savefile == 1)
				main.savefile = 2;
			else if (UserOrder2 == 5 && main.savefile == 2)
				main.savefile = 0;
				
		}
	}
}

