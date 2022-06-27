package base;

/**
* <h1>Player.java</h1>
* This class is used to record status of the player. Including current location, caught pokemons and path visited
* 
* @author  CHOI, Hong Joon
*/
public class Player {
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
		// week validation
		if (m.isWall(y,x)) {
			return;
		}
		if ((this.y-y)+(this.x-x)>1) {
			System.out.println("FATAL ERROR: player.moveTo() INVALID MOVEMENT PATH");
			return;
		}
		this.y=y;
		this.x=x;
		path[(pathLength+1)][0] = this.y;
		path[(pathLength++)+1][1] = this.x;
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
		int position=0;
		for (int i=0;i<pokemonCount;i++) {
			if (pokemons[i].getCombatPower()>currMax)
			{
				currMax=pokemons[i].getCombatPower();
				position=i;
			}
		}
		return currMax;
	}
	
}
