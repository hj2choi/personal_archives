package base;

public class Pokemon extends Cell{

	private String name;
	private String type;
	private int combatPower;
	private int requiredBalls;
	
	public Pokemon(int x, int y, int pathScore) {
		super(x, y, pathScore);
		// TODO Auto-generated constructor stub
	}
	
	public Pokemon(Pokemon other) {
		super(other);
		this.name = other.name;
		this.type = other.type;
		this.combatPower = other.combatPower;
		this.requiredBalls = other.requiredBalls;
	}
	
	public String toString() {
		return "["+requiredBalls+"] "+name+"("+type+") CP="+combatPower;
	}
	
	public Pokemon(int x, int y, int pathScore, String name, String type, int combatPower, int requiredBalls) {
		super(x, y, pathScore);
		// TODO Auto-generated constructor stub
		this.name = name;
		this.type = type;
		this.combatPower = combatPower;
		this.requiredBalls = requiredBalls;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public int getCombatPower() {
		return combatPower;
	}
	
	public int getRequiredBalls() {
		return requiredBalls;
	}
	
	public int estimateValue(Player p, Map m) {
		int value = 0;
		
		// return -100 if player cannot catch it for the time being
//		if (requiredBalls>p.calculateNB()) {
//			return -100;
//		}
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
		// value -= (player=>pokemon)+(pokemon=>dest)-(player=>dest);
		value-=(Game.performPathFinding(true, m, p, p.getY(), p.getX(), y, x)+Game.performPathFinding(true, m, p, y, x, Game.destY, Game.destX));
		value+=Game.performPathFinding(true, m, p, p.getY(), p.getX(), Game.destY, Game.destX);
		
		return value;
	}
}
