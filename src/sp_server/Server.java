package sp_server;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

import javax.jws.WebService;

import sp_db.*;
import sp_entities.*;

@WebService
public class Server {
	private DB db;
	private Map<String, UserInfo> sessions;
	private SecureRandom secureRandom;

	public Server() {
		XMLSerializer.saveObject(new Users(), "azaza.xml");
		db = new DB();
		sessions = new HashMap<>();
		secureRandom = new SecureRandom();
	}

	private AuthData getAuthData(String idSession, UserInfo info) {
		int id = info.getId();
		String userName = db.getUserName(id);
		AuthData data = new AuthData(idSession, userName, info.getStatus());
		UserRole status = data.getStatus();
		switch(status) {
		case DEPWORKER:
			String dep = db.getWorkerDepartment(id);
			data.setDepartment(dep);
			break;
		case STUDENT:
			String group = db.getStudentGroup(id);
			data.setGroup(group);
			break;
		}
		return data;
	}
	
	public String login(int univerID, String password) {
		UserInfo userInfo = null;
		String idSession = null;
		
		User user = db.login(univerID, password);
		if (user == null) return null;
		
		for (Map.Entry<String, UserInfo> session : sessions.entrySet()) {
			UserInfo info = session.getValue();
			if(info.getId() == univerID ) {
				userInfo = info;
				idSession = session.getKey();
				break;
			}
		}
		
		if (userInfo == null) {
			userInfo = new UserInfo(univerID, user.getStatus());
			idSession = new BigInteger(130, secureRandom).toString(32);
			sessions.put(idSession, userInfo);
			System.out.println("add user " + univerID + ", " + password + ". session: " + idSession);
		}
		
		AuthData authData = getAuthData(idSession, userInfo);
		return XMLSerializer.objectToXML(authData);
	}

	private UserInfo getUserIfAuthorized(String idSession, UserRole status) {
		UserInfo user = sessions.get(idSession);
		if(user == null) return null;
		if(user.getStatus() == status || 
		 (status == UserRole.TEACHER && user.getStatus() == UserRole.CURATOR))
			return user;
		return null;
	}

	public String getTeacherSemesters(String idSession) {
		UserInfo user = getUserIfAuthorized(idSession, UserRole.TEACHER);
		if(user == null) return null;
		Semesters sems = db.getTeacherSemesters(user.getId());
		return XMLSerializer.objectToXML(sems);
	}
	
	public List<String> getTeacherSubjects(String idSession, String semester) {
		UserInfo user = getUserIfAuthorized(idSession, UserRole.TEACHER);
		if(user == null) return null;
		Semester sem = (Semester)
				XMLSerializer.xmlToObject(semester, Semester.class);
		return db.getTeacherSubjects(user.getId(), sem);
	}
	
	public List<String> getTeacherGroups(String idSession, String semester, String subject) {
		UserInfo user = getUserIfAuthorized(idSession, UserRole.TEACHER);
		if(user == null) return null;
		Semester sem = (Semester)
				XMLSerializer.xmlToObject(semester, Semester.class);
		List<String> groupsList = db.getTeacherGroups(user.getId(), sem, subject);
		Collections.sort(groupsList);
		return groupsList;
	}
	
	public String getCuratorSemesters(String idSession) {
		UserInfo user = getUserIfAuthorized(idSession, UserRole.CURATOR);
		if(user == null) return null;
		Semesters sems = db.getCuratorSemesters(user.getId());
		return XMLSerializer.objectToXML(sems);
	}
	
	public String getCuratorGroup(String idSession, String semester) {
		UserInfo user = getUserIfAuthorized(idSession, UserRole.CURATOR);
		if(user == null) return null;
		Semester sem = (Semester)
				XMLSerializer.xmlToObject(semester, Semester.class);
		return db.getCuratorGroup(user.getId(), sem);
	}
	
	public List<String> getGroupSubjects(String idSession, String semester, String group) {
		UserInfo user = sessions.get(idSession);
		if(user == null) return null;
		Semester sem = (Semester)
				XMLSerializer.xmlToObject(semester, Semester.class);
		return db.getGroupSubjects(group, sem);
	}
	
	public String getDepSemesters(String idSession) {
		UserInfo user = getUserIfAuthorized(idSession, UserRole.DEPWORKER);
		if(user == null) return null;
		String dep = db.getWorkerDepartment(user.getId());
		Semesters sems = db.getDepSemesters(dep);
		return XMLSerializer.objectToXML(sems);
	}
	
	public List<String> getDepGroups(String idSession, String semester) {
		UserInfo user = getUserIfAuthorized(idSession, UserRole.DEPWORKER);
		if(user == null) return null;
		String dep = db.getWorkerDepartment(user.getId());
		Semester sem = (Semester)
				XMLSerializer.xmlToObject(semester, Semester.class);
		List<String> groupsList = db.getDepGroups(dep, sem);
		Collections.sort(groupsList);
		return groupsList;
	}
	
	public String getStudentSemesters(String idSession) {
		UserInfo user = getUserIfAuthorized(idSession, UserRole.STUDENT);
		if(user == null) return null;
		Semesters sems = db.getStudentSemesters(user.getId());
		return XMLSerializer.objectToXML(sems);
	}
	
	public String getSubjectMarks(String idSession, String group, String subject) {
		UserInfo user = sessions.get(idSession);
		if(user == null) return null;
		switch(user.getStatus()) {
		case TEACHER:
			if(!db.checkTeacherSubjectRights(user.getId(), subject, group))
				return null;
			break;
		case CURATOR:
			if(!db.checkCuratorSubjectRights(user.getId(), subject, group) &&
			   !db.checkTeacherSubjectRights(user.getId(), subject, group))
				return null;
			break;
		case DEPWORKER:
			if(!db.checkDepWorkerGroupRights(user.getId(), group))
				return null;
			break;
		default:
			return null;
		}
		
		GroupSubjectMarks marks = db.getSubjectMarks(group, subject);
		marks.sortByFirstColumn();
		return XMLSerializer.objectToXML(marks);
	}
	
	public String getStageMarks(String idSession, String group, String semester, int stage) {
		UserInfo user = sessions.get(idSession);
		if(user == null) return null;
		Semester sem = (Semester)
				XMLSerializer.xmlToObject(semester, Semester.class);
		switch(user.getStatus()) {
		case CURATOR:
			boolean hasRights = db.checkCuratorGroupRights(user.getId(), group, sem);
			if(!hasRights) return null;
			break;
		case DEPWORKER:
			hasRights = db.checkDepWorkerGroupRights(user.getId(), group);
			if(!hasRights) return null;
			break;
		default: 
			return null;
		}
		
		GroupStageMarks marks = db.getStageMarks(group, sem, stage);
		marks.sortByFirstColumn();
		return XMLSerializer.objectToXML(marks);
	}

	public String getStudentMarks(String idSession, String semester) {
		UserInfo user = getUserIfAuthorized(idSession, UserRole.STUDENT);
		if(user == null) return null;
		Semester sem = (Semester)
				XMLSerializer.xmlToObject(semester, Semester.class);
		StudentSemMarks marks = db.getStudentMarks(user.getId(), sem);
		marks.sortByFirstColumn();
		return XMLSerializer.objectToXML(marks);
	}
}
