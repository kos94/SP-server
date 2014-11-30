package sp_entities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="stageMarks")
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupStageMarks implements IMarks {
	@XmlElementWrapper
	@XmlElement(name="subj")
	private List<String> subjects;
	private List<StudentStageMarks> studMarks;
	private List<Float> subjectAvg;
	private List<Byte> subjectDebts;
	
	public GroupStageMarks() {
		subjects = new ArrayList<>();
		studMarks = new ArrayList<>();
		subjectAvg = new ArrayList<>();
		subjectDebts = new ArrayList<>();
	}

	public void addSubject(String subj) {
		subjects.add(subj);
	}
	
	public void addMark(String student, List<Byte> marks) {
		studMarks.add(new StudentStageMarks(student, marks));
	}
	
	public void setSubjects(List<String> subjs) {
		subjects = subjs;
	}
	
	public void sortByFirstColumn() {
		Collections.sort(studMarks, new StudentStageMarksComparator());
	}
	
	@Override
	public void countAggregation() {
		int nSubj = subjects.size(), nStud = studMarks.size();
		System.out.println("count agregation for " + nSubj + " subjs & " + nStud + " stud");
		float[] subjSum = new float[nSubj];
		byte[] subjDebts = new byte[nSubj];
		int[] subjGoodMarks = new int[nSubj];
		
		if(!subjectAvg.isEmpty()) {
			subjectAvg.clear();
			subjectDebts.clear();
		}
		int i = 0, j;
		
		for(StudentStageMarks m: studMarks) {
			j = 0;
			float stSum = 0;
			int stGoodMarks = 0, stDebts = 0;
			for(byte mark : m.marks) {
				if(mark == -1) {
					stSum = subjSum[j] = -1;
					stDebts = subjDebts[i] = -1;
				} else if (mark == 0) {
					stDebts++;
					subjDebts[j]++;
				} else {
					stSum += mark;
					stGoodMarks++;
					subjSum[j] += mark;
					subjGoodMarks[j]++;
				}
				j++;
			}
			float studAvg = (stSum == -1 || stGoodMarks == 0)? -1 : (stSum / stGoodMarks);
//			System.out.println("st sum: " + stSum + ", n: " + stGoodMarks + ", avg: " + studAvg);
			String twoSigns = String.format("%.2f", studAvg).replace(",", ".");
			m.avgMark = Float.parseFloat(twoSigns);
			m.nDebts = (byte)stDebts;
			i++;
		}
		
		for(i=0; i<nSubj; i++) {
			float subjAvg = (subjSum[i] == -1)? -1 
					: (subjSum[i] / subjGoodMarks[i]);
			String twoSigns = String.format("%.2f", subjAvg).replace(",", ".");
			subjectAvg.add(Float.parseFloat(twoSigns));
			subjectDebts.add(subjDebts[i]);
		}
	}
	
	public void printMarks() {
		for(String subj : subjects) {
			System.out.println(subj);
		}
		System.out.println("==========");
		
		for(StudentStageMarks ssm : studMarks) {
			System.out.println(ssm.student);
			for(Byte m : ssm.marks) {
				System.out.print(m + " ");
			}
			System.out.println();
		}
	}
	public int getStudentsNumber() { return studMarks.size(); }
	public List<String> getSubjects() { return subjects; }
	public StudentStageMarks getStudentMark(int i) { 
		if(i < 0 || i >= studMarks.size()) return null;
		return studMarks.get(i);
	}
	public byte getStudentDebts(int iStudent) { 
		return studMarks.get(iStudent).nDebts;
	}
	public float getStudentAvg(int iStudent) {
		return studMarks.get(iStudent).avgMark;
	}
	public List<Byte> getSubjDebts() { return subjectDebts; }
	public List<Float> getSubjAvg() { return subjectAvg; }
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class StudentStageMarks {
		public String student;
		@XmlElement(name="mark")
		public List<Byte> marks;
		public byte nDebts;
		public float avgMark;
		
		public StudentStageMarks() {
			marks = new ArrayList<>();
		}
		
		public StudentStageMarks(String student, List<Byte> marks) {
			this.student = student;
			this.marks = marks;
		}
	}
	
	class StudentStageMarksComparator implements Comparator<StudentStageMarks> {
		@Override
		public int compare(StudentStageMarks s0, StudentStageMarks s1) {
			return s0.student.compareTo(s1.student);
		}
	}
}