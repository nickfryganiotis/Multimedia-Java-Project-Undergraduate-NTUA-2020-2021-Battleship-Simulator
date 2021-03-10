package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


/**
* This Application simulates a Battleship game.
* It was made specifically for Multimedia Techonology Course's Lab
* 
* Full JavaDoc Documentation is created for Player Class.
*
* @author  Nikolas Fryganiotis el16444 
* @version 1.0
* @since   2021-03-09 
*/

public class Main extends Application {
	private Stage primaryStage;
	
	public void start(Stage primaryStage) {
        this.primaryStage = primaryStage; // connect primary stage
        mainWindow();
    }
	public void mainWindow() {
		try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Application/Main.fxml"));
			Scene scene = new Scene(root,700,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Medialab Project");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
