package trivia.guiTriviaProject;

public enum DifficultyEnum {
	
	ANY ("any", "Any"),
	EASY("easy", "Easy"),
	MEDIUM("medium", "Medium"),
	HARD("hard", "Hard");
	
	public String value;
	public String name;
	
	DifficultyEnum(String value, String name) {
		this.value = value;
		this.name = name;
	}
	
	// Overriding the toString method in order to display only the name in the UI.
	
	@Override
	public String toString() {
		return name;
	}

	public String getDifficultyValue() {
		return value;
	}

}
