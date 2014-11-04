package sp_db;

import javax.xml.bind.annotation.*;
import sp_entities.UserStatus;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
	private String pass;
	private String name;
	private UserStatus status;
	
	public User() {}
	
	public User(String fullName, UserStatus status, String password) {
		this(fullName, status);
		pass = password;
	}
	
	public User(String fullName, UserStatus status) {
		name = fullName;
		this.status = status;
	}
	
	public void print() {
		System.out.println("user status: " + status);
		System.out.println("  pass: " + pass + ", name: " + name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}
	
	public boolean isRightPass(String password) {
		return (pass.equals(password));
	}
}
