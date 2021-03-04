package pokemon;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
* <h1>Utilities.java</h1>
* This class is the collection of the helper functions
* 
* @author  CHOI, Hong Joon
*/
public class Utilities {
	static long initialTimeInSpeedCheck=0;
	
	public static ImageView createImageView(String iconFileName) {
		Image icon = new Image((new File("icons/"+iconFileName).toURI().toString()));
		ImageView imgView = new ImageView(icon);
		imgView.setFitHeight(pokemon.ui.Game.STEP_SIZE);
		imgView.setFitWidth(pokemon.ui.Game.STEP_SIZE);
		return imgView;
	}
	
	public static ImageView createImageView(String iconFileName, double width, double height) {
		Image icon = new Image((new File("icons/"+iconFileName).toURI().toString()));
		ImageView imgView = new ImageView(icon);
		imgView.setFitHeight(width);
		imgView.setFitWidth(height);
		return imgView;
	}
	
	/**
	 * starts timer
	 */
	public static void startTimer()
	{
		initialTimeInSpeedCheck=System.currentTimeMillis();
	}

	/**
	 * returns current progress of the timer
	 * @return long
	 */
	public static long checkTimer()
	{
		//System.out.println("time = "+(System.currentTimeMillis()-initialTimeInSpeedCheck)+"ms");
		return (System.currentTimeMillis()-initialTimeInSpeedCheck);
	}
	
}
