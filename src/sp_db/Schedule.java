package sp_db;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import sp_entities.Semester;
import sp_entities.Semesters;

public class Schedule {
	List<SchedRecord> records;
	
	public Schedule() {
		records = new ArrayList<>();
	}
	
	public void addRecord(SchedRecord rec) {
		records.add(rec);
	}
	
	public Semesters getTeacherSemesters(int idTeacher) {
		HashSet<Semester> semSet = new HashSet<>();
		List<Semester> semList = new ArrayList<>();
		for(SchedRecord r : records) {
			if(r.idTeacher == idTeacher) 
				semSet.add(r.semester);
		}
		semList.addAll(semSet);
		return new Semesters(semList);
	}
	
	public List<String> getTeacherSubjects(int idTeacher, Semester semester) {
		HashSet<String> subjSet = new HashSet<>();
		List<String> subjList = new ArrayList<>();
		for(SchedRecord r : records) {
			if(r.idTeacher == idTeacher && r.semester.equals(semester)) {
				subjSet.add(r.subj);
			}
		}
		subjList.addAll(subjSet);
		return subjList;
	}
	
	public List<String> getTeacherGroups(int idTeacher, Semester semester, String subject) {
		List<String> groups = new ArrayList<>();
		for(SchedRecord r : records) {
			if(r.idTeacher == idTeacher && r.semester.equals(semester)
					&& r.subj.equals(subject)) {
				groups.add(r.group);
			}
		}
		return groups;
	}
	
	public List<String> getGroupSubjects(String group, Semester sem) {
		List<String> subjects = new ArrayList<>();
		for(SchedRecord r : records) {
			if(r.semester.equals(sem) && r.group.equals(group)) {
				subjects.add(r.subj);
			}
		}
		return subjects;
	}
	
	//TODO delete
	public void tempInit() {
		Semester sem1_2012 = new Semester(1, 2012);
		Semester sem2_2012 = new Semester(2, 2012);
		Semester sem1_2013 = new Semester(1, 2013);
		Semester sem1_2014 = new Semester(1, 2014);
		
		records.add(new SchedRecord(1, sem1_2012, "Дискретная математика", "АС-111"));
		records.add(new SchedRecord(1, sem1_2012, "Дискретная математика", "АС-112"));
		records.add(new SchedRecord(1, sem2_2012, "Дискретные структуры", "АС-111"));
		records.add(new SchedRecord(1, sem2_2012, "Дискретные структуры", "АС-112"));
		records.add(new SchedRecord(2, sem2_2012, "ООП", "АС-111"));
		
		records.add(new SchedRecord(2, sem2_2012, "ООП", "АС-112"));
		records.add(new SchedRecord(2, sem1_2013, "Конструирование ПО", "АС-111"));
		records.add(new SchedRecord(2, sem1_2013, "Конструирование ПО", "АС-112"));
		records.add(new SchedRecord(1, sem1_2014, "Качество ПО", "АС-111"));
		records.add(new SchedRecord(1, sem1_2014, "Качество ПО", "АС-112"));
		
		records.add(new SchedRecord(2, sem1_2014, "Конструирование ПО", "АС-121"));
		records.add(new SchedRecord(2, sem1_2014, "Конструирование ПО", "АС-122"));
		records.add(new SchedRecord(1, sem1_2014, "Дискретная математика", "АС-131"));
		records.add(new SchedRecord(1, sem1_2014, "Дискретная математика", "АС-132"));
	}
	
	//TODO delete
	public void print() {
		System.out.println("===============\nSchedule: ");
		for(SchedRecord r : records) {
			r.print();
		}
	}
}
