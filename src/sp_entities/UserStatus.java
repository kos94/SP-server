package sp_entities;

public enum UserStatus { 
	NONE ("NONE"), TEACHER("TEACHER"), CURATOR("CURATOR"),
	DEPWORKER("DEPWORKER"), STUDENT("STUDENT");
	private String name;
	private UserStatus(String enName) {
		name = enName;
	}
	@Override
	public String toString() {
		return name;
	}
};