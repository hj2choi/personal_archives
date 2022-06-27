package pokemon;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import pokemon.ui.StationRespawnTask;

/**
* <h1>Station.java</h1>
* it is a special type of Cell, recording the number of pokeballs it hold
* 
* @author  CHOI, Hong Joon
*/
public class Station extends Cell{

	private int pokeballs;
	private boolean alive;
	private ImageView icon;
	private StationRespawnTask respawnTask;
	private boolean waitingForUIUpdate;
	/**
	* Initialize Station
	*
	* @author  CHOI, Hong Joon
	* @param x
	* @param y
	* @param pokeballs number of pokeballs at station
	*/
	public Station(Runnable task, int x, int y, int pathScore, int pokeballs) {
		super(task, x, y, pathScore);
		// TODO Auto-generated constructor stub
		this.pokeballs = pokeballs;
		this.alive = true;
		
		this.respawnTask = (StationRespawnTask)task;
		respawnTask.setTarget(this);
		icon = Utilities.createImageView("ball_ani.gif");
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
	
	public ImageView getIcon() {
		return icon;
	}
	
	public boolean interactAndKill() {
		alive = false;
		icon.setVisible(false);
		return true;
	}
	
	public void pauseRespawnTask() {
		respawnTask.pause();
	}
	public void resumeRespawnTask() {
		respawnTask.resume();
	}
	
	public boolean revive(int y, int x, ArrayList<Pokemon> pokemons, ArrayList<Station> stations, Map m) {
		
		if (m.isWall(y,x)) {
			//System.out.println("STATION: CANNOT MOVE: wall!");
			return false;
		}
		if (x == m.getDestX() && y == m.getDestY()) {
			return false;
		}
		for (int i=0; i<pokemons.size(); ++i) {
			if (x == pokemons.get(i).getX() && y == pokemons.get(i).getY()) {
				//System.out.println("STATION: FATAL ERROR: OVERLAPS OTHER POKEMON");
				return false;
			}
		}
		for (int i=0; i<stations.size(); ++i) {
			if (stations.get(i).isStationAlive() && x == stations.get(i).getX() && y == stations.get(i).getY()) {
				//System.out.println("STATION: FATAL ERROR: OVERLAPS OTHER STATION");
				return false;
			}
		}
		this.y=y;
		this.x=x;
		alive = true;
		icon.setVisible(true);
		waitingForUIUpdate = true;
		
		return true;
	}
	
	public boolean isWaitingForUIUpdate() {
		return waitingForUIUpdate;
	}
	
	public void unsetUIUpdateFlag() {
		waitingForUIUpdate = false;
	}
	
	public boolean isStationAlive() {
		return alive;
	}

}
