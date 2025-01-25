package trivia.guiTriviaProject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	// Stages

	static Stage window; // Declaring the window variable, belonging to the Stage class.

	// Scenes

	static Scene homeScene; // Declaring the homeScene variable, belonging to the Scene class.
	static Scene quizScene; // Declaring the quizScene variable, belonging to the Scene class.
	static Scene triviaGameScene; // Declaring the triviaGameScene variable, belonging to the Scene class.

	
	// Labels
	
	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Stella's and Stefanos' Trivia Game!"); // Stage title, which will essentially be the window
		// title.

		this.window = stage;
		
		// understand this.
		
		HomeSceneCreator homeSceneCreator = new HomeSceneCreator(); // Calling the constructor of the HomeSceneCreator class, to construct the class.		
		homeScene = homeSceneCreator.createHomeScene(); // Calling the createHomeScene method of the homeSceneCreator class, to fill the object with values.

		QuizParametersSceneCreator quizParametersSceneCreator = new QuizParametersSceneCreator(); // Calling the constructor of the QuizParametersSceneCreator class, to construct the class.		
		quizScene = quizParametersSceneCreator.createQuizParametersScene(); // Calling the createQuizParemetersScene method of the quizParametersSceneCreator class, to fill the object with values.

		
		// understand this.
		
		stage.setScene(homeScene); // Display the homeScene in the stage.

		stage.show(); // Display the application window.
	}
}