package sp_entities;

import javax.xml.bind.annotation.XmlAttribute;

public class Semester {
	@XmlAttribute
	public byte index;
	@XmlAttribute
	public int startYear;
	
	public Semester() {}
	public Semester(byte index, int startYear) {
		this.index = index;
		this.startYear = startYear;
	}
	
	@Override
	public String toString() {
		return index + " " + startYear + "/" + (startYear+1);
	}
}