package trivia.guiTriviaProject;

public enum TypeEnum {
	
	ANY ("any", "Any"),
	MULTIPLE_CHOICE ("multiple", "Multiple Choice"),
	TRUE_FALSE ("boolean", "True / False");

	public final String value;
	public final String name;
	
	TypeEnum(String value, String name) {
		this.value = value;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public String getTypeValue() {
		return value;
	}

}
