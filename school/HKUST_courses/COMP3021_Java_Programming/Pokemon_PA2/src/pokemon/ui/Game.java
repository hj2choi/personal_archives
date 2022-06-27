package pokemon.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import pokemon.Cell;
import pokemon.Map;
import pokemon.Pokemon;
import pokemon.Player;
import pokemon.Station;
import pokemon.Utilities;

public class Game extends Application{
	
	// this define the size of one CELL
	public final static int STEP_SIZE = 40;
	
	private StackPane stackPane;
	private GridPane mapGrid;
	private Label scoreLabel;
	private Label caughtPokemonsLabel;
	private Label ownedPokeballsLabel;
	private Label caughtStatusLabel;
	private Label pausedStatusLabel;
	
	public static int srcX;
	public static int srcY;
	public static int destX;
	public static int destY;
	private Map map;
	private Player player;
	private ArrayList<Pokemon> pokemons;
	private ArrayList<Station> stations;
	private boolean paused;
	private boolean interactionInProgress;
	
	/**
	* Initialize all game related fields
	*
	* @author  CHOI, Hong Joon
	* @param inputFile this is input file used to initialize game map and player
	*/
	public void initialize(File inputFile) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		
		// Read the first of the input file
		String line = br.readLine();
		int M = Integer.parseInt(line.split(" ")[0]);
		int N = Integer.parseInt(line.split(" ")[1]);
		
		System.out.println("row="+M);
		System.out.println("col="+N);
		// To do: define a map
		map = new Map(M,N);
		pokemons = new ArrayList<Pokemon>();
		stations = new ArrayList<Station>();
		
		// Read the following M lines of the Map
		for (int i = 0; i < M; i++) {
			line = br.readLine();
			//System.out.println(line);
			char[] tokens = line.toCharArray();
			// to do
			// Read the map line by line
			for (int j=0; j<N; j++) {
				if (tokens[j]=='#') {
					map.initializeCell(i, j, true);
				} else if (tokens[j]==' ') {
					map.initializeCell(i, j, false);
				} else if (tokens[j]=='D') {
					destX=j;
					destY=i;
					map.setDestX(destX);
					map.setDestY(destY);
					map.initializeCell(i, j, false);
				} else if (tokens[j]=='B') {
					// initialize player
					srcX=j;
					srcY=i;
					map.initializeCell(i, j, false);
				} else {
					map.initializeCell(i, j, false);
				}
			}
		}
		
		// to do
		// Find the number of stations and pokemons in the map 
		// Continue read the information of all the stations and pokemons by using br.readLine();
		while ((line=br.readLine()) != null) {
			line = line.replaceAll(" ", "");
			//System.out.println(line);
			String[] tokens = line.split(",");
			int yPoint = Integer.parseInt(""+tokens[0].substring(1,tokens[0].length()));
			int xPoint = Integer.parseInt(""+tokens[1].substring(0,tokens[1].length()-1));
			//System.out.println(xPoint+", "+yPoint);
			// Station
			try {
				int pokeballs = Integer.parseInt(""+tokens[2]);
				stations.add(new Station(new StationRespawnTask(pokemons, stations, map), xPoint, yPoint, Cell.WALL_PATH_SCORE, pokeballs));
			} catch (Exception e) { // Pokemon
				String name = tokens[2];
				String type = tokens[3];
				int combatPower = Integer.parseInt(tokens[4]);
				int requiredBalls = Integer.parseInt(tokens[5]);
				pokemons.add(new Pokemon(new PokemonMovementTask(pokemons, stations, map), xPoint, yPoint, Cell.WALL_PATH_SCORE, name, type, combatPower, requiredBalls));
			}
		}
		//System.out.println(map);
		br.close();
		
		// initialize player
		player = new Player(srcX, srcY);

		System.out.println("============================= FILE READ COMPLETE ========================");
		// start thread for each pokemon
		for (int i=0; i<pokemons.size(); ++i) {
			pokemons.get(i).setDaemon(true);
			pokemons.get(i).start();
		}
		// start thread for each pokemon
		for (int i=0; i<stations.size(); ++i) {
			stations.get(i).setDaemon(true);
			stations.get(i).start();
		}
	}
	
	private void replaceIcon(ImageView icon, int x, int y) {
		mapGrid.getChildren().remove(icon);
		mapGrid.add(icon,x,y);
	}

	private void registerKeyEventListeners(Scene scene) {
		// add listener on key pressing
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (paused) {
					return;
				}
				switch (event.getCode()) {
				case UP:
					player.moveAtomic(Player.UP, map);
					replaceIcon(player.getIcon(), player.getX(), player.getY());
					break;
				case DOWN:
					player.moveAtomic(Player.DOWN, map);
					replaceIcon(player.getIcon(), player.getX(), player.getY());
					break;
				case LEFT:
					player.moveAtomic(Player.LEFT, map);
					replaceIcon(player.getIcon(), player.getX(), player.getY());
					break;
				case RIGHT:
					player.moveAtomic(Player.RIGHT, map);
					replaceIcon(player.getIcon(), player.getX(), player.getY());
					break;
				default:
					break;
				}
			}
		});

		// add listener key released
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				
				switch (event.getCode()) {
				case UP:
					player.unlockMove(Player.UP);
					break;
				case DOWN:
					player.unlockMove(Player.DOWN);
					break;
				case LEFT:
					player.unlockMove(Player.LEFT);
					break;
				case RIGHT:
					player.unlockMove(Player.RIGHT);
					break;
				default:
					break;
				}
			}
		});
	}
	
	private void pause() {
		paused = true;
		for (int i=0; i<pokemons.size(); ++i) {
			pokemons.get(i).pauseMovementTask();
		}
		for (int i=0; i<stations.size(); ++i) {
			stations.get(i).pauseRespawnTask();
		}
		pausedStatusLabel.setText("GAME PAUSED");
	}
	
	private void resume() {
		if (interactionInProgress) {
			return;
		}
		paused = false;
		for (int i=0; i<pokemons.size(); ++i) {
			pokemons.get(i).resumeMovementTask();
		}
		for (int i=0; i<stations.size(); ++i) {
			stations.get(i).resumeRespawnTask();
		}
		pausedStatusLabel.setText("");
	}
	
	private void SetPeriodicTimer() {
		
		// it will execute this periodically
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				
				
				// update game score
				scoreLabel.setText("Current score: "+player.calculateGameScore());
				caughtPokemonsLabel.setText("# of pokemons caught: "+player.calculateNP());
				ownedPokeballsLabel.setText("# of pokeballs owned: "+player.calculateNB());
				
				// check if player has reached exit
				if (player.getX()==destX && player.getY()==destY) {
					System.exit(0);
				}
				
				// watch for pokemon UI state change
				for (int i=0; i<pokemons.size(); ++i) {
					if (pokemons.get(i).isWaitingForUIUpdate()) {
						replaceIcon(pokemons.get(i).getIcon(), pokemons.get(i).getX(), pokemons.get(i).getY());
						pokemons.get(i).unsetUIUpdateFlag();
					}
				}
				
				// watch for station - UI state change
				for (int i=0; i<stations.size(); ++i) {
					if (stations.get(i).isWaitingForUIUpdate()) {
						replaceIcon(stations.get(i).getIcon(), stations.get(i).getX(), stations.get(i).getY());
						stations.get(i).unsetUIUpdateFlag();
					}
				}
				
				// watch for station - player interaction
				for (int i=0; i<stations.size(); ++i) {
					if (player.getY() == stations.get(i).getY() && player.getX() == stations.get(i).getX()) {
						if (stations.get(i).isStationAlive()) {
							player.interactStation(stations.get(i));
							stations.get(i).interactAndKill();
						}
					}
				}
				
				// watch for pokemon - player interaction
				for (int i=0; i<pokemons.size(); ++i) {
					if (player.getY() == pokemons.get(i).getY() && player.getX() == pokemons.get(i).getX()) {
						if (pokemons.get(i).isPokemonAlive()) {
							caughtStatusLabel.setText("");
							boolean interactionSuccess = player.interactPokemon(pokemons.get(i));
							handleInteractionEvent(pokemons.get(i), interactionSuccess);
							
							
							if (interactionSuccess){
								Pokemon destroyTgt = pokemons.get(i);
								destroyTgt.destroyMovementTask();
								mapGrid.getChildren().remove(destroyTgt.getIcon());
								pokemons.remove(i);
								destroyTgt = null;
							}
							else {
								// TODO : kill temporarily for later respawn
								pokemons.get(i).killTemporarily();
							}
						}
					}
				}
			}
		};
		// start the timer
		timer.start();
	}
	
	
	public void handleInteractionEvent(Pokemon pokemon, boolean interactionSuccess) {
		/////////////////////////////////////////
		// setup interaction event
		/////////////////////////////////////////
		pause();
		interactionInProgress = true;
		
		Group interactionGrid = new Group();
		stackPane.getChildren().add(interactionGrid);
		
		Rectangle transitionRect = new Rectangle(0, 0, mapGrid.getWidth(), mapGrid.getHeight());
		ImageView playerIcon = Utilities.createImageView("back.png", mapGrid.getWidth()/4, Math.min(mapGrid.getWidth()/4, mapGrid.getHeight()/2));
		
		playerIcon.relocate(mapGrid.getWidth()/5, (2.5*mapGrid.getHeight())/4 - mapGrid.getHeight()/4);
		int iconId = PokemonList.getIdOfFromName(pokemon.getPokemonName());
		ImageView pokemonIcon = Utilities.createImageView(iconId+".png", mapGrid.getWidth()/5, Math.min(mapGrid.getWidth()/5, mapGrid.getHeight()/2));
		pokemonIcon.relocate(mapGrid.getWidth()/1.5, mapGrid.getHeight()/10);
		
		TextArea battleText = new TextArea();
		battleText.setEditable(false);
		battleText.setWrapText(true);
		battleText.setPrefWidth(mapGrid.getWidth());
		battleText.setPrefHeight(Math.max(mapGrid.getHeight()/5, 120));
		battleText.relocate(0, mapGrid.getHeight()-mapGrid.getHeight()/5);
		int playerBalls = player.calculateNB();
		if (interactionSuccess) {
			playerBalls += pokemon.getRequiredBalls();
		}
		Button catchButton = new Button ("throw pokeball ("+pokemon.getRequiredBalls()+"/"+playerBalls+")");
		catchButton.relocate(mapGrid.getWidth()/2,mapGrid.getHeight()/2);
		if (mapGrid.getWidth()<400) {
			catchButton.relocate(mapGrid.getWidth()/5,mapGrid.getHeight()/1.5);
		}
		if (!interactionSuccess) {
			catchButton.setText("escape ("+pokemon.getRequiredBalls()+"/"+playerBalls+")");
			catchButton.setTextFill(Color.RED);
		}
		else {
			catchButton.setTextFill(Color.GREEN);
		}
		ImageView ball = Utilities.createImageView("ball_ani.gif", Math.max(mapGrid.getWidth()/13, 30), Math.max(mapGrid.getWidth()/13, 30));
		ball.relocate(mapGrid.getWidth()/5+mapGrid.getWidth()/8, (3*mapGrid.getHeight())/4 - mapGrid.getHeight()/4);
		
		interactionGrid.getChildren().add(transitionRect);

		/////////////////////////////////////////
		// Transition animation
		/////////////////////////////////////////
		ScaleTransition ft = new ScaleTransition(Duration.millis(1000),transitionRect);
		ft.setFromX(1/((double)map.getCol()));
		ft.setToX(1);
		ft.setFromY(1/((double)map.getRow()));
		ft.setToY(1);
		ft.play();
		ft.onFinishedProperty().set(new EventHandler<ActionEvent>() {
			// finished transition to white screen
	        @Override 
	        public void handle(ActionEvent actionEvent) {
	        	
	        	transitionRect.setFill(Color.WHITE);
	        	
	        	interactionGrid.getChildren().add(playerIcon);
	        	interactionGrid.getChildren().add(pokemonIcon);
	        	try {
	    			Thread.sleep(300);
	    		} catch (InterruptedException e) {
	    			e.printStackTrace();
	    		}
	        	/////////////////////////////////////////
	        	// Pokemon & player coming in
	        	////////////////////////////////////////
	        	
				/////////////////////////////////////////
				// Display message
				/////////////////////////////////////////
	        	interactionGrid.getChildren().add(battleText);
	        	String msgTxt = ("watch out! Wild "+pokemon.getPokemonName()+" has appeared!\n");
	        	final IntegerProperty i = new SimpleIntegerProperty(0);
	        	Timeline timeline = new Timeline();
	            KeyFrame keyFrame = new KeyFrame(
	                    Duration.millis(20),
	                    event -> {
	                        if (i.get() > msgTxt.length()) {
	                            timeline.stop();
	                        } else {
	                            battleText.setText(msgTxt.substring(0, i.get()));
	                            i.set(i.get() + 1);
	                        }
	                    });
	            timeline.getKeyFrames().add(keyFrame);
	            timeline.setCycleCount(msgTxt.length()+1);
	            timeline.play();
	            timeline.onFinishedProperty().set(new EventHandler<ActionEvent>() {
	    	        @Override 
	    	        public void handle(ActionEvent actionEvent) {
	    	        	try {
	    	    			Thread.sleep(300);
	    	    		} catch (InterruptedException e) {
	    	    			e.printStackTrace();
	    	    		}
	    	        	String msgTxt = ("What should we do?\n");
	    	        	final IntegerProperty i = new SimpleIntegerProperty(0);
	    	        	Timeline timeline = new Timeline();
	    	            KeyFrame keyFrame = new KeyFrame(
	    	                    Duration.millis(20),
	    	                    event -> {
	    	                        if (i.get() > msgTxt.length()) {
	    	                            timeline.stop();
	    	                        } else {
	    	                            battleText.setText(msgTxt.substring(0, i.get()));
	    	                            i.set(i.get() + 1);
	    	                        }
	    	                    });
	    	            timeline.getKeyFrames().add(keyFrame);
	    	            timeline.setCycleCount(msgTxt.length()+1);
	    	            timeline.play();
	    	            timeline.onFinishedProperty().set(new EventHandler<ActionEvent>() {
							/////////////////////////////////////////
							// Create button and prompt to throw balls
							/////////////////////////////////////////
	    	    	        @Override 
	    	    	        public void handle(ActionEvent actionEvent) {
	    	    	        	try {
	    	    	    			Thread.sleep(300);
	    	    	    		} catch (InterruptedException e) {
	    	    	    			e.printStackTrace();
	    	    	    		}
	    	    	        	interactionGrid.getChildren().add(catchButton);
	    	    	        	catchButton.setOnAction(new EventHandler<ActionEvent>() {
	    	    	    			public void handle(ActionEvent event) {
					    	    	    /////////////////////////////////////////
										// failed to catch
										/////////////////////////////////////////
	    	    	    				if (!interactionSuccess) {
		    	    	    				catchButton.setVisible(false);
	    									caughtStatusLabel.setText("NOT ENOUGH poke balls");
	    									caughtStatusLabel.setTextFill(Color.RED);
	    									System.out.println("(FAIL) interaction event closed");
    	    	    	    	        	Rectangle transitionRect = new Rectangle(0, 0, mapGrid.getWidth(), mapGrid.getHeight());
    	    	    	    	        	//transitionRect.setFill(Color.BLACK);
    	    	    	    	        	interactionGrid.getChildren().add(transitionRect);
    	    	    	    	        	FadeTransition ft = new FadeTransition(Duration.millis(1000),transitionRect);
    	    	    	    	    		ft.setFromValue(0.1);
    	    	    	    	    		ft.setToValue(1);
    	    	    	    	    		ft.play();
    	    	    	    	    		ft.onFinishedProperty().set(new EventHandler<ActionEvent>() {
    	    	    	    	    	        @Override 
    	    	    	    	    	        public void handle(ActionEvent actionEvent) {
    	    	    	    	    	        	stackPane.getChildren().remove(interactionGrid);
    		    	    	    					interactionInProgress = false;
    		    	    	    					resume();
    	    	    	    	    	        }
    	    	    	    	    		});
	    	    	    				} else {
	    	    	    					/////////////////////////////////////////
	    	    	    					// successful catch
	    	    	    					/////////////////////////////////////////
		    	    	    	        	interactionGrid.getChildren().add(ball);
		    	    	    				catchButton.setVisible(false);
		    	    	    				// THROW BALL
		    	    	    				TranslateTransition tt = new TranslateTransition(Duration.millis(1000), ball);
		    	    	    				tt.setFromX(ball.getX());
		    	    	    			    tt.setFromY(ball.getY());
		    	    	    			    tt.setToX(mapGrid.getWidth()/1.35-(mapGrid.getWidth()/5+mapGrid.getWidth()/8));
		    	    	    			    tt.setToY(mapGrid.getHeight()/4.5-((3*mapGrid.getHeight())/4 - mapGrid.getHeight()/4));
		    	    	    			    tt.play();
		    	    	    			    tt.onFinishedProperty().set(new EventHandler<ActionEvent>() {
		    	    	    	    			// Close interaction and resume the game
		    	    	    	    	        @Override 
		    	    	    	    	        public void handle(ActionEvent actionEvent) {
			    									caughtStatusLabel.setText("Pokemon Caught!");
			    									caughtStatusLabel.setTextFill(Color.GREEN);
			    									System.out.println("(SUCCESS) interaction event closed");
		    	    	    	    	        	Rectangle transitionRect = new Rectangle(0, 0, mapGrid.getWidth(), mapGrid.getHeight());
		    	    	    	    	        	transitionRect.setFill(Color.WHITE);
		    	    	    	    	        	interactionGrid.getChildren().add(transitionRect);
		    	    	    	    	        	FadeTransition ft = new FadeTransition(Duration.millis(1000),transitionRect);
		    	    	    	    	    		ft.setFromValue(0.1);
		    	    	    	    	    		ft.setToValue(1);
		    	    	    	    	    		ft.play();
		    	    	    	    	    		ft.onFinishedProperty().set(new EventHandler<ActionEvent>() {
		    	    	    	    	    			// finished transition to white screen
		    	    	    	    	    	        @Override 
		    	    	    	    	    	        public void handle(ActionEvent actionEvent) {
		    	    	    	    	    	        	stackPane.getChildren().remove(interactionGrid);
		    		    	    	    					interactionInProgress = false;
		    		    	    	    					resume();
		    	    	    	    	    	        }
		    	    	    	    	    		});
		    	    	    	    	        	
		    	    	    	    	        }
		    	    	    	    	    });
	    	    	    				}
	    	    	    		
	    	    	    			}
	    	    	    		});
	    	    	        }
	    	    	    });
	    	        }
	    	    });
	        }
	    });
	}
	
	
	
	
	
	
	public VBox addTextAndButton() {
		// labels
		scoreLabel = new Label("Current score: 0");
		caughtPokemonsLabel = new Label("# of pokemons caught: 0");
		ownedPokeballsLabel = new Label("# of pokeballs owned: 0");
		caughtStatusLabel = new Label("");
		pausedStatusLabel = new Label("");
		
		// buttons
		HBox hbox = new HBox();
		Button buttonResume = new Button("Resume");
		buttonResume.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				resume();
			}
		});
		Button buttonPause = new Button("Pause");
		buttonPause.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				pause();
			}
		});
		hbox.getChildren().add(buttonResume);
		hbox.getChildren().add(buttonPause);

		// return this vbox
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10)); // Set all sides to 10
		vbox.setSpacing(12); // Gap between nodes
		vbox.getChildren().add(scoreLabel);
		vbox.getChildren().add(caughtPokemonsLabel);
		vbox.getChildren().add(ownedPokeballsLabel);
		vbox.getChildren().add(caughtStatusLabel);
		vbox.getChildren().add(pausedStatusLabel);
		vbox.getChildren().add(hbox);
		return vbox;
	}
	
	public GridPane addMapGUI() {
		// use gridPane to handle all Map elements
		mapGrid = new GridPane();
		// add player to mapGUI
		mapGrid.add(player.getIcon(),player.getX(),player.getY());
		
		// add destination to mapGUI
		mapGrid.add(Utilities.createImageView("exit.png"),destX,destY);
		
		// add static map objects
		for (int i=0; i<map.getRow(); ++i) {
			for (int j=0; j<map.getCol();++j) {
				if (map.isWall(i,j)) {
					mapGrid.add(Utilities.createImageView("tree.png"),j,i);
				}
			}
		}
		
		// add pokemons
		for (int i=0; i<pokemons.size(); ++i) {
			mapGrid.add(pokemons.get(i).getIcon(),pokemons.get(i).getX(),pokemons.get(i).getY());
		}
		
		// add stations
		for (int i=0; i<stations.size(); ++i) {
			mapGrid.add(stations.get(i).getIcon(),stations.get(i).getX(),stations.get(i).getY());
		}
		
		return mapGrid;
	}
	
	public BorderPane addGUIScreen() {
		// Use a border pane as the root for scene
		BorderPane border = new BorderPane();
		
		// combine map and UI 
		stackPane = new StackPane();
		stackPane.getChildren().add(addMapGUI());
		border.setLeft(stackPane);
		border.setRight(addTextAndButton());
		return border;
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		File inputFile = new File("./sampleIn.txt");
		initialize(inputFile);
		
		Scene scene = new Scene(addGUIScreen(), Color.WHITE);
		registerKeyEventListeners(scene);
		SetPeriodicTimer();

		stage.setScene(scene);
		stage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}



}


/*
- 2D top-down vs Gardener
	try to pollute entire map while running away from gardener.
	- Limited vision: radar item to temporarily improve vision
	- items / skill set to distract gardeners



- BUcket







*/