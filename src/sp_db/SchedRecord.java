package sp_db;

import sp_entities.Semester;

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
	
	//TODO delete 
	public void print() {
		System.out.println("teacher: " + idTeacher +", sem: " + semester);
		System.out.println("  subj: " + subj + ", group: " + group);
	}
}
