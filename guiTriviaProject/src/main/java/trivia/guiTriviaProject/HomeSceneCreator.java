package trivia.guiTriviaProject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HomeSceneCreator implements EventHandler<ActionEvent> {

	// First we declare our class variables.
	
	StackPane rootStackPane; 
	VBox rootVBox;
	Button enterQuizBtn; 
	Label homeSceneMainLbl;
	Label homeSceneOtherLbl;

	public HomeSceneCreator() {
		
		rootVBox = new VBox(20); // Instantiating an object ofs the FlowPane class.
		rootVBox.setAlignment(Pos.CENTER); // Aligning elements in the center of this pane.

			homeSceneMainLbl = new Label("Welcome to our Trivia Game project!");
			homeSceneOtherLbl = new Label("Please click on the button above to enter the game.");
	
			enterQuizBtn = new Button(); // Instantiating an object of the Button class.
			enterQuizBtn.setText("Enter Quiz!"); // Set button text.
			enterQuizBtn.setMinSize(120, 30); // Set minimum size for the button.
			enterQuizBtn.setOnAction(this); // "this" in order to call handle method.
		

		// Connecting the root with the child nodes.

		rootVBox.getChildren().add(enterQuizBtn);
		rootVBox.getChildren().add(homeSceneMainLbl);
		rootVBox.getChildren().add(homeSceneOtherLbl);
	}
	
		// Creating initial scene - enter quiz screen.

	Scene createHomeScene() {
		return new Scene(rootVBox, 400, 400);

	}
	
	// Implementing EventHandler class to use handle method.
	
	@Override
	public void handle(ActionEvent event) { // We're setting up the handle method, which will be called whenever we click on the button.
		if(event.getSource() == enterQuizBtn) { // We're identifying the source of the action (which button was it?)
			App.window.setScene(App.quizScene);
			
		}
		
	}

}
