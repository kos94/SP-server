package sp_db;

import java.util.*;
import sp_entities.GroupSubjectMarks;
import sp_entities.Semesters;
import sp_entities.UserStatus;
import sp_entities.Semester;

public class DB {
	private Users users;
	private FacultyStructure structure;
	private Schedule schedule;
	private Curators curators;
	private Marks marks;
	
	public DB() {
		//TODO initialization from xml files via JAXB 
		users = new Users();
		users.tempInit();
//		users.print();
		
		structure = new FacultyStructure();
		structure.tempInit();
//		structure.print();
		
		schedule = new Schedule();
		schedule.tempInit();
//		schedule.print();
		
		curators = new Curators();
		curators.tempInit();
//		curators.print();
		
		marks = new Marks();
		marks.tempInit();
//		marks.print();
	}
	
	public User login(int id, String password) {
		return users.login(id, password);
	}
	
	public Semesters getTeacherSemesters(int idTeacher) {
		return schedule.getTeacherSemesters(idTeacher);
	}
	
	public String[][] getCuratorSemesters(int curId) {
		// тут обращение к БД по поводу куратора
		String[][] sems = new String[2][];
		sems[0] = new String[]{ "300", "1", "2011" };
		sems[1] = new String[]{ "400", "2", "2011" };
		return sems;
	}
	
	public String[][] getDepSemesters(int depWorkerId) {
		// тут обращение к БД по поводу работника кафедры
		String[][] sems = new String[2][];
		sems[0] = new String[]{ "500", "1", "2012" };
		sems[1] = new String[]{ "600", "2", "2012" };
		return sems;
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
	
	public List<String> getTeacherGroups(int idTeacher, Semester semester, String subj) {
		return schedule.getTeacherGroups(idTeacher, semester, subj);
	}
	
	public String[][] getCuratorGroups(int curId, int semId) {
		String[][] groups = new String[2][];
		groups[0] = new String[]{ "3", "curator group 1" };
		groups[1] = new String[]{ "4", "curator group 2" };
		return groups;
	}
	
	public String[][] getDepGroups(int depWorkerId, int semId) {
		String[][] groups = new String[2][];
		groups[0] = new String[]{ "5", "dep group 1" };
		groups[1] = new String[]{ "6", "dep group 2" };
		return groups;
	}
	
	public List<String> getTeacherSubjects(int idTeacher, Semester semester) {
		return schedule.getTeacherSubjects(idTeacher, semester);
	}
	
	public String[][] getGroupSubjects(int groupId, int semId) {
		String[][] subjs = new String[2][];
		subjs[0] = new String[]{ "3", "group subj 1" };
		subjs[1] = new String[]{ "4", "group subj 2" };
		return subjs;
	}
	
	public GroupSubjectMarks getSubjectMarks(String group, String subject) {
		Set<Integer> idStudents = structure.getGroupStudents(group);
		System.out.println("ids for group " + group + ":");
		for(Integer id : idStudents) {
			System.out.print(id + " ");
		}
		System.out.println();
		
		Map<Integer, String> students = new HashMap<>();
		for(Integer id : idStudents) {
			students.put(id, users.getUserName(id));
		}
		
		return marks.getGroupSubjectMarks(students, subject);
	}
	
	public String[][] getStageMarks(int semId, int groupId, int stageId) {
		String[][] marks = new String[3][];
		marks[0] = new String[]{"Студент", "ООП", "Дискретная математика"};
		marks[1] = new String[]{"Михайлов С.А.", "30", "35"};
		marks[2] = new String[]{"Друзь А.А,", "50", "50"};
		return marks;
	}
	
	public String[][] getStudentMarks(int semId, int studId) {
		String[][] marks = new String[2][];
		marks[0] = new String[]{"Теория алгоритмов", "30", "45", "100"};
		marks[1] = new String[]{"Теория вероятности", "35", "35", "70"};
		return marks;
	}
}
