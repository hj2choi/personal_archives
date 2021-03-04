package pokemon;

/**
* <h1>Map.java</h1>
* Records information of all cells of the map, including walls, pokemons and stations
* 
* @author  CHOI, Hong Joon
*/
public class Map {
	private Cell[][] cells;
	private int row;
	private int col;
	
	private int destY;
	private int destX;
	
	/**
	* Initialize map with given map width and height
	*
	* @author  CHOI, Hong Joon
	* @param row map height
	* @param col map width
	*/
	public Map(int row, int col) {
		this.row = row;
		this.col = col;
		cells = new Cell[row][col];
	}
	
	/**
	* convert map into string for console output
	*
	* @author  CHOI, Hong Joon
	* @return String stringified map
	*/
	public String toString() {
		String result="";
		for (int i=0; i<getRow(); ++i) {
			for (int j=0; j<getCol(); ++j) {
				if (get(i,j) instanceof Pokemon) {
					result+=("P");
				}
				else if (get(i,j) instanceof Station) {
					result+=("S");
				}
				else if (get(i,j).getPathCost()==Cell.WALL_PATH_SCORE) {
					result+=("#");
				}
				else {
					result+=(" ");
				}
			}
			result+=("\n");
		}
		return result;
	}
	
	/**
	* returns Height of the map
	*
	* @author  CHOI, Hong Joon
	* @return int
	*/
	public int getRow() {
		return row;
	}
	
	/**
	* returns Width of the map
	*
	* @author  CHOI, Hong Joon
	* @return int
	*/
	public int getCol() {
		return col;
	}
	
	public void setDestY(int y) {
		destY = y;
	}
	
	public void setDestX(int x) {
		destX = x;
	}
	
	public int getDestY() {
		return destY;
	}
	
	public int getDestX() {
		return destX;
	}
	
	/**
	* Initialize cell at specified location
	*
	* @author  CHOI, Hong Joon
	* @param y y pos
	* @param x x pos
	* @param wallFlag initializes wall if set to true
	*/
	public void initializeCell(int y, int x, boolean wallFlag) {
		cells[y][x] = new Cell(null, x, y, wallFlag?Cell.WALL_PATH_SCORE:1);
	}
	
	/**
	* returns Cell at spefieid pos
	*
	* @author  CHOI, Hong Joon
	* @param y
	* @param x
	* @return Cell
	*/
	public Cell get(int y, int x) {
		return cells[y][x];
	}
	
	/**
	* returns true if Cell at specified position is wall
	*
	* @author  CHOI, Hong Joon
	* @param y
	* @param x
	* @return boolean
	*/
	public boolean isWall(int y, int x) {
		return cells[y][x].getPathCost()==Cell.WALL_PATH_SCORE;
	}

	
	
}
