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
		//��� ����� login � DB
		//��� �������� ������ ������������
		return db.login(univerID).toString();
	}
	
	public String getSemesters() {
		// ��� ������ � UserSession ����
		UserStatus status = UserStatus.DEPWORKER;
		// ��� ������ � UserSession id ������������
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
		// ��� �������� ����������� � ������ � UserSession ������
		// ������ ������� � user session
		UserStatus status = UserStatus.TEACHER;
		// ������ id ������������ � user session
		
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
		al.add("���-�������");
		al.add("�� ������������������ ������");
		al.add("�����������");
		// ��� ����� getSubjects � DB 
		
		return al;
	}
	
	public List<String> getGroups() {
		// ��� �������� ����������� � ������ � UserSession ������
		List<String> al = new ArrayList<>();
		al.add("��-111");
		al.add("AC-112");
		al.add("AC-101");
		al.add("AC-121");
		// ��� ����� getGroups � DB
		return al;
	}
	
	public String getSubjectMarks() {
		// ��� �������� ����������� � ������ � UserSession ������
		return "";
	}
	
	public String getStageMarks() {
		// ��� �������� ����������� � ������ � UserSession ������
		return "";
	}
	
	public String getStudentMarks() {
		// ��� �������� ����������� � ������ � UserSession �����x
		return "";
	}
	
	public boolean setGroup(int index) {
		// �������� � ������ ������� ����� �������
		// ��� �������� � UserSession ������
		// ��� ������� true ���� ��� ������������ ������ + ����� ����� ������
		return true;
	}
	
	public boolean setStage(int index) {
		// �������� � ������ ������� ����� �������
		// ��� �������� � UserSession �����
		// ��� ������� true ���� ���������� �������
		return true;
	}
	
	public boolean setSemester(int index) {
		// ��� �������� � ������ ������� ��������
		// ��� �������� � UserSession ��������
		// ��� ������� true ���� ���������� �������
		return true;
	}
	
	public boolean setSubject(int index) {
		// ��� �������� ������������ �������
		// ��� �������� � UserSession ��������
		// ��� ������� true ���� ���������� �������
		return true;
	}
}
