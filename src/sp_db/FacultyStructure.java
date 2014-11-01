package sp_db;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FacultyStructure {
	private List<Department> deps;
	
	public FacultyStructure() {
		deps = new ArrayList<>();
	}
	
	public void addDep(Department dep) {
		deps.add(dep);
	}
	
	public Set<Integer> getGroupStudents(String group) {
		Set<Integer> idStudents;
		for(Department d : deps) {
			idStudents = d.findGroupStudents(group);
			if(idStudents != null) 
				return idStudents;
		}
		return null;
	}
	
	//TODO delete
	public void tempInit() {
		
		Department spo = new Department("ัฯฮ");
		spo.addWorker(5);
		spo.addWorker(6);
		
		HashSet<Integer> ids = new HashSet<>();
		ids.add(7); ids.add(8);
		spo.addGroup("ภั-111", ids);
		
		ids = new HashSet<>();
		ids.add(9); ids.add(10);
		spo.addGroup("ภั-112", ids);
		
		ids = new HashSet<>();
		ids.add(11); ids.add(12);
		spo.addGroup("ภั-121", ids);
		
		ids = new HashSet<>();
		ids.add(13);
		spo.addGroup("ภั-131", ids);
		
		ids = new HashSet<>();
		ids.add(14);
		spo.addGroup("ภั-132", ids);
		
		deps.add(spo);
	}
	
	//TODO delete
	public void print() {
		for(Department d : deps) {
			d.print();
		}
	}	
}
