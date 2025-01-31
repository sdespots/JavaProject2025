package trivia.guiTriviaProject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

	// Declaring our stage. The static nature of this field facilitates scene switching.

	static Stage window; 

	// Declaring our scenes - the three different application screens.

	static Scene homeScene; 
	static Scene quizScene; 
	static Scene actualQuizScene; 
		
	// launch(args) is a JavaFX method starting the GUI.
	
	public static void main(String[] args) {
		launch(args);
	}

	// Declaring the start method.
	
	@Override
	public void start(Stage stage) throws Exception {
		
		// Setting the stage title, which will essentially be the browser window.
		
		stage.setTitle("Stella's and Stefanos' Trivia Game!"); 

		// Assign the stage reference to our static variable for scene management

		this.window = stage;
		
		// Below we call all the methods necessary to initialize the Scenes. The methods themselves are created in the respective classes.
			
		HomeSceneCreator homeSceneCreator = new HomeSceneCreator(); 		
		
		// Calling the createHomeScene method of the homeSceneCreator class, in order to create the class instance.
		// The same code is reproduced with minor variations for the same classes.
		
		homeScene = homeSceneCreator.createHomeScene(); 

		QuizParametersSceneCreator quizParametersSceneCreator = new QuizParametersSceneCreator(); 	
		quizScene = quizParametersSceneCreator.createQuizParametersScene(); 

		
		ActualQuizSceneCreator actualQuizSceneCreator = new ActualQuizSceneCreator(null);	
		actualQuizScene = actualQuizSceneCreator.createActualQuizScene();
		
		
		// Calling the setScene method to define homeScene as the initial screen.
		
		stage.setScene(homeScene); 
	
		// Display the application window.
		
		stage.show(); 
	}
}