package sp_entities;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class Semester {
	@XmlAttribute
	public byte index;
	@XmlAttribute
	public int startYear;
	
	public Semester() {}
	public Semester(int index, int startYear) {
		this.index = (byte)index;
		this.startYear = startYear;
	}
	
	@Override
	public String toString() {
		return index + " " + startYear + "/" + (startYear+1);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		result = prime * result + startYear;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Semester other = (Semester) obj;
		if (index != other.index)
			return false;
		if (startYear != other.startYear)
			return false;
		return true;
	}
}