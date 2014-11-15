package sp_db;

import java.util.*;

import sp_entities.*;

public class DB {
	private Users users;
	private FacultyStructure structure;
	private Schedule schedule;
	private Curators curators;
	private Marks marks;
	
	//TODO delete (need only to generate DB)
	private void saveToXML() {
		XMLSerializer.saveObject(users, "C:\\ws\\users.xml");
		XMLSerializer.saveObject(structure, "C:\\ws\\structure.xml");
		XMLSerializer.saveObject(schedule, "C:\\ws\\schedule.xml");
		XMLSerializer.saveObject(curators, "C:\\ws\\curators.xml");
		XMLSerializer.saveObject(marks, "C:\\ws\\marks.xml");
	}

	private void loadFromXML() {
		users = (Users) XMLSerializer
				.loadObject("C:\\ws\\users.xml", Users.class);
		structure = (FacultyStructure) XMLSerializer
				.loadObject("C:\\ws\\structure.xml", FacultyStructure.class);
		schedule = (Schedule) XMLSerializer
				.loadObject("C:\\ws\\schedule.xml", Schedule.class);
		curators = (Curators) XMLSerializer
				.loadObject("C:\\ws\\curators.xml", Curators.class);
		marks = (Marks) XMLSerializer
				.loadObject("C:\\ws\\marks.xml", Marks.class);
	}
	
	public DB() {
//		users = new Users();
//		users.tempInit();
//		
//		structure = new FacultyStructure();
//		structure.tempInit();
//		
//		schedule = new Schedule();
//		schedule.tempInit();
//		
//		curators = new Curators();
//		curators.tempInit();
//		
//		marks = new Marks();
//		marks.tempInit();

		loadFromXML();
		
//		users.print();
//		structure.print();
//		schedule.print();
//		curators.print();
//		marks.print();
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
		return schedule.getTeacherGroups(idTeacher, semester, subj);
	}
	
	public String getCuratorGroup(int idCurator, Semester semester) {
		return curators.getCuratorGroup(idCurator, semester);
	}
	
	public List<String> getDepGroups(String dep, Semester sem) {
		Set<String> depGroups = structure.getDepGroups(dep);
		Set<String> semesterGroups = schedule.filterGroupsBySemester(depGroups, sem);
		return new ArrayList<String>(semesterGroups);
	}
	
	public List<String> getTeacherSubjects(int idTeacher, Semester semester) {
		return schedule.getTeacherSubjects(idTeacher, semester);
	}
	
	public List<String> getGroupSubjects(String group, Semester semester) {
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
	
	public GroupStageMarks getStageMarks(String group, Semester sem, int stage) {
		List<String> subjects = schedule.getGroupSubjects(group, sem);
		Set<Integer> idStudents = structure.getGroupStudents(group);
		Map<Integer, String> students = users.getUsersNames(idStudents);
		
		return marks.getGroupStageMarks(subjects, students, stage);
	}
	
	public StudentSemMarks getStudentMarks(int idStudent, Semester sem) {
		String group = structure.getStudentGroup(idStudent);
		Set<String> subjects = schedule.getGroupSubjectsInSemester(group, sem);
		return marks.getStudentMarks(idStudent, subjects);
	}
}
