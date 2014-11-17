package sp_entities;

public enum UserRole { 
	NONE ("NONE", ""), 
	TEACHER("TEACHER", "Преподаватель"), 
	CURATOR("CURATOR", "Куратор"),
	DEPWORKER("DEPWORKER", "Работник кафедры"),
	STUDENT("STUDENT", "Студент");
	
	private String enName;
	private String rusName;
	private UserRole(String englishName, String russianName) {
		enName = englishName;
		rusName = russianName;
	}
	public String getRusName() { 
		return rusName;
	}
	@Override
	public String toString() {
		return rusName;
	}
};