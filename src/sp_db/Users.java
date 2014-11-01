package sp_db;

import java.util.ArrayList;
import java.util.List;

public class Users {
	private List<User> users;
	
	public Users() {
		users = new ArrayList<>();
	}
	
	public void addUser(User u) {
		users.add(u);
	}
	
	//TODO delete
	public void print() {
		System.out.println("===========\nUsers: ");
		for(User u : users) {
			u.print();
		}
	}
}
