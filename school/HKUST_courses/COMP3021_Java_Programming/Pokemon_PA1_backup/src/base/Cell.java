package base;

public class Cell {
	
	public final static int WALL_PATH_SCORE=50000;
	
	protected int x;
	protected int y;
	private int pathCost;

	public Cell(int x, int y, int pathCost) {
		this.x = x;
		this.y = y;
		this.pathCost = pathCost;
	}
	
	public Cell(Cell other) {
		this.x = other.x;
		this.y = other.y;
		this.pathCost = other.pathCost;
	}
	
	public int getPathCost() {
		return pathCost;
	}
	
	public String toString() {
		return "("+x+","+y+") "+pathCost;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
}
