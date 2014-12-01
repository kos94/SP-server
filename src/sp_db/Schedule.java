package sp_db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.xml.bind.annotation.*;

import sp_entities.Semester;
import sp_entities.UserRole;

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
	
	public Set<String> getTeacherGroups(int idTeacher, Semester semester, String subject) {
		Set<String> groups = new HashSet<>();
		for(SchedRecord r : records) {
			if(r.idTeacher == idTeacher && r.semester.equals(semester)
					&& r.subj.equals(subject)) {
				groups.add(r.group);
			}
		}
		return groups;
	}
	
	public Set<String> getTeacherGroups(int idTeacher, String subject) {
		Set<String> groups = new HashSet<>();
		for(SchedRecord r : records) {
			if(r.idTeacher == idTeacher && r.subj.equals(subject)) {
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
}
