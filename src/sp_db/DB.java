package sp_db;

import java.io.IOException;
import java.util.*;

import sp_entities.*;

public class DB {
	static final String DB_PATH = ".\\db\\";
	private Users users;
	private FacultyStructure structure;
	private Schedule schedule;
	private Curators curators;
	private Marks marks;
	
	private void loadFromXML() {
		users = (Users) XMLSerializer
				.loadObject( DB_PATH + "users.xml", Users.class);
		structure = (FacultyStructure) XMLSerializer
				.loadObject(DB_PATH + "structure.xml", FacultyStructure.class);
		schedule = (Schedule) XMLSerializer
				.loadObject(DB_PATH + "schedule.xml", Schedule.class);
		curators = (Curators) XMLSerializer
				.loadObject(DB_PATH + "curators.xml", Curators.class);
		marks = (Marks) XMLSerializer
				.loadObject(DB_PATH + "marks.xml", Marks.class);
	}
	
	public DB() {
		loadFromXML();
	}
	
	public User login(int id, String password) {
		return users.login(id, password);
	}
	
	public String getUserName(int idUser) {
		return users.getUserName(idUser);
	}
	
	public Semesters getTeacherSemesters(int idTeacher) {
		return new Semesters(schedule.getTeacherSemesters(idTeacher));
	}
	
	public Semesters getCuratorSemesters(int curId) {
		return new Semesters(curators.getCuratorSemesters(curId));
	}
	
	public Semesters getDepSemesters(String dep) {
		Set<String> groups = structure.getDepGroups(dep);
		return new Semesters(schedule.getGroupsSemesters(groups));
	}
	
	public Semesters getStudentSemesters(int idStudent) {
		String group = getStudentGroup(idStudent);
		return new Semesters(schedule.getGroupSemesters(group));
	}
	
	public String getStudentGroup(int idStudent) {
		return structure.getStudentGroup(idStudent);
	}
	
	public String getWorkerDepartment(int idWorker) {
		return structure.getWorkerDepartment(idWorker).getName();
	}
	
	public List<String> getTeacherGroups(int idTeacher, Semester semester, String subj) {
		Set<String> groups = schedule.getTeacherGroups(idTeacher, semester, subj);
		return new ArrayList<String>(groups);
	}
	
	public List<String> getTeacherFlows(int id, Semester sem, String subject) {
		Set<String> groups = schedule.getTeacherGroups(id, sem, subject);
		Set<String> flows = structure.getFlowsOfGroups(groups);
		return new ArrayList<String>(flows);
	}
	
	public String getCuratorGroup(int idCurator, Semester semester) {
		return curators.getCuratorGroup(idCurator, semester);
	}
	
	public List<String> getDepGroups(String dep, Semester sem) {
		Set<String> depGroups = structure.getDepGroups(dep);
		Set<String> semesterGroups = schedule.filterGroupsBySemester(depGroups, sem);
		return new ArrayList<String>(semesterGroups);
	}
	
	public List<String> getDepFlows(String dep, Semester sem) {
		Set<String> depGroups = structure.getDepGroups(dep);
		Set<String> semesterGroups = schedule.filterGroupsBySemester(depGroups, sem);
		Set<String> flows = structure.getFlowsOfGroups(semesterGroups);
		return new ArrayList<String>(flows);
	}
	
	public List<String> getTeacherSubjects(int idTeacher, Semester semester) {
		return schedule.getTeacherSubjects(idTeacher, semester);
	}
	
	public List<String> getGroupSubjects(String group, Semester semester) {
		return schedule.getGroupSubjects(group, semester);
	}
	
	public List<String> getFlowSubjects(String flow, Semester semester) {
		// flow subjects = any of its group subjects
		String group = structure.getFlowGroups(flow).iterator().next();
		return schedule.getGroupSubjects(group, semester);
	}
	
	public boolean checkTeacherSubjectRights(int id, String subj, String group) {
		return schedule.checkTeacherSubjectRights(id, subj, group);
	}
	
	public boolean checkCuratorSubjectRights(int id, String subject, String group) {
		Semester sem = schedule.getSubjectSemester(subject, group);
		String actualGroup = curators.getCuratorGroup(id, sem);
		return (group.equals(actualGroup));
	}
	
	public boolean checkCuratorGroupRights(int id, String group, Semester sem) {
		String actualGroup = curators.getCuratorGroup(id, sem);
		return (group.equals(actualGroup));
	}
	
	public boolean checkDepWorkerGroupRights(int id, String group) {
		Department d = structure.getWorkerDepartment(id);
		return d.getGroups().contains(group);
	}
	
	public GroupSubjectMarks getSubjectMarks(String group, String subject) {
		Set<Integer> idStudents = structure.getGroupStudents(group);
		Map<Integer, String> students = users.getUsersNames(idStudents);
		
		return marks.getGroupSubjectMarks(students, subject);
	}
	
	public GroupSubjectMarks getFlowSubjectMarks(String flow, String subject) {
		Set<Integer> idStudents = structure.getFlowStudents(flow);
		Map<Integer, String> students = users.getUsersNames(idStudents);
		
		return marks.getGroupSubjectMarks(students, subject);
	}
	
	public GroupStageMarks getStageMarks(String group, Semester sem, int stage) {
		List<String> subjects = schedule.getGroupSubjects(group, sem);
		Set<Integer> idStudents = structure.getGroupStudents(group);
		Map<Integer, String> students = users.getUsersNames(idStudents);
		
		return marks.getGroupStageMarks(subjects, students, stage);
	}
	
	public GroupStageMarks getFlowStageMarks(String flow, Semester sem,
			int stage) {
		List<String> subjects = getFlowSubjects(flow, sem);
		Set<Integer> idStudents = structure.getFlowStudents(flow);
		Map<Integer, String> students = users.getUsersNames(idStudents);

		return marks.getGroupStageMarks(subjects, students, stage);
	}
	
	public StudentSemMarks getStudentMarks(int idStudent, Semester sem) {
		String group = structure.getStudentGroup(idStudent);
		Set<String> subjects = schedule.getGroupSubjectsInSemester(group, sem);
		return marks.getStudentMarks(idStudent, subjects);
	}

	public boolean checkTeacherFlowSubjectRights(int id, String subject,
			String flow) {
		Set<String> flowGroups = structure.getFlowGroups(flow);
		Set<String> teacherGroups = schedule.getTeacherGroups(id, subject);
		for(String g : teacherGroups) {
			if(flowGroups.contains(g)) return true;
		}
		return false;
	}

	public boolean checkDepWorkerFlowRights(int id, String flow) {
		Set<String> flows = structure.getWorkerDepartment(id).getFlows();
		return flows.contains(flow);
	}
}
