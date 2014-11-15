package sp_db;

import java.util.*;
import javax.xml.bind.annotation.*;
import sp_entities.Semester;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Schedule {
	List<SchedRecord> records;
	
	public Schedule() {
		records = new ArrayList<>();
	}
	
	public void addRecord(SchedRecord rec) {
		records.add(rec);
	}
	
	public Set<Semester> getTeacherSemesters(int idTeacher) {
		Set<Semester> semSet = new HashSet<>();
		for(SchedRecord r : records) {
			if(r.idTeacher == idTeacher) 
				semSet.add(r.semester);
		}
		return semSet;
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
	
	public Set<Semester> getGroupsSemesters(Set<String> groups) {
		Set<Semester> semesters = new HashSet<>();
		for(SchedRecord r : records) {
			if(groups.contains(r.group)) {
				semesters.add(r.semester);
			}
		}
		return semesters;
	}
	
	public Set<Semester> getGroupSemesters(String group) {
		Set<Semester> semesters = new HashSet<>();
		for(SchedRecord r : records) {
			if(group.equals(r.group)) {
				semesters.add(r.semester);
			}
		}
		return semesters;
	}
	
	public Set<String> filterGroupsBySemester(Set<String> groupsSet, Semester sem) {
		Set<String> semGroups = new HashSet<>();
		for(SchedRecord r : records) {
			if(groupsSet.contains(r.group) && r.semester.equals(sem)) {
				semGroups.add(r.group);
			}
		}
		return semGroups;
	}
	
	public Set<String> getGroupSubjectsInSemester(String group, Semester sem) {
		HashSet<String> subjects = new HashSet<>();
		for(SchedRecord r : records) {
			if(r.group.equals(group) && r.semester.equals(sem)) {
				subjects.add(r.subj);
			}
		}
		return subjects;
	}
	
	public Semester getSubjectSemester(String subject, String group) {
		for(SchedRecord r : records) {
			if(r.group.equals(group) && r.subj.equals(subject)) {
				return r.semester;
			}
		}
		return null;
	}
	
	public boolean checkTeacherSubjectRights(int idTeacher, String subj, String group) {
		for(SchedRecord r : records) {
			if(r.idTeacher == idTeacher && r.group.equals(group) 
					&& r.subj.equals(subj) ) {
				return true;
			}
		}
		return false;
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
