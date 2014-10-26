package sp_entities;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;

class Semester {
	@XmlAttribute
	public int index;
	@XmlAttribute
	public int startYear;
	
	public Semester() {}
	public Semester(int index, int startYear) {
		this.index = index;
		this.startYear = startYear;
	}
}

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Semesters {
	@XmlElement(name="sem")
	private ArrayList<Semester> sems;
	
	public Semesters() {
		sems = new ArrayList<>();
	}
	
	public void addSemester(int index, int startYear) {
		sems.add(new Semester(index, startYear));
	}
	
	public void print() {
		for(Semester s : sems) {
			System.out.println(s.index + " " + s.startYear);
		}
	}
}
