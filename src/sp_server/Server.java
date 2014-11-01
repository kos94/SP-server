package sp_server;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import sp_db.DB;
import sp_entities.Semesters;
import sp_entities.XMLSerializer;
import sp_entities.UserStatus;

@WebService
public class Server {
	private DB db;
	
	public Server() {
		db = new DB();
	}
	
	public String login(int univerID) {
		//тут вызов login у DB
		//тут создание сессии пользователя
		return db.login(univerID).toString();
	}
	
	public String getSemesters() {
		// тут взятие с UserSession роли
		UserStatus status = UserStatus.DEPWORKER;
		// тут взятие с UserSession id пользователя
		int userId = 1;
		String[][] semTable;
		
		switch (status) {
		case TEACHER:
			semTable = db.getTeacherSemesters(userId);
			break;
		case CURATOR:
			semTable = db.getCuratorSemesters(userId);
			break;
		case DEPWORKER:
			semTable = db.getDepSemesters(userId);
			break;
		case STUDENT:
			semTable = db.getStudentSemesters(userId);
			break;
		case NONE:
		default:
			return "";
		}
		// тут сохранение массива id в UserSession
		Semesters semesters = new Semesters(semTable);
		return XMLSerializer.objectToXML(semesters);
	}
	
	public List<String> getSubjects() {
		// тут проверка авторизации и взятие с UserSession данных
		// взятие статуса с user session
		UserStatus status = UserStatus.TEACHER;
		// взятие id пользователя с user session
		
		String[][] subjTable;
		switch(status) {
		case TEACHER:
			//взятие id с UserSession
			int userId = 1000;
			//взятие id семестра с UserSession
			int semId = 10;
			subjTable = db.getTeacherSubjects(userId, semId);
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
