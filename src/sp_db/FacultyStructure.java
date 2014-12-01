package sp_db;

import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FacultyStructure {
	@XmlElement(name = "department")
	private List<Department> deps;

	public FacultyStructure() {
		deps = new ArrayList<>();
	}

	public void addDep(Department dep) {
		deps.add(dep);
	}

	public Set<Integer> getGroupStudents(String group) {
		Set<Integer> idStudents;
		for (Department d : deps) {
			idStudents = d.findGroupStudents(group);
			if (idStudents != null)
				return idStudents;
		}
		return null;
	}

	public Set<Integer> getFlowStudents(String flow) {
		Set<Integer> idStudents;
		for (Department d : deps) {
			idStudents = d.findFlowStudents(flow);
			if (idStudents != null)
				return idStudents;
		}
		return null;
	}
	
	protected Department getWorkerDepartment(int idWorker) {
		for (Department d : deps) {
			if (d.hasWorker(idWorker)) {
				return d;
			}
		}
		return null;
	}

	public Set<String> getDepGroups(String department) {
		for (Department d : deps) {
			if (d.getName().equals(department)) {
				return d.getGroups();
			}
		}
		return null;
	}
	
	public Set<String> getFlowGroups(String flow) {
		for (Department d : deps) {
			Set<String> groups = d.findFlowGroups(flow);
			if(d != null) return groups;
		}
		return null;
	}
	
	public String getStudentGroup(int idStudent) {
		for (Department d : deps) {
			String group = d.getStudentGroup(idStudent);
			if(group != null) return group;
		}
		return null;
	}
	
	public Set<String> getFlowsOfGroups(Set<String> groups) {
		Set<String> flows = new HashSet<>();
		for (Department d : deps) {
			flows.addAll( d.getFlowsOfGroups(groups) );
		}
		return flows;
	}
}
