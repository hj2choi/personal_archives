package base;

public class Player {
	private int x;
	private int y;
	private int[][] path;
	private int pathLength;
	private Pokemon[] pokemons;
	private int pokemonCount;
	private int pokeballs;
	private int gameScore;
	
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
	
	public String toString() {
		return "player ("+x+","+y+") "+pathLength+"d "+pokemonCount+"Ps "+pokeballs+"Bs ";
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public Pokemon getPokemon(int i) {
		return pokemons[i];
	}
	
	public boolean interactStation(Station s) {
		pokeballs+=s.getPokeballs();
		return true;
	}
	
	public boolean interactPokemon(Pokemon p) {
		if (p.getRequiredBalls() > this.pokeballs) {
			return false;
		}
		this.pokeballs -= p.getRequiredBalls();
		pokemons[pokemonCount++] = p;		// might require a deep copy
		return true;
	}
	
	public void printPokemons() {
		for (int i=0; i<pokemonCount; ++i) {
			System.out.println(pokemons[i]+" cp="+pokemons[i].getCombatPower());
		}
	}
	
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
	
	public int calculateGameScore() {
//		if (gameScore!=-10000) {
//			return gameScore;
//		}
		//System.out.println("Score: (-"+pathLength+") "+pokeballs+":"+(pokemonCount)+":"+(calculateNS())+":"+calculateMCP());
		//System.out.println("Score = "+(pokeballs+(5*pokemonCount)+(10*calculateNS())+calculateMCP()-pathLength));
		gameScore=(pokeballs+(5*pokemonCount)+(10*calculateNS())+calculateMCP()-pathLength);
		return gameScore;
	}
	
	public int getPathLength() {
		return pathLength;
	}
	
	public int calculateNB() {
		return pokeballs;
	}
	
	public int calculateNP() {
		return pokemonCount;
	}
	
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
