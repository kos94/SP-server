package sp_entities;

import java.util.*;
import javax.xml.bind.annotation.*;

class SemesterComparator implements Comparator<Semester> {
	@Override
	public int compare(Semester s0, Semester s1) {
		int dif = s0.startYear - s1.startYear;
		if(dif != 0) return dif;
		return s0.index - s1.index;
	}
	
}

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Semesters {
	@XmlElement(name="sem")
	private List<Semester> sems;
	private static SemesterComparator comp = new SemesterComparator();
	
	public Semesters() {
		sems = new ArrayList<>();
	}
	
	public Semesters(List<Semester> semesters) {
		sems = semesters;
	}
	
	public Semesters(Set<Semester> semesters) {
		this();
		sems.addAll(semesters);
		Collections.sort(sems, comp);
	}
	
	public void addSemester(int index, int startYear) {
		sems.add(new Semester(index, startYear));
	}
	
	public List<Semester> getSemesters() {
		return sems;
	}
}
