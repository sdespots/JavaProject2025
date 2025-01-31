module trivia.guiTriviaProject {
    requires javafx.controls;
	requires javafx.graphics;
	requires transitive apiTriviaProject;
	requires org.junit.jupiter.api;
	requires junit;
    exports trivia.guiTriviaProject;
}
