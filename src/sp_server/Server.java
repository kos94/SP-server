package sp_server;

import java.util.*;

import javax.jws.WebService;

import sp_db.*;
import sp_entities.*;

@WebService
public class Server {
	private DB db;
	private Map<Integer, UserSession> sessions;

	public Server() {
		db = new DB();
		sessions = new HashMap<>();
		tempTeacherScenario();

	}

	private void tempTeacherScenario() {
		int idTeacher = 1;

		System.out.println("--- Login");
		login(idTeacher, "aaa");

		System.out.println("--- Get teacher semesters: ");
		String sems = getSemesters(idTeacher);
		// System.out.println(sems);

		System.out.println("--- Set semester: ");
		Semester chosenSem = new Semester(1, 2014);
		setSemester(idTeacher, XMLSerializer.objectToXML(chosenSem));

		System.out.println("--- Get teacher subjects: ");
		List<String> subjects = getSubjects(idTeacher);
		for (String s : subjects) {
			System.out.println(s);
		}

		System.out.println("--- Set subject: ");
		String chosenSubj = subjects.get(1);
		setSubject(idTeacher, chosenSubj);

		System.out.println("--- Get teacher groups: ");
		List<String> groups = getGroups(idTeacher);
		for (String g : groups) {
			System.out.println(g);
		}

		System.out.println("--- Set group: ");
		String chosenGroup = groups.get(0);
		setGroup(idTeacher, chosenGroup);
		
		System.out.println("--- Get subject marks: ");
		String marks = getSubjectMarks(idTeacher);
		System.out.println(marks);
	}

	public String login(int univerID, String password) {
		User user;
		UserSession session = sessions.get(univerID);
		if (session != null) {
			user = session.getUserData();
		} else {
			user = db.login(univerID, password);
			if (user == null)
				return "";
			sessions.put(univerID, new UserSession(user));
		}
		return XMLSerializer.objectToXML(user);
	}

	public String getSemesters(int idUser) {
		UserSession session = sessions.get(idUser);
		if (session == null)
			return "";
		Semesters sems = null;

		UserStatus status = session.getUserData().getStatus();
		System.out.println("STATUS: " + status);
		switch (status) {
		case TEACHER:
			sems = db.getTeacherSemesters(idUser);
			break;
		case CURATOR:
			break;
		case DEPWORKER:
			break;
		case STUDENT:
			break;
		case NONE:
		default:
			return "";
		}
		System.out.println("GET SEMESTERS RESULT");
		if (sems == null)
			return "";
		sems.print();
		return XMLSerializer.objectToXML(sems);
	}

	public List<String> getSubjects(int idUser) {
		UserSession session = sessions.get(idUser);
		if (session == null)
			return null;
		List<String> subjects = null;

		UserStatus status = session.getUserData().getStatus();
		switch (status) {
		case TEACHER:
			subjects = db.getTeacherSubjects(idUser, session.getSemester());
			break;
		case CURATOR:
			break;
		case DEPWORKER:
			break;
		default:
			return null;
		}
		return subjects;
	}

	public List<String> getGroups(int idUser) {
		UserSession session = sessions.get(idUser);
		if (session == null) return null;
		List<String> groups = null;

		UserStatus status = session.getUserData().getStatus();
		switch (status) {
		case TEACHER:
			groups = db.getTeacherGroups(idUser, session.getSemester(),
					session.getSubject());
			break;
		case CURATOR:
			break;
		case DEPWORKER:
			break;
		case NONE:
			break;
		case STUDENT:
		default:
			return null;
		}
		return groups;
	}

	public String getSubjectMarks(int idUser) {
		UserSession session = sessions.get(idUser);
		if (session == null) return null;
		String subj = session.getSubject();
		String group = session.getGroup();
		// need to check rights? 
		GroupSubjectMarks marks = db.getSubjectMarks(group, subj);
		System.out.println(idUser + " GET SUBJECT MARKS: ");
		marks.print();
		return XMLSerializer.objectToXML(marks);
	}

	public String getStageMarks() {
		// тут проверка авторизации и взятие с UserSession данных
		return "";
	}

	public String getStudentMarks() {
		// тут проверка авторизации и взятие с UserSession данныx
		return "";
	}

	public boolean setGroup(int idUser, String group) {
		UserSession session = sessions.get(idUser);
		if (session == null) return false;
		// how to check if group value correct and available?
		session.setGroup(group);
		System.out.println(idUser + " SET GROUP " + group);
		return true;
	}

	public boolean setStage(int index) {
		// проверка в сессии наличия этого индекса
		// тут внесение в UserSession этапа
		// тут возврат true если правильный вариант
		return true;
	}

	public boolean setSemester(int idUser, String semester) {
		UserSession session = sessions.get(idUser);
		if (session == null) return false;
		// how to check if semester value correct and available?
		Semester sem = (Semester) XMLSerializer.xmlToObject(semester,
				Semester.class);
		System.out.println(idUser + " SET SEMESTER " + sem);
		session.setSemester(sem);
		return true;
	}

	public boolean setSubject(int idUser, String subj) {
		UserSession session = sessions.get(idUser);
		if (session == null) return false;
		// how to check if subject value correct and available?
		System.out.println(idUser + " SET SUBJECT " + subj);
		session.setSubject(subj);
		return true;
	}
}
