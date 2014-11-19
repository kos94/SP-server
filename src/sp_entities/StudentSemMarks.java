package sp_entities;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;

@XmlRootElement(name="studentMarks")
@XmlAccessorType(XmlAccessType.FIELD)
public class StudentSemMarks implements IMarks {
	private ArrayList<SubjectMarks> subjMarks;
	
	public StudentSemMarks() {
		subjMarks = new ArrayList<>();
	}
	
	public void addMark(String subj, int m1, int m2, int m3) {
		ArrayList<Integer> l = new ArrayList<>();
		l.add(m1); l.add(m2); l.add(m3); 
		subjMarks.add(new SubjectMarks(subj, l));
	}
	
	public int getSubjectsNumber() { 
		return subjMarks.size();
	}
	
	public SubjectMarks getSubjectMarks(int i) {
		if(i < 0 || i >= subjMarks.size()) return null;
		return subjMarks.get(i);
	}
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! DELETE IN FUTURE
	public void print() {
		for(SubjectMarks ssm : subjMarks) {
			System.out.println(ssm.subj);
			for(Integer m : ssm.marks) {
				System.out.print(" " + m);
			}
			System.out.println();
		}
	}
}