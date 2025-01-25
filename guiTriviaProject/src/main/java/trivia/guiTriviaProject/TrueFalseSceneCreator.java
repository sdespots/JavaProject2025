package trivia.guiTriviaProject;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class TrueFalseSceneCreator {
	
	GridPane gridPane;
	Menu answersMenu;
	ToggleGroup answersMenuToggle;
	Label labelA;
	Label labelB;
	RadioButton answerA;
	RadioButton answerB;
	
	public  TrueFalseSceneCreator() {
		
		gridPane = new GridPane();
		
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(8);
		gridPane.setHgap(10);
		
		answersMenuToggle = new ToggleGroup();
		
		labelA = new Label();
		labelA.setText("A");
		GridPane.setConstraints(labelA, 0, 0);
			
		answerA = new RadioButton();
		answerA.setToggleGroup(answersMenuToggle);
		
		labelB = new Label();
		labelB.setText("B");
		GridPane.setConstraints(labelB, 0, 1);
		
		answerB = new RadioButton();
		answerB.setToggleGroup(answersMenuToggle);
		

	}
	
	public Scene createTrueFalseScene() {
		return new Scene(gridPane, 400, 400);
	}
}
