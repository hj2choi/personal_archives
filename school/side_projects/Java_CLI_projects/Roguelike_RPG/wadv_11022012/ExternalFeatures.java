public class ExternalFeatures extends wadv
{
	{
		java.util.Scanner s = new java.util.Scanner(System.in);
		wadv main = new wadv();
		int UserOrder2=0;
		int UserOrder=0;
		String anykey="";
		
		SFloop: while(true) 
		{
			for (int asds = 0;asds<main.clearscreen ;asds++ )
			System.out.println("");
			System.out.println("			----------Special Features----------");
			for (int asd = 0;asd<2 ;asd++ )
				System.out.println("	 ");
			System.out.println("		-Study Purpose-");
			System.out.println("[1]	Grade Calculator/Estimator");
			System.out.println("[2]	Factor Finder");
			System.out.println("[3]	Binary-Decimal converter");
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
				break SFloop;

			case 1:
				for (int asds = 0;asds<main.clearscreen ;asds++ )
					System.out.println("");
				for (int vic = 0;vic<main.clearscreen ;vic++ )
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
				for (int asds = 0;asds<main.clearscreen ;asds++ )
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
				for (int asds = 0;asds<main.clearscreen ;asds++ )
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
				for (int asds = 0;asds<main.clearscreen ;asds++ )
					System.out.println("");
				System.out.println("[1] Binary to Decimal.");
				System.out.println("[2] Decimal to Binary.");
				UserOrder = s.nextInt();
				if (UserOrder==1)
				{
					System.out.println("Input Binary number.");
					String bin = s.next();
					char[] xx = bin.toCharArray();
					int ddd=0;
					int dec = 0;
					int position = bin.length()-1;
					
					for (ddd=0;ddd<position;ddd++);
					{
						if (xx[ddd] != '1' && xx[ddd] != '0')					// chk if numbers are binary
							{
								System.out.println("error");
								continue SFloop;
							}	
					}
					for (int k = 0; k<bin.length();k++)			// binary conversion algorithm
					{
						int t=1;
						for (int a = bin.length()-1; a>k; a--)
						{
							t=t*2;
						}
						dec = dec+((int)xx[k]-48)*t;
					}
					System.out.println("");System.out.println("");System.out.println("");System.out.println("Decimal Answer : "+dec);
					
				}

				if (UserOrder == 2)
				{
					System.out.println("Input a number.");
					int numbin = s.nextInt();
					String binans = "";
					while (numbin != 0)
					{
						if (numbin%2==1)
							binans = binans+"1";
						else
							binans=binans+"0";
						numbin = numbin/2;
					}
					binans = stringInverse(binans);
					System.out.println("");System.out.println("");System.out.println("");System.out.println("Binary Answer : "+binans);
				}
				System.out.println("Enter any key to continue");
					anykey=s.next();
				continue SFloop;

			case 4:
				for (int asds = 0;asds<main.clearscreen ;asds++ )
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
				System.out.println("Processor Speed at previous test"+main.Speed);
				main.Speed = (End-Start);
				System.out.println("");
				System.out.println("Current Processor Speed:"+main.Speed+"  (Less the value, Better the Processor)");
				if (main.Speed>=2400)
					System.out.println("Your computer's processor is Adequate");
				else if (main.Speed>=1700)
					System.out.println("Your computer's processor is Satisfying");
				else if (main.Speed>=1100)
					System.out.println("Your computer's processor is Good");
				else if (main.Speed>=700)
					System.out.println("Your computer's processor is Very Good");
				else if (main.Speed>=400)
					System.out.println("Your computer's processor is Perfect");
				else if (main.Speed<200)
					System.out.println("Your computer's processor is Outstanding");
				System.out.println("Changing battery plan, would drastically improve your test result. Try with maximum performance battery plan.");
				System.out.println("Type any key to return.");
				anykey = s.next();
				continue SFloop;

			case 5:
				for (int asds = 0;asds<main.clearscreen ;asds++ )
					System.out.println("");
				System.out.println("This timer doesn't stop");
				System.out.println("even when you are not using this program");
				System.out.println("or even when you turn off the computer, as long as you save this game once.");
				System.out.println("");
				System.out.println("");
				main.time = (System.currentTimeMillis()-main.TimerBegin);
				long time2 = main.time/1000;
				System.out.println("Time Elapsed : "+((time2/2628000)%365)+"M "+(int)((main.time/86400000)%30.4166667)+"D "+((main.time/3600000)%24)+"Hr "+((main.time/60000)%60)+"min "+((main.time/1000)%60)+"s "+(main.time%1000)+"ms");
				System.out.println("Time Elapsed : "+(main.time/1000)+" seconds");
				for (int asds = 0;asds<10 ;asds++ )
						System.out.println("");
				System.out.println("[1] Return");
				System.out.println("[0] Start New Timer");
				UserOrder2 = s.nextInt();
				if (UserOrder2 == 0)
				{
					System.out.println("");
					System.out.println("Time Elapsed of Last Timer: "+(System.currentTimeMillis()-main.TimerBegin)+"ms");
					main.TimerBegin = System.currentTimeMillis();
					System.out.println("Timer Initiated.");
					System.out.println("Type any key to return.");
					anykey = s.next();
					continue SFloop;
				}
				continue SFloop;
				

			case 6:
				

			case 7:
				for (int asds = 0;asds<main.clearscreen ;asds++ )
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
						continue SFloop;
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
				for (int asds = 0;asds<main.clearscreen ;asds++ )
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
			}
		}
					
	}
	public static String stringInverse(String ss)
	{
		String result="";
		char[] ssC = ss.toCharArray();
		for (int i=ss.length()-1;i>=0 ;i-- )
		{
			result = result+ssC[i];
		}
		return result;
	}
}

