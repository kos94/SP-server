package sp_entities;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;


@XmlRootElement(name="subjMarks")
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupSubjectMarks implements IMarks {
	private ArrayList<StudentMarks> studMarks;
	
	public GroupSubjectMarks() {
		studMarks = new ArrayList<>();
	}

	public void addMark(String student, int m1, int m2, int m3) {
		ArrayList<Byte> l = new ArrayList<>();
		l.add((byte)m1); l.add((byte)m2); l.add((byte)m3); 
		studMarks.add(new StudentMarks(student, l));
	}
	
	public int getMarksNumber() { return studMarks.size(); }
	public StudentMarks getStudentMark(int i) { 
		if(i < 0 || i >= studMarks.size()) return null;
		return studMarks.get(i);
	}
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! DELETE IN FUTURE
	public void print() {
		for(StudentMarks ssm : studMarks) {
			System.out.println(ssm.student);
			for(Byte m : ssm.marks) {
				System.out.print(m + " ");
			}
			System.out.println("");
		}
	}
}