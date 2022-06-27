package pokemon;


/**
* <h1>Cell.java</h1>
* Records information of current cell: x and y location, and whether it is wall
* 
* @author  CHOI, Hong Joon
*/
public class Cell extends java.lang.Thread{
	
	public final static int WALL_PATH_SCORE=50000;
	
	protected int x;
	protected int y;
	private int pathCost;
	
	/**
	* Initialize Cell
	*
	* @author  CHOI, Hong Joon
	* @param x
	* @param y
	* @param pathCost set fo WALL_PATH_SCORE in order to initialize as wall
	*/
	public Cell(Runnable task, int x, int y, int pathCost) {
		super(task);
		this.x = x;
		this.y = y;
		this.pathCost = pathCost;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getPathCost() {
		return pathCost;
	}
	
	
	
}
