package pokemon.ui;

import java.util.ArrayList;
import java.util.Random;

import pokemon.Map;
import pokemon.Pokemon;
import pokemon.Station;

public class StationRespawnTask implements Runnable{
	
	private static final int respawnDelayLow = 5000;
	private static final int respawnDelayHigh = 10000;
	private Station target;
	private ArrayList<Pokemon> referencePokemons;
	private ArrayList<Station> referenceStations;
	private Map referenceMap;
	private boolean paused;
	
	public StationRespawnTask(ArrayList<Pokemon> pokemons, ArrayList<Station> stations, Map m) {
		referencePokemons = pokemons;
		referenceMap = m;
		referenceStations = stations;
	}
	
	public void setTarget(Station tgt) {
		target = tgt;
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
		System.out.println("stationRespawnTask.run() init()");
		Random r = new Random();
		
		boolean respawnSequenceStarted = false;
		int delay=0;
		while (true) {
			// paused state
			if (paused) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				continue;
			}
			
			// Idle state
			if (target.isStationAlive()) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// wake up from idle state, and move onto respawn state
			else if (!target.isStationAlive() && !respawnSequenceStarted) {	
				System.out.println("stationRespawnTask.run(): Respawn sequence started");
				respawnSequenceStarted = true;
				delay = r.nextInt(respawnDelayHigh-respawnDelayLow) + respawnDelayLow;
			}
			
			// Respawn countdown
			if (respawnSequenceStarted) {
				if (delay>0) {
					try {
						--delay;
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else {	// respawn countdown finished
					System.out.println("stationRespawnTask.run(): Respawn sequence finished");
					respawnSequenceStarted = false;
					// revive target at random point!!
					while (true) {
						int newX = r.nextInt(referenceMap.getCol());
						int newY = r.nextInt(referenceMap.getRow());
						synchronized(PokemonMovementTask.class) {
							synchronized(StationRespawnTask.class) {
								if (target.revive(newY, newX, referencePokemons, referenceStations, referenceMap)) {
									System.out.println("stationRespawnTask.run(): Respawned at ("+newX+", "+newY+")");
									break;
								}
							}
						}
					}
				}
			}
		}
		
	}

}
