package sp_entities;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="stageMarks")
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupStageMarks {
	@XmlElementWrapper
	@XmlElement(name="subj")
	private List<String> subjects;
	private List<StudentMarks> studMarks;
	
	public GroupStageMarks() {
		subjects = new ArrayList<>();
		studMarks = new ArrayList<>();
	}

	public void addSubject(String subj) {
		subjects.add(subj);
	}
	
	public void addMark(String student, List<Byte> marks) {
		studMarks.add(new StudentMarks(student, marks));
	}
	
	public void setSubjects(List<String> subjs) {
		subjects = subjs;
	}
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! DELETE IN FUTURE
	public void print() {
		for(String subj : subjects) {
			System.out.println(subj);
		}
		System.out.println("==========");
		
		for(StudentMarks ssm : studMarks) {
			System.out.println(ssm.student);
			for(Byte m : ssm.marks) {
				System.out.print(m + " ");
			}
			System.out.println();
		}
	}
	public int getStudentsNumber() { return studMarks.size(); }
	public List<String> getSubjects() { return subjects; }
	public StudentMarks getStudentMark(int i) { 
		if(i < 0 || i >= studMarks.size()) return null;
		return studMarks.get(i);
	}
}