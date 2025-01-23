package trivia.guiTriviaProject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
	
	// Stages
	
	Stage primaryStage; // Declaring the primaryStage variable, belonging to the Stage class.
	
	// Scenes
	
	Scene homeScene; // Declaring the homeScene variable, belonging to the Scene class.
	
	Scene quizScene; // Declaring the quizScene variable, belonging to the Scene class.
	
	// Panes
	
	FlowPane rootFlowPane; // Declaring the root node (rootFlowPane), belonging to the FlowPane class. We had to import the FlowPane package.
	
	// Action buttons
	
	Button enterQuizBtn; // Declaring the startQuizBtn variable, which will be used to enter the quiz parameters screen.
	
	Button startQuizBtn; // Declaring the startQuizButton variable, which will be used to trigger the creation of the quiz. 
	
	Button submitBtn; // Declaring the submitBtn variable, which will be used by the user to submit the answer they have selected.
	
	Button retryBtn; // Declaring the retryBtn variable, which will be used to retry the quiz with the same questions.
	

    @Override
    public void start(Stage stage) {
    	
    	this.primaryStage = stage;
    	
    		stage.setTitle("Stella's and Stefanos' Trivia Game!"); // Stage title, which will essentially be the window title.
    	
    	rootFlowPane = new FlowPane(); // Instantiating an object of the FlowPane class.
    	
    	enterQuizBtn = new Button("Alhamdulillah Button"); // Instantiating an object of the Button class.
    	
    	homeScene = new Scene(rootFlowPane, 650, 300); // Instantiating an object of the Scene class.
    	
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}