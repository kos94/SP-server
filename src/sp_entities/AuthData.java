package sp_entities;
import javax.xml.bind.annotation.*;

import sp_db.User;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthData extends User {
	private String idSession;
	private String department;
	private String group;
	
	public AuthData() {}
	public AuthData(String idSession, String name, UserRole status) {
		super(name, status);
		this.idSession = idSession;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public void setGroup(String group) {
		this.group = group;
	}
	
	public String getDepartment() { return department; }
	public String getGroup() { return group; }
	public String getIdSession() { return idSession; }
}
