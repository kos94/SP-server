package sp_server;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;

enum UserStatus { 
	NONE ("NONE"), TEACHER("TEACHER"), CURATOR("CURATOR"),
	DEPWORKER("DEPWORKER"), STUDENT("STUDENT");
	private String name;
	private UserStatus(String enName) {
		name = enName;
	}
	@Override
	public String toString() {
		return name;
	}
};

@WebService
public class Server {
	private DB db;
	
	public Server() {
		db = new DB();
	}
	
	public String login(int univerID) {
		//тут вызов login у DB
		return db.login(univerID).toString();
	}
	
	public String getSemesters() {
		String s = "<semesters><sem index=\"1\" startYear=”2013”>"
				+ "<sem index=”2” startYear=”2014”></semesters>";
		// тут взятие с UserSession роли
		UserStatus status = UserStatus.TEACHER;
		// тут взятие с UserSession id пользователя
		int userId = 1;
		String semesters;
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! нужно в session сохранить semId | index. значит возврат не такой с БД!!!!
		switch (status) {
		case TEACHER:
			semesters = db.getTeacherSemesters(userId);
			break;
		case CURATOR:
			semesters = db.getCuratorSemesters(userId);
			break;
		case DEPWORKER:
			semesters = db.getDepSemesters(userId);
			break;
		case STUDENT:
			semesters = db.getStudentSemesters(userId);
			break;
		case NONE:
		default:
			semesters = "";
		}
		// тут вызов getSemesters у DB
		return s;
	}
	
	public List<String> getSubjects() {
		// тут проверка авторизации и взятие с UserSession данных
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
		return db.getSubjectMarks(0, 0, 0);
	}
	
	public String getStageMarks() {
		// тут проверка авторизации и взятие с UserSession данных
		return db.getStageMarks(0, 0, 0);
	}
	
	public String getStudentMarks() {
		// тут проверка авторизации и взятие с UserSession данныx
		return db.getStudentMarks(0, 0);
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
