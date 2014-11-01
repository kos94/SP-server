package sp_db;

import java.util.ArrayList;
import java.util.List;

import sp_entities.GroupStageMarks;
import sp_entities.GroupSubjectMarks;
import sp_entities.Semester;
import sp_entities.StudentSemMarks;

public class Marks {
	private List<StudentSubjMark> marks;
	
	public Marks() {
		marks = new ArrayList<>();
	}
	
	//TODO delete
	public void print() {
		for(StudentSubjMark m : marks) {
			m.print();
		}
	}
	
	public void addMark(StudentSubjMark m) {
		marks.add(m);
	}
	
	//TODO do
	public GroupSubjectMarks getGroupSubjectMarks(String group, String subj) {
		return null;
	}
	
	//TODO do
	public GroupStageMarks getGroupStageMarks(Semester sem, String group, byte stage) {
		return null;
	}
	
	//TODO do
	public StudentSemMarks getStudentMarks(Semester sem, int idStudent) {
		return null;
	}
}
