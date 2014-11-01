package sp_db;

public class StudentSubjMark {
	protected int idStudent;
	protected String subj;
	protected byte mark1;
	protected byte mark2;
	protected byte mark3;
	
	public StudentSubjMark() {}
	
	public StudentSubjMark(int id, String subject, int m1, int m2, int m3) {
		idStudent = id;
		subj = subject;
		mark1 = (byte)m1;
		mark2 = (byte)m2;
		mark3 = (byte)m3;
	}
	
	//TODO delete 
	public void print() {
		System.out.println("student: " + idStudent + ", subj: " + subj);
		System.out.println("  marks. 1: " + mark1 + ", 2: " + mark2 + ", 3: " + mark3);
	}
}
