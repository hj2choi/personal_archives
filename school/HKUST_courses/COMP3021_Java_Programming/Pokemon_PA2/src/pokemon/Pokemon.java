package pokemon;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import pokemon.ui.PokemonList;
import pokemon.ui.PokemonMovementTask;

/**
* <h1>Pokemon.java</h1>
* it is a special type of Cell, recording name, type, combatPower and requiredBalls to catch it
* 
* @author  CHOI, Hong Joon
*/
public class Pokemon extends Cell{
	// move types
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	private String name;
	private String type;
	private int combatPower;
	private int requiredBalls;
	
	private boolean alive;
	private ImageView icon;
	private boolean waitingForUIUpdate;
	private PokemonMovementTask movementTask;
	
	/**
	* Initialize Pokemon
	*
	* @author  CHOI, Hong Joon
	* @param x
	* @param y
	* @param pathScore
	* @param name
	* @param type
	* @param combatPower
	* @param requiredBalls
	*/
	public Pokemon(Runnable task, int x, int y, int pathScore, String name, String type, int combatPower, int requiredBalls) {
		super(task, x, y, pathScore);
		// TODO Auto-generated constructor stub
		this.name = name;
		this.type = type;
		this.combatPower = combatPower;
		this.requiredBalls = requiredBalls;
		
		this.movementTask = (PokemonMovementTask)task;
		movementTask.setTarget(this);
		int iconId = PokemonList.getIdOfFromName(name);
		icon = Utilities.createImageView(iconId+".png");
		waitingForUIUpdate=false;
		
		alive = true;
	}
	
	public boolean isPokemonAlive() {
		return alive;
	}
	
	public void killTemporarily() {
		icon.setVisible(false);
		alive = false;
	}

	public boolean revive(int y, int x, ArrayList<Pokemon> pokemons, ArrayList<Station> stations, Map m) {
		
		if (moveTo(y,x,pokemons,stations, m)) {
			alive = true;
			icon.setVisible(true);
			return true;
		}
		return false;
	}
	
	public ImageView getIcon() {
		return icon;
	}
	
	/**
	* toString
	*
	* @author  CHOI, Hong Joon
	* @return String
	*/
	public String toString() {
		return "["+requiredBalls+"] "+name+"("+type+") CP="+combatPower;
	}
	
	/**
	* name getter
	*
	* @author  CHOI, Hong Joon
	* @return String
	*/
	public String getPokemonName() {
		return name;
	}
	
	/**
	* type getter
	*
	* @author  CHOI, Hong Joon
	* @return String
	*/
	public String getType() {
		return type;
	}
	
	/**
	* CP getter
	*
	* @author  CHOI, Hong Joon
	* @return int
	*/
	public int getCombatPower() {
		return combatPower;
	}
	
	/**
	* requiredBalls getter
	*
	* @author  CHOI, Hong Joon
	* @return int
	*/
	public int getRequiredBalls() {
		return requiredBalls;
	}
	
	public boolean isWaitingForUIUpdate() {
		return waitingForUIUpdate;
	}
	
	public void unsetUIUpdateFlag() {
		waitingForUIUpdate = false;
	}
	
	public void pauseMovementTask() {
		movementTask.pause();
	}
	public void resumeMovementTask() {
		movementTask.resume();
	}
	
	public void destroyMovementTask() {
		movementTask.destroyTask();
	}
	
	private boolean moveTo(int y, int x, ArrayList<Pokemon> pokemons, ArrayList<Station> stations, Map m) {
		//System.out.println("moveTo "+x+", "+y);

		if (x<0 || x>=m.getCol() || y<0 || y>=m.getRow()) {
			//System.out.println("POKEMON: CANNOT MOVE: out of bounds!");
			return false;
		}
		if (x == m.getDestX() && y == m.getDestY()) {
			return false;
		}
		if (m.isWall(y,x)) {
			//System.out.println("POKEMON: CANNOT MOVE: wall!");
			return false;
		}
		for (int i=0; i<pokemons.size(); ++i) {
			if (x == pokemons.get(i).getX() && y == pokemons.get(i).getY()) {
				//System.out.println("POKEMON: FATAL ERROR: OVERLAPS OTHER POKEMON");
				return false;
			}
		}
		for (int i=0; i<stations.size(); ++i) {
			if (stations.get(i).isStationAlive() && x == stations.get(i).getX() && y == stations.get(i).getY()) {
				//System.out.println("POKEMON: FATAL ERROR: OVERLAPS OTHER STATION");
				return false;
			}
		}
		this.y=y;
		this.x=x;
		waitingForUIUpdate = true;
		return true;
	}
	
	public boolean relocate(int moveType, ArrayList<Pokemon> pokemons, ArrayList<Station> stations, Map m) {
		switch (moveType) {
		case UP:
			return moveTo(y-1, x, pokemons, stations, m);
		case DOWN:
			return moveTo(y+1, x, pokemons, stations, m);
		case LEFT:
			return moveTo(y, x-1, pokemons, stations, m);
		case RIGHT:
			return moveTo(y, x+1, pokemons, stations, m);
		default:
			break;
		}
		return false;
	}
	
}
