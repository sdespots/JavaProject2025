package pojoModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TriviaRequest {

	private String difficulty;
	private String category;
	private String type;
	private String question;

	@JsonProperty("correct_answer")
	private String correctAnswer;

	@JsonProperty("incorrect_answers")
	private List<String> incorrectAnswers;

	// Getters
	public String getDifficulty() {
		return difficulty;
	}

	public String getCategory() {
		return category;
	}

	public String getType() {
		return type;
	}

	public String getQuestion() {
		return question;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public List<String> getIncorrectAnswers() {
		return incorrectAnswers;
	}

	// Setters
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public void setIncorrectAnswers(List<String> incorrectAnswers) {
		this.incorrectAnswers = incorrectAnswers;
	}

	@Override
	public String toString() {
		return "TriviaRequest{" + "difficulty='" + difficulty + '\'' + ", category='" + category + '\'' + ", type='"
				+ type + '\'' + ", question='" + question + '\'' + ", correctAnswer='" + correctAnswer + '\''
				+ ", incorrectAnswers=" + incorrectAnswers + '}';
	}
}
