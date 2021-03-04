package base;

import java.util.ArrayList;

public class Station extends Cell{

	private int pokeballs;
	
	public Station(int x, int y, int pathScore) {
		super(x, y, pathScore);
		// TODO Auto-generated constructor stub
	}
	
	public Station(Station other) {
		super(other);
		this.pokeballs = other.pokeballs;
	}
	
	public Station(int x, int y, int pathScore, int pokeballs) {
		super(x, y, pathScore);
		// TODO Auto-generated constructor stub
		this.pokeballs = pokeballs;
	}
	
	public int getPokeballs() {
		return pokeballs;
	}
	
	public int estimateValue(Player p, Map m) {
		int value=pokeballs;
		
		//TODO: calculate maxPokemonValue
//		int maxPokemonValue =0;
//		if (pokemons.size()>0) {
//			maxPokemonValue = ((Pokemon)pokemons.get(0)).estimateValue(p,m);
//		}
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
		
		//value-=Game.performPathFinding(m, p, y, x, Game.destY, Game.destX);
		value-=(Game.performPathFinding(true, m, p, p.getY(), p.getX(), y, x)+Game.performPathFinding(true, m, p, y, x, Game.destY, Game.destX));
		value+=Game.performPathFinding(true, m, p, p.getY(), p.getX(), Game.destY, Game.destX);
		return value;
	}


}
