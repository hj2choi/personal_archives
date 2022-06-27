class Chess extends HongJoonTemplate
{	
	final static int grid =8;		//	 chessgrid
	final static int arraySize = 3000000;
	final static boolean showRange =  true;
	final static String black = "#";
	final static String white = "<";
	final static String bRange = "|";
	final static String wRange = "!";
	public static void main(String args[])
	{
		boolean blackTurn = true;
		String[] board = new String[arraySize];
		String[] range = new String[arraySize];
//=============================Units=======================
/* 1:King	 2: Queen	3,4 : Bishop	5,6 : Knight		7,8 : Rook	9~16 : Pawn*/
		int[] blackUnit = new int[arraySize];
		int[] whiteUnit = new int[arraySize];
		int unitLocation = 0;
		int moveToLocation = 0;
		blackUnit[1] = 85;
		blackUnit[2] = 84;
		blackUnit[3] = 83;
		blackUnit[4] = 86;
		blackUnit[5] = 82;
		blackUnit[6] = 87;
		blackUnit[7] = 81;
		blackUnit[8] = 88;
		for (int i=1;i<9 ;i++ )
			blackUnit[i+8]=70+i;
		whiteUnit[1] = 15;
		whiteUnit[2] = 14;
		whiteUnit[3] = 13;
		whiteUnit[4] = 16;
		whiteUnit[5] = 12;
		whiteUnit[6] = 17;
		whiteUnit[7] = 11;
		whiteUnit[8] = 18;
		for (int i=1;i<9 ;i++ )
			whiteUnit[i+8]=20+i;

		mainLoop: while (true)
		{
			clearscreen();
			System.out.println("Black Turn = "+blackTurn);
			System.out.println("[[ChessBoard]]   "+wRange+white+"=White   "+bRange+black+"=Black   ShowRange:"+showRange+"	    Displayed loc	     Real loc X<>");printLine(2);
//=============================ChessBoard Data setting=======================
			for (int k=0;k<grid*grid+1 ;k++ )		//	initialize chessboard
				board[k]=". ";
			for (int j=0;j<grid*grid+1 ;j++ )		// initialize range on chessboard
				range[j]=" ";//| or - or +
			board[toLocation(blackUnit[1])] = black+"K";
			board[toLocation(blackUnit[2])] = black+"Q";
			board[toLocation(blackUnit[3])] = black+"B";
			board[toLocation(blackUnit[4])] = black+"B";
			board[toLocation(blackUnit[5])] = black+"N";
			board[toLocation(blackUnit[6])] = black+"N";
			board[toLocation(blackUnit[7])] = black+"R";
			board[toLocation(blackUnit[8])] = black+"R";
			for (int i=9;i<=16 ;i++ )
				board[toLocation(blackUnit[i])] = black+"P";
			board[toLocation(whiteUnit[1])] =  white+"K";
			board[toLocation(whiteUnit[2])] =  white+"Q";
			board[toLocation(whiteUnit[3])] =  white+"B";
			board[toLocation(whiteUnit[4])] =  white+"B";
			board[toLocation(whiteUnit[5])] =  white+"N";
			board[toLocation(whiteUnit[6])] =  white+"N";
			board[toLocation(whiteUnit[7])] =  white+"R";
			board[toLocation(whiteUnit[8])] =  white+"R";
			for (int i=9;i<=16 ;i++ )
				board[toLocation(whiteUnit[i])] =  white+"P";
//=============================Move Range Calculation=======================
			if (showRange==true)
			{
				for (int i=1;i<grid*grid+1 ;i++ )
				{
					if (whitePawnRange(i, whiteUnit, "white"))
						range[i]=wRange;
					if (whitePawnRange(i, blackUnit, "black"))
						range[i]=bRange;
					/*
					if (rookRange(i, whiteUnit))
						range[i]=wRange;
					if (rookRange(i, blackUnit))
						range[i]=bRange;
					*/
				}
			}
//=============================PrintChessBoard=======================
			for (int a=0;a<grid ;a++ )
			{
				for (int i=1;i<=grid ;i++ )
					System.out.print(board[a*grid+i]+range[a*grid+i]+"     ");
				System.out.print("<"+(a+1)+">	 ");
				System.out.println("		"+(a*grid+grid));
				printLine(2);
			}
//=============================UserInput=======================
			System.out.println("<1>     <2>     <3>     <4>     <5>     <6>     <7>     <8>");
			printLine(3);
			do
			{
				System.out.println("Input location of the unit that you wish to move. (ex: 84)");
				unitLocation = s.nextInt();
			}
			while ((unitLocation>88 ||unitLocation<10) || (unitLocation%10<1 || unitLocation%10>8));
			do
			{
				System.out.println("Input location of selected unit to move. Enter 0 to cancel.");
				moveToLocation = s.nextInt();
				if (moveToLocation==0)
					continue mainLoop;
			}
			while ((unitLocation>88 ||unitLocation<10) || (unitLocation%10<1 || unitLocation%10>8));
			if (searchFor(unitLocation, blackUnit)!=-1 && blackTurn)
			{
				blackUnit[searchFor(unitLocation, blackUnit)] = moveToLocation;
				blackTurn=false;
				for (int i=1;i<=16 ;i++ )
					if (moveToLocation==whiteUnit[i])
						whiteUnit[i]=100;
			}
			if (searchFor(unitLocation, whiteUnit)!=-1 && !blackTurn)
			{
				whiteUnit[searchFor(unitLocation, whiteUnit)] = moveToLocation;
				for (int i=1;i<=16 ;i++ )
					if (moveToLocation==blackUnit[i])
						blackUnit[i]=100;
				blackTurn=true;
			}
			
		}
	}
//=============================Methods=======================
	public static int toLocation(int code)
	{
		int location=1;
		location +=((code/10)-1)*8;		// vertical location
		location +=(code%10)-1;		// horizontal location
		return location;
	}

	public static boolean whitePawnRange(int arrayLocation, int[] whiteUnit, String bOrW)
	{
		int operation=-8;
		if (bOrW.equals("white"))
			operation=-8;
		else
			operation=8;
		boolean rightend = false;
		boolean leftend = false;
		for (int i=1;i<7 ;i++ )
			if (arrayLocation==1+i*8)
				leftend = true;
		for (int i=1;i<=7 ;i++ )
			if (arrayLocation==i*8)
				rightend = true;
		for (int i=9;i<=16 ;i++ )
		{
			if ((toLocation(whiteUnit[i])==arrayLocation+operation-1 && !leftend) || (toLocation(whiteUnit[i])==arrayLocation+operation+1 && !rightend))
				return true;
		}
		return false;
	}
	public static boolean rookRange(int arrayLocation, int[] whiteUnit)
	{
		int horizontalLocation =arrayLocation%8;
		for (int i=7;i<=8 ;i++ )
		{
			for (int j=1;j<8 ;j++ )
			{
				if (toLocation(whiteUnit[i])==arrayLocation+j*8|| toLocation(whiteUnit[i])==arrayLocation-j*8 || toLocation(whiteUnit[i])==arrayLocation+(8-horizontalLocation))
					return true;
			}
		}
		return false;
	}
}
