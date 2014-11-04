package sp_db;

import java.util.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

class CuratorsMapEntry {
	public Integer key;
	public List<CuratorWork> work;
}

class CuratorsMapType  {
	@XmlElement(name="entry")
	public List<CuratorsMapEntry> entries = new ArrayList<>();
}

public class CuratorsMapAdapter extends XmlAdapter<CuratorsMapType, Map<Integer, List<CuratorWork>> > {
	@Override
	public CuratorsMapType marshal(Map<Integer, List<CuratorWork>> arg0)
			throws Exception {
		CuratorsMapType myMapType = new CuratorsMapType();
		for(Map.Entry<Integer, List<CuratorWork>> entry : arg0.entrySet()) {
			CuratorsMapEntry myEntryType = new CuratorsMapEntry();
			myEntryType.key = entry.getKey();
			myEntryType.work = entry.getValue();
			myMapType.entries.add(myEntryType);
		}
		return myMapType;
	}
	@Override
	public Map<Integer, List<CuratorWork>> unmarshal(CuratorsMapType arg0)
			throws Exception {
		HashMap<Integer, List<CuratorWork>> map = new HashMap<>();
		for(CuratorsMapEntry entry : arg0.entries) {
			map.put(entry.key, entry.work);
		}
		return map;
	}
}