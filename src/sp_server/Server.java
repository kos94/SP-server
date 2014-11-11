package sp_server;

import java.util.*;

import javax.jws.WebService;

import sp_db.*;
import sp_entities.*;

@WebService
public class Server {
	private DB db;
	private Users sessions;

	public Server() {
		db = new DB();
		sessions = new Users();
//		tempTeacherScenario();
//		tempCuratorScenario();
//		tempDepWorkerScenario();
		tempStudentScenario();
	}

	private void tempTeacherScenario() {
		int idUser = 2;

		System.out.println("--- Login");
		System.out.println( login(idUser, "bbb") );

		System.out.println("--- Get teacher semesters: ");
		String sems = getTeacherSemesters(idUser);
		System.out.println(sems);

		String chosenSem = XMLSerializer.objectToXML(new Semester(2, 2012));

		System.out.println("--- Get teacher subjects: ");
		List<String> subjects = getTeacherSubjects(idUser, chosenSem);
		for (String s : subjects) {
			System.out.println(s);
		}

		String chosenSubj = subjects.get(0);

		System.out.println("--- Get teacher groups: ");
		List<String> groups = getTeacherGroups(idUser, chosenSem, chosenSubj);
		for (String g : groups) {
			System.out.println(g);
		}

		String chosenGroup = groups.get(0);
		
		System.out.println("--- Get subject marks: ");
		String marks = getSubjectMarks(idUser, chosenGroup, chosenSubj);
		System.out.println(marks);
		GroupSubjectMarks gsm = (GroupSubjectMarks)XMLSerializer
				.xmlToObject(marks, GroupSubjectMarks.class);
		gsm.print();
	}

	private void tempCuratorScenario() {
		int idUser = 3;
		
		System.out.println("--- Login");
		System.out.println( login(idUser, "ccc") );
		
		System.out.println("--- Get curator semesters: ");
		String sems = getCuratorSemesters(idUser);
		System.out.println(sems);
		
		String chosenSem = XMLSerializer.objectToXML(new Semester(2, 2012));
		
		System.out.println("--- Get curator groups: ");
		String group = getCuratorGroup(idUser, chosenSem);
		System.out.println(group);

		/* <var1> */
		int chosenStage = 3;
		
		System.out.println("--- Get stage marks: ");
		String stageMarks = getStageMarks(idUser, group, chosenSem, chosenStage);
		System.out.println(stageMarks);
		GroupStageMarks gstm =  (GroupStageMarks)XMLSerializer
				.xmlToObject(stageMarks, GroupStageMarks.class);
		gstm.print();
		/* </var1> */
		
//		/* <var2> */
//		System.out.println("--- Get group subjects: ");
//		List<String> subjects = getGroupSubjects(idUser, chosenSem, group);
//		for (String s : subjects) {
//			System.out.println(s);
//		}
//		
//		String chosenSubj = subjects.get(0);
//		
//		System.out.println("--- Get subject marks: ");
//		String subjMarks = getSubjectMarks(idUser, group, chosenSubj);
//		System.out.println(subjMarks);
//		GroupSubjectMarks gsm = (GroupSubjectMarks)XMLSerializer
//				.xmlToObject(subjMarks, GroupSubjectMarks.class);
//		gsm.print();
//		/* </var2> */
	}
	
	private void tempDepWorkerScenario() {
		int idUser = 5;
		
		System.out.println("--- Login");
		System.out.println( login(idUser, "eee") );

		System.out.println( getDepWorkerDepartment(idUser) );
		
		System.out.println("--- Get dep worker semesters: ");
		String sems = getDepSemesters(idUser);
		System.out.println(sems);
		
		Semester sem = ((Semesters)XMLSerializer.xmlToObject(sems, Semesters.class))
				.getSemesters().get(2);
		String chosenSem = XMLSerializer.objectToXML(sem);

		System.out.println("--- Get dep worker groups: ");
		List<String> groups = getDepGroups(idUser, chosenSem);
		for (String g : groups) {
			System.out.println(g);
		}
		
		String chosenGroup = groups.get(1);

		/* <var1> */
		int chosenStage = 3;
		
		System.out.println("--- Get stage marks: ");
		String stageMarks = getStageMarks(idUser, chosenGroup, chosenSem, chosenStage);
		System.out.println(stageMarks);
		GroupStageMarks gstm =  (GroupStageMarks)XMLSerializer
				.xmlToObject(stageMarks, GroupStageMarks.class);
		gstm.print();
		/* </var1> */
		
//		/* <var2> */
//		System.out.println("--- Get group subjects: ");
//		List<String> subjects = getGroupSubjects(idUser, chosenSem, chosenGroup);
//		for (String s : subjects) {
//			System.out.println(s);
//		}
//		
//		String chosenSubj = subjects.get(0);
//		
//		System.out.println("--- Get subject marks: ");
//		String subjMarks = getSubjectMarks(idUser, chosenGroup, chosenSubj);
//		System.out.println(subjMarks);
//		GroupSubjectMarks gsm = (GroupSubjectMarks)XMLSerializer
//				.xmlToObject(subjMarks, GroupSubjectMarks.class);
//		gsm.print();
//		/* </var2> */
	}
	
	private void tempStudentScenario() {
		int idUser = 7;
		
		System.out.println("--- Login");
		System.out.println( login(idUser, "ggg") );
		
		System.out.println("--- Get student semesters: ");
		String sems = getStudentSemesters(idUser);
		System.out.println(sems);
		
		Semester sem = ((Semesters)XMLSerializer.xmlToObject(sems, Semesters.class))
				.getSemesters().get(0);
		String chosenSem = XMLSerializer.objectToXML(sem);
		
		String marks = getStudentMarks(idUser, chosenSem);
		System.out.println(marks);
		StudentSemMarks ssm = (StudentSemMarks) XMLSerializer
				.xmlToObject(marks, StudentSemMarks.class);
		ssm.print();
	}
	
	public String login(int univerID, String password) {
		User user = sessions.getUser(univerID);
		if (user == null) {
			user = db.login(univerID, password);
			if (user == null) return null;
			sessions.addUser(univerID, user);
		}
		User userNoPass = new User(user.getName(), user.getStatus());
		return XMLSerializer.objectToXML(userNoPass);
	}

	private User getUserIfAuthorized(int idUser, UserStatus status) {
		User user = sessions.getUser(idUser);
		if(user == null || user.getStatus() != status) 
			return null;
		return user;
	}
	
	public String getTeacherSemesters(int idUser) {
		User user = getUserIfAuthorized(idUser, UserStatus.TEACHER);
		if(user == null) return null;
		Semesters sems = db.getTeacherSemesters(idUser);
		return XMLSerializer.objectToXML(sems);
	}
	
	public List<String> getTeacherSubjects(int idUser, String semester) {
		User user = getUserIfAuthorized(idUser, UserStatus.TEACHER);
		if(user == null) return null;
		Semester sem = (Semester)
				XMLSerializer.xmlToObject(semester, Semester.class);
		return db.getTeacherSubjects(idUser, sem);
	}
	
	public List<String> getTeacherGroups(int idUser, String semester, String subject) {
		User user = getUserIfAuthorized(idUser, UserStatus.TEACHER);
		if(user == null) return null;
		Semester sem = (Semester)
				XMLSerializer.xmlToObject(semester, Semester.class);
		return db.getTeacherGroups(idUser, sem, subject);
	}
	
	public String getCuratorSemesters(int idUser) {
		User user = getUserIfAuthorized(idUser, UserStatus.CURATOR);
		if(user == null) return null;
		Semesters sems = db.getCuratorSemesters(idUser);
		return XMLSerializer.objectToXML(sems);
	}
	
	public String getCuratorGroup(int idUser, String semester) {
		User user = getUserIfAuthorized(idUser, UserStatus.CURATOR);
		if(user == null) return null;
		Semester sem = (Semester)
				XMLSerializer.xmlToObject(semester, Semester.class);
		return db.getCuratorGroup(idUser, sem);
	}
	
	public List<String> getGroupSubjects(int idUser, String semester, String group) {
		User user = sessions.getUser(idUser);
		if(user == null) return null;
		//TODO check rights for group somehow
		Semester sem = (Semester)
				XMLSerializer.xmlToObject(semester, Semester.class);
		return db.getGroupSubjects(group, sem);
	}
	
	public String getDepWorkerDepartment(int idUser) {
		User user = getUserIfAuthorized(idUser, UserStatus.DEPWORKER);
		if(user == null) return null;
		return db.getWorkerDepartment(idUser);
	}
	
	public String getDepSemesters(int idUser) {
		User user = getUserIfAuthorized(idUser, UserStatus.DEPWORKER);
		if(user == null) return null;
		String dep = db.getWorkerDepartment(idUser);
		Semesters sems = db.getDepSemesters(dep);
		return XMLSerializer.objectToXML(sems);
	}
	
	public List<String> getDepGroups(int idUser, String semester) {
		User user = getUserIfAuthorized(idUser, UserStatus.DEPWORKER);
		if(user == null) return null;
		String dep = db.getWorkerDepartment(idUser);
		Semester sem = (Semester)
				XMLSerializer.xmlToObject(semester, Semester.class);
		return db.getDepGroups(dep, sem);
	}
	
	public String getStudentSemesters(int idUser) {
		User user = getUserIfAuthorized(idUser, UserStatus.STUDENT);
		if(user == null) return null;
		Semesters sems = db.getStudentSemesters(idUser);
		return XMLSerializer.objectToXML(sems);
	}
	
	public String getSubjectMarks(int idUser, String group, String subject) {
		User user = sessions.getUser(idUser);
		if(user == null) return "";
		//TODO check user`s rights to get this group marks. HOW???
		GroupSubjectMarks marks = db.getSubjectMarks(group, subject);
		return XMLSerializer.objectToXML(marks);
	}
	
	public String getStageMarks(int idUser, String group, String semester, int stage) {
		User user = sessions.getUser(idUser);
		if(user == null) return "";
		//TODO check user`s rights to get this group marks. HOW???
		Semester sem = (Semester)
				XMLSerializer.xmlToObject(semester, Semester.class);
		GroupStageMarks marks = db.getStageMarks(group, sem, stage);
		return XMLSerializer.objectToXML(marks);
	}

	public String getStudentMarks(int idUser, String semester) {
		User user = getUserIfAuthorized(idUser, UserStatus.STUDENT);
		if(user == null) return null;
		Semester sem = (Semester)
				XMLSerializer.xmlToObject(semester, Semester.class);
		StudentSemMarks marks = db.getStudentMarks(idUser, sem);
		return XMLSerializer.objectToXML(marks);
	}

}
