package trivia.guiTriviaProject;

public enum CategoryEnum {
	
	// Setting up the enumerable structure. We made a GET request via Postman to retrieve the trivia_categories list from the API.
	
	ANY(0, "Any"),
	GENERAL_KNOWLEDGE(9, "General Knowledge"),
	ENTERTAINMENT_BOOKS(10, "Entertainment: Books"),
	ENTERTAINMENT_FILM(11, "Entertainment: Film"),
	ENTERTAINMENT_MUSIC(12, "Entertainment: Music"),
	ENTERTAINMENT_MUSICALS_THEATERS(13, "Entertainment: Musicals & Theatres"),
	ENTERTAINMENT_TELEVISION(14,"Entertainment: Television"),
	ENTERTAINMENT_VIDEO_GAMES(15, "Entertainment: Video Games"),
	ENTERTAINMENT_BOARD_GAMES(16, "Entertainment: Board Games"),
	SCIENCE_NATURE(17, "Science & Nature"),
	SCIENCE_COMPUTERS(18, "Science: Computers"),
	SCIENCE_MATHEMATICS(19, "Science: Mathematics"),
	MYTHOLOGY(20, "Mythology"),
	SPORTS(21, "Sports"),
	GEOGRAPHY(22, "Geography"),
	HISTORY(23, "History"),
	POLITICS(24, "Politics"),
	ART(25, "Art"),
	CELEBRITIES(26, "Celebrities"),
	ANIMALS(27, "Animals"),
	VEHICLES(28, "Vehicles"),
	ENTERTAINMENT_COMICS(29, "Entertainment: Comics"),
	SCIENCE_GADGETS(30, "Science: Gadgets"),
	ENTERTAINMENT_JAPANESE_ANIME_MANGA(31, "Entertainment: Japanese Anime & Manga"),
	ENTERTAINMENT_CARTOON_ANIMATIONS(32, "Entertainment: Cartoon & Animations");

	final int id; // final in order for the id to not be changed.
	private final String name; // string to store the string value of the category name.
	
	// The enum constructor.
	
	CategoryEnum (int id, String name){
		this.id = id;
		this.name = name;
	}

	// Overriding the toString method in order to display only the name in the UI.
	
	@Override
	public String toString() {
		return name;
	}
	
	public String getCategoryName() {
		return name;
	}
	
	public int getCategoryId() {
		return id;
	}
	
}
