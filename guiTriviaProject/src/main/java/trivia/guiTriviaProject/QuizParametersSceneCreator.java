package trivia.guiTriviaProject;

import java.util.ArrayList;
import exception.TriviaAPIException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pojoModel.TriviaResponse;
import services.TriviaAPIService;

public class QuizParametersSceneCreator implements EventHandler<ActionEvent> {

	/*
	 * Declaring the class variables. For the ChoiceBoxes we're going to be using
	 * enumerations, in order to distinguish between UI labels and values passed to
	 * the API. Also, we're declaring class variables that are reused in different
	 * methods - other attributes are declared locally.
	 */
	GridPane gridPane;
	Label feedbackLbl;
	TextField noQuestionsInput;
	ChoiceBox<CategoryEnum> categoryInput;
	ChoiceBox<DifficultyEnum> difficultyInput;
	ChoiceBox<TypeEnum> typeInput;
	Button startQuizBtn;
	Button quitBtn;
	TriviaResponse response;

	// Populating the constructor method.

	public QuizParametersSceneCreator() {

		gridPane = new GridPane();

		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(8);
		gridPane.setHgap(10);

		Label noQuestions = new Label();
		noQuestions.setText("Number of Questions:");
		GridPane.setConstraints(noQuestions, 0, 0);

		noQuestionsInput = new TextField();
		noQuestionsInput.setPromptText("No. of quiz questions");
		GridPane.setConstraints(noQuestionsInput, 1, 0);

		Label category = new Label();
		category.setText("Category:");
		GridPane.setConstraints(category, 0, 1);

		// We're populating all the ChoiceBox components with enumerations created
		// specifically for the GUI.
		// The enumeration data structure was defined by studying the objects returned
		// from the API using Postman.

		categoryInput = new ChoiceBox<>();
		categoryInput.setValue(CategoryEnum.ANY);
		categoryInput.getItems().addAll(CategoryEnum.values());
		categoryInput.setMinWidth(2);
		GridPane.setConstraints(categoryInput, 1, 1);

		Label difficulty = new Label();
		difficulty.setText("Difficulty:");
		GridPane.setConstraints(difficulty, 0, 2);

		difficultyInput = new ChoiceBox<>();
		difficultyInput.setValue(DifficultyEnum.ANY);
		difficultyInput.getItems().addAll(DifficultyEnum.values());
		GridPane.setConstraints(difficultyInput, 1, 2);

		Label type = new Label();
		type.setText("Type:");
		GridPane.setConstraints(type, 0, 3);

		typeInput = new ChoiceBox<>();
		typeInput.setValue(TypeEnum.ANY);
		typeInput.getItems().addAll(TypeEnum.values());
		GridPane.setConstraints(typeInput, 1, 3);

		// the setOnAction method calls the handler method declared below.

		startQuizBtn = new Button();
		startQuizBtn.setText("Start Quiz!");
		startQuizBtn.setOnAction(this);
		GridPane.setConstraints(startQuizBtn, 1, 4);

		quitBtn = new Button();
		quitBtn.setText("Home");
		quitBtn.setOnAction(this);
		GridPane.setConstraints(quitBtn, 2, 4);

		feedbackLbl = new Label();
		GridPane.setConstraints(feedbackLbl, 0, 9);

		gridPane.getChildren().addAll(noQuestions, category, difficulty, type, noQuestionsInput, categoryInput,
				difficultyInput, typeInput, feedbackLbl, quitBtn, startQuizBtn);
	}

	// Creating the createQuizParametersScene method, which will be called in
	// App.java to create the scene.

	public Scene createQuizParametersScene() {
		return new Scene(gridPane, 600, 500);
	}

	// Creating a private method for TriviaResponse (imported from the
	// apiTriviaProject.pojoModel package).
	// The method arguments is the user input.

	private TriviaResponse getChoice(TextField noQuestionsInput, ChoiceBox<CategoryEnum> categoryInput,
			ChoiceBox<DifficultyEnum> difficultyInput, ChoiceBox<TypeEnum> typeInput)
			throws TriviaAPIException, Exception {

		// Extract and convert (parse) text input to integer.

		int amount = Integer.parseInt(noQuestionsInput.getText());

		// Retrieve the ID from the enumeration parameter corresponding to the user's
		// selection.

		int category = categoryInput.getValue().getCategoryId();

		// Retrieve the value from the enumeration parameter corresponding to the user's
		// selection.

		String difficulty = difficultyInput.getValue().getDifficultyValue();

		// Same as above.

		String type = typeInput.getValue().getTypeValue(); // getting the value from the enumeration selection.
		// System.out.println(amount);
		// System.out.println(category);
		// System.out.println(difficulty);
		// System.out.println(type);

		// Calling the getAPIData method from the TriviaAPIService class (imported from
		// the apiTriviaProject.services package.
		// Returning a TriviaResponse object, declared as a class variable above, since
		// it's also used in the handle method.

		response = TriviaAPIService.getAPIData(amount, category, difficulty, type);
		return response;

	}

	// Creating the handle method, which is an EventHandler method to deal with
	// action buttons.

	@Override
	public void handle(ActionEvent event) {

		// the condition here is the clicking on the "Quit" button.

		if (event.getSource() == quitBtn) {

			// Navigate to the home scene

			App.window.setScene(App.homeScene);

			// setting the else if condition to be the clicking on the "Start Quiz" button.

		} else if (event.getSource() == startQuizBtn) {

			// Validate user input:
			// User input must be (a) an integer, and (b) an integer up to 50
			// If both validations pass, the quiz starts.

			if (isInt(noQuestionsInput, noQuestionsInput.getText())) {
				if (maxNumberCheck(noQuestionsInput, noQuestionsInput.getText())) {
					try {

						// Calling the getChoice method declared above to fetch trivia questions from
						// the API.
						// Storing the API response to a TriviaResponse object (imported from
						// apiTriviaProject.pojoModel package.

						response = getChoice(noQuestionsInput, categoryInput, difficultyInput, typeInput);

						// Extract first question (position 0) data from the API response, including
						// possible answers.
						// Declaring local variables to store the information.

						String question = response.getResults().get(0).getQuestion();
						String correctAnswer = response.getResults().get(0).getCorrectAnswer();
						ArrayList<String> incorrectAnswers = response.getResults().get(0).getIncorrectAnswers();

						// Creating an ActualQuizSceneCreator instance, passing the results using the
						// getResults getter method declared in the TriviaResponse class.

						ActualQuizSceneCreator actualQuizSceneCreator = new ActualQuizSceneCreator(
								response.getResults());

						// Calling the setter methods declared in ActualQuizSceneCreatior class.

						actualQuizSceneCreator.setTriviaQuestion(question);
						actualQuizSceneCreator.setTriviaAnswers(incorrectAnswers, correctAnswer);

						// Calling the scene creator method declared in ActualQuizSceneCreator.

						App.actualQuizScene = actualQuizSceneCreator.createActualQuizScene();
						App.window.setScene(App.actualQuizScene);
						
						// Printing the error stack trace in case of errors.
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	// Validation methods called above.
	
	private boolean isInt(TextField noQuestionsInput, String message) {
		try {
			int number = Integer.parseInt(noQuestionsInput.getText());
			return true;
		} catch (NumberFormatException e) {
			feedbackLbl.setText("Please add only numbers in this field!!");
			feedbackLbl.setTextFill(Color.RED);
			return false;
		}
	}

	private boolean maxNumberCheck(TextField noQuestionsInput, String message) {
		int number = Integer.parseInt(noQuestionsInput.getText());
		if (number > 50) {
			feedbackLbl.setText("Please add up to 50 questions!");
			feedbackLbl.setTextFill(Color.RED);
			return false;
		}
		return true;
	}

}

