package base;


/**
* <h1>Cell.java</h1>
* Records information of current cell: x and y location, and whether it is wall
* 
* @author  CHOI, Hong Joon
*/
public class Cell {
	
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
	public Cell(int x, int y, int pathCost) {
		this.x = x;
		this.y = y;
		this.pathCost = pathCost;
	}
	
	/**
	* Copy constructor
	*
	* @author  CHOI, Hong Joon
	* @param other
	*/
	public Cell(Cell other) {
		this.x = other.x;
		this.y = other.y;
		this.pathCost = other.pathCost;
	}
	
	/**
	* pathCost getter
	*
	* @author  CHOI, Hong Joon
	* @return int pathCost
	*/
	public int getPathCost() {
		return pathCost;
	}
	
	/**
	* x pos getter
	*
	* @author  CHOI, Hong Joon
	* @return int
	*/
	public int getX(){
		return x;
	}
	
	/**
	* y pos getter
	*
	* @author  CHOI, Hong Joon
	* @return int
	*/
	public int getY(){
		return y;
	}
	
}
