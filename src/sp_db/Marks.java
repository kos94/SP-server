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
				System.out.println("found " + m.subj + " " + studName);
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

	//TODO delete 
	public void tempInit() throws IOException {
//		ssMarks.add(new StudentSubjMark(7, "���������� ����������", 45, 45, 100));
//		ssMarks.add(new StudentSubjMark(7, "���������� ���������", 50, 46, 99));
//		ssMarks.add(new StudentSubjMark(7, "���", 48, 48, 96));
//		ssMarks.add(new StudentSubjMark(7, "��������������� ��",49, 47, 97));
//		ssMarks.add(new StudentSubjMark(7, "�������� ��", 40, 45, 90));
//		
//		ssMarks.add(new StudentSubjMark(8, "���������� ����������", 35, 50, 80 ));
//		ssMarks.add(new StudentSubjMark(8, "���������� ���������", 40, 40, 85));
//		ssMarks.add(new StudentSubjMark(8, "���", 50, 50, 100));
//		ssMarks.add(new StudentSubjMark(8, "��������������� ��", 45, 46, 92));
//		ssMarks.add(new StudentSubjMark(8, "�������� ��", 40, 40, 80));
//
//		ssMarks.add(new StudentSubjMark(9, "���������� ����������", 45, 40, 40));
//		ssMarks.add(new StudentSubjMark(9, "���������� ���������", 37, 37, 74));
//		ssMarks.add(new StudentSubjMark(9, "���", 40, 40, 90));
//		ssMarks.add(new StudentSubjMark(9, "��������������� ��", 49, 49, 94));
//		ssMarks.add(new StudentSubjMark(9, "�������� ��", 45, 50, 97));
//		
//		ssMarks.add(new StudentSubjMark(10, "���������� ����������", 30, 30, 60));
//		ssMarks.add(new StudentSubjMark(10, "���������� ���������", 40, 41, 81));
//		ssMarks.add(new StudentSubjMark(10, "���", 30, 50, 100));
//		ssMarks.add(new StudentSubjMark(10, "��������������� ��", 31, 40, 60));
//		ssMarks.add(new StudentSubjMark(10, "�������� ��", 40, 49, 89));
//		
//		ssMarks.add(new StudentSubjMark(11, "��������������� ��", 30, 33, 99));
//		ssMarks.add(new StudentSubjMark(12, "��������������� ��", 40, 43, 94));
//		ssMarks.add(new StudentSubjMark(13, "���������� ����������", 34, 43, 94));
//		ssMarks.add(new StudentSubjMark(14, "���������� ����������", 33, 44, 90));
		FileInputStream in = new FileInputStream(DB.DB_PATH + "marks.txt");
	    BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf8"));

	    String strLine;

	    while ((strLine = br.readLine()) != null) {
	    	if(!strLine.equals("")) {
	    		String[] ids = strLine.split(" ");
	    		
	    		while((strLine = br.readLine()) != null) {
	    			if(strLine.equals("")) break;
	    			String subj = strLine.trim();
	    			for(int i=0; i<ids.length; i++) {
		    			int id = Integer.parseInt(ids[i]);
		    			int m1 = (int)(Math.random() * 21 + 30);
		    			int m2 = (int)(Math.random() * 21 + 30);
		    			int m3 = (int)(Math.random() * 41 + 60);
		    			ssMarks.add(new StudentSubjMark(id, subj, m1, m2, m3));
		    		}
	    		}
	    	}
	    }
	    in.close();
	}
	
	//TODO delete
	public void print() {
		System.out.println("================\nMarks");
		for(StudentSubjMark m : ssMarks) {
			m.print();
		}
	}
}
