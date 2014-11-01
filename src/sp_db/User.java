package sp_db;

import sp_entities.UserStatus;

public class User {
	protected String pass;
	protected String name;
	protected UserStatus status;
	
	public User() {}
	
	public User(String password, String fullName, UserStatus status) {
		pass = password;
		name = fullName;
		this.status = status;
	}
	
	public void print() {
		System.out.println("user status: " + status);
		System.out.println("  pass: " + pass + ", name: " + name);
	}
}
