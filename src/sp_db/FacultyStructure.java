package sp_db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.xml.bind.annotation.*;

import sp_entities.UserRole;

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
	
	public String getStudentGroup(int idStudent) {
		for (Department d : deps) {
			String group = d.getStudentGroup(idStudent);
			if(group != null) return group;
		}
		return null;
	}
	
	// TODO delete
	public void tempInit() throws IOException {
		FileInputStream in = new FileInputStream(DB.DB_PATH + "structure.txt");
		BufferedReader br = new BufferedReader(
				new InputStreamReader(in, "utf8"));

		String strLine;
		UserRole role = null;
		int c = 0;

		while ((strLine = br.readLine()) != null) {
			if(strLine.equals("")) continue;
			Department dep = new Department(strLine);
			
			String[] idWorkers = br.readLine().trim().split(" ");
			for(int i=0; i<idWorkers.length; i++) {
				dep.addWorker(Integer.parseInt(idWorkers[i]));
			}
			
			while ((strLine = br.readLine()) != null) {
				if(strLine.equals("")) break;
				String[] gr = strLine.trim().split(" ");
				String group = gr[0];
				HashSet<Integer> idStudents = new HashSet<>();
				for(int i=1; i<gr.length; i++) {
					idStudents.add(Integer.parseInt(gr[i]));
				}
				dep.addGroup(group, idStudents);
			}
			
			deps.add(dep);
		}
		in.close();
	}
//	// TODO delete
//	public void tempInit() {
//
//		Department spo = new Department("ัฯฮ");
//		spo.addWorker(5);
//		spo.addWorker(6);
//
//		HashSet<Integer> ids = new HashSet<>();
//		ids.add(7);
//		ids.add(8);
//		spo.addGroup("ภั-111", ids);
//
//		ids = new HashSet<>();
//		ids.add(9);
//		ids.add(10);
//		spo.addGroup("ภั-112", ids);
//
//		ids = new HashSet<>();
//		ids.add(11);
//		ids.add(12);
//		spo.addGroup("ภั-121", ids);
//
//		ids = new HashSet<>();
//		ids.add(13);
//		spo.addGroup("ภั-131", ids);
//
//		ids = new HashSet<>();
//		ids.add(14);
//		spo.addGroup("ภั-132", ids);
//
//		deps.add(spo);
//
//		// TODO delete, non-logical values here, for testing
//		Department at = new Department("AT");
//		int c = 14;
//		at.addWorker(++c);
//		at.addWorker(++c);
//
//		ids = new HashSet<>();
//		ids.add(++c);
//		ids.add(++c);
//		at.addGroup("AT-111", ids);
//
//		ids = new HashSet<>();
//		for (int i = 0; i < 4; i++)
//			ids.add(++c);
//		at.addGroup("AT-112", ids);
//
//		deps.add(at);
//	}

	// TODO delete
	public void print() {
		for (Department d : deps) {
			d.print();
		}
	}
}
