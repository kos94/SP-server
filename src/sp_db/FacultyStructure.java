package sp_db;

import java.util.ArrayList;
import java.util.List;

public class FacultyStructure {
	private List<Department> deps;
	
	public FacultyStructure() {
		deps = new ArrayList<>();
	}
	
	//TODO delete
	public void print() {
		for(Department d : deps) {
			d.print();
		}
	}
	
	public void addDep(Department dep) {
		deps.add(dep);
	}
}
