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
		
		System.out.println("Login");
		login(idTeacher, "aaa");
		
		System.out.println("Get teacher semesters: ");
		String sems = getSemesters(idTeacher);
		System.out.println(sems);
		
		Semester chosenSem = new Semester(1, 2014);
		
		List<String> subjects = db.getTeacherSubjects(idTeacher, chosenSem);
		System.out.println("Get teacher subjects: ");
		for(String s : subjects) {
			System.out.println(s);
		}
		String chosenSubj = subjects.get(0);
		
		System.out.println("Get teacher groups: ");
		List<String> groups = db.getTeacherGroups(idTeacher, chosenSem, chosenSubj);
		for(String g : groups) {
			System.out.println(g);
		}
		String chosenGroup = groups.get(1);
		
		System.out.println("Get subject marks: ");
		GroupSubjectMarks gsm = db.getSubjectMarks(chosenGroup, chosenSubj);
		gsm.print();
	}

	
	public String login(int univerID, String password) {
		User user;
		UserSession session = sessions.get(univerID);
		if(session != null) {
			user = session.getUserData();
		} else {
			user = db.login(univerID, password);
			if(user == null) return "";
			sessions.put(univerID, new UserSession(user));
		}
		return XMLSerializer.objectToXML(user);
	}
	
	public String getSemesters(int id) {
		UserSession session = sessions.get(id);
		if(session == null) return "";
		UserStatus status = session.getUserData().getStatus();
		int userId = id;
		
		Semesters sems = new Semesters();
		System.out.println("STATUS: " + status);
		switch (status) {
		case TEACHER:
			sems = db.getTeacherSemesters(userId);
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
		sems.print();
		return XMLSerializer.objectToXML(sems);
	}
	
	public List<String> getSubjects(int id) {
		UserSession session = sessions.get(id);
		if(session == null) return null;
		UserStatus status = session.getUserData().getStatus();
		int userId = id;
		
		switch(status) {
		case TEACHER:
			break;
		case CURATOR:
			break;
		case DEPWORKER:
			break;
		default:
			return null;
		}
		List<String> al = new ArrayList<>();
		al.add("Веб-сервисы");
		al.add("ПО автоматизированных систем");
		al.add("Политология");
		// тут вызов getSubjects у DB 
		
		return al;
	}
	
	public List<String> getGroups() {
		// тут проверка авторизации и взятие с UserSession данных
		List<String> al = new ArrayList<>();
		al.add("АС-111");
		al.add("AC-112");
		al.add("AC-101");
		al.add("AC-121");
		// тут вызов getGroups у DB
		return al;
	}
	
	public String getSubjectMarks() {
		// тут проверка авторизации и взятие с UserSession данных
		return "";
	}
	
	public String getStageMarks() {
		// тут проверка авторизации и взятие с UserSession данных
		return "";
	}
	
	public String getStudentMarks() {
		// тут проверка авторизации и взятие с UserSession данныx
		return "";
	}
	
	public boolean setGroup(int index) {
		// проверка в сессии наличия этого индекса
		// тут внесение в UserSession группы
		// тут возврат true если был предоставлен список + можно такую группу
		return true;
	}
	
	public boolean setStage(int index) {
		// проверка в сессии наличия этого индекса
		// тут внесение в UserSession этапа
		// тут возврат true если правильный вариант
		return true;
	}
	
	public boolean setSemester(int index) {
		// тут проверка в сессии наличия семестра
		// тут внесение в UserSession семестра
		// тут возврат true если правильный вариант
		return true;
	}
	
	public boolean setSubject(int index) {
		// тут проверка правильности индекса
		// тут внесение в UserSession предмета
		// тут возврат true если правильный вариант
		return true;
	}
}
