package sp_db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.xml.bind.annotation.*;

import sp_entities.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Marks {
	private List<StudentSubjMark> ssMarks;
	
	public Marks() {
		ssMarks = new ArrayList<>();
	}
	
	public void addMark(StudentSubjMark m) {
		ssMarks.add(m);
	}
	
	public GroupSubjectMarks getGroupSubjectMarks(Map<Integer, String> students, String subject) {
		GroupSubjectMarks groupMarks = new GroupSubjectMarks();
		for(StudentSubjMark m : ssMarks) {
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
		
		for(StudentSubjMark m : ssMarks) {
			String studName = students.get(m.idStudent);
			int subjIndex = subjList.indexOf(m.subj);
			if(subjIndex != -1 && studName != null) {
				studMarks.get(studName).set(subjIndex, (byte)m.marks[stage-1]);
			}
		}
		
		groupMarks.setSubjects(subjList);
		for(Map.Entry<String, List<Byte>> sm : studMarks.entrySet()) {
			groupMarks.addMark(sm.getKey(), sm.getValue());
		}
		return groupMarks;
	}
	
	public StudentSemMarks getStudentMarks(int idStudent, Set<String> subjects) {
		StudentSemMarks studentMarks = new StudentSemMarks();
		for(StudentSubjMark m : ssMarks) {
			if(m.idStudent == idStudent && subjects.contains(m.subj)) {
				studentMarks.addMark(m.subj, m.marks[0], m.marks[1], m.marks[2]);
			}
		}
		return studentMarks;
	}
}
