package sp_entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.annotation.*;

import sp_entities.GroupStageMarks.StudentStageMarks;

@XmlRootElement(name = "subjMarks")
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupSubjectMarks implements IMarks {
	private ArrayList<StudentSubjMarks> studMarks;
	private byte[] stageDebts;
	private float[] stageAvg;

	public GroupSubjectMarks() {
		studMarks = new ArrayList<>();
		stageDebts = new byte[3];
		stageAvg = new float[3];
	}

	public void addMark(String student, int m1, int m2, int m3) {
		studMarks.add(new StudentSubjMarks(student, (byte)m1, (byte)m2, (byte)m3));
	}

	public int getMarksNumber() {
		return studMarks.size();
	}

	public StudentSubjMarks getStudentMark(int i) {
		if (i < 0 || i >= studMarks.size())
			return null;
		return studMarks.get(i);
	}

	public byte getDebtCount(int stage) {
		if (stage < 0 || stage >= stageDebts.length)
			return -1;
		return stageDebts[stage];
	}

	public float getAvgMark(int stage) {
		if (stage < 0 || stage >= stageAvg.length)
			return -1.0f;
		return stageAvg[stage];
	}

	public void countAggregation() {
		int[] c = new int[3];
		for (int i = 0; i < 3; i++) {
			stageAvg[i] = 0.0f;
			stageDebts[i] = 0;
		}
		for (StudentSubjMarks ssm : studMarks) {
			for (int i = 0; i < 3; i++) {
				byte mark = ssm.marks[i];
				if (mark == -1) { // -1 means that there is no mark yet
					stageDebts[i] = -1;
					stageAvg[i] = -1.0f;
				} else if (mark == 0) {
					stageDebts[i]++;
				} else {
					c[i]++;
					stageAvg[i] += mark;
				}
			}
		}

		for (int i = 0; i < 3; i++) {
			if (stageAvg[i] != -1) {// -1 means that there is no mark yet
				stageAvg[i] /= c[i];
				String twoSigns = String.format("%.2f", stageAvg[i]).replace(
						",", ".");
				stageAvg[i] = Float.parseFloat(twoSigns);
			}
		}
	}

	@Override
	public void sortByFirstColumn() {
		Collections.sort(studMarks, new StudentSubjMarksComparator());
	}

	// TODO delete
	public void printMarks() {
		for (StudentSubjMarks ssm : studMarks) {
			System.out.println(ssm.student);
			for (Byte m : ssm.marks) {
				System.out.print(m + " ");
			}
			System.out.println("");
		}
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class StudentSubjMarks {
		public String student;
		@XmlElement(name = "mark")
		public byte[] marks;

		public StudentSubjMarks() {
			marks = new byte[3];
		}

		public StudentSubjMarks(String student, byte m1, byte m2, byte m3) {
			this();
			this.student = student;
			marks[0] = m1;
			marks[1] = m2;
			marks[2] = m3;
		}
	}
	
	class StudentSubjMarksComparator implements Comparator<StudentSubjMarks> {
		@Override
		public int compare(StudentSubjMarks s0, StudentSubjMarks s1) {
			return s0.student.compareTo(s1.student);
		}
	}
}