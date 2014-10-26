package sp_entities;
import java.util.ArrayList;
import javax.xml.bind.annotation.*;


@XmlRootElement(name="subjMarks")
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupSubjectMarks {
	private ArrayList<StudentMarks> studMarks;
	
	public GroupSubjectMarks() {
		studMarks = new ArrayList<>();
	}

	public void addMark(String student, int m1, int m2, int m3) {
		ArrayList<Integer> l = new ArrayList<>();
		l.add(m1); l.add(m2); l.add(m3); 
		studMarks.add(new StudentMarks(student, l));
	}
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! DELETE IN FUTURE
	public void print() {
		for(StudentMarks ssm : studMarks) {
			System.out.println(ssm.student);
			for(Integer m : ssm.marks) {
				System.out.print(m + " ");
			}
			System.out.println("");
		}
	}
}