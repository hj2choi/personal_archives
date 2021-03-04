class Main extends HongJoonLibrary
{
	static int mapSizeY = 55;
	static int mapSizeX = 50;

	static boolean[][] map = new boolean[mapSizeY][mapSizeX];

	public static void print(boolean[][] map)
	{
		clearscreen();
		for (int i=0; i<mapSizeY; i++)
		{
			for (int j=0; j<mapSizeX; j++)
				System.out.print((map[i][j]?("x"):(" "))+" ");
			System.out.println();
		}
	}

	public static void tick()
	{
		boolean[][] newMap = new boolean[mapSizeY][mapSizeX];
		for (int i=0; i<mapSizeY; i++)
			for (int j=0; j<mapSizeX; j++)
				newMap[i][j]=map[i][j];

		//rewrite map
		for (int i=0; i<mapSizeY; i++)
		{
			for (int j=0; j<mapSizeX; j++)
			{
				int count=0;
				for (int k=0; k<3; k++)
					for (int l=0; l<3; l++)
					{
						try
						{
							if (map[i+k-1][j+l-1])
								count++;
						}
						catch (Exception e){}
						
					}
				if (!map[i][j] && count==3)
					newMap[i][j]=true;
				else if (map[i][j] && !(count==2+1 || count==3+1))
					newMap[i][j]=false;
			}
			
		}
		
		for (int i=0; i<mapSizeY; i++)
			for (int j=0; j<mapSizeX; j++)
				map[i][j]=newMap[i][j];



	}














































	public static void main(String[] args)
	{
		map[40][20]=true;
		map[41][20]=true;
		map[39][20]=true;
		map[40][19]=true;
		map[41][21]=true;
		map[41][22]=true;
		while (true)
		{
			print(map);
			tick();
			delay(500);
		}
		
	}
}
