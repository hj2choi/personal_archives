package pokemon;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
* <h1>Player.java</h1>
* This class is used to record status of the player. Including current location, caught pokemons and path visited
* 
* @author  CHOI, Hong Joon
*/
public class Player {
	// move types
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	// this are the urls of the images
	private static final String front = new File("icons/front.png").toURI().toString();
	private static final String back = new File("icons/back.png").toURI().toString();
	private static final String left = new File("icons/left.png").toURI().toString();
	private static final String right = new File("icons/right.png").toURI().toString();
	private ImageView icon;
	// these booleans correspond to the key pressed by the user
	boolean goUp, goDown, goRight, goLeft;
	

	private int x;
	private int y;
	private int[][] path;
	private int pathLength;
	private Pokemon[] pokemons;
	private int pokemonCount;
	private int pokeballs;
	private int gameScore;
	
	/**
	* Initialize Player at given point
	*
	* @author  CHOI, Hong Joon
	* @param x
	* @param y
	*/
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		path = new int[500][2];
		
		pathLength=-1;
		path[pathLength+1][0] = this.y;
		path[(pathLength++)+1][1] = this.x;
		pokemons = new Pokemon[20];
		pokemonCount = 0;
		pokeballs=0;
		gameScore = -10000;
		
		// GUI related\
		icon = Utilities.createImageView("front.png");
		//avatar.relocate(5*pokemon.ui.PokemonScreen.STEP_SIZE, 5*pokemon.ui.PokemonScreen.STEP_SIZE);
		goUp=goDown=goLeft=goRight=false;
	}
	
	/**
	* toString
	*
	* @author  CHOI, Hong Joon
	* @return String
	*/
	public String toString() {
		return "player ("+x+","+y+") "+pathLength+"d "+pokemonCount+"Ps "+pokeballs+"Bs ";
	}
	
	/**
	* x getter function
	*
	* @author  CHOI, Hong Joon
	* @return int
	*/
	public int getX() {
		return x;
	}
	
	/**
	* y getter function
	*
	* @author  CHOI, Hong Joon
	* @return int
	*/
	public int getY() {
		return y;
	}
	
	public ImageView getIcon() {
		return icon;
	}
	
	public void moveAtomic(int moveType, Map m) {
		// actual movement activates only once on signal rising edge
		switch(moveType) {
		case UP:
			icon.setImage(new Image(back));
			if (goUp) {
				return;
			}
			goUp = true;
			moveTo(y-1,x,m);
			break;
		case DOWN:
			icon.setImage(new Image(front));
			if (goDown) {
				return;
			}
			goDown = true;
			moveTo(y+1,x,m);
			break;
		case LEFT:
			icon.setImage(new Image(left));
			if (goLeft) {
				return;
			}
			goLeft = true;
			moveTo(y,x-1,m);
			break;
		case RIGHT:
			icon.setImage(new Image(right));
			if (goRight) {
				return;
			}
			goRight = true;
			moveTo(y,x+1,m);
			break;
		default:
			break;
		}
		
	}
	public void unlockMove(int moveType) {
		switch (moveType) {
		case UP:
			goUp = false;
			break;
		case DOWN:
			goDown = false;
			break;
		case LEFT:
			goLeft = false;
			break;
		case RIGHT:
			goRight = false;
			break;
		default:
			break;
		}
	}
	
	/**
	* Initialize Player at given point
	*
	* @author  CHOI, Hong Joon
	* @param x
	* @param y
	* @param map map is used to validate new position
	*/
	// MUST MOVE ONE STEP AT A TIME
	public void moveTo(int y, int x, Map m) {
		//System.out.println("moveTo "+x+", "+y);

		if (x<0 || x>=m.getCol() || y<0 || y>=m.getRow()) {
			//System.out.println("CANNOT MOVE: out of bounds!");
			return;
		}
		if (m.isWall(y,x)) {
			//System.out.println("CANNOT MOVE: wall!");
			return;
		}
		if (Math.abs(this.y-y)+Math.abs(this.x-x)>1) {
			System.out.println("FATAL ERROR: player.moveTo() INVALID MOVEMENT PATH");
			return;
		}
		this.y=y;
		this.x=x;
		path[(pathLength+1)][0] = this.y;
		path[(pathLength++)+1][1] = this.x;
	}
	
	/**
	* pokemon getter function
	*
	* @author  CHOI, Hong Joon
	* @param int index of the pokemon
	* @return Pokemon
	*/
	public Pokemon getPokemon(int i) {
		return pokemons[i];
	}
	
	/**
	* player interacts with station
	*
	* @author  CHOI, Hong Joon
	* @param Station: station to interact with
	* @return boolean whether the interaction is valid or not.
	*/
	public boolean interactStation(Station s) {
		pokeballs+=s.getPokeballs();
		return true;
	}
	
	/**
	* player interacts with pokemon
	*
	* @author  CHOI, Hong Joon
	* @param Pokemon: pokemon to interact with
	* @return boolean whether the interaction is valid or not.
	*/
	public boolean interactPokemon(Pokemon p) {
		if (p.getRequiredBalls() > this.pokeballs) {
			return false;
		}
		this.pokeballs -= p.getRequiredBalls();
		pokemons[pokemonCount++] = p;		// might require a deep copy
		return true;
	}
	
	/**
	* print all pokemons the player has
	*
	* @author  CHOI, Hong Joon
	*/
	public void printPokemons() {
		for (int i=0; i<pokemonCount; ++i) {
			System.out.println(pokemons[i]+" cp="+pokemons[i].getCombatPower());
		}
	}
	
	/**
	* print the path that player has taken so far
	*
	* @author  CHOI, Hong Joon
	* @return String
	*/
	public String pathString() {
		String result="";
		for (int i=0; i<=pathLength; ++i) {
			result+="<"+path[i][0]+","+path[i][1]+">";
			if (i<pathLength) {
				result+="->";
			}
			
		}
		return result;
	}
	
	/**
	* calculate the game score. Suggested to use this function after player reaches the destination point
	*
	* @author  CHOI, Hong Joon
	* @return int gameScore
	*/
	public int calculateGameScore() {
		gameScore=(pokeballs+(5*pokemonCount)+(10*calculateNS())+calculateMCP()-pathLength);
		return gameScore;
	}
	
	/**
	* pathLength getter function
	*
	* @author  CHOI, Hong Joon
	* @return int
	*/
	public int getPathLength() {
		return pathLength;
	}
	
	/**
	* pokeballs getter function
	*
	* @author  CHOI, Hong Joon
	* @return int
	*/
	public int calculateNB() {
		return pokeballs;
	}
	
	/**
	* pokemonCount getter function
	*
	* @author  CHOI, Hong Joon
	* @return int
	*/
	public int calculateNP() {
		return pokemonCount;
	}
	
	/**
	* calculate NS
	*
	* @author  CHOI, Hong Joon
	* @return int
	*/
	public int calculateNS() {
		String[] distinctTypes = new String[pokemonCount];
		int distinctNamesCount = 0;
		
		for (int i=0; i<pokemonCount; ++i) {
			boolean isTypeDistinct=true;
			for (int j=0; j<distinctNamesCount; ++j) {
				if (distinctTypes[j].equals(pokemons[i].getType())) {
					isTypeDistinct = false;
				}
			}
			if (isTypeDistinct) {
				distinctTypes[distinctNamesCount++] = pokemons[i].getType();
			}
		}
		
		return distinctNamesCount;
	}
	
	/**
	* calculate MCP
	*
	* @author  CHOI, Hong Joon
	* @return int
	*/
	public int calculateMCP() {
		if (pokemons[0]==null) {
			return 0;
		}
		int currMax=pokemons[0].getCombatPower();
		for (int i=0;i<pokemonCount;i++) {
			if (pokemons[i].getCombatPower()>currMax)
			{
				currMax=pokemons[i].getCombatPower();
			}
		}
		return currMax;
	}
	
}
