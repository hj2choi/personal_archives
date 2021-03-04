import javafx.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Test extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane pane = new Pane();
		// TODO Auto-generated method stub
		Circle circle = new Circle();
	    circle.centerXProperty().bind(pane.widthProperty().divide(2));
	    circle.centerYProperty().bind(pane.heightProperty().divide(2));
	    circle.setRadius(50);
	    circle.setStroke(Color.BLACK); 
	    circle.setFill(Color.WHITE);
	    pane.getChildren().add(circle); // Add circle to the pane
	    
	    Scene scene = new Scene(pane, 400, 400);
	    primaryStage.setTitle("ShowCircleCentered"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage

	}

}
