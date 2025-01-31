package trivia.guiTriviaProject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HomeSceneCreator implements EventHandler<ActionEvent> {

	// First we declare our class variables that are used in multiple methods.

	VBox rootVBox;
	Button enterQuizBtn; 


	public HomeSceneCreator() {
		
		// Instantiating an object of the VBox class and aligning UI elements to the center.
		// Similar UI configurations follow.
		
		rootVBox = new VBox(20); 
		rootVBox.setAlignment(Pos.CENTER); 

		
			Label homeSceneMainLbl = new Label("Welcome to our Trivia Game project!");
			homeSceneMainLbl.setTextFill(Color.GREEN);
			homeSceneMainLbl.setFont(Font.font(null, FontWeight.BOLD, 20));
			Label homeSceneOtherLbl = new Label("Please click on the button above to enter the game.");
	
			enterQuizBtn = new Button(); 
			enterQuizBtn.setText("Enter Quiz!"); 
			enterQuizBtn.setMinSize(120, 30); 
			enterQuizBtn.setOnAction(this); 
		

		// Connecting the root with the child nodes.

		rootVBox.getChildren().add(enterQuizBtn);
		rootVBox.getChildren().add(homeSceneMainLbl);
		rootVBox.getChildren().add(homeSceneOtherLbl);
	}
	
	// Creating initial scene - enter quiz screen.

	Scene createHomeScene() {
		return new Scene(rootVBox, 600, 500);

	}
	
	// Implementing EventHandler class to use handle method.
	
	@Override
	
	// We're setting up the handle method, which will be called whenever we click on the button.
	
	public void handle(ActionEvent event) { 
		
		// We're identifying the source of the action (which button was it?)
		
		if(event.getSource() == enterQuizBtn) { 
			App.window.setScene(App.quizScene);
			
		}
		
	}

}
