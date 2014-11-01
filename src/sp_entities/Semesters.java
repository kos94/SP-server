package sp_entities;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Semesters {
	@XmlElement(name="sem")
	private ArrayList<Semester> sems;
	
	public Semesters() {
		sems = new ArrayList<>();
	}
	
	public void addSemester(byte index, int startYear) {
		sems.add(new Semester(index, startYear));
	}
	
	public void print() {
		for(Semester s : sems) {
			System.out.println(s.index + " " + s.startYear);
		}
	}
}
