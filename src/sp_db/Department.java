package sp_db;

import java.util.ArrayList;
import java.util.List;

public class Department {
	protected String name;
	protected List<Integer> idWorkers;
	protected List<Group> groups;
	
	public Department() {
		idWorkers = new ArrayList<>();
		groups = new ArrayList<>();
	}
	
	public Department(String depName) {
		this();
		name = depName;
	}
	
	public void addWorker(int idWorker) {
		idWorkers.add(idWorker);
	}
	
	public void addGroup(Group g) {
		groups.add(g);
	}
	
	//TODO delete
	public void print() {
		System.out.println("========================\ndep name: " + name);
		for(Integer idW : idWorkers) {
			System.out.print(idW + " ");
		}
		System.out.println("\nGroups: ");
		for(Group g : groups) {
			g.print();
		}
	}
}
