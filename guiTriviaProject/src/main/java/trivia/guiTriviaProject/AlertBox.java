package trivia.guiTriviaProject;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
	
	GridPane gridPane;
	Button okButton;
	Button retryButton; 		// TODO - create retry button - where does it lead?
	Alert completionAlert;
	
	public static void display(String title, String message) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL); // Block user events/actions until modal is resolved.
		window.setTitle("Quiz completion!");
		
	}

}
