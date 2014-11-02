package sp_db;

public class StudentSubjMark {
	protected int idStudent;
	protected String subj;
	protected byte [] marks;
	
	public StudentSubjMark() { marks = new byte[3]; }
	
	public StudentSubjMark(int id, String subject, int m1, int m2, int m3) {
		this();
		idStudent = id;
		subj = subject;
		marks[0] = (byte)m1;
		marks[1] = (byte)m2;
		marks[2] = (byte)m3;
	}
	
	//TODO delete 
	public void print() {
		System.out.println("student: " + idStudent + ", subj: " + subj);
		System.out.println("  marks. 1: " + marks[0] + ", 2: " + marks[1] + ", 3: " + marks[2]);
	}
}
