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
	private SecureRandom secureRandom;
	private Map<String, UserInfo> sessions;

	public Server() {
		db = new DB();
		secureRandom = new SecureRandom();
		sessions = new HashMap<>();
		tempTeacherScenario();
//		tempCuratorScenario();
//		tempDepWorkerScenario();
//		tempStudentScenario();
	}

	private void tempTeacherScenario() {
		
		System.out.println("--- Login");
		String authStr = login(2, "bbb");
		System.out.println( authStr );
		AuthData authData = (AuthData)XMLSerializer.xmlToObject(authStr, AuthData.class);
		String idSession = authData.getIdSession();
		
		System.out.println("--- Get teacher semesters: ");
		String sems = getTeacherSemesters(idSession);
		System.out.println(sems);

		String chosenSem = XMLSerializer.objectToXML(new Semester(2, 2012));

		System.out.println("--- Get teacher subjects: ");
		List<String> subjects = getTeacherSubjects(idSession, chosenSem);
		for (String s : subjects) {
			System.out.println(s);
		}

		String chosenSubj = subjects.get(0);

		System.out.println("--- Get teacher groups: ");
		List<String> groups = getTeacherGroups(idSession, chosenSem, chosenSubj);
		for (String g : groups) {
			System.out.println(g);
		}

		String chosenGroup = groups.get(0);
		
		System.out.println("--- Get subject marks: ");
		String marks = getSubjectMarks(idSession, chosenGroup, chosenSubj);
		System.out.println(marks);
		GroupSubjectMarks gsm = (GroupSubjectMarks)XMLSerializer
				.xmlToObject(marks, GroupSubjectMarks.class);
		gsm.print();
	}

	private void tempCuratorScenario() {
		System.out.println("--- Login");
		String authStr = login(3, "ccc");
		System.out.println( authStr );
		AuthData authData = (AuthData)XMLSerializer.xmlToObject(authStr, AuthData.class);
		String idSession = authData.getIdSession();
		
		System.out.println("--- Get curator semesters: ");
		String sems = getCuratorSemesters(idSession);
		System.out.println(sems);
		
		String chosenSem = XMLSerializer.objectToXML(new Semester(2, 2012));
		
		System.out.println("--- Get curator groups: ");
		String group = getCuratorGroup(idSession, chosenSem);
		System.out.println(group);

		/* <var1> */
		int chosenStage = 3;
		
		System.out.println("--- Get stage marks: ");
		String stageMarks = getStageMarks(idSession, group, chosenSem, chosenStage);
		System.out.println(stageMarks);
		GroupStageMarks gstm =  (GroupStageMarks)XMLSerializer
				.xmlToObject(stageMarks, GroupStageMarks.class);
		gstm.print();
		/* </var1> */
		
//		/* <var2> */
//		System.out.println("--- Get group subjects: ");
//		List<String> subjects = getGroupSubjects(idSession, chosenSem, group);
//		for (String s : subjects) {
//			System.out.println(s);
//		}
//		
//		String chosenSubj = subjects.get(0);
//		
//		System.out.println("--- Get subject marks: ");
//		String subjMarks = getSubjectMarks(idSession, group, chosenSubj);
//		System.out.println(subjMarks);
//		GroupSubjectMarks gsm = (GroupSubjectMarks)XMLSerializer
//				.xmlToObject(subjMarks, GroupSubjectMarks.class);
//		gsm.print();
//		/* </var2> */
	}
	
	private void tempDepWorkerScenario() {
		System.out.println("--- Login");
		String authStr = login(5, "eee");
		System.out.println( authStr );
		AuthData authData = (AuthData)XMLSerializer.xmlToObject(authStr, AuthData.class);
		String idSession = authData.getIdSession();

		System.out.println( authData.getDepartment() );
		
		System.out.println("--- Get dep worker semesters: ");
		String sems = getDepSemesters(idSession);
		System.out.println(sems);
		
		Semester sem = ((Semesters)XMLSerializer.xmlToObject(sems, Semesters.class))
				.getSemesters().get(2);
		String chosenSem = XMLSerializer.objectToXML(sem);

		System.out.println("--- Get dep worker groups: ");
		List<String> groups = getDepGroups(idSession, chosenSem);
		for (String g : groups) {
			System.out.println(g);
		}
		
		String chosenGroup = groups.get(1);

//		/* <var1> */
//		int chosenStage = 3;
//		
//		System.out.println("--- Get stage marks: ");
//		String stageMarks = getStageMarks(idSession, chosenGroup, chosenSem, chosenStage);
//		System.out.println(stageMarks);
//		GroupStageMarks gstm =  (GroupStageMarks)XMLSerializer
//				.xmlToObject(stageMarks, GroupStageMarks.class);
//		gstm.print();
//		/* </var1> */
		
		/* <var2> */
		System.out.println("--- Get group subjects: ");
		List<String> subjects = getGroupSubjects(idSession, chosenSem, chosenGroup);
		for (String s : subjects) {
			System.out.println(s);
		}
		
		String chosenSubj = subjects.get(0);
		
		System.out.println("--- Get subject marks: ");
		String subjMarks = getSubjectMarks(idSession, chosenGroup, chosenSubj);
		System.out.println(subjMarks);
		GroupSubjectMarks gsm = (GroupSubjectMarks)XMLSerializer
				.xmlToObject(subjMarks, GroupSubjectMarks.class);
		gsm.print();
		/* </var2> */
	}
	
	private void tempStudentScenario() {
		System.out.println("--- Login");
		String authStr = login(7, "ggg");
		System.out.println( authStr );
		AuthData authData = (AuthData)XMLSerializer.xmlToObject(authStr, AuthData.class);
		String idSession = authData.getIdSession();
		
		System.out.println("--- Get student semesters: ");
		String sems = getStudentSemesters(idSession);
		System.out.println(sems);
		
		Semester sem = ((Semesters)XMLSerializer.xmlToObject(sems, Semesters.class))
				.getSemesters().get(0);
		String chosenSem = XMLSerializer.objectToXML(sem);
		
		String marks = getStudentMarks(idSession, chosenSem);
		System.out.println(marks);
		StudentSemMarks ssm = (StudentSemMarks) XMLSerializer
				.xmlToObject(marks, StudentSemMarks.class);
		ssm.print();
	}
	
	private AuthData getAuthData(String idSession, UserInfo info) {
		int id = info.getId();
		String userName = db.getUserName(id);
		AuthData data = new AuthData(idSession, userName, info.getStatus());
		UserStatus status = data.getStatus();
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
		
		for (Map.Entry<String, UserInfo> session : sessions.entrySet()) {
			UserInfo info = session.getValue();
			if(info.getId() == univerID ) {
				userInfo = info;
				idSession = session.getKey();
				break;
			}
		}
		
		if (userInfo == null) {
			User user = db.login(univerID, password);
			if (user == null) return null;
			userInfo = new UserInfo(univerID, user.getStatus());
			idSession = new BigInteger(130, secureRandom).toString(32);
			sessions.put(idSession, userInfo);
			System.out.println("add user " + univerID + ", " + password + ". session: " + idSession);
		}
		
		AuthData authData = getAuthData(idSession, userInfo);
		return XMLSerializer.objectToXML(authData);
	}

	private UserInfo getUserIfAuthorized(String idSession, UserStatus status) {
		UserInfo user = /*TODO*/ sessions.get(idSession);
		if(user == null || user.getStatus() != status) 
			return null;
		return user;
	}
	
	//TODO DELETE THIS STUB FOR COMPILING
	private UserInfo getUserIfAuthorized(int i, UserStatus s) {
		return null;
	}
	
	public String getTeacherSemesters(String idSession) {
		UserInfo user = getUserIfAuthorized(idSession, UserStatus.TEACHER);
		if(user == null) return null;
		Semesters sems = db.getTeacherSemesters(user.getId());
		return XMLSerializer.objectToXML(sems);
	}
	
	public List<String> getTeacherSubjects(String idSession, String semester) {
		UserInfo user = getUserIfAuthorized(idSession, UserStatus.TEACHER);
		if(user == null) return null;
		Semester sem = (Semester)
				XMLSerializer.xmlToObject(semester, Semester.class);
		return db.getTeacherSubjects(user.getId(), sem);
	}
	
	public List<String> getTeacherGroups(String idSession, String semester, String subject) {
		UserInfo user = getUserIfAuthorized(idSession, UserStatus.TEACHER);
		if(user == null) return null;
		Semester sem = (Semester)
				XMLSerializer.xmlToObject(semester, Semester.class);
		return db.getTeacherGroups(user.getId(), sem, subject);
	}
	
	public String getCuratorSemesters(String idSession) {
		UserInfo user = getUserIfAuthorized(idSession, UserStatus.CURATOR);
		if(user == null) return null;
		Semesters sems = db.getCuratorSemesters(user.getId());
		return XMLSerializer.objectToXML(sems);
	}
	
	public String getCuratorGroup(String idSession, String semester) {
		UserInfo user = getUserIfAuthorized(idSession, UserStatus.CURATOR);
		if(user == null) return null;
		Semester sem = (Semester)
				XMLSerializer.xmlToObject(semester, Semester.class);
		return db.getCuratorGroup(user.getId(), sem);
	}
	
	public List<String> getGroupSubjects(String idSession, String semester, String group) {
		UserInfo user = /*TODO*/ sessions.get(idSession);
		if(user == null) return null;
		//TODO check rights for group somehow
		Semester sem = (Semester)
				XMLSerializer.xmlToObject(semester, Semester.class);
		return db.getGroupSubjects(group, sem);
	}
	
	public String getDepSemesters(String idSession) {
		UserInfo user = getUserIfAuthorized(idSession, UserStatus.DEPWORKER);
		if(user == null) return null;
		String dep = db.getWorkerDepartment(user.getId());
		Semesters sems = db.getDepSemesters(dep);
		return XMLSerializer.objectToXML(sems);
	}
	
	public List<String> getDepGroups(String idSession, String semester) {
		UserInfo user = getUserIfAuthorized(idSession, UserStatus.DEPWORKER);
		if(user == null) return null;
		String dep = db.getWorkerDepartment(user.getId());
		Semester sem = (Semester)
				XMLSerializer.xmlToObject(semester, Semester.class);
		return db.getDepGroups(dep, sem);
	}
	
	public String getStudentSemesters(String idSession) {
		UserInfo user = getUserIfAuthorized(idSession, UserStatus.STUDENT);
		if(user == null) return null;
		Semesters sems = db.getStudentSemesters(user.getId());
		return XMLSerializer.objectToXML(sems);
	}
	
	public String getSubjectMarks(String idSession, String group, String subject) {
		UserInfo user = /*TODO*/ sessions.get(idSession);
		if(user == null) return null;
		//TODO check user`s rights to get this group marks. HOW???
		GroupSubjectMarks marks = db.getSubjectMarks(group, subject);
		return XMLSerializer.objectToXML(marks);
	}
	
	public String getStageMarks(String idSession, String group, String semester, int stage) {
		UserInfo user = /*TODO*/ sessions.get(idSession);
		if(user == null) return null;
		//TODO check user`s rights to get this group marks. HOW???
		Semester sem = (Semester)
				XMLSerializer.xmlToObject(semester, Semester.class);
		GroupStageMarks marks = db.getStageMarks(group, sem, stage);
		return XMLSerializer.objectToXML(marks);
	}

	public String getStudentMarks(String idSession, String semester) {
		UserInfo user = getUserIfAuthorized(idSession, UserStatus.STUDENT);
		if(user == null) return null;
		Semester sem = (Semester)
				XMLSerializer.xmlToObject(semester, Semester.class);
		StudentSemMarks marks = db.getStudentMarks(user.getId(), sem);
		return XMLSerializer.objectToXML(marks);
	}

}
