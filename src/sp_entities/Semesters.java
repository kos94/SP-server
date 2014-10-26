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
	
	public Semesters(String[][] dbData) {
		this();
		if (dbData.length == 0) return;
		// expects table with columns: id; index (1 or 2); startYear
		int colNum = dbData[0].length;
		assert (colNum == 3);
		for(String[] row : dbData) {
			int ind = Integer.parseInt(row[1]);
			int startYear = Integer.parseInt(row[2]);
			addSemester(ind, startYear);
		}
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
