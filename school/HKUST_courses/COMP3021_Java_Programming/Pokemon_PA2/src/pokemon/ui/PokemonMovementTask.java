package pokemon.ui;

import java.util.ArrayList;
import java.util.Random;

import pokemon.Map;
import pokemon.Pokemon;
import pokemon.Station;

public class PokemonMovementTask implements Runnable{

	private static final int movementDelayLow = 500;
	private static final int movementDelayHigh = 3000;
	private static final int respawnDelayLow = 3000;
	private static final int respawnDelayHigh = 5000;
	private Pokemon target;
	private ArrayList<Pokemon> referencePokemons;
	private ArrayList<Station> referenceStations;
	private Map referenceMap;
	private boolean alive;
	private boolean paused;
	
	public PokemonMovementTask(ArrayList<Pokemon> pokemons, ArrayList<Station> stations, Map m) {
		referencePokemons = pokemons;
		referenceMap = m;
		referenceStations = stations;
		alive = true;
		paused = false;
	}
	
	public void setTarget(Pokemon tgt) {
		target = tgt;
	}
	
	public void destroyTask() {
		alive = false;
	}
	
	public void pause() {
		paused = true;
	}
	
	public void resume() {
		paused = false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("pokemonMovementTask.run() init()");
		Random r = new Random();
		
		boolean respawnSequenceStarted=false;
		
		int movementDelay = r.nextInt(movementDelayHigh-movementDelayLow) + movementDelayLow;
		int respawnDelay=0;
		while (true) {
			//STATE 0: paused state
			if (paused) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				continue;
			}
						
			//STATE 1: terminate task if alive flag is set to false
			if (!alive) {
				break;
			}
			
			//STATE 2: countdown for movement
			if (target.isPokemonAlive() && movementDelay>0) {
				try {
					--movementDelay;
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (target.isPokemonAlive()) {	// move one step and reset counter, depending on wheter movement is successful or not.
				synchronized(PokemonMovementTask.class) {
					synchronized(StationRespawnTask.class) {
						if (target.relocate(r.nextInt(4), referencePokemons, referenceStations, referenceMap)) {
							movementDelay = r.nextInt(movementDelayHigh-movementDelayLow) + movementDelayLow;
						} else {
							movementDelay = movementDelayLow;
						}
					}
				}
			}
			
			// STATE 3: target is down, start respawn counter
			if (!target.isPokemonAlive() && !respawnSequenceStarted) {
				System.out.println("pokemonMovementTask.run() wait for Pokemon respawn");
				respawnSequenceStarted = true;
				respawnDelay = r.nextInt(respawnDelayHigh-respawnDelayLow) + respawnDelayLow;
			}
			
			// STATE 4: await for respawn counter
			if (respawnSequenceStarted) {
				if (respawnDelay>0) {
					try {
						--respawnDelay;
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else {	// respawn countdown finished
					
					respawnSequenceStarted = false;
					// revive target at random point!!
					while (true) {
						int newX = r.nextInt(referenceMap.getCol());
						int newY = r.nextInt(referenceMap.getRow());
						synchronized(PokemonMovementTask.class) {
							synchronized(StationRespawnTask.class) {
								if (target.revive(newY, newX, referencePokemons, referenceStations, referenceMap)) {
									System.out.println("pokemonMovementTask.run(): Respawned at ("+newX+", "+newY+")");
									break;
								}
							}
						}
					}
				}
			}
			
		}
		System.out.println(target.getPokemonName() + ": pokemonMovementTask.run() TERMINATED");
		
	}

}
