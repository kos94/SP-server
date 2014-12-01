package sp_db;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Flow {
	@XmlJavaTypeAdapter(FlowMapAdapter.class)
	@XmlElement(name="groups")
	private Map<String, Set<Integer>> grStudents;
	
	public Flow() {
		grStudents = new HashMap<>();
	}
	
	public Set<Integer> findGroupStudents(String group) {
		return grStudents.get(group);
	}
	
	public Set<Integer> getFlowStudents() {
		Set<Integer> students = new HashSet<>();
		for (Map.Entry<String, Set<Integer>> group : grStudents.entrySet()) {
			students.addAll( group.getValue() );
		}
		return students;
	}
	
	public String getStudentGroup(int idStudent) {
		for (Map.Entry<String, Set<Integer>> group : grStudents.entrySet()) {
			if(group.getValue().contains(idStudent))
				return group.getKey();
		}
		return null;
	}
	
	public Set<String> getFlowGroups() {
		return grStudents.keySet();
	}
}
