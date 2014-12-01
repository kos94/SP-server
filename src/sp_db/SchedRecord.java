package sp_db;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import sp_entities.Semester;

@XmlAccessorType(XmlAccessType.FIELD)
public class SchedRecord {
	protected int idTeacher;
	protected Semester semester;
	protected String subj;
	protected String group;
	
	public SchedRecord() {}
	
	public SchedRecord(int idTeach, Semester sem, String subject, String group) {
		idTeacher = idTeach;
		semester = sem;
		subj = subject;
		this.group = group;
	}
}
