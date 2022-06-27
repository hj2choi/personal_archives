package base;
import java.io.BufferedWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import base.Utilities;

/*
 * TODO:
 * How do you configure input and output files?
 * 
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




public class Game {
	
	public static final int REPEAT_COUNT=10000;
	public static final long TIME_LIMIT_MILLIS = 20000;
	public static int actual_repeat=REPEAT_COUNT;
	
	public Map referenceMap;
	public Map[] maps;
	public Player[] players;
	
	
	public static int srcX;
	public static int srcY;
	
	public static int destX;
	public static int destY;
	
	
	public Cell[] targetList;
	public int targetsCount;
	public ArrayList<Cell> targets;
	
	public void initialize(File inputFile) throws Exception{
		targetList = new Cell[100];
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
			System.out.println(line);
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
			System.out.println(line);
			String[] tokens = line.split(",");
			int yPoint = Integer.parseInt(""+tokens[0].substring(1,tokens[0].length()));
			int xPoint = Integer.parseInt(""+tokens[1].substring(0,tokens[1].length()-1));
			//System.out.println(xPoint+", "+yPoint);
			// Station
			try {
				int pokeballs = Integer.parseInt(""+tokens[2].substring(1));
				//System.out.println(pokeballs);
				referenceMap.initializeStation(yPoint, xPoint, false, pokeballs);
				targets.add(new Station(xPoint, yPoint, 1, pokeballs));
				targetList[targetsCount++] = new Station(xPoint, yPoint, 1, pokeballs);
			} catch (Exception e) { // Pokemon
				String name = tokens[2].substring(1);
				String type = tokens[3].substring(1);
				int combatPower = Integer.parseInt(tokens[4].substring(1));
				int requiredBalls = Integer.parseInt(tokens[5].substring(1));
				referenceMap.initializePokemon(yPoint, xPoint, false, name, type, combatPower, requiredBalls);
				targets.add(new Pokemon(xPoint, yPoint, 1, name, type, combatPower, requiredBalls));
				targetList[targetsCount++] = new Pokemon(xPoint, yPoint, 1, name, type, combatPower, requiredBalls);
			}
		}
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
	
	public int playGame(int idx) {
		for (int i=0; i<targets.size(); ++i) {
			int targetY = targets.get(i).getY();
			int targetX = targets.get(i).getX();
			
			//ignore target if its already visited
			if (!(targets.get(i) instanceof Pokemon) && !(targets.get(i) instanceof Station)) {
				continue;
			}
			// ignore target if its too far away, or blocked
			int dist = performPathFinding(true, maps[idx], players[idx], players[idx].getY(), players[idx].getX(), targetY, targetX);
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
			Game.performPathFinding(false, maps[idx], players[idx], players[idx].getY(), players[idx].getX(), targetY, targetX);
			Game.traversePath(maps[idx], players[idx], players[idx].getY(), players[idx].getX(), false);
			//System.out.println(maps[j]);
		}
		
		//lastly, perform pathfinding to destination
		Game.performPathFinding(false, maps[idx], players[idx], players[idx].getY(), players[idx].getX(), Game.destY, Game.destX);
		Game.traversePath(maps[idx], players[idx], players[idx].getY(), players[idx].getX(), false);
		//System.out.println(maps[j]);
		
		//calculate score
		return players[idx].calculateGameScore();
	}
	
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
	
	private void shuffleTargets() {
		for (int i=0; i<targets.size(); ++i) {
			int swapTarget = (int)(Math.random()*targets.size());
			Cell temp = targets.get(i);
			targets.set(i, targets.get(swapTarget));
			targets.set(swapTarget, temp);
		}
	}

	public static int performPathFinding(boolean evaluate, Map map, Player player, int sy, int sx, int ty, int tx) {
		//System.out.println("sx="+sx+" sy="+sy+" tx="+tx+" ty="+ty);
		int distance = Utilities.dijkstraPathFinding(evaluate, map.generatePathCostMap(), sy, sx, ty, tx, map.getRow(), map.getCol());

		return distance;
	}
	
	// traverse given path and return score
	private static void traversePath(Map map, Player player, int sy, int sx, boolean evaluateMode) {

//		for (int i=0; i<map.getRow(); ++i) { 
//			for (int j=0; j<map.getCol(); ++j) {
//				if (Utilities.pathBuffer[i][j]) {
//					System.out.print(">");
//				} else {
//					System.out.print(".");
//				}
//			}
//			System.out.println();
//		}
		
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
	
	public static void main(String[] args) throws Exception{
		Utilities.startTimer();
		File inputFile = new File("./sampleIn.txt");
		File outputFile = new File("./assignOut.txt");
		
		if (args.length > 0) {
			inputFile = new File(args[0]);
		}

		if (args.length > 1) {
			outputFile = new File(args[1]);
		}
		
		Game game = new Game();
		game.initialize(inputFile);
		System.out.println("============================= FILE READ COMPLETE ========================");
		// TO DO 
		// Read the configures of the map and pokemons from the file inputFile
		// and output the results to the file outputFile
		//game.printMap();
		
		// shuffle order of targets and repeat gameplay
		int bestPlayerIndex = 0;
		int maxScore = 0;
		for (int j=0; j<REPEAT_COUNT; ++j) {
			// check timer and break out timely
			if (Utilities.checkTimer() >= TIME_LIMIT_MILLIS) {
				actual_repeat = j-1;
				break;
			}
			game.shuffleTargets();
			int currentScore = game.playGame(j);
			if (currentScore > maxScore) {
				bestPlayerIndex=j;
				maxScore = currentScore;
				
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
