package base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import base.Utilities;

/*
 * 
 * TODO:
 * There may be extra space between commas in inputs!!!
 * ex) < 3,  5>,  15,   sp
 * check for this
 * 
 * TODO:
 * USE DEFAULT PACKAGES
 * 
 * 
 * TODO: DONE
 * add pokeball logic into account
 * 
 * 
 * ????
 * any specific class field design requirement?
 * ????
 * 60secs per each test case?
 * ????
 * How do you configure input and output files?
 */

/**
* <h1>Game.java</h1>
* The Game program takes in Pokemon go map in text file
* and outputs the optimal path
*
* @author  CHOI, Hong Joon
*/
public class Game {
	
	public static final int REPEAT_COUNT=10000;
	public static final long TIME_LIMIT_MILLIS = 20000;
	public static int actual_repeat=REPEAT_COUNT;

	
	private static int srcX;
	private static int srcY;
	public static int destX;
	public static int destY;
	
	private Map referenceMap;
	private Map[] maps;
	private Player[] players;
	private ArrayList<Cell> targets;
	
	
	/**
	* Initialize all game related fields
	*
	* @author  CHOI, Hong Joon
	* @param inputFile this is input file used to initialize game map and player
	*/
	public void initialize(File inputFile) throws Exception{
		targets = new ArrayList<Cell>();
		
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		
		// Read the first of the input file
		String line = br.readLine();
		int M = Integer.parseInt(line.split(" ")[0]);
		int N = Integer.parseInt(line.split(" ")[1]);
		
		System.out.println("row="+M);
		System.out.println("col="+N);
		// To do: define a map
		referenceMap = new Map(M,N);
		
		// Read the following M lines of the Map
		for (int i = 0; i < M; i++) {
			line = br.readLine();
			//System.out.println(line);
			char[] tokens = line.toCharArray();
			// to do
			// Read the map line by line
			for (int j=0; j<N; j++) {
				if (tokens[j]=='#') {
					referenceMap.initializeCell(i, j, true);
				} else if (tokens[j]==' ') {
					referenceMap.initializeCell(i, j, false);
				} else if (tokens[j]=='D') {
					destX=j;
					destY=i;
					referenceMap.initializeCell(i, j, false);
				} else if (tokens[j]=='B') {
					// initialize player
					srcX=j;
					srcY=i;
					referenceMap.initializeCell(i, j, false);
				} else {
					referenceMap.initializeCell(i, j, false);
				}
			}
		}
		
		// to do
		// Find the number of stations and pokemons in the map 
		// Continue read the information of all the stations and pokemons by using br.readLine();
		while ((line=br.readLine()) != null) {
			line = line.replaceAll(" ", "");
			System.out.println(line);
			String[] tokens = line.split(",");
			int yPoint = Integer.parseInt(""+tokens[0].substring(1,tokens[0].length()));
			int xPoint = Integer.parseInt(""+tokens[1].substring(0,tokens[1].length()-1));
			//System.out.println(xPoint+", "+yPoint);
			// Station
			try {
				int pokeballs = Integer.parseInt(""+tokens[2]);
				//System.out.println(pokeballs);
				referenceMap.initializeStation(yPoint, xPoint, false, pokeballs);
				targets.add(new Station(xPoint, yPoint, 1, pokeballs));
			} catch (Exception e) { // Pokemon
				String name = tokens[2];
				String type = tokens[3];
				int combatPower = Integer.parseInt(tokens[4]);
				int requiredBalls = Integer.parseInt(tokens[5]);
				referenceMap.initializePokemon(yPoint, xPoint, false, name, type, combatPower, requiredBalls);
				targets.add(new Pokemon(xPoint, yPoint, 1, name, type, combatPower, requiredBalls));
			}
		}
		System.out.println(referenceMap);
		br.close();
		
		// initialize players and respective maps
		maps = new Map[REPEAT_COUNT];
		for (int i=0; i<maps.length; ++i) {
			maps[i] = new Map(referenceMap);
			
		}
		players = new Player[REPEAT_COUNT];
		for (int i=0; i<players.length; ++i) {
			players[i] = new Player(srcX, srcY);
		}
	}
	
	/**
	* For given player index, player plays the game, and visits node in the order of game.targets
	* Skips nodes with negative expected score.
	*
	* @author  CHOI, Hong Joon
	* @param idx player and map index thats gonna play the game
	* @return int returns the final game score of the particular player.
	*/
	public int playGame(int idx) {
		for (int i=0; i<targets.size(); ++i) {
			int targetY = targets.get(i).getY();
			int targetX = targets.get(i).getX();
			
			//ignore target if its already visited
			if (!(targets.get(i) instanceof Pokemon) && !(targets.get(i) instanceof Station)) {
				continue;
			}
			// ignore target if its too far away, or blocked
			int dist = performPathFinding(true, maps[idx], players[idx].getY(), players[idx].getX(), targetY, targetX);
			if (dist>=Cell.WALL_PATH_SCORE) {
				continue;
			}
			
			// calculate value of target
			int targetValue = 0;
			if (targets.get(i) instanceof Pokemon) {
				targetValue = ((Pokemon)targets.get(i)).estimateValue(players[idx], maps[idx]);
			} else if (targets.get(i) instanceof Station) {
				targetValue = ((Station)targets.get(i)).estimateValue(players[idx], maps[idx]);
			}
			// ignore target if the value is negative
			if (targetValue<0) {
				continue;
			}
			
			// perform pathfinding and traverse path
			Game.performPathFinding(false, maps[idx], players[idx].getY(), players[idx].getX(), targetY, targetX);
			Game.traversePath(maps[idx], players[idx], players[idx].getY(), players[idx].getX());
			//System.out.println(maps[j]);
		}
		
		//lastly, perform pathfinding to destination
		Game.performPathFinding(false, maps[idx], players[idx].getY(), players[idx].getX(), Game.destY, Game.destX);
		Game.traversePath(maps[idx], players[idx], players[idx].getY(), players[idx].getX());
		//System.out.println(maps[j]);
		
		//calculate score
		return players[idx].calculateGameScore();
	}

	/**
	* Writes the game result of the player to the given file
	*
	* @author  CHOI, Hong Joon
	* @param outFile file for final output
	* @param p player which will be used for output
	*/
	public static void outputResult(File outFile, Player p) throws Exception {
		if(!outFile.exists()){
			outFile.createNewFile();
		}
		FileWriter fw = new FileWriter(outFile);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(p.calculateGameScore()+"\n");
		bw.write(p.calculateNB()+":"+(p.calculateNP())+":"+(p.calculateNS())+":"+p.calculateMCP()+"\n");
		bw.write(p.pathString());
		bw.close();
	}
	
	/**
	* player performs pathfinding, and returns distance
	*
	* @author  CHOI, Hong Joon
	* @param evaluate if set to false, path buffer will not be generated for future use.
	* @param map this map will be used to do pathfinding
	* @param sy source y pos
	* @param sx source x pos
	* @param ty target y pos
	* @param yx target x pos
	* @tag Utilities.buffer stores the path generated by pathfinding function
	* @return int shortest distance from source to destination
	*/
	public static int performPathFinding(boolean evaluate, Map map, int sy, int sx, int ty, int tx) {
		//System.out.println("sx="+sx+" sy="+sy+" tx="+tx+" ty="+ty);
		int distance = Utilities.dijkstraPathFinding(evaluate, map.generatePathCostMap(), sy, sx, ty, tx, map.getRow(), map.getCol());

		return distance;
	}
	
	/**
	* Shuffles the order of target nodes in random order
	*
	* @author  CHOI, Hong Joon
	*/
	public void shuffleTargets() {
		for (int i=0; i<targets.size(); ++i) {
			int swapTarget = (int)(Math.random()*targets.size());
			Cell temp = targets.get(i);
			targets.set(i, targets.get(swapTarget));
			targets.set(swapTarget, temp);
		}
	}
	
	/**
	* player travels the map using the path generated by performPathfinding() function. Player interacts with objects on the map on the way of travel
	*
	* @author  CHOI, Hong Joon
	* @param map this map will be used to do traversing
	* @param player this player will interact with map objects
	* @param sy source y pos
	* @param sx source x pos
	* @tag performPathfinding() is pre-requisite to traversing path, as it uses Utilities.pathBuffer to traverse the map
	*/
	public static void traversePath(Map map, Player player, int sy, int sx) {
		
		boolean[][] path = Utilities.pathBuffer;
		if (path==null) {
			return;
		}
		int currX = sx;
		int currY = sy;
		
		boolean adjacentExists=true;
		while (true) {
			path[currY][currX]=false;
			if (currY+1<map.getRow() && path[currY+1][currX]) {
				currY = currY+1;
			} else if (currY-1>=0 && path[currY-1][currX]) {
				currY = currY-1;
			} else if (currX+1<map.getCol() && path[currY][currX+1]) {
				currX = currX+1;
			} else if (currX-1>=0 && path[currY][currX-1]) {
				currX = currX-1;
			} else {
				adjacentExists=false;
				return;
			}
			// handle event at each location while traversing each location
			player.moveTo(currY, currX, map);
			map.handleInteractionEvent(player);
		}
	}
	
	/**
	   * This is the main method which is a entry point of the program.
	   * @param args Unused.
	   * @return Nothing.
	   * @exception IOException On input error.
	   * @see IOException
	   */
	public static void main(String[] args) throws Exception{
		Utilities.startTimer();
		File inputFile = new File("./test3.txt");
		File outputFile = new File("./sampleOut.txt");
		
		if (args.length > 0) {
			inputFile = new File(args[0]);
		}

		if (args.length > 1) {
			outputFile = new File(args[1]);
		}
		
		Game game = new Game();
		game.initialize(inputFile);
		System.out.println("============================= FILE READ COMPLETE ========================");

		
		int bestPlayerIndex = 0;
		int maxScore = 0;
		int lastBreakthrough=0;
		
		// first, go straight to the destination
		Game.performPathFinding(false, game.maps[0], game.players[0].getY(), game.players[0].getX(), Game.destY, Game.destX);
		Game.traversePath(game.maps[0], game.players[0], game.players[0].getY(), game.players[0].getX());
		maxScore = game.players[0].calculateGameScore();
		System.out.println("\n======= "+game.players[0]+" ======== idx="+0);
		game.players[0].printPokemons();
		System.out.println(game.players[0].calculateGameScore());
		System.out.println(game.players[0].calculateNB()+":"+(game.players[0].calculateNP())+":"+(game.players[0].calculateNS())+":"+game.players[0].calculateMCP());
		
		
		// suffle targets and repeat the game
		for (int j=1; j<REPEAT_COUNT; ++j) {
			// check timer and break out timely
			if (Utilities.checkTimer() >= TIME_LIMIT_MILLIS) {
				actual_repeat = j-1;
				break;
			}
			
			// if repeating game in random order doesn't help improving the score, try blocking some random target points
//			if (j>lastBreakthrough+10) {
//				lastBreakthrough=j;
//				int targetsCount = game.targets.size();
//				Cell blockCell = game.targets.get((int)(Math.random()*targetsCount));
//				Cell tgtBlock = game.maps[j].get(blockCell.getY(), blockCell.getX());
//				tgtBlock.setPathCost(Cell.WALL_PATH_SCORE);
//			}
			
			
			game.shuffleTargets();
			int currentScore = game.playGame(j);
			if (currentScore > maxScore) {
				bestPlayerIndex=j;
				maxScore = currentScore;
				lastBreakthrough=j;
				
				System.out.println("\n======= "+game.players[j]+" ======== idx="+j);
				game.players[j].printPokemons();
				Player p = game.players[j];
				System.out.println(p.calculateGameScore());
				System.out.println(p.calculateNB()+":"+(p.calculateNP())+":"+(p.calculateNS())+":"+p.calculateMCP());
				//System.out.println(p.pathString());
			}
		}
		
		System.out.println("\n================Best Player Found=============\nidx "+bestPlayerIndex+" of "+actual_repeat);
		Game.outputResult(outputFile, game.players[bestPlayerIndex]);
		System.out.println("elapsed time = "+Utilities.checkTimer()+"ms");
	}
}
