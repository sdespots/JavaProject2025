package trivia.guiTriviaProject;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class MultipleChoiceSceneCreator {
	
	GridPane gridPane;
	Menu answersMenu;
	ToggleGroup answersMenuToggle;
	Label labelA;
	Label labelB;
	Label labelC;
	Label labelD;
	RadioButton answerA;
	RadioButton answerB;
	RadioButton answerC;
	RadioButton answerD;
	
	
	public  MultipleChoiceSceneCreator() {
		
		gridPane = new GridPane();
		
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(8);
		gridPane.setHgap(10);
		
		answersMenu = new Menu();
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
		
		labelC = new Label();
		labelC.setText("C");
		GridPane.setConstraints(labelC, 0, 2);
		
		answerC = new RadioButton();
		answerC.setToggleGroup(answersMenuToggle);
		
		labelD = new Label();
		labelD.setText("D");
		GridPane.setConstraints(labelD, 0, 3);
		
		answerD = new RadioButton();
		answerD.setToggleGroup(answersMenuToggle);
		
	}
	
	

}
