package sp_db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;


class GroupsMapEntry {
	public String key;
	public Set<Integer> idStud;
}

class GroupsMapType  {
	@XmlElement(name="entry")
	public List<GroupsMapEntry> entries = new ArrayList<>();
}

public class FlowMapAdapter extends XmlAdapter< GroupsMapType,Map<String, Set<Integer>> > {
	@Override
	public GroupsMapType marshal(Map<String, Set<Integer>> arg0)
			throws Exception {
		GroupsMapType myMapType = new GroupsMapType();
		for(Map.Entry<String, Set<Integer>> entry : arg0.entrySet()) {
			GroupsMapEntry myEntryType = new GroupsMapEntry();
			myEntryType.key = entry.getKey();
			myEntryType.idStud = entry.getValue();
			myMapType.entries.add(myEntryType);
		}
		return myMapType;
	}
	@Override
	public Map<String, Set<Integer>> unmarshal(GroupsMapType arg0)
			throws Exception {
		HashMap<String, Set<Integer>> map = new HashMap<>();
		for(GroupsMapEntry entry : arg0.entries) {
			map.put(entry.key, entry.idStud	);
		}
		return map;
	}
}