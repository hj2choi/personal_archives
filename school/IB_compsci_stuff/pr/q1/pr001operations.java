public class pr001operations extends IBIO{
	public static void main (String [] args)	{
		
		//System.out.println("enter the number");
		
		int hp_player1=50;
		int hp_player2=100;
			
		int atk_player1=10;
		int atk_player2=20;

		while(hp_player1>0 && hp_player2>0)
		{
			System.out.println("Player 1 :  hp="+hp_player1+"           Player 2 :  hp="+hp_player2);
			System.out.println("Player 1 attacked player 2!");
			hp_player1=hp_player1-atk_player2;
			inputInt();
			
		}

		if (hp_player1<=0)
		{
			System.out.println("Player 1 :  hp="+hp_player1+"           Player 2 :  hp="+hp_player2);
			System.out.println("player 2 wins!!");
		}
		
		//System.out.println(x);


	}
}