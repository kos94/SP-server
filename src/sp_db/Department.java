package sp_db;

import java.util.*;

public class Department {
	private String name;
	private List<Integer> idWorkers;
	private Map<String, Set<Integer>> grStudents;
	
	public Department() {
		idWorkers = new ArrayList<>();
		grStudents = new HashMap<>();
	}
	
	public Department(String depName) {
		this();
		name = depName;
	}
	
	public void addWorker(int idWorker) {
		idWorkers.add(idWorker);
	}
	
	public void addGroup(String groupName, Set<Integer> idStudents) {
		grStudents.put(groupName, idStudents);
	}
	
	public Set<Integer> findGroupStudents(String groupName) {
		return grStudents.get(groupName);
	}
	
	public Set<String> getGroups() {
		return grStudents.keySet();
	}
	
	public boolean hasWorker(int idWorker) {
		return idWorkers.contains(idWorker);
	}
	
	public String getName() {
		return name;
	}
	
	//TODO delete
	public void print() {
		System.out.print("========================\ndep name: " + name + "\nWorkers: ");
		for(Integer idW : idWorkers) {
			System.out.print(idW + " ");
		}
		System.out.println("\nGroups: ");
		System.out.println("===========\nGroups: ");
		for (Map.Entry<String, Set<Integer>> group : grStudents.entrySet())
		{
			System.out.println("group: " + group.getKey());
		    for(Integer idStudent : group.getValue()) {
		    	System.out.print(idStudent + " ");
		    }
		}
	}
}
