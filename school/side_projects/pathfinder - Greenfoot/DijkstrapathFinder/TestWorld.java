import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;
import javax.swing.JInternalFrame;
import java.awt.event.*;
import java.util.*;
/**
 * TestWorld is a subclass of GWorld and it initializes game board and manages progress of the game.
 * Save and load functions are included as well.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * add save,load
 * add documentation
 * add sound
 */


/*
 * THIS VERSION IS A JUNGLE GAME WITH IMPLEMENTED AI
 * 
 * Priorities for AI:
 * 1. Try to take over the cave.
 * 2. Try to stick with opponent chess who it can kill. Prioritize higher rank pieces
 * 
 * 
 * AI procedures:
 * 1. search for the map and pick one chess to make a move, according to priorities
 * 2. 
 * 
 * 
 */
public class TestWorld extends GWorld
{
    public static final int MAP_WIDTH = 75;
    public static final int MAP_HEIGHT = 60;
    public static final int MAP_CELL_SIZE = 30 ;
    private Random randNumGenerator;
    
    char[][] mazeBuffer;
    boolean[][] mazeVisited;
    static {      //initializes the world
        GWorld.setWidth(MAP_WIDTH);
        GWorld.setHeight(MAP_HEIGHT);
        GWorld.setCellSize(MAP_CELL_SIZE);
    }
    
    
    public TestWorld() {
        prepare();
        randNumGenerator = new Random();
    }
    public void initialize() {                 
        initializeLandscape();
        initializeChess();
    }
    private void initializeChess(){
        //GWorld.addOneObject(new Chess(),0, 0);
    }
    private void initializeLandscape() {
        //GWorld.addOneObject( new Destination(), 10, 10); //Cave for GREEN
        
    }
    public void generateRandomWall(){
        for (int x=0; x<GWorld.width(); x++){
            for (int y=0; y<GWorld.height(); y++){
                if(Math.random()<0.5){
                     GWorld.addOneObject(new Wall(),x, y);
                }
                
                
            }
        }
    }
    public void clearMap(){
        removeObjects(getObjects(Wall.class));
        removeObjects(getObjects(Chess.class));
        removeObjects(getObjects(Path.class));
        removeObjects(getObjects(Chess.class));
        removeObjects(getObjects(Destination.class));
    }
    public void getWalls(){
        for (int i=3; i<6; i++)
            for (int j=3; j<14; j++)
                    GWorld.addOneObject(new Wall(), j, i);
    }
    public void clearPath(){
        GWorld.removeObjectsFromWorld(GWorld.getAllObjects("Path"));
    }
    private void prepare(){
        initialize();
    }
    
    public void createRandomMaze() {
        removeObjects(getObjects(Wall.class));
        removeObjects(getObjects(Chess.class));
        removeObjects(getObjects(Path.class));
        generateMazeMap();
    }
    private void generateMazeMap() {
        // TODO Auto-generated method stub
        
        // Build up the map...
        mazeBuffer = new char[MAP_WIDTH][MAP_HEIGHT];
        for (int i=0;i<MAP_WIDTH;i++)
            for (int j=0;j<MAP_HEIGHT;j++)
                mazeBuffer[i][j] = '#';
            
        // Generate the number from 1..23
        int xIndex  = MAP_WIDTH / 2 ;
        int yIndex =  MAP_HEIGHT/ 2 ;
        
        mazeBuffer[xIndex][yIndex] =  ' ';
       
        mazeVisited = new boolean[MAP_WIDTH][MAP_HEIGHT];
        for (int i=0;i<MAP_WIDTH;i++)
            for (int j=0;j<MAP_HEIGHT;j++)
                mazeVisited[i][j]=false;
    
        generateMazeMapRecurisve(xIndex, yIndex);
        
        for (int i=0;i<MAP_WIDTH;i++)
            for (int j=0;j<MAP_HEIGHT;j++)
                if ( mazeBuffer[i][j] == '#' ) {
                    addObject( new Wall(), i, j);
                }
            
     
    }
    private void generateMazeMapRecurisve(int r, int c) {
        
        mazeVisited[r][c] = true ;
        mazeBuffer[r][c] = ' ';
        
        int [] roffset = {-2,2,0,0};
        int [] coffset = {0,0,-2,2};
        int tmpIndexFrom, tmpIndexTo, tmp ;
        for (int i=0; i<10; i++) {
            tmpIndexFrom = randNumGenerator.nextInt(4);
            tmpIndexTo = randNumGenerator.nextInt(4);
            
            tmp = roffset[tmpIndexFrom];
            roffset[tmpIndexFrom] = roffset[tmpIndexTo];
            roffset[tmpIndexTo] = tmp ;
            
            tmp = coffset[tmpIndexFrom];
            coffset[tmpIndexFrom] = coffset[tmpIndexTo];
            coffset[tmpIndexTo] = tmp ;
            
        }
        for (int i = 0; i<4; i++) {
            int curRow = r+roffset[i];
            int curCol = c+coffset[i];
            if ( !onBoundary(curRow,curCol) &&
                    mazeVisited[curRow][curCol]==false ) {
                
                mazeBuffer[(r+curRow)/2][(c+curCol)/2] = ' ';
                generateMazeMapRecurisve(curRow, curCol);
                
            }
        }
    }
    private boolean onBoundary(int x, int y) {
        if ( x<=0 || y<=0 || x>=MAP_WIDTH || y >= MAP_HEIGHT)
            return true;
        return false;
    }
}
