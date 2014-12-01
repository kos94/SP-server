package sp_entities;

public enum UserRole { 
	NONE (""), 
	TEACHER("�������������"), 
	CURATOR("�������"),
	DEPWORKER("�������� �������"),
	STUDENT("�������");
	
	private String rusName;
	private UserRole(String name) {
		rusName = name;
	}
	public String getRusName() { 
		return rusName;
	}
	@Override
	public String toString() {
		return rusName;
	}
};