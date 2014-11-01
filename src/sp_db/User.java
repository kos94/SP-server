package sp_db;

import sp_entities.UserStatus;

public class User {
	protected int id;
	protected String pass;
	protected String name;
	protected UserStatus status;
	
	public User() {}
	
	public User(int idUser, String password, String fullName, UserStatus status) {
		id = idUser;
		pass = password;
		name = fullName;
		this.status = status;
	}
	
	public void print() {
		System.out.println("user id: " + id + ", status: " + status);
		System.out.println(">pass: " + pass + ", name: " + name);
	}
}
