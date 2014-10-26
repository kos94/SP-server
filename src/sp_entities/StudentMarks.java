package sp_entities;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
class StudentMarks {
	public String student;
	@XmlElement(name="mark")
	public ArrayList<Integer> marks;
	
	public StudentMarks() {
		marks = new ArrayList<>();
	}
	
	public StudentMarks(String student, ArrayList<Integer> marks) {
		this.student = student;
		this.marks = marks;
	}
}
