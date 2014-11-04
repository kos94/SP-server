package sp_server;

import sp_entities.Semester;
import sp_db.User;

public class UserSession {
	private User userData;
	private Semester sSemester;
	private String sDepartment;
	private String sGroup;
	private String sSubject;
	private byte sStage;
	
	public UserSession(User data) {
		userData = data;
	}
	
	public void setSemester(Semester sem) {
		sSemester = sem;
	}
	
	public void setDepartment(String department) {
		sDepartment = department;
	}
	public void setGroup(String group) {
		sGroup = group;
	}
	
	public void setSubject(String subj) {
		sSubject = subj;
	}
	
	public void setStage(byte stage) {
		sStage = stage;
	}
	
	public User getUserData() { return userData; }
	public Semester getSemester() { return sSemester; }
	public String getDepartment() { return sDepartment; }
	public String getGroup() { return sGroup; }
	public String getSubject() { return sSubject; }
	public byte getStage() { return sStage; }
}
