

/**
* <h1>Station.java</h1>
* it is a special type of Cell, recording the number of pokeballs it hold
* 
* @author  CHOI, Hong Joon
*/
public class Station extends Cell{

	private int pokeballs;
	
	/**
	* Initialize Station
	*
	* @author  CHOI, Hong Joon
	* @param x
	* @param y
	* @param pokeballs number of pokeballs at station
	*/
	public Station(int x, int y, int pathScore, int pokeballs) {
		super(x, y, pathScore);
		// TODO Auto-generated constructor stub
		this.pokeballs = pokeballs;
	}
	
	/**
	* Copy constructor
	*
	* @author  CHOI, Hong Joon
	* @param other
	*/
	public Station(Station other) {
		super(other);
		this.pokeballs = other.pokeballs;
	}
	
	/**
	* Pokeballs getter
	*
	* @author  CHOI, Hong Joon
	* @return int
	*/
	public int getPokeballs() {
		return pokeballs;
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
		int value=pokeballs;
		
		int pokemonsPositiveValue=0;
		int minRequiredPokeballs=1000000;
		int expectedTotalBalls=p.calculateNB();
		for (int i=0; i<m.getRow(); ++i) {
			for (int j=0; j<m.getCol(); ++j) {
				Cell c = m.get(i,j);
				int valueEst=0;
				if (c instanceof Pokemon) {
					valueEst=((Pokemon)c).estimateValue(p, m);
					if (valueEst>0) {
						pokemonsPositiveValue+=valueEst;
						if (minRequiredPokeballs>((Pokemon)c).getRequiredBalls()) {
							minRequiredPokeballs=((Pokemon)c).getRequiredBalls();
						}
					}
				}
				if (c instanceof Station) {
					expectedTotalBalls+=((Station)c).getPokeballs();
				}
			}
		}
		if (expectedTotalBalls<minRequiredPokeballs) {
			return -5;
		}
		value+=pokemonsPositiveValue;
		
		value-=(Game.performPathFinding(true, m, p.getY(), p.getX(), y, x)+Game.performPathFinding(true, m, y, x, Game.destY, Game.destX));
		value+=Game.performPathFinding(true, m, p.getY(), p.getX(), Game.destY, Game.destX);
		return value;
	}


}
