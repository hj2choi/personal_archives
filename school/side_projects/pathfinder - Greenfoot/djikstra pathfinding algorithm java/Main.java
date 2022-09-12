/**
pathfinding using djikstra's algorithm. 
Slow process, but guarantees the optimum solution. 
It works on 2x2 grid and with 4 direction movement only. Program can be altered to add more edges to each vertices
*/
import java.io.*;
class Main extends HongJoonLibrary
{
	static java.util.Scanner s = new java.util.Scanner(System.in);
	final static int worldWidth=30;
	final static int worldHeight=16;
	static int[][] walkCost = new int[worldWidth][worldHeight];	// stores cost value at each location
	static final int startXX=1;
	static final int startYY=1;
	static final int destinationXX=10;
	static final int destinationYY=6;
	public static void main(String[] args) 
	{
		for (int x=0; x<worldWidth; x++)	// initial value for walkCostMap = 10;
			for (int y=0; y<worldHeight; y++)
				walkCost[x][y]=10;
		boolean[][] allFalse = new boolean[worldWidth][worldHeight];

		
		sampleMaze();

		
		printMapWithShortestPath(allFalse);
		System.out.println("enter any word to execute the algorithm");
		s.next();
		printMapWithShortestPath(djikstraPathFinding(walkCost, startXX, startYY, destinationXX, destinationYY, worldWidth, worldHeight));
	}

	public static void printMapWithShortestPath(boolean[][] pathList)
	{
		for (int i=0; i<worldHeight; i++)
		{
			for (int j=0; j<worldWidth; j++)
			{
				if ((j==startXX && i==startYY)||(j==destinationXX&&i==destinationYY))
					System.out.print("ид  ");
				else if (pathList[j][i])
					System.out.print(">>  ");
				else if (walkCost[j][i]<=10)
					System.out.print(".   ");
				else if (walkCost[j][i]<=999)
					System.out.print(",   ");
				else if (walkCost[j][i]>=1000)
					System.out.print("##  ");
			}
			System.out.println("\n");
		}

	}

	
	public static void sampleMap1()
	{
		walkCost[1][6]=10000;
		walkCost[2][6]=10000;
		walkCost[3][6]=10000;
		walkCost[4][6]=10000;
		walkCost[5][6]=10000;
		walkCost[6][6]=10000;
		walkCost[6][5]=10000;
		walkCost[6][4]=10000;
		walkCost[6][3]=10000;
		walkCost[6][2]=10000;

		walkCost[4][9]=10000;
		walkCost[5][9]=10000;
		walkCost[6][9]=10000;
		walkCost[7][9]=10000;
		walkCost[8][9]=10000;
		walkCost[9][9]=10000;
		//
		//walkCost[10][9]=1000;
		//walkCost[11][9]=1000;
		//walkCost[12][9]=1000;


	}
	public static void sampleMap2()
	{
		walkCost[7][4]=10000;
		walkCost[8][4]=10000;
		walkCost[9][4]=10000;
		walkCost[10][4]=10000;
		walkCost[11][4]=10000;
		walkCost[12][4]=10000;
		walkCost[13][4]=10000;
		walkCost[7][11]=10000;
		walkCost[8][11]=10000;
		walkCost[9][11]=10000;
		walkCost[10][11]=10000;
		walkCost[11][11]=10000;
		walkCost[12][11]=10000;
		walkCost[13][11]=10000;

		walkCost[13][10]=10000;
		walkCost[13][9]=10000;
		walkCost[13][8]=10000;
		walkCost[13][7]=10000;
		walkCost[13][6]=10000;
		walkCost[13][5]=10000;
		//
		walkCost[14][11]=10000;
		walkCost[15][11]=10000;


	}

	public static void sampleMaze()
	{
		for (int i=0; i<10; i++)
			walkCost[4][i]=10000;
		for (int i=0; i<10; i++)
			walkCost[i][11]=10000;
		for (int i=0;i<8 ; i++)
			walkCost[6][i]=10000;
		for (int i=6;i<28 ; i++)
			walkCost[i][9]=10000;
		for (int i=7;i<15 ; i++)
			walkCost[i][7]=10000;
		for (int i=3; i<15; i++)
			walkCost[14][i]=10000;
		for (int i=3; i<20; i++)
			walkCost[i][14]=10000;


	}


/**
returns lists of shortest path taken by djikstra pathfinding algorithm by boolean[][] dataType
function Pre-requisites: generatePathList(), canOpenListAt(), djikstraMin(), min()
*/
	public static boolean[][] djikstraPathFinding(int[][] walkCost, int startX, int startY, int destinationX, int destinationY, int worldWidth, int worldHeight)	//djikstra's pathfinding algorithm
	{
		startTimer();
		boolean[][] openList = new boolean[worldWidth][worldHeight];			//stores wheter given coordinate is open
		boolean[][] closedList = new boolean[worldWidth][worldHeight];			//stores wheter given coordinate is closed
		int[][] djikstraCost = new int[worldWidth][worldHeight];		//stored djikstra cost from starting point
		for (int x=0; x<worldWidth; x++)	// initial value for djikstraCost = infinity(99999);
			for (int y=0; y<worldHeight; y++)
				djikstraCost[x][y]=99999;
		int currentX=startX;
		int currentY=startY;
		djikstraCost[currentX][currentY]=0;
		
		while (!(currentX==destinationX && currentY==destinationY))
		{
			//printDjikstraCost(djikstraCost);
			//printOpenList(currentX, currentY, openList, closedList);
			//delay(500);

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
			
			//goes through all openedLists and update djikstraCost
			for (int x=0; x<worldWidth; x++)
				for (int y=0; y<worldHeight; y++)
					if (openList[x][y] && (djikstraCost[currentX][currentY]+walkCost[x][y])<djikstraCost[x][y])//if current cost>updatedCost
						djikstraCost[x][y]=djikstraCost[currentX][currentY]+walkCost[x][y];
			
			//search through all openedLists and find coordinate with min djikstraCost
			int[] minCostIndex = djikstraMin(openList, djikstraCost, worldWidth, worldHeight);
			currentX=minCostIndex[0];
			currentY=minCostIndex[1];
			//update currentX and currentY. Then, repeat all steps
		}
		endTimer();
		//printDjikstraCost(djikstraCost);
		//printOpenList(currentX, currentY, openList, closedList);
		// creates pathList according to generated cost map and ends procedure.
		return generatePathList(djikstraCost, startX, startY, destinationX, destinationY, worldWidth, worldHeight);
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
		int currentMin=9999999;
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
		double temp=9999999;
		int position=0;
		for (int i=0;i<length;i++)
			if (list[i]<temp && list[i]>0)
			{
				temp=list[i];
				position=i;
			}
		return position;
	}





	public static void printDjikstraCost(int[][] djikstraCost)
	{
		for (int y=0; y<worldHeight; y++)
		{
			for (int x=0; x<worldWidth; x++)
			{
				if (walkCost[x][y]<1000){
					System.out.print(" ");
					if (djikstraCost[x][y]>=100000)
						System.out.print(allignMiddle("", 6));
					else
						System.out.print(allignMiddle(""+djikstraCost[x][y]%1000, 6));
				}
				else{
					System.out.print("#####");
					System.out.print(allignMiddle("", 2));
				}
				
			}
			System.out.println();
		}

	}
	public static void printOpenList(int currentX, int currentY, boolean[][] openList, boolean[][] closedList)
	{
		for (int y=0; y<worldHeight; y++)
		{
			for (int x=0; x<worldWidth; x++)
			{
				if (currentX==x && currentY==y)
					System.out.print("@");
				else
					System.out.print(" ");
				if (openList[x][y])
					System.out.print(allignMiddle(""+openList[x][y], 6));
				else if (closedList[x][y])
					System.out.print(allignMiddle(""+"--", 6));
				else
					System.out.print(allignMiddle(""+"  ", 6));
				
			}
			System.out.println();
		}

	}

}