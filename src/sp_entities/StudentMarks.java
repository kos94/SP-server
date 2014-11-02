package sp_entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
class StudentMarks {
	public String student;
	@XmlElement(name="mark")
	public List<Byte> marks;
	
	public StudentMarks() {
		marks = new ArrayList<>();
	}
	
	public StudentMarks(String student, List<Byte> marks) {
		this.student = student;
		this.marks = marks;
	}
}
