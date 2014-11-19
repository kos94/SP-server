package sp_entities;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;

public class SubjectMarks {
	public String subj;
	@XmlElement(name="mark")
	public ArrayList<Integer> marks;
	
	public SubjectMarks() {
		marks = new ArrayList<>();
	}
	
	public SubjectMarks(String subject, ArrayList<Integer> marks) {
		subj = subject;
		this.marks = marks;
	}
}