

/**
* <h1>Pokemon.java</h1>
* it is a special type of Cell, recording name, type, combatPower and requiredBalls to catch it
* 
* @author  CHOI, Hong Joon
*/
public class Pokemon extends Cell{

	private String name;
	private String type;
	private int combatPower;
	private int requiredBalls;
	
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
	public Pokemon(int x, int y, int pathScore, String name, String type, int combatPower, int requiredBalls) {
		super(x, y, pathScore);
		// TODO Auto-generated constructor stub
		this.name = name;
		this.type = type;
		this.combatPower = combatPower;
		this.requiredBalls = requiredBalls;
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
	* copy constructor
	*
	* @author  CHOI, Hong Joon
	* @param other
	*/
	public Pokemon(Pokemon other) {
		super(other);
		this.name = other.name;
		this.type = other.type;
		this.combatPower = other.combatPower;
		this.requiredBalls = other.requiredBalls;
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
	
	/**
	* returns estimate score for player if player visits the point. Player will skip any node with negative estimateValue
	*
	* @author  CHOI, Hong Joon
	* @param p player
	* @param m map
	* @return int
	*/
	public int estimateValue(Player p, Map m) {
		int value = 0;
		
		// add pokemonCount value 5
		value+=1*5;
		// take away requiredBalls
		value-=requiredBalls;
		// add value 10 if its Distinct
		boolean isTypeDistinct=true;
		for (int i=0; i<p.calculateNP(); ++i) {
			if (p.getPokemon(i).getType().equals(this.type)) {
				isTypeDistinct = false;
			}
		}
		if (isTypeDistinct) {
			value+=1*10;
		}
		// add MCP
		value+=Math.max(this.combatPower-p.calculateMCP(),0);
		
		// take away the opportunity cost of travel
		value-=(Game.performPathFinding(true, m, p.getY(), p.getX(), y, x)+Game.performPathFinding(true, m, y, x, Game.destY, Game.destX));
		value+=Game.performPathFinding(true, m, p.getY(), p.getX(), Game.destY, Game.destX);
		
		return value;
	}
}
