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
		//��� ����� login � DB
		//��� �������� ������ ������������
		return db.login(univerID).toString();
	}
	
	public String getSemesters() {
		// ��� ������ � UserSession ����
		UserStatus status = UserStatus.DEPWORKER;
		// ��� ������ � UserSession id ������������
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
		// ��� ���������� ������� id � UserSession
		Semesters semesters = new Semesters(semTable);
		return XMLSerializer.objectToXML(semesters);
	}
	
	public List<String> getSubjects() {
		// ��� �������� ����������� � ������ � UserSession ������
		// ������ ������� � user session
		UserStatus status = UserStatus.TEACHER;
		// ������ id ������������ � user session
		
		String[][] subjTable;
		switch(status) {
		case TEACHER:
			//������ id � UserSession
			int userId = 1000;
			//������ id �������� � UserSession
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
