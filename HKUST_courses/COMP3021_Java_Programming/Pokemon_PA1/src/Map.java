

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
	* Map copy constructor
	*
	* @author  CHOI, Hong Joon
	* @param other map to copy from
	*/
	public Map(Map other) {
		this.row = other.row;
		this.col = other.col;
		cells = new Cell[row][col];
		for (int i=0; i<other.row; ++i) {
			for (int j=0; j<other.col; ++j) {
				if (other.get(i,j) instanceof Station) {
					cells[i][j] = new Station((Station)other.cells[i][j]);
				} else if (other.get(i,j) instanceof Pokemon) {
					cells[i][j] = new Pokemon((Pokemon)other.cells[i][j]);
				}
				else {
					cells[i][j] = new Cell(other.cells[i][j]);
				}
				
			}
		}
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
	* Initialize cell at specified location
	*
	* @author  CHOI, Hong Joon
	* @param y y pos
	* @param x x pos
	* @param wallFlag initializes wall if set to true
	*/
	public void initializeCell(int y, int x, boolean wallFlag) {
		cells[y][x] = new Cell(x, y, wallFlag?Cell.WALL_PATH_SCORE:1);
	}
	
	/**
	* Initialize Pokemon at specified location
	*
	* @author  CHOI, Hong Joon
	* @param y y pos
	* @param x x pos
	* @param wallFlag set to false
	* @param name 
	* @param type 
	* @param combatPower 
	* @param requiredBalls 
	*/
	public void initializePokemon(int y, int x, boolean wallFlag, String name, String type, int combatPower, int requiredBalls) {
		cells[y][x] = new Pokemon(x, y, wallFlag?Cell.WALL_PATH_SCORE:1, name, type, combatPower, requiredBalls);
	}
	
	/**
	* Initialize Station at specified location
	*
	* @author  CHOI, Hong Joon
	* @param y y pos
	* @param x x pos
	* @param wallFlag set to false
	* @param pokeballs number of pokeballs
	*/
	public void initializeStation(int y, int x, boolean wallFlag, int pokeballs) {
		cells[y][x] = new Station(x, y, wallFlag?Cell.WALL_PATH_SCORE:1, pokeballs);
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

	/**
	* Handles interaction event with player. Interacts with pokemon or wall
	*
	* @author  CHOI, Hong Joon
	* @param p designated player
	*/
	public void handleInteractionEvent(Player p) {
		int x = p.getX();
		int y = p.getY();
		if (cells[y][x] instanceof Pokemon) {
			if (p.interactPokemon((Pokemon)cells[y][x])) {
				initializeCell(y,x,false);
			}
		}
		if (cells[y][x] instanceof Station) {
			if (p.interactStation((Station)cells[y][x])) {
				initializeCell(y,x,false);
			}
		}
	}
	
	/**
	* Generates cost map for dijkstra algorithm
	*
	* @author  CHOI, Hong Joon
	* @return int[][] returns path cost of each node
	*/
	public int[][] generatePathCostMap() {
		int[][] pathCostMap = new int[row][col];
		for (int i=0; i<row; ++i) {
			for (int j=0; j<col; ++j) {
				pathCostMap[i][j] = cells[i][j].getPathCost();
				//System.out.print(pathCostMap[i][j]+" ");
			}
			//System.out.println();
		}
		return pathCostMap;
	}
	
}
