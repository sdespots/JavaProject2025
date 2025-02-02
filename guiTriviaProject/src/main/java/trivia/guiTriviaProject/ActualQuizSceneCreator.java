package trivia.guiTriviaProject;

import java.util.ArrayList;
import java.util.Collections;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pojoModel.TriviaRequest;
import pojoModel.TriviaResponse;

public class ActualQuizSceneCreator {

	// All the below variables are used in multiple methods in this class - therefore we declare them as class attributes.
	
	GridPane gridPane;
	ToggleGroup answersToggleGroup;
	Button submitBtn;
	Button nextBtn;
	Button homeBtn;
	Button retryBtn;
	Label feedbackLbl;
	Label questionLbl;
	Label previousTotalScoreLbl;
	RadioButton answerA;
	RadioButton answerB;
	RadioButton answerC;
	RadioButton answerD;
	String correctAnswer;
	int questionCount;
	int correctUserAnswers;
	int incorrectUserAnswers;
	double previousHighestScore;
	double score;
	ArrayList<TriviaRequest> questions;

	// This constructor creates the necessary nodes and binds them together.
	
	public ActualQuizSceneCreator(ArrayList<TriviaRequest> questions) {

		this.questions = questions;
		this.questionCount = 0;

		gridPane = new GridPane();

		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(8);
		gridPane.setHgap(10);

		answersToggleGroup = new ToggleGroup();

		questionLbl = new Label();
		GridPane.setConstraints(questionLbl, 0, 0);

		answerA = new RadioButton();
		answerA.setToggleGroup(answersToggleGroup);
		GridPane.setConstraints(answerA, 0, 1);

		answerB = new RadioButton();
		answerB.setToggleGroup(answersToggleGroup);
		GridPane.setConstraints(answerB, 0, 2);

		answerC = new RadioButton();
		answerC.setToggleGroup(answersToggleGroup);
		GridPane.setConstraints(answerC, 0, 3);

		answerD = new RadioButton();
		answerD.setToggleGroup(answersToggleGroup);
		GridPane.setConstraints(answerD, 0, 4);

		submitBtn = new Button();
		submitBtn.setText("Submit");
		submitBtn.setOnAction(e -> validateAnswer());
		GridPane.setConstraints(submitBtn, 0, 5);

		nextBtn = new Button();
		nextBtn.setText("Next");
		nextBtn.setDisable(true);
		nextBtn.setOnAction(e -> loadNextQuestion());
		GridPane.setConstraints(nextBtn, 0, 6);

		homeBtn = new Button();
		homeBtn.setText("Home");
		homeBtn.setDisable(true);
		homeBtn.setOnAction(e -> goHome());
		GridPane.setConstraints(homeBtn, 0, 7);

		retryBtn = new Button();
		retryBtn.setText("Retry");
		retryBtn.setDisable(true);
		retryBtn.setOnAction(e -> retry());
		GridPane.setConstraints(retryBtn, 0, 8);

		feedbackLbl = new Label();
		GridPane.setConstraints(feedbackLbl, 0, 9);

		previousTotalScoreLbl = new Label();
		GridPane.setConstraints(previousTotalScoreLbl, 0, 10);

		gridPane.getChildren().addAll(questionLbl, answerA, answerB, answerC, answerD, submitBtn, feedbackLbl, nextBtn,
				retryBtn, homeBtn, previousTotalScoreLbl);

	}

	// This method is called in App.java to create the scene
	
	public Scene createActualQuizScene() {
		return new Scene(gridPane, 600, 500);
	}

	// Setter method to set the question retrieved from the API call.

	public void setTriviaQuestion(String question) {
		questionLbl.setText("Question: " + question);

	}

	// Setter method to set the answers retrieved from the API call.

	public void setTriviaAnswers(ArrayList<String> incorrectAnswers, String correctAnswer) {
		
		// Creating new local ArrayList variable, adding the incorrect answers
		
		ArrayList<String> allAnswers = new ArrayList<>(incorrectAnswers);
		
		// Adding the correct answer to the list.
		
		allAnswers.add(correctAnswer);	   
		
		// Storing the correct answer in a variable.
	    
		this.correctAnswer = correctAnswer;
		
		// Shuffling answers. 
		
		Collections.shuffle(allAnswers);
		
		// Setting default radio button to A (it is a best practice to not have null radio buttons)
		// In this way we also limit user error in the GUI.
		
		answersToggleGroup.selectToggle(answerA);
		
		// True/False question

	    if (allAnswers.size() == 2) {  
	        answerA.setText(allAnswers.get(0));
	        answerB.setText(allAnswers.get(1));
	        
	        // Hide extra answer choices
	        
	        answerC.setVisible(false);
	        answerD.setVisible(false);
	        
	     // Multiple-choice (4 answers)
	        
	    } else {  
	        answerA.setText(allAnswers.get(0));
	        answerB.setText(allAnswers.get(1));
	        answerC.setText(allAnswers.get(2));
	        answerD.setText(allAnswers.get(3));
	        answerC.setVisible(true);
	        answerD.setVisible(true);
	    }
	    
	}

	// Method to validate answer provided by user.

	public void validateAnswer() {
		
		// selectedAnswer and userAnswer declared as local variable as they're only used within this method.
		// selectedAnswer gets the value from the selected toggle.
		// user answer gets the text from the value retrieved from the toggle.
		
		RadioButton selectedAnswer = (RadioButton) answersToggleGroup.getSelectedToggle(); 

		String userAnswer = selectedAnswer.getText();

		if (userAnswer.equals(correctAnswer)) {
			feedbackLbl.setText("Correct! Well done. :)");
			feedbackLbl.setTextFill(Color.GREEN);
			correctUserAnswers++;
			nextBtn.setDisable(false);
			submitBtn.setDisable(true);
		} else {
			feedbackLbl.setText("Incorrect :(");
			feedbackLbl.setTextFill(Color.RED);
			incorrectUserAnswers++;
			nextBtn.setDisable(false);
			submitBtn.setDisable(true);
		}
	}

	// Method to load the next question and aggregate results in int variables.
	
	public void loadNextQuestion() {
		
		System.out.println("before: " + questionCount);
		
		// Incrementing the question count by 1.
		questionCount++;
		System.out.println("after: " + questionCount);
		
		//System.out.println("after: " + questionCount);
		
		
		// Prepare the next question if total number of questions is more than the incremental question count
		
		if (questionCount < questions.size()) {
			
			gridPane.getChildren().clear();
			
			// Clear the feedback label
			
			feedbackLbl.setText(""); 
			
			// Select the first radio button (no null radio buttons as explained above)
			
			answersToggleGroup.selectToggle(answerA); 

			// Instantiating a new TriviaRequest object, whose getter methods we're going to be using to retrieve the next questions,
			
			TriviaRequest nextQuestion = questions.get(questionCount);
			setTriviaQuestion(nextQuestion.getQuestion());
			setTriviaAnswers(nextQuestion.getIncorrectAnswers(), nextQuestion.getCorrectAnswer());
			nextBtn.setDisable(true); // Disable next button until user submits an answer
			submitBtn.setDisable(false); // Enable submit button again

			gridPane.getChildren().addAll(questionLbl, answerA, answerB, answerC, answerD, submitBtn, feedbackLbl,
					nextBtn, retryBtn, homeBtn, previousTotalScoreLbl);
			
			// No more questions, replace Submit and Next with Home and Retry
			
		} else {			
			submitBtn.setDisable(true);
			nextBtn.setDisable(true);
			homeBtn.setDisable(false);
			retryBtn.setDisable(false);

			// declaring totalAnsweredQuestions as local variable as it's only used in this method.
			
			int totalAnsweredQuestions = correctUserAnswers + incorrectUserAnswers;
			score = ((double)correctUserAnswers / totalAnsweredQuestions) * 100;

			if (score >= 60) {
				feedbackLbl.setText("Congratulations! You passed with final score: " + (int)score + "%"); // Casting to int in order to avoid many decimals.
				feedbackLbl.setTextFill(Color.GREEN);
			} else {
				feedbackLbl.setText("Sorry! You didn't pass. Please feel free to try again.");
				feedbackLbl.setTextFill(Color.RED);
			}
			if (score > previousHighestScore) {
				previousHighestScore = score;
				previousTotalScoreLbl.setText("New high score! Your high score is: " + (int)previousHighestScore + "%");		
			} else {
				previousTotalScoreLbl.setText("Your highest score so far is: " + (int)previousHighestScore + "%");
			}
		}
	}

	// Method to clear results, previous high score, and switch to the Home scene in order to restart the quiz.

	public void goHome() {
		previousHighestScore = 0;
		score = 0;
		correctUserAnswers = 0;
		incorrectUserAnswers = 0;
		questionCount = 0;
		gridPane.getChildren().clear();
		App.window.setScene(App.homeScene);
	}

	/* Method called to retry with the same questions:
	 *  - clears score, answers provided, disables next/home/retry buttons, enables retry button, loads the first q again.
	 */
	public void retry() {
		score = 0;
		correctUserAnswers = 0;
		incorrectUserAnswers = 0;
		questionCount = -1;
		nextBtn.setDisable(true);
		submitBtn.setDisable(false);
		homeBtn.setDisable(true);
		retryBtn.setDisable(true);
		loadNextQuestion(); 

	}
}
