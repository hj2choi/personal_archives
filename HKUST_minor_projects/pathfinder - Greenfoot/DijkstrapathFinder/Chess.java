import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Chess here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Chess extends Actor
{
    public static Greenfoot greenfoot = new Greenfoot();

    public Chess(){
        setImage("picobot.gif" ); 
    }
    
    private void Up(){
        moveToLocation(getX(), getY()-1);
    }
    private void Down(){
        moveToLocation(getX(), getY()+1);
    }
    private void Left(){
        moveToLocation(getX()-1, getY());
    }
    private void Right(){
        moveToLocation(getX()+1, getY());
    }
    
    private void moveToLocation(int x, int y){
        if (GWorld.width()>x && 0<=x && GWorld.height()>y && 0<=y) // validate if x and y is inside the boundaries of the world.
            setLocation(x,y);
    }
    public void act(){
        int destinationX=0;
        int destinationY=0;
        for (int x=0; x<GWorld.width(); x++){
            for (int y=-0; y<GWorld.height(); y++){
                if (GWorld.getOneObjectAt(x,y,"Destination")!=null){
                    destinationX=x;
                    destinationY=y;
                }
            }
        }
        boolean[][] map = djikstraPathFinding(generateWalkCost(), getX(), getY(), destinationX, destinationY, GWorld.width(), GWorld.height());
        int x=getX();
        int y=getY();
        
        if ((GWorld.width()>x+1 && 0<=x+1 && GWorld.height()>y && 0<=y) && map[x+1][y])
            Right();
        if ((GWorld.width()>x-1 && 0<=x-1 && GWorld.height()>y && 0<=y) && map[x-1][y])
            Left();
        if ((GWorld.width()>x && 0<=x && GWorld.height()>y+1 && 0<=y+1) && map[x][y+1])
            Down();
        if ((GWorld.width()>x && 0<=x && GWorld.height()>y-1 && 0<=y-1) && map[x][y-1])
            Up();
        
        
        
    }
    /**
    returns lists of shortest path taken by djikstra pathfinding algorithm by boolean[][] dataType
    function Pre-requisites: generatePathList(), canOpenListAt(), djikstraMin(), min()
    */
    private int[][] generateWalkCost(){
        int[][] walkCost= new int[GWorld.width()][GWorld.height()];
        for (int x=0; x<GWorld.width(); x++)
            for (int y=0; y<GWorld.height(); y++){
                walkCost[x][y]=10;
        }
        /*for (int x=0; x<GWorld.width(); x++)
            for (int y=0; y<GWorld.height(); y++){
                if (GWorld.getOneObjectAt(x, y, "Trap")!=null)
                    walkCost[x][y]=20;
        }*/
        for (int x=0; x<GWorld.width(); x++)
            for (int y=0; y<GWorld.height(); y++){
                if (GWorld.getOneObjectAt(x, y, "Wall")!=null)
                    walkCost[x][y]=10000;
        }
        for (int x=0; x<GWorld.width(); x++){
            for (int y=0; y<GWorld.height(); y++){
                Chess ch= (Chess)GWorld.getOneObjectAt(x, y, "Chess");
                if (ch!=null)
                    walkCost[x][y]=10000;
                }
        }
        return walkCost;
    }

    public static boolean[][] djikstraPathFinding(int[][] walkCost, int startX, int startY, int destinationX, int destinationY, int worldWidth, int worldHeight)    //djikstra's pathfinding algorithm
    {

        boolean[][] openList = new boolean[worldWidth][worldHeight];            //stores wheter given coordinate is open
        boolean[][] closedList = new boolean[worldWidth][worldHeight];          //stores wheter given coordinate is closed
        int[][] djikstraCost = new int[worldWidth][worldHeight];        //stored djikstra cost from starting point
        for (int x=0; x<worldWidth; x++)    // initial value for djikstraCost = infinity(99999);
            for (int y=0; y<worldHeight; y++)
                djikstraCost[x][y]=99999;
        int currentX=startX;        //initial coordinate
        int currentY=startY;        //initial coordinate
        djikstraCost[currentX][currentY]=0;     // cost of starting coordinate
        
        while (!(currentX==destinationX && currentY==destinationY))
        {
            //printDjikstraCost(djikstraCost);
            //printOpenList(currentX, currentY, openList, closedList);
            //delay(500);

            openList[currentX][currentY]=false; // closes list at current location
            closedList[currentX][currentY]=true;    // closes list at current location

            // opens adjacent coordinates(links two vertices by an edge) to current coordinate
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
                        djikstraCost[x][y]=djikstraCost[currentX][currentY]+walkCost[x][y]; // updates dijkstraCost
            
            //search through all openedLists and find coordinate with min djikstraCost
            int[] minCostIndex = djikstraMin(openList, djikstraCost, worldWidth, worldHeight);
            currentX=minCostIndex[0];
            currentY=minCostIndex[1];
            //update currentX and currentY. Then, repeat all steps
        }
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
    private static boolean canOpenListAt(boolean[][] closedList, int x, int y, int worldWidth, int worldHeight) //exception handling
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
    
    public void showPath(){
        GWorld.removeObjectsFromWorld(GWorld.getAllObjects("Path"));
        int destinationX=0;
        int destinationY=0;
        for (int x=0; x<GWorld.width(); x++){
            for (int y=-0; y<GWorld.height(); y++){
                if (GWorld.getOneObjectAt(x,y,"Destination")!=null){
                    destinationX=x;
                    destinationY=y;
                }
            }
        }
        boolean[][] map = djikstraPathFinding(generateWalkCost(), getX(), getY(), destinationX, destinationY, GWorld.width(), GWorld.height());
        for (int x=0; x<GWorld.width(); x++){
            for (int y=0; y<GWorld.height(); y++){
                if (map[x][y])
                    GWorld.addOneObject(new Path(), x, y);
            }
        }
    }
}
