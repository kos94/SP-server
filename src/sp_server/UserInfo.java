package sp_server;
import sp_entities.UserRole;

public class UserInfo {
	private int id;
	private UserRole status;
	
	public UserInfo(int id, UserRole status) {
		this.id = id;
		this.status = status;
	}
	
	public int getId() { return id; }
	public UserRole getStatus() { return status; }
}
