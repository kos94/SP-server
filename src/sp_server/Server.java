package sp_server;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import sp_db.DB;
import sp_entities.GroupSubjectMarks;
import sp_entities.Semester;
import sp_entities.Semesters;
import sp_entities.XMLSerializer;
import sp_entities.UserStatus;

@WebService
public class Server {
	private DB db;
	
	public Server() {
		db = new DB();
		int idTeacher = 1;
		System.out.println("Get teacher semesters: ");
		Semesters sems = db.getTeacherSemesters(idTeacher);
		sems.print();
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
		Semesters sems;
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
		return "";
//		if(sems == null) return "";
//		return XMLSerializer.objectToXML(sems);
	}
	
	public List<String> getSubjects() {
		// тут проверка авторизации и взятие с UserSession данных
		// взятие статуса с user session
		UserStatus status = UserStatus.TEACHER;
		// взятие id пользователя с user session
		
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
