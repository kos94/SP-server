package sp_entities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="subjMarks")
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupSubjectMarks implements IMarks {
	private ArrayList<StudentMarks> studMarks;
	private byte[] stageDebts;
	private float[] stageAvg;
	
	public GroupSubjectMarks() {
		studMarks = new ArrayList<>();
		stageDebts = new byte[3];
		stageAvg = new float[3];
	}

	public void addMark(String student, int m1, int m2, int m3) {
		ArrayList<Byte> l = new ArrayList<>();
		l.add((byte)m1); l.add((byte)m2); l.add((byte)m3); 
		studMarks.add(new StudentMarks(student, l));
	}
	
	public int getMarksNumber() { return studMarks.size(); }
	public StudentMarks getStudentMark(int i) { 
		if(i < 0 || i >= studMarks.size()) return null;
		return studMarks.get(i);
	}
	public byte getDebtCount(int stage) {
		if(stage < 0 || stage >= stageDebts.length) return -1;
		return stageDebts[stage];
	}
	public float getAvgMark(int stage) {
		if(stage < 0 || stage >= stageAvg.length) return -1.0f;
		return stageAvg[stage];
	}
	
	public void countAggregation() {
		int[] c = new int[3];
		for(int i=0; i<3; i++) {
			stageAvg[i] = 0.0f;
			stageDebts[i] = 0;
		}
		for(StudentMarks ssm : studMarks) {
			for(int i=0; i<3; i++) {
				byte mark = ssm.marks.get(i);
				if(mark == -1) { // -1 means that there is no mark yet
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
		for(int i=0; i<3; i++) {
			if(stageAvg[i] != -1) {// -1 means that there is no mark yet
				stageAvg[i] /= c[i];
				String twoSigns = String.format("%.2f", stageAvg[i]).replace(",", ".");
				stageAvg[i] = Float.parseFloat(twoSigns);
			}
		}
	}
	
	@Override
	public void sortByFirstColumn() {
		Collections.sort(studMarks, new StudentMarksComparator());
	}
	
	//TODO delete
	public void printMarks() {
		for(StudentMarks ssm : studMarks) {
			System.out.println(ssm.student);
			for(Byte m : ssm.marks) {
				System.out.print(m + " ");
			}
			System.out.println("");
		}
	}

}