package sp_server;

import java.util.ArrayList;
import java.util.List;

public class DB {
	public DB() {
		// ��� ������������� ���� ����� � ��
	}
	
	public UserStatus login(int univerID) {
		return UserStatus.TEACHER;
	}
	
	public String getTeacherSemesters(int teachId) {
		// ��� ��������� � �� �� ������ �������
		String s = "<semesters>�������� �������</semesters>";
		return s;
	}
	
	public String getCuratorSemesters(int curId) {
		// ��� ��������� � �� �� ������ ��������
		String s = "<semesters>�������� ��������</semesters>";
		return s;
	}
	
	public String getDepSemesters(int depWorkerId) {
		// ��� ��������� � �� �� ������ ��������� �������
		String s = "<semesters>�������� �������</semesters>";
		return s;
	}
	
	public String getGroupSemesters(int groupId) {
		// ��� ��������� � �� �� ������ ������
		String s = "<semesters>�������� ������</semesters>";
		return s;
	}
	
	public String getStudentSemesters(int studId) {
		// ��� ��������� � �� �� ������ ��������
		String s = "<semesters>�������� ��������</semesters>";
		return s;
	}
	
	public List<String> getTeacherGroups(int teachId, int semId, int subjId) {
		List<String> groups = new ArrayList<>();
		groups.add("teacher group 1");
		groups.add("teacher group 2");
		return groups;
	}
	
	public List<String> getCuratorGroups(int curId, int semId) {
		List<String> groups = new ArrayList<>();
		groups.add("curator group 1");
		groups.add("curator group 2");
		return groups;
	}
	
	public List<String> getDepGroups(int depWorkerId, int semId) {
		List<String> groups = new ArrayList<>();
		groups.add("dep group 1");
		groups.add("dep group 2");
		return groups;
	}
	
	public List<String> getTeacherSubjects(int teachId, int semId) {
		List<String> subjs = new ArrayList<>();
		subjs.add("teacher subj 1");
		subjs.add("teacher subj 2");
		return subjs;
	}
	
	public List<String> getGroupSubjects(int groupId, int semId) {
		List<String> subjs = new ArrayList<>();
		subjs.add("group subj 1");
		subjs.add("group subj 2");
		return subjs;
	}
	
	public String getSubjectMarks(int semId, int groupId, int subjId) {
		String marks = "<subjmarks><studmarks><student>������ �.�.</student>"
				+ "<first>45</first><second>40</second><res>85</res>"
				+ "</studmarks><studmarks><student>���������� �.�.</student>"
				+ "<first>40</first><second>41</second><res>90</res>"
				+ "</studmarks></subjmarks>";
		return marks;
	}
	
	public String getStageMarks(int semId, int groupId, int stageId) {
		String marks ="<stagemarks><subjects><subj>���</subj><subj>���������� ����������</subj>"
				+ "</subjects><studmarks><student>�������� �.�.</student><mark>30</mark>"
				+ "<mark>35</mark></studmarks><studmarks><student>����� A.�.</student>"
				+ "<mark>50</mark><mark>50</mark></studmarks></stagemarks>";
		return marks;
	}
	
	public String getStudentMarks(int semId, int studId) {
		String marks = "<studentmarks><subjmarks><subj>������ ����������</subj><first>30</first>"
				+ "<second>45</second><res>100</res></subjmarks><subjmarks><subj>"
				+ "������ �����������</subj><first>35</first><second>35</second><res>70</res>"
				+ "</subjmarks></studentmarks>";
		return marks;
	}
}
