package sp_db;

import javax.xml.bind.annotation.*;
import sp_entities.UserRole;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
	private String pass;
	private String name;
	private UserRole status;
	
	public User() {}
	
	public User(String fullName, UserRole status, String password) {
		this(fullName, status);
		pass = password;
	}
	
	public User(String fullName, UserRole status) {
		name = fullName;
		this.status = status;
	}
	
	public User(User user) {
		pass = user.pass;
		name = user.name;
		status = user.status;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public UserRole getStatus() {
		return status;
	}

	public void setStatus(UserRole status) {
		this.status = status;
	}
	
	public boolean isRightPass(String password) {
		return (pass.equals(password));
	}
}
