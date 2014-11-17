package sp_entities;

public enum UserRole { 
	NONE ("NONE", ""), 
	TEACHER("TEACHER", "�������������"), 
	CURATOR("CURATOR", "�������"),
	DEPWORKER("DEPWORKER", "�������� �������"),
	STUDENT("STUDENT", "�������");
	
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