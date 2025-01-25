package trivia.guiTriviaProject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class QuizParametersSceneCreator implements EventHandler<ActionEvent> {
	
	// Declaring our class variables.
	
	GridPane gridPane;
	Label noQuestions;
	Label category;
	Label difficulty;
	Label type;
	TextField noQuestionsInput;
	ChoiceBox<String> categoryInput;
	ChoiceBox<String> difficultyInput;
	ChoiceBox<String> typeInput;
	Button startQuizBtn;
	Button quitBtn;

	public QuizParametersSceneCreator() {
			
		gridPane = new GridPane();
			
			gridPane.setPadding(new Insets(10, 10, 10, 10));
			gridPane.setVgap(8);
			gridPane.setHgap(10);
			
				noQuestions = new Label();
				noQuestions.setText("Number of Questions:");
				GridPane.setConstraints(noQuestions, 0, 0);
				
				noQuestionsInput = new TextField();
				noQuestionsInput.setPromptText("No. of quiz questions");
				GridPane.setConstraints(noQuestionsInput, 1, 0);
				
				category = new Label();
				category.setText("Category:");
				GridPane.setConstraints(category, 0, 1);
				
				categoryInput = new ChoiceBox<>();
				categoryInput.setValue("Hi");
				categoryInput.getItems().addAll("Hi", "Hello", "Hola", "Salut");
				GridPane.setConstraints(categoryInput, 1, 1);
	
				difficulty = new Label();
				difficulty.setText("Difficulty:");
				GridPane.setConstraints(difficulty, 0, 2);
				
				difficultyInput = new ChoiceBox<>();
				difficultyInput.setValue("Easy");
				difficultyInput.getItems().addAll("Easy", "Medium", "Hard");
				GridPane.setConstraints(difficultyInput, 1, 2);
					
				type = new Label();
				type.setText("Type:");
				GridPane.setConstraints(type, 0, 3);
				
				typeInput = new ChoiceBox<>();
				typeInput.setValue("Multiplce choice");
				typeInput.getItems().addAll("Multiplce choice", "True/False");
				GridPane.setConstraints(typeInput, 1, 3);
				
				startQuizBtn = new Button();
				startQuizBtn.setText("Start Quiz!");
				startQuizBtn.setOnAction(this);
				GridPane.setConstraints(startQuizBtn, 1, 4);
				
				quitBtn = new Button();
				quitBtn.setText("Home");
				quitBtn.setOnAction(this);
				GridPane.setConstraints(quitBtn, 2, 4);
			
			gridPane.getChildren().addAll(noQuestions, category, difficulty, type);
			gridPane.getChildren().add(noQuestionsInput);
			gridPane.getChildren().addAll(categoryInput, difficultyInput, typeInput);
			gridPane.getChildren().add(quitBtn);
			gridPane.getChildren().add(startQuizBtn);
	}
	
		// Creating the createQuizParametersScene method, which will be called in App.java to create the scene.
	
	public Scene createQuizParametersScene() {
		return new Scene(gridPane, 400, 400);
	}

		// Creating the handle method, which is an EventHandler method to deal with action buttons.
	
	@Override
	public void handle(ActionEvent event) {
		
		if(event.getSource() == quitBtn) {
			App.window.setScene(App.homeScene);			
		}
		
		if(event.getSource() == startQuizBtn) {
			isInt(noQuestionsInput, noQuestionsInput.getText());
		}
		
		if(event.getSource() == startQuizBtn) {
			getChoice(noQuestionsInput, categoryInput, difficultyInput, typeInput);
		}
		
	//	if(event.getSource() == startQuizBtn) {
	//		App.window.setScene(App.multipleChoiceScene);
		}

	//}
	
	// Creating a method which checks whether the value inputed by the user in the text field is an integer or not.

	private boolean isInt(TextField noQuestionsInput, String message) {
		try {
			int number = Integer.parseInt(noQuestionsInput.getText());
			System.out.println("Question numbers are: " + number);
			return true;
		}catch(NumberFormatException e) {
			System.out.println("Error: " + message + "is not a number! Please insert a number to continue.");
			return false;
		}
	}
	
	// TODO implement a method that does not allow more than 50 to be inserted in the text box.
	
	// Creating a method to retrieve all data that the user inputs using our fields.
	
	private void getChoice(TextField noQuestionsInput, ChoiceBox<String> categoryInput, ChoiceBox<String> difficultyInput, ChoiceBox<String> typeInput) {
		String questions = noQuestionsInput.getText();
		String category = categoryInput.getValue();
		String difficulty = difficultyInput.getValue();
		String type = typeInput.getValue();
		System.out.println(questions);
		System.out.println(category);
		System.out.println(difficulty);
		System.out.println(type);
		
	}
	
}
