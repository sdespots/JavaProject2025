package trivia.guiTriviaProject;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.geometry.*;

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
