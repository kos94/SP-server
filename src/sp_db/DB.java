package sp_db;

import java.util.*;

import sp_entities.*;

public class DB {
	private Users users;
	private FacultyStructure structure;
	private Schedule schedule;
	private Curators curators;
	private Marks marks;
	
	//TODO delete (need only to generate DB
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
		
		users.print();
		structure.print();
		schedule.print();
		curators.print();
		marks.print();
	}
	
	public User login(int id, String password) {
		return users.login(id, password);
	}
	
	public Semesters getTeacherSemesters(int idTeacher) {
		return schedule.getTeacherSemesters(idTeacher);
	}
	
	public Semesters getCuratorSemesters(int curId) {
		return curators.getCuratorSemesters(curId);
	}
	
	public Semesters getDepSemesters(String dep) {
		Set<String> groups = structure.getDepGroups(dep);
		return schedule.getGroupsSemesters(groups);
	}
	
	public String[][] getGroupSemesters(int groupId) {
		// обращение к БД по поводу группы
		String[][] sems = new String[2][];
		sems[0] = new String[]{ "700", "1", "2013" };
		sems[1] = new String[]{ "800", "2", "2013" };
		return sems;
	}
	
	public String[][] getStudentSemesters(int studId) {
		// тут обращение к БД по поводу студента
		String[][] sems = new String[2][];
		sems[0] = new String[]{ "900", "1", "2014" };
		sems[1] = new String[]{ "1000", "2", "2014" };
		return sems;
	}
	
	public String getWorkerDepartment(int workerId) {
		return structure.getWorkerDepartment(workerId);
	}
	
	public List<String> getTeacherGroups(int idTeacher, Semester semester, String subj) {
		return schedule.getTeacherGroups(idTeacher, semester, subj);
	}
	
	public String getCuratorGroup(int idCurator, Semester semester) {
		return curators.getCuratorGroup(idCurator, semester);
	}
	
	public List<String> getDepGroups(String dep, Semester sem) {
		Set<String> depGroups = structure.getDepGroups(dep);
		return schedule.getSemesterGroups(depGroups, sem);
	}
	
	public List<String> getTeacherSubjects(int idTeacher, Semester semester) {
		return schedule.getTeacherSubjects(idTeacher, semester);
	}
	
	public List<String> getGroupSubjects(String group, Semester semester) {
		return schedule.getGroupSubjects(group, semester);
	}
	
	public GroupSubjectMarks getSubjectMarks(String group, String subject) {
		Set<Integer> idStudents = structure.getGroupStudents(group);
//		System.out.println("ids for group " + group + ":");
//		for(Integer id : idStudents) {
//			System.out.print(id + " ");
//		}
//		System.out.println();
		
		Map<Integer, String> students = users.getUsersNames(idStudents);
		
		return marks.getGroupSubjectMarks(students, subject);
	}
	
	public GroupStageMarks getStageMarks(Semester sem, String group, int stage) {
		List<String> subjects = schedule.getGroupSubjects(group, sem);
//		System.out.println("subjects for group " + group + ":");
//		for(String s : subjects) {
//			System.out.print(s + " ");
//		}
		
		Set<Integer> idStudents = structure.getGroupStudents(group);
//		System.out.println("ids for group " + group + ":");
//		for(Integer id : idStudents) {
//			System.out.print(id + " ");
//		}
//		System.out.println();
		
		Map<Integer, String> students = users.getUsersNames(idStudents);
		
		return marks.getGroupStageMarks(subjects, students, stage);
	}
	
	public String[][] getStudentMarks(int semId, int studId) {
		String[][] marks = new String[2][];
		marks[0] = new String[]{"Теория алгоритмов", "30", "45", "100"};
		marks[1] = new String[]{"Теория вероятности", "35", "35", "70"};
		return marks;
	}
}
