package sp_db;

import java.util.ArrayList;
import java.util.List;

public class Group {
	protected String name;
	protected List<Integer> idStudents;
	
	public Group() {
		idStudents = new ArrayList<>();
	}
	
	public Group(String groupName) {
		this();
		name = groupName;
	}
	
	public void addStudent(int idStudent) {
		idStudents.add(idStudent);
	}
	
	//TODO delete 
	public void print() {
		System.out.println("group: " + name);
		for(Integer id : idStudents) {
			System.out.print(id + " ");
		}
		System.out.println();
	}
}
