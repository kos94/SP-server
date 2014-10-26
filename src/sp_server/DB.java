package sp_server;

import java.util.ArrayList;
import java.util.List;

public class DB {
	public DB() {
		// тут инициализация всей ереси с БД
	}
	
	public UserStatus login(int univerID) {
		return UserStatus.TEACHER;
	}
	
	public String[][] getTeacherSemesters(int teachId) {
		// тут обращение к БД по поводу препода
		String[][] sems = new String[2][];
		sems[0] = new String[]{ "100", "1", "2010" };
		sems[1] = new String[]{ "200", "2", "2010" };
		return sems;
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
	
	public String[][] getTeacherGroups(int teachId, int semId, int subjId) {
		String[][] groups = new String[2][];
		groups[0] = new String[]{ "1", "teacher group 1" };
		groups[1] = new String[]{ "2", "teacher group 2" };
		return groups;
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
	
	public String[][] getTeacherSubjects(int teachId, int semId) {
		String[][] subjs = new String[2][];
		subjs[0] = new String[]{ "1", "teacher subj 1" };
		subjs[1] = new String[]{ "2", "teacher subj 2" };
		return subjs;
	}
	
	public String[][] getGroupSubjects(int groupId, int semId) {
		String[][] subjs = new String[2][];
		subjs[0] = new String[]{ "3", "group subj 1" };
		subjs[1] = new String[]{ "4", "group subj 2" };
		return subjs;
	}
	
	public String[][] getSubjectMarks(int semId, int groupId, int subjId) {
		String[][] marks = new String[2][];
		marks[0] = new String[]{"Иванов П.П.", "45", "40", "85" };
		marks[1] = new String[]{"Гончаренко П.С.", "40", "41", "90" };
		return marks;
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
