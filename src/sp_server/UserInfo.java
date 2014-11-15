package sp_server;
import sp_entities.UserStatus;

public class UserInfo {
	private int id;
	private UserStatus status;
	
	public UserInfo(int id, UserStatus status) {
		this.id = id;
		this.status = status;
	}
	
	public int getId() { return id; }
	public UserStatus getStatus() { return status; }
}
