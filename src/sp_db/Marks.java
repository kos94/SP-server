package sp_db;

import java.util.*;

import sp_entities.GroupStageMarks;
import sp_entities.GroupSubjectMarks;
import sp_entities.Semester;
import sp_entities.StudentSemMarks;

public class Marks {
	private List<StudentSubjMark> marks;
	
	public Marks() {
		marks = new ArrayList<>();
	}
	
	public void addMark(StudentSubjMark m) {
		marks.add(m);
	}
	
	public GroupSubjectMarks getGroupSubjectMarks(Map<Integer, String> students, String subject) {
		GroupSubjectMarks groupMarks = new GroupSubjectMarks();
		for(StudentSubjMark m : marks) {
			String studName = students.get(m.idStudent);
			if(m.subj.equals(subject) && studName != null) {
				groupMarks.addMark(studName, m.marks[0], m.marks[1], m.marks[2]);
			}
		}
		return groupMarks;
	}
	
	public GroupStageMarks getGroupStageMarks(List<String> subjList, Map<Integer, String> students, int stage) {
		GroupStageMarks groupMarks = new GroupStageMarks();
		Map<String, List<Byte>> studMarks = new HashMap<>();
		List<Byte> emptyMarks = new ArrayList<>();
		for(int i=0; i<subjList.size(); i++) {
			emptyMarks.add(null);
		}
		
		for(Map.Entry<Integer, String> s : students.entrySet()) {
			studMarks.put(s.getValue(), new ArrayList<Byte>(emptyMarks));
		}
		
		for(StudentSubjMark m : marks) {
			String studName = students.get(m.idStudent);
			int subjIndex = subjList.indexOf(m.subj);
			if(subjIndex != -1 && studName != null) {
				studMarks.get(studName).set(subjIndex, m.marks[stage-1]);
			}
		}
		
		groupMarks.setSubjects(subjList);
		for(Map.Entry<String, List<Byte>> sm : studMarks.entrySet()) {
			groupMarks.addMark(sm.getKey(), sm.getValue());
		}
		return groupMarks;
	}
	
	//TODO do
	public StudentSemMarks getStudentMarks(Semester sem, int idStudent) {
		return null;
	}

	//TODO delete 
	public void tempInit() {
		marks.add(new StudentSubjMark(7, "���������� ����������", 45, 45, 100));
		marks.add(new StudentSubjMark(7, "���������� ���������", 50, 46, 99));
		marks.add(new StudentSubjMark(7, "���", 48, 48, 96));
		marks.add(new StudentSubjMark(7, "��������������� ��",49, 47, 97));
		marks.add(new StudentSubjMark(7, "�������� ��", 40, 45, 90));
		
		marks.add(new StudentSubjMark(8, "���������� ����������", 35, 50, 80 ));
		marks.add(new StudentSubjMark(8, "���������� ���������", 40, 40, 85));
		marks.add(new StudentSubjMark(8, "���", 50, 50, 100));
		marks.add(new StudentSubjMark(8, "��������������� ��", 45, 46, 92));
		marks.add(new StudentSubjMark(8, "�������� ��", 40, 40, 80));

		marks.add(new StudentSubjMark(9, "���������� ����������", 45, 40, 40));
		marks.add(new StudentSubjMark(9, "���������� ���������", 37, 37, 74));
		marks.add(new StudentSubjMark(9, "���", 40, 40, 90));
		marks.add(new StudentSubjMark(9, "��������������� ��", 49, 49, 94));
		marks.add(new StudentSubjMark(9, "�������� ��", 45, 50, 97));
		
		marks.add(new StudentSubjMark(10, "���������� ����������", 30, 30, 60));
		marks.add(new StudentSubjMark(10, "���������� ���������", 40, 41, 81));
		marks.add(new StudentSubjMark(10, "���", 30, 50, 100));
		marks.add(new StudentSubjMark(10, "��������������� ��", 31, 40, 60));
		marks.add(new StudentSubjMark(10, "�������� ��", 40, 49, 89));
		
		marks.add(new StudentSubjMark(11, "��������������� ��", 30, 33, 99));
		marks.add(new StudentSubjMark(12, "��������������� ��", 40, 43, 94));
		marks.add(new StudentSubjMark(13, "���������� ����������", 34, 43, 94));
		marks.add(new StudentSubjMark(14, "���������� ����������", 33, 44, 90));
	}
	
	//TODO delete
	public void print() {
		System.out.println("================\nMarks");
		for(StudentSubjMark m : marks) {
			m.print();
		}
	}
}
