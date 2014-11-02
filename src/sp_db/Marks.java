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
		marks.add(new StudentSubjMark(7, "Дискретная математика", 45, 45, 100));
		marks.add(new StudentSubjMark(7, "Дискретные структуры", 50, 46, 99));
		marks.add(new StudentSubjMark(7, "ООП", 48, 48, 96));
		marks.add(new StudentSubjMark(7, "Конструирование ПО",49, 47, 97));
		marks.add(new StudentSubjMark(7, "Качество ПО", 40, 45, 90));
		
		marks.add(new StudentSubjMark(8, "Дискретная математика", 35, 50, 80 ));
		marks.add(new StudentSubjMark(8, "Дискретные структуры", 40, 40, 85));
		marks.add(new StudentSubjMark(8, "ООП", 50, 50, 100));
		marks.add(new StudentSubjMark(8, "Конструирование ПО", 45, 46, 92));
		marks.add(new StudentSubjMark(8, "Качество ПО", 40, 40, 80));

		marks.add(new StudentSubjMark(9, "Дискретная математика", 45, 40, 40));
		marks.add(new StudentSubjMark(9, "Дискретные структуры", 37, 37, 74));
		marks.add(new StudentSubjMark(9, "ООП", 40, 40, 90));
		marks.add(new StudentSubjMark(9, "Конструирование ПО", 49, 49, 94));
		marks.add(new StudentSubjMark(9, "Качество ПО", 45, 50, 97));
		
		marks.add(new StudentSubjMark(10, "Дискретная математика", 30, 30, 60));
		marks.add(new StudentSubjMark(10, "Дискретные структуры", 40, 41, 81));
		marks.add(new StudentSubjMark(10, "ООП", 30, 50, 100));
		marks.add(new StudentSubjMark(10, "Конструирование ПО", 31, 40, 60));
		marks.add(new StudentSubjMark(10, "Качество ПО", 40, 49, 89));
		
		marks.add(new StudentSubjMark(11, "Конструирование ПО", 30, 33, 99));
		marks.add(new StudentSubjMark(12, "Конструирование ПО", 40, 43, 94));
		marks.add(new StudentSubjMark(13, "Дискретная математика", 34, 43, 94));
		marks.add(new StudentSubjMark(14, "Дискретная математика", 33, 44, 90));
	}
	
	//TODO delete
	public void print() {
		System.out.println("================\nMarks");
		for(StudentSubjMark m : marks) {
			m.print();
		}
	}
}
