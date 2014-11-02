package sp_entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Semesters {
	@XmlElement(name="sem")
	private List<Semester> sems;
	
	public Semesters() {
		sems = new ArrayList<>();
	}
	
	public Semesters(List<Semester> semesters) {
		sems = semesters;
	}
	
	public Semesters(Set<Semester> semesters) {
		this();
		sems.addAll(semesters);
	}
	
	public void addSemester(int index, int startYear) {
		sems.add(new Semester(index, startYear));
	}
	
	public List<Semester> getSemesters() {
		return sems;
	}
	
	public void print() {
		for(Semester s : sems) {
			System.out.println(s);
		}
	}
}
