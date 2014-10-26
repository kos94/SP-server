package sp_entities;
import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="stageMarks")
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupStageMarks {
	@XmlElementWrapper
	@XmlElement(name="subj")
	private ArrayList<String> subjects;
	private ArrayList<StudentMarks> studMarks;
	
	public GroupStageMarks() {
		subjects = new ArrayList<>();
		studMarks = new ArrayList<>();
	}

	public void addSubject(String subj) {
		subjects.add(subj);
	}
	
	public void addMark(String student, ArrayList<Integer> marks) {
		studMarks.add(new StudentMarks(student, marks));
	}
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! DELETE IN FUTURE
	public void print() {
		for(String subj : subjects) {
			System.out.println(subj);
		}
		System.out.println("==========");
		
		for(StudentMarks ssm : studMarks) {
			System.out.println(ssm.student);
			for(Integer m : ssm.marks) {
				System.out.print(m + " ");
			}
			System.out.println();
		}
	}
}