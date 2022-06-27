package base;

public class Utilities {
//===================================Djikstra pathfinding algorithm===========================
/**
returns lists of shortest path taken by djikstra pathfinding algorithm by boolean[][] dataType
function Pre-requisites: generatePathList(), canOpenListAt(), djikstraMin(), min()

adapted for 2D vector space with 4 links for each edges
*/
	static boolean[][] pathBuffer;
	static long initialTimeInSpeedCheck=0;
	
	public static int dijkstraPathFinding(boolean evaluate, int[][] walkCost, int startX, int startY, int destinationX, int destinationY, int worldWidth, int worldHeight)	//djikstra's pathfinding algorithm
	{
		boolean[][] openList = new boolean[worldWidth][worldHeight];			//stores wheter given coordinate is open
		boolean[][] closedList = new boolean[worldWidth][worldHeight];			//stores wheter given coordinate is closed
		int[][] djikstraCost = new int[worldWidth][worldHeight];		//stored djikstra cost from starting point
		for (int x=0; x<worldWidth; x++)	// initial value for djikstraCost = infinity(99999);
			for (int y=0; y<worldHeight; y++)
				djikstraCost[x][y]=9999999;
		int currentX=startX;
		int currentY=startY;
		djikstraCost[currentX][currentY]=walkCost[currentX][currentY];
		
		
		int prevX=-5;
		int prevY=-5;
		while (!(currentX==destinationX && currentY==destinationY))
		{
			if (prevX==currentX && prevY==currentY) {
				//break;
				return Cell.WALL_PATH_SCORE;
			}
			//System.out.println("pathFinding: y="+currentY+", x="+currentX);
			
			openList[currentX][currentY]=false; // closes list at current location
			closedList[currentX][currentY]=true;	// closes list at current location

			// opens adjacent coordinates(two vertices are linked by an edge) to current coordinate
			if (canOpenListAt(closedList, currentX+1, currentY, worldWidth, worldHeight))
				openList[currentX+1][currentY]=true;
			if (canOpenListAt(closedList, currentX-1, currentY, worldWidth, worldHeight))
				openList[currentX-1][currentY]=true;
			if (canOpenListAt(closedList, currentX, currentY+1, worldWidth, worldHeight))
				openList[currentX][currentY+1]=true;
			if (canOpenListAt(closedList, currentX, currentY-1, worldWidth, worldHeight))
				openList[currentX][currentY-1]=true;
			//end
			
			//traverse through all openedLists and update djikstraCost
			for (int x=0; x<worldWidth; x++)
				for (int y=0; y<worldHeight; y++)
					if (openList[x][y] && (djikstraCost[currentX][currentY]+walkCost[x][y])<djikstraCost[x][y])//if current cost>updatedCost
						djikstraCost[x][y]=djikstraCost[currentX][currentY]+walkCost[x][y];
			//search through all openedLists and find coordinate with min djikstraCost
			int[] minCostIndex = djikstraMin(openList, djikstraCost, worldWidth, worldHeight);
			
			prevX=currentX;
			prevY=currentY;
			currentX=minCostIndex[0];
			currentY=minCostIndex[1];
			//update currentX and currentY. Then, repeat all steps
		}
		// create pathList according to generated cost matrix and terminate procedure.
		if (!evaluate) {
			pathBuffer = generatePathList(djikstraCost, startX, startY, destinationX, destinationY, worldWidth, worldHeight);
		}
		return djikstraCost[destinationX][destinationY]-1;
	}

	private static boolean[][] generatePathList(int[][] djikstraCost, int startX, int startY, int destinationX, int destinationY, int worldWidth, int worldHeight)
	{
		djikstraCost[startX][startY]=1;
		boolean[][] pathList = new boolean[worldWidth][worldHeight];
		boolean[][] allFalse = new boolean[worldWidth][worldHeight];
		pathList[destinationX][destinationY]=true;
		while (!(startX==destinationX && startY==destinationY))
		{
			int[] adjacentScores = new int[4];
			if (canOpenListAt(allFalse, destinationX+1, destinationY, worldWidth, worldHeight))
				adjacentScores[0]=djikstraCost[destinationX+1][destinationY];
			if (canOpenListAt(allFalse, destinationX-1, destinationY, worldWidth, worldHeight))
				adjacentScores[1]=djikstraCost[destinationX-1][destinationY];
			if (canOpenListAt(allFalse, destinationX, destinationY+1, worldWidth, worldHeight))
				adjacentScores[2]=djikstraCost[destinationX][destinationY+1];
			if (canOpenListAt(allFalse, destinationX, destinationY-1, worldWidth, worldHeight))
				adjacentScores[3]=djikstraCost[destinationX][destinationY-1];
			int index = min(adjacentScores, 4);
			switch (index)
			{
			case 0:
				pathList[destinationX+1][destinationY]=true;
				destinationX=destinationX+1;
				destinationY=destinationY;
				break;
			case 1:
				pathList[destinationX-1][destinationY]=true;
				destinationX=destinationX-1;
				destinationY=destinationY;
				break;
			case 2:
				pathList[destinationX][destinationY+1]=true;
				destinationX=destinationX;
				destinationY=destinationY+1;
				break;
			case 3:
				pathList[destinationX][destinationY-1]=true;
				destinationX=destinationX;
				destinationY=destinationY-1;
				break;
			}
		}
		pathList[startX][startY]=false;
		return pathList;
	}
	private static boolean canOpenListAt(boolean[][] closedList, int x, int y, int worldWidth, int worldHeight)	//exception handling
	{
		if (x<0 || x>=worldWidth)
			return false;
		if (y<0 || y>=worldHeight)
			return false;
		if (!closedList[x][y])
			return true;
		return false;

	}
	private static int[] djikstraMin(boolean[][] searchList, int[][] values, int lengthX, int lengthY)
	{
		int currentMin=values[0][0];
		int[] index=new int[2];
		for (int x=0; x<lengthX; x++)
			for (int y=0; y<lengthY; y++)
				if (searchList[x][y] && values[x][y]<currentMin)
				{
					index[0]=x;
					index[1]=y;
					currentMin=values[x][y];
				}
		return index;
	}
	private static int min(int[] list, int length)
	{
		double temp=list[0];
		int position=0;
		for (int i=0;i<length;i++)
			if (list[i]<temp && list[i]>0)
			{
				temp=list[i];
				position=i;
			}
		return position;
	}
	
	public static void startTimer()
	{
		initialTimeInSpeedCheck=System.currentTimeMillis();
	}

	public static long checkTimer()
	{
		//System.out.println("time = "+(System.currentTimeMillis()-initialTimeInSpeedCheck)+"ms");
		return (System.currentTimeMillis()-initialTimeInSpeedCheck);
	}
	
}
