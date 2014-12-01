package sp_entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.annotation.*;

class SubjectMarksComparator implements Comparator<SubjectMarks> {
	@Override
	public int compare(SubjectMarks s0, SubjectMarks s1) {
		return s0.subj.compareTo(s1.subj);
	}
}

@XmlRootElement(name="studentMarks")
@XmlAccessorType(XmlAccessType.FIELD)
public class StudentSemMarks implements IMarks {
	private ArrayList<SubjectMarks> subjMarks;
	private byte[] stageDebts;
	private float[] stageAvg;
	
	public StudentSemMarks() {
		subjMarks = new ArrayList<>();
		stageDebts = new byte[3];
		stageAvg = new float[3];
	}
	
	public void addMark(String subj, int m1, int m2, int m3) {
		ArrayList<Integer> l = new ArrayList<>();
		l.add(m1); l.add(m2); l.add(m3); 
		subjMarks.add(new SubjectMarks(subj, l));
	}
	
	public int getSubjectsNumber() { 
		return subjMarks.size();
	}
	
	public SubjectMarks getSubjectMarks(int i) {
		if(i < 0 || i >= subjMarks.size()) return null;
		return subjMarks.get(i);
	}
	
	public byte getDebtCount(int stage) {
		if (stage < 0 || stage >= stageDebts.length)
			return ABSENT;
		return stageDebts[stage];
	}

	public float getAvgMark(int stage) {
		if (stage < 0 || stage >= stageAvg.length)
			return ABSENT;
		return stageAvg[stage];
	}
	
	public void sortByFirstColumn() {
		Collections.sort(subjMarks, new SubjectMarksComparator());
	}

	@Override
	public void countAggregation() {
		int[] c = new int[3];
		for (int i = 0; i < 3; i++) {
			stageAvg[i] = 0.0f;
			stageDebts[i] = 0;
		}
		for (SubjectMarks ssm : subjMarks) {
			for (int i = 0; i < 3; i++) {
				int mark = ssm.marks.get(i);
				if (mark == ABSENT) {
					stageDebts[i] = ABSENT;
					stageAvg[i] = ABSENT;
				} else if (mark < MIN_MARK[i]) {
					stageDebts[i]++;
				} else {
					c[i]++;
					stageAvg[i] += mark;
				}
			}
		}

		for (int i = 0; i < 3; i++) {
			if (stageAvg[i] != ABSENT && c[i] > 0) {
				stageAvg[i] /= c[i];
				String twoSigns = String.format("%.2f", stageAvg[i]).replace(
						",", ".");
				stageAvg[i] = Float.parseFloat(twoSigns);
			}
		}
	}
}