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
		//��� ����� login � DB
		return db.login(univerID).toString();
	}
	
	public String getSemesters() {
		String s = "<semesters><sem index=\"1\" startYear=�2013�>"
				+ "<sem index=�2� startYear=�2014�></semesters>";
		// ��� ������ � UserSession ����
		UserStatus status = UserStatus.TEACHER;
		// ��� ������ � UserSession id ������������
		int userId = 1;
		String semesters;
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ����� � session ��������� semId | index. ������ ������� �� ����� � ��!!!!
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
		// ��� ����� getSemesters � DB
		return s;
	}
	
	public List<String> getSubjects() {
		// ��� �������� ����������� � ������ � UserSession ������
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
		return db.getSubjectMarks(0, 0, 0);
	}
	
	public String getStageMarks() {
		// ��� �������� ����������� � ������ � UserSession ������
		return db.getStageMarks(0, 0, 0);
	}
	
	public String getStudentMarks() {
		// ��� �������� ����������� � ������ � UserSession �����x
		return db.getStudentMarks(0, 0);
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
