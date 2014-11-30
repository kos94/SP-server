package sp_db;

import java.util.*;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Department {
	private String name;
	@XmlElementWrapper
	@XmlElement(name="idW")
	private List<Integer> idWorkers;
	private Map<String, Flow> flows;
	
	public Department() {
		idWorkers = new ArrayList<>();
		flows = new HashMap<>();
	}
	
	public Department(String depName) {
		this();
		name = depName;
	}
	//TODO DELETE
	public void addWorker(int idWorker) {
		idWorkers.add(idWorker);
	}
	//TODO DELETE
	public void addGroup(String flow, String group, Set<Integer> idStudents) {
		Flow f = flows.get(flow);
		if(f == null) {
			f = new Flow();//put(group, idStudents);
			f.addGroup(group, idStudents);
			flows.put(flow,  f);
		} else {
			f.addGroup(group, idStudents);
		}
	}
	
	public Set<Integer> findGroupStudents(String groupName) {
		for (Map.Entry<String, Flow> flow : flows.entrySet()) {
			Set<Integer> students = flow.getValue().findGroupStudents(groupName);
			if(students != null) return students;
		}
		return null;
	}
	
	public Set<Integer> findFlowStudents(String flow) {
		Flow f = flows.get(flow);
		if(f == null) return null;
		return f.getFlowStudents();
	}
	
	public Set<String> getGroups() {
		Set<String> groups = new HashSet<>();
		for (Map.Entry<String, Flow> flow : flows.entrySet()) {
			groups.addAll(flow.getValue().getFlowGroups());
		}
		return groups;
	}
	
	public Set<String> findFlowGroups(String flow) {
		Flow f = flows.get(flow);
		if(f == null) return null;
		return f.getFlowGroups();
	}
	
	public String getStudentGroup(int idStudent) {
		for (Map.Entry<String, Flow> flow : flows.entrySet()) {
			String group = flow.getValue().getStudentGroup(idStudent);
			if(group != null) return group;
		}
		return null;
	}

	public Set<String> getFlowsOfGroups(Set<String> groups) {
		Set<String> groupFlows = new HashSet<>();
		for (Map.Entry<String, Flow> flow : flows.entrySet()) {
			Set<String> flowGroups = flow.getValue().getFlowGroups();

			for(Iterator<String> iter = groups.iterator(); iter.hasNext();) {
				String group = iter.next();
				if(flowGroups.contains(group)) {
					groupFlows.add(flow.getKey());
					iter.remove();
				}
			}
		}
		return groupFlows;
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
		for (Map.Entry<String, Flow> flow : flows.entrySet()) {
			System.out.println("Flow: " + flow.getKey());
		    flow.getValue().print();
		}
	}
}
