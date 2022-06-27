package base;

import java.util.ArrayList;

public class Map {
	private Cell[][] cells;
	private int row;
	private int col;
	
	public Map(int row, int col) {
		this.row = row;
		this.col = col;
		cells = new Cell[row][col];
	}
	
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
	
	public String toString() {
		String result="";
		for (int i=0; i<getRow(); ++i) {
			for (int j=0; j<getCol(); ++j) {
				if (isPokemon(get(i,j))) {
					result+=("P");
				}
				else if (isStation(get(i,j))) {
					result+=("S");
				}
				else if (get(i,j).getPathCost()==Cell.WALL_PATH_SCORE) {
					result+=("#");
				}
				else {
					result+=(" ");
				}
				//System.out.println(map.get(i,j).getPathCost());
			}
			result+=("\n");
		}
		return result;
	}
	
	public void initializeCell(int y, int x, boolean wallFlag) {
		cells[y][x] = new Cell(x, y, wallFlag?Cell.WALL_PATH_SCORE:1);
	}
	
	public void initializePokemon(int y, int x, boolean wallFlag, String name, String type, int combatPower, int requiredBalls) {
		cells[y][x] = new Pokemon(x, y, wallFlag?Cell.WALL_PATH_SCORE:1, name, type, combatPower, requiredBalls);
	}
	
	public void initializeStation(int y, int x, boolean wallFlag, int pokeballs) {
		cells[y][x] = new Station(x, y, wallFlag?Cell.WALL_PATH_SCORE:1, pokeballs);
	}
	
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	
	public Cell get(int y, int x) {
		return cells[y][x];
	}
	
	public boolean isWall(int y, int x) {
		return cells[y][x].getPathCost()==Cell.WALL_PATH_SCORE;
	}
	
	public boolean isPokemon(Cell node) {
		if (node instanceof Pokemon) {
			return true;
		}
		return false;
	}
	public boolean isStation(Cell node) {
		if (node instanceof Station) {
			return true;
		}
		return false;
	}
	public void handleInteractionEvent(Player p) {
		int x = p.getX();
		int y = p.getY();
		if (isPokemon(cells[y][x])) {
			if (p.interactPokemon((Pokemon)cells[y][x])) {
				initializeCell(y,x,false);
			}
		}
		if (isStation(cells[y][x])) {
			if (p.interactStation((Station)cells[y][x])) {
				initializeCell(y,x,false);
			}
		}
	}
	
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
